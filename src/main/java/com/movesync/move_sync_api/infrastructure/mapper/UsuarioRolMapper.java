package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.domain.entity.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRolMapper implements RowMapper<Usuario> {

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
