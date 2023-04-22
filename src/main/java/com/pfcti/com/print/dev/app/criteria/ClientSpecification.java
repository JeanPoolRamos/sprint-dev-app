package com.pfcti.com.print.dev.app.criteria;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class ClientSpecification {

    public <T>Specification<T> equals(String fieldName, String fieldValue){
        return  fieldValue == null ? null :
                ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), fieldValue));
    }

    public  static <T> Specification<T> like(String fieldName, String fieldValue){
        if (fieldValue != null){
            String upperCaseValue = MessageFormat.format("%{0}%", fieldValue.trim().
                            toUpperCase(Locale.ROOT)).replaceAll(" ", "%");
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)),upperCaseValue));
        }
        else {
            return null;
        }
    }

    private Specification<Cliente> apellidoCriterio(ClienteDto clienteDto)
    {
        return like("apellidos", clienteDto.getApellidos());
    }

    private Specification<Cliente> nombreCriterio(ClienteDto clienteDto)
    {
        return like("nombre", clienteDto.getNombre());
    }

    private Specification<Cliente> cedulaCriterio(ClienteDto clienteDto)
    {
        return equals("cedula", clienteDto.getCedula());
    }

    private Specification<Cliente> telefonoCriterio(ClienteDto clienteDto)
    {
        return equals("telefono", clienteDto.getTelefono());
    }

    public Specification<Cliente> buildFilter(ClienteDto clienteDto){
        System.out.println("BUSQUEDA POR CRITERIOS: " + clienteDto);
        return Specification
                .where(apellidoCriterio(clienteDto))
                .and(cedulaCriterio(clienteDto))
                .and(telefonoCriterio(clienteDto))
                .and(nombreCriterio(clienteDto));
    }
}
