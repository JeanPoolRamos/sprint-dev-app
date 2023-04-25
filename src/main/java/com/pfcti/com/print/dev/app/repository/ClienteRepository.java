package com.pfcti.com.print.dev.app.repository;

import com.pfcti.com.print.dev.app.model.Cliente;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Integer>, JpaSpecificationExecutor<Cliente> {

    List<Cliente> findClientesByPaisAndCuentas_estadoIsTrue(String pais);

    @Query(value = "select c from Cliente c where c.apellidos = :apellidos")
    List<Cliente> buscarPorApellidos(String apellidos);


    @Query(value = "select NOMBRE, APELLIDOS, CEDULA, TELEFONO, PAIS, ID FROM TCLIENTE WHERE APELLIDOS =:apellidos", nativeQuery = true)
    List<Tuple> buscarPorApellidosNativo(String apellidos);

    @Modifying
    @Query(value = "update Cliente c set c.nombre =:nombre where c.apellidos =:apellidos")
    void actualizaClientePorApellido(String nombre, String apellidos);


    List<Cliente> findByApellidosAndAndNombre(String apellidos, String nombre);

    List<Cliente> findClienteByPaisIsNotContainingIgnoreCaseAndAndTarjetas_estadoIsFalse (String pais);

    List<Cliente> findClienteByCedula(String cedula);

    List<Cliente> findClientesByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombres, String apellidos);
}
