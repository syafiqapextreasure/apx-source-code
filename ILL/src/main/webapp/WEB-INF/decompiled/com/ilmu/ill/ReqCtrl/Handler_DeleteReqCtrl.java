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
import com.ilmu.ill.ReqCtrl.ReqCtrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Handler_DeleteReqCtrl"})
public class Handler_DeleteReqCtrl
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String loginid = request.getParameter("loginid");
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            boolean sucess = false;
            ReqCtrl.DeleteCurrentRecord(id);
            String gsModule = "CI";
            RecordTransaction.InsertAudit(gsModule, "CID0006", id, loginid);
            PrintWriter out = response.getWriter();
            out.print("ok");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
