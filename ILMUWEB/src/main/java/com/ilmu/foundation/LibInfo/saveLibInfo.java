/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.foundation.LibInfo;

import com.ilmu.foundation.global.libInfo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/saveLibInfo"})
public class saveLibInfo
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        libInfo lib = new libInfo();
        String LibraryName = request.getParameter("GL28NAME");
        System.out.println(String.valueOf(LibraryName) + " LibraryName");
        String OrganizationName = request.getParameter("GL28ORGNAME");
        System.out.println(String.valueOf(OrganizationName) + " OrganizationName");
        String LibrarySymbol = request.getParameter("GL28LIBSYMBOL");
        System.out.println(String.valueOf(LibrarySymbol) + " LibrarySymbol");
        String BranchCode = request.getParameter("GL28BRNC");
        System.out.println(String.valueOf(BranchCode) + " BranchCode");
        String Address1 = request.getParameter("GL28ADD1");
        System.out.println(String.valueOf(Address1) + " Address1");
        String Address2 = request.getParameter("GL28ADD2");
        System.out.println(String.valueOf(Address2) + " Address2");
        String Address3 = request.getParameter("GL28ADD3");
        System.out.println(String.valueOf(Address3) + " Address3");
        String Postcode = request.getParameter("GL28POSCODE");
        System.out.println(String.valueOf(Postcode) + " Postcode");
        String Town = request.getParameter("GL28TOWN");
        System.out.println(String.valueOf(Town) + " Town");
        String Telephone = request.getParameter("GL28TEL");
        System.out.println(String.valueOf(Telephone) + " Telephone");
        String Fax = request.getParameter("GL28FAX");
        System.out.println(String.valueOf(Fax) + " Fax");
        String HeadLibrarian = request.getParameter("GL28HEADLIB");
        System.out.println(String.valueOf(HeadLibrarian) + " HeadLibrarian");
        String HeadLibrarianTelExt = request.getParameter("GL28LIBHEADEXT");
        System.out.println(String.valueOf(HeadLibrarianTelExt) + " HeadLibrarianTelExt");
        String PROfficer = request.getParameter("GL28PROHEAD");
        System.out.println(String.valueOf(PROfficer) + " PROfficer");
        String PROfficerTelExt = request.getParameter("GL28PROEXT");
        System.out.println(String.valueOf(PROfficerTelExt) + " PROfficerTelExt");
        String LibraryLogo = request.getParameter("GL28LOGO");
        System.out.println(String.valueOf(LibraryLogo) + " LibraryLogo");
        String AcquisitionHead = request.getParameter("GL28ACQHEAD");
        System.out.println(String.valueOf(AcquisitionHead) + " AcquisitionHead");
        String GracePeriod1 = request.getParameter("GL28ACQCLAIMS1");
        System.out.println(String.valueOf(GracePeriod1) + " GracePeriod1");
        String GracePeriod2 = request.getParameter("GL28ACQCLAIMS2");
        System.out.println(String.valueOf(GracePeriod2) + " GracePeriod2");
        String GracePeriod3 = request.getParameter("GL28ACQCLAIMS3");
        System.out.println(String.valueOf(GracePeriod3) + " GracePeriod3");
        String OrderSpan = request.getParameter("GL28ORDERSPAN");
        System.out.println(String.valueOf(OrderSpan) + " OrderSpan");
        String BatchOrderPrinting = request.getParameter("GL28ACQBPRINT");
        System.out.println(String.valueOf(BatchOrderPrinting) + " BatchOrderPrinting");
        String CatalogHead = request.getParameter("GL28CATHEAD");
        System.out.println(String.valueOf(CatalogHead) + " CatalogHead");
        String CatalogHeadTelExt = request.getParameter("GL28CATEXT");
        System.out.println(String.valueOf(CatalogHeadTelExt) + " CatalogHeadTelExt");
        String BaseMARCType = request.getParameter("GL28MARCTYPE");
        System.out.println(String.valueOf(BaseMARCType) + " BaseMARCType");
        String IndexingThresholdTime = request.getParameter("GL28INXTRASH");
        System.out.println(String.valueOf(IndexingThresholdTime) + " IndexingThresholdTime");
        String CircHead = request.getParameter("GL28CIRHEAD");
        System.out.println(String.valueOf(CircHead) + " CircHead");
        String CircHeadTelExt = request.getParameter("GL28CIREXT");
        System.out.println(String.valueOf(CircHeadTelExt) + " CircHeadTelExt");
        String LibraryWeekends = request.getParameter("GL28LIBWEEKEND");
        System.out.println(String.valueOf(LibraryWeekends) + " LibraryWeekends");
        String ReservationTimeFrame = request.getParameter("GL28RESVTIME");
        System.out.println(String.valueOf(ReservationTimeFrame) + " ReservationTimeFrame");
        String DefaultReplacementCostforLostBook = request.getParameter("GL28RCOST");
        System.out.println(String.valueOf(DefaultReplacementCostforLostBook) + " DefaultReplacementCostforLostBook");
        String timesCostofLostBook = request.getParameter("GL28TIMESCOST");
        System.out.println(String.valueOf(timesCostofLostBook) + " timesCostofLostBook");
        String DueDateCalculation = request.getParameter("GL28CIRCMODE");
        System.out.println(String.valueOf(DueDateCalculation) + " DueDateCalculation");
        String OPACRetrievalLimit = request.getParameter("GL28OPACLIMIT");
        System.out.println(String.valueOf(OPACRetrievalLimit) + " OPACRetrievalLimit");
        String SerialsHead = request.getParameter("GL28SERHEAD");
        System.out.println(String.valueOf(SerialsHead) + " SerialsHead");
        String SerialsHeadTelExt = request.getParameter("GL28SEREXT");
        System.out.println(String.valueOf(SerialsHeadTelExt) + " SerialsHeadTelExt");
        String BatchOrderPrintingSerial = request.getParameter("GL28SERBPRINT");
        System.out.println(String.valueOf(BatchOrderPrintingSerial) + " BatchOrderPrintingSerial");
        String IRSHead = request.getParameter("GL28IRSHEAD");
        System.out.println(String.valueOf(IRSHead) + " IRSHead");
        String IRSHeadTelExt = request.getParameter("GL28IRSEXT");
        System.out.println(String.valueOf(IRSHeadTelExt) + " IRSHeadTelExt");
        String IRSMARCType = request.getParameter("GL28IRSMARCTYPE");
        System.out.println(String.valueOf(IRSMARCType) + " IRSMARCType");
        String SystemGeneratedNumber = request.getParameter("GL28IRSFLAG");
        System.out.println(String.valueOf(SystemGeneratedNumber) + " SystemGeneratedNumber");
        String FinanceController = request.getParameter("GL28FINHEAD");
        System.out.println(String.valueOf(FinanceController) + " FinanceController");
        String FinanceControllerTelExt = request.getParameter("GL28FINEXT");
        System.out.println(String.valueOf(FinanceControllerTelExt) + " FinanceControllerTelExt");
        String FundCode = request.getParameter("GL28FUND");
        System.out.println(String.valueOf(FundCode) + " FundCode");
        BatchOrderPrinting = BatchOrderPrinting != null ? "Y" : "N";
        BatchOrderPrintingSerial = BatchOrderPrintingSerial != null ? "Y" : "N";
        lib.setGL28NAME(request.getParameter("GL28NAME"));
        lib.setGL28ORGNAME(request.getParameter("GL28ORGNAME"));
        lib.setGL28LIBSYMBOL(request.getParameter("GL28LIBSYMBOL"));
        lib.setGL28BRNC(request.getParameter("GL28BRNC"));
        lib.setGL28ADD1(request.getParameter("GL28ADD1"));
        lib.setGL28ADD2(request.getParameter("GL28ADD2"));
        lib.setGL28ADD3(request.getParameter("GL28ADD3"));
        lib.setGL28POSCODE(request.getParameter("GL28POSCODE"));
        lib.setGL28TOWN(request.getParameter("GL28TOWN"));
        lib.setGL28TEL(request.getParameter("GL28TEL"));
        lib.setGL28FAX(request.getParameter("GL28FAX"));
        lib.setGL28HEADLIB(request.getParameter("GL28HEADLIB"));
        lib.setGL28LIBHEADEXT(request.getParameter("GL28LIBHEADEXT"));
        lib.setGL28PROHEAD(request.getParameter("GL28PROHEAD"));
        lib.setGL28PROEXT(request.getParameter("GL28PROEXT"));
        lib.setGL28LOGO(request.getParameter("GL28LOGO"));
        lib.setGL28ACQHEAD(request.getParameter("GL28ACQHEAD"));
        lib.setGL28ACQCLAIMS1(request.getParameter("GL28ACQCLAIMS1"));
        lib.setGL28ACQCLAIMS2(request.getParameter("GL28ACQCLAIMS2"));
        lib.setGL28ACQCLAIMS3(request.getParameter("GL28ACQCLAIMS3"));
        lib.setGL28ORDERSPAN(request.getParameter("GL28ORDERSPAN"));
        lib.setGL28ACQBPRINT(request.getParameter(BatchOrderPrinting));
        lib.setGL28CATHEAD(request.getParameter("GL28CATHEAD"));
        lib.setGL28CATEXT(request.getParameter("GL28CATEXT"));
        lib.setGL28MARCTYPE(request.getParameter("GL28MARCTYPE"));
        lib.setGL28INXTRASH(request.getParameter("GL28INXTRASH"));
        lib.setGL28CIRHEAD(request.getParameter("GL28CIRHEAD"));
        lib.setGL28CIREXT(request.getParameter("GL28CIREXT"));
        lib.setGL28LIBWEEKEND(request.getParameter("GL28LIBWEEKEND"));
        lib.setGL28RESVTIME(request.getParameter("GL28RESVTIME"));
        lib.setGL28RCOST(request.getParameter("GL28RCOST"));
        lib.setGL28TIMESCOST(request.getParameter("GL28TIMESCOST"));
        lib.setGL28CIRCMODE(request.getParameter("GL28CIRCMODE"));
        lib.setGL28OPACLIMIT(request.getParameter("GL28OPACLIMIT"));
        lib.setGL28SERHEAD(request.getParameter("GL28SERHEAD"));
        lib.setGL28SEREXT(request.getParameter("GL28SEREXT"));
        lib.setGL28SERBPRINT(request.getParameter(BatchOrderPrintingSerial));
        lib.setGL28IRSHEAD(request.getParameter("GL28IRSHEAD"));
        lib.setGL28IRSEXT(request.getParameter("GL28IRSEXT"));
        lib.setGL28IRSMARCTYPE(request.getParameter("GL28IRSMARCTYPE"));
        lib.setGL28IRSFLAG(request.getParameter("GL28IRSFLAG"));
        lib.setGL28FINHEAD(request.getParameter("GL28FINHEAD"));
        lib.setGL28FINEXT(request.getParameter("GL28FINEXT"));
        lib.setGL28FUND(request.getParameter("GL28FUND"));
        request.setAttribute("GL28NAME", (Object)LibraryName);
        request.setAttribute("GL28ORGNAME", (Object)OrganizationName);
        request.setAttribute("GL28LIBSYMBOL", (Object)LibrarySymbol);
        request.setAttribute("GL28BRNC", (Object)BranchCode);
        request.setAttribute("GL28ADD1", (Object)Address1);
        request.setAttribute("GL28ADD2", (Object)Address2);
        request.setAttribute("GL28ADD3", (Object)Address3);
        request.setAttribute("GL28POSCODE", (Object)Postcode);
        request.setAttribute("GL28TOWN", (Object)Town);
        request.setAttribute("GL28TEL", (Object)Telephone);
        request.setAttribute("GL28FAX", (Object)Fax);
        request.setAttribute("GL28HEADLIB", (Object)HeadLibrarian);
        request.setAttribute("GL28LIBHEADEXT", (Object)HeadLibrarianTelExt);
        request.setAttribute("GL28PROHEAD", (Object)PROfficer);
        request.setAttribute("GL28PROEXT", (Object)PROfficerTelExt);
        request.setAttribute("GL28LOGO", (Object)LibraryLogo);
        request.setAttribute("GL28ACQHEAD", (Object)AcquisitionHead);
        request.setAttribute("GL28ACQCLAIMS1", (Object)GracePeriod1);
        request.setAttribute("GL28ACQCLAIMS2", (Object)GracePeriod2);
        request.setAttribute("GL28ACQCLAIMS3", (Object)GracePeriod3);
        request.setAttribute("GL28ORDERSPAN", (Object)OrderSpan);
        request.setAttribute("GL28ACQBPRINT", (Object)BatchOrderPrinting);
        request.setAttribute("GL28CATHEAD", (Object)CatalogHead);
        request.setAttribute("GL28CATEXT", (Object)CatalogHeadTelExt);
        request.setAttribute("GL28MARCTYPE", (Object)BaseMARCType);
        request.setAttribute("GL28INXTRASH", (Object)IndexingThresholdTime);
        request.setAttribute("GL28CIRHEAD", (Object)CircHead);
        request.setAttribute("GL28CIREXT", (Object)CircHeadTelExt);
        request.setAttribute("GL28LIBWEEKEND", (Object)LibraryWeekends);
        request.setAttribute("GL28RESVTIME", (Object)ReservationTimeFrame);
        request.setAttribute("GL28RCOST", (Object)DefaultReplacementCostforLostBook);
        request.setAttribute("GL28TIMESCOST", (Object)timesCostofLostBook);
        request.setAttribute("GL28CIRCMODE", (Object)DueDateCalculation);
        request.setAttribute("GL28OPACLIMIT", (Object)OPACRetrievalLimit);
        request.setAttribute("GL28SERHEAD", (Object)SerialsHead);
        request.setAttribute("GL28SEREXT", (Object)SerialsHeadTelExt);
        request.setAttribute("GL28SERBPRINT", (Object)BatchOrderPrintingSerial);
        request.setAttribute("GL28IRSHEAD", (Object)IRSHead);
        request.setAttribute("GL28IRSEXT", (Object)IRSHeadTelExt);
        request.setAttribute("GL28IRSMARCTYPE", (Object)IRSMARCType);
        request.setAttribute("GL28IRSFLAG", (Object)SystemGeneratedNumber);
        request.setAttribute("GL28FINHEAD", (Object)FinanceController);
        request.setAttribute("GL28FINEXT", (Object)FinanceControllerTelExt);
        request.setAttribute("GL28FUND", (Object)FundCode);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/12_LibraryInformation&ACTION=LibInfo.jsp");
        view.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
