package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUsuarioController {
    ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarUsuarios();
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorId(@PathVariable String idUsuario);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorCorreo(@PathVariable String correo);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> registrarUsuario(@Valid @RequestBody UsuarioRequestDTO request);
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizarUsuario(
            @PathVariable String id, @Valid @RequestBody UsuarioRequestDTO request);
    ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable String id);
}