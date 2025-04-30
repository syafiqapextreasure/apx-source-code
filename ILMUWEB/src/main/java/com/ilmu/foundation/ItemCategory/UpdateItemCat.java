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
package com.ilmu.foundation.ItemCategory;

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

@WebServlet(value={"/UpdateItemCat"})
public class UpdateItemCat
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL10ICAT = request.getParameter("icat");
        String GL10DESC = request.getParameter("GL10DESC");
        String GL10DISPLAY = request.getParameter("GL10DISPLAY");
        String GL10ELIG = request.getParameter("GL10ELIG");
        String GL10UNIT = request.getParameter("GL10UNIT");
        String GL10RESERVEC = request.getParameter("GL10RESERVEC");
        if (GL10RESERVEC != null) {
            GL10RESERVEC = "Y";
            System.out.println("GL10RESERVEC null");
        } else {
            System.out.println("GL10RESERVEC");
            GL10RESERVEC = "N";
        }
        emp.setGL10ICAT(request.getParameter("icat"));
        emp.setGL10DESC(request.getParameter("GL10DESC"));
        emp.setGL10DISPLAY(request.getParameter("GL10DISPLAY"));
        emp.setGL10ELIG(request.getParameter("GL10ELIG"));
        emp.setGL10UNIT(request.getParameter("GL10UNIT"));
        emp.setGL10RESERVEC(GL10RESERVEC);
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateItemCat(emp);
        request.setAttribute("GL10ICAT", (Object)GL10ICAT);
        request.setAttribute("GL10DESC", (Object)GL10DESC);
        request.setAttribute("GL10DISPLAY", (Object)GL10DISPLAY);
        request.setAttribute("GL10ELIG", (Object)GL10ELIG);
        request.setAttribute("GL10UNIT", (Object)GL10UNIT);
        request.setAttribute("GL10RESERVEC", (Object)GL10RESERVEC);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/13_ItemCategory&ACTION=ItemCatTable.jsp");
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
