package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "mov_notificaciones", schema = "soporte")
@Data
public class MovNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_notificacion;

    @ManyToOne
    @JoinColumn(name = "n_id_usuario")
    private MovUsuario usuario;

    @ManyToOne
    @JoinColumn(name = "n_id_solicitud")
    private MovSolicitud solicitud;

    private String s_tipo;
    private String s_mensaje;
    private LocalDateTime f_fecha_envio;
    private String s_estado;
}
