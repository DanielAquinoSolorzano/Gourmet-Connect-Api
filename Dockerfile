# Imagen Base
FROM eclipse-temurin:21-jre-alpine

# Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo .jar compilado desde la carpeta target al contenedor
COPY target/*.jar app.jar

# Exponemos el puerto estándar en el que corre Tomcat
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]


