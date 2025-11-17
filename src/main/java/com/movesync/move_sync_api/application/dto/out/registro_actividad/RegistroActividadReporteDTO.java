package com.movesync.move_sync_api.application.dto.out.registro_actividad;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegistroActividadReporteDTO {
    private LocalDate fecha;
    private String actividad;
    private String duracion;
    private Double caloriasQuemadas;
    private Double caloriasEstimadas;
    private Double diferenciaCalorias;
    private String eventoAsociado;
    private Double promedioCaloriasUsuario;
}
