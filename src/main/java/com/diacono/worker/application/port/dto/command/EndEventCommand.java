package com.diacono.worker.application.port.dto.command;

import java.time.LocalDateTime;

public record EndEventCommand(
        LocalDateTime endDateTime,
        String timeZone
) {
}
