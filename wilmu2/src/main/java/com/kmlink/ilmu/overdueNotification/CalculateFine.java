/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.kmlink.ilmu.overdueNotification;

import com.kmlink.ilmu.shared.global.DateTime;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

public class CalculateFine {
    Statement stmt = null;
    ResultSet rsObj = null;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public BigDecimal calculatefines2(String msPatronID, String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetDueDate, String msRetItemBranch, Connection conn) throws SQLException, ParseException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal fineRates = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        int latedays = this.calculateDays(msRetDueDate, msRetItemBranch, conn);
        try {
            if (latedays <= 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID, msPatronCategory, msRetItemCat, msRetSMD, msRetItemBranch, conn);
                maxfine = this.MaxFine(msPatronID, msRetItemCat, msRetSMD, msRetItemBranch, conn);
                fineRates = this.fineRate(msPatronID, latedays, msPatronCategory, msRetItemCat, msRetSMD, msRetItemBranch, conn);
                int startDay = 1;
                int stopDay = this.getStopDay(startDay, msPatronCategory, msRetItemCat, msRetSMD, msRetItemBranch, conn);
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

    public BigDecimal calculatefines3(String msPatronID, String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetDueDate, String msRetItemBranch, int calDate, Connection conn) throws SQLException, ParseException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRates = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        int latedays = this.calculateDays(msRetDueDate, msRetItemBranch, conn);
        try {
            if (latedays <= 0) {
                fines = BigDecimal.ZERO;
            } else {
                maxfine = this.MaxFine(msPatronID, msRetItemCat, msRetSMD, msRetItemBranch, conn);
                fineRates = this.fineRate(msPatronID, latedays, msPatronCategory, msRetItemCat, msRetSMD, msRetItemBranch, conn);
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

    public int calculateDays(String msDocDueDate, String msBranch, Connection conn) throws SQLException, ParseException {
        int latedays;
        String chargeDate = DateTime.formatDatelocal(msDocDueDate);
        String currDate = DateTime.getTodayDate();
        LocalDate localChargeDate = new LocalDate((Object)chargeDate);
        LocalDate localCurrDate = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)localChargeDate, (ReadablePartial)localCurrDate);
        int days = d.getDays();
        int holidaycount = 0;
        if (days <= 0) {
            latedays = 0;
        } else {
            block14: {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDueDate + "'AND '" + currDate + "' ";
                try {
                    try {
                        this.stmt = conn.createStatement();
                        this.rsObj = this.stmt.executeQuery(sql2);
                        while (this.rsObj.next()) {
                            holidaycount = Integer.parseInt(this.rsObj.getString("Holiday"));
                        }
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        if (this.rsObj != null) {
                            this.rsObj.close();
                        }
                        if (this.stmt != null) {
                            this.stmt.close();
                        }
                        break block14;
                    }
                }
                catch (Throwable throwable) {
                    if (this.rsObj != null) {
                        this.rsObj.close();
                    }
                    if (this.stmt != null) {
                        this.stmt.close();
                    }
                    throw throwable;
                }
                if (this.rsObj != null) {
                    this.rsObj.close();
                }
                if (this.stmt != null) {
                    this.stmt.close();
                }
            }
            latedays = days - holidaycount;
        }
        return latedays;
    }

    public BigDecimal fineRate(String msPatronID, String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch, Connection conn) {
        BigDecimal finerate = BigDecimal.ZERO;
        Statement stmt = null;
        try {
            try {
                String sql3 = "SELECT GL38RATE FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql3);
                int count = 0;
                while (rsObj.next()) {
                    ++count;
                    finerate = rsObj.getBigDecimal("GL38RATE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (this.rsObj != null) {
                        this.rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (this.rsObj != null) {
                    this.rsObj.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finerate;
    }

    public BigDecimal MaxFine(String msPatronID, String msRetItemCat, String msRetSMD, String msRetItemBranch, Connection conn) {
        BigDecimal maxfine = BigDecimal.ZERO;
        Statement stmt = null;
        try {
            try {
                String msPatronCategory = this.getMsPatronCategory(msPatronID, conn);
                String sql = "SELECT  * from GLELIG WHERE GL27CATE = '" + msPatronCategory + "'" + "AND GL27ICAT = '" + msRetItemCat + "'" + "AND GL27SMD = '" + msRetSMD + "'" + "AND GL27BRNC = '" + msRetItemBranch + "'";
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                if (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL27MAXFINE");
                }
                if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                    String sql6 = "Select * from GLCATE WHERE GL07CATE = '" + msPatronCategory + "'";
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
                try {
                    if (this.rsObj != null) {
                        this.rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (this.rsObj != null) {
                    this.rsObj.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maxfine;
    }

    public BigDecimal fineRate(String msPatronID, int stopDay, String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch, Connection conn) {
        String sql3 = null;
        sql3 = "SELECT  GL38START, GL38STOP, GL38RATE, gl38first FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        double rate = 0.0;
        BigDecimal finerate = BigDecimal.ZERO;
        Statement stmt = null;
        try {
            try {
                stmt = conn.createStatement();
                int row = this.rowcount(msPatronCategory, msRetItemCat, msRetSMD, msRetItemBranch, conn);
                ResultSet rsObj = stmt.executeQuery(sql3);
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
                    GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                    BigDecimal GL38FIRST = new BigDecimal(rsObj.getString("GL38FIRST"));
                    int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                    BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                    finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                    stopDay = 0;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (this.rsObj != null) {
                        this.rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (this.rsObj != null) {
                    this.rsObj.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finerate;
    }

    public int getStopDay(int startDay, String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch, Connection conn) {
        String sql = "SELECT GL38STOP FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "' and GL38START= '" + startDay + "'";
        int stopDay = 0;
        Statement stmt = null;
        ResultSet rsObj = null;
        try {
            try {
                stmt = conn.createStatement();
                System.out.println(sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    stopDay = rsObj.getString("GL38STOP") == null ? 0 : Integer.parseInt(rsObj.getString("GL38STOP"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (rsObj != null) {
                        rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
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
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stopDay;
    }

    public String getMsPatronCategory(String msPatronID, Connection conn) {
        if (msPatronID.contains("'")) {
            msPatronID = msPatronID.replace("'", "''");
        }
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        String msPatronCategory = null;
        Statement stmt = null;
        ResultSet rsObj = null;
        try {
            try {
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                System.out.println(sql);
                while (rsObj.next()) {
                    msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (rsObj != null) {
                        rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
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
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msPatronCategory;
    }

    public int rowcount(String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch, Connection conn) {
        String sql3 = null;
        int count = 0;
        sql3 = "SELECT  Count(*) as count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        Statement stmt = null;
        try {
            try {
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql3);
                while (rsObj.next()) {
                    count = Integer.parseInt(rsObj.getString("count"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (this.rsObj != null) {
                        this.rsObj.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (this.rsObj != null) {
                    this.rsObj.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
