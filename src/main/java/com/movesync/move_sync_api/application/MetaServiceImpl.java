package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.port.interactor.IMetaService;
import com.movesync.move_sync_api.application.port.output.IMetaRepository;
import com.movesync.move_sync_api.domain.entity.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaServiceImpl implements IMetaService {

    @Autowired
    private IMetaRepository metaRepository;

    @Override
    public List<Meta> listarMetas() {
        return metaRepository.findAll();
    }

    @Override
    public Meta obtenerPorId(String idMeta) {
        return metaRepository.findById(idMeta);
    }

    @Override
    public void registrarMeta(Meta meta) {
        validarMeta(meta);
        metaRepository.save(meta);
    }

    @Override
    public void actualizarMeta(Meta meta) {
        validarMeta(meta);
        metaRepository.update(meta);
    }

    @Override
    public void eliminarMeta(String idMeta) {
        metaRepository.deleteById(idMeta);
    }

    private void validarMeta(Meta meta) {
        if (meta.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria.");
        }
        if (meta.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha fin es obligatoria.");
        }
        if (meta.getFechaFin().isBefore(meta.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha de inicio.");
        }
        if (meta.getObjetivo() == null || meta.getObjetivo().isBlank()) {
            throw new IllegalArgumentException("El objetivo es obligatorio.");
        }
    }
}
