package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.actividad.ActividadRankingDTO;
import com.movesync.move_sync_api.application.port.output.meta.IActividadRepository;
import com.movesync.move_sync_api.infrastructure.mapper.ActividadRankingMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActividadRepositoryImpl implements IActividadRepository {

    private final JdbcTemplate jdbcTemplate;

    public ActividadRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ActividadRankingDTO> obtenerRankingActividades(Integer idUsuario) {
        String sql = """
                    SELECT 
                        a.nombre AS actividad,
                        COUNT(ra.id_registro_actividad) AS veces_realizada,
                        SUM(ra.duracion) AS duracion_total,
                        AVG(ra.perdida_calorias_alcanzadas) AS promedio_calorias,

                        (
                            SELECT COUNT(*) 
                            FROM registro_actividad ra2
                            WHERE ra2.id_usuario = ra.id_usuario
                        ) AS total_actividades_usuario

                    FROM registro_actividad ra
                    JOIN actividad a ON ra.id_actividad = a.id_actividad
                    WHERE ra.id_usuario = ?
                    GROUP BY a.nombre, ra.id_usuario
                    ORDER BY veces_realizada DESC;
                """;

        return jdbcTemplate.query(sql, new ActividadRankingMapper(), idUsuario);
    }
}
