package com.example.calculator.data.service.imagesFolder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
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

        //Path outPath = Paths.get("/images/converted/");
        Path outPath = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/converted/");
        if (!Files.exists(outPath)) {
            Files.createDirectory(outPath);
        }

        String timeNow = DateTimeFormatter
                .ofPattern("yyyy_MM_dd__HH_mm_ss_SSS")
                .format(LocalDateTime.now());
        String filename = "image_4" + timeNow + "__.png";
        File absOutFile = outPath.resolve(filename).toFile();

        File fileToConvert = new File(System.getProperty("user.dir") + "/src/main/resources/static/images/uploads/image_4.jpg");
        BufferedImage bimg = ImageIO.read(fileToConvert);
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();
//        int width = fileToConvert;
//        int height = 300;

        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);

        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // Draw graphics
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        //drawPoints(Tablet.getPenPoints(), g2d, Color.BLACK);

        // Graphics context no longer needed so dispose it
        g2d.dispose();

        ImageIO.write(bufferedImage, "png", absOutFile);


        System.out.println("File saved to:");
        System.out.println(absOutFile);
    }

    public static Image makeColorTransparent(Image im, final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            @Override
            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

    public static BufferedImage makeImageTranslucent(BufferedImage source,
                                                     double alpha) {
        BufferedImage target = new BufferedImage(source.getWidth(),
                source.getHeight(), java.awt.Transparency.TRANSLUCENT);
        // Get the images graphics
        Graphics2D g = target.createGraphics();
        // Set the Graphics composite to Alpha
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float) alpha));
        // Draw the image into the prepared reciver image
        g.drawImage(source, null, 0, 0);
        // let go of all system resources in this Graphics
        g.dispose();
        // Return the image
        return target;
    }
}
