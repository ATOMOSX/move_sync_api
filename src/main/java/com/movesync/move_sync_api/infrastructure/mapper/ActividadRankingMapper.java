package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActividadRankingMapper implements RowMapper<ActividadRankingDTO> {

    @Override
    public ActividadRankingDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ActividadRankingDTO dto = new ActividadRankingDTO();
        dto.setActividad(rs.getString("actividad"));
        dto.setVecesRealizada(rs.getInt("veces_realizada"));
        dto.setDuracionTotal(rs.getString("duracion_total"));
        dto.setPromedioCalorias(rs.getDouble("promedio_calorias"));
        dto.setTotalActividadesUsuario(rs.getInt("total_actividades_usuario"));
        return dto;
    }
}
