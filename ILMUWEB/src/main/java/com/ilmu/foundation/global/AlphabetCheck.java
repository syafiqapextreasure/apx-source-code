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
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AlphabetCheck"})
public class AlphabetCheck
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ILMU";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String user = "paradigm";
        String pass = "paradigm";
        String GL14NAME = request.getParameter("txtDetails2");
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected!");
            if (Pattern.matches("^[\\p{L} .'-]+$", GL14NAME)) {
                System.out.println(GL14NAME);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("jsp/modules/Foundation/Test/NameError.jsp");
                rd.forward((ServletRequest)request, (ServletResponse)response);
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
