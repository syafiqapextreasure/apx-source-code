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
package com.ilmu.foundation.CurrencyCode;

import com.ilmu.global.DateFormatter;
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

@WebServlet(value={"/addCurrency"})
public class addCurrency
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL24FOREX = request.getParameter("GL24FOREX");
        String GL24DESC = request.getParameter("GL24DESC");
        String GL24PRATE = request.getParameter("GL24PRATE");
        System.out.println(String.valueOf(GL24PRATE) + " GL24PRATE");
        String GL24BRATE = request.getParameter("GL24BRATE");
        String GL24PDATE = request.getParameter("GL24PDATE");
        System.out.println(String.valueOf(GL24PDATE) + " GL24PDATE");
        String GL24BDATE = request.getParameter("GL24BDATE");
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLFORX (GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE, GL24PDATE, GL24BDATE) values (?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, GL24FOREX);
                statement.setString(2, GL24DESC);
                statement.setString(3, GL24PRATE);
                statement.setString(4, GL24BRATE);
                if (GL24PDATE.isEmpty()) {
                    statement.setString(5, null);
                } else {
                    statement.setString(5, DateFormatter.displayToDBFormat(GL24PDATE));
                }
                if (GL24BDATE.isEmpty()) {
                    statement.setString(6, null);
                } else {
                    statement.setString(6, DateFormatter.displayToDBFormat(GL24BDATE));
                }
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
                RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/04_CurrencyCode&ACTION=CurrencyTable.jsp");
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
            RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/04_CurrencyCode&ACTION=CurrencyTable.jsp");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
