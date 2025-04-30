/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package com.ilmu.enquiry.SerialsHolding;

import com.google.gson.Gson;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SerialsHolding {
    private String value1;

    public String getvalue1() {
        return Handler.ifIsNull(this.value1);
    }

    public SerialsHolding(String value1) {
        this.value1 = value1;
    }

    public static List<SerialsHolding> CheckControlNoInSEISSU(String code, String Search) {
        ArrayList<SerialsHolding> vendorname = new ArrayList<SerialsHolding>();
        String query = null;
        if (code.equals("issn")) {
            String ctrlno = SerialsHolding.GetControlNo(Search);
            query = "Select Distinct SE06MATNO From SEISSU Where SE06MATNO = '" + ctrlno + "'";
        } else if (code.equals("ctrlno")) {
            query = "Select Distinct SE06MATNO From SEISSU Where SE06MATNO = '" + Search + "'";
        }
        System.out.println("CheckControlNoInSEISSU = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SerialsHolding ControlNoInSEISSU = new SerialsHolding(Handler.ifIsNull(rs.getString("SE06MATNO")));
                    vendorname.add(ControlNoInSEISSU);
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
        return vendorname;
    }

    public static String GetControlNo(String value) {
        String sSQLStmt = "";
        sSQLStmt = "Select CT06MATNO FROM CTPONT, CTINDX WHERE CT06TAG = '022' AND CT06POINTER = CT05POINTER AND CT05SRAW LIKE '|a" + value + "' ";
        System.out.println("SQL MAILNO" + sSQLStmt);
        String getmatno = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getmatno = Handler.ifIsNull(rs.getString("CT06MATNO"));
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
        return getmatno;
    }

    public static List<SerialsHolding> getTitleByCtrlNo(String ctrlNo) {
        ArrayList<SerialsHolding> getTitle = new ArrayList<SerialsHolding>();
        String query = "SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = '" + ctrlNo + "' AND CT06TAG = '245'";
        System.out.println("getCtrlNoBY = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    SerialsHolding newgetTitle = new SerialsHolding(Handler.getSubfield(Handler.ifIsNull(rs.getString("CT05SRAW")), title));
                    if (Handler.getSubfield(Handler.ifIsNull(rs.getString("CT05SRAW")), title) == "") continue;
                    getTitle.add(newgetTitle);
                    SerialsHolding.serialsholdingArray(ctrlNo);
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
        return getTitle;
    }

    public static List<SerialsHolding> serialsholdingArray(String matno) {
        ArrayList<SerialsHolding> list2 = new ArrayList<SerialsHolding>();
        String query = "SELECT SE06ORDER, SE06ISSNO, SE06VOLLBL, SE06ISSLBL, SE06CPYNO FROM SEISSU  WHERE SE06MATNO ='" + matno + "' " + "GROUP BY SE06ORDER, SE06ISSNO, SE06VOLLBL, SE06ISSLBL, SE06CPYNO " + "ORDER BY SE06ORDER, SE06ISSNO";
        System.out.println("serialsholdingArray = " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String orderno = "";
                String volume = "";
                String issueno = "";
                String issuelbl = "";
                String copy = "";
                while (rs.next()) {
                    orderno = Handler.ifIsNull(rs.getString("SE06ORDER"));
                    volume = Handler.ifIsNull(rs.getString("SE06VOLLBL"));
                    issueno = Handler.ifIsNull(rs.getString("SE06ISSNO"));
                    issuelbl = Handler.ifIsNull(rs.getString("SE06ISSLBL"));
                    copy = Handler.ifIsNull(rs.getString("SE06CPYNO"));
                }
                Entry workNode = new Entry("Work");
                workNode.add(new Entry("Effort"));
                workNode.add(new Entry("Trust"));
                Entry salaryNode = new Entry("Salary");
                Entry cultureNode = new Entry("Culture");
                cultureNode.add(salaryNode);
                cultureNode.add(workNode);
                Gson g = new Gson();
                System.out.println(g.toJson((Object)cultureNode));
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
        return list2;
    }

    public static class Entry {
        private String name;
        private List<Entry> children;

        public Entry(String name) {
            this.name = name;
        }

        public void add(Entry node) {
            if (this.children == null) {
                this.children = new ArrayList<Entry>();
            }
            this.children.add(node);
        }
    }
}
