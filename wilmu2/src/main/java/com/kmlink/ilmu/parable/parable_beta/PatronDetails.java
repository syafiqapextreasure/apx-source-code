/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.DateTime;
import com.kmlink.ilmu.parable.parable_beta.GeneralUtil;
import com.kmlink.ilmu.parable.parable_beta.Handler;
import com.kmlink.ilmu.parable.parable_beta.ISBD;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatronDetails {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String PatrValue = null;

    public PatronDetails(String PatrValue) {
        this.PatrValue = PatrValue;
    }

    public String getPatrValue() {
        return this.PatrValue;
    }

    public static List<String> PatDetails(List<String> tblName, String patrID) {
        System.out.println("tblName: " + tblName + "_______patrID: " + patrID);
        ArrayList<String> list = new ArrayList<String>();
        String query = null;
        PrintStream out = System.out;
        String column = tblName.toString().replaceAll("\\[|\\]", "");
        query = "Select " + column + " " + "FROM GLPATR LEFT JOIN GLCATE ON (GL14CATE = GL07CATE) " + "LEFT JOIN GLDEPT ON (GL14DEPT = GL11DEPT) " + "LEFT JOIN GLCOUR ON (GL14COURSE = GL12COURSE) " + "LEFT JOIN GLTOWN ON (GL14TOWN = GL15TOWN) " + "LEFT JOIN GLBRNC ON (GL14BRNC = GL07CATE) WHERE GL14PATR='" + patrID + "'";
        Object sheet = null;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String newLine = System.getProperty("line.separator");
                while (rs.next()) {
                    String row = "";
                    int i = 1;
                    while (i <= tblName.size()) {
                        if (i > 1) {
                            row = String.valueOf(row) + newLine;
                        }
                        if ((row = rs.getString(i)) == null || row == "") {
                            row = "";
                        }
                        list.add(row);
                        ++i;
                    }
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

    public static List<String> loadAccession(List<String> tblName, String accNo) {
        ArrayList<String> list = new ArrayList<String>();
        String query = null;
        PrintStream out = System.out;
        tblName = tblName.stream().filter(x -> x != null).collect(Collectors.toList());
        String column = tblName.toString().replaceAll("\\[|\\]", "");
        System.out.println("column: " + column);
        query = "Select " + column + " FROM CTDOCM CTDOCM " + "LEFT JOIN CTPONT D1 ON (CTDOCM.CT03MATNO = D1.CT06MATNO AND D1.CT06TAG = '090') " + "LEFT JOIN CTCALL CTCALL ON (D1.CT06POINTER = CTCALL.CT05POINTER) " + "LEFT JOIN CTPONT D2 ON (CTDOCM.CT03MATNO = D2.CT06MATNO AND D2.CT06TAG = '245') " + "LEFT JOIN CTTITL CTTITL ON (D2.CT06POINTER = CTTITL.CT05POINTER) " + "LEFT JOIN GLICAT GLICAT ON (CTDOCM.CT03ICAT =GLICAT.GL10ICAT) " + "LEFT JOIN GLLOCA GLLOCA ON (CTDOCM.CT03LOCA =GLLOCA.GL05LOCA) " + "LEFT JOIN GLSMD GLSMD ON (CTDOCM.CT03SMD =GLSMD.GL47SMD) ";
        if (GeneralUtil.printAcq()) {
            query = String.valueOf(query) + "LEFT JOIN ACORDD ACORDD ON (CTDOCM.CT03MATNO=ACORDD.AC03CTRLNO) LEFT JOIN GLBRNC GLBRNC ON (GLLOCA.GL05BRNC = GLBRNC.GL71BRNC)";
        }
        query = String.valueOf(query) + "WHERE CTDOCM.CT03DOCNO = '" + accNo + "'";
        Object sheet = null;
        System.out.println("query: " + query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                String newLine = System.getProperty("line.separator");
                while (rs.next()) {
                    String row = "";
                    int i = 1;
                    while (i <= tblName.size()) {
                        if (i > 1) {
                            row = String.valueOf(row) + newLine;
                        }
                        System.out.println("err" + tblName.get(i - 1));
                        if (tblName.get(i - 1).equals("CTTITL.CT05SRAW as CTTITL_CT05SRAW")) {
                            if (rs.getString("CTTITL_CT05SRAW") == null) {
                                row = Handler.ifIsNull(rs.getString("CTTITL_CT05SRAW"));
                            } else {
                                List<ISBD> isbd1 = ISBD.getPunctuation("245");
                                row = Handler.getSubfield(rs.getString("CTTITL_CT05SRAW"), isbd1);
                            }
                        } else if (tblName.get(i - 1).equals("CTCALL.CT05SRAW as CTCALL_CT05SRAW")) {
                            if (rs.getString("CTCALL_CT05SRAW") == null) {
                                row = Handler.ifIsNull(rs.getString("CTCALL_CT05SRAW"));
                            } else {
                                List<ISBD> isbd2 = ISBD.getPunctuation("090");
                                row = Handler.getSubfield(rs.getString("CTCALL_CT05SRAW"), isbd2);
                            }
                            System.out.println(String.valueOf(row) + "row");
                        } else if (tblName.get(i - 1).equals("CTDOCM.CT03CRDATE as CTDOCM_CT03CRDATE")) {
                            System.out.println("Date" + rs.getString("CTDOCM_CT03CRDATE"));
                            row = rs.getString("CTDOCM_CT03CRDATE") == null ? Handler.ifIsNull(rs.getString("CTDOCM_CT03CRDATE")) : DateTime.formatDate(rs.getString("CTDOCM_CT03CRDATE"));
                        } else if (tblName.get(i - 1).equals("CTDOCM.CT03LCOST as CTDOCM_CT03LCOST")) {
                            System.out.println("Date" + rs.getString("CTDOCM_CT03LCOST"));
                            row = rs.getString("CTDOCM_CT03LCOST") == null ? Handler.ifIsNull(rs.getString("CTDOCM_CT03LCOST")) : Handler.decimalConversion(rs.getString("CTDOCM_CT03LCOST"));
                        } else {
                            row = tblName.get(i - 1).equals("ACORDD.AC03VEND as ACORDD_AC03VEND") ? (rs.getString("ACORDD_AC03VEND") == null ? Handler.ifIsNull(rs.getString("ACORDD_AC03VEND")) : "Supp.: " + rs.getString("ACORDD_AC03VEND")) : (tblName.get(i - 1).equals("ACORDD.AC03ORDER as ACORDD_AC03ORDER") ? (rs.getString("ACORDD_AC03ORDER") == null ? Handler.ifIsNull(rs.getString("ACORDD_AC03ORDER")) : "Ord. No: " + rs.getString("ACORDD_AC03ORDER")) : rs.getString(i));
                        }
                        if (row == null || row == "") {
                            row = "";
                        }
                        list.add(row);
                        int n = 3;
                        ++i;
                    }
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
