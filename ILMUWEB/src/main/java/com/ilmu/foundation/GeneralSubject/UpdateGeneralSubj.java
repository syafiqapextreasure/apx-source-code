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
package com.ilmu.foundation.GeneralSubject;

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

@WebServlet(value={"/UpdateGeneralSubj"})
public class UpdateGeneralSubj
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL54SUBJSTA = request.getParameter("Start");
        String GL54SUBJEND = request.getParameter("End");
        String GL54MARK = request.getParameter("GL54MARK");
        String GL54DESC = request.getParameter("GL54DESC");
        emp.setGL54SUBJSTA(request.getParameter("Start"));
        emp.setGL54SUBJEND(request.getParameter("End"));
        emp.setGL54MARK(request.getParameter("GL54MARK"));
        emp.setGL54DESC(request.getParameter("GL54DESC"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateGenSubj(emp);
        request.setAttribute("GL54SUBJSTA", (Object)GL54SUBJSTA);
        request.setAttribute("GL54SUBJEND", (Object)GL54SUBJEND);
        request.setAttribute("GL54MARK", (Object)GL54MARK);
        request.setAttribute("GL54DESC", (Object)GL54DESC);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp");
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
