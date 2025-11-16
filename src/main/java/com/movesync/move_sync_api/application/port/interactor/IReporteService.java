package com.movesync.move_sync_api.application.port.interactor;

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
    
    // Aquí se agregarán más métodos para otros reportes
}
