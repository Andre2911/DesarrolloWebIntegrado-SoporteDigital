package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstadisticaResumenResponse {
    private Long totalSolicitudes;
    private Long solicitudesPendientes;
    private Long solicitudesFinalizadas;
    private Double promedioHorasPorSolicitud;
    private Integer totalUsuarios;
}
