#!/bin/sh
java -jar target/escala-rest.jar --spring.config.location=file:./config/db.properties
cd ..