/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.foundation.BranchCode;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddBranchCode"})
public class AddBranchCode
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL71BRNC = request.getParameter("GL71BRNC");
        System.out.println(String.valueOf(GL71BRNC) + " GL71BRNC");
        String GL71DESC = request.getParameter("GL71DESC");
        System.out.println(String.valueOf(GL71DESC) + " GL71DESC");
        String GL71DISPLAY = request.getParameter("GL71DISPLAY");
        System.out.println(String.valueOf(GL71DISPLAY) + " GL71DISPLAY");
        String GL71ADD1 = request.getParameter("GL71ADD1");
        System.out.println(String.valueOf(GL71ADD1) + " GL71ADD1");
        String GL71ADD2 = request.getParameter("GL71ADD2");
        System.out.println(String.valueOf(GL71ADD2) + " GL71ADD2");
        String GL71ADD3 = request.getParameter("GL71ADD3");
        System.out.println(String.valueOf(GL71ADD3) + " GL71ADD3");
        String GL71POSCODE = request.getParameter("GL71POSCODE");
        System.out.println(String.valueOf(GL71POSCODE) + " GL71POSCODE");
        String GL71TOWN = request.getParameter("GL71TOWN");
        System.out.println(String.valueOf(GL71TOWN) + " GL71TOWN");
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLBRNC (GL71BRNC, GL71DESC, GL71DISPLAY, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN) values (?,?,?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, GL71BRNC);
                statement.setString(2, GL71DESC);
                statement.setString(3, GL71DISPLAY);
                statement.setString(4, GL71ADD1);
                statement.setString(5, GL71ADD2);
                statement.setString(6, GL71ADD3);
                statement.setString(7, GL71POSCODE);
                statement.setString(8, GL71TOWN);
                System.out.println(sql);
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
                RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp");
                rd.forward((ServletRequest)request, (ServletResponse)response);
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
            RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/01_BranchCode&ACTION=BranchCode.jsp");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
