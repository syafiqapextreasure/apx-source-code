/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.FilterChain
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.logging.Log
 *  org.apache.commons.logging.LogFactory
 *  org.springframework.web.filter.OncePerRequestFilter
 */
package com.wilmu.fpx;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class CORSFilter
extends OncePerRequestFilter {
    private static final Log LOG = LogFactory.getLog(CORSFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("libe 21 " + request.getMethod());
        System.out.println("libe 38 " + request.getHeader("Access-Control-Request-Method"));
        System.out.println("libe 23 " + "OPTIONS".equals(request.getMethod()));
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            System.out.println("libe 25 " + request.getHeader("Access-Control-Request-Method"));
            LOG.trace((Object)"Sending Header....");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.addHeader("Access-Control-Max-Age", "3600");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Authorization, refresh-token");
            response.addHeader("Access-Control-Allow-Credentials", "false");
        }
        filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
    }
}
