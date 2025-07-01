package com.empresa.solicitudes.soporte123.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class AsignacionesTableDTO {
    private Integer id;
    private String nombreColaborador;
    private String correo;
    private String tipoSolicitud;
    private String estadoSolicitud;
    private LocalDateTime fechaAsignacion;
}
