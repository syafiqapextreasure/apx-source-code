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
package com.ilmu.foundation.DocumentStatus;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/editDocStatus"})
public class editDocStatus
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String documentStatus = request.getParameter("documentStatus");
        System.out.println("documentStatus >  " + documentStatus);
        String desc = request.getParameter("desc");
        System.out.println("desc >  " + desc);
        String chgto = request.getParameter("chgto");
        System.out.println("chgto >  " + chgto);
        Connection con = null;
        Object message = null;
        try {
            try {
                con = DBConnection.getConnection();
                String editDocStatus2 = "UPDATE GLDOCS SET GL36DESC = '" + desc + "', GL36CHGTO = '" + chgto + "' " + "WHERE GL36STAT = '" + documentStatus + "'";
                System.out.println("editDocStatus" + editDocStatus2);
                PreparedStatement statementinsertDocStatus = con.prepareStatement(editDocStatus2);
                int succ = statementinsertDocStatus.executeUpdate();
                if (succ > 0) {
                    request.setAttribute("msg", (Object)"Success");
                    System.out.println("Success");
                }
                System.out.println("--------------------------");
                con.commit();
                con.close();
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

    public String getServletInfo() {
        return "Short description";
    }
}
