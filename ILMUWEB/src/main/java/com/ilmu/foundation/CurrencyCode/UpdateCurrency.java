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
package com.ilmu.foundation.CurrencyCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.foundation.global.GlobalSQLStatement;
import com.ilmu.global.DateFormatter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/UpdateCurrency"})
public class UpdateCurrency
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL24FOREX = request.getParameter("Currency");
        String GL24DESC = request.getParameter("GL24DESC");
        String GL24PRATE = request.getParameter("GL24PRATE");
        System.out.println(String.valueOf(GL24PRATE) + "GL24PRATE");
        String GL24BRATE = request.getParameter("GL24BRATE");
        String GL24PDATE = request.getParameter("GL24PDATE");
        String GL24BDATE = request.getParameter("GL24BDATE");
        System.out.println(String.valueOf(GL24PDATE) + " GL24PDATE");
        GL24PDATE = GL24PDATE.equals("-") ? "" : DateFormatter.displayToDBFormat(GL24PDATE);
        GL24BDATE = GL24BDATE.equals("-") ? "" : DateFormatter.displayToDBFormat(GL24BDATE);
        emp.setGL24FOREX(request.getParameter("Currency"));
        emp.setGL24DESC(request.getParameter("GL24DESC"));
        emp.setGL24PRATE(request.getParameter("GL24PRATE"));
        emp.setGL24BRATE(request.getParameter("GL24BRATE"));
        emp.setGL24PDATE(GL24PDATE);
        emp.setGL24BDATE(GL24BDATE);
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateCurrency(emp);
        request.setAttribute("GL24FOREX", (Object)GL24FOREX);
        request.setAttribute("GL24DESC", (Object)GL24DESC);
        request.setAttribute("GL24PRATE", (Object)GL24PRATE);
        request.setAttribute("GL24BRATE", (Object)GL24BRATE);
        request.setAttribute("GL24PDATE", (Object)GL24PDATE);
        request.setAttribute("GL24BDATE", (Object)GL24BDATE);
        String url = "/template?MODULE=Foundation/10_PatronDetails&ACTION=PatronTable.jsp";
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
