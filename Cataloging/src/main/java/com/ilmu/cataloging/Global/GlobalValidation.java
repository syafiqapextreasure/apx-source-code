/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Global;

import com.ilmu.cataloging.Bibliography.BO_Validation;
import com.ilmu.cataloging.Template_Maint.Subfield_Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                    if (GlobalValidation.chkAccLength().equals("Y")) {
                        maxlength = Integer.parseInt(rs.getString("GL99VALUE"));
                    }
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

    public static boolean checkSubfield(String GL23TAG, String splitSubfs, String marc) {
        List<String> subfList = Subfield_Handler.getNonRepeatSubf(GL23TAG, marc);
        ArrayList<String> subfArray = new ArrayList<String>();
        boolean exist = true;
        if (splitSubfs != null) {
            String[] subfields = splitSubfs.split("\\|");
            int i = 1;
            while (i < subfields.length) {
                String subf = subfields[i].substring(0, 1);
                String data = subfields[i].substring(1);
                if (subfList.contains(subf)) {
                    if (subfArray.contains(subf) && !data.trim().isEmpty()) {
                        exist = false;
                        break;
                    }
                } else {
                    exist = true;
                }
                subfArray.add(subf);
                ++i;
            }
        }
        return exist;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String chkifsubfexist(String GL23TAG, String subfield, String marc) {
        String wrongsubf = "";
        List<String> subfList = BO_Validation.SubfieldList(GL23TAG, marc);
        if (subfield == null) return wrongsubf;
        String[] subfields = subfield.split("\\|");
        int i = 1;
        while (i < subfields.length) {
            String subf = subfields[i].substring(0, 1);
            String data = subfields[i].substring(1);
            System.out.println("Data subfield" + data.trim() + "sss");
            if (!data.trim().isEmpty()) {
                if (!subfList.contains(subf)) return subf;
                wrongsubf = "";
            } else {
                System.out.println("Data subfield1");
                wrongsubf = "";
            }
            ++i;
        }
        return wrongsubf;
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
