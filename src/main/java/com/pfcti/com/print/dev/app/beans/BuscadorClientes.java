package com.pfcti.com.print.dev.app.beans;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.ClienteQueryDto;

import java.util.List;

public interface BuscadorClientes {

    List<ClienteDto> obtenerListaClientes(ClienteQueryDto clienteQueryDto);
}
