package com.movesync.move_sync_api.application.dto.in.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El primer nombre no puede estar vacío")
    private String primerNombre;

    @NotBlank(message = "El segundo nombre no puede estar vacío")
    private String segundoNombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    private String primerApellido;

    @NotBlank(message = "El segundo apellido no puede estar vacío")
    private String segundoApellido;

    @NotBlank(message = "La cédula no puede estar vacía")
    private String cedula;

    @NotNull(message = "El peso no puede estar vacío")
    private Double peso;

    @NotNull(message = "La estatura no puede estar vacía")
    private Integer estatura;

    @NotBlank(message = "El género no puede estar vacío")
    private String genero;

    private String contrasena;

    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
    private LocalDate fechaNacimiento;
}
