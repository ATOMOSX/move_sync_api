package com.movesync.move_sync_api.application;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteAdminDTO;
import com.movesync.move_sync_api.application.dto.out.meta.MetaReporteDTO;
import com.movesync.move_sync_api.application.dto.out.meta.MetasActivasVsFinalizadasDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.EstadisticaDTO;
import com.movesync.move_sync_api.application.dto.out.reporte.ReporteResponseDTO;
import com.movesync.move_sync_api.application.port.interactor.IMetaService;
import com.movesync.move_sync_api.application.port.output.meta.IMetaRepository;
import com.movesync.move_sync_api.application.port.output.meta.IReporteMetaRepository;
import com.movesync.move_sync_api.application.port.output.meta.IReporteMetaRepositoryAdmin;
import com.movesync.move_sync_api.domain.entity.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaServiceImpl implements IMetaService {

    @Autowired
    private IMetaRepository metaRepository;

    @Autowired
    private IReporteMetaRepository reporteMetaRepository;

    @Autowired
    private IReporteMetaRepositoryAdmin reporteMetaRepositoryAdmin;

    @Override
    public List<Meta> listarMetas() {
        return metaRepository.findAll();
    }

    @Override
    public Meta obtenerPorId(String idMeta) {
        return metaRepository.findById(idMeta);
    }

    @Override
    public void registrarMeta(Meta meta) {
        validarMeta(meta);
        metaRepository.save(meta);
    }

    @Override
    public void actualizarMeta(Meta meta) {
        validarMeta(meta);
        metaRepository.update(meta);
    }

    @Override
    public void eliminarMeta(String idMeta) {
        metaRepository.deleteById(idMeta);
    }

    @Override
    public List<MetaReporteDTO> obtenerReporteMetas(String idUsuario) {
        return reporteMetaRepository.obtenerReporteMetas(Integer.valueOf(idUsuario));
    }

    @Override
    public List<MetaReporteAdminDTO> obtenerReporteMetasAdmin() {
        return reporteMetaRepositoryAdmin.obtenerReporteMetasAdmin();
    }

    @Override
    public ReporteResponseDTO<MetasActivasVsFinalizadasDTO> obtenerReporteMetasActivasVsFinalizadas(String idUsuario) {
        List<MetasActivasVsFinalizadasDTO> datos = reporteMetaRepository.obtenerMetasActivasVsFinalizadas(Integer.valueOf(idUsuario));

        // Calcular total
        long total = datos.stream()
                .mapToLong(MetasActivasVsFinalizadasDTO::getCantidad)
                .sum();

        // Crear estadísticas
        List<EstadisticaDTO> estadisticas = datos.stream()
                .map(d -> EstadisticaDTO.builder()
                        .etiqueta(d.getDescripcionEstado())
                        .cantidad(d.getCantidad())
                        .porcentaje(d.getPorcentaje())
                        .build())
                .collect(Collectors.toList());

        return ReporteResponseDTO.<MetasActivasVsFinalizadasDTO>builder()
                .titulo("Metas Activas vs Finalizadas")
                .descripcion("Reporte estadístico que muestra la distribución de metas activas y finalizadas del usuario")
                .fechaGeneracion(LocalDateTime.now())
                .totalRegistros(total)
                .datos(datos)
                .estadisticas(estadisticas)
                .build();
    }

    private void validarMeta(Meta meta) {
        if (meta.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria.");
        }
        if (meta.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha fin es obligatoria.");
        }
        if (meta.getFechaFin().isBefore(meta.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha de inicio.");
        }
        if (meta.getObjetivo() == null || meta.getObjetivo().isBlank()) {
            throw new IllegalArgumentException("El objetivo es obligatorio.");
        }
    }
}
