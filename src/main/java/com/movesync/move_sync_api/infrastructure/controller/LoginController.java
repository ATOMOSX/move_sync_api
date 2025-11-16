package com.movesync.move_sync_api.infrastructure.controller;

import com.movesync.move_sync_api.application.LoginServiceImpl;
import com.movesync.move_sync_api.application.dto.ApiResponse;
import com.movesync.move_sync_api.application.dto.in.auth.LoginRequestDTO;
import com.movesync.move_sync_api.application.dto.out.auth.LoginResponseDTO;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login (@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = loginService.login(loginRequestDTO.getUsuario(), loginRequestDTO.getContrasena());
        return ResponseEntity.ok(ApiResponse.success(Constants.LOGIN_EXITOSO, response));
    }
}
