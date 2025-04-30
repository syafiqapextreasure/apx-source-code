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
package com.ilmu.enquiry.borrower;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.enquiry.borrower.borrower;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/resultBorrower"})
public class resultBorrower
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startSentDate = request.getParameter("startSentDate");
        String endSentDate = request.getParameter("endSentDate");
        String checkpatcat = request.getParameter("checkpatcat");
        String patcate = request.getParameter("patcate");
        String checkbrnch = request.getParameter("checkbrnch");
        String brnch = request.getParameter("brnch");
        String minBorrow = request.getParameter("minBorrow");
        List<Object> tableborrower = new ArrayList();
        tableborrower = borrower.getborrowerList(startSentDate, endSentDate, checkpatcat, patcate, checkbrnch, brnch, minBorrow);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(tableborrower, new TypeToken<List<borrower>>(){}.getType());
        System.out.println("tablepayHistory : " + element);
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
