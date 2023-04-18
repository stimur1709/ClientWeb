package com.example.clientweb.service.impl;

import com.example.clientweb.data.dto.ImageDto;
import com.example.clientweb.data.model.Image;
import com.example.clientweb.errors.SaveException;
import com.example.clientweb.repository.ImageRepository;
import com.example.clientweb.service.ModelServiceImpl;
import com.example.clientweb.util.MessageLocale;
import com.example.clientweb.util.ModelMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ImageService extends ModelServiceImpl<Image, ImageDto, ImageRepository> {
    private final ImageRepository imageRepository;

    @Value("${profile.upload.path}")
    private String uploadPath;

    public ImageService(ImageRepository repository, MessageLocale messageLocale,
                        ImageRepository imageRepository, ModelMapperUtil<Image, ImageDto> modelMapper) {
        super(repository, messageLocale, modelMapper);
        this.imageRepository = imageRepository;
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

    @Scheduled(fixedDelayString = "PT24H")
    private void deletingUnusedPictures() {
        log.info("The procedure for deleting unused images has been started");
        int size;
        List<Image> unusedPictures = repository.findUnusedPictures();
        for (Image image : unusedPictures) {
            File file = new File(uploadPath + File.separator + image.getName());
            if (file.delete()) {
                log.info("{} picture deleted", image.getName());
            } else {
                log.error("{} image deletion error", image.getName());
            }
        }
        if (unusedPictures.size() > 0) {
            repository.deleteAll(unusedPictures);
        }
        size = unusedPictures.size();
        File dir = new File(uploadPath);
        File[] arrFiles = dir.listFiles();
        if (arrFiles != null && arrFiles.length > 0) {
            List<String> filesName = Arrays.stream(arrFiles).map(File::getName).collect(Collectors.toList());
            List<String> images = imageRepository.findByNameIn(filesName).stream().map(Image::getName).collect(Collectors.toList());
            filesName.removeAll(images);
            List<File> filesDelete = Arrays.stream(arrFiles).filter(f -> filesName.contains(f.getName())).collect(Collectors.toList());
            if (filesDelete.size() > 0) {
                for (File file : filesDelete) {
                    if (file.delete()) {
                        log.info("{} picture deleted", file.getName());
                    } else {
                        log.error("{} image deletion error", file.getName());
                    }
                }
                size += filesDelete.size();
            }
        }
        log.info("{} pictures removed", size);
    }

}
