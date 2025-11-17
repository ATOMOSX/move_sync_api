package com.movesync.move_sync_api.application.dto.out.actividad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActividadRankingDTO {
    private String actividad;
    private Integer vecesRealizada;
    private String duracionTotal;
    private Double promedioCalorias;
    private Integer totalActividadesUsuario;
}
