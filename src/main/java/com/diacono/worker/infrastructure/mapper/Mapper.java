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
                entity.getIdToken(),
                entity.getMembroId(),
                entity.getEmail(),
                entity.getTokenRefresh(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
