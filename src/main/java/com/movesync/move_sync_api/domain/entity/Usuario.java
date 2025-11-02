package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @Past
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Recomendacion> recomendaciones;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PerfilSalud> perfilesSalud;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Notificacion> notificaciones;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Logro> logros;
}
