package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.movesync.move_sync_api.infrastructurecross.Constants;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private String idUsuario;

    @NotBlank
    private String primerNombre;

    private String segundoNombre;

    @NotBlank
    private String primerApellido;

    private String segundoApellido;

    @NotBlank
    private String cedula;

    private Double peso;
    private Integer estatura;

    private String genero;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String correo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
    private LocalDate fechaNacimiento;

    public String getNombrecompleto() {
        StringBuilder sb = new StringBuilder();

        if (primerNombre != null) sb.append(primerNombre).append(" ");
        if (segundoNombre != null && !segundoNombre.isBlank()) sb.append(segundoNombre).append(" ");
        if (primerApellido != null) sb.append(primerApellido).append(" ");
        if (segundoApellido != null && !segundoApellido.isBlank()) sb.append(segundoApellido);

        return sb.toString().trim();
    }

}
