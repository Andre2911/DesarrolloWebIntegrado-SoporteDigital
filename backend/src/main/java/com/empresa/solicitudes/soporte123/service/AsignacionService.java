package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.*;
import com.empresa.solicitudes.soporte123.models.MovSolicitud;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AsignacionService {
    ApiResponse<List<MovSolicitud>> listarAsignaciones(String username);
    ApiResponse<AsignacionResponse> asignarColaborador(AsignarColaboradorRequest request);
    ApiResponse<List<AsignacionResumenResponse>> listarAsignacionesDeSolicitud(Integer idSolicitud, String username) throws AccessDeniedException;
}
