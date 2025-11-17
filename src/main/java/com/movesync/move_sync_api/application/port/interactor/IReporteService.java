package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.application.dto.out.reporte.LogrosPorTipoDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;

public interface IReporteService {
    ReporteResponseDTO<UsuariosPorGeneroDTO> obtenerReporteUsuariosPorGenero();
    byte[] generarPdfUsuariosPorGenero();
    ReporteCategoriaTopDTO obtenerReporte();
    ReporteResponseDTO<LogrosPorTipoDTO> obtenerReporteLogrosPorTipo();
    byte[] generarPdfLogrosPorTipo();
}
