/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.json.JSONException
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.Label;
import com.kmlink.ilmu.parableTemplateMaint.ParableTemplateMaintenance;
import com.kmlink.ilmu.parableTemplateMaint.Sheet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

@WebServlet(value={"/SaveAttribute"})
public class SaveAttribute
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String template = request.getParameter("selectedTemplate");
        String branch = request.getParameter("selectedBranch");
        String selectedAttrType = request.getParameter("sendSelectedAttribute");
        String arrJSONStyle = request.getParameter("arrJSONStyle");
        if (selectedAttrType.equals("SHEET")) {
            try {
                Sheet.saveSheet(template, branch, selectedAttrType, arrJSONStyle);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (selectedAttrType.equals("LABEL")) {
            try {
                Label.saveLabel(template, branch, selectedAttrType, arrJSONStyle);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ParableTemplateMaintenance.saveStyle(template, branch, selectedAttrType, arrJSONStyle);
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
