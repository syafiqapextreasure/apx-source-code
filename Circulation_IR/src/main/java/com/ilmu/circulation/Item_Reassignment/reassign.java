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

@WebServlet(value={"/reassign"})
public class reassign
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("AccessionNo");
        String icat = request.getParameter("icat");
        String CT03ICAT = request.getParameter("oriItemCat");
        String Starts = request.getParameter("Starts");
        String rStops = request.getParameter("rStops");
        String GL14PATR = request.getParameter("GL14PATR");
        String CREBY = request.getParameter("CREBY");
        System.out.println(String.valueOf(icat) + " icat");
        System.out.println(String.valueOf(CT03DOCNO) + " CT03DOCNO");
        System.out.println(String.valueOf(CT03ICAT) + " CT03ICAT");
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
            String query = "INSERT INTO CITRAN ( CI07ACCESSION, CI07SICAT, CI07PICAT, CI07DTSTART, CI07DTEND, CI07AUTHORISED, CI07CRDATE, CI07CREBY) values (?, ?, ?, ?, ?, ?, ?, '" + CREBY + "')";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, CT03DOCNO);
            statement.setString(2, CT03ICAT);
            statement.setString(3, icat);
            statement.setString(4, DateFormatter.displayToDBFormat(Starts));
            statement.setString(5, DateFormatter.displayToDBFormat(rStops));
            statement.setString(6, GL14PATR);
            statement.setString(7, cDate);
            statement.executeUpdate();
            String sql = "UPDATE CTDOCM SET CT03ICAT = ? WHERE CT03DOCNO = ?";
            System.out.println(query);
            PreparedStatement statement2 = con.prepareStatement(sql);
            statement2.setString(1, icat);
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
