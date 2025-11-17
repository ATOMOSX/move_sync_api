package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;
import com.movesync.move_sync_api.application.port.interactor.IActividadService;
import com.movesync.move_sync_api.application.port.output.meta.IActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    private IActividadRepository actividadRepository;

    @Override
    public List<ActividadRankingDTO> obtenerRankingActividades(String idUsuario) {
        Integer usuarioId = Integer.parseInt(idUsuario);
        return actividadRepository.obtenerRankingActividades(usuarioId);
    }
}
