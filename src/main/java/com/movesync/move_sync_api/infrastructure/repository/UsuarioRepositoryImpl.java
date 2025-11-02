package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.port.output.IUsuarioRepository;
import com.movesync.move_sync_api.domain.entity.Usuario;
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
    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE correo = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), email);
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
                    .build();
        }
    }
}