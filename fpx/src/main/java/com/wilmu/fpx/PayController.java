/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu.fpx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmu.fpx.Payload;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Direct2"})
public class PayController
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("masuklahhh line 29");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Payload payload = (Payload)mapper.readValue((InputStream)request.getInputStream(), Payload.class);
        String subsysId = payload.getOrderNo();
        String password = payload.getStatus();
        String orderNo = payload.getTxnTime();
        String description = payload.getAmount();
        String txnTime = payload.getTxnReference();
        String feeCode = payload.getTxnId();
        String amount = payload.getBankName();
        response.setStatus(200);
        response.getWriter().println("Received subsysId: " + subsysId);
        response.getWriter().println("Received password: " + password);
        response.getWriter().println("Received orderNo: " + orderNo);
        response.getWriter().println("Received description: " + description);
        response.getWriter().println("Received txnTime: " + txnTime);
        response.getWriter().println("Received feeCode: " + feeCode);
        response.getWriter().println("Received amount: " + amount);
    }
}
