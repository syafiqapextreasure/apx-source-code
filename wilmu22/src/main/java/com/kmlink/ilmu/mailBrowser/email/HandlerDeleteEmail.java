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
package com.kmlink.ilmu.mailBrowser.email;

import com.kmlink.ilmu.mailBrowser.email.MailBrowser;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/HandlerDeleteEmail"})
public class HandlerDeleteEmail
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mailNo = request.getParameter("mailNo");
        try {
            boolean deletedMailNo = MailBrowser.deleteEmail(mailNo);
            response.setCharacterEncoding("UTF8");
            response.setContentType("application/text");
            response.getWriter().print(deletedMailNo);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
