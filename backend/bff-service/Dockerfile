# ETAPA 1: Construcción
FROM maven:3.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Compilamos y renombramos el archivo resultante a app.jar inmediatamente
# El 'mv' mueve el archivo ejecutable de Spring a /app/app.jar
RUN mvn clean package -DskipTests && \
    mv target/*.jar app.jar

# ETAPA 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos desde la etapa anterior el archivo que ya renombramos
COPY --from=build /app/app.jar .

# Ejecutamos directamente
ENTRYPOINT ["java","-jar","app.jar"]