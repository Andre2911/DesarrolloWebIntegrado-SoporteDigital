package com.empresa.solicitudes.soporte123.service;


import com.empresa.solicitudes.soporte123.dto.*;
import com.empresa.solicitudes.soporte123.models.MaeAplicativos;
import com.empresa.solicitudes.soporte123.models.MaeTipoSolicitud;
import com.empresa.solicitudes.soporte123.models.MovSolicitud;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MaeAplicativosRepository;
import com.empresa.solicitudes.soporte123.respository.MaeTipoSolicitudRepository;
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
public class SolicitudServiceImpl implements SolicitudService {

    private final MovUsuarioRepository usuarioRepository;
    private final MovSolicitudRepository solicitudRepository;
    private final MaeTipoSolicitudRepository tipoSolicitudRepository;
    private final MaeAplicativosRepository aplicativoRepository;


    @Override
    public ApiResponse<SolicitudResponse> crearSolicitud(CrearSolicitudRequest request, String username) throws Exception {
        System.out.println("Creando solicitud: " + request);
        System.out.println("Usuario autenticado: " + username);
        MovUsuario usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        MaeTipoSolicitud tipo = tipoSolicitudRepository.findById(request.getTipoSolicitudId())
                .orElseThrow(() -> new RuntimeException("Tipo de solicitud no válido"));

        MaeAplicativos aplicativo = aplicativoRepository.findById(request.getAplicativoId())
                .orElseThrow(() -> new RuntimeException("Aplicativo no válido"));

        MovSolicitud solicitud = new MovSolicitud();
        solicitud.setUsuario(usuario);
        solicitud.setTipo(tipo);
        solicitud.setAplicativo(aplicativo);
        solicitud.setS_descripcion(request.getDescripcion());
        solicitud.setF_fecha_registro(LocalDateTime.now());
        solicitud.setEstado("Pendiente");

        solicitudRepository.save(solicitud);

        SolicitudResponse response = new SolicitudResponse(solicitud.getN_id_solicitud(), solicitud.getEstado());

        return new ApiResponse<>(true, "Solicitud registrada correctamente", response);
    }

    @Override
    public ApiResponse<List<SolicitudResumeResponse>> obtenerSolicitudesDelUsuario(String correoUsuario) throws Exception {
        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<MovSolicitud> solicitudes = solicitudRepository.findByUsuario(usuario);

        List<SolicitudResumeResponse> lista = solicitudes.stream()
                .map(s -> new SolicitudResumeResponse(
                        s.getN_id_solicitud(),
                        s.getTipo().getS_nombre_tipo(),
                        s.getEstado(),
                        s.getF_fecha_registro()))
                .toList();

        return new ApiResponse<>(true, "Solicitudes encontradas", lista);
    }

    @Override
    public ApiResponse<SolicitudDetalleResponse> obtenerDetalle(Integer idSolicitud, String correoUsuario) throws Exception {
        MovSolicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        boolean esSolicitante = solicitud.getUsuario().getN_id_usuario().equals(usuario.getN_id_usuario());
        boolean esAdmin = usuario.getRol().getS_nombre_rol().equalsIgnoreCase("ADMIN");
        boolean esCoordinador = solicitud.getCoordinador() != null &&
                solicitud.getCoordinador().getN_id_usuario().equals(usuario.getN_id_usuario());

        if (!esSolicitante && !esCoordinador && !esAdmin) {
            throw new AccessDeniedException("No autorizado para ver esta solicitud.");
        }

        SolicitudDetalleResponse detalle = new SolicitudDetalleResponse(
                solicitud.getN_id_solicitud(),
                solicitud.getS_descripcion(),
                solicitud.getTipo().getS_nombre_tipo(),
                solicitud.getEstado(),
                solicitud.getF_fecha_registro(),
                solicitud.getF_fecha_cierre(),
                solicitud.getAplicativo().getNombre(),
                solicitud.getUsuario().getEmpresa().getNombreEmpresa(),
                solicitud.getUsuario().getS_nombre()
        );

        return new ApiResponse<>(true, "Detalle de la solicitud", detalle);
    }

    @Override
    public ApiResponse<Void> cerrarSolicitud(Integer idSolicitud, String correoUsuario) throws Exception {
        MovSolicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        MovUsuario coordinador = solicitud.getCoordinador();
        if (coordinador == null || !coordinador.getS_correo().equalsIgnoreCase(correoUsuario)) {
            throw new AccessDeniedException("Solo el coordinador puede cerrar esta solicitud.");
        }

        if ("Finalizada".equalsIgnoreCase(solicitud.getEstado())) {
            throw new IllegalStateException("La solicitud ya fue finalizada.");
        }

        solicitud.setEstado("Finalizada");
        solicitud.setF_fecha_cierre(LocalDateTime.now());
        solicitudRepository.save(solicitud);

        return new ApiResponse<>(true, "Solicitud finalizada correctamente", null);
    }
}
