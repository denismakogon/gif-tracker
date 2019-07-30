module main.app {

    // system
    requires java.desktop;
    requires java.base;

    // local
    requires api.gif.com;
    requires api.facedetect.com;

    // exposure
    exports main.app;
}