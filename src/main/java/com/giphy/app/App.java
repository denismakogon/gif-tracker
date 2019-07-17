package com.giphy.app;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;


public class App {

    public static void main(String[] args) {

        GifSequenceParser gifParser = new GifSequenceParser(new File("original.gif"));
        try {
            ArrayList<BufferedImage> frames = gifParser.parseToFrames();

            ImageOutputStream output = new FileImageOutputStream(new File("final.gif"));
            GifSequenceWriter writer = new GifSequenceWriter(
                    output, BufferedImage.TYPE_INT_RGB, 0, true);

            for (BufferedImage img: frames) {
                writer.writeToSequence(img);
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
