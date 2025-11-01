package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private String idUsuario;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String cedula;

    private Double peso;

    private Integer estatura;

    private String genero;

    private String contrasena;

    private String correo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
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
