package com.movesync.move_sync_api.infrastructure.repository.meta;

import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteAdminDTO;
import com.movesync.move_sync_api.application.port.output.meta.IReporteMetaRepositoryAdmin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReporteMetaRepositoryAdminImpl implements IReporteMetaRepositoryAdmin {

    private final JdbcTemplate jdbcTemplate;

    public ReporteMetaRepositoryAdminImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MetaReporteAdminDTO> obtenerReporteMetasAdmin() {
        String sql = """
                SELECT 
                    m.id_meta,
                    u.primer_nombre || ' ' || u.primer_apellido AS usuario,
                    COUNT(hp.id_historial) AS dias_registrados,
                    (m.fecha_fin - CURRENT_DATE) AS dias_restantes,
                    m.perdida_calorias_diarias,

                    (
                        SELECT SUM(ra2.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra2
                        WHERE ra2.id_usuario = m.id_usuario
                    ) AS total_calorias

                FROM meta m
                JOIN usuario u ON m.id_usuario = u.id_usuario
                LEFT JOIN historial_progreso hp ON m.id_meta = hp.id_meta
                GROUP BY m.id_meta, usuario, m.fecha_fin, m.perdida_calorias_diarias;
                """;

        return jdbcTemplate.query(sql, new MetaReporteAdminRowMapper());
    }

    public static class MetaReporteAdminRowMapper implements RowMapper<MetaReporteAdminDTO> {

        @Override
        public MetaReporteAdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new MetaReporteAdminDTO(
                    rs.getInt("id_meta"),
                    rs.getString("usuario"),
                    rs.getInt("dias_registrados"),
                    rs.getInt("dias_restantes"),
                    rs.getDouble("perdida_calorias_diarias"),
                    rs.getDouble("total_calorias")
            );
        }
    }

}
