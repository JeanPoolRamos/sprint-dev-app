package com.pfcti.com.print.dev.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "TCLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(columnDefinition = "varchar(15)")
    private String apellidos;
    @Column(length = 30)
    private String cedula;
    @Column
    private String telefono;
    @Column
    private String pais;

    @OneToMany(mappedBy = "cliente")/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)*/
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente")/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)*/
    private List<Cuenta> cuentas;

    @OneToMany(mappedBy = "cliente")/*, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)*/
    private List<Tarjeta> tarjetas;
}
