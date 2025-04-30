/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessionDetails {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msRetAccessionNo;
    private String msRetStatus;
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetDocNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msRetItemBranch;
    private String msRetDueTime;
    private String msPatronBranch;

    public AccessionDetails(String msAccessionNo) {
        try {
            System.out.println("from Details");
            this.RetrieveAccessionDetail(msAccessionNo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo) {
        boolean exist = false;
        try {
            String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo + "'" + "AND CT03LOCA = GL05LOCA AND (CT03STAT='A' or CT03STAT='H')";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetDocNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                this.msRetCondition = String.valueOf(rsObj.getString("CT03DDATE"));
                this.msRetItemBranch = String.valueOf(rsObj.getString("GL05BRNC"));
                System.out.println(this.msRetItemBranch);
                System.out.println(rsObj.getString("CT03LOCA"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public AccessionDetails(String msAccessionNo, String patron) throws SQLException {
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rsObj1 = null;
        String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo + "'" + "AND CT03LOCA = GL05LOCA";
        System.out.println(sql);
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rsObj1 = stmt1.executeQuery(sql);
                while (rsObj1.next()) {
                    this.msRetDocNo = String.valueOf(rsObj1.getString("CT03DOCNO"));
                    this.msRetStatus = String.valueOf(rsObj1.getString("CT03STAT"));
                    this.msRetItemCat = String.valueOf(rsObj1.getString("CT03ICAT"));
                    this.msRetMatNo = String.valueOf(rsObj1.getString("CT03MATNO"));
                    this.msRetSMD = String.valueOf(rsObj1.getString("CT03SMD"));
                    this.msRetLocation = String.valueOf(rsObj1.getString("CT03LOCA"));
                    this.msRetPatron = String.valueOf(rsObj1.getString("CT03PATR"));
                    this.msRetCondition = String.valueOf(rsObj1.getString("CT03DDATE"));
                    this.msRetItemBranch = String.valueOf(rsObj1.getString("GL05BRNC"));
                    this.msRetDueDate = String.valueOf(rsObj1.getString("CT03DDATE"));
                    System.out.println(rsObj1.getString("CT03LOCA"));
                    this.msRetDueTime = rsObj1.getString("CT03DTIME");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                conn1.close();
                stmt1.close();
                rsObj1.close();
            }
        }
        finally {
            conn1.close();
            stmt1.close();
            rsObj1.close();
        }
    }

    public String getBookTitle(String msRetMatNo) {
        String msBookTitle = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + msRetMatNo + "'";
            System.out.println("Title" + sql);
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

    public String getMsCircStatus(String msDocNo) {
        String msCircStatus = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT CT03DOCNO, CT03STAT,GL36STAT, GL36DESC  FROM CTDOCM,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }

    public String getMsRetAccessionNo() {
        return this.msRetAccessionNo;
    }

    public String getMsRetStatus() {
        return this.msRetStatus;
    }

    public String getMsRetItemCat() {
        return this.msRetItemCat;
    }

    public String getMsRetMatNo() {
        return this.msRetMatNo;
    }

    public String getMsRetDocNo() {
        return this.msRetDocNo;
    }

    public String getMsRetSMD() {
        return this.msRetSMD;
    }

    public String getMsRetLocation() {
        return this.msRetLocation;
    }

    public String getMsRetPatron() {
        return this.msRetPatron;
    }

    public String getMsRetCondition() {
        return this.msRetCondition;
    }

    public String getMsRetDueDate() {
        return this.msRetDueDate;
    }

    public String getMsNoCircByPatron() {
        return this.msNoCircByPatron;
    }

    public void setMsRetAccessionNo(String msRetAccessionNo) {
        this.msRetAccessionNo = msRetAccessionNo;
    }

    public void setMsRetStatus(String msRetStatus) {
        this.msRetStatus = msRetStatus;
    }

    public void setMsRetItemCat(String msRetItemCat) {
        this.msRetItemCat = msRetItemCat;
    }

    public void setMsRetMatNo(String msRetMatNo) {
        this.msRetMatNo = msRetMatNo;
    }

    public void setMsRetDocNo(String msRetDocNo) {
        this.msRetDocNo = msRetDocNo;
    }

    public void setMsRetSMD(String msRetSMD) {
        this.msRetSMD = msRetSMD;
    }

    public void setMsRetLocation(String msRetLocation) {
        this.msRetLocation = msRetLocation;
    }

    public void setMsRetPatron(String msRetPatron) {
        this.msRetPatron = msRetPatron;
    }

    public void setMsRetCondition(String msRetCondition) {
        this.msRetCondition = msRetCondition;
    }

    public void setMsRetDueDate(String msRetDueDate) {
        this.msRetDueDate = msRetDueDate;
    }

    public void setMsNoCircByPatron(String msNoCircByPatron) {
        this.msNoCircByPatron = msNoCircByPatron;
    }

    public String getMsRetItemBranch() {
        return this.msRetItemBranch;
    }

    public void setMsRetItemBranch(String msRetItemBranch) {
        this.msRetItemBranch = msRetItemBranch;
    }
}
