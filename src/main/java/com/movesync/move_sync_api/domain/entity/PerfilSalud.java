package com.movesync.move_sync_api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilSalud {

    private String idPerfil;

    private String nivelActividad;

    private String gastoEnergetico;

    private String condicionCardiovascular;

    private String imc;

    private String balanceEnergetico;

    private String idUsuario;
}
