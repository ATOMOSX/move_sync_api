package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.port.interactor.IEventoService;
import com.movesync.move_sync_api.application.port.output.IEventoRepository;
import com.movesync.move_sync_api.domain.entity.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private IEventoRepository eventoRepository;

    @Override
    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento obtenerPorId(String idEvento) {
        return eventoRepository.findById(idEvento);
    }

    @Override
    public void registrarEvento(Evento evento) {
        validarEvento(evento);
        eventoRepository.save(evento);
    }

    @Override
    public void actualizarEvento(Evento evento) {
        validarEvento(evento);
        eventoRepository.update(evento);
    }

    @Override
    public void eliminarEvento(String idEvento) {
        eventoRepository.deleteById(idEvento);
    }

    private void validarEvento(Evento evento) {
        if (evento.getNombre() == null || evento.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio.");
        }
        if (evento.getFecha() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        if (evento.getDuracion() == null) {
            throw new IllegalArgumentException("La duración es obligatoria.");
        }
    }
}
