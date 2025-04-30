/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.Cancel_Order;

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

public class Cancel_Order {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String AC03ORDER = null;
    private String AC03VEND = null;
    private String AC03ORDATE = null;
    private String AC03EXDATE = null;
    private String AC03CTRLNO = null;
    private String AC03COPIES = null;
    private String AC03RECEIVED = null;
    private String AC03STAT = null;

    public Cancel_Order(String AC03ORDER, String AC03VEND, String AC03ORDATE, String AC03EXDATE, String AC03CTRLNO, String AC03COPIES, String AC03RECEIVED, String AC03STAT) {
        this.AC03ORDER = AC03ORDER;
        this.AC03VEND = AC03VEND;
        this.AC03ORDATE = AC03ORDATE;
        this.AC03EXDATE = AC03EXDATE;
        this.AC03CTRLNO = AC03CTRLNO;
        this.AC03COPIES = AC03COPIES;
        this.AC03RECEIVED = AC03RECEIVED;
        this.AC03STAT = AC03STAT;
    }

    public String getAC03ORDER() {
        return this.AC03ORDER;
    }

    public String getAC03VEND() {
        return this.AC03VEND;
    }

    public String getAC03ORDATE() {
        return this.AC03ORDATE;
    }

    public String getAC03EXDATE() {
        return this.AC03EXDATE;
    }

    public String getAC03CTRLNO() {
        return this.AC03CTRLNO;
    }

    public String getAC03COPIES() {
        return this.AC03COPIES;
    }

    public String getAC03RECEIVED() {
        return this.AC03RECEIVED;
    }

    public String getAC03STAT() {
        return this.AC03STAT;
    }

    public static List<Cancel_Order> getCancelOrderList(String checkboxArray, String vendor, String cancelstartdate, String cancelenddate, String orderstartdate, String orderenddate, String feedback, String orderno, String reference) throws SQLException {
        ArrayList<Cancel_Order> tplList = new ArrayList<Cancel_Order>();
        String query = " Select AC03ORDER, AC03VEND, AC03ORDATE, AC03CLDATE, CT05SRAW, AC03COPIES, AC03RECEIVED, AC03STATUS,GL43ACQDESC  from ACORDD, CTTITL, CTPONT, GLACST WHERE  AC03CTRLNO=CT06MATNO AND CT06POINTER=CT05POINTER AND GL43ACQSTAT=AC03STATUS AND CT06TAG='245' AND (AC03STATUS='15' OR  AC03STATUS='14') ";
        System.out.println("New" + feedback);
        query = feedback != "" ? String.valueOf(query) + "AND " : String.valueOf(query) + "AND ";
        String[] chkboxValue = checkboxArray.split(",");
        int i = 0;
        while (i < chkboxValue.length) {
            System.out.println("action" + chkboxValue[i]);
            if (i != 0) {
                query = String.valueOf(query) + " AND ";
            }
            if (chkboxValue[i].equals("vendor")) {
                query = String.valueOf(query) + "AC03VEND='" + Handler.convUpperCase(vendor) + "'";
            }
            if (chkboxValue[i].equals("ordercanceldate")) {
                query = String.valueOf(query) + "AC03EXDATE BETWEEN '" + DateTime.displayToDBFormat(cancelstartdate) + "' AND '" + DateTime.displayToDBFormat(cancelenddate) + "'";
            }
            if (chkboxValue[i].equals("orderdate")) {
                query = String.valueOf(query) + "AC03ORDATE BETWEEN '" + DateTime.displayToDBFormat(orderstartdate) + "' AND '" + DateTime.displayToDBFormat(orderenddate) + "'";
            }
            System.out.println(String.valueOf(feedback) + "ss");
            if (chkboxValue[i].equals("feedbacktype") && feedback != null) {
                query = String.valueOf(query) + "AC03FEED='" + feedback + "'";
            }
            if (chkboxValue[i].equals("orderno")) {
                query = String.valueOf(query) + "AC03ORDER='" + orderno + "'";
            }
            if (chkboxValue[i].equals("referenceno")) {
                query = String.valueOf(query) + "AC03REFNO='" + reference + "'";
            }
            ++i;
        }
        query = String.valueOf(query) + " ORDER BY AC03ORDER";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Cancel_Order cancelorderlist = new Cancel_Order(rs.getString("AC03ORDER"), rs.getString("AC03VEND"), DateTime.DBToDisFormat(rs.getString("AC03ORDATE")), DateTime.DBToDisFormat(rs.getString("AC03CLDATE")), Handler.getSubfield(rs.getString("CT05SRAW"), isbd), rs.getString("AC03COPIES"), rs.getString("AC03RECEIVED"), rs.getString("GL43ACQDESC"));
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

    public static List<Cancel_Order> getCancelOrderListReload(String orderno) throws SQLException {
        ArrayList<Cancel_Order> tplList = new ArrayList<Cancel_Order>();
        String query = " Select AC03ORDER, AC03VEND, AC03ORDATE, AC03CLDATE, CT05SRAW, AC03COPIES, AC03RECEIVED, AC03STATUS,GL43ACQDESC  from ACORDD, CTTITL, CTPONT, GLACST WHERE  AC03CTRLNO=CT06MATNO AND CT06POINTER=CT05POINTER AND GL43ACQSTAT=AC03STATUS AND CT06TAG='245' AND (AC03STATUS='15' OR AC03STATUS='14') AND AC03ORDER IN (" + orderno + ") ORDER BY AC03ORDER";
        System.out.println(query);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Cancel_Order cancelorderlist = new Cancel_Order(rs.getString("AC03ORDER"), rs.getString("AC03VEND"), DateTime.DBToDisFormat(rs.getString("AC03ORDATE")), DateTime.DBToDisFormat(rs.getString("AC03CLDATE")), Handler.getSubfield(rs.getString("CT05SRAW"), isbd), rs.getString("AC03COPIES"), rs.getString("AC03RECEIVED"), rs.getString("GL43ACQDESC"));
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
