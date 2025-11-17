package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioReporteAvanzadoDTO;
import com.movesync.move_sync_api.application.port.output.IUsuarioRepository;
import com.movesync.move_sync_api.domain.entity.Rol;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructure.mapper.UsuarioReporteAvanzadoMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM usuario ORDER BY id_usuario";

        try {
            return jdbcTemplate.query(sql, new UsuarioRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Usuario findById(String idUsuario) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario findByCedula(String cedula) {
        String sql = "SELECT * FROM usuario WHERE cedula = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), cedula);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Usuario usuario) {
        //Genera el id si no existe
        if (usuario.getIdUsuario() == null || usuario.getIdUsuario().isBlank()) {
            usuario.setIdUsuario(UUID.randomUUID().toString());
        }

        String sql = """
                INSERT INTO usuario
                (id_usuario,primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,
                 cedula, peso, estatura, genero, contrasena, correo, fecha_nacimiento)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                usuario.getIdUsuario(),
                usuario.getPrimerNombre(),
                usuario.getSegundoNombre(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getCedula(),
                usuario.getPeso(),
                usuario.getEstatura(),
                usuario.getGenero(),
                usuario.getContrasena(),
                usuario.getCorreo(),
                Date.valueOf(usuario.getFechaNacimiento())
        );
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                UPDATE usuario
                SET primer_nombre = ?, segundo_nombre = ?, primer_apellido = ?, segundo_apellido = ?,
                    cedula = ?, peso = ?, estatura = ?, genero = ?, contrasena = ?, correo = ?,
                    fecha_nacimiento = ?
                WHERE id_usuario = ?
                """;
        jdbcTemplate.update(sql,
                usuario.getPrimerNombre(),
                usuario.getSegundoNombre(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getCedula(),
                usuario.getPeso(),
                usuario.getEstatura(),
                usuario.getGenero(),
                usuario.getContrasena(),
                usuario.getCorreo(),
                Date.valueOf(usuario.getFechaNacimiento()),
                usuario.getIdUsuario()
        );
    }

    @Override
    public void deleteById(String idUsuario) {
        // Borrar filas hijas que referencian al usuario antes de eliminar el usuario
        jdbcTemplate.update("DELETE FROM recomendacion WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM perfil_salud WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM historial_progreso WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM registro_participantes WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM historial_progreso WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM logro WHERE id_usuario = ?", idUsuario);
        jdbcTemplate.update("DELETE FROM notificacion WHERE id_usuario = ?", idUsuario);

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        jdbcTemplate.update(sql, idUsuario);
    }

    @Override
    public Usuario findByUsuarioAndContrasena(String usuario, String contrasena) {
        String sql = """
            SELECT u.*, r.nombre AS rol_nombre
            FROM usuario u
            JOIN rol r ON u.id_rol = r.id_rol
            WHERE (u.correo = ? OR u.cedula = ?)
              AND u.contrasena = ?
            LIMIT 1
            """;

        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), usuario, usuario, contrasena);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UsuarioReporteAvanzadoDTO> obtenerReporteAvanzadoUsuarios() {
        String sql = """
                SELECT 
                    u.id_usuario,
                    u.primer_nombre || ' ' || u.primer_apellido AS usuario,
                    ps.nivel_actividad,
                    ps.gasto_energetico,
                    ps.imc,
                    (
                        SELECT AVG(sub.calorias_dia)
                        FROM (
                            SELECT fecha, SUM(perdida_calorias_alcanzadas) AS calorias_dia
                            FROM registro_actividad
                            WHERE id_usuario = u.id_usuario
                            GROUP BY fecha
                        ) AS sub
                    ) AS promedio_calorias_usuario,
                    (
                        SELECT nombre FROM (
                            SELECT 
                                a2.nombre,
                                COUNT(*) AS repeticiones,
                                RANK() OVER (PARTITION BY ra2.id_usuario ORDER BY COUNT(*) DESC) AS ranking
                            FROM registro_actividad ra2
                            JOIN actividad a2 ON ra2.id_actividad = a2.id_actividad
                            WHERE ra2.id_usuario = u.id_usuario
                            GROUP BY ra2.id_usuario, a2.nombre
                        ) AS af
                        WHERE af.ranking = 1
                        LIMIT 1
                    ) AS actividad_favorita,
                    (
                        SELECT fecha FROM (
                            SELECT 
                                fecha,
                                SUM(perdida_calorias_alcanzadas) AS calorias_dia,
                                RANK() OVER (PARTITION BY id_usuario ORDER BY SUM(perdida_calorias_alcanzadas) DESC) AS rnk
                            FROM registro_actividad
                            WHERE id_usuario = u.id_usuario
                            GROUP BY id_usuario, fecha
                        ) AS dt
                        WHERE rnk = 1
                        LIMIT 1
                    ) AS dia_mas_activo,
                    (
                        SELECT calorias_dia FROM (
                            SELECT 
                                fecha,
                                SUM(perdida_calorias_alcanzadas) AS calorias_dia,
                                RANK() OVER (PARTITION BY id_usuario ORDER BY SUM(perdida_calorias_alcanzadas) DESC) AS rnk
                            FROM registro_actividad
                            WHERE id_usuario = u.id_usuario
                            GROUP BY id_usuario, fecha
                        ) AS dt2
                        WHERE rnk = 1
                        LIMIT 1
                    ) AS calorias_dia_top,
                    (SELECT AVG(perdida_calorias_alcanzadas) FROM registro_actividad) AS promedio_general,
                    (
                        SELECT MAX(calorias_dia)
                        FROM (
                            SELECT fecha, SUM(perdida_calorias_alcanzadas) AS calorias_dia
                            FROM registro_actividad
                            GROUP BY fecha
                        ) AS mx
                    ) AS max_calorias_global,
                    (
                        (
                            SELECT AVG(sub2.calorias_dia)
                            FROM (
                                SELECT fecha, SUM(perdida_calorias_alcanzadas) AS calorias_dia
                                FROM registro_actividad
                                WHERE id_usuario = u.id_usuario
                                GROUP BY fecha
                            ) AS sub2
                        ) -
                        (SELECT AVG(perdida_calorias_alcanzadas) FROM registro_actividad)
                    ) AS diferencia_promedio,
                    (
                        SELECT objetivo
                        FROM meta m
                        WHERE m.id_usuario = u.id_usuario
                        ORDER BY fecha_inicio DESC
                        LIMIT 1
                    ) AS meta_actual,
                    (
                        SELECT COUNT(*)
                        FROM historial_progreso hp
                        JOIN meta m2 ON hp.id_meta = m2.id_meta
                        WHERE m2.id_usuario = u.id_usuario
                    ) AS dias_registrados,
                    (
                        SELECT (m3.fecha_fin - CURRENT_DATE)
                        FROM meta m3
                        WHERE m3.id_usuario = u.id_usuario
                        ORDER BY fecha_inicio DESC
                        LIMIT 1
                    ) AS dias_restantes
                FROM usuario u
                LEFT JOIN perfil_salud ps ON ps.id_usuario = u.id_usuario
                ORDER BY promedio_calorias_usuario DESC
                """;

        return jdbcTemplate.query(sql, new UsuarioReporteAvanzadoMapper());
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Usuario.builder()
                    .idUsuario(rs.getString("id_usuario"))
                    .primerNombre(rs.getString("primer_nombre"))
                    .segundoNombre(rs.getString("segundo_nombre"))
                    .primerApellido(rs.getString("primer_apellido"))
                    .segundoApellido(rs.getString("segundo_apellido"))
                    .cedula(rs.getString("cedula"))
                    .peso(rs.getDouble("peso"))
                    .estatura(rs.getInt("estatura"))
                    .genero(rs.getString("genero"))
                    .contrasena(rs.getString("contrasena"))
                    .correo(rs.getString("correo"))
                    .fechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
                    .idRol(rs.getString("id_rol"))
                    .rolNombre(rs.getString("rol_nombre"))
                    .build();
        }
    }
}