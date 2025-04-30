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
package com.kmlink.ilmu.circulation.Weeding;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.circulation.Weeding.SQLStatusMaintenance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/detailRecall"})
public class detailRecall
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CT03MATNO = request.getParameter("CT03MATNO");
        System.out.println(String.valueOf(CT03MATNO) + "f");
        String CT03DOCNO = request.getParameter("CT03DOCNO");
        System.out.println(String.valueOf(CT03DOCNO) + "ff");
        List<Object> sqlgetDetailRecall = new ArrayList();
        sqlgetDetailRecall = SQLStatusMaintenance.getRecallDetail(CT03MATNO, CT03DOCNO);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(sqlgetDetailRecall, new TypeToken<List<SQLStatusMaintenance>>(){}.getType());
        System.out.println(element);
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
