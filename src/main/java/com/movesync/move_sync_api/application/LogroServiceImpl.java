package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.port.interactor.ILogroService;
import com.movesync.move_sync_api.application.port.output.ILogroRepository;
import com.movesync.move_sync_api.domain.entity.Logro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogroServiceImpl implements ILogroService {

    @Autowired
    private ILogroRepository logroRepository;

    @Override
    public List<Logro> listarLogros() {
        return logroRepository.findAll();
    }

    @Override
    public Logro obtenerPorId(String idLogro) {
        return logroRepository.findById(idLogro);
    }

    @Override
    public List<Logro> obtenerPorUsuario(String idUsuario) {
        return logroRepository.findByUsuario(idUsuario);
    }

    @Override
    public void registrarLogro(Logro logro) {
        validarLogro(logro);
        logroRepository.save(logro);
    }

    @Override
    public void actualizarLogro(Logro logro) {
        validarLogro(logro);
        logroRepository.update(logro);
    }

    @Override
    public void eliminarLogro(String idLogro) {
        logroRepository.deleteById(idLogro);
    }

    private void validarLogro(Logro logro) {
        if (logro.getNombre() == null || logro.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del logro es obligatorio.");
        }
        if (logro.getRecompensa() == null || logro.getRecompensa().isBlank()) {
            throw new IllegalArgumentException("La recompensa es obligatoria.");
        }
        if (logro.getDescripcion() == null || logro.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("La descripción es obligatoria.");
        }
        if (logro.getTipo() == null || logro.getTipo().isBlank()) {
            throw new IllegalArgumentException("El tipo es obligatorio.");
        }
        if (logro.getIdUsuario() == null || logro.getIdUsuario().isBlank()) {
            throw new IllegalArgumentException("El id de usuario es obligatorio.");
        }
    }
}
