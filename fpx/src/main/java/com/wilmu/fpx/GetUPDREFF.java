/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu.fpx;

import com.google.gson.Gson;
import com.wilmu.fpx.TxnoVal;
import com.wilmu.fpx.UpdRefCount;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetUPDREFF"})
public class GetUPDREFF
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderNo = request.getParameter("orderNo");
        System.out.println("GetUPDREF orderNo : " + orderNo);
        String getTXNO = TxnoVal.re01txno(orderNo.substring(3));
        System.out.println(getTXNO);
        String totalrecord = UpdRefCount.totalRetxn(getTXNO);
        Gson gson = new Gson();
        String json = gson.toJson((Object)totalrecord);
        System.out.println("totalrecord" + totalrecord);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/json");
        response.getWriter().print(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
