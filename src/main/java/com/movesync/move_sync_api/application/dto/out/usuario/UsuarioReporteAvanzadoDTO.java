package com.movesync.move_sync_api.application.dto.out.usuario;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioReporteAvanzadoDTO {

    private Integer idUsuario;
    private String nombreCompleto;

    private String nivelActividad;
    private String gastoEnergetico;
    private String imc;

    private Double promedioCaloriasUsuario;
    private String actividadFavorita;

    private LocalDate diaMasActivo;
    private Double caloriasDiaTop;

    private Double promedioGeneral;
    private Double maxCaloriasGlobal;
    private Double diferenciaPromedio;

    private String metaActual;
    private Integer diasRegistrados;
    private Integer diasRestantes;
}
