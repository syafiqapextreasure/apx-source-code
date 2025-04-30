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
package com.ilmu.circulation.Item_Status_Maintenance;

import com.ilmu.circulation.Charging.Discharging;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/modifyItemStatus"})
public class modifyItemStatus
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("accno");
        System.out.println(String.valueOf(CT03DOCNO) + "CT03DOCNO");
        String GL14PATR = request.getParameter("patrid");
        System.out.println(String.valueOf(GL14PATR) + "GL14PATR");
        String newStat = request.getParameter("newStat");
        System.out.println(String.valueOf(newStat) + "istat");
        String id = request.getParameter("id");
        System.out.println(String.valueOf(id) + "id");
        Discharging chr = null;
        Object details = null;
        System.out.println("CT03DOCNO" + CT03DOCNO);
        chr = new Discharging();
        boolean modifysuccessfull = false;
        modifysuccessfull = chr.modify(CT03DOCNO, GL14PATR, id);
        System.out.println("Message" + chr.getErrMessage());
    }

    public String getServletInfo() {
        return "Short description";
    }
}
