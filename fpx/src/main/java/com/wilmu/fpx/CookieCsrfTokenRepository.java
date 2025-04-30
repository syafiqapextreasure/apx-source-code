/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.security.config.annotation.web.builders.HttpSecurity
 *  org.springframework.security.web.SecurityFilterChain
 *  org.springframework.security.web.csrf.CsrfTokenRepository
 */
package com.wilmu.fpx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;

public class CookieCsrfTokenRepository {
    public static CsrfTokenRepository withHttpOnlyFalse() {
        return null;
    }

    @Configuration
    public class SpringSecurityConfiguration {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            return (SecurityFilterChain)http.build();
        }
    }
}
