package com.empresa.solicitudes.soporte123.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mov_usuarios", schema = "soporte")
@Data
public class MovUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_usuario;

    @Column(nullable = false)
    private String s_nombre;

    @Column(nullable = false, unique = true)
    private String s_correo;

    @Column(nullable = false)
    private String s_contrasena;

    @ManyToOne
    @JoinColumn(name = "n_id_rol")
    private MaeRoles rol;
}
