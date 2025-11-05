package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.in.evento.EventoRequestDTO;
import com.movesync.move_sync_api.application.dto.out.evento.EventoResponseDTO;
import com.movesync.move_sync_api.domain.entity.Evento;

public class EventoMapper {
    public static Evento toEntity(EventoRequestDTO dto) {
        return Evento.builder()
                .duracion(dto.getDuracion())
                .fecha(dto.getFecha())
                .nombre(dto.getNombre())
                .build();
    }

    public static EventoResponseDTO toResponse(Evento entity) {
        return EventoResponseDTO.builder()
                .idEvento(entity.getIdEvento())
                .duracion(entity.getDuracion())
                .fecha(entity.getFecha())
                .nombre(entity.getNombre())
                .build();
    }
}
