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

@WebServlet(value={"/NumericalCheck"})
public class NumericalCheck
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ILMU";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String user = "paradigm";
        String pass = "paradigm";
        String GL14NEWIC = request.getParameter("txtDetails9");
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected!");
            if (Pattern.matches("[0-9]+", GL14NEWIC) && GL14NEWIC.length() == 12) {
                System.out.println(GL14NEWIC);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("jsp/modules/Foundation/Test/ICError.jsp");
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
