package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.domain.entity.Logro;

import java.util.List;

public interface ILogroRepository {
    List<Logro> findAll();
    Logro findById(String idLogro);
    List<Logro> findByUsuario(String idUsuario);
    void save(Logro logro);
    void update(Logro logro);
    void deleteById(String idLogro);
}
