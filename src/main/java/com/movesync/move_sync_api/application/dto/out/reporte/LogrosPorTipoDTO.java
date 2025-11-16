package com.movesync.move_sync_api.application.dto.out.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogrosPorTipoDTO {
    private String tipo;
    private Long cantidad;
    private Double porcentaje;
}
