package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import com.pfcti.com.print.dev.app.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class CuentaService {
    private CuentaRepository cuentaRepository;
}