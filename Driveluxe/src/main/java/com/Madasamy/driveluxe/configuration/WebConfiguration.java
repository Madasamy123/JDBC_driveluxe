package com.Madasamy.driveluxe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                //"http://127.0.0.1:5501",  // Admin Page
                                "http://127.0.0.1:5502",  // Website Page
                                //"http://localhost:5501", // Admin with localhost
                                "http://localhost:5502", // Website with localhost
                                "http://localhost:3000",  //  react
                                "http://localhost:5173" )  // vite
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");



            }


        };
    }
}





