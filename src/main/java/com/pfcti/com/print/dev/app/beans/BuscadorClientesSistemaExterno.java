package com.pfcti.com.print.dev.app.beans;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.ClienteQueryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SistemaExterno")
public class BuscadorClientesSistemaExterno  implements BuscadorClientes{
    @Override
    public List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto) {
        return new ArrayList<>();
    }
}
