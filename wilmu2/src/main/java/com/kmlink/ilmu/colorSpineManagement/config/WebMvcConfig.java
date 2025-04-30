/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.context.annotation.Bean
 *  org.springframework.core.io.DefaultResourceLoader
 *  org.springframework.core.io.ResourceLoader
 *  org.springframework.jdbc.datasource.DriverManagerDataSource
 *  org.springframework.web.servlet.view.InternalResourceViewResolver
 */
package com.kmlink.ilmu.colorSpineManagement.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class WebMvcConfig {
    @Autowired
    ResourceLoader resourceLoader;

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Autowired
    public void resourceloadfile(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    DriverManagerDataSource getDataSource() throws IOException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        DefaultResourceLoader loader = new DefaultResourceLoader();
        return ds;
    }
}
