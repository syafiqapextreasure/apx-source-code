/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.pdf;

import com.ilmu.global.Encrypter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Library {
    private String sLetterSubject;
    private String sRequestor;
    private String sControlNo;
    private String sRequestNo;
    private String sRequestDate;
    private String sCurrentRawDate;
    private String sCurrentRawTime;
    private String sCurrentDate;
    private String sCurrentTime;
    private String sLetterID;
    private String sLibraryName;
    private String sLibraryAdd1;
    private String sLibraryAdd2;
    private String sLibraryAdd3;
    private String sLibraryTown;
    private String sLibraryPostcode;
    private String sBranchName;
    private String sBranchAdd1;
    private String sBranchAdd2;
    private String sBranchAdd3;
    private String sBranchTown;
    private String sBranchPostcode;
    private String dtNow;
    private String dtCurrent;
    private String dtDue;
    private String sDueDate;
    private String sDueTime;
    private String sAccessionNo;
    private String sDocStatus;
    private String sDocItemCat;
    private String sSMD;
    private String sLocation;
    private String sDocLastBorrowedPatron;
    private String sDocDueDate;
    private String sPeriodType;
    private String iPeriodOfLoan;
    private String iEligibility;
    private String sAllowOverdue;
    private String sDeptHead;
    private String sSender;
    private String tel;
    private String fax;
    private String orgName;
    private String officerName;
    private String officerDesign;
    private String collDays;
    private String patrId;
    private String name;
    private String desgination;
    private String add1;
    private String add2;
    private String add3;

    public String getsLetterSubject() {
        return Handler.ifIsNull(this.sLetterSubject);
    }

    public String getsRequestor() {
        return Handler.ifIsNull(this.sRequestor);
    }

    public String getsControlNo() {
        return Handler.ifIsNull(this.sControlNo);
    }

    public String getsRequestNo() {
        return Handler.ifIsNull(this.sRequestNo);
    }

    public String getsRequestDate() {
        return Handler.ifIsNull(this.sRequestDate);
    }

    public String getsCurrentRawDate() {
        return Handler.ifIsNull(this.sCurrentRawDate);
    }

    public String getsCurrentRawTime() {
        return Handler.ifIsNull(this.sCurrentRawTime);
    }

    public String getsCurrentDate() {
        return Handler.ifIsNull(this.sCurrentDate);
    }

    public String getsCurrentTime() {
        return Handler.ifIsNull(this.sCurrentTime);
    }

    public String getsLetterID() {
        return Handler.ifIsNull(this.sLetterID);
    }

    public String getsLibraryName() {
        return Handler.ifIsNull(this.sLibraryName);
    }

    public String getsLibraryAdd1() {
        return Handler.ifIsNull(this.sLibraryAdd1);
    }

    public String getsLibraryAdd2() {
        return Handler.ifIsNull(this.sLibraryAdd2);
    }

    public String getsLibraryAdd3() {
        return Handler.ifIsNull(this.sLibraryAdd3);
    }

    public String getssLibraryTown() {
        return Handler.ifIsNull(this.sLibraryTown);
    }

    public String getsLibraryPostcode() {
        return Handler.ifIsNull(this.sLibraryPostcode);
    }

    public String getsBranchName() {
        return Handler.ifIsNull(this.sBranchName);
    }

    public String getsBranchAdd1() {
        return Handler.ifIsNull(this.sBranchAdd1);
    }

    public String getsBranchAdd2() {
        return Handler.ifIsNull(this.sBranchAdd2);
    }

    public String getsBranchAdd3() {
        return Handler.ifIsNull(this.sBranchAdd3);
    }

    public String getssBranchTown() {
        return Handler.ifIsNull(this.sBranchTown);
    }

    public String getssBranchPostcode() {
        return Handler.ifIsNull(this.sBranchPostcode);
    }

    public String getdtNow() {
        return Handler.ifIsNull(this.dtNow);
    }

    public String getdtCurrent() {
        return Handler.ifIsNull(this.dtCurrent);
    }

    public String getdtDue() {
        return Handler.ifIsNull(this.dtDue);
    }

    public String getsDueDate() {
        return Handler.ifIsNull(this.sDueDate);
    }

    public String getsDueTime() {
        return Handler.ifIsNull(this.sDueTime);
    }

    public String getsAccessionNo() {
        return Handler.ifIsNull(this.sAccessionNo);
    }

    public String getsDocStatus() {
        return Handler.ifIsNull(this.sDocStatus);
    }

    public String getsDocItemCat() {
        return Handler.ifIsNull(this.sDocItemCat);
    }

    public String getsSMD() {
        return Handler.ifIsNull(this.sSMD);
    }

    public String getsLocation() {
        return Handler.ifIsNull(this.sLocation);
    }

    public String getsDocLastBorrowedPatron() {
        return Handler.ifIsNull(this.sDocLastBorrowedPatron);
    }

    public String getsDocDueDate() {
        return Handler.ifIsNull(this.sDocDueDate);
    }

    public String getssPeriodType() {
        return Handler.ifIsNull(this.sPeriodType);
    }

    public String getiPeriodOfLoan() {
        return Handler.ifIsNull(this.iPeriodOfLoan);
    }

    public String getiEligibility() {
        return Handler.ifIsNull(this.iEligibility);
    }

    public String getsAllowOverdue() {
        return Handler.ifIsNull(this.sAllowOverdue);
    }

    public String getsDeptHead() {
        return Handler.ifIsNull(this.sDeptHead);
    }

    public String getssSender() {
        return Handler.ifIsNull(this.sSender);
    }

    public String getTel() {
        return this.tel;
    }

    public String getFax() {
        return this.fax;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public String getOfficerName() {
        return this.officerName;
    }

    public String getOfficerDesign() {
        return this.officerDesign;
    }

    public String getCollDays() {
        return this.collDays;
    }

    public String getPatrId() {
        return this.patrId;
    }

    public String getName() {
        return this.name;
    }

    public String getdesgination() {
        return this.desgination;
    }

    public String getadd1() {
        return Handler.ifIsNull(this.add1);
    }

    public String getadd2() {
        return Handler.ifIsNull(this.add2);
    }

    public String getadd3() {
        return Handler.ifIsNull(this.add3);
    }

    private Library(String sLetterSubject) {
        this.sLetterSubject = sLetterSubject;
    }

    private Library(String sLibraryName, String sLibraryAdd1, String sLibraryAdd2, String sLibraryAdd3, String sLibraryPostcode, String sLibraryTown, String fax, String tel, String orgName, String officerName, String officerDesign, String collDays, String sBranchName, String sBranchAdd1, String sBranchAdd2, String sBranchAdd3, String sBranchPostcode, String sBranchTown) {
        this.sLibraryName = sLibraryName;
        this.sLibraryAdd1 = sLibraryAdd1;
        this.sLibraryAdd2 = sLibraryAdd2;
        this.sLibraryAdd3 = sLibraryAdd3;
        this.sLibraryPostcode = sLibraryPostcode;
        this.sLibraryTown = sLibraryTown;
        this.fax = fax;
        this.tel = tel;
        this.orgName = orgName;
        this.officerName = officerName;
        this.officerDesign = officerDesign;
        this.collDays = collDays;
        this.sBranchName = sBranchName;
        this.sBranchAdd1 = sBranchAdd1;
        this.sBranchAdd2 = sBranchAdd2;
        this.sBranchAdd3 = sBranchAdd3;
        this.sBranchPostcode = sBranchPostcode;
        this.sBranchTown = sBranchTown;
    }

    private Library(String patrId, String name, String desgination) {
        this.patrId = patrId;
        this.name = name;
        this.desgination = desgination;
    }

    private Library(String patrId, String name, String add1, String add2, String add3) {
        this.patrId = patrId;
        this.name = name;
        this.add1 = add1;
        this.add2 = add2;
        this.add3 = add3;
    }

    public static String getComputerName() {
        String hostname = "Unknown";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException unknownHostException) {
            // empty catch block
        }
        return hostname;
    }

    public static Library lettersubject(String letterID) {
        Library library = null;
        String sql = "SELECT GL52DISSUBJ FROM GLLETR WHERE UPPER(GL52LETTERID) = UPPER('" + letterID + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL52DISSUBJ"));
                }
            }
            catch (SQLException e) {
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
        return library;
    }

    public static Library getContactsOnly() {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' ";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' WHERE ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' LIMIT 1";
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    String sLibName = Handler.ifIsNull(rs.getString("GL28NAME"));
                    String getLibName = Encrypter.encrypt(sLibName);
                    library = new Library(getLibName, rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("TOWNDESC"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getString("GL14NAME"), rs.getString("DESGDESC"), rs.getString("GL28MSGDELAY"), rs.getString("GL71DESC"), rs.getString("GL71ADD1"), rs.getString("GL71ADD2"), rs.getString("GL71ADD3"), rs.getString("GL71POSCODE"), rs.getString("GL71TOWN"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return library;
    }

    public static Library hodDetail() throws SQLException {
        Library library = null;
        String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN GLLIBR ON GL14PATR = GL28CIRHEAD LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=GL28CIRHEAD";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
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
        return library;
    }

    public static Library loginDetail(String patrid) {
        Library library = null;
        String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE UPPER(GL14PATR)= UPPER('" + patrid + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return library;
    }

    public void checkAccession(String sAccessionNo, String txtMaterial, String patrID) {
        String sTitle = Library.GetTagValue(txtMaterial, "245", "No");
        String sAuthor = Library.GetTagValue(txtMaterial, "100", "No");
        String sPublisher = Library.GetTagValue(txtMaterial, "260", "No");
        String sVolume = Library.GetVolume(sAccessionNo, txtMaterial);
        String sSMD = Library.GetSMD(sAccessionNo, txtMaterial);
        sAccessionNo.equals(null);
    }

    public static String GetSMD(String sAccessionNo, String txtMaterial) {
        String sSQLStmt = "SELECT GL47DESC FROM GLSMD,CTDOCM WHERE GL47SMD = CT03SMD AND CT03DOCNO = '" + sAccessionNo + "' " + "AND CT03MATNO = '" + txtMaterial + "'";
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL47DESC"));
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
        return value;
    }

    public static String GetVolume(String sAccessionNo, String txtMaterial) {
        String sSQLStmt = "SELECT CT03VOLUME FROM CTDOCM WHERE CT03DOCNO = '" + sAccessionNo + "' " + "AND CT03MATNO = '" + txtMaterial + "'";
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("CT03VOLUME"));
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
        return value;
    }

    public static String GetTagValue(String vsControlNo, String vsTag, String vsSingleISBN) {
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        String sTable = Library.getMarkTagTableName(vsTag);
        String[] tempArray = sTable.split("=");
        String sModule = tempArray[2].trim().substring(0, 2);
        sTable = tempArray[2].trim();
        if (!sTable.equals("")) {
            if (vsSingleISBN.equals("YES")) {
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

    public static Library GetPatronDetails(String patrid) {
        Library library = null;
        String sql = "SELECT GL14PATR, GL14NAME, GL14ADD1, GL14ADD2, GL14ADD3, GL14DESC, GL14NAMETITLE, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14MAILFLAG, GL14STAT, GL14CATE, GL14RELID, GL14RMVD  FROM GLPATR WHERE GL14PATR='" + patrid + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    String flag = Handler.ifIsNull(rs.getString("GL14MAILFLAG"));
                    String rsPatronAdd1 = null;
                    String rsPatronAdd2 = null;
                    String rsPatronAdd3 = null;
                    String rsPatronId = Handler.ifIsNull(rs.getString("GL14PATR"));
                    String rsPatronName = Handler.ifIsNull(rs.getString("GL14NAME"));
                    if (flag.equals("R")) {
                        rsPatronAdd1 = Handler.ifIsNull(rs.getString("GL14ADD1"));
                        rsPatronAdd2 = Handler.ifIsNull(rs.getString("GL14ADD2"));
                        rsPatronAdd3 = Handler.ifIsNull(rs.getString("GL14ADD3"));
                    } else if (flag.equals("O")) {
                        rsPatronAdd1 = Handler.ifIsNull(rs.getString("GL14OFFADD1"));
                        rsPatronAdd2 = Handler.ifIsNull(rs.getString("GL14OFFADD2"));
                        rsPatronAdd3 = Handler.ifIsNull(rs.getString("GL14OFFADD3"));
                    }
                    library = new Library(rsPatronId, rsPatronName, rsPatronAdd1, rsPatronAdd2, rsPatronAdd3);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return library;
    }

    public static Library RetrieveAccessionDetails(String accession) {
        Library library = null;
        String sql = "Select CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA, CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD From CTDOCM Where CT03DOCNO = '" + accession + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    String rsRetStatus = Handler.ifIsNull(rs.getString("CT03STAT"));
                    String rsRetItemCat = Handler.ifIsNull(rs.getString("CT03ICAT"));
                    String rsRetSMD = Handler.ifIsNull(rs.getString("CT03SMD"));
                    String rsRetLocation = Handler.ifIsNull(rs.getString("CT03LOCA"));
                    String rsRetPatron = Handler.ifIsNull(rs.getString("CT03PATR"));
                    String string = Handler.ifIsNull(rs.getString("CT03DDATE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
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
        return library;
    }

    public static String GetDeptOfficer() {
        String sSQLStmt = "SELECT GL28CIRHEAD FROM GLLIBR";
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL28CIRHEAD"));
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
        return value;
    }

    public static String GetPatronName(String id) {
        String sSQLStmt = " SELECT GL14NAME FROM GLPATR WHERE UPPER(GL14PATR)= UPPER('" + id + "')";
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL14NAME"));
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
        return value;
    }

    public static String GetDesignation(String id) {
        String sSQLStmt = " SELECT GL14DESC FROM GLPATR WHERE UPPER(GL14PATR)= UPPER('" + id + "')";
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL14DESC"));
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
        return value;
    }
}
