package com.fpx.pay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/pay-api/**").permitAll()  // Allow payment API endpoints without auth
            .antMatchers("/h2-console/**").permitAll()  // Allow H2 console without auth
            .antMatchers("/payment/success").permitAll()  // Allow access to success page
            .anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .headers().frameOptions().disable()  // Required for H2 console
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
} 