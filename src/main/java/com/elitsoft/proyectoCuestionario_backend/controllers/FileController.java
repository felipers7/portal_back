package com.elitsoft.proyectoCuestionario_backend.controllers;

import com.elitsoft.proyectoCuestionario_backend.messages.FileMessage;
import com.elitsoft.proyectoCuestionario_backend.models.FileModel;
import com.elitsoft.proyectoCuestionario_backend.services.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload/{usr_id}")
    public ResponseEntity<FileMessage> uploadFiles(@PathVariable Long usr_id, @RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            // Crear una carpeta por usr_id si no existe
            File userFolder = new File(System.getProperty("user.dir") + "\\uploads\\" + usr_id);
            System.out.println("Ruta absoluta: " + userFolder.getAbsolutePath());
            System.out.println("Directorio de trabajo actual: " + System.getProperty("user.dir"));
            if (!userFolder.exists()) {
                userFolder.mkdir();
            }

            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).forEach(file -> {
                try {
                    // Guarda el archivo en la carpeta del usuario
                    Path userFilePath = userFolder.toPath().resolve(file.getOriginalFilename());
                    Files.copy(file.getInputStream(), userFilePath, StandardCopyOption.REPLACE_EXISTING);

                    fileNames.add(file.getOriginalFilename());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

    @GetMapping("/files/{usr_id}")
    public ResponseEntity<List<FileModel>> getFiles(@PathVariable Long usr_id) {
        List<FileModel> fileInfos = fileService.loadAll(usr_id).map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                    usr_id, path.getFileName().toString()).build().toString();
            return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{usr_id}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable Long usr_id, @PathVariable String filename) {
        try {
            // Construir la ruta completa del archivo
            String filePath = System.getProperty("user.dir") + "\\uploads\\" + usr_id + "\\" + filename;
            Resource file = fileService.load(filePath);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/delete/{usr_id}/{filename:.+}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable Long usr_id, @PathVariable String filename) {
        String message = "";
        try {
            // Construir la ruta completa del archivo
            String filePath = System.getProperty("user.dir") + "\\uploads\\" + usr_id + "\\" + filename;
            File fileToDelete = new File(filePath);

            // Verificar si el archivo existe antes de intentar eliminarlo
            if (fileToDelete.exists()) {
                fileToDelete.delete();
                message = "Archivo eliminado con éxito";
                return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
            } else {
                message = "El archivo no existe";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileMessage(message));
            }
        } catch (Exception e) {
            message = "Error al eliminar el archivo";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

}