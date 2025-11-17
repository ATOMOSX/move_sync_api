package com.movesync.move_sync_api.application.dto.out.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaReporteDTO {
    private Integer idMeta;
    private String objetivo;
    private String fechaInicio;
    private String fechaFin;
    private Double perdidaCaloriasDiarias;
    private Integer diasRegistrados;
    private Long diasRestantes;
    private Double promedioCalorias;
}
