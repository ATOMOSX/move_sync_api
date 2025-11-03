package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUsuarioController {
    ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarUsuarios();
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorId(String idUsuario);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorCorreo(String correo);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> obtenerPorCedula(String cedula);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> registrarUsuario(@Valid UsuarioRequestDTO request);
    ResponseEntity<ApiResponse<UsuarioResponseDTO>> actualizarUsuario(String id, @Valid UsuarioRequestDTO request);
    ResponseEntity<ApiResponse<Void>> eliminarUsuario(String id);
}