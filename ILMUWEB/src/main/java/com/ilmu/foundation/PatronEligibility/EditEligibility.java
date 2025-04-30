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
package com.ilmu.foundation.PatronEligibility;

import com.ilmu.foundation.PatronEligibility.SQLStatement;
import com.ilmu.utilities.query.DBQuery;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/EditEligibility"})
public class EditEligibility
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess;
        String[] cate = request.getParameterValues("cates[]");
        String[] icats = request.getParameterValues("icats[]");
        String[] smds = request.getParameterValues("smds[]");
        String[] brnchs = request.getParameterValues("brnchs[]");
        String periodOfLoan = request.getParameter("periodOfLoan");
        String eligibilityNum = request.getParameter("eligibilityNum");
        String renewalNum = request.getParameter("renewalNum");
        String overGP1 = request.getParameter("overGP1");
        String overGP2 = request.getParameter("overGP2");
        String overGP3 = request.getParameter("overGP3");
        String timesCostBy = request.getParameter("timesCostBy");
        String lostFee = request.getParameter("lostFee");
        String fineGP1 = request.getParameter("fineGP1");
        String fineGP2 = request.getParameter("fineGP2");
        String fineGP3 = request.getParameter("fineGP3");
        String doOverdue = request.getParameter("doOverdue");
        String doReserve = request.getParameter("doReserve");
        String typeOfLoan = request.getParameter("typeOfLoan");
        String inFine = request.getParameter("inFine");
        String maxFines = request.getParameter("maxFines");
        String[] lateFroms = request.getParameterValues("lateFroms[]");
        String[] lateTos = request.getParameterValues("lateTos[]");
        String[] rates = request.getParameterValues("rates[]");
        String[] fines = request.getParameterValues("fines[]");
        String GL27CATE = request.getParameter("category");
        String GL27ICAT = request.getParameter("icat");
        String GL27SMD = request.getParameter("smd");
        String GL27BRNC = request.getParameter("branch");
        String regeneratefine = request.getParameter("regeneratefine");
        String recby = request.getParameter("recby");
        System.out.println("regeneratefine :" + regeneratefine);
        ArrayList<String> queryList = new ArrayList<String>();
        if (regeneratefine.equals("N")) {
            queryList.add(SQLStatement.deleteEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC));
            isSuccess = DBQuery.runBatchQuery(queryList);
            if (isSuccess) {
                SQLStatement.editEligibilityWithoutFine(cate, icats, smds, brnchs, periodOfLoan, eligibilityNum, renewalNum, overGP1, overGP2, overGP3, timesCostBy, lostFee, fineGP1, fineGP2, fineGP3, doOverdue, doReserve, typeOfLoan, inFine, maxFines, lateFroms, lateTos, rates, fines, recby);
            }
        } else if (regeneratefine.equals("Y")) {
            queryList.add(SQLStatement.deleteEligibility(GL27CATE, GL27ICAT, GL27SMD, GL27BRNC));
            queryList.add(SQLStatement.deleteFinesSchedule(GL27CATE, GL27ICAT, GL27BRNC, GL27SMD));
            isSuccess = DBQuery.runBatchQuery(queryList);
            if (isSuccess) {
                SQLStatement.addEligibility(cate, icats, smds, brnchs, periodOfLoan, eligibilityNum, renewalNum, overGP1, overGP2, overGP3, timesCostBy, lostFee, fineGP1, fineGP2, fineGP3, doOverdue, doReserve, typeOfLoan, inFine, maxFines, lateFroms, lateTos, rates, fines, recby);
            }
        }
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp");
        view.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
