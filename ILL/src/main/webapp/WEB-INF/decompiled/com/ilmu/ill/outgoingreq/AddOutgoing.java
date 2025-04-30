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
import com.ilmu.global.globalFunc;
import com.ilmu.ill.outgoingreq.OutgoingReq;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddOutgoing"})
public class AddOutgoing
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String daterec = request.getParameter("daterec");
        String recby = request.getParameter("recby");
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            boolean sucess = false;
            int outgoing = globalFunc.Get98VALUE("OUTREQUESTNO");
            int newoutgoing = new Integer(outgoing + 1);
            globalFunc.updatingGlnumb(newoutgoing);
            String outgoingConvert = String.format("%010d", newoutgoing);
            sucess = OutgoingReq.AddNewRecord(cntrlno, accno, doctype, vol, iss, pageNo, dateReq, expDate, dueDate, libId, contactperson, patr, daterec, recby, outgoingConvert);
            if (sucess) {
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CII0008", outgoingConvert, recby);
            }
            PrintWriter out = response.getWriter();
            out.print(outgoingConvert);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
