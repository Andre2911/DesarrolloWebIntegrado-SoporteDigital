package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class SolicitudResumeResponse {
    private Integer id;
    private String tipo;
    private String estado;
    private LocalDateTime fechaRegistro;
}
