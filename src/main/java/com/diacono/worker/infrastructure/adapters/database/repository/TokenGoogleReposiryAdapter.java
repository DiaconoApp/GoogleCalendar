package com.diacono.worker.infrastructure.adapters.database.repository;

import com.diacono.worker.application.port.out.TokenGoogleRepository;
import com.diacono.worker.domain.TokenGoogle;
import com.diacono.worker.infrastructure.adapters.database.entity.TokenGoogleEntity;
import com.diacono.worker.infrastructure.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TokenGoogleReposiryAdapter implements TokenGoogleRepository {

    private final JpaTokenGoogleRepository jpaTokenGoogleRepository;
    private final Mapper mapper;

    public TokenGoogleReposiryAdapter(JpaTokenGoogleRepository jpaTokenGoogleRepository, Mapper mapper) {
        this.jpaTokenGoogleRepository = jpaTokenGoogleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TokenGoogle> findAllTokensRefresh() {
        List<TokenGoogleEntity> tokenGoogleEntities = jpaTokenGoogleRepository.findAll();
        if(tokenGoogleEntities.isEmpty()) {
            Logger log = LoggerFactory.getLogger(TokenGoogleReposiryAdapter.class);
            log.warn("Nenhum token de atualização do Google encontrado no banco de dados.");
        }
        return mapper.toListTokenGoogle(tokenGoogleEntities);
    }
}
