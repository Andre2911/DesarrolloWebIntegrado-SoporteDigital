package com.empresa.solicitudes.soporte123.dto;

import lombok.Data;

@Data
public class CrearSolicitudRequest {
    private Integer tipoSolicitudId;
    private Integer aplicativoId;
    private String descripcion;
}
