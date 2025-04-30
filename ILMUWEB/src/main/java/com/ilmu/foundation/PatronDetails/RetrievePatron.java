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
package com.ilmu.foundation.PatronDetails;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/RetrievePatron"})
public class RetrievePatron
extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<String> code = null;
        ArrayList codelist = new ArrayList();
        Connection con = null;
        String value = request.getParameter("value");
        String sqlqueary = "SELECT TOP 100 GL14PATR, GL14NAME, GL14ABBR from GLPATR";
        System.out.println(value);
        try {
            con = DBConnection.getConnection();
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sqlqueary);
                while (rs.next()) {
                    code = new ArrayList<String>();
                    code.add(rs.getString("GL14PATR"));
                    code.add(rs.getString("GL14NAME"));
                    code.add(rs.getString("GL14ABBR"));
                    codelist.add(code);
                    System.out.println(sqlqueary);
                }
            }
            catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("codelist", codelist);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/modules/Foundation/10_PatronDetails/PatDetails.jsp");
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
