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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/generateID"})
public class generateID
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PrintWriter out = response.getWriter();
        Object message = null;
        con = DBConnection.getConnection();
        System.out.println("Connected!");
        try {
            String GL99FUNC = "";
            String GL99VALUE = "";
            String GL99DESC = "";
            String patrnoID = "";
            String queryGetGLFLAG2 = "SELECT * FROM GLFLAG2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryGetGLFLAG2);
            System.out.println(queryGetGLFLAG2);
            while (rs.next()) {
                GL99FUNC = rs.getString("GL99FUNC");
                GL99VALUE = rs.getString("GL99VALUE");
                GL99DESC = rs.getString("GL99DESC");
                if (!GL99FUNC.equals("PATRONID")) continue;
                System.out.println("55");
                System.out.println(GL99VALUE);
                String query = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'PATRID'";
                st = con.createStatement();
                ResultSet rs2 = st.executeQuery(query);
                System.out.println(query);
                while (rs2.next()) {
                    patrnoID = rs2.getString("GL98VALUE");
                    System.out.println(rs2.getString("GL98VALUE"));
                }
                if (patrnoID.equals("0")) {
                    PreparedStatement ps = null;
                    ps = con.prepareStatement("UPDATE GLNUMB SET GL98VALUE = '1' WHERE GL98FUNC = 'PATRID'");
                    ps.executeUpdate();
                    System.out.println(String.format("%0" + GL99VALUE + "d", 1));
                    patrnoID = String.format("%0" + GL99VALUE + "d", 1);
                    out.print(patrnoID);
                    continue;
                }
                int count = Integer.parseInt(patrnoID);
                System.out.println(String.valueOf(count) + " zzz");
                PreparedStatement ps = null;
                ps = con.prepareStatement("UPDATE GLNUMB SET GL98VALUE = ? WHERE GL98FUNC = 'PATRID'");
                ps.setInt(1, ++count);
                ps.executeUpdate();
                System.out.println(ps + " cc");
                System.out.println(String.format("%0" + GL99VALUE + "d", count));
                patrnoID = String.format("%0" + GL99VALUE + "d", count);
                out.print(patrnoID);
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
