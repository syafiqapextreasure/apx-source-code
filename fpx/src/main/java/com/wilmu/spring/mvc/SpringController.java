/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.servlet.ModelAndView
 *  org.springframework.web.servlet.config.annotation.CorsRegistry
 *  org.springframework.web.servlet.config.annotation.EnableWebMvc
 *  org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
 */
package com.wilmu.spring.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class SpringController {
    @RequestMapping(value={"/Loading"})
    public String Loader() {
        return "include/Loader";
    }

    @RequestMapping(value={"/"})
    public String hello() {
        return "template/template";
    }

    @RequestMapping(value={"/template"})
    public String hi() {
        return "template/template";
    }

    @RequestMapping(value={"/Global"})
    public String Global(@RequestParam(value="type") String type, @RequestParam(value="name") String name) {
        System.out.println("include/shared/" + type + "/" + name);
        return "include/shared/" + type + "/" + name;
    }

    @RequestMapping(value={"/ajax"})
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", (Object)"Crunchify Spring MVC with Ajax and JQuery Demo..");
    }

    @RequestMapping(value={"Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @Configuration
    @EnableWebMvc
    public class WebConfig
    extends WebMvcConfigurerAdapter {
        public void addCorsMappings(CorsRegistry registry) {
            System.out.println("lieneeee 76");
            registry.addMapping("/**").allowedMethods(new String[]{"HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"});
        }
    }
}
