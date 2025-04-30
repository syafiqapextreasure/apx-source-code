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
package com.ilmu.foundation.PatrMiscellaneous;

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

@WebServlet(value={"/CheckingpatrAttriID"})
public class CheckingpatrAttriID
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String inputpatrAttri = request.getParameter("inputpatrAttri");
        PrintWriter out = response.getWriter();
        Object message = null;
        con = DBConnection.getConnection();
        try {
            if (String.valueOf(inputpatrAttri) != null) {
                System.out.println(inputpatrAttri);
                String query = "SELECT GL66ATTRI FROM GLATTRI WHERE GL66ATTRI = '" + inputpatrAttri + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println(query);
                if (rs.next()) {
                    out.print("{\"valid\" : false }");
                    System.out.println("GL14IPADD isEmpty false");
                } else {
                    out.print("{\"valid\" : true }");
                    System.out.println("GL14IPADD isEmpty true");
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
