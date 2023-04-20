package com.example.clientweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // установите размер базового пула потоков
        executor.setMaxPoolSize(50); // установите максимальный размер пула потоков
        executor.setQueueCapacity(100); // установите емкость очереди задач
        executor.setThreadNamePrefix("MyAsyncExecutor-"); // установите префикс имени потоков
        executor.initialize();
        return executor;
    }
}
