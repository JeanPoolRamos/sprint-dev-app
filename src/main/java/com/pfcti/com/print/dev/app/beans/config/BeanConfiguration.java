package com.pfcti.com.print.dev.app.beans.config;

import com.pfcti.com.print.dev.app.beans.AdministradorClientes;
import com.pfcti.com.print.dev.app.dto.enums.ClienteQueryType;
import com.pfcti.com.print.dev.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Autowired
    private ClienteRepository clienteRepository;
    @Bean({"defaultCedula", "criterioCedula"})
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClientes administradorClientesBean() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.CEDULA);
    }

    @Bean({"defaultNombres", "criterioNombre"})
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Lazy
    public AdministradorClientes administradorClientesBeanByNombre() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRES);
    }

    @Bean({"defaultNombresSngleton"})
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy
    public AdministradorClientes administradorClientesBeanByNombreSigleton() {
        return new AdministradorClientes(clienteRepository, ClienteQueryType.NOMBRES);
    }
}
