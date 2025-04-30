/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global;

import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patron {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
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
    private String msPatronDeptsDesc = "";
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
    private String GL02NAME;
    private String GL14DOB;
    private String GL11NAME;
    private String GL14SEX;
    private int TOTALCHARGED;
    private String GL66DESC;
    private String GL65VALUE;
    private String REQSTATNO;
    private String REQSTATDATE;
    private String REQSTATTITLE;
    private String REQSTAT;
    private String onloan = null;
    private int overdue = 0;
    private int reserve = 0;
    private int onhold = 0;
    private double outstanding = 0.0;
    private double charged = 0.0;
    private String deptdesc = null;
    String errmessage = "";
    boolean isvalidPatron = false;

    public Patron(String GL02NAME, String GL08DESC, String GL14DOB, String GL11NAME, String GL07DESC, String GL14SEX) {
        this.GL02NAME = GL02NAME;
        this.GL08DESC = GL08DESC;
        this.GL14DOB = GL14DOB;
        this.GL11NAME = GL11NAME;
        this.GL07DESC = GL07DESC;
        this.GL14SEX = GL14SEX;
    }

    public Patron(String GL66DESC, String GL65VALUE) {
        this.GL66DESC = GL66DESC;
        this.GL65VALUE = GL65VALUE;
    }

    public Patron(String REQSTATDATE, String REQSTATNO, String REQSTATTILE, String REQSTAT) {
        this.REQSTATDATE = REQSTATDATE;
        this.REQSTATNO = REQSTATNO;
        this.REQSTATTITLE = REQSTATTILE;
        this.REQSTAT = REQSTAT;
    }

    public Patron(String CI02PATR, String GL14NAME, String GL07DESC, String GL14MEMDATE, String GL14EXPDATE, String GL71DESC, String GL08DESC, String deptdesc, String onloan, int overdue, int reserve, int onhold, double outstanding, double charged) {
        this.CI02PATR = CI02PATR;
        this.GL14NAME = GL14NAME;
        this.GL07DESC = GL07DESC;
        this.GL14MEMDATE = GL14MEMDATE;
        this.GL14EXPDATE = GL14EXPDATE;
        this.GL71DESC = GL71DESC;
        this.GL08DESC = GL08DESC;
        this.deptdesc = deptdesc;
        this.onloan = onloan;
        this.overdue = overdue;
        this.reserve = reserve;
        this.onhold = onhold;
        this.outstanding = outstanding;
        this.charged = charged;
    }

    public String getCI02PATR() {
        return this.CI02PATR;
    }

    public String getGL14NAME() {
        return this.GL14NAME;
    }

    public String getGL66DESC() {
        return this.GL66DESC;
    }

    public String getGL65VALUE() {
        return this.GL65VALUE;
    }

    public String getREQSTAT() {
        return this.REQSTAT;
    }

    public String getREQSTATNO() {
        return this.REQSTATNO;
    }

    public String getREQSTATDATE() {
        return this.REQSTATDATE;
    }

    public String getREQSTATTITLE() {
        return this.REQSTATTITLE;
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

    public String getDeptDesc() {
        return this.deptdesc;
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

    public String getGL02NAME() {
        return this.GL02NAME;
    }

    public String getGL14DOB() {
        return this.GL14DOB;
    }

    public String getGL11NAME() {
        return this.GL11NAME;
    }

    public String getGL14SEX() {
        return this.GL14SEX;
    }

    public int getTOTALCHARGED() {
        return this.TOTALCHARGED;
    }

    public Patron() {
        this.setMsPatronId(null);
        this.setMsPatronName(null);
        this.setMsPatronTitle(null);
        this.setMsPatronDeptDesc(null);
        this.setMsPatronDeptsDesc(null);
        this.setMsMemExpiryDate(null);
        this.setMsPatronCateDesc(null);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
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

    public void setMsPatronId(String msPatronId) {
        this.msPatronId = msPatronId;
    }

    public String getGL14IMG() {
        return this.GL14IMG;
    }

    public void setGL14IMG(String GL14IMG) {
        this.GL14IMG = GL14IMG;
    }

    public void setMsPatronName(String msPatronName) {
        this.msPatronName = msPatronName;
    }

    public void setMsPatronDeptDesc(String msPatronDeptDesc) {
        this.msPatronDeptDesc = msPatronDeptDesc;
    }

    public void setMsPatronDeptsDesc(String msPatronDeptsDesc) {
        this.msPatronDeptsDesc = msPatronDeptsDesc;
    }

    public String getMsPatronName() {
        return this.msPatronName;
    }

    public String executeChkPatr(String msPatronID) {
        String status = null;
        return status;
    }

    public static boolean patrStatus(String GL14PATR) {
        boolean deletable;
        block12: {
            String query = "SELECT COUNT(*) AS status FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "' and GL14STAT!='01'";
            deletable = true;
            System.out.println(query);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("status")) < 1) continue;
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

    public static boolean validPatr(String GL14PATR) {
        boolean deletable;
        block12: {
            String query = "SELECT COUNT(*) AS PatrId FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "'";
            deletable = false;
            System.out.println(query);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        if (Integer.parseInt(rs.getString("PatrId")) < 1) continue;
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

    public static boolean patrMem(String GL14PATR) {
        boolean deletable;
        block12: {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate localDate = LocalDate.now();
            String today = dtf.format(localDate);
            String query = "SELECT COUNT(*) AS Membership FROM GLPATR WHERE GL14PATR = '" + GL14PATR + "' AND GL14EXPDATE< " + today;
            deletable = false;
            System.out.println(query);
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        System.out.println(Integer.parseInt(rs.getString("Membership")));
                        if (Integer.parseInt(rs.getString("Membership")) != 0) continue;
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

    public static List<Patron> PatronDetails(String CT03DOCNO) {
        ArrayList<Patron> list = new ArrayList<Patron>();
        String patrid = Patron.getPatrId(CT03DOCNO);
        String onloan = Patron.getMsNoCircByPatrons(patrid);
        int overdue = Patron.getNoMsGetItemOverdues(patrid);
        int reserve = Patron.getNoMsGetItemReserve(patrid);
        int onhold = Patron.getNoMsGetItemOnHolds(patrid);
        double outstanding = Patron.getMsGetTotalFineOutstanding(patrid);
        double charged = Patron.getMsGetTotalCharge(patrid);
        String sql = "Select DISTINCT CI02PATR, GL14NAME, GL07DESC, GL14MEMDATE, GL14EXPDATE, GL71DESC, GL08DESC, GL11NAME FROM CICIRC LEFT JOIN GLPATR ON  GL14PATR=CI02PATR LEFT JOIN GLCATE ON GL07CATE=GL14CATE LEFT JOIN  GLBRNC ON GL14BRNC = GL71BRNC LEFT JOIN GLSTAT ON GL08STAT=GL14STAT LEFT JOIN GLDEPT ON GL14DEPT = GL11DEPT WHERE CI02PATR = '" + patrid + "' and CI02DOCNO ='" + CT03DOCNO + "' and CI02FLAG = 'C'";
        System.out.println(sql);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                Patron patronDetail = new Patron(rsObj.getString("CI02PATR"), rsObj.getString("GL14NAME"), rsObj.getString("GL07DESC"), rsObj.getString("GL14MEMDATE"), rsObj.getString("GL14EXPDATE"), rsObj.getString("GL71DESC"), rsObj.getString("GL08DESC"), rsObj.getString("GL11NAME"), onloan, overdue, reserve, onhold, outstanding, charged);
                list.add(patronDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Patron> DischargedPatronDetails(String CT03DOCNO) {
        ArrayList<Patron> list = new ArrayList<Patron>();
        String patrid = Patron.getPatrId(CT03DOCNO);
        String onloan = Patron.getMsNoCircByPatrons(patrid);
        int overdue = Patron.getNoMsGetItemOverdues(patrid);
        int reserve = Patron.getNoMsGetItemReserve(patrid);
        int onhold = Patron.getNoMsGetItemOnHolds(patrid);
        double outstanding = Patron.getMsGetTotalFineOutstanding(patrid);
        double charged = Patron.getMsGetTotalCharge(patrid);
        String sql = "Select CI02PATR, GL14NAME, GL07DESC, GL14MEMDATE, GL14EXPDATE, GL71DESC, GL08DESC, GL11NAME FROM CICIRC, GLPATR, GLCATE, GLBRNC, GLSTAT, GLDEPT  WHERE GL14PATR=CI02PATR and GL07CATE=GL14CATE and GL14BRNC = GL71BRNC and GL08STAT=GL14STAT AND GL14DEPT = GL11DEPT and CI02PATR = '" + patrid + "' and CI02DOCNO ='" + CT03DOCNO + "' and CI02FLAG = 'C'";
        System.out.println(sql);
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                Patron patronDetail = new Patron(rsObj.getString("CI02PATR"), rsObj.getString("GL14NAME"), rsObj.getString("GL07DESC"), rsObj.getString("GL14MEMDATE"), rsObj.getString("GL14EXPDATE"), rsObj.getString("GL71DESC"), rsObj.getString("GL08DESC"), rsObj.getString("GL11NAME"), onloan, overdue, reserve, onhold, outstanding, charged);
                list.add(patronDetail);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

    public String getMsPatronBranchDesc() {
        String sql = "Select GL71BRNC, GL71DESC From GLBRNC Where GL71BRNC = '" + this.msPatronBranch + "'";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msPatronBranchDesc = String.valueOf(rsObj.getString("GL71DESC"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
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
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println("SQL" + sql);
            while (rsObj.next()) {
                this.msPatronDept = String.valueOf(rsObj.getString("GL14DEPT"));
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.msPatronDept;
    }

    public String getMsPatronDeptDesc() {
        String sql = "Select GL11DEPT, GL11NAME From GLDEPT Where GL11DEPT = '" + this.msPatronDept + "'";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                this.msPatronDeptDesc = String.valueOf(rsObj.getString("GL11NAME"));
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.msPatronDeptDesc;
    }

    public String getMsPatronDeptsDesc(String msPatronID) {
        String sql = "SELECT GL11NAME FROM GLPATR, GLDEPT WHERE GL14DEPT=GL11DEPT and GL14PATR= '" + msPatronID + "' ";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                this.msPatronDeptsDesc = String.valueOf(rsObj.getString("GL11NAME"));
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.msPatronDeptsDesc;
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

    public String getMsMemDate() {
        return this.msMemDate;
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

    public static String getMsNoCircByPatrons(String msPatronID) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02FLAG = 'C' ";
        String onloan = null;
        try {
            Connection conn = null;
            Statement stmt1 = null;
            conn = DBConnection.getConnection();
            stmt1 = conn.createStatement();
            rsObj = stmt1.executeQuery(sql);
            while (rsObj.next()) {
                onloan = rsObj.getString("CIRCULATEDNO");
                if (!onloan.equals(null)) continue;
                onloan = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return onloan;
    }

    public String getMsNoCircByPatron(String msPatronID) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02FLAG = 'C' ";
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msNoCircByPatron = rsObj.getString("CIRCULATEDNO");
                if (!this.msNoCircByPatron.equals(null)) continue;
                this.msNoCircByPatron = "0";
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return this.msNoCircByPatron;
    }

    public String getMsNoCircByPatron() {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + this.msPatronId.trim() + "' " + "And CI02FLAG = 'C' ";
        System.out.println("SQL" + sql);
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

    public int GetLateReturn() {
        String sql = "Select count(*) As LateReturn From CICIRC Where CI02PATR = '" + this.msPatronId.trim() + "' " + "And CI02FLAG = 'D' And CI02DDATE <  CI02DIDATE";
        int msLateReturn = 0;
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msLateReturn = Integer.parseInt(rsObj.getString("LateReturn"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return msLateReturn;
    }

    public int GetBorrowedToYear(String msPatronId) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
        SimpleDateFormat dateFormatV1 = new SimpleDateFormat("yyyy");
        String sCurDate = dateFormat.format(date).toString();
        String sStartDate = dateFormatV1.format(date).toString();
        sStartDate = String.valueOf(sStartDate) + "0101";
        String sql = "SELECT  COUNT(CI02PATR) as msBorrowedToYear FROM CICIRC WHERE CI02PATR = '" + msPatronId + "' " + "AND CI02CDATE BETWEEN '" + sStartDate + "' AND '" + sCurDate + "'";
        int msBorrowedToYear = 0;
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msBorrowedToYear = Integer.parseInt(rsObj.getString("msBorrowedToYear"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return msBorrowedToYear;
    }

    public int GetLateReturnToYear(String msPatronId) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
        SimpleDateFormat dateFormatV1 = new SimpleDateFormat("yyyy");
        String sCurDate = dateFormat.format(date).toString();
        String sStartDate = dateFormatV1.format(date).toString();
        sStartDate = String.valueOf(sStartDate) + "0101";
        String sql = "SELECT  COUNT(CI02PATR) as msLateReturnToYear FROM CICIRC WHERE CI02PATR = '" + msPatronId + "' " + "AND CI02DIDATE > CI02DDATE " + "AND (CI02DIDATE BETWEEN '" + sStartDate + "' AND '" + sCurDate + "')";
        int msLateReturnToYear = 0;
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                msLateReturnToYear = Integer.parseInt(rsObj.getString("msLateReturnToYear"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return msLateReturnToYear;
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
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
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
        outstanding = Patron.getMsGetTotalCharge(patrid) - Patron.getMsGetTotalPaids(patrid);
        return Double.valueOf(df2.format(outstanding));
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

    public static List<Patron> PatrDetails(String msPatronID) throws SQLException {
        ArrayList<Patron> PatronDetails = new ArrayList<Patron>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "Select GL02NAME, GL08DESC, GL14DOB, GL11NAME, GL07DESC, GL14SEX from GLPATR, GLSTAT, GLCATE, GLDEPT, GLGRMA where GL14STAT = GL08STAT AND GL07CATE = GL14CATE AND GL11DEPT = GL14DEPT AND GL02GRP = GL14GRID AND GL14PATR='" + msPatronID + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                Patron circ = new Patron(rsObj.getString("GL02NAME"), rsObj.getString("GL08DESC"), rsObj.getString("GL14DOB"), rsObj.getString("GL11NAME"), rsObj.getString("GL07DESC"), rsObj.getString("GL14SEX"));
                PatronDetails.add(circ);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return PatronDetails;
    }

    public static List<Patron> patrMisc(String msPatronID) throws SQLException {
        ArrayList<Patron> patrMisc = new ArrayList<Patron>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "Select GL65VALUE, GL66DESC from GLPATREX, GLATTRI where GL66ATTRI = GL65ATTRI and GL65PATR='" + msPatronID + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                Patron misc = new Patron(rsObj.getString("GL65VALUE"), rsObj.getString("GL66DESC"));
                patrMisc.add(misc);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patrMisc;
    }

    public static List<Patron> retrievePatron(String criteria, String searchType, String cate_id, String sort) throws SQLException {
        ArrayList<Patron> patrMisc = new ArrayList<Patron>();
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "Select GL14PATR, GL14NAME from GLPATR ";
                if (searchType.equals("patronId")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "where GL14PATR LIKE '" + criteria + "' " : String.valueOf(sql) + "where GL14PATR= '" + criteria + "' ";
                } else if (searchType.equals("patronName")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "where GL14NAME LIKE '" + criteria + "' " : String.valueOf(sql) + "where GL14NAME= '" + criteria + "' ";
                } else if (searchType.equals("patrnic")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "where GL14NEWIC LIKE '" + criteria + "' " : String.valueOf(sql) + "where GL14NEWIC= '" + criteria + "' ";
                } else if (searchType.equals("patonic")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "where GL14OLDIC LIKE '" + criteria + "' " : String.valueOf(sql) + "where GL14OLDIC= '" + criteria + "' ";
                }
                if (sort.equals("Id")) {
                    sql = String.valueOf(sql) + "order by GL14PATR";
                } else if (sort.equals("Name")) {
                    sql = String.valueOf(sql) + "order by GL14NAME";
                }
                System.out.println(cate_id);
                if (!cate_id.equals("0")) {
                    sql = String.valueOf(sql) + " and GL14CATE='" + cate_id + "'";
                }
                System.out.println(sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Patron misc = new Patron(rsObj.getString("GL14PATR"), rsObj.getString("GL14NAME"));
                    patrMisc.add(misc);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                conn.close();
            }
        }
        finally {
            conn.close();
        }
        return patrMisc;
    }

    public static List<Patron> retrieveDischarging(String criteria, String searchType, String cate_id) throws SQLException {
        ArrayList<Patron> patrMisc = new ArrayList<Patron>();
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "Select CI02DOCNO, CT05SRAW from CICIRC, GLPATR, CTDOCM, CTPONT, CTTITL WHERE CI02PATR=GL14PATR and CI02FLAG = 'C'  and CT03DOCNO = CI02DOCNO  and CT03MATNO = CT06MATNO  and CT06POINTER = CT05POINTER  and CT06TAG = '245' ";
                if (searchType.equals("patronId")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "and GL14PATR LIKE '" + criteria + "'" : String.valueOf(sql) + "and GL14PATR= '" + criteria + "'";
                } else if (searchType.equals("patronName")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "and GL14NAME LIKE '" + criteria + "'" : String.valueOf(sql) + "and GL14NAME= '" + criteria + "'";
                } else if (searchType.equals("patrnic")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "and GL14NEWIC LIKE '" + criteria + "'" : String.valueOf(sql) + "and GL14NEWIC= '" + criteria + "'";
                } else if (searchType.equals("patonic")) {
                    sql = criteria.contains("%") ? String.valueOf(sql) + "and GL14OLDIC LIKE '" + criteria + "'" : String.valueOf(sql) + "and GL14OLDIC= '" + criteria + "'";
                }
                System.out.println(cate_id);
                if (!cate_id.equals("0")) {
                    sql = String.valueOf(sql) + " and GL14CATE='" + cate_id + "'";
                }
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Patron misc = new Patron(rsObj.getString("CI02DOCNO"), Handler.removeSubfield(rsObj.getString("CT05SRAW")));
                    patrMisc.add(misc);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                conn.close();
            }
        }
        finally {
            conn.close();
        }
        return patrMisc;
    }

    public static List<Patron> reqStat(String msPatronID, String flag) throws SQLException {
        ArrayList<Patron> patrReqStat = new ArrayList<Patron>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = null;
            if (flag.equals("accReq")) {
                sql = "Select AC01DATEREQ, AC01REQUEST, AC01TITLE, GL43ACQDESC from ACREQC, GLACST WHERE GL43ACQSTAT=AC01STATUS AND AC01REQUESTOR='" + msPatronID + "'";
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Patron reqStat = new Patron(rsObj.getString("AC01DATEREQ"), rsObj.getString("AC01REQUEST"), rsObj.getString("AC01TITLE"), rsObj.getString("GL43ACQDESC"));
                    patrReqStat.add(reqStat);
                }
            } else if (flag.equals("seReq")) {
                sql = "Select SE02DTREQUEST, SE02REQUEST, SE02TITLE, SE04DESC from SEREQC, SESTAT WHERE SE04STAT=SE02STATUS AND SE02REQUESTOR='" + msPatronID + "'";
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Patron reqStat = new Patron(rsObj.getString("SE02DTREQUEST"), rsObj.getString("SE02REQUEST"), rsObj.getString("SE02TITLE"), rsObj.getString("SE04DESC"));
                    patrReqStat.add(reqStat);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return patrReqStat;
    }
}
