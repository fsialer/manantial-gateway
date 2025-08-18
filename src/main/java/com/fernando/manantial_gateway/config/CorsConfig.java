package com.fernando.manantial_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration generalCors = new CorsConfiguration();
        //generalCors.setAllowedOrigins(List.of("*"));
        generalCors.setAllowedOriginPatterns(List.of("*"));
        generalCors.setAllowedMethods(List.of("GET", "POST", "PUT","DELETE", "OPTIONS"));
        generalCors.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        generalCors.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", generalCors);

        return source;
    }
}
