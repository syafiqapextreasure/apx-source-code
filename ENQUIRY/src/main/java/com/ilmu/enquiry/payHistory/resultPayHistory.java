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
package com.ilmu.enquiry.payHistory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.enquiry.payHistory.payHistory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/resultPayHistory"})
public class resultPayHistory
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String module = request.getParameter("module");
        String vendor = request.getParameter("vendor");
        String radioOption1 = request.getParameter("radioOption1");
        System.out.println("radioOption1 : " + radioOption1);
        String radioOption2 = request.getParameter("radioOption2");
        String radioOption3 = request.getParameter("radioOption3");
        String inputstartDoc = request.getParameter("inputstartDoc");
        String inputendDoc = request.getParameter("inputendDoc");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Object> tablepayHistory = new ArrayList();
        tablepayHistory = payHistory.getpayHistoryTable(module, vendor, radioOption1, radioOption2, radioOption3, inputstartDoc, inputendDoc, startDate, endDate);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(tablepayHistory, new TypeToken<List<payHistory>>(){}.getType());
        System.out.println("tablepayHistory : " + element);
        JsonArray jsonArray = element.getAsJsonArray();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
