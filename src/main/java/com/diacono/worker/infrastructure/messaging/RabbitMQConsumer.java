package com.diacono.worker.infrastructure.messaging;

import com.diacono.worker.application.exceptions.AuthGatewayException;
import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.application.port.in.TokenOrchestratorUseCase;
import com.diacono.worker.infrastructure.messaging.dto.EventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


public class RabbitMQConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final TokenOrchestratorUseCase useCase;

    public RabbitMQConsumer(TokenOrchestratorUseCase useCase) {
        this.useCase = useCase;
    }

    @RabbitListener(queues = "${mq.queue.evento}")
    public void processMessage(EventDTO data){

        EventInformationCommand dataForUseCase = new EventInformationCommand(
                data.idEvento(),
                data.idIgreja(),
                data.nome(),
                data.publicoAlvo(),
                data.dataHoraInicio(),
                data.dataHoraFim(),
                data.descricao(),
                data.custo(),
                data.localizacao()
        );

        try{
            useCase.execute(dataForUseCase);
        }catch(AuthGatewayException e){
            log.error("Erro ao comunicar com Google", e);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado", e);
            throw  e;
        }


    }

}
