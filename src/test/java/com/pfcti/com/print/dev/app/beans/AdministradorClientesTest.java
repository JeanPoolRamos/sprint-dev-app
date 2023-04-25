package com.pfcti.com.print.dev.app.beans;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.ClienteQueryDto;
import com.pfcti.com.print.dev.app.dto.enums.ClienteQueryType;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministradorClientesTest {

    @Autowired
    ClienteRepository clienteRepository;
    @Test
    void obtenerListaClientesPorCriterio() {

        AdministradorClientes administradorClientes = new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRES);
        ClienteQueryDto clienteQueryDto = new ClienteQueryDto();
        clienteQueryDto.setTextoBusqueda("ROBERTO");
        clienteQueryDto.setClienteQueryType(ClienteQueryType.NOMBRES);

        List<ClienteDto> clienteDtos = administradorClientes.obtenerListaClientesPorCriterio(clienteQueryDto);

        System.out.println("<<<<<<<<<<<<<<<<<Clientes SANCHEZ>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() > 0);

    }
}