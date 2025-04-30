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
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Label {
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

    public Label(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Label() {
    }

    List<String> labelName() {
        ArrayList<String> labelName = new ArrayList<String>();
        labelName.add("Width");
        labelName.add("Height");
        labelName.add("Row");
        labelName.add("Column");
        return labelName;
    }

    public static String getLabelData(String template, String branch, String selectedAttr) throws JSONException {
        String query = "SELECT LB01WIDTH, LB01HEIGHT, LB01ROWS, LB01COLS FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES='" + template + "') AND LB01BRNC=?";
        ArrayList data = new ArrayList();
        String arrJson = null;
        conn = DBConnection.getConnection();
        JSONObject jObj = new JSONObject();
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
                    System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    JSONArray jArray = new JSONArray();
                    JSONObject jObj1 = new JSONObject();
                    jObj1.put("name", (Object)"Width");
                    jObj1.put("value", (Object)rs.getString("LB01WIDTH"));
                    jObj1.put("dbFieldName", (Object)"LB01WIDTH");
                    jArray.put((Object)jObj1);
                    JSONObject jObj2 = new JSONObject();
                    jObj2.put("name", (Object)"Height");
                    jObj2.put("value", (Object)rs.getString("LB01HEIGHT"));
                    jObj2.put("dbFieldName", (Object)"LB01HEIGHT");
                    jArray.put((Object)jObj2);
                    JSONObject jObj3 = new JSONObject();
                    jObj3.put("name", (Object)"Row");
                    jObj3.put("value", (Object)rs.getString("LB01ROWS"));
                    jObj3.put("dbFieldName", (Object)"LB01ROWS");
                    jArray.put((Object)jObj3);
                    JSONObject jObj4 = new JSONObject();
                    jObj4.put("name", (Object)"Column");
                    jObj4.put("value", (Object)rs.getString("LB01COLS"));
                    jObj4.put("dbFieldName", (Object)"LB01COLS");
                    jArray.put((Object)jObj4);
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

    public static void saveLabel(String template, String branch, String selectedAttrType, String arrJSON) throws JSONException {
        HashMap<String, String> labelHM = new HashMap<String, String>();
        JSONArray array = new JSONArray(arrJSON);
        int i = 0;
        while (i < array.length()) {
            JSONObject object = array.getJSONObject(i);
            labelHM.put(object.getString("name"), object.getString("value"));
            ++i;
        }
        String updateQuery = "UPDATE LBMSTR SET LB01COLS ='" + (String)labelHM.get("Column") + "', LB01HEIGHT ='" + (String)labelHM.get("Height") + "', LB01ROWS ='" + (String)labelHM.get("Row") + "', LB01WIDTH ='" + (String)labelHM.get("Width") + "' WHERE LB01TPLNAME='" + template + "' AND LB01FIELD='LABEL' AND LB01BRNC='" + branch + "'";
        System.out.println("sql: " + updateQuery);
        Connection conn = null;
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
