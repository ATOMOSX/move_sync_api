package com.movesync.move_sync_api.infrastructure.repository.meta;

import com.movesync.move_sync_api.application.port.output.meta.IMetaRepository;
import com.movesync.move_sync_api.domain.entity.Meta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class MetaRepositoryImpl implements IMetaRepository {

    private final JdbcTemplate jdbcTemplate;

    public MetaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Meta> findAll() {
        String sql = "SELECT * FROM meta ORDER BY id_meta";
        try {
            return jdbcTemplate.query(sql, new MetaRowMapper());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public Meta findById(String idMeta) {
        String sql = "SELECT * FROM meta WHERE id_meta = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new MetaRowMapper(), Integer.parseInt(idMeta));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Meta meta) {

        String sql = """
                UPDATE meta
                SET id_usuario = ?, fecha_inicio = ?, fecha_fin = ?, objetivo = ?, perdida_calorias_diarias = ?
                WHERE id_meta = ?
                """;

        jdbcTemplate.update(sql,
                Integer.parseInt(meta.getIdUsuario()),
                meta.getFechaInicio(),
                meta.getFechaFin(),
                meta.getObjetivo(),
                meta.getPerdidaCaloriasDiarias(),
                meta.getIdMeta()
        );
    }

    @Override
    public void update(String idMeta, Meta meta) {
        String sql = """
                UPDATE meta
                SET id_usuario = ?, fecha_inicio = ?, fecha_fin = ?, objetivo = ?, perdida_calorias_diarias = ?
                WHERE id_meta = ?
                """;

        jdbcTemplate.update(sql,
                Integer.parseInt(meta.getIdUsuario()),
                meta.getFechaInicio(),
                meta.getFechaFin(),
                meta.getObjetivo(),
                new BigDecimal(meta.getPerdidaCaloriasDiarias()),
                Integer.parseInt(idMeta)
        );
    }

    @Override
    public void deleteById(String idMeta) {
        // Borrar filas hijas que referencian al usuario antes de eliminar el usuario
        jdbcTemplate.update("DELETE FROM historial_progreso WHERE id_meta = ?", idMeta);

        String sql = "DELETE FROM meta WHERE id_meta = ?";
        jdbcTemplate.update(sql, idMeta);
    }

    private static class MetaRowMapper implements RowMapper<Meta> {
        @Override
        public Meta mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Meta.builder()
                    .idMeta(rs.getString("id_meta"))
                    .fechaInicio(rs.getDate("fecha_inicio").toLocalDate())
                    .fechaFin(rs.getDate("fecha_fin").toLocalDate())
                    .objetivo(rs.getString("objetivo"))
                    .perdidaCaloriasDiarias(Double.valueOf(rs.getString("perdida_calorias_diarias")))
                    .build();
        }
    }
}
