package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AsignacionResumenResponse {
    private Integer asignacionId;
    private String nombreColaborador;
    private Boolean esCoordinador;
    private LocalDateTime fechaAsignacion;
}