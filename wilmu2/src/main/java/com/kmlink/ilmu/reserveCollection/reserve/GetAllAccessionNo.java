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
package com.kmlink.ilmu.reserveCollection.reserve;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.reserveCollection.reserve.AccessionNo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetAllAccessionNo"})
public class GetAllAccessionNo
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String controlNo = request.getParameter("controlNo");
        try {
            List<AccessionNo> getAllAccessionNo = AccessionNo.getAllAccessionNo(controlNo);
            Gson gson = new Gson();
            JsonElement element = gson.toJsonTree(getAllAccessionNo, new TypeToken<List<AccessionNo>>(){}.getType());
            JsonArray jsonArray = element.getAsJsonArray();
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/json");
            response.getWriter().print(jsonArray);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
