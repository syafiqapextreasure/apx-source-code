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
package com.ilmu.global;

import com.ilmu.global.Encrypter;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/CheckLoginIn"})
public class CheckLoginIn
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patronid = request.getParameter("patronid");
        String password = request.getParameter("password");
        boolean patrexist = CheckLoginIn.checkPatrID(patronid);
        if (patrexist) {
            boolean exist = CheckLoginIn.checkPassword(password, patronid);
            System.out.println("ssdsdsd" + exist);
            if (exist) {
                response.getWriter().append("true");
            } else {
                response.getWriter().append("false");
            }
        } else {
            response.getWriter().append("false");
        }
    }

    public static boolean checkPassword(String password, String patronid) {
        password = Encrypter.encrypt(password);
        System.out.println("Password" + password);
        password = password.replaceAll("'", "''");
        boolean exist = false;
        String query = "Select Count(*) as count FROM GLPATR WHERE GL14PASW = '" + password + "' AND GL14PATR= '" + patronid.toUpperCase() + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public static boolean checkPatrID(String patronid) {
        boolean exist = false;
        String query = "Select Count(*) as count FROM GLPATR WHERE GL14PATR = '" + patronid + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
