package com.empresa.solicitudes.soporte123.service;


import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.EstadisticaResumenResponse;
import com.empresa.solicitudes.soporte123.respository.MovActividadesRepository;
import com.empresa.solicitudes.soporte123.respository.MovSolicitudRepository;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadisticaServiceImpl implements EstadisticaService{
    private final MovSolicitudRepository solicitudRepository;
    private final MovUsuarioRepository usuarioRepository;
    private final MovActividadesRepository actividadRepository;



    @Override
    public ApiResponse<EstadisticaResumenResponse> obtenerResumen() {
        long total = solicitudRepository.count();
        long pendientes = solicitudRepository.countBySEstadoIgnoreCase("Pendiente");
        long finalizadas = solicitudRepository.countBySEstadoIgnoreCase("Finalizada");

        Double promedioHoras = actividadRepository.promedioHorasPorSolicitud();
        promedioHoras = promedioHoras != null ? promedioHoras : 0.0;

        int totalUsuarios = (int) usuarioRepository.count();

        EstadisticaResumenResponse data = new EstadisticaResumenResponse(
                total, pendientes, finalizadas, promedioHoras, totalUsuarios
        );

        return new ApiResponse<>(true, "Estadísticas generales", data);
    }
}
