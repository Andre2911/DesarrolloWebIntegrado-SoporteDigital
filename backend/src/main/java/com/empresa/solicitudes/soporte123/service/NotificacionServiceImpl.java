package com.empresa.solicitudes.soporte123.service;


import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.NotificacionResponse;
import com.empresa.solicitudes.soporte123.models.MovNotificacion;
import com.empresa.solicitudes.soporte123.models.MovUsuario;
import com.empresa.solicitudes.soporte123.respository.MovNotificacionRepository;
import com.empresa.solicitudes.soporte123.respository.MovUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {
    private final MovUsuarioRepository usuarioRepository;
    private final MovNotificacionRepository notificacionRepository;

    public ApiResponse<List<NotificacionResponse>> obtenerMisNotificaciones(String correoUsuario) {
        MovUsuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<MovNotificacion> notificaciones = notificacionRepository.findByUsuario(usuario);

        List<NotificacionResponse> lista = notificaciones.stream()
                .map(n -> new NotificacionResponse(
                        n.getN_id_notificacion(),
                        n.getS_tipo(),
                        n.getS_mensaje(),
                        n.getS_estado(),
                        n.getF_fecha_envio(),
                        n.getSolicitud().getN_id_solicitud()
                )).toList();

        return new ApiResponse<>(true, "Notificaciones encontradas", lista);
    }

}
