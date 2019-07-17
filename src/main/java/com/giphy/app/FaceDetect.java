package com.giphy.app;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;


public class FaceDetect {

    private CascadeClassifier classifier = null;

    public FaceDetect() {
        try {
            this.classifier = this.setupClasifier();
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    public RectVector detectFaces(Mat frame) {
        RectVector faces = new RectVector();
        this.classifier.detectMultiScale(frame, faces);
        return faces;
    }

    private CascadeClassifier setupClasifier() throws IOException {
        URL url = new URL("https://raw.github.com/opencv/opencv/master/data/haarcascades/haarcascade_frontalface_alt.xml");
        File file = Loader.cacheResource(url);
        String classifierName = file.getAbsolutePath();
        return new CascadeClassifier(classifierName);
    }

    public void drawFaces(Mat frame, RectVector faces) {
        long nFaces = faces.size();

        System.out.println("Faces detected: " + nFaces);
        if (nFaces == 0) {
            return;
        }

        int[] bbox = new int[4];
        for (int iface = 0; iface < nFaces; ++iface) {
            Rect rect = faces.get(iface);

            bbox[0] = rect.x();
            bbox[1] = rect.y();
            bbox[2] = rect.x() + rect.width();
            bbox[3] = rect.y() + rect.height();

            rectangle(frame,
                    new Point(bbox[0], bbox[1]),
                    new Point(bbox[2], bbox[3]),
                    new Scalar(255, 0, 255, 128),
                    2, 1, 0
            );
        }
    }
}
