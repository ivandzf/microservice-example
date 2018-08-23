package org.ivandzf.microservice.consume.dto;

import lombok.Data;

/**
 * user-service
 *
 * @author Divananda Zikry Fadilla (19 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Data
public class User {

    private String _id;
    private String name;
    private String email;
    private String phone;
    private String password;

}
