/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.reflect.TypeToken
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.parableTemplateMaint.Style;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetStyleComponents"})
public class GetStyleComponents
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String template = request.getParameter("selectedTemplate");
        String branch = request.getParameter("selectedBranch");
        String selectedStyle = request.getParameter("selectedAttribute");
        List<Style> styleList = Style.getStyle(template, branch, selectedStyle);
        JsonElement element = gson.toJsonTree(styleList, new TypeToken<List<Style>>(){}.getType());
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(element);
    }

    public void getHtmlTable(String htmlTable) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
