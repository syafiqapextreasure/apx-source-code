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
package com.kmlink.ilmu.reserveCollection.reserve;

import com.kmlink.ilmu.reserveCollection.reserve.ReserveCollection;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AddReserve"})
public class AddReserve
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String semesterId = request.getParameter("semesterId");
        String subjectId = request.getParameter("subjectId");
        String instructor = request.getParameter("instructor");
        String controlNo = request.getParameter("controlNo");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String createdDate = request.getParameter("createdDate");
        String recordedBy = request.getParameter("recordedBy");
        String title = request.getParameter("title");
        String callNo = request.getParameter("callNo");
        String publication = request.getParameter("publication");
        String author = request.getParameter("author");
        String docNo = request.getParameter("docNo");
        System.out.println("course id: " + courseId);
        System.out.println("semester id: " + semesterId);
        System.out.println("subject id: " + subjectId);
        System.out.println("instructor id: " + instructor);
        System.out.println("control id: " + controlNo);
        System.out.println("startDate: " + startDate);
        System.out.println("endDate: " + endDate);
        System.out.println("createdDate: " + createdDate);
        System.out.println("recordedBy: " + recordedBy);
        System.out.println("title: " + title);
        System.out.println("callNo: " + callNo);
        String getReserveNo = new String();
        try {
            getReserveNo = ReserveCollection.addReserve(courseId, semesterId, subjectId, instructor, controlNo, startDate, endDate, createdDate, recordedBy, title, callNo, publication, author, docNo);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/text");
        response.getWriter().print(getReserveNo);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
