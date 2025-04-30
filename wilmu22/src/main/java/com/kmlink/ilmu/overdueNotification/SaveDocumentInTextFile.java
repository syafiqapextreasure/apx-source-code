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
package com.kmlink.ilmu.overdueNotification;

import com.google.gson.Gson;
import com.kmlink.ilmu.shared.pdf.Document;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/SaveDocumentInTextFile"})
public class SaveDocumentInTextFile
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mailNo = request.getParameter("mailNo");
        String accessionNo = request.getParameter("accessionNo");
        String patronId = request.getParameter("patronId");
        String title = request.getParameter("title");
        String callNo = request.getParameter("callNo");
        String dueDate = request.getParameter("dueDate");
        String lateBy = request.getParameter("lateBy");
        String fines = request.getParameter("fines");
        String filteredPatronId = patronId.substring(0, patronId.indexOf(44));
        String filteredPatronName = patronId.substring(patronId.lastIndexOf(",") + 1);
        String text = null;
        try {
            text = Document.TON01(mailNo, accessionNo, filteredPatronId, filteredPatronName, title, callNo, dueDate);
        }
        catch (IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        gson.toJson((Object)text);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(gson.toJson((Object)text));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
