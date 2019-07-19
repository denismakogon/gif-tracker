package com.giphy.app;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;


public class App {

    public static void main(String[] args) {

        try {

            // note: GIF file must be an instance of InputStream
            // note: for the sake of simplicity while obtaining GIF from the internet
            FileInputStream originalGifFileStream = new FileInputStream("original.gif");

            // note: output stream might not be just a file
            // note: it could be whatever implements OutputStream interface
            ImageOutputStream output = new FileImageOutputStream(new File("final.gif"));

            GifDecoder gifDecoder = new GifDecoder();
            GifEncoder writer = new GifEncoder(
                    output, BufferedImage.TYPE_INT_RGB, 0, true);
            FaceDetect faceDetect = new FaceDetect();

            // note: Pair class is just a placeholder for origin GIF frame and its OpenCV Mat representation
            ArrayList<Pair> mats = gifDecoder.framesToMat(originalGifFileStream);

            for (Pair matFrame: mats) {
                writer.writeToSequence(
                        faceDetect.processImageFromMat(matFrame)
                );
            }

            writer.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}
