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
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.AccessionDischarge;
import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Item;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.circulation.Charging.Renewal;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        block34: {
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
                    String OverExpDate = request.getParameter("overExp");
                    AccessionDischarge accessionDischarge = new AccessionDischarge();
                    String loanType = accessionDischarge.checkLoanType(CT03DOCNO, GL14PATR);
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
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF8");
                        response.getWriter().println(output);
                        break block34;
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
                        patr.checkPatr(GL14PATR);
                        patr.getErrMessage();
                        String errmessageP = patr.getErrMessage();
                        System.out.println(String.valueOf(errmessageP) + "errmessage Renewal ");
                        System.out.println("MemExp" + msPatronExpDate);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String curnDate = dateFormat.format(date).toString();
                        String msRawDateChargeds = GeneralUtility.DatetoStr(curnDate);
                        String chargeDate = GeneralUtility.StrToDate(msRawDateChargeds);
                        System.out.println("chargeDate " + chargeDate);
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date curnDateD = sourceFormat.parse(GeneralUtility.StrToDate(curnDate));
                        Date memExp = sourceFormat.parse(msPatronExpDate);
                        System.out.println("Due" + rnw.getmsRawDateDue());
                        Date date1 = sourceFormat.parse(GeneralUtility.StrToDate(rnw.getmsRawDateDue()));
                        System.out.println("MemExp" + memExp.compareTo(date1));
                        if (errmessageP != "082") {
                            System.out.println("not finelimit???");
                            if (memExp.compareTo(curnDateD) > 0) {
                                if (memExp.compareTo(date1) > 0) {
                                    if (loanType.equals("H")) {
                                        output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + GeneralUtility.StrToDate(rnw.getmsRawDateDue()) + "\n" + rnw.getmsRawTimeDue() + "\n" + rnw.getLateBy() + " Hour(s)" + "\n" + msFines;
                                    } else if (loanType.equals("D")) {
                                        output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + GeneralUtility.StrToDate(rnw.getmsRawDateDue()) + "\n" + rnw.getmsRawTimeDue() + "\n" + rnw.getLateBy() + " Day(s)" + "\n" + msFines;
                                    }
                                } else {
                                    System.out.println("OverExpDate Rnew " + OverExpDate);
                                    if (!OverExpDate.equals("null")) {
                                        String renewDate = "";
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(memExp);
                                        cal.add(5, -1);
                                        String msPatronBranch = Item.getItemBrnc(CT03DOCNO);
                                        System.out.println("msPatronBranch " + msPatronBranch);
                                        int holiday = this.isWorkingDayorHoliday(cal, msPatronBranch);
                                        System.out.println("Holiday " + holiday);
                                        while (holiday > 0) {
                                            cal.add(5, -1);
                                            String currentcal = dateFormat.format(cal.getTime()).toString();
                                            String msRawDateCal = GeneralUtility.DatetoStr(currentcal);
                                            String calDate = GeneralUtility.StrToDate(msRawDateCal);
                                            System.out.println("calDate " + calDate);
                                            System.out.println("chargeDate " + chargeDate);
                                            System.out.println("chargeDate == calDate " + chargeDate.equals(calDate));
                                            if (chargeDate.equals(calDate)) {
                                                holiday = 0;
                                                System.out.println("ALRIGHT AccessionServlet ");
                                                continue;
                                            }
                                            holiday = this.isWorkingDayorHoliday(cal, msPatronBranch);
                                            System.out.println("chargeDate AccessionServlet " + calDate);
                                        }
                                        renewDate = dateFormat2.format(cal.getTime());
                                        if (loanType.equals("H")) {
                                            output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + renewDate + "\n" + rnw.getmsRawTimeDue() + "\n" + rnw.getLateBy() + " Hour(s)" + "\n" + msFines;
                                        } else if (loanType.equals("D")) {
                                            output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + renewDate + "\n" + rnw.getmsRawTimeDue() + "\n" + rnw.getLateBy() + " Day(s)" + "\n" + msFines;
                                        }
                                    } else {
                                        System.out.println("MemExp almost");
                                        output = "068";
                                    }
                                }
                            } else {
                                System.out.println("MemExp");
                                output = "032";
                            }
                        } else {
                            System.out.println("FineLimit");
                            output = "082";
                        }
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

    private int isWorkingDayorHoliday(Calendar cal, String msRetItemBranch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate" + duedate);
        Connection conn = null;
        Statement st = null;
        try {
            try {
                String sql2 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                System.out.println("sql2 " + sql2);
                conn = DBConnection.getConnection();
                st = conn.createStatement();
                rsObj = st.executeQuery(sql2);
                while (rsObj.next()) {
                    rows = Integer.parseInt(rsObj.getString("count"));
                }
            }
            catch (SQLException e) {
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    if (rsObj != null) {
                        rsObj.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (rsObj != null) {
                    rsObj.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rows;
    }
}
