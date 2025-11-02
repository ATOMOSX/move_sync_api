package com.movesync.move_sync_api.application.port.output;

import com.movesync.move_sync_api.domain.entity.Meta;

import java.util.List;

public interface IMetaRepository {
    List<Meta> findAll();
    Meta findById(String idMeta);
    void save(Meta meta);
    void update(Meta meta);
    void deleteById(String idMeta);
}
