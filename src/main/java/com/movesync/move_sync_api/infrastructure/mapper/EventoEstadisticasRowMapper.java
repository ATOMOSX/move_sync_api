package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.out.evento.EventoEstadisticasDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventoEstadisticasRowMapper implements RowMapper<EventoEstadisticasDTO> {

    @Override
    public EventoEstadisticasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return EventoEstadisticasDTO.builder()
                .idEvento(rs.getInt("id_evento"))
                .nombreEvento(rs.getString("evento"))
                .fecha(rs.getString("fecha"))
                .actividadMasRealizada(rs.getString("actividad_mas_realizada"))
                .totalCalorias(rs.getDouble("total_calorias"))
                .promedioCalorias(rs.getDouble("promedio_calorias"))
                .maximoIndividual(rs.getDouble("maximo_individual"))
                .usuarioTop(rs.getString("usuario_top"))
                .diferenciaPromedioGeneral(rs.getDouble("diferencia_promedio"))
                .build();
    }
}
