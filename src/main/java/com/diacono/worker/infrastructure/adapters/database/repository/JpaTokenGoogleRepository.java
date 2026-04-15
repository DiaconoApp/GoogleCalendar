package com.diacono.worker.infrastructure.adapters.database.repository;

import com.diacono.worker.infrastructure.adapters.database.entity.TokenGoogleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTokenGoogleRepository extends JpaRepository<TokenGoogleEntity, Long> {

        List<TokenGoogleEntity> findByIdIgreja(UUID idIgreja);

}
