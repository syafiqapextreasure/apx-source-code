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
package com.ilmu.foundation.SMD;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddSMD"})
public class AddSMD
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL47SMD = request.getParameter("GL47SMD");
        String GL47DESC = request.getParameter("GL47DESC");
        String GL47DISPLAY = request.getParameter("GL47DISPLAY");
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLSMD (GL47SMD, GL47DESC, GL47DISPLAY) values (?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, GL47SMD);
                statement.setString(2, GL47DESC);
                statement.setString(3, GL47DISPLAY);
                statement.executeUpdate();
            }
            catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (SQLException ex2) {
                        ex2.printStackTrace();
                    }
                }
                response.sendRedirect("template?MODULE=Foundation/15_SMD&ACTION=SMDTable.jsp");
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            response.sendRedirect("template?MODULE=Foundation/15_SMD&ACTION=SMDTable.jsp");
        }
    }
}
