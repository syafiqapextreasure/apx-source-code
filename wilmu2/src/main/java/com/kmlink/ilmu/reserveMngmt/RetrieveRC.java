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
package com.kmlink.ilmu.reserveMngmt;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.reserveMngmt.ReserveCollectionManagement;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/RetrieveRC"})
public class RetrieveRC
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userDefineRB = request.getParameter("userDefineRB");
        String fetchListRB = request.getParameter("fetchListRB");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String fetchList = request.getParameter("fetchList");
        List<ReserveCollectionManagement> retrieveRC = new ArrayList();
        try {
            retrieveRC = fetchListRB.equals("true") ? ReserveCollectionManagement.getFetchList(fetchList) : ReserveCollectionManagement.getRC(dateFrom, dateTo);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(retrieveRC, new TypeToken<List<ReserveCollectionManagement>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
