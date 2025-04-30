/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.serial.Renewal;

import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.global.serial.IssuesPattern;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Renewal {
    private static Connection c = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private String SE07ORDER = null;
    private String SE07CRDATE = null;
    private String TITLE = null;
    private String ISBN = null;
    private String SE07LPRICE = null;
    private String SE07COPIES = null;
    private String SE07RECEIVED = null;
    private String SE07STAT = null;
    private String quantity = null;
    private String SE07FOREX = null;
    private String SE07FPRICE = null;
    private String total = null;
    private String localtotal = null;
    private String SE07STATUS = null;
    private String SE07CTRLNO = null;
    private String edition = null;
    private String publication = null;
    private String bdraf = null;
    private String bdate = null;
    private String orderdate = null;
    private String invoice = null;
    private String SE07PRATE = null;
    private String SE03VOLFROM = null;
    private String SE03ISSFROM = null;
    private String SE03SUPPLIER = null;
    private String VENDNAME = null;
    private String SE03FREQ = null;
    private String freq = null;
    private String SE03ISSUES = null;
    private String forex = null;
    private String code = null;
    private String desc = null;
    private String type = null;
    private String time = null;
    private String SE03DTSTART = null;
    private String SE03DTSTOP = null;
    private String newVolume = null;
    private String SE03MATNO = null;
    private String SE06FREQ = null;
    private String SE06MATNO = null;
    private String SE03VOLTO = null;
    private String SE03ISSTO = null;
    private String SE06CPYNO = null;

    public Renewal(String SE07ORDER, String SE07STATUS, String SE07CTRLNO, String TITLE, String ISBN, String quantity, String SE07FOREX, String SE07FPRICE, String SE07LPRICE, String SE07PRATE, String total) {
        this.SE07ORDER = SE07ORDER;
        this.SE07STATUS = SE07STATUS;
        this.SE07CTRLNO = SE07CTRLNO;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE07FOREX = SE07FOREX;
        this.SE07FPRICE = SE07FPRICE;
        this.SE07LPRICE = SE07LPRICE;
        this.SE07PRATE = SE07PRATE;
        this.total = total;
    }

    public Renewal(String SE07ORDER, String SE07CRDATE, String TITLE, String ISBN, String quantity, String SE07FOREX, String SE07FPRICE, String total, String localtotal, String SE07STATUS, String SE07CTRLNO, String edition, String publication) {
        this.SE07ORDER = SE07ORDER;
        this.SE07CRDATE = SE07CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE07FOREX = SE07FOREX;
        this.SE07FPRICE = SE07FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.SE07STATUS = SE07STATUS;
        this.SE07CTRLNO = SE07CTRLNO;
        this.edition = edition;
        this.publication = publication;
    }

    public Renewal(String SE07ORDER, String SE07CRDATE, String TITLE, String ISBN, String quantity, String SE07FOREX, String SE07FPRICE, String total, String localtotal, String SE07STATUS, String SE07CTRLNO, String edition, String publication, String invoice, String bdraf, String bdate, String orderdate) {
        this.SE07ORDER = SE07ORDER;
        this.SE07CRDATE = SE07CRDATE;
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.SE07FOREX = SE07FOREX;
        this.SE07FPRICE = SE07FPRICE;
        this.total = total;
        this.localtotal = localtotal;
        this.SE07STATUS = SE07STATUS;
        this.SE07CTRLNO = SE07CTRLNO;
        this.edition = edition;
        this.publication = publication;
        this.invoice = invoice;
        this.bdraf = bdraf;
        this.bdate = bdate;
        this.orderdate = orderdate;
    }

    public Renewal(String TITLE, String ISBN, String SE07ORDER, String SE03VOLFROM, String newVolume, String SE03ISSFROM, String SE03SUPPLIER, String VENDNAME, String SE03FREQ, String freq, String SE03COPIES, String SE03ISSUES, String SE03FOREX, String forex, String SE07PRATE, String SE03FPRICE, String SE03LPRICE, String SE03DTSTART, String SE03DTSTOP, String SE03MATNO) {
        this.TITLE = TITLE;
        this.ISBN = ISBN;
        this.SE07ORDER = SE07ORDER;
        this.SE03VOLFROM = SE03VOLFROM;
        this.newVolume = newVolume;
        this.SE03ISSFROM = SE03ISSFROM;
        this.SE03SUPPLIER = SE03SUPPLIER;
        this.VENDNAME = VENDNAME;
        this.SE03FREQ = SE03FREQ;
        this.freq = freq;
        this.SE07COPIES = SE03COPIES;
        this.SE03ISSUES = SE03ISSUES;
        this.SE07FOREX = SE03FOREX;
        this.forex = forex;
        this.SE07PRATE = SE07PRATE;
        this.SE07FPRICE = SE03FPRICE;
        this.SE07LPRICE = SE03LPRICE;
        this.SE03DTSTART = SE03DTSTART;
        this.SE03DTSTOP = SE03DTSTOP;
        this.SE03MATNO = SE03MATNO;
    }

    public Renewal(String SE06MATNO, String SE03VOLFROM, String SE03VOLTO, String SE03ISSFROM, String SE03ISSTO, String SE03DTSTART, String SE03DTSTOP, String SE06FREQ, String SE06CPYNO, String SE03ISSUES) {
        this.SE06MATNO = SE06MATNO;
        this.SE03VOLFROM = SE03VOLFROM;
        this.SE03VOLTO = SE03VOLTO;
        this.SE03ISSFROM = SE03ISSFROM;
        this.SE03ISSTO = SE03ISSTO;
        this.SE03DTSTART = SE03DTSTART;
        this.SE03DTSTOP = SE03DTSTOP;
        this.SE06FREQ = SE06FREQ;
        this.SE06CPYNO = SE06CPYNO;
        this.SE03ISSUES = SE03ISSUES;
    }

    public Renewal(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Renewal(String code, String desc, String time, String type) {
        this.code = code;
        this.desc = desc;
        this.time = time;
        this.type = type;
    }

    public String getSE07ORDER() {
        return this.SE07ORDER;
    }

    public String getSE07CRDATE() {
        return this.SE07CRDATE;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String getSE07LPRICE() {
        return this.SE07LPRICE;
    }

    public String getSE07COPIES() {
        return this.SE07COPIES;
    }

    public String getSE07RECEIVED() {
        return this.SE07RECEIVED;
    }

    public String getSE07STAT() {
        return this.SE07STAT;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public String getSE07FOREX() {
        return this.SE07FOREX;
    }

    public String getSE07FPRICE() {
        return this.SE07FPRICE;
    }

    public String getTotal() {
        return this.total;
    }

    public String getLocalTotal() {
        return this.localtotal;
    }

    public String getSE07STATUS() {
        return this.SE07STATUS;
    }

    public String getSE07CTRLNO() {
        return this.SE07CTRLNO;
    }

    public String getEdition() {
        return this.edition;
    }

    public String getPublication() {
        return this.publication;
    }

    public String geOrderdate() {
        return this.orderdate;
    }

    public String getBdraf() {
        return this.bdraf;
    }

    public String getBDate() {
        return this.bdate;
    }

    public String getInvoice() {
        return this.invoice;
    }

    public String getSE07PRATE() {
        return this.SE07PRATE;
    }

    public String getSE03VOLFROM() {
        return this.SE03VOLFROM;
    }

    public String getSE03ISSFROM() {
        return this.SE03ISSFROM;
    }

    public String getSE03SUPPLIER() {
        return this.SE03SUPPLIER;
    }

    public String getVENDNAME() {
        return this.VENDNAME;
    }

    public String getSE03FREQ() {
        return this.SE03FREQ;
    }

    public String getfreq() {
        return this.freq;
    }

    public String getSE03ISSUES() {
        return this.SE03ISSUES;
    }

    public String getforex() {
        return this.forex;
    }

    public String getcode() {
        return this.code;
    }

    public String getdesc() {
        return this.desc;
    }

    public String gettype() {
        return this.type;
    }

    public String gettime() {
        return this.time;
    }

    public String getSE03DTSTART() {
        return this.SE03DTSTART;
    }

    public String getSE03DTSTOP() {
        return this.SE03DTSTOP;
    }

    public String getnewVolume() {
        return this.newVolume;
    }

    public String getSE03MATNO() {
        return this.SE03MATNO;
    }

    public String getSE06FREQ() {
        return this.SE06FREQ;
    }

    public String getSE06MATNO() {
        return this.SE06MATNO;
    }

    public String getSE03VOLTO() {
        return this.SE03VOLTO;
    }

    public String getSE03ISSTO() {
        return this.SE03ISSTO;
    }

    public String getSE06CPYNO() {
        return this.SE06CPYNO;
    }

    private static String GetSQLStmt(String criteria, String sVendor, String value, String value1, String ordermode) {
        System.out.println("Criteria" + criteria);
        DBConnection dbtype = new DBConnection();
        String sSQLStmt = null;
        String gsOrderNo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String sTodayDate = sdf.format(cal.getTime());
        sVendor = Handler.convUpperCase(sVendor);
        sSQLStmt = gsOrderNo == "" ? "Select SEORDD.*,t3.CT05SRAW as title,t5.ct05sraw as issn From SEORDD LEFT JOIN SESERM ON SE03MATNO=SE01MATNO inner join ctpont t2 on t2.CT06MATNO = SEORDD.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = SEORDD.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where SE03SUPPLIER = '" + sVendor + "' " + "And (SE03STAT BETWEEN '20' AND '39' " + "OR SE03STAT BETWEEN '44' AND '59' " + "OR SE03STAT BETWEEN '60' AND '79') " + "AND SE03STAT NOT IN ('24','29','45','47','61','62')" : "Select SEORDD.*, t3.CT05SRAW as title,t5.ct05sraw as issn From SEORDD inner join ctpont t2 on t2.CT06MATNO = SEORDD.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = SEORDD.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER Where SE03ORDER = '" + gsOrderNo + "' " + "And SE03MATNO IS NOT NULL " + "And (SE03STAT BETWEEN '20' AND '39' " + "OR SE03STAT BETWEEN '44' AND '59' " + "OR SE03STAT BETWEEN '60' AND '79') " + "AND SE03STAT NOT IN ('24','29','45','47','61','62')";
        String msCondition = "AND SE03MATNO IS NOT NULL AND (SE03STAT BETWEEN '20' AND '39' OR SE03STAT BETWEEN '44' AND '59' OR SE03STAT BETWEEN '60' AND '79') AND SE03STAT NOT IN ('24','29','45','47','61','62')";
        if (criteria.equals("all")) {
            if (gsOrderNo == "") {
                msCondition = String.valueOf(msCondition) + " And  SE03DTSTOP <= '" + sTodayDate + "' ";
            }
        } else if (criteria.equals("orderdate")) {
            if (value != "" && value1 != "") {
                msCondition = String.valueOf(msCondition) + " And  SE03DTSTOP Between '" + DateTime.displayToDBFormat(value) + "' And '" + DateTime.displayToDBFormat(value1) + "' ";
            } else if (value != "") {
                msCondition = String.valueOf(msCondition) + " And  SE03DTSTOP >= '" + DateTime.displayToDBFormat(value) + "' ";
            } else if (value1 != "") {
                msCondition = String.valueOf(msCondition) + " And  SE03DTSTOP <= '" + DateTime.displayToDBFormat(value1) + "' ";
            }
        } else if (gsOrderNo == "") {
            msCondition = String.valueOf(msCondition) + "AND SE03MATNO = '" + value + "' " + "AND  SE03DTSTOP <= '" + sTodayDate + "' ";
        }
        sSQLStmt = String.valueOf(sSQLStmt) + msCondition;
        sSQLStmt = String.valueOf(sSQLStmt) + "Order By SE03DTSTOP";
        return sSQLStmt;
    }

    public static List<Renewal> LoadRecordset(String criteria, String vendor, String value, String value1, String ordermode) throws SQLException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        String sSQLStmt = Renewal.GetSQLStmt(criteria, vendor, value, value1, ordermode);
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                float total = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL1" + sSQLStmt);
                while (rs.next()) {
                    total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                    Renewal cancelorderlist = new Renewal(rs.getString("SE03ORDER"), rs.getString("SE03STAT"), rs.getString("SE03MATNO"), Handler.getSubfield(Handler.ifIsNull(rs.getString("title")), isbd), Handler.getSubfield(Handler.ifIsNull(rs.getString("issn")), isbd), rs.getString("SE03COPIES"), rs.getString("SE03FOREX"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(rs.getString("SE03LPRICE")), rs.getString("SE03PRATE"), Handler.decimalConversion(String.valueOf(total)));
                    list.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<Renewal> PredictionPattern(String orderno) throws SQLException, ParseException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        String sSQLStmt = "Select SE03MATNO,t3.CT05SRAW as title, t5.ct05sraw as issn, SE03ORDER, SE03VOLFROM, SE03ISSFROM, SE03SUPPLIER,  GLVEND.GL39NAME AS VENDNAME, SE03FREQ, GLFREQ.GL49DESC as freq, SE03COPIES, SE03ISSUES, SE03FOREX, GLFORX.GL24DESC as forex, SE03PRATE,SE03FPRICE, SE03LPRICE, SE03DTSTART,SE03DTSTOP from SEORDD inner join ctpont t2 on t2.CT06MATNO = SEORDD.SE03MATNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = SEORDD.SE03MATNO and t4.CT06TAG = '022' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER LEFT JOIN GLVEND ON SEORDD.SE03SUPPLIER = GLVEND.GL39CODE INNER JOIN GLFREQ ON SEORDD.SE03FREQ = GLFREQ.GL49FREQ inner join GLFORX on SEORDD.SE03FOREX = GLFORX.GL24FOREX where SE03ORDER = '" + orderno + "'";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                float total = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL1" + sSQLStmt);
                while (rs.next()) {
                    total = Float.valueOf(rs.getString("SE03FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE03COPIES"));
                    Renewal renewal = new Renewal(Handler.getSubfield(Handler.ifIsNull(rs.getString("title")), isbd), Handler.getSubfield(Handler.ifIsNull(rs.getString("issn")), isbd), rs.getString("SE03ORDER"), rs.getString("SE03VOLFROM"), IssuesPattern.getNextVolume(rs.getString("SE03VOLFROM"), rs.getString("SE03DTSTART")), rs.getString("SE03ISSFROM"), rs.getString("SE03SUPPLIER"), rs.getString("VENDNAME"), rs.getString("SE03FREQ"), rs.getString("freq"), rs.getString("SE03COPIES"), rs.getString("SE03ISSUES"), rs.getString("SE03FOREX"), rs.getString("forex"), rs.getString("SE03PRATE"), Handler.decimalConversion(rs.getString("SE03FPRICE")), Handler.decimalConversion(rs.getString("SE03LPRICE")), IssuesPattern.getNextExpDate(rs.getString("SE03DTSTART")), rs.getString("SE03DTSTOP"), rs.getString("SE03MATNO"));
                    list.add(renewal);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<Renewal> GLFREQ() throws SQLException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        String sSQLStmt = "SELECT GL49FREQ, GL49DESC, GL49TIME, GL49PTYPE FROM GLFREQ ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    Renewal renewal = new Renewal(rs.getString("GL49FREQ"), rs.getString("GL49DESC"), rs.getString("GL49TIME"), rs.getString("GL49PTYPE"));
                    list.add(renewal);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<Renewal> GLFOREX() throws SQLException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        String sSQLStmt = "SELECT GL24FOREX, GL24DESC FROM GLFORX ";
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    Renewal renewal = new Renewal(rs.getString("GL24FOREX"), rs.getString("GL24DESC"));
                    list.add(renewal);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static boolean IsUniqueReferenceNo(String vsReferenceNo) throws SQLException {
        boolean hrs = false;
        String sSQLStmt = "";
        boolean IsUniqueReferenceNo = false;
        try {
            try {
                sSQLStmt = "SELECT COUNT(*) as count FROM SEORDD WHERE SE03PONO='" + vsReferenceNo + "'";
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL" + sSQLStmt);
                while (rs.next()) {
                    System.out.println("SQL" + Integer.parseInt(rs.getString("Count")));
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    IsUniqueReferenceNo = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return IsUniqueReferenceNo;
    }

    public static List<Renewal> GetIssueDetails(String order) throws SQLException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        DBConnection dbtype = new DBConnection();
        String sSQLStmt = dbtype.getDBType().equals("mssql") ? "Select TOP 1 SE06MATNO, min(SE06VOLLBL) as SE03VOLFROM,max(SE06VOLLBL) as SE03VOLTO,  min(SE06ISSLBL) as SE03ISSFROM, max(SE06ISSLBL) as SE03ISSTO, min(SE06EXPDATE) as SE03DTSTART, max(SE06EXPDATE) as SE03DTSTOP, SE06FREQ, SE06CPYNO, max(SE06ISSNO) as SE03ISSUES from SEISSU where SE06ORDER = '" + order + "' " + "group by SE06MATNO, SE06FREQ, SE06CPYNO" : "Select SE06MATNO, min(SE06VOLLBL) as SE03VOLFROM,max(SE06VOLLBL) as SE03VOLTO,  min(SE06ISSLBL) as SE03ISSFROM, max(SE06ISSLBL) as SE03ISSTO, min(SE06EXPDATE) as SE03DTSTART, max(SE06EXPDATE) as SE03DTSTOP, SE06FREQ, SE06CPYNO, max(SE06ISSNO) as SE03ISSUES from SEISSU where SE06ORDER = '" + order + "' and rownum=1 " + "group by SE06MATNO, SE06FREQ, SE06CPYNO";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                float total = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL1" + sSQLStmt);
                while (rs.next()) {
                    Renewal cancelorderlist = new Renewal(rs.getString("SE06MATNO"), rs.getString("SE03VOLFROM"), rs.getString("SE03VOLTO"), rs.getString("SE03ISSFROM"), rs.getString("SE03ISSTO"), rs.getString("SE03DTSTART"), rs.getString("SE03DTSTOP"), rs.getString("SE06FREQ"), rs.getString("SE06CPYNO"), rs.getString("SE03ISSUES"));
                    list.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static List<Renewal> GetPreviousOrder(String order) throws SQLException {
        ArrayList<Renewal> list = new ArrayList<Renewal>();
        String sSQLStmt = "Select SE03DTSTOP, SE03STAT FROM SEORDD WHERE SE03ORDER='" + order + "'";
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                float total = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                System.out.println("SQL1" + sSQLStmt);
                while (rs.next()) {
                    Renewal cancelorderlist = new Renewal(rs.getString("SE03DTSTOP"), rs.getString("SE03STAT"));
                    list.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return list;
    }

    public static boolean CreateReference(String orderno, String ordermode, String SE03FOREX, String SE03PRATE, String SE03FPRICE, String SE03LPRICE, String SE03SUPPLIER) throws SQLException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueDouble = new HashMap();
        HashMap condition = new HashMap();
        List<Renewal> renewal = Renewal.GetIssueDetails(orderno);
        boolean isSuccess = false;
        for (Renewal i : renewal) {
            valueStr.put("SE03ORDER", orderno);
            System.out.println("Order" + orderno);
            valueStr.put("SE03MATNO", i.getSE06MATNO());
            System.out.println("Order1" + i.getSE06MATNO());
            valueStr.put("SE03VOLFROM", i.getSE03VOLFROM());
            System.out.println("Order2" + i.getSE03VOLFROM());
            valueStr.put("SE03VOLTO", i.getSE03VOLTO());
            System.out.println("Order3" + i.getSE03VOLTO());
            valueStr.put("SE03ISSFROM", i.getSE03ISSFROM());
            System.out.println("Order4" + i.getSE03ISSFROM());
            valueStr.put("SE03ISSTO", i.getSE03ISSTO());
            System.out.println("Order5" + i.getSE03ISSTO());
            valueStr.put("SE03DTSTART", i.getSE03DTSTART());
            System.out.println("Order6" + i.getSE03DTSTART());
            valueStr.put("SE03DTSTOP", i.getSE03DTSTOP());
            System.out.println("Order7" + i.getSE03DTSTOP());
            System.out.println("dsss");
            valueStr.put("SE03FOREX", SE03FOREX);
            System.out.println("Order8" + SE03FOREX);
            valueStr.put("SE03PRATE", SE03PRATE);
            System.out.println("Order9" + SE03PRATE);
            valueStr.put("SE03FPRICE", SE03FPRICE);
            System.out.println("Order10" + SE03FPRICE);
            valueStr.put("SE03LPRICE", SE03LPRICE);
            System.out.println("Order11" + SE03LPRICE);
            valueStr.put("SE03FREQ", i.getSE06FREQ());
            System.out.println("Order12" + i.getSE06FREQ());
            valueStr.put("SE03ACQMODE", "9");
            valueStr.put("SE03PRINT", "Y");
            valueStr.put("SE03CRDATE", DateTime.getTodaySystemDate());
            valueStr.put("SE03STAT", ordermode);
            System.out.println("Order13" + ordermode);
            valueStr.put("SE03SUPPLIER", SE03SUPPLIER);
            System.out.println("Order14" + SE03SUPPLIER);
            valueStr.put("SE03COPIES", i.getSE06CPYNO());
            System.out.println("Order15" + i.getSE06CPYNO());
            valueStr.put("SE03ISSUES", i.getSE03ISSUES());
            System.out.println("Order16" + i.getSE03ISSUES());
            valueStr.put("SE03INVSTAT", ordermode);
            System.out.println("Order17" + ordermode);
            valueStr.put("SE03ORDATE", DateTime.getTodaySystemDate());
            String query = QueryBuilder.createInsertQuery("SEORDD", valueStr, null, null);
            System.out.println("Orderquery" + query);
            isSuccess = DBQuery.runQuery(query);
        }
        return isSuccess;
    }

    public static List<Renewal> getOrderingDoc(String order) throws SQLException {
        ArrayList<Renewal> tplList = new ArrayList<Renewal>();
        System.out.println("OrderList" + order);
        String query = " select SE07STATUS, SE07order, SE07CRDATE, SE07SETS, SE07COPIES, SE07FOREX, SE07FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE07LPRICE, t1.SE07CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE07CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE07CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE07CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE07CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where SE07order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE07ORDER";
        System.out.println(query);
        Renewal cancelorderlist = null;
        String current = null;
        String currentISBN = null;
        String isbn = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String previous = current;
                    current = rs.getString("SE07order");
                    String previousISBN = currentISBN;
                    currentISBN = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                    if (Integer.parseInt(rs.getString("SE07SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("SE07SETS")) + "set";
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                    } else {
                        quantity = rs.getString("SE07COPIES");
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                    }
                    if (previous != null) {
                        if (previous.equals(current)) {
                            isbn = String.valueOf(isbn) + previousISBN + ",";
                            System.out.println("Currentss" + previousISBN + currentISBN);
                            if (!currentISBN.equals(Handler.getSubfield(rs.getString("isbn"), isbd))) {
                                System.out.println("Current" + previousISBN + currentISBN);
                                isbn = String.valueOf(isbn) + Handler.getSubfield(rs.getString("isbn"), isbd);
                            }
                        } else {
                            isbn = Handler.getSubfield(Handler.ifIsNull(rs.getString("isbn")), isbd);
                        }
                    } else {
                        isbn = Handler.getSubfield(rs.getString("isbn"), isbd);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Renewal(rs.getString("SE07order"), output, Handler.getSubfield(rs.getString("title"), isbd), isbn, quantity, rs.getString("SE07FOREX"), Handler.decimalConversion(rs.getString("SE07FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE07STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"));
                    if (previous != null) {
                        if (previous.equals(current)) continue;
                        tplList.add(cancelorderlist);
                        continue;
                    }
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static List<Renewal> getPerformaOrder(String order) throws SQLException {
        ArrayList<Renewal> tplList = new ArrayList<Renewal>();
        System.out.println("OrderList" + order);
        String query = " select SE07ORDATE, t10.AC05INVNO AS invoice , t10.AC05BDRAF as chq, t10.AC05BDATE as chqdate,SE07STATUS, SE07order, SE07CRDATE, SE07SETS, SE07COPIES, SE07FOREX, SE07FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE07LPRICE, t1.SE07CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE07CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE07CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE07CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE07CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER left join ACINVO t10 on t1.SE07ORDER = t10.AC05ORDER where SE07order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE07ORDER";
        System.out.println(query);
        Renewal cancelorderlist = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("SE07SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("SE07SETS")) + "set";
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                    } else {
                        quantity = rs.getString("SE07COPIES");
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Renewal(rs.getString("SE07order"), output, Handler.removeSubfield(rs.getString("title")), Handler.removeSubfield(rs.getString("isbn")), quantity, rs.getString("SE07FOREX"), Handler.decimalConversion(rs.getString("SE07FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE07STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"), rs.getString("invoice"), rs.getString("chq"), rs.getString("chqdate"), rs.getString("SE07ORDATE"));
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static List<Renewal> getReqNotification(String order) throws SQLException {
        ArrayList<Renewal> tplList = new ArrayList<Renewal>();
        System.out.println("OrderListzz" + order);
        String query = " select SE07STATUS, SE07order, SE07CRDATE, SE07SETS, SE07COPIES, SE07FOREX, SE07FPRICE, t3.CT05SRAW as title, t5.CT05SRAW as isbn, SE07LPRICE, t1.SE07CTRLNO as ctrlno, t7.CT05SRAW as edition, t9.CT05SRAW as publication from ACORDD t1 inner join ctpont t2 on t2.CT06MATNO = t1.SE07CTRLNO and t2.CT06TAG = '245' inner join CTTITL t3 on t2.CT06POINTER = t3.CT05POINTER left join CTPONT t4 on t4.CT06MATNO = t1.SE07CTRLNO and t4.CT06TAG = '020' left join CTINDX t5 on t4.CT06POINTER = t5.CT05POINTER left join CTPONT t6 on t6.CT06MATNO = t1.SE07CTRLNO and t6.CT06TAG = '250' left join CTINDX t7 on t6.CT06POINTER = t7.CT05POINTER left join CTPONT t8 on t8.CT06MATNO = t1.SE07CTRLNO and t8.CT06TAG = '260' left join CTSUBJ t9 on t8.CT06POINTER = t9.CT05POINTER where SE07order IN (" + order + ")";
        query = String.valueOf(query) + " ORDER BY SE07ORDER";
        System.out.println(query);
        Renewal cancelorderlist = null;
        try {
            try {
                List<ISBD> isbd = ISBD.getPunctuation("245");
                String quantity = "";
                float total = 0.0f;
                float localtotal = 0.0f;
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("SE07SETS")) != 0) {
                        quantity = String.valueOf(rs.getString("SE07SETS")) + "set";
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07SETS"));
                    } else {
                        quantity = rs.getString("SE07COPIES");
                        total = Float.valueOf(rs.getString("SE07FPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                        localtotal = Float.valueOf(rs.getString("SE07LPRICE")).floatValue() * (float)Integer.parseInt(rs.getString("SE07COPIES"));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
                    c.add(5, Library.getOrderSpan());
                    String output = sdf.format(c.getTime());
                    System.out.println(output);
                    cancelorderlist = new Renewal(rs.getString("SE07order"), output, Handler.getSubfield(rs.getString("title"), isbd), Handler.getSubfield(rs.getString("isbn"), isbd), quantity, rs.getString("SE07FOREX"), Handler.decimalConversion(rs.getString("SE07FPRICE")), Handler.decimalConversion(String.valueOf(total)), Handler.decimalConversion(String.valueOf(localtotal)), rs.getString("SE07STATUS"), rs.getString("ctrlno"), rs.getString("edition"), rs.getString("publication"));
                    tplList.add(cancelorderlist);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                c.close();
            }
        }
        finally {
            c.close();
        }
        return tplList;
    }

    public static boolean UpdateReference(String vsReferenceNo, String orderno, String ordermode) throws SQLException {
        boolean hrs = false;
        String sSQLStmt = "";
        boolean updated = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(5, Library.getOrderSpan());
        String output = sdf.format(cal.getTime());
        c = DBConnection.getConnection();
        try {
            try {
                sSQLStmt = "Update ACORDD SET SE07ORDATE='" + DateTime.getTodaySystemDate() + "', SE07REFNO='" + vsReferenceNo + "', SE07STATUS='" + ordermode + "', SE07INVSTAT='" + ordermode + "', " + "SE07EXDATE='" + output + "', SE07PRINTED='Y' WHERE SE07ORDER IN(" + orderno + ")";
                System.out.println(sSQLStmt);
                PreparedStatement preparedStmt = c.prepareStatement(sSQLStmt);
                preparedStmt.executeUpdate();
                updated = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return updated;
    }

    public static String deleteACORDD(String order) {
        String query = "DELETE FROM ACORDD WHERE SE07ORDER = '" + order + "'";
        return query;
    }

    public static String resubmitACORDD(String orderno, String vendor) {
        String query = "UPDATE ACORDD SET SE07INVSTAT='10', SE07STATUS='10', SE07VEND='" + Handler.convUpperCase(vendor) + "' where SE07ORDER='" + orderno + "'";
        System.out.println(query);
        return query;
    }

    public static boolean checkReq(String vsOrderNumber) throws SQLException {
        String sSQLStmt = "";
        sSQLStmt = "SELECT Count(*) as count FROM ACREQC where AC01ORDER='" + vsOrderNumber + "'";
        boolean exist = false;
        System.out.println(sSQLStmt);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("count")) <= 0) continue;
                    exist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return exist;
    }

    public static boolean updateRenewal(String order, String refno) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueDouble = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        condition.put("SE03ORDER", order);
        valueStr.put("SE03PONO", refno);
        String query = QueryBuilder.createUpdateQuery("SEORDD", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(query);
        return isSuccess;
    }

    public static String updatePreviousOrder(String order) throws SQLException, ParseException {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap valueDouble = new HashMap();
        HashMap<String, String> condition = new HashMap<String, String>();
        List<Renewal> renewal = Renewal.GetPreviousOrder(order.trim());
        String query = "";
        String status = "";
        for (Renewal i : renewal) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date stop = sdf.parse(i.getcode());
            Date date = new Date();
            Date today = sdf.parse(sdf.format(date));
            if (today.compareTo(stop) > 0) {
                if (i.getdesc().equals("20") || i.getdesc().equals("31") || i.getdesc().equals("31")) {
                    status = "29";
                } else if (i.getdesc().equals("44") || i.getdesc().equals("72")) {
                    status = "47";
                } else if (i.getdesc().equals("60") || i.getdesc().equals("71") || i.getdesc().equals("72")) {
                    status = "62";
                }
                System.out.println("Date1 is after Date2");
            } else if (today.compareTo(stop) < 0) {
                if (i.getdesc().equals("20") || i.getdesc().equals("31") || i.getdesc().equals("31")) {
                    status = "24";
                } else if (i.getdesc().equals("44") || i.getdesc().equals("72")) {
                    status = "45";
                } else if (i.getdesc().equals("60") || i.getdesc().equals("71") || i.getdesc().equals("72")) {
                    status = "62";
                }
            } else if (today.compareTo(stop) == 0) {
                if (i.getdesc().equals("20") || i.getdesc().equals("31") || i.getdesc().equals("31")) {
                    status = "24";
                } else if (i.getdesc().equals("44") || i.getdesc().equals("72")) {
                    status = "45";
                } else if (i.getdesc().equals("60") || i.getdesc().equals("71") || i.getdesc().equals("72")) {
                    status = "62";
                }
                System.out.println("Date1 is before Date2");
            }
            condition.put("SE03ORDER", order.trim());
            valueStr.put("SE03STAT", status);
            query = QueryBuilder.createUpdateQuery("SEORDD", valueStr, null, null, condition);
        }
        return query;
    }

    public static String DeleteIssue(String order) {
        String query = "DELETE FROM SEISSU WHERE SE06ORDER = '" + order + "'";
        System.out.println(query);
        return query;
    }

    public static String DeleteOrder(String order) {
        String query = "DELETE FROM SEORDD WHERE SE03ORDER = '" + order + "'";
        System.out.println(query);
        return query;
    }
}
