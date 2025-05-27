package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActividadResumenResponse {
    private Integer id;
    private String descripcion;
    private Double horasTrabajadas;
    private LocalDate fechaActividad;
    private String colaborador;
}
