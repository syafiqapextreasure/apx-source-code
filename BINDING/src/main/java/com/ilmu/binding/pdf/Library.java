/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.pdf;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private String GL14IPADD;
    private String PERINC;
    private String desgination;
    private String tolOrder;
    private String tolQty;
    private String forex;
    private String orderForeign;
    private String vendAcc;
    private String vendBank;
    private String contno;
    private String desig;
    private String dept;
    private String bindno;
    private String title;
    private String dtsent;
    private String dtexpec;
    private String code;
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

    public String getGL14IPADD() {
        return this.GL14IPADD;
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

    public String getdept() {
        return this.dept;
    }

    public String gettitle() {
        return this.title;
    }

    public String getdtsent() {
        return this.dtsent;
    }

    public String getdtexpec() {
        return this.dtexpec;
    }

    public String getbindno() {
        return this.bindno;
    }

    public String getcode() {
        return this.code;
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

    private Library(String code, String name, String address1, String address2, String address3, String poscode, String tel, String fax, String PERINC, String vendAcc, String vendBank, String desig, String contno) {
        this.code = code;
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

    private Library(String patrId, String name, String desgination, String address1, String address2, String address3) {
        this.patrId = patrId;
        this.name = name;
        this.desgination = desgination;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
    }

    private Library(String patrId, String name, String dept) {
        this.patrId = patrId;
        this.name = name;
        this.dept = dept;
    }

    private Library(String bindno, String title, String dtsent, String dtexpec) {
        this.bindno = bindno;
        this.title = title;
        this.dtsent = dtsent;
        this.dtexpec = dtexpec;
    }

    public static Library getContactsOnly() {
        Library library = null;
        DBConnection dbtype = new DBConnection();
        String sql = "";
        if (dbtype.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' ";
        }
        if (dbtype.getDBType().equals("oracle")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' WHERE ROWNUM=1";
        }
        if (dbtype.getDBType().equals("mysql")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY, GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28SERHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' LIMIT 1";
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
                    library = new Library(Handler.ifIsNull(rs.getString("GL28NAME")), Handler.ifIsNull(rs.getString("GL28ADD1")), Handler.ifIsNull(rs.getString("GL28ADD2")), Handler.ifIsNull(rs.getString("GL28ADD3")), Handler.ifIsNull(rs.getString("GL28POSCODE")), Handler.ifIsNull(rs.getString("TOWNDESC")), Handler.ifIsNull(rs.getString("GL28FAX")), Handler.ifIsNull(rs.getString("GL28TEL")), Handler.ifIsNull(rs.getString("GL28ORGNAME")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESGDESC")), Handler.ifIsNull(rs.getString("GL28MSGDELAY")), Handler.ifIsNull(rs.getString("GL71DESC")), Handler.ifIsNull(rs.getString("GL71ADD1")), Handler.ifIsNull(rs.getString("GL71ADD2")), Handler.ifIsNull(rs.getString("GL71ADD3")), Handler.ifIsNull(rs.getString("GL71POSCODE")), Handler.ifIsNull(rs.getString("GL71TOWN")));
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

    public static Library getVendorDetails2(String bindno) {
        Library library;
        block17: {
            library = null;
            DBConnection dbtype = new DBConnection();
            String sql = "";
            if (dbtype.getDBType().equals("mssql")) {
                sql = "SELECT TOP 1 GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC,GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN BIMAST ON BI01BINDER = GL39CODE WHERE BI01BINDNO IN (" + bindno + ")";
            }
            if (dbtype.getDBType().equals("oracle")) {
                sql = "SELECT GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC, GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN BIMAST ON BI01BINDER = GL39CODE WHERE BI01BINDNO IN (" + bindno + ") AND ROWNUM=1";
            }
            if (dbtype.getDBType().equals("mysql")) {
                sql = "SELECT GL39CODE, GL39NAME, GL39ADD1, GL39ADD2, GL39ADD3, GL39PCODE, GL39TELNO, GL39FAX, GL39PERINC, GL39ACCNO,GL39BANK, GL39DESIG, GL39CONTNO FROM GLVEND LEFT JOIN BIMAST ON BI01BINDER = GL39CODE WHERE BI01BINDNO IN (" + bindno + ") LIMIT 1";
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
                    if (!rs.next()) {
                        library = new Library("", "", "", "", "", "", "", "", "", "", "", "", "");
                        break block17;
                    }
                    library = new Library(Handler.ifIsNull(rs.getString("GL39CODE")), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(rs.getString("GL39ADD1")), Handler.ifIsNull(rs.getString("GL39ADD2")), Handler.ifIsNull(rs.getString("GL39ADD3")), Handler.ifIsNull(rs.getString("GL39PCODE")), Handler.ifIsNull(rs.getString("GL39TELNO")), Handler.ifIsNull(rs.getString("GL39FAX")), Handler.ifIsNull(rs.getString("GL39PERINC")), Handler.ifIsNull(rs.getString("GL39ACCNO")), Handler.ifIsNull(rs.getString("GL39BANK")), Handler.ifIsNull(rs.getString("GL39DESIG")), Handler.ifIsNull(rs.getString("GL39CONTNO")));
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

    public static List<Library> getBindClaimDetail(String bindno) {
        ArrayList<Library> list = new ArrayList<Library>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT BI01BINDNO, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, BI01DTSENT, BI01DTEXPECTED FROM BIMAST WHERE BI01BINDNO IN (" + bindno + ")";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT BI01BINDNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, BI01DTSENT, BI01DTEXPECTED FROM BIMAST WHERE BI01BINDNO IN (" + bindno + ") ";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT BI01BINDNO, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' LIMIT 1) AS TITLE, BI01DTSENT, BI01DTEXPECTED FROM BIMAST WHERE BI01BINDNO IN (" + bindno + ") ";
        }
        System.out.println("query getBindClaimDetail : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Library loadtabledetail = new Library(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))));
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
}
