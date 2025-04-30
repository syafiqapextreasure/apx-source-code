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
package com.ilmu.ill.RequestAuthorisation;

import com.ilmu.global.Handler;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.global.globalFunc;
import com.ilmu.ill.RequestAuthorisation.RequestAuthorisation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Approve"})
public class Approve
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String liferayLogin = request.getParameter("liferayLogin");
        int total = Integer.parseInt(request.getParameter("total"));
        String reqno = request.getParameter("reqno");
        String vsRequestorID = "";
        String vsLibraryID = "";
        String vsRequestedDate = "";
        String vsExpectedDate = "";
        String vsTitle = "";
        String vsDocumentType = "";
        String vsVolume = "";
        String vsIssue = "";
        String vsPageNumber = "";
        String rsReferenceNumber = "";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            boolean sucess = false;
            String[] reqno2 = reqno.split(",");
            int i = 0;
            while (i < total) {
                String getREQValue = "SELECT IL01REQST0,IL01OFFIID,IL01DTPROC,IL01STATUS, IL01VRSION,IL01LNDLIB,IL01REQTER,IL01CRTNDT,IL01DBFORE, IL01TITLE0,IL01DOTYPE,IL01VOLUME,IL01ISSUES,IL01PAGENO,IL01REQCID FROM ILREQC WHERE IL01REQCID = '" + reqno2[i] + "'";
                PreparedStatement statementgetREQValue = con.prepareStatement(getREQValue);
                ResultSet rsgetREQValue = statementgetREQValue.executeQuery();
                while (rsgetREQValue.next()) {
                    vsRequestorID = Handler.ifIsNull(rsgetREQValue.getString("IL01REQTER"));
                    vsLibraryID = Handler.ifIsNull(rsgetREQValue.getString("IL01LNDLIB"));
                    vsRequestedDate = Handler.ifIsNull(rsgetREQValue.getString("IL01CRTNDT"));
                    vsExpectedDate = Handler.ifIsNull(rsgetREQValue.getString("IL01DBFORE"));
                    vsTitle = Handler.ifIsNull(rsgetREQValue.getString("IL01TITLE0"));
                    vsDocumentType = Handler.ifIsNull(rsgetREQValue.getString("IL01DOTYPE"));
                    vsVolume = Handler.ifIsNull(rsgetREQValue.getString("IL01VOLUME"));
                    vsIssue = Handler.ifIsNull(rsgetREQValue.getString("IL01ISSUES"));
                    vsPageNumber = Handler.ifIsNull(rsgetREQValue.getString("IL01PAGENO"));
                }
                int outreqno = globalFunc.Get98VALUE("OUTREQUESTNO");
                int newoutreqno = new Integer(outreqno + 1);
                globalFunc.updatingGlnumb(newoutreqno);
                String outreqnoConvert = String.format("%010d", newoutreqno);
                sucess = RequestAuthorisation.AddRequest(vsRequestorID, vsLibraryID, vsRequestedDate, vsExpectedDate, vsTitle, vsDocumentType, vsVolume, vsIssue, vsPageNumber, outreqnoConvert, liferayLogin);
                if (sucess) {
                    sucess = RequestAuthorisation.ApproveRequest(liferayLogin, reqno2[i], outreqnoConvert);
                }
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CIU0005", reqno2[i], liferayLogin);
                ++i;
            }
            PrintWriter out = response.getWriter();
            out.print("Done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
