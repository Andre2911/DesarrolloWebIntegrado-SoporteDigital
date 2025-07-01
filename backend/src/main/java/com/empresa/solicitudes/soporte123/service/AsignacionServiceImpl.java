package com.empresa.solicitudes.soporte123.service;


import com.empresa.solicitudes.soporte123.dto.*;
import com.empresa.solicitudes.soporte123.models.MovAsignacion;
import com.empresa.solicitudes.soporte123.models.MovSolicitud;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MovAsignacionRepository;
import com.empresa.solicitudes.soporte123.respository.MovSolicitudRepository;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionServiceImpl implements AsignacionService {

    private final MovSolicitudRepository solicitudRepository;
    private final MovUsuarioRepository usuarioRepository;
    private final MovAsignacionRepository asignacionRepository;

    @Override
    public ApiResponse<List<MovSolicitud>> listarAsignaciones(String username) {
        return new ApiResponse<>(true, "Asignaciones obtenidas correctamente",
                asignacionRepository.findByColaboradorCorreo());
    }

    public ApiResponse<AsignacionResponse> asignarColaborador(AsignarColaboradorRequest request) {
        MovSolicitud solicitud = solicitudRepository.findById(request.getSolicitudId())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        MovUsuario colaborador = usuarioRepository.findById(request.getColaboradorId())
                .orElseThrow(() -> new RuntimeException("Colaborador no encontrado"));

        MovAsignacion asignacion = new MovAsignacion();
        asignacion.setSolicitud(solicitud);
        asignacion.setColaborador(colaborador);
        asignacion.setB_es_coordinador(request.getEsCoordinador() != null && request.getEsCoordinador());
        asignacion.setF_fecha_asignacion(LocalDateTime.now());

        asignacionRepository.save(asignacion);

        if (asignacion.getB_es_coordinador()) {
            solicitud.setCoordinador(colaborador);
            solicitudRepository.save(solicitud);
        }

        AsignacionResponse response = new AsignacionResponse(
                asignacion.getN_id_asignacion(),
                colaborador.getS_nombre(),
                asignacion.getB_es_coordinador()
        );

        return new ApiResponse<>(true, "Colaborador asignado correctamente", response);
    }

    @Override
    public ApiResponse<List<AsignacionResumenResponse>> listarAsignacionesDeSolicitud(Integer solicitudId, String correoUsuario) throws AccessDeniedException {
        MovSolicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        boolean esSolicitante = solicitud.getUsuario().getN_id_usuario().equals(usuario.getN_id_usuario());
        boolean esAdmin = usuario.getRol().getS_nombre_rol().equalsIgnoreCase("ADMIN");
        boolean esAsignado = asignacionRepository.existsBySolicitudAndColaborador(solicitud, usuario);
        boolean esCoordinador = solicitud.getCoordinador() != null &&
                solicitud.getCoordinador().getN_id_usuario().equals(usuario.getN_id_usuario());

        if (!esSolicitante && !esAsignado && !esCoordinador && !esAdmin) {
            throw new AccessDeniedException("No autorizado para ver las asignaciones de esta solicitud.");
        }

        List<MovAsignacion> asignaciones = asignacionRepository.findBySolicitud(solicitud);

        List<AsignacionResumenResponse> lista = asignaciones.stream()
                .map(a -> new AsignacionResumenResponse(
                        a.getN_id_asignacion(),
                        a.getColaborador().getS_nombre(),
                        a.getB_es_coordinador(),
                        a.getF_fecha_asignacion()
                ))
                .toList();

        return new ApiResponse<>(true, "Asignaciones encontradas", lista);
    }


}
