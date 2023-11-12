#!/bin/sh
echo Compiling jblox.jar
javac org/hn/jblox/*.java
jar cf jblox.jar org/hn/jblox/*.class org/hn/jblox/img/*.gif
#python3 -m http.server 8080
