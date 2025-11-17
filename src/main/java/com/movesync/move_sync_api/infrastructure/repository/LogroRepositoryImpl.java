package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.port.output.ILogroRepository;
import com.movesync.move_sync_api.domain.entity.Logro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LogroRepositoryImpl implements ILogroRepository {

    private final JdbcTemplate jdbcTemplate;

    public LogroRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Logro> findAll() {
        String sql = "SELECT * FROM logro ORDER BY id_logro";
        try {
            return jdbcTemplate.query(sql, new LogroRowMapper());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public Logro findById(Integer idLogro) {
        String sql = "SELECT * FROM logro WHERE id_logro = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new LogroRowMapper(), idLogro);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Logro> findByUsuario(String idUsuario) {
        String sql = "SELECT * FROM logro WHERE id_usuario = ? ORDER BY id_logro";
        try {
            return jdbcTemplate.query(sql, new LogroRowMapper(), Integer.parseInt(idUsuario));
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public void save(Logro logro) {
        String sql = """
            INSERT INTO logro
            (nombre, recompensa, descripcion, tipo, id_usuario)
            VALUES (?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(sql,
                logro.getNombre(),
                logro.getRecompensa(),
                logro.getDescripcion(),
                logro.getTipo(),
                Integer.parseInt(logro.getIdUsuario())
        );
    }

    @Override
    public void update(Logro logro) {
        String sql = """
                UPDATE logro
                SET nombre = ?, recompensa = ?, descripcion = ?, tipo = ?, id_usuario = ?
                WHERE id_logro = ?
                """;
        jdbcTemplate.update(sql,
                logro.getNombre(),
                logro.getRecompensa(),
                logro.getDescripcion(),
                logro.getTipo(),
                Integer.parseInt(logro.getIdUsuario()),
                Integer.parseInt(logro.getIdLogro())
        );
    }

    @Override
    public void deleteById(String idLogro) {
        String sql = "DELETE FROM logro WHERE id_logro = ?";
        jdbcTemplate.update(sql, idLogro);
    }

    private static class LogroRowMapper implements RowMapper<Logro> {
        @Override
        public Logro mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Logro.builder()
                    .idLogro(rs.getString("id_logro"))
                    .nombre(rs.getString("nombre"))
                    .recompensa(rs.getString("recompensa"))
                    .descripcion(rs.getString("descripcion"))
                    .tipo(rs.getString("tipo"))
                    .idUsuario(rs.getString("id_usuario"))
                    .build();
        }
    }
}
