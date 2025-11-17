package com.movesync.move_sync_api.application.dto.out.meta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetasActivasVsFinalizadasDTO {
    private String estadoMeta;
    private String descripcionEstado;
    private Long cantidad;
    private Double porcentaje;
}

