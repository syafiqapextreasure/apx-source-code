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

    @RequestMapping(value={"/GeneratePreviewDocument"}, method={RequestMethod.POST})
    public String GeneratePreviewDocument() {
        System.out.println("GeneratePreviewDocument");
        return "include/shared/GeneratePreviewDocument";
    }

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal() {
        return "include/shared/RetrieveTitleModal";
    }

    @RequestMapping(value={"/RetrieveSearchTitle"}, method={RequestMethod.GET})
    public String RetrieveSearchTitle1() {
        return "include/shared/RetrieveSearchTitle";
    }

    @RequestMapping(value={"/ViewTitles"}, method={RequestMethod.GET})
    public String ViewTitles1() {
        return "include/shared/ViewTitles";
    }

    @RequestMapping(value={"/Handler_Add"}, method={RequestMethod.POST})
    public String Handler_Add() {
        System.out.println("Handler_Add");
        return "module/ILL/01_IncomingRequest/Handler_Add";
    }

    @RequestMapping(value={"/Handler_Edit"}, method={RequestMethod.GET})
    public String Handler_Edit() {
        return "module/ILL/01_IncomingRequest/Handler_Edit";
    }

    @RequestMapping(value={"/Handler_Delete"}, method={RequestMethod.GET})
    public String Handler_Delete() {
        return "module/ILL/01_IncomingRequest/Handler_Delete";
    }

    @RequestMapping(value={"/Handler_Patron"}, method={RequestMethod.GET})
    public String Handler_Patron() {
        return "module/ILL/01_IncomingRequest/Handler_Patron";
    }

    @RequestMapping(value={"/clickreturnILL"}, method={RequestMethod.GET})
    public String clickreturnILL() {
        return "module/ILL/05_ReturnIll/clickreturnILL";
    }

    @RequestMapping(value={"/Handler_DeleteOutgoing"}, method={RequestMethod.GET})
    public String Handler_DeleteOutgoing() {
        return "module/ILL/03_OutgoingReq/Handler_DeleteOutgoing";
    }
}
