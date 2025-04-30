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
package com.kmlink.ilmu.shared.spring.mvc;

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
        return "shared/" + url;
    }

    @RequestMapping(value={"/Handler"})
    public String Handler() {
        return "shared/Handler";
    }

    @RequestMapping(value={"/Error_Page"})
    public String Error_Page() {
        return "shared/Error_Page";
    }

    @RequestMapping(value={"Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        return "shared/Error_Page";
    }

    @RequestMapping(value={"/Loading"})
    public String Loader() {
        return "shared/Loader";
    }

    @RequestMapping(value={"/create-reserve"}, method={RequestMethod.GET})
    public String Create_Reserve() {
        System.out.println("in spring controller /create-reserve");
        return "shared/Create_ModalReserve";
    }

    @RequestMapping(value={"/edit-reserve"}, method={RequestMethod.GET})
    public String Edit_Reserve() {
        return "shared/Edit_ModalReserve";
    }

    @RequestMapping(value={"/delete-reserve"}, method={RequestMethod.GET})
    public String Delete_Reserve() {
        return "shared/Delete_ModalReserve";
    }

    @RequestMapping(value={"/view-reserve"}, method={RequestMethod.GET})
    public String View_Reserve() {
        return "shared/View_ModalReserve";
    }

    @RequestMapping(value={"/Modal_AddAccMaint"}, method={RequestMethod.GET})
    public String Modal_AddAccMaint() {
        System.out.println("Modal_AddAccMaint");
        return "shared/Modal_AddAccMaint";
    }

    @RequestMapping(value={"/Term_Search"})
    public String Term_Search() {
        return "shared/Term_Search";
    }

    @RequestMapping(value={"/Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        System.out.println("qwerty");
        return "shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"/Modal_MemberPatrSearch"}, method={RequestMethod.GET})
    public String Modal_MemberPatrSearch() {
        System.out.println("qwerty");
        return "shared/Modal_MemberPatrSearch";
    }

    @RequestMapping(value={"/Modal_BOSecurity"}, method={RequestMethod.GET})
    public String Modal_BOSecurity() {
        return "include/Security/Modal_BOSecurity";
    }

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal() {
        System.out.println("in template RetrieveTitleModal");
        return "shared/RetrieveTitleModal";
    }

    @RequestMapping(value={"/MarcView"}, method={RequestMethod.GET})
    public String ISBDView() {
        return "include/shared/MarcView";
    }

    @RequestMapping(value={"/GetAccessionNo"})
    public String GetAccessionNo() {
        return "include/shared/GetAccessionNo";
    }

    @RequestMapping(value={"/RetrieveTitleAccMainModal"}, method={RequestMethod.GET})
    public String RetrieveTitleAccMainModal() {
        System.out.println("in template RetrieveTitleAccMainModal");
        return "include/shared/RetrieveTitleAccMainModal";
    }

    @RequestMapping(value={"/find-reserve"}, method={RequestMethod.GET})
    public String Find_ModalReserve() {
        return "include/shared/Find_ModalReserve";
    }

    @RequestMapping(value={"/SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "shared/SearchPatron";
    }

    @RequestMapping(value={"/MemberSearchPatron"}, method={RequestMethod.GET})
    public String MemberSearchPatron() {
        return "shared/MemberSearchPatron";
    }

    @RequestMapping(value={"/Counter_Service"})
    public String Counter_Service(@RequestParam(value="actionID") String actionID) {
        return "module/Circulation/01_Charging/" + actionID;
    }

    @RequestMapping(value={"/Handler_PatronDetails"}, method={RequestMethod.GET})
    public String Handler_PatronDetails() {
        return "module/Circulation/01_Charging/Handler_PatronDetails";
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

    @RequestMapping(value={"/Modal_BORcrd"}, method={RequestMethod.GET})
    public String Modal_BORcrd() {
        return "include/shared/Modal_BORecord";
    }

    @RequestMapping(value={"/Search_OfficerID"})
    public String Search_OfficerID() {
        return "include/shared/Search_OfficerID";
    }

    @RequestMapping(value={"/Modal_OfficerID"})
    public String Modal_OfficerID() {
        return "include/shared/Modal_OfficerID";
    }

    @RequestMapping(value={"/RetrieveSearchTitle"}, method={RequestMethod.GET})
    public String RetrieveSearchTitle1() {
        System.out.println("196 spring controller RetrieveSearchTitle");
        return "shared/RetrieveSearchTitle";
    }

    @RequestMapping(value={"/Table_TermSearch"}, method={RequestMethod.GET})
    public String tes3() {
        return "include/shared/Table_TermSearch";
    }

    @RequestMapping(value={"/GetTolNote"})
    public String GetTolNote() {
        return "include/shared/GetTolNote";
    }

    @RequestMapping(value={"/ViewTitles"}, method={RequestMethod.GET})
    public String ViewTitles1() {
        return "shared/ViewTitles";
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

    @RequestMapping(value={"/printMail"}, method={RequestMethod.POST})
    public String printMail() {
        return "module/mailBrowser/printMail";
    }

    @RequestMapping(value={"/InsertGLMAILDocument"}, method={RequestMethod.GET})
    public String InsertGLMAILDocument() {
        return "module/mailBrowser/InsertGLMAILDocument";
    }

    @RequestMapping(value={"/Handler_DeleteEmail"}, method={RequestMethod.GET})
    public String Handler_DeleteEmail() {
        System.out.println("Handler_DeleteEmail");
        return "module/mailBrowser/Handler_DeleteEmail";
    }

    @RequestMapping(value={"/Handler_SendEmail"}, method={RequestMethod.GET})
    public String Handler_SendEmail() {
        System.out.println("Handler_SendEmail");
        return "module/mailBrowser/Handler_SendEmail";
    }

    @RequestMapping(value={"/ViewMailBrowser"}, method={RequestMethod.GET})
    public String ViewMailBrowser() {
        return "include/shared/ViewMailBrowser";
    }

    @RequestMapping(value={"/Handler_DeleteTag"}, method={RequestMethod.GET})
    public String Handler_DeleteTag() {
        System.out.println("Handler_DeleteTag");
        return "module/tagParameter/Handler_DeleteTag";
    }

    @RequestMapping(value={"/parableMaint_print_Details"}, method={RequestMethod.GET})
    public String parableMaint_print_Details() {
        return "/module/parableTemplateMaint/book/parableMaint_print_Details";
    }

    @RequestMapping(value={"/parableMaint_printPatronLabel"}, method={RequestMethod.GET})
    public String parableMaint_printPatronLabel() {
        return "/module/parableTemplateMaint/book/parableMaint_printPatronLabel";
    }

    @RequestMapping(value={"/book_labelProcess"}, method={RequestMethod.POST})
    public String book_labelProcess() {
        return "module/parable/book/book_labelProcess";
    }

    @RequestMapping(value={"/spineProcess"}, method={RequestMethod.POST})
    public String spineProcess() {
        return "module/parable/spine/spineProcess";
    }

    @RequestMapping(value={"/print_Details"}, method={RequestMethod.POST})
    public String print_Details() {
        return "module/parable/book/print_Details";
    }

    @RequestMapping(value={"/update_print"}, method={RequestMethod.GET})
    public String update_print() {
        return "module/parable/book/update_print";
    }

    @RequestMapping(value={"/patronProcess"}, method={RequestMethod.POST})
    public String patronProcess() {
        return "module/parable/patron/patronProcess";
    }

    @RequestMapping(value={"/update_printPatron"}, method={RequestMethod.GET})
    public String update_printPatron() {
        return "module/parable/patron/update_printPatron";
    }

    @RequestMapping(value={"/patron_print"}, method={RequestMethod.POST})
    public String patron_print() {
        return "module/parable/patron/patron_print";
    }

    @RequestMapping(value={"/printSpine_Details"}, method={RequestMethod.POST})
    public String printSpine_Details() {
        return "module/parable/spine/printSpine_Details";
    }

    @RequestMapping(value={"/printSpine_Details_Copy"}, method={RequestMethod.POST})
    public String printSpine_Details_Copy() {
        return "module/parable/spine/printSpine_Details - Copy";
    }

    @RequestMapping(value={"/updateSpine_print"}, method={RequestMethod.GET})
    public String updateSpine_print() {
        return "module/parable/spine/updateSpine_print";
    }

    @RequestMapping(value={"/testurl"})
    public String testMethod() {
        return "/module/parable/book/test";
    }

    @RequestMapping(value={"/redirectPage"})
    public String abc() {
        return "/module/overdueNotification/redirectPage";
    }

    @RequestMapping(value={"/Receipting"})
    public String Receipting(@RequestParam(value="actionID") String actionID) {
        System.out.println(actionID);
        return "module/Receipting/" + actionID;
    }

    @RequestMapping(value={"/Cataloging"})
    public String Template_Maintenance(@RequestParam(value="actionID") String actionID) {
        System.out.println(String.valueOf(actionID) + " kkk");
        if (actionID.equals("Handler_BOValidation")) {
            return "module/Cataloging/02_Bibliographic_Organisation/" + actionID;
        }
        if (actionID.equals("Delete_TplDetails")) {
            return "module/Cataloging/01_Template_Maintenance/Handler_DeleteTplDetails";
        }
        return "module/Cataloging/01_Template_Maintenance/" + actionID;
    }

    @RequestMapping(value={"/BO"})
    public String BO(@RequestParam(value="actionID") String actionID) {
        System.out.println(actionID);
        return "module/Cataloging/02_Bibliographic_Organisation/" + actionID;
    }

    @RequestMapping(value={"/Accession_Maint"})
    public String Accession_Maint(@RequestParam(value="actionID") String actionID) {
        System.out.println(actionID);
        return "module/Cataloging/03_Accession_Maintenance/" + actionID;
    }

    @RequestMapping(value={"/Release_Circ"})
    public String Release_Circ(@RequestParam(value="actionID") String actionID) {
        System.out.println(actionID);
        return "module/Cataloging/04_Release_Circulation/" + actionID;
    }

    @RequestMapping(value={"/AddNewTplInfo"})
    public String test6() {
        return "module/Cataloging/01_Template_Maintenance/Modal_AddTplInfo";
    }

    @RequestMapping(value={"/Authority_Maint"})
    public String Authority_Maint(@RequestParam(value="actionID") String actionID) {
        System.out.println(String.valueOf(actionID) + ":actionID");
        return "module/Authority/02_AuthorityMaint/" + actionID;
    }

    @RequestMapping(value={"/Authority_Tpl"})
    public String Authority_Tpl(@RequestParam(value="actionID") String actionID) {
        System.out.println(actionID);
        return "module/Authority/04_Template_Maintenance/" + actionID;
    }

    @RequestMapping(value={"/Handler_Circulation"}, method={RequestMethod.GET})
    public String Handler_Circulation() {
        return "module/Circulation/01_Charging/Handler_Circulation";
    }

    @RequestMapping(value={"/Handler_Discharging"}, method={RequestMethod.GET})
    public String Handler_Discharging() {
        return "module/Circulation/01_Charging/Handler_Discharging";
    }

    @RequestMapping(value={"/GeneratePreview"}, method={RequestMethod.GET})
    public String GeneratePreview() {
        System.out.println("click print charging slip btn ");
        return "shared/GeneratePreviewDoc";
    }

    @RequestMapping(value={"/Handler_Renewal"}, method={RequestMethod.GET})
    public String Handler_Renewal() {
        return "module/Circulation/01_Charging/Handler_Renewal";
    }

    @RequestMapping(value={"/PatrDetailsModal"}, method={RequestMethod.GET})
    public String PatrDetailsModal() {
        return "module/Circulation/01_Charging/PatrDetailsModal";
    }

    @RequestMapping(value={"/Handler_PatrStatus"}, method={RequestMethod.GET})
    public String Handler_PatrStatus() {
        return "module/Circulation/01_Charging/Handler_PatrStatus";
    }

    @RequestMapping(value={"/Modal_PatrLateReturnHistory"}, method={RequestMethod.GET})
    public String Modal_PatrLateReturnHistory() {
        return "module/Circulation/01_Charging/Modal_LateReturnHistory";
    }

    @RequestMapping(value={"/modalViewNote"}, method={RequestMethod.GET})
    public String modalViewNote() {
        return "shared/viewNoteAcq";
    }

    @RequestMapping(value={"/Handler_editnoteAcq"}, method={RequestMethod.GET})
    public String Handler_editnoteAcq() {
        return "shared/Handler_editnoteAcq";
    }

    @RequestMapping(value={"/Handler_ViewNoteAcq"}, method={RequestMethod.GET})
    public String Handler_ViewNoteAcq() {
        return "shared/Handler_ViewNoteAcq";
    }

    @RequestMapping(value={"/Handler_compareGroup"}, method={RequestMethod.GET})
    public String Handler_compareGroup() {
        return "shared/Handler_compareGroup";
    }

    @RequestMapping(value={"/Handler_DeleteNoteFND"}, method={RequestMethod.GET})
    public String Handler_DeleteNoteFND() {
        return "shared/Handler_DeleteNoteFND";
    }

    @RequestMapping(value={"Handler_ResvAcc"}, method={RequestMethod.GET})
    public String Handler_ResvAcc() {
        return "module/Circulation/02_Reservation/Handler_ResvAcc";
    }

    @RequestMapping(value={"List_Resv"}, method={RequestMethod.GET})
    public String List_Resv() {
        return "module/Circulation/02_Reservation/List_Resv";
    }

    @RequestMapping(value={"Handler_AddResv"}, method={RequestMethod.GET})
    public String Handler_AddResv() {
        return "module/Circulation/02_Reservation/Handler_AddResv";
    }

    @RequestMapping(value={"/LoadSelectBox"}, method={RequestMethod.GET})
    public String LoadSelectBox() {
        return "module/Circulation/02_Reservation/LoadSelectBox";
    }

    @RequestMapping(value={"/OpenNewPageInNewTab"}, method={RequestMethod.GET})
    public String OpenNewPageInNewTab() {
        System.out.println("551 OpenNewPageInNewTab");
        return "shared/testing";
    }
}
