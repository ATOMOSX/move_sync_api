package com.movesync.move_sync_api.application.dto.out.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDTO<T> {
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaGeneracion;
    private Long totalRegistros;
    private List<T> datos;
    private List<EstadisticaDTO> estadisticas;
}
