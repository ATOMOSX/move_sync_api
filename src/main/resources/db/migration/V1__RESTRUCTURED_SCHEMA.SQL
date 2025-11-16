-- ============================================
-- V2__restructured_schema.sql
-- Esquema unificado y limpio (nombres en minúsculas)
-- ============================================

-- Eliminación de tablas anteriores (solo para desarrollo)
DROP TABLE IF EXISTS historial_progreso CASCADE;
DROP TABLE IF EXISTS registro_actividad CASCADE;
DROP TABLE IF EXISTS meta CASCADE;
DROP TABLE IF EXISTS evento CASCADE;
DROP TABLE IF EXISTS actividad CASCADE;
DROP TABLE IF EXISTS notificacion CASCADE;
DROP TABLE IF EXISTS recomendacion CASCADE;
DROP TABLE IF EXISTS logro CASCADE;
DROP TABLE IF EXISTS perfil_salud CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;

-- ============================================
-- TABLA: usuario
-- ============================================
CREATE TABLE usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         primer_nombre VARCHAR(50) NOT NULL,
                         segundo_nombre VARCHAR(50),
                         primer_apellido VARCHAR(50) NOT NULL,
                         segundo_apellido VARCHAR(50),
                         cedula VARCHAR(20) NOT NULL UNIQUE,
                         peso NUMERIC(5,2),
                         estatura NUMERIC(5,2),
                         genero CHAR(1) NOT NULL,
                         contrasena VARCHAR(100) NOT NULL,
                         correo VARCHAR(100) NOT NULL UNIQUE,
                         fecha_nacimiento DATE NOT NULL
);

CREATE INDEX idx_usuario_correo ON usuario (correo);

-- ============================================
-- TABLA: perfil_salud
-- ============================================
CREATE TABLE perfil_salud (
                              id_perfil SERIAL PRIMARY KEY,
                              id_usuario INTEGER NOT NULL,
                              nivel_actividad VARCHAR(50),
                              gasto_energetico VARCHAR(50),
                              condicion_cardiovascular VARCHAR(50),
                              imc VARCHAR(10),
                              balance_energetico VARCHAR(50),
                              fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              CONSTRAINT fk_perfil_salud_usuario
                                  FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                                      ON DELETE CASCADE
);

-- ============================================
-- TABLA: logro
-- ============================================
CREATE TABLE logro (
                       id_logro SERIAL PRIMARY KEY,
                       id_usuario INTEGER NOT NULL,
                       nombre VARCHAR(100) NOT NULL,
                       descripcion TEXT,
                       recompensa VARCHAR(100),
                       tipo VARCHAR(50),
                       fecha DATE DEFAULT CURRENT_DATE,
                       CONSTRAINT fk_logro_usuario
                           FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                               ON DELETE CASCADE
);

-- ============================================
-- TABLA: recomendacion
-- ============================================
CREATE TABLE recomendacion (
                               id_recomendacion SERIAL PRIMARY KEY,
                               id_usuario INTEGER NOT NULL,
                               mensaje TEXT NOT NULL,
                               fecha DATE NOT NULL DEFAULT CURRENT_DATE,
                               CONSTRAINT fk_recomendacion_usuario
                                   FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                                       ON DELETE CASCADE
);

-- ============================================
-- TABLA: notificacion
-- ============================================
CREATE TABLE notificacion (
                              id_notificacion SERIAL PRIMARY KEY,
                              id_usuario INTEGER NOT NULL,
                              mensaje TEXT NOT NULL,
                              fecha DATE NOT NULL DEFAULT CURRENT_DATE,
                              CONSTRAINT fk_notificacion_usuario
                                  FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                                      ON DELETE CASCADE
);

-- ============================================
-- TABLA: actividad
-- ============================================
CREATE TABLE actividad (
                           id_actividad SERIAL PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           tipo VARCHAR(50) NOT NULL
);

-- ============================================
-- TABLA: evento
-- ============================================
CREATE TABLE evento (
                        id_evento SERIAL PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        fecha TIMESTAMP NOT NULL,
                        duracion INTERVAL NOT NULL,
                        distancia NUMERIC(6,2)
);

-- ============================================
-- TABLA: registro_actividad
-- ============================================
CREATE TABLE registro_actividad (
                                    id_registro_actividad SERIAL PRIMARY KEY,
                                    id_usuario INTEGER NOT NULL,
                                    id_actividad INTEGER NOT NULL,
                                    id_evento INTEGER,
                                    fecha DATE NOT NULL,
                                    duracion INTERVAL,
                                    perdida_calorias_estimadas NUMERIC(6,2),
                                    perdida_calorias_alcanzadas NUMERIC(6,2),
                                    CONSTRAINT fk_registro_actividad_usuario
                                        FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                                            ON DELETE CASCADE,
                                    CONSTRAINT fk_registro_actividad_actividad
                                        FOREIGN KEY (id_actividad) REFERENCES actividad (id_actividad)
                                            ON DELETE CASCADE,
                                    CONSTRAINT fk_registro_actividad_evento
                                        FOREIGN KEY (id_evento) REFERENCES evento (id_evento)
                                            ON DELETE SET NULL
);

-- ============================================
-- TABLA: meta
-- ============================================
CREATE TABLE meta (
                      id_meta SERIAL PRIMARY KEY,
                      id_usuario INTEGER NOT NULL,
                      fecha_inicio DATE NOT NULL,
                      fecha_fin DATE NOT NULL,
                      objetivo VARCHAR(200) NOT NULL,
                      perdida_calorias_diarias NUMERIC(6,2),
                      CONSTRAINT fk_meta_usuario
                          FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                              ON DELETE CASCADE
);

-- ============================================
-- TABLA: historial_progreso
-- ============================================
CREATE TABLE historial_progreso (
                                    id_historial SERIAL PRIMARY KEY,
                                    id_usuario INTEGER NOT NULL,
                                    id_meta INTEGER NOT NULL,
                                    fecha DATE NOT NULL,
                                    avance_objetivo VARCHAR(100) NOT NULL,
                                    CONSTRAINT fk_historial_progreso_usuario
                                        FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
                                            ON DELETE CASCADE,
                                    CONSTRAINT fk_historial_progreso_meta
                                        FOREIGN KEY (id_meta) REFERENCES meta (id_meta)
                                            ON DELETE CASCADE
);

-- ============================================
-- ÍNDICES ADICIONALES
-- ============================================
CREATE INDEX idx_logro_usuario ON logro (id_usuario);
CREATE INDEX idx_recomendacion_usuario ON recomendacion (id_usuario);
CREATE INDEX idx_notificacion_usuario ON notificacion (id_usuario);
CREATE INDEX idx_perfil_salud_usuario ON perfil_salud (id_usuario);
CREATE INDEX idx_meta_usuario ON meta (id_usuario);
