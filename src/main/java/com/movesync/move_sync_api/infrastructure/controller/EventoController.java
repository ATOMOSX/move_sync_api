package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.evento.EventoRequestDTO;
import com.movesync.move_sync_api.application.dto.out.evento.EventoResponseDTO;
import com.movesync.move_sync_api.application.port.input.IEventoController;
import com.movesync.move_sync_api.application.port.interactor.IEventoService;
import com.movesync.move_sync_api.domain.entity.Evento;
import com.movesync.move_sync_api.infrastructure.mapper.EventoMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController implements IEventoController {

    @Autowired
    private IEventoService eventoService;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<EventoResponseDTO>>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        List<EventoResponseDTO> response = eventos.stream()
                .map(EventoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(Constants.EVENTO_OBTENIDOS, response));
    }

    @Override
    @GetMapping("/{idEvento}")
    public ResponseEntity<ApiResponse<EventoResponseDTO>> obtenerPorId(@PathVariable String idEvento) {
        Evento evento = eventoService.obtenerPorId(idEvento);
        EventoResponseDTO response = EventoMapper.toResponse(evento);
        return ResponseEntity.ok(ApiResponse.success(Constants.EVENTO_OBTENIDO, response));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<EventoResponseDTO>> registrarEvento(@Valid @RequestBody EventoRequestDTO request) {
        Evento evento = EventoMapper.toEntity(request);
        eventoService.registrarEvento(evento);
        EventoResponseDTO response = EventoMapper.toResponse(evento);
        return ResponseEntity.ok(ApiResponse.success(Constants.EVENTO_REGISTRADO, response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventoResponseDTO>> actualizarEvento(@PathVariable String id,
                                                                            @Valid @RequestBody EventoRequestDTO request) {
        Evento evento = EventoMapper.toEntity(request);
        evento.setIdEvento(id);
        eventoService.actualizarEvento(evento);
        EventoResponseDTO response = EventoMapper.toResponse(evento);
        return ResponseEntity.ok(ApiResponse.success(Constants.EVENTO_ACTUALIZADO, response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarEvento(@PathVariable String id) {
        eventoService.eliminarEvento(id);
        return ResponseEntity.ok(ApiResponse.success(Constants.EVENTO_ELIMINADO, null));
    }
}
