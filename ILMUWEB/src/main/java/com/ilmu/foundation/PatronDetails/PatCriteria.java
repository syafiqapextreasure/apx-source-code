/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.PatronDetails;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatCriteria {
    static Connection con;
    static Statement stmt;
    static ResultSet rsObj;
    private String Id = null;
    private String Name = null;
    private String GL76LBLNAME = null;
    private String GL76LBLCAPT = null;
    private String GL76TYPE = null;
    private String GL76INPUTNAME = null;
    private String GL76MANDAT = null;
    private String GL76RETAIN = null;
    private String GL76UNIQ = null;
    private String GL76UNIQFLD = null;
    private String GL76DEFAULT = null;

    public PatCriteria(String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public PatCriteria(String GL76LBLNAME, String GL76LBLCAPT, String GL76TYPE, String GL76INPUTNAME, String GL76MANDAT, String GL76RETAIN, String GL76UNIQ, String GL76UNIQFLD, String GL76DEFAULT) {
        this.GL76LBLNAME = GL76LBLNAME;
        this.GL76LBLCAPT = GL76LBLCAPT;
        this.GL76TYPE = GL76TYPE;
        this.GL76INPUTNAME = GL76INPUTNAME;
        this.GL76MANDAT = GL76MANDAT;
        this.GL76RETAIN = GL76RETAIN;
        this.GL76UNIQ = GL76UNIQ;
        this.GL76UNIQFLD = GL76UNIQFLD;
        this.GL76DEFAULT = GL76DEFAULT;
    }

    public String getGL76LBLNAME() {
        return this.GL76LBLNAME;
    }

    public String getGL76LBLCAPT() {
        return this.GL76LBLCAPT;
    }

    public String getGL76TYPE() {
        return this.GL76TYPE;
    }

    public String getGL76INPUTNAME() {
        return this.GL76INPUTNAME;
    }

    public String getGL76MANDAT() {
        return this.GL76MANDAT;
    }

    public String getGL76RETAIN() {
        return this.GL76RETAIN;
    }

    public String getGL76UNIQ() {
        return this.GL76UNIQ;
    }

    public String getGL76UNIQFLD() {
        return this.GL76UNIQFLD;
    }

    public String getGL76DEFAULT() {
        return this.GL76DEFAULT;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public static List<PatCriteria> searchPatronById(String searchType, String criteria, String cate_type, String cate_id) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLPATR WHERE GL14GRID >= '00' AND ";
        if (searchType.equals("patronId")) {
            sql = String.valueOf(sql) + "UPPER(GL14PATR) LIKE UPPER('%" + criteria + "%') ";
        } else if (searchType.equals("patronName")) {
            sql = String.valueOf(sql) + "UPPER(GL14NAME) LIKE UPPER('%" + criteria + "%') ";
        } else if (searchType.equals("patrnic")) {
            sql = String.valueOf(sql) + "GL14NEWIC LIKE '%" + criteria + "%' ";
        } else if (searchType.equals("patonic")) {
            sql = String.valueOf(sql) + "GL14OLDIC LIKE '%" + criteria + "%' ";
        } else if (searchType.equals("patronRelId")) {
            sql = String.valueOf(sql) + " GL14RELID LIKE '%" + criteria + "%'";
        }
        System.out.println(String.valueOf(sql) + " sql");
        System.out.println(String.valueOf(cate_id) + " cate_id");
        if (!cate_id.equals("")) {
            System.out.println("ddd");
            sql = String.valueOf(sql) + "AND GL14CATE = '" + cate_id + "' ";
        } else {
            System.out.println("aaa");
        }
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    PatCriteria misc = new PatCriteria(rsObj.getString("GL14PATR"), rsObj.getString("GL14NAME"));
                    list.add(misc);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronById2(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + "UPPER(GL14PATR) LIKE UPPER('%" + criteria + "%')";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronById = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronById);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByName(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + "UPPER(GL14NAME) LIKE UPPER('%" + criteria + "%')";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByName = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByName);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByNewIc(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + " GL14NEWIC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByNewIc = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByNewIc);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByOldIc(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + " GL14OLDIC LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByOldIc = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByOldIc);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByRelId(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + " GL14RELID LIKE '%" + criteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByRelId = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByRelId);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByMemDate(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String datecriteria = DateFormatter.displayToDBFormat(criteria);
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + " GL14MEMDATE LIKE '%" + datecriteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByMemDate = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByMemDate);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> searchPatronByExpDate(String criteria, String loginId) {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String datecriteria = DateFormatter.displayToDBFormat(criteria);
        String sql = "SELECT GL14PATR, GL14NAME, GL14ABBR FROM GLGRMA JOIN GLPATR ON GL14GRID = GL02GRP WHERE GL02ACL >= (SELECT GL02ACL FROM GLPATR JOIN GLGRMA ON GL14GRID = GL02GRP WHERE UPPER(GL14PATR) = UPPER('" + loginId + "')) AND ";
        sql = String.valueOf(sql) + " GL14EXPDATE LIKE '%" + datecriteria + "%'";
        System.out.println(sql);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronByExpDate = new PatCriteria(rs.getString("GL14PATR"), rs.getString("GL14NAME"));
                    list.add(newSearchPatronByExpDate);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<PatCriteria> retrievePatrDetails() {
        ArrayList<PatCriteria> list = new ArrayList<PatCriteria>();
        Statement stmt = null;
        String sql = "SELECT GL76LBLNAME, GL76LBLCAPT,GL76TYPE,GL76INPUTNAME,GL76MANDAT,GL76RETAIN,GL76UNIQ,GL76UNIQFLD, GL76DEFAULT FROM GLPCTRL";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    PatCriteria newSearchPatronById = new PatCriteria(rs.getString("GL76LBLNAME"), rs.getString("GL76LBLCAPT"), rs.getString("GL76TYPE"), rs.getString("GL76INPUTNAME"), rs.getString("GL76MANDAT"), rs.getString("GL76RETAIN"), rs.getString("GL76UNIQ"), rs.getString("GL76UNIQFLD"), rs.getString("GL76DEFAULT"));
                    list.add(newSearchPatronById);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                try {
                    con.close();
                }
                catch (Exception exception) {}
            }
        }
        finally {
            try {
                stmt.close();
            }
            catch (Exception exception) {}
            try {
                con.close();
            }
            catch (Exception exception) {}
        }
        return list;
    }
}
