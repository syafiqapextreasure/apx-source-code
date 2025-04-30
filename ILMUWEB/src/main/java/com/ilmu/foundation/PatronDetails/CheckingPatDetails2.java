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
package com.ilmu.foundation.PatronDetails;

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

@WebServlet(value={"/CheckingPatDetails2"})
public class CheckingPatDetails2
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String GL14NEWIC = request.getParameter("GL14NEWIC");
        String GL14PATR = request.getParameter("GL14PATR");
        PrintWriter out = response.getWriter();
        String getICVal = "";
        Object message = null;
        con = DBConnection.getConnection();
        try {
            String valGL14NEWIC = "";
            if (String.valueOf(GL14NEWIC) != null) {
                if (String.valueOf(GL14PATR).isEmpty()) {
                    System.out.println("GL14PATR isEmpty");
                    System.out.println(GL14PATR);
                    String query = "Select GL14NEWIC from GLPATR where GL14NEWIC='" + GL14NEWIC + "'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    System.out.println(query);
                    System.out.println("GL14PATR isEmpty222");
                    if (rs.next()) {
                        out.print("{\"valid\" : false }");
                        System.out.println("GL14PATR isEmpty false");
                    } else {
                        out.print("{\"valid\" : true }");
                        System.out.println("GL14PATR isEmpty true");
                    }
                } else {
                    System.out.println("patr not null");
                    String query = "Select GL14NEWIC from GLPATR where GL14NEWIC='" + GL14NEWIC + "' AND GL14PATR='" + GL14PATR + "'";
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery(query);
                    System.out.println(query);
                    System.out.println("not e 333");
                    if (rs2.next()) {
                        out.print("{\"valid\" : true }");
                        System.out.println("uuu");
                    } else {
                        System.out.println("789");
                        if (GL14NEWIC.isEmpty()) {
                            System.out.println("???");
                        } else {
                            System.out.println("//");
                            String query2 = "Select GL14NEWIC from GLPATR where GL14NEWIC='" + GL14NEWIC + "'";
                            st = con.createStatement();
                            ResultSet rs = st.executeQuery(query2);
                            System.out.println(query2);
                            if (rs.next()) {
                                out.print("{\"valid\" : false }");
                            } else {
                                out.print("{\"valid\" : true }");
                            }
                        }
                    }
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
