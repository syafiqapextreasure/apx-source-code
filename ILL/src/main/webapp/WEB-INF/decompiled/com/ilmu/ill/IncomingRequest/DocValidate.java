/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.IncomingRequest;

import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DocValidate {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String rsRetStatus;
    private String rsRetItemCat;
    private String rsRetSMD;
    private String rsRetLocation;
    private String rsRetPatron;
    private String rsRetDueDate;
    private String msPatronCategory;
    private String rsPeriodType;
    private String riRetPeriodOfLoan;
    private String riEligibility;
    private String rsAllowOverdue;
    private String rsItemCat;
    private String rsSMD;
    private String rsLocation;
    String errmessage = "";

    public String getErrMessage() {
        return this.errmessage;
    }

    public boolean DocValidateMain(String msAccessionNo, String msPatronID, String txtMaterial) throws SQLException {
        boolean tempDocValidate = false;
        Object sAccessionNo = null;
        Object sSMD = null;
        String sLocation = null;
        String sDocStatus = null;
        String sDocItemCategory = null;
        String sDocLastBorrowedPatron = null;
        String sDocDueDate = null;
        long lIndxPointer = 0L;
        Object sPeriodType = null;
        boolean iPeriodOfLoan = false;
        boolean iEligibility = false;
        Object sAllowOverdue = null;
        int iItemCateCirculated = 0;
        boolean bEligible = false;
        Object sConvertDate = null;
        String sCurrentDate = null;
        Object sCurrentTime = null;
        Object sDueDate = null;
        Object sDueTime = null;
        int iNoCategoryElig = 0;
        int iItemCategoryMaxLoan = 0;
        int iNoCirculated = 0;
        tempDocValidate = true;
        sCurrentDate = DateTime.getTodaySystemDate();
        sDocStatus = "";
        sDocItemCategory = "";
        sLocation = "";
        sDocLastBorrowedPatron = "";
        sDocDueDate = "";
        String msPatronCategory = this.GetPatronCatCode(msPatronID);
        if (!msAccessionNo.equals("")) {
            this.RetrieveAccessionDetails(msAccessionNo);
            System.out.println("fffffffffffffffff" + this.rsRetLocation);
            bEligible = this.ValidateEligibility(msPatronCategory, this.rsRetItemCat, this.rsRetSMD, this.rsRetLocation);
            if (!bEligible) {
                this.errmessage = "The borrower is not eligible to charge/borrow this item";
                tempDocValidate = false;
                return tempDocValidate;
            }
        } else {
            this.RetrieveMaterialDetails(txtMaterial);
            bEligible = this.ValidateEligibility(msPatronCategory, this.rsItemCat, this.rsSMD, this.rsLocation);
            if (!bEligible) {
                this.errmessage = "The borrower is not eligible to charge/borrow this item";
            }
            if (this.IsRequested(txtMaterial, msPatronID)) {
                this.errmessage = "The borrower has requested this item by Control Number";
                tempDocValidate = false;
                return tempDocValidate;
            }
        }
        if (!msPatronID.equals("")) {
            System.out.println("qqqqqqqqqqqqqqqqqqqqqqqq");
            if (this.IsPatronExceedMaximumLoan(msPatronID, msPatronCategory, this.rsItemCat, this.rsSMD, this.rsLocation, this.riEligibility, "CT")) {
                this.errmessage = "Patron has exceeded the eligibility to charge/borrow items";
                tempDocValidate = false;
                return tempDocValidate;
            }
            iNoCirculated = this.GetNoCircByPatron(msPatronID);
            iNoCategoryElig = this.GetPatrCateEligNo(msPatronCategory);
            if (iNoCategoryElig > 0 && iNoCirculated + 1 > iNoCategoryElig) {
                this.errmessage = "Patron has exceeded maximum borrow items";
                tempDocValidate = false;
                return tempDocValidate;
            }
            iItemCateCirculated = this.GetNoCircByItem(msPatronID, this.rsItemCat);
            iItemCategoryMaxLoan = this.GetItemCateMaxLoan(this.rsItemCat);
            System.out.println("rrrrrrrrrrrrrr666");
            if (iItemCategoryMaxLoan > 0) {
                System.out.println("rrrrrrrrrrrrrr777");
                if (iItemCateCirculated + 1 > iItemCategoryMaxLoan) {
                    this.errmessage = "Patron has exceeded maximum borrow " + this.rsItemCat + " items.";
                    tempDocValidate = false;
                    return tempDocValidate;
                }
            }
            System.out.println("rrrrrrrrrrrrrr888");
            System.out.println("rrrrrrrrrrrrrr888" + this.rsRetStatus);
        }
        return tempDocValidate;
    }

    public boolean RetrieveAccessionDetails(String msAccessionNo) {
        boolean exist = false;
        String sql = "Select CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA, CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD From CTDOCM Where CT03DOCNO = '" + msAccessionNo + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.rsRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                    this.rsRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                    this.rsRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                    this.rsRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                    this.rsRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                    this.rsRetDueDate = String.valueOf(rsObj.getString("CT03DDATE"));
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

    public String GetPatronCatCode(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
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
        return this.msPatronCategory;
    }

    public boolean ValidateEligibility(String msPatronCategory, String sDocItemCategory, String sSMD, String sLocation) {
        boolean exist = false;
        String loca = this.GetBranch(sLocation);
        String sql = "Select GL27CATE,GL27ICAT,GL27PTYPE,GL27PLOAN,GL27ELIG, GL27GRACE1,GL27GRACE2,GL27GRACE3,GL27RSTR,GL27ALLOWOVD From GLELIG Where GL27CATE = '" + msPatronCategory + "' " + "And GL27ICAT = '" + sDocItemCategory + "' " + "And GL27SMD = '" + sSMD + "' " + "And GL27BRNC = '" + loca + "'";
        System.out.println("sql docvalidate : " + sql);
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
        System.out.println("GetBranch" + sql);
        String newGetBranch = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    newGetBranch = rsObj.getString("GL05BRNC");
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
        return newGetBranch;
    }

    private void RetrieveMaterialDetails(String txtMaterial) {
        Object sAllowOverdue = null;
        boolean bEligible = false;
        String sql = "Select CT03STAT, CT03ICAT, CT03LOCA, CT03SMD, CT03PATR From CTDOCM Where CT03MATNO = '" + txtMaterial + "' " + "AND (CT03STAT = 'A' OR CT03STAT = 'G')";
        System.out.println("RetrieveMaterialDetails" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                if (rsObj.next()) {
                    this.rsItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                    this.rsSMD = String.valueOf(rsObj.getString("CT03SMD"));
                    this.rsLocation = String.valueOf(rsObj.getString("CT03LOCA"));
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
    }

    public final boolean IsRequested(String vsMaterialNo, String vsPatron) {
        boolean tempIsRequested;
        block13: {
            tempIsRequested = false;
            String sSQLStmt = "SELECT COUNT(*) AS COUNT FROM CIINCO WHERE CI01MATNO = '" + vsMaterialNo + "' " + "AND UPPER(CI01REQUESTOR) = UPPER('" + vsPatron + "') ";
            System.out.println("IsRequested" + sSQLStmt);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sSQLStmt);
                    if (!rsObj.next()) {
                        tempIsRequested = false;
                        break block13;
                    }
                    int count = rsObj.getInt("COUNT");
                    if (count > 0) {
                        tempIsRequested = true;
                        break block13;
                    }
                    tempIsRequested = false;
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
        return tempIsRequested;
    }

    public final boolean IsPatronExceedMaximumLoan(String vsPatronID, String vsPatronCategory, String vsItemCategory, String vsSMD, String vsItemLocation, String viNumberEligible, String vsModule) throws SQLException {
        boolean tempIsPatronExceedMaximumLoan = false;
        boolean iMaxLoan = false;
        int iNoCirculated = 0;
        iNoCirculated = this.GetNoCirculated(vsPatronID, vsItemCategory, vsSMD, vsItemLocation, vsModule);
        int totiNoCirculated = iNoCirculated + 1;
        System.out.println(totiNoCirculated);
        int nviNumberEligible = Integer.parseInt(viNumberEligible);
        System.out.println(nviNumberEligible);
        System.out.println(totiNoCirculated > nviNumberEligible);
        if (totiNoCirculated > nviNumberEligible) {
            tempIsPatronExceedMaximumLoan = true;
        }
        System.out.println("tempIsPatronExceedMaximumLoan" + tempIsPatronExceedMaximumLoan);
        return tempIsPatronExceedMaximumLoan;
    }

    private int GetNoCirculated(String vsPatronID, String vsItemCat, String vsSMD, String vsLocation, String vsModule) throws SQLException {
        int tempGetNoCirculated = 0;
        String sSQLStmt = "Select Count(" + vsModule + "03STAT) As CIRCULATEDNO " + "From " + vsModule + "DOCM " + "Where " + vsModule + "03PATR = '" + vsPatronID + "' " + "And " + vsModule + "03STAT = 'C' " + "And " + vsModule + "03ICAT = '" + vsItemCat + "' " + "And " + vsModule + "03SMD = '" + vsSMD + "' " + "And " + vsModule + "03LOCA in ('" + vsLocation + "') ";
        System.out.println("GetNoCirculated" + sSQLStmt);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sSQLStmt);
                if (rsObj.next()) {
                    tempGetNoCirculated = rsObj.getInt("CIRCULATEDNO");
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
        return tempGetNoCirculated;
    }

    private int GetNoCircByPatron(String vsPatronID) throws SQLException {
        int GetNoCircByPatron;
        block12: {
            GetNoCircByPatron = 0;
            String sSQLStmt = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where UPPER(CI02PATR) = UPPER('" + vsPatronID + "') " + "And CI02FLAG = 'C' ";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        GetNoCircByPatron = 0;
                        break block12;
                    }
                    GetNoCircByPatron = rs.getInt("CIRCULATEDNO");
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
        return GetNoCircByPatron;
    }

    private int GetPatrCateEligNo(String vsPatronCat) throws SQLException {
        int iNoOfElig;
        block12: {
            iNoOfElig = 0;
            String sSQLStmt = "Select GL07CATE, GL07ELIG From GLCATE Where GL07CATE = '" + vsPatronCat + "' ";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) {
                        iNoOfElig = 0;
                        break block12;
                    }
                    iNoOfElig = rs.getInt("GL07ELIG");
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
        return iNoOfElig;
    }

    private int GetNoCircByItem(String vsPatronID, String vsItemCat) throws SQLException {
        int GetNoCircByItem = 0;
        String sSQLStmt = "Select Count(CT03ICAT) As CIRCULATEDITEMCATE From CICIRC, CTDOCM Where CI02DOCNO = CT03DOCNO And UPPER(CI02PATR) = UPPER('" + vsPatronID + "') " + "And CI02FLAG = 'C' " + "And CT03ICAT = '" + vsPatronID + "'";
        System.out.println("SQL GetNoCircByItem" + sSQLStmt);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (rs.next()) {
                    GetNoCircByItem = rs.getInt("CIRCULATEDITEMCATE");
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
        return GetNoCircByItem;
    }

    private int GetItemCateMaxLoan(String vsDocItemCat) throws SQLException {
        int iMaxLoan = 0;
        String sSQLStmt = "Select GL10ICAT, GL10ELIG From GLICAT Where GL10ICAT = '" + vsDocItemCat + "'";
        System.out.println("SQL GetItemCateMaxLoan" + sSQLStmt);
        Connection conn = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (rs.next()) {
                    iMaxLoan = rs.getInt("GL10ELIG");
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
        return iMaxLoan;
    }

    public final String GetLocations(String vsBranch) throws SQLException {
        String tempGetLocations = null;
        boolean hrs = false;
        String sSQLStmt = null;
        String sLocations = null;
        sSQLStmt = "SELECT GL05LOCA FROM GLLOCA WHERE GL05BRNC = '" + vsBranch + "' ";
        System.out.println("GetLocations" + sSQLStmt);
        Connection conn = null;
        try {
            try {
                if (rsObj.next()) {
                    while (true) {
                        sLocations = "'" + rsObj.getString("GL05LOCA") + "'";
                        sLocations = String.valueOf(sLocations) + ",'" + rsObj.getString("GL05LOCA") + "'";
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
        return tempGetLocations;
    }
}
