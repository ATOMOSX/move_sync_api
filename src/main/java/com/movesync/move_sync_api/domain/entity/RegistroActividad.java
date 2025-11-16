package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;

import java.time.LocalDate;

public class RegistroActividad {
    private String idRegistroActividad;
    private String idUsuario;
    private String idActividad;
    private String idEvento;

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
    private LocalDate fecha;
    private String duracion;
    private Double perdidaCaloriasEstimadas;
    private Double perdidaCaloriasAlcanzadas;
}
