package com.movesync.move_sync_api.application;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.movesync.move_sync_api.application.dto.out.reporte.EstadisticaDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;
import com.movesync.move_sync_api.application.port.interactor.IReporteService;
import com.movesync.move_sync_api.application.port.output.IReporteRepository;
import com.movesync.move_sync_api.infrastructurecross.util.ChartUtils;
import com.movesync.move_sync_api.infrastructurecross.util.PdfUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private IReporteRepository reporteRepository;

    @Override
    public ReporteResponseDTO<UsuariosPorGeneroDTO> obtenerReporteUsuariosPorGenero() {
        List<UsuariosPorGeneroDTO> datos = reporteRepository.obtenerUsuariosPorGenero();
        
        // Calcular total
        long total = datos.stream()
                .mapToLong(UsuariosPorGeneroDTO::getCantidad)
                .sum();

        // Crear estadísticas
        List<EstadisticaDTO> estadisticas = datos.stream()
                .map(d -> EstadisticaDTO.builder()
                        .etiqueta(d.getDescripcionGenero())
                        .cantidad(d.getCantidad())
                        .porcentaje(d.getPorcentaje())
                        .build())
                .collect(Collectors.toList());

        return ReporteResponseDTO.<UsuariosPorGeneroDTO>builder()
                .titulo("Distribución de Usuarios por Género")
                .descripcion("Reporte estadístico que muestra la distribución de usuarios registrados según su género")
                .fechaGeneracion(LocalDateTime.now())
                .totalRegistros(total)
                .datos(datos)
                .estadisticas(estadisticas)
                .build();
    }

    @Override
    public byte[] generarPdfUsuariosPorGenero() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = PdfUtils.createDocument(baos);
            
            // Obtener datos
            ReporteResponseDTO<UsuariosPorGeneroDTO> reporte = obtenerReporteUsuariosPorGenero();
            
            // Agregar encabezado
            PdfUtils.addHeader(document, reporte.getTitulo(), reporte.getDescripcion());
            
            // Agregar resumen
            PdfUtils.addSummary(document, "Total de Usuarios", String.valueOf(reporte.getTotalRegistros()));
            PdfUtils.addSpacer(document);
            
            // Crear gráfico de pastel
            Map<String, Number> chartData = reporte.getDatos().stream()
                    .collect(Collectors.toMap(
                            UsuariosPorGeneroDTO::getDescripcionGenero,
                            UsuariosPorGeneroDTO::getCantidad,
                            (a, b) -> a,
                            LinkedHashMap::new
                    ));
            
            JFreeChart pieChart = ChartUtils.createPieChart(
                    "Distribución por Género",
                    chartData
            );
            
            // Agregar gráfico al PDF
            PdfUtils.addChart(document, pieChart, 500, 400);
            PdfUtils.addSpacer(document);
            
            // Agregar tabla de datos
            document.add(new Paragraph("Detalle Estadístico")
                    .setBold()
                    .setFontSize(14)
                    .setMarginTop(20)
                    .setMarginBottom(10));
            
            Table table = PdfUtils.createStyledTable(new float[]{3, 2, 2});
            
            // Encabezados
            table.addHeaderCell(PdfUtils.createHeaderCell("Género"));
            table.addHeaderCell(PdfUtils.createHeaderCell("Cantidad"));
            table.addHeaderCell(PdfUtils.createHeaderCell("Porcentaje"));
            
            // Datos
            for (UsuariosPorGeneroDTO dato : reporte.getDatos()) {
                table.addCell(PdfUtils.createDataCell(dato.getDescripcionGenero()));
                table.addCell(PdfUtils.createDataCell(String.valueOf(dato.getCantidad())));
                table.addCell(PdfUtils.createDataCell(String.format("%.2f%%", dato.getPorcentaje())));
            }
            
            // Total
            table.addCell(new Cell().add(new Paragraph("TOTAL").setBold())
                    .setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(reporte.getTotalRegistros())).setBold())
                    .setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY));
            table.addCell(new Cell().add(new Paragraph("100.00%").setBold())
                    .setBackgroundColor(com.itextpdf.kernel.colors.ColorConstants.LIGHT_GRAY));
            
            document.add(table);
            
            // Cerrar documento
            document.close();
            
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar PDF: " + e.getMessage());
        }
    }
}
