/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Template_Maint;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Template_Maintenance {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private int CT15SEQNO = 0;
    private String CT15TNAME = null;
    private String CT15STAT = null;
    private int TPLID = 0;
    private String TPLNAME = null;
    private String TAG = null;
    private String CTTPLSUBF = null;
    private String SUBFDESC = null;
    private int CTTPLID = 0;
    private String CTTPLN = null;
    private String CTTPLTAG = null;
    private String GL17DESC = null;
    private String CTTPLINDI1 = null;
    private String CTTPLINDI2 = null;
    private String GL18INDIVL = null;
    private String GL18DESC1 = null;

    public Template_Maintenance(int CT15SEQNO, String CT15TNAME, String CT15STAT) {
        this.CT15SEQNO = CT15SEQNO;
        this.CT15TNAME = CT15TNAME;
        this.CT15STAT = CT15STAT;
    }

    public Template_Maintenance(int CT15SEQNO) {
        this.CT15SEQNO = CT15SEQNO;
    }

    public Template_Maintenance(int CTTPLID, String CTTPLTAG, String GL18INDIVL, String CTTPLINDI1, String CTTPLINDI2, String GL18DESC1, String CTTPLSUBF) {
        this.CTTPLID = CTTPLID;
        this.CTTPLTAG = CTTPLTAG;
        this.GL18INDIVL = GL18INDIVL;
        this.CTTPLINDI1 = CTTPLINDI1;
        this.CTTPLINDI2 = CTTPLINDI2;
        this.GL18DESC1 = GL18DESC1;
        this.CTTPLSUBF = CTTPLSUBF;
    }

    public Template_Maintenance(int TPLID, String TAG, String CTTPLSUBF, String SUBFDESC) {
        this.TPLID = TPLID;
        this.TAG = TAG;
        this.CTTPLSUBF = CTTPLSUBF;
        this.SUBFDESC = SUBFDESC;
    }

    public Template_Maintenance(int CTTPLID, String CTTPLTAG, String GL17DESC, String CTTPLINDI1, String CTTPLINDI2, String CTTPLSUBF) {
        this.CTTPLID = CTTPLID;
        this.CTTPLTAG = CTTPLTAG;
        this.GL17DESC = GL17DESC;
        this.CTTPLINDI1 = CTTPLINDI1;
        this.CTTPLINDI2 = CTTPLINDI2;
        this.CTTPLSUBF = CTTPLSUBF;
    }

    public Template_Maintenance(String CT15TNAME, int CTTPLID, String CTTPLTAG, String GL17DESC, String CTTPLINDI1, String CTTPLINDI2, String CTTPLSUBF) {
        this.CT15TNAME = CT15TNAME;
        this.CTTPLID = CTTPLID;
        this.CTTPLTAG = CTTPLTAG;
        this.GL17DESC = GL17DESC;
        this.CTTPLINDI1 = CTTPLINDI1;
        this.CTTPLINDI2 = CTTPLINDI2;
        this.CTTPLSUBF = CTTPLSUBF;
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

    public int getTPLID() {
        return this.TPLID;
    }

    public String getTPLNAME() {
        return this.TPLNAME;
    }

    public String getTAG() {
        return this.TAG;
    }

    public String getCTTPLSUBF() {
        return this.CTTPLSUBF;
    }

    public String getSUBFDESC() {
        return this.SUBFDESC;
    }

    public int getCTTPLID() {
        return this.CTTPLID;
    }

    public String getCTTPLN() {
        return this.CTTPLN;
    }

    public String getCTTPLTAG() {
        return this.CTTPLTAG;
    }

    public String getCTTPLINDI1() {
        return this.CTTPLINDI1;
    }

    public String getCTTPLINDI2() {
        return this.CTTPLINDI2;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getGL18INDIVL() {
        return this.GL18INDIVL;
    }

    public String getGL18DESC1() {
        return this.GL18DESC1;
    }

    public static List<Template_Maintenance> getAllTpl() throws SQLException {
        ArrayList<Template_Maintenance> tplList = new ArrayList<Template_Maintenance>();
        String query = " SELECT * FROM CTTMSTR ORDER BY CT15STAT";
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
        String query = "Select CT15SEQNo from CTTMSTR MT where exists (select CT16MSTR from CTTPL OT where OT.CT16MSTR = MT.CT15SEQNo)";
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
        String query = "Select CT15SEQNo from CTTMSTR MT where not exists (select CT16MSTR from CTTPL OT where OT.CT16MSTR = MT.CT15SEQNo)";
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

    public static List<Template_Maintenance> getTplId() throws SQLException {
        ArrayList<Template_Maintenance> tplList = new ArrayList<Template_Maintenance>();
        String query = "Select max(GL98VALUE)+1 AS CT15SEQNO from GLNUMB WHERE GL98FUNC='CTTPLSEQNO'";
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

    public static Template_Maintenance CT_Tpl(String CT15SEQNO, String CT15TNAME, String CT15STAT) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueInt.put("CT15SEQNO", Integer.parseInt(CT15SEQNO));
        valueStr.put("CT15TNAME", CT15TNAME);
        valueStr.put("CT15STAT", CT15STAT);
        String query = QueryBuilder.createInsertQuery("CTTMSTR", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static List<Template_Maintenance> DisplayByTplID(int CTMSTR) {
        System.out.println("sss");
        ArrayList<Template_Maintenance> list = new ArrayList<Template_Maintenance>();
        String query = "SELECT CT15TNAME, CT16SEQNO, CT16TAG, GL17DESC, CT16INDI1, CT16INDI2,CT16SUBF FROM CTTPL, GLTAGP, CTTMSTR where CT16TAG=GL17TAG and GL17MARC='U' and CT15SEQNO=CT16MSTR and CT16MSTR='" + CTMSTR + "'order by CT16TAG";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Template_Maintenance tplInfo_GetListBy = new Template_Maintenance(rs.getString("CT15TNAME"), Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("GL17DESC"), rs.getString("CT16INDI1"), rs.getString("CT16INDI2"), Handler.ifIsNull(rs.getString("CT16SUBF")));
                    list.add(tplInfo_GetListBy);
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

    public static List<Template_Maintenance> getSubfield(int seqNo) {
        ArrayList<Template_Maintenance> list = new ArrayList<Template_Maintenance>();
        String query = "SELECT CT16SEQNO,CT16TAG, GL17DESC, CT16INDI1, CT16INDI2, CT16SUBF FROM CTTPL where CT16MSTR='" + seqNo + "'order by CT16TAG";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Template_Maintenance tplInfo_GetListBy = new Template_Maintenance(Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("CT16SUBF"), rs.getString("SUBFDESC"));
                    list.add(tplInfo_GetListBy);
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

    public static Template_Maintenance getTplByID(int seqNo) throws SQLException {
        String query = "select * from CTTMSTR where CT15SEQNO='" + seqNo + "'";
        Template_Maintenance new01_TemplateMaintenance = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    new01_TemplateMaintenance = new Template_Maintenance(Integer.parseInt(rs.getString("CT15SEQNO")), rs.getString("CT15TNAME"), rs.getString("CT15STAT"));
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
        return new01_TemplateMaintenance;
    }

    public static boolean CT_editTplMaint(int CT15SEQNO, String CT15TNAME, String CT15STAT) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        condition.put("CT15SEQNO", CT15SEQNO);
        valueStr.put("CT15TNAME", CT15TNAME);
        valueStr.put("CT15STAT", CT15STAT);
        String query = QueryBuilder.createUpdateIntQuery("CTTMSTR", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean Tpl_isDeletable(String tplID) {
        String query = "SELECT COUNT(*) AS Count FROM CTTMSTR,CTTPL WHERE CT15SEQNO=CT16MSTR and CT15SEQNO='" + Integer.parseInt(tplID) + "' AND CT15STAT='I'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    deletable = true;
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
        return deletable;
    }

    public static void CT_deleteTplMaint(String tplID) {
        String query = "UPDATE CTTMSTR SET CT15STAT='I' WHERE CT15SEQNO = '" + Integer.parseInt(tplID) + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }

    public static boolean CT_editTplInfoMaint(int CTTPLID, String CTTPLN, String CTTPLTAG, String CTINDI1, String CTINDI2, String CTSUBF, int CTMSTR) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> condition = new HashMap<String, Integer>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        condition.put("CT16SEQNO", CTTPLID);
        valueStr.put("CT16TAG", CTTPLTAG);
        valueStr.put("CT16INDI1", CTINDI1);
        valueStr.put("CT16INDI2", CTINDI2);
        valueStr.put("CT16SUBF", CTSUBF);
        valueInt.put("CT16MSTR", CTMSTR);
        String query = QueryBuilder.createUpdateIntQuery("CTTPL", valueStr, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static void CT_deleteTplInfo(String tplID) {
        String query = "DELETE FROM CTTPL WHERE CT16SEQNO = '" + Integer.parseInt(tplID) + "'";
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            PreparedStatement delete = c.prepareStatement(query);
            delete.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CT_DeleteTplInfo(String CT16MSTR) {
        String query = "DELETE FROM CTTPL WHERE CT16MSTR = '" + Integer.parseInt(CT16MSTR) + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }

    public static void CT_TplInfo(String CT16TAG, String CT16INDI1, String CT16INDI2, String CT16SUBF, String CT16MSTR) {
        String query = " insert into CTTPL (CT16TAG, CT16INDI1, CT16INDI2, CT16SUBF, CT16MSTR) values (?, ?, ?, ?, ?)";
        System.out.println(query);
        try {
            c = DBConnection.getConnection();
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, CT16TAG);
            preparedStmt.setString(2, CT16INDI1);
            preparedStmt.setString(3, CT16INDI2);
            preparedStmt.setString(4, CT16SUBF);
            preparedStmt.setInt(5, Integer.parseInt(CT16MSTR));
            preparedStmt.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tplNameChk(String CT15TNAME) {
        String query = "Select Count(CT15TNAME) AS Count FROM CTTMSTR WHERE CT15TNAME= '" + CT15TNAME + "'";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static boolean rcrdChk(String CT15TNAME) {
        String query = "Select Count(*) As Count FROM CTTMSTR WHERE CT15TNAME='" + CT15TNAME + "'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
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
        return exist;
    }

    public static Template_Maintenance getTplInfo(int seqNo) throws SQLException {
        String query = "SELECT CT16SEQNO, CT16TAG, GL18INDILVL, CT16INDI1, CT16INDI2, CT15TNAME, CT16SUBF from CTTPL, GLMINDI, CTTMSTR where CT16TAG= GL18TAG and CT15SEQNO=CT16MSTR and CT16SEQNO='" + seqNo + "'";
        Template_Maintenance new01_TemplateMaintenance = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    new01_TemplateMaintenance = new Template_Maintenance(Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("GL18INDILVL"), rs.getString("CT16INDI1"), rs.getString("CT16INDI2"), rs.getString("CT15TNAME"), rs.getString("CT16SUBF"));
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
        return new01_TemplateMaintenance;
    }

    public static boolean tplIDExists(String CT16SEQNO) {
        String query = "Select COUNT(CT16SEQNO) AS Count FROM CTTPL WHERE CT16MSTR='" + Integer.parseInt(CT16SEQNO) + "'";
        boolean exist = false;
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}
