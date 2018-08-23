package org.ivandzf.microservice.produce.repository.custom;

import org.ivandzf.microservice.produce.model.User;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (13 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public interface CustomUserRepository {

    @Async
    CompletableFuture<List<User>> findAllUsers();

}
