package org.ivandzf.microservice.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * example-microservice
 *
 * @author Divananda Zikry Fadilla (28 August 2018)
 * Email: divanandazf@gmail.com
 * <p>
 * Documentation here !!
 */
@Primary
@Configuration
@EnableSwagger2
public class SwaggerConfig implements SwaggerResourcesProvider {

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder
                .builder()
                .showExtensions(true)
                .docExpansion(DocExpansion.NONE)
                .defaultModelRendering(ModelRendering.MODEL)
                .build();
    }

    @Override
    public List<SwaggerResource> get() {
        return Arrays.asList(
                swaggerResource("user-service", "/api/user-service/v2/api-docs")
        );
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion("2.0");
        return resource;
    }

}
