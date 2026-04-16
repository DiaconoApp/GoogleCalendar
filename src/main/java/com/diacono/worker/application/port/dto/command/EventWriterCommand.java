package com.diacono.worker.application.port.dto.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventWriterCommand(
        String summary, //titulo do evento
        String description,
        String location, //local do evento
        BigDecimal coast,
        String targetPublic,
        LocalDateTime start,
        LocalDateTime end
) {
}
