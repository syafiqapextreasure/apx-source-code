/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GlobalValidation {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static int getAccessionMaxLength() {
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='ACCNOLEN'";
        int maxlength = 0;
        System.out.println("Validation" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    maxlength = Integer.parseInt(rs.getString("GL99VALUE"));
                    System.out.println("Validation22" + maxlength);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maxlength;
    }

    public static int getAccessionMinLength() {
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='ACCNOMINLEN'";
        int minlength = 0;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (!GlobalValidation.chkAccLength().equals("Y")) continue;
                    minlength = Integer.parseInt(rs.getString("GL99VALUE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return minlength;
    }

    public static String chkAccLength() {
        Connection c1 = DBConnection.getConnection();
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='CHECKACCLEN'";
        String chklength = "";
        try {
            try {
                Statement stmt1 = c1.createStatement();
                ResultSet rs1 = stmt1.executeQuery(query);
                while (rs1.next()) {
                    chklength = rs1.getString("GL99VALUE");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chklength;
    }

    public static boolean checkSubfield(String string, String string2, String string3) {
        throw new Error("Unresolved compilation problem: \n\tThe method getNonRepeatSubf(String, String) is undefined for the type Subfield_Handler\n");
    }

    public static String chkifsubfexist(String string, String string2, String string3) {
        throw new Error("Unresolved compilation problem: \n\tThe method SubfieldList(String, String) is undefined for the type BO_Validation\n");
    }

    public static String removeSubfWithNoRecord(String raw) {
        System.out.println("Value" + raw);
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                System.out.println("Data1" + rawArray[i] + "s");
                if (!rawArray[i].isEmpty()) {
                    String splitData = rawArray[i].trim().substring(1);
                    System.out.println("Data1" + splitData + "s");
                    if (splitData.trim() != null && splitData.trim().length() != 0) {
                        System.out.println("Data12" + splitData);
                        result.append("|" + rawArray[i]);
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }
}
