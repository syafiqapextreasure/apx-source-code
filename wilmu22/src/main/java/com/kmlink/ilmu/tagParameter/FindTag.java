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
package com.kmlink.ilmu.tagParameter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.tagParameter.TagParameter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/FindTag"})
public class FindTag
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String marcId = request.getParameter("marcId");
        String marcDesc = request.getParameter("marcDesc");
        String selectedRadio = request.getParameter("selectedRadio");
        String userInput = request.getParameter("userInput");
        List<TagParameter> findTag = TagParameter.FindTag(marcId, marcDesc, selectedRadio, userInput);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(findTag, new TypeToken<List<TagParameter>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
