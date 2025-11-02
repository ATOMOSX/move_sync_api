package com.movesync.move_sync_api.application.dto.out.meta;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MetaResponseDTO {
    private String idMeta;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String objetivo;
    private String perdidaCaloriasDiarias;
}
