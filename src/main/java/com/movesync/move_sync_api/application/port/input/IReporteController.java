package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import org.springframework.http.ResponseEntity;

public interface IReporteController {
    ResponseEntity<ApiResponse<ReporteCategoriaTopDTO>> reporteCategoriaTop();
}
