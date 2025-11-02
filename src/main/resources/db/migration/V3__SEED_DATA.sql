-- V3__SEED_DATA.sql - Datos de ejemplo para pruebas
BEGIN;

-- Usuarios
INSERT INTO usuario (id_usuario, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, cedula, peso,
                     estatura, genero, contrasena, correo, fecha_nacimiento) VALUES

('u1', 'Carlos', 'Andrés', 'Gómez', 'Pérez', '12345678', 78, 175, 'M', 'password123', 'carlos@example.com', '1990-05-15'),
('u2', 'María', 'Luisa', 'Rodríguez', 'Soto', '87654321', 62, 165, 'F', 'password123', 'maria@example.com', '1992-09-03'),
('u3', 'Luis', 'Miguel', 'Herrera', 'Díaz', '23456789', 85, 180, 'M', 'pass123', 'luis@example.com', '1985-11-20'),
('u4', 'Ana', 'Sofía', 'Vargas', 'Lopez', '34567890', 58, 162, 'F', 'pass123', 'ana@example.com', '1998-07-12'),
('u5', 'Pedro', 'Ramón', 'Suarez', 'Martinez', '45678901', 90, 185, 'M', 'pass123', 'pedro@example.com', '1980-03-05'),
('u6', 'Lucía', 'Beatriz', 'Núñez', 'García', '56789012', 68, 168, 'F', 'pass123', 'lucia@example.com', '1993-12-01');

-- Recomendaciones
INSERT INTO recomendacion (id_recomendacion, mensaje, id_usuario) VALUES

('rec1', 'Aumentar consumo de agua a 2L diarios.', 'u1'),
('rec2', 'Caminar 30 minutos diarios.', 'u2'),
('rec3', 'Incluir 5 porciones de fruta al día.', 'u3'),
('rec4', 'Mantener postura correcta en el trabajo.', 'u4'),
('rec5', 'Reducir bebidas azucaradas.', 'u5'),
('rec6', 'Realizar estiramientos matutinos 10 min.', 'u6');

-- Perfil de salud
INSERT INTO perfil_salud (id_perfil, nivel_actividad, gasto_energetico, condicion_cardiovascular,
                          imc, balance_energetico, id_usuario) VALUES

('ps1', 'Moderado', '2000 kcal', 'Bueno', '25.5', 'Equilibrado', 'u1'),
('ps2', 'Bajo', '1800 kcal', 'Regular', '22.8', 'Déficit leve', 'u2'),
('ps3', 'Alto', '2600 kcal', 'Excelente', '26.2', 'Superávit leve', 'u3'),
('ps4', 'Moderado', '1900 kcal', 'Bueno', '22.1', 'Equilibrado', 'u4'),
('ps5', 'Bajo', '2100 kcal', 'Regular', '26.3', 'Déficit', 'u5'),
('ps6', 'Moderado', '2000 kcal', 'Bueno', '24.1', 'Equilibrado', 'u6');

-- Notificaciones
INSERT INTO notificacion (id_notificacion, mensaje, id_usuario, fecha) VALUES

('n1', 'Recordatorio: sesión de hoy a las 18:00.', 'u1', '2025-01-10'),
('n2', 'Nueva recomendación disponible.', 'u2', '2025-01-11'),
('n3', 'Tu objetivo semanal se actualizó.', 'u3', '2025-01-12'),
('n4', 'Clase nueva disponible: Pilates.', 'u4', '2025-01-13'),
('n5', 'Promoción en membresía premium.', 'u5', '2025-01-14'),
('n6', 'Recordatorio: 10 min estiramientos.', 'u6', '2025-01-15');

-- Logros
INSERT INTO logro (id_logro, nombre, recompensa, descripcion, tipo, id_usuario) VALUES

('l1', 'Primer día', 'Badge Bronze', 'Completaste tu primera actividad', 'Actividad', 'u1'),
('l2', '5 días seguidos', 'Badge Silver', 'Actividad durante 5 días consecutivos', 'Consistencia', 'u2'),
('l3', 'Semana Activa', 'Badge Gold', 'Completaste 7 días de actividad', 'Consistencia', 'u3'),
('l4', 'Flexibilidad', 'Badge Flex', 'Completaste 10 sesiones de estiramiento', 'Habilidad', 'u4'),
('l5', 'Perseverancia', 'Badge Iron', '10 metas alcanzadas', 'Consistencia', 'u5'),
('l6', 'Social', 'Badge Friend', 'Invitaste a un amigo', 'Social', 'u6');

-- Eventos
INSERT INTO evento (id_evento, duracion, fecha, nombre) VALUES

('e1', '01:30:00', '2025-06-15 10:00:00+00', 'Caminata Comunitaria'),
('e2', '00:45:00', '2025-06-20 08:30:00+00', 'Clase de Yoga'),
('e3', '00:30:00', '2025-07-01 07:00:00+00', 'Clase de HIIT Matutino'),
('e4', '01:00:00', '2025-07-05 18:00:00+00', 'Pilates en Pareja');



-- Registro participantes
INSERT INTO registro_participantes (id_registro_participantes, id_evento, id_usuario) VALUES

('rp1', 'e1', 'u1'),
('rp2', 'e2', 'u2'),
('rp3', 'e3', 'u3'),
('rp4', 'e3', 'u4'),
('rp5', 'e4', 'u5'),
('rp6', 'e4', 'u6');


-- Tipos de actividad
INSERT INTO tipo_actividad (id_tipo, nombre) VALUES
('t1', 'Cardio'),
('t2', 'Fuerza'),
('t3', 'HIIT'),
('t4', 'Pilates');

-- Actividades
INSERT INTO actividad (id_actividad, id_tipo, descripcion) VALUES
('a1', 't1', 'Trote liviano 30 min'),
('a2', 't2', 'Circuito de resistencia 45 min'),
('a3', 't3', 'HIIT 30 min - intervalos'),
('a4', 't4', 'Pilates nivel básico'),
('a5', 't1', 'Caminata rápida 45 min'),
('a6', 't2', 'Levantamiento ligero 30 min');

-- Calorías estimadas (liga evento -> actividad)
INSERT INTO calorias_estimadas (id_calorias_estimadas, id_evento, id_actividad) VALUES
('ce1', 'e1', 'a1'),
('ce2', 'e2', 'a2'),
('ce3', 'e3', 'a3'),
('ce4', 'e4', 'a4'),
('ce5', 'e1', 'a5'),
('ce6', 'e2', 'a6');

-- Metas
INSERT INTO meta (id_meta, fecha_inicio, fecha_fin, objetivo, perdida_calorias_diarias) VALUES
('m1', '2025-01-01', '2025-03-31', 'Perder 4 kg', '500 kcal'),
('m2', '2025-02-01', '2025-04-30', 'Mejorar resistencia', '300 kcal'),
('m3', '2025-03-01', '2025-05-31', 'Ganar masa muscular', '400 kcal'),
('m4', '2025-04-01', '2025-06-30', 'Mejorar flexibilidad', '200 kcal');

-- Historial progreso
INSERT INTO historial_progreso (id_historial, id_usuario, id_meta, fecha, avance_objetivo) VALUES
('h1', 'u1', 'm1', '2025-01-15', '10%'),
('h2', 'u2', 'm2', '2025-02-10', '20%'),
('h3', 'u3', 'm3', '2025-03-20', '30%'),
('h4', 'u4', 'm4', '2025-04-15', '25%'),
('h5', 'u5', 'm3', '2025-03-30', '10%'),
('h6', 'u6', 'm4', '2025-05-05', '60%');


COMMIT;
