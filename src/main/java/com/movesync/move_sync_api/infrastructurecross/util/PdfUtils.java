package com.movesync.move_sync_api.infrastructurecross.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PdfUtils {

    private static final Color HEADER_COLOR = new DeviceRgb(52, 152, 219);
    private static final Color HEADER_TEXT_COLOR = ColorConstants.WHITE;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Crea un nuevo documento PDF
     */
    public static Document createDocument(ByteArrayOutputStream baos) throws IOException {
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        return new Document(pdfDoc);
    }

    /**
     * Agrega el encabezado del reporte
     */
    public static void addHeader(Document document, String titulo, String descripcion) {
        // Título principal
        Paragraph title = new Paragraph(titulo)
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
        document.add(title);

        // Descripción
        if (descripcion != null && !descripcion.isBlank()) {
            Paragraph desc = new Paragraph(descripcion)
                    .setFontSize(12)
                    .setItalic()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(5);
            document.add(desc);
        }

        // Fecha de generación
        Paragraph fecha = new Paragraph("Fecha de generación: " + LocalDateTime.now().format(FORMATTER))
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20);
        document.add(fecha);

        // Línea separadora
        document.add(new Paragraph("\n"));
    }

    /**
     * Crea una tabla con estilos predefinidos
     */
    public static Table createStyledTable(float[] columnWidths) {
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));
        return table;
    }

    /**
     * Crea una celda de encabezado
     */
    public static Cell createHeaderCell(String content) {
        return new Cell()
                .add(new Paragraph(content).setBold().setFontColor(HEADER_TEXT_COLOR))
                .setBackgroundColor(HEADER_COLOR)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(8)
                .setBorder(Border.NO_BORDER);
    }

    /**
     * Crea una celda de datos
     */
    public static Cell createDataCell(String content) {
        return new Cell()
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(5);
    }

    /**
     * Crea una celda de datos alineada a la derecha
     */
    public static Cell createDataCellRight(String content) {
        return new Cell()
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.RIGHT)
                .setPadding(5);
    }

    /**
     * Agrega un gráfico al documento
     */
    public static void addChart(Document document, JFreeChart chart, int width, int height) throws IOException {
        // Crear archivo temporal para el gráfico
        File chartFile = File.createTempFile("chart", ".png");
        ChartUtils.saveChartAsPNG(chartFile, chart, width, height);

        // Agregar imagen al documento
        Image chartImage = new Image(ImageDataFactory.create(chartFile.getAbsolutePath()));
        chartImage.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
        chartImage.setWidth(UnitValue.createPercentValue(80));
        document.add(chartImage);

        // Eliminar archivo temporal
        chartFile.delete();
    }

    /**
     * Agrega una sección de resumen
     */
    public static void addSummary(Document document, String label, String value) {
        Paragraph summary = new Paragraph()
                .add(new Paragraph(label + ": ").setBold())
                .add(new Paragraph(value))
                .setMarginTop(10);
        document.add(summary);
    }

    /**
     * Agrega un espaciador
     */
    public static void addSpacer(Document document) {
        document.add(new Paragraph("\n"));
    }
}
