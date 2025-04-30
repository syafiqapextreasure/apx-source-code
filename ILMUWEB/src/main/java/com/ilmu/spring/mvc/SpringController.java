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

    @RequestMapping(value={"Modal_AddCode"}, method={RequestMethod.GET})
    public String Modal_AddCode() {
        return "module/Foundation/02_CodeTable/Modal_AddCode";
    }

    @RequestMapping(value={"/Handler_AddCodeTable"}, method={RequestMethod.GET})
    public String Handler_AddCodeTable() {
        return "module/Foundation/02_CodeTable/Handler_AddCodeTable";
    }

    @RequestMapping(value={"/Handler_DeleteCodeTable"}, method={RequestMethod.GET})
    public String Handler_DeleteCodeTable() {
        return "module/Foundation/02_CodeTable/Handler_DeleteCodeTable";
    }

    @RequestMapping(value={"Modal_ViewCode"}, method={RequestMethod.GET})
    public String Modal_ViewCode() {
        return "module/Foundation/02_CodeTable/Modal_ViewCode";
    }

    @RequestMapping(value={"Modal_EditCode"}, method={RequestMethod.GET})
    public String Modal_EditCode() {
        return "module/Foundation/02_CodeTable/Modal_EditCode";
    }

    @RequestMapping(value={"CodeSearch"}, method={RequestMethod.GET})
    public String CodeSearch() {
        return "module/Foundation/02_CodeTable/CodeSearch";
    }

    @RequestMapping(value={"SearchCode"}, method={RequestMethod.GET})
    public String SearchCode() {
        return "module/Foundation/02_CodeTable/SearchCode";
    }

    @RequestMapping(value={"Modal_AddBranch"}, method={RequestMethod.GET})
    public String Modal_AddBranch() {
        return "module/Foundation/01_BranchCode/Modal_AddBranch";
    }

    @RequestMapping(value={"Modal_ViewBranch"}, method={RequestMethod.GET})
    public String Modal_ViewBranch() {
        return "module/Foundation/01_BranchCode/Modal_ViewBranch";
    }

    @RequestMapping(value={"Modal_EditBranch"}, method={RequestMethod.GET})
    public String Modal_EditBranch() {
        return "module/Foundation/01_BranchCode/Modal_EditBranch";
    }

    @RequestMapping(value={"/Handler_DeleteBranch"}, method={RequestMethod.GET})
    public String Handler_DeleteBranch() {
        return "module/Foundation/01_BranchCode/Handler_DeleteBranch";
    }

    @RequestMapping(value={"/Handler_AddBranch"}, method={RequestMethod.GET})
    public String Handler_AddBranch() {
        return "module/Foundation/01_BranchCode/Handler_AddBranch";
    }

    @RequestMapping(value={"/SearchBranch"}, method={RequestMethod.GET})
    public String SearchBranch() {
        return "module/Foundation/01_BranchCode/SearchBranch";
    }

    @RequestMapping(value={"/BranchSearch"}, method={RequestMethod.GET})
    public String BranchSearch() {
        return "module/Foundation/01_BranchCode/BranchSearch";
    }

    @RequestMapping(value={"Modal_AddSMD"}, method={RequestMethod.GET})
    public String Modal_AddSMD() {
        return "module/Foundation/15_SMD/Modal_AddSMD";
    }

    @RequestMapping(value={"Modal_ViewSMD"}, method={RequestMethod.GET})
    public String Modal_ViewSMD() {
        return "module/Foundation/15_SMD/Modal_ViewSMD";
    }

    @RequestMapping(value={"Modal_EditSMD"}, method={RequestMethod.GET})
    public String Modal_EditSMD() {
        return "module/Foundation/15_SMD/Modal_EditSMD";
    }

    @RequestMapping(value={"/Handler_DeleteSMD"}, method={RequestMethod.GET})
    public String Handler_DeleteSMD() {
        return "module/Foundation/15_SMD/Handler_DeleteSMD";
    }

    @RequestMapping(value={"/SearchSmd"}, method={RequestMethod.GET})
    public String SearchSmd() {
        return "module/Foundation/15_SMD/SearchSmd";
    }

    @RequestMapping(value={"/SmdSearch"}, method={RequestMethod.GET})
    public String SmdSearch() {
        return "module/Foundation/15_SMD/SmdSearch";
    }

    @RequestMapping(value={"Modal_AddSubject"}, method={RequestMethod.GET})
    public String Modal_AddSubject() {
        return "module/Foundation/09_GeneralSubject/Modal_AddSubject";
    }

    @RequestMapping(value={"Modal_ViewSubject"}, method={RequestMethod.GET})
    public String Modal_ViewSubject() {
        return "module/Foundation/09_GeneralSubject/Modal_ViewSubject";
    }

    @RequestMapping(value={"Modal_EditSubject"}, method={RequestMethod.GET})
    public String Modal_EditSubject() {
        return "module/Foundation/09_GeneralSubject/Modal_EditSubject";
    }

    @RequestMapping(value={"/SearchSubject"}, method={RequestMethod.GET})
    public String SearchSubject() {
        return "module/Foundation/09_GeneralSubject/SearchSubject";
    }

    @RequestMapping(value={"/SubjectSearch"}, method={RequestMethod.GET})
    public String SubjectSearch() {
        return "module/Foundation/09_GeneralSubject/SubjectSearch";
    }

    @RequestMapping(value={"/Handler_AddGenSubj"}, method={RequestMethod.GET})
    public String Handler_AddGenSubj() {
        return "module/Foundation/09_GeneralSubject/Handler_AddGenSubj";
    }

    @RequestMapping(value={"/Handler_DeleteSubj"}, method={RequestMethod.GET})
    public String Handler_DeleteSubj() {
        return "module/Foundation/09_GeneralSubject/Handler_DeleteSubj";
    }

    @RequestMapping(value={"Modal_AddStatus"}, method={RequestMethod.GET})
    public String Modal_AddStatus() {
        return "module/Foundation/16_PatronStatus/Modal_AddStatus";
    }

    @RequestMapping(value={"Modal_ViewStatus"}, method={RequestMethod.GET})
    public String Modal_ViewStatus() {
        return "module/Foundation/16_PatronStatus/Modal_ViewStatus";
    }

    @RequestMapping(value={"Modal_EditStatus"}, method={RequestMethod.GET})
    public String Modal_EditStatus() {
        return "module/Foundation/16_PatronStatus/Modal_EditStatus";
    }

    @RequestMapping(value={"/Handler_DeleteStatus"}, method={RequestMethod.GET})
    public String Handler_DeleteStatus() {
        return "module/Foundation/16_PatronStatus/Handler_DeleteStatus";
    }

    @RequestMapping(value={"/Handler_AddStatus"}, method={RequestMethod.GET})
    public String Handler_AddStatus() {
        return "module/Foundation/16_PatronStatus/Handler_AddStatus";
    }

    @RequestMapping(value={"/SearchStatus"}, method={RequestMethod.GET})
    public String SearchStatus() {
        return "module/Foundation/16_PatronStatus/SearchStatus";
    }

    @RequestMapping(value={"/StatusSearch"}, method={RequestMethod.GET})
    public String StatusSearch() {
        return "module/Foundation/16_PatronStatus/StatusSearch";
    }

    @RequestMapping(value={"Modal_AddItemCat"}, method={RequestMethod.GET})
    public String Modal_AddItemCat() {
        return "module/Foundation/13_ItemCategory/Modal_AddItemCat";
    }

    @RequestMapping(value={"Modal_ViewItemCat"}, method={RequestMethod.GET})
    public String Modal_ViewItemCat() {
        return "module/Foundation/13_ItemCategory/Modal_ViewItemCat";
    }

    @RequestMapping(value={"Modal_EditItemCat"}, method={RequestMethod.GET})
    public String Modal_EditItemCat() {
        return "module/Foundation/13_ItemCategory/Modal_EditItemCat";
    }

    @RequestMapping(value={"/Handler_DeleteItemCat"}, method={RequestMethod.GET})
    public String Handler_DeleteItemCat() {
        return "module/Foundation/13_ItemCategory/Handler_DeleteItemCat";
    }

    @RequestMapping(value={"/Handler_AddItemCat"}, method={RequestMethod.GET})
    public String Handler_AddItemCat() {
        return "module/Foundation/13_ItemCategory/Handler_AddItemCat";
    }

    @RequestMapping(value={"SearchItem"}, method={RequestMethod.GET})
    public String SearchItem() {
        return "module/Foundation/13_ItemCategory/SearchItem";
    }

    @RequestMapping(value={"ItemSearch"}, method={RequestMethod.GET})
    public String ItemSearch() {
        return "module/Foundation/13_ItemCategory/ItemSearch";
    }

    @RequestMapping(value={"SearchLocation"}, method={RequestMethod.GET})
    public String SearchLocation() {
        return "module/Foundation/06_LocationCode/SearchLocation";
    }

    @RequestMapping(value={"LocaSearch"}, method={RequestMethod.GET})
    public String LocaSearch() {
        return "module/Foundation/06_LocationCode/LocaSearch";
    }

    @RequestMapping(value={"Modal_AddLocation"}, method={RequestMethod.GET})
    public String Modal_AddLocation() {
        return "module/Foundation/06_LocationCode/Modal_AddLocation";
    }

    @RequestMapping(value={"Modal_ViewLocation"}, method={RequestMethod.GET})
    public String Modal_ViewLocation() {
        return "module/Foundation/06_LocationCode/Modal_ViewLocation";
    }

    @RequestMapping(value={"Modal_EditLocation"}, method={RequestMethod.GET})
    public String Modal_Editlocation() {
        return "module/Foundation/06_LocationCode/Modal_EditLocation";
    }

    @RequestMapping(value={"/Handler_DeleteLocation"}, method={RequestMethod.GET})
    public String Handler_DeleteLocation() {
        return "module/Foundation/06_LocationCode/Handler_DeleteLocation";
    }

    @RequestMapping(value={"/Handler_AddLocation"}, method={RequestMethod.GET})
    public String Handler_AddLocation() {
        return "module/Foundation/06_LocationCode/Handler_AddLocation";
    }

    @RequestMapping(value={"AddWizard"}, method={RequestMethod.GET})
    public String AddWizard() {
        return "module/Foundation/18_PatronEligibility/AddWizard";
    }

    @RequestMapping(value={"MainPage"}, method={RequestMethod.GET})
    public String MainPage() {
        return "module/Foundation/18_PatronEligibility/MainPage";
    }

    @RequestMapping(value={"CheckRecords"}, method={RequestMethod.GET})
    public String CheckRecords() {
        return "module/Foundation/18_PatronEligibility/CheckRecords";
    }

    @RequestMapping(value={"Modal_ViewElig"}, method={RequestMethod.GET})
    public String Modal_ViewElig() {
        return "module/Foundation/18_PatronEligibility/Modal_ViewElig";
    }

    @RequestMapping(value={"Modal_SearchElig"}, method={RequestMethod.GET})
    public String Modal_SearchElig() {
        return "module/Foundation/18_PatronEligibility/Modal_SearchElig";
    }

    @RequestMapping(value={"/Handler_DeleteElig"}, method={RequestMethod.GET})
    public String Handler_DeleteElig() {
        return "module/Foundation/18_PatronEligibility/Handler_DeleteElig";
    }

    @RequestMapping(value={"SearchElig"}, method={RequestMethod.GET})
    public String SearchElig() {
        return "module/Foundation/18_PatronEligibility/SearchElig";
    }

    @RequestMapping(value={"/SearchEligCriteria"}, method={RequestMethod.GET})
    public String SearchEligCriteria() {
        return "module/Foundation/18_PatronEligibility/SearchEligCriteria";
    }

    @RequestMapping(value={"/CriterionSelection"}, method={RequestMethod.GET})
    public String CriterionSelection() {
        return "module/Foundation/18_PatronEligibility/CriterionSelection";
    }

    @RequestMapping(value={"Modal_EditElig"}, method={RequestMethod.GET})
    public String Modal_EditElig() {
        return "module/Foundation/18_PatronEligibility/Modal_EditElig";
    }

    @RequestMapping(value={"/Handler_AddElig"}, method={RequestMethod.GET})
    public String Handler_AddElig() {
        return "module/Foundation/18_PatronEligibility/Handler_AddElig";
    }

    @RequestMapping(value={"Modal_AddSerial"}, method={RequestMethod.GET})
    public String Modal_AddSerial() {
        return "module/Foundation/17_SerialFrequency/Modal_AddSerial";
    }

    @RequestMapping(value={"Modal_ViewSerial"}, method={RequestMethod.GET})
    public String Modal_ViewSerial() {
        return "module/Foundation/17_SerialFrequency/Modal_ViewSerial";
    }

    @RequestMapping(value={"Modal_EditSerial"}, method={RequestMethod.GET})
    public String Modal_EditSerial() {
        return "module/Foundation/17_SerialFrequency/Modal_EditSerial";
    }

    @RequestMapping(value={"/Handler_DeleteSerial"}, method={RequestMethod.GET})
    public String Handler_DeleteSerial() {
        return "module/Foundation/17_SerialFrequency/Handler_DeleteSerial";
    }

    @RequestMapping(value={"/Handler_AddSerial"}, method={RequestMethod.GET})
    public String Handler_AddSerial() {
        return "module/Foundation/17_SerialFrequency/Handler_AddSerial";
    }

    @RequestMapping(value={"/SerialSearch"}, method={RequestMethod.GET})
    public String SerialSearch() {
        return "module/Foundation/17_SerialFrequency/SerialSearch";
    }

    @RequestMapping(value={"/SearchSerial"}, method={RequestMethod.GET})
    public String SearchSerial() {
        return "module/Foundation/17_SerialFrequency/SearchSerial";
    }

    @RequestMapping(value={"Modal_AddPatCat"}, method={RequestMethod.GET})
    public String Modal_AddPatCat() {
        return "module/Foundation/14_PatronCategory/Modal_AddPatCat";
    }

    @RequestMapping(value={"Modal_ViewPatCat"}, method={RequestMethod.GET})
    public String Modal_ViewPatCat() {
        return "module/Foundation/14_PatronCategory/Modal_ViewPatCat";
    }

    @RequestMapping(value={"Modal_EditPatCat"}, method={RequestMethod.GET})
    public String Modal_EditPatCat() {
        return "module/Foundation/14_PatronCategory/Modal_EditPatCat";
    }

    @RequestMapping(value={"/Handler_DeletePatCate"}, method={RequestMethod.GET})
    public String Handler_DeletePatCate() {
        return "module/Foundation/14_PatronCategory/Handler_DeletePatCate";
    }

    @RequestMapping(value={"/Handler_AddPatCate"}, method={RequestMethod.GET})
    public String Handler_AddPatCate() {
        return "module/Foundation/14_PatronCategory/Handler_AddPatCate";
    }

    @RequestMapping(value={"/SearchCategory"}, method={RequestMethod.GET})
    public String SearchCategory() {
        return "module/Foundation/14_PatronCategory/SearchCategory";
    }

    @RequestMapping(value={"/CategorySearch"}, method={RequestMethod.GET})
    public String CategorySearch() {
        return "module/Foundation/14_PatronCategory/CategorySearch";
    }

    @RequestMapping(value={"Modal_AddVendor"}, method={RequestMethod.GET})
    public String Modal_AddVendor() {
        return "module/Foundation/07_VendorCode/Modal_AddVendor";
    }

    @RequestMapping(value={"Modal_ViewVendor"}, method={RequestMethod.GET})
    public String Modal_ViewVendor() {
        return "module/Foundation/07_VendorCode/Modal_ViewVendor";
    }

    @RequestMapping(value={"Modal_EditVendor"}, method={RequestMethod.GET})
    public String Modal_EditVendor() {
        return "module/Foundation/07_VendorCode/Modal_EditVendor";
    }

    @RequestMapping(value={"/Handler_DeleteVendor"}, method={RequestMethod.GET})
    public String Handler_DeleteVendor() {
        return "module/Foundation/07_VendorCode/Handler_DeleteVendor";
    }

    @RequestMapping(value={"/Handler_AddVendor"}, method={RequestMethod.GET})
    public String Handler_AddVendor() {
        return "module/Foundation/07_VendorCode/Handler_AddVendor";
    }

    @RequestMapping(value={"/SearchVendor"}, method={RequestMethod.GET})
    public String SearchVendor() {
        return "module/Foundation/07_VendorCode/SearchVendor";
    }

    @RequestMapping(value={"/VendorSearch"}, method={RequestMethod.GET})
    public String VendorSearch() {
        return "module/Foundation/07_VendorCode/VendorSearch";
    }

    @RequestMapping(value={"Modal_AddCurrency"}, method={RequestMethod.GET})
    public String Modal_AddCurrency() {
        return "module/Foundation/04_CurrencyCode/Modal_AddCurrency";
    }

    @RequestMapping(value={"Modal_ViewCurrency"}, method={RequestMethod.GET})
    public String Modal_ViewCurrency() {
        return "module/Foundation/04_CurrencyCode/Modal_ViewCurrency";
    }

    @RequestMapping(value={"Modal_EditCurrency"}, method={RequestMethod.GET})
    public String Modal_EditCurrency() {
        return "module/Foundation/04_CurrencyCode/Modal_EditCurrency";
    }

    @RequestMapping(value={"/Handler_DeleteCurrency"}, method={RequestMethod.GET})
    public String Handler_DeleteCurrency() {
        return "module/Foundation/04_CurrencyCode/Handler_DeleteCurrency";
    }

    @RequestMapping(value={"/Handler_AddCurrency"}, method={RequestMethod.GET})
    public String Handler_AddCurrency() {
        return "module/Foundation/04_CurrencyCode/Handler_AddCurrency";
    }

    @RequestMapping(value={"/SearchCurrency"}, method={RequestMethod.GET})
    public String SearchCurrency() {
        return "module/Foundation/04_CurrencyCode/SearchCurrency";
    }

    @RequestMapping(value={"/CurrencySearch"}, method={RequestMethod.GET})
    public String CurrencySearch() {
        return "module/Foundation/04_CurrencyCode/CurrencySearch";
    }

    @RequestMapping(value={"Modal_AddTag"}, method={RequestMethod.GET})
    public String Modal_AddTag() {
        return "module/Foundation/08_TagParameter/Modal_AddTag";
    }

    @RequestMapping(value={"Modal_ViewTag"}, method={RequestMethod.GET})
    public String Modal_ViewTag() {
        return "module/Foundation/08_TagParameter/Modal_ViewTag";
    }

    @RequestMapping(value={"Modal_EditTag"}, method={RequestMethod.GET})
    public String Modal_EditTag() {
        return "module/Foundation/08_TagParameter/Modal_EditTag";
    }

    @RequestMapping(value={"SearchTag"}, method={RequestMethod.GET})
    public String SearchTag() {
        return "module/Foundation/08_TagParameter/SearchTag";
    }

    @RequestMapping(value={"/Handler_AddTagP"}, method={RequestMethod.GET})
    public String Handler_AddTagP() {
        return "module/Foundation/08_TagParameter/Handler_AddTagP";
    }

    @RequestMapping(value={"Modal_AddCourse"}, method={RequestMethod.GET})
    public String Modal_AddCourse() {
        return "module/Foundation/03_CourseCode/Modal_AddCourse";
    }

    @RequestMapping(value={"Modal_ViewCourse"}, method={RequestMethod.GET})
    public String Modal_ViewCourse() {
        return "module/Foundation/03_CourseCode/Modal_ViewCourse";
    }

    @RequestMapping(value={"Modal_EditCourse"}, method={RequestMethod.GET})
    public String Modal_EditCourse() {
        return "module/Foundation/03_CourseCode/Modal_EditCourse";
    }

    @RequestMapping(value={"/Handler_DeleteCourse"}, method={RequestMethod.GET})
    public String Handler_DeleteCourse() {
        return "module/Foundation/03_CourseCode/Handler_DeleteCourse";
    }

    @RequestMapping(value={"/Handler_AddCourse"}, method={RequestMethod.GET})
    public String Handler_AddCourse() {
        return "module/Foundation/03_CourseCode/Handler_AddCourse";
    }

    @RequestMapping(value={"/Modal_PatronSearch"}, method={RequestMethod.GET})
    public String Modal_PatronSearch() {
        return "module/Foundation/03_CourseCode/Modal_PatronSearch";
    }

    @RequestMapping(value={"/SearchPatron"}, method={RequestMethod.GET})
    public String SearchPatron() {
        return "module/Foundation/03_CourseCode/SearchPatron";
    }

    @RequestMapping(value={"/SearchCriteria"}, method={RequestMethod.GET})
    public String SearchCriteria() {
        return "module/Foundation/10_PatronDetails/SearchCriteria";
    }

    @RequestMapping(value={"/CriteriaSearch"}, method={RequestMethod.GET})
    public String CriteriaSearch() {
        return "module/Foundation/10_PatronDetails/CriteriaSearch";
    }

    @RequestMapping(value={"Modal_AddPatron"}, method={RequestMethod.GET})
    public String Modal_AddPatron() {
        return "module/Foundation/10_PatronDetails/Modal_AddPatron";
    }

    @RequestMapping(value={"Modal_ViewPatron"}, method={RequestMethod.GET})
    public String Modal_ViewPatron() {
        return "module/Foundation/10_PatronDetails/Modal_ViewPatron";
    }

    @RequestMapping(value={"Modal_EditPatron"}, method={RequestMethod.GET})
    public String Modal_EditPatron() {
        System.out.println("Modal_EditPatron");
        return "module/Foundation/10_PatronDetails/Modal_EditPatron";
    }

    @RequestMapping(value={"/Handler_DeletePatDetail"}, method={RequestMethod.GET})
    public String Handler_DeletePatDetail() {
        return "module/Foundation/10_PatronDetails/Handler_DeletePatDetail";
    }

    @RequestMapping(value={"PatronTable"}, method={RequestMethod.GET})
    public String PatronTable() {
        return "module/Foundation/10_PatronDetails/PatronTable";
    }

    @RequestMapping(value={"Error_Message"}, method={RequestMethod.GET})
    public String Error_Message() {
        return "include/Error_Page";
    }

    @RequestMapping(value={"Error_Page"}, method={RequestMethod.GET})
    public String Error_Page() {
        return "include/shared/Error_Page";
    }

    @RequestMapping(value={"Exist_Page"}, method={RequestMethod.GET})
    public String Exist_Page() {
        return "module/Foundation/Test/Exist_Page";
    }

    @RequestMapping(value={"Modal_ViewDept"}, method={RequestMethod.GET})
    public String Modal_ViewDept() {
        return "module/Foundation/05_DepartmentCode/Modal_ViewDept";
    }

    @RequestMapping(value={"Modal_EditDept"}, method={RequestMethod.GET})
    public String Modal_EditDept() {
        return "module/Foundation/05_DepartmentCode/Modal_EditDept";
    }

    @RequestMapping(value={"Modal_AddDept"}, method={RequestMethod.GET})
    public String Modal_AddDept() {
        return "module/Foundation/05_DepartmentCode/Modal_AddDept";
    }

    @RequestMapping(value={"Handler_AddDept"}, method={RequestMethod.GET})
    public String Handler_AddDept() {
        return "module/Foundation/05_DepartmentCode/Handler_AddDept";
    }

    @RequestMapping(value={"Handler_DeleteDept"}, method={RequestMethod.GET})
    public String Handler_DeleteDept() {
        return "module/Foundation/05_DepartmentCode/Handler_DeleteDept";
    }

    @RequestMapping(value={"/SearchDept"}, method={RequestMethod.GET})
    public String SearchDept() {
        return "module/Foundation/05_DepartmentCode/SearchDept";
    }

    @RequestMapping(value={"/DeptSearch"}, method={RequestMethod.GET})
    public String DeptSearch() {
        return "module/Foundation/05_DepartmentCode/DeptSearch";
    }

    @RequestMapping(value={"/Handler_FetchHolidays"}, method={RequestMethod.GET})
    public String Handler_FetchHolidays() {
        return "module/Foundation/11_LibraryCalendar/Handler_FetchHolidays";
    }

    @RequestMapping(value={"/Handler_AddHoliday"}, method={RequestMethod.GET})
    public String Handler_AddHoliday() {
        return "module/Foundation/11_LibraryCalendar/Handler_AddHoliday";
    }

    @RequestMapping(value={"/Handler_EditHoliday"}, method={RequestMethod.GET})
    public String Handler_EditHoliday() {
        return "module/Foundation/11_LibraryCalendar/Handler_EditHoliday";
    }

    @RequestMapping(value={"/Handler_DeleteHoliday"}, method={RequestMethod.GET})
    public String Handler_DeleteHoliday() {
        return "module/Foundation/11_LibraryCalendar/Handler_DeleteHoliday";
    }

    @RequestMapping(value={"/Handler_AddEvent"}, method={RequestMethod.GET})
    public String Handler_AddEvent() {
        return "module/Foundation/11_LibraryCalendar/Handler_AddEvent";
    }

    @RequestMapping(value={"/Handler_DeleteEvent"}, method={RequestMethod.GET})
    public String Handler_DeleteEvent() {
        return "module/Foundation/11_LibraryCalendar/Handler_DeleteEvent";
    }

    @RequestMapping(value={"/Handler_EditEvent"}, method={RequestMethod.GET})
    public String Handler_EditEvent() {
        return "module/Foundation/11_LibraryCalendar/Handler_EditEvent";
    }

    @RequestMapping(value={"/Handler_AddWeekend"}, method={RequestMethod.GET})
    public String Handler_AddWeekend() {
        return "module/Foundation/11_LibraryCalendar/Handler_AddWeekend";
    }

    @RequestMapping(value={"/Handler_DeleteAndAdd"}, method={RequestMethod.GET})
    public String Handler_DeleteAndAdd() {
        return "module/Foundation/11_LibraryCalendar/Handler_DeleteAndAdd";
    }

    @RequestMapping(value={"/SearchCourse"}, method={RequestMethod.GET})
    public String SearchCourse() {
        return "module/Foundation/03_CourseCode/SearchCourse";
    }

    @RequestMapping(value={"/CourseSearch"}, method={RequestMethod.GET})
    public String CourseSearch() {
        return "module/Foundation/03_CourseCode/CourseSearch";
    }

    @RequestMapping(value={"/Handler_DocStaus"}, method={RequestMethod.GET})
    public String Handler_DocStaus() {
        return "module/Foundation/19_DocumentStatus/Handler_DocStaus";
    }

    @RequestMapping(value={"/Handler_SavePatrAttri"}, method={RequestMethod.GET})
    public String Handler_SavePatrAttri() {
        return "module/Foundation/20_PatrMiscellaneous/Handler_SavePatrAttri";
    }

    @RequestMapping(value={"/Handler_EditPatrAttri"}, method={RequestMethod.GET})
    public String Handler_EditPatrAttri() {
        return "module/Foundation/20_PatrMiscellaneous/Handler_EditPatrAttri";
    }

    @RequestMapping(value={"/Handler_DeletePatrAttri"}, method={RequestMethod.GET})
    public String Handler_DeletePatrAttri() {
        return "module/Foundation/20_PatrMiscellaneous/Handler_DeletePatrAttri";
    }

    @RequestMapping(value={"/Handler_AddAndEditAccessLevel"}, method={RequestMethod.GET})
    public String Handler_AddAndEditAccessLevel() {
        return "module/Foundation/21_ModuleAccessLevel/Handler_AddAndEditAccessLevel";
    }

    @RequestMapping(value={"/Handler_DeleteAccessLEvel"}, method={RequestMethod.GET})
    public String Handler_DeleteAccessLEvel() {
        return "module/Foundation/21_ModuleAccessLevel/Handler_DeleteAccessLEvel";
    }

    @RequestMapping(value={"/HandlerAddEditGroupId"}, method={RequestMethod.GET})
    public String HandlerAddEditGroupId() {
        return "module/Foundation/22_GroupId/HandlerAddEditGroupId";
    }

    @RequestMapping(value={"/Handler_DeleteGroupID"}, method={RequestMethod.GET})
    public String Handler_DeleteGroupID() {
        return "module/Foundation/22_GroupId/Handler_DeleteGroupID";
    }

    @RequestMapping(value={"/HandlerAddEditVendorFeedback"}, method={RequestMethod.GET})
    public String HandlerAddEditVendorFeedback() {
        return "module/Foundation/23_VendorFeedback/HandlerAddEditVendorFeedback";
    }

    @RequestMapping(value={"/Handler_DeleteVendorFeedback"}, method={RequestMethod.GET})
    public String Handler_DeleteVendorFeedback() {
        return "module/Foundation/23_VendorFeedback/Handler_DeleteVendorFeedback";
    }

    @RequestMapping(value={"/HandlerAddEditFlag"}, method={RequestMethod.GET})
    public String HandlerAddEditFlag() {
        return "module/Foundation/24_Flag/HandlerAddEditFlag";
    }

    @RequestMapping(value={"/Handler_DeleteFlag"}, method={RequestMethod.GET})
    public String Handler_DeleteFlag() {
        return "module/Foundation/24_Flag/Handler_DeleteFlag";
    }

    @RequestMapping(value={"/HandlerAddEditMenu"}, method={RequestMethod.GET})
    public String HandlerAddEditMenu() {
        return "module/Foundation/25_Menu/HandlerAddEditMenu";
    }

    @RequestMapping(value={"/Handler_DeleteMenu"}, method={RequestMethod.GET})
    public String Handler_DeleteMenu() {
        return "module/Foundation/25_Menu/Handler_DeleteMenu";
    }

    @RequestMapping(value={"GetTolNote"}, method={RequestMethod.GET})
    public String GetTolNote() {
        return "module/Foundation/10_PatronDetails/GetTolNote";
    }

    @RequestMapping(value={"/viewNote"}, method={RequestMethod.GET})
    public String viewNote() {
        System.out.println("viewNote");
        return "include/shared/viewNote";
    }

    @RequestMapping(value={"/Handler_ViewNote"}, method={RequestMethod.GET})
    public String Handler_ViewNote() {
        System.out.println("Handler_ViewNote");
        return "include/shared/Handler_ViewNote";
    }

    @RequestMapping(value={"/Handler_LibInfo"}, method={RequestMethod.GET})
    public String Handler_LibInfo() {
        System.out.println("Handler_LibInfo");
        return "module/Foundation/26_LibInfo/Handler_LibInfo";
    }

    @RequestMapping(value={"/Handler_compareGroup"}, method={RequestMethod.GET})
    public String Handler_compareGroup() {
        System.out.println("Handler_compareGroup");
        return "include/shared/Handler_compareGroup";
    }

    @RequestMapping(value={"/Handler_DeleteNote"}, method={RequestMethod.GET})
    public String Handler_DeleteNote() {
        System.out.println("Handler_DeleteNote");
        return "include/shared/Handler_DeleteNote";
    }

    @RequestMapping(value={"/Handler_editnote"}, method={RequestMethod.GET})
    public String Handler_editnote() {
        System.out.println("Handler_editnote");
        return "include/shared/Handler_editnote";
    }

    @RequestMapping(value={"/Modal_PatrSearch"}, method={RequestMethod.GET})
    public String Modal_PatrSearch() {
        System.out.println("qwerty");
        return "include/shared/Modal_PatrSearch";
    }

    @RequestMapping(value={"/SearchPatron2"}, method={RequestMethod.GET})
    public String SearchPatron2() {
        return "include/shared/SearchPatron2";
    }

    @RequestMapping(value={"/Loading"})
    public String Loader() {
        return "include/shared/Loader";
    }
}
