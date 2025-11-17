package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.application.dto.out.registro_actividad.RegistroActividadReporteDTO;

import java.util.List;

public interface IReporteActividadService {
    List<RegistroActividadReporteDTO> obtenerHistorialDetallado(Integer idUsuario);
}
