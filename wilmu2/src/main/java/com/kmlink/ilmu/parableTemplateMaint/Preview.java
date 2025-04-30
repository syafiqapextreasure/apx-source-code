/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.parableTemplateMaint.ParableTemplateMaintenance;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(value={"/previewParableTempt"})
public class Preview
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject object;
        String styleSelectedPrint = request.getParameter("styleSelectedPrint");
        String selectedTemplate = request.getParameter("selectedTemplate");
        String selectedBranch = request.getParameter("selectedBranch");
        String sendSelectedAttribute = request.getParameter("sendSelectedAttribute");
        String arrJSONStyle = request.getParameter("arrJSONStyle");
        HashMap<String, String> mapStyle = new HashMap<String, String>();
        HashMap<String, String> mapStyle1 = new HashMap<String, String>();
        if (sendSelectedAttribute.equals("SHEET")) {
            try {
                JSONArray array = new JSONArray(arrJSONStyle);
                int i = 0;
                while (i < array.length()) {
                    object = array.getJSONObject(i);
                    mapStyle.put(object.getString("dbFieldName"), object.getString("value"));
                    ++i;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (sendSelectedAttribute.equals("LABEL")) {
            try {
                JSONArray array = new JSONArray(arrJSONStyle);
                int i = 0;
                while (i < array.length()) {
                    object = array.getJSONObject(i);
                    mapStyle.put(object.getString("dbFieldName"), object.getString("value"));
                    ++i;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONArray array = new JSONArray(arrJSONStyle);
                int i = 0;
                while (i < array.length()) {
                    object = array.getJSONObject(i);
                    mapStyle.put(object.getString("name"), object.getString("value"));
                    ++i;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mapStyle.forEach((key, value) -> {
            String string2 = mapStyle1.put(String.valueOf(sendSelectedAttribute) + key, (String)value);
        });
        if (sendSelectedAttribute.equals("SHEET")) {
            mapStyle1.forEach((key, value) -> request.setAttribute(key, value));
        } else if (sendSelectedAttribute.equals("LABEL")) {
            mapStyle1.forEach((key, value) -> request.setAttribute(key, value));
        } else {
            mapStyle1.forEach((key, value) -> request.setAttribute(key, value));
        }
        Map<String, String> styleMapping = ParableTemplateMaintenance.mapStyle(selectedTemplate, selectedBranch, sendSelectedAttribute);
        mapStyle1.forEach((key, value) -> {
            String string2 = styleMapping.put(String.valueOf(sendSelectedAttribute) + key, (String)value);
        });
        styleMapping.forEach((key, value) -> request.setAttribute(key, value));
        if (selectedTemplate.startsWith("Book")) {
            try {
                request.getRequestDispatcher("WEB-INF/jsp/module/parableTemplateMaint/book/parableMaint_printBookLabel.jsp").forward((ServletRequest)request, (ServletResponse)response);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (selectedTemplate.startsWith("PAT")) {
            request.getRequestDispatcher("WEB-INF/jsp/module/parableTemplateMaint/book/parableMaint_printPatronLabel.jsp").forward((ServletRequest)request, (ServletResponse)response);
        } else if (selectedTemplate.startsWith("SPINE")) {
            request.getRequestDispatcher("WEB-INF/jsp/module/parableTemplateMaint/book/parableMaint_printSpineLabel.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
