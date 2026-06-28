# ===================================================================
# ETAPA 1: Compilación del código fuente (Build Stage)
# ===================================================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

# Definimos el directorio de trabajo para compilar
WORKDIR /build

# Copiamos los archivos de configuración de Maven y el código fuente
COPY pom.xml .
COPY src ./src

# Compilamos el proyecto omitiendo los tests para acelerar el despliegue
RUN mvn clean package -DskipTests

# ===================================================================
# ETAPA 2: Ejecución del aplicativo (Run Stage)
# ===================================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiamos el .jar generado en la ETAPA 1 usando la referencia 'build'
# Nota cómo ya no depende de tu carpeta target local
COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

