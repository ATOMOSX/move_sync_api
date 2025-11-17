package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;

import java.util.List;

public interface IReporteRepository {
    List<UsuariosPorGeneroDTO> obtenerUsuariosPorGenero();
    ReporteCategoriaTopDTO obtenerReporteCategoriaTop();
}
