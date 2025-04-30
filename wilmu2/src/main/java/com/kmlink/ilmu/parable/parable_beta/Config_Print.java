/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Config_Print {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String LBL01SEQNO = null;
    public String LBL01FIELD = null;
    public String LBL01MTYPE = null;
    public String LBL01PAPERTYPE = null;
    public String LBL01SIZE = null;
    public String LBL01WIDTH = null;
    public String LBL01HEIGHT = null;
    public String LBL01TOP = null;
    public String LBL01LEFT = null;
    public String LBL01ROWS = null;
    public String LBL01COLS = null;
    public String LBL01COLN = null;
    public String LBL01PRINT = null;
    public String LBL01TBLN = null;
    public String LB01TPLNAME = null;
    public String tplgrp = null;
    public String LB02PROPERTY = null;
    public String LBL02VALUE = null;
    public String LBL02SEQNO = null;
    public String LB01NOTES = null;
    public String LB01TplGrp = null;

    public Config_Print(String LB02SEQNO, String LB02PROPERTY, String LBL02VALUE) {
        this.LBL02SEQNO = LB02SEQNO;
        this.LB02PROPERTY = LB02PROPERTY;
        this.LBL02VALUE = LBL02VALUE;
    }

    public Config_Print(String LB01NOTES, String LB01TplGrp) {
        this.LB01NOTES = LB01NOTES;
        this.LB01TplGrp = LB01TplGrp;
    }

    public Config_Print(String LB01NOTES, String LB01TPLNAME, String tplgrp, String LB01TplGrp) {
        this.LB01NOTES = LB01NOTES;
        this.LB01TPLNAME = LB01TPLNAME;
        this.tplgrp = tplgrp;
        this.LB01TplGrp = LB01TplGrp;
    }

    public Config_Print(String LBL01SEQNO, String LBL01FIELD, String LBL01MTYPE, String LBL01PAPERTYPE, String LBL01SIZE, String LBL01WIDTH, String LBL01HEIGHT, String LBL01TOP, String LBL01LEFT, String LBL01ROWS, String LBL01COLN, String LBL01COLS, String LBL01PRINT, String LBL01TBLN) {
        this.LBL01SEQNO = LBL01SEQNO;
        this.LBL01FIELD = LBL01FIELD;
        this.LBL01MTYPE = LBL01MTYPE;
        this.LBL01PAPERTYPE = LBL01PAPERTYPE;
        this.LBL01SIZE = LBL01SIZE;
        this.LBL01WIDTH = LBL01WIDTH;
        this.LBL01HEIGHT = LBL01HEIGHT;
        this.LBL01TOP = LBL01TOP;
        this.LBL01LEFT = LBL01LEFT;
        this.LBL01ROWS = LBL01ROWS;
        this.LBL01COLN = LBL01COLN;
        this.LBL01COLS = LBL01COLS;
        this.LBL01PRINT = LBL01PRINT;
        this.LBL01TBLN = LBL01TBLN;
    }

    public String getLB01TPLNAME() {
        return this.LB01TPLNAME;
    }

    public String gettplgrp() {
        return this.tplgrp;
    }

    public String getLB01NOTES() {
        return this.LB01NOTES;
    }

    public String getLB01TplGrp() {
        return this.LB01TplGrp;
    }

    public String getLB02PROPERTY() {
        return this.LB02PROPERTY;
    }

    public String getLBL02SEQNO() {
        return this.LBL02SEQNO;
    }

    public String getLBL02VALUE() {
        return this.LBL02VALUE;
    }

    public String getLBL01SEQNO() {
        return this.LBL01SEQNO;
    }

    public String getLBL01FIELD() {
        return this.LBL01FIELD;
    }

    public String getLBL01TBLN() {
        return this.LBL01TBLN;
    }

    public String getLBL01MTYPE() {
        return this.LBL01MTYPE;
    }

    public String getLBL01PAPERTYPE() {
        return this.LBL01PAPERTYPE;
    }

    public String getLBL01SIZE() {
        return this.LBL01SIZE;
    }

    public String getLBL01PRINT() {
        return this.LBL01PRINT;
    }

    public String getLBL01WIDTH() {
        return this.LBL01WIDTH;
    }

    public String getLBL01HEIGHT() {
        return this.LBL01HEIGHT;
    }

    public String getLBL01TOP() {
        return this.LBL01TOP;
    }

    public String getLBL01LEFT() {
        return this.LBL01LEFT;
    }

    public String getLBL01ROWS() {
        return this.LBL01ROWS;
    }

    public String getLBL01COLN() {
        return this.LBL01COLN;
    }

    public String getLBL01COLS() {
        return this.LBL01COLS;
    }

    public static List<Config_Print> LblMstr(String Lb01TPLNAME) {
        ArrayList<Config_Print> list = new ArrayList<Config_Print>();
        String query = "Select * FROM LBMSTR WHERE LB01TPLNAME='" + Lb01TPLNAME + "' ";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Config_Print sheet = new Config_Print(rs.getString("LB01SEQNO"), rs.getString("LB01FIELD"), rs.getString("LB01MTYPE"), rs.getString("LB01PAPERTYPE"), rs.getString("LB01SIZE"), rs.getString("LB01WIDTH"), rs.getString("LB01HEIGHT"), rs.getString("LB01TOP"), rs.getString("LB02LEFT"), rs.getString("LB01ROWS"), rs.getString("LB01COLN"), rs.getString("LB01COLS"), rs.getString("LB01PRINT"), rs.getString("LB01TBLN"));
                    list.add(sheet);
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
        return list;
    }

    public static List<Config_Print> retrieveTpl(String grpID, String LB01BRNC) {
        ArrayList<Config_Print> list = new ArrayList<Config_Print>();
        DBConnection db = new DBConnection();
        String query = "";
        System.out.println("DB type" + DBConnection.getDBType());
        if (DBConnection.getDBType().equals("mssql")) {
            query = "SELECT LB01NOTES, LB01TPLNAME, LB01MTYPE+LB01TPLGRP as TplGrp, LB01TPLGRP FROM LBMSTR WHERE LB01MTYPE='" + grpID + "' AND LB01PRINT='Y'";
        } else if (DBConnection.getDBType().equals("oracle") || DBConnection.getDBType().equals("mysql")) {
            query = "SELECT LB01NOTES, LB01TPLNAME, CONCAT(LB01MTYPE,LB01TPLGRP) as TplGrp, LB01TPLGRP FROM LBMSTR WHERE LB01MTYPE='" + grpID + "'  AND LB01PRINT='Y'";
        }
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Config_Print tplName = new Config_Print(rs.getString("LB01NOTES"), rs.getString("LB01TPLNAME"), rs.getString("TplGrp"), rs.getString("LB01TPLGRP"));
                    list.add(tplName);
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
        return list;
    }

    public static List<Config_Print> LblProperty(String Lb01TPLNAME, String Lb01SEQNO) {
        ArrayList<Config_Print> list = new ArrayList<Config_Print>();
        String width = null;
        String query = "Select * FROM LBLPROPERTY WHERE LB02TPLNAME='" + Lb01TPLNAME + "' AND LB02SEQNO='" + Lb01SEQNO + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    width = rs.getString("LB02PROPERTY").equals("WIDTH") || rs.getString("LB02PROPERTY").equals("HEIGHT") || rs.getString("LB02PROPERTY").equals("TOP") || rs.getString("LB02PROPERTY").equals("LEFT") ? rs.getString("LB02VALUE").trim() : rs.getString("LB02VALUE");
                    System.out.println(width);
                    Config_Print sheet = new Config_Print(rs.getString("LB02SEQNO"), rs.getString("LB02PROPERTY"), width);
                    list.add(sheet);
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
        return list;
    }
}
