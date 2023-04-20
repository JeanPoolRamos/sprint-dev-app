package com.pfcti.com.print.dev.app.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class InversionDto {

    private int Id;
    private String numero;
    private String tipo;
    private int clienteId;
}
