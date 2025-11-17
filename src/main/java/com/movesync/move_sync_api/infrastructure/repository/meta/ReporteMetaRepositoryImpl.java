package com.movesync.move_sync_api.infrastructure.repository.meta;

import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteDTO;
import com.movesync.move_sync_api.application.port.output.meta.IReporteMetaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReporteMetaRepositoryImpl implements IReporteMetaRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteMetaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MetaReporteDTO> obtenerReporteMetas(Integer idUsuario) {
        String sql = """
                SELECT 
                    m.id_meta,
                    m.objetivo,
                    m.fecha_inicio,
                    m.fecha_fin,
                    m.perdida_calorias_diarias,

                    COUNT(hp.id_historial) AS dias_registrados,

                    (m.fecha_fin - CURRENT_DATE) AS dias_restantes,

                    (
                        SELECT AVG(ra.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra
                        WHERE ra.id_usuario = m.id_usuario
                          AND ra.fecha BETWEEN m.fecha_inicio AND m.fecha_fin
                    ) AS promedio_calorias

                FROM meta m
                LEFT JOIN historial_progreso hp ON hp.id_meta = m.id_meta
                WHERE m.id_usuario = ?
                GROUP BY m.id_meta, m.objetivo, m.fecha_inicio, m.fecha_fin, m.perdida_calorias_diarias
                """;

        return jdbcTemplate.query(sql, new RowMapperMetaReporte(), idUsuario);
    }

    public static class RowMapperMetaReporte implements RowMapper<MetaReporteDTO> {

        @Override
        public MetaReporteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            return MetaReporteDTO.builder()
                    .idMeta(rs.getInt("id_meta"))
                    .objetivo(rs.getString("objetivo"))
                    .fechaInicio(rs.getString("fecha_inicio"))
                    .fechaFin(rs.getString("fecha_fin"))
                    .perdidaCaloriasDiarias(rs.getDouble("perdida_calorias_diarias"))
                    .diasRegistrados(rs.getInt("dias_registrados"))
                    .diasRestantes(rs.getLong("dias_restantes"))
                    .promedioCalorias(rs.getDouble("promedio_calorias"))
                    .build();
        }
    }
}
