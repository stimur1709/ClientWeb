package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.ImageDto;
import com.example.clientweb.data.model.Image;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ImageRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService extends ModelServiceImpl<Image, ImageDto, ImageRepository> {

    @Value("${profile.upload.path}")
    private String uploadPath;

    public ImageService(ImageRepository repository, ModelMapper modelMapper, MessageLocale messageLocale) {
        super(repository, ImageDto.class, Image.class, modelMapper, messageLocale);
    }

    public List<ImageDto> saveImage(MultipartFile[] files) throws IOException {
        if (!new File(uploadPath).exists()) {
            Files.createDirectories(Paths.get(uploadPath));
        }
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.getContentType() != null) {
                String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
                Path path = Paths.get(uploadPath, fileName);
                images.add(new Image(fileName, file.getSize()));
                file.transferTo(path);
            }
        }
        return saveAll(images);
    }

    @Override
    public ImageDto save(ImageDto dto) throws SaveException {
        Image image = findById(dto.getId());
        dto.setName(image.getName());
        return super.save(dto);
    }
}
