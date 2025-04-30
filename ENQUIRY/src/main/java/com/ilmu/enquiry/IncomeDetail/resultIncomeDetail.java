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
package com.ilmu.enquiry.IncomeDetail;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.ilmu.enquiry.IncomeDetail.IncomeDetail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/resultIncomeDetail"})
public class resultIncomeDetail
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
        String chkOfficer = request.getParameter("chkOfficer");
        System.out.println("chkOfficer : " + chkOfficer);
        String officer = request.getParameter("officer");
        System.out.println("officer : " + officer);
        String chkBranch = request.getParameter("chkBranch");
        System.out.println("chkBranch : " + chkBranch);
        String branchVal = request.getParameter("branchVal");
        if (chkBranch.equals("Y")) {
            branchVal = branchVal.replaceAll("[\\[\\]]", "");
            branchVal = branchVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(patrCateValue) + "branchVal");
        String chkPaymentMode = request.getParameter("chkPaymentMode");
        System.out.println("chkPaymentMode : " + chkPaymentMode);
        String paymodeVal = request.getParameter("paymodeVal");
        if (chkPaymentMode.equals("Y")) {
            paymodeVal = paymodeVal.replaceAll("[\\[\\]]", "");
            paymodeVal = paymodeVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(paymodeVal) + "paymodeVal");
        String chkCharge = request.getParameter("chkCharge");
        System.out.println("chkCharge : " + chkCharge);
        String chargeVal = request.getParameter("chargeVal");
        if (chkCharge.equals("Y")) {
            chargeVal = chargeVal.replaceAll("[\\[\\]]", "");
            chargeVal = chargeVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(chargeVal) + "chargeVal");
        String chkPayment = request.getParameter("chkPayment");
        System.out.println("chkPayment : " + chkPayment);
        String payVal = request.getParameter("payVal");
        if (chkPayment.equals("Y")) {
            payVal = payVal.replaceAll("[\\[\\]]", "");
            payVal = payVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(payVal) + "payVal");
        String chkOverride = request.getParameter("chkOverride");
        System.out.println("chkOverride : " + chkCharge);
        String overrideVal = request.getParameter("overrideVal");
        if (chkOverride.equals("Y")) {
            overrideVal = overrideVal.replaceAll("[\\[\\]]", "");
            overrideVal = overrideVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(overrideVal) + "overrideVal");
        String chkOthers = request.getParameter("chkOthers");
        System.out.println("chkOthers : " + chkOthers);
        String otherVal = request.getParameter("otherVal");
        if (chkOthers.equals("Y")) {
            otherVal = otherVal.replaceAll("[\\[\\]]", "");
            otherVal = otherVal.replace("\"", "'");
        }
        System.out.println(String.valueOf(otherVal) + "otherVal");
        List<Object> tableIncomeDetail = new ArrayList();
        tableIncomeDetail = IncomeDetail.GetSQLStmt(txtFrom, txtTo, patrCate, brancodeValue, patrCateValue, chkOfficer, officer, chkBranch, branchVal, chkPaymentMode, paymodeVal, chkCharge, chargeVal, chkPayment, payVal, chkOverride, overrideVal, chkOthers, otherVal);
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(tableIncomeDetail, new TypeToken<List<IncomeDetail>>(){}.getType());
        System.out.println("tableIncomeDetail : " + element);
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
