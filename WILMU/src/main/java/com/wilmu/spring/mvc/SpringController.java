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

    @RequestMapping(value={"/Global"})
    public String Global(@RequestParam(value="type") String type, @RequestParam(value="name") String name) {
        System.out.println("include/shared/" + type + "/" + name);
        return "include/shared/" + type + "/" + name;
    }

    @RequestMapping(value={"/Acquisition"})
    public String Acquisition_Maintenance(@RequestParam(value="type") String type, @RequestParam(value="module") String module, @RequestParam(value="name") String name) {
        return "module/Acquisition/" + type + "/" + module + "/" + name;
    }

    @RequestMapping(value={"/Reserve_Collection"})
    public String ReserveCollection(@RequestParam(value="type") String type, @RequestParam(value="module") String module, @RequestParam(value="name") String name) {
        return "module/Reserve_Collection/" + type + "/" + module + "/" + name;
    }

    @RequestMapping(value={"/Routing"})
    public String Routing(@RequestParam(value="type") String type, @RequestParam(value="module") String module, @RequestParam(value="name") String name) {
        return "module/Routing/" + type + "/" + module + "/" + name;
    }

    @RequestMapping(value={"Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        System.out.println("sdsddsda");
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @RequestMapping(value={"Modal_Vendor"}, method={RequestMethod.GET})
    public String Modal_Vendor() {
        return "include/shared/Modal/Modal_Vendor";
    }

    @RequestMapping(value={"SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "include/shared/SearchVendor";
    }

    @RequestMapping(value={"Modal_BibSearch"}, method={RequestMethod.GET})
    public String Modal_Modal_BibSearch() {
        return "include/shared/Modal/Modal_BibSearch";
    }
}
