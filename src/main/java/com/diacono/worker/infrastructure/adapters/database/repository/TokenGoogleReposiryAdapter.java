package com.diacono.worker.infrastructure.adapters.database.repository;

import com.diacono.worker.application.port.out.TokenGoogleRepository;
import com.diacono.worker.domain.TokenGoogle;
import com.diacono.worker.infrastructure.adapters.database.entity.TokenGoogleEntity;
import com.diacono.worker.infrastructure.mapper.Mapper;

import java.util.List;
import java.util.UUID;

public class TokenGoogleReposiryAdapter implements TokenGoogleRepository {

    private final JpaTokenGoogleRepository jpaTokenGoogleRepository;
    private final Mapper mapper;

    public TokenGoogleReposiryAdapter(JpaTokenGoogleRepository jpaTokenGoogleRepository, Mapper mapper) {
        this.jpaTokenGoogleRepository = jpaTokenGoogleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TokenGoogle> findTokensRefreshByIdIgreja(UUID idIgreja) {

        List<TokenGoogleEntity> tokenGoogleEntities = jpaTokenGoogleRepository.findByIdIgreja(idIgreja);
        List<TokenGoogle> tokenGoogleList = mapper.toListTokenGoogle(tokenGoogleEntities);

        return tokenGoogleList;
    }
}
