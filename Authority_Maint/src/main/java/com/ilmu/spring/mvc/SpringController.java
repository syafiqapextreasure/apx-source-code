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

    @RequestMapping(value={"/TermRetrieval"}, method={RequestMethod.GET})
    public String TermRetrieval() {
        return "module/Authority/02_GlobalChange/TermRetrieval";
    }

    @RequestMapping(value={"/Modal_TagChange"}, method={RequestMethod.GET})
    public String Modal_TagChange() {
        return "module/Authority/02_GlobalChange/Modal_TagChange";
    }

    @RequestMapping(value={"/Handler_GlobalChange"}, method={RequestMethod.GET})
    public String Handler_GlobalChange() {
        return "module/Authority/02_GlobalChange/Handler_GlobalChange";
    }

    @RequestMapping(value={"/Modal_Retrieval"}, method={RequestMethod.GET})
    public String Modal_Retrieval() {
        return "include/shared/Modal_Retrieval";
    }

    @RequestMapping(value={"/Handler_DeleteRecord"}, method={RequestMethod.GET})
    public String Handler_ParamipsTable() {
        return "module/Authority/02_GlobalChange/Handler_DeleteRecord";
    }

    @RequestMapping(value={"/Handler_Merge"}, method={RequestMethod.GET})
    public String Handler_Merge() {
        return "module/Authority/03_GlobalMerge/Handler_Merge";
    }

    @RequestMapping(value={"/Handler_Resubmit"}, method={RequestMethod.GET})
    public String Handler_Resubmit() {
        return "module/Acquisition/10_Cancel_Order/Handler_Resubmit";
    }

    @RequestMapping(value={"/AddNewTplInfo"}, method={RequestMethod.GET})
    public String AddNewTplInfo() {
        return "module/Authority/04_Template_Maintenance/Modal_AddTplInfo";
    }

    @RequestMapping(value={"/Indicators_Handler"}, method={RequestMethod.GET})
    public String Indicators_Handler() {
        return "module/Authority/04_Template_Maintenance/Indicators_Handler";
    }

    @RequestMapping(value={"/Table_CancelOrderList"}, method={RequestMethod.GET})
    public String Table_CancelOrderList() {
        return "module/Acquisition/10_Cancel_Order/Table_CancelOrderList";
    }

    @RequestMapping(value={"/Table_Ordering"}, method={RequestMethod.GET})
    public String Table_OrderingList() {
        return "module/Acquisition/04_Ordering/Table_Ordering";
    }

    @RequestMapping(value={"/SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "include/shared/SearchVendor";
    }

    @RequestMapping(value={"/Modal_Vendor"}, method={RequestMethod.GET})
    public String Modal_Vendor() {
        return "include/shared/Modal_Vendor";
    }

    @RequestMapping(value={"GeneratePreviewDocument"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument() {
        return "module/Acquisition/04_Ordering/GeneratePreviewDocument";
    }

    @RequestMapping(value={"GeneratePreview"}, method={RequestMethod.GET})
    public String GeneratePreview() {
        return "module/Acquisition/06_RequestForPayment/GeneratePreviewDocument";
    }

    @RequestMapping(value={"Modal_Process"}, method={RequestMethod.GET})
    public String Modal_Process() {
        return "module/Acquisition/04_Ordering/Modal_Process";
    }

    @RequestMapping(value={"GenerateOrderList"}, method={RequestMethod.GET})
    public String GenerateOrderList() {
        return "module/Acquisition/04_Ordering/GenerateOrderList";
    }

    @RequestMapping(value={"Handler_ProcessOrdering"}, method={RequestMethod.GET})
    public String Handler_ProcessOrdering() {
        return "module/Acquisition/04_Ordering/Handler_ProcessOrdering";
    }

    @RequestMapping(value={"GenerateOrderDoc"}, method={RequestMethod.GET})
    public String GenerateOrderDoc() {
        return "module/Acquisition/04_Ordering/GenerateOrderDoc";
    }

    @RequestMapping(value={"Handler_GenerateNo"}, method={RequestMethod.GET})
    public String Handler_GenerateNo() {
        return "include/shared/Handler_GenerateNo";
    }

    @RequestMapping(value={"FundDistribution"}, method={RequestMethod.GET})
    public String FundDistribution() {
        return "include/shared/FundDistribution";
    }

    @RequestMapping(value={"Handler_CheckBalance"}, method={RequestMethod.GET})
    public String Handler_CheckBalance() {
        return "include/shared/Handler_CheckBalance";
    }

    @RequestMapping(value={"/Table_RequestForPayment"}, method={RequestMethod.GET})
    public String Table_RequestForPayment() {
        return "module/Acquisition/06_RequestForPayment/Table_RequestForPayment";
    }

    @RequestMapping(value={"/Handler_AcceptPayment"}, method={RequestMethod.GET})
    public String Handler_AcceptPayment() {
        return "module/Acquisition/06_RequestForPayment/Handler_AcceptPayment";
    }

    @RequestMapping(value={"/GenerateDoc"}, method={RequestMethod.GET})
    public String GenerateDoc() {
        return "module/Acquisition/06_RequestForPayment/GenerateDoc";
    }

    @RequestMapping(value={"/Handler_AcceptRecord"}, method={RequestMethod.GET})
    public String Handler_AcceptRecord() {
        return "module/Acquisition/07_RecordForPayment/Handler_AcceptRecord";
    }

    @RequestMapping(value={"/Table_RecordPayment"}, method={RequestMethod.GET})
    public String Table_RecordPayment() {
        return "module/Acquisition/07_RecordForPayment/Table_RecordPayment";
    }

    @RequestMapping(value={"/Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"Modal_BORecord"}, method={RequestMethod.GET})
    public String Modal_BORecord() {
        return "include/shared/Modal_BORecord";
    }

    @RequestMapping(value={"/Handler_Unindex"}, method={RequestMethod.GET})
    public String Handler_Unindex() {
        return "include/shared/Handler_Unindex";
    }

    @RequestMapping(value={"/Handler_DeleteBO"}, method={RequestMethod.GET})
    public String Handler_DeleteBO() {
        return "include/shared/Handler_DeleteBO";
    }
}
