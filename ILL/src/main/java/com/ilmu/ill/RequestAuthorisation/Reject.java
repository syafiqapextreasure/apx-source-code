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
package com.ilmu.ill.RequestAuthorisation;

import com.ilmu.global.RecordTransaction;
import com.ilmu.ill.RequestAuthorisation.RequestAuthorisation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Reject"})
public class Reject
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String liferayLogin = request.getParameter("liferayLogin");
        int total = Integer.parseInt(request.getParameter("total"));
        String reqno = request.getParameter("reqno");
        try {
            boolean sucess = false;
            String[] reqno2 = reqno.split(",");
            int i = 0;
            while (i < total) {
                sucess = RequestAuthorisation.RejectRequest(liferayLogin, reqno2[i]);
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CIU0004", reqno2[i], liferayLogin);
                ++i;
            }
            PrintWriter out = response.getWriter();
            out.print("Done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
