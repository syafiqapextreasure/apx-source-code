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
package com.ilmu.foundation.SMD;

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

@WebServlet(value={"/UpdateSMD"})
public class UpdateSMD
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL47SMD = request.getParameter("SMD");
        String GL47DESC = request.getParameter("GL47DESC");
        String GL47DISPLAY = request.getParameter("GL47DISPLAY");
        emp.setGL47SMD(request.getParameter("SMD"));
        emp.setGL47DESC(request.getParameter("GL47DESC"));
        emp.setGL47DISPLAY(request.getParameter("GL47DISPLAY"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateSMD(emp);
        request.setAttribute("GL47SMD", (Object)GL47SMD);
        request.setAttribute("GL47DESC", (Object)GL47DESC);
        request.setAttribute("GL47DISPLAY", (Object)GL47DISPLAY);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/15_SMD&ACTION=SMDTable.jsp");
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
