-- 1. Crear y asegurar la codificación de la base de datos
CREATE DATABASE IF NOT EXISTS db_colegio 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE db_colegio;

-- 2. Tabla de Estudiantes
CREATE TABLE IF NOT EXISTS estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rut VARCHAR(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tabla de Asistencias
CREATE TABLE IF NOT EXISTS asistencias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Tabla de Comunicaciones
CREATE TABLE IF NOT EXISTS comunicaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --- POBLAR DATOS ---

INSERT INTO estudiantes (nombre, rut) VALUES 
('Valeria Estudiante', '12.345.678-9'),
('Gabriel Estudiante', '98.765.432-1'),
('Catalina Rojas', '15.234.567-0');

INSERT INTO asistencias (estudiante_id, fecha, estado) VALUES 
(1, '2026-05-20', 'Presente'),
(2, '2026-05-20', 'Presente'),
(3, '2026-05-20', 'Ausente');

INSERT INTO comunicaciones (titulo, mensaje) VALUES 
('Reunion de Apoderados', 'Se les recuerda la reunión este viernes a las 18:00 hrs.'),
('Vacunacion Escolar', 'El proceso de vacunación comienza el lunes próximo.');