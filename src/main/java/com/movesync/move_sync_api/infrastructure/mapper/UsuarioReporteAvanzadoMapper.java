package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioReporteAvanzadoDTO;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UsuarioReporteAvanzadoMapper implements RowMapper<UsuarioReporteAvanzadoDTO> {

    @Override
    public UsuarioReporteAvanzadoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UsuarioReporteAvanzadoDTO.builder()
                .idUsuario(rs.getInt("id_usuario"))
                .nombreCompleto(rs.getString("usuario"))
                .nivelActividad(rs.getString("nivel_actividad"))
                .gastoEnergetico(rs.getString("gasto_energetico"))
                .imc(rs.getString("imc"))
                .promedioCaloriasUsuario(toDouble(rs.getObject("promedio_calorias_usuario")))
                .actividadFavorita(rs.getString("actividad_favorita"))
                .diaMasActivo(toLocalDate(rs.getObject("dia_mas_activo")))
                .caloriasDiaTop(toDouble(rs.getObject("calorias_dia_top")))
                .promedioGeneral(toDouble(rs.getObject("promedio_general")))
                .maxCaloriasGlobal(toDouble(rs.getObject("max_calorias_global")))
                .diferenciaPromedio(toDouble(rs.getObject("diferencia_promedio")))
                .metaActual(rs.getString("meta_actual"))
                .diasRegistrados(toInteger(rs.getObject("dias_registrados")))
                .diasRestantes(toInteger(rs.getObject("dias_restantes")))
                .build();
    }

    private Double toDouble(Object obj) {
        if (obj == null) return null;
        if (obj instanceof BigDecimal bd) return bd.doubleValue();
        if (obj instanceof Number n) return n.doubleValue();
        return null;
    }

    private Integer toInteger(Object obj) {
        if (obj == null) return null;

        if (obj instanceof Integer i) return i;
        if (obj instanceof Long l) return l.intValue();
        if (obj instanceof BigDecimal bd) return bd.intValue();

        return null;
    }

    private LocalDate toLocalDate(Object obj) {
        if (obj == null) return null;
        if (obj instanceof LocalDate d) return d;
        return null;
    }
}
