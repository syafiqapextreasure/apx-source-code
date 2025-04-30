/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.ResponseBody
 *  org.springframework.web.servlet.ModelAndView
 */
package com.ilmu.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value={"/01_Template_Maintenance"})
    @ResponseBody
    public String method7() {
        return "module/Cataloging/01_Template_Maintenance/Modal_AddTplInfo";
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

    @RequestMapping(value={"/MarcView"}, method={RequestMethod.GET})
    public String ISBDView() {
        return "include/shared/MarcView";
    }

    @RequestMapping(value={"/Modal_BORcrd"}, method={RequestMethod.GET})
    public String Modal_BORcrd() {
        return "include/shared/Modal_BORecord";
    }

    @RequestMapping(value={"/Modal_ViewBO"}, method={RequestMethod.GET})
    public String Modal_ViewBO() {
        return "module/Cataloging/02_Bibliographic_Organisation/Modal_ViewBO";
    }

    @RequestMapping(value={"/Handler_Paramips"}, method={RequestMethod.GET})
    public String Handler_Paramips() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_Paramips";
    }

    @RequestMapping(value={"/Uploads"}, method={RequestMethod.GET})
    public String upload() {
        return "module/Cataloging/02_Bibliographic_Organisation/UploadFile";
    }

    @RequestMapping(value={"/ValidateBO"}, method={RequestMethod.GET})
    public String ValidateBO() {
        return "module/Cataloging/02_Bibliographic_Organisation/ValidateBO";
    }

    @RequestMapping(value={"/Handler_ParamipsTable"}, method={RequestMethod.GET})
    public String Handler_ParamipsTable() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_ParamipsTable";
    }

    @RequestMapping(value={"/Duplicate_Record"}, method={RequestMethod.GET})
    public String Duplicate_Record() {
        return "module/Cataloging/02_Bibliographic_Organisation/Duplicate_Record";
    }

    @RequestMapping(value={"/ParamipsZ39"}, method={RequestMethod.GET})
    public String ParamipsZ3950() {
        return "module/Cataloging/02_Bibliographic_Organisation/ParamipsZ39.50";
    }

    @RequestMapping(value={"/Modal_Z3950"}, method={RequestMethod.GET})
    public String ParamipsZ39V1() {
        return "module/Cataloging/02_Bibliographic_Organisation/Modal_Z3950";
    }

    @RequestMapping(value={"/Retrieve_Acq"}, method={RequestMethod.GET})
    public String Retrieve_Acq() {
        return "module/Cataloging/02_Bibliographic_Organisation/Retrieve_Acq";
    }

    @RequestMapping(value={"/Indicator_Help"}, method={RequestMethod.GET})
    public String Indicator_Help() {
        return "module/Cataloging/02_Bibliographic_Organisation/Indicator_Help";
    }

    @RequestMapping(value={"/Duplicate_Copy"}, method={RequestMethod.GET})
    public String Duplicate_Copy() {
        return "module/Cataloging/02_Bibliographic_Organisation/Duplicate_Copy";
    }

    @RequestMapping(value={"/Z3950"}, method={RequestMethod.GET})
    public String Z3950() {
        return "module/Cataloging/05_z3950/zClient";
    }

    @RequestMapping(value={"/zSearch"}, method={RequestMethod.GET})
    public String zSearch() {
        return "module/Cataloging/05_z3950/zSearch";
    }

    @RequestMapping(value={"/Handler_DeleteBO"}, method={RequestMethod.GET})
    public String Handler_DeleteBO() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_DeleteBO";
    }

    @RequestMapping(value={"/Handler_BOValidation"}, method={RequestMethod.GET})
    public String Handler_BOValidation() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_BOValidation";
    }

    @RequestMapping(value={"/Handler_UpdateSecurity"}, method={RequestMethod.GET})
    public String Handler_UpdateSecurity() {
        return "include/Security/Handler_UpdateSecurity";
    }

    @RequestMapping(value={"/Modal_BOSecurity"}, method={RequestMethod.GET})
    public String Modal_BOSecurity() {
        return "include/Security/Modal_BOSecurity";
    }

    @RequestMapping(value={"/Handler_Subfields"}, method={RequestMethod.GET})
    public String Handler_Subfields() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_Subfields";
    }

    @RequestMapping(value={"/Handler_BufferTable"}, method={RequestMethod.GET})
    public String Handler_BufferTable() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_BufferTable";
    }

    @RequestMapping(value={"/Handler_NonFilling"}, method={RequestMethod.GET})
    public String Handler_NonFilling() {
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_NonFilling";
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

    @RequestMapping(value={"GenerateDoc"})
    public String GenerateDoc() {
        return "module/Cataloging/04_Release_Circulation/GenerateDoc";
    }

    @RequestMapping(value={"/Handler_CIRESV"})
    public String Handler_CIRESV() {
        return "module/Cataloging/04_Release_Circulation/Handler_CIRESV";
    }

    @RequestMapping(value={"/CreateMail"})
    public String CreateMail() {
        return "module/Cataloging/04_Release_Circulation/CreateMail";
    }

    @RequestMapping(value={"/Error_Page"})
    public String Error_Page() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"/UploadServlet"})
    public String UploadServlet() {
        return "module/Cataloging/02_Bibliographic_Organisation/Testing";
    }

    @RequestMapping(value={"/Term_Search1"})
    public String test2() {
        return "module/Cataloging/02_Bibliographic_Organisation/Term_Search";
    }

    @RequestMapping(value={"/Term_Search"})
    public String Term_Search() {
        return "include/shared/Term_Search";
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

    @RequestMapping(value={"/Handler_AddAuthority"})
    public String Handler_AddAuthority() {
        return "module/Authority/02_AuthorityMaint/Handler_AddAuthority";
    }

    @RequestMapping(value={"/Handler_AuthIndexing"})
    public String Handler_AuthIndexing() {
        return "module/Authority/02_AuthorityMaint/Handler_AuthIndexing";
    }

    @RequestMapping(value={"/Handler_AuthUnindex"})
    public String Handler_AuthUnindex() {
        return "module/Authority/02_AuthorityMaint/Handler_AuthUnindex";
    }

    @RequestMapping(value={"/Modal_CtrlTag"})
    public String Modal_CtrlTag() {
        return "module/Cataloging/02_Bibliographic_Organisation/Modal_CtrlTag";
    }

    @RequestMapping(value={"/Table_TermSearch"}, method={RequestMethod.GET})
    public String tes3() {
        return "include/shared/Table_TermSearch";
    }

    @RequestMapping(value={"/Control_Fields"}, method={RequestMethod.GET})
    public String test4() {
        return "module/Cataloging/02_Bibliographic_Organisation/Control_Fields";
    }

    @RequestMapping(value={"/SubfieldList"}, method={RequestMethod.GET})
    public String test5() {
        return "module/Cataloging/02_Bibliographic_Organisation/SubfieldList";
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
        return "module/Cataloging/02_Bibliographic_Organisation/Handler_UnindexTable";
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
        return "module/Cataloging/03_Accession_Maintenance/Table_SearchByAccNo";
    }

    @RequestMapping(value={"Modal_ViewAccMaint"}, method={RequestMethod.GET})
    public String Modal_ViewAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Modal_ViewAccMaint";
    }

    @RequestMapping(value={"Modal_EditAccMaint"}, method={RequestMethod.GET})
    public String Modal_EditAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Modal_EditAccMaint";
    }

    @RequestMapping(value={"Delete_Modal"}, method={RequestMethod.GET})
    public String Delete_Modal() {
        return "include/sharedV1/Delete_Modal";
    }

    @RequestMapping(value={"/Handler_AddTplInfoMaint"}, method={RequestMethod.GET})
    public String Handler_AddTplInfoMaint() {
        return "module/Cataloging/01_Template_Maintenance/Handler_AddTplInfoMaint";
    }

    @RequestMapping(value={"/Delete_TplDetails"}, method={RequestMethod.GET})
    public String Handler_DeleteTplDetails() {
        return "module/Cataloging/01_Template_Maintenance/Handler_DeleteTplDetails";
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

    @RequestMapping(value={"GeneratePreviewDocument"}, method={RequestMethod.GET})
    public String GeneratePreviewDocument() {
        return "module/Cataloging/04_Release_Circulation/GeneratePreviewDocument";
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

    @RequestMapping(value={"/Handler_AutFlag"}, method={RequestMethod.GET})
    public String Handler_AutFlag() {
        return "include/shared/Modal_AutLink";
    }

    @RequestMapping(value={"/Handler_AddNewAccMaint"}, method={RequestMethod.GET})
    public String Handler_AddNewAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Handler_AddNewAccMaint";
    }

    @RequestMapping(value={"/Handler_EditAccMaint"}, method={RequestMethod.GET})
    public String Handler_EditAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Handler_EditAccMaint";
    }

    @RequestMapping(value={"/Handler_DeleteAccMaint"}, method={RequestMethod.GET})
    public String Handler_DeleteAccMaint() {
        return "module/Cataloging/03_Accession_Maintenance/Handler_DeleteAccMaint";
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

    @RequestMapping(value={"/RetrieveTitleModal"}, method={RequestMethod.GET})
    public String RetrieveTitleModal1() {
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

    @RequestMapping(value={"/ReleaseCirculation"}, method={RequestMethod.GET})
    public String ReleaseCirculation() {
        return "module/Cataloging/04_Release_Circulation/ReleaseCirculation";
    }

    @RequestMapping(value={"/DisplayTitle"}, method={RequestMethod.GET})
    public String DisplayTitle() {
        return "module/Cataloging/04_Release_Circulation/DisplayTitle";
    }

    @RequestMapping(value={"/Handler_Release"}, method={RequestMethod.GET})
    public String Handler_Release() {
        return "module/Cataloging/04_Release_Circulation/Handler_Release";
    }

    @RequestMapping(value={"/Table_CTResultMaster"}, method={RequestMethod.GET})
    public String Table_CTResultMaster() {
        return "module/Cataloging/03_Accession_Maintenance/Table_CTResultMaster";
    }

    @RequestMapping(value={"/z3950"}, method={RequestMethod.GET})
    public String z3950() {
        return "module/Cataloging/05_z3950/zClient";
    }

    @RequestMapping(value={"/processingDelay"}, method={RequestMethod.GET})
    public String processingDelay() {
        return "module/Cataloging/05_z3950/processingDelay";
    }

    @RequestMapping(value={"/processingDelay2"}, method={RequestMethod.GET})
    public String processingDelay2() {
        return "module/Cataloging/05_z3950/processingDelay2";
    }

    @RequestMapping(value={"/zView"}, method={RequestMethod.GET})
    public String zView() {
        return "module/Cataloging/05_z3950/zView";
    }

    @RequestMapping(value={"/zList"}, method={RequestMethod.GET})
    public String zList() {
        return "module/Cataloging/05_z3950/zList";
    }

    @RequestMapping(value={"/zDownloadMarc"}, method={RequestMethod.GET})
    public String zDownloadMarc() {
        return "module/Cataloging/05_z3950/zDownloadMarc";
    }

    @RequestMapping(value={"/index"}, method={RequestMethod.GET})
    public String index() {
        return "template/index";
    }

    @RequestMapping(value={"/Modal_OfficerID"}, method={RequestMethod.GET})
    public String Modal_OfficerID() {
        return "include/shared/Modal_OfficerID";
    }

    @RequestMapping(value={"/SearchPatron12"}, method={RequestMethod.GET})
    public String SearchPatron() {
        System.out.println("tester");
        return "include/shared/SearchPatron";
    }
}
