package com.diacono.worker.application.usecase;

import com.diacono.worker.application.exceptions.NoTaskException;
import com.diacono.worker.application.exceptions.NoTokenFoundException;
import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.application.port.in.CreateSingleEventCalendarUseCase;
import com.diacono.worker.application.port.in.TokenOrchestratorUseCase;
import com.diacono.worker.application.port.out.AsyncManager;
import com.diacono.worker.application.port.out.TokenGoogleRepository;
import com.diacono.worker.domain.TokenGoogle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

//responsável por buscar token e distribuir pro use case correto.
public class TokenOrchestratorImpl implements TokenOrchestratorUseCase {

    private static final Logger log = LoggerFactory.getLogger(TokenOrchestratorImpl.class);

    private final TokenGoogleRepository tokenGoogleRepository;
    private final CreateSingleEventCalendarUseCase createSingleEventCalendar;
    private final AsyncManager asyncManager;

    public TokenOrchestratorImpl(TokenGoogleRepository tokenGoogleRepository, CreateSingleEventCalendarUseCase createSingleEventCalendar, AsyncManager asyncManager) {
        this.tokenGoogleRepository = tokenGoogleRepository;
        this.createSingleEventCalendar = createSingleEventCalendar;
        this.asyncManager = asyncManager;
    }

    @Override
    public void execute(EventInformationCommand eventInformationCommand) {

        List<TokenGoogle> tokenGoogleList = tokenGoogleRepository.findTokensRefreshByIdIgreja(eventInformationCommand.idIgreja());

        if(tokenGoogleList == null || tokenGoogleList.isEmpty()){
            log.info("Nenhum usuário com token cadastrado");
            return;
        }

        List<Runnable> tarefas = new ArrayList<>();

        for(TokenGoogle token : tokenGoogleList){
            if(token.temTokenRefresh()){
                tarefas.add(() -> createSingleEventCalendar.execute(token, eventInformationCommand));
            }
        }

        if(tarefas.isEmpty()){
            log.info("Nenhuma token válido para execução");
            return;
        }

        asyncManager.executeInParallel(tarefas);

    }

}
