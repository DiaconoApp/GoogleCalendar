package com.diacono.worker.application.port.out;

public interface GoogleAuthGateway {

        String getAccessToken(String refreshToken);

}
