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

    @RequestMapping(value={"/Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        System.out.println("qwerty");
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"/SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
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

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal() {
        return "include/shared/RetrieveTitleModal";
    }

    @RequestMapping(value={"/RetrieveSearchTitle"}, method={RequestMethod.GET})
    public String RetrieveSearchTitle1() {
        return "include/shared/RetrieveSearchTitle";
    }

    @RequestMapping(value={"/Modal_OfficerID"}, method={RequestMethod.GET})
    public String Modal_OfficerID() {
        return "include/shared/Modal_OfficerID";
    }

    @RequestMapping(value={"/ViewTitles"}, method={RequestMethod.GET})
    public String ViewTitles1() {
        return "include/shared/ViewTitles";
    }

    @RequestMapping(value={"/GeneratePreviewDocument"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument() {
        return "include/shared/GeneratePreviewDocument";
    }

    @RequestMapping(value={"/Handler_AddMonograph"}, method={RequestMethod.GET})
    public String Handler_AddMonograph() {
        System.out.println("Handler_AddMonograph");
        return "module/binding/01_Monograph_Binding_Maintenance/Handler_AddMonograph";
    }

    @RequestMapping(value={"/Handler_EditMonograph"}, method={RequestMethod.GET})
    public String Handler_EditMonograph() {
        System.out.println("Handler_EditMonograph");
        return "module/binding/01_Monograph_Binding_Maintenance/Handler_EditMonograph";
    }

    @RequestMapping(value={"/Handler_DeleteMono"}, method={RequestMethod.GET})
    public String Handler_DeleteMono() {
        System.out.println("Handler_DeleteMono");
        return "module/binding/01_Monograph_Binding_Maintenance/Handler_DeleteMono";
    }

    @RequestMapping(value={"/Handler_EditSer"}, method={RequestMethod.GET})
    public String Handler_EditSer() {
        System.out.println("Handler_EditSer");
        return "module/binding/02_Serials_Binding_Maintenance/Handler_EditSer";
    }

    @RequestMapping(value={"/Handler_AddSer"}, method={RequestMethod.GET})
    public String Handler_AddSer() {
        System.out.println("Handler_AddSer");
        return "module/binding/02_Serials_Binding_Maintenance/Handler_AddSer";
    }

    @RequestMapping(value={"/Handler_DeleteSer"}, method={RequestMethod.GET})
    public String Handler_DeleteSer() {
        System.out.println("Handler_DeleteSer");
        return "module/binding/02_Serials_Binding_Maintenance/Handler_DeleteSer";
    }

    @RequestMapping(value={"/claimBinding"}, method={RequestMethod.GET})
    public String claimBinding() {
        System.out.println("claimBinding");
        return "module/binding/03_Bindery_Claims/claimBinding";
    }

    @RequestMapping(value={"/CheckInMonograph"}, method={RequestMethod.GET})
    public String CheckInMonograph() {
        System.out.println("CheckInMonograph");
        return "module/binding/04_Binding_CheckIn/CheckInMonograph";
    }

    @RequestMapping(value={"/CheckInSerial"}, method={RequestMethod.GET})
    public String CheckInSerial() {
        System.out.println("CheckInSerial");
        return "module/binding/04_Binding_CheckIn/CheckInSerial";
    }

    @RequestMapping(value={"/managementBinding"}, method={RequestMethod.GET})
    public String managementBinding() {
        System.out.println("managementBinding");
        return "module/binding/05_Binding_Management/managementBinding";
    }
}
