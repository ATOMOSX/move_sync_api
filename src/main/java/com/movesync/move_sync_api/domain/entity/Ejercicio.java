package com.movesync.move_sync_api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ejercicio {

    private String idEjercicio;

    private String nombre;

    private String descripcion;

    private String categoria;

    private Integer duracionMinutos;
}
