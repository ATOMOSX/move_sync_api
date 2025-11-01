package com.movesync.move_sync_api.infrastructure.repository;

import com.movesync.move_sync_api.application.port.output.IUsuarioRepository;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT * FROM \"Usuario\" ORDER BY \"idUsuario\"";

        try {
            return jdbcTemplate.query(sql, new UsuarioRowMapper());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public Usuario findById(String idUsuario) {
        String sql = "SELECT * FROM \"Usuario\" WHERE \"idUsuario\" = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), idUsuario);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM \"Usuario\" WHERE \"correo\" = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UsuarioRowMapper(), email);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(Usuario usuario) {
        String sql = """
                INSERT INTO "Usuario"
                ("primerNombre","segundoNombre","primerApellido","segundoApellido",
                 "cedula","peso","estatura","genero","contrasena","correo","fechaNacimiento")
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
                Date.valueOf(usuario.getFechaNacimiento())
        );
    }

    @Override
    public void update(Usuario usuario) {
        String sql = """
                UPDATE "Usuario"
                SET "primerNombre" = ?, "segundoNombre" = ?, "primerApellido" = ?, "segundoApellido" = ?,
                    "cedula" = ?, "peso" = ?, "estatura" = ?, "genero" = ?, "contrasena" = ?, "correo" = ?,
                    "fechaNacimiento" = ?
                WHERE "idUsuario" = ?
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
        String sql = "DELETE FROM \"Usuario\" WHERE \"idUsuario\" = ?";
        jdbcTemplate.update(sql, idUsuario);
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                return Usuario.builder()
                        .idUsuario(rs.getString("idUsuario"))
                        .primerNombre(rs.getString("primerNombre"))
                        .segundoNombre(rs.getString("segundoNombre"))
                        .primerApellido(rs.getString("primerApellido"))
                        .segundoApellido(rs.getString("segundoApellido"))
                        .cedula(rs.getString("cedula"))
                        .peso(rs.getDouble("peso"))
                        .estatura(rs.getInt("estatura"))
                        .genero(rs.getString("genero"))
                        .contrasena(rs.getString("contrasena"))
                        .correo(rs.getString("correo"))
                        .fechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate())
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
