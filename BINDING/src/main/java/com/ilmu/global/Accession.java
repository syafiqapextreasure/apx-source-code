/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Accession {
    private String ctrlno;
    private String title;
    private String issn;
    private String callno;
    private String binder;
    private String bindName;

    public String getctrlno() {
        return this.ctrlno;
    }

    public String gettitle() {
        return this.title;
    }

    public String getissn() {
        return this.issn;
    }

    public String getcallno() {
        return this.callno;
    }

    public String getbinder() {
        return this.binder;
    }

    public String getbindName() {
        return this.bindName;
    }

    public Accession(String ctrlno, String title, String callno, String issn) {
        this.ctrlno = ctrlno;
        this.title = title;
        this.callno = callno;
        this.issn = issn;
    }

    public Accession(String ctrlno, String title, String callno, String issn, String binder, String bindName) {
        this.ctrlno = ctrlno;
        this.title = title;
        this.callno = callno;
        this.issn = issn;
        this.binder = binder;
        this.bindName = bindName;
    }

    public static List<Accession> getAccsionDetail(String accno) {
        ArrayList<Accession> list = new ArrayList<Accession>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        try {
            try {
                if (dbtype.getDBType().equals("mssql")) {
                    System.out.println("sql here");
                    query = "SELECT CT03MATNO, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245') AS TITLE, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '020') AS ISBNISSN FROM CTDOCM WHERE CT03STAT = 'A' AND CT03DOCNO = '" + accno + "'";
                } else if (dbtype.getDBType().equals("oracle")) {
                    System.out.println("oracle here");
                    query = "SELECT CT03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '020' AND rownum = 1) AS ISBNISSN FROM CTDOCM WHERE CT03STAT = 'A' AND CT03DOCNO = '" + accno + "'";
                } else if (dbtype.getDBType().equals("mysql")) {
                    System.out.println("MYSQL here");
                    query = "SELECT CT03MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = CT03MATNO AND CT06TAG = '020' LIMIT 1) AS ISBNISSN FROM CTDOCM WHERE CT03STAT = 'A' AND CT03DOCNO = '" + accno + "'";
                }
                System.out.println("getAccsionDetail" + query);
                Statement stmt1 = null;
                ResultSet rs = null;
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                List<ISBD> isbn = ISBD.getPunctuation("020");
                List<ISBD> call = ISBD.getPunctuation("090");
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Accession accDetail = new Accession(Handler.ifIsNull(rs.getString("CT03MATNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("ISBNISSN")), isbn));
                    list.add(accDetail);
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

    public static List<Accession> getCtrlNoDetailBySerial(String ctrlno) {
        ArrayList<Accession> list = new ArrayList<Accession>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        try {
            try {
                if (dbtype.getDBType().equals("mssql")) {
                    System.out.println("sql here");
                    query = "SELECT SE01MATNO, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '245') AS TITLE, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '090') AS CALLNUMBER, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '022') AS ISBNISSN,SE01BINDER, GL39NAME FROM SESERM LEFT JOIN  GLVEND ON GL39CODE = SE01BINDER WHERE SE01MATNO = '" + ctrlno + "'";
                } else if (dbtype.getDBType().equals("oracle")) {
                    System.out.println("oracle here");
                    query = "SELECT SE01MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '090' AND rownum = 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '022' AND rownum = 1) AS ISBNISSN,SE01BINDER, GL39NAME FROM SESERM LEFT JOIN  GLVEND ON GL39CODE = SE01BINDER WHERE SE01MATNO = '" + ctrlno + "'";
                } else if (dbtype.getDBType().equals("mysql")) {
                    System.out.println("MYSQL here");
                    query = "SELECT SE01MATNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '245' LIMIT 1) AS TITLE, (SELECT CT05SRAW FROM CTPONT, CTCALL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '090' LIMIT 1) AS CALLNUMBER, (SELECT CT05SRAW FROM CTPONT, CTINDX WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = SE01MATNO AND CT06TAG = '022' LIMIT 1) AS ISBNISSN,SE01BINDER, GL39NAME FROM SESERM LEFT JOIN  GLVEND ON GL39CODE = SE01BINDER WHERE SE01MATNO = '" + ctrlno + "'";
                }
                System.out.println("getCtrlNoDetailBySerial" + query);
                Statement stmt1 = null;
                ResultSet rs = null;
                stmt1 = conn.createStatement();
                rs = stmt1.executeQuery(query);
                List<ISBD> isbn = ISBD.getPunctuation("020");
                List<ISBD> call = ISBD.getPunctuation("090");
                List<ISBD> title = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Accession accDetail = new Accession(Handler.ifIsNull(rs.getString("SE01MATNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), title), Handler.getSubfield(Handler.ifIsNull(rs.getString("CALLNUMBER")), call), Handler.getSubfield(Handler.ifIsNull(rs.getString("ISBNISSN")), isbn), Handler.ifIsNull(rs.getString("SE01BINDER")), Handler.ifIsNull(rs.getString("GL39NAME")));
                    list.add(accDetail);
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
