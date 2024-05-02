package com.PostBloging2.PostBloging.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.authorizeRequests(auth -> auth
                .requestMatchers("/auth").permitAll()
                .requestMatchers("/api/v1/reviews/addReview/").hasRole("USER")
                .requestMatchers("/api/v1/reviews/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/v1/reviews/all/Review").hasRole("ADMIN")
                .requestMatchers("/api/v1/reviews/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/v1/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        ).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ).oauth2ResourceServer(
                resourse -> resourse.jwt(
                        jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
                                keycloackConverter()
                        )
                )
        ).build();






    }

    private Converter<Jwt,? extends AbstractAuthenticationToken> keycloackConverter() {

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
        return converter;
    }


}
