package com.example.calculator.data.service.imagesFolder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ImageService {

    public boolean checkImageSize(long fileSize) {

//        Path path = Paths.get("/images/uploads/" + fileName);
//        long bytes = 0L;
//
//        try {
//            // size of a file (in bytes)
//            bytes = Files.size(path);
//            System.out.println(String.format("%,d bytes", bytes));
//            System.out.println(String.format("%,d kilobytes", bytes / 1024));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        long fileSizeInMB = fileSize / (1024 * 1024);

        if ( fileSizeInMB > 3L ) {
            return true;
        }

        return false;
    }


    public void changeImageBackground() throws IOException {

        Path outPath = Paths.get("/images/converted/");
        if (!Files.exists(outPath)) {
            Files.createDirectory(outPath);
        }

        String timeNow = DateTimeFormatter
                .ofPattern("yyyy_MM_dd__HH_mm_ss_SSS")
                .format(LocalDateTime.now());
        String filename = "image_4" + timeNow + "__.png";
        File absOutFile = outPath.resolve(filename).toFile();

        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, width, height);

        g2d.setComposite(AlphaComposite.Src);
        int alpha = 127; // 50% transparent
        g2d.setColor(new Color(255, 100, 100, alpha));
        g2d.fillRect(100, 100, 123, 123);

        g2d.setColor(new Color(0, 0, 0));
        g2d.fillRect(30, 30, 60, 60);

        g2d.dispose();

        ImageIO.write(bufferedImage, "png", absOutFile);
        System.out.println("File saved to:");
        System.out.println(absOutFile);
    }

}
