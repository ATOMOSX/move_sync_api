package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rutina {

    private String idRutina;

    private String idUsuario;

    private String nombre;

    private String descripcion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN)
    private LocalDateTime fechaCreacion;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<DetalleRutina> detalles;
}
