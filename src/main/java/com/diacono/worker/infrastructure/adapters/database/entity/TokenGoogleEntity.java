package com.diacono.worker.infrastructure.adapters.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "token_google")
public class TokenGoogleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token")
    private Long idToken;

    @Column(name = "membro_id", nullable = false)
    private UUID membroId;

    @Column(nullable = false)
    private String email;

    @Column(name = "token_Refresh", nullable = false)
    private String tokenRefresh;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public TokenGoogleEntity(Long idToken, UUID membroId, String email, String tokenRefresh, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idToken = idToken;
        this.membroId = membroId;
        this.email = email;
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

    public UUID getMembroId() {
        return membroId;
    }

    public void setMembroId(UUID membroId) {
        this.membroId = membroId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
