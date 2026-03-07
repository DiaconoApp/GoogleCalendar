package com.diacono.worker.application.usecase;

import com.diacono.worker.application.port.out.CalendarProviderPort;
import com.diacono.worker.application.port.out.UserRepositoryPort;
import com.diacono.worker.domain.SyncEvent;
import com.diacono.worker.domain.User;

import java.util.List;

public class GoogleCalendarUseCase {

    private UserRepositoryPort user;
    private CalendarProviderPort calendar;

    public GoogleCalendarUseCase(UserRepositoryPort user, CalendarProviderPort calendar) {
        this.user = user;
        this.calendar = calendar;
    }

    public void execute(SyncEvent event){
        List<User> users = user.findAllUserWithGoogleAuthTokenAndIgrejaIdIsEqualTo(event.igrejaId());
        if(!users.isEmpty()) {
            calendar.syncEvent(users, event);
        }
    }

}
