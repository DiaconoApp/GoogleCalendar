package com.diacono.worker.infrastructure.config;

import com.diacono.worker.application.port.in.CreateSingleEventCalendarUseCase;
import com.diacono.worker.application.port.in.TokenOrchestratorUseCase;
import com.diacono.worker.application.port.out.AsyncManager;
import com.diacono.worker.application.port.out.CalendarWriterGateway;
import com.diacono.worker.application.port.out.GoogleAuthGateway;
import com.diacono.worker.application.port.out.TokenGoogleRepository;
import com.diacono.worker.application.usecase.CreateSingleEventCalendarImpl;
import com.diacono.worker.application.usecase.TokenOrchestratorImpl;
import com.diacono.worker.infrastructure.adapters.async.VirtualThreadAsyncAdapter;
import com.diacono.worker.infrastructure.adapters.database.repository.JpaTokenGoogleRepository;
import com.diacono.worker.infrastructure.adapters.database.repository.TokenGoogleReposiryAdapter;
import com.diacono.worker.infrastructure.adapters.google.AuthTokenGoogleAdapter;
import com.diacono.worker.infrastructure.adapters.google.GoogleCalendarAdapter;
import com.diacono.worker.infrastructure.mapper.Mapper;
import com.diacono.worker.infrastructure.messaging.RabbitMQConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

    @Bean
    public TokenGoogleRepository tokenGoogleRepository(JpaTokenGoogleRepository jpaTokenGoogleRepository, Mapper mapper) {
        return new TokenGoogleReposiryAdapter(jpaTokenGoogleRepository, mapper);
    }

    @Bean
    public GoogleAuthGateway googleAuthGateway() {
        return new AuthTokenGoogleAdapter();
    }

    @Bean
    public CalendarWriterGateway calendarWriterGateway() {
        return new GoogleCalendarAdapter();
    }

    @Bean
    public AsyncManager asyncManager() {
        return new VirtualThreadAsyncAdapter();
    }

    @Bean
    public CreateSingleEventCalendarUseCase createSingleEventCalendarUseCase(
            GoogleAuthGateway googleAuthGateway,
            CalendarWriterGateway calendarWriterGateway
    ) {
        return new CreateSingleEventCalendarImpl(googleAuthGateway, calendarWriterGateway);
    }

    @Bean
    public TokenOrchestratorUseCase tokenOrchestratorUseCase(
            TokenGoogleRepository tokenGoogleRepository,
            CreateSingleEventCalendarUseCase createSingleEventCalendarUseCase,
            AsyncManager asyncManager
    ) {
        return new TokenOrchestratorImpl(tokenGoogleRepository, createSingleEventCalendarUseCase, asyncManager);
    }

    @Bean
    public RabbitMQConsumer rabbitMQConsumer(TokenOrchestratorUseCase tokenOrchestratorUseCase) {
        return new RabbitMQConsumer(tokenOrchestratorUseCase);
    }

}
