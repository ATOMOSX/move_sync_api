package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import com.movesync.move_sync_api.application.port.input.IReporteController;
import com.movesync.move_sync_api.application.port.interactor.IReporteService;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController implements IReporteController {

    @Autowired
    private IReporteService reporteService;

    @Override
    @GetMapping("/categoria-top")
    public ResponseEntity<ApiResponse<ReporteCategoriaTopDTO>> reporteCategoriaTop() {
        ReporteCategoriaTopDTO reporte = reporteService.obtenerReporte();
        return ResponseEntity.ok(
                ApiResponse.success(Constants.REPORTE_GENERADO, reporte)
        );
    }
}
