package org.ivandzf.microservice.consume.controller;

import lombok.AllArgsConstructor;
import org.ivandzf.microservice.consume.service.UserService;
import org.ivandzf.microservice.consume.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * user-service
 *
 * @author Divananda Zikry Fadilla (19 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@RestController
@AllArgsConstructor
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAll() {
        return userService.getAllUser();
    }

}
