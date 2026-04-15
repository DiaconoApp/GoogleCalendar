package com.diacono.worker.application.port.dto.command;

//Informações necessárias para criar um evento único no Google Calendar
public record SingleEventCommand(
        String acessToken,
        String summary,
        String description,
        String location,
        StartEventComand start,
        EndEventCommand end
) {
}
