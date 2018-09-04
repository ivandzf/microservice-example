package org.ivandzf.microservice.apigateway.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (27 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.ivandzf.microservice.apigateway.repository")
public class DatabaseConfig {

    private static final String POOL_NAME = "oauth2-server";
    public static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/oauth_client";
    public static String JDBC_USERNAME = "root";
    public static String JDBC_PASSWORD = "";

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(JDBC_DRIVER);
        hikariDataSource.setJdbcUrl(JDBC_URL);
        hikariDataSource.setUsername(JDBC_USERNAME);
        hikariDataSource.setPassword(JDBC_PASSWORD);
        hikariDataSource.setPoolName(POOL_NAME);
        hikariDataSource.setAutoCommit(false);
        hikariDataSource.setAllowPoolSuspension(true);
        hikariDataSource.setMinimumIdle(0);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setIdleTimeout(10000);
        hikariDataSource.setConnectionTimeout(60000);
        hikariDataSource.setValidationTimeout(5000);
        hikariDataSource.setMaxLifetime(30000);

        return hikariDataSource;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

}
