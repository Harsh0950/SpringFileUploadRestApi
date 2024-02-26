package com.green.storageservice.controllers;

import com.green.storageservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/")
    public ResponseEntity<?> uploadImage(@RequestParam(name = "image") MultipartFile file) throws IOException {
       String image = storageService.uploadFile(file);
       return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable(name = "fileName") String fileName) throws IOException {
        byte[] images= storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(images);
    }
}
