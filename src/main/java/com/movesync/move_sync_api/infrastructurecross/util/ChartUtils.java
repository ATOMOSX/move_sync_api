package com.movesync.move_sync_api.infrastructurecross.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ChartUtils {

    // Paleta de colores mate profesionales
    private static final List<Color> MATTE_COLORS = List.of(
            new Color(100, 149, 237),  // Azul Acero mate
            new Color(144, 190, 109),  // Verde Salvia mate
            new Color(249, 168, 77),   // Naranja Suave mate
            new Color(188, 108, 157),  // Púrpura Ciruela mate
            new Color(77, 182, 172),   // Turquesa mate
            new Color(240, 128, 128),  // Coral mate
            new Color(158, 158, 158),  // Gris mate
            new Color(255, 183, 77),   // Ámbar mate
            new Color(129, 178, 154),  // Jade mate
            new Color(206, 147, 216)   // Lavanda mate
    );

    /**
     * Crea un gráfico de pastel (pie chart)
     */
    public static JFreeChart createPieChart(String title, Map<String, Number> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,  // legend
                true,  // tooltips
                false  // URLs
        );

        // Personalizar colores
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setSectionPaint("Masculino", new Color(52, 152, 219));
        plot.setSectionPaint("Femenino", new Color(231, 76, 60));
        plot.setSectionPaint("Otro", new Color(149, 165, 166));

        return chart;
    }

    /**
     * Crea un gráfico de barras con colores mate personalizados por categoría
     */
    public static JFreeChart createBarChart(String title, String categoryAxisLabel,
                                            String valueAxisLabel, Map<String, Number> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((key, value) -> dataset.addValue(value, "Cantidad", key));

        JFreeChart chart = ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false,  // legend (deshabilitada para usar colores por barra)
                true,   // tooltips
                false   // URLs
        );

        // Personalizar fondo del chart
        chart.setBackgroundPaint(Color.WHITE);
        chart.setBorderVisible(false);
        chart.setPadding(new org.jfree.chart.ui.RectangleInsets(10, 5, 5, 5));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(250, 250, 250));
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(220, 220, 220));
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(false);

        // Renderer personalizado para asignar colores por categoría
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                // Cada columna obtiene un color diferente de la paleta
                return MATTE_COLORS.get(column % MATTE_COLORS.size());
            }

            @Override
            public Paint getItemOutlinePaint(int row, int column) {
                // Usar el mismo color que el relleno para el borde
                return MATTE_COLORS.get(column % MATTE_COLORS.size());
            }
        };


        renderer.setDrawBarOutline(false);           // No dibujar borde
        renderer.setShadowVisible(false);            // No mostrar sombra
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter());  // Usar pintor estándar (sin efectos 3D)
        renderer.setItemMargin(0.0);                 // Sin margen entre items
        renderer.setMaximumBarWidth(0.10);           // Ancho máximo de barra (10%)

        plot.setRenderer(renderer);

        // Configurar ejes para mejor apariencia
        plot.getDomainAxis().setTickMarksVisible(false);
        plot.getRangeAxis().setTickMarksVisible(true);

        return chart;
    }
}
