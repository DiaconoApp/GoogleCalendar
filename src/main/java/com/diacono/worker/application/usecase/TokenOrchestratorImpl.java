package com.diacono.worker.application.usecase;

import com.diacono.worker.application.port.dto.command.EventInformationCommand;
import com.diacono.worker.application.port.in.CreateSingleEventCalendarUseCase;
import com.diacono.worker.application.port.in.TokenOrchestratorUseCase;
import com.diacono.worker.application.port.out.TokenGoogleRepository;
import com.diacono.worker.domain.TokenGoogle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//responsável por buscar token e distribuir pro use case correto.
public class TokenOrchestratorImpl implements TokenOrchestratorUseCase {

    private final TokenGoogleRepository tokenGoogleRepository;
    private final CreateSingleEventCalendarUseCase createSingleEventCalendar;
    private final Executor virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();

    public TokenOrchestratorImpl(TokenGoogleRepository tokenGoogleRepository, CreateSingleEventCalendarUseCase createSingleEventCalendar) {
        this.tokenGoogleRepository = tokenGoogleRepository;
        this.createSingleEventCalendar = createSingleEventCalendar;
    }

    @Override
    public void execute(EventInformationCommand eventInformationCommand) {

        List<TokenGoogle> tokenGoogleList = tokenGoogleRepository.findTokensRefreshByIdIgreja(eventInformationCommand.idIgreja());
        //2 - filtra os tokens, para ver quais são os válidos

        if(tokenGoogleList == null || tokenGoogleList.isEmpty()){
            //lançar erro
        }

        List<CompletableFuture<Void>> tarefasAtivas = new ArrayList<>();

        for(TokenGoogle token : tokenGoogleList){
            if(token.temTokenRefresh()){
                //chamar a classe que cria o evento para um determinado usuário -> precisa ser thread pq são mais de 300 usuarios
                CompletableFuture<Void> tarefa = CompletableFuture.runAsync(() -> {
                    createSingleEventCalendar.execute(token, eventInformationCommand);
                }, virtualExecutor);

                tarefasAtivas.add(tarefa);
            }else{
                continue;
            }
        }

        if (tarefasAtivas.isEmpty()) {
            return;
        }

        CompletableFuture.allOf(tarefasAtivas.toArray(new CompletableFuture[0])).join();

    }

}
