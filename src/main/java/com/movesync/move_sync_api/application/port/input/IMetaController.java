package com.movesync.move_sync_api.application.port.input;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.meta.MetaRequestDTO;
import com.movesync.move_sync_api.application.dto.out.meta.MetaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IMetaController {
    ResponseEntity<ApiResponse<List<MetaResponseDTO>>> listarMetas();
    ResponseEntity<ApiResponse<MetaResponseDTO>> obtenerPorId(@PathVariable String idMeta);
    ResponseEntity<ApiResponse<MetaResponseDTO>> registrarMeta(@Valid @RequestBody MetaRequestDTO request);
    ResponseEntity<ApiResponse<MetaResponseDTO>> actualizarMeta(@PathVariable String id, @Valid @RequestBody MetaRequestDTO request);
    ResponseEntity<ApiResponse<Void>> eliminarMeta(@PathVariable String id);
}
