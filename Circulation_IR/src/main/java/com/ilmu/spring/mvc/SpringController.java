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

    @RequestMapping(value={"/Handler_PatronDetails"}, method={RequestMethod.GET})
    public String method7() {
        return "module/Circulation/01_Charging/Handler_PatronDetails";
    }

    @RequestMapping(value={"/Handler_PatrStatus"}, method={RequestMethod.GET})
    public String Handler_PatrStatus() {
        return "module/Circulation/01_Charging/Handler_PatrStatus";
    }

    @RequestMapping(value={"/Modal_LateReturnHistory"}, method={RequestMethod.GET})
    public String Modal_LateReturnHistory() {
        return "module/Circulation/01_Charging/Modal_LateReturnHistory";
    }

    @RequestMapping(value={"/Handler_DeleteResv"}, method={RequestMethod.GET})
    public String Handler_DeleteResv() {
        return "module/Circulation/02_Reservation/Handler_DeleteResv";
    }

    @RequestMapping(value={"/LoadSelectBox"}, method={RequestMethod.GET})
    public String LoadSelectBox() {
        return "module/Circulation/02_Reservation/LoadSelectBox";
    }

    @RequestMapping(value={"/Table_PatrReqStat"}, method={RequestMethod.GET})
    public String Table_PatrReqStat() {
        return "module/Circulation/01_Charging/Table_PatrReqStat";
    }

    @RequestMapping(value={"/Modal_Officer"}, method={RequestMethod.GET})
    public String Modal_Officer() {
        return "include/shared/Modal_OfficerID";
    }

    @RequestMapping(value={"/PatrDetailsModal"}, method={RequestMethod.GET})
    public String PatrDetailsModal() {
        return "module/Circulation/01_Charging/PatrDetailsModal";
    }

    @RequestMapping(value={"/Handler_ViewDischarge"}, method={RequestMethod.GET})
    public String Handler_ViewDischarge() {
        return "module/Circulation/01_Charging/Handler_ViewDischarge";
    }

    @RequestMapping(value={"/Handler_Charging"}, method={RequestMethod.GET})
    public String Handler_Charging() {
        return "module/Circulation/01_Charging/Handler_Charging";
    }

    @RequestMapping(value={"/Handler_Circulation"}, method={RequestMethod.GET})
    public String Handler_Circulation() {
        return "module/Circulation/01_Charging/Handler_Circulation";
    }

    @RequestMapping(value={"/Handler_Discharging"}, method={RequestMethod.GET})
    public String Handler_Discharging() {
        return "module/Circulation/01_Charging/Handler_Discharging";
    }

    @RequestMapping(value={"/Handler_Renewal"}, method={RequestMethod.GET})
    public String Handler_Renewal() {
        return "module/Circulation/01_Charging/Handler_Renewal";
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

    @RequestMapping(value={"/Handler_BORecord"}, method={RequestMethod.GET})
    public String test() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_BORecord";
    }

    @RequestMapping(value={"/Handler_BufferTable"}, method={RequestMethod.GET})
    public String Handler_BufferTable() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_BufferTable";
    }

    @RequestMapping(value={"/ViewSubfields"})
    public String test1() {
        return "module/Cataloging/02_Bibliographic_Organisation/ViewSubfields";
    }

    @RequestMapping(value={"/Handler_Indexing"})
    public String Indexing() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_Indexing";
    }

    @RequestMapping(value={"/Handler_Unindex"})
    public String Unindex() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_Unindex";
    }

    @RequestMapping(value={"/Error_Page"})
    public String Error_Page() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"/Term_Search"})
    public String test2() {
        return "module/Cataloging/02_Bibliographic_Organisation/Term_Search";
    }

    @RequestMapping(value={"/Search_OfficerID"})
    public String Search_OfficerID() {
        return "include/shared/Search_OfficerID";
    }

    @RequestMapping(value={"/AddNewTplInfo"})
    public String test6() {
        return "module/Cataloging/01_Template_Maintenance/Modal_AddTplInfo";
    }

    @RequestMapping(value={"/AddNewTplInfo1"})
    public String test9() {
        return "module/Cataloging/02_Bibliographic_Organisation/Modal_AddTplInfo";
    }

    @RequestMapping(value={"/Table_TermSearch"}, method={RequestMethod.GET})
    public String tes3() {
        return "module/Cataloging/02_Bibliographic_Organisation/Table_TermSearch";
    }

    @RequestMapping(value={"/Control_Fields"}, method={RequestMethod.GET})
    public String test4() {
        return "module/Cataloging/02_Bibliographic_Organisation/Control_Fields";
    }

    @RequestMapping(value={"/BO_AjaxHandler"}, method={RequestMethod.GET})
    public String test5() {
        return "module/Cataloging/02_Bibliographic_Organisation/BO_AjaxHandler";
    }

    @RequestMapping(value={"/Indicators_Handler"}, method={RequestMethod.GET})
    public String test7() {
        return "module/Cataloging/01_Template_Maintenance/Indicators_Handler";
    }

    @RequestMapping(value={"/Media_Record"}, method={RequestMethod.GET})
    public String test8() {
        return "module/Cataloging/02_Bibliographic_Organisation/Media_Record";
    }

    @RequestMapping(value={"/Handler_AddBO"}, method={RequestMethod.GET})
    public String AddBO() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_AddBO";
    }

    @RequestMapping(value={"/Table_UnindexBO"}, method={RequestMethod.GET})
    public String Table_UnindexBO() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_BORecord";
    }

    @RequestMapping(value={"/Handler_Unindex"}, method={RequestMethod.GET})
    public String Handler_Unindex() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_Unindex";
    }

    @RequestMapping(value={"/AddTplMaint"}, method={RequestMethod.GET})
    public String AddTplMaint() {
        return "module/Cataloging/01_Template_Maintenance/Modal_AddTplMaint";
    }

    @RequestMapping(value={"/Handler_AddNewTplMaint"}, method={RequestMethod.GET})
    public String Handler_AddNewTplMaint() {
        return "module/Cataloging/01_Template_Maintenance/Handler_AddNewTplMaint";
    }

    @RequestMapping(value={"/ShowTplDetails"}, method={RequestMethod.GET})
    public String ShowTplDetails() {
        return "module/Cataloging/01_Template_Maintenance/ShowTplDetails";
    }

    @RequestMapping(value={"/Modal_EditTpl"}, method={RequestMethod.GET})
    public String Modal_EditTpl() {
        return "module/Cataloging/01_Template_Maintenance/Modal_EditTpl";
    }

    @RequestMapping(value={"/Handler_EditTpl"}, method={RequestMethod.GET})
    public String Handler_EditTpl() {
        return "module/Cataloging/01_Template_Maintenance/Handler_EditTpl";
    }

    @RequestMapping(value={"/Handler_DeleteTplMaint"}, method={RequestMethod.GET})
    public String Handler_DeleteTplMaint() {
        return "module/Cataloging/01_Template_Maintenance/Handler_DeleteTplMaint";
    }

    @RequestMapping(value={"/Handler_EditTplInfo"}, method={RequestMethod.GET})
    public String Handler_EditTplInfo() {
        return "module/Cataloging/01_Template_Maintenance/Handler_EditTplInfo";
    }

    @RequestMapping(value={"/Modal_EditTplInfo"}, method={RequestMethod.GET})
    public String Modal_EditTplInfo() {
        return "module/Cataloging/01_Template_Maintenance/Modal_EditTplInfo";
    }

    @RequestMapping(value={"Modal_AddAccMaint"}, method={RequestMethod.GET})
    public String Modal_AddAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Modal_AddAccMaint";
    }

    @RequestMapping(value={"/Table_SearchByAccNo"}, method={RequestMethod.GET})
    public String Table_SearchByAccNo() {
        return "include/shared/Table_SearchByAccNo";
    }

    @RequestMapping(value={"Modal_ViewAccMaint"}, method={RequestMethod.GET})
    public String Modal_ViewAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Modal_ViewAccMaint";
    }

    @RequestMapping(value={"Modal_AddResv"}, method={RequestMethod.GET})
    public String Modal_AddResv() {
        return "module/Circulation/02_Reservation/Modal_AddResv";
    }

    @RequestMapping(value={"Handler_AddResv"}, method={RequestMethod.GET})
    public String Handler_AddResv() {
        return "module/Circulation/02_Reservation/Handler_AddResv";
    }

    @RequestMapping(value={"Handler_ResvAcc"}, method={RequestMethod.GET})
    public String Handler_ResvAcc() {
        return "module/Circulation/02_Reservation/Handler_ResvAcc";
    }

    @RequestMapping(value={"Print_Slip"}, method={RequestMethod.GET})
    public String Print_Slip() {
        return "module/Circulation/01_Charging/Print_Slip";
    }

    @RequestMapping(value={"List_Resv"}, method={RequestMethod.GET})
    public String List_Resv() {
        return "module/Circulation/02_Reservation/List_Resv";
    }

    @RequestMapping(value={"Modal_EditAccMaint"}, method={RequestMethod.GET})
    public String Modal_EditAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Modal_EditAccMaint";
    }

    @RequestMapping(value={"/Handler_AddTplInfoMaint"}, method={RequestMethod.GET})
    public String Handler_AddTplInfoMaint() {
        return "module/Cataloging/01_Template_Maintenance/Handler_AddTplInfoMaint";
    }

    @RequestMapping(value={"/Handler_AddTpl"}, method={RequestMethod.GET})
    public String Handler_AddTpl() {
        return "module/Cataloging/01_Template_Maintenance/Handler_AddTpl";
    }

    @RequestMapping(value={"Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"trial"}, method={RequestMethod.GET})
    public String trial() {
        return "module/Cataloging/04_Release_Circulation/GenerateDoc";
    }

    @RequestMapping(value={"/Loading"})
    public String Loader() {
        return "include/Loader";
    }

    @RequestMapping(value={"/GetAccessionNo"})
    public String GetAccessionNo() {
        return "include/shared/GetAccessionNo";
    }

    @RequestMapping(value={"/Handler_DeleteTplInfo"}, method={RequestMethod.GET})
    public String Handler_DeleteTplInfo() {
        return "module/Cataloging/01_Template_Maintenance/Handler_DeleteTplInfo";
    }

    @RequestMapping(value={"/Handler_AddNewAccMaint"}, method={RequestMethod.GET})
    public String Handler_AddNewAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Handler_AddNewAccMaint";
    }

    @RequestMapping(value={"/CurrencyRate"}, method={RequestMethod.GET})
    public String CurrencyRate() {
        return "include/shared/CurrencyRate";
    }

    @RequestMapping(value={"/SearchTitleModal1"}, method={RequestMethod.GET})
    public String SearchTitleModal1() {
        return "include/shared/SearchTitleModal";
    }

    @RequestMapping(value={"/ViewTitles"}, method={RequestMethod.GET})
    public String ViewTitles1() {
        return "include/shared/ViewTitles";
    }

    @RequestMapping(value={"/ViewTitlesDetail"}, method={RequestMethod.GET})
    public String ViewTitlesDetail() {
        return "include/shared/ViewTitlesDetail";
    }

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal() {
        return "include/shared/RetrieveTitleModal";
    }

    @RequestMapping(value={"/RetrieveSearchTitle"}, method={RequestMethod.GET})
    public String RetrieveSearchTitle1() {
        return "include/shared/RetrieveSearchTitle";
    }

    @RequestMapping(value={"/SearchTitle"}, method={RequestMethod.GET})
    public String SearchTitle() {
        return "include/shared/SearchTitle";
    }

    @RequestMapping(value={"/Modal_Vendor"}, method={RequestMethod.GET})
    public String Modal_Vendor() {
        return "include/shared/Modal_Vendor";
    }

    @RequestMapping(value={"/SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "include/shared/SearchVendor";
    }

    @RequestMapping(value={"/ListOfAcc"}, method={RequestMethod.GET})
    public String ListOfAcc() {
        return "module/Cataloging/04_Release_Circulation/ListOfAcc";
    }

    @RequestMapping(value={"/DisplayTitle"}, method={RequestMethod.GET})
    public String DisplayTitle() {
        return "module/Cataloging/04_Release_Circulation/DisplayTitle";
    }

    @RequestMapping(value={"/Table_CTResultMaster"}, method={RequestMethod.GET})
    public String Table_CTResultMaster() {
        return "module/Cataloging/03_Accession_Maintenance/Table_CTResultMaster";
    }

    @RequestMapping(value={"/Modal_OfficerID"}, method={RequestMethod.GET})
    public String Modal_OfficerID() {
        return "include/shared/Modal_OfficerID";
    }

    @RequestMapping(value={"/Print_CancellationLetter"}, method={RequestMethod.GET})
    public String Print_CancellationLetter() {
        System.out.println("tester");
        return "module/Circulation/02_Reservation/Print_CancellationLetter";
    }

    @RequestMapping(value={"/SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "include/shared/SearchPatron";
    }

    @RequestMapping(value={"/Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        System.out.println("qwerty");
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"/view"}, method={RequestMethod.GET})
    public String view() {
        return "module/Circulation/05_Item_Reassignment/view";
    }

    @RequestMapping(value={"/searchAN"}, method={RequestMethod.GET})
    public String searchAN() {
        System.out.println("qwerty2");
        return "module/Circulation/05_Item_Reassignment/searchAN";
    }

    @RequestMapping(value={"/modify"}, method={RequestMethod.GET})
    public String modify() {
        return "module/Circulation/06_Item_Status_Maintenance/modify";
    }

    @RequestMapping(value={"/bib"}, method={RequestMethod.GET})
    public String bib() {
        System.out.println("123456");
        return "module/Circulation/06_Item_Status_Maintenance/bib";
    }

    @RequestMapping(value={"/circulationStatus"}, method={RequestMethod.GET})
    public String circulationStatus() {
        return "module/Circulation/06_Item_Status_Maintenance/circulationStatus";
    }

    @RequestMapping(value={"/recallLetter"}, method={RequestMethod.GET})
    public String recallLetter() {
        return "module/Circulation/06_Item_Status_Maintenance/recallLetter";
    }

    @RequestMapping(value={"/PrintRecallLetter"}, method={RequestMethod.GET})
    public String PrintRecallLetter() {
        return "module/Circulation/06_Item_Status_Maintenance/PrintRecallLetter";
    }

    @RequestMapping(value={"/GeneratePreviewDocument"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument() {
        return "include/GeneratePreviewDocument";
    }
}
