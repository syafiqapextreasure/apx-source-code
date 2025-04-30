/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

import com.kmlink.ilmu.overdueNotification.OverdueNotification;
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

@WebServlet(value={"/SentOverdueToMailBrowser"})
public class SentOverdueToMailBrowser
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String JSON = request.getParameter("json");
        String liferayLogin = request.getParameter("sender");
        String reminderType = request.getParameter("reminderType");
        String htmlText = null;
        ServletContext servletContext = this.getServletContext();
        OverdueNotification sendEmailtoPatron = new OverdueNotification();
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
        catch (IllegalArgumentException e) {
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
        String emailAddr = null;
        JSONObject jsonPassToClient = new JSONObject();
        ArrayList<String> listInsertedRow = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray(JSON);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        patron = jsonObject.getString("Patron");
        emailAddr = jsonObject.getString("Email");
        String filteredPatronId = patron.substring(0, patron.indexOf(44));
        String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
        String filterHTMLText = htmlText.replaceAll("\\\\", "");
        List<String> removedAccession = Document.removedAcessionNo(JSON, liferayLogin, reminderType);
        for (String removeAccDataTable : removedAccession) {
            listInsertedRow.add(removeAccDataTable);
        }
        JSONArray ja = new JSONArray();
        for (String row : listInsertedRow) {
            ja.put((Object)row);
        }
        jsonPassToClient.put("insertedRow", (Object)ja);
        if (reminderType == null) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER10");
                OverdueNotification.sendEmailtoPatronInNotication(newmailno, emailAddr, "OVERDUE REMINDER", htmlText);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("first")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER04");
                OverdueNotification.sendEmailtoPatronInNotication(newmailno, emailAddr, "OVERDUE FIRST REMINDER", htmlText);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("second")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER05");
                OverdueNotification.sendEmailtoPatronInNotication(newmailno, emailAddr, "OVERDUE SECOND REMINDER", htmlText);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (reminderType.equals("third")) {
            try {
                saveMail.InsertGLMAIL(JSON, newmailno, liferayLogin, filteredPatronId, htmlText, "ER06");
                OverdueNotification.sendEmailtoPatronInNotication(newmailno, emailAddr, "OVERDUE THIRD REMINDER", htmlText);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setCharacterEncoding("UTF8");
        response.setContentType("application/text");
        response.getWriter().print(jsonPassToClient.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
