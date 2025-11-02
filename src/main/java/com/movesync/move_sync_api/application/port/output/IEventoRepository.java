package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.domain.entity.Evento;

import java.util.List;

public interface IEventoRepository {
    List<Evento> findAll();
    Evento findById(String idEvento);
    void save(Evento evento);
    void update(Evento evento);
    void deleteById(String idEvento);
}
