#!/bin/sh
echo making JBlox
rm jblox.jar jblox.jar.js
javac org/hn/jblox/*.java
jar cf jblox.jar org/hn/jblox/*.class org/hn/jblox/img/*.gif
/Applications/cheerpj/cheerpjfy.py jblox.jar
#python3 -m http.server 8080
