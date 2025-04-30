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

import com.ilmu.circulation.Charging.Charging;
import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.io.IOException;
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

@WebServlet(value={"/AccessionServlet"})
public class AccessionServlet
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

    public AccessionServlet() {
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
        String CT03DOCNO = request.getParameter("name");
        String GL14PATR = request.getParameter("patronid");
        System.out.println("PatrID" + GL14PATR);
        String matno = "";
        String msBookTitle = "";
        System.out.println("From details" + CT03DOCNO + " " + GL14PATR);
        try {
            con = DBConnection.getConnection();
            Charging chr = new Charging();
            String action = request.getParameter("action");
            chr.executeCharging(CT03DOCNO, GL14PATR, action);
            String output = chr.getErrMessage();
            String newDue = null;
            if (action.equals("overrideMemExp")) {
                System.out.println("Override");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                String expDate = request.getParameter("expDate");
                Date date = dateFormat.parse(expDate);
                System.out.println("Date" + date);
                cal.setTime(date);
                cal.add(5, -1);
                System.out.println("Date" + cal.getTime());
                int holiday = this.isWorkingDayorHoliday(cal);
                System.out.println("Holiday" + holiday);
                while (holiday > 0) {
                    cal.add(5, -1);
                    holiday = this.isWorkingDayorHoliday(cal);
                }
                newDue = dateFormat.format(cal.getTime());
                System.out.println(String.valueOf(newDue) + "new");
                output = null;
            }
            if (output != null && !output.isEmpty()) {
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
                PreparedStatement stmt2 = con.prepareStatement("SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + matno + "'");
                System.out.println("Title" + stmt2);
                ResultSet rsObj = stmt2.executeQuery();
                while (rsObj.next()) {
                    msBookTitle = Handler.removeSubfield(rsObj.getString("CT05SRAW"));
                }
                if (matno != "" && msBookTitle != "") {
                    this.patr = new Patron(GL14PATR);
                    this.msRetItemCat = this.RetrieveAccessionDetails(CT03DOCNO, GL14PATR);
                    this.msPatronCategory = this.patr.getMsPatronCategory();
                    this.msPatronBranch = this.patr.getMsPatronBranch();
                    this.checkItemEligibility(this.msPatronCategory, this.msPatronBranch);
                    System.out.println(this.msLoanPeriod);
                    if (this.msLoanPeriod == 0) {
                        output = "025";
                    } else {
                        System.out.println("Testis");
                        this.chargingdate();
                        if (!action.equals("overrideMemExp")) {
                            newDue = GeneralUtility.StrToDate(this.msRawDateDue);
                        }
                        output = String.valueOf(CT03DOCNO) + "\n" + msBookTitle + "\n" + GeneralUtility.StrToDate(this.msRawDateCharged) + "\n" + GeneralUtility.StrToTime2(this.msRawTimeCharged) + "\n" + newDue + "\n" + GeneralUtility.StrToTime2(this.msRawTimeDue);
                    }
                }
            }
            response.setContentType("text/plain");
            response.getWriter().println(output);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
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

    public int numberOfHolidays(String currentdate, String duedate) {
        System.out.println("In Holiday");
        int holiday = 0;
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
            rsObj = stmt.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                holiday = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return holiday;
    }

    public int isHoliday(Date date, Calendar cal) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String startDate = dateFormat.format(date).toString();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE BETWEEN '" + startDate + "'AND '" + duedate + "') and GL30BRNC='" + this.msPatronBranch + "'";
            Statement st = conn.createStatement(1004, 1007);
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                rows = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return rows;
    }

    public void chargingdate() throws SQLException {
        boolean holidaycount = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
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
        String inclusive = "SELECT Count(*) as Count FROM GLLIBR WHERE GL28CIRCMODE = 'E'";
        rsObj = stmt.executeQuery(inclusive);
        Charging chr = new Charging();
        while (rsObj.next()) {
            if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
            int totalHoliday = chr.isHoliday(today, date, this.msPatronBranch);
            System.out.println(date);
            System.out.println(cal);
            System.out.println(totalHoliday);
            while (totalHoliday > 0) {
                today = date = cal.getTime();
                cal.add(5, totalHoliday);
                date = cal.getTime();
                totalHoliday = chr.isHoliday(today, date, this.msPatronBranch);
            }
        }
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
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
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
