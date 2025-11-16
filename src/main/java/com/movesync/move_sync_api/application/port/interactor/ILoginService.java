package com.movesync.move_sync_api.application.port.interactor;

import com.movesync.move_sync_api.application.dto.out.auth.LoginResponseDTO;
import com.movesync.move_sync_api.domain.entity.Usuario;

public interface ILoginService {
    LoginResponseDTO login(String usuario, String contrasena);
}
