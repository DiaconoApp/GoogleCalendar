package com.diacono.worker.application.port.out;

import com.diacono.worker.application.port.dto.command.EventWriterCommand;

public interface CalendarWriterGateway {

    void insertEvent(String acessToken, EventWriterCommand data);

}
