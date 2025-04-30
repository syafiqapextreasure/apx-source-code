/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.serial_master;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serial_Components {
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    private String code = null;
    private String desc = null;
    private double pubRate = 0.0;
    private double bankRate = 0.0;

    public Serial_Components(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Serial_Components(String forexCode, String forexDesc, double pubRate, double bankRate) {
        this.code = forexCode;
        this.desc = forexDesc;
        this.pubRate = pubRate;
        this.bankRate = bankRate;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public double getPubRate() {
        return this.pubRate;
    }

    public double getBankRate() {
        return this.bankRate;
    }

    public static synchronized List<Serial_Components> getListSMD() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL47SMD, GL47DESC FROM GLSMD";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListSMD = new Serial_Components(rs.getString("GL47SMD"), rs.getString("GL47DESC"));
                    list.add(newGetListSMD);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListLanguage() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL26CODE, GL26DESC FROM GLINFO WHERE GL26START = 36";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListLanguage = new Serial_Components(rs.getString("GL26CODE"), rs.getString("GL26DESC"));
                    list.add(newGetListLanguage);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Serial_Components> geCurrencytList() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL24FOREX, GL24DESC, GL24PRATE, GL24BRATE FROM GLFORX";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newCurrency = new Serial_Components(rs.getString("GL24FOREX"), rs.getString("GL24DESC"), Double.parseDouble(rs.getString("GL24PRATE")), Double.parseDouble(rs.getString("GL24BRATE")));
                    list.add(newCurrency);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListDepartment() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL11DEPT, GL11NAME FROM GLDEPT";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListDepartment = new Serial_Components(rs.getString("GL11DEPT"), rs.getString("GL11NAME"));
                    list.add(newGetListDepartment);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListFrequency() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL49FREQ, GL49DESC FROM GLFREQ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListFrequency = new Serial_Components(rs.getString("GL49FREQ"), rs.getString("GL49DESC"));
                    list.add(newGetListFrequency);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListBibliographicSource() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL48SOURCE, GL48DESC FROM GLBIBS";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListBibliographicSource = new Serial_Components(rs.getString("GL48SOURCE"), rs.getString("GL48DESC"));
                    list.add(newGetListBibliographicSource);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListSerialMode() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT SE05MODE, SE05DESC FROM SEMODE";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListSerialMode = new Serial_Components(rs.getString("SE05MODE"), rs.getString("SE05DESC"));
                    list.add(newGetListSerialMode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListItemCategory() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL10ICAT, GL10DESC from GLICAT";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListSerialMode = new Serial_Components(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
                    list.add(newGetListSerialMode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getListLocation() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL05LOCA, GL05DESC FROM GLLOCA";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListSerialMode = new Serial_Components(rs.getString("GL05LOCA"), rs.getString("GL05DESC"));
                    list.add(newGetListSerialMode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized List<Serial_Components> getEISList() {
        ArrayList<Serial_Components> list = new ArrayList<Serial_Components>();
        String query = "SELECT GL41TRUC, GL41CODE FROM GLEISP";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Components newGetListSerialMode = new Serial_Components(rs.getString("GL41CODE"), rs.getString("GL41TRUC"));
                    list.add(newGetListSerialMode);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
