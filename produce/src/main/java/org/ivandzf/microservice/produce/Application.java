package org.ivandzf.microservice.produce;

import org.ivandzf.microservice.produce.model.User;
import org.ivandzf.microservice.produce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class Application {

    private final UserRepository userRepository;

    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        userRepository.deleteAll();

        User userAdmin = new User();
        userAdmin.setName("admin");
        userAdmin.setEmail("contact@admin.com");
        userAdmin.setPhone("-");
        userAdmin.setPassword("123123");

        User user = new User();
        user.setName("user");
        user.setEmail("contact@user.com");
        user.setPhone("-");
        user.setPassword("123123");

        List<User> users = new ArrayList<>();
        users.add(userAdmin);
        users.add(user);

        userRepository.insert(users);
    }

}
