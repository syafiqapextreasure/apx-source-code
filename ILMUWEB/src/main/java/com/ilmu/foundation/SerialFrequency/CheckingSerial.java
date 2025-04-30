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
package com.ilmu.foundation.SerialFrequency;

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

@WebServlet(value={"/CheckingSerial"})
public class CheckingSerial
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        System.out.println("dfg");
        String GL49FREQ = request.getParameter("GL49FREQ").toUpperCase();
        PrintWriter out = response.getWriter();
        Object message = null;
        con = DBConnection.getConnection();
        System.out.println("Connected!");
        if (String.valueOf(GL49FREQ) != null) {
            try {
                System.out.println(GL49FREQ);
                String query = "Select GL49FREQ from GLFREQ where GL49FREQ='" + GL49FREQ + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                System.out.println(query);
                System.out.println("next222");
                if (rs.next()) {
                    out.print("{\"valid\" : false }");
                    System.out.println("false");
                } else {
                    out.print("{\"valid\" : true }");
                    System.out.println("true");
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
