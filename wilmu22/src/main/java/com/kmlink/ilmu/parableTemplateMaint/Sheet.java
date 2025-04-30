/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.parableTemplateMaint;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sheet {
    private static Connection conn = null;
    private String name;
    private String value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Sheet(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static String getSheetData(String template, String branch, String selectedAttr) throws JSONException, SQLException {
        String query = "SELECT LB01MTYPE, LB01PAPERTYPE, LB01SIZE, LB01WIDTH, LB01HEIGHT, LB01TOP, LB02LEFT, LB01ROWS, LB01COLN, LB01NOTES, LB01TPLGRP FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES='" + template + "') AND LB01BRNC=?";
        System.out.println("sql: " + query);
        ArrayList data = new ArrayList();
        String arrJson = null;
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, selectedAttr);
                stmt.setString(2, branch);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    JSONArray jArray = new JSONArray();
                    JSONObject jObj1 = new JSONObject();
                    jObj1.put("name", (Object)"Attribute Type");
                    jObj1.put("value", (Object)rs.getString("LB01MTYPE"));
                    jArray.put((Object)jObj1);
                    JSONObject jObj2 = new JSONObject();
                    jObj2.put("name", (Object)"Paper Type");
                    jObj2.put("value", (Object)rs.getString("LB01PAPERTYPE"));
                    jArray.put((Object)jObj2);
                    JSONObject jObj3 = new JSONObject();
                    jObj3.put("name", (Object)"Size");
                    jObj3.put("value", (Object)rs.getString("LB01SIZE"));
                    jArray.put((Object)jObj3);
                    JSONObject jObj4 = new JSONObject();
                    jObj4.put("name", (Object)"Width");
                    jObj4.put("value", (Object)rs.getString("LB01WIDTH"));
                    jArray.put((Object)jObj4);
                    JSONObject jObj5 = new JSONObject();
                    jObj5.put("name", (Object)"Height");
                    jObj5.put("value", (Object)rs.getString("LB01HEIGHT"));
                    jArray.put((Object)jObj5);
                    JSONObject jObj6 = new JSONObject();
                    jObj6.put("name", (Object)"Top");
                    jObj6.put("value", (Object)rs.getString("LB01TOP"));
                    jArray.put((Object)jObj6);
                    JSONObject jObj7 = new JSONObject();
                    jObj7.put("name", (Object)"Left");
                    jObj7.put("value", (Object)rs.getString("LB02LEFT"));
                    jArray.put((Object)jObj7);
                    JSONObject jObj8 = new JSONObject();
                    jObj8.put("name", (Object)"Row Per Sheet");
                    jObj8.put("value", (Object)rs.getString("LB01ROWS"));
                    jArray.put((Object)jObj8);
                    JSONObject jObj9 = new JSONObject();
                    jObj9.put("name", (Object)"Column Per Sheet");
                    jObj9.put("value", (Object)rs.getString("LB01COLN"));
                    jArray.put((Object)jObj9);
                    JSONObject jObj10 = new JSONObject();
                    jObj10.put("name", (Object)"Notes");
                    jObj10.put("value", (Object)rs.getString("LB01NOTES"));
                    jArray.put((Object)jObj10);
                    JSONObject jObj11 = new JSONObject();
                    jObj11.put("name", (Object)"Template Group");
                    jObj11.put("value", (Object)rs.getString("LB01TPLGRP"));
                    jArray.put((Object)jObj11);
                    arrJson = jArray.toString(2);
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
        return arrJson;
    }

    public static void saveSheet(String template, String branch, String selectedAttrType, String arrJSON) throws JSONException {
        Connection conn = null;
        HashMap<String, String> sheetHM = new HashMap<String, String>();
        JSONArray array = new JSONArray(arrJSON);
        int i = 0;
        while (i < array.length()) {
            JSONObject object = array.getJSONObject(i);
            sheetHM.put(object.getString("name"), object.getString("value"));
            ++i;
        }
        String updateQuery = "UPDATE LBMSTR SET LB01MTYPE ='" + (String)sheetHM.get("Attribute Type") + "',LB01COLS ='" + (String)sheetHM.get("Column Per Sheet") + "', LB01HEIGHT ='" + (String)sheetHM.get("Height") + "', LB02LEFT ='" + (String)sheetHM.get("Left") + "', LB01NOTES ='" + (String)sheetHM.get("Notes") + "', LB01PAPERTYPE ='" + (String)sheetHM.get("Paper Type") + " ',  LB01ROWS ='" + (String)sheetHM.get("Row Per Sheet") + "',  LB01SIZE ='" + (String)sheetHM.get("Size") + "',  LB01TPLGRP ='" + (String)sheetHM.get("Template Group") + "',  LB01TOP='" + (String)sheetHM.get("Top") + "', LB01WIDTH ='" + (String)sheetHM.get("Width") + "' WHERE LB01TPLNAME='" + template + "' AND LB01FIELD='SHEET' AND LB01BRNC='" + branch + "'";
        System.out.println("update query: " + updateQuery);
        try {
            try {
                PreparedStatement stmt1 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.prepareStatement(updateQuery);
                int updatedRow = stmt1.executeUpdate();
                System.out.println("update row: " + updatedRow);
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
    }
}
