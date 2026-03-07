package com.diacono.worker.application.port.out;

import com.diacono.worker.domain.SyncEvent;
import com.diacono.worker.domain.User;

import java.util.List;

public interface CalendarProviderPort {

    void syncEvent(List<User> users, SyncEvent syncEvent);

}

