package com.movesync.move_sync_api.application;

import com.movesync.move_sync_api.application.dto.out.auth.LoginResponseDTO;
import com.movesync.move_sync_api.application.port.interactor.ILoginService;
import com.movesync.move_sync_api.application.port.output.IUsuarioRepository;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public LoginResponseDTO login(String usuario, String contrasena) {
        Usuario usuario1 = usuarioRepository.findByUsuarioAndContrasena(usuario, contrasena);

        if (usuario1 == null) {
            throw new IllegalArgumentException(Constants.INVALID_CREDENTIALS_MESSAGE);
        }

        return LoginResponseDTO.builder()
                .idUsuario(usuario1.getIdUsuario())
                .nombreCompleto(usuario1.getNombrecompleto())
                .correoElectronico(usuario1.getCorreo())
                .build();
    }
}
