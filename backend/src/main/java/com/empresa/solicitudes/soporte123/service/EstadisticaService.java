package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.ApiResponse;
import com.empresa.solicitudes.soporte123.dto.EstadisticaResumenResponse;

public interface EstadisticaService {
    ApiResponse<EstadisticaResumenResponse> obtenerResumen();
}
