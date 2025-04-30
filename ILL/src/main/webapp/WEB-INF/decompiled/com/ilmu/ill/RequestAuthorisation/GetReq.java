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
package com.ilmu.ill.RequestAuthorisation;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.ill.RequestAuthorisation.RequestAuthorisation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetReq"})
public class GetReq
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputEndDate;
        String requestor = request.getParameter("requestor");
        String libID = request.getParameter("libID");
        String inputStartDate = request.getParameter("inputStartDate");
        if (inputStartDate.equals("Invalid date")) {
            inputStartDate = "";
        }
        if ((inputEndDate = request.getParameter("inputEndDate")).equals("Invalid date")) {
            inputEndDate = "";
        }
        String isreqidCheck = request.getParameter("isreqidCheck");
        String islibidCheck = request.getParameter("islibidCheck");
        String isdateCheck = request.getParameter("isdateCheck");
        List<Object> sqlreqauthor = new ArrayList();
        sqlreqauthor = RequestAuthorisation.LoadRecordset(requestor, libID, inputStartDate, inputEndDate, isreqidCheck, islibidCheck, isdateCheck);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(sqlreqauthor, new TypeToken<List<RequestAuthorisation>>(){}.getType());
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
