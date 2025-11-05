package com.movesync.move_sync_api.application.dto.out.evento;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class EventoResponseDTO {
    private String idEvento;
    private LocalTime duracion;
    private LocalDateTime fecha;
    private String nombre;
}
