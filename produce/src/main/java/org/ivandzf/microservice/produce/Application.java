package org.ivandzf.microservice.produce;

import org.ivandzf.microservice.produce.model.User;
import org.ivandzf.microservice.produce.repository.UserRepository;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
@EnableEurekaClient
public class Application {

    private final UserRepository userRepository;

    public Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
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
