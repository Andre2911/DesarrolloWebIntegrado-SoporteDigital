package com.empresa.solicitudes.soporte123.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mov_cliente_aplicativos", schema = "soporte")
@Data
public class MovClienteAplicativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer n_id_cliente_aplicativo;

    @ManyToOne
    @JoinColumn(name = "n_id_empresa")
    private MaeEmpresas empresa;

    @ManyToOne
    @JoinColumn(name = "n_id_aplicativo")
    private MaeAplicativos aplicativo;


}
