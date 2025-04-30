/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.VendorCode;

import com.ilmu.foundation.global.Foundation;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLStatement {
    public Connection connection = DBConnection.getConnection();

    public List<Foundation> getAllVendorCode() throws SQLException {
        ArrayList<Foundation> vendlist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL39CODE, GL39NAME, GL39BINDER, GL39SUPPLIER, GL39PUB from GLVEND";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL39CODE(rs.getString("GL39CODE"));
                    fnd.setGL39NAME(rs.getString("GL39NAME"));
                    fnd.setGL39BINDER(rs.getString("GL39BINDER"));
                    fnd.setGL39SUPPLIER(rs.getString("GL39SUPPLIER"));
                    fnd.setGL39PUB(rs.getString("GL39PUB"));
                    vendlist.add(fnd);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                rs.close();
                stmt.close();
                con.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            con.close();
        }
        return vendlist;
    }

    public static SQLStatement AddVendor(String GL39CODE, String GL39NAME, String GL39ADD1, String GL39ADD2, String GL39ADD3, String GL39TELNO, String GL39FAX, String GL39PERINC, String GL39DESIG, String GL39CONTNO, String GL39CBDATE, String GL39CEDATE, String GL39REMARK, String GL39ACCNO, String GL39PCODE, String GL39IPADD, String GL39BINDER, String GL39SUPPLIER, String GL39PUB, String GL39INDI, String GL39USERNAME, String GL39PASSWORD, String GL39EMAIL, String GL39BANK) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        valueStr.put("GL39CODE", GL39CODE);
        valueStr.put("GL39NAME", GL39NAME);
        if (GL39ADD1 != null) {
            valueStr.put("GL39ADD1", GL39ADD1);
        } else {
            valueStr.put("GL39ADD1", null);
        }
        if (GL39ADD2 != null) {
            valueStr.put("GL39ADD2", GL39ADD2);
        } else {
            valueStr.put("GL39ADD2", null);
        }
        if (GL39ADD3 != null) {
            valueStr.put("GL39ADD3", GL39ADD3);
        } else {
            valueStr.put("GL39ADD3", null);
        }
        if (GL39TELNO != null) {
            valueStr.put("GL39TELNO", GL39TELNO);
        } else {
            valueStr.put("GL39TELNO", null);
        }
        if (GL39FAX != null) {
            valueStr.put("GL39FAX", GL39FAX);
        } else {
            valueStr.put("GL39FAX", null);
        }
        if (GL39PERINC != null) {
            valueStr.put("GL39PERINC", GL39PERINC);
        } else {
            valueStr.put("GL39PERINC", null);
        }
        if (GL39DESIG != null) {
            valueStr.put("GL39DESIG", GL39DESIG);
        } else {
            valueStr.put("GL39DESIG", null);
        }
        if (GL39CONTNO != null) {
            valueStr.put("GL39CONTNO", GL39CONTNO);
        } else {
            valueStr.put("GL39CONTNO", null);
        }
        if (GL39REMARK != null) {
            valueStr.put("GL39REMARK", GL39REMARK);
        } else {
            valueStr.put("GL39REMARK", null);
        }
        if (GL39ACCNO != null) {
            valueStr.put("GL39ACCNO", GL39ACCNO);
        } else {
            valueStr.put("GL39ACCNO", null);
        }
        if (GL39PCODE != null) {
            valueStr.put("GL39PCODE", GL39PCODE);
        } else {
            valueStr.put("GL39PCODE", null);
        }
        if (GL39IPADD != null) {
            valueStr.put("GL39IPADD", GL39IPADD);
        } else {
            valueStr.put("GL39IPADD", null);
        }
        valueStr.put("GL39BINDER", GL39BINDER);
        valueStr.put("GL39SUPPLIER", GL39SUPPLIER);
        valueStr.put("GL39PUB", GL39PUB);
        valueStr.put("GL39INDI", GL39INDI);
        if (GL39USERNAME != null) {
            valueStr.put("GL39USERNAME", GL39USERNAME);
        } else {
            valueStr.put("GL39USERNAME", null);
        }
        if (GL39PASSWORD != null) {
            valueStr.put("GL39PASSWORD", GL39PASSWORD);
        } else {
            valueStr.put("GL39PASSWORD", null);
        }
        if (GL39EMAIL != null) {
            valueStr.put("GL39EMAIL", GL39EMAIL);
        } else {
            valueStr.put("GL39EMAIL", null);
        }
        if (GL39BANK != null) {
            valueStr.put("GL39BANK", GL39BANK);
        } else {
            valueStr.put("GL39BANK", null);
        }
        if (!GL39CBDATE.isEmpty()) {
            valueStr.put("GL39CBDATE", DateFormatter.displayToDBFormat(GL39CBDATE));
        } else {
            valueStr.put("GL39CBDATE", "");
        }
        if (!GL39CEDATE.isEmpty()) {
            valueStr.put("GL39CEDATE", DateFormatter.displayToDBFormat(GL39CEDATE));
        } else {
            valueStr.put("GL39CEDATE", "");
        }
        String query = QueryBuilder.createInsertQuery("GLVEND", valueStr, null, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL39CODE) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        Statement stmt3 = null;
        ResultSet rs3 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CT03VEND) AS Count FROM CTDOCM, GLVEND WHERE CT03VEND=GL39CODE AND GL39CODE='" + GL39CODE + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    System.out.println(String.valueOf(rs.getString("Count")) + "firstsmt");
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE03SUPPLIER) AS Count FROM SEORDD, GLVEND WHERE SE03SUPPLIER=GL39CODE AND GL39CODE='" + GL39CODE + "'";
                    System.out.println(query2);
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(query2);
                    while (rs2.next()) {
                        System.out.println(String.valueOf(rs2.getString("Count")) + "secondsmt");
                        if (Integer.parseInt(rs2.getString("Count")) < 1) {
                            String query3 = "SELECT COUNT(AC03VEND) AS Count FROM ACORDD, GLVEND WHERE AC03VEND=GL39CODE AND GL39CODE='" + GL39CODE + "'";
                            System.out.println(query3);
                            stmt3 = con.createStatement();
                            rs3 = stmt3.executeQuery(query3);
                            while (rs3.next()) {
                                System.out.println(String.valueOf(rs3.getString("Count")) + "thirdsmt");
                                if (Integer.parseInt(rs3.getString("Count")) >= 1) {
                                    System.out.println("thirdsmt false");
                                    deletable = false;
                                }
                                deletable = true;
                            }
                        }
                        deletable = true;
                    }
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return deletable;
    }

    public static void deleteVendor(String GL39CODE) {
        Connection con = null;
        String query = "DELETE FROM GLVEND WHERE GL39CODE = '" + GL39CODE + "'";
        System.out.println(query);
        try {
            try {
                con = DBConnection.getConnection();
                PreparedStatement delete = con.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
