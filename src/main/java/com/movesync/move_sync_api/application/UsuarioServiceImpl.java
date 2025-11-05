package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.port.interactor.IUsuarioService;
import com.movesync.move_sync_api.application.port.output.IUsuarioRepository;
import com.movesync.move_sync_api.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public Usuario obtenerPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula);
    }

    @Override
    public Usuario obtenerPorCorreo(String correo) {
        // Compatibilidad: si piden buscar por correo, lo interpretamos como búsqueda por cédula
        // (según requisito del usuario). Intentamos buscar por cédula con el valor proporcionado.
        return usuarioRepository.findByCedula(correo);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarioRepository.update(usuario);
    }

    @Override
    public void eliminarUsuario(String idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getPrimerNombre() == null || usuario.getPrimerNombre().isBlank()) {
            throw new IllegalArgumentException("El primer nombre es obligatorio.");
        }
        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
    }
}
