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
package com.ilmu.ill.outgoingreq;

import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.ill.outgoingreq.OutgoingReq;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/EditOutgoing"})
public class EditOutgoing
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String refno = request.getParameter("refno");
        String cntrlno = request.getParameter("cntrlno");
        String accno = request.getParameter("accno");
        String doctype = request.getParameter("doctype");
        String vol = request.getParameter("vol");
        String iss = request.getParameter("iss");
        String pageNo = request.getParameter("pageNo");
        String dateReq = request.getParameter("dateReq");
        String expDate = request.getParameter("expDate");
        String dueDate = request.getParameter("dueDate");
        String libId = request.getParameter("libId");
        String contactperson = request.getParameter("contactperson");
        String patr = request.getParameter("patr");
        String loginid = request.getParameter("loginid");
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            boolean sucess = false;
            sucess = OutgoingReq.SaveChangesAndLog(refno, cntrlno, accno, doctype, vol, iss, pageNo, dateReq, expDate, dueDate, libId, contactperson, patr);
            if (sucess) {
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CIU0006", refno, loginid);
            }
            PrintWriter out = response.getWriter();
            out.print("Success");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
