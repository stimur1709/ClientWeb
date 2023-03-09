package com.example.clientweb.controller;

import com.example.clientweb.data.dto.ImageDto;
import com.example.clientweb.data.model.Image;
import com.example.clientweb.service.ImageService;
import com.example.clientweb.util.BindingResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController extends ModelControllerImpl<ImageDto, Image, ImageService> {

    @Autowired
    protected ImageController(ImageService service, BindingResultResponse bindingResultResponse) {
        super(service, bindingResultResponse);
    }

    @PostMapping(value = "/file")
    public ResponseEntity<?> save(@RequestParam(name = "file", required = false) MultipartFile... file) {
        try {
            return new ResponseEntity<>(service.saveImage(file), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
