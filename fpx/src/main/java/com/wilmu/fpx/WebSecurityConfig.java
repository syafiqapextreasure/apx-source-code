/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.security.config.annotation.web.builders.HttpSecurity
 *  org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
 *  org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
 */
package com.wilmu.fpx;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig
extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity httpSecurity) {
        throw new Error("Unresolved compilation problem: \n\tLambda expressions are allowed only at source level 1.8 or above\n");
    }
}
