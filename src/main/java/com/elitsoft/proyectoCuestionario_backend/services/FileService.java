package com.elitsoft.proyectoCuestionario_backend.services;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {


    public void init();
    public void save(MultipartFile file) throws IOException;
    public Resource load(String filename) throws IOException;
    public void deleteall();
    public Stream<Path> loadAll(Long usr_id);
    public String deletefile(String filename);


    public void deleteFile(String filePath) throws IOException;


    String saveFile(MultipartFile file) throws IOException;
    Resource getCV(String fileName) throws IOException;





}
