package com.diacono.worker.infrastructure.adapters.google;

import com.diacono.worker.application.exceptions.AuthGatewayException;
import com.diacono.worker.application.port.out.GoogleAuthGateway;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthTokenGoogleAdapter implements GoogleAuthGateway {

    private static final Logger log = LoggerFactory.getLogger(AuthTokenGoogleAdapter.class);

    @Value("${client.id.google}")
    private String CLIENT_ID;
    @Value("${client.secret.google}")
    private String CLIENT_SECRET;

    @Override
    public String getAccessToken(String refreshToken) {

        try {
            GoogleTokenResponse response = new GoogleRefreshTokenRequest(
                    new NetHttpTransport(),
                    new GsonFactory(),
                    refreshToken,
                    CLIENT_ID,
                    CLIENT_SECRET)
                    .execute();

            return response.getAccessToken();

        } catch (IOException e) {
            log.error("Falha de I/O ao tentar renovar o token no Google Auth.", e);
            throw new AuthGatewayException("Não foi possível gerar o access token a partir do refresh token.", e);
        }


    }
}
