/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String name;
    private String address1;
    private String address2;
    private String address3;
    private String poscode;
    private String town;
    private String tel;
    private String fax;
    private String orgName;
    private int acqClaim1;
    private int acqClaim2;
    private int acqClaim3;
    private String patrId;
    private String patrName;
    private String patrCourse;
    private String patrAdd1;
    private String patrAdd2;
    private String patrAdd3;
    private String patrCode;
    private String patrTown;
    private String patrHtel;
    private String patrOfftel;
    private String officerName;
    private String officerDesign;
    private String collDays;
    private String CI03PATR;
    private String CI03DOCNO;
    private String CI03MATNO;
    private String CI03BRNC;
    private String CI03OFFIC;
    private String GL14IPADD;
    private String GL39NAME;
    private String GL39ADD1;
    private String GL39ADD2;
    private String GL39ADD3;
    private String GL39PCODE;
    private String GL39TELNO;
    private String GL39FAX;
    private String PERINC;
    private String ORDER;
    private String INVNO;
    private String DESCRIPTION;
    private String branch;
    private String brncadd1;
    private String brncadd2;
    private String brncadd3;
    private String brncpostcode;
    private String brnctown;
    private static Map<String, String> columnName = new HashMap(){
        private static final long serialVersionUID = 1L;
    };

    private Library(String name, String address1, String address2, String address3, String poscode, String town, String tel, String fax, String orgName, int acqClaim1, int acqClaim2, int acqClaim3) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.poscode = poscode;
        this.town = town;
        this.tel = tel;
        this.fax = fax;
        this.orgName = orgName;
        this.acqClaim1 = acqClaim1;
        this.acqClaim2 = acqClaim2;
        this.acqClaim3 = acqClaim3;
    }

    private Library(String name, String address1, String address2, String address3, String poscode, String town, String tel, String fax, String orgName, String officerName, String officerDesign, String collDays) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.poscode = poscode;
        this.town = town;
        this.tel = tel;
        this.fax = fax;
        this.orgName = orgName;
        this.officerName = officerName;
        this.officerDesign = officerDesign;
        this.collDays = collDays;
    }

    private Library(String CI03PATR, String CI03MATNO, String CI03DOCNO, String CI03OFFIC, String CI03BRNC) {
        this.CI03PATR = CI03PATR;
        this.CI03MATNO = CI03MATNO;
        this.CI03DOCNO = CI03DOCNO;
        this.CI03OFFIC = CI03OFFIC;
        this.CI03BRNC = CI03BRNC;
    }

    private Library(int acqClaim1, int acqClaim2, int acqClaim3) {
        this.acqClaim1 = acqClaim1;
        this.acqClaim2 = acqClaim2;
        this.acqClaim3 = acqClaim3;
    }

    private Library(String patrId, String patrName, String patrCourse, String patrAdd1, String patrAdd2, String patrAdd3, String patrCode, String patrTown, String partHtel, String offTel, String GL14IPADD) {
        this.patrId = patrId;
        this.patrName = patrName;
        this.patrCourse = patrCourse;
        this.patrAdd1 = patrAdd1;
        this.patrAdd2 = patrAdd2;
        this.patrAdd3 = patrAdd3;
        this.patrCode = patrCode;
        this.patrTown = patrTown;
        this.patrHtel = partHtel;
        this.patrOfftel = offTel;
        this.GL14IPADD = GL14IPADD;
    }

    private Library(String GL39NAME, String GL39ADD1, String GL39ADD2, String GL39ADD3, String GL39PCODE, String GL39TELNO, String GL39FAX, String PERINC) {
        this.GL39NAME = GL39NAME;
        this.GL39ADD1 = GL39ADD1;
        this.GL39ADD2 = GL39ADD2;
        this.GL39ADD3 = GL39ADD3;
        this.GL39PCODE = GL39PCODE;
        this.GL39TELNO = GL39TELNO;
        this.GL39FAX = GL39FAX;
        this.PERINC = PERINC;
    }

    private Library(String ORDER, String INVNO, String DESCRIPTION) {
        this.ORDER = ORDER;
        this.INVNO = INVNO;
        this.DESCRIPTION = DESCRIPTION;
    }

    private Library(String name, String address1, String address2, String address3, String poscode, String town, String tel, String fax, String orgName, String officerName, String officerDesign, String collDays, String branch, String brncadd1, String brncadd2, String brncadd3, String brncpostcode, String brnctown) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.poscode = poscode;
        this.town = town;
        this.tel = tel;
        this.fax = fax;
        this.orgName = orgName;
        this.officerName = officerName;
        this.officerDesign = officerDesign;
        this.collDays = collDays;
        this.branch = branch;
        this.brncadd1 = brncadd1;
        this.brncadd2 = brncadd2;
        this.brncadd3 = brncadd3;
        this.brncpostcode = brncpostcode;
        this.brnctown = brnctown;
    }

    private Library(String name) {
        this.name = name;
    }

    public String getGL39NAME() {
        return this.GL39NAME;
    }

    public String getGL39ADD1() {
        return this.GL39ADD1;
    }

    public String getGL39ADD2() {
        return this.GL39ADD2;
    }

    public String getGL39ADD3() {
        return this.GL39ADD3;
    }

    public String getGL39PCODE() {
        return this.GL39PCODE;
    }

    public String getGL39TELNO() {
        return this.GL39TELNO;
    }

    public String getGL39FAX() {
        return this.GL39FAX;
    }

    public String getPatrId() {
        return this.patrId;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress1() {
        return this.address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public String getAddress3() {
        return this.address3;
    }

    public String getPoscode() {
        return this.poscode;
    }

    public String getTown() {
        return this.town;
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

    public int getAcqClaim1() {
        return this.acqClaim1;
    }

    public int getAcqClaim2() {
        return this.acqClaim2;
    }

    public int getAcqClaim3() {
        return this.acqClaim3;
    }

    public String getPatrName() {
        return this.patrName;
    }

    public String getPatrCourse() {
        return this.patrCourse;
    }

    public String getPatrAdd1() {
        return this.patrAdd1;
    }

    public String getPatrAdd2() {
        return this.patrAdd2;
    }

    public String getPatrAdd3() {
        return this.patrAdd3;
    }

    public String getPatrCode() {
        return this.patrCode;
    }

    public String getPatrTown() {
        return this.patrTown;
    }

    public String getPatrHtel() {
        return this.patrHtel;
    }

    public String getPatrOtel() {
        return this.patrOfftel;
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

    public String getCI03PATR() {
        return this.CI03PATR;
    }

    public String CI03MATNO() {
        return this.CI03MATNO;
    }

    public String CI03DOCNO() {
        return this.CI03DOCNO;
    }

    public String CI03OFFIC() {
        return this.CI03OFFIC;
    }

    public String CI03BRNC() {
        return this.CI03BRNC;
    }

    public String getGL14IPADD() {
        return this.GL14IPADD;
    }

    public String getPERINC() {
        return this.PERINC;
    }

    public String getORDER() {
        return this.ORDER;
    }

    public String getINVNO() {
        return this.INVNO;
    }

    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    public String getbranch() {
        return this.branch;
    }

    public String getbrncadd1() {
        return this.brncadd1;
    }

    public String getbrncadd2() {
        return this.brncadd2;
    }

    public String getbrncadd3() {
        return this.brncadd3;
    }

    public String getbrncpostcode() {
        return this.brncpostcode;
    }

    public String getbrnctown() {
        return this.brnctown;
    }

    public static Library Receiver(String CI03MATNO) {
        Library library = null;
        String sql = "SELECT * FROM GLPATR, CIRESV  WHERE CI03PATR=GL14PATR AND CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='A'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14COURSE"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), rs.getString("GL14HTEL"), rs.getString("GL14OTEL"), rs.getString("GL14IPADD"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static Library Sender(String CI03MATNO) {
        Library library = null;
        String sql = "SELECT * FROM GLPATR, CIRESV  WHERE CI03OFFIC=GL14PATR AND CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='X'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14BRNC"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), rs.getString("GL14HTEL"), rs.getString("GL14OTEL"), rs.getString("GL14IPADD"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static String matno(String CT03DOCNO) {
        String sql = "SELECT * FROM CTDOCM WHERE CT03DOCNO='" + CT03DOCNO + "'";
        String CT03MATNO = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    CT03MATNO = rs.getString("CT03MATNO");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CT03MATNO;
    }

    public static boolean CIRESVExist(String CI03MATNO) {
        String query = "SELECT COUNT(*) AS COUNT FROM CIRESV WHERE CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='X'";
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("COUNT")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
        return exist;
    }

    public static Library chkResv(String accession) {
        Library library = null;
        String sql = "SELECT * FROM CIRESV  WHERE CI03DOCNO='" + accession + "' AND CI03NSTAT='X'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("CI03PATR"), rs.getString("CI03MATNO"), rs.getString("CI03DOCNO"), rs.getString("CI03OFFIC"), rs.getString("CI03BRNC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static Library getFull() throws SQLException {
        Library library = null;
        Connection conn = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (DBConnection.getDBType().equals("mssql")) {
            System.out.println("sql here");
            sql = "SELECT TOP 1 * FROM GLLIBR";
        } else if (DBConnection.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            sql = "SELECT * FROM GLLIBR WHERE rownum = 1";
        } else if (DBConnection.getDBType().equals("mysql")) {
            System.out.println("mysql here");
            sql = "SELECT * FROM GLLIBR LIMIT 1";
        }
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("GL28TOWN"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getInt("GL2ACQCLAIMS1"), rs.getInt("GL28ACQCLAIM2"), rs.getInt("GL28ACQCLAIM3"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
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
        if (DBConnection.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' ";
        }
        if (DBConnection.getDBType().equals("oracle")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' WHERE ROWNUM=1";
        }
        if (DBConnection.getDBType().equals("mysql")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' LIMIT 1";
        }
        System.out.println("getContactsOnly :" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("TOWNDESC"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getString("GL14NAME"), rs.getString("DESGDESC"), rs.getString("GL28MSGDELAY"), rs.getString("GL71DESC"), rs.getString("GL71ADD1"), rs.getString("GL71ADD2"), rs.getString("GL71ADD3"), rs.getString("GL71POSCODE"), rs.getString("GL71TOWN"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static Library getVendorDetails(String vendor) {
        Library library = null;
        String sql = "SELECT * FROM GLVEND WHERE GL39CODE='" + vendor + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL39NAME"), rs.getString("GL39ADD1"), rs.getString("GL39ADD2"), rs.getString("GL39ADD3"), rs.getString("GL39PCODE"), rs.getString("GL39TELNO"), rs.getString("GL39FAX"), rs.getString("GL39PERINC"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static Library getClaimsOnly() {
        Library library = null;
        String sql = "SELECT TOP 1 * FROM GLLIBR";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getInt("GL2ACQCLAIMS1"), rs.getInt("GL28ACQCLAIM2"), rs.getInt("GL28ACQCLAIM3"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static void updateCIRESV(String CI03DOCNO, String CI03MATNO) {
        String query = "UPDATE CIRESV SET CI03NSTAT='A', CI03NDATE='" + DateTime.displayToDBFormat(DateTime.getTodayDate()) + "', " + "CI03DDATE='" + DateTime.displayToDBFormat(DateTime.getTodayDate()) + "', " + "CI03DOCNO='" + CI03DOCNO + "' WHERE CI03MATNO = '" + CI03MATNO + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
    }

    public static String getVendorCode(String OrderNo) {
        String sql = "SELECT AC03VEND FROM ACORDD WHERE AC03ORDER = '" + OrderNo + "'";
        System.out.println("SQL getVendorCode : " + sql);
        String msGetVendorCode = "";
        try {
            try {
                Statement stmt1 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                ResultSet rs = stmt1.executeQuery(sql);
                while (rs.next()) {
                    msGetVendorCode = rs.getString("AC03VEND");
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
        return msGetVendorCode;
    }

    public static Library getVendorDetails2(String OrderNo) {
        Library library = null;
        String sql = "SELECT * FROM GLVEND WHERE GL39CODE='" + Library.getVendorCode(OrderNo) + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL39ADD1")), Handler.ifIsNull(rs.getString("GL39ADD2")), Handler.ifIsNull(rs.getString("GL39ADD3")), Handler.ifIsNull(rs.getString("GL39PCODE")), Handler.ifIsNull(rs.getString("GL39TELNO")), Handler.ifIsNull(rs.getString("GL39FAX")), Handler.ifIsNull(rs.getString("GL39PERINC")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static String getMAXDATEEIS(String OrderNo) {
        String sql = "SELECT MAX(AC08TRXDATE) AS 'MaxDate' FROM ACEISX WHERE AC08ORDER IN (" + OrderNo + ") GROUP BY AC08ORDER";
        System.out.println("SQL getMAXDATEEIS : " + sql);
        String msGetMaxDate = "";
        try {
            try {
                Statement stmt1 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                ResultSet rs = stmt1.executeQuery(sql);
                while (rs.next()) {
                    msGetMaxDate = rs.getString("MaxDate");
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
        return msGetMaxDate;
    }

    public static List<Library> getLISTEIS(String OrderNo, String date) {
        ArrayList<Library> list = new ArrayList<Library>();
        String sql = "SELECT AC08ORDER, AC03INVNO, DESCRIPTION FROM ACEISX LEFT JOIN ACORDD ON AC03ORDER=AC08ORDER LEFT JOIN FNDCODE ON CODE=AC08REASON AND FCODE = 'F' WHERE AC08ORDER IN(" + OrderNo + ") AND  AC08TRXDATE = '" + date + "'";
        System.out.println("sql getLISTEIS = " + sql);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Library eis = new Library(rs.getString("AC08ORDER"), Handler.ifIsNull(rs.getString("AC03INVNO")), rs.getString("DESCRIPTION"));
                    list.add(eis);
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

    public static Library getVendorDetailsBYCODE(String vend) {
        Library library = null;
        String sql = "SELECT * FROM GLVEND WHERE UPPER(GL39CODE) = UPPER('" + vend + "')";
        System.out.println("SQL VENDOR BY NAME" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL39ADD1")), Handler.ifIsNull(rs.getString("GL39ADD2")), Handler.ifIsNull(rs.getString("GL39ADD3")), Handler.ifIsNull(rs.getString("GL39PCODE")), Handler.ifIsNull(rs.getString("GL39TELNO")), Handler.ifIsNull(rs.getString("GL39FAX")), Handler.ifIsNull(rs.getString("GL39PERINC")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }

    public static Library lettersubject(String letterID) {
        Library library = null;
        String sql = "SELECT GL52DISSUBJ FROM GLLETR WHERE GL52LETTERID ='" + letterID + "'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL52DISSUBJ"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                stmt.close();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return library;
    }
}
