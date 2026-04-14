package com.diacono.worker.application.port.dto.command;

public record SingleEventCommand(
        String acessToken,
        String id, //primary
        String summary,
        String description,
        String location,
        StartEventComand start,
        EndEventCommand end
) {
}
