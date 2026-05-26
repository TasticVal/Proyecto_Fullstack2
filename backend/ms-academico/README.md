# Microservicio Academico

## Descripción
Microservicio encargado de gestión de alumnos

## Tecnologías
- Java 17
- Spring Boot 3.2.0
- Maven

## Ejecución Local
1. Asegurarse de tener MySQL corriendo.
2. Ejecutar: `mvn spring-boot:run`

## Despliegue con Docker
Este servicio está configurado dentro del ecosistema `docker-compose` de la raíz del proyecto. Para levantarlo:
`docker-compose up --build ms-academico`