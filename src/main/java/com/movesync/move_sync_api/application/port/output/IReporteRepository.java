package com.movesync.move_sync_api.application.port.output;

import java.util.List;

import com.movesync.move_sync_api.application.dto.out.reporte.LogrosPorTipoDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;

public interface IReporteRepository {
    
    // Reporte Simple 1: Usuarios por género
    List<UsuariosPorGeneroDTO> obtenerUsuariosPorGenero();
    
     // Reporte Simple 2: Logros por tipo
    List<LogrosPorTipoDTO> obtenerLogrosPorTipo();

    // Aquí se agregarán más métodos para otros reportes
}
