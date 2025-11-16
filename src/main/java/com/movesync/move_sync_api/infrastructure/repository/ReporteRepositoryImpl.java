package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;
import com.movesync.move_sync_api.application.port.output.IReporteRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReporteRepositoryImpl implements IReporteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UsuariosPorGeneroDTO> obtenerUsuariosPorGenero() {
        String sql = """
                SELECT 
                    genero,
                    CASE 
                        WHEN genero = 'M' THEN 'Masculino'
                        WHEN genero = 'F' THEN 'Femenino'
                        ELSE 'Otro'
                    END as descripcion_genero,
                    COUNT(*) as cantidad,
                    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM usuario), 2) as porcentaje
                FROM usuario
                GROUP BY genero
                ORDER BY cantidad DESC
                """;
        
        try {
            return jdbcTemplate.query(sql, new UsuariosPorGeneroRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private static class UsuariosPorGeneroRowMapper implements RowMapper<UsuariosPorGeneroDTO> {
        @Override
        public UsuariosPorGeneroDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return UsuariosPorGeneroDTO.builder()
                    .genero(rs.getString("genero"))
                    .descripcionGenero(rs.getString("descripcion_genero"))
                    .cantidad(rs.getLong("cantidad"))
                    .porcentaje(rs.getDouble("porcentaje"))
                    .build();
        }
    }
}
