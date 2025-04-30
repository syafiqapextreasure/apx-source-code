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
package com.ilmu.foundation.PatronStatus;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.foundation.global.GlobalSQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/UpdatePatronStatus"})
public class UpdatePatronStatus
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL08STAT = request.getParameter("Status");
        System.out.println(GL08STAT);
        String GL08DESC = request.getParameter("GL08DESC");
        System.out.println(GL08DESC);
        String GL08ACTION1 = request.getParameter("GL08ACTION1");
        System.out.println(String.valueOf(GL08ACTION1) + " GL08ACTION1");
        String GL08ACTION2 = request.getParameter("GL08ACTION2");
        System.out.println(String.valueOf(GL08ACTION2) + " GL08ACTION2");
        String GL08ACTION3 = request.getParameter("GL08ACTION3");
        String GL08ACTION4 = request.getParameter("GL08ACTION4");
        String GL08ACTION5 = request.getParameter("GL08ACTION5");
        String GL08ACTION6 = request.getParameter("GL08ACTION6");
        String GL08ACTION7 = request.getParameter("GL08ACTION7");
        String GL08ACTION8 = request.getParameter("GL08ACTION8");
        String GL08ACTION9 = request.getParameter("GL08ACTION9");
        String GL08ACTION10 = request.getParameter("GL08ACTION10");
        String GL08ACTION11 = request.getParameter("GL08ACTION11");
        String GL08ACTION12 = request.getParameter("GL08ACTION12");
        String GL08ACTION13 = request.getParameter("GL08ACTION13");
        String GL08ACTION14 = request.getParameter("GL08ACTION14");
        String GL08ACTION15 = request.getParameter("GL08ACTION15");
        String GL08ACTION16 = request.getParameter("GL08ACTION16");
        String GL08ACTION17 = request.getParameter("GL08ACTION17");
        String GL08ACTION18 = request.getParameter("GL08ACTION18");
        String GL08ACTION19 = request.getParameter("GL08ACTION19");
        String GL08ACTION20 = request.getParameter("GL08ACTION20");
        GL08ACTION1 = GL08ACTION1 != null ? "Y" : "N";
        GL08ACTION2 = GL08ACTION2 != null ? "Y" : "N";
        GL08ACTION3 = GL08ACTION3 != null ? "Y" : "N";
        GL08ACTION4 = GL08ACTION4 != null ? "Y" : "N";
        GL08ACTION5 = GL08ACTION5 != null ? "Y" : "N";
        GL08ACTION6 = GL08ACTION6 != null ? "Y" : "N";
        GL08ACTION7 = GL08ACTION7 != null ? "Y" : "N";
        GL08ACTION8 = GL08ACTION8 != null ? "Y" : "N";
        GL08ACTION9 = GL08ACTION9 != null ? "Y" : "N";
        GL08ACTION10 = GL08ACTION10 != null ? "Y" : "N";
        GL08ACTION11 = GL08ACTION11 != null ? "Y" : "N";
        GL08ACTION12 = GL08ACTION12 != null ? "Y" : "N";
        GL08ACTION13 = GL08ACTION13 != null ? "Y" : "N";
        GL08ACTION14 = GL08ACTION14 != null ? "Y" : "N";
        GL08ACTION15 = GL08ACTION15 != null ? "Y" : "N";
        GL08ACTION16 = GL08ACTION16 != null ? "Y" : "N";
        GL08ACTION17 = GL08ACTION17 != null ? "Y" : "N";
        GL08ACTION18 = GL08ACTION18 != null ? "Y" : "N";
        GL08ACTION19 = GL08ACTION19 != null ? "Y" : "N";
        GL08ACTION20 = GL08ACTION20 != null ? "Y" : "N";
        emp.setGL08STAT(request.getParameter("Status"));
        emp.setGL08DESC(request.getParameter("GL08DESC"));
        emp.setGL08ACTION1(GL08ACTION1);
        emp.setGL08ACTION2(GL08ACTION2);
        emp.setGL08ACTION3(GL08ACTION3);
        emp.setGL08ACTION4(GL08ACTION4);
        emp.setGL08ACTION5(GL08ACTION5);
        emp.setGL08ACTION6(GL08ACTION6);
        emp.setGL08ACTION7(GL08ACTION7);
        emp.setGL08ACTION8(GL08ACTION8);
        emp.setGL08ACTION9(GL08ACTION9);
        emp.setGL08ACTION10(GL08ACTION10);
        emp.setGL08ACTION11(GL08ACTION11);
        emp.setGL08ACTION12(GL08ACTION12);
        emp.setGL08ACTION13(GL08ACTION13);
        emp.setGL08ACTION14(GL08ACTION14);
        emp.setGL08ACTION15(GL08ACTION15);
        emp.setGL08ACTION16(GL08ACTION16);
        emp.setGL08ACTION17(GL08ACTION17);
        emp.setGL08ACTION18(GL08ACTION18);
        emp.setGL08ACTION19(GL08ACTION19);
        emp.setGL08ACTION20(GL08ACTION20);
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updatePatStat(emp);
        request.setAttribute("GL08STAT", (Object)GL08STAT);
        request.setAttribute("GL08DESC", (Object)GL08DESC);
        request.setAttribute("GL08ACTION1", (Object)GL08ACTION1);
        request.setAttribute("GL08ACTION2", (Object)GL08ACTION2);
        request.setAttribute("GL08ACTION3", (Object)GL08ACTION3);
        request.setAttribute("GL08ACTION4", (Object)GL08ACTION4);
        request.setAttribute("GL08ACTION5", (Object)GL08ACTION5);
        request.setAttribute("GL08ACTION6", (Object)GL08ACTION6);
        request.setAttribute("GL08ACTION7", (Object)GL08ACTION7);
        request.setAttribute("GL08ACTION8", (Object)GL08ACTION8);
        request.setAttribute("GL08ACTION9", (Object)GL08ACTION9);
        request.setAttribute("GL08ACTION10", (Object)GL08ACTION10);
        request.setAttribute("GL08ACTION11", (Object)GL08ACTION11);
        request.setAttribute("GL08ACTION12", (Object)GL08ACTION12);
        request.setAttribute("GL08ACTION13", (Object)GL08ACTION13);
        request.setAttribute("GL08ACTION14", (Object)GL08ACTION14);
        request.setAttribute("GL08ACTION15", (Object)GL08ACTION15);
        request.setAttribute("GL08ACTION16", (Object)GL08ACTION16);
        request.setAttribute("GL08ACTION17", (Object)GL08ACTION17);
        request.setAttribute("GL08ACTION18", (Object)GL08ACTION18);
        request.setAttribute("GL08ACTION19", (Object)GL08ACTION19);
        request.setAttribute("GL08ACTION20", (Object)GL08ACTION20);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/16_PatronStatus&ACTION=StatusTable.jsp");
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
