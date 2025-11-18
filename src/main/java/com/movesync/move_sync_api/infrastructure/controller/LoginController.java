package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.LoginServiceImpl;
import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.auth.LoginRequestDTO;
import com.movesync.move_sync_api.application.dto.in.usuario.UsuarioRequestDTO;
import com.movesync.move_sync_api.application.dto.out.auth.LoginResponseDTO;
import com.movesync.move_sync_api.application.dto.out.usuario.UsuarioResponseDTO;
import com.movesync.move_sync_api.application.port.interactor.ILoginService;
import com.movesync.move_sync_api.application.port.interactor.IUsuarioService;
import com.movesync.move_sync_api.domain.entity.Usuario;
import com.movesync.move_sync_api.infrastructure.mapper.UsuarioMapper;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = loginService.login(loginRequestDTO.getUsuario(), loginRequestDTO.getContrasena());
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGIN_EXITOSO, response));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> registrarUsuario(@Valid @RequestBody UsuarioRequestDTO request) {
        Usuario usuario = UsuarioMapper.toEntity(request);
        usuarioService.registrarUsuario(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(usuario);
        return ResponseEntity.ok(ApiResponse.success(Constants.USUARIO_REGISTRADO, response));
    }
}
