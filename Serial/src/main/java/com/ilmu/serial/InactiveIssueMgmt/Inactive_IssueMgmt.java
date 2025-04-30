/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.InactiveIssueMgmt;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Inactive_IssueMgmt {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String SE06ORDER = null;
    private String SE06CTRLNO = null;
    private String SE06ISSUNO = null;
    private String SE06ISSULBL = null;
    private String SE06COPIES = null;
    private String SE06VOL = null;
    private String SE06EXPDATE = null;
    private String SE06REMOVEDATE = null;

    public Inactive_IssueMgmt(String SE06ORDER, String SE06CTRLNO, String SE06ISSUNO, String SE06ISSULBL, String SE06COPIES, String SE06VOL, String SE06EXPDATE, String SE06REMOVEDATE) {
        this.SE06ORDER = SE06ORDER;
        this.SE06CTRLNO = SE06CTRLNO;
        this.SE06ISSUNO = SE06ISSUNO;
        this.SE06ISSULBL = SE06ISSULBL;
        this.SE06COPIES = SE06COPIES;
        this.SE06VOL = SE06VOL;
        this.SE06EXPDATE = SE06EXPDATE;
        this.SE06REMOVEDATE = SE06REMOVEDATE;
    }

    public String getSE06ORDER() {
        return this.SE06ORDER;
    }

    public String getSE06CTRLNO() {
        return this.SE06CTRLNO;
    }

    public String getSE06ISSUNO() {
        return this.SE06ISSUNO;
    }

    public String getSE06ISSULBL() {
        return this.SE06ISSULBL;
    }

    public String getSE06COPIES() {
        return this.SE06COPIES;
    }

    public String getSE06EXPDATE() {
        return this.SE06EXPDATE;
    }

    public String getSE06REMOVEDATE() {
        return this.SE06REMOVEDATE;
    }

    public String getSE06VOL() {
        return this.SE06VOL;
    }

    private static String GetSQLStmt(String sStartDate, String sStopDate, String criteria, String sOrderNoFrom, String sOrderNoTo, String sControlNoFrom, String sControlNoTo) {
        String sSQLStmt = "SELECT * FROM SEISSU WHERE SE06ISSTAT = 'I' ";
        sStartDate = DateTime.displayToDBFormat(sStartDate);
        sStopDate = DateTime.displayToDBFormat(sStopDate);
        if (sStartDate != "" && sStopDate != "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND SE06STATDATE BETWEEN '" + sStartDate + "' AND '" + sStopDate + "' ";
        } else if (sStartDate != "" && sStopDate == "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND SE06STATDATE >= '" + sStartDate + "' ";
        } else if (sStartDate == "" && sStopDate != "") {
            sSQLStmt = String.valueOf(sSQLStmt) + "AND SE06STATDATE <= '" + sStopDate + "' ";
        }
        if (criteria.equals("orderno")) {
            if (sOrderNoFrom != "" && sOrderNoTo != "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06ORDER BETWEEN '" + sOrderNoFrom + "' AND '" + sOrderNoTo + "'";
            } else if (sOrderNoFrom != "" && sOrderNoTo == "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06ORDER >= '" + sOrderNoFrom + "'";
            } else if (sOrderNoFrom == "" && sOrderNoTo != "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06ORDER <= '" + sOrderNoTo + "'";
            }
        }
        if (criteria.equals("controlno")) {
            if (sControlNoFrom != "" && sControlNoTo != "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06MATNO BETWEEN '" + sControlNoFrom + "' AND '" + sControlNoTo + "'";
            } else if (sControlNoFrom != "" && sControlNoTo == "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06MATNO >= '" + sControlNoFrom + "'";
            } else if (sControlNoFrom == "" && sOrderNoTo != "") {
                sSQLStmt = String.valueOf(sSQLStmt) + " AND SE06MATNO <= '" + sControlNoTo + "'";
            }
        }
        sSQLStmt = String.valueOf(sSQLStmt) + " ORDER BY SE06ORDER, SE06ISSNO ";
        return sSQLStmt;
    }

    public static List<Inactive_IssueMgmt> LoadRecordset(String sStartDate, String sStopDate, String criteria, String sOrderNoFrom, String sOrderNoTo, String sControlNoFrom, String sControlNoTo) throws SQLException {
        ArrayList<Inactive_IssueMgmt> tplList = new ArrayList<Inactive_IssueMgmt>();
        String query = Inactive_IssueMgmt.GetSQLStmt(sStartDate, sStopDate, criteria, sOrderNoFrom, sOrderNoTo, sControlNoFrom, sControlNoTo);
        System.out.println("SQL" + query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Inactive_IssueMgmt inactive = new Inactive_IssueMgmt(rs.getString("SE06ORDER"), rs.getString("SE06MATNO"), rs.getString("SE06ISSNO"), rs.getString("SE06ISSLBL"), Handler.getSubfield(rs.getString("SE06CPYNO"), isbd), rs.getString("SE06VOLLBL"), DateTime.DBToDisplayFormat(rs.getString("SE06EXPDATE")), DateTime.DBToDisplayFormat(rs.getString("SE06STATDATE")));
                    tplList.add(inactive);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return tplList;
    }

    public static String purgeRecord(String vsOrder, String vsCopy, String vsIssuNo) {
        String query = "DELETE FROM SEISSU WHERE SE06ORDER = '" + vsOrder + "' " + "AND SE06CPYNO = " + vsCopy + " " + "AND SE06ISSNO = " + vsIssuNo + " ";
        System.out.println("Deletesss" + query);
        return query;
    }

    public static List<Inactive_IssueMgmt> getCancelOrderListReload(String orderno) throws SQLException {
        ArrayList<Inactive_IssueMgmt> tplList = new ArrayList<Inactive_IssueMgmt>();
        String query = " Select AC03ORDER, AC03VEND, AC03ORDATE, AC03CLDATE, CT05SRAW, AC03COPIES, AC03RECEIVED, AC03STATUS,GL43ACQDESC  from ACORDD, CTTITL, CTPONT, GLACST WHERE  AC03CTRLNO=CT06MATNO AND CT06POINTER=CT05POINTER AND GL43ACQSTAT=AC03STATUS AND CT06TAG='245' AND (AC03STATUS='15' OR AC03STATUS='14') AND AC03ORDER IN (" + orderno + ") ORDER BY AC03ORDER";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Inactive_IssueMgmt cancelorderlist = new Inactive_IssueMgmt(rs.getString("AC03ORDER"), rs.getString("AC03VEND"), DateTime.DBToDisFormat(rs.getString("AC03ORDATE")), DateTime.DBToDisFormat(rs.getString("AC03CLDATE")), Handler.getSubfield(rs.getString("CT05SRAW"), isbd), rs.getString("AC03COPIES"), rs.getString("AC03RECEIVED"), rs.getString("GL43ACQDESC"));
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return tplList;
    }

    public static String getCtrlNo(String orderno) throws SQLException {
        String query = " Select AC03CTRLNO FROM ACORDD WHERE AC03ORDER='" + orderno + "'";
        System.out.println(query);
        String ctrlno = "";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ctrlno = rs.getString("AC03CTRLNO");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return ctrlno;
    }

    public static String deleteACORDD(String order) {
        String query = "DELETE FROM ACORDD WHERE AC03ORDER = '" + order + "'";
        return query;
    }

    public static String resubmitACORDD(String orderno, String vendor) {
        String query = "UPDATE ACORDD SET AC03INVSTAT='10', AC03STATUS='10', AC03VEND='" + Handler.convUpperCase(vendor) + "', AC03CRDATE='" + DateTime.getTodaySystemDate() + "'" + " where AC03ORDER='" + orderno + "'";
        System.out.println(query);
        return query;
    }
}
