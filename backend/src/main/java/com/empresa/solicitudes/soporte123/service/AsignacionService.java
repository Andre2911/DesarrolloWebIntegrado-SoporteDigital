package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.AsignacionResponse;
import com.empresa.solicitudes.soporte123.dto.AsignacionResumenResponse;
import com.empresa.solicitudes.soporte123.dto.AsignarColaboradorRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AsignacionService {
    ApiResponse<AsignacionResponse> asignarColaborador(AsignarColaboradorRequest request);
    ApiResponse<List<AsignacionResumenResponse>> listarAsignacionesDeSolicitud(Integer idSolicitud, String username) throws AccessDeniedException;
}
