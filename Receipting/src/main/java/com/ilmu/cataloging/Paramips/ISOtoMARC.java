/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.marc4j.MarcStreamReader
 *  org.marc4j.marc.Record
 */
package com.ilmu.cataloging.Paramips;

import com.ilmu.global.Glnumb;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.Record;

public class ISOtoMARC {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String MP02INS = null;
    private String MP02COND = null;
    private String MP02OPE = null;
    private String MP02REP = null;
    private String MP02SRC = null;
    private String MP02TRG = null;

    public ISOtoMARC(String MP02INS, String MP02COND, String MP02OPE, String MP02REP, String MP02SRC, String MP02TRG) {
        this.MP02INS = MP02INS;
        this.MP02COND = MP02COND;
        this.MP02OPE = MP02OPE;
        this.MP02REP = MP02REP;
        this.MP02SRC = MP02SRC;
        this.MP02TRG = MP02TRG;
    }

    public static String convertToMarc(String fileName) throws FileNotFoundException {
        System.out.println(fileName);
        FileInputStream in = new FileInputStream(fileName);
        MarcStreamReader reader = new MarcStreamReader((InputStream)in);
        while (reader.hasNext()) {
            Record record = reader.next();
            ISOtoMARC.InsertConvMarc(record.toString());
        }
        return fileName;
    }

    public static ISOtoMARC InsertConvMarc(String record) {
        String[] data;
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        int recno = 1;
        Glnumb BATCHNO = Glnumb.getGL98VALUE("BATCHNO");
        int batchno = BATCHNO.getGL98VALUE();
        System.out.println(record);
        if (record.startsWith("LEADER")) {
            record.replaceAll("LEADER", "000");
        }
        String[] stringArray = data = record.split("\\r?\\n");
        int n = data.length;
        int n2 = 0;
        while (n2 < n) {
            String datas = stringArray[n2];
            String tag = datas.substring(0, 3);
            String indi1 = datas.substring(4, 5);
            String indi2 = datas.substring(5, 6);
            if (tag.equals("LEA") && indi1.equals("E") && indi2.equals("R")) {
                tag = "000";
                indi1 = "";
                indi2 = "";
            }
            String rawData = datas.substring(6);
            rawData = rawData.replaceAll("\\$", "|");
            rawData = rawData.replace("'", "''");
            valueInt.put("MP01BATCH", batchno);
            valueInt.put("MP01RECNO", recno++);
            valueStr.put("MP01OTAG", tag);
            valueStr.put("MP01OINDI1", indi1);
            valueStr.put("MP01OINDI2", indi2);
            valueStr.put("MP01ODATA", rawData);
            String query = QueryBuilder.createInsertQuery("MPDATA", valueStr, valueInt, null);
            boolean isSuccess = DBQuery.runQuery(query);
            System.out.println(isSuccess);
            ++n2;
        }
        return null;
    }

    public static List<ISOtoMARC> getScript(String tplName) {
        ArrayList<ISOtoMARC> list = new ArrayList<ISOtoMARC>();
        String query = "SELECT MP02INS, MP02COND, MP02OPE, MP02REP, MP02SRC, MP02TRG FROM MPSCRPT WHERE MP02TPLNAME='" + tplName + "'";
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ISOtoMARC script = new ISOtoMARC(rs.getString("MP02INS"), rs.getString("MP02COND"), rs.getString("MP02OPE"), rs.getString("MP02REP"), rs.getString("MP02SRC"), rs.getString("MP02TRG"));
                    ISOtoMARC.updateOriRecord(rs.getString("MP02INS"), rs.getString("MP02COND"), rs.getString("MP02OPE"), rs.getString("MP02REP"), rs.getString("MP02SRC"), rs.getString("MP02TRG"));
                    list.add(script);
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

    public static void updateOriRecord(String MP02OINS, String MP02COND, String MP02OPE, String MP02REP, String MP02SRC, String MP02TRG) {
        String column = null;
        if (MP02OINS.equals("Tag") || MP02REP.equals("Tag")) {
            column = "MP01OTAG";
        }
        try {
            String query = "UPDATE MPDATA set " + column + " = '" + MP02TRG + "' where " + column + " " + MP02COND + " '" + MP02OPE + "'";
            System.out.println(query);
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.executeUpdate();
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
