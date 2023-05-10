#!/bin/sh
echo making jblox.jar...
javac ./org/hn/jblox/*.java
jar cf jblox.jar ./org/hn/jblox/*.class ./org/hn/jblox/img/*.gif
