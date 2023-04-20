package com.pfcti.com.print.dev.app.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DireccionesDto {

    private int Id;
    private String direccion;
    private String nomenclatura;
    private int clienteId;
}
