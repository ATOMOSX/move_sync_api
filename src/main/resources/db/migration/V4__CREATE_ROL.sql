CREATE TABLE rol
(
    id_rol SERIAL PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO rol (nombre)
VALUES ('ADMIN'),
       ('USUARIO');

ALTER TABLE usuario
    ADD COLUMN id_rol INTEGER NOT NULL DEFAULT 2;

ALTER TABLE usuario
    ADD CONSTRAINT fk_usuario_rol
        FOREIGN KEY (id_rol)
            REFERENCES rol (id_rol)
            ON DELETE RESTRICT;

UPDATE usuario
SET id_rol = CASE
                 WHEN RANDOM() < 0.3 THEN 1
                 ELSE 2
    END;
