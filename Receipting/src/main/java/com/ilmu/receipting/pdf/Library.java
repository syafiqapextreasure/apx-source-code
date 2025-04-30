/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.pdf;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private String officerName;
    private String officerDesign;
    private String collDays;
    private String branch;
    private String brncadd1;
    private String brncadd2;
    private String brncadd3;
    private String brncpostcode;
    private String brnctown;
    private String patrId;
    private String desgination;
    private String location;
    private String locDesc;

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

    public String getOfficerName() {
        return this.officerName;
    }

    public String getOfficerDesign() {
        return this.officerDesign;
    }

    public String getCollDays() {
        return this.collDays;
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

    public String getpatrId() {
        return this.patrId;
    }

    public String getdesgination() {
        return this.desgination;
    }

    public String getlocation() {
        return this.location;
    }

    public String getlocDesc() {
        return this.locDesc;
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

    private Library(String patrId, String name, String desgination, String location, String locDesc) {
        this.patrId = patrId;
        this.name = name;
        this.desgination = desgination;
        this.location = location;
        this.locDesc = locDesc;
    }

    public static Library getContactsOnly() {
        Library library = null;
        String dbtype = DBConnection.getDBtype();
        String sql = "";
        if (dbtype.equals("mssql")) {
            sql = "SELECT TOP 1 GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' ";
        }
        if (dbtype.equals("oracle")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' WHERE ROWNUM=1";
        }
        if (dbtype.equals("mysql")) {
            sql = "SELECT GL28NAME, GL28ADD1, GL28ADD2, GL28ADD3, GL28POSCODE, F2.DESCRIPTION AS TOWNDESC, GL28FAX, GL28TEL, GL28ORGNAME, GL14NAME, F1.DESCRIPTION AS DESGDESC, GL28MSGDELAY,  GL71DESC, GL71ADD1, GL71ADD2, GL71ADD3, GL71POSCODE, GL71TOWN FROM GLLIBR LEFT JOIN GLBRNC ON GL71BRNC = GL28BRNC LEFT JOIN GLPATR ON GL28CIRHEAD=GL14PATR LEFT JOIN FNDCODE F1 ON F1.CODE = GL14STAT AND F1.FCODE = 'D' LEFT JOIN FNDCODE F2 ON F2.CODE = GL28TOWN AND F2.FCODE = 'M' LIMIT 1";
        }
        System.out.println("sql:" + sql);
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

    public static Library lettersubject(String letterID) {
        Library library = null;
        String sql = "SELECT GL52DISSUBJ FROM GLLETR WHERE UPPER(GL52LETTERID) =UPPER('" + letterID + "')";
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

    public static Library SenderPatron(String patrid) {
        Library library = null;
        String sql = "SELECT GL14PATR, GL14NAME, DESCRIPTION, GL14BRNC, GL71DESC FROM GLPATR LEFT JOIN FNDCODE ON CODE = GL14DESC AND FCODE = 'D' LEFT JOIN GLBRNC ON GL14BRNC = GL71BRNC WHERE UPPER(GL14PATR)= UPPER('" + patrid + "')";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        System.out.println("SenderPatronSenderPatron" + sql);
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    library = new Library(Handler.ifIsNull(rs.getString("GL14PATR")), Handler.ifIsNull(rs.getString("GL14NAME")), Handler.ifIsNull(rs.getString("DESCRIPTION")), Handler.ifIsNull(rs.getString("GL14BRNC")), Handler.ifIsNull(rs.getString("GL71DESC")));
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
}
