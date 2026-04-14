package com.diacono.worker.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class TokenGoogle {

    private final UUID idGreja;
    private final UUID idUsuario;
    private final Long idToken;
    private final String tokenRefresh;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TokenGoogle(UUID idGreja, UUID idUsuario, Long idToken, String tokenRefresh, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idGreja = idGreja;
        this.idUsuario = idUsuario;
        this.idToken = idToken;
        this.tokenRefresh = tokenRefresh;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean temTokenRefresh(){
        return this.tokenRefresh != null && !this.tokenRefresh.isBlank();
    }

}
