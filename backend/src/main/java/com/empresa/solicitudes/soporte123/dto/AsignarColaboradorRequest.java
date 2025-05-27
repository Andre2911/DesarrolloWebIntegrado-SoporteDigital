package com.empresa.solicitudes.soporte123.dto;


import lombok.Data;

@Data
public class AsignarColaboradorRequest {
    private Integer solicitudId;
    private Integer colaboradorId;
    private Boolean esCoordinador;
}
