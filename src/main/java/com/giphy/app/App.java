package com.giphy.app;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import org.bytedeco.opencv.opencv_core.*;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;


public class App {

    public static void main(String[] args) {

        GifSequenceParser gifParser = new GifSequenceParser(new File("giphy.gif"));
        try {
            ArrayList<Mat> frames = gifParser.parseToFrames();
            FaceDetect faceDetect = new FaceDetect();

            // ImageOutputStream output = new FileImageOutputStream(new File("final.gif"));
            // GifSequenceWriter writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, 250, true);

            for (Mat mat: frames) {
                RectVector faces = faceDetect.detectFaces(mat);
                faceDetect.drawFaces(mat, faces);
                imwrite(String.format("%s-%d.jpg", "output", frames.indexOf(mat)), mat);
            }

            // writer.close();
            // output.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}
