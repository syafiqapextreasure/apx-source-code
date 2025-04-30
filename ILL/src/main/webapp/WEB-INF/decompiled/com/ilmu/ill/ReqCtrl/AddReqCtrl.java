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
package com.ilmu.ill.ReqCtrl;

import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.global.globalFunc;
import com.ilmu.ill.ReqCtrl.ReqCtrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddReqCtrl"})
public class AddReqCtrl
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqDate = request.getParameter("reqDate");
        String reqID = request.getParameter("reqID");
        String lendLib = request.getParameter("lendLib");
        String doctype = request.getParameter("doctype");
        String dexpect = request.getParameter("dexpect");
        String predevtype = request.getParameter("predevtype");
        String title = request.getParameter("title");
        String issn = request.getParameter("issn");
        String author = request.getParameter("author");
        String placePub = request.getParameter("placePub");
        String pub = request.getParameter("pub");
        String yearPub = request.getParameter("yearPub");
        String edition = request.getParameter("edition");
        String remarks = request.getParameter("remarks");
        String vol = request.getParameter("vol");
        String iss = request.getParameter("iss");
        String pageno = request.getParameter("pageno");
        String gsUserId = request.getParameter("gsUserId");
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            boolean sucess = false;
            int illreq = globalFunc.Get98VALUE("ILLOUTREQCNO");
            int newillreq = new Integer(illreq + 1);
            globalFunc.updatingGlnumb2(newillreq, "ILLOUTREQCNO");
            String illreqConvert = String.format("%010d", newillreq);
            sucess = ReqCtrl.AddNewRecord(reqDate, reqID, lendLib, doctype, dexpect, predevtype, title, issn, author, placePub, pub, yearPub, edition, remarks, vol, iss, pageno, gsUserId, illreqConvert);
            if (sucess) {
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CII0009", illreqConvert, gsUserId);
            }
            PrintWriter out = response.getWriter();
            out.print(illreqConvert);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
