package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.registro_actividad.RegistroActividadReporteDTO;
import com.movesync.move_sync_api.application.port.input.IReporteActividadController;
import com.movesync.move_sync_api.application.port.interactor.IReporteActividadService;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/reporte-actividad")
public class ReporteActividadController implements IReporteActividadController {

    @Autowired
    private IReporteActividadService reporteActividadService;

    @GetMapping("/historial-detallado/{idUsuario}")
    @Override
    public ResponseEntity<ApiResponse<List<RegistroActividadReporteDTO>>> obtenerHistorialDetallado(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(
                ApiResponse.success(Constants.REGISTRO_ACTIVIDAD_OBTENIDOS, reporteActividadService.obtenerHistorialDetallado(idUsuario))
        );
    }
}
