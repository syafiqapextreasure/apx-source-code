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

import com.ilmu.foundation.DepartmentCode.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddDept"})
public class AddDept
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL11DEPT = request.getParameter("GL11DEPT");
        String GL11NAME = request.getParameter("GL11NAME");
        String GL11ADD1 = request.getParameter("GL11ADD1");
        System.out.println(GL11ADD1);
        String GL11ADD2 = request.getParameter("GL11ADD2");
        String GL11ADD3 = request.getParameter("GL11ADD3");
        String GL11POSCODE = request.getParameter("GL11POSCODE");
        String GL11TOWN = request.getParameter("GL11TOWN");
        String GL11TEL = request.getParameter("GL11TEL");
        String GL11HEAD = request.getParameter("GL11HEAD");
        String GL11STAFF = request.getParameter("GL11STAFF");
        String GL11FAX = request.getParameter("GL11FAX");
        SQLStatement.AddDeptCode(GL11DEPT, GL11NAME, GL11ADD1, GL11ADD2, GL11ADD3, GL11POSCODE, GL11TOWN, GL11TEL, GL11HEAD, GL11STAFF, GL11FAX);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/05_DepartmentCode&ACTION=DeptTable.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
}
