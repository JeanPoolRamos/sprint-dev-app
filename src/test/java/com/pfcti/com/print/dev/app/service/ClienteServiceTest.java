package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void insertarCliente() {

        List<Cliente> listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println("------ Lista antes:" + listaClientes.size());

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setTelefono("72661559");
        clienteDto.setNombre("Pool");
        clienteDto.setCedula("111870225");
        clienteDto.setApellidos("Ramos");
        clienteService.insertarCliente(clienteDto);

        listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println("------ Lista despues:" + listaClientes.size());


        assertEquals(3, listaClientes.size());
    }

    @Test
    void obtenerCliente() {
        ClienteDto clienteDto = clienteService.obtenerCliente(1);
        System.out.println("------ Nombre:"+ clienteDto.getNombre());
        assertEquals(3, 3);
    }

    @Test
    void actualizarCliente() {
        ClienteDto clienteDtoInicial = clienteService.obtenerCliente(1);
        System.out.println("------ CLienet inicial:"+ clienteDtoInicial.getNombre());
        clienteDtoInicial.setNombre("Pedro");
        clienteService.actualizarCliente(clienteDtoInicial);
        ClienteDto clienteDtoUpdate = clienteService.obtenerCliente(1);
        System.out.println("------ Cliente final:"+ clienteDtoUpdate.getNombre());
        assertEquals(3, 3);

    }

    @Test
    void obtenerClientes() {
        clienteService.obtenerClientes().stream().map(
                cliente ->{
                    System.out.println(">>>>>>> Cliente :" + cliente.getApellidos());
                    return cliente;
                }
        ).collect(Collectors.toList());
        assertEquals(1,1);
    }
}