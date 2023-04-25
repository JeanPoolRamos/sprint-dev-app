package com.pfcti.com.print.dev.app.beans;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.enums.ClienteQueryType;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import com.pfcti.com.print.dev.app.dto.ClienteQueryDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AdministradorClientes {

    private ClienteRepository clienteRepository;
    private ClienteQueryType defauldClienteQueryType;

    public AdministradorClientes(ClienteRepository clienteRepository, ClienteQueryType defauldClienteQueryType)
    {
        System.out.println(">>>>> AdministradorClientes INSTANCIA: " + this);
        this.clienteRepository = clienteRepository;
        this.defauldClienteQueryType = defauldClienteQueryType;
    }

    public List<ClienteDto> obtenerListaClientesPorCriterio(ClienteQueryDto clienteQueryDto){
        List<Cliente> clientes = null;
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        if (ClienteQueryType.CEDULA.equals(clienteQueryDto.getClienteQueryType())) {
            clientes = clienteRepository.findClienteByCedula(clienteQueryDto.getTextoBusqueda());
        }
        else if(ClienteQueryType.NOMBRES.equals(clienteQueryDto.getClienteQueryType())) {
            clientes = clienteRepository.findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(clienteQueryDto.getTextoBusqueda(),clienteQueryDto.getTextoBusqueda());
        }
        clientes.forEach(cliente -> {
            clienteDtoList.add(fromClientetoClienteTdo(cliente));
        });
        return clienteDtoList;
    }


    private ClienteDto fromClientetoClienteTdo (Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        BeanUtils.copyProperties(cliente, clienteDto);
        return clienteDto;
    }
}
