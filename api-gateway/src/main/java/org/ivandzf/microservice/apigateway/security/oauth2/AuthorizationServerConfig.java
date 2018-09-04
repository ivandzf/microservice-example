package org.ivandzf.microservice.apigateway.security.oauth2;

import com.zaxxer.hikari.HikariDataSource;
import org.ivandzf.microservice.apigateway.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (27 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final HikariDataSource hikariDataSource;
    private final TokenStore tokenStore;
    private final UserDetailsServiceImpl userDetailsService;
    private final DefaultTokenServices tokenService;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager,
                                     HikariDataSource hikariDataSource,
                                     UserDetailsServiceImpl userDetailsService,
                                     @Qualifier("redisTokenStore") TokenStore tokenStore,
                                     @Qualifier("customTokenService") DefaultTokenServices tokenService) {
        this.authenticationManager = authenticationManager;
        this.hikariDataSource = hikariDataSource;
        this.tokenStore = tokenStore;
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(hikariDataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenService)
                .tokenStore(tokenStore)
                .userDetailsService(userDetailsService);
    }

}
