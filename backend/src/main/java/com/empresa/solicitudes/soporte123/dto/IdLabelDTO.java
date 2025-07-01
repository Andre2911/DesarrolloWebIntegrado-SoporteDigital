package com.empresa.solicitudes.soporte123.dto;


import lombok.Data;

@Data
public class IdLabelDTO {
    private Integer id;
    private String label;

    public IdLabelDTO(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
}
