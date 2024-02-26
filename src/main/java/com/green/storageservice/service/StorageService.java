package com.green.storageservice.service;

import com.green.storageservice.entity.ImageData;
import com.green.storageservice.repository.StorageRepository;
import com.green.storageservice.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public String uploadFile(MultipartFile file) throws IOException {
      ImageData imageData = storageRepository.save(ImageData.builder().name(file.getOriginalFilename())
                .type(file.getContentType()).imageData(ImageUtils.compressImage(file.getBytes())).build());
      if(imageData!=null)
      {
          return "File uploaded successfully" + file.getOriginalFilename();
      }
      else
      {
        return null;
      }
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
