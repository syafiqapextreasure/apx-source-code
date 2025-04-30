/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.marc4j.MarcException
 *  org.marc4j.MarcStreamReader
 *  org.marc4j.marc.DataField
 *  org.marc4j.marc.Leader
 *  org.marc4j.marc.Record
 *  org.marc4j.marc.Subfield
 */
package com.kmlink.ilmu.cataloging.Paramips;

import com.kmlink.ilmu.shared.global.Glnumb;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.utilities.query.DBQuery;
import com.kmlink.ilmu.shared.utilities.query.QueryBuilder;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.marc4j.MarcException;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Leader;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;

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
    private static int counter = 0;
    private static ArrayList<Integer> nums = new ArrayList();

    public ISOtoMARC(String MP02INS, String MP02COND, String MP02OPE, String MP02REP, String MP02SRC, String MP02TRG) {
        this.MP02INS = MP02INS;
        this.MP02COND = MP02COND;
        this.MP02OPE = MP02OPE;
        this.MP02REP = MP02REP;
        this.MP02SRC = MP02SRC;
        this.MP02TRG = MP02TRG;
    }

    public static String validateRcrd(InputStream data) throws FileNotFoundException {
        String error = "";
        try {
            MarcStreamReader reader = new MarcStreamReader(data);
            Record marcRecord = reader.next();
            error = "";
            System.out.println("sss12");
        }
        catch (MarcException e) {
            System.out.println("sss" + e.toString());
            error = e.toString();
        }
        return error;
    }

    public static List<Integer> convertToMarc(InputStream data, String script) throws FileNotFoundException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        ArrayList<String> queryList = new ArrayList<String>();
        int recno = 1;
        Glnumb BATCHNO = Glnumb.getGL98VALUE("BATCHNO");
        int batchno = BATCHNO.getGL98VALUE();
        Glnumb CT04COUNTER = Glnumb.getGL98VALUE("MPCOUNT");
        int counter = CT04COUNTER.getGL98VALUE();
        System.out.println("Batch" + batchno + data);
        ArrayList<Integer> arrlist = new ArrayList<Integer>();
        arrlist.add(batchno);
        MarcStreamReader reader = new MarcStreamReader(data);
        Record marcRecord = reader.next();
        Leader ldr = marcRecord.getLeader();
        valueInt.put("MP01BATCH", batchno);
        valueInt.put("MP01RECNO", recno++);
        valueStr.put("MP01OTAG", "000");
        valueStr.put("MP01OINDI1", "#");
        valueStr.put("MP01OINDI2", "#");
        valueStr.put("MP01ODATA", ldr.toString());
        valueInt.put("MP01COUNT", counter);
        String query = QueryBuilder.createInsertQuery("MPDATA", valueStr, valueInt, null);
        queryList.add(query);
        List fields = marcRecord.getDataFields();
        Iterator i = fields.iterator();
        while (i.hasNext()) {
            CT04COUNTER = Glnumb.getGL98VALUE("MPCOUNT");
            counter = CT04COUNTER.getGL98VALUE();
            DataField field = (DataField)i.next();
            valueInt.put("MP01BATCH", batchno);
            valueInt.put("MP01RECNO", recno++);
            valueStr.put("MP01OTAG", field.getTag());
            if (String.valueOf(field.getIndicator1()) == null) {
                valueStr.put("MP01OINDI1", "#");
            } else if (String.valueOf(field.getIndicator1()).trim().equals("")) {
                valueStr.put("MP01OINDI1", "#");
            } else {
                valueStr.put("MP01OINDI1", String.valueOf(field.getIndicator1()));
            }
            if (String.valueOf(field.getIndicator2()) == null) {
                valueStr.put("MP01OINDI2", "#");
            } else if (String.valueOf(field.getIndicator2()).trim().equals("")) {
                valueStr.put("MP01OINDI2", "#");
            } else {
                valueStr.put("MP01OINDI2", String.valueOf(field.getIndicator2()));
            }
            List subfields = field.getSubfields();
            Iterator j = subfields.iterator();
            StringBuilder builder = new StringBuilder();
            while (j.hasNext()) {
                Subfield subfield = (Subfield)j.next();
                String rawData = subfield.getData().replace("'", "''");
                rawData = "|" + subfield.getCode() + rawData.trim();
                System.out.println("Subfield code: " + rawData);
                builder.append(rawData);
            }
            valueStr.put("MP01ODATA", builder.toString());
            valueInt.put("MP01COUNT", counter);
            query = QueryBuilder.createInsertQuery("MPDATA", valueStr, valueInt, null);
            queryList.add(query);
        }
        boolean isSuccess = DBQuery.runBatchQuery(queryList);
        if (isSuccess) {
            boolean bl = ISOtoMARC.getScript(script);
        }
        System.out.println("Batch21" + arrlist.toString());
        return arrlist;
    }

    public static ISOtoMARC InsertConvMarc(String record) throws SQLException {
        String[] data;
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        int recno = 1;
        Glnumb BATCHNO = Glnumb.getGL98VALUE("BATCHNO");
        int batchno = BATCHNO.getGL98VALUE();
        Glnumb CT04COUNTER = Glnumb.getGL98VALUE("MPCOUNT");
        int counter = CT04COUNTER.getGL98VALUE();
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
            valueInt.put("MP01COUNT", counter);
            String query = QueryBuilder.createInsertQuery("MPDATA", valueStr, valueInt, null);
            boolean isSuccess = DBQuery.runQuery(query);
            System.out.println(isSuccess);
            ++n2;
        }
        nums.add(batchno);
        ++counter;
        System.out.println("Counter" + nums);
        return null;
    }

    public static boolean getScript(String tplName) {
        ArrayList<ISOtoMARC> list = new ArrayList<ISOtoMARC>();
        String query = "SELECT MP02INS, MP02COND, MP02OPE, MP02REP, MP02SRC, MP02TRG, MP02FIELD FROM MPSCRPT WHERE MP02TPLNAME='" + tplName + "'";
        System.out.println(query);
        boolean done = false;
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ISOtoMARC script = new ISOtoMARC(rs.getString("MP02INS"), rs.getString("MP02COND"), rs.getString("MP02OPE"), rs.getString("MP02REP"), rs.getString("MP02SRC"), rs.getString("MP02TRG"));
                    ISOtoMARC.updateOriRecord(rs.getString("MP02INS"), rs.getString("MP02COND"), rs.getString("MP02OPE"), rs.getString("MP02REP"), rs.getString("MP02SRC"), rs.getString("MP02TRG"), rs.getString("MP02FIELD"));
                    list.add(script);
                    done = true;
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
        return done;
    }

    public static void updateOriRecord(String MP02OINS, String MP02COND, String MP02OPE, String MP02REP, String MP02SRC, String MP02TRG, String MP02FIELD) {
        String column = null;
        String column1 = null;
        String query1 = "";
        System.out.println("Tags" + MP02OINS + "ssss" + MP02REP + "ssd");
        if (MP02REP.equals("Tag")) {
            column1 = "MP01OTAG ";
        }
        if (MP02OINS.trim().equals("Tag")) {
            column = "MP01OTAG ";
        }
        if (MP02REP.equals("Indi1")) {
            column1 = "MP01OINDI1";
        }
        if (MP02OINS.equals("Indi1")) {
            column = "MP01OINDI1";
        }
        if (MP02REP.equals("Indi2")) {
            column1 = "MP01OINDI2";
        }
        if (MP02OINS.equals("Indi2")) {
            column = "MP01OINDI2";
        }
        if (MP02REP.equals("RawData")) {
            column1 = "MP01ODATA";
        }
        if (MP02OINS.equals("RawData")) {
            column = "MP01ODATA";
        }
        query1 = String.valueOf(column) + MP02COND + "'" + MP02OPE + "'";
        try {
            String query = "";
            System.out.println("TestField" + MP02FIELD);
            if (MP02FIELD.contains("Subf")) {
                if (MP02FIELD.contains("Drop") || MP02FIELD.contains("Replace")) {
                    query = "UPDATE MPDATA set MP01ODATA = cast(replace(cast(MP01ODATA as nvarchar(max)), '" + MP02SRC + "', '" + MP02TRG + "')as ntext) where " + query1;
                }
            } else if (MP02FIELD.contains("Reject")) {
                query = "DELETE from MPDATA where " + column1 + " = '" + MP02SRC + "' and " + query1;
            } else if (MP02FIELD.contains("Drop") || MP02FIELD.contains("Replace")) {
                query = "UPDATE MPDATA set " + column1 + " = '" + MP02TRG + "' where " + column1 + " = '" + MP02SRC + "' and " + query1;
            }
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
