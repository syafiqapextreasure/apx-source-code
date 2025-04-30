/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Release_Circulation;

import com.ilmu.cataloging.Release_Circulation.TableName;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CatalogingDOC {
    private String ISBN;
    private String DueDate;
    private String PickVar;
    private String CollectionDays;
    private String OfficerName;
    private String OfficerDesign;
    private String AccMatNo;
    private String PatrOffTel;
    private String Location;
    private String Branch;
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
            this.put("Branch", "GL71DESC");
        }
    };

    public CatalogingDOC() {
    }

    public CatalogingDOC(String ISBN, String Location, String Branch) {
        this.ISBN = ISBN;
        this.Location = Location;
        this.Branch = Branch;
        System.out.println(Branch);
    }

    public CatalogingDOC(String PatrOffTel, String CollectionDays, String OfficerName, String OfficerDesign) {
        this.PatrOffTel = PatrOffTel;
        this.CollectionDays = CollectionDays;
        this.OfficerName = OfficerName;
        this.OfficerDesign = OfficerDesign;
        System.out.println(PatrOffTel);
    }

    public static String getColumnName(String name) {
        return columnName.get(name);
    }

    public String getAccNo() {
        return this.AccMatNo;
    }

    public String getISBN() {
        return this.ISBN;
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

    private static CatalogingDOC search(String sql) {
        CatalogingDOC catDOC = null;
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
                    String raw = rs.getString(CatalogingDOC.getColumnName("ISBN"));
                    raw = raw.replace("|a", " ");
                    catDOC = new CatalogingDOC(raw, rs.getString(CatalogingDOC.getColumnName("Location")), rs.getString(CatalogingDOC.getColumnName("Branch")));
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

    private static CatalogingDOC searchPatrDetails(String sql) {
        CatalogingDOC catDOC = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    catDOC = new CatalogingDOC(CatalogingDOC.getColumnName("PatrOffTel"), CatalogingDOC.getColumnName("CollectionDays"), CatalogingDOC.getColumnName("OfficerName"), CatalogingDOC.getColumnName("OfficerDesign"));
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

    public static CatalogingDOC searchAccMatNo(String accMatNo) {
        String sql = "SELECT * FROM GLLOCA, GLBRNC, " + TableName.get("index") + "," + TableName.get("pointer") + "," + TableName.get("document") + " WHERE GL05BRNC=GL71BRNC and GL05LOCA=CT03LOCA and CT05POINTER=CT06POINTER and CT03MATNO=CT06MATNO and GL05LOCA=CT03LOCA and CT06TAG='020' and " + CatalogingDOC.getColumnName("AccMatNo") + " = '" + accMatNo + "'";
        System.out.println(sql);
        return CatalogingDOC.search(sql);
    }

    public static CatalogingDOC patrDetails() {
        String sql = "SELECT TOP 1 * FROM GLPATR, GLDESG, " + TableName.get("patrDetails") + " WHERE GL28CIRHEAD=GL14PATR and GL14STAT=GL69DESG";
        return CatalogingDOC.searchPatrDetails(sql);
    }
}
