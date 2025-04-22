package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mae_roles", schema = "soporte")
@Data
public class MaeRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_rol;

    @Column(nullable = false)
    private String s_nombre_rol;
}
