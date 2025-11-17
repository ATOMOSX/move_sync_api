package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IActividadController {
    ResponseEntity<ApiResponse<List<ActividadRankingDTO>>> ranking(
            @PathVariable String idUsuario);
}
