/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.json.JSONException
 */
package com.kmlink.ilmu.overdueNotification;

import com.google.gson.Gson;
import com.kmlink.ilmu.shared.pdf.Document;
import com.kmlink.ilmu.shared.pdf.saveMail;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

@WebServlet(value={"/PrintOverdueNotification"})
public class PrintOverdueNotification
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String JSON = request.getParameter("json");
        String liferayLogin = request.getParameter("sender");
        String patronId = request.getParameter("patronId");
        String reminderType = request.getParameter("reminderType");
        ServletContext servletContext = this.getServletContext();
        String htmlText = null;
        try {
            htmlText = Document.SendNotificationToMailBrowser(JSON, reminderType, servletContext);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        int mailno = saveMail.Get98VALUE("MAILNO");
        int newmailno = new Integer(mailno + 1);
        try {
            saveMail.updatingmailno(newmailno);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String filteredPatronId = patronId.substring(0, patronId.indexOf(44));
        String filteredPatronName = patronId.substring(patronId.lastIndexOf(",") + 1);
        try {
            saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER06");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (reminderType != null) {
            Gson gson = new Gson();
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(gson.toJson((Object)htmlText));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
