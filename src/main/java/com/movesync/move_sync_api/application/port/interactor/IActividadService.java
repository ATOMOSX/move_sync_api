package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;

import java.util.List;

public interface IActividadService {
    List<ActividadRankingDTO> obtenerRankingActividades(String idUsuario);
}
