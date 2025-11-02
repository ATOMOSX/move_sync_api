package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.evento.EventoRequestDTO;
import com.movesync.move_sync_api.application.dto.out.evento.EventoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IEventoController {
    ResponseEntity<ApiResponse<List<EventoResponseDTO>>> listarEventos();
    ResponseEntity<ApiResponse<EventoResponseDTO>> obtenerPorId(@PathVariable String idEvento);
    ResponseEntity<ApiResponse<EventoResponseDTO>> registrarEvento(@Valid @RequestBody EventoRequestDTO request);
    ResponseEntity<ApiResponse<EventoResponseDTO>> actualizarEvento(@PathVariable String id, @Valid @RequestBody EventoRequestDTO request);
    ResponseEntity<ApiResponse<Void>> eliminarEvento(@PathVariable String id);
}
