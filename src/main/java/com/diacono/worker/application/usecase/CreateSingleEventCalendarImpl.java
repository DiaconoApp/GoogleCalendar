package com.diacono.worker.application.usecase;

import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.application.port.in.CreateSingleEventCalendarUseCase;
import com.diacono.worker.application.port.out.CalendarWriterGateway;
import com.diacono.worker.application.port.out.GoogleAuthGateway;
import com.diacono.worker.application.port.out.TokenGoogleRepository;

public class CreateSingleEventCalendarImpl implements CreateSingleEventCalendarUseCase {

    private final GoogleAuthGateway googleAuthGateway;
    private CalendarWriterGateway calendarWriterGateway;

    public CreateSingleEventCalendarImpl(GoogleAuthGateway googleAuthGateway, CalendarWriterGateway calendarWriterGateway) {
        this.googleAuthGateway = googleAuthGateway;
        this.calendarWriterGateway = calendarWriterGateway;
    }

    @Override
    public void execute(EventInformationCommand eventInformationCommand) {

        //1 - busco acess token com base no token enviado pelo orquestrador
        //2 - crio o evento na agenda do usuário

    }
}

