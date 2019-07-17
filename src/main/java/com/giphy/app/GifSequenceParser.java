package com.giphy.app;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

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

    public ArrayList<Mat> parseToFrames() throws IOException {
        ArrayList<Mat> frames = new ArrayList<>();

        for (int i = 0, count = this.reader.getNumImages(true); i < count; i++) {
            BufferedImage bufferedImage = reader.read(i);
            frames.add(new OpenCVFrameConverter.ToMat().convert(new Java2DFrameConverter().convert(bufferedImage)));
        }
        return frames;
    }

}
