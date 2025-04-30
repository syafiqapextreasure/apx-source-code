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
package com.ilmu.foundation.CodeTable;

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

@WebServlet(value={"/UpdateCode"})
public class UpdateCode
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String CODE = request.getParameter("CODE2");
        String DESCRIPTION = request.getParameter("DESCRIPTION");
        String FCODE = request.getParameter("FCODE");
        emp.setCODE(request.getParameter("CODE2"));
        emp.setDESCRIPTION(request.getParameter("DESCRIPTION"));
        emp.setFCODE(request.getParameter("FCODE"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateGlobal(emp);
        request.setAttribute("CODE", (Object)CODE);
        request.setAttribute("DESCRIPTION", (Object)DESCRIPTION);
        request.setAttribute("FCODE", (Object)FCODE);
        request.setAttribute("msg", (Object)"Success Update");
        String url = "/template?MODULE=Foundation/02_CodeTable&ACTION=CodeTable.jsp?value=" + FCODE;
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
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
