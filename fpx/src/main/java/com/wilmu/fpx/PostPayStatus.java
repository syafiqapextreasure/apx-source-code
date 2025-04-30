/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu.fpx;

import com.wilmu.fpx.UpdatePayStatus;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/PostPayStatus"})
public class PostPayStatus
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pOrderNo = request.getParameter("pOrderNo");
        System.out.println("pOrderNo" + pOrderNo);
        boolean isSuccess = UpdatePayStatus.updatePayStatus(pOrderNo);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        if (isSuccess) {
            response.getWriter().print("true");
        } else {
            response.getWriter().print("false");
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
