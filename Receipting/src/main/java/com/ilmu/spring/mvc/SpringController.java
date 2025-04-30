/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.servlet.ModelAndView
 */
package com.ilmu.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value={"/PatronDetails"}, method={RequestMethod.GET})
    public String upload() {
        return "module/Receipting/PatronDetails";
    }

    @RequestMapping(value={"/miscellaneous"}, method={RequestMethod.GET})
    public String miscellaneous() {
        return "module/Receipting/miscellaneous";
    }

    @RequestMapping(value={"/ListPendingPayment"}, method={RequestMethod.GET})
    public String ListPendingPayment() {
        return "module/Receipting/ListPendingPayment";
    }

    @RequestMapping(value={"/ListPaidPayment"}, method={RequestMethod.GET})
    public String ListPaidPayment() {
        return "module/Receipting/ListPaidPayment";
    }

    @RequestMapping(value={"/override"}, method={RequestMethod.GET})
    public String override() {
        return "module/Receipting/override";
    }

    @RequestMapping(value={"/AddTransaction"}, method={RequestMethod.GET})
    public String AddTransaction() {
        return "module/Receipting/AddTransaction";
    }

    @RequestMapping(value={"/DistributionModal"}, method={RequestMethod.GET})
    public String DistributionModal() {
        return "module/Receipting/DistributionModal";
    }

    @RequestMapping(value={"/ListReceipt"}, method={RequestMethod.GET})
    public String ListReceipt() {
        return "module/Receipting/ListReceipt";
    }

    @RequestMapping(value={"/printReceipt"}, method={RequestMethod.GET})
    public String printReceipt() {
        return "module/Receipting/print";
    }

    @RequestMapping(value={"/Modal_View"}, method={RequestMethod.GET})
    public String Modal_View() {
        return "module/Receipting/Modal_View";
    }

    @RequestMapping(value={"Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_AddResv() {
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @RequestMapping(value={"/Handler_GetReceiptNo"}, method={RequestMethod.GET})
    public String Handler_GetReceiptNo() {
        return "module/Receipting/Handler_GetReceiptNo";
    }

    @RequestMapping(value={"/Modal_ListOfPrinter"}, method={RequestMethod.GET})
    public String Modal_ListOfPrinter() {
        System.out.println("tester");
        return "include/shared/Modal_ListOfPrinters";
    }

    @RequestMapping(value={"/Handler_DefaultPrinter"}, method={RequestMethod.GET})
    public String Handler_DefaultPrinter() {
        System.out.println("tester");
        return "include/shared/Handler_DefaultPrinter";
    }

    @RequestMapping(value={"/OverrideUser"}, method={RequestMethod.GET})
    public String SignedInUser() {
        System.out.println("tester");
        return "include/shared/Override_UserLogIn";
    }

    @RequestMapping(value={"/Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        System.out.println("tester");
        return "include/Error_Page";
    }

    @RequestMapping(value={"/GeneratePreview"}, method={RequestMethod.GET})
    public String GeneratePreview() {
        System.out.println("tester");
        return "include/GeneratePreviewDoc";
    }
}
