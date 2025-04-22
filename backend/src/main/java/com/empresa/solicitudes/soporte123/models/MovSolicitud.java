package com.empresa.solicitudes.soporte123.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "mov_solicitudes", schema = "soporte")
@Data
public class MovSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_solicitud;

    @ManyToOne
    @JoinColumn(name = "n_id_usuario")
    private MovUsuario usuario;

    @ManyToOne
    @JoinColumn(name = "n_id_tipo_solicitud")
    private MaeTipoSolicitud tipo;

    @ManyToOne
    @JoinColumn(name = "n_id_coordinador")
    private MovUsuario coordinador;

    @Column(nullable = false)
    private String s_descripcion;

    @Column(name = "f_fecha_registro", nullable = false)
    private LocalDateTime f_fecha_registro;

    private String s_estado;
    private LocalDateTime f_fecha_cierre;
}
