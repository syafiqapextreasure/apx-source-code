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
    @RequestMapping(value={"/Table_AllSerialMaster"}, method={RequestMethod.GET})
    public String test() {
        return "module/Serial/01_Serial_Master/Table_AllSerialMaster";
    }

    @RequestMapping(value={"/Table_SerialMaster"}, method={RequestMethod.GET})
    public String Table_SerialMaster() {
        return "module/Serial/01_Serial_Master/Table_SerialMaster";
    }

    @RequestMapping(value={"/Modal_ViewSerialMaster"}, method={RequestMethod.GET})
    public String Modal_ViewSerialMaster() {
        return "module/Serial/01_Serial_Master/Modal_ViewSerialMaster";
    }

    @RequestMapping(value={"/Modal_EditSerialMaster"}, method={RequestMethod.GET})
    public String Modal_EditSerialMaster() {
        return "module/Serial/01_Serial_Master/Modal_EditAllSerialMaster";
    }

    @RequestMapping(value={"/Modal_AddNewSerialMaster"}, method={RequestMethod.GET})
    public String Modal_AddNewSerialMaster() {
        return "module/Serial/01_Serial_Master/Modal_AddNewSerialMaster";
    }

    @RequestMapping(value={"/Handler_EditSerialMaster"}, method={RequestMethod.GET})
    public String Handler_EditSerialMaster() {
        return "module/Serial/01_Serial_Master/Handler_EditSerialMaster";
    }

    @RequestMapping(value={"/Handler_DeleteSerialMaster"}, method={RequestMethod.GET})
    public String Handler_DeleteSerialMaster() {
        return "module/Serial/01_Serial_Master/Handler_DeleteSerialMaster";
    }

    @RequestMapping(value={"/Handler_AddNewSerialMaster"}, method={RequestMethod.GET})
    public String Handler_AddNewSerialMaster() {
        return "module/Serial/01_Serial_Master/Handler_AddNewSerialMaster";
    }

    @RequestMapping(value={"/ViewAddEdit"}, method={RequestMethod.GET})
    public String ViewAddEdit() {
        return "module/Serial/02_Order_Maintenance/view-add-edit";
    }

    @RequestMapping(value={"/SearchRequestorModal"}, method={RequestMethod.GET})
    public String SearchRequestorModal() {
        return "include/shared/SearchRequestorModal";
    }

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal() {
        return "include/shared/RetrieveTitleModal";
    }

    @RequestMapping(value={"/RetrieveSearchTitle"}, method={RequestMethod.GET})
    public String RetrieveSearchTitle() {
        return "include/shared/RetrieveSearchTitle";
    }

    @RequestMapping(value={"/SearchRequestor"}, method={RequestMethod.GET})
    public String SearchRequestor() {
        return "include/shared/SearchRequestor";
    }

    @RequestMapping(value={"/Modal_Vendor"}, method={RequestMethod.GET})
    public String Modal_Vendor() {
        return "include/shared/Modal_Vendor";
    }

    @RequestMapping(value={"/SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "include/shared/SearchVendor";
    }

    @RequestMapping(value={"/Modal_SerialMstr"}, method={RequestMethod.GET})
    public String Modal_SerialMstr() {
        return "include/shared/Modal_SerialMstr";
    }

    @RequestMapping(value={"/RetrieveSerialMstr"}, method={RequestMethod.GET})
    public String RetrieveSerialMstr() {
        return "include/shared/RetrieveSerialMstr";
    }

    @RequestMapping(value={"/ViewTitles"}, method={RequestMethod.GET})
    public String ViewTitles() {
        return "include/shared/ViewTitles";
    }

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

    @RequestMapping(value={"/Modal_CancelOrderSearch"}, method={RequestMethod.GET})
    public String Handler_ParamipsTable() {
        return "module/Serial/10_Cancel_Order/Modal_CancelOrderSearch";
    }

    @RequestMapping(value={"/Handler_Resubmit"}, method={RequestMethod.GET})
    public String Handler_Resubmit() {
        return "module/Serial/10_Cancel_Order/Handler_Resubmit";
    }

    @RequestMapping(value={"/Table_CancelOrderList"}, method={RequestMethod.GET})
    public String Table_CancelOrderList() {
        return "module/Serial/10_Cancel_Order/Table_CancelOrderList";
    }

    @RequestMapping(value={"/Table_Ordering"}, method={RequestMethod.GET})
    public String Table_OrderingList() {
        return "module/Serial/05_Ordering/Table_Ordering";
    }

    @RequestMapping(value={"GeneratePreview"}, method={RequestMethod.GET})
    public String GeneratePreview() {
        return "module/Serial/07_RequestForPayment/GeneratePreviewDocument";
    }

    @RequestMapping(value={"Modal_Process"}, method={RequestMethod.GET})
    public String Modal_Process() {
        return "module/Serial/05_Ordering/Modal_Process";
    }

    @RequestMapping(value={"GenerateOrderList"}, method={RequestMethod.GET})
    public String GenerateOrderList() {
        return "module/Serial/05_Ordering/GenerateOrderList";
    }

    @RequestMapping(value={"Handler_ProcessOrdering"}, method={RequestMethod.GET})
    public String Handler_ProcessOrdering() {
        return "module/Serial/05_Ordering/Handler_ProcessOrdering";
    }

    @RequestMapping(value={"GenerateOrderDoc"}, method={RequestMethod.GET})
    public String GenerateOrderDoc() {
        return "module/Serial/05_Ordering/GenerateOrderDoc";
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
        return "module/Serial/07_RequestForPayment/Table_RequestForPayment";
    }

    @RequestMapping(value={"/Handler_AcceptPayment"}, method={RequestMethod.GET})
    public String Handler_AcceptPayment() {
        return "module/Serial/07_RequestForPayment/Handler_AcceptPayment";
    }

    @RequestMapping(value={"/GenerateDoc"}, method={RequestMethod.GET})
    public String GenerateDoc() {
        return "module/Serial/07_RequestForPayment/GenerateDoc";
    }

    @RequestMapping(value={"/Handler_AcceptRecord"}, method={RequestMethod.GET})
    public String Handler_AcceptRecord() {
        return "module/Serial/08_RecordForPayment/Handler_AcceptRecord";
    }

    @RequestMapping(value={"/Table_RecordPayment"}, method={RequestMethod.GET})
    public String Table_RecordPayment() {
        return "module/Serial/08_RecordForPayment/Table_RecordPayment";
    }

    @RequestMapping(value={"/Inactive_IssueMgmt"}, method={RequestMethod.GET})
    public String Inactive_IssueMgmt() {
        return "module/Serial/08_RecordForPayment/Inactive_IssueMgmt";
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

    @RequestMapping(value={"/Modal_IssuePattern"}, method={RequestMethod.GET})
    public String Modal_IssuePattern() {
        return "include/shared/Modal_IssuePattern";
    }

    @RequestMapping(value={"/Load_Cardex"}, method={RequestMethod.GET})
    public String Load_Cardex() {
        return "include/shared/Load_Cardex";
    }

    @RequestMapping(value={"/ComputeStopDate"}, method={RequestMethod.GET})
    public String ComputeStopDate() {
        return "include/shared/ComputeStopDate";
    }

    @RequestMapping(value={"/Handler_StoreIssuesList"}, method={RequestMethod.GET})
    public String Handler_StoreIssuesList() {
        return "include/shared/Handler_StoreIssuesList";
    }

    @RequestMapping(value={"/Table_GenerateIssuesList"}, method={RequestMethod.GET})
    public String Table_GenerateIssuesList() {
        return "include/shared/Table_GenerateIssuesList";
    }

    @RequestMapping(value={"/Table_Renewal"}, method={RequestMethod.GET})
    public String Table_Renewal() {
        return "module/Serial/11_Renewal/Table_Renewal";
    }

    @RequestMapping(value={"/Modal_Renew"}, method={RequestMethod.GET})
    public String Modal_Renew() {
        return "module/Serial/11_Renewal/Modal_Renew";
    }

    @RequestMapping(value={"/Handler_DeleteRenewal"}, method={RequestMethod.GET})
    public String Handler_DeleteRenewal() {
        return "module/Serial/11_Renewal/Handler_DeleteRenewal";
    }

    @RequestMapping(value={"Modal_ProcessRenewal"}, method={RequestMethod.GET})
    public String Modal_ProcessRenewal() {
        return "module/Serial/11_Renewal/Modal_ProcessRenewal";
    }

    @RequestMapping(value={"Handler_ProcessRenewal"}, method={RequestMethod.GET})
    public String Handler_ProcessRenewal() {
        return "module/Serial/11_Renewal/Handler_ProcessRenewal";
    }

    @RequestMapping(value={"/Table_InactiveIssueMgtmt"}, method={RequestMethod.GET})
    public String Table_InactiveIssueMgtmt() {
        return "module/Serial/12_InactiveIssueMgmt/Table_InactiveIssueMgmt";
    }

    @RequestMapping(value={"/Handler_DeleteRecord"}, method={RequestMethod.GET})
    public String Handler_DeleteRecord() {
        return "module/Serial/12_InactiveIssueMgmt/Handler_DeleteRecord";
    }

    @RequestMapping(value={"GeneratePreviewDocument"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument() {
        return "include/shared/GeneratePreviewDocument";
    }

    @RequestMapping(value={"GeneratePreviewDocument2"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument2() {
        return "include/shared/GeneratePreviewDocument2";
    }
}
