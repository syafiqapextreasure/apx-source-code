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
package com.ilmu.foundation.VendorFeedback;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.foundation.VendorFeedback.VendorFeedback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/resultSearchVendorFeedback"})
public class resultSearchVendorFeedback
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String input_criteria = request.getParameter("input_criteria");
        String search_type = request.getParameter("search_type");
        List<Object> searchVendorFeedback = new ArrayList();
        searchVendorFeedback = VendorFeedback.searchatVendFeedback(input_criteria, search_type);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(searchVendorFeedback, new TypeToken<List<VendorFeedback>>(){}.getType());
        System.out.println("searchVendorFeedback : " + element);
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
