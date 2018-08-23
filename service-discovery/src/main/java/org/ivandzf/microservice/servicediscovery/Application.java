package org.ivandzf.microservice.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (23 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
