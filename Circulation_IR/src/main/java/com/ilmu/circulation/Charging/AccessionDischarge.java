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

import com.ilmu.circulation.Charging.Discharging;
import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.global.Handler;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/AccessionDischarges"})
public class AccessionDischarge
extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msRetAccessionNo;
    private String msRetStatus = "";
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msNoCircByPatronByItem;
    private String msRetItemBranch;
    private String msRetDocNo;
    String msRawDateDue;
    String msRawTimeDue;
    String msRawDateCharged;
    String msRawTimeCharged;
    private Patron patr;
    int msLoanPeriod;
    String msPatronCategory;
    String msPatronBranch;

    public AccessionDischarge() {
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        System.out.println("Discharge");
        String CT03DOCNO = request.getParameter("name");
        String GL14PATR = request.getParameter("patronid");
        System.out.println("Discharge" + CT03DOCNO + GL14PATR);
        Discharging discharge = new Discharging();
        boolean canDischarge = discharge.CanPatronDischarge(GL14PATR);
        System.out.println("sd" + GL14PATR);
        System.out.println("PatrID" + GL14PATR);
        String matno = "";
        String status = "";
        String docno = "";
        String ddate = "";
        String dtime = "";
        String cdate = "";
        String ctime = "";
        String msBookTitle = "";
        if (!canDischarge) {
            response.getWriter().println("081");
        } else {
            try {
                if (GL14PATR != null && GL14PATR != "" && !GL14PATR.equals("undefined")) {
                    con = DBConnection.getConnection();
                    Discharging chr = new Discharging();
                    chr.ExecuteDischarge(CT03DOCNO, GL14PATR);
                    String output = "";
                    BigDecimal msFines = chr.calculatefines2(GL14PATR);
                    if (chr.getErrMessage() != null && !chr.getErrMessage().isEmpty()) {
                        System.out.println("Testiss");
                        output = chr.getErrMessage();
                    } else {
                        PreparedStatement stmt = con.prepareStatement("SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + CT03DOCNO + "'" + "AND CT03LOCA = GL05LOCA");
                        System.out.println(stmt);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            matno = rs.getString("CT03MATNO");
                            this.msRetSMD = rs.getString("CT03SMD");
                        }
                        System.out.println("matno" + matno);
                        PreparedStatement stmt2 = con.prepareStatement("SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + matno + "'");
                        System.out.println("Title" + stmt2);
                        ResultSet rsObj = stmt2.executeQuery();
                        while (rsObj.next()) {
                            msBookTitle = Handler.removeSubfield(rsObj.getString("CT05SRAW"));
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
                            this.patr = new Patron(GL14PATR);
                            this.msRetItemCat = this.RetrieveAccessionDetails(CT03DOCNO, GL14PATR);
                            this.chargingdate();
                            if (status.equals("C")) {
                                status = "Circulated";
                            }
                            output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(cdate) + "\n" + GeneralUtility.StrToTime2(ctime) + "\n" + GeneralUtility.StrToDate(ddate) + "\n" + GeneralUtility.StrToTime2(dtime) + "\n" + status + "\n" + chr.getLateBy() + "\n" + msFines;
                        } else {
                            output = "The item is not available for circulation";
                        }
                    }
                    response.setContentType("text/plain");
                    response.getWriter().println(output);
                    con.close();
                } else {
                    response.getWriter().println("073");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post servlet");
        this.doGet(request, response);
    }

    public String RetrieveAccessionDetails(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "'";
        System.out.println("Accession" + sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetItemCat = rsObj.getString("CT03ICAT");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msRetItemCat;
    }

    public void RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03MATNOCT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetItemCat = rsObj.getString("CT03ICAT");
                this.msRetAccessionNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                this.msRetCondition = String.valueOf(rsObj.getString("CT03DDATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int checkItemEligibility(String msPatronCategory, String msPatronBranch) {
        this.msLoanPeriod = 0;
        try {
            String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msPatronBranch + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msLoanPeriod;
    }

    public void chargingdate() throws SQLException {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        System.out.println("Current Time " + currentTime);
        this.msPatronCategory = this.patr.getMsPatronCategory();
        this.msPatronBranch = this.patr.getMsPatronBranch();
        System.out.println("Loan Period" + this.msLoanPeriod + this.msPatronCategory + this.msPatronBranch);
        System.out.println("Loan" + this.msLoanPeriod);
        this.msLoanPeriod = this.checkItemEligibility(this.msPatronCategory, this.msPatronBranch);
        cal.add(5, this.msLoanPeriod);
        System.out.println("Loan Period : " + this.msLoanPeriod);
        date = cal.getTime();
        String duedate = dateFormat.format(date).toString();
        System.out.println("Due Date before holiday deduction" + duedate);
        String duetime = timeFormat.format(date).toString();
        String inclusive = "SELECT * FROM GLLIBR WHERE GL28CIRCMODE = 'E'";
        rsObj = stmt.executeQuery(inclusive);
        System.out.println(inclusive);
        if (rsObj.next()) {
            try {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                System.out.println("Number of Holidays" + holidaycount);
            }
            catch (SQLException sql2) {
                // empty catch block
            }
        }
        System.out.println("Holidays Count : " + holidaycount);
        cal.add(5, holidaycount);
        date = cal.getTime();
        int isholiday = 0;
        isholiday = this.isWorkingDayorHoliday(cal);
        System.out.println("Isholiday : " + isholiday);
        while (isholiday > 0) {
            cal.add(5, 1);
            isholiday = this.isWorkingDayorHoliday(cal);
            System.out.println(isholiday);
        }
        date = cal.getTime();
        duedate = dateFormat.format(date).toString();
        duetime = timeFormat.format(date).toString();
        System.out.println("Due Date after including number of holidays" + duedate);
        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        this.msRawTimeDue = GeneralUtility.TimetoStr(duetime);
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
        System.out.println(String.valueOf(this.msRawDateDue) + this.msRawTimeDue + this.msRawDateCharged);
    }

    private int isWorkingDayorHoliday(Calendar cal) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "'";
            Statement st = conn.createStatement(1004, 1007);
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            rows = 0;
            rsObj.last();
            rows = rsObj.getRow();
            rsObj.beforeFirst();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return rows;
    }
}
