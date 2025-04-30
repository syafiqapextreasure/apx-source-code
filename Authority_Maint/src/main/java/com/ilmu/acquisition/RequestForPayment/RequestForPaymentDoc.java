/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.acquisition.RequestForPayment;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestForPaymentDoc {
    private String ISBN;
    private String DueDate;
    private String PickVar;
    private String CollectionDays;
    private String OfficerName;
    private String OfficerDesign;
    private String AccMatNo;
    private String PatrOffTel;
    private String Location;
    private String Branch;
    private static Map<String, String> columnName1 = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("AccMatNo", "CT03DOCNO");
            this.put("PatrOffTel", "GL28FAX");
            this.put("ISBN", "CT05SRAW");
            this.put("DueDate", "GL39NAME");
            this.put("PickVar", "GL39ADD1");
            this.put("CollectionDays", "GL28MSGDELAY");
            this.put("OfficerName", "GL14NAME");
            this.put("OfficerDesign", "GL69DESC");
            this.put("Location", "GL05DESC");
            this.put("Branch", "GL71DESC");
        }
    };
    private static Map<String, String> columnName = new HashMap<String, String>(){
        private static final long serialVersionUID = 1L;
        {
            this.put("AccMatNo", "CT03DOCNO");
            this.put("PatrOffTel", "GL28FAX");
            this.put("ISBN", "CT05SRAW");
            this.put("DueDate", "GL39NAME");
            this.put("PickVar", "GL39ADD1");
            this.put("CollectionDays", "GL28MSGDELAY");
            this.put("OfficerName", "GL14NAME");
            this.put("OfficerDesign", "GL69DESC");
            this.put("Location", "GL05DESC");
            this.put("Branch", "GL71DESC");
        }
    };

    public RequestForPaymentDoc() {
    }

    public RequestForPaymentDoc(String Location, String Branch) {
        this.Location = Location;
        this.Branch = Branch;
    }

    public RequestForPaymentDoc(String ISBN) {
        this.ISBN = ISBN;
    }

    public RequestForPaymentDoc(String PatrOffTel, String CollectionDays, String OfficerName, String OfficerDesign) {
        this.PatrOffTel = PatrOffTel;
        this.CollectionDays = CollectionDays;
        this.OfficerName = OfficerName;
        this.OfficerDesign = OfficerDesign;
        System.out.println(PatrOffTel);
    }

    public static String getColumnName(String name) {
        return columnName1.get(name);
    }

    public String getAccNo() {
        return this.AccMatNo;
    }

    public String getISBN() {
        System.out.println("wwww");
        return Handler.ifIsNull(this.ISBN);
    }

    public String getDueDate() {
        return this.DueDate;
    }

    public String getPickVar() {
        return this.PickVar;
    }

    public String getCollectionDays() {
        System.out.println(this.CollectionDays);
        return this.CollectionDays;
    }

    public String getLocation() {
        return this.Location;
    }

    public String getBranch() {
        return this.Branch;
    }

    public String getPatrOffTel() {
        return this.PatrOffTel;
    }

    public String getOfficerDesign() {
        return this.OfficerDesign;
    }

    public String getOfficerName() {
        return this.OfficerName;
    }

    public static List<RequestForPaymentDoc> previewList(String supplier, String invno) {
        ArrayList<RequestForPaymentDoc> requestForPayment = new ArrayList<RequestForPaymentDoc>();
        RequestForPaymentDoc doc = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            try {
                connection = DBConnection.getConnection();
                statement = connection.createStatement();
                String sql = "SELECT AC05SUPPLIER,AC05INVNO,AC05INVDATE,AC05INVTIME,AC05ORDER,AC05PONO,AC05AMT,AC05DESC,AC05PRATE,AC05FPRICE,AC05COPY,AC05FOREX FROM ACINVO where AC05SUPPLIER='" + supplier + "' and ac05invno='" + invno + "' and ac05stat='N'";
                rs = statement.executeQuery(sql);
                System.out.println(sql);
                while (rs.next()) {
                    System.out.println("Supplier" + rs.getString("AC05SUPPLIER"));
                    doc = new RequestForPaymentDoc(rs.getString("AC05SUPPLIER"), rs.getString("AC05INVNO"));
                    requestForPayment.add(doc);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    rs.close();
                    statement.close();
                    connection.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return requestForPayment;
    }
}
