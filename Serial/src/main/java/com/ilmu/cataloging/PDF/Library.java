/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.global.Handler;
import com.ilmu.global.MalayNumberToWords;
import com.ilmu.global.connection.DBConnection;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Library {
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
    private String branch;
    private String brncadd1;
    private String brncadd2;
    private String brncadd3;
    private String brncpostcode;
    private String brnctown;
    private String CI03PATR;
    private String CI03DOCNO;
    private String CI03MATNO;
    private String CI03BRNC;
    private String CI03OFFIC;
    private String GL14IPADD;
    private String CI03RDATE;
    private String CI03RTIME;
    private String PERINC;
    private String desgination;
    private String tolOrder;
    private String tolQty;
    private String forex;
    private String orderForeign;
    private String inword;
    private String vendcode;
    private String vendAcc;
    private String vendBank;
    private String contno;
    private String desig;
    private static Map<String, String> columnName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("name", "GL28NAME");
            this.put("address1", "GL28ADD1");
            this.put("address2", "GL28ADD2");
            this.put("address3", "GL28ADD3");
            this.put("poscode", "GL28POSCODE");
            this.put("town", "(F2.DESCRIPTION)");
            this.put("tel", "GL28FAX");
            this.put("fax", "GL28TEL");
            this.put("organizationName", "GL28ORGNAME");
            this.put("acqClaim1", "GL38ACQCLAIMS1");
            this.put("acqClaim2", "GL38ACQCLAIM2");
            this.put("acqClaim3", "GL38ACQCLAIM3");
            this.put("patrName", "GL14NAME");
            this.put("patrCourse", "GL14COURSE");
            this.put("patrAdd1", "GL14ADD1");
            this.put("patrAdd2", "GL14ADD3");
            this.put("patrAdd3", "GL14ADD3");
            this.put("patrCode", "GL14CODE");
            this.put("patrTown", "GL14TOWN");
            this.put("patrHtel", "GL14HTEL");
            this.put("patrOfftel", "GL14OTEL");
            this.put("officerName", "GL14NAME");
            this.put("officerDesign", "DESCRIPTION");
            this.put("collDays", "GL28MSGDELAY");
        }
    };

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

    public String getCI03RDATE() {
        return this.CI03RDATE;
    }

    public String getCI03RTIME() {
        return this.CI03RTIME;
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

    public String getPERINC() {
        return this.PERINC;
    }

    public String getdesgination() {
        return this.desgination;
    }

    public String gettolOrder() {
        return this.tolOrder;
    }

    public String gettolQty() {
        return this.tolQty;
    }

    public String getforex() {
        return this.forex;
    }

    public String getorderForeign() {
        return this.orderForeign;
    }

    public String getinword() {
        return this.inword;
    }

    public String getvendcode() {
        return this.vendcode;
    }

    public String getvendAcc() {
        return this.vendAcc;
    }

    public String getvendBank() {
        return this.vendBank;
    }

    public String getcontno() {
        return this.contno;
    }

    public String getdesig() {
        return this.desig;
    }

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

    private Library(String CI03RDATE, String CI03RTIME) {
        this.CI03RDATE = CI03RDATE;
        this.CI03RTIME = CI03RTIME;
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

    private Library(String vendcode, String name, String address1, String address2, String address3, String poscode, String tel, String fax, String PERINC, String vendAcc, String vendBank, String desig, String contno) {
        this.vendcode = vendcode;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.poscode = poscode;
        this.tel = tel;
        this.fax = fax;
        this.PERINC = PERINC;
        this.vendAcc = vendAcc;
        this.vendBank = vendBank;
        this.desig = desig;
        this.contno = contno;
    }

    private Library(String name) {
        this.name = name;
    }

    private Library(String patrId, String name, String desgination) {
        this.patrId = patrId;
        this.name = name;
        this.desgination = desgination;
    }

    private Library(String tolOrder2, String inword, String tolQty2, String forex, String orderForeign2) {
        this.tolOrder = tolOrder2;
        this.inword = inword;
        this.tolQty = tolQty2;
        this.forex = forex;
        this.orderForeign = orderForeign2;
    }

    private Library(String name, String address1, String address2, String address3, String poscode, String tel, String fax, String PERINC, String vendAcc, String vendBank, String desig, String contno) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.poscode = poscode;
        this.tel = tel;
        this.fax = fax;
        this.PERINC = PERINC;
        this.vendAcc = vendAcc;
        this.vendBank = vendBank;
        this.desig = desig;
        this.contno = contno;
    }

    public static Library getContactsOnly() {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' ";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' WHERE ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' LIMIT 1";
        }
        System.out.println("getContactsOnly :" + sql);
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("TOWNDESC"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getString("GL14NAME"), rs.getString("DESGDESC"), rs.getString("GL28MSGDELAY"), rs.getString("GL71DESC"), rs.getString("GL71ADD1"), rs.getString("GL71ADD2"), rs.getString("GL71ADD3"), rs.getString("GL71POSCODE"), rs.getString("GL71TOWN"));
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

    public static int getOrderSpan() throws SQLException {
        boolean hrs = false;
        String sSQLStmt = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        int days = 0;
        try {
            try {
                DBConnection dbtype = new DBConnection();
                sSQLStmt = dbtype.getDBType().equals("mssql") ? "SELECT TOP 1 GL28ORDERSPAN FROM GLLIBR" : (dbtype.getDBType().equals("mysql") ? "SELECT GL28ORDERSPAN FROM GLLIBR LIMIT 1" : "SELECT GL28ORDERSPAN FROM GLLIBR rownum=1");
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sSQLStmt);
                if (rs.next()) {
                    days = rs.getInt("GL28ORDERSPAN");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                statement.close();
                connection.close();
            }
        }
        finally {
            rs.close();
            statement.close();
            connection.close();
        }
        return days;
    }

    public static Library ReceiverCancel(String CI03DOCNO) {
        Library library = null;
        String sql = "SELECT * FROM GLPATR  WHERE GL14PATR='" + CI03DOCNO + "'";
        System.out.println(sql);
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14COURSE"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), rs.getString("GL14HTEL"), rs.getString("GL14OTEL"), rs.getString("GL14IPADD"));
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

    public static String getComputerName() {
        System.out.println("Hostname1asasa");
        String hostname = "Unknown";
        try {
            System.out.println("Hostname1");
            System.out.println("Hostname2");
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("Hostname3");
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex) {
            System.out.println("Hostname can not be resolved");
        }
        System.out.println("Hostname" + hostname);
        return hostname;
    }

    public static Library getVendorDetails2(String orderNo) {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC,GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN SEORDD ON SE03SUPPLIER = GL39CODE WHERE SE03ORDER IN (" + orderNo + ")";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC,GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN SEORDD ON SE03SUPPLIER = GL39CODE WHERE SE03ORDER IN (" + orderNo + ") AND ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC,GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN SEORDD ON SE03SUPPLIER = GL39CODE WHERE SE03ORDER IN (" + orderNo + ") LIMIT 1";
        }
        System.out.println("getVendorDetails2" + sql);
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("gggggggg" + rs.getString("GL39NAME"));
                    library = new Library(Handler.ifIsNull(rs.getString("GL39CODE")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL39ADD1")), Handler.ifIsNull(rs.getString("GL39ADD2")), Handler.ifIsNull(rs.getString("GL39ADD3")), Handler.ifIsNull(rs.getString("GL39PCODE")), Handler.ifIsNull(rs.getString("GL39TELNO")), Handler.ifIsNull(rs.getString("GL39FAX")), Handler.ifIsNull(rs.getString("GL39PERINC")), Handler.ifIsNull(rs.getString("GL39ACCNO")), Handler.ifIsNull(rs.getString("GL39BANK")), Handler.ifIsNull(rs.getString("GL39DESIG")), Handler.ifIsNull(rs.getString("GL39CONTNO")));
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

    public static Library lettersubject(String letterID) {
        Library library = null;
        String sql = "SELECT GL52DISSUBJ FROM GLLETR WHERE GL52LETTERID ='" + letterID + "'";
        System.out.println("lettersubject" + sql);
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

    public static Library loginDetail(String patrid) {
        Library library = null;
        String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE UPPER(GL14PATR)= UPPER('" + patrid + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println("loginDetail" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("loginDetailvvvvvvvvvvvv" + Handler.ifIsNull(rs.getString("GL14PATR")));
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

    public static Library hodDetail() {
        Library library;
        block13: {
            library = null;
            String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN GLLIBR ON GL14PATR = GL28SERHEAD LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=GL28SERHEAD";
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            System.out.println("hodDetail" + sql);
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    System.out.println("111111111111111111");
                    if (!rs.next()) {
                        System.out.println("ResultSet in empty in Java");
                        String patr = "";
                        String name = "";
                        String desc = "";
                        library = new Library(patr, name, desc);
                        break block13;
                    }
                    do {
                        System.out.println("fddddddddd");
                        library = new Library(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")));
                    } while (rs.next());
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
        }
        return library;
    }

    public static Library ReadOrderSpan() {
        Library library = null;
        String sql = "SELECT GL28ORDERSPAN FROM GLLIBR";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println("ReadOrderSpan" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL28ORDERSPAN")));
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

    public static Library staffDetail(String order) {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN SEORDD ON GL14PATR = AC03OFFID LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=AC03OFFID AND AC03ORDER IN (" + order + ")";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN SEORDD ON GL14PATR = AC03OFFID LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=AC03OFFID AND AC03ORDER IN (" + order + ") AND ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION FROM GLPATR LEFT JOIN SEORDD ON GL14PATR = AC03OFFID LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' WHERE GL14PATR=AC03OFFID AND AC03ORDER IN (" + order + ") LIMIT 1";
        }
        System.out.println("staffDetail" + sql);
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

    public static Library batchrequestor(String orderno) {
        Library library;
        block18: {
            library = null;
            DBConnection dbtype = new DBConnection();
            String sql = "";
            if (dbtype.getDBType().equals("mssql")) {
                sql = "SELECT TOP 1 SE02REQUESTOR FROM SEREQC WHERE SE02ORDER IN (" + orderno + ")";
            }
            if (dbtype.getDBType().equals("oracle")) {
                sql = "SELECT SE02REQUESTOR FROM SEREQC WHERE SE02ORDER IN (" + orderno + ") AND ROWNUM=1";
            }
            if (dbtype.getDBType().equals("mysql")) {
                sql = "SELECT SE02REQUESTOR FROM SEREQC WHERE SE02ORDER IN (" + orderno + ") LIMIT 1";
            }
            System.out.println("batchrequestor" + sql);
            Connection connection = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                try {
                    connection = DBConnection.getConnection();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(sql);
                    if (!rs.next()) {
                        System.out.println("ResultSet in empty in Java");
                        String patr = "";
                        library = new Library(patr);
                        break block18;
                    }
                    do {
                        System.out.println("fddddddddd");
                        library = new Library(Handler.ifIsNull(rs.getString("SE02REQUESTOR")));
                    } while (rs.next());
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
        }
        return library;
    }

    public static Library allTotal(String order) {
        Library library = null;
        String sql = "SELECT SE03FOREX, SE03COPIES, SE03FPRICE, SE03LPRICE, SE03PRATE FROM SEORDD WHERE SE03ORDER IN (" + order + ")";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println("allTotal" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                String forex = "";
                float tolOrder = 0.0f;
                boolean tolQty = false;
                float orderForeign = 0.0f;
                String totalimword = "";
                BigDecimal tolOrder2 = BigDecimal.ZERO;
                BigDecimal tolQty2 = BigDecimal.ZERO;
                BigDecimal orderForeign2 = BigDecimal.ZERO;
                boolean count = false;
                while (rs.next()) {
                    forex = Handler.ifIsNull(rs.getString("SE03FOREX"));
                    BigDecimal iQuantity2 = new BigDecimal(rs.getString("SE03COPIES"));
                    BigDecimal cForeignUnitPrice = new BigDecimal(rs.getString("SE03FPRICE"));
                    BigDecimal cLocalUnitPrice = new BigDecimal(rs.getString("SE03LPRICE"));
                    System.out.println("cLocalUnitPrice" + cLocalUnitPrice);
                    BigDecimal multiplytotalOrder = BigDecimal.ZERO;
                    multiplytotalOrder = cLocalUnitPrice.multiply(iQuantity2);
                    tolOrder2 = tolOrder2.add(multiplytotalOrder);
                    tolQty2 = tolQty2.add(iQuantity2);
                    orderForeign2 = cForeignUnitPrice.multiply(cForeignUnitPrice);
                    System.out.println("tolOrdertolOrder" + tolOrder2);
                    BigDecimal num = tolOrder2;
                    num.setScale(2, 4);
                    BigDecimal result = num.subtract(new BigDecimal(num.longValue()));
                    System.out.println((int)(result.doubleValue() * 100.0));
                    System.out.println(num.intValue());
                    totalimword = (int)(result.doubleValue() * 100.0) == 0 ? "RM " + MalayNumberToWords.convert(num.intValue()) + " SAHAJA" : "RM " + MalayNumberToWords.convert(num.intValue()) + " DAN SEN " + MalayNumberToWords.convert((int)(result.doubleValue() * 100.0)) + " SAHAJA";
                }
                library = new Library(String.format("%.2f", tolOrder2), totalimword, tolQty2.toString(), forex, String.format("%.2f", orderForeign2));
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

    public static Library getVendorDetails3(String code) {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC,GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND WHERE UPPER(GL39CODE) = UPPER('" + code + "')";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC, GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND WHERE UPPER(GL39CODE) = UPPER('" + code + "') AND ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC, GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN ACORDD ON AC03VEND = GL39CODE WHERE UPPER(GL39CODE) = UPPER('" + code + "') LIMIT 1";
        }
        System.out.println("getVendorDetails3" + sql);
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("gggggggg" + rs.getString("GL39NAME"));
                    library = new Library(Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL39ADD1")), Handler.ifIsNull(rs.getString("GL39ADD2")), Handler.ifIsNull(rs.getString("GL39ADD3")), Handler.ifIsNull(rs.getString("GL39PCODE")), Handler.ifIsNull(rs.getString("GL39TELNO")), Handler.ifIsNull(rs.getString("GL39FAX")), Handler.ifIsNull(rs.getString("GL39PERINC")), Handler.ifIsNull(rs.getString("GL39ACCNO")), Handler.ifIsNull(rs.getString("GL39BANK")), Handler.ifIsNull(rs.getString("GL39DESIG")), Handler.ifIsNull(rs.getString("GL39CONTNO")));
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
}
