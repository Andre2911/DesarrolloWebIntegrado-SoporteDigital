package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.ActividadResponse;
import com.empresa.solicitudes.soporte123.dto.ActividadResumenResponse;
import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.RegistrarActividadRequest;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface ActividadService {

    ApiResponse<ActividadResponse> registrarActividad(RegistrarActividadRequest request, String correo) throws AccessDeniedException;
    ApiResponse<List<ActividadResumenResponse>> listarPorAsignacion(Integer idAsignacion, String username) throws AccessDeniedException;
}
