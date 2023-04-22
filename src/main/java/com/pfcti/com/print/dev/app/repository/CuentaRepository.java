package com.pfcti.com.print.dev.app.repository;

import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CuentaRepository extends JpaRepository<Cuenta,Integer>, JpaSpecificationExecutor<Cuenta> {
    void deleteAllByCliente_Id(Integer id);
}
