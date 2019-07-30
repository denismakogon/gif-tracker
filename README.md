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
java -cp main.app/target/main.app-1.0.jar:$(echo target/* | tr ' ' ':') main.app.Main original.gif 

```