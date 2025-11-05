package com.movesync.move_sync_api.application.dto.in.logro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogroRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La recompensa no puede estar vacía")
    private String recompensa;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "El tipo no puede estar vacío")
    private String tipo;

    @NotBlank(message = "El id de usuario no puede estar vacío")
    private String idUsuario;
}
