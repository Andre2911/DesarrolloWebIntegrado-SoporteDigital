package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "mov_asignaciones", schema = "soporte")
@Data
public class MovAsignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_asignacion;

    @ManyToOne
    @JoinColumn(name = "n_id_solicitud")
    private MovSolicitud solicitud;

    @ManyToOne
    @JoinColumn(name = "n_id_colaborador")
    private MovUsuario colaborador;

    private Boolean b_es_coordinador;

    @Column(nullable = false)
    private LocalDateTime f_fecha_asignacion;
}
