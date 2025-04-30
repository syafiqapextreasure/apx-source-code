/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.serial_master;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Serial_Master {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String controlNo = null;
    private String title = null;
    private String smd = null;
    private String language = null;
    private String department = null;
    private String frequency = null;
    private String vendor = null;
    private String vendorName = null;
    private String binder = null;
    private String binderName = null;
    private String publisher = null;
    private String publisherName = null;
    private String bibliographic = null;
    private String renewal = null;
    private String serialMode = null;
    private String mailType = null;
    private String requestor = null;
    private String requestorName = null;
    private String refNo = null;
    private String currency = null;
    private double exchRate = 0.0;
    private double fPrice = 0.0;
    private double lPrice = 0.0;
    private String cumIndx = null;
    private String contPg = null;
    private String stdOrd = null;
    private String bindtr = null;
    private String subjHead = null;
    private String irsIdx = null;
    private String titlPg = null;
    private String route = null;

    public Serial_Master(String controlNo, String title) {
        this.controlNo = controlNo;
        this.title = title;
    }

    public Serial_Master(String controlNo, String title, String smd, String language, String department, String frequency, String vendor, String vendorName, String binder, String binderName, String publisher, String publisherName, String bibliographic, String renewal, String serialMode, String mailType, String requestor, String requestorName, String refNo, String currency, double exchRate, double fPrice, double lPrice, String cumIndx, String contPg, String stdOrd, String bindtr, String subjHead, String irsIdx, String titlPg, String route) {
        this.controlNo = controlNo;
        this.title = title;
        this.smd = smd;
        this.language = language;
        this.department = department;
        this.frequency = frequency;
        this.vendor = vendor;
        this.vendorName = vendorName;
        this.binder = binder;
        this.binderName = binderName;
        this.publisher = publisher;
        this.publisherName = publisherName;
        this.bibliographic = bibliographic;
        this.renewal = renewal;
        this.serialMode = serialMode;
        this.mailType = mailType;
        this.requestor = requestor;
        this.requestorName = requestorName;
        this.refNo = refNo;
        this.currency = currency;
        this.exchRate = exchRate;
        this.fPrice = fPrice;
        this.lPrice = lPrice;
        this.cumIndx = cumIndx;
        this.contPg = contPg;
        this.stdOrd = stdOrd;
        this.bindtr = bindtr;
        this.subjHead = subjHead;
        this.irsIdx = irsIdx;
        this.titlPg = titlPg;
        this.route = route;
    }

    public Serial_Master(String smd, String language, String department, String frequency, String bibliographic, String serialMode) {
        this.smd = smd;
        this.language = language;
        this.department = department;
        this.frequency = frequency;
        this.bibliographic = bibliographic;
        this.serialMode = serialMode;
    }

    public String getControlNo() {
        return this.controlNo;
    }

    public String getTitle() {
        return Serial_Master.rawToDisplayFormat(this.title);
    }

    public String getSMD() {
        return this.smd;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public String getVendorCode() {
        return this.vendor;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public String getBinder() {
        return this.binder;
    }

    public String getBinderName() {
        return this.binderName;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getPublisherName() {
        return this.publisherName;
    }

    public String getBibliographic() {
        return this.bibliographic;
    }

    public String getRenewal() {
        return this.renewal;
    }

    public String getSerialMode() {
        return this.serialMode;
    }

    public String getMailType() {
        return this.mailType;
    }

    public String getRequestor() {
        return this.requestor;
    }

    public String getRequestorName() {
        return this.requestorName;
    }

    public String getRefNo() {
        return this.refNo;
    }

    public String getCurrency() {
        return this.currency;
    }

    public double getExchRate() {
        return this.exchRate;
    }

    public double getFPrice() {
        return this.fPrice;
    }

    public double getLPrice() {
        return this.lPrice;
    }

    public String getCumIndx() {
        return this.cumIndx;
    }

    public String getContPg() {
        return this.contPg;
    }

    public String getStdOrd() {
        return this.stdOrd;
    }

    public String getBindtr() {
        return this.bindtr;
    }

    public String getSubjHead() {
        return this.subjHead;
    }

    public String getIrsIdx() {
        return this.irsIdx;
    }

    public String getTitlPg() {
        return this.titlPg;
    }

    public String getRoute() {
        return this.route;
    }

    public static String rawToDisplayFormat(String raw) {
        String[] rawArray = raw.split("\\|");
        StringBuilder result = new StringBuilder();
        if (raw != null) {
            int i = 1;
            while (i < rawArray.length) {
                String splitData = rawArray[i].substring(1);
                if (splitData != null && splitData != "") {
                    result.append(splitData);
                    if (i != rawArray.length) {
                        result.append(" ");
                    }
                }
                ++i;
            }
        }
        return result.toString();
    }

    public static List<Serial_Master> SE01_DisplayAll() {
        ArrayList<Serial_Master> list = new ArrayList<Serial_Master>();
        String query = "SELECT SE01MATNO, CT05SRAW FROM SESERM LEFT JOIN CTPONT ON SESERM.SE01MATNO = CTPONT.CT06MATNO LEFT JOIN CTTITL ON CTPONT.CT06POINTER = CTTITL.CT05POINTER WHERE CT06TAG = '245' AND CT06STATUS = 'A' ORDER BY SE01MATNO ";
        System.out.println("ssas" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Master newSE01_DisplayAll = new Serial_Master(rs.getString("SE01MATNO"), rs.getString("CT05SRAW"));
                    list.add(newSE01_DisplayAll);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Serial_Master> SE01_SearchByMatno(String controlNoInput) {
        ArrayList<Serial_Master> list = new ArrayList<Serial_Master>();
        String query = "SELECT SE01MATNO, CT05SRAW FROM SESERM LEFT JOIN CTPONT ON SESERM.SE01MATNO = CTPONT.CT06MATNO LEFT JOIN CTTITL ON CTPONT.CT06POINTER = CTTITL.CT05POINTER WHERE CT06TAG = '245' AND CT06STATUS = 'A' AND CT06MATNO LIKE '%" + controlNoInput + "%' ORDER BY SE01MATNO ";
        System.out.println("Query" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Master newSE01_SearchByMatno = new Serial_Master(rs.getString("SE01MATNO"), rs.getString("CT05SRAW"));
                    list.add(newSE01_SearchByMatno);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static Serial_Master SE01_getDetailsByControlNo(String controlNo) {
        String query = "SELECT SE01MATNO, CT05SRAW, GL47DESC, GL26DESC, GL11NAME, GL49DESC, T1.SE01SUPPLIER, T2.GL39NAME AS Supplier, T1.SE01BINDER, T3.GL39NAME AS Binder, T1.SE01PUBLISHER, T4.GL39NAME AS Publisher, GL48DESC, SE01RENEWAL, SE05DESC, SE01MAIL, GL14PATR, GL14NAME, SE01PONO, SE01FOREX, SE01PRATE, SE01FPRICE, SE01LPRICE, SE01CUMIDX, SE01CONTPG, SE01STDORD, SE01BINDTR, SE01SUBJHEAD, SE01IRSIDX, SE01TITLPG, SE01ROUTE FROM SESERM T1 LEFT JOIN CTPONT ON (T1.SE01MATNO = CTPONT.CT06MATNO) LEFT JOIN CTTITL ON (CTPONT.CT06POINTER = CTTITL.CT05POINTER) LEFT JOIN GLSMD ON (T1.SE01SMD = GLSMD.GL47SMD) LEFT JOIN GLVEND T2 ON (T1.SE01SUPPLIER = T2.GL39CODE) LEFT JOIN GLVEND T3 ON (T1.SE01BINDER = T3.GL39CODE) LEFT JOIN GLVEND T4 ON (T1.SE01PUBLISHER = T4.GL39CODE) LEFT JOIN GLBIBS ON (T1.SE01SOURCE = GLBIBS.GL48SOURCE) LEFT JOIN SEMODE ON (T1.SE01MODE = SEMODE.SE05MODE) LEFT JOIN GLFREQ ON (T1.SE01FREQ = GLFREQ.GL49FREQ) LEFT JOIN GLINFO ON (T1.SE01LANGUAGE = GLINFO.GL26CODE) LEFT JOIN GLPATR ON (T1.SE01REQUESTOR = GLPATR.GL14PATR) LEFT JOIN GLDEPT ON (T1.SE01DEPT = GLDEPT.GL11DEPT) WHERE CT06TAG = '245' AND CT06STATUS = 'A' AND SE01MATNO = '" + controlNo + "' ";
        Serial_Master newSE01_getDetailsByControlNo = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    double prate = 0.0;
                    double fprice = 0.0;
                    double lprice = 0.0;
                    if (rs.getString("SE01PRATE") != null) {
                        prate = Double.parseDouble(rs.getString("SE01PRATE"));
                    }
                    if (rs.getString("SE01FPRICE") != null) {
                        fprice = Double.parseDouble(rs.getString("SE01FPRICE"));
                    }
                    if (rs.getString("SE01LPRICE") != null) {
                        lprice = Double.parseDouble(rs.getString("SE01LPRICE"));
                    }
                    newSE01_getDetailsByControlNo = new Serial_Master(rs.getString("SE01MATNO"), rs.getString("CT05SRAW"), rs.getString("GL47DESC"), rs.getString("GL26DESC"), rs.getString("GL11NAME"), rs.getString("GL49DESC"), Handler.ifIsNull(rs.getString("SE01SUPPLIER")), Handler.ifIsNull(rs.getString("Supplier")), rs.getString("SE01BINDER"), rs.getString("Binder"), rs.getString("SE01PUBLISHER"), rs.getString("Publisher"), rs.getString("GL48DESC"), rs.getString("SE01RENEWAL"), rs.getString("SE05DESC"), rs.getString("SE01MAIL"), rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("SE01PONO"), rs.getString("SE01FOREX"), prate, fprice, lprice, Handler.checkingChk(rs.getString("SE01CUMIDX")), Handler.checkingChk(rs.getString("SE01CONTPG")), Handler.checkingChk(rs.getString("SE01STDORD")), Handler.checkingChk(rs.getString("SE01BINDTR")), Handler.checkingChk(rs.getString("SE01SUBJHEAD")), Handler.checkingChk(rs.getString("SE01IRSIDX")), Handler.checkingChk(rs.getString("SE01TITLPG")), Handler.checkingChk(rs.getString("SE01ROUTE")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newSE01_getDetailsByControlNo;
    }

    public static Serial_Master SE01_getCodeByControlNo(String controlNo) {
        String query = "SELECT SE01SMD, SE01LANGUAGE, SE01DEPT, SE01FREQ, SE01SOURCE, SE01MODE FROM SESERM WHERE SE01MATNO = '" + controlNo + "' ";
        Serial_Master newSE01_getCodeByControlNo = null;
        System.out.println("QUERY" + query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    newSE01_getCodeByControlNo = new Serial_Master(rs.getString("SE01SMD"), rs.getString("SE01LANGUAGE"), rs.getString("SE01DEPT"), rs.getString("SE01FREQ"), rs.getString("SE01SOURCE"), rs.getString("SE01MODE"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newSE01_getCodeByControlNo;
    }

    public static List<Serial_Master> SE01_GetListBy(String marcTag, String tableName, String condition) {
        ArrayList<Serial_Master> list = new ArrayList<Serial_Master>();
        String query = "SELECT CT05SRAW, CT06MATNO FROM " + tableName + " " + "LEFT JOIN CTPONT ON " + tableName + ".CT05POINTER = CTPONT.CT06POINTER " + " " + "WHERE CT06STATUS = 'A' AND CT06TAG = '" + marcTag + "' AND CT05SCONV LIKE '" + condition + "%' ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Serial_Master newSE01_GetListBy = new Serial_Master(rs.getString("CT06MATNO"), rs.getString("CT05SRAW"));
                    list.add(newSE01_GetListBy);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<Serial_Master> searchByTitle(String criteria) {
        return Serial_Master.SE01_GetListBy("245", "CTTITL", criteria);
    }

    public static List<Serial_Master> searchByName(String criteria) {
        return Serial_Master.SE01_GetListBy("100", "CTAUTH", criteria);
    }

    public static List<Serial_Master> searchBySubject(String criteria) {
        return Serial_Master.SE01_GetListBy("650", "CTSUBJ", criteria);
    }

    public static List<Serial_Master> searchByPubl(String criteria) {
        return Serial_Master.SE01_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<Serial_Master> searchByPlaceOfPubl(String criteria) {
        return Serial_Master.SE01_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<Serial_Master> searchByYearOfPubl(String criteria) {
        return Serial_Master.SE01_GetListBy("260", "CTPUBL", criteria);
    }

    public static List<Serial_Master> searchBySeries(String criteria) {
        return Serial_Master.SE01_GetListBy("440", "CTSERI", criteria);
    }

    public static List<Serial_Master> searchByCallNo(String criteria) {
        return Serial_Master.SE01_GetListBy("090", "CTCALL", criteria);
    }

    public static List<Serial_Master> searchByISBN(String criteria) {
        return Serial_Master.SE01_GetListBy("020", "CTINDX", criteria);
    }

    public static List<Serial_Master> searchByISSN(String criteria) {
        return Serial_Master.SE01_GetListBy("022", "CTINDX", criteria);
    }

    public static List<Serial_Master> searchByNoteArea(String criteria) {
        return Serial_Master.SE01_GetListBy("500", "CTINDX", criteria);
    }

    public static boolean isExistControlNo(String controlNo) {
        String query = "SELECT COUNT(*) AS Count FROM SESERM WHERE SE01MATNO = '" + controlNo + "' ";
        boolean exist = false;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) < 1) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exist;
    }

    public static Serial_Master SE01_addNewPeriodicalsMaster(String controlNo, String smd, String language, String department, String frequency, String vendor, String binder, String publisher, String bibliographicSource, String renewalAlert, String serialMode, String requestor, String currency, String pubRate, String fPrice, String lPrice, String cIndex, String cPage, String sOrder, String bTreatment, String sHeading, String iIndexing, String tPage, String r) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        valueStr.put("SE01MATNO", controlNo);
        valueStr.put("SE01SUPPLIER", vendor);
        valueStr.put("SE01FOREX", currency);
        valueStr.put("SE01CUMIDX", cIndex);
        valueStr.put("SE01CONTPG", cPage);
        valueStr.put("SE01STDORD", sOrder);
        valueStr.put("SE01BINDTR", bTreatment);
        valueStr.put("SE01SUBJHEAD", sHeading);
        valueStr.put("SE01IRSIDX", iIndexing);
        valueStr.put("SE01TITLPG", tPage);
        valueStr.put("SE01ROUTE", r);
        if (pubRate != null && pubRate != "") {
            valueDouble.put("SE01PRATE", Double.parseDouble(pubRate));
        } else {
            valueDouble.put("SE01PRATE", 0.0);
        }
        if (pubRate != null && pubRate != "") {
            valueDouble.put("SE01FPRICE", Double.parseDouble(fPrice));
        } else {
            valueDouble.put("SE01FPRICE", 0.0);
        }
        System.out.println("smd" + smd);
        if (!smd.equals("0")) {
            valueStr.put("SE01SMD", smd);
        }
        if (!language.equals("0")) {
            valueStr.put("SE01LANGUAGE", language);
        }
        if (!department.equals("0")) {
            valueStr.put("SE01DEPT", department);
        }
        if (!frequency.equals("0")) {
            valueStr.put("SE01FREQ", frequency);
        }
        if (!binder.equals("")) {
            valueStr.put("SE01BINDER", binder);
        }
        if (!publisher.equals("")) {
            valueStr.put("SE01PUBLISHER", publisher);
        }
        if (!bibliographicSource.equals("")) {
            valueStr.put("SE01SOURCE", bibliographicSource);
        }
        if (!renewalAlert.equals("")) {
            valueStr.put("SE01RENEWAL", renewalAlert);
        }
        if (!serialMode.equals("")) {
            valueStr.put("SE01MODE", serialMode);
        }
        if (!requestor.equals("")) {
            valueStr.put("SE01REQUESTOR", requestor);
        }
        if (!lPrice.equals("")) {
            valueDouble.put("SE01LPRICE", Double.parseDouble(lPrice));
        }
        String query = QueryBuilder.createInsertQuery("SESERM", valueStr, null, valueDouble);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return null;
    }

    public static boolean SE01_editPeriodicalsMaster(String controlNo, String smd, String language, String department, String frequency, String vendor, String binder, String publisher, String bibliographicSource, String renewalAlert, String serialMode, String requestor, String currency, String pubRate, String fPrice, String lPrice, String cIndex, String cPage, String sOrder, String bTreatment, String sHeading, String iIndexing, String tPage, String r) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Double> valueDouble = new HashMap<String, Double>();
        HashMap<String, String> condition = new HashMap<String, String>();
        System.out.println("SMD11" + smd);
        condition.put("SE01MATNO", controlNo);
        valueStr.put("SE01SUPPLIER", vendor);
        valueStr.put("SE01FOREX", currency);
        valueStr.put("SE01CUMIDX", cIndex);
        valueStr.put("SE01CONTPG", cPage);
        valueStr.put("SE01STDORD", sOrder);
        valueStr.put("SE01BINDTR", bTreatment);
        valueStr.put("SE01SUBJHEAD", sHeading);
        valueStr.put("SE01IRSIDX", iIndexing);
        valueStr.put("SE01TITLPG", tPage);
        valueStr.put("SE01ROUTE", r);
        valueDouble.put("SE01PRATE", Double.parseDouble(pubRate));
        valueDouble.put("SE01FPRICE", Double.parseDouble(fPrice));
        if (!smd.equals("")) {
            valueStr.put("SE01SMD", smd);
        } else {
            valueStr.put("SE01SMD", null);
        }
        if (!language.equals("")) {
            valueStr.put("SE01LANGUAGE", language);
        } else {
            valueStr.put("SE01LANGUAGE", null);
        }
        if (!department.equals("")) {
            valueStr.put("SE01DEPT", department);
        } else {
            valueStr.put("SE01DEPT", null);
        }
        if (!frequency.equals("")) {
            valueStr.put("SE01FREQ", frequency);
        } else {
            valueStr.put("SE01FREQ", null);
        }
        if (!binder.equals("")) {
            valueStr.put("SE01BINDER", binder);
        } else {
            valueStr.put("SE01BINDER", null);
        }
        if (!publisher.equals("")) {
            valueStr.put("SE01PUBLISHER", publisher);
        } else {
            valueStr.put("SE01PUBLISHER", "0");
        }
        if (!bibliographicSource.equals("")) {
            valueStr.put("SE01SOURCE", bibliographicSource);
        } else {
            valueStr.put("SE01SOURCE", null);
        }
        if (!renewalAlert.equals("")) {
            valueStr.put("SE01RENEWAL", renewalAlert);
        } else {
            valueStr.put("SE01RENEWAL", "0");
        }
        if (!serialMode.equals("")) {
            valueStr.put("SE01MODE", serialMode);
        } else {
            valueStr.put("SE01MODE", null);
        }
        if (!requestor.equals("")) {
            valueStr.put("SE01REQUESTOR", requestor);
        } else {
            valueStr.put("SE01REQUESTOR", null);
        }
        if (!lPrice.equals("")) {
            valueDouble.put("SE01LPRICE", Double.parseDouble(lPrice));
        } else {
            valueStr.put("SE01LPRICE", null);
        }
        String query = QueryBuilder.createUpdateQuery("SESERM", valueStr, null, valueDouble, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean SE01_deletePeriodicalsMaster(String controlNo) {
        String query = "DELETE FROM SESERM WHERE SE01MATNO = '" + controlNo + "' ";
        int orddCount = Integer.parseInt(DBQuery.getSingleDataAsData("Count(SE03MATNO) AS Data", "SEORDD", "SE03MATNO = '" + controlNo + "'"));
        if (orddCount == 0) {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
                return true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            finally {
                try {
                    c.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
