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
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddEligibility"})
public class SaveEligibilityServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        System.out.println("GL27CATE" + GL27CATE);
        String GL27ICAT = request.getParameter("icat");
        String GL27SMD = request.getParameter("smd");
        String GL27BRNC = request.getParameter("branch");
        String recby = request.getParameter("recby");
        SQLStatement.addEligibility(cate, icats, smds, brnchs, periodOfLoan, eligibilityNum, renewalNum, overGP1, overGP2, overGP3, timesCostBy, lostFee, fineGP1, fineGP2, fineGP3, doOverdue, doReserve, typeOfLoan, inFine, maxFines, lateFroms, lateTos, rates, fines, recby);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/18_PatronEligibility&ACTION=MainPage.jsp");
        view.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
