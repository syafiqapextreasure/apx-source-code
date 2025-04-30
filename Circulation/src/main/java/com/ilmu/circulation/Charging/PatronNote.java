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
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.Patron;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/PatronNote"})
public class PatronNote
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private Patron patr;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PATRON NOTE ");
        Connection conn = null;
        String AN01ACAT = request.getParameter("AN01ACAT");
        String GL14PATR = request.getParameter("AN01REFKEY");
        String output = "";
        conn = DBConnection.getConnection();
        String sqlCountNote = "SELECT COUNT(*) AS Note FROM ANMESG WHERE AN01ACAT = '" + AN01ACAT + "' AND AN01REFKEY = '" + GL14PATR + "'";
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCountNote);
            System.out.println("Count Note CS" + sqlCountNote);
            conn.commit();
            while (rs.next()) {
                output = rs.getString("Note");
                System.out.println("Count Note output" + output);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(output);
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
