package com.empresa.solicitudes.soporte123.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String correo;
    private String contrasena;
    private Integer rolId;
    private Integer empresaId;
}
