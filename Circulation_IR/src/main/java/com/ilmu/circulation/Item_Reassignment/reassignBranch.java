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
package com.ilmu.circulation.Item_Reassignment;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/reassignBranch"})
public class reassignBranch
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("AccessionNo");
        String CT03LOCA = request.getParameter("LOCA03");
        String CI13PLOCA = request.getParameter("PLOCA13");
        String Starts = request.getParameter("Starts");
        String rStops = request.getParameter("rStops");
        String GL14PATR = request.getParameter("GL14PATR");
        String CREBY = request.getParameter("CREBY");
        System.out.println(String.valueOf(CT03LOCA) + " CT03LOCA");
        System.out.println(String.valueOf(CT03DOCNO) + " CT03DOCNO");
        System.out.println(String.valueOf(CI13PLOCA) + " CI13PLOCA");
        System.out.println(String.valueOf(DateFormatter.displayToDBFormat(Starts)) + " Starts");
        System.out.println(String.valueOf(rStops) + " rStops");
        System.out.println(String.valueOf(GL14PATR) + " GL14PATR");
        System.out.println(String.valueOf(CREBY) + " CREBY");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        String cDate = GeneralUtility.DatetoStr(currentdate);
        try {
            con = DBConnection.getConnection();
            String query = "INSERT INTO CITLOCA ( CI13ACCESSION, CI13SLOCA, CI13PLOCA, CI13DTSTART, CI13DTEND, CI13AUTHORISED, CI13CRDATE, CI13CREBY) values (?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, CT03DOCNO);
            statement.setString(2, CT03LOCA);
            statement.setString(3, CI13PLOCA);
            statement.setString(4, DateFormatter.displayToDBFormat(Starts));
            statement.setString(5, DateFormatter.displayToDBFormat(rStops));
            statement.setString(6, GL14PATR);
            statement.setString(7, cDate);
            statement.setString(8, CREBY);
            statement.executeUpdate();
            String sql = "UPDATE CTDOCM SET CT03LOCA = ? WHERE CT03DOCNO = ?";
            System.out.println(query);
            PreparedStatement statement2 = con.prepareStatement(sql);
            statement2.setString(1, CI13PLOCA);
            statement2.setString(2, CT03DOCNO);
            statement2.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
