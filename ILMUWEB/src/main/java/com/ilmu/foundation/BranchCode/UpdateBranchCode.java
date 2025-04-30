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
package com.ilmu.foundation.BranchCode;

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

@WebServlet(value={"/UpdateBranchCode"})
public class UpdateBranchCode
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Foundation emp = new Foundation();
        String GL71BRNC = request.getParameter("Branch");
        String GL71DESC = request.getParameter("GL71DESC");
        String GL71DISPLAY = request.getParameter("GL71DISPLAY");
        String GL71ADD1 = request.getParameter("GL71ADD1");
        String GL71ADD2 = request.getParameter("GL71ADD2");
        String GL71ADD3 = request.getParameter("GL71ADD3");
        String GL71TOWN = request.getParameter("GL71TOWN");
        String GL71POSCODE = request.getParameter("GL71POSCODE");
        String phonenoedit = request.getParameter("phonenoedit");
        System.out.println("phonenoedit" + phonenoedit);
        String emailedit = request.getParameter("emailedit");
        emp.setGL71BRNC(request.getParameter("Branch"));
        emp.setGL71DESC(request.getParameter("GL71DESC"));
        emp.setGL71DISPLAY(request.getParameter("GL71DISPLAY"));
        emp.setGL71ADD1(request.getParameter("GL71ADD1"));
        emp.setGL71ADD2(request.getParameter("GL71ADD2"));
        emp.setGL71ADD3(request.getParameter("GL71ADD3"));
        emp.setGL71TOWN(request.getParameter("GL71TOWN"));
        emp.setGL71POSCODE(request.getParameter("GL71POSCODE"));
        emp.setHtel(request.getParameter("phonenoedit"));
        emp.setIPADD(request.getParameter("emailedit"));
        GlobalSQLStatement eb = new GlobalSQLStatement();
        eb.updateBrnch(emp);
        request.setAttribute("GL71BRNC", (Object)GL71BRNC);
        request.setAttribute("GL71DESC", (Object)GL71DESC);
        request.setAttribute("GL71DISPLAY", (Object)GL71DISPLAY);
        request.setAttribute("GL71ADD1", (Object)GL71ADD1);
        request.setAttribute("GL71ADD2", (Object)GL71ADD2);
        request.setAttribute("GL71ADD3", (Object)GL71ADD3);
        request.setAttribute("GL71TOWN", (Object)GL71TOWN);
        request.setAttribute("GL71POSCODE", (Object)GL71POSCODE);
        request.setAttribute("GL71HTEL", (Object)phonenoedit);
        request.setAttribute("GL71IPADD", (Object)emailedit);
        RequestDispatcher view = this.getServletContext().getRequestDispatcher("/template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp");
        view.forward((ServletRequest)request, (ServletResponse)response);
    }
}
