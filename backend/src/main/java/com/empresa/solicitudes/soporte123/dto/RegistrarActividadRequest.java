package com.empresa.solicitudes.soporte123.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrarActividadRequest {
    private Integer asignacionId;
    private String descripcion;
    private Double horasTrabajadas;
    private LocalDate fechaActividad;
}