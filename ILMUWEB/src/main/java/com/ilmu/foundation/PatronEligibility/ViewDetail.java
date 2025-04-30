/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.ilmu.foundation.PatronEligibility;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ViewDetail"})
public class ViewDetail
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block14: {
            response.setContentType("text/html");
            ArrayList<String> cate = null;
            ArrayList catelist = new ArrayList();
            Connection con = null;
            con = DBConnection.getConnection();
            String GL27CATE = request.getParameter("GL27CATE");
            String sqlqueary = "SELECT GL27CATE,GL07CATE,GL07DESC FROM GLELIG LEFT JOIN GLCATE ON GL07CATE = GL27CATE  WHERE GL27CATE='" + GL27CATE + "'";
            try {
                try {
                    con = DBConnection.getConnection();
                    try {
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sqlqueary);
                        while (rs.next()) {
                            cate = new ArrayList<String>();
                            cate.add(rs.getString("GL07DESC"));
                            catelist.add(cate);
                            System.out.println(sqlqueary);
                        }
                    }
                    catch (SQLException s) {
                        System.out.println("SQL statement is not executed!");
                    }
                    request.setAttribute("catelist", catelist);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        con.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block14;
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
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/modules/Foundation/18_PatronEligibility/AddWizard.jsp");
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
