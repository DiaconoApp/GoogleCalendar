package com.diacono.worker.infrastructure.adapters.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "token_google")
public class TokenGoogleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idToken;
    private UUID idIgreja;
    private UUID idUsuario;
    private String tokenRefresh;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TokenGoogleEntity(Long idToken, UUID idIgreja, UUID idUsuario, String tokenRefresh, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idToken = idToken;
        this.idIgreja = idIgreja;
        this.idUsuario = idUsuario;
        this.tokenRefresh = tokenRefresh;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TokenGoogleEntity() {
    }

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }

    public UUID getIdIgreja() {
        return idIgreja;
    }

    public void setIdIgreja(UUID idIgreja) {
        this.idIgreja = idIgreja;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
