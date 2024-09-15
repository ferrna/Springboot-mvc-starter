package com.expensestracker.expensestracker.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public OpenAPI customOpenAPI(@Value("V1") String ApiVersion){
        return new OpenAPI().info(new Info().title("Expense Tracker API")
                .version(ApiVersion)
                .description("Expense Tracker API Spring Boot with documentation OPENAPI & Swagger")
                .termsOfService("http://www.google.com")
                .license(new License().name("Apache 2.0").url("http://www.google.com")));
    }
}
