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
 *  org.json.JSONException
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.google.gson.Gson;
import com.kmlink.ilmu.parableTemplateMaint.Label;
import com.kmlink.ilmu.parableTemplateMaint.Sheet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

@WebServlet(value={"/GetAttributeComponents"})
public class GetAttributeComponents
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String template = request.getParameter("selectedTemplate");
        String branch = request.getParameter("selectedBranch");
        String selectedAttribute = request.getParameter("selectedAttribute");
        if (selectedAttribute.equals("SHEET")) {
            try {
                String sheets = Sheet.getSheetData(template, branch, selectedAttribute);
                response.setCharacterEncoding("UTF8");
                response.setContentType("application/json");
                response.getWriter().print(sheets);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (selectedAttribute.equals("LABEL")) {
            try {
                String labels = Label.getLabelData(template, branch, selectedAttribute);
                response.setCharacterEncoding("UTF8");
                response.setContentType("application/json");
                response.getWriter().print(labels);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
