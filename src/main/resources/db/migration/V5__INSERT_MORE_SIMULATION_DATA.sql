BEGIN;

-- ============================================================
-- NUEVAS ACTIVIDADES (EXTENSIÓN DE CATALOGO)
-- ============================================================
INSERT INTO actividad (nombre, tipo)
VALUES ('Natación 45 min', 'Cardio'),
       ('Ciclismo 60 min', 'Cardio'),
       ('Crossfit 40 min', 'Fuerza'),
       ('Zumba 50 min', 'Cardio'),
       ('Yoga Avanzado', 'Pilates'),
       ('HIIT Extremo 25 min', 'HIIT'),
       ('Pesas intensas 1h', 'Fuerza'),
       ('Elíptica 30 min', 'Cardio'),
       ('Caminata Montaña 2h', 'Cardio'),
       ('Resistencia con bandas 30 min', 'Fuerza');

-- ============================================================
-- NUEVOS EVENTOS (20 eventos)
-- ============================================================
INSERT INTO evento (nombre, fecha, duracion, distancia)
VALUES ('Maratón Local', '2025-08-10 07:00:00+00', '03:00:00', 10),
       ('Clase Funcional Premium', '2025-08-15 18:00:00+00', '01:00:00', 0),
       ('Entrenamiento Outdoor', '2025-08-20 06:30:00+00', '01:30:00', 4),
       ('Zumba Night', '2025-09-01 20:00:00+00', '00:50:00', 0),
       ('Caminata Ecológica', '2025-09-05 09:00:00+00', '02:00:00', 5),
       ('Clase Fuerza Total', '2025-09-10 18:00:00+00', '00:45:00', 0),
       ('Ciclismo Ruta', '2025-09-15 07:00:00+00', '02:30:00', 20),
       ('Festival de Cardio', '2025-10-01 10:00:00+00', '02:00:00', 0),
       ('Clase HIIT Master', '2025-10-03 07:30:00+00', '00:35:00', 0),
       ('Pilates Avanzado', '2025-10-07 19:00:00+00', '01:00:00', 0),
       ('Caminata familiar', '2025-10-12 09:00:00+00', '01:45:00', 3),
       ('Run Sunset', '2025-10-20 17:30:00+00', '01:20:00', 7),
       ('Bike Indoor', '2025-10-25 06:00:00+00', '00:45:00', 0),
       ('Yoga Sunset', '2025-11-02 18:00:00+00', '01:00:00', 0),
       ('Cardio Express', '2025-11-05 12:00:00+00', '00:30:00', 0),
       ('Climb Training', '2025-11-10 10:00:00+00', '01:00:00', 0),
       ('Circuito intensivo', '2025-11-13 08:00:00+00', '00:50:00', 0),
       ('CrossFit Cup', '2025-11-20 09:00:00+00', '01:20:00', 0),
       ('Functional Day', '2025-11-22 07:30:00+00', '01:10:00', 0),
       ('Mega Entrenamiento Global', '2025-12-01 07:00:00+00', '02:00:00', 0);

-- ============================================================
-- REGISTRO DE ACTIVIDAD MASIVO (150+ registros)
-- ============================================================
INSERT INTO registro_actividad (id_usuario, id_actividad, id_evento, fecha, duracion,
                                perdida_calorias_estimadas, perdida_calorias_alcanzadas)
SELECT u.id_usuario,
       a.id_actividad,
       e.id_evento,
       (CURRENT_DATE - (random() * 40)::int),
       (interval '20 minutes' + (random() * 3600)::int * interval '1 second'),
       (200 + (random() * 450)::int),
       (180 + (random() * 430)::int)
FROM usuario u
         CROSS JOIN actividad a
         JOIN evento e ON e.id_evento >= 1 AND e.id_evento <= 10
WHERE random() > 0.55 -- controla la cantidad total generada
LIMIT 180;

-- ============================================================
-- NUEVAS METAS PARA DAR MÁS DATA A REPORTES COMPLEJOS
-- ============================================================
INSERT INTO meta (id_usuario, fecha_inicio, fecha_fin, objetivo, perdida_calorias_diarias)
SELECT id_usuario,
       CURRENT_DATE - (random() * 60)::int,
       CURRENT_DATE + (random() * 60)::int,
       CASE
           WHEN random() > 0.5 THEN 'Incrementar masa muscular'
           ELSE 'Mejorar resistencia'
           END,
       (200 + (random() * 350)::int)
FROM usuario;

-- ============================================================
-- HISTORIAL DE PROGRESO MASIVO
-- ============================================================
INSERT INTO historial_progreso (id_usuario, id_meta, fecha, avance_objetivo)
SELECT u.id_usuario,
       m.id_meta,
       CURRENT_DATE - (random() * 25)::int,
       CONCAT(((random() * 100)::int), '%')
FROM usuario u
         JOIN meta m ON m.id_usuario = u.id_usuario
WHERE random() > 0.4
LIMIT 120;

COMMIT;
