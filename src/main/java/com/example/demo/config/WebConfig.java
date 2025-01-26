package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Alle Endpunkte, die mit "/api/" beginnen.
                .allowedOrigins("http://localhost:3000") // Frontend-URL.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Erlaubte HTTP-Methoden.
                .allowedHeaders("*") // Erlaubt alle Header.
                .allowCredentials(true); // Falls Cookies oder Auth verwendet werden.
    }
}
