package com.agenciacapsula.infraestrutura.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF, necessário para aceitar POST/PUT/DELETE de clientes externos (Postman/Curl)
                .csrf(AbstractHttpConfigurer::disable)

                // Permite qualquer requisição para qualquer endpoint sem autenticação
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Desabilita o frameOptions para permitir consoles como o H2 (se vier a usar)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }
}