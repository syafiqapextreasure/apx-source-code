/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Bibliography;

import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
    private String CT04MARC = null;
    private String CT04TYPE = null;
    private String CT02T006 = null;
    private String CT02T007 = null;
    private String CT02T008 = null;
    private int CT02NORESV = 0;
    private String GL17TAG = null;

    public BO_Record(int TPLID, String TAG, String TAGDESC, String INDI1, String INDI2, String CTTPLSUBF, String GL17MANDA, int CT16MSTR, String GL17AUTFLAG, String GL17REPEAT) {
        this.TPLID = TPLID;
        this.TAG = TAG;
        this.TAGDESC = TAGDESC;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
        this.CTTPLSUBF = CTTPLSUBF;
        this.GL17MANDA = GL17MANDA;
        this.CT16MSTR = CT16MSTR;
        this.GL17AUTFLAG = GL17AUTFLAG;
        this.GL17REPEAT = GL17REPEAT;
    }

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

    public BO_Record(String CT04MATNO, String TAG, String INDI1, String INDI2, String CTTPLSUBF, String TAGDESC, String CT02T006, String CT02T007, String CT02T008, int CT02NORESV, String GL17REPEAT) {
        this.CT04MATNO = CT04MATNO;
        this.TAG = TAG;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
        this.CTTPLSUBF = CTTPLSUBF;
        this.TAGDESC = TAGDESC;
        this.CT02T006 = CT02T006;
        this.CT02T007 = CT02T007;
        this.CT02T008 = CT02T008;
        this.CT02NORESV = CT02NORESV;
        this.GL17REPEAT = GL17REPEAT;
    }

    public BO_Record(String CT04MATNO, String TAG, String INDI1, String INDI2, String CTTPLSUBF, String TAGDESC) {
        this.CT04MATNO = CT04MATNO;
        this.TAG = TAG;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
        this.CTTPLSUBF = CTTPLSUBF;
        this.TAGDESC = TAGDESC;
    }

    public BO_Record(String CT02MATNO, String CT02STATUS, String CT02CREDATE, String CT02CREBY, String CT02CRETIME, String CT02TYPE, String CT02IDXBY, String CT02IDXDATE, String CT02IDXTIME) {
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

    public BO_Record(String CT02MATNO, String CT02STATUS, String CT02CREDATE, String CT02CREBY, String CT02CRETIME, String CT02TYPE, String CT02IDXBY, String CT02IDXDATE, String CT02IDXTIME, String CT04TYPE, String CT04MARC) {
        this.CT02MATNO = CT02MATNO;
        this.CT02STATUS = CT02STATUS;
        this.CT02CREDATE = CT02CREDATE;
        this.CT02CREBY = CT02CREBY;
        this.CT02CRETIME = CT02CRETIME;
        this.CT02TYPE = CT02TYPE;
        this.CT02IDXBY = CT02IDXBY;
        this.CT02IDXDATE = CT02IDXDATE;
        this.CT02IDXTIME = CT02IDXTIME;
        this.CT04TYPE = CT04TYPE;
        this.CT04MARC = CT04MARC;
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

    public BO_Record(String CT04MATNO, String TAG, String INDI1, String INDI2, String CTTPLSUBF, String CT04STATUS, String TAGDESC, String GL17TABNAME, String CT04MARC, String GL17MANDA, String GL17REPEAT, String GL17TAG) {
        this.CT04MATNO = CT04MATNO;
        this.TAG = TAG;
        this.INDI1 = INDI1;
        this.INDI2 = INDI2;
        this.CTTPLSUBF = CTTPLSUBF;
        this.CT04STATUS = CT04STATUS;
        this.TAGDESC = TAGDESC;
        this.GL17TABNAME = GL17TABNAME;
        this.CT04MARC = CT04MARC;
        this.GL17MANDA = GL17MANDA;
        this.GL17REPEAT = GL17REPEAT;
        this.GL17TAG = GL17TAG;
    }

    public String getCT04MARC() {
        return this.CT04MARC;
    }

    public String getCT02T006() {
        return this.CT02T006;
    }

    public String getCT02T007() {
        return this.CT02T007;
    }

    public String getCT02T008() {
        return this.CT02T008;
    }

    public String getCT04TYPE() {
        return this.CT04TYPE;
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

    public String getGL17REPEAT() {
        return this.GL17REPEAT;
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

    public static String getLinkTagDesc() {
        String query = "SELECT GL17DESC FROM GLTAGP WHERE GL17TAG='880'";
        String desc = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    desc = rs.getString("GL17DESC");
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
        return desc;
    }

    public static List<BO_Record> getLeader(String CT02MATNO, String module) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT " + module + "02LEADER, " + module + "02MATNO FROM " + module + "MATM " + "WHERE " + module + "02MATNO='" + CT02MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record newSE01_SearchByMatno = new BO_Record(Handler.ifIsNull(rs.getString(String.valueOf(module) + "02LEADER")), rs.getString(String.valueOf(module) + "02MATNO"));
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

    public static BO_Record getIndexCTMATM(String controlNoInput, String module) {
        String query = "SELECT " + module + "02MATNO, " + module + "02STATUS, " + module + "02CRDATE, " + module + "02CREBY, " + module + "02CRETIME, " + module + "02TYPE, " + module + "02IDXBY, " + module + "02IDXDATE," + module + "02IDXTIME FROM " + module + "MATM " + "WHERE " + module + "02MATNO = '" + controlNoInput + "'";
        BO_Record newSE01_SearchByMatno = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_SearchByMatno = new BO_Record(Handler.ifIsNull(rs.getString(String.valueOf(module) + "02MATNO")), Handler.CatalogueIDXStatus(rs.getString(String.valueOf(module) + "02STATUS")), rs.getString(String.valueOf(module) + "02CRDATE"), Handler.ifIsNull(rs.getString(String.valueOf(module) + "02CREBY")), rs.getString(String.valueOf(module) + "02CRETIME"), Handler.ifIsNull(rs.getString(String.valueOf(module) + "02TYPE")), Handler.ifIsNull(rs.getString(String.valueOf(module) + "02IDXBY")), rs.getString(String.valueOf(module) + "02IDXDATE"), rs.getString(String.valueOf(module) + "02IDXTIME"));
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

    public static BO_Record getBufferCTMATM(String controlNoInput, String module) {
        String query = "SELECT DISTINCT " + module + "04MATNO, " + module + "04STATUS, " + module + "04CREDATE, " + module + "04CREBY, " + module + "04CRETIME," + module + "04MODIBY, " + module + "04MODIDATE, " + module + "04MODITIME, " + module + "04TYPE, " + module + "04MARC FROM " + module + "WORK " + "WHERE " + module + "04MATNO = '" + controlNoInput + "'";
        BO_Record newSE01_SearchByMatno = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_SearchByMatno = new BO_Record(rs.getString(String.valueOf(module) + "04MATNO"), rs.getString(String.valueOf(module) + "04STATUS"), rs.getString(String.valueOf(module) + "04CREDATE"), Handler.ifIsNull(rs.getString(String.valueOf(module) + "04CREBY")), rs.getString(String.valueOf(module) + "04CRETIME"), rs.getString(String.valueOf(module) + "04STATUS"), Handler.ifIsNull(rs.getString(String.valueOf(module) + "04MODIBY")), rs.getString(String.valueOf(module) + "04MODIDATE"), rs.getString(String.valueOf(module) + "04MODITIME"), rs.getString(String.valueOf(module) + "04TYPE"), rs.getString(String.valueOf(module) + "04MARC"));
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

    public static List<BO_Record> getRetrievedData(String controlNoInput, String marctype, String module) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "select gl17tabname, GL17DESC, GL17TAG, " + module + "06pointer, " + module + "02T006, " + module + "02T007, " + module + "02T008, " + module + "02NORESV, GL17REPEAT from GLTAGP, " + module + "pont, " + module + "matm where gl17tag=" + module + "06TAG " + "and " + module + "02matno=" + module + "06matno and GL17MARC = '" + marctype + "' and  " + module + "06MATNO = '" + controlNoInput + "' order by gl17tag";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String tablename = rs.getString("gl17tabname");
                    String tag = rs.getString("GL17TAG");
                    String query1 = "Select " + module + "06MATNO, " + module + "06TAG, " + module + "06INDI1, " + module + "06INDI2, CT05SRAW, CT05RAW, " + module + "02T006, " + module + "02T007, " + module + "02T008 " + "from " + module + "pont, " + tablename + ", " + module + "matm " + "where " + module + "02matno=" + module + "06matno and " + module + "06POINTER = ct05pointer and " + module + "06POINTER = " + Integer.parseInt(rs.getString(module + "06pointer")) + " and CT06MARC = '" + marctype + "' and  " + module + "06MATNO = '" + controlNoInput + "' and " + module + "06TAG='" + tag + "' order by " + module + "06tag";
                    System.out.println(query1);
                    Statement stmt1 = c.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(query1);
                    if (!rs1.next()) continue;
                    String data = "";
                    data = rs1.getString("CT05RAW") != null ? String.valueOf(rs1.getString("CT05SRAW")) + rs1.getString("CT05RAW") : rs1.getString("CT05SRAW");
                    BO_Record newSE01_SearchByMatno = new BO_Record(rs1.getString(String.valueOf(module) + "06MATNO"), rs1.getString(String.valueOf(module) + "06TAG"), Handler.convertIndi(rs1.getString(String.valueOf(module) + "06INDI1")), Handler.convertIndi(rs1.getString(String.valueOf(module) + "06INDI2")), data, rs.getString("GL17DESC"), rs.getString(String.valueOf(module) + "02T006"), rs.getString(String.valueOf(module) + "02T007"), rs.getString(String.valueOf(module) + "02T008"), Integer.parseInt(rs.getString(String.valueOf(module) + "02NORESV")), rs.getString("GL17REPEAT"));
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

    public static boolean matno_exist(String matno, String module) {
        String query = "SELECT COUNT(*) AS MATNO FROM " + module + "WORK WHERE " + module + "04MATNO='" + matno + "'";
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

    public static String getType(int tplID) {
        String query = " Select CT15TYPE from CTTMSTR where CT15SEQNO='" + tplID + "' and CT15MARC='U'";
        System.out.println(query);
        String authlink = "";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    authlink = rs.getString("CT15TYPE");
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

    public static List<BO_Record> getBOList(int tplID, String marc) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "SELECT CT16SEQNO,GL17MANDA,CT16TAG," + Handler.ifIsNull("CT16INDI1") + ", CT16INDI2, CT16SUBF, GL17DESC, " + "CT16MSTR, GL17AUTFLAG, GL17REPEAT " + "FROM CTTPL, GLTAGP " + "WHERE CT16TAG=GL17TAG AND GL17MARC='" + marc + "' AND CT16MSTR=" + tplID + " ORDER BY CT16TAG";
        System.out.println("BO_List" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record getBOList = new BO_Record(Integer.parseInt(rs.getString("CT16SEQNO")), rs.getString("CT16TAG"), rs.getString("GL17DESC"), Handler.convertIndi(rs.getString("CT16INDI1")), Handler.convertIndi(rs.getString("CT16INDI2")), rs.getString("CT16SUBF"), rs.getString("GL17MANDA"), Integer.parseInt(rs.getString("CT16MSTR")), rs.getString("GL17AUTFLAG"), rs.getString("GL17REPEAT"));
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

    public static List<BO_Record> getCTWORK(String matno, String tableName) {
        ArrayList<BO_Record> list = new ArrayList<BO_Record>();
        String query = "Select " + tableName + "04MATNO," + tableName + "04MARC, " + tableName + "04TAG, " + tableName + "04INDI1," + tableName + "04INDI2," + tableName + "04RAW, " + tableName + "04STATUS, " + "GL17DESC, GL17TABNAME, GL17MANDA, " + tableName + "04CTRLTAG, GL17REPEAT, GL17TAG " + "FROM " + tableName + "WORK, GLTAGP " + "where " + tableName + "04TAG=GL17TAG AND " + tableName + "04MARC=GL17MARC AND " + tableName + "04MATNO='" + matno + "' order by " + tableName + "04tag";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    BO_Record searchTable = new BO_Record(rs.getString(String.valueOf(tableName) + "04MATNO"), rs.getString(String.valueOf(tableName) + "04TAG"), Handler.convertIndi(rs.getString(String.valueOf(tableName) + "04INDI1")), Handler.convertIndi(rs.getString(String.valueOf(tableName) + "04INDI2")), rs.getString(String.valueOf(tableName) + "04RAW"), rs.getString(String.valueOf(tableName) + "04STATUS"), rs.getString("GL17DESC"), rs.getString("GL17TABNAME"), rs.getString(String.valueOf(tableName) + "04CTRLTAG"), rs.getString("GL17MANDA"), rs.getString("GL17REPEAT"), rs.getString("GL17TAG"));
                    list.add(searchTable);
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

    public static String storeIntoMemory(String tag, String indi1, String indi2, String data) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("Tag", tag);
        valueStr.put("Indi1", indi1);
        valueStr.put("Indi2", indi2);
        valueStr.put("Data", data);
        String query = QueryBuilder.createString(valueStr);
        return query;
    }
}
