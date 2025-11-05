package com.movesync.move_sync_api.application.dto.out.usuario;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioResponseDTO {
    private String idUsuario;
    private String cedula;
    private String primerNombre;
    private String primerApellido;
    private String correo;
    private LocalDate fechaNacimiento;
}
