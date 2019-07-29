package main.app;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import api.facedetect.com.*;
import api.gif.com.*;


public class Main {

    public static void main(String[] args) {

        try {

            if (args.length < 1) {
                System.err.println("no GIF file specified!");
                throw new IOException("no GIF file specified!");
            }
            System.out.println("creating OpenCV face detect instance");
            FaceDetect faceDetect = new FaceDetect();
            System.out.println("OpenCV face detect instance created");

            // note: GIF file must be an instance of InputStream
            // note: for the sake of simplicity while obtaining GIF from the internet
            FileInputStream originalGifFileStream = new FileInputStream(args[0]);
            System.out.println("reading original GIF");

            // note: output stream might not be just a file
            // note: it could be whatever implements OutputStream interface
            ImageOutputStream output = new FileImageOutputStream(new File("final.gif"));
            System.out.println("creating final GIF storage");

            System.out.println("GIF decoder initializing");
            GifDecoder gifDecoder = new GifDecoder();
            gifDecoder.read(originalGifFileStream);
            System.out.println("GIF decoder initialized");
            GifEncoder writer = new GifEncoder(
                    output, BufferedImage.TYPE_INT_RGB, 0, true);

            var wg = new FiberWaitGroup();
            // note: Pair class is just a placeholder for origin GIF frame and its OpenCV Mat representation
            faceDetect.processFrameWithDetections(wg, gifDecoder);

            // await until all fibers are done
            // retrieve results from futures
            var resultedFrames = wg.awaitForResult();
            System.out.println("all fibers are out");

            for (Object result: resultedFrames) {
                writer.writeToSequence((BufferedImage) result);
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
