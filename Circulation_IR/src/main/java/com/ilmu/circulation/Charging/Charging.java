/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.circulation.resv.Reservation;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Charging {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private Patron patr;
    String msPatronStatus;
    String msPatronCategory;
    String msPatronBranch;
    String msPatronExpDate;
    String msBorrowerName;
    String msBorrowerID;
    String itemStatCode;
    private String msRetAccessionNo;
    private String msRetStatus = "";
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msNoCircByPatronByItem;
    private String msAllowOvd;
    private String msRetItemBranch;
    private String msRetDocNo;
    private int msLoanPeriod;
    private String CallNo;
    private String Title;
    private String CI02DDATE;
    private String CI02DIDATE;
    private String CI02DTIME;
    private String CI02DOCNO;
    private String CI02DITIME;
    private String status;
    private Patron patrs;
    private String CT03DDATE;
    private String CT03DTIME;
    private String CT03STAT;
    private String CT03PATR;
    private String GL14BRNC;
    private String CT03LOCA;
    private String CT03ICAT;
    private String CT03SMD;
    String msStat = "";
    String msRawDateDue;
    String msRawTimeDue;
    String msRawDateCharged;
    String msRawTimeCharged;
    int msLateReturn;
    int msBorrowedToYear;
    int msLateReturnToYear;
    String msRenewDate;
    String msLostBok;
    String msSuspension;
    String msLastBor;
    String msLastRet;
    int msCharged;
    String printMessage;
    Double msFineLimit;
    String errmessage = "";

    public Charging(String CI02DOCNO, String CallNo, String Title, String CI02DIDATE, String CI02DDATE, String CI02DTIME, String CI02DITIME, String FLAGDESC) {
        this.CI02DOCNO = CI02DOCNO;
        this.CallNo = CallNo;
        this.Title = Title;
        this.CI02DIDATE = CI02DIDATE;
        this.CI02DDATE = CI02DDATE;
        this.CI02DTIME = CI02DTIME;
        this.CI02DITIME = CI02DITIME;
        this.status = FLAGDESC;
    }

    public Charging(String CI02DOCNO, String CT03DDATE, String CT03DTIME, String CT03STAT, String CT03PATR, String GL14BRNC, String CT03LOCA, String CT03ICAT, String CT03SMD) {
        this.CI02DOCNO = CI02DOCNO;
        this.CT03DDATE = CT03DDATE;
        this.CT03DTIME = CT03DTIME;
        this.CT03STAT = CT03STAT;
        this.CT03PATR = CT03PATR;
        this.GL14BRNC = GL14BRNC;
        this.CT03LOCA = CT03LOCA;
        this.CT03ICAT = CT03ICAT;
        this.CT03SMD = CT03SMD;
    }

    public String CT03DDATE() {
        return this.CT03DDATE;
    }

    public String CT03DTIME() {
        return this.CT03DTIME;
    }

    public String CT03STAT() {
        return this.CT03STAT;
    }

    public String CT03PATR() {
        return this.CT03PATR;
    }

    public String GL14BRNC() {
        return this.GL14BRNC;
    }

    public String CT03LOCA() {
        return this.CT03LOCA;
    }

    public String CT03ICAT() {
        return this.CT03ICAT;
    }

    public String CT03SMD() {
        return this.CT03SMD;
    }

    public String msStat() {
        return this.msStat;
    }

    public String getCT02DOCNO() {
        return this.CI02DOCNO;
    }

    public String getCallNo() {
        return this.CallNo;
    }

    public String getTitle() {
        return this.Title;
    }

    public String getCI02DIDATE() {
        return this.CI02DIDATE;
    }

    public String getCI02DDATE() {
        return this.CI02DDATE;
    }

    public String getCI02DTIME() {
        return this.CI02DTIME;
    }

    public String getMsStat() {
        return this.msStat;
    }

    public int getmsLateReturn() {
        return this.msLateReturn;
    }

    public int getmsBorrowedToYear() {
        return this.msBorrowedToYear;
    }

    public int getmsLateReturnToYear() {
        return this.msLateReturnToYear;
    }

    public String getmsRenewDate() {
        return this.msRenewDate;
    }

    public String getmsLostBok() {
        return this.msLostBok;
    }

    public String getmsSuspension() {
        return this.msSuspension;
    }

    public String getmsLastBor() {
        return this.msLastBor;
    }

    public String getmsLastRet() {
        return this.msLastRet;
    }

    public int getmsCharged() {
        return this.msCharged;
    }

    public String getCI02DITIME() {
        return this.CI02DITIME;
    }

    public String getstatus() {
        return this.status;
    }

    public void setmsCharged(int msCharged) {
        this.msCharged = msCharged;
    }

    public Charging() {
        try {
            System.out.println("from charging");
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean executeCharging(String msAccessionNo, String msPatronID, String chkMaxLoan) {
        String sql = "";
        int GL27ELIG = 0;
        int patronEligibility = 0;
        boolean patrCanCharge = false;
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
        System.out.println("Action" + chkMaxLoan);
        this.patr = new Patron(msPatronID);
        this.msPatronStatus = this.patr.getMsPatronStatus();
        System.out.println("Status" + this.msPatronStatus);
        String msPatronStatusDesc = this.patr.getMsPatronStatusDesc();
        System.out.println("Status1" + msPatronStatusDesc);
        this.msPatronCategory = this.patr.getMsPatronCategory();
        System.out.println("Status2" + this.msPatronCategory);
        System.out.println("Status3" + this.msNoCircByPatron);
        this.msPatronExpDate = this.patr.getMsMemExpiryDate();
        System.out.println("Status4" + this.msPatronExpDate);
        this.msPatronBranch = this.patr.getMsPatronBranch();
        patronEligibility = this.checkPatronEligibility();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(date).toString();
        Object date1 = null;
        Object date2 = null;
        try {
            System.out.println("Eligibility By Item" + GeneralUtility.StrToDate(this.msRawDateDue));
            if (this.RetrieveAccessionDetail(msAccessionNo)) {
                if (this.GetCircStatus(msAccessionNo).equals("A") || this.GetCircStatus(msAccessionNo).equals("H")) {
                    patrCanChargeAccession = this.CanPatronBorrowbyAcession(msAccessionNo, this.msPatronCategory);
                    if (patrCanChargeAccession) {
                        if (this.msRetStatus.equals("H")) {
                            if (this.checkReservationStatus(msPatronID, msAccessionNo)) {
                                bSucessful = true;
                            } else {
                                this.printMessage = "This item is reserved by another patron";
                                System.out.println("This item is reserved by another patron");
                                this.errmessage = "023, " + this.getReservedPatron(msAccessionNo);
                                bSucessful = false;
                            }
                        } else {
                            bSucessful = true;
                        }
                    }
                } else {
                    String status = this.getItemStatus(msAccessionNo);
                    String duedate = this.getCirculatedPatrDetails(msAccessionNo);
                    if (this.itemStatCode.equals("C") && this.msBorrowerID.equalsIgnoreCase(msPatronID)) {
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date due = sourceFormat.parse(GeneralUtility.StrToDate(duedate));
                        Date today = new Date();
                        System.out.println(due);
                        System.out.println(date);
                        this.errmessage = due.compareTo(date) < 0 ? "086," + this.msBorrowerName : "087," + this.msBorrowerName;
                    } else {
                        this.errmessage = "066," + status;
                        bSucessful = false;
                    }
                }
            } else {
                this.printMessage = "This item is not available for ciculation (Item status) :" + this.msRetStatus;
                System.out.println("Accession Invalid" + this.msRetStatus);
                this.errmessage = "020";
                bSucessful = false;
            }
            if (bSucessful) {
                System.out.println("No of items circulated by item" + this.msNoCircByPatronByItem);
                System.out.println("Patron Eligibility" + patronEligibility);
                System.out.println("Patron Branch" + this.msPatronBranch);
                GL27ELIG = this.checkItemEligibility(this.msPatronCategory, this.msPatronBranch);
                System.out.println("Eligibility By Item" + this.patr.getNoMsGetItemOverdue() + this.msAllowOvd);
                if (GL27ELIG > 0) {
                    if (this.getCircSattelite()) {
                        System.out.println("T" + this.getCircSattelite());
                        if (this.msRetLocation.equals(this.getPatrBrnc(msPatronID))) {
                            bSucessful = true;
                        } else {
                            System.out.println("T1" + this.msRetLocation + this.getPatrBrnc(msPatronID));
                            bSucessful = false;
                            this.errmessage = "085";
                        }
                    } else {
                        bSucessful = true;
                    }
                    System.out.println(bSucessful);
                    Patron patr = new Patron(msPatronID);
                    if (bSucessful) {
                        if (this.msAllowOvd.equals("Y") || patr.getNoMsGetItemOverdue() == 0) {
                            if (chkMaxLoan.equals("chkPatrCateMaxLoan")) {
                                bSucessful = this.checkMaxLoan("chkPatrCateMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility);
                                System.out.println("Action1" + chkMaxLoan + bSucessful);
                            } else if (chkMaxLoan.equals("chkItemCategoryMaxLoan")) {
                                bSucessful = this.checkMaxLoan("chkItemCategoryMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility);
                                System.out.println("Action" + chkMaxLoan + bSucessful);
                            } else {
                                bSucessful = this.checkMaxLoan("chkEligMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility);
                            }
                        } else {
                            bSucessful = false;
                            this.printMessage = "This patron is not eligible to borrow any item in this item category";
                            System.out.println("This patron is not eligible to borrow any item in this item category");
                            this.errmessage = "046";
                        }
                    }
                } else {
                    bSucessful = false;
                    this.printMessage = "This patron is not eligible to borrow any item in this item category";
                    System.out.println("This patron is not eligible to borrow any item in this item category");
                    this.errmessage = "039";
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean executeOverride(Calendar dueDate, String patron) {
        System.out.println(dueDate);
        Patron patr = new Patron(patron);
        this.msPatronBranch = patr.getMsPatronBranch();
        this.msPatronExpDate = patr.getMsMemExpiryDate();
        boolean bSucessful = false;
        try {
            System.out.println(this.isWorkingDayorHoliday(dueDate, this.msPatronBranch));
            if (this.isWorkingDayorHoliday(dueDate, this.msPatronBranch) == 0) {
                Date due;
                SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date memExp = sourceFormat.parse(this.msPatronExpDate);
                if (memExp.compareTo(due = sourceFormat.parse(sourceFormat.format(dueDate.getTime()))) > 0) {
                    bSucessful = true;
                } else {
                    bSucessful = false;
                    this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                    System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
                    this.errmessage = "068";
                }
            } else {
                System.out.println("ssser");
                bSucessful = false;
                this.errmessage = "092," + this.getHolidayDesc(dueDate);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean getPatrOtherInfo(String msPatronID) throws SQLException {
        boolean bSucessful = false;
        Patron patr = new Patron(msPatronID);
        this.msCharged = this.getItemCharged(msPatronID);
        System.out.println(String.valueOf(this.msCharged) + " msCharged");
        this.msLateReturn = patr.GetLateReturn();
        System.out.println(String.valueOf(this.msLateReturn) + " msLateReturn");
        this.msBorrowedToYear = patr.GetBorrowedToYear(msPatronID);
        System.out.println("BTD" + this.msBorrowedToYear);
        this.msLateReturnToYear = patr.GetLateReturnToYear(msPatronID);
        System.out.println(String.valueOf(this.msLateReturnToYear) + " msLateReturnToYear");
        this.msRenewDate = patr.GetLastRenewDate(msPatronID);
        System.out.println(String.valueOf(this.msRenewDate) + " msRenewDate");
        List<Patron> patrList = null;
        patrList = Patron.getPatronDetails(msPatronID);
        for (Patron i : patrList) {
            this.msLostBok = i.getREQSTATDATE();
            System.out.println("Lost" + this.msLostBok);
            this.msSuspension = i.getREQSTATNO();
            this.msLastBor = i.getREQSTATTITLE();
            this.msLastRet = i.getREQSTAT();
        }
        return bSucessful;
    }

    public boolean charge(String msAccessionNo, String msPatronID, String CI02DDATE) throws SQLException {
        boolean bSucessful = false;
        this.patrs = new Patron(msPatronID);
        this.chargingdate();
        if (this.checkReservationStatus(msPatronID, msAccessionNo)) {
            Reservation.deleteReservation(msPatronID, msAccessionNo);
        }
        if (this.UpdateAccessionDetail(this.msRawDateCharged, this.msRawTimeCharged, GeneralUtility.DatetoStr(CI02DDATE), this.msRawTimeDue, msAccessionNo, msPatronID)) {
            if (this.AddChargeTransaction(msPatronID, this.msRetMatNo, msAccessionNo, GeneralUtility.DatetoStr(CI02DDATE), this.msRawTimeDue, this.msRawDateCharged, this.msRawTimeCharged, "C", "SYSADMIN")) {
                if (this.UpdatePatron(msPatronID, this.msRawDateCharged)) {
                    this.printMessage = "The item has been charged successfully";
                    this.errmessage = "OK";
                    bSucessful = true;
                } else {
                    bSucessful = false;
                    System.out.println("The Update patron operation is not successfull");
                    this.errmessage = "043";
                }
            } else {
                bSucessful = false;
                System.out.println("The AddChargeTransaction operation is not successfull");
                this.errmessage = "042";
            }
        } else {
            bSucessful = false;
            System.out.println("The Update Accession operation is not successfull");
            this.errmessage = "041";
        }
        return bSucessful;
    }

    public boolean CanPatronCharge() {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION1 From GLSTAT Where GL08STAT = '" + this.patr.getMsPatronStatus().trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION1").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronCharge(String msPatronStatus) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION1 From GLSTAT Where GL08STAT = '" + msPatronStatus.trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION1").equals("Y")) continue;
                System.out.println("Satisfy");
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronDisCharge() {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION2 From GLSTAT Where GL08STAT = '" + this.patr.getMsPatronStatus().trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION2").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronDisCharge(String msPatronStatus) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION2 From GLSTAT Where GL08STAT = '" + msPatronStatus.trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION2").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronReserve() {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION4 From GLSTAT Where GL08STAT = '" + this.patr.getMsPatronStatus().trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION4").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronReserve(String msPatronStatus) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION4 From GLSTAT Where GL08STAT = '" + msPatronStatus.trim() + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                if (!rsObj.getString("GL08ACTION4").equals("Y")) continue;
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronBorrow(String vsControlNo) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03MATNO = '" + vsControlNo + "' " + " AND GL27CATE = '" + this.patr.getMsPatronStatus().trim() + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y') ";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronBorrow(String vsControlNo, String msPatronStatus) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03MATNO = '" + vsControlNo + "' " + " AND GL27CATE = '" + msPatronStatus + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronBorrowbyAcession(String vsAccessionNo) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03DOCNO = '" + vsAccessionNo + "' " + " AND  GL27CATE = '" + this.patr.getMsPatronCategory() + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC ";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronBorrowbyAcession(String vsAccessionNo, String msPatronCategory) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03DOCNO = '" + vsAccessionNo + "' " + " AND  GL27CATE = '" + msPatronCategory + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public int getItemCharged(String msPatronId) {
        int noChargedItem = 0;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(CI02DOCNO) AS NOCHARGED FROM CICIRC WHERE CI02PATR ='" + msPatronId + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(String.valueOf(sql) + " NOCHARGED");
            while (rsObj.next()) {
                noChargedItem = Integer.parseInt(rsObj.getString("NOCHARGED"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return noChargedItem;
    }

    public boolean CanPatronHold(String vsDocNo) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE   CT03DOCNO = '" + vsDocNo + "' " + "AND GL27CATE = '" + this.patr.getMsPatronCategory() + "' " + "AND GL27ICAT = CT03ICAT " + "AND GL27SMD = CT03SMD " + "AND GL27BRNC = GL05BRNC " + "AND GL05LOCA= CT03LOCA " + "AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public boolean CanPatronHold(String vsDocNo, String msPatronCategory) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE   CT03DOCNO = '" + vsDocNo + "' " + "AND GL27CATE = '" + msPatronCategory + "' " + "AND GL27ICAT = CT03ICAT " + "AND GL27SMD = CT03SMD " + "AND GL27BRNC = GL05BRNC " + "AND GL05LOCA= CT03LOCA " + "AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                validate = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return validate;
    }

    public long getCicircCounter(String msPatronID, String vsMatNo, String vsDocNo) {
        long cicirccounter = 0L;
        String sql = "SELECT CI02COUNTER FROM CICIRC WHERE CI02PATR = '" + msPatronID.trim() + "' " + "AND CI02MATNO = '" + vsMatNo.trim() + "' " + "AND CI02DOCNO = '" + vsDocNo.trim() + "' " + "AND CI02FLAG = 'C' ";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                cicirccounter = rsObj.getLong("CI02COUNTER");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cicirccounter;
    }

    public String getStatusDesc(String vsStatus) {
        String Statusdesc = null;
        String sql = "SELECT GL36STAT,GL36DESC FROM GLDOCS WHERE GL36STAT = '" + vsStatus + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                Statusdesc = rsObj.getString("GL36DESC");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Statusdesc;
    }

    public String getReservedPatron(String msAccession) {
        String patronName = null;
        String sql = "SELECT GL14NAME, GL14PATR FROM GLPATR, CIRESV WHERE GL14PATR=CI03PATR AND CI03DOCNO='" + msAccession + "' AND CI03NSTAT='A'";
        System.out.println(sql);
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                patronName = String.valueOf(rsObj.getString("GL14NAME")) + ", " + rsObj.getString("GL14PATR");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return patronName;
    }

    public String GetCircStatus(String vsItemID) {
        String itemStatus = null;
        String sql = "SELECT CT03DOCNO, CT03STAT FROM CTDOCM WHERE CT03DOCNO = '" + vsItemID + "'";
        try {
            ResultSet rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                itemStatus = rsObj.getString("CT03STAT");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemStatus;
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo) {
        boolean exist = false;
        try {
            String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetDocNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                this.msRetCondition = String.valueOf(rsObj.getString("CT03DDATE"));
                this.msRetItemBranch = String.valueOf(rsObj.getString("GL05BRNC"));
                System.out.println(this.msRetItemBranch);
                System.out.println(rsObj.getString("CT03LOCA"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean validAccession(String msAccessionNo) {
        boolean exist = false;
        try {
            String sql = "SELECT Count(*) FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            if (rsObj.next()) {
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean checkBuffer(String msAccessionNo) {
        boolean exist = false;
        try {
            String sql = "SELECT Count(*) FROM CTWORK WHERE CT03DOCNO = '" + msAccessionNo + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            if (rsObj.next()) {
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean itemCharged(String msAccessionNo) {
        boolean exist = false;
        try {
            String sql = "SELECT Count(*) as Count FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "' AND (CT03STAT!= 'A' AND CT03STAT!='H')";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            if (rsObj.next() && Integer.parseInt(rsObj.getString("Count")) > 0) {
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public String getItemStatus(String msAccessionNo) {
        String status = "";
        try {
            String sql = "Select GL36DESC, GL36STAT from CTDOCM, GLDOCS where GL36STAT=CT03STAT and CT03DOCNO='" + msAccessionNo + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.itemStatCode = rsObj.getString("GL36STAT");
                status = rsObj.getString("GL36DESC");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getItemStat(String msAccessionNo) {
        String status = "";
        try {
            String sql = "Select GL36DESC from CTDOCM, GLDOCS where CT03STAT!='A' and GL36STAT=CT03STAT and CT03DOCNO='" + msAccessionNo + "'";
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                status = rsObj.getString("GL36DESC");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public String getRsRetAccessionNo() {
        return this.msRetAccessionNo;
    }

    public String getRsRetStatus() {
        return this.msRetStatus;
    }

    public String getRsRetItemCat() {
        return this.msRetItemCat;
    }

    public String getRsRetMatNo() {
        return this.msRetMatNo;
    }

    public String getRsRetSMD() {
        return this.msRetSMD;
    }

    public String getRsRetLocation() {
        return this.msRetLocation;
    }

    public String getRsRetPatron() {
        return this.msRetPatron;
    }

    public String getRsRetCondition() {
        return this.msRetCondition;
    }

    public String getRsRetDueDate() {
        return this.msRetDueDate;
    }

    public boolean chkItemCateMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) throws SQLException, ParseException {
        boolean bSucessful = false;
        String msNoCircByPatronByItemCate = this.getMsNoCircByPatronByItemCate(msPatronID, this.msRetSMD);
        patronEligibility = this.checkItemCategory(msAccessionNo);
        if (patronEligibility > Integer.parseInt(msNoCircByPatronByItemCate) || patronEligibility == 0) {
            this.chargingdate();
            System.out.println("sdd" + this.msRawDateDue);
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(this.msPatronExpDate);
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            System.out.println("Date" + sourceFormat.format(memExp) + sourceFormat.format(dueDate));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
            this.errmessage = "084," + this.msRetItemCat;
        }
        return bSucessful;
    }

    public boolean chkPatrCateMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCircByPatronCate = this.getMsNoCircByPatronCate(msPatronID);
        if (patronEligibility > Integer.parseInt(msNoCircByPatronCate) || patronEligibility == 0) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            System.out.println("This patron's charging count has exceeded the charging eligibility treshold");
            this.errmessage = "026";
        }
        return bSucessful;
    }

    public boolean chkEligMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCirc = this.getMsNoCircByPatronElig(msPatronID);
        if (Integer.parseInt(msNoCirc) < GL27ELIG) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron has exceeded the charging count limit for this item category";
            System.out.println("This patron has exceeded the charging count limit for this item category");
            this.errmessage = "038";
        }
        return bSucessful;
    }

    public boolean checkMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) throws SQLException, ParseException {
        boolean bSucessful = true;
        System.out.println("Action" + action);
        switch (action) {
            case "chkEligMaxLoan": {
                if (this.chkEligMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility)) {
                    if (this.chkPatrCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility)) {
                        if (this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility)) {
                            bSucessful = true;
                            break;
                        }
                        bSucessful = false;
                        break;
                    }
                    bSucessful = false;
                    break;
                }
                bSucessful = false;
                break;
            }
            case "chkPatrCateMaxLoan": {
                if (this.chkPatrCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility)) {
                    if (this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility)) {
                        bSucessful = true;
                        break;
                    }
                    bSucessful = false;
                    break;
                }
                bSucessful = false;
                break;
            }
            case "chkItemCategoryMaxLoan": {
                bSucessful = this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility);
            }
            default: {
                System.out.println("Invalid grade");
            }
        }
        return bSucessful;
    }

    public void chargingdate() throws SQLException {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date currentDate = new Date();
        Date today = new Date();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        System.out.println("Current Time " + currentTime);
        System.out.println("Loan" + this.msLoanPeriod);
        cal.add(5, this.msLoanPeriod);
        System.out.println("Loan Period : " + this.msLoanPeriod);
        date = cal.getTime();
        String duedate = dateFormat.format(date).toString();
        System.out.println("Due Date before holiday deduction" + duedate);
        String duetime = timeFormat.format(date).toString();
        String inclusive = "SELECT Count(*) as Count FROM GLLIBR WHERE GL28CIRCMODE = 'E'";
        rsObj = stmt.executeQuery(inclusive);
        System.out.println(inclusive);
        while (rsObj.next()) {
            if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
            int totalHoliday = this.isHoliday(today, date, this.msPatronBranch);
            System.out.println(date);
            System.out.println(cal);
            System.out.println(totalHoliday);
            while (totalHoliday > 0) {
                today = date = cal.getTime();
                cal.add(5, totalHoliday);
                date = cal.getTime();
                totalHoliday = this.isHoliday(today, date, this.msPatronBranch);
            }
        }
        System.out.println("Holidays Count : " + holidaycount);
        System.out.println("Before holiday5");
        cal.add(5, holidaycount);
        date = cal.getTime();
        System.out.println("After holiday5");
        int isholiday = 0;
        isholiday = this.isWorkingDayorHoliday(cal, this.msPatronBranch);
        System.out.println("Isholiday : " + isholiday);
        while (isholiday > 0) {
            cal.add(5, 1);
            isholiday = this.isWorkingDayorHoliday(cal, this.msPatronBranch);
            System.out.println(isholiday);
        }
        date = cal.getTime();
        duedate = dateFormat.format(date).toString();
        duetime = timeFormat.format(date).toString();
        System.out.println("Due Date after including number of holidays" + duedate);
        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        this.msRawTimeDue = GeneralUtility.TimetoStr(duetime);
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
        System.out.println(String.valueOf(this.msRawDateDue) + this.msRawTimeDue + this.msRawDateCharged);
    }

    private int checkHoliday(String currentDate, Calendar cal) {
        int holidaycount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Date" + cal);
        String duedate = dateFormat.format(cal).toString();
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentDate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
            rsObj = stmt.executeQuery(sql2);
            System.out.println("Second holiday" + sql2);
            while (rsObj.next()) {
                holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
            }
            System.out.println("Number of Holidays" + holidaycount);
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return holidaycount;
    }

    private int isWorkingDayorHoliday(Calendar cal, String branch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' and GL30BRNC='" + branch + "'";
            Statement st = conn.createStatement(1004, 1007);
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            rows = 0;
            rsObj.last();
            rows = rsObj.getRow();
            rsObj.beforeFirst();
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return rows;
    }

    public int isHoliday(Date today, Date duedate, String branch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        String startDate = dateFormat.format(today).toString();
        String duedates = dateFormat.format(duedate).toString();
        System.out.println("Duedate in working day method " + duedate);
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE> '" + startDate + "'AND GL30DATE<='" + duedates + "') and GL30BRNC='" + branch + "'";
            Statement st = conn.createStatement(1004, 1007);
            rsObj = st.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                rows = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return rows;
    }

    public String getHolidayDesc(Calendar cal) throws SQLException {
        String holiday = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        String sql2 = "SELECT GL30DESC FROM GLHOLIDAY WHERE GL30DATE  ='" + GeneralUtility.DatetoStr(duedate) + "'";
        rsObj = stmt.executeQuery(sql2);
        System.out.println(sql2);
        while (rsObj.next()) {
            holiday = rsObj.getString("GL30DESC");
        }
        return holiday;
    }

    public int numberOfHolidays(String currentdate, String duedate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        int holiday = 0;
        try {
            String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
            rsObj = stmt.executeQuery(sql2);
            System.out.println(sql2);
            while (rsObj.next()) {
                holiday = Integer.parseInt(rsObj.getString("Holiday"));
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        return holiday;
    }

    public int checkItemEligibility(String msPatronCategory, String msPatronBranch) {
        int GL27ELIG = 0;
        this.msLoanPeriod = 0;
        this.msAllowOvd = "";
        System.out.println(msPatronBranch);
        try {
            String sql = "SELECT GL27PLOAN, GL27ELIG, GL27PTYPE,GL27ALLOWOVD FROM GLELIG WHERE GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27CATE = '" + msPatronCategory + "'" + "AND GL27BRNC = '" + msPatronBranch + "'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                GL27ELIG = rsObj.getInt("GL27ELIG");
                this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
                this.msAllowOvd = rsObj.getString("GL27ALLOWOVD");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return GL27ELIG;
    }

    public int checkPatronEligibility() {
        int GL07ELIG = 0;
        String sql = "SELECT * from GLCATE where GL07CATE='" + this.msPatronCategory + "'";
        System.out.println("Testsd" + sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                GL07ELIG = rsObj.getInt("GL07ELIG");
                this.msFineLimit = Double.parseDouble(rsObj.getString("GL07FINELIMIT"));
            }
            System.out.println(GL07ELIG);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return GL07ELIG;
    }

    public int checkItemEligibility(String msGL10ICAT) {
        int GL07ELIG = 0;
        String sql = "SELECT * from GLICAT where GL10ICAT='" + msGL10ICAT + "'";
        System.out.println("Testsd" + sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                GL07ELIG = rsObj.getInt("GL07ELIG");
            }
            System.out.println(GL07ELIG);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return GL07ELIG;
    }

    public boolean checkReservationStatus(String msPatronID, String msAccessionNo) {
        boolean reservationstatus = false;
        String sql = "SELECT CI03PATR,CI03DOCNO from CIRESV where CI03PATR='" + msPatronID + "' and CI03DOCNO='" + msAccessionNo + "'";
        System.out.println(sql);
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                reservationstatus = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationstatus;
    }

    public boolean UpdateAccessionDetail(String msRawDateCharged, String msRawTimeCharged, String msRawDateDue, String msRawTimeDue, String msAccessionNo, String msPatronID) {
        boolean flag = false;
        try {
            String sSQLStmt1 = "Update CTDOCM Set CT03BDATE = '" + msRawDateCharged + "', " + "CT03BTIME = '" + msRawTimeCharged + "', " + "CT03DDATE = '" + msRawDateDue + "', " + "CT03DTIME = '" + msRawTimeDue + "', " + "CT03STAT = 'C', ";
            sSQLStmt1 = String.valueOf(sSQLStmt1) + "CT03PATR = '" + msPatronID + "', " + "CT03CIRHITS = CT03CIRHITS + 1, " + "CT03LASTACT = '" + msRawDateCharged + "', " + "CT03LASTID = '" + msPatronID + "' " + "Where CT03DOCNO = '" + msAccessionNo + "'";
            System.out.println(sSQLStmt1);
            int count = stmt.executeUpdate(sSQLStmt1);
            if (count > 0) {
                flag = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean AddChargeTransaction(String msPatronID, String msControlNo, String msAccessionNo, String msRawDateDue, String msRawTimeDue, String msRawDateCharged, String msRawTimeCharged, String msCircStatus, String msOfficerID) {
        boolean flag = false;
        int iCounter = 0;
        String sql2 = "SELECT * FROM GLNUMB where GL98FUNC='CICIRCCOUNT'";
        try {
            rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                iCounter = rsObj.getInt("GL98VALUE");
            }
        }
        catch (SQLException sQLException) {
            // empty catch block
        }
        System.out.println("Icounter" + (iCounter + 1));
        String sql = "INSERT INTO CICIRC VALUES ('" + msAccessionNo + "'" + "," + "'" + msRawDateDue + "'" + "," + "'" + msRawTimeDue + "'" + "," + "'" + msPatronID + "'" + "," + "'" + msCircStatus + "'" + "," + "'" + msRawDateCharged + "'" + "," + "'" + msRawTimeCharged + "'" + "," + "'" + msOfficerID + "'" + "," + "null,null,null,null,null,null," + "'" + msControlNo + "'" + "," + 0 + "," + "null" + "," + (iCounter + 1) + ",null,null,null,null)";
        try {
            System.out.println("Insert" + sql);
            int count = stmt.executeUpdate(sql);
            if (count > 0) {
                String sql3 = "Update GLNUMB set GL98VALUE='" + (iCounter + 1) + "'" + "where GL98FUNC='CICIRCCOUNT'";
                System.out.println(sql3);
                int count2 = stmt.executeUpdate(sql3);
                if (count2 > 0) {
                    flag = true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean UpdatePatron(String msPatronID, String msRawDateCharged) {
        boolean flag = false;
        String sql = "Update GLPATR Set GL14BORDATE = GL14BORDATE + 1, GL14BORYEAR = GL14BORYEAR + 1, GL14LASTBOR = '" + msRawDateCharged + "' " + "Where GL14PATR = '" + msPatronID + "'";
        try {
            int count = stmt.executeUpdate(sql);
            if (count > 0) {
                flag = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String getEligibility(String msPatronID, String msRetSMD) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "'" + "And CI02FLAG = 'C' AND  CT03ICAT='" + msRetSMD + "'";
        String sql2 = "select COUNT(CT03ICAT) As CIRCULATEDNOITEM from CTDOCM,CICIRC where CT03DOCNO=CI02DOCNO AND CI02FLAG='C' AND CI02PATR='" + msPatronID.trim() + "'" + " and CT03ICAT='" + this.msRetItemCat + "' and CT03ICAT='" + msRetSMD + "'";
        System.out.println(sql2);
        try {
            ResultSet rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                if (!this.msNoCircByPatronByItem.equals(null)) continue;
                this.msNoCircByPatronByItem = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronByItemCate(String msPatronID, String msRetSMD) {
        String sql = "Select Count(CI02FLAG) As CIRCULATEDNO From CICIRC Where CI02PATR = '" + msPatronID.trim() + "'" + "And CI02FLAG = 'C' AND  CT03ICAT='" + msRetSMD + "'";
        String sql2 = "select COUNT(CT03ICAT) As CIRCULATEDNOITEM from CTDOCM,CICIRC where CT03DOCNO=CI02DOCNO AND CI02FLAG='C' AND CI02PATR='" + msPatronID.trim() + "'" + " and CT03ICAT='" + this.msRetItemCat + "' and CT03SMD='" + msRetSMD + "'";
        System.out.println(sql2);
        try {
            ResultSet rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                if (!this.msNoCircByPatronByItem.equals(null)) continue;
                this.msNoCircByPatronByItem = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronCate(String msGL14PATR) {
        String sql2 = "Select COUNT(*) as CIRCULATEDNOITEM from GLPATR, CICIRC where GL14PATR=CI02PATR and CI02FLAG = 'C' and GL14CATE='" + this.msPatronCategory + "' and GL14PATR='" + msGL14PATR + "'";
        System.out.println(sql2);
        try {
            ResultSet rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                if (!this.msNoCircByPatronByItem.equals(null)) continue;
                this.msNoCircByPatronByItem = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronElig(String msPatronID) {
        String sql2 = "SELECT COUNT(*) as CIRCULATEDNOITEM FROM CICIRC WHERE CI02PATR = '" + msPatronID + "'  " + "AND CI02DOCNO = (SELECT CT03DOCNO FROM CTDOCM WHERE CT03DOCNO = CI02DOCNO AND CT03SMD = '" + this.msRetSMD + "' AND CT03ICAT = '" + this.msRetItemCat + "') " + "AND CI02FLAG = 'C' ";
        System.out.println(sql2);
        try {
            ResultSet rsObj = stmt.executeQuery(sql2);
            while (rsObj.next()) {
                this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                if (!this.msNoCircByPatronByItem.equals(null)) continue;
                this.msNoCircByPatronByItem = "0";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return this.msNoCircByPatronByItem;
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

    public String getMsPatronCategory(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
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

    public String getMsPatronBranch(String msPatronID) {
        String sql = "SELECT GL14BRNC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
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

    public String getPatrBrnc(String msPatronID) {
        String branch = "";
        String sql = "SELECT GL05LOCA FROM GLPATR,GLLOCA WHERE GL14PATR= '" + msPatronID + "' AND GL05BRNC=GL14BRNC";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                branch = String.valueOf(rsObj.getString("GL05LOCA"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }

    public boolean getCircSattelite() {
        boolean exist = false;
        String sql = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC= 'CIRBYLOCATION'";
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

    public int checkItemCategory(String CT03DOCNO) {
        String sql = "Select GL10ELIG from GLICAT, CTDOCM where GL10ICAT = CT03ICAT and CT03DOCNO = '" + CT03DOCNO + "' ";
        int elig = 0;
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                elig = Integer.parseInt(rsObj.getString("GL10ELIG"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return elig;
    }

    public String getCirculatedPatrDetails(String CT03DOCNO) {
        String sql = "Select GL14PATR, GL14NAME, CI02DDATE from CICIRC, GLPATR where CI02PATR=GL14PATR and CI02FLAG = 'C' and CI02DOCNO = '" + CT03DOCNO + "' ";
        String details = "";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msBorrowerName = rsObj.getString("GL14NAME");
                this.msBorrowerID = rsObj.getString("GL14PATR");
                details = rsObj.getString("CI02DDATE");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }

    public static List<Charging> LateReturnHistory(String msPatronID) throws SQLException {
        ArrayList<Charging> msgrdReturnsHistory = new ArrayList<Charging>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "Select T1.CI02DOCNO, T1.CI02DDATE, T1.CI02DTIME, T1.CI02DIDATE, T1.CI02DITIME, T4.CT05SRAW as CallNo, T6.CT05SRAW as Title, CASE WHEN T1.CI02FLAG  = 'D' THEN 'Discharged' WHEN T1.CI02FLAG  = 'C' THEN 'Charged' WHEN T1.CI02FLAG  = 'L' THEN 'Lost' ELSE 'Invalid' END AS FLAGDESC From CICIRC T1 INNER JOIN CTDOCM T2 ON T1.CI02DOCNO = T2.CT03DOCNO INNER JOIN CTPONT T3 ON T2.CT03MATNO = T3.CT06MATNO INNER JOIN CTCALL T4 ON T4.CT05POINTER=T3.CT06POINTER INNER JOIN CTPONT T5 ON T2.CT03MATNO = T5.CT06MATNO INNER JOIN CTTITL T6 ON T6.CT05POINTER = T5.CT06POINTER Where (T1.CI02DIDATE > T1.CI02DDATE) And T1.CI02FLAG = 'D' And T1.CI02PATR = '" + msPatronID + "' " + "and T3.CT06TAG = '090' and T5.CT06TAG = '245' " + "Order By T1.CI02DOCNO";
            System.out.println("sqllatereturn" + sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                Charging circ = new Charging(rsObj.getString("CI02DOCNO"), Handler.removeSubfield(rsObj.getString("CallNo")), Handler.removeSubfield(rsObj.getString("Title")), DateTime.formatDate(rsObj.getString("CI02DIDATE")), DateTime.formatDate(rsObj.getString("CI02DDATE")), DateTime.Time(rsObj.getString("CI02DTIME")), DateTime.Time(rsObj.getString("CI02DITIME")), rsObj.getString("FLAGDESC"));
                msgrdReturnsHistory.add(circ);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msgrdReturnsHistory;
    }

    public static List<Charging> relatedItem(String CI03DOCNO, String CI03MATNO) throws SQLException {
        ArrayList<Charging> msgrdReturnsHistory = new ArrayList<Charging>();
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "Select CT03DOCNO, CT03DDATE, CT03DTIME, GL36DESC, CT03PATR, GL05DESC, GL71DESC, GL10DESC, GL47SMD from CTDOCM, GLLOCA, GLDOCS, GLICAT, GLSMD, GLBRNC where GL05LOCA=CT03LOCA AND GL10ICAT = CT03ICAT AND GL36STAT = CT03STAT AND GL47SMD = CT03SMD AND GL71BRNC = GL05BRNC ";
            if (CI03MATNO == null || CI03MATNO.equals("0")) {
                sql = String.valueOf(sql) + "AND CT03MATNO =(SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + CI03DOCNO + "')";
            }
            if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                sql = String.valueOf(sql) + "AND CT03MATNO = '" + CI03MATNO + "'";
            }
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                Charging circ = new Charging(rsObj.getString("CT03DOCNO"), DateTime.formatDateV2(rsObj.getString("CT03DDATE")), Handler.ifIsNull(rsObj.getString("CT03DTIME")), rsObj.getString("GL36DESC"), Handler.ifIsNull(rsObj.getString("CT03PATR")), rsObj.getString("GL71DESC"), rsObj.getString("GL05DESC"), rsObj.getString("GL10DESC"), rsObj.getString("GL47SMD"));
                msgrdReturnsHistory.add(circ);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return msgrdReturnsHistory;
    }
}
