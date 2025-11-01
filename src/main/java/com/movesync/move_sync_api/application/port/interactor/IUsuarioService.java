package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.domain.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();

    Usuario obtenerPorId(String idUsuario);

    Usuario obtenerPorCorreo(String correo);

    void registrarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(String idUsuario);
}
