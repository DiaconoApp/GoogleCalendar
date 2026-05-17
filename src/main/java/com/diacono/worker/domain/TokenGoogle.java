package com.diacono.worker.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class TokenGoogle {

    private final Long idToken;
    private final UUID membroId;
    private final String email;
    private final String tokenRefresh;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TokenGoogle(Long idToken, UUID membroId, String email, String tokenRefresh, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idToken = idToken;
        this.membroId = membroId;
        this.email = email;
        this.tokenRefresh = tokenRefresh;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getIdToken() {
        return idToken;
    }

    public UUID getMembroId() {
        return membroId;
    }

    public String getEmail() {
        return email;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public boolean temTokenRefresh(){
        return this.tokenRefresh != null && !this.tokenRefresh.isBlank();
    }

}
