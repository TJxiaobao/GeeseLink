package com.message.job.dispatch;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.message.common.domin.MessageTaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WorkPool implements DisposableBean {

    private ThreadPoolExecutor workExecutor;

    public WorkPool() {
        initWorkExecutor();
    }

    private void initWorkExecutor() {
        // thread factory
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setUncaughtExceptionHandler((thread, throwable) -> {
                    log.error("[Important] WorkPool workExecutor has uncaughtException.", throwable);
                    log.error("Thread Name {} : {}", thread.getName(), throwable.getMessage(), throwable);
                })
                .setDaemon(true)
                .setNameFormat("send-work-%d")
                .build();
        workExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                1024,
                10,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    public void executeJob(Runnable runnable) throws RejectedExecutionException {
        workExecutor.execute(runnable);
    }

    public Future<MessageTaskInfo> submitJob(Callable<MessageTaskInfo> callable) throws RejectedExecutionException {
        return workExecutor.submit(callable);
    }

    @Override
    public void destroy() throws Exception {
        if (workExecutor != null) {
            workExecutor.shutdownNow();
        }
    }
}
