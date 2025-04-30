/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.overdueNotification;

import com.google.gson.Gson;
import com.kmlink.ilmu.shared.pdf.Document;
import com.kmlink.ilmu.shared.pdf.saveMail;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(value={"/PrintDocument"})
public class PrintDocument
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String JSON = request.getParameter("json");
        String liferayLogin = request.getParameter("liferayLogin");
        String reminderType = request.getParameter("reminderType");
        String overdueType = request.getParameter("overdueType");
        ServletContext servletContext = this.getServletContext();
        String htmlText = null;
        try {
            htmlText = Document.PrintDocumentNestedForLoop(JSON, liferayLogin, reminderType, servletContext);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        int mailno = saveMail.Get98VALUE("MAILNO");
        int newmailno = new Integer(mailno + 1);
        try {
            saveMail.updatingmailno(newmailno);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String patron = null;
        JSONObject jsonPassToClient = new JSONObject();
        ArrayList<String> listInsertedRow = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray(JSON);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        patron = jsonObject.getString("Patron");
        String filteredPatronId = patron.substring(0, patron.indexOf(44));
        List<String> removedAccession = Document.removedAcessionNo(JSON, liferayLogin, reminderType);
        for (String removeAccDataTable : removedAccession) {
            listInsertedRow.add(removeAccDataTable);
        }
        JSONArray ja = new JSONArray();
        for (String row : listInsertedRow) {
            ja.put((Object)row);
        }
        jsonPassToClient.put("insertedRow", (Object)ja);
        jsonPassToClient.put("htmlLetter", (Object)htmlText);
        if (reminderType == null) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER10");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("first")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER04");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("second")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER05");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("third")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER06");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/text");
        response.getWriter().print(jsonPassToClient.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
