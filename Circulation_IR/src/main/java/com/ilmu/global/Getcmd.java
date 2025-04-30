/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.global;

import com.google.gson.Gson;
import com.ilmu.global.cmd;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Getcmd"})
public class Getcmd
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String desc = request.getParameter("desc");
        System.out.println("desc" + desc);
        String recpt = null;
        try {
            recpt = cmd.getCMDVal(desc);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson((Object)recpt);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
