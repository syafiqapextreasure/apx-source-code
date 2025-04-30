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
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParableTemplateMaintenance {
    private static Connection conn = null;
    Integer seqNo;
    String attributes;
    String attrType;
    String paperType;
    int size;
    float width;
    float height;
    float top;
    float left;
    int rowPerSheet;
    int colsPerSheet;
    String print;
    String notes;
    String colsName;
    String templateGroup;
    String tableName;
    String decimal;
    String styleContent;
    String styleFontDeco;
    String styleFontFamily;
    String styleFontSize;
    String styleFontStyle;
    String styleFontWeight;
    String styleformat;
    String styleHeight;
    String styleLeft;
    String stylePrintOut;
    String styleTextAlign;
    String styleTop;
    String styleType;
    String styleWidth;
    String styleNameId;
    String styleDisplay;
    String styleLineHeight;
    String styleOverflow;
    String styleTextOverflow;
    String styleName;
    String styleValue;

    public Integer getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getAttributes() {
        return this.attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getAttrType() {
        return this.attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getPaperType() {
        return this.paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getTop() {
        return this.top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getLeft() {
        return this.left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public int getRowPerSheet() {
        return this.rowPerSheet;
    }

    public void setRowPerSheet(int rowPerSheet) {
        this.rowPerSheet = rowPerSheet;
    }

    public int getColsPerSheet() {
        return this.colsPerSheet;
    }

    public void setColsPerSheet(int colsPerSheet) {
        this.colsPerSheet = colsPerSheet;
    }

    public String getPrint() {
        return this.print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getColsName() {
        return this.colsName;
    }

    public void setColsName(String colsName) {
        this.colsName = colsName;
    }

    public String getTemplateGroup() {
        return this.templateGroup;
    }

    public void setTemplateGroup(String templateGroup) {
        this.templateGroup = templateGroup;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDecimal() {
        return this.decimal;
    }

    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }

    public String getStyleContent() {
        return this.styleContent;
    }

    public void setStyleContent(String styleContent) {
        this.styleContent = styleContent;
    }

    public String getStyleFontDeco() {
        return this.styleFontDeco;
    }

    public void setStyleFontDeco(String styleFontDeco) {
        this.styleFontDeco = styleFontDeco;
    }

    public String getStyleFontFamily() {
        return this.styleFontFamily;
    }

    public void setStyleFontFamily(String styleFontFamily) {
        this.styleFontFamily = styleFontFamily;
    }

    public String getStyleFontSize() {
        return this.styleFontSize;
    }

    public void setStyleFontSize(String styleFontSize) {
        this.styleFontSize = styleFontSize;
    }

    public String getStyleFontStyle() {
        return this.styleFontStyle;
    }

    public void setStyleFontStyle(String styleFontStyle) {
        this.styleFontStyle = styleFontStyle;
    }

    public String getStyleFontWeight() {
        return this.styleFontWeight;
    }

    public void setStyleFontWeight(String styleFontWeight) {
        this.styleFontWeight = styleFontWeight;
    }

    public String getStyleformat() {
        return this.styleformat;
    }

    public void setStyleformat(String styleformat) {
        this.styleformat = styleformat;
    }

    public String getStyleHeight() {
        return this.styleHeight;
    }

    public void setStyleHeight(String styleHeight) {
        this.styleHeight = styleHeight;
    }

    public String getStyleLeft() {
        return this.styleLeft;
    }

    public void setStyleLeft(String styleLeft) {
        this.styleLeft = styleLeft;
    }

    public String getStylePrintOut() {
        return this.stylePrintOut;
    }

    public void setStylePrintOut(String stylePrintOut) {
        this.stylePrintOut = stylePrintOut;
    }

    public String getStyleTextAlign() {
        return this.styleTextAlign;
    }

    public void setStyleTextAlign(String styleTextAlign) {
        this.styleTextAlign = styleTextAlign;
    }

    public String getStyleTop() {
        return this.styleTop;
    }

    public void setStyleTop(String styleTop) {
        this.styleTop = styleTop;
    }

    public String getStyleType() {
        return this.styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public String getStyleWidth() {
        return this.styleWidth;
    }

    public void setStyleWidth(String styleWidth) {
        this.styleWidth = styleWidth;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleValue() {
        return this.styleValue;
    }

    public void setStyleValue(String styleValue) {
        this.styleValue = styleValue;
    }

    public ParableTemplateMaintenance(int seqNo, String attributes) {
        this.seqNo = seqNo;
        this.attributes = attributes;
    }

    public ParableTemplateMaintenance(String styleName, String styleValue) {
        this.styleName = styleName;
        this.styleValue = styleValue;
    }

    public String getStyleNameId() {
        return this.styleNameId;
    }

    public void setStyleNameId(String styleNameId) {
        this.styleNameId = styleNameId;
    }

    public String getStyleDisplay() {
        return this.styleDisplay;
    }

    public void setStyleDisplay(String styleDisplay) {
        this.styleDisplay = styleDisplay;
    }

    public String getStyleLineHeight() {
        return this.styleLineHeight;
    }

    public void setStyleLineHeight(String styleLineHeight) {
        this.styleLineHeight = styleLineHeight;
    }

    public String getStyleOverflow() {
        return this.styleOverflow;
    }

    public void setStyleOverflow(String styleOverflow) {
        this.styleOverflow = styleOverflow;
    }

    public String getStyleTextOverflow() {
        return this.styleTextOverflow;
    }

    public void setStyleTextOverflow(String styleTextOverflow) {
        this.styleTextOverflow = styleTextOverflow;
    }

    public ParableTemplateMaintenance(String attrType, String paperType, int size, float width, float height, float top, float left, int rowPerSheet, int colsPerSheet, String print, String notes, String colsName, String templateGroup, String tableName, String decimal) {
        this.attrType = attrType;
        this.paperType = paperType;
        this.size = size;
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
        this.rowPerSheet = rowPerSheet;
        this.colsPerSheet = colsPerSheet;
        this.print = print;
        this.notes = notes;
        this.colsName = colsName;
        this.templateGroup = templateGroup;
        this.tableName = tableName;
        this.decimal = decimal;
    }

    public ParableTemplateMaintenance(String styleNameId, String styleDisplay, String styleFontDeco, String styleFontFamily, String styleFontSize, String styleFontStyle, String styleFontWeight, String styleformat, String styleLeft, String styleLineHeight, String styleOverflow, String stylePrintOut, String styleTextAlign, String styleTextOverflow, String styleTop, String styleType, String styleWidth) {
        this.styleNameId = styleNameId;
        this.styleDisplay = styleDisplay;
        this.styleFontDeco = styleFontDeco;
        this.styleFontFamily = styleFontFamily;
        this.styleFontSize = styleFontSize;
        this.styleFontStyle = styleFontStyle;
        this.styleFontWeight = styleFontWeight;
        this.styleformat = styleformat;
        this.styleLeft = styleLeft;
        this.styleLineHeight = styleLineHeight;
        this.styleOverflow = styleOverflow;
        this.stylePrintOut = stylePrintOut;
        this.styleTextAlign = styleTextAlign;
        this.styleTextOverflow = styleTextOverflow;
        this.styleTop = styleTop;
        this.styleType = styleType;
        this.styleWidth = styleWidth;
    }

    public ParableTemplateMaintenance(String print, String styleContent, String styleFontDeco, String styleFontFamily, String styleFontSize, String styleFontStyle, String styleFontWeight, String styleformat, String styleHeight, String styleLeft, String stylePrintOut, String styleTextAlign, String styleTop, String styleType, String styleWidth) {
        this.print = print;
        this.styleContent = styleContent;
        this.styleFontDeco = styleFontDeco;
        this.styleFontFamily = styleFontFamily;
        this.styleFontSize = styleFontSize;
        this.styleFontStyle = styleFontStyle;
        this.styleFontWeight = styleFontWeight;
        this.styleformat = styleformat;
        this.styleHeight = styleHeight;
        this.styleLeft = styleLeft;
        this.stylePrintOut = stylePrintOut;
        this.styleTextAlign = styleTextAlign;
        this.styleTop = styleTop;
        this.styleType = styleType;
        this.styleWidth = styleWidth;
    }

    public static Map<String, String> getTemplates() {
        ArrayList templates = new ArrayList();
        HashMap<String, String> hMapStyles = new HashMap<String, String>();
        String query = "SELECT DISTINCT(LB01NOTES), LB01TPLNAME FROM LBMSTR WHERE LB01FIELD='TPLNAME'";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    hMapStyles.put(rs.getString("LB01NOTES"), rs.getString("LB01TPLNAME"));
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
        return hMapStyles;
    }

    public static List<String> getBranches() {
        ArrayList<String> branches = new ArrayList<String>();
        String query = "SELECT DISTINCT(LB01BRNC) FROM LBMSTR";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branches.add(rs.getString("LB01BRNC"));
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
        return branches;
    }

    public static List<ParableTemplateMaintenance> getAttributes(String template, String branch) {
        ArrayList<ParableTemplateMaintenance> branches = new ArrayList<ParableTemplateMaintenance>();
        String query = "SELECT LB01SEQNO, LB01FIELD FROM LBMSTR WHERE LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, template);
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
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    branches.add(new ParableTemplateMaintenance(rs.getInt("LB01SEQNO"), rs.getString("LB01FIELD")));
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
        return branches;
    }

    public static List<ParableTemplateMaintenance> getAttributeValues(String template, String branch, String selectedAttribute) {
        ArrayList<ParableTemplateMaintenance> attributeValues = new ArrayList<ParableTemplateMaintenance>();
        String query1 = "SELECT LB01PRINT FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        String query2 = "SELECT LB01MTYPE, LB01PAPERTYPE, LB01SIZE, LB01WIDTH, LB01HEIGHT, LB01TOP, LB02LEFT, LB01ROWS, LB01COLS, LB01PRINT, LB01NOTES, LB01COLN, LB01TPLGRP, LB01TBLN, LB01DECIMAL FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt1 = null;
                PreparedStatement stmt2 = null;
                ResultSet rs1 = null;
                ResultSet rs2 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.prepareStatement(query1);
                stmt1.setString(1, selectedAttribute);
                stmt1.setString(2, template);
                stmt1.setString(3, branch);
                rs1 = stmt1.executeQuery();
                ResultSetMetaData metadata1 = rs1.getMetaData();
                int columnCount1 = metadata1.getColumnCount();
                int i = 1;
                while (i <= columnCount1) {
                    System.out.println("metadata [" + metadata1.getColumnTypeName(i) + "|" + metadata1.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                String row1 = "";
                while (rs1.next()) {
                    int i2 = 1;
                    while (i2 <= columnCount1) {
                        row1 = String.valueOf(row1) + rs1.getString(i2) + ", ";
                        ++i2;
                    }
                }
                stmt2 = conn.prepareStatement(query2);
                stmt2.setString(1, selectedAttribute);
                stmt2.setString(2, template);
                stmt2.setString(3, branch);
                rs2 = stmt2.executeQuery();
                ResultSetMetaData metadata2 = rs2.getMetaData();
                int columnCount2 = metadata2.getColumnCount();
                int i3 = 1;
                while (i3 <= columnCount2) {
                    System.out.println("metadata [" + metadata2.getColumnTypeName(i3) + "|" + metadata2.getColumnName(i3) + "]" + ", ");
                    ++i3;
                }
                String row2 = "";
                while (rs2.next()) {
                    int i4 = 1;
                    while (i4 <= columnCount2) {
                        row2 = String.valueOf(row2) + rs2.getString(i4) + ", ";
                        ++i4;
                    }
                    attributeValues.add(new ParableTemplateMaintenance(rs2.getString("LB01MTYPE"), rs2.getString("LB01PAPERTYPE"), rs2.getInt("LB01SIZE"), rs2.getFloat("LB01WIDTH"), rs2.getFloat("LB01HEIGHT"), rs2.getFloat("LB01TOP"), rs2.getFloat("LB02LEFT"), rs2.getInt("LB01ROWS"), rs2.getInt("LB01COLS"), rs2.getString("LB01PRINT"), rs2.getString("LB01NOTES"), rs2.getString("LB01COLN"), rs2.getString("LB01TPLGRP"), rs2.getString("LB01TBLN"), rs2.getString("LB01DECIMAL")));
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
        return attributeValues;
    }

    public static List<ParableTemplateMaintenance> getStyleAttributes(String template, String branch, String selectedStyle) {
        ArrayList<ParableTemplateMaintenance> styleValues = new ArrayList<ParableTemplateMaintenance>();
        String query1 = "SELECT LB01PRINT FROM LBMSTR WHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        String query2 = "SELECT T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD=? AND LB01TPLNAME=? AND LB01BRNC=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt1 = null;
                ResultSet rs1 = null;
                PreparedStatement stmt2 = null;
                ResultSet rs2 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.prepareStatement(query1);
                stmt1.setString(1, selectedStyle);
                stmt1.setString(2, template);
                stmt1.setString(3, branch);
                rs1 = stmt1.executeQuery();
                ResultSetMetaData metadata1 = rs1.getMetaData();
                int columnCount1 = metadata1.getColumnCount();
                int i = 1;
                while (i <= columnCount1) {
                    System.out.println("metadata [" + metadata1.getColumnTypeName(i) + "|" + metadata1.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                String row1 = "";
                while (rs1.next()) {
                    int i2 = 1;
                    while (i2 <= columnCount1) {
                        row1 = String.valueOf(row1) + rs1.getString(i2);
                        ++i2;
                    }
                }
                stmt2 = conn.prepareStatement(query2);
                stmt2.setString(1, selectedStyle);
                stmt2.setString(2, template);
                stmt2.setString(3, branch);
                rs2 = stmt2.executeQuery();
                System.out.println("sql statement: " + stmt2);
                ResultSetMetaData metadata2 = rs2.getMetaData();
                int columnCount2 = metadata2.getColumnCount();
                int i3 = 1;
                while (i3 <= columnCount2) {
                    System.out.println("metadata [" + metadata2.getColumnTypeName(i3) + "|" + metadata2.getColumnName(i3) + "]" + ", ");
                    ++i3;
                }
                String row2 = "";
                while (rs2.next()) {
                    int i4 = 1;
                    while (i4 <= columnCount2) {
                        row2 = String.valueOf(row2) + rs2.getString(i4) + ",";
                        ++i4;
                    }
                }
                String[] attr = row2.split(",");
                styleValues.add(new ParableTemplateMaintenance(row1, attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6], attr[7], attr[8], attr[9], attr[10], attr[11], attr[12], attr[13]));
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
        return styleValues;
    }

    public static void saveAttribute(String template, String branch, String attrType, String selectedAttrType, String selectedPaperType, String sheetSize, String sheetWidth, String sheetHeight, String sheetTop, String sheetLeft, String sheetRowPerSheet, String sheetColPerSheet, String sheetPrint, String sheetNotes, String sheetColumnName, String sheetTemplateGroup, String sheetTableName, String sheetDecimal, String labelWidth, String labelHeight, String labelRow, String labelCol) {
        String queryUpdate1 = "UPDATE LBMSTR SET LB01MTYPE=?, LB01PAPERTYPE=?, LB01SIZE=?, LB01WIDTH=?, LB01HEIGHT=?, LB01TOP=?, LB02LEFT=?, LB01ROWS=?, LB01COLS=?, LB01PRINT=?, LB01NOTES=?, LB01COLN=?, LB01TPLGRP=?, LB01TBLN=?, lb01decimal=? FROM LBMSTR WHERE LB01FIELD='" + attrType + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "'";
        String queryUpdate2 = "UPDATE LBMSTR SET LB01WIDTH=?, LB01HEIGHT=?, LB01ROWS=?, LB01COLS=? FROM LBMSTR WHERE LB01FIELD='" + attrType + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "'";
        Connection conn = null;
        if (labelRow.equals("0") && labelCol.equals("0")) {
            try {
                try {
                    PreparedStatement stmt = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(queryUpdate1);
                    stmt.setString(1, selectedAttrType);
                    stmt.setString(2, selectedPaperType);
                    stmt.setString(3, sheetSize);
                    stmt.setString(4, sheetWidth);
                    stmt.setString(5, sheetHeight);
                    stmt.setString(6, sheetTop);
                    stmt.setString(7, sheetLeft);
                    stmt.setString(8, sheetRowPerSheet);
                    stmt.setString(9, sheetColPerSheet);
                    stmt.setString(10, sheetPrint);
                    stmt.setString(11, sheetNotes);
                    stmt.setString(12, sheetColumnName);
                    stmt.setString(13, sheetTemplateGroup);
                    stmt.setString(14, sheetTableName);
                    stmt.setString(15, sheetDecimal);
                    int updatedRow = stmt.executeUpdate();
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
        } else if (!labelRow.equals("0") && !labelCol.equals("0")) {
            try {
                try {
                    System.out.println("execute label query");
                    System.out.println("labelWidth: " + labelWidth);
                    System.out.println("labelHeight: " + labelHeight);
                    System.out.println("labelRow: " + labelRow);
                    System.out.println("labelCol: " + labelCol);
                    PreparedStatement stmt = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(queryUpdate2);
                    stmt.setString(1, labelWidth);
                    stmt.setString(2, labelHeight);
                    stmt.setString(3, labelRow);
                    stmt.setString(4, labelCol);
                    int updatedRow = stmt.executeUpdate();
                    System.out.println("update row: " + updatedRow);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
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

    public static void saveStyle(String template, String branch, String sendSelectedStyle, String arrJSONstyle) throws JSONException {
        JSONArray array = new JSONArray(arrJSONstyle);
        int i = 0;
        while (i < array.length()) {
            block14: {
                String updateQuery;
                JSONObject object = array.getJSONObject(i);
                if (object.getString("name").equals("LB01PRINT")) {
                    updateQuery = "UPDATE LBMSTR SET LB01PRINT='" + object.getString("value") + "' WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES='" + template + "') AND LB01BRNC='" + branch + "'";
                    System.out.println(updateQuery);
                } else {
                    updateQuery = "UPDATE LBLPROPERTY SET LB02VALUE='" + object.getString("value") + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME= (SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES='" + template + "') AND LB01BRNC='" + branch + "') AND LB02PROPERTY='" + object.getString("name") + "' AND LB02TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES='" + template + "')";
                    System.out.println(updateQuery);
                }
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
                        break block14;
                    }
                }
                catch (Throwable throwable) {
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throw throwable;
                }
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ++i;
        }
    }

    public static void saveStyleTitle(String template, String branch, String sendSelectedStyle, String styleName, String styleDisplay, String styleTitleFontDeco, String styleTitleFontFamily, String styleTitleFontSize, String styleTitleFontStyle, String styleTitleFontWeight, String styleTitleFormat, String styleTitleLeft, String styleLineHeight, String styleOverflow, String styleTitlePrintout, String styleTitleTextAlign, String styleTextOverflow, String styleTitleTop, String styleTitleType, String styleTitleWidth) {
        System.out.println("styleName: " + styleName);
        System.out.println("styleDisplay: " + styleDisplay);
        System.out.println("styleTitleFontDeco: " + styleTitleFontDeco);
        System.out.println("styleTitleFontFamily: " + styleTitleFontFamily);
        System.out.println("styleTitleFontSize: " + styleTitleFontSize);
        System.out.println("styleTitleFontStyle: " + styleTitleFontStyle);
        System.out.println("styleTitleFontWeight: " + styleTitleFontWeight);
        System.out.println("styleTitleFormat: " + styleTitleFormat);
        System.out.println("styleTitleLeft: " + styleTitleLeft);
        System.out.println("styleLineHeight: " + styleLineHeight);
        System.out.println("styleOverflow: " + styleOverflow);
        System.out.println("styleTitlePrintout: " + styleTitlePrintout);
        System.out.println("styleTitleTextAlign: " + styleTitleTextAlign);
        System.out.println("styleTextOverflow: " + styleTextOverflow);
        System.out.println("styleTitleTop: " + styleTitleTop);
        System.out.println("styleTitleType: " + styleTitleType);
        System.out.println("styleTitleWidth: " + styleTitleTextAlign);
        String queryUpdateName = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleName + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='#NAME?' AND LB02TPLNAME='" + template + "'";
        String queryUpdateDisplay = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleDisplay + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='DISPLAY' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFontDec = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFontDeco + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FONT-DECORATION' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFontFamily = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFontFamily + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FONT-FAMILY' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFontSize = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFontSize + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FONT-SIZE' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFontStyle = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFontStyle + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FONT-STYLE' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFontWeight = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFontWeight + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FONT-WEIGHT' AND LB02TPLNAME='" + template + "'";
        String queryUpdateFormat = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleFormat + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='FORMAT' AND LB02TPLNAME='" + template + "'";
        String queryUpdateLeft = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleLeft + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='LEFT' AND LB02TPLNAME='" + template + "'";
        String queryUpdateLineHeight = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleLineHeight + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='LINE-HEIGHT' AND LB02TPLNAME='" + template + "'";
        String queryUpdateOverflow = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleOverflow + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='OVERFLOW' AND LB02TPLNAME='" + template + "'";
        String queryUpdatePrintout = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitlePrintout + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='PRINTOUT' AND LB02TPLNAME='" + template + "'";
        String queryUpdateTextAlign = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleTextAlign + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='TEXT-ALIGN' AND LB02TPLNAME='" + template + "'";
        String queryUpdateTextOverflow = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTextOverflow + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='TEXT-OVERFLOW' AND LB02TPLNAME='" + template + "'";
        String queryUpdateTop = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleTop + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='TOP' AND LB02TPLNAME='" + template + "'";
        String queryUpdateType = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleType + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='TYPE' AND LB02TPLNAME='" + template + "'";
        String queryUpdateWidth = "UPDATE LBLPROPERTY SET LB02VALUE='" + styleTitleWidth + "' WHERE LB02SEQNO=(SELECT LB01SEQNO FROM LBMSTR WHERE LB01FIELD='" + sendSelectedStyle + "' AND LB01TPLNAME='" + template + "' AND LB01BRNC='" + branch + "') AND LB02PROPERTY='WIDTH' AND LB02TPLNAME='" + template + "'";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt1 = null;
                PreparedStatement stmt2 = null;
                PreparedStatement stmt3 = null;
                PreparedStatement stmt4 = null;
                PreparedStatement stmt5 = null;
                PreparedStatement stmt6 = null;
                PreparedStatement stmt7 = null;
                PreparedStatement stmt8 = null;
                PreparedStatement stmt9 = null;
                PreparedStatement stmt10 = null;
                PreparedStatement stmt11 = null;
                PreparedStatement stmt12 = null;
                PreparedStatement stmt13 = null;
                PreparedStatement stmt14 = null;
                PreparedStatement stmt15 = null;
                PreparedStatement stmt16 = null;
                PreparedStatement stmt17 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.prepareStatement(queryUpdateName);
                stmt2 = conn.prepareStatement(queryUpdateDisplay);
                stmt3 = conn.prepareStatement(queryUpdateFontDec);
                stmt4 = conn.prepareStatement(queryUpdateFontFamily);
                stmt5 = conn.prepareStatement(queryUpdateFontSize);
                stmt6 = conn.prepareStatement(queryUpdateFontStyle);
                stmt7 = conn.prepareStatement(queryUpdateFontWeight);
                stmt8 = conn.prepareStatement(queryUpdateFormat);
                stmt9 = conn.prepareStatement(queryUpdateLeft);
                stmt10 = conn.prepareStatement(queryUpdateLineHeight);
                stmt11 = conn.prepareStatement(queryUpdateOverflow);
                stmt12 = conn.prepareStatement(queryUpdatePrintout);
                stmt13 = conn.prepareStatement(queryUpdateTextAlign);
                stmt14 = conn.prepareStatement(queryUpdateTextOverflow);
                stmt15 = conn.prepareStatement(queryUpdateTop);
                stmt16 = conn.prepareStatement(queryUpdateType);
                stmt17 = conn.prepareStatement(queryUpdateWidth);
                int updatedRow = stmt1.executeUpdate();
                stmt2.executeUpdate();
                stmt3.executeUpdate();
                stmt4.executeUpdate();
                stmt5.executeUpdate();
                stmt6.executeUpdate();
                stmt7.executeUpdate();
                stmt8.executeUpdate();
                stmt9.executeUpdate();
                stmt10.executeUpdate();
                stmt11.executeUpdate();
                stmt12.executeUpdate();
                stmt13.executeUpdate();
                stmt14.executeUpdate();
                stmt15.executeUpdate();
                stmt16.executeUpdate();
                stmt17.executeUpdate();
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

    public static Map<String, String> mapStyle(String template, String branch, String selectedStyle) {
        HashMap<String, String> hMapStyles;
        block30: {
            Connection conn = null;
            DBConnection dbtype = new DBConnection();
            hMapStyles = new HashMap<String, String>();
            ArrayList listOLists = new ArrayList();
            ArrayList styleValues = new ArrayList();
            String mssqlQuerySheet = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN('SHEET', 'LABEL') AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01PRINT) as LB01PRINT from LBMSTR\n\rWHERE LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01PRINT)\n\r)AS LBMSTRUnpivot\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01WIDTH) as LB01WIDTH,\n\rconvert(varchar(100), LB01HEIGHT) as LB01HEIGHT,\n\rconvert(varchar(100), LB01ROWS) as LB01ROWS,\n\rconvert(varchar(100), LB01COLS) as LB01COLS\n\rfrom LBMSTR\n\rWHERE LB01FIELD='LABEL' AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01WIDTH, LB01HEIGHT, LB01ROWS, LB01COLS)\n\r)AS LBMSTRUnpivot";
            String mysqlQuerySheet = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN('SHEET', 'LABEL') AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01PRINT' LB02PROPERTY, LB01PRINT LB02VALUE\n\rFROM LBMSTR WHERE LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01WIDTH' LB02PROPERTY, LB01WIDTH LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01HEIGHT' LB02PROPERTY, LB01HEIGHT LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01ROWS' LB02PROPERTY, LB01ROWS LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01COLS' LB02PROPERTY, LB01COLS LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=? AND LB01BRNC=?";
            String mssqlQueryLabel = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN('SHEET', 'LABEL') AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01PRINT) as LB01PRINT from LBMSTR\n\rWHERE LB01TPLNAME=? AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01PRINT)\n\r)AS LBMSTRUnpivot\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01WIDTH) as LB01WIDTH,\n\rconvert(varchar(100), LB01HEIGHT) as LB01HEIGHT,\n\rconvert(varchar(100), LB01ROWS) as LB01ROWS,\n\rconvert(varchar(100), LB01COLS) as LB01COLS\n\rfrom LBMSTR\n\rWHERE LB01FIELD='SHEET' AND LB01TPLNAME=? AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01WIDTH, LB01HEIGHT, LB01ROWS, LB01COLS)\n\r)AS LBMSTRUnpivot";
            String mysqlQueryLabel = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN('SHEET', 'LABEL') AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01PRINT' LB02PROPERTY, LB01PRINT LB02VALUE\n\rFROM LBMSTR WHERE LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01WIDTH' LB02PROPERTY, LB01WIDTH LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01HEIGHT' LB02PROPERTY, LB01HEIGHT LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01ROWS' LB02PROPERTY, LB01ROWS LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01COLS' LB02PROPERTY, LB01COLS LB02VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=? AND LB01BRNC=?";
            String queryStyle = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN(?, 'SHEET', 'LABEL') AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01PRINT) as LB01PRINT from LBMSTR\n\rWHERE LB01FIELD NOT IN(?) AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01PRINT)\n\r)AS LBMSTRUnpivot\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01MTYPE) as LB01MTYPE,\n\rconvert(varchar(100), LB01PAPERTYPE) as LB01PAPERTYPE,\n\rconvert(varchar(100), LB01WIDTH) as LB01WIDTH,\n\rconvert(varchar(100), LB01HEIGHT) as LB01HEIGHT,\n\rconvert(varchar(100), LB01TOP) as LB01TOP,\n\rconvert(varchar(100), LB02LEFT) as LB02LEFT,\n\rconvert(varchar(100), LB01ROWS) as LB01ROWS from LBMSTR\n\rWHERE LB01FIELD='SHEET' AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01MTYPE, LB01PAPERTYPE, LB01WIDTH, LB01HEIGHT, LB01TOP, LB02LEFT, LB01ROWS)\n\r)AS LBMSTRUnpivot\n\rUNION\n\rSELECT LB01FIELD, LB02PROPERTY, LB02VALUE FROM(\n\rselect LB01FIELD,\n\rconvert(varchar(100), LB01WIDTH) as LB01WIDTH,\n\rconvert(varchar(100), LB01HEIGHT) as LB01HEIGHT,\n\rconvert(varchar(100), LB01ROWS) as LB01ROWS,\n\rconvert(varchar(100), LB01COLS) as LB01COLS\n\rfrom LBMSTR\n\rWHERE LB01FIELD='LABEL' AND LB01TPLNAME=(SELECT LB01TPLNAME FROM LBMSTR WHERE LB01NOTES=?) AND LB01BRNC=?) pv\n\rUNPIVOT\n\r(LB02VALUE FOR LB02PROPERTY in (LB01WIDTH, LB01HEIGHT, LB01ROWS, LB01COLS)\n\r)AS LBMSTRUnpivot";
            String MysqlQueryStyle = "SELECT T1.LB01FIELD, T2.LB02PROPERTY, T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD NOT IN(?, 'SHEET', 'LABEL') AND LB01TPLNAME=? AND LB01BRNC=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01PRINT' AS LB02PROPERTY, LB01PRINT FROM LBMSTR\n\rWHERE LB01FIELD NOT IN(?) AND LB01TPLNAME=? AND LB01BRNC='DEFT'\n\rUNION\n\rSELECT LB01FIELD, 'LB01MTYPE' LB02PROPERTY, LB01MTYPE VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01PAPERTYPE' col, LB01PAPERTYPE VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01WIDTH' col, LB01WIDTH VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01HEIGHT' col, LB01HEIGHT VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01TOP' col, LB01TOP VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB02LEFT' col, LB02LEFT VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01ROWS' col, LB01ROWS VALUE\n\rFROM LBMSTR WHERE LB01FIELD='SHEET' AND LB01TPLNAME=?\n\rUNION\n\rSELECT LB01FIELD, 'LB01WIDTH' col, LB01WIDTH VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01HEIGHT' col, LB01HEIGHT VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01ROWS' col, LB01ROWS VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=?\n\rUNION ALL\n\rSELECT LB01FIELD, 'LB01COLS' col, LB01COLS VALUE\n\rFROM LBMSTR WHERE LB01FIELD='LABEL' AND LB01TPLNAME=?";
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    if (selectedStyle.equals("SHEET")) {
                        if (DBConnection.getDBType().equals("mssql")) {
                            System.out.println("query: " + mssqlQuerySheet);
                            stmt = conn.prepareStatement(mssqlQuerySheet);
                            stmt.setString(1, template);
                            stmt.setString(2, branch);
                            stmt.setString(3, template);
                            stmt.setString(4, branch);
                            stmt.setString(5, template);
                            stmt.setString(6, branch);
                            rs = stmt.executeQuery();
                            rs = stmt.executeQuery();
                        } else if (DBConnection.getDBType().equals("mysql")) {
                            System.out.println("query: " + mysqlQuerySheet);
                            stmt = conn.prepareStatement(mysqlQuerySheet);
                            stmt.setString(1, template);
                            stmt.setString(2, branch);
                            stmt.setString(3, template);
                            stmt.setString(4, branch);
                            stmt.setString(5, template);
                            stmt.setString(6, branch);
                            stmt.setString(7, template);
                            stmt.setString(8, branch);
                            stmt.setString(9, template);
                            stmt.setString(10, branch);
                            stmt.setString(11, template);
                            stmt.setString(12, branch);
                            rs = stmt.executeQuery();
                        }
                    } else if (selectedStyle.equals("LABEL")) {
                        if (DBConnection.getDBType().equals("mssql")) {
                            System.out.println("query: " + mssqlQueryLabel);
                            stmt = conn.prepareStatement(mssqlQueryLabel);
                            stmt.setString(1, template);
                            stmt.setString(2, branch);
                            stmt.setString(3, template);
                            stmt.setString(4, branch);
                            stmt.setString(5, template);
                            stmt.setString(6, branch);
                            rs = stmt.executeQuery();
                        } else if (DBConnection.getDBType().equals("mysql")) {
                            System.out.println("query: " + mysqlQueryLabel);
                            stmt = conn.prepareStatement(mysqlQueryLabel);
                            stmt.setString(1, template);
                            stmt.setString(2, branch);
                            stmt.setString(3, template);
                            stmt.setString(4, branch);
                            stmt.setString(5, template);
                            stmt.setString(6, branch);
                            stmt.setString(7, template);
                            stmt.setString(8, branch);
                            stmt.setString(9, template);
                            stmt.setString(10, branch);
                            stmt.setString(11, template);
                            stmt.setString(12, branch);
                            rs = stmt.executeQuery();
                        }
                    } else if (DBConnection.getDBType().equals("mssql")) {
                        System.out.println("query: " + queryStyle);
                        stmt = conn.prepareStatement(queryStyle);
                        stmt.setString(1, selectedStyle);
                        stmt.setString(2, template);
                        stmt.setString(3, branch);
                        stmt.setString(4, selectedStyle);
                        stmt.setString(5, template);
                        stmt.setString(6, branch);
                        stmt.setString(7, template);
                        stmt.setString(8, branch);
                        stmt.setString(9, template);
                        stmt.setString(10, branch);
                        rs = stmt.executeQuery();
                    } else if (DBConnection.getDBType().equals("mysql")) {
                        System.out.println("query: " + MysqlQueryStyle);
                        stmt = conn.prepareStatement(MysqlQueryStyle);
                        stmt.setString(1, selectedStyle);
                        stmt.setString(2, template);
                        stmt.setString(3, branch);
                        stmt.setString(4, selectedStyle);
                        stmt.setString(5, template);
                        stmt.setString(6, template);
                        stmt.setString(7, template);
                        stmt.setString(8, template);
                        stmt.setString(9, template);
                        stmt.setString(10, template);
                        stmt.setString(11, template);
                        stmt.setString(12, template);
                        stmt.setString(13, template);
                        stmt.setString(14, template);
                        stmt.setString(15, template);
                        stmt.setString(16, template);
                        rs = stmt.executeQuery();
                    }
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        ArrayList<String> singleList = new ArrayList<String>();
                        String row = "";
                        int i2 = 1;
                        while (i2 <= columnCount) {
                            row = String.valueOf(row) + rs.getString(i2);
                            if (i2 == 2) {
                                singleList.add(row);
                            }
                            if (i2 == 3) {
                                row = "";
                                row = rs.getString(i2);
                                singleList.add(row);
                            }
                            ++i2;
                        }
                        listOLists.add(singleList);
                    }
                    i = 0;
                    while (i < listOLists.size()) {
                        List sublist = (List)listOLists.get(i);
                        hMapStyles.put((String)sublist.get(0), (String)sublist.get(1));
                        ++i;
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
                    break block30;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        TreeMap<String, String> treeMap = new TreeMap<String, String>(hMapStyles);
        return treeMap;
    }

    public static List<ParableTemplateMaintenance> getStyleAttributesTitle(String template, String branch, String selectedStyle) {
        ArrayList<ParableTemplateMaintenance> styleValues = new ArrayList<ParableTemplateMaintenance>();
        String query = "SELECT T2.LB02VALUE FROM LBMSTR T1\n\rLEFT JOIN  LBLPROPERTY T2 ON (T2.LB02SEQNO =T1.LB01SEQNO) AND (T2.LB02TPLNAME =T1.LB01TPLNAME)\n\rWHERE LB01FIELD='Title' AND LB01TPLNAME=? AND LB01BRNC=?";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, template);
                stmt.setString(2, branch);
                rs = stmt.executeQuery();
                System.out.println("sql statement: " + stmt);
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                String row = "";
                while (rs.next()) {
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        System.out.println("row in for loop: " + row);
                        ++i2;
                    }
                    System.out.println("row: " + row);
                }
                String[] attr = row.split(",");
                styleValues.add(new ParableTemplateMaintenance(attr[0], attr[1], attr[2], attr[3], attr[4], attr[5], attr[6], attr[7], attr[8], attr[9], attr[10], attr[11], attr[12], attr[13], attr[14], attr[15], attr[16]));
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
        return styleValues;
    }
}
