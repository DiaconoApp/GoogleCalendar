package com.diacono.worker.application.port.dto.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventInformationCommand(
        UUID idEvento,
        UUID idIgreja,
        String name,
        String targetPublic,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String description,
        BigDecimal coast,
        String location
) {
}
