package com.PostBloging2.PostBloging.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                .requestMatchers("/api/v1/all").permitAll()
                .requestMatchers("api/v1/reviews/all/").permitAll()
                .requestMatchers("api/v1/reviews/addReview/").hasRole("user")
                .requestMatchers("api/v1/reviews/deleteReview/").hasRole("user")
                .requestMatchers("api/v1/reviews/**").hasRole("admin")
                .anyRequest().authenticated()
        ).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).oauth2ResourceServer(
                resource -> resource.jwt(
                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                keycloakAuthConverter()
                        )
                )
        ).build();

    }

    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloakAuthConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(
                new AuthoritiesConverter()
        );
        return converter;
    }

}
