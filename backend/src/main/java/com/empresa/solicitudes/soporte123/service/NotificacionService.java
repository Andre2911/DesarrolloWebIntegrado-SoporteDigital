package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.NotificacionResponse;

import java.util.List;

public interface NotificacionService {

    ApiResponse<List<NotificacionResponse>> obtenerMisNotificaciones(String username);
}
