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
 *  org.springframework.util.CollectionUtils
 */
package com.ilmu.foundation.LibraryCalendar;

import com.google.gson.Gson;
import com.ilmu.foundation.LibraryCalendar.LibCalendar;
import com.ilmu.foundation.LibraryCalendar.SQLStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.CollectionUtils;

@WebServlet(value={"/CalendarJsonServlet"})
public class CalendarJsonServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String branch = request.getParameter("libraryBranch");
        System.out.println(String.valueOf(branch) + " branch");
        ArrayList<LibCalendar> calendar = new ArrayList<LibCalendar>();
        try {
            List<LibCalendar> holidays = SQLStatement.getHolidays(branch);
            List<LibCalendar> events = SQLStatement.getEvents(branch);
            if (!CollectionUtils.isEmpty(holidays)) {
                calendar.addAll(holidays);
            }
            if (!CollectionUtils.isEmpty(events)) {
                calendar.addAll(events);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(calendar));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
