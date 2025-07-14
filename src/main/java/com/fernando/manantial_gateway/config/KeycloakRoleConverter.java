package com.fernando.manantial_gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<String> roles = new ArrayList<>();

        // Obtener roles de realm_access
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles.addAll((Collection<String>) realmAccess.get("roles"));
        }

        // Si usas roles a nivel de cliente:
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            Map<String, Object> client = (Map<String, Object>) resourceAccess.get(System.getenv("CLIENT_ID"));
            if (client != null && client.containsKey("roles")) {
                roles.addAll((Collection<String>) client.get("roles"));
            }
        }

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role)) // o "ROLE_" + role
                .collect(Collectors.toSet());
    }
}
