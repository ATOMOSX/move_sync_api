package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.dto.out.registro_actividad.RegistroActividadReporteDTO;
import com.movesync.move_sync_api.application.port.interactor.IReporteActividadService;
import com.movesync.move_sync_api.application.port.output.IReporteActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteActividadServiceImpl implements IReporteActividadService {

    @Autowired
    private IReporteActividadRepository reporteActividadRepository;

    @Override
    public List<RegistroActividadReporteDTO> obtenerHistorialDetallado(Integer idUsuario) {
        return reporteActividadRepository.obtenerHistorialDetallado(idUsuario);
    }
}
