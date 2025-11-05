package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.meta.MetaRequestDTO;
import com.movesync.move_sync_api.application.dto.out.meta.MetaResponseDTO;
import com.movesync.move_sync_api.application.port.input.IMetaController;
import com.movesync.move_sync_api.application.port.interactor.IMetaService;
import com.movesync.move_sync_api.domain.entity.Meta;
import com.movesync.move_sync_api.infrastructure.mapper.MetaMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController implements IMetaController {

    @Autowired
    private IMetaService metaService;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<MetaResponseDTO>>> listarMetas() {
        List<Meta> metas = metaService.listarMetas();
        List<MetaResponseDTO> response = metas.stream()
                .map(MetaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(Constants.META_OBTENIDAS, response));
    }

    @Override
    @GetMapping("/{idMeta}")
    public ResponseEntity<ApiResponse<MetaResponseDTO>> obtenerPorId(@PathVariable String idMeta) {
        Meta meta = metaService.obtenerPorId(idMeta);
        MetaResponseDTO response = MetaMapper.toResponse(meta);
        return ResponseEntity.ok(ApiResponse.success(Constants.META_OBTENIDA, response));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<MetaResponseDTO>> registrarMeta(@Valid @RequestBody MetaRequestDTO request) {
        Meta meta = MetaMapper.toEntity(request);
        metaService.registrarMeta(meta);
        MetaResponseDTO response = MetaMapper.toResponse(meta);
        return ResponseEntity.ok(ApiResponse.success(Constants.META_REGISTRADA, response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MetaResponseDTO>> actualizarMeta(@PathVariable String id,
                                                                        @Valid @RequestBody MetaRequestDTO request) {
        Meta meta = MetaMapper.toEntity(request);
        meta.setIdMeta(id);
        metaService.actualizarMeta(meta);
        MetaResponseDTO response = MetaMapper.toResponse(meta);
        return ResponseEntity.ok(ApiResponse.success(Constants.META_ACTUALIZADA, response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarMeta(@PathVariable String id) {
        metaService.eliminarMeta(id);
        return ResponseEntity.ok(ApiResponse.success(Constants.META_ELIMINADA, null));
    }
}
