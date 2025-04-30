/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Item_Reassignment;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLItem_Reassignement {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String PATR = null;
    private String NAME14 = null;
    private String STAT = null;
    private String DESC08 = null;
    private String CATE = null;
    private String DESC07 = null;
    private String GRID = null;
    private String NAME02 = null;
    private String EXPDATE = null;
    private String IMG = null;
    private String BRNC14 = null;
    private String DESC71 = null;
    private String BRNC71 = null;
    private String OLDIC = null;
    private String NEWIC = null;
    private String ICAT = null;
    private String DESC10 = null;
    private String LOCA = null;
    private String DESC05 = null;
    private String BRNC05 = null;
    private String VALUE9 = null;
    private String ACTION8RECALL = null;
    private String ALLOWRSV = null;
    private String action01 = null;
    private String FINELIMIT = null;
    private String totalcharged = null;
    private String totalpaid = null;

    public SQLItem_Reassignement(String GL14PATR, String GL14NAME, String GL14STAT, String GL08DESC, String GL14CATE, String GL07DESC, String GL14GRID, String GL02NAME, String GL14EXPDATE, String GL14IMG, String GL14BRNC, String GL71DESC, String ACTION8RECALL, String ALLOWRSV, String action01, String FINELIMIT, String totalcharged, String totalpaid) {
        this.PATR = GL14PATR;
        this.NAME14 = GL14NAME;
        this.STAT = GL14STAT;
        this.DESC08 = GL08DESC;
        this.CATE = GL14CATE;
        this.DESC07 = GL07DESC;
        this.GRID = GL14GRID;
        this.NAME02 = GL02NAME;
        this.EXPDATE = GL14EXPDATE;
        this.IMG = GL14IMG;
        this.BRNC14 = GL14BRNC;
        this.DESC71 = GL71DESC;
        this.ACTION8RECALL = ACTION8RECALL;
        this.ALLOWRSV = ALLOWRSV;
        this.action01 = action01;
        this.FINELIMIT = FINELIMIT;
        this.totalcharged = totalcharged;
        this.totalpaid = totalpaid;
    }

    public SQLItem_Reassignement(String GL10ICAT, String GL10DESC) {
        this.ICAT = GL10ICAT;
        this.DESC10 = GL10DESC;
    }

    public SQLItem_Reassignement(String GL05LOCA, String GL05DESC, String GL05BRNC, String GL71DESC) {
        this.LOCA = GL05LOCA;
        this.DESC05 = GL05DESC;
        this.BRNC05 = GL05BRNC;
        this.DESC71 = GL71DESC;
    }

    public SQLItem_Reassignement(String GL99VALUE) {
        this.VALUE9 = GL99VALUE;
    }

    public String getPATR() {
        return Handler.ifIsNull(this.PATR);
    }

    public String getNAME14() {
        return Handler.ifIsNull(this.NAME14);
    }

    public String getSTAT() {
        return Handler.ifIsNull(this.STAT);
    }

    public String getDESC08() {
        return Handler.ifIsNull(this.DESC08);
    }

    public String getCATE() {
        return Handler.ifIsNull(this.CATE);
    }

    public String getDESC07() {
        return Handler.ifIsNull(this.DESC07);
    }

    public String getGRID() {
        return Handler.ifIsNull(this.GRID);
    }

    public String getNAME02() {
        return Handler.ifIsNull(this.NAME02);
    }

    public String getEXPDATE() {
        return SQLItem_Reassignement.dateConverter(this.EXPDATE);
    }

    public String getIMG() {
        return Handler.ifIsNull(this.IMG);
    }

    public String getOLDIC() {
        return Handler.ifIsNull(this.OLDIC);
    }

    public String getNEWIC() {
        return Handler.ifIsNull(this.NEWIC);
    }

    public String getICAT() {
        return Handler.ifIsNull(this.ICAT);
    }

    public String getDESC10() {
        return Handler.ifIsNull(this.DESC10);
    }

    public String getBRNC14() {
        return Handler.ifIsNull(this.BRNC14);
    }

    public String getDESC71() {
        return Handler.ifIsNull(this.DESC71);
    }

    public String getLOCA() {
        return Handler.ifIsNull(this.LOCA);
    }

    public String getDESC05() {
        return Handler.ifIsNull(this.DESC05);
    }

    public String getBRNC05() {
        return Handler.ifIsNull(this.BRNC05);
    }

    public String getBRNC71() {
        return Handler.ifIsNull(this.BRNC71);
    }

    public String getVALUE9() {
        return Handler.ifIsNull(this.VALUE9);
    }

    public String getACTION8RECALL() {
        return Handler.ifIsNull(this.ACTION8RECALL);
    }

    public String getALLOWRSV() {
        return Handler.ifIsNull(this.ALLOWRSV);
    }

    public String getaction01() {
        return Handler.ifIsNull(this.action01);
    }

    public String getFINELIMIT() {
        return Handler.ifIsNull(this.FINELIMIT);
    }

    public String gettotalcharged() {
        return Handler.ifIsNull(this.totalcharged);
    }

    public String gettotalpaid() {
        return Handler.ifIsNull(this.totalpaid);
    }

    public static List<SQLItem_Reassignement> getPatronDetail(String GL14PATR) {
        ArrayList<SQLItem_Reassignement> list = new ArrayList<SQLItem_Reassignement>();
        String query = "Select p.GL14PATR, p.GL14NAME, p.GL14STAT, s.GL08DESC, p.GL14CATE, c.GL07DESC, p.GL14GRID, d.GL02NAME, p.GL14EXPDATE, p.GL14IMG, p.GL14BRNC, b.GL71DESC, (SELECT GL08ACTION8 FROM GLSTAT WHERE GL08STAT = GL14STAT) AS ACTION8RECALL, (SELECT GL07ALLOWRSV FROM GLCATE WHERE GL07CATE = GL14CATE) AS ALLOWRSV, (SELECT GL08ACTION1 FROM GLSTAT WHERE GL08STAT =  p.GL14STAT AND GL08ACTION1 = 'Y') AS YESNO , GL07FINELIMIT, (SELECT SUM(RE01AMT) FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'D' AND  UPPER(RE01PATR) = UPPER('" + GL14PATR + "')) AS totalcharged, " + "(SELECT SUM(RE01AMT) AS totalpaid FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD " + "AND GL38MODE = 'C' AND  UPPER(RE01PATR) = UPPER('" + GL14PATR + "')) AS totalpaid " + "FROM GLPATR p  " + "LEFT JOIN GLSTAT s ON p.GL14STAT=s.GL08STAT " + "LEFT JOIN GLCATE c ON p.GL14CATE=c.GL07CATE " + "LEFT JOIN GLGRMA d ON p.GL14GRID=d.GL02GRP " + "LEFT JOIN GLBRNC b ON p.GL14BRNC=b.GL71BRNC " + "WHERE UPPER(GL14PATR) = UPPER('" + GL14PATR + "')";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement p_getDetail = new SQLItem_Reassignement(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14STAT"), rs.getString("GL08DESC"), rs.getString("GL14CATE"), rs.getString("GL07DESC"), rs.getString("GL14GRID"), rs.getString("GL02NAME"), rs.getString("GL14EXPDATE"), rs.getString("GL14IMG"), rs.getString("GL14BRNC"), rs.getString("GL71DESC"), rs.getString("ACTION8RECALL"), Handler.ifIsNull(rs.getString("ALLOWRSV")), Handler.ifIsNull(rs.getString("YESNO")), Handler.ifIsNull(rs.getString("GL07FINELIMIT")), Handler.ifIsNull(rs.getString("totalcharged")), Handler.ifIsNull(rs.getString("totalpaid")));
                    list.add(p_getDetail);
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

    public static List<SQLItem_Reassignement> getLOCABRANCH() {
        ArrayList<SQLItem_Reassignement> listloca = new ArrayList<SQLItem_Reassignement>();
        String query = "  SELECT GL05LOCA,  GL05DESC, GL05BRNC, GL71DESC FROM GLLOCA, GLBRNC WHERE GL05BRNC = GL71BRNC";
        try {
            try {
                System.out.println(query);
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement ICaInfo = new SQLItem_Reassignement(rs.getString("GL05LOCA"), rs.getString("GL05DESC"), rs.getString("GL05BRNC"), rs.getString("GL71DESC"));
                    listloca.add(ICaInfo);
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
        return listloca;
    }

    public static List<SQLItem_Reassignement> getRecallBRANCH() {
        ArrayList<SQLItem_Reassignement> listloca = new ArrayList<SQLItem_Reassignement>();
        String query = "SELECT * FROM GLBRNC";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement ICaInfo = new SQLItem_Reassignement(rs.getString("GL71BRNC"), rs.getString("GL71DESC"));
                    listloca.add(ICaInfo);
                }
            }
            catch (SQLException sQLException) {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
        return listloca;
    }

    public static List<SQLItem_Reassignement> getICat() {
        ArrayList<SQLItem_Reassignement> listICat = new ArrayList<SQLItem_Reassignement>();
        String query = "SELECT GL10ICAT, GL10DESC FROM GLICAT";
        try {
            try {
                System.out.println(query);
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement ICaInfo = new SQLItem_Reassignement(rs.getString("GL10ICAT"), rs.getString("GL10DESC"));
                    listICat.add(ICaInfo);
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
        return listICat;
    }

    public static List<SQLItem_Reassignement> getLOCABRANCHBYID(String id) {
        ArrayList<SQLItem_Reassignement> listloca = new ArrayList<SQLItem_Reassignement>();
        String query = "  SELECT GL05BRNC, GL71DESC FROM GLLOCA, GLBRNC WHERE GL05BRNC = GL71BRNC AND GL05LOCA = '" + id + "'";
        try {
            try {
                System.out.println(query);
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement ICaInfo = new SQLItem_Reassignement(rs.getString("GL05BRNC"), rs.getString("GL71DESC"));
                    listloca.add(ICaInfo);
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
        return listloca;
    }

    public static List<SQLItem_Reassignement> getReassignGrace() {
        ArrayList<SQLItem_Reassignement> listReassignGrace = new ArrayList<SQLItem_Reassignement>();
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC ='REASSIGNEDGRACE'";
        try {
            try {
                System.out.println(query);
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    SQLItem_Reassignement GL10DESCVal = new SQLItem_Reassignement(rs.getString("GL99VALUE"));
                    listReassignGrace.add(GL10DESCVal);
                    System.out.println(String.valueOf(rs.getString("GL99VALUE")) + "123");
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
        return listReassignGrace;
    }

    public static String dateConverter(String oldDate) {
        StringBuilder result = new StringBuilder();
        if (oldDate == null) {
            result.append(" ");
        } else {
            result.append(DateTime.DBToDisplayFormat(oldDate));
        }
        return result.toString();
    }
}
