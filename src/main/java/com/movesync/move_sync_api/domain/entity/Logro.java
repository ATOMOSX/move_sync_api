package com.movesync.move_sync_api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logro {

    private String idLogro;

    private String nombre;

    private String recompensa;

    private String descripcion;

    private String tipo;

    private String idUsuario;
}
