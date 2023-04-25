package com.pfcti.com.print.dev.app.criteria;

import com.pfcti.com.print.dev.app.dto.ClienteDto;
import com.pfcti.com.print.dev.app.dto.CuentaDto;
import com.pfcti.com.print.dev.app.model.Cliente;
import com.pfcti.com.print.dev.app.model.Cuenta;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
public class CuentaSpecification {

    public <T> Specification<T> equals(String fieldName, String fieldValue){
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

    public <T> Specification<T> isTrue(String fieldName, Boolean fieldValue) {
        return fieldValue == null ? null :
                (root, query, criteriaBuilder)
                        -> criteriaBuilder.equal(root.get(fieldName), fieldValue);
    }

    private Specification<Cuenta> numeroCriterio(CuentaDto cuentaDto)
    {

        return like("numero", cuentaDto.getNumero());
    }

    private Specification<Cuenta> tipoCriterio(CuentaDto cuentaDto)
    {

        return equals("tipo", cuentaDto.getTipo());
    }

    private Specification<Cuenta> estadoCriteria(CuentaDto cuentaDto) {
        return isTrue("estado", cuentaDto.getEstado());
    }

    public Specification<Cuenta> buildFilter(CuentaDto cuentaDto){
        System.out.println("BUSQUEDA POR CRITERIOS: " + cuentaDto);
        return Specification
                .where(tipoCriterio(cuentaDto))
                .and(estadoCriteria(cuentaDto))
                .and(numeroCriterio(cuentaDto));
    }
}
