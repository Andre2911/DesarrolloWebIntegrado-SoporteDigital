package com.empresa.solicitudes.soporte123.service;


import com.empresa.solicitudes.soporte123.dto.ActividadResponse;
import com.empresa.solicitudes.soporte123.dto.ActividadResumenResponse;
import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.RegistrarActividadRequest;
import com.empresa.solicitudes.soporte123.models.MovActividad;
import com.empresa.solicitudes.soporte123.models.MovAsignacion;
import com.empresa.solicitudes.soporte123.models.MovSolicitud;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MovActividadesRepository;
import com.empresa.solicitudes.soporte123.respository.MovAsignacionRepository;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadServiceImpl implements ActividadService {

    private final MovAsignacionRepository asignacionRepository;
    private final MovActividadesRepository actividadRepository;
    private final MovUsuarioRepository usuarioRepository;

    public ApiResponse<ActividadResponse> registrarActividad(RegistrarActividadRequest request, String correoUsuario) throws AccessDeniedException {
        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        MovAsignacion asignacion = asignacionRepository.findById(request.getAsignacionId())
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        if (!asignacion.getColaborador().getN_id_usuario().equals(usuario.getN_id_usuario())) {
            throw new AccessDeniedException("Solo el colaborador asignado puede registrar actividades.");
        }

        MovActividad actividad = new MovActividad();
        actividad.setAsignacion(asignacion);
        actividad.setS_descripcion(request.getDescripcion());
        actividad.setN_horas_trabajadas(request.getHorasTrabajadas());
        actividad.setF_fecha_actividad(request.getFechaActividad());

        actividadRepository.save(actividad);

        return new ApiResponse<>(true, "Actividad registrada correctamente", new ActividadResponse(
                actividad.getN_id_actividad(),
                usuario.getS_nombre(),
                actividad.getS_descripcion(),
                actividad.getN_horas_trabajadas(),
                actividad.getF_fecha_actividad()
        ));
    }

    @Override
    public ApiResponse<List<ActividadResumenResponse>> listarPorAsignacion(Integer asignacionId, String correoUsuario) throws AccessDeniedException {
        MovAsignacion asignacion = asignacionRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        MovSolicitud solicitud = asignacion.getSolicitud();
        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        boolean esSolicitante = solicitud.getUsuario().getN_id_usuario().equals(usuario.getN_id_usuario());
        boolean esAsignado = asignacion.getColaborador().getN_id_usuario().equals(usuario.getN_id_usuario());
        boolean esCoordinador = solicitud.getCoordinador() != null &&
                solicitud.getCoordinador().getN_id_usuario().equals(usuario.getN_id_usuario());
        boolean esAdmin = usuario.getRol().getS_nombre_rol().equalsIgnoreCase("ADMIN");

        if (!esSolicitante && !esAsignado && !esCoordinador && !esAdmin) {
            throw new AccessDeniedException("No autorizado para ver actividades.");
        }

        List<MovActividad> actividades = actividadRepository.findByAsignacion(asignacion);

        List<ActividadResumenResponse> lista = actividades.stream()
                .map(a -> new ActividadResumenResponse(
                        a.getN_id_actividad(),
                        a.getS_descripcion(),
                        a.getN_horas_trabajadas(),
                        a.getF_fecha_actividad(),
                        a.getAsignacion().getColaborador().getS_nombre()
                ))
                .toList();

        return new ApiResponse<>(true, "Actividades encontradas", lista);
    }
}
