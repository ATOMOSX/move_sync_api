package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.domain.entity.Evento;

import java.util.List;

public interface IEventoService {
    List<Evento> listarEventos();
    Evento obtenerPorId(String idEvento);
    void registrarEvento(Evento evento);
    void actualizarEvento(Evento evento);
    void eliminarEvento(String idEvento);
}
