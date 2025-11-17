package com.movesync.move_sync_api.application.dto.out.evento;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventoEstadisticasDTO {
    private Integer idEvento;
    private String nombreEvento;
    private String fecha;

    private String actividadMasRealizada;

    private Double totalCalorias;
    private Double promedioCalorias;
    private Double maximoIndividual;

    private String usuarioTop;

    private Double diferenciaPromedioGeneral;
}
