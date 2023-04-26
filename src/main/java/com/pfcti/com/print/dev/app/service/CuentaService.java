package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.criteria.CuentaSpecification;
import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.model.Cuenta;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import com.pfcti.com.print.dev.app.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
    private CuentaSpecification cuentaSpecification;

    public List<CuentaDto> buscardinamicamenteCuentaPorCriteriosDeBusqueda(CuentaDto CuentaDtoFilter)
    {
        return cuentaRepository
                .findAll(cuentaSpecification.buildFilter(CuentaDtoFilter))
                .stream()
                .map(this::fromCuentatoCuentaTdo)
                .collect(Collectors.toList());
    }


    private CuentaDto fromCuentatoCuentaTdo (Cuenta cuenta){
        CuentaDto CuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, CuentaDto);
        return CuentaDto;
    }

    public void insertarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        cliente.setId(cuentaDto.getClienteId());
        cuenta.setCliente(cliente);
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setNumero(cuentaDto.getNumero());
        cuentaRepository.save(cuenta);
    }


    public void actualizarCuenta(CuentaDto cuentaDto){
        Cuenta cuenta = cuentaRepository.findById(cuentaDto.getId())
                .orElseThrow(() -> {
                            throw new RuntimeException("NO Existe el cliente");
                        }
                );
        cuenta.setId (cuentaDto.getId());
        cuenta.setEstado(cuentaDto.getEstado());

        cuentaRepository.save(cuenta);
    }

    public  List<CuentaDto> BuscarCuentaCliente(int cliente_id){
        List<CuentaDto> cuentaDtos = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas = cuentaRepository.findCuentaByCliente_Id(cliente_id);
        cuentas.forEach(cuenta -> {
            cuentaDtos.add(fromCuentaToCuentaDto(cuenta));
        });


        return cuentaDtos;
    }


    private CuentaDto fromCuentaToCuentaDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        BeanUtils.copyProperties(cuenta, cuentaDto);
        return cuentaDto;
    }
}