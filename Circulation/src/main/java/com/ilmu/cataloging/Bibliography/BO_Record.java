/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BO_Record {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String CT04STATUS = null;
    private String CT04MATNO = null;
    private String CT04CREDATE = null;
    private String CT04CREBY = null;
    private String CT04CRETIME = null;
    private String CT04MODIDATE = null;
    private String CT04MODIBY = null;
    private String CT04MODITIME = null;
    private String CT04TAG = null;
    private String CT04INDI1 = null;
    private String CT04INDI2 = null;
    private String CT04RAW = null;
    private String GL17TABNAME = null;
    private String GL17DESC = null;
    private String GL23DESC = null;
    private String CT06TAG = null;
    private String CT02MATNO = null;
    private String CT02CREDATE = null;
    private String CT02STATUS = null;
    private String CT02CRETIME = null;
    private String CT02CREBY = null;
    private String CT05SCONV = null;
    private int TPLID = 0;
    private String TPLNAME = null;
    private String TAG = null;
    private String TAGDESC = null;
    private String INDI1 = null;
    private String INDI2 = null;
    private String SUBFIELDS = null;
    private String SUBFDESC = null;
    private String CTTPLSUBF = null;
    private int CT16MSTR = 0;
    private String CT02TYPE = null;
    private String CT02IDXDATE = null;
    private String CT02IDXBY = null;
    private String CT02LEADER = null;
    private int CT06POINTER = 0;
    private String GL17AUTFLAG = null;
    private String GL17AUTHFLAG = null;
    private String GL17CPFLAG = null;
    private String GL17STOP = null;
    private String GL17MANDA = null;
    private String CT02IDXTIME = null;
    private String GL17REPEAT = null;

    public BO_Record(int TPLID, String TAG, String TAGDESC, String INDI1, String INDI2, String CTTPLSUBF, String GL17MANDA, int CT16MSTR, String GL17AUTFLAG) {
        this.TPLID = TPLID;
        this.TAG = TAG;
        this.TAGDESC = TAGDESC;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
        this.CTTPLSUBF = CTTPLSUBF;
        this.GL17MANDA = GL17MANDA;
        this.CT16MSTR = CT16MSTR;
        this.GL17AUTFLAG = GL17AUTFLAG;
    }

    public BO_Record(int TPLID, String TPLNAME, String TAG, String TAGDESC, String INDI1, String INDI2) {
        this.TPLID = TPLID;
        this.TPLNAME = TPLNAME;
        this.TAG = TAG;
        this.TAGDESC = TAGDESC;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
    }

    public BO_Record(int TPLID, String TAG, String SUBFIELDS, String SUBFDESC) {
        this.TPLID = TPLID;
        this.TAG = TAG;
        this.SUBFIELDS = SUBFIELDS;
        this.SUBFDESC = SUBFDESC;
    }

    public BO_Record(String CT04MATNO, String CT04TAG, String CT04INDI1, String CT04INDI2, String CT04RAW, String GL17DESC) {
        this.CT04MATNO = CT04MATNO;
        this.CT04TAG = CT04TAG;
        this.CT04INDI1 = CT04INDI1;
        this.CT04INDI2 = CT04INDI2;
        this.CT04RAW = CT04RAW;
        this.GL17DESC = GL17DESC;
    }

    public BO_Record(String CT02MATNO, String CT02STATUS, String CT02CREDATE, String CT02CREBY, String CT02CRETIME, String CT02TYPE, String CT02IDXDATE, String CT02IDXBY, String CT02IDXTIME) {
        this.CT02MATNO = CT02MATNO;
        this.CT02STATUS = CT02STATUS;
        this.CT02CREDATE = CT02CREDATE;
        this.CT02CREBY = CT02CREBY;
        this.CT02CRETIME = CT02CRETIME;
        this.CT02TYPE = CT02TYPE;
        this.CT02IDXBY = CT02IDXBY;
        this.CT02IDXDATE = CT02IDXDATE;
        this.CT02IDXTIME = CT02IDXTIME;
    }

    public BO_Record(String CT06TAG, String GL17TABNAME, int CT06POINTER, String GL17AUTFLAG) {
        this.CT06TAG = CT06TAG;
        this.GL17TABNAME = GL17TABNAME;
        this.CT06POINTER = CT06POINTER;
        this.GL17AUTFLAG = GL17AUTFLAG;
    }

    public BO_Record(String CT06TAG, String GL17TABNAME, int CT06POINTER, String GL17AUTFLAG, String GL17MANDA) {
        this.CT06TAG = CT06TAG;
        this.GL17TABNAME = GL17TABNAME;
        this.CT06POINTER = CT06POINTER;
        this.GL17AUTFLAG = GL17AUTFLAG;
        this.GL17MANDA = GL17MANDA;
    }

    public BO_Record(String GL23DESC) {
        this.GL23DESC = GL23DESC;
    }

    public BO_Record(String GL17AUTFLAG, String GL17STOP, String GL17CPFLAG) {
        this.GL17AUTFLAG = this.GL17AUTHFLAG;
        this.GL17STOP = GL17STOP;
        this.GL17CPFLAG = GL17CPFLAG;
    }

    public BO_Record(String CT02LEADER, String CT02MATNO) {
        this.CT02LEADER = CT02LEADER;
        this.CT02MATNO = CT02MATNO;
    }

    public BO_Record(String CT04STATUS, String CT04CREDATE, String CT04CREBY, String CT04CRETIME, String CT04MODIDATE, String CT04MODIBY, String CT04MODITIME) {
        this.CT04STATUS = CT04STATUS;
        this.CT04CREDATE = CT04CREDATE;
        this.CT04CREBY = CT04CREBY;
        this.CT04CRETIME = CT04CRETIME;
        this.CT04MODIDATE = CT04MODIDATE;
        this.CT04MODIBY = CT04MODIBY;
        this.CT04MODITIME = CT04MODITIME;
    }

    public String getGL17MANDA() {
        return this.GL17MANDA;
    }

    public String getCT04MATNO() {
        return this.CT04MATNO;
    }

    public int getCT16MSTR() {
        return this.CT16MSTR;
    }

    public String getCT04STATUS() {
        return this.CT04STATUS;
    }

    public String getGL17TABNAME() {
        return this.GL17TABNAME;
    }

    public String getCT04RAW() {
        return Handler.ifIsNull(this.CT04RAW);
    }

    public String getCT05SCONV() {
        return Handler.ifIsNull(this.CT05SCONV);
    }

    public String getCT04TAG() {
        return this.CT04TAG;
    }

    public String getCT04INDI1() {
        return this.CT04INDI1;
    }

    public String getCT04INDI2() {
        return this.CT04INDI2;
    }

    public String getGL17DESC() {
        return this.GL17DESC;
    }

    public String getGL23DESC() {
        return this.GL23DESC;
    }

    public String getCT06TAG() {
        return this.CT06TAG;
    }

    public String getCT02MATNO() {
        return this.CT02MATNO;
    }

    public String getCT02STATUS() {
        return this.CT02STATUS;
    }

    public String getCT02TYPE() {
        return this.CT02TYPE;
    }

    public String getCT02IDXDATE() {
        return DateTime.ifDateNull(this.CT02IDXDATE);
    }

    public String getCT02IDXTIME() {
        return DateTime.ifTimeNull(this.CT02IDXTIME);
    }

    public String getCT02IDXBY() {
        return this.CT02IDXBY;
    }

    public String getCT02CREDATE() {
        return DateTime.ifDateNull(this.CT02CREDATE);
    }

    public String getCT02CRTIME() {
        return DateTime.ifTimeNull(this.CT02CRETIME);
    }

    public String getCT02CREBY() {
        return this.CT02CREBY;
    }

    public int getTPLID() {
        return this.TPLID;
    }

    public String getTPLNAME() {
        return this.TPLNAME;
    }

    public int getCT06POINTER() {
        return this.CT06POINTER;
    }

    public String getTAG() {
        return this.TAG;
    }

    public String getTAGDESC() {
        return this.TAGDESC;
    }

    public String getINDI1() {
        return Handler.ifIsNull(this.INDI1);
    }

    public String getINDI2() {
        return Handler.ifIsNull(this.INDI2);
    }

    public String getSUBFIELDS() {
        return this.SUBFIELDS;
    }

    public String getSUBFDESC() {
        return this.SUBFDESC;
    }

    public String getCTTPLSUBF() {
        return Handler.ifIsNull(this.CTTPLSUBF);
    }

    public String getCT04CREDATE() {
        return this.CT04CREDATE;
    }

    public String getCT04CREBY() {
        return this.CT04CREBY;
    }

    public String getCT04CRETIME() {
        return this.CT04CRETIME;
    }

    public String getCT04MODIDATE() {
        return this.CT04MODIDATE;
    }

    public String getCT04MODITIME() {
        return this.CT04MODITIME;
    }

    public String getCT04MODIBY() {
        return this.CT04MODIBY;
    }

    public String getCT02LEADER() {
        return this.CT02LEADER;
    }

    public String getGL17AUTFLAG() {
        return this.GL17AUTFLAG;
    }

    public String getGL17STOP() {
        return this.GL17STOP;
    }

    public String getGL17CPFLAG() {
        return this.GL17CPFLAG;
    }

    public static List<BO_Record> getBORecord(String controlNoInput) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT06TAG, GL17TABNAME, CT06POINTER, GL17AUTFLAG, GL17MANDA FROM GLTAGP, CTPONT WHERE GL17TAG=CT06TAG AND GL17MARC=CT06MARC AND CT06MATNO='" + controlNoInput + "' ORDER BY CT06TAG";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record newSE01_SearchByMatno = new BO_Record(rs.getString("CT06TAG"), rs.getString("GL17TABNAME"), Integer.parseInt(rs.getString("CT06POINTER")), rs.getString("GL17AUTFLAG"), rs.getString("GL17MANDA"));
                    list.add(newSE01_SearchByMatno);
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

    public static List<BO_Record> getLeader(String CT02MATNO) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT02LEADER, CT02MATNO FROM CTMATM WHERE CT02MATNO='" + CT02MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record newSE01_SearchByMatno = new BO_Record(Handler.ifIsNull(rs.getString("CT02LEADER")), rs.getString("CT02MATNO"));
                    list.add(newSE01_SearchByMatno);
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

    public static String getLeaderManda(String GL17TAG) {
        String query = "SELECT GL17MANDA FROM GLTAGP WHERE GL17TAG='" + GL17TAG + "'";
        String manda = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    manda = rs.getString("GL17MANDA");
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
        return manda;
    }

    public static BO_Record getCTMATM(String controlNoInput) {
        String query = "SELECT CT02MATNO, CT02STATUS, CT02CRDATE, CT02CREBY, CT02CRETIME, CT02TYPE, CT02IDXBY, CT02IDXDATE,CT02IDXTIME FROM CTMATM WHERE CT02MATNO = '" + controlNoInput + "'";
        BO_Record newSE01_SearchByMatno = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_SearchByMatno = new BO_Record(Handler.ifIsNull(rs.getString("CT02MATNO")), Handler.CatalogueIDXStatus(rs.getString("CT02STATUS")), rs.getString("CT02CRDATE"), Handler.ifIsNull(rs.getString("CT02CREBY")), rs.getString("CT02CRETIME"), Handler.CatalogueType(rs.getString("CT02TYPE")), rs.getString("CT02IDXDATE"), rs.getString("CT02IDXBY"), rs.getString("CT02IDXTIME"));
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
        return newSE01_SearchByMatno;
    }

    public static List<BO_Record> getAllBy(String controlNoInput, String marcTag, String tableName, int pointer) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT06MATNO,CT06TAG,CT06INDI1,CT06INDI2,CT05SRAW, CT05SCONV, GL17DESC, GL17MANDA, GL17REPEAT FROM CTPONT, GLTAGP, " + tableName + " " + "WHERE CT06TAG = '" + marcTag + "' " + "AND CT06MATNO = '" + controlNoInput + "' " + "AND CT06POINTER = " + pointer + " " + "AND CT06POINTER = CT05POINTER " + "AND CT06TAG=GL17TAG AND GL17MARC='U' ORDER BY CT06TAG";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record newSE01_SearchByMatno = new BO_Record(rs.getString("CT06MATNO"), rs.getString("CT06TAG"), rs.getString("CT06INDI1"), rs.getString("CT06INDI2"), rs.getString("CT05SRAW"), rs.getString("GL17DESC"));
                    list.add(newSE01_SearchByMatno);
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

    public static String getMarkTagTableName(String marcTag) {
        String query = "SELECT GL17TABNAME FROM GLTAGP WHERE GL17TAG = '" + marcTag + "' AND GL17MARC = 'U'";
        String result = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    result = rs.getString("GL17TABNAME");
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
        return result;
    }

    public static List<BO_Record> retrieveDesc(String subf, String tag) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = " Select GL17DESC, GL23DESC from GLMSUBF, GLTAGP where GL17TAG=GL23TAG and GL17MARC=GL23MARC and GL23SUBF='" + subf + "' and GL17TAG='" + tag + "' and GL17MARC='U'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record newSE01_SearchByMatno = new BO_Record(rs.getString("GL23DESC"));
                    list.add(newSE01_SearchByMatno);
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

    public static String authorityLink(String tag) {
        String query = " Select GL17AUTFLAG, GL17STOP, GL17CPFLAG from GLTAGP where GL17TAG='" + tag + "' and GL17MARC='U'";
        Object newSE01_SearchByMatno = null;
        System.out.println(query);
        String authlink = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    authlink = rs.getString("GL17AUTFLAG");
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
        return authlink;
    }

    public static boolean matno_exist(String CT04MATNO) {
        String query = "SELECT COUNT(*) AS MATNO FROM CTWORK WHERE CT04MATNO='" + CT04MATNO + "'";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("MATNO")) <= 0) continue;
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

    public static List<BO_Record> getBOList(int tplID) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT16SEQNO,GL17MANDA,CT16TAG," + Handler.ifIsNull("CT16INDI1") + ", CT16INDI2, CT16SUBF, GL17DESC, CT16MSTR, GL17AUTFLAG " + "FROM CTTPL, GLTAGP " + "WHERE CT16TAG=GL17TAG AND GL17MARC='U' AND CT16MSTR=" + tplID + " ORDER BY CT16TAG";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getBOList = new BO_Record(Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("GL17DESC"), rs.getString("CT16INDI1"), rs.getString("CT16INDI2"), rs.getString("CT16SUBF"), rs.getString("GL17MANDA"), Integer.parseInt(rs.getString("CT16MSTR")), rs.getString("GL17AUTFLAG"));
                    list.add(getBOList);
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

    public static List<BO_Record> loadDefaultTpl() {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT16SEQNO,GL17MANDA,CT16TAG," + Handler.ifIsNull("CT16INDI1") + ", CT16INDI2, CT16SUBF, GL17DESC, CT16MSTR, GL17AUTFLAG " + "FROM CTTPL, GLTAGP, cttmstr " + "WHERE CT16TAG=GL17TAG and CT16MSTR=CT15SEQNO AND GL17MARC='U' AND CT15TNAME='DEFAULT TEMPLATE' ORDER BY CT16TAG";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getBOList = new BO_Record(Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("GL17DESC"), rs.getString("CT16INDI1"), rs.getString("CT16INDI2"), rs.getString("CT16SUBF"), rs.getString("GL17MANDA"), Integer.parseInt(rs.getString("CT16MSTR")), rs.getString("GL17AUTFLAG"));
                    list.add(getBOList);
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

    public static List<BO_Record> getBOSubfList(int tplID) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT TPLID, TAG, SUBFIELDS, SUBFDESC FROM BIBRCRD WHERE CTMSTR=" + tplID;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getBOSubfList = new BO_Record(Integer.parseInt(rs.getString("TPLID")), rs.getString("TAG"), rs.getString("SUBFIELDS"), rs.getString("SUBFDESC"));
                    list.add(getBOSubfList);
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

    public static List<BO_Record> getEmptySubf(int tplID) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT Distinct TPLID, TPLNAME, TAG, TAGDESC, INDI1, INDI2 FROM BIBRCRD WHERE CTMSTR=" + tplID;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getBOList = new BO_Record(Integer.parseInt(rs.getString("TPLID")), rs.getString("TPLNAME"), rs.getString("TAG"), rs.getString("TAGDESC"), rs.getString("INDI1"), rs.getString("INDI2"));
                    list.add(getBOList);
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

    public static String getTitle(String rawData) {
        StringBuilder result = new StringBuilder();
        if (rawData == null) {
            result.append(" ");
        } else {
            result.append(rawData);
        }
        return result.toString();
    }

    public static boolean getIndiCheck(String GL18TAG, String GL18INDILVL, String GL18INDI) {
        String query = "SELECT COUNT(GL18INDI) AS Count FROM GLMINDI WHERE GL18TAG = '" + GL18TAG + "' " + "AND GL18INDI='" + GL18INDI + "' " + "AND GL18INDILVL='" + GL18INDILVL + "'";
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

    public static List<BO_Record> getCTWORK(String matno) {
        String query = "SELECT TOP 1 CT04STATUS, CT04CREDATE, CT04CREBY, CT04CRETIME, CT04MODIDATE, CT04MODITIME, CT04MODIBY FROM CTWORK WHERE CT04MATNO = '" + matno + "'";
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getCTWORK = new BO_Record(rs.getString("CT04STATUS"), rs.getString("CT04CREDATE"), rs.getString("CT04CREBY"), rs.getString("CT04CRETIME"), rs.getString("CT04MODIDATE"), rs.getString("CT04MODITIME"), rs.getString("CT04MODIBY"));
                    list.add(getCTWORK);
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
