# Usar la imagen oficial de OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de tu aplicación a la imagen
COPY target/wordToo-0.0.1-SNAPSHOT.jar app.jar

# Configurar el puerto en el que correrá la aplicación (cambiar si tu app usa otro puerto)
EXPOSE 8000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
