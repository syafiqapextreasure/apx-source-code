/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.foundation.SerialFrequency;

import com.ilmu.foundation.SerialFrequency.SQLStatement;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddSerialFrequency"})
public class AddSerialFrequency
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String GL49FREQ = request.getParameter("GL49FREQ");
        String GL49DESC = request.getParameter("GL49DESC");
        String GL49TIME = request.getParameter("GL49TIME");
        String GL49ALERT = request.getParameter("GL49ALERT");
        String GL49PTYPE = request.getParameter("GL49PTYPE");
        SQLStatement.AddSerial(GL49FREQ, GL49DESC, GL49TIME, GL49ALERT, GL49PTYPE);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/17_SerialFrequency&ACTION=SerialTable.jsp");
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }
}
