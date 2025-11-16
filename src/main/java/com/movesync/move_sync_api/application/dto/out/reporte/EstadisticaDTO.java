package com.movesync.move_sync_api.application.dto.out.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticaDTO {
    private String etiqueta;
    private Long cantidad;
    private Double porcentaje;
}
