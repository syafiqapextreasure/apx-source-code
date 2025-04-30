/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.ilmu.circulation.Item_Status_Maintenance;

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

public class modifyItemStatusMethod {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msRetAccessionNo;
    private String msRetStatus;
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetDocNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msRetDateCharged;
    private String msPatronCategory;
    private String msRetItemBranch;
    private String msResvPatrName;
    private String msResvPatrId;
    private String msResvPickup;
    private String circDDate;
    private String circDTime;
    private int circCounter;
    String msRawDateReturned;
    String msRawTimeReturned;
    private int msLateBy;
    private BigDecimal msFinesByItem;
    boolean validAccession = false;
    boolean UpdateAccessionDetail = false;
    boolean UpdatePatronDetails = false;
    boolean UpdateCirculationDetails = false;
    boolean UpdateReservation = false;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    String printMessage;
    int latedays = 0;
    String errmessage = "";

    public modifyItemStatusMethod() {
        try {
            System.out.println("modifyItemStatusMethod");
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And CT03PATR='" + msPatronID + "' AND (CT03STAT='C' OR CT03STAT='E')";
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
                this.msRetDateCharged = String.valueOf(rsObj.getString("CT03BDATE"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(exist);
        return exist;
    }

    public boolean UpdateFinesTransaction(String msPatronID, String msRawDateReturned, BigDecimal msFines, String msOfficerID, String msAccessionNo) throws SQLException {
        boolean success = false;
        String msCode = "100";
        int iCounter = 0;
        String sql2 = "SELECT * FROM GLNUMB where GL98FUNC='TRXNO'";
        try {
            rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                iCounter = rsObj.getInt("GL98VALUE");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
        String sSQlStmt3 = "Insert Into RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) values(" + (iCounter + 1) + "," + "'" + msCode + "'" + "," + "'" + msRawDateReturned + "'" + "," + msFines + "," + "0," + "0," + "'" + msAccessionNo.trim() + "'" + "," + "'" + msPatronID + "'" + "," + "'" + RE01REFER + "'" + "," + "'" + msOfficerID + "'" + ",null" + "," + "'" + msAccessionNo.trim() + "'" + ",null," + "null," + "null," + "'" + this.circCounter + "'" + ",null" + ")";
        System.out.println(sSQlStmt3);
        int count = stmt.executeUpdate(sSQlStmt3);
        if (count > 0) {
            String sql3 = "Update GLNUMB set GL98VALUE='" + (iCounter + 1) + "'" + "where GL98FUNC='TRXNO'";
            System.out.println(sql3);
            int count2 = stmt.executeUpdate(sql3);
            if (count2 > 0) {
                success = true;
            }
        }
        return success;
    }

    public String getMsPatronCategory(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCategory;
    }

    public BigDecimal calculatefines(String msPatronID) {
        BigDecimal fines = BigDecimal.ZERO;
        int holidaycount = 0;
        int trxno = 0;
        BigDecimal maxfine = BigDecimal.ZERO;
        BigDecimal finerate = BigDecimal.ZERO;
        System.out.println(this.circDDate);
        String datecharged = DateTime.formatDatelocal(this.circDDate);
        String datereturned = DateTime.getTodayDate();
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        LocalDate dateStart = new LocalDate((Object)datecharged);
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Date Start" + dateStart);
        System.out.println("Date end" + dateEnd);
        System.out.println("Number of days" + days);
        String CI02DATE = "";
        if (days < 0) {
            this.errmessage = "021";
        }
        if (days > 0) {
            try {
                String sql1 = "select * from CICIRC where CI02PATR='" + msPatronID + "'";
                ResultSet rsObj = stmt.executeQuery(sql1);
                while (rsObj.next()) {
                    CI02DATE = rsObj.getString("CI02DDATE");
                }
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + this.circDDate + "'AND '" + datereturned + "'";
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                System.out.println("C" + holidaycount);
                String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetLocation + "'";
                System.out.println(sql3);
                rsObj = stmt.executeQuery(sql3);
                while (rsObj.next()) {
                    finerate = rsObj.getBigDecimal("GL38RATE");
                }
                String sql4 = "SELECT  GL98VALUE from GLNUMB where GL98FUNC= 'TRXNO'";
                System.out.println(sql4);
                rsObj = stmt.executeQuery(sql4);
                while (rsObj.next()) {
                    trxno = rsObj.getInt("GL98VALUE");
                }
                String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetLocation + "'";
                System.out.println(sql5);
                rsObj = stmt.executeQuery(sql5);
                while (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL27MAXFINE");
                }
                if (maxfine == null) {
                    maxfine = BigDecimal.ZERO;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            this.latedays = days - holidaycount;
            System.out.println("TRXNO" + trxno++);
            System.out.println("finerate" + finerate);
            String RE01REFER = String.valueOf(DateTime.splitDate(this.circDDate)) + DateTime.formatTime(this.circDTime) + ":" + this.latedays;
            System.out.println("Late Days" + this.latedays);
            System.out.println("REFERENCE" + RE01REFER);
            fines = finerate.multiply(new BigDecimal(this.latedays));
            double totalfines = 0.0;
            System.out.println("MaxFines" + maxfine);
            System.out.println("Fines" + fines);
            if (maxfine.compareTo(BigDecimal.ZERO) == 0) {
                this.errmessage = "021";
            } else if (maxfine.compareTo(fines) < 0) {
                fines = maxfine;
            }
            System.out.println("Fines" + fines);
        }
        return fines;
    }

    public BigDecimal calculatefines2(String msPatronID) throws SQLException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        this.latedays = this.calculateDays(this.msRetDueDate, this.msRetLocation);
        try {
            if (this.latedays < 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                maxfine = this.MaxFine(msPatronID);
                System.out.println("Fine Rate" + fineRate);
                System.out.println("MaxFine" + maxfine);
                fines = fineRate.multiply(new BigDecimal(this.latedays));
                if (maxfine.compareTo(BigDecimal.ZERO) != 0 && maxfine.compareTo(fines) < 0) {
                    fines = maxfine;
                }
                System.out.println("Fines" + fines);
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

    public BigDecimal MaxFine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        try {
            this.msPatronCategory = this.getMsPatronCategory(msPatronID);
            String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetLocation + "'";
            System.out.println(sql5);
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql5);
            System.out.println("MaxFine");
            if (rsObj.next()) {
                maxfine = rsObj.getBigDecimal("GL27MAXFINE");
            }
            if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                String sql6 = "Select * from GLCATE WHERE GL07CATE = '" + this.msPatronCategory + "'";
                System.out.println(sql6);
                rsObj = stmt.executeQuery(sql6);
                while (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL07MAXFINE");
                }
                if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                    maxfine = BigDecimal.ZERO;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return maxfine;
    }

    public int getMsLateBy() {
        return this.msLateBy;
    }

    public BigDecimal getMsFinesByItem() {
        return this.msFinesByItem;
    }

    public void setMsLateBy(int msLateBy) {
        this.msLateBy = msLateBy;
    }

    public void setMsFinesByItem(BigDecimal msFinesByItem) {
        this.msFinesByItem = msFinesByItem;
    }

    public int getLateBy() {
        return this.latedays;
    }

    public int calculateDays(String msDocDate, String msBranch) throws SQLException {
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String datereturned = DateTime.getTodayDate();
        LocalDate dateStart = new LocalDate((Object)datecharged);
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Days count" + days);
        int holidaycount = 0;
        if (days < 0) {
            this.latedays = 0;
        } else {
            stmt = conn.createStatement();
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + datereturned + "'";
            ResultSet rsObj = stmt.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
            }
            System.out.println("Holiday Count" + holidaycount);
            rsObj.close();
            this.latedays = days - holidaycount;
        }
        return this.latedays;
    }

    public BigDecimal fineRate(String msPatronID) {
        BigDecimal finerate = BigDecimal.ZERO;
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        try {
            String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetLocation + "'";
            stmt = conn.createStatement();
            System.out.println(sql3);
            ResultSet rsObj = stmt.executeQuery(sql3);
            int count = 0;
            while (rsObj.next()) {
                finerate = rsObj.getBigDecimal("GL38RATE");
                System.out.println("" + finerate + ++count);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return finerate;
    }

    public BigDecimal Prcfees(String msPatronID) {
        BigDecimal prcfees = BigDecimal.ZERO;
        try {
            this.msPatronCategory = this.getMsPatronCategory(msPatronID);
            String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetLocation + "'";
            System.out.println(sql5);
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql5);
            System.out.println("Prcfees");
            if (rsObj.next()) {
                prcfees = rsObj.getBigDecimal("GL27PRCFEES");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return prcfees;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }
}
