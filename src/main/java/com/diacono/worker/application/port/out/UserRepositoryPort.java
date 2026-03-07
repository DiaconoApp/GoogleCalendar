package com.diacono.worker.application.port.out;

import com.diacono.worker.domain.User;
import java.util.List;

public interface UserRepositoryPort {

    List<User> findAllUserWithGoogleAuthTokenAndIgrejaIdIsEqualTo(Long igrejaId);

}
