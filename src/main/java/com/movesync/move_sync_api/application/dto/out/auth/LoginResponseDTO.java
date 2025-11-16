package com.movesync.move_sync_api.application.dto.out.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
}
