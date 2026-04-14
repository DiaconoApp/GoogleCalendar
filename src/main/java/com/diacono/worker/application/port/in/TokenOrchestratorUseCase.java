package com.diacono.worker.application.port.in;

import com.diacono.worker.application.port.dto.command.EventInformationCommand;

public interface TokenOrchestratorUseCase {

    public void execute(EventInformationCommand eventInformationCommand);

}
