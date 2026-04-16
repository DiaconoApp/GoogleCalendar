package com.diacono.worker.application.usecase;

import com.diacono.worker.application.exceptions.AuthGatewayException;
import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.application.port.dto.command.EventWriterCommand;
import com.diacono.worker.application.port.in.CreateSingleEventCalendarUseCase;
import com.diacono.worker.application.port.out.CalendarWriterGateway;
import com.diacono.worker.application.port.out.GoogleAuthGateway;
import com.diacono.worker.domain.TokenGoogle;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CreateSingleEventCalendarImpl implements CreateSingleEventCalendarUseCase {

    private final GoogleAuthGateway googleAuthGateway;
    private final CalendarWriterGateway calendarWriterGateway;

    public CreateSingleEventCalendarImpl(GoogleAuthGateway googleAuthGateway, CalendarWriterGateway calendarWriterGateway) {
        this.googleAuthGateway = googleAuthGateway;
        this.calendarWriterGateway = calendarWriterGateway;
    }

    @Override
    public void execute(TokenGoogle tokenGoogle, EventInformationCommand eventInformationCommand) {
        try {

            log.debug("Iniciando criação de evento para a igreja: {}", eventInformationCommand.idIgreja());

            String accessToken = googleAuthGateway.getAccessToken(tokenGoogle.getTokenRefresh());

            calendarWriterGateway.insertEvent(accessToken, create(eventInformationCommand));

            log.info("Evento '{}' gerado com sucesso.", eventInformationCommand.name());

        } catch (AuthGatewayException e) {

            log.warn("Operação cancelada. Falha na autenticação (Token inválido/expirado): {}", e.getMessage());

        } catch (Exception e) {

            log.error("Erro inesperado ao processar o evento id: {}", eventInformationCommand.idEvento(), e);

        }
    }

    private EventWriterCommand create(EventInformationCommand dataPre) {
        return new EventWriterCommand(
                dataPre.name(),
                dataPre.description(),
                dataPre.location(),
                dataPre.coast(),
                dataPre.targetPublic(),
                dataPre.startEvent(),
                dataPre.endEventCommand()
        );
    }

}

