package org.ivandzf.microservice.produce.repository;

import org.ivandzf.microservice.produce.model.User;
import org.ivandzf.microservice.produce.repository.custom.CustomUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (10 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
}
