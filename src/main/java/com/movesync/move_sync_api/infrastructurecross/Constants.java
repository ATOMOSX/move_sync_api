package com.movesync.move_sync_api.infrastructurecross;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Constants {
    private Constants() {
    }

    // Formatos de fecha y hora
    public static final String DATA_PATTERN = "dd-MM-yyyy";
    public static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("America/Bogota");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATA_PATTERN);

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    // Mensajeria endpoints
    public static final String USUARIO_OBTENIDOS = "Usuarios obtenidos correctamente.";
    public static final String USUARIO_REGISTRADO = "Usuario registrado correctamente.";
    public static final String USUARIO_ACTUALIZADO = "Usuario actualizado correctamente.";
    public static final String USUARIO_ELIMINADO = "Usuario eliminado correctamente.";


    // Errores
    public static final String ERROR_FORMATO_FECHA = "Formato de fecha inválido. Se espera dd/MM/yyyy";
    public static final String ERROR_FORMATO_FECHA_HORA = "Formato de fecha y hora inválido. Se espera dd/MM/yyyy HH:mm:ss";

}
