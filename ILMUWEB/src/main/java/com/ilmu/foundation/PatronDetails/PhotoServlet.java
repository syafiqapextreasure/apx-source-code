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
package com.ilmu.foundation.PatronDetails;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/PhotoServlet"})
public class PhotoServlet
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block13: {
            Connection con = null;
            String GL14PATR = request.getParameter("GL14PATR");
            System.out.println("From Photo" + GL14PATR);
            try {
                try {
                    con = DBConnection.getConnection();
                    PreparedStatement stmt = con.prepareStatement("SELECT GL14IMG from GLPATR where GL14PATR='" + GL14PATR + "'");
                    System.out.println("SELECT GL14IMG from GLPATR where GL14PATR='" + GL14PATR + "'");
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        if (rs.getBytes("GL14IMG") != null) {
                            response.getOutputStream().write(rs.getBytes("GL14IMG"));
                        }
                        break block13;
                    }
                    response.getOutputStream().write(null);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        con.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
