package org.ivandzf.microservice.produce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (13 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@EnableAsync
@Configuration
public class AsynchronousConfiguration {

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(500);
        executor.setMaxPoolSize(500);
        executor.setQueueCapacity(Integer.MAX_VALUE);
        executor.setThreadNamePrefix("mongo-");
        executor.initialize();
        executor.setKeepAliveSeconds(5);
        return executor;
    }

    @Bean
    public ConcurrentTaskExecutor concurrentTaskExecutor(Executor executor) {
        ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor();
        concurrentTaskExecutor.setConcurrentExecutor(executor);
        return concurrentTaskExecutor;
    }

}
