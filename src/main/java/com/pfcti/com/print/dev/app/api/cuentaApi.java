package com.pfcti.com.print.dev.app.api;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.service.ClienteService;
import com.pfcti.com.print.dev.app.service.CuentaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/api/cuenta")
public class cuentaApi {

    @Autowired
    private CuentaService cuentaService;


    @PostMapping
    public void guardarCuenta(@RequestBody CuentaDto cuentaDto){
        log.info("Cuenta a guardar : {}", cuentaDto);
        cuentaService.insertarCuenta(cuentaDto); }

    @GetMapping("/{id}")
    public List<CuentaDto> buscarCuentaCliente(@PathVariable int id) {
        log.info("Busqueda de cliente : {}", id);
        return cuentaService.BuscarCuentaCliente(id);
    }

    @PutMapping
    public void actualizarCuenta(@Valid @RequestBody CuentaDto cuentaDto){
        log.info("Cuenta a guardar : {}", cuentaDto);
        cuentaService.actualizarCuenta(cuentaDto); }
}
