package com.movesync.move_sync_api.application.dto.in.evento;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class EventoRequestDTO {

    @NotNull(message = "La duración no puede estar vacía")
    private LocalTime duracion;

    @NotNull(message = "La fecha no puede estar vacía")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_TIME_PATTERN)
    private LocalDateTime fecha;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
}
