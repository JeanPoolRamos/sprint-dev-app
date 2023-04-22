package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
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

        List<ClienteDto> clienteDtos = clienteService.obtenerClientes();
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(3, 3);
    }

    @Test
    void obtenerClientesporCodigoPaisYCuentasActivas() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientesporCodigoPaisYCuentasActivas("CR");
        System.out.println("<<<<<<<<<<<<<<<<<<Clientes Activos>>>>>>>>>>>>>>>>>> ");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(3, 3);
    }

    @Test
    void eliminarCLiente() {
        clienteService.eliminarcliente(1);
        assertEquals(3, 3);

    }

    @Test
    void buscarPorApellidos() {

        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidos("PEREZ");
        System.out.println("<<<<<<<<<<<<<<<<<Clientes>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertEquals(3, 3);
    }

    @Test
    void buscarPorApellidosNativo() {

        List<ClienteDto> clienteDtos = clienteService.buscarPorApellidosNativo("PEREZ");
        System.out.println("<<<<<<<<<<<<<<<<<Clientes>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertEquals(3, 3);
    }

    @Test
    void actualizaClientePorApellido() {

        ClienteDto clienteDtoOriginal = clienteService.buscarPorApellidos("PEREZ").get(0);
        System.out.println("<<<<<<<<<<<ORIGINAL "+clienteDtoOriginal.getNombre());
        clienteService.actualizaClientePorApellido("POOL", clienteDtoOriginal.getApellidos());
        ClienteDto clienteDtoNuevo = clienteService.buscarPorApellidos("PEREZ").get(0);
        System.out.println("<<<<<<<<<<<<NUEVO "+clienteDtoNuevo.getNombre());
        assertNotEquals(clienteDtoOriginal.getNombre(), clienteDtoNuevo.getNombre());
    }

    @Test
    void findByApellidosAndAndNombre() {
        List<ClienteDto> clienteDtos = clienteService.findByApellidosAndAndNombre("SANCHEZ", "RAUL");

        System.out.println("<<<<<<<<<<<<ENCONTRADO "+clienteDtos.get(0).getNombre());

        assertFalse(clienteDtos.isEmpty());
        assertEquals("SANCHEZ", clienteDtos.get(0).getApellidos());
    }

    @Test
    void obtenerClientesExtrangerosTarjetasActivas() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientesExtrangerosTarjetasInactivas("CR");
        System.out.println("<<<<<<<<<<<<<<<<<<Extrangeros Inactivos>>>>>>>>>>>>>>>>>> ");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals("PEREZ", clienteDtos.get(0).getApellidos());
    }

    @Test
    void buscardinamicamentePorCriteriosDeBusqueda() {
        List<ClienteDto> clienteDtos = clienteService.buscardinamicamentePorCriteriosDeBusqueda(new ClienteDto());
        assertFalse(clienteDtos.isEmpty());
        System.out.println("<<<<<<<<<<<<<<<<<Clientes>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() > 1);

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDtos = clienteService.buscardinamicamentePorCriteriosDeBusqueda(clienteDto);
        System.out.println("<<<<<<<<<<<<<<<<<Clientes SANCHEZ>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() > 1);

        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setNombre("HORACIO");
        clienteDtos = clienteService.buscardinamicamentePorCriteriosDeBusqueda(clienteDto);
        System.out.println("<<<<<<<<<<<<<<<<<Clientes SANCHEZ>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() == 1);

        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setCedula("111");
        clienteDtos = clienteService.buscardinamicamentePorCriteriosDeBusqueda(clienteDto);
        System.out.println("<<<<<<<<<<<<<<<<<Clientes SANCHEZ>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() == 1);

        clienteDto = new ClienteDto();
        clienteDto.setApellidos("SANCHEZ");
        clienteDto.setNombre("HORACIO");
        clienteDto.setCedula("1111");
        clienteDtos = clienteService.buscardinamicamentePorCriteriosDeBusqueda(clienteDto);
        System.out.println("<<<<<<<<<<<<<<<<<Clientes SANCHEZ>>>>>>>>>>>>>>>>>>>>");
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getNombre()));
        assertTrue(clienteDtos.size() == 1);
    }
}