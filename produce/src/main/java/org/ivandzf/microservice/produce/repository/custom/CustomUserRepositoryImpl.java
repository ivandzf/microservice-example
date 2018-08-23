package org.ivandzf.microservice.produce.repository.custom;

import org.ivandzf.microservice.produce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (13 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CompletableFuture<List<User>> findAllUsers() {
        Query query = new Query();
        return CompletableFuture.completedFuture(mongoTemplate.find(query, User.class));
    }

}
