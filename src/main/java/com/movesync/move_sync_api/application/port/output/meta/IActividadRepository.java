package com.movesync.move_sync_api.application.port.output.meta;

import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;

import java.util.List;

public interface IActividadRepository {
    List<ActividadRankingDTO> obtenerRankingActividades(Integer idUsuario);
}
