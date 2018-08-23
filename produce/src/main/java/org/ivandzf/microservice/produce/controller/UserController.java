package org.ivandzf.microservice.produce.controller;

import org.ivandzf.microservice.produce.model.User;
import org.ivandzf.microservice.produce.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (10 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getall")
    public List<User> getAllUser() {
        System.out.println("incoming request");
        return userRepository.findAll();
    }

    @GetMapping("/getallasync")
    public Callable<Future<List<User>>> getAllUserAsync() {
        return userRepository::findAllUsers;
    }

}
