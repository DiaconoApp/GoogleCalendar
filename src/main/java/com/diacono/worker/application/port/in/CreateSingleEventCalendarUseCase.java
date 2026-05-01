package com.diacono.worker.application.port.in;

import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.domain.TokenGoogle;

public interface CreateSingleEventCalendarUseCase {

    void execute(TokenGoogle tokenGoogle, EventInformationCommand eventInformationCommand);

}
