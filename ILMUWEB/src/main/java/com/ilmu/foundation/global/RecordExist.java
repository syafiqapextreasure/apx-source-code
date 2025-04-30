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
package com.ilmu.foundation.global;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(value={"/RecordExist"})
public class RecordExist
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ILMU";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String user = "paradigm";
        String pass = "paradigm";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected!");
            String GL14PATR = request.getParameter("data");
            if (String.valueOf(GL14PATR) != null) {
                System.out.println(GL14PATR);
                String query = "Select GL14PATR from GLPATR where GL14PATR='" + GL14PATR + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println("query " + query);
                if (rs.next()) {
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/modules/Foundation/Error_Page.jsp");
                    rd.forward((ServletRequest)request, (ServletResponse)response);
                }
                System.out.println("Disconnected!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
