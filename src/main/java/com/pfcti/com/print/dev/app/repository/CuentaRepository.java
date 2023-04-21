package com.pfcti.com.print.dev.app.repository;

import com.pfcti.com.print.dev.app.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta,Integer> {
    void deleteAllByCliente_Id(Integer id);
}
