package com.example.clientweb.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ResourceStorage {

    @Value("${profile.upload.path}")
    private String uploadPath;

    public String saveImage(MultipartFile file) throws IOException {
        if (!new File(uploadPath).exists()) {
            Files.createDirectories(Paths.get(uploadPath));
        }
        String fileName = hashCode() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get(uploadPath, fileName);
        String resourceURI = "/img/" + fileName;
        file.transferTo(path);
        return resourceURI;
    }
}
