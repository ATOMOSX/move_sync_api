package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialProgreso {

    private String idHistorial;

    private String idUsuario;

    private String idMeta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
    private LocalDate fecha;

    private String avanceObjetivo;
}
