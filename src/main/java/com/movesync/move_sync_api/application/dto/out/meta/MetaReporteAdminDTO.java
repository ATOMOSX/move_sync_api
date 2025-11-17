package com.movesync.move_sync_api.application.dto.out.meta;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetaReporteAdminDTO {
    private Integer idMeta;
    private String usuario;
    private Integer diasRegistrados;
    private Integer diasRestantes;
    private Double perdidaCaloriasDiarias;
    private Double totalCalorias;
}
