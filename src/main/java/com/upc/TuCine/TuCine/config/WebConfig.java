package com.upc.TuCine.TuCine.config;

/*
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.RedirectViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        RedirectViewControllerRegistration redirectViewControllerRegistration = registry.addRedirectViewController("/", "https://backend-tucine-production.up.railway.app/swagger-ui/index.html");
        redirectViewControllerRegistration.setKeepQueryParams(true);
        redirectViewControllerRegistration.setStatusCode(HttpStatusCode.valueOf(302));
    }
}
*/
