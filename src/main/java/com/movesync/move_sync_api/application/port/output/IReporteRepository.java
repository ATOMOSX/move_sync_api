package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;

import java.util.List;

public interface IReporteRepository {
    
    // Reporte Simple 1: Usuarios por género
    List<UsuariosPorGeneroDTO> obtenerUsuariosPorGenero();
    
    // Aquí se agregarán más métodos para otros reportes
}
