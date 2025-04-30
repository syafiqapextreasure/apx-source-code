/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Bean
 *  org.springframework.security.config.annotation.web.builders.HttpSecurity
 *  org.springframework.security.web.DefaultSecurityFilterChain
 */
package com.wilmu.fpx;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class SecurityFilterChain {
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return (DefaultSecurityFilterChain)http.build();
    }
}
