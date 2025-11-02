package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.logro.LogroRequestDTO;
import com.movesync.move_sync_api.application.dto.out.logro.LogroResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ILogroController {
    ResponseEntity<ApiResponse<List<LogroResponseDTO>>> listarLogros();
    ResponseEntity<ApiResponse<LogroResponseDTO>> obtenerPorId(@PathVariable String idLogro);
    ResponseEntity<ApiResponse<List<LogroResponseDTO>>> obtenerPorUsuario(@PathVariable String idUsuario);
    ResponseEntity<ApiResponse<LogroResponseDTO>> registrarLogro(@Valid @RequestBody LogroRequestDTO request);
    ResponseEntity<ApiResponse<LogroResponseDTO>> actualizarLogro(@PathVariable String id, @Valid @RequestBody LogroRequestDTO request);
    ResponseEntity<ApiResponse<Void>> eliminarLogro(@PathVariable String id);
}
