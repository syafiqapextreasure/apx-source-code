/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.ill.receiveIll;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.ilmu.ill.receiveIll.ReceiveILL;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetIllLegth"})
public class GetIllLegth
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sqlAccession = ReceiveILL.illlength();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree((Object)sqlAccession);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(element);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
