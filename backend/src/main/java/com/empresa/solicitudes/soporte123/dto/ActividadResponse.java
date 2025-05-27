package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActividadResponse {
    private Integer id;
    private String colaborador;
    private String descripcion;
    private Double horasTrabajadas;
    private LocalDate fecha;
}