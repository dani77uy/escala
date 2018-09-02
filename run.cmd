@echo off
IF "%JAVA_HOME%" == "" (
    echo Enter path to JAVA_HOME:
    set /p JAVA_HOME=
) ELSE (
    echo %JAVA_HOME%
)
java -jar target\escala-rest.jar --spring.config.location=file:./config/db.properties
cd..