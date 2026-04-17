package com.diacono.worker.infrastructure.messaging.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Valid
public record EventDTO(

        @NotNull
        UUID idEvento,
        @NotNull
        UUID idIgreja,
        @NotBlank
        String name,
        @NotBlank
        String targetPublic,
        @NotNull
        @FutureOrPresent
        LocalDateTime startEvent,
        @NotNull
        @FutureOrPresent
        LocalDateTime endEventCommand,
        @NotBlank
        String description,
        @NotNull
        @DecimalMin(value="0.0", inclusive = true)
        BigDecimal coast,
        @NotBlank
        String location
) {
}
