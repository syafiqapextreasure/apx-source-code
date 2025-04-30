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
package com.ilmu.circulation.Item_Status_Maintenance;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/updateRecall"})
public class updateRecall
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Object message = null;
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        String docno = request.getParameter("docno");
        System.out.println(String.valueOf(docno) + " = docno");
        String matno = request.getParameter("matno");
        System.out.println(String.valueOf(matno) + " = matno");
        String accessionNo = request.getParameter("accessionNo");
        System.out.println(String.valueOf(accessionNo) + " = accessionNo");
        String recallBranch = request.getParameter("recallBranch");
        System.out.println(String.valueOf(recallBranch) + " = recallBranch");
        String getPatr = request.getParameter("getPatr");
        System.out.println(String.valueOf(getPatr) + " = getPatr");
        String id = request.getParameter("id");
        System.out.println(String.valueOf(id) + " = id");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        String cDate = GeneralUtility.DatetoStr(currentdate);
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss");
        String currentTime = timeFormat.format(date).toString();
        try {
            con = DBConnection.getConnection();
            String getIcatQuery = "SELECT CT03ICAT FROM CTDOCM WHERE CT03DOCNO = '" + docno + "' " + "AND CT03MATNO ='" + matno + "'";
            System.out.println(getIcatQuery);
            PreparedStatement statementIcat = con.prepareStatement(getIcatQuery);
            ResultSet rsIcat = statementIcat.executeQuery();
            while (rsIcat.next()) {
                String icat = rsIcat.getString("CT03ICAT");
                System.out.println(String.valueOf(icat) + "ghgkjgk");
                String query = "SELECT COUNT(CI03PRIOR) AS TOTALPRIOR FROM CIRESV WHERE  CI03MATNO = '" + matno + "' " + "AND CI03DOCNO = '" + docno + "'";
                System.out.println(query);
                PreparedStatement statement = con.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    String TOTALPRIOR = rs.getString("TOTALPRIOR");
                    System.out.println("TOTALPATR : " + TOTALPRIOR);
                    if (TOTALPRIOR.equals("0")) {
                        System.out.println("TOTALPATR = 0  Akan Insert");
                        String queryInsertPRIOR = "Insert into CIRESV (CI03PATR, CI03NSTAT, CI03RDATE, CI03RTIME, CI03PRIOR, CI03MATNO, CI03OFFIC, CI03DOCNO, CI03BRNC, CI03ICAT) values ('" + getPatr + "', 'X', '" + cDate + "', '" + currentTime + "', " + "'1', '" + matno + "', '" + id + "', '" + docno + "', '" + recallBranch + "', '" + icat + "')";
                        System.out.println("Insert into CIRESV (CI03PATR, CI03NSTAT, CI03RDATE, CI03RTIME, CI03PRIOR, CI03MATNO, CI03OFFIC, CI03DOCNO, CI03BRNC, CI03ICAT) values ('" + getPatr + "', 'X', '" + cDate + "', '" + currentTime + "', " + "'1', '" + matno + "', " + id + ", '" + docno + "', '" + recallBranch + "', '" + icat + "')");
                        System.out.println(queryInsertPRIOR);
                        PreparedStatement statementInsertPRIOR = con.prepareStatement(queryInsertPRIOR);
                        statementInsertPRIOR.executeUpdate();
                    } else {
                        System.out.println("TOTALPATR > 0 SELECT ");
                        String queryGetPRIOR = "SELECT CI03PRIOR FROM CIRESV WHERE CI03MATNO = '" + matno + "' AND CI03DOCNO = '" + docno + "' AND CI03NSTAT = 'X'";
                        System.out.println(queryGetPRIOR);
                        PreparedStatement statementGetPRIOR = con.prepareStatement(queryGetPRIOR);
                        ResultSet rs2 = statementGetPRIOR.executeQuery();
                        while (rs2.next()) {
                        }
                    }
                    String queryUpdateCTDOCM = "UPDATE CTDOCM SET CT03STAT = 'E' WHERE CT03DOCNO = '" + docno + "' " + "AND CT03MATNO = '" + matno + "'";
                    System.out.println(queryUpdateCTDOCM);
                    PreparedStatement statementUpdateCTDOCM = con.prepareStatement(queryUpdateCTDOCM);
                    statementUpdateCTDOCM.executeUpdate();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
