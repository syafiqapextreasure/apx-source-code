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
package com.ilmu.foundation.CourseCode;

import com.ilmu.global.GeneralUtility;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddCourseCode"})
public class AddCourseCode
extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        String message = null;
        String GL12COURSE = request.getParameter("GL12COURSE");
        String GL12DESC = request.getParameter("GL12DESC");
        String GL12TUTOR = request.getParameter("GL12TUTOR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        String cDate = GeneralUtility.DatetoStr(currentdate);
        try {
            try {
                con = DBConnection.getConnection();
                String sql = "INSERT INTO GLCOUR (GL12COURSE, GL12DESC, GL12TUTOR, \tGL12DATEREC) values (?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, GL12COURSE);
                statement.setString(2, GL12DESC);
                statement.setString(3, GL12TUTOR);
                statement.setString(4, cDate);
                System.out.println(sql);
                statement.executeUpdate();
                con.commit();
                con.close();
            }
            catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (SQLException ex2) {
                        ex2.printStackTrace();
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/03_CourseCode&ACTION=CourseTable.jsp");
                rd.forward((ServletRequest)request, (ServletResponse)response);
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=Foundation/03_CourseCode&ACTION=CourseTable.jsp");
            rd.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}
