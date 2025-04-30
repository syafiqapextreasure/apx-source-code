/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.template;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Template_Maintenance {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private int CT15SEQNO = 0;
    private String CT15TNAME = null;
    private String CT15STAT = null;

    public Template_Maintenance(int CT15SEQNO, String CT15TNAME, String CT15STAT) {
        this.CT15SEQNO = CT15SEQNO;
        this.CT15TNAME = CT15TNAME;
        this.CT15STAT = CT15STAT;
    }

    public Template_Maintenance(int CT15SEQNO) {
        this.CT15SEQNO = CT15SEQNO;
    }

    public int getCT15SEQNO() {
        return this.CT15SEQNO;
    }

    public void setCT15SEQNO(int CT15SEQNO) {
        this.CT15SEQNO = CT15SEQNO;
    }

    public String getCT15TNAME() {
        return this.CT15TNAME;
    }

    public void setCT15TNAME(String CT15TNAME) {
        this.CT15TNAME = CT15TNAME;
    }

    public String getCT15STAT() {
        return this.CT15STAT;
    }

    public void setCT15STAT(String CT15STAT) {
        this.CT15STAT = CT15STAT;
    }

    public static List<Template_Maintenance> getAllTpl() throws SQLException {
        ArrayList<Template_Maintenance> tplList = new ArrayList<Template_Maintenance>();
        String query = " SELECT * FROM CTTMSTR";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Template_Maintenance new01_TemplateMaintenance = new Template_Maintenance(Integer.parseInt(rs.getString("CT15SEQNO")), rs.getString("CT15TNAME"), rs.getString("CT15STAT"));
                    tplList.add(new01_TemplateMaintenance);
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
        return tplList;
    }

    public static List<Template_Maintenance> chkExist() throws SQLException {
        ArrayList<Template_Maintenance> tplList = new ArrayList<Template_Maintenance>();
        String query = "Select CT15SEQNo from CTTMSTR MT where exists (select CTMSTR from CTTPL OT where OT.CTMSTR = MT.CT15SEQNo)";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                System.out.println(query);
                while (rs.next()) {
                    Template_Maintenance new01_TemplateMaintenance = new Template_Maintenance(Integer.parseInt(rs.getString("CT15SEQNO")));
                    tplList.add(new01_TemplateMaintenance);
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

    public static List<Template_Maintenance> notExist() throws SQLException {
        ArrayList<Template_Maintenance> tplList = new ArrayList<Template_Maintenance>();
        String query = "Select CT15SEQNo from CTTMSTR MT where not exists (select CTMSTR from CTTPL OT where OT.CTMSTR = MT.CT15SEQNo)";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Template_Maintenance new01_TemplateMaintenance = new Template_Maintenance(Integer.parseInt(rs.getString("CT15SEQNO")));
                    tplList.add(new01_TemplateMaintenance);
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
}
