package com.pfcti.com.print.dev.app.repository;

import com.pfcti.com.print.dev.app.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion,Integer> {
    void deleteAllByCliente_id(Integer id);
}
