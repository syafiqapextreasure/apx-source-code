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
package com.ilmu.foundation.DepartmentCode;

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

@WebServlet(value={"/UpdateDeptCode"})
public class UpdateDeptCode
extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Foundation emp = new Foundation();
        System.out.println("Masuklahhhh");
        String GL11DEPT = request.getParameter("Department");
        String GL11NAME = request.getParameter("GL11NAME");
        String GL11ADD1 = request.getParameter("GL11ADD1");
        String GL11ADD2 = request.getParameter("GL11ADD2");
        String GL11ADD3 = request.getParameter("GL11ADD3");
        String GL11POSCODE = request.getParameter("GL11POSCODE");
        String GL11TOWN = request.getParameter("GL11TOWN");
        String GL11TEL = request.getParameter("GL11TEL");
        String GL11HEAD = request.getParameter("GL11HEAD");
        String GL11STAFF = request.getParameter("GL11STAFF");
        String GL11FAX = request.getParameter("GL11FAX");
        emp.setGL11DEPT(request.getParameter("Department"));
        emp.setGL11NAME(request.getParameter("GL11NAME"));
        emp.setGL11ADD1(request.getParameter("GL11ADD1"));
        emp.setGL11ADD2(request.getParameter("GL11ADD2"));
        emp.setGL11ADD3(request.getParameter("GL11ADD3"));
        emp.setGL11POSCODE(request.getParameter("GL11POSCODE"));
        emp.setGL11TOWN(request.getParameter("GL11TOWN"));
        emp.setGL11TEL(request.getParameter("GL11TEL"));
        emp.setGL11HEAD(request.getParameter("GL11HEAD"));
        emp.setGL11STAFF(Integer.parseInt(request.getParameter("GL11STAFF")));
        emp.setGL11FAX(request.getParameter("GL11FAX"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateDeptCode(emp);
        request.setAttribute("GL11DEPT", (Object)GL11DEPT);
        request.setAttribute("GL11NAME", (Object)GL11NAME);
        request.setAttribute("GL11ADD1", (Object)GL11ADD1);
        request.setAttribute("GL11ADD2", (Object)GL11ADD2);
        request.setAttribute("GL11ADD3", (Object)GL11ADD3);
        request.setAttribute("GL11POSCODE", (Object)GL11POSCODE);
        request.setAttribute("GL11TOWN", (Object)GL11TOWN);
        request.setAttribute("GL11TEL", (Object)GL11TEL);
        request.setAttribute("GL11HEAD", (Object)GL11HEAD);
        request.setAttribute("GL11STAFF", (Object)Integer.parseInt(GL11STAFF));
        request.setAttribute("GL11FAX", (Object)GL11FAX);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptTable.jsp");
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
