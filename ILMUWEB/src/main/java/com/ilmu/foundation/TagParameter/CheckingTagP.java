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
package com.ilmu.foundation.TagParameter;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/CheckingTagP"})
public class CheckingTagP
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String GL17TAG = request.getParameter("GL17TAG");
        String GL17MARC = request.getParameter("GL17MARC");
        Object message = null;
        try {
            con = DBConnection.getConnection();
            System.out.println("Connected!");
            if (String.valueOf(GL17TAG) != null) {
                System.out.println(GL17TAG);
                String query = "Select GL17TAG from GLTAGP where GL17TAG='" + GL17TAG + "' AND GL17MARC ='" + GL17MARC + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println(query);
                if (rs.next()) {
                    RequestDispatcher rd = request.getRequestDispatcher("Error_Page");
                    rd.forward((ServletRequest)request, (ServletResponse)response);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
