package com.movesync.move_sync_api.application.port.output.meta;

import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteDTO;

import java.util.List;

public interface IReporteMetaRepository {
    List<MetaReporteDTO> obtenerReporteMetas(Integer idUsuario);
}
