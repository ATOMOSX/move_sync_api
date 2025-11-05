package com.movesync.move_sync_api.infrastructure.mapper;

import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import com.movesync.move_sync_api.domain.entity.Usuario;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .primerNombre(dto.getPrimerNombre())
                .segundoNombre(dto.getSegundoNombre())
                .primerApellido(dto.getPrimerApellido())
                .segundoApellido(dto.getSegundoApellido())
                .cedula(dto.getCedula())
                .peso(dto.getPeso())
                .estatura(dto.getEstatura())
                .genero(dto.getGenero())
                .contrasena(dto.getContrasena())
                .correo(dto.getCorreo())
                .fechaNacimiento(dto.getFechaNacimiento())
                .build();
    }

    public static UsuarioResponseDTO toResponse(Usuario entity) {
        return UsuarioResponseDTO.builder()
                .idUsuario(entity.getIdUsuario())
                .cedula(entity.getCedula())
                .primerNombre(entity.getPrimerNombre())
                .primerApellido(entity.getPrimerApellido())
                .correo(entity.getCorreo())
                .fechaNacimiento(entity.getFechaNacimiento())
                .build();
    }
}
