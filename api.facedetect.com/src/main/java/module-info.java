module api.facedetect.com {

    // core
    requires java.base;
    requires java.desktop;

    // 3rd-party
    requires org.bytedeco.opencv.platform;
    requires org.bytedeco.javacv.platform;

    // local modules
    requires api.gif.com;

    exports api.facedetect.com;
}