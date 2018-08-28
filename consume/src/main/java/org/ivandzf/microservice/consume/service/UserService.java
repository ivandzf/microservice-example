package org.ivandzf.microservice.consume.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.ivandzf.microservice.consume.api.UserApi;
import org.ivandzf.microservice.consume.dto.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * user-service
 *
 * @author Divananda Zikry Fadilla (19 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final UserApi userApi;

    public UserService(RestTemplate restTemplate, UserApi userApi) {
        this.restTemplate = restTemplate;
        this.userApi = userApi;
    }

    @Cacheable(cacheNames = "userCache")
    @HystrixCommand(fallbackMethod = "fallbackGetAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        return restTemplate.exchange(
                "http://user-service-produce/getall",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );
    }

    public ResponseEntity<List<User>> fallbackGetAllUser() {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Cacheable(cacheNames = "userCache")
    @HystrixCommand(fallbackMethod = "fallbackGetAllUser")
    public ResponseEntity<List<User>> getAllUserFeign() {
        return new ResponseEntity<>(userApi.getAllUser(), HttpStatus.OK);
    }

}
