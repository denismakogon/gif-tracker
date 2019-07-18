package com.giphy.app;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;


public class App {

    public static void main(String[] args) {

        try {
            GifDecoder gifDecoder = new GifDecoder();
            ImageOutputStream output = new FileImageOutputStream(new File("final.gif"));
            GifEncoder writer = new GifEncoder(
                    output, BufferedImage.TYPE_INT_RGB, 0, true);
            FaceDetect faceDetect = new FaceDetect();

            ArrayList<String> framePaths = gifDecoder.saveFramesFrom("original.gif");
            for (String imgPath: framePaths) {
                writer.writeToSequence(
                        faceDetect.processImage(framePaths.indexOf(imgPath), imgPath)
                );
            }

             writer.close();
             output.close();
             gifDecoder.close();
             faceDetect.cleaUp(framePaths.size());

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}
