package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import com.movesync.move_sync_api.application.port.input.IUsuarioController;
import com.movesync.move_sync_api.application.port.interactor.IReporteService;
import com.movesync.move_sync_api.application.port.interactor.IUsuarioService;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructure.mapper.UsuarioMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable String id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_ELIMINADO, null));
    }

    @Autowired
    private IReporteService reporteService;

    /**
     * Reporte Simple 1: Usuarios por Género (JSON)
     * GET /api/reportes/usuarios-por-genero
     */
    @GetMapping("/usuarios-por-genero")
    public ResponseEntity<ApiResponse<ReporteResponseDTO<UsuariosPorGeneroDTO>>> obtenerReporteUsuariosPorGenero() {
        ReporteResponseDTO<UsuariosPorGeneroDTO> reporte = reporteService.obtenerReporteUsuariosPorGenero();
        return ResponseEntity.ok(ApiResponse.success("Reporte generado correctamente", reporte));
    }

    /**
     * Reporte Simple 1: Usuarios por Género (PDF)
     * GET /api/reportes/usuarios-por-genero/pdf
     */
    @GetMapping("/usuarios-por-genero/pdf")
    public ResponseEntity<byte[]> descargarPdfUsuariosPorGenero() {
        byte[] pdfBytes = reporteService.generarPdfUsuariosPorGenero();

        String fileName = "reporte_usuarios_genero_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
                ".pdf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
