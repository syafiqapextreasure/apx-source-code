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
package com.ilmu.enquiry.IncomeDetailFPX;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.enquiry.IncomeDetailFPX.IncomeDetailFPX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/resultIncomeDetailFPX"})
public class resultIncomeDetailFPX
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String txtFrom = request.getParameter("txtFrom");
        System.out.println("txtFrom : " + txtFrom);
        String txtTo = request.getParameter("txtTo");
        System.out.println("txtTo : " + txtTo);
        String patrCate = request.getParameter("patrCate");
        System.out.println("patrCate : " + patrCate);
        String brancodeValue = request.getParameter("brancodeValue");
        System.out.println("brancodeValue : " + brancodeValue);
        String patrCateValue = request.getParameter("patrCateValue");
        if (patrCate.equals("Y")) {
            patrCateValue = patrCateValue.replaceAll("[\\[\\]]", "");
            patrCateValue = patrCateValue.replace("\"", "'");
        }
        System.out.println(String.valueOf(patrCateValue) + "patrCateValue");
        String chkBranch = request.getParameter("chkBranch");
        System.out.println("chkBranch : " + chkBranch);
        String branchVal = request.getParameter("branchVal");
        if (chkBranch.equals("Y")) {
            branchVal = branchVal.replaceAll("[\\[\\]]", "");
            branchVal = branchVal.replace("\"", "'");
        }
        System.out.println("branchVal" + branchVal + "branchVal");
        String chkPayment = request.getParameter("chkPayment");
        System.out.println("chkPayment : " + chkPayment);
        String payVal = request.getParameter("payVal");
        if (chkPayment.equals("Y")) {
            payVal = payVal.replaceAll("[\\[\\]]", "");
            payVal = payVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(payVal) + "payVal");
        List<Object> tableIncomeDetailFPX = new ArrayList();
        tableIncomeDetailFPX = IncomeDetailFPX.GetSQLStmt(txtFrom, txtTo, patrCate, brancodeValue, patrCateValue, chkBranch, branchVal, chkPayment, payVal);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(tableIncomeDetailFPX, new TypeToken<List<IncomeDetailFPX>>(){}.getType());
        System.out.println("tableIncomeDetailFPX : " + element);
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
