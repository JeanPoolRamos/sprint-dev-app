package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.criteria.CuentaSpecification;
import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.model.Cuenta;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import com.pfcti.com.print.dev.app.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}