# Frontend - Colegio Fullstack

## Descripción
Interfaz de usuario desarrollada en React + Vite para la gestión del sistema escolar.

## Requisitos Previos
- Node.js (versión 20+)
- Docker (opcional, para despliegue en contenedor)

## Instalación y Ejecución
1. Instalar dependencias: `npm install`
2. Ejecutar en modo desarrollo: `npm run dev`
3. Construcción para producción: `npm run build`

## Despliegue con Docker
Para construir la imagen:
`docker build -t frontend-app .`
`docker run -p 3000:80 frontend-app`