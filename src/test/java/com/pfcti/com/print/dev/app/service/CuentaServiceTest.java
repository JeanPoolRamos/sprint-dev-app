package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.dto.CuentaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuentaServiceTest {

    @Autowired
    private CuentaService cuentaService;

    @Test
    void buscardinamicamenteCuentaPorCriteriosDeBusqueda() {

        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(false);
        List<CuentaDto> cuentaDtos = cuentaService.buscardinamicamenteCuentaPorCriteriosDeBusqueda(cuentaDto);
        assertFalse(cuentaDtos.isEmpty());
        System.out.println("<<<<<<<<<<<<<<<<<Cuentas>>>>>>>>>>>>>>>>>>>>");
        cuentaDtos.forEach(cuenta -> System.out.println("Cuenta: " + cuenta.getNumero()));
        assertTrue(cuentaDtos.size() > 1);

    }
}