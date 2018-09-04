package org.ivandzf.microservice.apigateway.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (29 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Component
public class TokenFactory implements TokenEnhancer {

    private final ClientDetailsService clientDetailsService;

    private List<TokenEnhancer> delegates = Collections.emptyList();

    public TokenFactory(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void setTokenEnhancers(List<TokenEnhancer> delegates) {
        this.delegates = delegates;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) oAuth2AccessToken;

        final Map<String, Object> additionalInformation = new HashMap<>();

        if (oAuth2Authentication.getOAuth2Request().getRequestParameters().get("grant_type").equals("password")) {
            additionalInformation.put("user", oAuth2Authentication.getUserAuthentication().getPrincipal());
        }

        tempResult.setAdditionalInformation(additionalInformation);

        OAuth2AccessToken result = tempResult;
        for (TokenEnhancer enhancer : delegates) {
            result = enhancer.enhance(result, oAuth2Authentication);
        }

        return result;
    }

    @Primary
    @Bean("customTokenService")
    public DefaultTokenServices tokenService(@Qualifier("redisTokenStore") TokenStore tokenStore,
                                             TokenFactory customToken) {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(customToken);
        defaultTokenServices.setClientDetailsService(clientDetailsService);

        return defaultTokenServices;
    }

    @Bean("redisTokenStore")
    public TokenStore tokenStoreRedis(JedisConnectionFactory jedisConnectionFactory) {
        return new RedisTokenStore(jedisConnectionFactory);
    }

}
