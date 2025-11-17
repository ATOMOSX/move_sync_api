package com.movesync.move_sync_api.application.dto.out.reporte;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteCategoriaTopDTO {
    private String categoriaTop;

    private Double totalCaloriasCategoria;
    private String actividadMasFrecuente;
    private Double promedioCaloriasPorActividad;

    private Double totalGlobal;
    private Double diferenciaConPromedioCategorias;

    private Double totalCardio;
    private Double diferenciaVsCardio;
}
