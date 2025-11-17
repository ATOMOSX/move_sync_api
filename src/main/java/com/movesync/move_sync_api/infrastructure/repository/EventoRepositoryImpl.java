package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.evento.EventoEstadisticasDTO;
import com.movesync.move_sync_api.application.port.output.IEventoRepository;
import com.movesync.move_sync_api.domain.entity.Evento;
import com.movesync.move_sync_api.infrastructure.mapper.EventoEstadisticasRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Repository
public class EventoRepositoryImpl implements IEventoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Evento> findAll() {
        String sql = "SELECT * FROM evento ORDER BY fecha DESC";
        try {
            return jdbcTemplate.query(sql, new EventoRowMapper());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public Evento findById(String idEvento) {
        String sql = "SELECT * FROM evento WHERE id_evento = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new EventoRowMapper(), Integer.parseInt(idEvento));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Evento evento) {
        String sql = """
                INSERT INTO evento
                (duracion, fecha, nombre, distancia)
                VALUES (CAST(? AS interval), ?, ?, ?)
                """;

        String duracion = evento.getDuracion().toString();

        jdbcTemplate.update(sql,
                duracion,
                Timestamp.valueOf(evento.getFecha()),
                evento.getNombre(),
                evento.getDistancia()
        );
    }

    @Override
    public void update(Evento evento) {
        String sql = """
                            
                    UPDATE evento
                SET duracion = CAST(? AS interval), fecha = ?, nombre = ?, distancia = ?
                WHERE id_evento = ?
                """;

        String duracion = evento.getDuracion().toString();

        jdbcTemplate.update(sql,
                duracion,
                Timestamp.valueOf(evento.getFecha()),
                evento.getNombre(),
                evento.getDistancia(),
                Integer.parseInt(evento.getIdEvento())
        );
    }

    @Override
    public void deleteById(String idEvento) {
        jdbcTemplate.update("DELETE FROM registro_participantes WHERE id_evento = ?", idEvento);

        String sql = "DELETE FROM evento WHERE id_evento = ?";
        jdbcTemplate.update(sql, idEvento);
    }

    @Override
    public List<EventoEstadisticasDTO> obtenerEstadisticasEventos() {
        String sql = """
                SELECT 
                    e.id_evento,
                    e.nombre AS evento,
                    e.fecha,

                    (
                        SELECT a2.nombre
                        FROM actividad a2
                        JOIN registro_actividad ra2 ON ra2.id_actividad = a2.id_actividad
                        WHERE ra2.id_evento = e.id_evento
                        GROUP BY a2.nombre
                        ORDER BY COUNT(*) DESC
                        LIMIT 1
                    ) AS actividad_mas_realizada,

                    (
                        SELECT SUM(ra3.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra3
                        WHERE ra3.id_evento = e.id_evento
                    ) AS total_calorias,

                    (
                        SELECT AVG(ra4.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra4
                        WHERE ra4.id_evento = e.id_evento
                    ) AS promedio_calorias,

                    (
                        SELECT MAX(ra5.perdida_calorias_alcanzadas)
                        FROM registro_actividad ra5
                        WHERE ra5.id_evento = e.id_evento
                    ) AS maximo_individual,

                    (
                        SELECT u.primer_nombre || ' ' || u.primer_apellido
                        FROM usuario u
                        JOIN registro_actividad ra6 ON ra6.id_usuario = u.id_usuario
                        WHERE ra6.id_evento = e.id_evento
                        GROUP BY u.id_usuario
                        ORDER BY SUM(ra6.perdida_calorias_alcanzadas) DESC
                        LIMIT 1
                    ) AS usuario_top,

                    (
                        SELECT 
                            (SELECT AVG(perdida_calorias_alcanzadas) FROM registro_actividad)
                            -
                            (SELECT AVG(perdida_calorias_alcanzadas) 
                             FROM registro_actividad 
                             WHERE id_evento = e.id_evento)
                    ) AS diferencia_promedio

                FROM evento e
                ORDER BY total_calorias DESC
                """;

        return jdbcTemplate.query(sql, new EventoEstadisticasRowMapper());
    }

    private static class EventoRowMapper implements RowMapper<Evento> {
        @Override
        public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Evento.builder()
                    .idEvento(rs.getString("id_evento"))
                    .duracion(rs.getTime("duracion").toLocalTime())
                    .fecha(rs.getTimestamp("fecha").toLocalDateTime())
                    .nombre(rs.getString("nombre"))
                    .build();
        }
    }
}
