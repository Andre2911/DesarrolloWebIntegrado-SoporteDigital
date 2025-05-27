package com.empresa.solicitudes.soporte123.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "mae_aplicativos", schema = "soporte")
public class MaeAplicativos {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;
}
