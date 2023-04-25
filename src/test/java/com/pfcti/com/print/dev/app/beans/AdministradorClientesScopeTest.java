package com.pfcti.com.print.dev.app.beans;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdministradorClientesScopeTest {

    @Autowired
    @Qualifier("defaultNombresSngleton")
    private AdministradorClientes Singleton1;

    @Autowired
    @Qualifier("defaultNombresSngleton")
    private AdministradorClientes Singleton2;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes prototipe1;

    @Autowired
    @Qualifier("defaultNombres")
    private AdministradorClientes prototipe2;


    @Test
    void instancias(){
        System.out.println("Singleton 1 " + Singleton1);
        System.out.println("Singleton 2 " + Singleton2);
        System.out.println("prototype 1 " + prototipe1);
        System.out.println("prototype 2 " + prototipe2);
        Assertions.assertEquals(1,1);
    }

}
