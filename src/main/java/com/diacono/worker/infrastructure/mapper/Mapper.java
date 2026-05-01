package com.diacono.worker.infrastructure.mapper;

import com.diacono.worker.domain.TokenGoogle;
import com.diacono.worker.infrastructure.adapters.database.entity.TokenGoogleEntity;

import java.util.List;

public class Mapper {

    public List<TokenGoogle> toListTokenGoogle(List<TokenGoogleEntity> tokenGoogleEntities) {
        return tokenGoogleEntities.stream()
                .map(this::toTokenGoogle)
                .toList();
    }

    public TokenGoogle toTokenGoogle(TokenGoogleEntity entity) {
        return new TokenGoogle(
                entity.getIdIgreja(),
                entity.getIdUsuario(),
                entity.getIdToken(),
                entity.getTokenRefresh(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
