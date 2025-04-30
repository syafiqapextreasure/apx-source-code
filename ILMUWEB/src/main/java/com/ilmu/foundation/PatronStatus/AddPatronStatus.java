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
package com.ilmu.foundation.PatronStatus;

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

@WebServlet(value={"/AddPatronStatus"})
public class AddPatronStatus
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        System.out.println("Masuk Servlet");
        String GL08STAT = request.getParameter("GL08STAT");
        String GL08DESC = request.getParameter("GL08DESC");
        String GL08ACTION1 = request.getParameter("GL08ACTION1");
        System.out.println(String.valueOf(GL08ACTION1) + " GL08ACTION1");
        String GL08ACTION2 = request.getParameter("GL08ACTION2");
        String GL08ACTION3 = request.getParameter("GL08ACTION3");
        String GL08ACTION4 = request.getParameter("GL08ACTION4");
        String GL08ACTION5 = request.getParameter("GL08ACTION5");
        String GL08ACTION6 = request.getParameter("GL08ACTION6");
        String GL08ACTION7 = request.getParameter("GL08ACTION7");
        String GL08ACTION8 = request.getParameter("GL08ACTION8");
        String GL08ACTION9 = request.getParameter("GL08ACTION9");
        String GL08ACTION10 = request.getParameter("GL08ACTION10");
        String GL08ACTION11 = request.getParameter("GL08ACTION11");
        String GL08ACTION12 = request.getParameter("GL08ACTION12");
        String GL08ACTION13 = request.getParameter("GL08ACTION13");
        String GL08ACTION14 = request.getParameter("GL08ACTION14");
        String GL08ACTION15 = request.getParameter("GL08ACTION15");
        String GL08ACTION16 = request.getParameter("GL08ACTION16");
        String GL08ACTION17 = request.getParameter("GL08ACTION17");
        String GL08ACTION18 = request.getParameter("GL08ACTION18");
        String GL08ACTION19 = request.getParameter("GL08ACTION19");
        String GL08ACTION20 = request.getParameter("GL08ACTION20");
        GL08ACTION1 = GL08ACTION1 != null ? "Y" : "N";
        GL08ACTION2 = GL08ACTION2 != null ? "Y" : "N";
        GL08ACTION3 = GL08ACTION3 != null ? "Y" : "N";
        GL08ACTION4 = GL08ACTION4 != null ? "Y" : "N";
        GL08ACTION5 = GL08ACTION5 != null ? "Y" : "N";
        GL08ACTION6 = GL08ACTION6 != null ? "Y" : "N";
        GL08ACTION7 = GL08ACTION7 != null ? "Y" : "N";
        GL08ACTION8 = GL08ACTION8 != null ? "Y" : "N";
        GL08ACTION9 = GL08ACTION9 != null ? "Y" : "N";
        GL08ACTION10 = GL08ACTION10 != null ? "Y" : "N";
        GL08ACTION11 = GL08ACTION11 != null ? "Y" : "N";
        GL08ACTION12 = GL08ACTION12 != null ? "Y" : "N";
        GL08ACTION13 = GL08ACTION13 != null ? "Y" : "N";
        GL08ACTION14 = GL08ACTION14 != null ? "Y" : "N";
        GL08ACTION15 = GL08ACTION15 != null ? "Y" : "N";
        GL08ACTION16 = GL08ACTION16 != null ? "Y" : "N";
        GL08ACTION17 = GL08ACTION17 != null ? "Y" : "N";
        GL08ACTION18 = GL08ACTION18 != null ? "Y" : "N";
        GL08ACTION19 = GL08ACTION19 != null ? "Y" : "N";
        GL08ACTION20 = GL08ACTION20 != null ? "Y" : "N";
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLSTAT (GL08STAT, GL08DESC, GL08ACTION1, GL08ACTION2, GL08ACTION3,  GL08ACTION4, GL08ACTION5, GL08ACTION6, GL08ACTION7, GL08ACTION8, GL08ACTION9,  GL08ACTION10, GL08ACTION11, GL08ACTION12, GL08ACTION13, GL08ACTION14, GL08ACTION15,  GL08ACTION16, GL08ACTION17, GL08ACTION18, GL08ACTION19, GL08ACTION20)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                System.out.println(sql);
                statement.setString(1, GL08STAT);
                statement.setString(2, GL08DESC);
                statement.setString(3, GL08ACTION1);
                statement.setString(4, GL08ACTION2);
                statement.setString(5, GL08ACTION3);
                statement.setString(6, GL08ACTION4);
                statement.setString(7, GL08ACTION5);
                statement.setString(8, GL08ACTION6);
                statement.setString(9, GL08ACTION7);
                statement.setString(10, GL08ACTION8);
                statement.setString(11, GL08ACTION9);
                statement.setString(12, GL08ACTION10);
                statement.setString(13, GL08ACTION11);
                statement.setString(14, GL08ACTION12);
                statement.setString(15, GL08ACTION13);
                statement.setString(16, GL08ACTION14);
                statement.setString(17, GL08ACTION15);
                statement.setString(18, GL08ACTION16);
                statement.setString(19, GL08ACTION17);
                statement.setString(20, GL08ACTION18);
                statement.setString(21, GL08ACTION19);
                statement.setString(22, GL08ACTION20);
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
                response.sendRedirect("template?MODULE=Foundation/16_PatronStatus&ACTION=StatusTable.jsp");
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
            response.sendRedirect("template?MODULE=Foundation/16_PatronStatus&ACTION=StatusTable.jsp");
        }
    }
}
