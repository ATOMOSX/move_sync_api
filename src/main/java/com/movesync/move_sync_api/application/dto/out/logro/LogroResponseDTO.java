package com.movesync.move_sync_api.application.dto.out.logro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogroResponseDTO {
    private String idLogro;
    private String nombre;
    private String recompensa;
    private String descripcion;
    private String tipo;
    private String idUsuario;
}
