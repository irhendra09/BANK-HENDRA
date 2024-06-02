# Gunakan image dasar untuk aplikasi Spring Boot
FROM openjdk:17-jdk-slim

# Set direktori kerja
WORKDIR /app

# Salin file JAR aplikasi ke dalam container
COPY target/your-app-name.jar /app/app.jar

# Tentukan perintah untuk menjalankan aplikasi Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
