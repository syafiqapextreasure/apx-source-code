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
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/PhotoServlet"})
public class PhotoServlet
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String GL14PATR = request.getParameter("GL14PATR");
        System.out.println("From Photo" + GL14PATR);
        try {
            con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT GL14IMG from GLPATR where GL14PATR='" + GL14PATR + "'");
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getBytes("GL14IMG") != null) {
                    response.getOutputStream().write(rs.getBytes("GL14IMG"));
                }
            } else {
                response.getOutputStream().write(null);
            }
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
