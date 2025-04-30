/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PatronSQL {
    public static String chkValidPatrSQL(String GL14PATR) {
        String query = "SELECT COUNT(GL14PATR) AS Count FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "'";
        return query;
    }

    public static String getMembershipDate(String GL14PATR) {
        String query = "Select GL14EXPDATE,GL14PATR From GLPATR Where GL14PATR = '" + GL14PATR + "'";
        return query;
    }

    public static String chkPatrActive(String GL14PATR) {
        String query = "SELECT COUNT(*) AS Count FROM GLPATR, GLSTAT WHERE GL14STAT=GL08STAT and GL14PATR = '" + GL14PATR + "' and GL08ACTION1='Y'";
        return query;
    }

    public static String getFineLimit(String GL14PATR) {
        String sql = "SELECT GL07FINELIMIT FROM GLCATE, GLPATR WHERE GL14CATE=GL07CATE AND GL14PATR= '" + GL14PATR + "' ";
        return sql;
    }

    public static String getTotalFineCharged(String msPatronId) {
        String sql = "SELECT SUM(RE01AMT) AS totalcharged FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'D' AND  RE01PATR = '" + msPatronId + "'";
        return sql;
    }

    public static String getMsGetTotalPaid(String msPatronId) {
        String sql = "SELECT SUM(RE01AMT) AS totalpaid FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'C' AND  RE01PATR = '" + msPatronId + "'";
        return sql;
    }

    public static String patronCateAllowedOverdue(String msPatronCategory) {
        String sql = "SELECT GL07ALLOWOVD FROM GLCATE WHERE GL07CATE = '" + msPatronCategory + "'";
        return sql;
    }

    public static String getNoMsGetItemOverdue(String msPatronId) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        String date = ft.format(dNow);
        String sql = "SELECT COUNT(*) as count FROM CICIRC WHERE CI02FLAG ='C'AND CI02DDATE < " + date + " " + "AND CI02PATR = '" + msPatronId + "'";
        return sql;
    }

    public static String getMsPatronStatusDesc(String GL14PATR) {
        String query = "SELECT GL08DESC FROM GLPATR, GLSTAT WHERE GL08STAT=GL14STAT and GL14PATR = '" + GL14PATR + "'";
        return query;
    }

    public static String getMsPatronStatusCode(String msPatronID) {
        String query = "SELECT GL14STAT FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        return query;
    }

    public static String getMsPatronStatDesc(String msPatronID) {
        String query = "SELECT GL08DESC FROM GLSTAT WHERE GL08STAT= '" + msPatronID + "' ";
        return query;
    }

    public static String getMsPatronCatCode(String msPatronID) {
        String query = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        return query;
    }

    public static String getMsPatronBranchCode(String msPatronID) {
        String query = "SELECT GL14BRNC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        return query;
    }

    public static String getMsChkPatronElig(String msPatronCategory) {
        String query = "SELECT * from GLCATE where GL07CATE='" + msPatronCategory + "'";
        return query;
    }

    public static String getMsPatronCanDischarge(String GL14PATR) {
        String query = "Select GL08ACTION2 From GLPATR, GLSTAT Where GL14STAT=GL08STAT and GL14PATR = '" + GL14PATR + "'";
        return query;
    }
}
