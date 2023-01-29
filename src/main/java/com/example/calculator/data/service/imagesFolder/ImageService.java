package com.example.calculator.data.service.imagesFolder;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public boolean checkImageSize(long fileSize) {

        long fileSizeInMB = fileSize / (1024 * 1024);

        if ( fileSizeInMB > 3L ) {
            return true;
        }

        return false;
    }

}
