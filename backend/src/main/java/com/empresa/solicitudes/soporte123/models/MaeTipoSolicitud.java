package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mae_tipos_solicitud", schema = "soporte")
@Data
public class MaeTipoSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_tipo_solicitud;

    @Column(nullable = false)
    private String s_nombre_tipo;
}

