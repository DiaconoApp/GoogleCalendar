package com.diacono.worker.application.port.out;

import com.diacono.worker.domain.TokenGoogle;

import java.util.List;

public interface TokenGoogleRepository {

    List<TokenGoogle> findAllTokensRefresh();

}
