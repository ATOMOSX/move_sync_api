package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;
import com.movesync.move_sync_api.application.port.interactor.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

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
