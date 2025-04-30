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

import com.ilmu.foundation.ItemCategory.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddItemCat"})
public class AddItemCat
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL10ICAT = request.getParameter("GL10ICAT");
        System.out.println(String.valueOf(GL10ICAT) + " GL10ICAT");
        String GL10DESC = request.getParameter("GL10DESC");
        System.out.println(String.valueOf(GL10DESC) + " GL10DESC");
        String GL10DISPLAY = request.getParameter("GL10DISPLAY");
        System.out.println(String.valueOf(GL10DISPLAY) + " GL10DISPLAY");
        String GL10ELIG = request.getParameter("GL10ELIG");
        if (GL10ELIG.equals(null) || GL10ELIG.equals("")) {
            GL10ELIG = "0";
        }
        System.out.println(String.valueOf(GL10ELIG) + " GL10ELIG");
        String GL10UNIT = request.getParameter("GL10UNIT");
        System.out.println(String.valueOf(GL10UNIT) + " GL10UNIT");
        String GL10RESERVEC = request.getParameter("GL10RESERVEC");
        System.out.println(String.valueOf(GL10RESERVEC) + " GL10RESERVEC");
        if (GL10RESERVEC != null) {
            GL10RESERVEC = "Y";
            System.out.println("GL10RESERVEC null");
        } else {
            System.out.println("GL10RESERVEC");
            GL10RESERVEC = "N";
        }
        SQLStatement.AddItemCat(GL10ICAT, GL10DESC, GL10DISPLAY, GL10ELIG, GL10UNIT, GL10RESERVEC);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/13_ItemCategory&ACTION=ItemCatTable.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
}
