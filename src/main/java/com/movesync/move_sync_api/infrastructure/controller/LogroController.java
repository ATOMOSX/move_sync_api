package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.logro.LogroRequestDTO;
import com.movesync.move_sync_api.application.dto.out.logro.LogroResponseDTO;
import com.movesync.move_sync_api.application.port.input.ILogroController;
import com.movesync.move_sync_api.application.port.interactor.ILogroService;
import com.movesync.move_sync_api.domain.entity.Logro;
import com.movesync.move_sync_api.infrastructure.mapper.LogroMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logros")
public class LogroController implements ILogroController {

    @Autowired
    private ILogroService logroService;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<LogroResponseDTO>>> listarLogros() {
        List<Logro> logros = logroService.listarLogros();
        List<LogroResponseDTO> response = logros.stream()
                .map(LogroMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_OBTENIDOS, response));
    }

    @Override
    @GetMapping("/{idLogro}")
    public ResponseEntity<ApiResponse<LogroResponseDTO>> obtenerPorId(@PathVariable String idLogro) {
        Logro logro = logroService.obtenerPorId(idLogro);
        LogroResponseDTO response = LogroMapper.toResponse(logro);
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_OBTENIDO, response));
    }

    @Override
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<List<LogroResponseDTO>>> obtenerPorUsuario(@PathVariable String idUsuario) {
        List<Logro> logros = logroService.obtenerPorUsuario(idUsuario);
        List<LogroResponseDTO> response = logros.stream()
                .map(LogroMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_OBTENIDOS, response));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<LogroResponseDTO>> registrarLogro(@Valid @RequestBody LogroRequestDTO request) {
        Logro logro = LogroMapper.toEntity(request);
        logroService.registrarLogro(logro);
        LogroResponseDTO response = LogroMapper.toResponse(logro);
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_REGISTRADO, response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LogroResponseDTO>> actualizarLogro(@PathVariable String id,
                                                                          @Valid @RequestBody LogroRequestDTO request) {
        Logro logro = LogroMapper.toEntity(request);
        logro.setIdLogro(id);
        logroService.actualizarLogro(logro);
        LogroResponseDTO response = LogroMapper.toResponse(logro);
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_ACTUALIZADO, response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarLogro(@PathVariable String id) {
        logroService.eliminarLogro(id);
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGRO_ELIMINADO, null));
    }
}
