package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "mov_actividades", schema = "soporte")
@Data
public class MovActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_actividad;

    @ManyToOne
    @JoinColumn(name = "n_id_asignacion")
    private MovAsignacion asignacion;

    @Column(nullable = false)
    private LocalDate f_fecha_actividad;

    @Column(nullable = false)
    private String s_descripcion;

    @Column(nullable = false)
    private Double n_horas_trabajadas;
}

