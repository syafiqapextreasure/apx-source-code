/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.IncomingRequest;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatValidate {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String rsMembershipDate;
    private String rsExpiryDate;
    private String PatronID;
    private String PatronName;
    private String PatronAdd1;
    private String PatronAdd2;
    private String PatronAdd3;
    private String PatronStatus;
    private String PatronCategory;
    private String RelatedID;
    private String bRemoved;
    String errmessage = "";
    private String rsPeriodType;
    private String riRetPeriodOfLoan;
    private String riEligibility;
    private String rsAllowOverdue;

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getrsMembershipDate() {
        return Handler.ifIsNull(this.rsMembershipDate);
    }

    public String getrsExpiryDate() {
        return Handler.ifIsNull(this.rsExpiryDate);
    }

    public String getPatronID() {
        return Handler.ifIsNull(this.PatronID);
    }

    public String getPatronName() {
        return Handler.ifIsNull(this.PatronName);
    }

    public String getPatronAdd1() {
        return Handler.ifIsNull(this.PatronAdd1);
    }

    public String getPatronAdd2() {
        return Handler.ifIsNull(this.PatronAdd2);
    }

    public String getPatronAdd3() {
        return Handler.ifIsNull(this.PatronAdd3);
    }

    public String getPatronStatus() {
        return Handler.ifIsNull(this.PatronStatus);
    }

    public String getPatronCategory() {
        return Handler.ifIsNull(this.PatronCategory);
    }

    public String getRelatedID() {
        return Handler.ifIsNull(this.RelatedID);
    }

    public String getbRemoved() {
        return Handler.ifIsNull(this.bRemoved);
    }

    public PatValidate(String rsMembershipDate, String rsExpiryDate) {
        this.rsMembershipDate = rsMembershipDate;
        this.rsExpiryDate = rsExpiryDate;
    }

    public PatValidate(String PatronName, String PatronAdd1, String PatronAdd2, String PatronAdd3, String PatronStatus, String PatronCategory, String RelatedID, String bRemoved) {
        this.PatronName = PatronName;
        this.PatronAdd1 = PatronAdd1;
        this.PatronAdd2 = PatronAdd2;
        this.PatronAdd3 = PatronAdd3;
        this.PatronStatus = PatronStatus;
        this.PatronCategory = PatronCategory;
        this.RelatedID = RelatedID;
        this.bRemoved = bRemoved;
    }

    public PatValidate() {
    }

    public boolean PatValidateMain(String sPatronID) throws SQLException {
        boolean tempPatValidate = false;
        Object sPatronName = null;
        Object sPatronAdd1 = null;
        Object sPatronAdd2 = null;
        Object sPatronAdd3 = null;
        String sPatronStatus = null;
        String sPatronCategory = null;
        Object sPatronExpiryDate = null;
        Object sPatronMembershipDate = null;
        Object sCorpCategory = null;
        String sCurrentDate = null;
        Object sRelatedID = null;
        Object sPatronStatusDesc = null;
        boolean iPatronIsInILLBorrowerCategory = false;
        Object sCorpStatus = null;
        Object sCorpStatusDesc = null;
        boolean bRemoved = false;
        tempPatValidate = true;
        sCurrentDate = DateTime.getTodaySystemDate();
        sPatronStatus = PatValidate.getpatrStat(sPatronID);
        boolean canCharge = false;
        canCharge = this.CanPatronCharge(sPatronStatus);
        if (!this.CanPatronCharge(sPatronStatus)) {
            this.errmessage = "Patron does not have CHARGE status";
            tempPatValidate = false;
            return tempPatValidate;
        }
        this.ValidateMembershipExpiry(sPatronID);
        if (this.rsExpiryDate.compareTo(sCurrentDate) < 0) {
            this.errmessage = "membership had expired";
            tempPatValidate = false;
            return tempPatValidate;
        }
        if (this.rsMembershipDate.compareTo(sCurrentDate) > 0) {
            this.errmessage = "membership will not be activated until";
            tempPatValidate = false;
            return tempPatValidate;
        }
        sPatronCategory = PatValidate.getpatrCat(sPatronID);
        if (this.IsPatronAllowOverdue(sPatronCategory) && !this.ValidateOverduePatCat(sPatronID, sPatronCategory)) {
            this.errmessage = "The borrower has overdue items, new borrowing is not allowed.";
            tempPatValidate = false;
            return tempPatValidate;
        }
        return tempPatValidate;
    }

    public boolean ValidateMembershipExpiry(String vsPatronID) {
        boolean exist;
        block12: {
            exist = false;
            String sql = "Select GL14MEMDATE,GL14EXPDATE From GLPATR Where GL14PATR = '" + vsPatronID + "'";
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sql);
                    if (!rsObj.next()) {
                        this.rsMembershipDate = "";
                        this.rsExpiryDate = "";
                        break block12;
                    }
                    this.rsMembershipDate = String.valueOf(rsObj.getString("GL14MEMDATE"));
                    this.rsExpiryDate = String.valueOf(rsObj.getString("GL14EXPDATE"));
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
        return exist;
    }

    public final boolean CanPatronCharge(String id) {
        boolean tempCanPatronCharge;
        block13: {
            tempCanPatronCharge = false;
            String sSQLStmt = "Select GL08ACTION1 From GLSTAT Where GL08STAT = '" + id + "'";
            System.out.println("SQL CanPatronCharge" + sSQLStmt);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sSQLStmt);
                    if (!rsObj.next()) {
                        tempCanPatronCharge = false;
                        break block13;
                    }
                    if (rsObj.getString("GL08ACTION1").equals("Y")) {
                        tempCanPatronCharge = true;
                        break block13;
                    }
                    tempCanPatronCharge = false;
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
        return tempCanPatronCharge;
    }

    public static List<PatValidate> PatronDetails(String id) {
        ArrayList<PatValidate> list = new ArrayList<PatValidate>();
        String sql = "SELECT GL14PATR, GL14NAME, GL14ADD1, GL14ADD2, GL14ADD3, GL14DESC, GL14NAMETITLE, GL14OFFADD1, GL14OFFADD2, GL14OFFADD3, GL14MAILFLAG, GL14STAT, GL14CATE, GL14RELID, GL14RMVD  FROM GLPATR WHERE UPPER(GL14PATR) = UPPER('" + id + "') ";
        System.out.println(sql);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            String rsPatronName = null;
            String rsRetStatus = null;
            String rsPatronCat = null;
            String rsRetRelatedID = null;
            String rbRemoved = null;
            String mail = null;
            String rsPatronAdd1 = null;
            String rsPatronAdd2 = null;
            String rsPatronAdd3 = null;
            while (rsObj.next()) {
                rsPatronName = rsObj.getString("GL14NAME");
                rsRetStatus = rsObj.getString("GL14STAT");
                rsPatronCat = rsObj.getString("GL14CATE");
                rsRetRelatedID = rsObj.getString("GL14RELID");
                mail = rsObj.getString("GL14MAILFLAG");
                rbRemoved = rsObj.getString("GL14RMVD").equals("Y") ? "true" : "false";
                if (mail.equals("R")) {
                    rsPatronAdd1 = rsObj.getString("GL14ADD1");
                    rsPatronAdd2 = rsObj.getString("GL14ADD2");
                    rsPatronAdd3 = rsObj.getString("GL14ADD3");
                } else if (mail.equals("O")) {
                    rsPatronAdd1 = rsObj.getString("GL14OFFADD1");
                    rsPatronAdd2 = rsObj.getString("GL14OFFADD2");
                    rsPatronAdd3 = rsObj.getString("GL14OFFADD3");
                }
                PatValidate patronDetail = new PatValidate(rsPatronName, rsPatronAdd1, rsPatronAdd2, rsPatronAdd3, rsRetStatus, rsPatronCat, rsRetRelatedID, rbRemoved);
                list.add(patronDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getpatrStat(String id) {
        String sSQLStmt = "Select GL14STAT From GLPATR Where GL14PATR = '" + id + "'";
        System.out.println("SQL getpatrStat" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL14STAT"));
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
        return value;
    }

    public static String getpatrCat(String id) {
        String sSQLStmt = "Select GL14CATE From GLPATR Where GL14PATR = '" + id + "'";
        System.out.println("SQL getpatrCat" + sSQLStmt);
        String value = null;
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    value = Handler.ifIsNull(rs.getString("GL14CATE"));
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
        return value;
    }

    public final boolean IsPatronAllowOverdue(String vsPatronCategory) {
        boolean tempIsPatronAllowOverdue;
        block13: {
            tempIsPatronAllowOverdue = false;
            String sSQLStmt = "SELECT GL07ALLOWOVD FROM GLCATE WHERE GL07CATE = '" + vsPatronCategory + "' ";
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sSQLStmt);
                    if (!rsObj.next()) {
                        tempIsPatronAllowOverdue = false;
                        break block13;
                    }
                    String allow = rsObj.getString("GL07ALLOWOVD");
                    if (allow.equals("N")) {
                        tempIsPatronAllowOverdue = false;
                        break block13;
                    }
                    tempIsPatronAllowOverdue = false;
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
        return tempIsPatronAllowOverdue;
    }

    public final boolean ValidateOverduePatCat(String vsPatronID, String vsPatronCategory) {
        boolean tempValidateOverduePatCat = false;
        String sSQLStmt = null;
        boolean bEligibility = false;
        String sLocation = null;
        String sICAT = null;
        String sSMD = null;
        String sPeriodType = null;
        sSQLStmt = "SELECT CT03LOCA, CT03ICAT, CT03SMD, CT03DDATE, CT03DTIME FROM CTDOCM WHERE CT03PATR = '" + vsPatronID + "' " + " AND (CT03STAT = 'C' OR CT03STAT = 'E') " + "AND CT03DDATE <'" + DateTime.getTodaySystemDate() + "'";
        System.out.println("ValidateOverduePatCat" + sSQLStmt);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sSQLStmt);
                if (!rsObj.next()) {
                    tempValidateOverduePatCat = false;
                } else {
                    sLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                    sICAT = String.valueOf(rsObj.getString("CT03ICAT"));
                    bEligibility = this.ValidateEligibility(vsPatronCategory, sICAT, sSMD = String.valueOf(rsObj.getString("CT03SMD")), sLocation, sPeriodType, 0, 0, "");
                    if (bEligibility) {
                        if (sPeriodType.equals("D")) {
                            if (rsObj.getString("CT03DDATE").compareTo(DateTime.getTodaySystemDate()) < 0) {
                                tempValidateOverduePatCat = false;
                            }
                        } else if (sPeriodType.equals("H")) {
                            if (rsObj.getString("CT03DDATE").compareTo(DateTime.getTodaySystemDate()) < 0) {
                                tempValidateOverduePatCat = false;
                            } else if (rsObj.getString("CT03DTIME").compareTo(DateTime.getCurrentTime()) < 0) {
                                tempValidateOverduePatCat = false;
                            }
                        }
                    }
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
        return tempValidateOverduePatCat;
    }

    public boolean ValidateEligibility(String msPatronCategory, String sDocItemCategory, String sSMD, String sLocation, String sPeriodType, int iPeriodOfLoan, int iEligibility, String sAllowOverdue) {
        boolean exist = false;
        String loca = this.GetBranch(sLocation);
        String sql = "Select GL27CATE,GL27ICAT,GL27PTYPE,GL27PLOAN,GL27ELIG, GL27GRACE1,GL27GRACE2,GL27GRACE3,GL27RSTR,GL27ALLOWOVD From GLELIG Where GL27CATE = '" + msPatronCategory + "' " + "And GL27ICAT = '" + sDocItemCategory + "' " + "And GL27SMD = '" + sSMD + "' " + "And GL27BRNC = '" + loca + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.rsPeriodType = String.valueOf(rsObj.getString("GL27PTYPE"));
                    this.riRetPeriodOfLoan = String.valueOf(rsObj.getString("GL27PLOAN"));
                    this.riEligibility = String.valueOf(rsObj.getString("GL27ELIG"));
                    this.rsAllowOverdue = String.valueOf(rsObj.getString("GL27ALLOWOVD"));
                    exist = true;
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
        return exist;
    }

    public String GetBranch(String vsLoca) {
        String sql = "SELECT GL05LOCA, GL05BRNC FROM GLLOCA WHERE GL05LOCA = '" + vsLoca + "' ";
        String GetBranch = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    GetBranch = String.valueOf(rsObj.getString("GL14BRNC"));
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
        return GetBranch;
    }
}
