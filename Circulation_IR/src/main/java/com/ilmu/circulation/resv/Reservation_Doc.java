/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.ilmu.cataloging.Release_Circulation.TableName
 */
package com.ilmu.circulation.resv;

import com.ilmu.cataloging.Release_Circulation.TableName;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Reservation_Doc {
    private String ISBN;
    private String CTCALL;
    private String CTTITL;
    private String DueDate;
    private String PickVar;
    private String CollectionDays;
    private String OfficerName;
    private String OfficerDesign;
    private String AccMatNo;
    private String PatrOffTel;
    private String Location;
    private String Branch;
    private static Map<String, String> columnName1 = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("AccMatNo", "CT03DOCNO");
            this.put("PatrOffTel", "GL28FAX");
            this.put("ISBN", "CT05SRAW");
            this.put("DueDate", "GL39NAME");
            this.put("PickVar", "GL39ADD1");
            this.put("CollectionDays", "GL28MSGDELAY");
            this.put("OfficerName", "GL14NAME");
            this.put("OfficerDesign", "GL69DESC");
            this.put("Location", "GL05DESC");
        }
    };
    private static Map<String, String> columnName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("AccMatNo", "CT03DOCNO");
            this.put("PatrOffTel", "GL28FAX");
            this.put("ISBN", "CT05SRAW");
            this.put("DueDate", "GL39NAME");
            this.put("PickVar", "GL39ADD1");
            this.put("CollectionDays", "GL28MSGDELAY");
            this.put("OfficerName", "GL14NAME");
            this.put("OfficerDesign", "GL69DESC");
            this.put("Location", "GL05DESC");
        }
    };

    public Reservation_Doc() {
    }

    public Reservation_Doc(String Location, String Branch) {
        this.Location = Location;
        this.Branch = Branch;
    }

    public Reservation_Doc(String ISBN) {
        this.ISBN = ISBN;
    }

    public Reservation_Doc(String PatrOffTel, String CollectionDays, String OfficerName, String OfficerDesign) {
        this.PatrOffTel = PatrOffTel;
        this.CollectionDays = CollectionDays;
        this.OfficerName = OfficerName;
        this.OfficerDesign = OfficerDesign;
        System.out.println(PatrOffTel);
    }

    public static String getColumnName(String name) {
        return columnName1.get(name);
    }

    public String getAccNo() {
        return this.AccMatNo;
    }

    public String getISBN() {
        System.out.println("wwww");
        return Handler.ifIsNull(this.ISBN);
    }

    public String getCallNo() {
        return Handler.ifIsNull(this.CTCALL);
    }

    public String getTitle() {
        return Handler.ifIsNull(this.CTTITL);
    }

    public String getDueDate() {
        return this.DueDate;
    }

    public String getPickVar() {
        return this.PickVar;
    }

    public String getCollectionDays() {
        System.out.println(this.CollectionDays);
        return this.CollectionDays;
    }

    public String getLocation() {
        return this.Location;
    }

    public String getBranch() {
        return this.Branch;
    }

    public String getPatrOffTel() {
        return this.PatrOffTel;
    }

    public String getOfficerDesign() {
        return this.OfficerDesign;
    }

    public String getOfficerName() {
        return this.OfficerName;
    }

    private static Reservation_Doc ISBN(String sql) {
        Reservation_Doc ISBN;
        block12: {
            ISBN = null;
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        ISBN = new Reservation_Doc(rs.getString("CT05SRAW"));
                        break block12;
                    }
                    System.out.println("33332");
                    String raw = "";
                    ISBN = new Reservation_Doc(raw);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        rs.close();
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ISBN;
    }

    private static Reservation_Doc title(String sql) {
        Reservation_Doc ISBN;
        block13: {
            ISBN = null;
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        ISBN = new Reservation_Doc(rs.getString("CT05SRAW"));
                    } else {
                        System.out.println("33332");
                        String raw = "";
                        ISBN = new Reservation_Doc(raw);
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        rs.close();
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    break block13;
                }
            }
            catch (Throwable throwable) {
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ISBN);
        return ISBN;
    }

    private static Reservation_Doc callno(String sql) {
        Reservation_Doc ISBN;
        block12: {
            ISBN = null;
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (rs.next()) {
                        ISBN = new Reservation_Doc(rs.getString("CT05SRAW"));
                        break block12;
                    }
                    System.out.println("33332");
                    String raw = "";
                    ISBN = new Reservation_Doc(raw);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        rs.close();
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            finally {
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ISBN;
    }

    private static Reservation_Doc search(String sql) {
        Reservation_Doc catDOC = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                System.out.println(sql);
                if (rs.next()) {
                    catDOC = new Reservation_Doc(rs.getString(Reservation_Doc.getColumnName("Location")), rs.getString(Reservation_Doc.getColumnName("Branch")));
                    System.out.println("raws");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return catDOC;
    }

    private static Reservation_Doc searchPatrDetails(String sql) {
        Reservation_Doc catDOC = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    catDOC = new Reservation_Doc(Reservation_Doc.getColumnName("PatrOffTel"), Reservation_Doc.getColumnName("CollectionDays"), Reservation_Doc.getColumnName("OfficerName"), Reservation_Doc.getColumnName("OfficerDesign"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return catDOC;
    }

    public static Reservation_Doc getResvTitleCancel(String docno) {
        String sql = "Select CT05SRAW from CTTITL, CTDOCM, CTPONT where CT03DOCNO = '" + docno + "' " + "AND CT03MATNO = CT06MATNO " + "AND CT06POINTER = CT05POINTER " + "and CT06TAG = '245'";
        System.out.println(sql);
        return Reservation_Doc.title(sql);
    }

    public static Reservation_Doc getResvCallNo(String docno) {
        String sql = "Select CT05SRAW, CI03DOCNO,CI03RDATE, CI03RTIME from CIRESV, CTCALL, CTDOCM, CTPONT where CI03DOCNO = '" + docno + "' " + "AND CI03DOCNO = CT03DOCNO " + "AND CT03MATNO = CT06MATNO " + "AND CT06POINTER = CT05POINTER " + "and CT06TAG = '090'";
        System.out.println(sql);
        return Reservation_Doc.callno(sql);
    }

    public static Reservation_Doc getResvTitle(String docno) {
        String sql = "Select CT05SRAW, CI03DOCNO,CI03RDATE, CI03RTIME from CIRESV, CTTITL, CTDOCM, CTPONT where CI03DOCNO = '" + docno + "' " + "AND CI03DOCNO = CT03DOCNO " + "AND CT03MATNO = CT06MATNO " + "AND CT06POINTER = CT05POINTER " + "and CT06TAG = '245'";
        System.out.println(sql);
        return Reservation_Doc.title(sql);
    }

    public static Reservation_Doc getResvCallNoCancel(String docno) {
        String sql = "Select CT05SRAW from CTCALL, CTDOCM, CTPONT where CT03DOCNO = '" + docno + "' " + "AND CT03MATNO = CT06MATNO " + "AND CT06POINTER = CT05POINTER " + "and CT06TAG = '090'";
        System.out.println(sql);
        return Reservation_Doc.callno(sql);
    }

    public static Reservation_Doc getResvISBN(String docno) {
        String sql = "Select GL05DESC, CT05SRAW, CI03DOCNO,CI03RDATE, CI03RTIME from CIRESV, CTINDX, CTDOCM, CTPONT, GLLOCA where CI03DOCNO = '" + docno + "' " + "AND CI03DOCNO = CT03DOCNO " + "AND CT03MATNO = CT06MATNO " + "AND CT06POINTER = CT05POINTER " + "AND GL05LOCA=CI03BRNC " + "and CT06TAG = '020'";
        System.out.println(sql);
        return Reservation_Doc.ISBN(sql);
    }

    public static Reservation_Doc retrieveISBN(String accMatNo) {
        String sql = "SELECT CT05SRAW FROM " + TableName.get((String)"index") + "," + TableName.get((String)"pointer") + "," + TableName.get((String)"document") + " WHERE CT05POINTER=CT06POINTER and CT03MATNO=CT06MATNO and CT06TAG=020 and " + Reservation_Doc.getColumnName("AccMatNo") + " = '" + accMatNo + "'";
        return Reservation_Doc.ISBN(sql);
    }

    public static Reservation_Doc patrDetails() {
        String sql = "SELECT TOP 1 * FROM GLPATR, GLDESG, " + TableName.get((String)"patrDetails") + " WHERE GL28CIRHEAD=GL14PATR and GL14STAT=GL69DESG";
        return Reservation_Doc.searchPatrDetails(sql);
    }
}
