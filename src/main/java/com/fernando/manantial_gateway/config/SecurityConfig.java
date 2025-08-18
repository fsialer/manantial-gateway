package com.fernando.manantial_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;



    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        ReactiveJwtAuthenticationConverter jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(new KeycloakRoleConverter()));
        return http
                .cors(Customizer.withDefaults())
                .authorizeExchange(auth -> auth
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .pathMatchers(
                                "/api/v1/customers/swagger-ui.html",
                                "/api/v1/customers/swagger-ui/**",
                                "/api/v1/customers/v3/api-docs",
                                "/actuator/**"
                        ).permitAll()
                        .pathMatchers(HttpMethod.GET,
                                "/api/v1/customers/**"
                        ).hasAnyAuthority("user","admin")
                        .pathMatchers(HttpMethod.DELETE,
                                "/api/v1/customers/{id}"
                        ).hasAnyAuthority("admin")
                        .pathMatchers(HttpMethod.PUT, "/api/v1/customers/{id}").hasAnyAuthority("admin","user")
                        .pathMatchers(HttpMethod.POST, "/api/v1/customers").hasAnyAuthority("admin","user")
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2->oauth2.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .build();
    }
}
