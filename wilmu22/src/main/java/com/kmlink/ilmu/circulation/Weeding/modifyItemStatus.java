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
package com.kmlink.ilmu.circulation.Weeding;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("CT03DOCNO");
        System.out.println(String.valueOf(CT03DOCNO) + "CT03DOCNO");
        String newStat = request.getParameter("newStat");
        System.out.println(String.valueOf(newStat) + "istat");
        try {
            con = DBConnection.getConnection();
            String query = "UPDATE CTDOCM SET CT03STAT = ? WHERE CT03DOCNO = ?";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newStat);
            statement.setString(2, CT03DOCNO);
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
