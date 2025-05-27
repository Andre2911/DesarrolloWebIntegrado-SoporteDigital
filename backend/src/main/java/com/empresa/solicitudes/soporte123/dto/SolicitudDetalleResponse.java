package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SolicitudDetalleResponse {
    private Integer id;
    private String descripcion;
    private String tipo;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaCierre;
    private String aplicativo;
    private String empresa;
    private String solicitante;
}