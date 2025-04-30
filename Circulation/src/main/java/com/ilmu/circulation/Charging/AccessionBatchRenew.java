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
import com.ilmu.global.ErrorMessage_Handler;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
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

@WebServlet(value={"/AccessionBatchRenew"})
public class AccessionBatchRenew
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private static Connection con = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String output = "";
        int num = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date2 = new Date();
            String curnDate = dateFormat.format(date2).toString();
            System.out.println("curnDate" + curnDate);
            Date curnDateD = sourceFormat.parse(GeneralUtility.StrToDate(curnDate));
            System.out.println("curnDateD" + curnDateD);
            String GL14PATR = request.getParameter("patronid");
            String OverExpDate = request.getParameter("overExp");
            AccessionDischarge accessionDischarge = new AccessionDischarge();
            List<Renewal> loanItems = null;
            loanItems = Renewal.getLoanItems(GL14PATR);
            for (Renewal i : loanItems) {
                System.out.println("lepas break x i ? " + i.getCI02DOCNO());
                boolean renewalfull = false;
                Renewal rnw = null;
                rnw = new Renewal();
                renewalfull = rnw.ExecuteRenewal_V1(i.getCI02DOCNO(), GL14PATR);
                String errmessage = rnw.getErrMessage();
                System.out.println("errmessage accessionBatchRenewal " + errmessage + " " + i.getCI02DOCNO());
                BigDecimal msFines = rnw.calculatefines2(GL14PATR);
                String loanType = accessionDischarge.checkLoanType(i.getCI02DOCNO(), GL14PATR);
                String output1 = "";
                String renewDate = "";
                String renewTime = "";
                String testStringReplacement = "";
                if (errmessage != null && !errmessage.isEmpty()) {
                    String[] message = errmessage.split(",");
                    String codeErr = message[0];
                    String msgErr = message.length >= 2 ? message[1] : "";
                    List<ErrorMessage_Handler> ErrorMessage = null;
                    ErrorMessage = ErrorMessage_Handler.getGL79ERRMSG(codeErr);
                    for (ErrorMessage_Handler j : ErrorMessage) {
                        testStringReplacement = j.getGL79ERRMSG();
                        boolean containsPatrid = testStringReplacement.contains("@patrid");
                        if (containsPatrid) {
                            testStringReplacement = testStringReplacement.replace("@patrid", msgErr);
                        }
                        renewDate = "";
                        renewTime = "";
                        output1 = String.valueOf(output1) + "<td><span class='glyphicon glyphicon-info-sign' data-toggle='tooltip' title='" + testStringReplacement + "'></span></td>";
                    }
                } else {
                    System.out.println("yahhoooooo");
                    renewDate = GeneralUtility.StrToDate(rnw.getmsRawDateDue());
                    renewTime = rnw.getmsRawTimeDue();
                    output1 = String.valueOf(output1) + "<td></td>";
                }
                Patron patr = new Patron(GL14PATR);
                System.out.println("i.getCT05SRAW()" + i.getCT05SRAW());
                String msPatronExpDate = patr.getMsMemExpiryDate();
                System.out.println("MemExp" + msPatronExpDate);
                Date date = new Date();
                Date memExp = sourceFormat.parse(msPatronExpDate);
                System.out.println("Due" + rnw.getmsRawDateDue());
                Date date1 = sourceFormat.parse(GeneralUtility.StrToDate(rnw.getmsRawDateDue()));
                System.out.println("MemExp" + memExp.compareTo(date1));
                if (memExp.compareTo(curnDateD) > 0) {
                    if (memExp.compareTo(date1) > 0) {
                        System.out.println("MemExp Betul");
                        if (loanType.equals("H")) {
                            output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Hour(s)" + "</td>" + "<td>" + msFines + "</td>";
                            output = String.valueOf(output) + output1;
                            output = String.valueOf(output) + "</tr>";
                            continue;
                        }
                        if (!loanType.equals("D")) continue;
                        output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Day(s)" + "</td>" + "<td>" + msFines + "</td>";
                        output = String.valueOf(output) + output1;
                        output = String.valueOf(output) + "</tr>";
                        continue;
                    }
                    System.out.println("OverExpDate " + OverExpDate);
                    if (OverExpDate == "1" || OverExpDate == "2") {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(memExp);
                        cal.add(5, -1);
                        String msPatronBranch = Item.getItemBrnc(i.getCI02DOCNO());
                        System.out.println("msPatronBranch " + msPatronBranch);
                        int holiday = this.isWorkingDayorHoliday(cal, msPatronBranch);
                        System.out.println("Holiday " + holiday);
                        while (holiday > 0) {
                            cal.add(5, -1);
                            holiday = this.isWorkingDayorHoliday(cal, msPatronBranch);
                        }
                        renewDate = dateFormat2.format(cal.getTime());
                        if (loanType.equals("H")) {
                            output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Hour(s)" + "</td>" + "<td>" + msFines + "</td>";
                            output = String.valueOf(output) + output1;
                            output = String.valueOf(output) + "</tr>";
                            continue;
                        }
                        if (!loanType.equals("D")) continue;
                        output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Day(s)" + "</td>" + "<td>" + msFines + "</td>";
                        output = String.valueOf(output) + output1;
                        output = String.valueOf(output) + "</tr>";
                        continue;
                    }
                    System.out.println("MemExp almost");
                    renewDate = "";
                    renewTime = "";
                    if (loanType.equals("H")) {
                        output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Hour(s)" + "</td>" + "<td>" + msFines + "</td>";
                        output = String.valueOf(output) + "<td><span class='glyphicon glyphicon-info-sign' data-toggle='tooltip' title='This patron membership will expire before the due date, and no further transactions will be allowed.'></span></td>";
                        output = String.valueOf(output) + "</tr>";
                        continue;
                    }
                    if (!loanType.equals("D")) continue;
                    output = String.valueOf(output) + "<tr><td><input type = 'checkbox' class='isyraf' id = 'checkAll" + num + "' name='renew" + num + "'></td>" + "<td>" + num++ + "</td>" + "<td><span class='glyphicon glyphicon-book' data-toggle='tooltip' title='" + i.getCT05SRAW() + "'></span>&nbsp;" + i.getCI02DOCNO() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02CDATE()) + "</td>" + "<td>" + i.getCI02CTIME() + "</td>" + "<td>" + GeneralUtility.StrToDate(i.getCI02DDATE()) + "</td>" + "<td>" + i.getCI02DTIME() + "</td>" + "<td>" + i.getCI02FLAG() + "</td>" + "<td>" + renewDate + "</td>" + "<td>" + renewTime + "</td>" + "<td>" + rnw.getLateBy() + " Day(s)" + "</td>" + "<td>" + msFines + "</td>";
                    output = String.valueOf(output) + "<td><span class='glyphicon glyphicon-info-sign' data-toggle='tooltip' title='This patron membership will expire before the due date, and no further transactions will be allowed.'></span></td>";
                    output = String.valueOf(output) + "</tr>";
                    continue;
                }
                System.out.println("MemExp");
                output = "032";
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF8");
            response.getWriter().println(output);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
