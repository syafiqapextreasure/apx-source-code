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
package com.ilmu.foundation.GeneralSubject;

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

@WebServlet(value={"/AddGeneralSubj"})
public class AddGeneralSubj
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL54SUBJSTA = request.getParameter("GL54SUBJSTA");
        String GL54SUBJEND = request.getParameter("GL54SUBJEND");
        String GL54MARK = request.getParameter("GL54MARK");
        String GL54DESC = request.getParameter("GL54DESC");
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLSUBJ (GL54SUBJSTA, GL54SUBJEND, GL54MARK, GL54DESC) values (?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, GL54SUBJSTA);
                statement.setString(2, GL54SUBJEND);
                statement.setString(3, GL54MARK);
                statement.setString(4, GL54DESC);
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
                RequestDispatcher rd = request.getRequestDispatcher("template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp");
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
            RequestDispatcher rd = request.getRequestDispatcher("template?MODULE=Foundation/09_GeneralSubject&ACTION=SubjTable.jsp");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
