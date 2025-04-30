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

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/modifynotlost"})
public class modifynotlost
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("accno");
        System.out.println(String.valueOf(CT03DOCNO) + "CT03DOCNO");
        String newStat = request.getParameter("newStat");
        System.out.println(String.valueOf(newStat) + "istat");
        String loginid = request.getParameter("loginid");
        Date date = new Date();
        try {
            con = DBConnection.getConnection();
            String query = "UPDATE CTDOCM SET CT03STAT = ?, CT03LASTACT = ?, CT03LASTID = ? WHERE CT03DOCNO = ?";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newStat);
            statement.setString(2, DateTime.getTodayDate());
            statement.setString(3, loginid);
            statement.setString(4, CT03DOCNO);
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
