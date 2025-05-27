package com.empresa.solicitudes.soporte123.service;

import com.empresa.solicitudes.soporte123.dto.*;

import java.util.List;

public interface SolicitudService {

    public ApiResponse<SolicitudResponse> crearSolicitud(CrearSolicitudRequest request, String username) throws Exception;
    ApiResponse<List<SolicitudResumeResponse>> obtenerSolicitudesDelUsuario(String username) throws Exception;
    ApiResponse<SolicitudDetalleResponse> obtenerDetalle(Integer id, String username) throws Exception;
    ApiResponse<Void> cerrarSolicitud(Integer id, String username) throws Exception;
}
