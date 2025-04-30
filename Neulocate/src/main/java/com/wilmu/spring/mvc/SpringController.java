/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.servlet.ModelAndView
 */
package com.wilmu.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value={"/ajax"})
    public ModelAndView helloAjaxTest() {
        return new ModelAndView("ajax", "message", (Object)"Crunchify Spring MVC with Ajax and JQuery Demo..");
    }

    @RequestMapping(value={"Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_AddResv() {
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @RequestMapping(value={"NeuLocate/Inventory_Check"})
    public String Inventory_Check(@RequestParam(value="actionID") String actionID) {
        System.out.println("SMS" + actionID);
        return "module/NeuLocate/01_Inventory_Check/" + actionID;
    }

    @RequestMapping(value={"NeuLocate/StockTake"})
    public String StockTake(@RequestParam(value="actionID") String actionID) {
        System.out.println("SMS" + actionID);
        return "module/NeuLocate/05_StockTake/" + actionID;
    }

    @RequestMapping(value={"/RecordRetrieve"})
    public String RecordRetrieve(@RequestParam(value="actionID") String actionID) {
        System.out.println("SMS" + actionID);
        return "module/NeuLocate/01_Inventory_Check/RecordRetrieve";
    }
}
