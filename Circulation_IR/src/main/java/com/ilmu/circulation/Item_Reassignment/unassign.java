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

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/unassign"})
public class unassign
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        String CT03DOCNO = request.getParameter("AccessionNo");
        System.out.println(String.valueOf(CT03DOCNO) + "CT03DOCNO");
        String CT03ICAT = request.getParameter("oriItemCat");
        System.out.println(String.valueOf(CT03ICAT) + "icat");
        try {
            con = DBConnection.getConnection();
            String query = "UPDATE CTDOCM SET CT03ICAT = ? WHERE CT03DOCNO = ?";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, CT03ICAT);
            statement.setString(2, CT03DOCNO);
            statement.executeUpdate();
            String sql = "DELETE FROM CITRAN WHERE  CI07ACCESSION = ?";
            System.out.println(query);
            PreparedStatement statement2 = con.prepareStatement(sql);
            statement2.setString(1, CT03DOCNO);
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
