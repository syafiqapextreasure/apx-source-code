/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Global.DateFormatter;
import com.kmlink.ilmu.circulation.Global.DateTime;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.ReadablePartial;

public class Circulation {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msDocNo;
    private String msDocDate;
    private String msDocTime;
    private String msPatronID;
    private String msCircFlag;
    private String msCircDate;
    private int msLateBy;
    private BigDecimal msFinesByItem;
    private String msCircTime;
    private String msCircOff;
    private String msCircMatNo;
    private int msCircCounter;
    private String msCircStatus;
    private String msBookTitle;
    private String msNoCircByPatron;
    private String CI02DOCNO;
    private String CI02BDATE;
    private String CI02DDATE;
    private String CT05CALL;
    private String CT05TITLE;
    private String CI02DTIME;
    private String CI02BTIME;
    private String CT03STAT;
    String msRetItemCat;
    String msRetSMD;
    String msRetLocation;
    String msPatronCategory;
    String msStatus;
    static String msDueDate;
    String msDueTime;
    int latedays = 0;
    private static DecimalFormat df2;

    static {
        df2 = new DecimalFormat(".##");
    }

    public Circulation() throws SQLException {
    }

    public Circulation(String msPatronID) {
    }

    public Circulation(String CT03DOCNO, String CT03STAT, String CT05SCONV) {
    }

    public Circulation(String CI02DOCNO, String CT05CALL, String CT05TITLE, String CI02BDATE, String CI02DDATE) {
        this.CI02DOCNO = CI02DOCNO;
        this.CT05CALL = CT05CALL;
        this.CT05TITLE = CT05TITLE;
        this.CI02BDATE = CI02BDATE;
        this.CI02DDATE = CI02DDATE;
    }

    public Circulation(String CI02DOCNO, String CT05CALL, String CT05TITLE, String CI02BDATE, String CI02DDATE, String CI02BTIME, String CI02DTIME) {
        this.CI02DOCNO = CI02DOCNO;
        this.CT05CALL = CT05CALL;
        this.CT05TITLE = CT05TITLE;
        this.CI02BDATE = CI02BDATE;
        this.CI02DDATE = CI02DDATE;
        this.CI02BTIME = CI02BTIME;
        this.CI02DTIME = CI02DTIME;
    }

    public String getCI02DOCNO() {
        return this.CI02DOCNO;
    }

    public String getCI02BDATE() {
        return this.CI02BDATE;
    }

    public String getmsDueDate() {
        return msDueDate;
    }

    public String getmsDueTime() {
        return this.msDueTime;
    }

    public String getCI02DDATE() {
        return this.CI02DDATE;
    }

    public String getCT05CALL() {
        return this.CT05CALL;
    }

    public String getCT05TITLE() {
        return this.CT05TITLE;
    }

    public String getCI02DTIME() {
        return this.CI02DTIME;
    }

    public String getCI02BTIME() {
        return this.CI02BTIME;
    }

    public String getmsStatus() {
        return this.msStatus;
    }

    public void setMsStatus(String msStatus) {
        this.msStatus = msStatus;
    }

    public Circulation(String msPatronID, String CI02DOCNO, String CI02DDATE, String CI02DTIME, String CI02FLAG, String CI02MATNO, String CI02CDATE, String CI02CTIME, String CI02OFFI, int CI02COUNTER, String msBranch) {
        try {
            Patron patr = new Patron(msPatronID);
            this.msPatronCategory = patr.getMsPatronCategory(msPatronID);
            this.msDocNo = CI02DOCNO;
            this.msDocDate = CI02DDATE;
            this.msDocTime = CI02DTIME;
            this.msCircFlag = CI02FLAG;
            this.msCircMatNo = CI02DOCNO;
            this.msCircDate = CI02CDATE;
            this.msCircTime = CI02CTIME;
            this.msCircOff = CI02OFFI;
            this.msCircCounter = CI02COUNTER;
            this.msBookTitle = this.getBookTitle(CI02MATNO);
            this.msCircStatus = this.getMsCircStatus(CI02DOCNO);
            this.msLateBy = this.calculateDays(CI02DDATE, msBranch);
            this.msFinesByItem = this.calculatefines2(msPatronID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Circulation> viewtableacc(String msPatronID, String CI02DOCNO) throws SQLException {
        String BookTitle = "";
        String CircStatus = "";
        ArrayList<Circulation> circList = new ArrayList<Circulation>();
        Patron patr = new Patron(msPatronID);
        String msBranch = patr.getMsPatronBranch();
        System.out.println("From Circulation" + msBranch);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT * FROM CICIRC WHERE CI02DOCNO ='" + CI02DOCNO + "' and CI02FLAG = 'C' ORDER BY CI02CDATE DESC";
                System.out.println(sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    System.out.println(rsObj.getString("CI02DOCNO"));
                    System.out.println(rsObj.getString("CI02MATNO"));
                    Circulation circ = new Circulation(rsObj.getString("CI02PATR"), rsObj.getString("CI02DOCNO"), rsObj.getString("CI02DDATE"), rsObj.getString("CI02DTIME"), rsObj.getString("CI02FLAG"), rsObj.getString("CI02MATNO"), rsObj.getString("CI02CDATE"), rsObj.getString("CI02CTIME"), rsObj.getString("CI02OFFI"), rsObj.getInt("CI02COUNTER"), msBranch);
                    System.out.println(circ.getMsFinesByItem());
                    circList.add(circ);
                }
            }
            catch (Exception e) {
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
        return circList;
    }

    public static List<Circulation> patronStatus(String patrID, String flag) {
        ArrayList<Circulation> list = new ArrayList<Circulation>();
        String sql = null;
        DBConnection db = new DBConnection();
        if (flag.equals("C")) {
            if (DBConnection.getDBType().equals("oracle")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' AND ROWNUM=1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' AND ROWNUM=1)) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='" + flag + "'";
            } else if (DBConnection.getDBType().equals("mssql")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='" + flag + "'";
            } else if (DBConnection.getDBType().equals("mysql")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' LIMIT 1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' LIMIT 1)) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='" + flag + "'";
            }
        } else if (flag.equals("R")) {
            if (DBConnection.getDBType().equals("oracle")) {
                sql = "SELECT circ.CI03DOCNO, circ.CI03RDATE, circ.CI03RTIME,  (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' AND ROWNUM=1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' AND ROWNUM=1)) AS Title FROM CIRESV circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI03DOCNO WHERE circ.CI03PATR='" + patrID + "'";
            } else if (DBConnection.getDBType().equals("mssql")) {
                sql = "SELECT circ.CI03DOCNO, circ.CI03RDATE, circ.CI03RTIME,  (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS Title FROM CIRESV circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI03DOCNO WHERE circ.CI03PATR='" + patrID + "'";
            } else if (DBConnection.getDBType().equals("mysql")) {
                sql = "SELECT circ.CI03DOCNO, circ.CI03RDATE, circ.CI03RTIME,  (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' LIMIT 1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' LIMIT 1)) AS Title FROM CIRESV circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI03DOCNO WHERE circ.CI03PATR='" + patrID + "'";
            }
        } else if (flag.equals("O")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String currentdate = dateFormat.format(date).toString();
            if (DBConnection.getDBType().equals("oracle")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' AND ROWNUM=1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' AND ROWNUM=1)) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='C' and circ.CI02DDATE<" + currentdate;
            } else if (DBConnection.getDBType().equals("mssql")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090')) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT TOP 1 CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245')) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='C' and circ.CI02DDATE<" + currentdate;
            } else if (DBConnection.getDBType().equals("mysql")) {
                sql = "SELECT circ.CI02DOCNO, circ.CI02CDATE, circ.CI02DDATE, circ.CI02CTIME, circ.CI02DTIME, (SELECT CT05SRAW FROM CTCALL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '090' LIMIT 1)) AS CallNo, (SELECT CT05SRAW FROM CTTITL WHERE CT05POINTER = (SELECT CT06POINTER FROM CTPONT WHERE CT06MATNO = docm.CT03MATNO AND CT06TAG = '245' LIMIT 1)) AS Title FROM CICIRC circ INNER JOIN CTDOCM docm ON docm.CT03DOCNO = circ.CI02DOCNO WHERE circ.CI02PATR='" + patrID + "' and circ.CI02FLAG='C' and circ.CI02DDATE<" + currentdate;
            }
        }
        System.out.println(sql);
        try {
            try {
                Circulation patronDetail;
                Statement stmt1 = null;
                conn = DBConnection.getConnection();
                stmt1 = conn.createStatement();
                rsObj = stmt1.executeQuery(sql);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                List<ISBD> callisbd = ISBD.getPunctuation("090");
                if (flag.equals("C") || flag.equals("O")) {
                    while (rsObj.next()) {
                        patronDetail = new Circulation(rsObj.getString("CI02DOCNO"), Handler.getSubfield(rsObj.getString("CallNo"), callisbd), Handler.getSubfield(rsObj.getString("Title"), isbd), DateTime.formatDate(rsObj.getString("CI02CDATE")), DateTime.formatDate(rsObj.getString("CI02DDATE")), DateTime.Time(rsObj.getString("CI02CTIME")), DateTime.Time(rsObj.getString("CI02DTIME")));
                        System.out.println("Date" + rsObj.getString("CI02DTIME"));
                        list.add(patronDetail);
                    }
                } else if (flag.equals("R")) {
                    while (rsObj.next()) {
                        patronDetail = new Circulation(rsObj.getString("CI03DOCNO"), Handler.getSubfield(rsObj.getString("CallNo"), callisbd), Handler.getSubfield(rsObj.getString("Title"), isbd), DateFormatter.DBToDisplayFormat(rsObj.getString("CI03RDATE")), DateTime.Time(rsObj.getString("CI03RTIME")));
                        list.add(patronDetail);
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
        return list;
    }

    public static List<Circulation> viewtable(String msPatronID, String msCircStatus) throws SQLException {
        String BookTitle = "";
        String CircStatus = "";
        ArrayList<Circulation> circList = new ArrayList<Circulation>();
        Patron patr = new Patron(msPatronID);
        String msBranch = patr.getMsPatronBranch();
        System.out.println("From Circulation" + msBranch);
        try {
            try {
                String sql;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                System.out.println("Circulation Status:" + msCircStatus);
                if (msCircStatus.equals(null) || msCircStatus.equals(" ") || msCircStatus.equals("none")) {
                    System.out.println("null");
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "ORDER BY CI02CDATE DESC";
                    System.out.println(sql);
                } else if (msCircStatus.equals("Current")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    String currentdate = dateFormat.format(date).toString();
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "AND CI02CDATE=" + GeneralUtility.DatetoStr(currentdate) + " AND CI02FLAG = 'C'";
                    System.out.println(sql);
                } else {
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "AND CI02FLAG = '" + msCircStatus + "'" + "ORDER BY CI02CDATE DESC, CI02CTIME DESC";
                }
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    System.out.println(rsObj.getString("CI02DOCNO"));
                    System.out.println(rsObj.getString("CI02MATNO"));
                    Circulation circ = new Circulation(rsObj.getString("CI02PATR"), rsObj.getString("CI02DOCNO"), rsObj.getString("CI02DDATE"), rsObj.getString("CI02DTIME"), rsObj.getString("CI02FLAG"), rsObj.getString("CI02MATNO"), rsObj.getString("CI02CDATE"), rsObj.getString("CI02CTIME"), rsObj.getString("CI02OFFI"), rsObj.getInt("CI02COUNTER"), msBranch);
                    System.out.println(circ.getMsFinesByItem());
                    circList.add(circ);
                }
            }
            catch (Exception e) {
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
        return circList;
    }

    public boolean renewTable(String msPatronID, String msCircStatus) throws SQLException {
        boolean renew = true;
        String BookTitle = "";
        String CircStatus = "";
        ArrayList circList = new ArrayList();
        Patron patr = new Patron(msPatronID);
        String msBranch = patr.getMsPatronBranch();
        System.out.println("From Circulation" + msBranch);
        try {
            try {
                String sql;
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                System.out.println("Circulation Status:" + msCircStatus);
                if (msCircStatus.equals(null) || msCircStatus.equals(" ") || msCircStatus.equals("none")) {
                    System.out.println("null");
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "ORDER BY CI02CDATE DESC";
                    System.out.println(sql);
                } else if (msCircStatus.equals("Current")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    String currentdate = dateFormat.format(date).toString();
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "AND CI02CDATE=" + GeneralUtility.DatetoStr(currentdate) + " AND CI02FLAG = 'C'";
                    System.out.println(sql);
                } else {
                    sql = "SELECT * FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'" + "AND CI02FLAG = '" + msCircStatus + "'" + "ORDER BY CI02CDATE DESC, CI02CTIME DESC";
                }
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    msDueDate = GeneralUtility.StrToDate(rsObj.getString("CI02DDATE"));
                    this.msDueTime = GeneralUtility.StrToTime2(rsObj.getString("CI02DTIME"));
                }
            }
            catch (Exception e) {
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
        return renew;
    }

    public String getMsDocNo() {
        return this.msDocNo;
    }

    public String getMsDocNo(String msPatronID) {
        try {
            String sql = "SELECT CI02DOCNO FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msDocNo = String.valueOf(rsObj.getString("CI02DOCNO"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msDocNo;
    }

    public static String getStatus(String CT03DOCNO) {
        String result = "";
        Object connection = null;
        Object ps = null;
        Object rs = null;
        String sql = "SELECT CT03STAT, GL36DESC FROM CTDOCM, GLDOCS WHERE CT03STAT=GL36STAT AND CT03DOCNO ='" + CT03DOCNO + "'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                if (rsObj.next()) {
                    result = String.valueOf(rsObj.getString("CT03STAT")) + "," + rsObj.getString("GL36DESC");
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
        return result;
    }

    public void setMsDocNo(String msDocNo) {
        this.msDocNo = msDocNo;
    }

    public String getMsDocDate() {
        return this.msDocDate;
    }

    public String getMsDocDate(String msPatronID) {
        try {
            String sql = "SELECT CI02DDATE FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msDocDate = String.valueOf(rsObj.getString("CI02DDATE"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msDocDate;
    }

    public void setMsDocDate(String msDocDate) {
        this.msDocDate = msDocDate;
    }

    public String getMsDocTime() {
        return this.msDocTime;
    }

    public String getMsDocTime(String msPatronID) {
        try {
            String sql = "SELECT CI02DTIME FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msDocDate = String.valueOf(rsObj.getString("CI02DTIME"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msDocTime;
    }

    public void setMsDocTime(String msDocTime) {
        this.msDocTime = msDocTime;
    }

    public String getMsPatronID() {
        return this.msPatronID;
    }

    public String getMsPatronID(String msPatronID) {
        return msPatronID;
    }

    public void setMsPatronID(String msPatronID) {
        this.msPatronID = msPatronID;
    }

    public String getMsCircFlag() {
        return this.msCircFlag;
    }

    public String getMsCircFlag(String msPatronID) {
        try {
            String sql = "SELECT CI02FLAG FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCircFlag = String.valueOf(rsObj.getString("CI02FLAG"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msCircFlag;
    }

    public void setMsCircFlag(String msCircFlag) {
        this.msCircFlag = msCircFlag;
    }

    public String getMsCircDate() {
        return this.msCircDate;
    }

    public String getMsCircDate(String msPatronID) {
        return this.msCircDate;
    }

    public void setMsCircDate(String msCircDate) {
        this.msCircDate = msCircDate;
    }

    public String getMsCircTime() {
        return this.msCircTime;
    }

    public String getMsCircTime(String msPatronID) {
        try {
            String sql = "SELECT CI02CTIME FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCircTime = String.valueOf(rsObj.getString("CI02CTIME"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msCircTime;
    }

    public void setMsCircTime(String msCircTime) {
        this.msCircTime = msCircTime;
    }

    public String getMsCircOff() {
        return this.msCircOff;
    }

    public String getMsCircOff(String msPatronID) {
        try {
            String sql = "SELECT CI02OFFI FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCircOff = String.valueOf(rsObj.getString("CI02OFFI"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msCircOff;
    }

    public void setMsCircOff(String msCircOff) {
        this.msCircOff = msCircOff;
    }

    public String getMsCircMatNo() {
        return this.msCircMatNo;
    }

    public String getMsCircMatNo(String msPatronID) {
        try {
            String sql = "SELECT CI02MATNO FROM CICIRC WHERE CI02PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCircMatNo = String.valueOf(rsObj.getString("CI02MATNO"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msCircMatNo;
    }

    public void setMsCircMatNo(String msCircMatNo) {
        this.msCircMatNo = msCircMatNo;
    }

    public int getMsCircCounter(String vsPatronID, String vsMatNo, String vsDocNo, String vsDisChargeDate, String vsDischargeTime) {
        try {
            String sql = "SELECT CI02COUNTER FROM CICIRC WHERE CI02PATR = '" + vsPatronID.trim() + "' " + "AND CI02MATNO = '" + vsMatNo.trim() + "' " + "AND CI02DOCNO = '" + vsDocNo.trim() + "' " + "AND CI02DIDATE = '" + vsDisChargeDate.trim() + "' " + "AND CI02DITIME = '" + vsDischargeTime.trim() + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCircCounter = rsObj.getInt("CI02COUNTER");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msCircCounter;
    }

    public int getMsCircCounter() {
        return this.msCircCounter;
    }

    public void setMsCircCounter(int msCircCounter) {
        this.msCircCounter = msCircCounter;
    }

    public String getMsCircStatus() {
        return this.msCircStatus;
    }

    public void setMsCircStatus(String msCircStatus) {
        this.msCircStatus = msCircStatus;
    }

    public String getMsBookTitle() {
        return this.msBookTitle;
    }

    public void setMsBookTitle(String msBookTitle) {
        this.msBookTitle = msBookTitle;
    }

    public static void displayCirculationDetails(List<Circulation> circList) {
        System.out.print("No\tAccession No\tBorrowed Date\tReturn Date\tStatus\tBook Title\n");
        int count = 0;
        for (Circulation s : circList) {
            System.out.print(String.valueOf(++count) + "\t" + s.getMsDocNo() + "\t" + GeneralUtility.StrToDate(s.getMsCircDate()) + "\t" + GeneralUtility.StrToDate(s.getMsDocDate()) + "\t" + s.getMsCircStatus() + "\t" + s.getMsBookTitle() + "\n");
        }
    }

    public String getMsNoCircByPatron(String msPatronID) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02FLAG = 'C' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoCircByPatron = rsObj.getString("CIRCULATEDNO");
                if (!this.msNoCircByPatron.equals(null)) continue;
                this.msNoCircByPatron = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoCircByPatron;
    }

    public String getBookTitle(String msMatNo) {
        String msBookTitle = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT CT05SRAW FROM CTTITL,CTPONT WHERE CT06POINTER=CT05POINTER AND CT06TAG = '245'AND CT06MATNO = '" + msMatNo + "'";
            System.out.println("Title" + sql);
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBookTitle = Handler.removeSubfield(rsObj.getString("CT05SRAW"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        msBookTitle = msBookTitle.replaceFirst("\\|a", "");
        msBookTitle = msBookTitle.replaceAll("\\|c", "/");
        return msBookTitle;
    }

    public String getMsCircStatus(String msDocNo) {
        String msCircStatus = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT CT03DOCNO, CT03STAT,CT03ICAT,CT03SMD,GL36STAT, CT03LOCA,GL36DESC  FROM CTDOCM,GLDOCS WHERE CT03DOCNO = '" + msDocNo + "'" + "AND GL36STAT=CT03STAT";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                msCircStatus = rsObj.getString("GL36DESC");
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msCircStatus;
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And CT03PATR='" + msPatronID + "' AND CT03STAT='C'";
        System.out.println(sql);
        boolean exist = false;
        try {
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public BigDecimal calculatefines2(String msPatronID) {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        try {
            if (this.latedays < 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                maxfine = this.MaxFine(msPatronID);
                System.out.println(fineRate);
                System.out.println(maxfine);
                fines = fineRate.multiply(new BigDecimal(this.latedays));
                if (maxfine.compareTo(BigDecimal.ZERO) != 0 && maxfine.compareTo(fines) < 0) {
                    fines = maxfine;
                }
                System.out.println("Fines" + fines);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        fines = fines.setScale(2, 1);
        df2.setMaximumFractionDigits(2);
        df2.setMinimumFractionDigits(0);
        df2.setGroupingUsed(false);
        return fines;
    }

    public BigDecimal calculatefines(String msPatronID, String msBranch) {
        BigDecimal fines = BigDecimal.ZERO;
        int holidaycount = 0;
        int trxno = 0;
        BigDecimal maxfine = BigDecimal.ZERO;
        BigDecimal finerate = BigDecimal.ZERO;
        System.out.println(this.msDocDate);
        String datecharged = DateTime.formatDatelocal(this.msDocDate);
        String datereturned = DateTime.getTodayDate();
        Patron patr = new Patron(msPatronID);
        this.RetrieveAccessionDetail(this.msDocNo, msPatronID);
        String msPatronCategory = patr.getMsPatronCategory(msPatronID);
        LocalDate dateStart = new LocalDate((Object)datecharged);
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Date Start" + dateStart);
        System.out.println("Date end" + dateEnd);
        System.out.println("Number of days" + days);
        String CI02DATE = "";
        if (days < 0) {
            this.latedays = 0;
        }
        if (days > 0) {
            try {
                stmt = conn.createStatement();
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + this.msDocDate + "'AND '" + datereturned + "'";
                ResultSet rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                rsObj.close();
                System.out.println("C" + holidaycount);
                String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetLocation + "'";
                stmt = conn.createStatement();
                ResultSet rsObj2 = stmt.executeQuery(sql3);
                while (rsObj2.next()) {
                    finerate = rsObj2.getBigDecimal("GL38RATE");
                }
                rsObj2.close();
                String sql4 = "SELECT  GL98VALUE from GLNUMB where GL98FUNC= 'TRXNO'";
                System.out.println(sql4);
                stmt = conn.createStatement();
                ResultSet rsObj3 = stmt.executeQuery(sql4);
                rsObj3 = stmt.executeQuery(sql4);
                while (rsObj3.next()) {
                    trxno = rsObj3.getInt("GL98VALUE");
                }
                rsObj3.close();
                String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetLocation + "'";
                System.out.println(sql5);
                ResultSet rsObj4 = stmt.executeQuery(sql5);
                rsObj4 = stmt.executeQuery(sql5);
                while (rsObj4.next()) {
                    maxfine = rsObj4.getBigDecimal("GL27MAXFINE");
                }
                if (maxfine == null) {
                    maxfine = BigDecimal.ZERO;
                }
                rsObj4.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            this.latedays = days - holidaycount;
            System.out.println("TRXNO" + trxno++);
            System.out.println("finerate" + finerate);
            System.out.println("Late Days" + this.latedays);
            fines = finerate.multiply(new BigDecimal(this.latedays));
            double totalfines = 0.0;
            System.out.println("MaxFines" + maxfine);
            System.out.println("Fines" + fines);
            if (maxfine.compareTo(BigDecimal.ZERO) != 0 && maxfine.compareTo(fines) < 0) {
                fines = maxfine;
            }
            System.out.println("Fines" + fines);
        }
        return fines;
    }

    public int getLateBy() {
        return this.latedays;
    }

    public int calculateDays(String msDocDate, String msBranch) throws SQLException {
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String datereturned = DateTime.getTodayDate();
        LocalDate dateStart = new LocalDate((Object)datecharged);
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Days count" + days);
        int holidaycount = 0;
        if (days < 0) {
            this.latedays = 0;
        } else {
            stmt = conn.createStatement();
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + datereturned + "'";
            ResultSet rsObj = stmt.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
            }
            System.out.println("Holiday Count" + holidaycount);
            rsObj.close();
            this.latedays = days - holidaycount;
        }
        return this.latedays;
    }

    public BigDecimal fineRate(String msPatronID) {
        BigDecimal finerate = BigDecimal.ZERO;
        try {
            String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetLocation + "'";
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql3);
            int count = 0;
            while (rsObj.next()) {
                finerate = rsObj.getBigDecimal("GL38RATE");
                System.out.println("" + finerate + ++count);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return finerate;
    }

    public String getMsPatronCategory(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            if (rsObj.next()) {
                this.msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCategory;
    }

    public BigDecimal MaxFine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        try {
            String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetLocation + "'";
            System.out.println(sql5);
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql5);
            System.out.println("MaxFine");
            if (rsObj.next()) {
                maxfine = rsObj.getBigDecimal("GL27MAXFINE");
            }
            if (maxfine == null) {
                maxfine = BigDecimal.ZERO;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return maxfine;
    }

    public int getMsLateBy() {
        return this.msLateBy;
    }

    public BigDecimal getMsFinesByItem() {
        return this.msFinesByItem;
    }

    public void setMsLateBy(int msLateBy) {
        this.msLateBy = msLateBy;
    }

    public void setMsFinesByItem(BigDecimal msFinesByItem) {
        this.msFinesByItem = msFinesByItem;
    }
}
