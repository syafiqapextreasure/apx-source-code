/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.foundation.LocationCode;

import com.ilmu.foundation.global.Foundation;
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

    public List<Foundation> getAllLocaCode() throws SQLException {
        ArrayList<Foundation> localist = new ArrayList<Foundation>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                String query = "SELECT GL05BRNC, GL05LOCA, GL05DESC, GL05SUBJECT, GL05MATCAP from GLLOCA";
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Foundation fnd = new Foundation();
                    fnd.setGL05BRNC(rs.getString("GL05BRNC"));
                    fnd.setGL05LOCA(rs.getString("GL05LOCA"));
                    fnd.setGL05DESC(rs.getString("GL05DESC"));
                    fnd.setGL05SUBJECT(rs.getString("GL05SUBJECT"));
                    fnd.setGL05MATCAP(rs.getString("GL05MATCAP"));
                    localist.add(fnd);
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
        return localist;
    }

    public static SQLStatement AddLocation(String GL05LOCA, String GL05DESC, String GL05SUBJECT, String GL05IPADD, String GL05MATCAP, String GL05NOSERVER, String GL05NOTER, String GL05NOPC, String GL05LNPRT, String GL05LJPRT, String GL05DMPRT, String GL05MODEM, String GL05MMEDIA, String GL05CDROM, String GL05SDI, String GL05SDDS, String GL05IRL, String GL05JARING, String GL05NST, String GL05LAYOUT, String GL05DISPLAY, String GL05BRNC) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("GL05LOCA", GL05LOCA);
        valueStr.put("GL05DESC", GL05DESC);
        valueStr.put("GL05SUBJECT", GL05SUBJECT);
        valueStr.put("GL05IPADD", GL05IPADD);
        valueInt.put("GL05MATCAP", Integer.parseInt(GL05MATCAP));
        valueInt.put("GL05NOSERVER", Integer.parseInt(GL05NOSERVER));
        valueInt.put("GL05NOTER", Integer.parseInt(GL05NOTER));
        valueInt.put("GL05NOPC", Integer.parseInt(GL05NOPC));
        valueInt.put("GL05LNPRT", Integer.parseInt(GL05LNPRT));
        valueInt.put("GL05LJPRT", Integer.parseInt(GL05LJPRT));
        valueInt.put("GL05DMPRT", Integer.parseInt(GL05DMPRT));
        valueInt.put("GL05MODEM", Integer.parseInt(GL05MODEM));
        valueStr.put("GL05MMEDIA", GL05MMEDIA);
        valueStr.put("GL05CDROM", GL05CDROM);
        valueStr.put("GL05SDI", GL05SDI);
        valueStr.put("GL05SDDS", GL05SDDS);
        valueStr.put("GL05IRL", GL05IRL);
        valueStr.put("GL05JARING", GL05JARING);
        valueStr.put("GL05NST", GL05NST);
        valueStr.put("GL05LAYOUT", GL05LAYOUT);
        valueStr.put("GL05DISPLAY", GL05DISPLAY);
        valueStr.put("GL05BRNC", GL05BRNC);
        String query = QueryBuilder.createInsertQuery("GLLOCA", valueStr, valueInt, null);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean CheckIfExist(String GL05LOCA) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Statement stmt2 = null;
        ResultSet rs2 = null;
        boolean deletable = false;
        try {
            try {
                con = DBConnection.getConnection();
                String query = "SELECT COUNT(CT03LOCA) AS Count FROM CTDOCM, GLLOCA WHERE CT03LOCA=GL05LOCA AND GL05LOCA='" + GL05LOCA + "'";
                System.out.println(query);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) >= 1) continue;
                    String query2 = "SELECT COUNT(SE06LOCA) AS Count FROM SEISSU, GLLOCA WHERE SE06LOCA=GL05LOCA AND GL05LOCA='" + GL05LOCA + "'";
                    System.out.println(query2);
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery(query2);
                    while (rs2.next()) {
                        Integer.parseInt(rs.getString("Count"));
                    }
                    deletable = true;
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

    public static void deleteLoca(String GL05LOCA) {
        Connection con = null;
        String query = "DELETE FROM GLLOCA WHERE GL05LOCA = '" + GL05LOCA + "'";
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
