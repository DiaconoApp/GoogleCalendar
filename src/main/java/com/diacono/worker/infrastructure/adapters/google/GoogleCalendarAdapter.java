package com.diacono.worker.infrastructure.adapters.google;

import com.diacono.worker.application.exceptions.CalendarProviderException;
import com.diacono.worker.application.port.dto.command.EventWriterCommand;
import com.diacono.worker.application.port.out.CalendarWriterGateway;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.time.ZoneId;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

@Slf4j
public class GoogleCalendarAdapter implements CalendarWriterGateway{

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private static final String CALENDAR_ID = "primary";
    private static final String NAME_APP = "Diacono APP";
    private static final String TIME_ZONE = "America/Sao_Paulo";
    private static final String COLOR_ID = "8";

    @Override
    public void insertEvent(String accessToken, EventWriterCommand data) {

        try {

            GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                    .setApplicationName(NAME_APP)
                    .build();

            Event event = createEventObject(data);

            service.events().insert(CALENDAR_ID, event).execute();

            log.info("Evento '{}' inserido com sucesso via Google API.", data.summary());



        } catch (IOException e) {

            log.error("Erro ao comunicar com a API do Google Calendar para o resumo: {}", data.summary(), e);

            throw new CalendarProviderException("Falha ao persistir evento no Google Calendar", e);

        }

    }

    private Event createEventObject(EventWriterCommand data) {

        String descriptionFormatada = formatarParaGoogle(data);

        Event event = new Event()
                .setSummary(data.summary())
                .setDescription(descriptionFormatada)
                .setLocation(data.location())
                .setColorId(COLOR_ID);

        ZoneId zoneId = ZoneId.of(TIME_ZONE);
        long startMillis = data.start().atZone(zoneId).toInstant().toEpochMilli();

        event.setStart(new EventDateTime()
                .setDateTime(new DateTime(startMillis))
                .setTimeZone(TIME_ZONE));

        long endMillis = data.end().atZone(zoneId).toInstant().toEpochMilli();

        event.setEnd(new EventDateTime()
                .setDateTime(new DateTime(endMillis))
                .setTimeZone(TIME_ZONE));

        event.setReminders(new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(
                        new EventReminder().setMethod("email").setMinutes(10080),
                        new EventReminder().setMethod("popup").setMinutes(300),
                        new EventReminder().setMethod("popup").setMinutes(1440)
                )));
        return event;
    }

    private String formatarParaGoogle(EventWriterCommand data) {
        String custo = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(data.coast());

        return String.format("%s\n\nPublico alvo: %s\nCusto: %s",
                data.description(), data.targetPublic(), custo);
    }


}
