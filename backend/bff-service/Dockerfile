# Usamos una imagen base ligera con Java 17 (asegúrate de que sea la misma versión que tus otros servicios)
FROM eclipse-temurin:17-jre-alpine

# Copiamos el archivo .jar que generaste con 'mvn clean package'
# Asegúrate de que el nombre del jar coincida con el que se genera en tu carpeta 'target'
COPY target/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app.jar"]