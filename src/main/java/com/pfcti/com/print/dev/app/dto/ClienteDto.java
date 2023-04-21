package com.pfcti.com.print.dev.app.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDto {

    private int id;
    private String nombre;
    private String apellidos;
    private String cedula;
    private String telefono;
    private String pais;
    private List<DireccionesDto> direcciones;
    private List<CuentaDto> cuentas;
}
