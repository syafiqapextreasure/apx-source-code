/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Renewal {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    String msPatronRenewalEligibility = "";
    private String msRetAccessionNo = "";
    private String msRetStatus = "";
    private String msRetItemCat = "";
    private String msRetMatNo = "";
    private String msRetDocNo = "";
    private String msRetSMD = "";
    private String msRetLocation = "";
    private String msRetPatron = "";
    private String msRetCondition = "";
    private String msRetDueDate = "";
    private String msNoCircByPatron = "";
    private String GL14NAME = "";
    private String CI03PATR = "";
    private String msPatronStatus = "";
    private String msPatronCategory = "";
    private String msPatronBranch = "";
    private int msLoanPeriod;
    String msRawDateDue;
    String msRawTimeDue;
    String msRawDateCharged;
    String msRawTimeCharged;
    String errmessage = "";

    public Renewal() {
        try {
            System.out.println("from Renewal");
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And CT03PATR='" + msPatronID + "' AND CT03STAT='C'";
        System.out.println(sql);
        boolean exist = false;
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetAccessionNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                this.msRetDocNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                this.msRetDueDate = String.valueOf(rsObj.getString("CT03DDATE"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean CanPatronRenew(String msPatronStatus) {
        boolean validate = false;
        try {
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

    public int CountPatronRenewCount(String msPatronCategory, String msRetItemCat, String msRetSMD, String msPatronBranch) {
        int patronrenewcount = 0;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL27RENEW, GL27PLOAN From GLELIG WHERE GL27CATE = '" + msPatronCategory.trim() + "' And GL27ICAT='" + msRetItemCat + "' AND GL27SMD='" + msRetSMD + "'AND GL27BRNC='" + msPatronBranch + "'";
            System.out.println(sql);
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                patronrenewcount = rsObj.getInt("GL27RENEW");
                this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patronrenewcount;
    }

    public int patronCirculationRenewCount(String msPatronID, String msAccessionNo) {
        int circpatronrenewcount = 0;
        try {
            stmt = conn.createStatement();
            String sql = "Select * From CICIRC WHERE CI02DOCNO = '" + msAccessionNo.trim() + "' And CI02PATR='" + msPatronID + "'";
            System.out.println(sql);
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                circpatronrenewcount = rsObj.getInt("CI02RENEW");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
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
        String inclusive = "SELECT * FROM GLLIBR WHERE GL28CIRCMODE = 'E' and GL28BRNC='" + this.msPatronBranch + "'";
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
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION3").equals("Y")) continue;
                reservationstatus = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationstatus;
    }

    public boolean ExecuteRenewal(String msAccessionNo, String msPatronID) {
        boolean bSucessful;
        block17: {
            String msOfficerID = "000";
            Patron a = new Patron(msPatronID);
            this.msPatronStatus = a.getMsPatronStatus(msPatronID);
            this.msPatronCategory = a.getMsPatronCategory(msPatronID);
            this.msPatronBranch = a.getMsPatronBranch(msPatronID);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            System.out.println(String.valueOf(this.msPatronCategory) + this.msPatronBranch);
            boolean canPatronRenew = false;
            int patronRenewCount = 0;
            int circPatronRenewCount = 0;
            bSucessful = false;
            try {
                canPatronRenew = this.CanPatronRenew(this.msPatronStatus);
                if (canPatronRenew) {
                    Date date = new Date();
                    String currentdate = dateFormat.format(date).toString();
                    bSucessful = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
                    System.out.println(currentdate);
                    System.out.println("Before renew period" + GeneralUtility.StrToDate(this.msRetDueDate));
                    Date date1 = dateFormat.parse(currentdate);
                    String msRetDueDate2 = GeneralUtility.StrToDate(this.msRetDueDate);
                    Date date2 = dateFormat.parse(msRetDueDate2);
                    System.out.println(this.canRenew(msPatronID));
                    if (date1.compareTo(date2) > 0) {
                        System.out.println("Date1 is after Date2");
                        System.out.println("This patron is not allowed to renew an overdue item");
                        bSucessful = false;
                        this.errmessage = "031";
                        break block17;
                    }
                    bSucessful = true;
                    if (bSucessful) {
                        if (this.canRenew(msPatronID)) {
                            System.out.println("IN");
                            patronRenewCount = this.CountPatronRenewCount(this.msPatronCategory, this.msRetItemCat, this.msRetSMD, this.msPatronBranch);
                            System.out.println("Days" + patronRenewCount);
                            if (patronRenewCount <= 0) {
                                System.out.println("This patron is not allowed to Renew for this item category");
                                bSucessful = false;
                                this.errmessage = "027";
                                break block17;
                            }
                            circPatronRenewCount = this.patronCirculationRenewCount(msPatronID, msAccessionNo);
                            System.out.println(circPatronRenewCount + patronRenewCount);
                            if (circPatronRenewCount > patronRenewCount && patronRenewCount != 0) {
                                System.out.println("This patron has exceed renewal eligibility");
                                this.errmessage = "030";
                                bSucessful = false;
                            } else {
                                ++circPatronRenewCount;
                                bSucessful = true;
                            }
                        } else {
                            System.out.println("Invalid Accession");
                            this.errmessage = "021";
                        }
                    } else {
                        bSucessful = false;
                        this.errmessage = "089";
                    }
                } else {
                    System.out.println("This patron is not allowed to Renew");
                    this.errmessage = "028";
                }
                System.out.println("1" + bSucessful);
                this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
                this.renewalDate();
                if (bSucessful) {
                    bSucessful = this.UpdateCirculationDetails(this.msRawDateDue, this.msRawTimeDue, msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo, circPatronRenewCount);
                    if (!bSucessful) {
                        System.out.println("Circulation details not updated");
                    }
                    System.out.println("2" + bSucessful);
                    bSucessful = this.RenewingAccessionStatus(this.msRawDateDue, this.msRawTimeDue, msAccessionNo);
                    if (!bSucessful) {
                        System.out.println("Accession Details not updated");
                    }
                    System.out.println("3" + bSucessful);
                } else {
                    System.out.println("Error");
                }
            }
            catch (SQLException date) {
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bSucessful;
    }

    public boolean UpdateCirculationDetails(String msRawDateDue, String msRawTimeDue, String msOfficerID, String msPatronID, String msAccessionNo, String msRetDocNo, int renewcount) {
        boolean success = false;
        try {
            String sSQLStmt1 = " Update CICIRC Set CI02DDATE = '" + msRawDateDue.trim() + "' ," + "CI02DTIME = '" + msRawTimeDue.trim() + "' ," + "CI02RENEW = '" + renewcount + "' ," + "CI02DIOFFI = '" + msOfficerID + "' " + "Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02DOCNO = '" + msAccessionNo.trim() + "'  " + "And CI02FLAG = 'C'";
            System.out.println(sSQLStmt1);
            int count = stmt.executeUpdate(sSQLStmt1);
            System.out.println("C" + count);
            if (count > 0) {
                success = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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

    public int checkItemEligibility(String msPatronCategory, String msPatronBranch) {
        int GL27ELIG = 0;
        this.msLoanPeriod = 0;
        System.out.println(msPatronBranch);
        try {
            String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msPatronBranch + "'";
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

    public void renewalDate() throws SQLException {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        System.out.println("Loan Period" + this.msLoanPeriod + this.msPatronCategory + this.msPatronBranch);
        System.out.println("Loan" + this.msLoanPeriod);
        cal.add(5, this.msLoanPeriod);
        LocalDate.now().plusDays(this.msLoanPeriod);
        System.out.println("Loan Period : " + this.msLoanPeriod);
        date = cal.getTime();
        String duedate = dateFormat.format(date).toString();
        System.out.println("Due Date before holiday deduction" + duedate);
        String duetime = timeFormat.format(date).toString();
        String inclusive = "SELECT * FROM GLLIBR WHERE GL28CIRCMODE = 'E' and GL28BRNC='" + this.msPatronBranch + "'";
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
        holidaycount = this.numberOfHolidays(duedate, cal);
        System.out.println("Isholidaysd : " + holidaycount);
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
        System.out.println("D" + this.msRawDateDue);
    }

    public boolean RenewingAccessionStatus(String msRawDateDue, String msRawTimeDue, String msAccessionNo) throws SQLException {
        boolean success = false;
        String sSQlStmt4 = "Update CTDOCM Set CT03DDATE = '" + msRawDateDue.trim() + "', " + "CT03DTIME = '" + msRawTimeDue + "' " + "Where CT03DOCNO = '" + msAccessionNo.trim() + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
        int count = stmt.executeUpdate(sSQlStmt4);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    public int rowcount(String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch) {
        String sql3 = null;
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rs1 = null;
        String dbtype = DBConnection.getDBtype();
        int count = 0;
        if (dbtype.equals("mssql") || dbtype.equals("mysql")) {
            sql3 = "SELECT  Count(*) as count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        } else if (dbtype.equals("oracle")) {
            sql3 = "SELECT  Count(*) as count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        }
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                System.out.println(String.valueOf(sql3) + "line1767");
                rs1 = stmt1.executeQuery(sql3);
                while (rs1.next()) {
                    String intcount = rs1.getString("count");
                    System.out.println(String.valueOf(intcount) + "ggggggggggg");
                    count = Integer.parseInt(rs1.getString("count"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                    stmt1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
                stmt1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public String getErrMessage() {
        return this.errmessage;
    }
}
