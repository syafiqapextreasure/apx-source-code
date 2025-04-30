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
package com.ilmu.circulation.Item_Status_Maintenance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.circulation.Item_Status_Maintenance.SQLStatusMaintenance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/searchAccessionAtStatusMaint3"})
public class searchAccessionAtStatusMaint3
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String CT03DOCNO = request.getParameter("AccessionNO");
        System.out.println(String.valueOf(CT03DOCNO) + "newPlan");
        List<Object> sqlaccesionStatusMaint3 = new ArrayList();
        sqlaccesionStatusMaint3 = SQLStatusMaintenance.getAccessionNoStatusMaint3(CT03DOCNO);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(sqlaccesionStatusMaint3, new TypeToken<List<SQLStatusMaintenance>>(){}.getType());
        System.out.println(element);
        JsonArray jsonArray = element.getAsJsonArray();
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
        System.out.println(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
