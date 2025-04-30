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
package com.ilmu.foundation.SerialFrequency;

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

@WebServlet(value={"/UpdateSerial"})
public class UpdateSerial
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL49FREQ = request.getParameter("Frequency");
        String GL49DESC = request.getParameter("GL49DESC");
        String GL49TIME = request.getParameter("GL49TIME");
        String GL49ALERT = request.getParameter("GL49ALERT");
        String GL49PTYPE = request.getParameter("GL49PTYPE");
        emp.setGL49FREQ(request.getParameter("Frequency"));
        emp.setGL49DESC(request.getParameter("GL49DESC"));
        emp.setGL49TIME(request.getParameter("GL49TIME"));
        emp.setGL49ALERT(request.getParameter("GL49ALERT"));
        emp.setGL49PTYPE(request.getParameter("GL49PTYPE"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateSerial(emp);
        request.setAttribute("GL49FREQ", (Object)GL49FREQ);
        request.setAttribute("GL49DESC", (Object)GL49DESC);
        request.setAttribute("GL49TIME", (Object)GL49TIME);
        request.setAttribute("GL49ALERT", (Object)GL49ALERT);
        request.setAttribute("GL49PTYPE", (Object)GL49PTYPE);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/17_SerialFrequency&ACTION=SerialTable.jsp");
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
