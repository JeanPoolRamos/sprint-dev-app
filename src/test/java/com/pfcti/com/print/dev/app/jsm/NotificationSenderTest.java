package com.pfcti.com.print.dev.app.jsm;

import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.service.CuentaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Slf4j
public class NotificationSenderTest {
    @Autowired
    private CuentaService cuentaService;

    @Test
    void crearCuentaYNotificar() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        cuentaDto.setNumero("4788987255");
        cuentaDto.setTipo("AHO");
        cuentaDto.setClienteId(1);
        this.cuentaService.creacionDeCuentaYNotificacion(cuentaDto);
    }

    @Test
    void crearCuentaPubSub() {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setEstado(true);
        cuentaDto.setNumero("4788987255");
        cuentaDto.setTipo("AHO");
        cuentaDto.setClienteId(1);
        this.cuentaService.creacionDeCuentaPublishSub(cuentaDto);
    }

}
