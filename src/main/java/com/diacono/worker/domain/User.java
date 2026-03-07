package com.diacono.worker.domain;

public record User(
        Long id,
        Long igrejaId,
        String email,
        String googleAuthToken
) {

    public boolean hasGoogleAuthToken() {
        return googleAuthToken != null && !googleAuthToken.isEmpty();
    }

}
