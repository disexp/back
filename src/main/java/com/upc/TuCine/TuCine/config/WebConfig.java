package com.upc.TuCine.TuCine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedOrigins("https://backend-tucine-production.up.railway.app/swagger-ui/index.html")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("")
                        .allowCredentials(true);
    }
}
