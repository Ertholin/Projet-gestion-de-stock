package com.ertho.gestiondestosck.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ertho.gestiondestosck.utils.Constants.APP_ROOT;

@Configuration
public class SwaggerConfiguration {


    @Bean
    public OpenAPI customOpenAPI(@Value("Gestion de stock API documentation") String appDesciption,
                                 @Value("REST API V1") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion de Stock REST API")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
