/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.PDF;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class reservationletter {
    private String PATRONNAME = null;
    private String PATRONADD1 = null;
    private String PATRONADD2 = null;
    private String PATRONADD3 = null;
    private String PATRONID = null;
    private String HOMETEL = null;
    private String OFFTEL = null;
    private String POSTCODE = null;
    private String TOWN = null;
    private String TITLE = null;
    private String CALLNO = null;
    private String ISBN = null;
    private String ACCNO = null;
    private String DUEDATE = null;
    private String PICKBRNC = null;
    private String COURSE = null;
    private String COLLECTIONDAYS = null;
    private String STAFFNAME = null;
    private String STAFFDESIGNATION = null;

    public String getPATRONNAME() {
        return Handler.ifIsNull(this.PATRONNAME);
    }

    public String getPATRONADD1() {
        return Handler.ifIsNull(this.PATRONADD1);
    }

    public String getPATRONADD2() {
        return Handler.ifIsNull(this.PATRONADD2);
    }

    public String getPATRONADD3() {
        return Handler.ifIsNull(this.PATRONADD3);
    }

    public String getPATRONID() {
        return Handler.ifIsNull(this.PATRONID);
    }

    public String getHOMETEL() {
        return Handler.ifIsNull(this.HOMETEL);
    }

    public String getOFFTEL() {
        return Handler.ifIsNull(this.OFFTEL);
    }

    public String getPOSTCODE() {
        return Handler.ifIsNull(this.POSTCODE);
    }

    public String getTOWN() {
        return Handler.ifIsNull(this.TOWN);
    }

    public String getTITLE() {
        return Handler.ifIsNull(this.TITLE);
    }

    public String getCALLNO() {
        return Handler.ifIsNull(this.CALLNO);
    }

    public String getISBN() {
        return Handler.ifIsNull(this.ISBN);
    }

    public String getACCNO() {
        return Handler.ifIsNull(this.ACCNO);
    }

    public String getDUEDATE() {
        return Handler.ifIsNull(this.DUEDATE);
    }

    public String getPICKBRNC() {
        return Handler.ifIsNull(this.PICKBRNC);
    }

    public String getCOURSE() {
        return Handler.ifIsNull(this.COURSE);
    }

    public String getCOLLECTIONDAYS() {
        return Handler.ifIsNull(this.COLLECTIONDAYS);
    }

    public String getSTAFFNAME() {
        return Handler.ifIsNull(this.STAFFNAME);
    }

    public String getSTAFFDESIGNATION() {
        return Handler.ifIsNull(this.STAFFDESIGNATION);
    }

    public reservationletter(String PATRONNAME, String PATRONADD1, String PATRONADD2, String PATRONADD3, String PATRONID, String HOMETEL, String OFFTEL, String POSTCODE, String TOWN, String COURSE) {
        this.PATRONNAME = PATRONNAME;
        this.PATRONADD1 = PATRONADD1;
        this.PATRONADD2 = PATRONADD2;
        this.PATRONADD3 = PATRONADD3;
        this.PATRONID = PATRONID;
        this.HOMETEL = HOMETEL;
        this.OFFTEL = OFFTEL;
        this.POSTCODE = POSTCODE;
        this.TOWN = TOWN;
        this.COURSE = COURSE;
    }

    public reservationletter(String TITLE, String CALLNO, String ISBN, String ACCNO, String DUEDATE, String PICKBRNC, String STAFFNAME, String STAFFDESIGNATION) {
        this.TITLE = TITLE;
        this.CALLNO = CALLNO;
        this.ISBN = ISBN;
        this.ACCNO = ACCNO;
        this.DUEDATE = DUEDATE;
        this.PICKBRNC = PICKBRNC;
        this.STAFFNAME = STAFFNAME;
        this.STAFFDESIGNATION = STAFFDESIGNATION;
    }

    public reservationletter(String COLLECTIONDAYS) {
        this.COLLECTIONDAYS = COLLECTIONDAYS;
    }

    public static reservationletter GetPatronDetail(String docno) throws SQLException {
        reservationletter GetPatronDetail;
        block25: {
            GetPatronDetail = null;
            String sql = "SELECT GL14PATR, GL14NAME, GL14DESC, GL14MAILFLAG, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14HTEL, GL14ADD21, GL14ADD22, GL14ADD23, GL14CODE2, GL14TOWN2, GL14HTEL2, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14OFFCODE, GL14OFFTOWN, GL14OTEL, GL12DESC FROM GLPATR LEFT JOIN GLCOUR ON GL14COURSE = GL12COURSE LEFT JOIN CIRESV ON CI03PATR = GL14PATR WHERE CI03DOCNO = '" + docno + "'";
            System.out.println("GetRequestorAddress" + sql);
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        System.out.println("ResultSet in empty in GetPatronDetail");
                        String rsName = "";
                        String rsAdd1 = "";
                        String rsAdd2 = "";
                        String rsAdd3 = "";
                        String rsId = "";
                        String rshomeTel = "";
                        String rsofficeTel = "";
                        String rsCode = "";
                        String rsTown = "";
                        String rsCourse = "";
                        GetPatronDetail = new reservationletter(rsName, rsAdd1, rsAdd2, rsAdd3, rsId, rshomeTel, rsofficeTel, rsCode, rsTown, rsCourse);
                        break block25;
                    }
                    do {
                        String rsTown;
                        String rsCode;
                        String rsofficeTel;
                        String rshomeTel;
                        String rsAdd3;
                        String rsAdd2;
                        String rsAdd1;
                        String mailflag;
                        System.out.println("GetPatronDetail != empty ");
                        String rsName = Handler.ifIsNull(rs.getString("GL14NAME"));
                        String rsId = Handler.ifIsNull(rs.getString("GL14PATR"));
                        String rsCourse = Handler.ifIsNull(rs.getString("GL12DESC"));
                        switch (mailflag = Handler.ifIsNull(rs.getString("GL14MAILFLAG"))) {
                            case "S": {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14ADD21"));
                                rshomeTel = Handler.ifIsNull(rs.getString("GL14HTEL2"));
                                rsofficeTel = Handler.ifIsNull(rs.getString("GL14OTEL"));
                                rsCode = Handler.ifIsNull(rs.getString("GL14CODE2"));
                                rsTown = Handler.ifIsNull(rs.getString("GL14TOWN2"));
                                break;
                            }
                            case "O": {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14OFFADD1"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14OFFADD2"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14OFFADD3"));
                                rshomeTel = Handler.ifIsNull(rs.getString("GL14HTEL2"));
                                rsofficeTel = "";
                                rsCode = Handler.ifIsNull(rs.getString("GL14OFFCODE"));
                                rsTown = Handler.ifIsNull(rs.getString("GL14OFFTOWN"));
                                break;
                            }
                            default: {
                                rsAdd1 = Handler.ifIsNull(rs.getString("GL14ADD1"));
                                rsAdd2 = Handler.ifIsNull(rs.getString("GL14ADD2"));
                                rsAdd3 = Handler.ifIsNull(rs.getString("GL14ADD3"));
                                rsCode = Handler.ifIsNull(rs.getString("GL14CODE"));
                                rshomeTel = Handler.ifIsNull(rs.getString("GL14HTEL"));
                                rsofficeTel = "";
                                rsTown = Handler.ifIsNull(rs.getString("GL14TOWN"));
                            }
                        }
                        GetPatronDetail = new reservationletter(rsName, rsAdd1, rsAdd2, rsAdd3, rsId, rshomeTel, rsofficeTel, rsCode, rsTown, rsCourse);
                    } while (rs.next());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return GetPatronDetail;
    }

    public static reservationletter GetReservstionDetail(String docno) throws SQLException {
        reservationletter GetPatronDetail;
        block18: {
            GetPatronDetail = null;
            String sql = "SELECT CI03MATNO, CI03DOCNO, CI03DDATE, GL71DESC, GL14NAME, DESCRIPTION FROM CIRESV LEFT JOIN GLBRNC ON  GL71BRNC = CI03BRNC LEFT JOIN GLPATR ON CI03OFFIC = GL14PATR LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE CI03DOCNO = '" + docno + "'";
            System.out.println("GetReservstionDetail" + sql);
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        System.out.println("ResultSet in empty in GetReservstionDetail");
                        String sTitle = "";
                        String sCallno = "";
                        String sISBN = "";
                        String sAccno = "";
                        String sDuedate = "";
                        String sPickbranc = "";
                        String sStaffname = "";
                        String sDesignation = "";
                        GetPatronDetail = new reservationletter(sTitle, sCallno, sISBN, sAccno, sDuedate, sPickbranc, sStaffname, sDesignation);
                        break block18;
                    }
                    do {
                        String sISBN;
                        String sCallno;
                        System.out.println("GetPatronDetail != empty ");
                        String sControlNo = Handler.ifIsNull(rs.getString("CI03MATNO"));
                        String sAccno = Handler.ifIsNull(rs.getString("CI03DOCNO"));
                        String sPickbranc = Handler.ifIsNull(rs.getString("GL71DESC"));
                        String sDuedate = Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("CI03DDATE")));
                        String sStaffname = Handler.ifIsNull(rs.getString("GL14NAME"));
                        String sDesignation = Handler.ifIsNull(rs.getString("DESCRIPTION"));
                        String sTitle = reservationletter.GetTagValue(sControlNo, "245", "No");
                        if (sTitle == null) {
                            sTitle = "";
                        }
                        if ((sCallno = reservationletter.GetTagValue(sControlNo, "090", "No")) == null) {
                            sCallno = "";
                        }
                        if ((sISBN = reservationletter.GetTagValue(sControlNo, "020", "No")) == null) {
                            sISBN = "";
                        }
                        GetPatronDetail = new reservationletter(sTitle, sCallno, sISBN, sAccno, sDuedate, sPickbranc, sStaffname, sDesignation);
                    } while (rs.next());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return GetPatronDetail;
    }

    public static String GetTagValue(String vsControlNo, String vsTag, String vsSingleISBN) {
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        String sTable = reservationletter.getMarkTagTableName(vsTag);
        System.out.println("sTablesTablesTable" + sTable);
        String[] tempArray = sTable.split("=");
        String sModule = tempArray[2].trim().substring(0, 2);
        sTable = tempArray[2].trim();
        if (!sTable.equals("")) {
            if (vsSingleISBN.equals("YES")) {
                System.out.println("YESSSSSSS");
                if (dbtype.getDBType().equals("mssql")) {
                    sSQLStmt = "SELECT TOP 1 " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A'";
                }
                if (dbtype.getDBType().equals("oracle")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A' AND ROWNUM=1";
                }
                if (dbtype.getDBType().equals("mysql")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A' LIMIT 1";
                }
            } else if (vsTag.equals("260")) {
                if (dbtype.getDBType().equals("mssql")) {
                    sSQLStmt = "SELECT TOP 1 " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A'";
                }
                if (dbtype.getDBType().equals("oracle")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A' AND ROWNUM=1";
                }
                if (dbtype.getDBType().equals("mysql")) {
                    sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG IN ('260','264') " + "AND  " + sModule + "06STATUS = 'A' LIMIT 1";
                }
            } else {
                sSQLStmt = "SELECT " + sModule + "05POINTER, " + sModule + "05SRAW " + "FROM " + sTable + ", " + sModule + "PONT " + "WHERE " + sModule + "06MATNO = '" + vsControlNo + "' " + "AND  " + sModule + "06POINTER = " + sModule + "05POINTER " + "AND  " + sModule + "06TAG = '" + vsTag + "' " + "AND  " + sModule + "06STATUS = 'A'";
            }
            System.out.println("sSQLStmt GetTagValue" + sSQLStmt);
        }
        String getpoiter = null;
        String getsrow = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                List<ISBD> isbd = ISBD.getPunctuation(vsTag);
                while (rs.next()) {
                    getpoiter = Handler.ifIsNull(rs.getString(String.valueOf(sModule) + "05POINTER"));
                    getsrow = Handler.getSubfield(Handler.ifIsNull(rs.getString(String.valueOf(sModule) + "05SRAW")), isbd);
                    System.out.println("getsrow " + getsrow);
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
        return getsrow;
    }

    public static String getMarkTagTableName(String marcTag) {
        String TABNAME;
        String CATTITLTAG;
        block12: {
            String query = "Select GL99CATTITLTAG, GL17TABNAME From GLFLAG, GLTAGP Where GL17MARC = 'U' And GL17TAG = '" + marcTag + "'";
            CATTITLTAG = null;
            TABNAME = null;
            Connection conn = null;
            System.out.println("getMarkTagTableName query" + query);
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        CATTITLTAG = Handler.ifIsNull(rs.getString("GL99CATTITLTAG"));
                        TABNAME = Handler.ifIsNull(rs.getString("GL17TABNAME"));
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
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "=" + CATTITLTAG + " =" + TABNAME;
    }

    public static reservationletter GetCOLLECTIONDAYS() throws SQLException {
        reservationletter GetCOLLECTIONDAYS;
        block15: {
            GetCOLLECTIONDAYS = null;
            String sql = "SELECT GL28RESVTIME FROM GLLIBR";
            System.out.println("GetCOLLECTIONDAYS" + sql);
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        System.out.println("ResultSet in empty in GetCOLLECTIONDAYS");
                        String rsResvTime = "";
                        GetCOLLECTIONDAYS = new reservationletter(rsResvTime);
                        break block15;
                    }
                    do {
                        System.out.println("GetPatronDetail != empty ");
                        String rsResvTime = Handler.ifIsNull(rs.getString("GL28RESVTIME"));
                        GetCOLLECTIONDAYS = new reservationletter(rsResvTime);
                    } while (rs.next());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                    try {
                        statement.close();
                        connection.close();
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    try {
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
                    statement.close();
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return GetCOLLECTIONDAYS;
    }
}
