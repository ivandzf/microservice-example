package org.ivandzf.microservice.produce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * mongo
 *
 * @author Divananda Zikry Fadilla (10 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Data
@Document(collection = "user")
public class User {

    @Id
    private String _id;
    private String name;
    private String email;
    private String phone;
    private String password;

}
