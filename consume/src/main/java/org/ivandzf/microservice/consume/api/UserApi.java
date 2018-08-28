package org.ivandzf.microservice.consume.api;

import org.ivandzf.microservice.consume.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (28 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Service
@FeignClient("user-service-produce")
public interface UserApi {

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    List<User> getAllUser();

}
