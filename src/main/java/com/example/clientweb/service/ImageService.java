package com.example.clientweb.service;

import com.example.clientweb.data.dto.ImageDto;
import com.example.clientweb.data.model.Image;
import com.example.clientweb.repository.ImageRepository;
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

    public ImageService(ImageRepository repository, ModelMapper modelMapper) {
        super(repository, ImageDto.class, modelMapper);
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
                images.add(new Image(File.separator, fileName, file.getSize()));
                file.transferTo(path);
            }
        }
        return saveAll(images);
    }

    @Override
    public ImageDto save(Image model) {
        //todo перемещение картинок доделать
        String s = File.separator;
        Image image = findById(model.getId());
        String sourceUri = uploadPath + image.getPath() + image.getName();
        System.out.println(sourceUri);
        String targetUri = uploadPath + s + "123" + s;
        System.out.println(targetUri);
        try {
            Path source = Paths.get(sourceUri);
            File file = new File(targetUri);
            Path target = Paths.get(targetUri + model.getName());
            System.out.println(target);
            if (file.isDirectory()) {
                System.out.println(111);
                Files.move(source, target);
            } else if (file.mkdirs()) {
                Files.move(source, target);
                System.out.println(222);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.save(model);
    }
}
