/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.web.servlet.config.annotation.CorsRegistry
 *  org.springframework.web.servlet.config.annotation.EnableWebMvc
 *  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 */
package com.wilmu.spring.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class SpringController$WebConfig
extends WebMvcConfigurerAdapter {
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("lieneeee 76");
        registry.addMapping("/**").allowedMethods(new String[]{"HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"});
    }
}
