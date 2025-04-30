/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.servlet.ModelAndView
 */
package com.ilmu.global;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class Log4j {
    private static final Logger logger = Logger.getLogger((String)Log4j.class.getName());

    @RequestMapping(value={"/"}, method={RequestMethod.GET})
    public ModelAndView getWelcome() {
        if (logger.isDebugEnabled()) {
            logger.debug((Object)"getWelcome is executed!");
        }
        logger.error((Object)"This is Error message", (Throwable)new Exception("Testing"));
        ModelAndView model = new ModelAndView("welcome");
        model.addObject("msg", (Object)"Hello Spring MVC + Log4j");
        return model;
    }
}
