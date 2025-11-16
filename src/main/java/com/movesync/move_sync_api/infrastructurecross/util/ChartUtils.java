package com.movesync.move_sync_api.infrastructurecross.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.Map;

public class ChartUtils {

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
     * Crea un gráfico de barras
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
                true,  // legend
                true,  // tooltips
                false  // URLs
        );

        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(new Color(240, 240, 240));

        return chart;
    }

    /**
     * Crea un gráfico de líneas
     */
    public static JFreeChart createLineChart(String title, String categoryAxisLabel,
                                             String valueAxisLabel, Map<String, Number> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.forEach((key, value) -> dataset.addValue(value, "Serie", key));

        JFreeChart chart = ChartFactory.createLineChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,  // legend
                true,  // tooltips
                false  // URLs
        );

        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(new Color(240, 240, 240));

        return chart;
    }
}
