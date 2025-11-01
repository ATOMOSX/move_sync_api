-- =============================================
-- V1__CREATE_SCHEMA.sql
-- Creación de tablas base del sistema MoveSync
-- =============================================

CREATE TABLE IF NOT EXISTS usuario (
                                       id_usuario VARCHAR(50) PRIMARY KEY,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50),
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    cedula VARCHAR(20) NOT NULL,
    peso INTEGER,
    estatura INTEGER,
    genero CHAR(1) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS recomendacion (
                                             id_recomendacion VARCHAR(50) PRIMARY KEY,
    mensaje TEXT NOT NULL,
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario)
    );

CREATE TABLE IF NOT EXISTS perfil_salud (
                                            id_perfil VARCHAR(50) PRIMARY KEY,
    nivel_actividad VARCHAR(50) NOT NULL,
    gasto_energetico VARCHAR(50) NOT NULL,
    condicion_cardiovascular VARCHAR(50) NOT NULL,
    imc VARCHAR(10) NOT NULL,
    balance_energetico VARCHAR(50) NOT NULL,
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario)
    );

CREATE TABLE IF NOT EXISTS notificacion (
                                            id_notificacion VARCHAR(50) PRIMARY KEY,
    mensaje TEXT NOT NULL,
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario),
    fecha DATE NOT NULL
    );

CREATE TABLE IF NOT EXISTS logro (
                                     id_logro VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    recompensa VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario)
    );

CREATE TABLE IF NOT EXISTS evento (
                                      id_evento VARCHAR(50) PRIMARY KEY,
    duracion TIME NOT NULL,
    fecha TIMESTAMP WITH TIME ZONE NOT NULL,
                        nombre VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS registro_participantes (
                                                      id_registro_participantes VARCHAR(50) PRIMARY KEY,
    id_evento VARCHAR(50) NOT NULL REFERENCES evento(id_evento),
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario)
    );

CREATE TABLE IF NOT EXISTS tipo_actividad (
                                              id_tipo VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS actividad (
                                         id_actividad VARCHAR(50) PRIMARY KEY,
    id_tipo VARCHAR(50) NOT NULL REFERENCES tipo_actividad(id_tipo),
    descripcion VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS calorias_estimadas (
                                                  id_calorias_estimadas VARCHAR(50) PRIMARY KEY,
    id_evento VARCHAR(50) NOT NULL REFERENCES evento(id_evento),
    id_actividad VARCHAR(50) NOT NULL REFERENCES actividad(id_actividad)
    );

CREATE TABLE IF NOT EXISTS meta (
                                    id_meta VARCHAR(50) PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    objetivo VARCHAR(200) NOT NULL,
    perdida_calorias_diarias VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS historial_progreso (
                                                  id_historial VARCHAR(50) PRIMARY KEY,
    id_usuario VARCHAR(50) NOT NULL REFERENCES usuario(id_usuario),
    id_meta VARCHAR(50) NOT NULL REFERENCES meta(id_meta),
    fecha DATE NOT NULL,
    avance_objetivo VARCHAR(100) NOT NULL
    );
