package com.PostBloging2.PostBloging.Security;


//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers("/auth").permitAll()
//                                .requestMatchers("/api/all").hasRole("ADMIN")
//                                .requestMatchers("/api/v1/posts/**").hasRole("ADMIN")
//                                .requestMatchers("/api/v1/reviews/**").hasRole("ADMIN")
//                                .requestMatchers("/addReview/{postId}").hasRole("USER")
//                                .anyRequest().authenticated()
//                ).sessionManagement(
//                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).oauth2ResourceServer(
//                        resourceServer -> resourceServer.jwt(
//                                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(
//                                       keycloakAuthConverter()
//                                )
//                        )
//                )
//                .build();
//    }
//
//    private Converter<org.springframework.security.oauth2.jwt.Jwt, ? extends AbstractAuthenticationToken> keycloakAuthConverter() {
//        return new JwtGrantedAuthoritiesConverter();
//    }
//
//
//}