/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.claim;

import com.ilmu.global.DateFormatter;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.RecordTransaction;
import com.ilmu.global.connection.DBConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Claims {
    private String bindno;
    private String refno;
    private String title;
    private String binderName;
    private String datesent;
    private String dateexpected;

    public String getbindno() {
        return Handler.ifIsNull(this.bindno);
    }

    public String getrefno() {
        return Handler.ifIsNull(this.refno);
    }

    public String gettitle() {
        return Handler.ifIsNull(this.title);
    }

    public String getbinderName() {
        return Handler.ifIsNull(this.binderName);
    }

    public String getdatesent() {
        return Handler.ifIsNull(this.datesent);
    }

    public String getdateexpected() {
        return Handler.ifIsNull(this.dateexpected);
    }

    public Claims(String bindno, String refno, String title, String binderName, String datesent, String dateexpected) {
        this.bindno = bindno;
        this.refno = refno;
        this.title = title;
        this.binderName = binderName;
        this.datesent = datesent;
        this.dateexpected = dateexpected;
    }

    public static List<Claims> getBindClaimTable(String input_criteria, String search_type, String inputStartDate, String inputEndDate) {
        ArrayList<Claims> list = new ArrayList<Claims>();
        Connection conn = null;
        conn = DBConnection.getConnection();
        String query = "";
        DBConnection dbtype = new DBConnection();
        if (dbtype.getDBType().equals("mssql")) {
            System.out.println("sql here");
            query = "SELECT BI01BINDNO, BI01REFERENCE, (SELECT TOP 1 CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245') AS TITLE, GL39NAME, BI01DTSENT, BI01DTEXPECTED FROM BIMAST LEFT JOIN GLVEND ON GL39CODE = BI01BINDER WHERE BI01STATUS = 'S'  AND ";
        } else if (dbtype.getDBType().equals("oracle")) {
            System.out.println("oracle here");
            query = "SELECT BI01BINDNO, BI01REFERENCE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' AND rownum = 1) AS TITLE, GL39NAME, BI01DTSENT, BI01DTEXPECTED FROM BIMAST LEFT JOIN GLVEND ON GL39CODE = BI01BINDER WHERE BI01STATUS = 'S'  AND ";
        } else if (dbtype.getDBType().equals("mysql")) {
            System.out.println("MYSQL here");
            query = "SELECT BI01BINDNO, BI01REFERENCE, (SELECT CT05SRAW FROM CTPONT, CTTITL WHERE  CT06POINTER = CT05POINTER AND CT06MATNO = BI01CTRLNO AND CT06TAG = '245' LIMIT 1) AS TITLE, GL39NAME, BI01DTSENT, BI01DTEXPECTED FROM BIMAST LEFT JOIN GLVEND ON GL39CODE = BI01BINDER WHERE BI01STATUS = 'S'  AND ";
        }
        if (search_type.equals("bindercode")) {
            query = String.valueOf(query) + "UPPER(BI01BINDER) = UPPER('" + input_criteria + "')";
        } else if (search_type.equals("dateClaims")) {
            if (inputStartDate != "" && inputEndDate != "") {
                System.out.println("inputStartDate and inputEndDate");
                query = String.valueOf(query) + "BI01DTEXPECTED BETWEEN '" + inputStartDate + "' AND '" + inputEndDate + "' ";
            }
            if (inputStartDate != "" && inputEndDate == "") {
                System.out.println("inputStartDate");
                query = String.valueOf(query) + "BI01DTEXPECTED >= '" + inputStartDate + "' ";
            }
            if (inputStartDate == "" && inputEndDate != "") {
                System.out.println("inputEndDate");
                query = String.valueOf(query) + "BI01DTEXPECTED <= '" + inputEndDate + "' ";
            }
        }
        System.out.println("query getBindClaimTable : " + query);
        try {
            try {
                Statement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rs.next()) {
                    Claims loadtabledetail = new Claims(Handler.ifIsNull(rs.getString("BI01BINDNO")), Handler.ifIsNull(rs.getString("BI01REFERENCE")), Handler.getSubfield(Handler.ifIsNull(rs.getString("TITLE")), isbd), Handler.ifIsNull(rs.getString("GL39NAME")), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTSENT"))), Handler.ifIsNull(DateFormatter.DBToDisplayFormat(rs.getString("BI01DTEXPECTED"))));
                    list.add(loadtabledetail);
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
        return list;
    }

    public static boolean claimBind(String liferayLogin, String bindno, int total) throws SQLException, UnknownHostException {
        boolean bSuccessful = false;
        String[] newsbindno = bindno.split(",");
        int i = 0;
        while (i < total) {
            System.out.println("value is :" + newsbindno[i]);
            String gsModule = "BI";
            RecordTransaction.InsertAudit(gsModule, "BIO0001", newsbindno[i], liferayLogin);
            ++i;
        }
        return bSuccessful;
    }
}
