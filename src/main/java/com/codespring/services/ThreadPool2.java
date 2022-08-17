package com.codespring.services;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Component
public class ThreadPool2 {
    void executeTask(MyRunnable myRunnable) {
        int corePoolSize = 5;
        int maximumPoolSize = 5;
        long keepAliveSizee = 0L;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();

        // Khai báo 1 Thread Pool thông qua ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize, keepAliveSizee, unit, workQueue);
        executor.execute(myRunnable);
    }
}
//