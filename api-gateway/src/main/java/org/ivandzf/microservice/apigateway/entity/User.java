package org.ivandzf.microservice.apigateway.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Collections;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (29 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Data
@Entity
public class User implements UserDetails {

    @Id
    private String username;
    private String password;
    private String role;
    private int accountExpired;
    private int accountLocked;
    private int enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired == 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 0;
    }

}
