/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.cataloging.Template_Maint;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Subfield_Handler {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String GL23SUBF = null;
    private String GL23DESC = null;
    private String GL23REPEAT = null;

    public Subfield_Handler(String GL23SUBF, String GL23DESC, String GL23REPEAT) {
        this.GL23SUBF = GL23SUBF;
        this.GL23DESC = GL23DESC;
        this.GL23REPEAT = GL23REPEAT;
    }

    public Subfield_Handler(String GL23SUBF) {
        this.GL23SUBF = GL23SUBF;
    }

    public Subfield_Handler(String GL23DESC, String GL23REPEAT) {
        this.GL23DESC = GL23DESC;
        this.GL23REPEAT = GL23REPEAT;
    }

    public String getGL23REPEAT() {
        return this.GL23REPEAT;
    }

    public String getGL23DESC() {
        return this.GL23DESC;
    }

    public String getGL23SUBF() {
        return this.GL23SUBF;
    }

    public static List<Subfield_Handler> getListOfSubf(String GL23TAG, String marc) {
        ArrayList<Subfield_Handler> list = new ArrayList<Subfield_Handler>();
        String query = "SELECT GL23SUBF, GL23DESC, GL23REPEAT FROM GLMSUBF WHERE GL23TAG='" + GL23TAG + "' and GL23SUBF BETWEEN  'a' and 'z' and GL23MARC='" + marc + "' " + "UNION " + "SELECT GL23SUBF, GL23DESC, GL23REPEAT " + "FROM GLMSUBF " + "WHERE GL23TAG='" + GL23TAG + "' and GL23SUBF BETWEEN  '0' and '9' and GL23MARC='" + marc + "'";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Subfield_Handler newSubfList = new Subfield_Handler(rs.getString("GL23SUBF"), rs.getString("GL23DESC"), rs.getString("GL23REPEAT"));
                    list.add(newSubfList);
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
        return list;
    }

    public static Subfield_Handler getSubfDetails(String subfields, String tag, String marc) {
        String query = "SELECT GL23DESC, GL23REPEAT FROM GLMSUBF WHERE GL23SUBF='" + subfields + "' " + "AND GL23TAG='" + tag + "' " + "AND GL23MARC='" + marc + "'";
        Subfield_Handler getSubfields = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getSubfields = new Subfield_Handler(rs.getString("GL23DESC"), rs.getString("GL23REPEAT"));
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
        return getSubfields;
    }

    public static boolean subfieldExist(String subfields, String tag, String marc) {
        String query = "SELECT Count(*) as Count FROM GLMSUBF WHERE GL23SUBF='" + subfields + "' " + "AND GL23TAG='" + tag + "' " + "AND GL23MARC='" + marc + "'";
        boolean exist = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    exist = true;
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
        return exist;
    }

    public static String repeatCheck(String CT23SUBF, String GL23TAG, String CT16MSTR) {
        String query = "Select GL23SUBF from CTTPL, GLMSUBF WHERE GL23TAG=CT16TAG and CT16TAG='" + GL23TAG + "'" + "and GL23REPEAT='N' and CT16MSTR=" + Integer.parseInt(CT16MSTR) + " " + "and GL23SUBF='" + CT23SUBF + "'";
        String getSubfields = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getSubfields = rs.getString("GL23SUBF");
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
        return getSubfields;
    }

    public static boolean Add_Subfield(String subfields) {
        String query = "SELECT COUNT(*) AS Count FROM GLMSUBF WHERE GL23SUBF = '" + subfields + "'";
        boolean deletable = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
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

    public static boolean rcrdExist_CT23SUBF(String CT23SUBF, String GL23TAG, String CT16MSTR) {
        boolean deletable = false;
        String[] rawArray = CT23SUBF.split("\\|");
        if (CT23SUBF != null) {
            int i = 1;
            while (i < rawArray.length) {
                block15: {
                    String splitData = rawArray[i].substring(0, 1);
                    if (splitData != null && splitData != "") {
                        boolean deletable1 = false;
                        String query = "Select CT16SUBF from CTTPL, GLMSUBF where CT16SUBF=GL23SUBF and GL23TAG=CT16TAG and CT16SUBF='" + splitData + "' and GL23REPEAT='N' and GL23TAG='" + GL23TAG + "'and CT16MSTR='" + CT16MSTR + "'";
                        try {
                            try {
                                c = DBConnection.getConnection();
                                stmt = c.createStatement();
                                rs = stmt.executeQuery(query);
                                if (rs.next()) {
                                    deletable = true;
                                    deletable1 = true;
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
                                break block15;
                            }
                        }
                        catch (Throwable throwable) {
                            try {
                                c.close();
                            }
                            catch (SQLException e) {
                                e.printStackTrace();
                            }
                            throw throwable;
                        }
                        try {
                            c.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                ++i;
            }
        }
        return deletable;
    }

    public static String nonRepeat_Subf(String GL23SUBF, String GL23TAG) {
        String query = "Select GL23SUBF from GLMSUBF WHERE GL23TAG='" + GL23TAG + "'" + "and GL23REPEAT='N' " + "and GL23SUBF='" + GL23SUBF + "'";
        String getSubfields = null;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    getSubfields = rs.getString("GL23SUBF");
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
        return getSubfields;
    }

    public static List<String> getNonRepeatSubf(String GL23TAG, String marc) {
        ArrayList<String> list = new ArrayList<String>();
        String query = "Select GL23SUBF,GL23DESC, GL23REPEAT from GLMSUBF WHERE GL23TAG='" + GL23TAG + "' and GL23REPEAT = 'N' and GL23MARC='" + marc + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String newSubfList = rs.getString("GL23SUBF");
                    list.add(newSubfList);
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
        return list;
    }
}
