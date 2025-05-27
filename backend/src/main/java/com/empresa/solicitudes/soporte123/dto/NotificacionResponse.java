package com.empresa.solicitudes.soporte123.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificacionResponse {
    private Integer id;
    private String tipo;
    private String mensaje;
    private String estado; // Enviada, Vista, etc.
    private LocalDateTime fechaEnvio;
    private Integer solicitudId;
}
