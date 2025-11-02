package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.domain.entity.Logro;

import java.util.List;

public interface ILogroService {
    List<Logro> listarLogros();
    Logro obtenerPorId(String idLogro);
    List<Logro> obtenerPorUsuario(String idUsuario);
    void registrarLogro(Logro logro);
    void actualizarLogro(Logro logro);
    void eliminarLogro(String idLogro);
}
