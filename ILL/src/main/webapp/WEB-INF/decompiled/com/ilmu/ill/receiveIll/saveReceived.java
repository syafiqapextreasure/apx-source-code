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
package com.ilmu.ill.receiveIll;

import com.ilmu.global.RecordTransaction;
import com.ilmu.ill.receiveIll.ReceiveILL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/saveReceived"})
public class saveReceived
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int total = Integer.parseInt(request.getParameter("total"));
        String accno = request.getParameter("accno");
        String loca = request.getParameter("loca");
        String icate = request.getParameter("icate");
        String con = request.getParameter("con");
        String smd = request.getParameter("smd");
        String refno = request.getParameter("refno");
        String ctrlno = request.getParameter("ctrlno");
        String reqId = request.getParameter("reqId");
        String recDate = request.getParameter("recDate");
        String dueDate = request.getParameter("dueDate");
        String liferayLogin = request.getParameter("liferayLogin");
        try {
            boolean sucess = false;
            String[] accno2 = accno.split(",");
            String[] loca2 = loca.split(",");
            String[] icate2 = icate.split(",");
            String[] con2 = con.split(",");
            String[] smd2 = smd.split(",");
            int i = 0;
            while (i < total) {
                sucess = ReceiveILL.SaveRecords(accno2[i], ctrlno, loca2[i], icate2[i], con2[i], smd2[i], reqId, liferayLogin);
                ++i;
            }
            String newAccno = accno.replaceAll(",", ";");
            if (sucess) {
                sucess = ReceiveILL.UpdateCIOUTR(total, recDate, dueDate, newAccno, refno);
            }
            if (sucess) {
                sucess = ReceiveILL.UpdateILREQC(refno);
            }
            String gsModule = "CI";
            RecordTransaction.InsertAudit(gsModule, "CIU0003", refno, liferayLogin);
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
