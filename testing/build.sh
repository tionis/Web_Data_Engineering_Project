#!/bin/sh
../mvnw package
java -jar ../target/Webseite-0.0.1-SNAPSHOT.jar
