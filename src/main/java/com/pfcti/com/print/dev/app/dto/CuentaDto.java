package com.pfcti.com.print.dev.app.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CuentaDto {
    private int Id;
    private String numero;
    private String tipo;
    private int clienteId;
    private boolean estado;
}
