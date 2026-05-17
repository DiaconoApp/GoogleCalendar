package com.diacono.worker.infrastructure.adapters.database.repository;

import com.diacono.worker.infrastructure.adapters.database.entity.TokenGoogleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenGoogleRepository extends JpaRepository<TokenGoogleEntity, Long> {

}
