package com.diacono.worker.application.port.out;

import java.util.List;

public interface AsyncManager {

    void executeInParallel(List<Runnable> tasks);

}
