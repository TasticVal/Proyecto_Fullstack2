# ETAPA 1: Construcción
FROM maven:3.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la caché de Docker
COPY pom.xml .
# Copiamos el código fuente
COPY src ./src

# Compilamos saltando tests (ya que los tests los correremos aparte o fuera)
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Imagen más ligera)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos solo el jar generado desde la etapa anterior
# Ajusta el nombre si tu jar tiene un nombre diferente al final
COPY --from=build /app/target/*.jar app.jar

# Ejecutamos
ENTRYPOINT ["java","-jar","app.jar"]