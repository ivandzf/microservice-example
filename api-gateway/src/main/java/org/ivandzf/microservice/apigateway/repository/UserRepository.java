package org.ivandzf.microservice.apigateway.repository;

import org.ivandzf.microservice.apigateway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (29 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);

}
