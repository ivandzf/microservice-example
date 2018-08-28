package org.ivandzf.microservice.apigateway.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (27 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Configuration
public class DatabaseConfig {

    public static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static String JDBC_URL = "jdbc:mysql://localhost:3306/oauth_client";
    public static String JDBC_USERNAME = "root";
    public static String JDBC_PASSWORD = "";
    private static final String POOL_NAME = "oauth2-server";

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
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(hikariDataSource);

        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(HikariDataSource hikariDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(hikariDataSource);
        dataSourceTransactionManager.setRollbackOnCommitFailure(true);

        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate transactionTemplate(HikariDataSource hikariDataSource) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager(hikariDataSource));

        return transactionTemplate;
    }

    @Bean("customTokenStore")
    public TokenStore tokenStore(HikariDataSource hikariDataSource) {
        String insertAccessTokenSql = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";
        String selectAccessTokensFromUserNameAndClientIdSql = "select token_id, token from oauth_access_token where user_name = ? and client_id = ?";
        String selectAccessTokensFromUserNameSql = "select token_id, token from oauth_access_token where user_name = ?";
        String selectAccessTokensFromClientIdSql = "select token_id, token from oauth_access_token where client_id = ?";
        String insertRefreshTokenSql = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";

        JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(hikariDataSource);
        jdbcTokenStore.setInsertAccessTokenSql(insertAccessTokenSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(selectAccessTokensFromUserNameAndClientIdSql);
        jdbcTokenStore.setSelectAccessTokensFromUserNameSql(selectAccessTokensFromUserNameSql);
        jdbcTokenStore.setSelectAccessTokensFromClientIdSql(selectAccessTokensFromClientIdSql);
        jdbcTokenStore.setInsertRefreshTokenSql(insertRefreshTokenSql);


        return jdbcTokenStore;
    }

}
