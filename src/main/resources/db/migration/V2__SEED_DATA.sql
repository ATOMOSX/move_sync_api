
BEGIN;

-- ============================================
-- USUARIOS
-- ============================================
INSERT INTO usuario (
    primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
    cedula, peso, estatura, genero, contrasena, correo, fecha_nacimiento
)
VALUES
    ('Carlos', 'Andrés', 'Gómez', 'Pérez', '12345678', 78, 175, 'M', 'password123', 'carlos@example.com', '1990-05-15'),
    ('María', 'Luisa', 'Rodríguez', 'Soto', '87654321', 62, 165, 'F', 'password123', 'maria@example.com', '1992-09-03'),
    ('Luis', 'Miguel', 'Herrera', 'Díaz', '23456789', 85, 180, 'M', 'pass123', 'luis@example.com', '1985-11-20'),
    ('Ana', 'Sofía', 'Vargas', 'Lopez', '34567890', 58, 162, 'F', 'pass123', 'ana@example.com', '1998-07-12'),
    ('Pedro', 'Ramón', 'Suarez', 'Martinez', '45678901', 90, 185, 'M', 'pass123', 'pedro@example.com', '1980-03-05'),
    ('Lucía', 'Beatriz', 'Núñez', 'García', '56789012', 68, 168, 'F', 'pass123', 'lucia@example.com', '1993-12-01');

-- ============================================
-- PERFIL DE SALUD
-- ============================================
INSERT INTO perfil_salud (
    id_usuario, nivel_actividad, gasto_energetico, condicion_cardiovascular,
    imc, balance_energetico
)
VALUES
    (1, 'Moderado', '2000 kcal', 'Bueno', '25.5', 'Equilibrado'),
    (2, 'Bajo', '1800 kcal', 'Regular', '22.8', 'Déficit leve'),
    (3, 'Alto', '2600 kcal', 'Excelente', '26.2', 'Superávit leve'),
    (4, 'Moderado', '1900 kcal', 'Bueno', '22.1', 'Equilibrado'),
    (5, 'Bajo', '2100 kcal', 'Regular', '26.3', 'Déficit'),
    (6, 'Moderado', '2000 kcal', 'Bueno', '24.1', 'Equilibrado');

-- ============================================
-- RECOMENDACIONES
-- ============================================
INSERT INTO recomendacion (id_usuario, mensaje)
VALUES
    (1, 'Aumentar consumo de agua a 2L diarios.'),
    (2, 'Caminar 30 minutos diarios.'),
    (3, 'Incluir 5 porciones de fruta al día.'),
    (4, 'Mantener postura correcta en el trabajo.'),
    (5, 'Reducir bebidas azucaradas.'),
    (6, 'Realizar estiramientos matutinos 10 min.');

-- ============================================
-- NOTIFICACIONES
-- ============================================
INSERT INTO notificacion (id_usuario, mensaje, fecha)
VALUES
    (1, 'Recordatorio: sesión de hoy a las 18:00.', '2025-01-10'),
    (2, 'Nueva recomendación disponible.', '2025-01-11'),
    (3, 'Tu objetivo semanal se actualizó.', '2025-01-12'),
    (4, 'Clase nueva disponible: Pilates.', '2025-01-13'),
    (5, 'Promoción en membresía premium.', '2025-01-14'),
    (6, 'Recordatorio: 10 min estiramientos.', '2025-01-15');

-- ============================================
-- LOGROS
-- ============================================
INSERT INTO logro (id_usuario, nombre, recompensa, descripcion, tipo)
VALUES
    (1, 'Primer día', 'Badge Bronze', 'Completaste tu primera actividad', 'Actividad'),
    (2, '5 días seguidos', 'Badge Silver', 'Actividad durante 5 días consecutivos', 'Consistencia'),
    (3, 'Semana Activa', 'Badge Gold', 'Completaste 7 días de actividad', 'Consistencia'),
    (4, 'Flexibilidad', 'Badge Flex', 'Completaste 10 sesiones de estiramiento', 'Habilidad'),
    (5, 'Perseverancia', 'Badge Iron', '10 metas alcanzadas', 'Consistencia'),
    (6, 'Social', 'Badge Friend', 'Invitaste a un amigo', 'Social');

-- ============================================
-- ACTIVIDADES
-- ============================================
INSERT INTO actividad (nombre, tipo)
VALUES
    ('Trote liviano 30 min', 'Cardio'),
    ('Circuito de resistencia 45 min', 'Fuerza'),
    ('HIIT 30 min - intervalos', 'HIIT'),
    ('Pilates nivel básico', 'Pilates'),
    ('Caminata rápida 45 min', 'Cardio'),
    ('Levantamiento ligero 30 min', 'Fuerza');

-- ============================================
-- EVENTOS
-- ============================================
INSERT INTO evento (nombre, fecha, duracion, distancia)
VALUES
    ('Caminata Comunitaria', '2025-06-15 10:00:00+00', '01:30:00', 5.0),
    ('Clase de Yoga', '2025-06-20 08:30:00+00', '00:45:00', 0),
    ('Clase de HIIT Matutino', '2025-07-01 07:00:00+00', '00:30:00', 0),
    ('Pilates en Pareja', '2025-07-05 18:00:00+00', '01:00:00', 0);

-- ============================================
-- REGISTROS DE ACTIVIDAD
-- ============================================
INSERT INTO registro_actividad (
    id_usuario, id_actividad, id_evento, fecha, duracion,
    perdida_calorias_estimadas, perdida_calorias_alcanzadas
)
VALUES
    (1, 1, 1, '2025-06-15', '01:30:00', 350, 300),
    (2, 2, 2, '2025-06-20', '00:45:00', 250, 240),
    (3, 3, 3, '2025-07-01', '00:30:00', 280, 270),
    (4, 4, 4, '2025-07-05', '01:00:00', 220, 210),
    (5, 5, 1, '2025-06-15', '00:45:00', 300, 290),
    (6, 6, 2, '2025-06-20', '00:30:00', 200, 195);

-- ============================================
-- METAS
-- ============================================
INSERT INTO meta (id_usuario, fecha_inicio, fecha_fin, objetivo, perdida_calorias_diarias)
VALUES
    (1, '2025-01-01', '2025-03-31', 'Perder 4 kg', 500),
    (2, '2025-02-01', '2025-04-30', 'Mejorar resistencia', 300),
    (3, '2025-03-01', '2025-05-31', 'Ganar masa muscular', 400),
    (4, '2025-04-01', '2025-06-30', 'Mejorar flexibilidad', 200),
    (5, '2025-05-01', '2025-07-31', 'Reducir grasa corporal', 350),
    (6, '2025-06-01', '2025-08-31', 'Mantener peso actual', 250);

-- ============================================
-- HISTORIAL DE PROGRESO
-- ============================================
INSERT INTO historial_progreso (id_usuario, id_meta, fecha, avance_objetivo)
VALUES
    (1, 1, '2025-01-15', '10%'),
    (2, 2, '2025-02-10', '20%'),
    (3, 3, '2025-03-20', '30%'),
    (4, 4, '2025-04-15', '25%'),
    (5, 3, '2025-03-30', '10%'),
    (6, 4, '2025-05-05', '60%');

COMMIT;