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
 *  javax.servlet.http.HttpSession
 */
package com.kmlink.ilmu.membershipRenewal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.kmlink.ilmu.membershipRenewal.MembershipRenewal;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/UpdateMembership"})
public class UpdateMembership
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object> returnNewExpDate = new ArrayList();
        String recordedBy = request.getParameter("recordedBy");
        HttpSession session = request.getSession();
        session.setAttribute("screenname: ", (Object)recordedBy);
        String patronId = request.getParameter("patronId");
        String expDate = request.getParameter("expDate");
        String newExpDate = request.getParameter("newExpDate");
        String fee = request.getParameter("fee");
        BigDecimal BDfee = new BigDecimal(fee);
        try {
            returnNewExpDate = MembershipRenewal.updateMembership(patronId, expDate, newExpDate, recordedBy, BDfee);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(returnNewExpDate, new TypeToken<List<MembershipRenewal>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(jsonArray);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
