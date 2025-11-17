package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteCategoriaTopMapper implements RowMapper<ReporteCategoriaTopDTO> {

    private Double toDouble(Object val) {
        return val == null ? 0.0 : ((Number) val).doubleValue();
    }

    @Override
    public ReporteCategoriaTopDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReporteCategoriaTopDTO.builder()
                .categoriaTop(rs.getString("categoria_top"))
                .totalCaloriasCategoria(toDouble(rs.getObject("total_calorias_categoria")))
                .actividadMasFrecuente(rs.getString("actividad_mas_frecuente"))
                .promedioCaloriasPorActividad(toDouble(rs.getObject("promedio_calorias_por_actividad")))
                .totalGlobal(toDouble(rs.getObject("total_global")))
                .diferenciaConPromedioCategorias(toDouble(rs.getObject("diferencia_con_promedio_categorias")))
                .totalCardio(toDouble(rs.getObject("total_cardio")))
                .diferenciaVsCardio(toDouble(rs.getObject("diferencia_vs_cardio")))
                .build();
    }
}
