/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.resv;

import com.kmlink.ilmu.circulation.Charging.Charging;
import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Global.AuditTrail;
import com.kmlink.ilmu.circulation.Global.DateTime;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.circulation.Global.ISBD;
import com.kmlink.ilmu.circulation.utilities.query.DBQuery;
import com.kmlink.ilmu.circulation.utilities.query.QueryBuilder;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Reservation {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    String message;
    private String CI03PATR = null;
    private String CI03BRNC;
    private String CI03NDATE;
    private String CI03DDATE;
    private String CI03RDATE;
    private String CI03NSTAT;
    private String CI03ICAT;
    private String CI03PRIOR;
    private String CI03MATNO;
    private String msCI03PRIOR;
    private String msItemStat;
    private String CI02CDATE;
    private String CI02CTIME;
    private String CI02DDATE;
    private String CI02OFFI;
    public String grace;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    private String msPatronId;
    private String msPatronName;
    private String msPatronTitle;
    private String msPatronStatus;
    private String msPatronOldIC;
    private String msPatronNewIC;
    private String msPatronCode;
    private String msPatronTown;
    private String msPatronCode2;
    private String msPatronTown2;
    private String msPatronHTel;
    private String msPatronOTel;
    private String msPatronFax;
    private String msPatronCategory;
    private String msPatronHomeAdd1;
    private String msPatronHomeAdd2;
    private String msPatronHomeAdd3;
    private String msPatronOHomeAdd1;
    private String msPatronOHomeAdd2;
    private String msPatronOHomeAdd3;
    private String msPatronDOB;
    private String msPatronSex;
    private String msPatronRace;
    private String msPatronPassword;
    private byte[] msPatronImage;
    private String msPatronBranch;
    private String msParentInfo;
    private String msPatronCourse;
    private String msPatronDept;
    private int msPatronCateEligNo = 0;
    private String msPatronSerialNo = "";
    private String msHighestPriorityPatronID = "";
    private String msPatronDeptDesc = "";
    private String msPatronCateDesc = "";
    private String msPatronBranchDesc = "";
    private String msPatronCourseDesc = "";
    private String msPatronStatusDesc = "";
    private String msPatronDesc = "";
    private String msPatronElig = "";
    private String msNoCircByPatron = "";
    private int msNoGetLostItem = 0;
    private int msNoGetItemOverdue = 0;
    private int msNoGetItemReserved = 0;
    private int msNoGetItemOnHold = 0;
    private double msGetTotalFines = 0.0;
    private double msGetTotalFinesPaid = 0.0;
    private double msGetTotalFinesOutstanding = 0.0;
    private String msMaxResv = "";
    private String msAllowResv = "";
    private String msMemDate = "";
    private String msMemExpiryDate = "";
    private String msMemFee = "";
    private String msMemDeposit = "";
    private String GL14IMG = "";
    private String CI02PATR = null;
    private String GL14NAME = null;
    private String GL07DESC = null;
    private String GL14MEMDATE = null;
    private String GL14EXPDATE = null;
    private String GL71DESC = null;
    private String GL08DESC = null;
    private String GL28ORGNAME = null;
    private String GL28POSCODE = null;
    private String GL28ADD1 = null;
    private String GL28ADD2 = null;
    private String GL14ADD1 = null;
    private String GL14ADD2 = null;
    private String GL14ADD3 = null;
    private String GL14CODE = null;
    private String onloan = null;
    private int overdue = 0;
    private int reserve = 0;
    private int onhold = 0;
    private double outstanding = 0.0;
    private double charged = 0.0;
    private String CT05SRAW;
    private String GL05LOCA;
    private String GL05DESC;
    private String CI03DOCNO;
    private String errmessage = "";
    public String msCateAllowResv;
    private int msCateMaxResv = 0;
    private String GL14OFFNAME = "";
    boolean isvalidPatron = false;

    public Reservation(String CT05SRAW, String GL05LOCA, String GL05DESC) {
        this.CT05SRAW = CT05SRAW;
        this.GL05LOCA = GL05LOCA;
        this.GL05DESC = GL05DESC;
    }

    public Reservation(String GL14NAME, String GL14ADD1, String GL14ADD2, String GL14ADD3, String GL14EXPDATE, String GL28ORGNAME, String GL28POSCODE, String GL28ADD1, String GL28ADD2, String GL14CODE) {
        this.GL14NAME = GL14NAME;
        this.GL14ADD1 = GL14ADD1;
        this.GL14ADD2 = GL14ADD2;
        this.GL14ADD3 = GL14ADD3;
        this.GL14EXPDATE = GL14EXPDATE;
        this.GL28ORGNAME = GL28ORGNAME;
        this.GL28POSCODE = GL28POSCODE;
        this.GL28ADD1 = GL28ADD1;
        this.GL28ADD2 = GL28ADD2;
        this.GL14CODE = GL14CODE;
    }

    public Reservation(String CI03NDATE, String CI03PATR, String GL14NAME, String CT05SRAW, String CI03PRIOR, String CI03MATNO, String CI03DOCNO) {
        this.CI03NDATE = CI03NDATE;
        this.CI03PATR = CI03PATR;
        this.GL14NAME = GL14NAME;
        this.CT05SRAW = CT05SRAW;
        this.CI03PRIOR = CI03PRIOR;
        this.CI03MATNO = CI03MATNO;
        this.CI03DOCNO = CI03DOCNO;
    }

    public Reservation(String CI03DOCNO, String CI03PATR, String CI03BRNC, String CI03NDATE, String CI03DDATE, String CI03RDATE, String CI03NSTAT, String GL14NAME, String CI03ICAT) {
        this.CI03DOCNO = CI03DOCNO;
        this.CI03PATR = CI03PATR;
        this.CI03BRNC = CI03BRNC;
        this.CI03NDATE = CI03NDATE;
        this.CI03DDATE = CI03DDATE;
        this.CI03RDATE = CI03RDATE;
        this.CI03NSTAT = CI03NSTAT;
        this.GL14NAME = GL14NAME;
        this.CI03ICAT = CI03ICAT;
    }

    public Reservation(String CI02DDATE, String CI02CDATE, String CI02CTIME, String CI02OFFI, String GL14OFFNAME) {
        this.CI02DDATE = CI02DDATE;
        this.CI02CDATE = CI02CDATE;
        this.CI02CTIME = CI02CTIME;
        this.CI02OFFI = CI02OFFI;
        this.GL14OFFNAME = GL14OFFNAME;
    }

    public Reservation(String CT05SRAW) {
        this.CT05SRAW = CT05SRAW;
    }

    public Reservation(String GL05LOCA, String GL05DESC) {
        this.GL05LOCA = GL05LOCA;
        this.GL05DESC = GL05DESC;
    }

    public Reservation(String CI02PATR, String GL14NAME, String GL07DESC, String GL14MEMDATE, String GL14EXPDATE, String GL71DESC, String GL08DESC, String onloan, int overdue, int reserve, int onhold, double outstanding, double charged) {
        this.CI02PATR = CI02PATR;
        this.GL14NAME = GL14NAME;
        this.GL07DESC = GL07DESC;
        this.GL14MEMDATE = GL14MEMDATE;
        this.GL14EXPDATE = GL14EXPDATE;
        this.GL71DESC = GL71DESC;
        this.GL08DESC = GL08DESC;
        this.onloan = onloan;
        this.overdue = overdue;
        this.reserve = reserve;
        this.onhold = onhold;
        this.outstanding = outstanding;
        this.charged = charged;
    }

    public String geGL14OFFNAME() {
        return this.GL14OFFNAME;
    }

    public String getCI03PRIOR() {
        return this.CI03PRIOR;
    }

    public String getCI03MATNO() {
        return this.CI03MATNO;
    }

    public String getGL28ORGNAME() {
        return this.GL28ORGNAME;
    }

    public String getGL28POSCODE() {
        return this.GL28POSCODE;
    }

    public String getGL28ADD1() {
        return this.GL28ADD1;
    }

    public String getGL28ADD2() {
        return this.GL28ADD2;
    }

    public String getCI02DDATE() {
        return this.CI02DDATE;
    }

    public String getCI02CDATE() {
        return this.CI02CDATE;
    }

    public String getCI02CTIME() {
        return this.CI02CTIME;
    }

    public String getCI02OFFI() {
        return this.CI02OFFI;
    }

    public String getGL14ADD1() {
        return this.GL14ADD1;
    }

    public String getGL14ADD2() {
        return this.GL14ADD2;
    }

    public String getGL14ADD3() {
        return this.GL14ADD3;
    }

    public String getGL14CODE() {
        return this.GL14CODE;
    }

    public String getGL05LOCA() {
        return this.GL05LOCA;
    }

    public String getCI03DOCNO() {
        return this.CI03DOCNO;
    }

    public String getCI03PATR() {
        return this.CI03PATR;
    }

    public String getCI03NDATE() {
        return this.CI03NDATE;
    }

    public String getCI03DDATE() {
        return this.CI03DDATE;
    }

    public String getCI03NSTAT() {
        return this.CI03NSTAT;
    }

    public String getCI03RDATE() {
        return this.CI03RDATE;
    }

    public String getCI03ICAT() {
        return this.CI03ICAT;
    }

    public String getCI03BRNC() {
        return this.CI03BRNC;
    }

    public String getGL05DESC() {
        return this.GL05DESC;
    }

    public String getCT05SRAW() {
        return this.CT05SRAW;
    }

    public String getCI02PATR() {
        return this.CI02PATR;
    }

    public String getGL14NAME() {
        return this.GL14NAME;
    }

    public String getGL07DESC() {
        return this.GL07DESC;
    }

    public String getGL14MEMDATE() {
        return this.GL14MEMDATE;
    }

    public String getGL14EXPDATE() {
        return this.GL14EXPDATE;
    }

    public String getGL71DESC() {
        return this.GL71DESC;
    }

    public String getGL08DESC() {
        return this.GL08DESC;
    }

    public String getonloan() {
        return this.onloan;
    }

    public int getoverdue() {
        return this.overdue;
    }

    public int getreserve() {
        return this.reserve;
    }

    public int getonhold() {
        return this.onhold;
    }

    public double getoutstanding() {
        return this.outstanding;
    }

    public double getcharged() {
        return this.charged;
    }

    public Reservation() {
        this.setMsPatronId(null);
        this.setMsPatronName(null);
        this.setMsPatronTitle(null);
        this.setMsPatronDeptDesc(null);
        this.setMsMemExpiryDate(null);
        this.setMsPatronCateDesc(null);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
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
    }

    public static void initializePatron() {
    }

    private void setMsPatronSerialNo(String string) {
    }

    private void setMsPatronDep(String string) {
    }

    private void setMsPatronCourse(String string) {
    }

    private void setMsParentInfo(String string) {
    }

    private void setMsPatronBranch(String msPatronBranch) {
        this.msPatronBranch = msPatronBranch;
    }

    private void setMsPatronPassword(String string) {
    }

    private void setMsPatronCategory(String msPatronCategory) {
        this.msPatronCategory = msPatronCategory;
    }

    private void setMsPatronStatus(String msPatronStatus) {
        this.msPatronStatus = msPatronStatus;
    }

    public String getMsPatronId() {
        return this.msPatronId;
    }

    public String getItemStat() {
        return this.msItemStat;
    }

    public void setMsPatronId(String msPatronId) {
        this.msPatronId = msPatronId;
    }

    public void setItemStat(String msItemStat) {
        this.msItemStat = msItemStat;
    }

    public String getGL14IMG() {
        return this.GL14IMG;
    }

    public String getgrace() {
        return this.grace;
    }

    public void setGL14IMG(String GL14IMG) {
        this.GL14IMG = GL14IMG;
    }

    public void setmsAllowResv(String msAllowResv) {
        this.msAllowResv = msAllowResv;
    }

    public void setgrace(String grace) {
        this.grace = grace;
    }

    public void setmsMaxResv(String msMaxResv) {
        this.msMaxResv = msMaxResv;
    }

    public void setMsPatronName(String msPatronName) {
        this.msPatronName = msPatronName;
    }

    public void setMsPatronDeptDesc(String msPatronDeptDesc) {
        this.msPatronDeptDesc = msPatronDeptDesc;
    }

    public String getMsPatronName() {
        return this.msPatronName;
    }

    public String getmsAllowResv() {
        return this.msAllowResv;
    }

    public String getmsMaxResv() {
        return this.msMaxResv;
    }

    public static int grace() {
        int graces = 0;
        try {
            String sql = "SELECT GL28RESVTIME FROM GLLIBR";
            System.out.println("patrid" + sql);
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                graces = Integer.parseInt(rsObj.getString("GL28RESVTIME"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return graces;
    }

    public static String getPatrId(String getPatrId) {
        try {
            String sql = "SELECT CI02PATR FROM CICIRC WHERE CI02DOCNO ='" + getPatrId + "' AND CI02FLAG='C'";
            System.out.println("patrid" + sql);
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                getPatrId = rsObj.getString("CI02PATR");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getPatrId;
    }

    public String getMsPatronName(String msPatronID) {
        try {
            String sql = "SELECT GL14PATR,GL14NAME FROM GLPATR WHERE GL14PATR ='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronName = String.valueOf(rsObj.getString("GL14Name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.msPatronName;
    }

    public String getMsPatronTitle() {
        return this.msPatronTitle;
    }

    public void setMsPatronTitle(String msPatronTitle) {
        this.msPatronTitle = msPatronTitle;
    }

    public String getMsPatronTitle(String vsTitleCode) {
        String sql = "Select GL64TITLE From GLTITLE Where GL64CODE = '" + vsTitleCode + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronTitle = String.valueOf(rsObj.getString("GL64TITLE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronTitle;
    }

    public String getMsPatronStatus() {
        return this.msPatronStatus;
    }

    public String getMsPatronStatus(String msPatronID) {
        String sql = "SELECT GL14STAT FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronStatus = String.valueOf(rsObj.getString("GL14STAT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronStatus;
    }

    public String getMsPatronStatusDesc() {
        String sql = "SELECT GL08DESC FROM GLSTAT WHERE GL08STAT= '" + this.msPatronStatus + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronStatusDesc = String.valueOf(rsObj.getString("GL08DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronStatusDesc;
    }

    public String getMsPatronElig() {
        String sql = "SELECT GL08ACTION1 FROM GLSTAT WHERE GL08STAT = '" + this.msPatronStatus + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronElig = String.valueOf(rsObj.getString("GL08ACTION1"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronElig;
    }

    public void setMsPatronElig(String msPatronElig) {
        this.msPatronElig = msPatronElig;
    }

    public String getMsPatronCategory() {
        return this.msPatronCategory;
    }

    public String getMsPatronCategory(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        System.out.println("Category" + sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCategory;
    }

    public String getMsPatronCateDesc() {
        this.msPatronCategory = this.getMsPatronCategory(this.msPatronId);
        String sql = "SELECT GL07DESC FROM GLCATE WHERE GL07CATE= '" + this.msPatronCategory + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCateDesc = String.valueOf(rsObj.getString("GL07DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCateDesc;
    }

    public int getMsPatrCateEligNo() {
        String sql = "Select GL07CATE, GL07ELIG From GLCATE Where GL07CATE = '" + this.msPatronCateDesc.trim() + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCateEligNo = rsObj.getString("GL07ELIG").equals(null) ? 0 : Integer.parseInt(rsObj.getString("GL07ELIG"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCateEligNo;
    }

    public int getMsPatrCateEligNo(String msPatronCateDesc) {
        String sql = "Select GL07CATE, GL07ELIG From GLCATE Where GL07CATE = '" + this.msPatronCateDesc.trim() + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCateEligNo = rsObj.getString("GL07ELIG").equals(null) ? 0 : Integer.parseInt(rsObj.getString("GL07ELIG"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCateEligNo;
    }

    public void setMsPatronCateDesc(String msPatronCateDesc) {
        this.msPatronCateDesc = msPatronCateDesc;
    }

    public void setMsPatronCateEligNo(int msPatronCateEligNo) {
        this.msPatronCateEligNo = msPatronCateEligNo;
    }

    public void setMsPatronBranchDesc(String msPatronBranchDesc) {
        this.msPatronBranchDesc = msPatronBranchDesc;
    }

    public String getMsPatronBranch() {
        return this.msPatronBranch;
    }

    public String getMsPatronBranch(String msPatronID) {
        String sql = "SELECT GL14BRNC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        System.out.println("Branch" + sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronBranch = String.valueOf(rsObj.getString("GL14BRNC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronBranch;
    }

    public String getMsPatronBranchDesc() {
        String sql = "Select GL71BRNC, GL71DESC From GLBRNC Where GL71BRNC = '" + this.msPatronBranch + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronBranchDesc = String.valueOf(rsObj.getString("GL71DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronBranchDesc;
    }

    public String getMsPatronCourse() {
        return this.msPatronCourse;
    }

    public String getMsPatronCourse(String msPatronID) {
        String sql = "SELECT GL14COURSE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCourse = String.valueOf(rsObj.getString("GL14COURSE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCourse;
    }

    public String getMsPatronCourseDesc(String msPatronCourse) {
        String sql = "Select GL12COURSE, GL12DESC From GLCOUR Where GL12COURSE = '" + msPatronCourse + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCourseDesc = String.valueOf(rsObj.getString("GL12DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCourseDesc;
    }

    public String getMsPatronCourseDesc() {
        String sql = "Select GL12COURSE, GL12DESC From GLCOUR Where GL12COURSE = '" + this.msPatronCourse + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCourseDesc = String.valueOf(rsObj.getString("GL12DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCourseDesc;
    }

    public String getMsPatronDept() {
        return this.msPatronDept;
    }

    public String getMsPatronDept(String msPatronID) {
        String sql = "SELECT GL14DEPT FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronDept = String.valueOf(rsObj.getString("GL14DEPT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronDept;
    }

    public String getMsPatronDeptDesc() {
        String sql = "Select GL11DEPT, GL11NAME From GLDEPT Where GL11DEPT = '" + this.msPatronDept + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronDeptDesc = String.valueOf(rsObj.getString("GL11NAME"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronDeptDesc;
    }

    public String getMsPatronSerialNo() {
        return this.msPatronSerialNo;
    }

    public String getMsPatronSerialNo(String msPatronID) {
        String sql = "SELECT GL14SECURE FROM GLPATR WHERE GL14PATR = '" + msPatronID + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronSerialNo = String.valueOf(rsObj.getString("GL14SECURE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronSerialNo;
    }

    public String getMsPatronReservePriority(String msAccessionNo) {
        String sql = "Select Distinct CI03PATR,CI03MATNO,CI03PRIOR,CI03DOCNO,CI03ICAT From CIRESV Where CI03NSTAT = 'A' And CI03DOCNO = '" + msAccessionNo + "'" + "Order By CI03PRIOR";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msHighestPriorityPatronID = rsObj.getString("CI03PATR");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msHighestPriorityPatronID;
    }

    public String getMsParentinfo() {
        return this.msParentInfo;
    }

    public String getMsParentinfo(String msPatronID) {
        String sql = "SELECT GL14PARENTID FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msParentInfo = String.valueOf(rsObj.getString("GL14PARENTID"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msParentInfo;
    }

    public String getMsPatronPassword() {
        return this.msPatronPassword;
    }

    public String getMsPatronPassword(String msPatronID) {
        String sql = "SELECT GL14PASW FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronPassword = String.valueOf(rsObj.getString("GL14PASW"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronPassword;
    }

    public String getMsPatronHomeAdd1() {
        return this.msPatronHomeAdd1;
    }

    public String getMsPatronHomeAdd2() {
        return this.msPatronHomeAdd2;
    }

    public String getMsPatronHomeAdd3() {
        return this.msPatronHomeAdd3;
    }

    public String getMsPatronHomeAdd1(String msPatronID) {
        String sql = "SELECT GL14ADD1 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronHomeAdd1 = String.valueOf(rsObj.getString("GL14ADD1"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronHomeAdd1;
    }

    public void setMsPatronHomeAdd1(String msPatronHomeAdd1) {
        this.msPatronHomeAdd1 = msPatronHomeAdd1;
    }

    public String getMsPatronHomeAdd2(String msPatronID) {
        String sql = "SELECT GL14ADD2 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronHomeAdd2 = String.valueOf(rsObj.getString("GL14ADD2"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronHomeAdd2;
    }

    public void setMsPatronHomeAdd2(String msPatronHomeAdd2) {
        this.msPatronHomeAdd2 = msPatronHomeAdd2;
    }

    public String getMsPatronHomeAdd3(String msPatronID) {
        String sql = "SELECT GL14ADD3 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronHomeAdd3 = String.valueOf(rsObj.getString("GL14ADD3"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronHomeAdd3;
    }

    public void setMsPatronHomeAdd3(String msPatronHomeAdd3) {
        this.msPatronHomeAdd3 = msPatronHomeAdd3;
    }

    public String getMsPatronOHomeAdd1() {
        return this.msPatronOHomeAdd1;
    }

    public String getMsPatronOHomeAdd2() {
        return this.msPatronOHomeAdd2;
    }

    public String getMsPatronOHomeAdd3() {
        return this.msPatronOHomeAdd3;
    }

    public String getMsPatronOHomeAdd1(String msPatronID) {
        String sql = "SELECT GL14ADD21 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronOHomeAdd1 = String.valueOf(rsObj.getString("GL14ADD21"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronOHomeAdd1;
    }

    public void setMsPatronOHomeAdd1(String msPatronOHomeAdd1) {
        this.msPatronOHomeAdd1 = msPatronOHomeAdd1;
    }

    public String getMsPatronOHomeAdd2(String msPatronID) {
        String sql = "SELECT GL14ADD22 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronOHomeAdd2 = String.valueOf(rsObj.getString("GL14ADD22"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronOHomeAdd2;
    }

    public void setMsPatronOHomeAdd2(String msPatronOHomeAdd2) {
        this.msPatronOHomeAdd2 = msPatronOHomeAdd2;
    }

    public String getMsPatronOHomeAdd3(String msPatronID) {
        String sql = "SELECT GL14ADD23 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronOHomeAdd3 = String.valueOf(rsObj.getString("GL14ADD23"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronOHomeAdd3;
    }

    public void setMsPatronOHomeAdd3(String msPatronOHomeAdd3) {
        this.msPatronOHomeAdd3 = msPatronOHomeAdd3;
    }

    public String getMsPatronOldIC() {
        return this.msPatronOldIC;
    }

    public String getMsPatronOldIC(String msPatronID) {
        String sql = "SELECT GL14OLDIC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronOldIC = String.valueOf(rsObj.getString("GL14OLDIC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronOldIC;
    }

    public void setMsPatronOldIC(String msPatronOldIC) {
        this.msPatronOldIC = msPatronOldIC;
    }

    public String getMsPatronNewIC() {
        return this.msPatronNewIC;
    }

    public String getMsPatronNewIC(String msPatronID) {
        String sql = "SELECT GL14NEWIC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronNewIC = String.valueOf(rsObj.getString("GL14NEWIC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronNewIC;
    }

    public void setMsPatronNewIC(String msPatronNewIC) {
        this.msPatronNewIC = msPatronNewIC;
    }

    public String getMsPatronCode() {
        return this.msPatronCode;
    }

    public String getMsPatronCode(String msPatronID) {
        String sql = "SELECT GL14CODE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCode = String.valueOf(rsObj.getString("GL14CODE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCode;
    }

    public void setMsPatronCode(String msPatronCode) {
        this.msPatronCode = msPatronCode;
    }

    public String getMsPatronTown() {
        return this.msPatronTown;
    }

    public String getMsPatronTown(String msPatronID) {
        String sql = "SELECT GL14TOWN FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronTown = String.valueOf(rsObj.getString("GL14TOWN"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronTown;
    }

    public void setMsPatronTown(String msPatronTown) {
        this.msPatronTown = msPatronTown;
    }

    public String getMsPatronHTel() {
        return this.msPatronHTel;
    }

    public String getMsPatronHTel(String msPatronID) {
        String sql = "SELECT GL14HTEL FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronHTel = String.valueOf(rsObj.getString("GL14HTEL"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronHTel;
    }

    public void setMsPatronHTel(String msPatronHTel) {
        this.msPatronHTel = msPatronHTel;
    }

    public String getMsPatronOTel() {
        return this.msPatronOTel;
    }

    public String getMsPatronOTel(String msPatronID) {
        String sql = "SELECT GL14OTEL FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronHTel = String.valueOf(rsObj.getString("GL14OTEL"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronOTel;
    }

    public void setMsPatronOTel(String msPatronOTel) {
        this.msPatronOTel = msPatronOTel;
    }

    public String getMsPatronFax() {
        return this.msPatronFax;
    }

    public String getMsPatronFax(String msPatronID) {
        String sql = "SELECT GL14FAX FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronFax = String.valueOf(rsObj.getString("GL14FAX"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronFax;
    }

    public void setMsPatronFax(String msPatronFax) {
        this.msPatronFax = msPatronFax;
    }

    public String getMsPatronDOB() {
        return this.msPatronDOB;
    }

    public String getMsPatronDOB(String msPatronID) {
        String sql = "SELECT GL14DOB FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronDOB = String.valueOf(rsObj.getString("GL14DOB"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronDOB;
    }

    public void setMsPatronDOB(String msPatronDOB) {
        this.msPatronDOB = msPatronDOB;
    }

    public String getMsPatronSex() {
        return this.msPatronSex;
    }

    public String getMsPatronSex(String msPatronID) {
        String sql = "SELECT GL14SEX FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronSex = String.valueOf(rsObj.getString("GL14SEX"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronSex;
    }

    public void setMsPatronSex(String msPatronSex) {
        this.msPatronSex = msPatronSex;
    }

    public String getMsPatronRace() {
        return this.msPatronRace;
    }

    public String getMsPatronRace(String msPatronID) {
        String sql = "SELECT GL14RACE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronSex = String.valueOf(rsObj.getString("GL14RACE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronRace;
    }

    public void setMsPatronRace(String msPatronRace) {
        this.msPatronRace = msPatronRace;
    }

    public String getMsPatronDesc(String msPatronID) {
        String sql = "SELECT GL14DESC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronSex = String.valueOf(rsObj.getString("GL14DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronDesc;
    }

    public void setMsPatronDesc(String msPatronDesc) {
        this.msPatronDesc = msPatronDesc;
    }

    public String getMsPatronCode2() {
        return this.msPatronCode2;
    }

    public String getMsPatronCode2(String msPatronID) {
        String sql = "SELECT GL14CODE2 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronCode2 = String.valueOf(rsObj.getString("GL14CODE2"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronCode2;
    }

    public void setMsPatronCode2(String msPatronCode2) {
        this.msPatronCode2 = msPatronCode2;
    }

    public String getMsPatronTown2() {
        return this.msPatronTown2;
    }

    public String getMsPatronTown2(String msPatronID) {
        String sql = "SELECT GL14TOWN2 FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronTown2 = String.valueOf(rsObj.getString("GL14TOWN2"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronTown2;
    }

    public void setMsPatronTown2(String msPatronTown2) {
        this.msPatronTown2 = msPatronTown2;
    }

    public void setMsMemDate(String msMemDate) {
        this.msMemDate = msMemDate;
    }

    public String getMsMemExpiryDate() {
        return this.msMemExpiryDate;
    }

    public void setMsMemExpiryDate(String msMemExpiryDate) {
        this.msMemExpiryDate = msMemExpiryDate;
    }

    public String getMsMemFee() {
        return this.msMemFee;
    }

    public String getMsMemFee(String msPatronID) {
        String sql = "Select GL14MEMFEEFrom GLPATR Where GL14PATR = '" + msPatronID + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msMemFee = String.valueOf(rsObj.getString("GL14MEMFEE"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msMemFee;
    }

    public void setMsMemFee(String msMemFee) {
        this.msMemFee = msMemFee;
    }

    public String getMsMemDeposit() {
        return this.msMemDeposit;
    }

    public String getMsMemDeposit(String msPatronID) {
        String sql = "Select GL14DEPOSITFrom GLPATR Where GL14PATR = '" + msPatronID + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msMemDeposit = String.valueOf(rsObj.getString("GL14DEPOSIT"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msMemDeposit;
    }

    public void setMsMemDeposit(String msMemDeposit) {
        this.msMemDeposit = msMemDeposit;
    }

    public static boolean chkItemAvailable(String CT03DOCNO) {
        String sql = "";
        sql = String.valueOf(sql) + "Select Count(*) As  ItemAvailable From CTDOCM Where (CT03STAT = 'C' OR CT03STAT='H') AND CT03DOCNO= '" + CT03DOCNO + "'";
        System.out.println(sql);
        boolean deletable = false;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (Integer.parseInt(rsObj.getString("ItemAvailable")) != 0) continue;
                System.out.println("In");
                deletable = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return deletable;
    }

    public boolean getResvSattelite() {
        boolean exist = false;
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC= 'RSVBYBRANCH'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL99VALUE").equals("Y")) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public String getPatrBrnc(String msPatronID) {
        String branch = "";
        String sql = "SELECT GL14BRNC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                branch = String.valueOf(rsObj.getString("GL14BRNC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }

    public String getItemBrnc(String accessionNo) {
        String branch = "";
        String sql = "SELECT GL05BRNC FROM CTDOCM, GLLOCA WHERE CT03LOCA = GL05LOCA AND CT03DOCNO= '" + accessionNo + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                branch = String.valueOf(rsObj.getString("GL05BRNC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }

    public static boolean ableResv(String GL14PATR) {
        String query = "SELECT COUNT(*) AS Count FROM GLPATR, GLSTAT WHERE GL14STAT=GL08STAT and GL14PATR = '" + GL14PATR + "' and GL08ACTION4='Y'";
        boolean deletable = true;
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("Count")) != 0) continue;
                deletable = false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(deletable);
        return deletable;
    }

    public boolean executeReserve(String CI03PATR, String CI03DOCNO, String CI03MATNO) {
        boolean patrMemExp = false;
        String patrCate = "";
        boolean patrCanChargeAccession = false;
        boolean validAccession = false;
        boolean updateAccession = false;
        boolean addTransaction = false;
        boolean updatePatron = false;
        boolean bSucessful = false;
        boolean validReservation = false;
        boolean patrAllowedOverdue = false;
        boolean checkIfItemInBuffer = false;
        boolean itemUnCharged = false;
        boolean itemMissing = false;
        boolean itemRecall = false;
        boolean ableReserve = false;
        Patron patr = new Patron(CI03PATR);
        patrMemExp = Patron.patrMem(CI03PATR);
        if (patrMemExp) {
            bSucessful = true;
            if (Patron.patrStatus(CI03PATR)) {
                bSucessful = true;
            } else {
                this.errmessage = "050";
                bSucessful = false;
            }
        } else {
            this.errmessage = "032";
            bSucessful = false;
        }
        ableReserve = Reservation.ableResv(CI03PATR);
        if (ableReserve) {
            bSucessful = true;
        } else {
            this.errmessage = "101";
            bSucessful = false;
        }
        if (this.getResvSattelite()) {
            if (!this.getItemBrnc(CI03DOCNO).equals("")) {
                if (this.getItemBrnc(CI03DOCNO).equals(this.getPatrBrnc(CI03PATR))) {
                    bSucessful = true;
                } else {
                    bSucessful = false;
                    this.errmessage = "085";
                }
            } else {
                bSucessful = true;
            }
        } else {
            System.out.println("Test1" + this.getItemBrnc(CI03DOCNO));
            bSucessful = true;
        }
        if (bSucessful) {
            if (this.bItemIsAvailable(CI03PATR, CI03MATNO, CI03DOCNO)) {
                if (this.bIsReserved(CI03PATR, CI03MATNO, CI03DOCNO)) {
                    if (this.bIsBorrowed(CI03PATR, CI03MATNO, CI03DOCNO)) {
                        if (this.bIsPatronBorrowed_ItemCat(CI03PATR, CI03MATNO, CI03DOCNO, this.CI03ICAT)) {
                            if (this.bCanPatronBorrow(CI03PATR, CI03MATNO, CI03DOCNO, this.CI03ICAT)) {
                                patrCate = this.patronCategory(CI03PATR);
                                if (this.msCateAllowResv.trim().equals("Y")) {
                                    int totalReserved = this.totalResvItem(CI03PATR);
                                    if (totalReserved >= this.msCateMaxResv && this.msCateMaxResv != 0) {
                                        bSucessful = false;
                                        this.errmessage = "076," + this.msCateMaxResv;
                                    } else {
                                        bSucessful = true;
                                    }
                                } else {
                                    this.errmessage = "075," + CI03PATR;
                                    bSucessful = false;
                                }
                            } else {
                                this.errmessage = "079";
                                bSucessful = false;
                            }
                        } else {
                            this.errmessage = "078";
                            bSucessful = false;
                        }
                    } else {
                        this.errmessage = "078";
                        bSucessful = false;
                    }
                } else {
                    this.errmessage = "060";
                }
            } else {
                this.errmessage = "077";
                bSucessful = false;
            }
        }
        return bSucessful;
    }

    public boolean bIsBorrowed(String vsPatronID, String vsControlNo, String docno) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) as Count FROM CICIRC WHERE CI02PATR = '" + vsPatronID + "' ";
        System.out.println("Docno" + docno);
        sql = vsControlNo == null || vsControlNo.equals("0") ? String.valueOf(sql) + " AND  CI02MATNO = (SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + docno + "') " + " AND  CI02FLAG = 'C'" : String.valueOf(sql) + " AND  CI02MATNO = '" + vsControlNo + "' " + " AND  CI02FLAG = 'C'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean bCanPatronBorrow(String vsPatronID, String vsControlNo, String docno, String vsPatronCategory) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) as Count FROM CTDOCM, GLELIG, GLLOCA, GLPATR ";
        sql = vsControlNo == null || vsControlNo.equals("0") ? String.valueOf(sql) + "WHERE CT03MATNO = (SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + docno + "') " + " AND GL27CATE = GL14CATE" + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y') " + "AND GL14PATR = '" + vsPatronID + "'" : String.valueOf(sql) + "WHERE CT03MATNO = '" + vsControlNo + "' " + " AND GL27CATE = GL14CATE" + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y') " + "AND GL14PATR = '" + vsPatronID + "'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                System.out.println(Integer.parseInt(rsObj.getString("Count")));
                if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean bIsPatronBorrowed_ItemCat(String vsPatronID, String vsControlNo, String docno, String itemCat) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) as count FROM CTDOCM, GLICAT  ";
        sql = vsControlNo == null || vsControlNo.equals("0") ? String.valueOf(sql) + "WHERE CT03MATNO = (SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + docno + "') " + " AND  CT03PATR = '" + vsPatronID + "' " + " AND  GL10ICAT=CT03ICAT AND CT03STAT IN ('C') " : String.valueOf(sql) + "WHERE CT03MATNO = '" + vsControlNo + "' " + " AND  CT03PATR = '" + vsPatronID + "' " + " AND  GL10ICAT=CT03ICAT AND CT03STAT IN ('C') ";
        if (itemCat != null) {
            sql = String.valueOf(sql) + " AND  CT03ICAT = '" + itemCat + "' ";
        }
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean bIsReserved(String vsPatronID, String vsControlNo, String docno) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) as Count FROM CIRESV WHERE CI03PATR = '" + vsPatronID + "' ";
        sql = vsControlNo == null || vsControlNo.equals("0") ? String.valueOf(sql) + " AND  CI03MATNO =(SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO= '" + docno + "')" : String.valueOf(sql) + " AND  CI03MATNO = '" + vsControlNo + "'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean bItemIsAvailable(String vsPatronID, String vsControlNo, String docno) {
        boolean exist = false;
        String sql = "SELECT COUNT(*) as Count FROM CTDOCM ";
        sql = vsControlNo.equals("0") ? String.valueOf(sql) + " WHERE  CT03DOCNO ='" + docno + "' AND CT03STAT='A'" : String.valueOf(sql) + " WHERE  CT03MATNO = '" + vsControlNo + "' AND CT03STAT='A'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public String patronCategory(String GL14PATR) {
        String sql = "SELECT GL07ALLOWRSV, GL07MAXRESV FROM GLCATE, GLPATR WHERE GL14CATE=GL07CATE AND GL14PATR='" + GL14PATR + "'";
        System.out.println(sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msCateAllowResv = rsObj.getString("GL07ALLOWRSV");
                this.msCateMaxResv = rsObj.getString("GL07MAXRESV") == null ? 0 : Integer.parseInt(rsObj.getString("GL07MAXRESV"));
                System.out.println("N" + this.msCateMaxResv);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msCateAllowResv;
    }

    public int totalResvItem(String GL14PATR) {
        int totalResv = 0;
        try {
            String sql = "SELECT Count(CI03DOCNO) as count FROM CIRESV WHERE CI03PATR='" + GL14PATR + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                totalResv = rsObj.getInt("count");
                System.out.println(totalResv);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return totalResv;
    }

    public boolean checkAccession(String CI03DOCNO, String CI03MATNO) {
        boolean validAccession = false;
        boolean bSucessful = false;
        boolean chkItemStat = false;
        boolean chkItemMatno = false;
        Charging chr = new Charging();
        if (CI03DOCNO != null && CI03MATNO == null) {
            validAccession = chr.RetrieveAccessionDetail(CI03DOCNO);
            if (validAccession) {
                bSucessful = true;
                chkItemStat = Reservation.chkItemStat(CI03DOCNO, "CT03DOCNO");
                if (chkItemStat) {
                    bSucessful = true;
                } else {
                    this.errmessage = "049," + chr.getItemStatus(CI03DOCNO);
                    bSucessful = false;
                }
            } else {
                this.errmessage = "020";
                bSucessful = false;
            }
        }
        if (CI03MATNO != null) {
            chkItemMatno = this.chkAccessionList(CI03MATNO);
            if (!chkItemMatno) {
                bSucessful = true;
                chkItemStat = Reservation.chkItemStat(CI03MATNO, "CT03MATNO");
                if (chkItemStat) {
                    bSucessful = true;
                } else {
                    this.errmessage = "088";
                    bSucessful = false;
                }
            } else {
                bSucessful = false;
                this.errmessage = "070";
            }
        }
        return bSucessful;
    }

    public static boolean chkItemStat(String CT03DOCNO, String action) {
        String sql = "Select Count(CT03DOCNO) As  accessionNo From CTDOCM ";
        if (action.equals("CT03DOCNO")) {
            sql = String.valueOf(sql) + "WHERE CT03DOCNO= '" + CT03DOCNO + "'";
            sql = String.valueOf(sql) + "AND (CT03STAT!='C' AND CT03STAT!='H' AND CT03STAT!='E')";
        } else {
            sql = String.valueOf(sql) + "WHERE CT03MATNO= '" + CT03DOCNO + "'";
            sql = String.valueOf(sql) + "AND (CT03STAT='C' OR CT03STAT='H')";
        }
        System.out.println(sql);
        boolean deletable = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    System.out.println("Delete");
                    if (action.equals("CT03DOCNO")) {
                        if (Integer.parseInt(rsObj.getString("accessionNo")) != 0) continue;
                        System.out.println("Delete2");
                        deletable = true;
                        continue;
                    }
                    if (Integer.parseInt(rsObj.getString("accessionNo")) <= 0) continue;
                    System.out.println("Delete1");
                    deletable = true;
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
        return deletable;
    }

    public boolean chkAccessionList(String CT03MATNO) {
        String sql = "";
        sql = String.valueOf(sql) + "Select Count(CT03DOCNO) As  accessionNo From CTDOCM WHERE CT03MATNO= '" + CT03MATNO + "'";
        System.out.println(sql);
        boolean deletable = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("accessionNo")) != 0) continue;
                    System.out.println("In");
                    deletable = true;
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
        return deletable;
    }

    public boolean chkMatList(String CT03MATNO) {
        String sql = "";
        sql = String.valueOf(sql) + "Select Count(CT03DOCNO) As  accessionNo From CTDOCM WHERE CT03MATNO= '" + CT03MATNO + "' and CT03STAT='A'";
        System.out.println(sql);
        boolean deletable = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("accessionNo")) != 0) continue;
                    System.out.println("In");
                    deletable = true;
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
        return deletable;
    }

    public static List<Reservation> retrieveLoca(String Ct03MATNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select Distinct CT03LOCA, GL05DESC from CTDOCM, GLLOCA where GL05LOCA=CT03LOCA and CT03MATNO = '" + Ct03MATNO + "' and (CT03STAT='H' OR CT03STAT='C')";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(rsObj.getString("CT03LOCA"), rsObj.getString("GL05DESC"));
                    list.add(info);
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

    public static List<Reservation> retrieveAcc(String CT03MATNO, String CT03LOCA) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select Distinct CT03DOCNO from CTDOCM ";
        query = CT03LOCA.equals("0") ? String.valueOf(query) + "where CT03MATNO = '" + CT03MATNO + "' and (CT03STAT='H' OR CT03STAT='C')" : String.valueOf(query) + "where CT03LOCA = '" + CT03LOCA + "' and CT03MATNO = '" + CT03MATNO + "' and (CT03STAT='H' OR CT03STAT='C')";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(rsObj.getString("CT03DOCNO"));
                    list.add(info);
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

    public static boolean canResv(String CI03MATNO) {
        boolean deletable;
        block12: {
            String sql = "Select Count(*) As  Exist From CTDOCM Where CT03MATNO= '" + CI03MATNO + "' AND (CT03STAT='C' OR CT03STAT='H')";
            System.out.println(sql);
            deletable = false;
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    rsObj = stmt.executeQuery(sql);
                    while (rsObj.next()) {
                        if (Integer.parseInt(rsObj.getString("Exist")) <= 0) continue;
                        System.out.println("sd");
                        deletable = true;
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
                    break block12;
                }
            }
            catch (Throwable throwable) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                throw throwable;
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(deletable);
        return deletable;
    }

    public static List<Reservation> getPatrInfo(String GL14PATR) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select GL14NAME, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14EXPDATE,\tGL28POSCODE, GL28ORGNAME, GL28ADD1, GL28ADD2 from GLPATR, GLLIBR where GL14BRNC=GL28BRNC AND GL14PATR = '" + GL14PATR + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(rsObj.getString("GL14NAME"), Handler.ifIsNull(rsObj.getString("GL14ADD1")), Handler.ifIsNull(rsObj.getString("GL14ADD2")), Handler.ifIsNull(rsObj.getString("GL14ADD3")), rsObj.getString("GL14EXPDATE"), rsObj.getString("GL28ORGNAME"), rsObj.getString("GL28POSCODE"), rsObj.getString("GL28ADD1"), rsObj.getString("GL28ADD2"), rsObj.getString("GL14CODE"));
                    list.add(info);
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

    public static List<Reservation> getItemInfo(String CT03DOCNO, String action) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "";
        if (action.equals("accession")) {
            query = "Select CT05SRAW, GL05LOCA, GL05DESC from CTDOCM,GLLOCA, CTPONT, CTTITL where GL05LOCA = CT03LOCA AND CT03MATNO = CT06MATNO AND CT05POINTER = CT06POINTER AND CT06TAG = '245' ";
            query = String.valueOf(query) + "AND CT03DOCNO= '" + CT03DOCNO + "'";
            System.out.println(query);
        } else if (action.equals("ctrlno")) {
            query = "Select Distinct CT05SRAW from CTDOCM,GLLOCA, CTPONT, CTTITL where GL05LOCA = CT03LOCA AND CT03MATNO = CT06MATNO AND CT05POINTER = CT06POINTER AND CT06TAG = '245' ";
            query = String.valueOf(query) + "AND CT03DOCNO IN (SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO='" + CT03DOCNO + "')";
        }
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rsObj.next()) {
                    Reservation info;
                    if (action.equals("accession")) {
                        info = new Reservation(Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd), rsObj.getString("GL05LOCA"), rsObj.getString("GL05DESC"));
                    } else {
                        System.out.println("sdw");
                        info = new Reservation(Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd));
                    }
                    list.add(info);
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

    public static List<Reservation> getCirculatedInfo(String CT03DOCNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI02DOCNO, CI02DDATE, CI02CDATE,CI02CTIME, CI02OFFI, GL14NAME from CICIRC, GLPATR where CI02OFFI=GL14PATR AND CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG='C'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(GeneralUtility.StrToDate(rsObj.getString("CI02DDATE")), GeneralUtility.StrToDate(rsObj.getString("CI02CDATE")), GeneralUtility.StrToTime2(rsObj.getString("CI02CTIME")), rsObj.getString("CI02OFFI"), rsObj.getString("GL14NAME"));
                    list.add(info);
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

    public static List<Reservation> getDischargeInfo(String CT03DOCNO, String CI02DDATE, String CI02CDATE, String CI02DTIME, String CI02CTIME) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        System.out.println("Date" + CI02CDATE + CI02DDATE);
        String query = "Select CI02DOCNO, CI02DDATE, CI02CDATE,CI02CTIME, CI02OFFI, GL14NAME from CICIRC, GLPATR where CI02PATR=GL14PATR AND CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG='D' " + "AND CI02CDATE='" + GeneralUtility.splitDate(CI02CDATE) + "' AND CI02DDATE='" + GeneralUtility.splitDate(CI02DDATE) + "' " + " AND CI02CTIME='" + GeneralUtility.TimetoStr(CI02CTIME) + "' AND CI02DTIME='" + GeneralUtility.TimetoStr(CI02DTIME) + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(GeneralUtility.StrToDate(rsObj.getString("CI02DDATE")), GeneralUtility.StrToDate(rsObj.getString("CI02CDATE")), GeneralUtility.StrToTime2(rsObj.getString("CI02CTIME")), rsObj.getString("CI02OFFI"), rsObj.getString("GL14NAME"));
                    list.add(info);
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

    public static List<Reservation> getRenewInfo(String CT03DOCNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI02DOCNO, CI02DDATE, CI02CDATE,CI02CTIME, CI02OFFI, GL14NAME from CICIRC, GLPATR where CI02PATR=GL14PATR AND CI02DOCNO = '" + CT03DOCNO + "' AND CI02FLAG='C' ";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    Reservation info = new Reservation(GeneralUtility.StrToDate(rsObj.getString("CI02DDATE")), GeneralUtility.StrToDate(rsObj.getString("CI02CDATE")), GeneralUtility.StrToTime2(rsObj.getString("CI02CTIME")), rsObj.getString("CI02OFFI"), rsObj.getString("GL14NAME"));
                    list.add(info);
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

    public static List<Reservation> getListResv(String CI03DOCNO, String CI03MATNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI03DOCNO, CI03PATR, GL14NAME, CI03RDATE, CI03BRNC, CI03NDATE, CI03ICAT, CI03NSTAT, CI03DDATE  FROM GLPATR, CIRESV WHERE CI03PATR = GL14PATR ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "and CI03MATNO = (Select CT03MATNO FROM CTDOCM WHERE CT03DOCNO ='" + CI03DOCNO + "') ORDER BY CI03PRIOR";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "and CI03MATNO = (Select CT03MATNO FROM CTDOCM WHERE CT03DOCNO ='" + CI03DOCNO + "') ORDER BY CI03PRIOR";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "and CI03MATNO = '" + CI03MATNO + "' ORDER BY CI03PRIOR";
            }
        }
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            while (rsObj.next()) {
                System.out.println(rsObj.getString("CI03NDATE"));
                Reservation info = new Reservation(rsObj.getString("CI03DOCNO"), rsObj.getString("CI03PATR"), Handler.defaultValue(rsObj.getString("CI03BRNC")), DateTime.formatDateV2(rsObj.getString("CI03NDATE")), DateTime.formatDateV2(rsObj.getString("CI03DDATE")), DateTime.formatDateV2(rsObj.getString("CI03RDATE")), rsObj.getString("CI03NSTAT"), rsObj.getString("GL14NAME"), Handler.ifIsNull(rsObj.getString("CI03ICAT")));
                list.add(info);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Reservation> getListResvScrutiny(String CI03DOCNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI03NDATE, CI03PATR, GL14NAME, CI03PRIOR, CI03MATNO, CI03DOCNO, CT05SRAW from CIRESV, GLPATR, CTPONT, CTTITL where GL14PATR=CI03PATR and CT06MATNO = CI03MATNO and CT06POINTER = CT05POINTER and CT06TAG = '245' and CI03DOCNO = '" + CI03DOCNO + "' and CI03NSTAT='A'";
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            List<ISBD> isbd = ISBD.getPunctuation("245");
            while (rsObj.next()) {
                System.out.println(rsObj.getString("CI03NDATE"));
                Reservation info = new Reservation(DateTime.formatDateV2(rsObj.getString("CI03NDATE")), rsObj.getString("CI03PATR"), rsObj.getString("GL14NAME"), Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd), rsObj.getString("CI03PRIOR"), rsObj.getString("CI03MATNO"), rsObj.getString("CI03DOCNO"));
                list.add(info);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Reservation> getListResvScrutinyByDays(String NoOfDays) throws ParseException {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        Date today = new Date();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String query = "Select CI03NDATE,CI03PATR, GL14NAME, CI03PRIOR, CI03MATNO, CI03DOCNO, CT05SRAW from CIRESV, GLPATR, CTPONT, CTTITL where GL14PATR=CI03PATR and CT06MATNO = CI03MATNO and CT06POINTER = CT05POINTER and CT06TAG = '245' and CI03NSTAT='A' and CI03NDATE IS NOT NULL order by CI03NDATE";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("245");
                while (rsObj.next()) {
                    Date date = new Date();
                    Date notificationDate = new SimpleDateFormat("dd/MM/yyyy").parse(DateTime.formatDateV2(rsObj.getString("CI03NDATE")));
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.setTime(notificationDate);
                    cal.add(5, Integer.parseInt(NoOfDays));
                    if (date.compareTo(cal.getTime()) <= 0) continue;
                    Reservation info = new Reservation(DateTime.formatDateV2(rsObj.getString("CI03NDATE")), rsObj.getString("CI03PATR"), rsObj.getString("GL14NAME"), Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd), rsObj.getString("CI03PRIOR"), rsObj.getString("CI03MATNO"), rsObj.getString("CI03DOCNO"));
                    list.add(info);
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

    public static List<Reservation> retrievResvByPatr(String GL14PATR) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI03DOCNO, CI03PATR, GL14NAME, CI03RDATE, CI03BRNC, CI03NDATE, CI03ICAT, CI03NSTAT, CI03DDATE  FROM GLPATR, CIRESV WHERE CI03PATR = GL14PATR and GL14PATR='" + GL14PATR + "'";
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            while (rsObj.next()) {
                Reservation info = new Reservation(rsObj.getString("CI03DOCNO"), rsObj.getString("CI03PATR"), Handler.defaultValue(rsObj.getString("CI03BRNC")), DateTime.formatDateV2(rsObj.getString("CI03NDATE")), DateTime.formatDateV2(rsObj.getString("CI03DDATE")), DateTime.formatDateV2(rsObj.getString("CI03RDATE")), rsObj.getString("CI03NSTAT"), rsObj.getString("GL14NAME"), Handler.ifIsNull(rsObj.getString("CI03ICAT")));
                list.add(info);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Reservation> getCallNo(String CT03DOCNO, String action) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select Distinct CT05SRAW from CTDOCM, GLLOCA, CTPONT, CTCALL where GL05LOCA = CT03LOCA AND CT03MATNO = CT06MATNO AND CT05POINTER = CT06POINTER AND CT06TAG = '090' ";
        if (action.equals("accession")) {
            query = String.valueOf(query) + "AND CT03DOCNO= '" + CT03DOCNO + "'";
        } else if (action.equals("ctrlno")) {
            query = String.valueOf(query) + "AND CT03DOCNO IN (SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO='" + CT03DOCNO + "')";
        }
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                List<ISBD> isbd = ISBD.getPunctuation("090");
                while (rsObj.next()) {
                    Reservation info = new Reservation(Handler.getSubfield(rsObj.getString("CT05SRAW"), isbd));
                    list.add(info);
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

    public String getMsNoCircByPatron(String msPatronID) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02FLAG = 'C' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
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

    public String getMsNoCircByPatron() {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + this.msPatronId.trim() + "' " + "And CI02FLAG = 'C' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
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

    public int getMsGetLostItemCount(String msPatronID) {
        this.msNoGetLostItem = 0;
        String sql = "Select COUNT(RE01PATR) AS count From RETRXN Where RE01PATR = '" + msPatronID.trim() + "' " + "And RE01CODE = '104'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetLostItem = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetLostItem;
    }

    public int getMsGetLostItemCount() {
        this.msNoGetLostItem = 0;
        String sql = "Select COUNT(RE01PATR) AS count From RETRXN Where RE01PATR = '" + this.msPatronId + "' " + "And RE01CODE = '104'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetLostItem = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetLostItem;
    }

    public static int getNoMsGetItemOverdues(String msPatronID) {
        int overdue = 0;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyymmdd");
        String date = ft.format(dNow);
        String sql = "SELECT COUNT(*) as count FROM CICIRC WHERE CI02FLAG ='C'AND CI02DDATE < '" + date + "' " + "AND CI02PATR = '" + msPatronID + "'";
        System.out.println("Overdue Items " + sql);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                overdue = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return overdue;
    }

    public int getNoMsGetItemOverdue(String msPatronID) {
        this.msNoGetItemOverdue = 0;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyymmdd");
        String date = ft.format(dNow);
        String sql = "SELECT COUNT(*) as count FROM CICIRCWHERE CI02FLAG ='C'AND CI02DDATE < '" + date + "' " + "AND CI02PATR = '" + msPatronID + "'";
        System.out.println("Overdue Items " + sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetItemOverdue = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemOverdue;
    }

    public int getNoMsGetItemOverdue() {
        this.msNoGetItemOverdue = 0;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        String date = ft.format(dNow);
        String sql = "SELECT COUNT(*) as count FROM CICIRC WHERE CI02FLAG ='C'AND CI02DDATE < " + date + " " + "AND CI02PATR = '" + this.msPatronId + "'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetItemOverdue = rsObj.getInt("count");
                System.out.print(this.msNoGetItemOverdue);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemOverdue;
    }

    public int getNoMsGetItemReserved(String msPatronID) {
        this.msNoGetItemReserved = 0;
        String sql = "SELECT COUNT(*) as count FROM CIRESV WHERE CI03NSTAT in ('A','X') And CI03PATR = '" + this.msPatronId + "'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetItemReserved = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemReserved;
    }

    public int getNoMsGetItemReserved() {
        this.msNoGetItemReserved = 0;
        String sql = "SELECT COUNT(*) as count FROM CIRESV WHERE CI03NSTAT in ('A','X') AND CI03PATR = '" + this.msPatronId + "'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetItemReserved = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemReserved;
    }

    public static int getNoMsGetItemReserve(String patrid) {
        int reserve = 0;
        String sql = "SELECT COUNT(*) as count FROM CIRESV WHERE CI03NSTAT in ('A','X') AND CI03PATR = '" + patrid + "'";
        System.out.println(sql);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                reserve = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reserve;
    }

    public int getNoMsGetItemOnHold(String msPatronID) {
        this.msNoGetItemOnHold = 0;
        String sql = "Select COUNT(RE01PATR) AS count From RETRXN Where RE01PATR = '" + msPatronID.trim() + "' " + "And RE01CODE = '104'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetLostItem = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemOnHold;
    }

    public int getNoMsGetItemOnHold() {
        this.msNoGetItemOnHold = 0;
        String sql = "Select COUNT(RE01PATR) AS count From RETRXN Where RE01PATR = '" + this.msPatronId.trim() + "' " + "And RE01CODE = '104'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoGetLostItem = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoGetItemOnHold;
    }

    public static int getNoMsGetItemOnHolds(String patrid) {
        int onhold = 0;
        String sql = "Select COUNT(RE01PATR) AS count From RETRXN Where RE01PATR = '" + patrid.trim() + "' " + "And RE01CODE = '104'";
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                onhold = rsObj.getInt("count");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return onhold;
    }

    public static double getMsGetTotalFineOutstanding(String patrid) {
        double outstanding = 0.0;
        outstanding = Reservation.getMsGetTotalCharge(patrid) - Reservation.getMsGetTotalPaids(patrid);
        return Double.valueOf(df2.format(outstanding));
    }

    public double getMsGetTotalFinesOutstanding() {
        this.msGetTotalFinesOutstanding = this.getMsGetTotalCharged() - this.getMsGetTotalPaid();
        return Double.valueOf(df2.format(this.msGetTotalFinesOutstanding));
    }

    public double getMsGetTotalCharged() {
        String sql = "SELECT SUM(RE01AMT) AS totalcharged FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'D' AND  RE01PATR = '" + this.msPatronId + "'";
        System.out.println("sql from total charged" + sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msGetTotalFines = rsObj.getDouble("totalcharged");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msGetTotalFines;
    }

    public static double getMsGetTotalCharge(String patrid) {
        String sql = "SELECT SUM(RE01AMT) AS totalcharged FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'D' AND  RE01PATR = '" + patrid + "'";
        System.out.println("sql from total charged" + sql);
        double fines = 0.0;
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                fines = rsObj.getDouble("totalcharged");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return fines;
    }

    public double getMsGetTotalPaid() {
        String sql = "SELECT SUM(RE01AMT) AS totalpaid FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'C' AND  RE01PATR = '" + this.msPatronId + "'";
        System.out.println("sql from total paid" + sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msGetTotalFinesPaid = rsObj.getDouble("totalpaid");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msGetTotalFinesPaid;
    }

    public static double getMsGetTotalPaids(String patrid) {
        String sql = "SELECT SUM(RE01AMT) AS totalpaid FROM RETRXN,GLTRXC WHERE RE01CODE = GL38TXCD AND GL38MODE = 'C' AND  RE01PATR = '" + patrid + "'";
        System.out.println("sql from total paid" + sql);
        double paid = 0.0;
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                paid = rsObj.getDouble("totalpaid");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return paid;
    }

    public byte[] getMsPatronImage() {
        return this.msPatronImage;
    }

    public byte[] getMsPatronImage(String msPatronID) {
        String sql = "SELECT GL14IMG from GLPATR where GL14PATR='" + msPatronID + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronImage = rsObj.getBytes("GL14IMG");
                System.out.println(this.msPatronImage);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msPatronImage;
    }

    public void setMsPatronImage(byte[] msPatronImage) {
        this.msPatronImage = msPatronImage;
    }

    public boolean patronAllowedOverdue() {
        boolean allowoverdue = false;
        String sql = "SELECT GL27ALLOWOVD FROM GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'";
        System.out.println("Patron Category" + this.msPatronCategory);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL27ALLOWOVD").equals("Y")) continue;
                allowoverdue = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allowoverdue;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public static void deleteRsv(String CI02PATR, String CI02DOCNO, String CI02MATNO, String username) {
        String query = "DELETE FROM CIRESV WHERE CI03PATR = '" + CI02PATR + "' ";
        System.out.println("Accession" + CI02DOCNO + "er");
        query = !CI02DOCNO.isEmpty() && CI02DOCNO != null && !CI02DOCNO.equals("0") ? String.valueOf(query) + "AND CI03DOCNO='" + CI02DOCNO + "'" : String.valueOf(query) + "AND CI03MATNO='" + CI02MATNO + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                AuditTrail.InsertAudit("CI", "CID0001", String.valueOf(CI02MATNO) + "," + CI02DOCNO + "," + CI02PATR, username);
                delete.execute();
            }
            catch (UnknownHostException | SQLException e) {
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

    public static void deleteResv(String CI02PATR, String CI02DOCNO, String CI02MATNO, String username) {
        String query = "DELETE FROM CIRESV WHERE CI03PATR = '" + CI02PATR + "' ";
        System.out.println("Accession" + CI02DOCNO + "er");
        query = !CI02DOCNO.isEmpty() && CI02DOCNO != null && !CI02DOCNO.equals("0") ? String.valueOf(query) + "AND CI03DOCNO='" + CI02DOCNO + "'" : String.valueOf(query) + "AND CI03MATNO='" + CI02MATNO + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                AuditTrail.InsertAudit("CI", "CID0003", String.valueOf(CI02MATNO) + ";" + CI02DOCNO + ";" + CI02PATR, username);
                delete.execute();
            }
            catch (UnknownHostException | SQLException e) {
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

    public static boolean deleteReservation(String CI02PATR, String CI03DOCNO) {
        String query = "DELETE FROM CIRESV WHERE CI03PATR = '" + CI02PATR + "' AND CI03DOCNO='" + CI03DOCNO + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                delete.execute();
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
        return true;
    }

    public static boolean resvIsDeleted(String CI03PATR, String CI02DOCNO, String CI02MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM CIRESV WHERE CI03PATR='" + CI03PATR + "' ";
        if (CI02DOCNO != null && !CI02DOCNO.equals("0")) {
            query = String.valueOf(query) + "AND CI03DOCNO='" + CI02DOCNO + "'";
        }
        if (CI02MATNO != null) {
            query = CI02DOCNO != null && !CI02DOCNO.equals("0") ? String.valueOf(query) + "AND CI03DOCNO='" + CI02DOCNO + "'" : String.valueOf(query) + "AND CI03MATNO='" + CI02MATNO + "'";
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                if (rsObj.next()) {
                    deletable = true;
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
        return deletable;
    }

    public static List<Reservation> getPatrByOrder(String CI03DOCNO, String CI03MATNO) {
        ArrayList<Reservation> list = new ArrayList<Reservation>();
        String query = "Select CI03PATR, CI03MATNO FROM CIRESV WHERE ";
        System.out.println(String.valueOf(CI03DOCNO) + CI03MATNO);
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "CI03MATNO = (Select CT03MATNO FROM CTDOCM WHERE CT03DOCNO ='" + CI03DOCNO + "') ";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO = (Select CT03MATNO FROM CTDOCM WHERE CT03DOCNO ='" + CI03DOCNO + "') ";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO = '" + CI03MATNO + "' ";
            }
        }
        query = String.valueOf(query) + "ORDER BY CI03PRIOR";
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            while (rsObj.next()) {
                Reservation info = new Reservation(rsObj.getString("CI03PATR"), rsObj.getString("CI03MATNO"));
                list.add(info);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean checkPatrStat(String CI03PATR, String CI03DOCNO, String CI03MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM GLPATR, GLSTAT, GLELIG, CTDOCM WHERE ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03DOCNO = '" + CI03DOCNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL08STAT=GL14STAT AND GL08ACTION4 = 'Y' AND GL27ALLOWRSV = 'Y'";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03DOCNO = '" + CI03DOCNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL08STAT=GL14STAT AND GL08ACTION4 = 'Y' AND GL27ALLOWRSV = 'Y'";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03MATNO = '" + CI03MATNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL08STAT=GL14STAT AND GL08ACTION4 = 'Y' AND GL27ALLOWRSV = 'Y'";
            }
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    deletable = true;
                }
                System.out.println("T" + deletable);
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
        return deletable;
    }

    public static boolean chkItemStat(String CI03PATR, String CI03DOCNO, String CI03MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM GLPATR, GLELIG, CTDOCM WHERE ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03DOCNO = '" + CI03DOCNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL27ALLOWRSV = 'Y'";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03DOCNO = '" + CI03DOCNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL27ALLOWRSV = 'Y'";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "GL14PATR='" + CI03PATR + "' AND CT03MATNO = '" + CI03MATNO + "' " + "AND GL27BRNC = GL14BRNC " + "AND GL14CATE = GL27CATE " + "AND CT03ICAT = GL27ICAT " + "AND CT03SMD = GL27SMD " + "AND GL27ALLOWRSV = 'Y'";
            }
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
                    deletable = true;
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
        return deletable;
    }

    public String chkPatrCate(String CI03PATR, String CI03DOCNO, String CI03MATNO) {
        String query = "Select GL07MAXRESV, GL07ALLOWRSV from GLCATE, GLPATR WHERE GL14CATE = GL07CATE and GL14PATR = '" + CI03PATR + "'";
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    this.msMaxResv = rsObj.getString("GL07MAXRESV");
                    this.msAllowResv = rsObj.getString("GL07ALLOWRSV");
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
        return this.msMaxResv;
    }

    public static boolean updatePriority(String CI03PATR, String CI03MATNO, int CI03PRIOR) {
        HashMap<String, String> condition = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap valueStr = new HashMap();
        condition.put("CI03PATR", CI03PATR);
        condition.put("CI03MATNO", CI03MATNO);
        valueInt.put("CI03PRIOR", CI03PRIOR);
        String query = QueryBuilder.createUpdateQuery("CIRESV", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updateCTDOCM(String CI03MATNO, String CI03DOCNO) {
        HashMap<String, String> condition = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> valueStr = new HashMap<String, String>();
        if (!CI03DOCNO.equals("0")) {
            System.out.println("e");
            condition.put("CT03DOCNO", CI03DOCNO);
        } else if (CI03MATNO != null && CI03MATNO != "") {
            System.out.println("e1");
            if (!CI03DOCNO.equals("0")) {
                condition.put("CT03DOCNO", CI03DOCNO);
                condition.put("CT03MATNO", CI03MATNO);
            } else {
                condition.put("CT03MATNO", CI03MATNO);
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    System.out.println("Test1");
                    String sql = "Select CT03DOCNO From CTDOCM Where CT03MATNO = '" + CI03MATNO + "' AND CT03STAT='H'";
                    System.out.println("Test3");
                    ResultSet rsObj = stmt.executeQuery(sql);
                    System.out.println(sql);
                    while (rsObj.next()) {
                        System.out.println("Testtt");
                        condition.put("CT03DOCNO", rsObj.getString("CT03DOCNO"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        valueStr.put("CT03STAT", "A");
        String query = QueryBuilder.createUpdateQuery("CTDOCM", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static String getMaxPrior(String CI03DOCNO, String CI03MATNO) {
        String query = "Select Max(CI03PRIOR) as CI03PRIOR FROM CIRESV WHERE ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0") && !CI03MATNO.equals("0")) {
            query = String.valueOf(query) + "CI03MATNO = (SELECT DISTINCT CI03MATNO FROM CIRESV WHERE CI03DOCNO='" + CI03DOCNO + "' and CI03MATNO = '" + CI03MATNO + "')";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO = (SELECT DISTINCT CI03MATNO FROM CIRESV WHERE CI03DOCNO='" + CI03DOCNO + "')";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO = '" + CI03MATNO + "'";
            }
        }
        System.out.println(query);
        String msCI03PRIOR = "";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            while (rsObj.next()) {
                msCI03PRIOR = rsObj.getString("CI03PRIOR");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return msCI03PRIOR;
    }

    public static Reservation InsertResv(String CI03PATR, String CI03DOCNO, String CI03MATNO, String username) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentdate = dateFormat.format(date).toString();
        SimpleDateFormat timmeFormat = new SimpleDateFormat("HHmmss");
        Date time = new Date();
        String currenttime = timmeFormat.format(time).toString();
        ArrayList list = new ArrayList();
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        String query = "Select GL14NAME, CT03ICAT, GL14BRNC, CT03MATNO FROM GLPATR, GLELIG, CTDOCM where GL14PATR = '" + CI03PATR + "' ";
        System.out.println(String.valueOf(CI03MATNO) + CI03DOCNO);
        if (CI03MATNO != null && !CI03DOCNO.equals("0") && !CI03MATNO.equals("0")) {
            query = String.valueOf(query) + "AND CT03DOCNO='" + CI03DOCNO + "' AND CT03MATNO='" + CI03MATNO + "' AND GL14CATE = GL27CATE " + "AND GL14BRNC=GL27BRNC AND CT03SMD = GL27SMD AND CT03ICAT = GL27ICAT";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "AND CT03DOCNO='" + CI03DOCNO + "' AND GL14CATE = GL27CATE " + "AND GL14BRNC=GL27BRNC AND CT03SMD = GL27SMD AND CT03ICAT = GL27ICAT";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "AND CT03MATNO='" + CI03MATNO + "' AND GL14CATE = GL27CATE " + "AND GL14BRNC=GL27BRNC AND CT03SMD = GL27SMD AND CT03ICAT = GL27ICAT";
            }
        }
        int CI03PRIOR = 0;
        String PRIOR = Reservation.getMaxPrior(CI03DOCNO, CI03MATNO);
        CI03PRIOR = PRIOR != null ? Integer.parseInt(Reservation.getMaxPrior(CI03DOCNO, CI03MATNO)) + 1 : 1;
        System.out.println(query);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(query);
            while (rsObj.next()) {
                System.out.println("Circualtion");
                System.out.println(String.valueOf(CI03PATR) + currentdate + currenttime + CI03PRIOR);
                valueStr.put("CI03PATR", CI03PATR);
                valueStr.put("CI03NSTAT", "X");
                valueStr.put("CI03RDATE", currentdate);
                valueStr.put("CI03RTIME", currenttime);
                valueInt.put("CI03PRIOR", CI03PRIOR);
                valueStr.put("CI03MATNO", rsObj.getString("CT03MATNO"));
                valueStr.put("CI03OFFIC", "Sysadmin");
                if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                    valueStr.put("CI03DOCNO", "");
                } else {
                    valueStr.put("CI03DOCNO", CI03DOCNO);
                }
                valueStr.put("CI03BRNC", rsObj.getString("GL14BRNC"));
                valueStr.put("CI03ICAT", rsObj.getString("CT03ICAT"));
                String add = QueryBuilder.createInsertQuery("CIRESV", valueStr, valueInt, null);
                boolean isSuccess = DBQuery.runQuery(add);
                System.out.println(isSuccess);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkIfPatronExist(String CI03PATR, String CI03DOCNO, String CI03MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM CIRESV WHERE ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "CI03PATR='" + CI03PATR + "' and CI03DOCNO='" + CI03DOCNO + "' ";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "CI03PATR='" + CI03PATR + "' and CI03DOCNO='" + CI03DOCNO + "' ";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "CI03PATR='" + CI03PATR + "' and CI03MATNO='" + CI03MATNO + "' ";
            }
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                    deletable = true;
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
        return deletable;
    }

    public static boolean checkMemDate(String GL14PATR) throws ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(date).toString();
        String query = "SELECT GL14EXPDATE FROM GLPATR WHERE GL14PATR='" + GL14PATR + "' ";
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    String Edate = DateTime.formatDateV2(rsObj.getString("GL14EXPDATE"));
                    Date expdate = new SimpleDateFormat("dd/MM/yyyy").parse(Edate);
                    if (!expdate.after(date)) continue;
                    deletable = true;
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
        return deletable;
    }

    public static boolean ciresvExist(String CI03DOCNO, String CI03MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM CIRESV WHERE ";
        if (CI03MATNO != null && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "CI03MATNO='" + CI03MATNO + "' and CI03DOCNO='" + CI03DOCNO + "' ";
        } else {
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "CI03DOCNO='" + CI03DOCNO + "' ";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO='" + CI03MATNO + "' ";
            }
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) != 0) continue;
                    deletable = true;
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
        return deletable;
    }

    public static boolean checkAwaiting(String CI03DOCNO, String CI03MATNO) {
        String query = "SELECT COUNT(*) AS Count FROM CIRESV WHERE ";
        if (!CI03MATNO.equals("0") && !CI03DOCNO.equals("0")) {
            query = String.valueOf(query) + "CI03MATNO='" + CI03MATNO + "' and CI03DOCNO='" + CI03DOCNO + "' ";
        } else {
            if (CI03MATNO.equals("0")) {
                query = String.valueOf(query) + "CI03DOCNO='" + CI03DOCNO + "' ";
            }
            if (CI03DOCNO.equals("0")) {
                query = String.valueOf(query) + "CI03MATNO='" + CI03MATNO + "' ";
            }
        }
        boolean deletable = false;
        System.out.println(query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(query);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) > 1) {
                        deletable = true;
                        continue;
                    }
                    if (Integer.parseInt(rsObj.getString("Count")) != 1) continue;
                    deletable = false;
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
        return deletable;
    }

    public static String getItemStatus(String CI03DOCNO) {
        String sql = "Select CT03DOCNO, GL36DESC from CTDOCM, GLDOCS where CT03STAT=GL36STAT AND CT03DOCNO='" + CI03DOCNO + "'";
        String status = "";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                status = rsObj.getString("GL36DESC");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("sd" + status);
        return status;
    }

    public static boolean updateStatus(String CI03DOCNO, String CI03MATNO) {
        HashMap<String, String> condition = new HashMap<String, String>();
        HashMap valueInt = new HashMap();
        HashMap<String, String> valueStr = new HashMap<String, String>();
        if (!CI03DOCNO.equals("0")) {
            System.out.println("e");
            condition.put("CI03DOCNO", CI03DOCNO);
        } else if (CI03MATNO != null && CI03MATNO != "") {
            System.out.println("e1");
            if (!CI03DOCNO.equals("0")) {
                condition.put("CI03DOCNO", CI03DOCNO);
                condition.put("CI03MATNO", CI03MATNO);
            } else {
                condition.put("CI03MATNO", CI03MATNO);
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    System.out.println("Test1");
                    String sql = "Select CT03DOCNO From CTDOCM Where CT03MATNO = '" + CI03MATNO + "' AND CT03STAT='H'";
                    System.out.println("Test3");
                    ResultSet rsObj = stmt.executeQuery(sql);
                    System.out.println(sql);
                    while (rsObj.next()) {
                        System.out.println("Testtt");
                        valueStr.put("CI03DOCNO", rsObj.getString("CT03DOCNO"));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        condition.put("CI03PRIOR", "1");
        valueStr.put("CI03NSTAT", "A");
        String query = QueryBuilder.createUpdateQuery("CIRESV", valueStr, null, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }
}
