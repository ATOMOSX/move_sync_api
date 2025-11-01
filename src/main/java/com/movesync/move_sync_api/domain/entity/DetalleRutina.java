package com.movesync.move_sync_api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleRutina {

    private String idDetalle;

    private String idRutina;

    private String idEjercicio;

    private Integer repeticiones;

    private Integer series;

    private Integer descansoSegundos;
}
