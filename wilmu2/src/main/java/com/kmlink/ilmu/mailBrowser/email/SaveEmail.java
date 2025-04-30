/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.reflect.TypeToken
 *  com.itextpdf.text.DocumentException
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
import com.itextpdf.text.DocumentException;
import com.kmlink.ilmu.mailBrowser.email.MailBrowser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/SaveEmail"})
public class SaveEmail
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mailNo = request.getParameter("mailNo");
        try {
            List<MailBrowser> mail = MailBrowser.saveEmail(Integer.parseInt(mailNo));
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(mail, new TypeToken<List<MailBrowser>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
