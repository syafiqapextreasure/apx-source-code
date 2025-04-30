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
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Charging.Renewal;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AccessionRenewals"})
public class AccessionRenewal
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String msRetSMD;
    private String msRetItemCat;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private static Connection con = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block18: {
            Connection con = null;
            String matno = "";
            String status = "";
            String docno = "";
            String ddate = "";
            String dtime = "";
            String cdate = "";
            String ctime = "";
            String msBookTitle = "";
            try {
                try {
                    String CT03DOCNO = request.getParameter("name");
                    String GL14PATR = request.getParameter("patronid");
                    Renewal rnw = null;
                    Object details = null;
                    rnw = new Renewal();
                    boolean renewalfull = false;
                    renewalfull = rnw.ExecuteRenewal_V1(CT03DOCNO, GL14PATR);
                    String errmessage = rnw.getErrMessage();
                    String output = "";
                    Renewal renew = new Renewal();
                    BigDecimal msFines = rnw.calculatefines2(GL14PATR);
                    con = DBConnection.getConnection();
                    if (errmessage != null && !errmessage.isEmpty()) {
                        output = errmessage;
                        response.getWriter().println(output);
                        break block18;
                    }
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + CT03DOCNO + "'" + "AND CT03LOCA = GL05LOCA");
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        matno = rs.getString("CT03MATNO");
                        this.msRetSMD = rs.getString("CT03SMD");
                    }
                    PreparedStatement stmt2 = con.prepareStatement("SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + matno + "'");
                    List<ISBD> isbd = ISBD.getPunctuation("245");
                    ResultSet rsObj = stmt2.executeQuery();
                    while (rsObj.next()) {
                        msBookTitle = Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd);
                    }
                    PreparedStatement stmt3 = con.prepareStatement("SELECT * FROM CICIRC WHERE CI02DOCNO ='" + CT03DOCNO + "' and CI02FLAG = 'C' ORDER BY CI02CDATE DESC");
                    System.out.println(stmt3);
                    ResultSet rs1 = stmt3.executeQuery();
                    if (rs1.next()) {
                        docno = rs1.getString("CI02DOCNO");
                        ddate = rs1.getString("CI02DDATE");
                        dtime = rs1.getString("CI02DTIME");
                        status = rs1.getString("CI02FLAG");
                        cdate = rs1.getString("CI02CDATE");
                        ctime = rs1.getString("CI02CTIME");
                    }
                    if (matno != "" || msBookTitle != "") {
                        Patron patr = new Patron(GL14PATR);
                        this.msRetItemCat = this.RetrieveAccessionDetail(CT03DOCNO);
                        renew.renewalDate_V1();
                        if (status.equals("C")) {
                            status = "Circulated";
                        }
                        String msPatronExpDate = patr.getMsMemExpiryDate();
                        System.out.println("MemExp" + msPatronExpDate);
                        Date date = new Date();
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date memExp = sourceFormat.parse(msPatronExpDate);
                        System.out.println("Due" + rnw.getmsRawDateDue());
                        Date date1 = sourceFormat.parse(GeneralUtility.StrToDate(rnw.getmsRawDateDue()));
                        output = memExp.compareTo(date1) > 0 ? String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + GeneralUtility.StrToDate(rnw.getmsRawDateDue()) + "\n" + rnw.getmsRawTimeDue() + "\n" + rnw.getLateBy() + "\n" + msFines : "068";
                    } else {
                        output = "The item is not available for circulation";
                    }
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF8");
                    response.getWriter().println(output);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        con.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String RetrieveAccessionDetail(String msAccessionNo) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "'";
        System.out.println(sql);
        boolean exist = false;
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msRetItemCat = rsObj.getString("CT03ICAT");
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.msRetItemCat;
    }
}
