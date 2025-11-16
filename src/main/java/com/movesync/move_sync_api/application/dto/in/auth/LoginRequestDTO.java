package com.movesync.move_sync_api.application.dto.in.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class LoginRequestDTO {

    @NotNull
    private String usuario;
//    @NotNull
//    @Pattern(regexp = Constants.PASSWORD_REGEX, message = Constants.PASSWORD_MESSAGE)
    private String contrasena;
}
