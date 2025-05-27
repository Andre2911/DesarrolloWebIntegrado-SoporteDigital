package com.empresa.solicitudes.soporte123.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mae_empresas", schema = "soporte")
public class MaeEmpresas {

    @Id
    @GeneratedValue
    @Column(name = "n_id")
    private Integer id;

    private String nombreEmpresa;
}
