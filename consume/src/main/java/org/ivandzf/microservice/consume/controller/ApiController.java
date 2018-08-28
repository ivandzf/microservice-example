package org.ivandzf.microservice.consume.controller;

import org.ivandzf.microservice.consume.dto.User;
import org.ivandzf.microservice.consume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * user-service
 *
 * @author Divananda Zikry Fadilla (19 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Controller
public class ApiController {

    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAll() {
        return userService.getAllUser();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getallfeign", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllFeign() {
        return userService.getAllUserFeign();
    }

}
