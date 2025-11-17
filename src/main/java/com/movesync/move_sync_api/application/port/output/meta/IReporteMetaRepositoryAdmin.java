package com.movesync.move_sync_api.application.port.output.meta;

import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteAdminDTO;

import java.util.List;

public interface IReporteMetaRepositoryAdmin {
    List<MetaReporteAdminDTO> obtenerReporteMetasAdmin();
}
