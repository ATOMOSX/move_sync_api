package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.application.dto.out.reporte.LogrosPorTipoDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;

public interface IReporteService {

    /**
     * Reporte Simple 1: Usuarios por género
     */
    ReporteResponseDTO<UsuariosPorGeneroDTO> obtenerReporteUsuariosPorGenero();

    /**
     * Genera PDF del reporte de usuarios por género
     */
    byte[] generarPdfUsuariosPorGenero();

    /**
     * Reporte Simple 2: Logros por tipo
     */
    ReporteResponseDTO<LogrosPorTipoDTO> obtenerReporteLogrosPorTipo();

    /**
     * Genera PDF del reporte de logros por tipo
     */
    byte[] generarPdfLogrosPorTipo();

    // Aquí se agregarán más métodos para otros reportes}
}