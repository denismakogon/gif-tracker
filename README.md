# GIF face detection app


## Obtain dependencies

```bash
./build_dependencies.sh
```

## Package app

```bash
mvn package
```

## Run app

```bash
java -cp api.facedetect.com/target/api.facedetect.com-1.0.jar:api.gif.com/target/api.gif.com-1.0.jar:main.app/target/main.app-1.0.jar:$(echo target/* | tr ' ' ':') main.app.Main original.gif 

```