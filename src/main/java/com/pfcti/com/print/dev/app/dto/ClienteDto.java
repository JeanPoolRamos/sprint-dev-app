package com.pfcti.com.print.dev.app.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDto {

    private int id;
    @NotNull
    @Size(max = 10)
    private String nombre;
    private String apellidos;
    @NotNull
    @Size(max = 13)
    private String cedula;
    private String telefono;
    private String pais;
    private List<DireccionesDto> direcciones;
    private List<CuentaDto> cuentas;

}
