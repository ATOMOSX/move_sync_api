package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.domain.entity.Usuario;

import java.util.List;

public interface IUsuarioRepository {

    List<Usuario> findAll();
    Usuario findById(String idUsuario);
    Usuario findByCedula(String cedula);
    void save(Usuario usuario);
    void update(Usuario usuario);
    void deleteById(String idUsuario);
}
