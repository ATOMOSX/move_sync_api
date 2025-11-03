package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import com.movesync.move_sync_api.application.port.input.IUsuarioController;
import com.movesync.move_sync_api.application.port.interactor.IUsuarioService;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructure.mapper.UsuarioMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController implements IUsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        List<UsuarioResponseDTO> response = usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_OBTENIDOS, response));
    }

    @Override
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorId(@PathVariable String idUsuario) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_OBTENIDOS, response));
    }

    @Override
    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorCedula(@PathVariable String cedula) {
        Usuario usuario = usuarioService.obtenerPorCedula(cedula);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_OBTENIDOS, response));
    }

    @Override
    @GetMapping("/correo/{correo}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorCorreo(@PathVariable String correo) {
        // Compatibilidad: buscar por cédula utilizando el parámetro proporcionado
        Usuario usuario = usuarioService.obtenerPorCorreo(correo);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_OBTENIDOS, response));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> registrarUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuarioService.registrarUsuario(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_REGISTRADO, response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizarUsuario(@PathVariable String id,
                                                                             @Valid @RequestBody UsuarioRequestDTO request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuario.setIdUsuario(id);
        usuarioService.actualizarUsuario(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_ACTUALIZADO, response));
    }

    @Override
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable String id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_ELIMINADO, null));
    }
}
