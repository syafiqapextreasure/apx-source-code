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
package com.ilmu.foundation.CodeTable;

import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet(value={"/AddCode"})
public class AddCode
extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<String> code = null;
        ArrayList codelist = new ArrayList();
        Connection con = null;
        String value = request.getParameter("FCODE");
        String sqlqueary = "SELECT CODE, DESCRIPTION from FNDCODE WHERE FCODE='" + value + "'";
        System.out.println("test 3" + value);
        try {
            con = DBConnection.getConnection();
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sqlqueary);
                while (rs.next()) {
                    code = new ArrayList<String>();
                    code.add(rs.getString("CODE"));
                    code.add(rs.getString("DESCRIPTION"));
                    codelist.add(code);
                    System.out.println(sqlqueary);
                }
            }
            catch (SQLException s) {
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("codelist", codelist);
        String url = "/template?MODULE=Foundation/02_CodeTable&ACTION=CodeTable.jsp?value=" + value;
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection con = null;
        String FCODE = request.getParameter("FCODE");
        String FNAME = request.getParameter("FNAME");
        String CODE = request.getParameter("CODE");
        String DESCRIPTION = request.getParameter("DESCRIPTION");
        try {
            con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO FNDCODE(FCODE, FNAME, CODE, DESCRIPTION) VALUES(?,?,?,?)");
            stmt.setString(1, FCODE);
            stmt.setString(2, FNAME);
            stmt.setString(3, CODE);
            stmt.setString(4, DESCRIPTION);
            int i = stmt.executeUpdate();
            if (i > 0) {
                request.setAttribute("msg", (Object)"Success");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        this.doGet(request, response);
    }
}
