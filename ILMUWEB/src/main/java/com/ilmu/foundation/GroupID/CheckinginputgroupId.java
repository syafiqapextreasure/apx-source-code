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
package com.ilmu.foundation.GroupID;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/CheckinginputgroupId"})
public class CheckinginputgroupId
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block13: {
            Connection con = null;
            System.out.println("CheckinginputgroupId");
            String inputgroupId = request.getParameter("inputgroupId");
            PrintWriter out = response.getWriter();
            Object message = null;
            if (String.valueOf(inputgroupId) != null) {
                System.out.println("Start");
                String query = "SELECT GL02GRP FROM GLGRMA WHERE GL02GRP ='" + inputgroupId + "'";
                try {
                    try {
                        con = DBConnection.getConnection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        System.out.println("inputgroupId sql = " + query);
                        if (rs.next()) {
                            System.out.println("answer = " + rs.next());
                            out.print("{\"valid\" : false }");
                            System.out.println("false");
                            break block13;
                        }
                        out.print("{\"valid\" : true }");
                        System.out.println("true");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        try {
                            con.close();
                        }
                        catch (SQLException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                finally {
                    try {
                        con.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
