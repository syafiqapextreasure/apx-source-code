/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.reflect.TypeToken
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.mailBrowser.email.MailBrowser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/MailBrowserView"})
public class MailBrowserView
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mailNo = request.getParameter("mailNo");
        MailBrowser mail = MailBrowser.get(Integer.parseInt(mailNo));
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree((Object)mail, new TypeToken<MailBrowser>(){}.getType());
        JsonObject obj = element.getAsJsonObject();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(obj);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String time = request.getParameter("time");
        String mailNo = request.getParameter("mailNo");
        MailBrowser mail = MailBrowser.delete(time, mailNo);
    }
}
