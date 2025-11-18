package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;
import com.movesync.move_sync_api.application.port.input.IActividadController;
import com.movesync.move_sync_api.application.port.interactor.IActividadService;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/actividades")
public class ActividadController implements IActividadController {

    @Autowired
    private IActividadService actividadService;

    @Override
    @GetMapping("/ranking/{idUsuario}")
    public ResponseEntity<ApiResponse<List<ActividadRankingDTO>>> ranking(@PathVariable String idUsuario) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        Constants.RANKING_ACTIVIDADES_OBTENIDO,
                        actividadService.obtenerRankingActividades(idUsuario)
                )
        );
    }
}
