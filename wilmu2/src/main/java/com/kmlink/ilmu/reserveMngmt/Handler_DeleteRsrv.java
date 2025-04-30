/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.kmlink.ilmu.reserveMngmt;

import com.kmlink.ilmu.reserveMngmt.ReserveCollectionManagement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Handler_DeleteRsrv"})
public class Handler_DeleteRsrv
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reserveNo = request.getParameter("reserveNo");
        System.out.println("reserveNo: " + reserveNo);
        boolean status = ReserveCollectionManagement.deleteReservation(reserveNo);
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/text");
        response.getWriter().print(status);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
