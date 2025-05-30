package com.fsse2212_v2.eshop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                 // hacking protection
                .authorizeHttpRequests()
                .requestMatchers("/public/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()//log in to do
                .csrf().disable().cors();


        // Set and validate the issuer
        http
                .oauth2ResourceServer(
                        oauth2ResourceServer -> oauth2ResourceServer.jwt( //lambda -> nameless method
                                jwt -> jwt.decoder( // decode the jwt to check the issuer if the same
                                        JwtDecoders.fromIssuerLocation(issuer))
                        )
                );
        return http.build();
    }
}
