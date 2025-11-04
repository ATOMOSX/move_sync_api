-- ============================================
-- V1__CREATE_SCHEMA.sql
-- Esquema inicial corregido (mantiene nombres originales)
-- Compatible con PostgreSQL y Flyway
-- ============================================

-- Eliminamos primero las tablas si existen (para desarrollo)
DROP TABLE IF EXISTS "DetalleRutina" CASCADE;
DROP TABLE IF EXISTS "Rutina" CASCADE;
DROP TABLE IF EXISTS "Ejercicio" CASCADE;
DROP TABLE IF EXISTS "Logro" CASCADE;
DROP TABLE IF EXISTS "PerfilSalud" CASCADE;
DROP TABLE IF EXISTS "Recomendacion" CASCADE;
DROP TABLE IF EXISTS "Usuario" CASCADE;

-- ============================================
-- TABLA: Usuario
-- ============================================
CREATE TABLE "Usuario" (
                           "idUsuario" SERIAL PRIMARY KEY,
                           "primerNombre" VARCHAR(50) NOT NULL,
                           "segundoNombre" VARCHAR(50),
                           "primerApellido" VARCHAR(50) NOT NULL,
                           "segundoApellido" VARCHAR(50),
                           "cedula" VARCHAR(20) NOT NULL UNIQUE,
                           "peso" NUMERIC(5,2),
                           "estatura" NUMERIC(5,2),
                           "genero" CHAR(1) NOT NULL,
                           "contrasena" VARCHAR(100) NOT NULL,
                           "correo" VARCHAR(100) NOT NULL UNIQUE,
                           "fechaNacimiento" DATE NOT NULL
);

-- ============================================
-- TABLA: Recomendacion
-- ============================================
CREATE TABLE "Recomendacion" (
                                 "idRecomendacion" SERIAL PRIMARY KEY,
                                 "idUsuario" INTEGER NOT NULL,
                                 "descripcion" TEXT NOT NULL,
                                 "fecha" DATE NOT NULL DEFAULT CURRENT_DATE,
                                 CONSTRAINT "fk_Recomendacion_Usuario"
                                     FOREIGN KEY ("idUsuario")
                                         REFERENCES "Usuario" ("idUsuario")
                                         ON DELETE CASCADE
);

-- ============================================
-- TABLA: PerfilSalud
-- ============================================
CREATE TABLE "PerfilSalud" (
                               "idPerfil" SERIAL PRIMARY KEY,
                               "idUsuario" INTEGER NOT NULL,
                               "frecuenciaCardiaca" INTEGER,
                               "presionSistolica" INTEGER,
                               "presionDiastolica" INTEGER,
                               "nivelActividad" VARCHAR(50),
                               "fechaRegistro" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT "fk_PerfilSalud_Usuario"
                                   FOREIGN KEY ("idUsuario")
                                       REFERENCES "Usuario" ("idUsuario")
                                       ON DELETE CASCADE
);

-- ============================================
-- TABLA: Logro
-- ============================================
CREATE TABLE "Logro" (
                         "idLogro" SERIAL PRIMARY KEY,
                         "idUsuario" INTEGER NOT NULL,
                         "nombre" VARCHAR(100) NOT NULL,
                         "descripcion" TEXT,
                         "fecha" DATE DEFAULT CURRENT_DATE,
                         CONSTRAINT "fk_Logro_Usuario"
                             FOREIGN KEY ("idUsuario")
                                 REFERENCES "Usuario" ("idUsuario")
                                 ON DELETE CASCADE
);

-- ============================================
-- TABLA: Ejercicio
-- ============================================
CREATE TABLE "Ejercicio" (
                             "idEjercicio" SERIAL PRIMARY KEY,
                             "nombre" VARCHAR(100) NOT NULL,
                             "descripcion" TEXT,
                             "categoria" VARCHAR(50),
                             "duracionMinutos" INTEGER
);

-- ============================================
-- TABLA: Rutina
-- ============================================
CREATE TABLE "Rutina" (
                          "idRutina" SERIAL PRIMARY KEY,
                          "idUsuario" INTEGER NOT NULL,
                          "nombre" VARCHAR(100) NOT NULL,
                          "descripcion" TEXT,
                          "fechaCreacion" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT "fk_Rutina_Usuario"
                              FOREIGN KEY ("idUsuario")
                                  REFERENCES "Usuario" ("idUsuario")
                                  ON DELETE CASCADE
);

-- ============================================
-- TABLA: DetalleRutina
-- ============================================
CREATE TABLE "DetalleRutina" (
                                 "idDetalle" SERIAL PRIMARY KEY,
                                 "idRutina" INTEGER NOT NULL,
                                 "idEjercicio" INTEGER NOT NULL,
                                 "repeticiones" INTEGER,
                                 "series" INTEGER,
                                 "descansoSegundos" INTEGER,
                                 CONSTRAINT "fk_DetalleRutina_Rutina"
                                     FOREIGN KEY ("idRutina")
                                         REFERENCES "Rutina" ("idRutina")
                                         ON DELETE CASCADE,
                                 CONSTRAINT "fk_DetalleRutina_Ejercicio"
                                     FOREIGN KEY ("idEjercicio")
                                         REFERENCES "Ejercicio" ("idEjercicio")
                                         ON DELETE CASCADE
);

-- ============================================
-- ÍNDICES (para optimizar búsquedas)
-- ============================================
CREATE INDEX "idx_Usuario_Correo" ON "Usuario" ("correo");
CREATE INDEX "idx_Recomendacion_Usuario" ON "Recomendacion" ("idUsuario");
CREATE INDEX "idx_Rutina_Usuario" ON "Rutina" ("idUsuario");
