package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.in.logro.LogroRequestDTO;
import com.movesync.move_sync_api.application.dto.out.logro.LogroResponseDTO;
import com.movesync.move_sync_api.domain.entity.Logro;

public class LogroMapper {
    public static Logro toEntity(LogroRequestDTO dto) {
        return Logro.builder()
                .nombre(dto.getNombre())
                .recompensa(dto.getRecompensa())
                .descripcion(dto.getDescripcion())
                .tipo(dto.getTipo())
                .idUsuario(dto.getIdUsuario())
                .build();
    }

    public static LogroResponseDTO toResponse(Logro entity) {
        return LogroResponseDTO.builder()
                .idLogro(entity.getIdLogro())
                .nombre(entity.getNombre())
                .recompensa(entity.getRecompensa())
                .descripcion(entity.getDescripcion())
                .tipo(entity.getTipo())
                .idUsuario(entity.getIdUsuario())
                .build();
    }
}
