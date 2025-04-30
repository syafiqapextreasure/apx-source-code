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
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

@WebServlet(value={"/GeneratePreviewDocument"})
public class GeneratePreviewDocument
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dsfsfdsfdss");
        String JSON = request.getParameter("json");
        System.out.println(JSON);
        String reminderType = request.getParameter("reminderType");
        String liferayLogin = request.getParameter("liferayLogin");
        String officetype = request.getParameter("officetype");
        System.out.println(String.valueOf(reminderType) + "officetype" + officetype);
        String htmlText = null;
        ServletContext servletContext = this.getServletContext();
        try {
            htmlText = Document.PreviewDocument(JSON, reminderType, liferayLogin, servletContext, officetype);
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
        Gson gson = new Gson();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson((Object)htmlText));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
