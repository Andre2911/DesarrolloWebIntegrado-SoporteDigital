package com.empresa.solicitudes.soporte123.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsignacionResponse {
    private Integer asignacionId;
    private String nombreColaborador;
    private Boolean esCoordinador;
}
