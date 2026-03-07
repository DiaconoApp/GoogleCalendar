package com.diacono.worker.domain;

import java.time.LocalDateTime;

public record SyncEvent(
        Long igrejaId,
        Long id,
        String titulo,
        String descricao,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim
) {
}
