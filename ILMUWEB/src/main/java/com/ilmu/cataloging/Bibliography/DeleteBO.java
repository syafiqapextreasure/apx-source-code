/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.Bibliography;

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteBO {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static boolean isDeleteable(String CT02MATNO) {
        String query = "SELECT CT02MATNO FROM CTMATM WHERE CT02STATUS='T' AND CT02MATNO='" + CT02MATNO + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    deletable = true;
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
        return deletable;
    }

    public static boolean rcrdExist_CTWORK(String CT04MATNO) {
        String query = "SELECT CT04MATNO FROM CTMATM WHERE CT04MATNO='" + CT04MATNO + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    deletable = true;
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
        return deletable;
    }

    public static boolean rcrdExist_CTMATM(String CT03MATNO) {
        String query = "SELECT COUNT(*) AS CT03MATNO FROM CTDOCM WHERE CT03MATNO='" + CT03MATNO + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("CT03MATNO")) <= 0) continue;
                    deletable = false;
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
        return deletable;
    }

    public static boolean rcrdExist_ACORDD(String AC03CTRLNO) {
        String query = "SELECT COUNT(*) AS AC03CTRLNO FROM ACORDD WHERE AC03CTRLNO='" + AC03CTRLNO + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("AC03CTRLNO")) <= 0) continue;
                    deletable = false;
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
        return deletable;
    }

    public static boolean rcrdExist_SESERM(String SE01MATNO) {
        String query = "SELECT COUNT(*) AS SE01MATNO FROM SESERM WHERE SE01MATNO='" + SE01MATNO + "'";
        boolean deletable = true;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("SE01MATNO")) <= 0) continue;
                    deletable = true;
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
        return deletable;
    }

    public static void updateCTMATM(String CT02MATNO) {
        String query = "UPDATE CTMATM SET CT02STATUS='D', CT02DLTDATE='" + DateTime.getTodayDate() + "', " + "CT02DLTIME='" + DateTime.getTodayTime() + "'WHERE CT02MATNO = '" + CT02MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }

    public static void updateCTWORK(String CT04MATNO) {
        String query = "UPDATE CTWORK SET CT04STATUS='D' WHERE CT04MATNO = '" + CT04MATNO + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
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
    }
}
