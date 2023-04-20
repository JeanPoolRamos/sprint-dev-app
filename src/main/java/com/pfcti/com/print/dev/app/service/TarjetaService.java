package com.pfcti.com.print.dev.app.service;

import com.pfcti.com.print.dev.app.repository.TarjetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarjetaService {
    private TarjetaRepository tarjetaRepository;
}
