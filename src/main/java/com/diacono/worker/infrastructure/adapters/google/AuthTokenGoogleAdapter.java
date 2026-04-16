package com.diacono.worker.infrastructure.adapters.google;

import com.diacono.worker.application.exceptions.AuthGatewayException;
import com.diacono.worker.application.port.out.GoogleAuthGateway;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class AuthTokenGoogleAdapter implements GoogleAuthGateway {

    private static final String CLIENT_ID = "client_id_diacono";
    private static final String CLIENT_SECRET = "client_secret_diacono";

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
