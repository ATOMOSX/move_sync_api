package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.application.dto.out.registro_actividad.RegistroActividadReporteDTO;

import java.util.List;

public interface IReporteActividadRepository {
    List<RegistroActividadReporteDTO> obtenerHistorialDetallado(Integer idUsuario);
}
