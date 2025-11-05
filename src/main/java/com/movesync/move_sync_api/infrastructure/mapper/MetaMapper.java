package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.in.meta.MetaRequestDTO;
import com.movesync.move_sync_api.application.dto.out.meta.MetaResponseDTO;
import com.movesync.move_sync_api.domain.entity.Meta;

public class MetaMapper {
    public static Meta toEntity(MetaRequestDTO dto) {
        return Meta.builder()
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .objetivo(dto.getObjetivo())
                .perdidaCaloriasDiarias(dto.getPerdidaCaloriasDiarias())
                .build();
    }

    public static MetaResponseDTO toResponse(Meta entity) {
        return MetaResponseDTO.builder()
                .idMeta(entity.getIdMeta())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .objetivo(entity.getObjetivo())
                .perdidaCaloriasDiarias(entity.getPerdidaCaloriasDiarias())
                .build();
    }
}
