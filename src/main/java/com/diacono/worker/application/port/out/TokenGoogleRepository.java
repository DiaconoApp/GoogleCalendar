package com.diacono.worker.application.port.out;

import com.diacono.worker.domain.TokenGoogle;

import java.util.List;
import java.util.UUID;

public interface TokenGoogleRepository {

    public List<TokenGoogle> findTokensRefreshByIdIgreja(UUID idIgreja);

}
