package com.diacono.worker.application.port.dto.command;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//Informações provenientes da fila
public record EventInformationCommand(
        UUID idEvento,
        UUID idIgreja,
        String name,
        String targetPublic,
        LocalDateTime startEvent,
        LocalDateTime endEventCommand,
        String description,
        BigDecimal coast,
        String location
) {
}
