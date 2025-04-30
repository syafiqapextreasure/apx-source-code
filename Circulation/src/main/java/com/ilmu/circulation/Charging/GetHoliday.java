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
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GetHoliday"})
public class GetHoliday
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> holidayList;
        block12: {
            String patron = request.getParameter("patron");
            Patron patr = new Patron();
            String branch = patr.getMsPatronBranch(patron);
            holidayList = new ArrayList<String>();
            try {
                try {
                    String sql = "SELECT GL30DATE FROM GLHOLIDAY WHERE GL30BRNC='" + branch + "'";
                    System.out.println(sql);
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sql);
                    String holidayDate = "";
                    while (rsObj.next()) {
                        holidayDate = DateTime.dateTimePicker(rsObj.getString("GL30DATE"));
                        holidayList.add("'" + holidayDate + "'");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ssd" + ((Object)holidayList).toString());
        response.getWriter().println(holidayList);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
