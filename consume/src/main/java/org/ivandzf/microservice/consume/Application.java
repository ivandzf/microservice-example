package org.ivandzf.microservice.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * user-service
 *
 * @author Divananda Zikry Fadilla (19 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
