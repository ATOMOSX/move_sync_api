package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.reporte.ReporteCategoriaTopDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.UsuariosPorGeneroDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.LogrosPorTipoDTO;
import com.movesync.move_sync_api.application.port.output.IReporteRepository;
import com.movesync.move_sync_api.infrastructure.mapper.ReporteCategoriaTopMapper;
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

    @Override
    public ReporteCategoriaTopDTO obtenerReporteCategoriaTop() {
        String sql = """
                SELECT 
                    a.tipo AS categoria_top,

                    SUM(ra.perdida_calorias_alcanzadas) AS total_calorias_categoria,

                    (
                        SELECT nombre
                        FROM actividad a2
                        WHERE a2.tipo = a.tipo
                        ORDER BY (
                            SELECT COUNT(*)
                            FROM registro_actividad ra2
                            WHERE ra2.id_actividad = a2.id_actividad
                        ) DESC
                        LIMIT 1
                    ) AS actividad_mas_frecuente,

                    (
                        SELECT AVG(
                            (
                                SELECT SUM(ra3.perdida_calorias_alcanzadas)
                                FROM registro_actividad ra3
                                WHERE ra3.id_actividad = a3.id_actividad
                            )
                        )
                        FROM actividad a3
                        WHERE a3.tipo = a.tipo
                    ) AS promedio_calorias_por_actividad,

                    (
                        SELECT SUM(perdida_calorias_alcanzadas)
                        FROM registro_actividad
                    ) AS total_global,

                    (
                        (
                            SELECT SUM(perdida_calorias_alcanzadas)
                            FROM registro_actividad ra4
                            JOIN actividad aa4 ON aa4.id_actividad = ra4.id_actividad
                            WHERE aa4.tipo = a.tipo
                        )
                        -
                        (
                            SELECT AVG(total_categoria)
                            FROM (
                                SELECT SUM(ra5.perdida_calorias_alcanzadas) AS total_categoria
                                FROM registro_actividad ra5
                                JOIN actividad a5 ON a5.id_actividad = ra5.id_actividad
                                GROUP BY a5.tipo
                            ) AS x
                        )
                    ) AS diferencia_con_promedio_categorias,

                    (
                        SELECT SUM(ra6.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra6
                        JOIN actividad a6 ON a6.id_actividad = ra6.id_actividad
                        WHERE a6.tipo = 'Cardio'
                    ) AS total_cardio,

                    (
                        SUM(ra.perdida_calorias_alcanzadas)
                        -
                        (
                            SELECT SUM(ra7.perdida_calorias_alcanzadas)
                            FROM registro_actividad ra7
                            JOIN actividad a7 ON a7.id_actividad = ra7.id_actividad
                            WHERE a7.tipo = 'Cardio'
                        )
                    ) AS diferencia_vs_cardio

                FROM actividad a
                JOIN registro_actividad ra ON ra.id_actividad = a.id_actividad

                WHERE a.tipo = (
                    SELECT tipo
                    FROM actividad
                    GROUP BY tipo
                    ORDER BY (
                        SELECT COUNT(*)
                        FROM registro_actividad ra_top
                        JOIN actividad act_top ON act_top.id_actividad = ra_top.id_actividad
                        WHERE act_top.tipo = actividad.tipo
                    ) DESC
                    LIMIT 1
                )

                GROUP BY a.tipo

                ORDER BY total_calorias_categoria DESC
                """;
        try {
            return jdbcTemplate.queryForObject(sql, new ReporteCategoriaTopMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    @Override
    public List<LogrosPorTipoDTO> obtenerLogrosPorTipo() {
        String sql = """
                SELECT 
                    tipo,
                    COUNT(*) as cantidad,
                    ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM logro), 2) as porcentaje
                FROM logro
                GROUP BY tipo
                ORDER BY cantidad DESC
                """;
        
        try {
            return jdbcTemplate.query(sql, new LogrosPorTipoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private static class LogrosPorTipoRowMapper implements RowMapper<LogrosPorTipoDTO> {
        @Override
        public LogrosPorTipoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return LogrosPorTipoDTO.builder()
                    .tipo(rs.getString("tipo"))
                    .cantidad(rs.getLong("cantidad"))
                    .porcentaje(rs.getDouble("porcentaje"))
                    .build();
        }
    }
}
