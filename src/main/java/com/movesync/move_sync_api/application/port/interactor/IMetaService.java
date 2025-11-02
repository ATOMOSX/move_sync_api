package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.domain.entity.Meta;

import java.util.List;

public interface IMetaService {
    List<Meta> listarMetas();
    Meta obtenerPorId(String idMeta);
    void registrarMeta(Meta meta);
    void actualizarMeta(Meta meta);
    void eliminarMeta(String idMeta);
}
