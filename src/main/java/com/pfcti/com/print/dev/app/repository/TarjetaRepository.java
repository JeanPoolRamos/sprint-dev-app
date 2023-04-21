package com.pfcti.com.print.dev.app.repository;

import com.pfcti.com.print.dev.app.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer> {

    void deleteAllByCliente_id(Integer id);
}
