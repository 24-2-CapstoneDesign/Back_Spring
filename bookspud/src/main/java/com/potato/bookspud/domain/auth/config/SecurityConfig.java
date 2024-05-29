package com.potato.bookspud.domain.auth.config;

import com.potato.bookspud.domain.auth.handler.CustomAccessDeniedHandler;
import com.potato.bookspud.domain.auth.handler.CustomJwtAuthenticationEntryPoint;
import com.potato.bookspud.domain.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {
            "/api/login/kakao"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // csrf 토큰 disable
                .formLogin(AbstractHttpConfigurer::disable) // form login 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // http 기본 인증 비활성화
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .exceptionHandling(exception ->
                {
                    exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                });

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(AUTH_WHITELIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
