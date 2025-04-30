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
package com.ilmu.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringController {
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

    @RequestMapping(value={"/include"})
    public String method1(@RequestParam(value="url") String url) {
        System.out.println(url);
        return "include/shared/" + url;
    }

    @RequestMapping(value={"/Handler"})
    public String Handler() {
        return "include/shared/Handler";
    }

    @RequestMapping(value={"/Error_Page"})
    public String Error_Page() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"/Loading"})
    public String Loader() {
        return "include/shared/Loader";
    }

    @RequestMapping(value={"/Modal_Vendor"}, method={RequestMethod.GET})
    public String Modal_Vendor() {
        System.out.println("Modal_Vendor");
        return "include/shared/Modal_Vendor";
    }

    @RequestMapping(value={"/SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "include/shared/SearchVendor";
    }

    @RequestMapping(value={"/SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @RequestMapping(value={"/Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"/Handler_SaveReply"}, method={RequestMethod.POST})
    public String Handler_SaveReply() {
        return "module/enquiry/09_SuggestionBox/Handler_SaveReply";
    }

    @RequestMapping(value={"/Handler_SuggBox"}, method={RequestMethod.GET})
    public String Handler_SuggBox() {
        return "module/enquiry/09_SuggestionBox/Handler_SuggBox";
    }

    @RequestMapping(value={"/GeneratePreviewDocumentEnquiry"}, method={RequestMethod.POST})
    public String GeneratePreviewDocumentEnquiry() {
        return "include/shared/GeneratePreviewDocumentEnquiry";
    }

    @RequestMapping(value={"/privewIncome"}, method={RequestMethod.POST})
    public String privewIncome() {
        return "module/enquiry/11_IncomeDetails/privewIncome";
    }
}
