package org.ivandzf.microservice.apigateway;

import org.ivandzf.microservice.apigateway.entity.User;
import org.ivandzf.microservice.apigateway.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (29 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserTest() {
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            Assert.assertNotNull(user.getUsername());
        });
    }

    @Test
    public void findUserByUsernameTest() {
        User user = userRepository.findByUsername("admin");

        Assert.assertNotNull(user.getUsername());
    }

}
