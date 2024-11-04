package com.alligator.blog;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BlogWebAPI")
                        .version("1.0")
                        .description("This project empowers merchants to create and manage their own blog posts, enabling them to consistently maintain and update their content"));
    }
}
