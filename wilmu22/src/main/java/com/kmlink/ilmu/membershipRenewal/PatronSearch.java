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
package com.kmlink.ilmu.membershipRenewal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.membershipRenewal.PatronDetail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/PatronSearch"})
public class PatronSearch
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchBy = request.getParameter("searchBy");
        String searchText = request.getParameter("searchText");
        String filteredBy = request.getParameter("filterBy");
        String sortedBy = request.getParameter("sortedBy");
        List<Object> patronDetail = new ArrayList();
        if (searchBy.equals("patronId")) {
            patronDetail = PatronDetail.searchByPatronId(searchBy, searchText, filteredBy, sortedBy);
        } else if (searchBy.equals("name")) {
            patronDetail = PatronDetail.searchByName(searchBy, searchText, filteredBy, sortedBy);
        } else if (searchBy.equals("newIC")) {
            patronDetail = PatronDetail.searchByNewIc(searchBy, searchText, filteredBy, sortedBy);
        } else if (searchBy.equals("oldIC")) {
            patronDetail = PatronDetail.searchByOldIc(searchBy, searchText, filteredBy, sortedBy);
        }
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(patronDetail, new TypeToken<List<PatronDetail>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
