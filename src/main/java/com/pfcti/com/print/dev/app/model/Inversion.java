package com.pfcti.com.print.dev.app.model;

import jakarta.persistence.*;

public class Inversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String numero;
    @Column
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;
}
