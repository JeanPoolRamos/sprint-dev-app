package com.pfcti.com.print.dev.app.jms.subscribers;

import com.pfcti.com.print.dev.app.dto.CuentaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProcesadorNotificacionEjecutivos {

    @ServiceActivator(inputChannel = "pubSubNotification")
    public Message<String> consumirMensajeParaEjecutivos(Message<CuentaDto> message) {
        CuentaDto cuentaDto = message.getPayload();
        log.info("ProcesadorNotificacionClientes -> Enviando notificación al ejecutivo con la siguiente información : {}", cuentaDto);
        return MessageBuilder.withPayload("Mensaje recibido por ProcesadorNotificacionEjecutivos").build();
    }
}
