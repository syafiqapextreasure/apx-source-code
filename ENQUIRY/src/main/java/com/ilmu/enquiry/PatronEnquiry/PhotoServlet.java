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
package com.ilmu.enquiry.PatronEnquiry;

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
        Connection con = null;
        String id = request.getParameter("id");
        System.out.println("From Photo" + id);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("SELECT GL14IMG FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + id + "')");
                System.out.println("SELECT GL14IMG from GLPATR where UPPER(GL14PATR) = UPPER('" + id + "')");
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    if (rs.getBytes("GL14IMG") != null) {
                        response.getOutputStream().write(rs.getBytes("GL14IMG"));
                    }
                } else {
                    response.getOutputStream().write(null);
                }
                con.close();
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
