/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssignOrderItems {
    private String NO;
    private String ORDERNO;
    private String ISSN;
    private String TITLE;
    private String QUANTITY;
    private String UNITFOREIGNPRICE;
    private String TOTALFOREIGNPRICE;
    private String UNITLOCALPRICE;
    private String TOTALLOCALPRICE;
    private String UNITCURRENCY;
    private String EXCHANGERATE;
    private String VOLFROM;
    private String ISSFROM;
    private String VOLTO;
    private String ISSTO;
    private String ISSUES;
    private String PUBLISHER;
    private String ANNOTE;
    private String RQTRORDER;
    private String RQTRID;
    private String RQTRNAME;
    private String RQTRDEPT;
    private String DEPTDESC;

    public String getNO() {
        return this.NO;
    }

    public String getORDERNO() {
        return this.ORDERNO;
    }

    public String getISSN() {
        return this.ISSN;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public String getQUANTITY() {
        return this.QUANTITY;
    }

    public String getUNITFOREIGNPRICE() {
        return this.UNITFOREIGNPRICE;
    }

    public String getTOTALFOREIGNPRICE() {
        return this.TOTALFOREIGNPRICE;
    }

    public String getUNITLOCALPRICE() {
        return this.UNITLOCALPRICE;
    }

    public String getTOTALLOCALPRICE() {
        return this.TOTALLOCALPRICE;
    }

    public String getUNITCURRENCY() {
        return this.UNITCURRENCY;
    }

    public String getEXCHANGERATE() {
        return this.EXCHANGERATE;
    }

    public String getVOLFROM() {
        return this.VOLFROM;
    }

    public String getISSFROM() {
        return this.ISSFROM;
    }

    public String getVOLTO() {
        return this.VOLTO;
    }

    public String getISSTO() {
        return this.ISSTO;
    }

    public String getISSUES() {
        return this.ISSUES;
    }

    public String getPUBLISHER() {
        return this.PUBLISHER;
    }

    public String getANNOTE() {
        return this.ANNOTE;
    }

    public String getRQTRORDER() {
        return this.RQTRORDER;
    }

    public String getRQTRID() {
        return this.RQTRID;
    }

    public String getRQTRNAME() {
        return this.RQTRNAME;
    }

    public String getRQTRDEPT() {
        return this.RQTRDEPT;
    }

    public String getDEPTDESC() {
        return this.DEPTDESC;
    }

    public AssignOrderItems(String NO, String ORDERNO, String ISSN, String TITLE, String QUANTITY, String UNITFOREIGNPRICE, String TOTALFOREIGNPRICE, String UNITLOCALPRICE, String TOTALLOCALPRICE, String UNITCURRENCY, String EXCHANGERATE, String VOLFROM, String ISSFROM, String VOLTO, String ISSTO, String ISSUES, String PUBLISHER, String ANNOTE) {
        this.NO = NO;
        this.ORDERNO = ORDERNO;
        this.ISSN = ISSN;
        this.TITLE = TITLE;
        this.QUANTITY = QUANTITY;
        this.UNITFOREIGNPRICE = UNITFOREIGNPRICE;
        this.TOTALFOREIGNPRICE = TOTALFOREIGNPRICE;
        this.UNITLOCALPRICE = UNITLOCALPRICE;
        this.TOTALLOCALPRICE = TOTALLOCALPRICE;
        this.UNITCURRENCY = UNITCURRENCY;
        this.EXCHANGERATE = EXCHANGERATE;
        this.VOLFROM = VOLFROM;
        this.ISSFROM = ISSFROM;
        this.VOLTO = VOLTO;
        this.ISSTO = ISSTO;
        this.ISSUES = ISSUES;
        this.PUBLISHER = PUBLISHER;
        this.ANNOTE = ANNOTE;
    }

    public AssignOrderItems(String RQTRORDER, String RQTRID, String RQTRNAME, String RQTRDEPT, String DEPTDESC) {
        this.RQTRORDER = RQTRORDER;
        this.RQTRID = RQTRID;
        this.RQTRNAME = RQTRNAME;
        this.RQTRDEPT = RQTRDEPT;
        this.DEPTDESC = DEPTDESC;
    }

    public AssignOrderItems(String NO, String ORDERNO, String ISSN, String TITLE, String QUANTITY, String UNITFOREIGNPRICE, String TOTALFOREIGNPRICE, String UNITLOCALPRICE, String TOTALLOCALPRICE, String UNITCURRENCY, String EXCHANGERATE, String VOLFROM, String ISSFROM, String VOLTO, String ISSTO, String ISSUES, String PUBLISHER, String ANNOTE, String RQTRORDER, String RQTRID, String RQTRNAME, String RQTRDEPT, String DEPTDESC) {
        this.NO = NO;
        this.ORDERNO = ORDERNO;
        this.ISSN = ISSN;
        this.TITLE = TITLE;
        this.QUANTITY = QUANTITY;
        this.UNITFOREIGNPRICE = UNITFOREIGNPRICE;
        this.TOTALFOREIGNPRICE = TOTALFOREIGNPRICE;
        this.UNITLOCALPRICE = UNITLOCALPRICE;
        this.TOTALLOCALPRICE = TOTALLOCALPRICE;
        this.UNITCURRENCY = UNITCURRENCY;
        this.EXCHANGERATE = EXCHANGERATE;
        this.VOLFROM = VOLFROM;
        this.ISSFROM = ISSFROM;
        this.VOLTO = VOLTO;
        this.ISSTO = ISSTO;
        this.ISSUES = ISSUES;
        this.PUBLISHER = PUBLISHER;
        this.ANNOTE = ANNOTE;
        this.RQTRORDER = RQTRORDER;
        this.RQTRID = RQTRID;
        this.RQTRNAME = RQTRNAME;
        this.RQTRDEPT = RQTRDEPT;
        this.DEPTDESC = DEPTDESC;
    }

    public static List<AssignOrderItems> orderItems(String orderno) throws SQLException {
        ArrayList<AssignOrderItems> detail = new ArrayList<AssignOrderItems>();
        String sql2 = "SELECT SE03ORDER, SE03MATNO, SE03COPIES, SE03FPRICE, SE03LPRICE, SE03FOREX, SE03PRATE, SE03VOLFROM, SE03ISSFROM, SE03VOLTO, SE03ISSTO, SE03ISSUES FROM SEORDD WHERE SE03ORDER IN (" + orderno + ")";
        System.out.println("orderItems" + sql2);
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rsObj = stmt.executeQuery(sql2);
        float cForeignTotalPrice = 0.0f;
        float cLocalTotalPrice = 0.0f;
        int iQuantity = 0;
        int count = 0;
        try {
            while (rsObj.next()) {
                String vsOrderNumber = Handler.ifIsNull(rsObj.getString("SE03ORDER"));
                String sControlNo = Handler.ifIsNull(rsObj.getString("SE03MATNO"));
                String sISBN = AssignOrderItems.GetTagValue(sControlNo, "020", "No");
                if (sISBN == null) {
                    sISBN = "";
                }
                String sTitle = AssignOrderItems.GetTagValue(sControlNo, "245", "No");
                iQuantity = Integer.parseInt(rsObj.getString("SE03COPIES"));
                String sQuantity = Integer.toString(iQuantity);
                String cForeignUnitPrice = Handler.ifIsNull(Handler.decimalConversion(rsObj.getString("SE03FPRICE")));
                cForeignTotalPrice = Float.valueOf(iQuantity).floatValue() * Float.valueOf(cForeignUnitPrice).floatValue();
                String cLocalUnitPrice = Handler.ifIsNull(Handler.decimalConversion(rsObj.getString("SE03LPRICE")));
                cLocalTotalPrice = Float.valueOf(iQuantity).floatValue() * Float.valueOf(cLocalUnitPrice).floatValue();
                System.out.println("cLocalTotalPrice" + cLocalTotalPrice);
                String sForex = Handler.ifIsNull(rsObj.getString("SE03FOREX"));
                String sExchangeRate = Handler.decimalConversion(rsObj.getString("SE03PRATE"));
                String sVolumeFrom = Handler.ifIsNull(rsObj.getString("SE03VOLFROM"));
                String sIssueFrom = Handler.ifIsNull(rsObj.getString("SE03ISSFROM"));
                String sVolumeTo = Handler.ifIsNull(rsObj.getString("SE03VOLTO"));
                String sIssueTo = Handler.ifIsNull(rsObj.getString("SE03ISSTO"));
                String sIssues = Handler.ifIsNull(rsObj.getString("SE03ISSUES"));
                String sPublisher = AssignOrderItems.GetTagValue(sControlNo, "260", "No");
                String sVendorNote = AssignOrderItems.LoadVendorNote(vsOrderNumber);
                if (sVendorNote == null) {
                    sVendorNote = "";
                }
                AssignOrderItems assignOrderItems = new AssignOrderItems(Integer.toString(++count), vsOrderNumber, sISBN, sTitle, sQuantity, cForeignUnitPrice, String.format("%.2f", Float.valueOf(cForeignTotalPrice)), cLocalUnitPrice, String.format("%.2f", Float.valueOf(cLocalTotalPrice)), sForex, sExchangeRate, sVolumeFrom, sIssueFrom, sVolumeTo, sIssueTo, sIssues, sPublisher, sVendorNote);
                detail.add(assignOrderItems);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static List<AssignOrderItems> AssignRequestorDetail(String orderno) {
        ArrayList<AssignOrderItems> list = new ArrayList<AssignOrderItems>();
        String query = "SELECT SE02ORDER, GL14PATR, GL14NAME, GL14DEPT, GL11NAME FROM GLPATR LEFT JOIN SEREQC ON SE02REQUESTOR = GL14PATR LEFT JOIN GLDEPT ON GL14DEPT = GL11DEPT WHERE SE02ORDER IN (" + orderno + ")";
        System.out.println("query AssignRequestorDetail : " + query);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    AssignOrderItems loadtabledetail = new AssignOrderItems(Handler.ifIsNull(rs.getString("SE02ORDER")), Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("GL14DEPT")), Handler.ifIsNull(rs.getString("GL11NAME")));
                    list.add(loadtabledetail);
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

    public static List<AssignOrderItems> orderItems2(String orderno) throws SQLException {
        ArrayList<AssignOrderItems> detail = new ArrayList<AssignOrderItems>();
        String sql2 = "SELECT SE03ORDER, SE03MATNO, SE03COPIES, SE03FPRICE, SE03LPRICE, SE03FOREX, SE03PRATE, SE03VOLFROM, SE03ISSFROM, SE03VOLTO, SE03ISSTO, SE03ISSUES, SE02ORDER, GL14PATR, GL14NAME, GL14DEPT, GL11NAME FROM SEORDD LEFT JOIN SEREQC ON SE02ORDER = SE03ORDER LEFT JOIN GLPATR ON SE02REQUESTOR = GL14PATR LEFT JOIN GLDEPT ON GL14DEPT = GL11DEPT WHERE SE03ORDER IN (" + orderno + ")";
        System.out.println("orderItems" + sql2);
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rsObj = stmt.executeQuery(sql2);
        float cForeignTotalPrice = 0.0f;
        float cLocalTotalPrice = 0.0f;
        int iQuantity = 0;
        int count = 0;
        try {
            while (rsObj.next()) {
                String sVendorNote;
                String vsOrderNumber = Handler.ifIsNull(rsObj.getString("SE03ORDER"));
                String sControlNo = Handler.ifIsNull(rsObj.getString("SE03MATNO"));
                String sISBN = AssignOrderItems.GetTagValue(sControlNo, "022", "No");
                if (sISBN == null) {
                    sISBN = "";
                }
                String sTitle = AssignOrderItems.GetTagValue(sControlNo, "245", "No");
                iQuantity = Integer.parseInt(rsObj.getString("SE03COPIES"));
                String sQuantity = Integer.toString(iQuantity);
                String cForeignUnitPrice = Handler.ifIsNull(Handler.decimalConversion(rsObj.getString("SE03FPRICE")));
                System.out.println("cForeignUnitPrice" + cForeignUnitPrice);
                cForeignTotalPrice = Float.valueOf(iQuantity).floatValue() * Float.valueOf(cForeignUnitPrice).floatValue();
                System.out.println("cForeignTotalPricecForeignTotalPrice" + cForeignTotalPrice);
                String cLocalUnitPrice = Handler.ifIsNull(Handler.decimalConversion(rsObj.getString("SE03LPRICE")));
                cLocalTotalPrice = Float.valueOf(iQuantity).floatValue() * Float.valueOf(cLocalUnitPrice).floatValue();
                String sForex = Handler.ifIsNull(rsObj.getString("SE03FOREX"));
                String sExchangeRate = Handler.decimalConversion(rsObj.getString("SE03PRATE"));
                String sVolumeFrom = Handler.ifIsNull(rsObj.getString("SE03VOLFROM"));
                String sIssueFrom = Handler.ifIsNull(rsObj.getString("SE03ISSFROM"));
                String sVolumeTo = Handler.ifIsNull(rsObj.getString("SE03VOLTO"));
                String sIssueTo = Handler.ifIsNull(rsObj.getString("SE03ISSTO"));
                String sIssues = Handler.ifIsNull(rsObj.getString("SE03ISSUES"));
                String sPublisher = AssignOrderItems.GetTagValue(sControlNo, "260", "No");
                if (sPublisher == null) {
                    sPublisher = "";
                }
                if ((sVendorNote = AssignOrderItems.LoadVendorNote(vsOrderNumber)) == null) {
                    sVendorNote = "";
                }
                String sOrder = Handler.ifIsNull(rsObj.getString("SE02ORDER"));
                String sRequestor = Handler.ifIsNull(rsObj.getString("GL14PATR"));
                String sReturnName = Handler.ifIsNull(rsObj.getString("GL14NAME"));
                if (sRequestor.equals(" ")) {
                    sReturnName = "*No requestor detail*";
                }
                String sReturnDept = Handler.ifIsNull(rsObj.getString("GL14DEPT"));
                String sReturnDeptDesc = Handler.ifIsNull(rsObj.getString("GL11NAME"));
                AssignOrderItems assignOrderItems = new AssignOrderItems(Integer.toString(++count), vsOrderNumber, sISBN, sTitle, sQuantity, cForeignUnitPrice, String.format("%.2f", Float.valueOf(cForeignTotalPrice)), cLocalUnitPrice, String.format("%.2f", Float.valueOf(cLocalTotalPrice)), sForex, sExchangeRate, sVolumeFrom, sIssueFrom, sVolumeTo, sIssueTo, sIssues, sPublisher, sVendorNote, sOrder, sRequestor, sReturnName, sReturnDept, sReturnDeptDesc);
                detail.add(assignOrderItems);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static String GetTagValue(String vsControlNo, String vsTag, String vsSingleISBN) {
        String sSQLStmt = "";
        DBConnection dbtype = new DBConnection();
        String sTable = AssignOrderItems.getMarkTagTableName(vsTag);
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

    public static String LoadVendorNote(String orderno) {
        String query = "SELECT AN01NOTE FROM ANMESG WHERE (AN01ACAT = 'SE01' AND AN01REFKEY IN (" + orderno + ")) AND AN01PRNTPMS = 'Y'";
        String note = null;
        Connection conn = null;
        System.out.println("LoadVendorNote" + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    note = Handler.ifIsNull(rs.getString("AN01NOTE"));
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
        return note;
    }

    public static String getNoteNo(String vend, String invno, String type) {
        String sSQLStmt = "";
        sSQLStmt = "SELECT * FROM SECDNOTE WHERE SE16VEND = '" + vend + "' " + "AND SE16INVNO = '" + invno + "' " + "AND SE16NOTETYPE = '" + type + "'";
        System.out.println("SQL getNoteNo" + sSQLStmt);
        String getNoteNo = "";
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    getNoteNo = rs.getString("SE16NOTENO");
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
        return getNoteNo;
    }
}
