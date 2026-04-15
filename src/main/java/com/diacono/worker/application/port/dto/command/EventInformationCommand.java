package com.diacono.worker.application.port.dto.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Informações provenientes da fila
public record EventInformationCommand(
        UUID idEvento,
        UUID idIgreja,
        String name,
        String targetPublic,
        StartEventComand startEvent,
        EndEventCommand endEventCommand,
        String description,
        BigDecimal coast,
        String location
) {
}
