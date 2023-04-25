package com.pfcti.com.print.dev.app.beans;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.ClienteQueryDto;
import com.pfcti.com.print.dev.app.dto.enums.ClienteQueryType;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("baseDeDatos")
public class BuscadorClienteBdd implements BuscadorClientes{

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
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
