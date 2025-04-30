/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.returnIll;

import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.ill.returnIll.returnILL;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ClickReturn {
    static String errmessage = "";
    private static String msRemaining;
    private static String msDeleted;

    public String getErrMessage() {
        return errmessage;
    }

    public boolean cmdOK_Click(String sReferenceNo, String sControlNo, String iCopies, int total, String liferayLogin) throws SQLException, UnknownHostException {
        boolean bSuccessfull = false;
        int iCopiesAvailable = 0;
        String[] sReferenceNo2 = sReferenceNo.split(",");
        String[] sControlNo2 = sControlNo.split(",");
        String[] iCopies2 = iCopies.split(",");
        int i = 0;
        while (i < total) {
            int intiCopies2 = Integer.parseInt(iCopies2[i]);
            iCopiesAvailable = returnILL.CopiesAvailable(sControlNo2[i], sReferenceNo2[i]);
            if (iCopiesAvailable < intiCopies2) {
                bSuccessfull = false;
                errmessage = "The process of Control No : " + sControlNo2[i] + " " + "will be terminated as the number of book return " + "is not equal to the number of book received.";
            } else {
                bSuccessfull = ClickReturn.UpdateCIOUTR(sReferenceNo2[i]);
                if (bSuccessfull && (bSuccessfull = ClickReturn.DeleteCTDOCM(sControlNo2[i], sReferenceNo2[i]))) {
                    ClickReturn.UpdateILREQC(sReferenceNo2[i]);
                }
                String ref = String.valueOf(sReferenceNo2[i]) + ", " + sControlNo2[i];
                String gsModule = "CI";
                RecordTransaction.InsertAudit(gsModule, "CIU0002", ref, liferayLogin);
            }
            ++i;
        }
        return bSuccessfull;
    }

    public static String GetRecAcc(String vsRefNo) {
        String sql = "SELECT CI04REQUEST,CI04RECACC FROM CIOUTR WHERE CI04REQUEST = '" + vsRefNo + "' ";
        Connection conn = null;
        String tempGetRecAcc = null;
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    tempGetRecAcc = rs.getString("CI04RECACC");
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
        return tempGetRecAcc;
    }

    public static final boolean CheckAccessionAvailable(String vsRefNo, String vsControlNo) {
        boolean tempCheckAccessionAvailable = false;
        String sSQLStmt = null;
        String sAccession = null;
        String sRecAcc = null;
        Connection conn = null;
        sSQLStmt = "SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO = '" + vsRefNo + "' " + "AND CT03STAT = 'A'";
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (!rs.next()) {
                    tempCheckAccessionAvailable = false;
                } else {
                    tempCheckAccessionAvailable = true;
                    sRecAcc = ClickReturn.GetRecAcc(vsRefNo);
                    if (sRecAcc.indexOf(sAccession = Handler.ifIsNull(rs.getString("CT03DOCNO"))) + 1 > 0 && !ClickReturn.UpdateCTDOCM(sAccession, vsControlNo)) {
                        tempCheckAccessionAvailable = false;
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
        return tempCheckAccessionAvailable;
    }

    public static boolean UpdateILREQC(String vsReferenceNumber) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("IL01REQST0", vsReferenceNumber);
        valueStr.put("IL01STATUS", "04");
        String query = QueryBuilder.createUpdateQuery("ILREQC", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean UpdateCTDOCM(String vsAccessionNo, String vsControlNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CT03DOCNO", vsAccessionNo);
        condition.put("CT03MATNO", vsControlNo);
        valueStr.put("CT03STAT", "J");
        valueStr.put("CT03LASTACT", DateTime.getTodaySystemDate());
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static boolean UpdateCIOUTR(String vsReferenceNo) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("CI04REQUEST", vsReferenceNo);
        valueStr.put("CI04DTRETURNED", DateTime.getTodaySystemDate());
        valueStr.put("CI04RETFLAG", "P");
        String query = QueryBuilder.createUpdateQuery("CIOUTR", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        return isSuccess;
    }

    public static final boolean DeleteCTDOCM(String vsControlNo, String vsReferenceNo) throws SQLException {
        boolean tempDeleteCTDOCM;
        block12: {
            tempDeleteCTDOCM = false;
            String sSQLStmt = null;
            String sRemaining = null;
            String sDeleted = null;
            String sAccession = null;
            sSQLStmt = "SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO = '" + vsControlNo + "' " + "AND CT03DOCNO IN (" + returnILL.ConstructInStatementForAccessionNo(vsReferenceNo) + ")";
            Connection conn = null;
            try {
                try {
                    Statement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sSQLStmt);
                    if (!rs.next()) break block12;
                    sAccession = Handler.ifIsNull(rs.getString("CT03DOCNO"));
                    ClickReturn.DeleteAccessions(vsControlNo, returnILL.ConstructInStatementForAccessionNo(vsReferenceNo));
                    if (ClickReturn.IsExistsInCirc(vsControlNo, sAccession)) {
                        sRemaining = String.valueOf(sRemaining) + sAccession + ", ";
                        break block12;
                    }
                    sDeleted = String.valueOf(sDeleted) + sAccession + ", ";
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
        return tempDeleteCTDOCM;
    }

    public static final boolean IsExistsInCirc(String vsControlNo, String vsAccessionNumber) {
        boolean tempIsExistsInCirc = false;
        boolean hrs = false;
        String sSQLStmt = "SELECT COUNT(*) AS COUNT FROM CICIRC WHERE CI02MATNO = '" + vsControlNo + "' " + "AND CI02DOCNO = '" + vsAccessionNumber + "'";
        Connection conn = null;
        try {
            try {
                int count;
                conn = DBConnection.getConnection();
                Statement stmt = null;
                ResultSet rs = null;
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                if (rs.next() && (count = rs.getInt("COUNT")) > 0) {
                    tempIsExistsInCirc = true;
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
        return tempIsExistsInCirc;
    }

    public static final boolean DeleteAccessions(String vsControlNo, String vsAccessionsString) {
        boolean tempDeleteAccessions = false;
        boolean hrs = false;
        String sSQLStmt = "DELETE FROM CTDOCM WHERE CT03MATNO = '" + vsControlNo.toString() + "' " + "AND CT03DOCNO IN (" + vsAccessionsString + ")";
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(sSQLStmt);
                tempDeleteAccessions = delete.execute();
                tempDeleteAccessions = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.commit();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.commit();
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tempDeleteAccessions;
    }
}
