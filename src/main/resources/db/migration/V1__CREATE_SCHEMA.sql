-- Table: public.Usuario

-- DROP TABLE IF EXISTS public."Usuario";

CREATE TABLE IF NOT EXISTS public."Usuario"
(
    "idUsuario" "char" NOT NULL,
    "primerNombre" "char" NOT NULL,
    "segundNombre" "char",
    "primerApellido" "char" NOT NULL,
    "segundoApellido" "char",
    cedula "char" NOT NULL,
    peso integer,
    estatura integer,
    genero "char" NOT NULL,
    "contraseña" "char" NOT NULL,
    correo "char" NOT NULL,
    "fechaNacimiento" date NOT NULL,
    CONSTRAINT "Usuario_pkey" PRIMARY KEY ("idUsuario")
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Usuario"
    OWNER to postgres;

-- Table: public.Recomendacion

-- DROP TABLE IF EXISTS public."Recomendacion";

CREATE TABLE IF NOT EXISTS public."Recomendacion"
(
    "idRecomendacion" "char" NOT NULL,
    mensaje "char" NOT NULL,
    "idUsuario" "char" NOT NULL,
    CONSTRAINT "Recomendacion_pkey" PRIMARY KEY ("idRecomendacion"),
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Recomendacion"
    OWNER to postgres;

-- Table: public.PerfilSalud

-- DROP TABLE IF EXISTS public."PerfilSalud";

CREATE TABLE IF NOT EXISTS public."PerfilSalud"
(
    "idPerfil" "char" NOT NULL,
    "nivelActividad" "char" NOT NULL,
    "gastoEnergetico" "char" NOT NULL,
    "condicionCardiovascular" "char" NOT NULL,
    imc "char" NOT NULL,
    "balanceEnergetico" "char" NOT NULL,
    "idUsuario" "char" NOT NULL,
    CONSTRAINT "Perfil_pkey" PRIMARY KEY ("idPerfil"),
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."PerfilSalud"
    OWNER to postgres;

-- Table: public.Notificacion

-- DROP TABLE IF EXISTS public."Notificacion";

CREATE TABLE IF NOT EXISTS public."Notificacion"
(
    "idNotificacion" "char" NOT NULL,
    mensaje text COLLATE pg_catalog."default" NOT NULL,
    "idUsuario" "char" NOT NULL,
    fecha date NOT NULL,
    CONSTRAINT "Notificacion_pkey" PRIMARY KEY ("idNotificacion"),
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Notificacion"
    OWNER to postgres;

-- Table: public.Logro

-- DROP TABLE IF EXISTS public."Logro";

CREATE TABLE IF NOT EXISTS public."Logro"
(
    "idLogro" "char" NOT NULL,
    nombre "char" NOT NULL,
    recompensa "char" NOT NULL,
    descripcion "char" NOT NULL,
    tipo "char" NOT NULL,
    "idUsuario" "char" NOT NULL,
    CONSTRAINT "Logro_pkey" PRIMARY KEY ("idLogro"),
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Logro"
    OWNER to postgres;

-- Table: public.Evento

-- DROP TABLE IF EXISTS public."Evento";

CREATE TABLE IF NOT EXISTS public."Evento"
(
    "idEvento" "char" NOT NULL,
    duracion time(6) without time zone NOT NULL,
    fecha timestamp(6) with time zone NOT NULL,
                         nombre "char" NOT NULL,
                         CONSTRAINT "Evento_pkey" PRIMARY KEY ("idEvento")
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Evento"
    OWNER to postgres;

-- Table: public.RegistroParticipantes

-- DROP TABLE IF EXISTS public."RegistroParticipantes";

CREATE TABLE IF NOT EXISTS public."RegistroParticipantes"
(
    "idRegistroParticipantes" "char" NOT NULL,
    "idEvento" "char" NOT NULL,
    "idUsuario" "char" NOT NULL,
    CONSTRAINT "RegistroParticipantes_pkey" PRIMARY KEY ("idRegistroParticipantes"),
    CONSTRAINT "idEvento" FOREIGN KEY ("idEvento")
    REFERENCES public."Evento" ("idEvento") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."RegistroParticipantes"
    OWNER to postgres;

-- Table: public.TipoActividad

-- DROP TABLE IF EXISTS public."TipoActividad";

CREATE TABLE IF NOT EXISTS public."TipoActividad"
(
    "idTipo" "char" NOT NULL,
    nombre "char" NOT NULL,
    CONSTRAINT "TipoActividad_pkey" PRIMARY KEY ("idTipo")
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."TipoActividad"
    OWNER to postgres;

-- Table: public.Actividad

-- DROP TABLE IF EXISTS public."Actividad";

CREATE TABLE IF NOT EXISTS public."Actividad"
(
    "idActividad" "char" NOT NULL,
    "idTipo" "char" NOT NULL,
    descripcion "char" NOT NULL,
    CONSTRAINT "Actividad_pkey" PRIMARY KEY ("idActividad"),
    CONSTRAINT "idTipoActividad" FOREIGN KEY ("idTipo")
    REFERENCES public."TipoActividad" ("idTipo") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Actividad"
    OWNER to postgres;

-- Table: public.CaloriasEstimadas

-- DROP TABLE IF EXISTS public."CaloriasEstimadas";

CREATE TABLE IF NOT EXISTS public."CaloriasEstimadas"
(
    "idCaloriasEstimadas" "char" NOT NULL,
    "idEvento" "char" NOT NULL,
    "idActividad" "char" NOT NULL,
    CONSTRAINT "CaloriasEstimadas_pkey" PRIMARY KEY ("idCaloriasEstimadas"),
    CONSTRAINT "idActividad" FOREIGN KEY ("idActividad")
    REFERENCES public."Actividad" ("idActividad") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT "idEvento" FOREIGN KEY ("idEvento")
    REFERENCES public."Evento" ("idEvento") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."CaloriasEstimadas"
    OWNER to postgres;

-- Table: public.Meta

-- DROP TABLE IF EXISTS public."Meta";

CREATE TABLE IF NOT EXISTS public."Meta"
(
    "idMeta" "char" NOT NULL,
    "fechaInicio" date NOT NULL,
    "fechaFin" date NOT NULL,
    objetivo "char" NOT NULL,
    "perdidaCaloriasDiarias" "char",
    CONSTRAINT "Meta_pkey" PRIMARY KEY ("idMeta")
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."Meta"
    OWNER to postgres;

-- Table: public.HistorialProgreso

-- DROP TABLE IF EXISTS public."HistorialProgreso";

CREATE TABLE IF NOT EXISTS public."HistorialProgreso"
(
    "idHistorial" "char" NOT NULL,
    "idUsuario" "char" NOT NULL,
    "idMeta" "char" NOT NULL,
    fecha date NOT NULL,
    "avanceObjetivo" "char" NOT NULL,
    CONSTRAINT "HistorialProgreso_pkey" PRIMARY KEY ("idHistorial"),
    CONSTRAINT "idMeta" FOREIGN KEY ("idMeta")
    REFERENCES public."Meta" ("idMeta") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT "idUsuario" FOREIGN KEY ("idUsuario")
    REFERENCES public."Usuario" ("idUsuario") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."HistorialProgreso"
    OWNER to postgres;