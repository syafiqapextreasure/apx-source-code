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
package com.ilmu.foundation.CodeTable;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/CheckingCode"})
public class CheckingCode
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        System.out.println("dfg");
        String CODE = request.getParameter("CODE").toUpperCase();
        String DESCRIPTION = request.getParameter("DESCRIPTION");
        String FCODE = request.getParameter("FCODE");
        PrintWriter out = response.getWriter();
        Object message = null;
        con = DBConnection.getConnection();
        DBConnection dbtype = new DBConnection();
        System.out.println("Connected!");
        if (String.valueOf(CODE) != null) {
            String query = "";
            if (dbtype.getDBType().equals("mssql")) {
                query = "SELECT CODE FROM FNDCODE WHERE FCODE='" + FCODE + "' AND LTRIM(RTRIM(CODE)) = '" + CODE + "'";
                System.out.println("sql here");
            } else if (dbtype.getDBType().equals("oracle")) {
                query = "SELECT CODE FROM FNDCODE WHERE FCODE='" + FCODE + "' AND TRIM(CODE) = '" + CODE + "'";
                System.out.println("oracle here");
            } else if (dbtype.getDBType().equals("mysql")) {
                query = "SELECT CODE FROM FNDCODE WHERE FCODE='" + FCODE + "' AND TRIM(CODE) = '" + CODE + "'";
                System.out.println("mysql here");
            }
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println(query);
                if (rs.next()) {
                    out.print("{\"valid\" : false }");
                } else {
                    out.print("{\"valid\" : true }");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
