package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.domain.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();

    Usuario obtenerPorId(String idUsuario);

    // Mantener obtenerPorCorreo por compatibilidad pero delegará a obtenerPorCedula en la implementación
    Usuario obtenerPorCorreo(String correo);

    Usuario obtenerPorCedula(String cedula);

    void registrarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(String idUsuario);
}
