/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.Charging;
import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Global.AuditTrail;
import com.kmlink.ilmu.circulation.Global.DateFormatter;
import com.kmlink.ilmu.circulation.Global.DateTime;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.circulation.sql.CirculationSQL;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Renewal {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    String msPatronRenewalEligibility = "";
    private String msRetAccessionNo = "";
    private String msRetStatus = "";
    private String msRetItemCat = "";
    private String msRetItemBranch = "";
    private String msRetMatNo = "";
    private String msRetDocNo = "";
    private String msRetSMD = "";
    private String msRetLocation = "";
    private String msRetPatron = "";
    private String msRetCondition = "";
    private String msRetDueDate = "";
    private String msNoCircByPatron = "";
    private String circDDate = "";
    private String circDTime = "";
    private int circCounter;
    private String resvPatr;
    private String resvPatrName;
    private String GL14NAME = "";
    private String CI03PATR = "";
    private String msPatronStatus = "";
    private String msPatronCategory = "";
    private String msPatronBranch = "";
    private int msLoanPeriod;
    String msPatronExpDate;
    String msRawDateDue;
    String msRawTimeDue;
    String msRawDateCharged;
    String msRawTimeCharged;
    String errmessage = "";
    int latedays = 0;
    String msPtype;
    private String CI02DOCNO = "";
    private String CI02CDATE = "";
    private String CI02CTIME = "";
    private String CI02DDATE = "";
    private String CI02DTIME = "";
    private String CI02FLAG = "";
    private String CT05SRAW = "";
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public Renewal() {
    }

    public Renewal(String CI02DOCNO, String CI02CDATE, String CI02CTIME, String CI02DDATE, String CI02DTIME, String CI02FLAG, String CT05SRAW) {
        this.CI02DOCNO = CI02DOCNO;
        this.CI02CDATE = CI02CDATE;
        this.CI02CTIME = CI02CTIME;
        this.CI02DDATE = CI02DDATE;
        this.CI02DTIME = CI02DTIME;
        this.CI02FLAG = CI02FLAG;
        this.CT05SRAW = CT05SRAW;
    }

    public void setmsCharged(String msRawDateDue) {
        this.msRawDateDue = msRawDateDue;
    }

    public void setmsRawTimeDue(String msRawTimeDue) {
        this.msRawTimeDue = msRawTimeDue;
    }

    public String getmsRawDateDue() {
        return this.msRawDateDue;
    }

    public String getmsRawTimeDue() {
        return this.msRawTimeDue;
    }

    public String getCI02CTIME() {
        return this.CI02CTIME;
    }

    public String getCI02DOCNO() {
        return this.CI02DOCNO;
    }

    public String getCI02CDATE() {
        return this.CI02CDATE;
    }

    public String getCI02DDATE() {
        return this.CI02DDATE;
    }

    public String getCI02DTIME() {
        return this.CI02DTIME;
    }

    public String getCI02FLAG() {
        return this.CI02FLAG;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,GL05BRNC, CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And CT03PATR='" + msPatronID + "' AND CT03STAT='C' AND CT03LOCA = GL05LOCA";
        System.out.println(sql);
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        boolean exist = false;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                while (rs1.next()) {
                    this.msRetAccessionNo = String.valueOf(rs1.getString("CT03DOCNO"));
                    this.msRetStatus = String.valueOf(rs1.getString("CT03STAT"));
                    this.msRetItemCat = String.valueOf(rs1.getString("CT03ICAT"));
                    this.msRetMatNo = String.valueOf(rs1.getString("CT03MATNO"));
                    this.msRetDocNo = String.valueOf(rs1.getString("CT03DOCNO"));
                    this.msRetSMD = String.valueOf(rs1.getString("CT03SMD"));
                    this.msRetLocation = String.valueOf(rs1.getString("CT03LOCA"));
                    this.msRetPatron = String.valueOf(rs1.getString("CT03PATR"));
                    this.msRetDueDate = String.valueOf(rs1.getString("CT03DDATE"));
                    this.msRetItemBranch = String.valueOf(rs1.getString("GL05BRNC"));
                    this.msRawDateDue = String.valueOf(rs1.getString("CT03DDATE"));
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public boolean getReservedDetails(String msAccessionNo) {
        boolean exist = false;
        String sql = "SELECT CI03PATR, GL14NAME from CIRESV, GLPATR WHERE GL14PATR=CI03PATR and CI03DOCNO = '" + msAccessionNo.trim() + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.resvPatr = String.valueOf(rsObj.getString("CI03PATR"));
                this.resvPatrName = String.valueOf(rsObj.getString("GL14NAME"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean chkReservedItem(String msAccessionNo) {
        String sql = "SELECT Count(*) as Count from CIRESV WHERE CI03DOCNO = '" + msAccessionNo.trim() + "'";
        System.out.println(sql);
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public boolean CanPatronRenew(String msPatronStatus) {
        boolean validate = false;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION3 From GLSTAT Where GL08STAT = '" + msPatronStatus.trim() + "'";
            System.out.println(sql);
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION3").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public int CountPatronRenewCount(String msPatronCategory, String msRetItemCat, String msRetSMD, String msItemBranch) {
        int patronrenewcount = 0;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "Select GL27RENEW, GL27PLOAN From GLELIG WHERE GL27CATE = '" + msPatronCategory.trim() + "' And GL27ICAT='" + msRetItemCat + "' AND GL27SMD='" + msRetSMD + "'AND GL27BRNC='" + msItemBranch + "'";
                System.out.println(sql);
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    patronrenewcount = rsObj.getInt("GL27RENEW");
                    this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patronrenewcount;
    }

    public int patronCirculationRenewCount(String msPatronID, String msAccessionNo) {
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        int circpatronrenewcount = 0;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                String sql = "Select * From CICIRC WHERE CI02DOCNO = '" + msAccessionNo.trim() + "' And CI02PATR='" + msPatronID + "'";
                System.out.println(sql);
                rs1 = stmt1.executeQuery(sql);
                while (rs1.next()) {
                    circpatronrenewcount = rs1.getInt("CI02RENEW");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return circpatronrenewcount;
    }

    public String dischargedDate(String CI02DOCNO, String CI02PATR) {
        String dischargedDate = null;
        try {
            stmt = conn.createStatement();
            String sql = "Select * From CICIRC WHERE CI02DOCNO = '" + CI02DOCNO.trim() + "' And CI02PATR='" + CI02PATR + "'";
            System.out.println(sql);
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                dischargedDate = rsObj.getString("CI02DDATE");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dischargedDate;
    }

    public void renewaldate() throws SQLException {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        System.out.println(currentTime);
        Date currentDate = new Date();
        System.out.println("New Date" + dateFormat.format(currentDate));
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(5, 14);
        Date currentDatePlusOne = c.getTime();
        System.out.println("Added Date" + dateFormat.format(currentDatePlusOne));
        cal.add(5, this.msLoanPeriod);
        date = cal.getTime();
        String duedate = dateFormat.format(date).toString();
        System.out.println(duedate);
        String duetime = timeFormat.format(date).toString();
        System.out.println("Due Date before holiday deduction" + duedate);
        String inclusive = "SELECT * FROM GLLIBR WHERE GL28CIRCMODE = 'I'";
        rsObj = stmt.executeQuery(inclusive);
        System.out.println(inclusive);
        if (rsObj.next()) {
            try {
                System.out.println("Date2" + currentdate);
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "' " + "GL28BRNC='" + this.msRetItemBranch + "'";
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                System.out.println("C" + holidaycount);
            }
            catch (SQLException sql2) {
                // empty catch block
            }
        }
        System.out.println("D55" + holidaycount);
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
        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        this.msRawTimeDue = GeneralUtility.TimetoStr(duetime);
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

    public boolean checkReservationStatus(String msPatronID, String msAccessionNo) {
        boolean reservationstatus = false;
        String sql = "SELECT CI03PATR,GL14NAME from CIRESV, GLPATR where GL14PATR=CI03PATR and CI03DOCNO='" + msAccessionNo + "' OR CI03MATNO=(SELECT CT03MATNO FROM CTDOCM WHERE CI03DOCNO='" + msAccessionNo + "')";
        System.out.println(sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.CI03PATR = rsObj.getString("CI03PATR");
                this.GL14NAME = rsObj.getString("GL14NAME");
                reservationstatus = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationstatus;
    }

    public boolean canRenew(String msPatronID) {
        boolean reservationstatus = false;
        String sql = "SELECT GL08ACTION3 from GLSTAT, GLPATR where GL08STAT=GL14STAT and GL14PATR='" + msPatronID + "'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (!rsObj.getString("GL08ACTION3").equals("Y")) continue;
                    reservationstatus = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reservationstatus;
    }

    public boolean ExecuteRenewal_V1(String msAccessionNo, String msPatronID) throws SQLException {
        boolean bSucessful;
        block17: {
            Patron a = new Patron(msPatronID);
            this.msPatronStatus = a.getMsPatronStatus(msPatronID);
            this.msPatronCategory = a.getMsPatronCategory(msPatronID);
            this.msPatronBranch = a.getMsPatronBranch(msPatronID);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            boolean canPatronRenew = false;
            int patronRenewCount = 0;
            int circPatronRenewCount = 0;
            bSucessful = false;
            try {
                canPatronRenew = this.CanPatronRenew(this.msPatronStatus);
                if (canPatronRenew) {
                    Date dueDate;
                    Patron patr = new Patron(msPatronID);
                    this.msPatronExpDate = patr.getMsMemExpiryDate();
                    Date date = new Date();
                    SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date memExp = sourceFormat.parse(this.msPatronExpDate);
                    if (memExp.compareTo(dueDate = sourceFormat.parse(sourceFormat.format(date))) > 0) {
                        bSucessful = true;
                        String currentdate = dateFormat.format(date).toString();
                        bSucessful = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
                        System.out.println(currentdate);
                        Date date1 = dateFormat.parse(currentdate);
                        String msRetDueDate2 = GeneralUtility.StrToDate(this.msRetDueDate);
                        Date date2 = dateFormat.parse(msRetDueDate2);
                        if (this.chkReservedItem(msAccessionNo)) {
                            this.getReservedDetails(msAccessionNo);
                            System.out.println("Date1 is after Date2");
                            System.out.println("This patron is not allowed to renew an overdue item");
                            bSucessful = false;
                            this.errmessage = "107," + this.resvPatrName + " (" + this.resvPatr + ")";
                        } else {
                            bSucessful = true;
                        }
                        if (date1.compareTo(date2) > 0) {
                            if (!this.patrCateAllowOvd(msPatronID) || !this.eligAllowOvd(this.msPatronCategory, this.msRetItemBranch)) {
                                System.out.println("Date1 is after Date2");
                                System.out.println("This patron is not allowed to renew an overdue item");
                                bSucessful = false;
                                this.errmessage = "031";
                                break block17;
                            }
                            bSucessful = true;
                        }
                        if (bSucessful) {
                            System.out.println("IN23");
                            if (this.canRenew(msPatronID)) {
                                System.out.println("IN");
                                patronRenewCount = this.CountPatronRenewCount(this.msPatronCategory, this.msRetItemCat, this.msRetSMD, this.msRetItemBranch);
                                if (patronRenewCount <= 0) {
                                    System.out.println("This patron is not allowed to Renew for this item category");
                                    bSucessful = false;
                                    this.errmessage = "089";
                                    break block17;
                                }
                                circPatronRenewCount = this.patronCirculationRenewCount(msPatronID, msAccessionNo);
                                if (++circPatronRenewCount > patronRenewCount && patronRenewCount != 0) {
                                    System.out.println("This patron has exceed renewal eligibility");
                                    this.errmessage = "030";
                                    bSucessful = false;
                                } else {
                                    ++circPatronRenewCount;
                                    System.out.println("Increase COunt");
                                    bSucessful = true;
                                }
                            } else {
                                this.errmessage = "021";
                            }
                        }
                    } else {
                        bSucessful = false;
                        System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
                        this.errmessage = "032";
                    }
                } else {
                    System.out.println("This patron is not allowed to Renew");
                    this.errmessage = "028";
                }
                this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
                this.renewalDate_V1();
            }
            catch (SQLException patr) {
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bSucessful;
    }

    public boolean renew_ori(String msAccessionNo, String msPatronID, String msRawDateDue, String msRawTimeDue, String username) throws SQLException {
        boolean bSucessful = false;
        String msOfficerID = username;
        int circPatronRenewCount = 0;
        this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        BigDecimal msFines = this.calculatefines2(msPatronID);
        try {
            circPatronRenewCount = this.patronCirculationRenewCount(msPatronID, msAccessionNo);
            ++circPatronRenewCount;
            if (msFines.signum() != 0) {
                this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
                this.UpdateFinesTransaction(msPatronID, DateFormatter.displayToDBFormat(msRawDateDue), msFines, msOfficerID, msAccessionNo);
            }
            if (!(bSucessful = this.UpdateCirculationDetails(DateFormatter.displayToDBFormat(msRawDateDue), GeneralUtility.TimetoStr(msRawTimeDue), msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo, circPatronRenewCount, msOfficerID))) {
                System.out.println("Circulation details not updated");
            }
            if (!(bSucessful = this.RenewingAccessionStatus(DateFormatter.displayToDBFormat(msRawDateDue), GeneralUtility.TimetoStr(msRawTimeDue), msAccessionNo, username))) {
                System.out.println("Accession Details not updated");
            }
            System.out.println("3ss" + bSucessful + msAccessionNo + msPatronID + msPatronID);
            AuditTrail.InsertAudit("CI", "CII0003", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (UnknownHostException | SQLException exception) {
            // empty catch block
        }
        return bSucessful;
    }

    public boolean renew(String msAccessionNo, String msPatronID, String msRawDateDue, String msRawTimeDue, String username) throws SQLException, UnknownHostException {
        boolean bSucessful = false;
        String msOfficerID = username;
        int circPatronRenewCount = 0;
        this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        BigDecimal msFines = this.calculatefines2(msPatronID);
        try {
            circPatronRenewCount = this.patronCirculationRenewCount(msPatronID, msAccessionNo);
            ++circPatronRenewCount;
            if (msFines.signum() != 0) {
                this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
                this.UpdateFinesTransaction(msPatronID, DateFormatter.displayToDBFormat(msRawDateDue), msFines, msOfficerID, msAccessionNo);
            }
            if (!(bSucessful = this.UpdateCirculationDetails(DateFormatter.displayToDBFormat(msRawDateDue), GeneralUtility.TimetoStr(msRawTimeDue), msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo, circPatronRenewCount, msOfficerID))) {
                System.out.println("Circulation details not updated");
            }
            if (!(bSucessful = this.RenewingAccessionStatus(DateFormatter.displayToDBFormat(msRawDateDue), GeneralUtility.TimetoStr(msRawTimeDue), msAccessionNo, username))) {
                System.out.println("Accession Details not updated");
            }
            System.out.println("3ss" + bSucessful + msAccessionNo + msPatronID + msPatronID);
            AuditTrail.InsertAudit("CI", "CII0003", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean UpdateCirculationDetails(String msRawDateDue, String msRawTimeDue, String msOfficerID, String msPatronID, String msAccessionNo, String msRetDocNo, int renewcount, String username) {
        Connection conn1 = null;
        Statement stmt1 = null;
        Object rs1 = null;
        boolean success = false;
        try {
            try {
                String sSQLStmt1 = " Update CICIRC Set CI02DDATE = '" + msRawDateDue.trim() + "' ," + "CI02DTIME = '" + msRawTimeDue.trim() + "' ," + "CI02RDATE = '" + DateTime.getTodayDate() + "' ," + "CI02RTIME = '" + DateTime.getToday24Time() + "' ," + "CI02RENEW = '" + renewcount + "' ," + "CI02ROFFID = '" + username + "' " + "Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02DOCNO = '" + msAccessionNo.trim() + "'  " + "And CI02FLAG = 'C'";
                conn1 = DBConnection.getConnection();
                conn1.setAutoCommit(false);
                stmt1 = conn1.createStatement(1005, 1008);
                System.out.println(sSQLStmt1);
                int count = stmt1.executeUpdate(sSQLStmt1);
                if (count > 0) {
                    success = true;
                    conn1.commit();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean UpdatePatronLastReturn(String msPatronID, String msRawDateReturned) throws SQLException {
        boolean success = false;
        String sSQlStmt3 = "Update GLPATR Set GL14LASTRET = '" + msRawDateReturned + "' " + "Where GL14PATR = '" + msPatronID + "'";
        System.out.println(sSQlStmt3);
        int count = stmt.executeUpdate(sSQlStmt3);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    public boolean patrCateAllowOvd(String GL14PATR) {
        boolean exist = false;
        try {
            try {
                String sql = "SELECT COUNT(*) as Count FROM GLCATE, GLPATR WHERE GL14CATE=GL07CATE AND GL14PATR = '" + GL14PATR + "' AND GL07ALLOWOVD='Y'";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public boolean eligAllowOvd(String msPatronCategory, String msRetItemBranch) {
        boolean exist = false;
        try {
            try {
                String sql = "SELECT COUNT(*) as Count FROM GLELIG WHERE GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msRetItemBranch + "' AND GL27ALLOWOVD='Y'";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public int checkItemEligibility(String msPatronCategory, String msItemBranch) {
        int GL27ELIG = 0;
        this.msLoanPeriod = 0;
        System.out.println(this.msPatronBranch);
        try {
            String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msItemBranch + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                GL27ELIG = rsObj.getInt("GL27ELIG");
                this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return GL27ELIG;
    }

    public int numberOfHolidays(String currentdate, Calendar cal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
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

    private int isWorkingDayorHoliday(Calendar cal, String msItemBranch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msItemBranch + "'";
            System.out.println("Testsql2");
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement(1004, 1007);
            System.out.println("Test1sql2");
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            rows = 0;
            rsObj.last();
            rows = rsObj.getRow();
            rsObj.beforeFirst();
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return rows;
    }

    private int isWorkingDayorHoliday_v1(Calendar cal, String msRetItemBranch, int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int incrementDay = 1;
        Date nextDay = new Date();
        cal.add(5, incrementDay);
        nextDay = cal.getTime();
        String duedate = dateFormat.format(nextDay).toString();
        boolean next = false;
        boolean stop = false;
        boolean holiday = false;
        boolean libClose = false;
        try {
            while (!stop) {
                String sql2 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                rsObj = st.executeQuery(sql2);
                System.out.println(sql2);
                next = rsObj.next();
                if (next) {
                    int result = Integer.parseInt(rsObj.getString("count"));
                    holiday = result > 0;
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    LocalDateTime openTime = nextDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    String day = openTime.format(formatterOutput);
                    day = Handler.getDay(day);
                    String sql3 = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
                    String libStart = "";
                    try {
                        rsObj = st.executeQuery(sql3);
                        System.out.println(sql3);
                        if (rsObj.next()) {
                            libStart = rsObj.getString("GL37START");
                        }
                        libClose = libStart == "";
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (!holiday && !libClose) {
                    ++count;
                    break;
                }
                ++count;
                cal.add(5, incrementDay);
                nextDay = cal.getTime();
                duedate = dateFormat.format(nextDay).toString();
                stop = false;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return count;
    }

    private boolean isWorkingDayorHoliday_V1(Calendar cal, String msRetItemBranch) {
        int rows = 0;
        boolean isholiday = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method: " + duedate);
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                ++rows;
            }
            if (rows >= 1) {
                isholiday = true;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return isholiday;
    }

    private String getPtype(String cate, String icat) {
        String ptype = "";
        try {
            try {
                String sql2 = "SELECT GL27PTYPE FROM GLELIG WHERE GL27CATE ='" + this.msPatronCategory + "' and GL27ICAT='" + this.msRetItemCat + "' and GL27SMD='" + this.msRetSMD + "' and GL27BRNC='" + this.msRetItemBranch + "'";
                System.out.println("Test" + sql2);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    ptype = rsObj.getString("GL27PTYPE");
                }
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ptype;
    }

    private String calDueDate(Date date) {
        block18: {
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            DBConnection db = new DBConnection();
            String inclusive = "";
            if (DBConnection.getDBType().equals("oracle")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR WHERE rownum = 1";
            } else if (DBConnection.getDBType().equals("mssql")) {
                inclusive = "SELECT TOP 1 GL28CIRCMODE FROM GLLIBR";
            } else if (DBConnection.getDBType().equals("mysql")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR WHERE LIMIT 1";
            }
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(inclusive);
                    Charging chr = new Charging();
                    while (rsObj.next()) {
                        System.out.println("Exclude" + rsObj.getString("GL28CIRCMODE"));
                        if (!rsObj.getString("GL28CIRCMODE").equals("E")) continue;
                        System.out.println("Exclude");
                        int totalHoliday = chr.isHoliday(today, date, this.msRetItemBranch);
                        System.out.println("Exclude" + totalHoliday);
                        while (totalHoliday > 0) {
                            today = date = cal.getTime();
                            cal.add(5, totalHoliday);
                            date = cal.getTime();
                            totalHoliday = chr.isHoliday(today, date, this.msRetItemBranch);
                            System.out.println("Excludes" + totalHoliday);
                        }
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block18;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return date.toString();
    }

    private String getTime_V1(int loanPeriod, LocalDateTime currentTime, LocalDate localDate) throws ParseException {
        block12: {
            try {
                try {
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(locale);
                    String day = localDate.format(formatterOutput);
                    day = Handler.getDay(day);
                    Calendar calendar1 = Calendar.getInstance();
                    boolean isHoliday = false;
                    isHoliday = this.isWorkingDayorHoliday_V1(calendar1, this.msRetItemBranch);
                    String libEnd = CirculationSQL.getMsLibClose(this.msRetItemBranch, day);
                    int count = 0;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(libEnd);
                    if (!rsObj.next()) break block12;
                    String libClose = DateTime.convertLibraryTiming(rsObj.getString("GL37STOP"));
                    libClose = String.valueOf(DateTime.getSysTodayDate()) + "T" + libClose;
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter formatForTime = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime closeTime = LocalDateTime.parse(libClose);
                    LocalDateTime addEligTimeToCurrentTime = currentTime.plus(loanPeriod, ChronoUnit.HOURS);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Calendar cal = Calendar.getInstance();
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.clear();
                    calendar.set(currentTime.getYear(), currentTime.getMonthValue() - 1, currentTime.getDayOfMonth());
                    if (addEligTimeToCurrentTime.isAfter(closeTime)) {
                        Duration duration = Duration.between(addEligTimeToCurrentTime, closeTime);
                        int isholiday = 0;
                        isholiday = this.isWorkingDayorHoliday_v1(calendar, this.msRetItemBranch, count);
                        String duedate = this.calDueDate(calendar.getTime());
                        cal.add(5, isholiday);
                        date = cal.getTime();
                        duedate = dateFormat.format(date).toString();
                        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                        LocalDateTime openTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        day = openTime.format(formatterOutput);
                        day = Handler.getDay(day);
                        String libStart = CirculationSQL.getMsLibOpen_V1(this.msRetItemBranch, day);
                        String libOpen = DateTime.convertLibraryTiming(libStart);
                        libOpen = String.valueOf(openTime.format(formatterDate)) + "T" + libOpen;
                        openTime = LocalDateTime.parse(libOpen);
                        currentTime = openTime.plus(duration.abs());
                        this.msRawTimeDue = currentTime.format(formatForTime);
                        break block12;
                    }
                    date = cal.getTime();
                    String duedate = dateFormat.format(date).toString();
                    this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
                    this.msRawTimeDue = currentTime.plusHours(loanPeriod).format(formatForTime);
                }
                catch (SQLException sQLException) {
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.msRawTimeDue;
    }

    public void renewalDate_V1() throws SQLException, ParseException {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        Calendar cal = Calendar.getInstance();
        Date currentDate = new Date();
        Date today = new Date();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        this.msPtype = this.getPtype(this.msPatronCategory, this.msRetItemBranch);
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        cal.add(5, this.msLoanPeriod);
        date = cal.getTime();
        String duedate = dateFormat.format(date).toString();
        String duetime = timeFormat.format(date).toString();
        if (this.msPtype.equals("H")) {
            String time;
            this.msRawTimeDue = time = this.getTime_V1(this.msLoanPeriod, now, localDate);
        } else {
            Locale locale = Locale.US;
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(locale);
            this.msRawTimeDue = now.format(formatterTime);
        }
        if (!this.msPtype.equals("H")) {
            String inclusive = "SELECT Count(*) as Count FROM GLLIBR WHERE GL28CIRCMODE = 'I'";
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(inclusive);
                System.out.println(inclusive);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    int totalHoliday = this.isHoliday(today, date, this.msRetItemBranch);
                    while (totalHoliday > 0) {
                        today = date = cal.getTime();
                        cal.add(5, totalHoliday);
                        date = cal.getTime();
                        totalHoliday = this.isHoliday(today, date, this.msRetItemBranch);
                    }
                }
            }
            catch (SQLException e) {
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            cal.add(5, holidaycount);
            date = cal.getTime();
            int isholiday = 0;
            isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
            while (isholiday > 0) {
                cal.add(5, 1);
                isholiday = this.isWorkingDayorHoliday(cal, this.msRetItemBranch);
            }
            date = cal.getTime();
            duedate = dateFormat.format(date).toString();
            duetime = timeFormat.format(date).toString();
            this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
            this.msRawTimeDue = duetime;
        }
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
    }

    public int isHoliday(Date today, Date duedate, String branch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        String startDate = dateFormat.format(today).toString();
        String duedates = dateFormat.format(duedate).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE> '" + startDate + "'AND GL30DATE<='" + duedates + "') and GL30BRNC='" + branch + "'";
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

    public boolean RetrieveCirculationDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT * FROM CICIRC WHERE CI02DOCNO = '" + msAccessionNo.trim() + "' And CI02PATR='" + msPatronID + "'AND CI02FLAG='C'";
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.circDDate = String.valueOf(rsObj.getString("CI02DDATE"));
                    this.circDTime = String.valueOf(rsObj.getString("CI02DTIME"));
                    this.circCounter = rsObj.getInt("CI02COUNTER");
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public boolean UpdateFinesTransaction(String msPatronID, String msRawDateReturned, BigDecimal msFines, String msOfficerID, String msAccessionNo) throws SQLException {
        int iCounter;
        String msCode;
        boolean success;
        block27: {
            success = false;
            msCode = "100";
            iCounter = 0;
            String sql2 = "SELECT * FROM GLNUMB where GL98FUNC='TRXNO'";
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sql2);
                    while (rsObj.next()) {
                        iCounter = rsObj.getInt("GL98VALUE");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block27;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        int count = 0;
        int count2 = 0;
        try {
            String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
            String sSQlStmt3 = "Insert Into RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) values(" + (iCounter + 1) + "," + "'" + msCode + "'" + "," + "'" + msRawDateReturned + "'" + "," + msFines + "," + "0," + "0," + "'" + msAccessionNo.trim() + "'" + "," + "'" + msPatronID + "'" + "," + "'" + RE01REFER + "'" + "," + "'" + msOfficerID + "'" + ",null" + "," + "'" + msAccessionNo.trim() + "'" + ",null," + "null," + "null," + "'" + this.circCounter + "'" + ",null" + ")";
            System.out.println(sSQlStmt3);
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            count = stmt.executeUpdate(sSQlStmt3);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            try {
                try {
                    String sql3 = "Update GLNUMB set GL98VALUE='" + (iCounter + 1) + "'" + "where GL98FUNC='TRXNO'";
                    System.out.println(sql3);
                    count2 = stmt.executeUpdate(sql3);
                    if (count2 > 0) {
                        success = true;
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    public boolean RenewingAccessionStatus(String msRawDateDue, String msRawTimeDue, String msAccessionNo, String officerId) throws SQLException {
        Connection conn1 = null;
        Statement stmt1 = null;
        Object rs1 = null;
        boolean success = false;
        try {
            try {
                String sSQlStmt4 = "Update CTDOCM Set CT03DDATE = '" + msRawDateDue.trim() + "', " + "CT03DTIME = '" + msRawTimeDue + "', " + "CT03LASTACT  = '" + DateTime.getTodayDate() + "' " + "Where CT03DOCNO = '" + msAccessionNo.trim() + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
                System.out.println(sSQlStmt4);
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                int count = stmt1.executeUpdate(sSQlStmt4);
                if (count > 0) {
                    success = true;
                    conn1.commit();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public String getMsPatronCategory(String msPatronID) {
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql);
                while (rs1.next()) {
                    this.msPatronCategory = String.valueOf(rs1.getString("GL14CATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return this.msPatronCategory;
    }

    public BigDecimal fineRate(String msPatronID, int stopDay) {
        String sql3 = null;
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rsObj = null;
        sql3 = "SELECT  GL38START, GL38STOP, GL38RATE, gl38first FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
        double rate = 0.0;
        BigDecimal finerate = BigDecimal.ZERO;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                System.out.println(sql3);
                rsObj = stmt1.executeQuery(sql3);
                int row = this.rowcount(this.msPatronCategory, this.msRetItemCat, this.msRetSMD, this.msRetItemBranch);
                while (rsObj.next()) {
                    BigDecimal GL38RATE;
                    if (stopDay <= 0) {
                        break;
                    }
                    if (rsObj.getString("GL38STOP") != null) {
                        if (stopDay >= Integer.parseInt(rsObj.getString("GL38START")) && stopDay <= Integer.parseInt(rsObj.getString("GL38STOP"))) {
                            GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                            int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                            BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                            finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                            stopDay = 0;
                            continue;
                        }
                        if (stopDay < Integer.parseInt(rsObj.getString("GL38STOP")) || row != 1) continue;
                        GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                        int noOfDays = 0;
                        noOfDays = Integer.parseInt(rsObj.getString("GL38START")) > 1 ? Integer.parseInt(rsObj.getString("GL38STOP")) - Integer.parseInt(rsObj.getString("GL38START")) + 1 : Integer.parseInt(rsObj.getString("GL38STOP"));
                        BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                        finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                        stopDay = 0;
                        continue;
                    }
                    System.out.println("line 2208 its null4: " + rsObj.getString("GL38START") + rsObj.getString("GL38FIRST"));
                    GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                    System.out.println("1" + GL38RATE);
                    BigDecimal GL38FIRST = new BigDecimal(rsObj.getString("GL38FIRST"));
                    System.out.println("2" + GL38FIRST);
                    int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                    BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                    finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                    stopDay = 0;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finerate;
    }

    public int rowcount(String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch) {
        String sql3 = null;
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String dbtype = DBConnection.getDBType();
        int count = 0;
        if (dbtype.equals("mssql")) {
            sql3 = "SELECT  Count(*) as count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        } else if (dbtype.equals("oracle")) {
            sql3 = "SELECT  Count(*) as count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        }
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                System.out.println("sql: " + sql3);
                rs1 = stmt1.executeQuery(sql3);
                while (rs1.next()) {
                    count = Integer.parseInt(rs1.getString("count"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public String noOfDays(String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch) {
        String sql3 = null;
        String dbtype = DBConnection.getDBType();
        String start = "";
        if (dbtype.equals("mssql")) {
            sql3 = "SELECT  TOP 1 GL38START FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        }
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                System.out.println(sql3);
                ResultSet rsObj = stmt.executeQuery(sql3);
                while (rsObj.next()) {
                    start = rsObj.getString("GL38START");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return start;
    }

    public BigDecimal MaxFine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        try {
            try {
                this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetItemBranch + "'";
                System.out.println(sql5);
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rs1 = stmt1.executeQuery(sql5);
                if (rs1.next()) {
                    maxfine = rs1.getBigDecimal("GL27MAXFINE");
                }
                if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                    String sql6 = "Select * from GLCATE WHERE GL07CATE = '" + this.msPatronCategory + "'";
                    System.out.println(sql6);
                    rs1 = stmt1.executeQuery(sql6);
                    while (rs1.next()) {
                        maxfine = rs1.getBigDecimal("GL07MAXFINE");
                    }
                    if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                        maxfine = BigDecimal.ZERO;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maxfine;
    }

    public int calculateDays(String msDocDate, String msBranch) throws SQLException {
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String datecharged = DateTime.formatDatelocals(msDocDate);
        String datereturned = DateTime.getTodayDate();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateStart = LocalDate.parse(datecharged, df);
        LocalDate dateEnd = LocalDate.now();
        System.out.println("Date" + datecharged + datereturned + "www");
        int days = (int)ChronoUnit.DAYS.between(dateStart, dateEnd);
        int holidaycount = 0;
        if (days <= 0) {
            this.latedays = 0;
        } else {
            try {
                try {
                    conn1 = DBConnection.getConnection();
                    stmt1 = conn1.createStatement();
                    String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + datereturned + "'";
                    rs1 = stmt1.executeQuery(sql2);
                    System.out.println(sql2);
                    while (rs1.next()) {
                        holidaycount = Integer.parseInt(rs1.getString("Holiday"));
                    }
                    System.out.println("Count total days: " + days);
                    System.out.println("Holiday Count: " + holidaycount);
                    rsObj.close();
                    this.latedays = days - holidaycount;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    try {
                        conn1.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    conn1.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.latedays;
    }

    public int getLateBy() {
        return this.latedays;
    }

    public BigDecimal fineRate(String msPatronID) {
        BigDecimal finerate = BigDecimal.ZERO;
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        try {
            try {
                String sql3 = "SELECT GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                System.out.println(sql3);
                rs1 = stmt1.executeQuery(sql3);
                int count = 0;
                while (rs1.next()) {
                    ++count;
                    finerate = rs1.getBigDecimal("GL38RATE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finerate;
    }

    public BigDecimal calculatefines2(String msPatronID) throws SQLException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal fineRates = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        this.latedays = this.calculateDays(this.msRetDueDate, this.msRetItemBranch);
        try {
            if (this.latedays <= 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                maxfine = this.MaxFine(msPatronID);
                fineRates = this.fineRate(msPatronID, this.latedays);
                fines = maxfine.compareTo(BigDecimal.ZERO) == 0 ? fineRates : (fineRates.compareTo(maxfine) > 0 ? maxfine : fineRates);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        fines = fines.setScale(2, 1);
        df2.setMaximumFractionDigits(2);
        df2.setMinimumFractionDigits(0);
        df2.setGroupingUsed(false);
        return fines;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public static List<Renewal> getLoanItems(String msPatronID) throws SQLException {
        ArrayList<Renewal> PatronDetails = new ArrayList<Renewal>();
        List<ISBD> isbd = ISBD.getPunctuation("245");
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "select CI02DOCNO, CI02CDATE, CI02CTIME, CI02DDATE, CI02DTIME, CI02FLAG, CT05SRAW from CICIRC, CTPONT, CTTITL where CI02PATR='" + msPatronID + "' AND CI02FLAG='C' and " + "CI02MATNO = CT06MATNO AND CT06POINTER = CT05POINTER AND CT06TAG = '245'";
                System.out.println("TitleSQL" + sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Renewal circ = new Renewal(rsObj.getString("CI02DOCNO"), rsObj.getString("CI02CDATE"), GeneralUtility.StrToTime2(rsObj.getString("CI02CTIME")), rsObj.getString("CI02DDATE"), GeneralUtility.StrToTime2(rsObj.getString("CI02DTIME")), Handler.circStat(rsObj.getString("CI02FLAG")), Handler.getSubfield(Handler.removeSubf(rsObj.getString("CT05SRAW")), isbd));
                    System.out.println("Title" + rsObj.getString("CI02DTIME"));
                    PatronDetails.add(circ);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return PatronDetails;
    }
}
