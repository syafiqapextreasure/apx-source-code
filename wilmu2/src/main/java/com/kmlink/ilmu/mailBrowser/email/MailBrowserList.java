/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.reflect.TypeToken
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.mailBrowser.email;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.mailBrowser.email.MailBrowser;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/MailBrowserList"})
public class MailBrowserList
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String subject = request.getParameter("subject");
        String sendMailStatus = request.getParameter("sentMail");
        try {
            List<MailBrowser> mail = MailBrowser.getBySubject(startDate, endDate, subject, sendMailStatus);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(mail, new TypeToken<List<MailBrowser>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
