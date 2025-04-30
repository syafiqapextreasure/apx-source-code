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
package com.ilmu.foundation.CourseCode;

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

@WebServlet(value={"/UpdateCourse"})
public class UpdateCourse
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        String GL12COURSE = request.getParameter("Course");
        String GL12DESC = request.getParameter("GL12DESC");
        String patronId = request.getParameter("GL12TUTOR");
        System.out.println(String.valueOf(patronId) + " patronId");
        emp.setGL12COURSE(request.getParameter("Course"));
        emp.setGL12DESC(request.getParameter("GL12DESC"));
        emp.setGL12TUTOR(request.getParameter("GL12TUTOR"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateCourse(emp);
        request.setAttribute("GL12COURSE", (Object)GL12COURSE);
        request.setAttribute("GL12DESC", (Object)GL12DESC);
        request.setAttribute("patronId", (Object)patronId);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/03_CourseCode&ACTION=CourseTable.jsp");
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
