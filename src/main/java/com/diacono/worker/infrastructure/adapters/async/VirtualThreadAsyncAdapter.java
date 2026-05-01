package com.diacono.worker.infrastructure.adapters.async;

import com.diacono.worker.application.port.out.AsyncManager;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class VirtualThreadAsyncAdapter implements AsyncManager {

    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();
    private final Semaphore semaphore = new Semaphore(10);

    @Override
    public void executeInParallel(List<Runnable> tasks) {
        List<CompletableFuture<Void>> futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(() -> {
                    try {
                        semaphore.acquire();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        semaphore.release();
                    }
                }, executor))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}