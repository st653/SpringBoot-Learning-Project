package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Alle Endpunkte.
                .allowedOrigins("http://localhost:63342") // Frontend-URL.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE") // Erlaubte HTTP-Methoden.
                .allowedHeaders("*") // Erlaubt alle Header.
                .allowCredentials(true); // Falls Cookies oder Auth verwendet werden.
    }
}
