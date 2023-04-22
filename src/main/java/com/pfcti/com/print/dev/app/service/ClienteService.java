package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.criteria.ClientSpecification;
import com.pfcti.com.print.dev.app.criteria.CuentaSpecification;
import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.model.Cuenta;
import com.pfcti.com.print.dev.app.repository.*;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;
    private DireccionRepository direccionRepository;
    private CuentaRepository cuentaRepository;
    private TarjetaRepository tarjetaRepository;
    private InversionRepository inversionRepository;

    private ClientSpecification clientSpecification;


    public void insertarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);
    }

    public ClienteDto obtenerCliente(int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> {
                            throw new RuntimeException("NO Existe el cliente");
                        }
                );
        return fromClientetoClienteTdo(cliente);
    }

    public void actualizarCliente(ClienteDto clienteDto){
             Cliente cliente = clienteRepository.findById(clienteDto.getId())
                .orElseThrow(() -> {
                            throw new RuntimeException("NO Existe el cliente");
                        }
                );
            cliente.setId(clienteDto.getId());
            cliente.setNombre(clienteDto.getNombre());
            cliente.setApellidos(clienteDto.getApellidos());
            cliente.setCedula(clienteDto.getCedula());
            cliente.setTelefono(clienteDto.getTelefono());

            clienteRepository.save(cliente);
    }

    public List<ClienteDto> obtenerClientes(){
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();

        clientes.forEach(cliente -> {
            clienteDtoList.add(fromClientetoClienteTdo(cliente));
        });

        return clienteDtoList;
    }

    private ClienteDto fromClientetoClienteTdo (Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);

        /*clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());*/
        return clienteDto;
    }

    public List<ClienteDto> obtenerClientesporCodigoPaisYCuentasActivas(String codigoPais)
    {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClientesByPaisAndCuentas_estadoIsTrue(codigoPais);
        clientes.forEach(cliente -> {
            clienteDtoList.add(fromClientetoClienteTdo(cliente));
        });

        return clienteDtoList;
    }

    public void eliminarcliente(Integer id)
    {
        direccionRepository.deleteAllByCliente_id(id);
        cuentaRepository.deleteAllByCliente_Id(id);
        clienteRepository.deleteById(id);
        tarjetaRepository.deleteAllByCliente_id(id);
        inversionRepository.deleteAllByCliente_Id(id);
    }

    public List<ClienteDto> buscarPorApellidos(String apellidos)
    {
        List<ClienteDto> clienteDtoList = new ArrayList<>() ;
        List<Cliente> clientes = clienteRepository.buscarPorApellidos(apellidos);
        clientes.forEach(cliente -> {
            clienteDtoList.add(fromClientetoClienteTdo(cliente));
        });
        return clienteDtoList;
    }

    public List<ClienteDto> buscarPorApellidosNativo(String apellidos)
    {
        List<ClienteDto> clienteDtoList = new ArrayList<>() ;
        List<Tuple> tuples = clienteRepository.buscarPorApellidosNativo(apellidos);


        tuples.forEach(tuple -> {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setApellidos((String) tuple.get("apellidos"));
            clienteDto.setNombre((String) tuple.get("Nombre"));
            clienteDto.setCedula((String) tuple.get("cedula"));
            clienteDto.setTelefono((String) tuple.get("telefono"));
            clienteDto.setId((Integer)tuple.get("id"));
            clienteDtoList.add(clienteDto);
        });
        return clienteDtoList;
    }

    public void actualizaClientePorApellido(String nombre, String apellidos) {
        clienteRepository.actualizaClientePorApellido(nombre,apellidos);
    }

    public List<ClienteDto> findByApellidosAndAndNombre(String apellidos, String nombre){
        return clienteRepository
                .findByApellidosAndAndNombre(apellidos,nombre)
                .stream()
                .map(this::fromClientetoClienteTdo)
                .collect(Collectors.toList());
    }

    public List<ClienteDto> obtenerClientesExtrangerosTarjetasInactivas(String codigoPais)
    {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findClienteByPaisIsNotContainingIgnoreCaseAndAndTarjetas_estadoIsFalse(codigoPais);
        clientes.forEach(cliente -> {
            clienteDtoList.add(fromClientetoClienteTdo(cliente));
        });

        return clienteDtoList;
    }

    public List<ClienteDto> buscardinamicamentePorCriteriosDeBusqueda(ClienteDto clienteDtoFilter)
    {
        return clienteRepository
                .findAll(clientSpecification.buildFilter(clienteDtoFilter))
                .stream()
                .map(this::fromClientetoClienteTdo)
                .collect(Collectors.toList());
    }


}
