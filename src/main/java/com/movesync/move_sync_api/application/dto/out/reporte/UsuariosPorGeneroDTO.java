package com.movesync.move_sync_api.application.dto.out.reporte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosPorGeneroDTO {
    private String genero;
    private String descripcionGenero;
    private Long cantidad;
    private Double porcentaje;
}
