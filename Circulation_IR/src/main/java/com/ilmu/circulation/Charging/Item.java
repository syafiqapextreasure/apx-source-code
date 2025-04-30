/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {
    private String vsItemCategory;
    private String vsItemCategoryDesc;
    private String vsItemUnit;
    private String vsItemLastDate;
    private String vsItemDateRec;
    private String vsItemRecBy;
    private String vsItemDisplay;
    private String vsITemReservec;
    private int vsItemElig;
    private String vsItemCode;
    private String msItemTitle;
    private String msItemCallNo;
    private String msItemCircStatus;
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public Item() {
        try {
            conn = DBConnection.getConnection();
            conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBookTitle(String msMatNo) {
        String msBookTitle = null;
        try {
            String sql = "SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + msMatNo + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBookTitle = rsObj.getString("CT05SRAW");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msBookTitle;
    }

    public static String getBookCallNo(String msMatNo) {
        String msBookCallNo = null;
        try {
            String sql = "Select CT05POINTER, CT05SRAW From CTCALL, CTPONT Where CT06MATNO = '" + msMatNo + "' " + "And  CT06POINTER = CT05POINTER " + "And  CT06TAG = '090' " + "And  CT06STATUS = 'A'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBookCallNo = rsObj.getString("CT05SRAW");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msBookCallNo;
    }

    public static String getMsCircStatus(String msDocNo) {
        String msCircStatus = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT CT03DOCNO, CT03STAT,GL36STAT, GL36DESC  FROM CTDOCM,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }

    public static String getMsCircStatus(String msDocNo, String msCircMatNo, String msCircFlag) {
        String msCircStatus = null;
        try {
            String sql = "SELECT CT03DOCNO, CT03STAT,GL36STAT, GL36DESC FROM CTDOCM ,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }

    public String getMsItemTitle() {
        return this.msItemTitle;
    }

    public void setMsItemTitle(String msItemTitle) {
        this.msItemTitle = msItemTitle;
    }

    public String getMsItemCallNo() {
        return this.msItemCallNo;
    }

    public void setMsItemCallNo(String msItemCallNo) {
        this.msItemCallNo = msItemCallNo;
    }

    public String getMsItemCircStatus() {
        return this.msItemCircStatus;
    }

    public void setMsItemCircStatus(String msItemCircStatus) {
        this.msItemCircStatus = msItemCircStatus;
    }

    public String getVsItemCategoryDesc() {
        String sql = "Select GL10DESC From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemCategoryDesc = String.valueOf(rsObj.getString("GL10DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemCategoryDesc;
    }

    public String getVsItemCategoryDesc(String vsItemCategory) {
        String sql = "Select GL10DESC From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemCategoryDesc = String.valueOf(rsObj.getString("GL10DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemCategoryDesc;
    }

    public void setVsItemCategory(String vsItemCategoryDesc) {
        this.vsItemCategoryDesc = vsItemCategoryDesc;
    }

    public String getVsItemUnit() {
        String sql = "Select GL10UnitFrom GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemUnit = String.valueOf(rsObj.getString("GL10UNIT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemUnit;
    }

    public String getVsItemUnit(String vsItemCategory) {
        String sql = "Select GL10UnitFrom GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemUnit = String.valueOf(rsObj.getString("GL10UNIT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemUnit;
    }

    public void setVsItemUnit(String vsItemUnit) {
        this.vsItemUnit = vsItemUnit;
    }

    public String getVsItemLastDate() {
        String sql = "Select GL10LASTDATEFrom GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemLastDate = GeneralUtility.StrToDate(rsObj.getString("GL10LASTDATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemLastDate;
    }

    public String getVsItemLastDate(String vsItemCategory) {
        String sql = "Select GL10LASTDATEFrom GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemLastDate = GeneralUtility.StrToDate(rsObj.getString("GL10LASTDATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemLastDate;
    }

    public void setVsItemLastDate(String vsItemLastDate) {
        this.vsItemLastDate = vsItemLastDate;
    }

    public String getVsItemDateRec() {
        String sql = "Select GL10DATERECFrom GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemDateRec = GeneralUtility.StrToDate(rsObj.getString("GL10DATEREC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemDateRec;
    }

    public String getVsItemDateRec(String vsItemCategory) {
        String sql = "Select GL10DATERECFrom GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemDateRec = GeneralUtility.StrToDate(rsObj.getString("GL10DATEREC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemDateRec;
    }

    public void setVsItemDateRec(String vsItemDateRec) {
        this.vsItemDateRec = vsItemDateRec;
    }

    public String getVsItemRecBy() {
        String sql = "Select GL10RECBY From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemRecBy = String.valueOf(rsObj.getString("GL10RECBY"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemRecBy;
    }

    public String getVsItemRecBy(String vsItemCategory) {
        String sql = "Select GL10RECBY From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemRecBy = String.valueOf(rsObj.getString("GL10RECBY"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemRecBy;
    }

    public void setVsItemRecBy(String vsItemRecBy) {
        this.vsItemRecBy = vsItemRecBy;
    }

    public String getVsItemDisplay() {
        String sql = "Select GL10DISPLAY From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemDisplay = String.valueOf(rsObj.getString("GL10DISPLAY"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemDisplay;
    }

    public String getVsItemDisplay(String vsItemCategory) {
        String sql = "Select GL10DISPLAY From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemDisplay = String.valueOf(rsObj.getString("GL10DISPLAY"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemDisplay;
    }

    public void setVsItemDisplay(String vsItemDisplay) {
        this.vsItemDisplay = vsItemDisplay;
    }

    public String getVsITemReservec(String vsItemCategory) {
        String sql = "Select GL10RESERVEC From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsITemReservec = String.valueOf(rsObj.getString("GL10RESERVEC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsITemReservec;
    }

    public String getVsITemReservec() {
        String sql = "Select GL10RESERVEC From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsITemReservec = String.valueOf(rsObj.getString("GL10RESERVEC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsITemReservec;
    }

    public void setVsITemReservec(String vsITemReservec) {
        this.vsITemReservec = vsITemReservec;
    }

    public int getVsItemElig(String vsItemCategory) {
        String sql = "Select GL10ELIG From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemElig = rsObj.getInt("GL10ELIG");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemElig;
    }

    public int getVsItemElig() {
        String sql = "Select GL10ELIG From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemElig = rsObj.getInt("GL10ELIG");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemElig;
    }

    public void setVsItemElig(int vsItemElig) {
        this.vsItemElig = vsItemElig;
    }

    public String getVsItemCode() {
        String sql = "Select GL10CODE From GLICAT Where GL10ICAT = '" + this.vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemCode = String.valueOf(rsObj.getInt("GL10CODE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemCode;
    }

    public String getVsItemCode(String vsItemCategory) {
        String sql = "Select GL10CODE From GLICAT Where GL10ICAT = '" + vsItemCategory + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.vsItemCode = String.valueOf(rsObj.getInt("GL10CODE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.vsItemCode;
    }

    public void setVsItemCode(String vsItemCode) {
        this.vsItemCode = vsItemCode;
    }
}
