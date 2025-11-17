package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.registro_actividad.RegistroActividadReporteDTO;
import com.movesync.move_sync_api.application.port.output.IReporteActividadRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReporteActividadRepositoryImpl implements IReporteActividadRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReporteActividadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RegistroActividadReporteDTO> obtenerHistorialDetallado(Integer idUsuario) {

        String sql = """
                SELECT 
                    ra.fecha,
                    a.nombre AS actividad,
                    ra.duracion,
                    ra.perdida_calorias_alcanzadas AS calorias_quemadas,
                    ra.perdida_calorias_estimadas AS calorias_estimadas,
                    (ra.perdida_calorias_estimadas - ra.perdida_calorias_alcanzadas) AS diferencia_calorias,
                    e.nombre AS evento_asociado,
                    
                    (
                        SELECT AVG(ra2.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra2
                        WHERE ra2.id_usuario = ra.id_usuario
                    ) AS promedio_calorias_usuario
                    
                FROM registro_actividad ra
                JOIN actividad a ON ra.id_actividad = a.id_actividad
                LEFT JOIN evento e ON ra.id_evento = e.id_evento
                WHERE ra.id_usuario = ?
                ORDER BY ra.fecha DESC
                """;

        return jdbcTemplate.query(sql, new RowMapperReporteActividad(), idUsuario);
    }

    private static class RowMapperReporteActividad implements RowMapper<RegistroActividadReporteDTO> {
        @Override
        public RegistroActividadReporteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return RegistroActividadReporteDTO.builder()
                    .fecha(rs.getDate("fecha").toLocalDate())
                    .actividad(rs.getString("actividad"))
                    .duracion(rs.getString("duracion"))
                    .caloriasQuemadas(rs.getDouble("calorias_quemadas"))
                    .caloriasEstimadas(rs.getDouble("calorias_estimadas"))
                    .diferenciaCalorias(rs.getDouble("diferencia_calorias"))
                    .eventoAsociado(rs.getString("evento_asociado"))
                    .promedioCaloriasUsuario(rs.getDouble("promedio_calorias_usuario"))
                    .build();
        }
    }
}
