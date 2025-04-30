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
package com.kmlink.ilmu.overdueNotification;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.overdueNotification.OverdueNotification;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/OverdueNotificationList"})
public class OverdueNotificationList
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object> tableList = new ArrayList();
        String retrieveType = request.getParameter("retrieveType");
        String unprintedDate = request.getParameter("unprintedDate");
        String reprintDate = request.getParameter("reprintDate");
        String inputPatronId = request.getParameter("inputPatronId");
        String patronCategoryId = request.getParameter("patronCategoryId");
        String branchId = request.getParameter("branchId");
        String reminder = request.getParameter("reminder");
        String address = request.getParameter("address");
        String officerIdStatus = request.getParameter("officerIdStatus");
        String officerId = request.getParameter("officerId");
        String patronStatusId = request.getParameter("patronStatusId");
        try {
            tableList = OverdueNotification.getTableList(retrieveType, unprintedDate, reprintDate, inputPatronId, patronCategoryId, branchId, reminder, address, officerId, patronStatusId);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(tableList, new TypeToken<List<OverdueNotification>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
