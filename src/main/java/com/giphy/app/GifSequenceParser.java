package com.giphy.app;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GifSequenceParser {

    private ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();

    public GifSequenceParser(File gifFile) {
        try {
            this.reader.setInput(ImageIO.createImageInputStream(gifFile));
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    public ArrayList<BufferedImage> parseToFrames() throws IOException {
        ArrayList<BufferedImage> frames = new ArrayList<>();

        for (int i = 0, count = this.reader.getNumImages(true); i < count; i++) {
            BufferedImage bufferedImage = reader.read(i);
            frames.add(bufferedImage);
        }

//        for (int i = 0, count = this.reader.getNumImages(true); i < count; i++) {
//            BufferedImage bufferedImage = reader.read(i);
//            BufferedImage result =new BufferedImage(
//                    bufferedImage.getWidth(),
//                    bufferedImage.getHeight(),
//                    BufferedImage.TYPE_INT_RGB);
//            result.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
//
//            String filename = String.format("output-%d.jpg", i);
//            ImageIO.write(result, "jpg", new File(String.format("output-%d.jpg", i)));
//            Mat mat = imread(filename);
//            frames.add(mat);
//            Files.deleteIfExists(Paths.get(filename));
//
//            // frames.add(new OpenCVFrameConverter.ToMat().convert(new Java2DFrameConverter().convert(bufferedImage)));
//        }
        return frames;
    }

}
