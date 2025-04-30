/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private String CI03PATR;
    private String CI03DOCNO;
    private String CI03MATNO;
    private String CI03BRNC;
    private String CI03OFFIC;
    private String GL14IPADD;
    private static Map<String, String> columnName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("name", "GL28NAME");
            this.put("address1", "GL28ADD1");
            this.put("address2", "GL28ADD2");
            this.put("address3", "GL28ADD3");
            this.put("poscode", "GL28POSCODE");
            this.put("town", "GL28TOWN");
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
            this.put("officerDesign", "GL69DESC");
            this.put("collDays", "GL28MSGDELAY");
        }
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

    public static Library Receiver(String CI03MATNO) {
        Library library = null;
        String sql = "SELECT * FROM GLPATR, CIRESV  WHERE CI03PATR=GL14PATR AND CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='A'";
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

    public static Library Sender(String CI03MATNO) {
        Library library = null;
        String sql = "SELECT * FROM GLPATR, CIRESV  WHERE CI03OFFIC=GL14PATR AND CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='X'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL14BRNC"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), rs.getString("GL14HTEL"), rs.getString("GL14OTEL"), rs.getString("GL14IPADD"));
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

    public static Library CatalogSender() {
        Library library = null;
        String sql = "select GL14PATR, GL14NAME, gl14course, GL14ADD1, GL14ADD2, GL14ADD3,GL14CODE, GL14TOWN, GL14HTEL, GL14OTEL from GLLIBR, GLPATR  WHERE GL28CATHEAD=GL14PATR";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println(sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                while (rs.next()) {
                    library = new Library(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("gl14course"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), rs.getString("GL14HTEL"), rs.getString("GL14OTEL"), rs.getString("gl14course"));
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

    public static String matno(String CT03DOCNO) {
        String sql = "SELECT * FROM CTDOCM WHERE CT03DOCNO='" + CT03DOCNO + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String CT03MATNO = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    CT03MATNO = rs.getString("CT03MATNO");
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
        return CT03MATNO;
    }

    public static boolean CIRESVExist(String CI03MATNO) {
        String query = "SELECT COUNT(*) AS COUNT FROM CIRESV WHERE CI03MATNO='" + CI03MATNO + "' and CI03NSTAT='X'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println(query);
        boolean exist = false;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("COUNT")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                connection.close();
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
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("CI03PATR"), rs.getString("CI03MATNO"), rs.getString("CI03DOCNO"), rs.getString("CI03OFFIC"), rs.getString("CI03BRNC"));
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

    public static Library getFull() {
        Library library = null;
        String sql = "SELECT TOP 1 * FROM GLLIBR";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("GL28TOWN"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getInt("GL2ACQCLAIMS1"), rs.getInt("GL28ACQCLAIM2"), rs.getInt("GL28ACQCLAIM3"));
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

    public static Library getContactsOnly() {
        Library library = null;
        String sql = "SELECT TOP 1 * FROM GLPATR, GLDESG, GLLIBR WHERE GL28CIRHEAD=GL14PATR and GL14STAT=GL69DESG";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getString("GL28NAME"), rs.getString("GL28ADD1"), rs.getString("GL28ADD2"), rs.getString("GL28ADD3"), rs.getString("GL28POSCODE"), rs.getString("GL28TOWN"), rs.getString("GL28FAX"), rs.getString("GL28TEL"), rs.getString("GL28ORGNAME"), rs.getString("GL14NAME"), rs.getString("GL69DESC"), rs.getString("GL28MSGDELAY"));
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

    public static Library getClaimsOnly() {
        Library library = null;
        String sql = "SELECT TOP 1 * FROM GLLIBR";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(rs.getInt("GL2ACQCLAIMS1"), rs.getInt("GL28ACQCLAIM2"), rs.getInt("GL28ACQCLAIM3"));
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

    public static void updateCIRESV(String CI03DOCNO, String CI03MATNO) {
        Connection connection = null;
        String query = "UPDATE CIRESV SET CI03NSTAT='A', CI03NDATE='" + DateTime.displayToDBFormat(DateTime.getTodayDate()) + "', " + "CI03DDATE='" + DateTime.displayToDBFormat(DateTime.getTodayDate()) + "', " + "CI03DOCNO='" + CI03DOCNO + "' WHERE CI03MATNO = '" + CI03MATNO + "'";
        System.out.println(query);
        try {
            try {
                connection = DBConnection.getConnection();
                PreparedStatement delete = connection.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
