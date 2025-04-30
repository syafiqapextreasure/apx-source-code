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
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.ILL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/OverideExpFlag"})
public class OverideExpFlag
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String GL99FUNC = "DUEBEFOREEXPIRY";
        String oExpFlag = null;
        oExpFlag = ILL.getOverExpUser(GL99FUNC);
        System.out.print("flag Expired User " + oExpFlag);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(oExpFlag);
    }
}
