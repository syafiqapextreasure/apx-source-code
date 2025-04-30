/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.ill.receiveIll;

import com.google.gson.Gson;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.ill.receiveIll.ReceiveILL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/generateAccNO"})
public class generateAccNO
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        String rowCount = request.getParameter("rowCount");
        int changerowCount = Integer.parseInt(rowCount);
        ArrayList<String> result = new ArrayList<String>();
        int i = 1;
        while (i <= changerowCount) {
            block15: {
                try {
                    try {
                        int accLength = ReceiveILL.illlength();
                        String prefixcal = ReceiveILL.illaccval();
                        System.out.println("prefixcal" + prefixcal);
                        int countprefixcal = prefixcal.length();
                        int finallength = 0;
                        finallength = accLength - countprefixcal;
                        con = DBConnection.getConnection();
                        String ACCNO = "";
                        String COUNT = "";
                        String query = " SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC = 'ILLACCNO'";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        while (rs.next()) {
                            ACCNO = rs.getString("GL98VALUE");
                        }
                        int plusAccno = Integer.parseInt(ACCNO);
                        String padLeftZero = String.format("%0" + finallength + "d", plusAccno);
                        String illAcc = String.valueOf(prefixcal) + padLeftZero;
                        do {
                            String query2 = "Select COUNT(CT03DOCNO) AS Count FROM CTDOCM WHERE CT03DOCNO='" + illAcc + "'";
                            st = con.createStatement();
                            ResultSet rs2 = st.executeQuery(query2);
                            while (rs2.next()) {
                                COUNT = rs2.getString("COUNT");
                            }
                            if (COUNT.equals("0")) continue;
                            padLeftZero = String.format("%0" + finallength + "d", ++plusAccno);
                        } while (!COUNT.equals("0"));
                        result.add(String.valueOf(prefixcal) + padLeftZero);
                        PreparedStatement ps = null;
                        ps = con.prepareStatement("UPDATE GLNUMB SET GL98VALUE = ? WHERE GL98FUNC = 'ILLACCNO'");
                        ps.setInt(1, ++plusAccno);
                        ps.executeUpdate();
                        System.out.println(ps + " cc");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        try {
                            con.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                        break block15;
                    }
                }
                catch (Throwable throwable) {
                    try {
                        con.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throw throwable;
                }
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
            ++i;
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
