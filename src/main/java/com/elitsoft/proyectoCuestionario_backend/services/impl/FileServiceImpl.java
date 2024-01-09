package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {

    private final String uploadDirectory = "./cvs";

    private final Path root = Paths.get("uploads");


    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("no se puede iniciar el storage");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {

            String fileName = file.getOriginalFilename();
            Path filePath = this.root.resolve(file.getOriginalFilename());
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                String mensajeSobrescritura = "El archivo '" + fileName + "' se ha sobrescrito.";
                System.out.println(mensajeSobrescritura);
            }

            Files.copy(file.getInputStream(), filePath);

        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo", e);
        }
    }


    @Override
    public Resource load(String filename) {

        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("no se puede leer el archivo");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }


    }

    @Override
    public void deleteall() {
        FileSystemUtils.deleteRecursively(root.toFile());

    }

    @Override
    public Stream<Path> loadAll(Long usr_id) {
        try {
            Path userFolder = this.root.resolve(usr_id.toString());
            return Files.walk(userFolder, 1)
                    .filter(path -> !path.equals(userFolder))
                    .map(userFolder::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
    }

    @Override
    public String deletefile(String filename) {
        try {
            Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
            return "Borrado";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error Borrando";

        }

    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.deleteIfExists(path);
    }


    @Override
    public String saveFile(MultipartFile file) throws IOException {

        if (!isPDF(file)){
            throw new IOException("File is not PDF");
        }

        Path uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        File uploadDir = new File(uploadPath.toString());
        if (!uploadDir.exists()) {
            if(!uploadDir.mkdirs()){
                throw new IOException("Couldn't make folder in path: "+ uploadPath.toString());
            }
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetLocation = uploadPath.resolve(fileName);

        // Save the file to the server
        file.transferTo(targetLocation.toFile());
        return fileName;
    }

    @Override
    public Resource getCV(String fileName) throws IOException {

        Path filePath = Paths.get(uploadDirectory +"/"+fileName); // Change the path accordingly

        Resource cv = new UrlResource(filePath.toUri());
        if (cv.exists() && cv.isReadable()) {
            return cv;
        } else {
           throw new IOException("File not found");
        }
    }



    private boolean isPDF(MultipartFile file) {
        return Objects.requireNonNull(file.getContentType()).equalsIgnoreCase("application/pdf");
    }



}
