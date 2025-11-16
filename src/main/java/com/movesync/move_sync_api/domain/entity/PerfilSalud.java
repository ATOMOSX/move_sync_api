package com.movesync.move_sync_api.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movesync.move_sync_api.infrastructurecross.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilSalud {

    private String idPerfil;

    private String idUsuario;

    private String nivelActividad;

    private String gastoEnergetico;

    private String condicionCardiovascular;

    private String imc;

    private String balanceEnergetico;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATA_PATTERN)
    private LocalDate fechaRegistro;
}
