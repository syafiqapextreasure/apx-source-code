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
package com.ilmu.acquisition.ordermaintenance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.acquisition.ordermaintenance.Ordermaintenance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/getOrdermaintenance5"})
public class getOrdermaintenance5
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderNo = request.getParameter("rowid");
        System.out.println("orderNo5 = " + orderNo);
        List<Object> sqlsOrderMaint5 = new ArrayList();
        sqlsOrderMaint5 = Ordermaintenance.viewErrorInSupply(orderNo);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(sqlsOrderMaint5, new TypeToken<List<Ordermaintenance>>(){}.getType());
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
