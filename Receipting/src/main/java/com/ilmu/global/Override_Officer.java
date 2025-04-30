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
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Override_Officer"})
public class Override_Officer
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        int overridelvl = Override_Officer.getOverrideOfficer();
        int userlvl = Override_Officer.getUserLvl(username);
        if (userlvl <= overridelvl) {
            response.getWriter().append("override");
        } else {
            response.getWriter().append("fail");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public static int getOverrideOfficer() {
        String query = "Select GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = 'FINESOVERRIDEAC'";
        System.out.println(query);
        int count = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString("GL99VALUE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public static int getUserLvl(String patronid) {
        String query = "Select GL02ACL from glpatr, GLGRMA where GL14GRID = GL02GRP and GL14PATR='" + patronid + "'";
        System.out.println(query);
        int count = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString("GL02ACL"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
