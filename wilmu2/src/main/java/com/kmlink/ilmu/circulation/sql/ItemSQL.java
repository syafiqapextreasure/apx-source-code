/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.sql;

import com.kmlink.ilmu.shared.global.connection.DBConnection;

public class ItemSQL {
    public static String getMsItemBrnc(String msdocno) {
        String sql = "SELECT GL05BRNC FROM CTDOCM, GLLOCA WHERE GL05LOCA = CT03LOCA AND CT03DOCNO = '" + msdocno + "'";
        return sql;
    }

    public static String getMsCircStatus(String msdocno) {
        String sql = "SELECT CT03DOCNO, CT03STAT FROM CTDOCM WHERE CT03DOCNO = '" + msdocno + "'";
        return sql;
    }

    public static String getMsAllowBorrowItem(String vsAccessionNo, String msPatronCategory) {
        String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03DOCNO = '" + vsAccessionNo + "' " + " AND  GL27CATE = '" + msPatronCategory + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
        return sql;
    }

    public static String getMsChkResvStat(String msPatronID, String msAccessionNo) {
        DBConnection db = new DBConnection();
        String sql = null;
        if (DBConnection.getDBType().equals("mssql")) {
            sql = "SELECT TOP 1 CI03PATR,CI03DOCNO from CIRESV where CI03DOCNO='" + msAccessionNo + "' ";
        } else if (DBConnection.getDBType().equals("oracle")) {
            sql = "SELECT CI03PATR,CI03DOCNO from CIRESV where CI03DOCNO='" + msAccessionNo + "' AND ROWNUM=1";
        } else if (DBConnection.getDBType().equals("mysql")) {
            sql = "SELECT CI03PATR,CI03DOCNO from CIRESV where CI03DOCNO='" + msAccessionNo + "' limit 1";
        }
        return sql;
    }

    public static String getMsReservedPatron(String msAccession) {
        String sql = "SELECT GL14NAME, GL14PATR FROM GLPATR, CIRESV WHERE GL14PATR=CI03PATR AND CI03DOCNO='" + msAccession + "' AND CI03NSTAT='A'";
        return sql;
    }

    public static String getMsItemStatus(String msAccessionNo) {
        String sql = "Select DESCRIPTION, CODE from CTDOCM, FNDCODE where CODE=CT03STAT and CT03DOCNO='" + msAccessionNo + "' AND FCODE='E'";
        return sql;
    }

    public static String getMsCirculatedPatrDetails(String msAccessionNo) {
        String sql = "Select GL14PATR, GL14NAME, CI02DDATE from CICIRC, GLPATR where CI02PATR=GL14PATR and CI02FLAG = 'C' and CI02DOCNO = '" + msAccessionNo + "' ";
        return sql;
    }

    public static String getMsItemEligibility(String msRetItemCat, String msRetSMD, String msPatronCategory, String msPatronBranch) {
        String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + msRetItemCat + "'" + "AND GL27SMD = '" + msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msPatronBranch + "'";
        return sql;
    }

    public static String getMsCircSattelite() {
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC= 'CIRBYLOCATION'";
        return sql;
    }

    public static String getMsPatrSatelitteBrnc(String msPatronID) {
        String sql = "SELECT GL05BRNC FROM GLPATR,GLLOCA WHERE GL14PATR= '" + msPatronID + "' AND GL05BRNC=GL14BRNC";
        return sql;
    }

    public static String getMsChkResvStat(String msRetMatNo) {
        String sql = "select count(*) As ReservedNO from ciresv where CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X'";
        return sql;
    }
}
