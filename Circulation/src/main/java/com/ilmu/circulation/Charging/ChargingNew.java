/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.ILL;
import com.ilmu.circulation.Charging.Item;
import com.ilmu.circulation.Charging.Patron;
import com.ilmu.circulation.resv.Reservation;
import com.ilmu.circulation.sql.ItemSQL;
import com.ilmu.circulation.sql.PatronSQL;
import com.ilmu.global.AuditTrail;
import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.net.UnknownHostException;
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

public class ChargingNew {
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
    String msDocno;
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

    public ChargingNew(String CI02DOCNO, String CallNo, String Title, String CI02DIDATE, String CI02DDATE, String CI02DTIME) {
        this.CI02DOCNO = CI02DOCNO;
        this.CallNo = CallNo;
        this.Title = Title;
        this.CI02DIDATE = CI02DIDATE;
        this.CI02DDATE = CI02DDATE;
        this.CI02DTIME = CI02DTIME;
    }

    public ChargingNew(String CI02DOCNO, String CT03DDATE, String CT03DTIME, String CT03STAT, String CT03PATR, String GL14BRNC, String CT03LOCA, String CT03ICAT, String CT03SMD) {
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

    public void setmsCharged(int msCharged) {
        this.msCharged = msCharged;
    }

    public ChargingNew() {
        try {
            System.out.println("from charging");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean updatePriority(String CI03MATNO) {
        HashMap<String, Integer> valueInt;
        HashMap<String, String> condition;
        block12: {
            condition = new HashMap<String, String>();
            valueInt = new HashMap<String, Integer>();
            HashMap valueStr = new HashMap();
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    String sql = "Select CI03PATR, CI03MATNO, CI03PRIOR FROM CIRESV where CI03MATNO=(SELECT CT03MATNO from CTDOCM where CT03DOCNO='" + CI03MATNO + "') " + "ORDER BY CI03PRIOR";
                    ResultSet rsObj = stmt.executeQuery(sql);
                    System.out.println(sql);
                    while (rsObj.next()) {
                        System.out.println("Prior" + rsObj.getString("CI03PRIOR"));
                        condition.put("CI03PATR", rsObj.getString("CI03PATR").toUpperCase());
                        condition.put("CI03MATNO", rsObj.getString("CI03MATNO"));
                        valueInt.put("CI03PRIOR", Integer.parseInt(rsObj.getString("CI03PRIOR")) - 1);
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
        String query = QueryBuilder.createUpdateQuery("CIRESV", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public static boolean updatePriority(String CI03MATNO, Connection conn1) throws SQLException {
        HashMap<String, String> condition = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        HashMap valueStr = new HashMap();
        try {
            stmt = conn1.createStatement();
            String sql = "Select CI03PATR, CI03MATNO, CI03PRIOR FROM CIRESV where CI03MATNO=(SELECT CT03MATNO from CTDOCM where CT03DOCNO='" + CI03MATNO + "') " + "ORDER BY CI03PRIOR";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rsObj.next()) {
                condition.put("CI03PATR", rsObj.getString("CI03PATR").toUpperCase());
                condition.put("CI03MATNO", rsObj.getString("CI03MATNO"));
                valueInt.put("CI03PRIOR", Integer.parseInt(rsObj.getString("CI03PRIOR")) - 1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String query = QueryBuilder.createUpdateQuery("CIRESV", null, valueInt, null, condition);
        boolean isSuccess = DBQuery.runQuery(query, conn1);
        return isSuccess;
    }

    public boolean executeCharging(String msAccessionNo, String msPatronID, String chkMaxLoan) {
        int GL27ELIG = 0;
        int patronEligibility = 0;
        boolean patrCanChargeAccession = false;
        boolean bSucessful = false;
        this.patr = new Patron(msPatronID);
        this.msPatronStatus = this.patr.getMsPatronStatus();
        this.msPatronCategory = this.patr.getMsPatronCategory();
        this.msPatronExpDate = this.patr.getMsMemExpiryDate();
        this.msPatronBranch = this.patr.getMsPatronBranch();
        patronEligibility = this.checkPatronEligibility();
        this.msDocno = Item.getItemBrnc(msAccessionNo);
        Date date = new Date();
        this.RetrieveAccessionDetail(msAccessionNo);
        try {
            if (this.RetrieveAccessionDetail(msAccessionNo)) {
                if (this.GetCircStatus(msAccessionNo).equals("A") || this.GetCircStatus(msAccessionNo).equals("H")) {
                    patrCanChargeAccession = this.CanPatronBorrowbyAcession(msAccessionNo, this.msPatronCategory);
                    if (patrCanChargeAccession) {
                        if (this.msRetStatus.equals("H")) {
                            if (this.checkReservationStatus(msPatronID, msAccessionNo)) {
                                bSucessful = true;
                            } else {
                                this.printMessage = "This item is reserved by another patron";
                                this.errmessage = "023, " + Item.getReservedPatron(msAccessionNo);
                                bSucessful = false;
                            }
                        } else {
                            bSucessful = true;
                        }
                    }
                    System.out.println("Datess");
                } else {
                    String status = this.getItemStatus(msAccessionNo);
                    String duedate = this.getCirculatedPatrDetails(msAccessionNo);
                    if (this.itemStatCode.equals("C") && this.msBorrowerID.equalsIgnoreCase(msPatronID)) {
                        System.out.println("Dates");
                        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                        System.out.println("Dates1");
                        Date due = sourceFormat.parse(GeneralUtility.StrToDate(duedate));
                        System.out.println("Dates2");
                        Date today = new Date();
                        this.errmessage = due.compareTo(date) < 0 ? "086," + this.msBorrowerName + ",@patronName" : "087," + this.msBorrowerName + ",@patronName";
                    } else if (status.trim().equals("Incoming Request")) {
                        bSucessful = true;
                    } else {
                        System.out.println("066 Charging.java line 391");
                        this.errmessage = "066," + status + ",@status";
                        bSucessful = false;
                    }
                }
            } else {
                this.printMessage = "This item is not available for ciculation (Item status) :" + this.msRetStatus;
                this.errmessage = "020";
                bSucessful = false;
            }
            if (bSucessful) {
                GL27ELIG = this.checkItemEligibility(this.msPatronCategory, this.msRetLocation);
                if (GL27ELIG > 0) {
                    if (Item.getCircSattelite()) {
                        if (this.msRetLocation.equals(Item.getPatrSatelitteBrnc(msPatronID))) {
                            bSucessful = true;
                        } else {
                            bSucessful = false;
                            this.errmessage = "085";
                        }
                    } else {
                        bSucessful = true;
                    }
                    Patron patr = new Patron(msPatronID);
                    if (bSucessful) {
                        if (this.msAllowOvd.equals("Y") || patr.getNoMsGetItemOverdue() == 0) {
                            if (chkMaxLoan.equals("chkPatrCateMaxLoan")) {
                                System.out.println("chkPatrCateMaxLoan");
                                bSucessful = this.checkMaxLoan_v1("chkPatrCateMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility, this.msRetLocation);
                            } else if (chkMaxLoan.equals("chkItemCategoryMaxLoan")) {
                                System.out.println("chkItemCategoryMaxLoan");
                                bSucessful = this.checkMaxLoan_v1("chkItemCategoryMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility, this.msRetLocation);
                            } else {
                                System.out.println("else msAllowOvd");
                                bSucessful = this.checkMaxLoan_v1("chkEligMaxLoan", msPatronID, GL27ELIG, msAccessionNo, patronEligibility, this.msRetLocation);
                            }
                        } else {
                            bSucessful = false;
                            this.printMessage = "This patron is not eligible to borrow any item in this item category";
                            this.errmessage = "046";
                        }
                    }
                } else {
                    bSucessful = false;
                    this.printMessage = "This patron is not eligible to borrow any item in this item category";
                    this.errmessage = "039";
                }
                if (bSucessful && ILL.chkILLModExist()) {
                    if (this.msPatronCategory.equals(ILL.getILLFlag())) {
                        if (ILL.getchkILLAccession(msAccessionNo)) {
                            if (ILL.getAccessionStat(msAccessionNo).equals("G")) {
                                if (msPatronID.compareToIgnoreCase(ILL.getPatrID(msAccessionNo)) != 0) {
                                    bSucessful = false;
                                    this.printMessage = "Document is reserved to @patron";
                                    this.errmessage = "160," + ILL.getPatrID(msAccessionNo) + ",@patron";
                                } else {
                                    bSucessful = true;
                                }
                            } else {
                                bSucessful = true;
                            }
                        } else {
                            bSucessful = false;
                            this.printMessage = "Accession is not reserved for ILL body. Please perform ILL incoming request.";
                            this.errmessage = "159";
                        }
                    }
                } else {
                    bSucessful = true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean executeOverride(Calendar dueDate, String patron) {
        Patron patr = new Patron(patron);
        this.msPatronBranch = patr.getMsPatronBranch();
        this.msPatronExpDate = patr.getMsMemExpiryDate();
        boolean bSucessful = false;
        try {
            if (this.isWorkingDayorHoliday(dueDate, this.msPatronBranch) == 0) {
                Date due;
                SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date memExp = sourceFormat.parse(this.msPatronExpDate);
                if (memExp.compareTo(due = sourceFormat.parse(sourceFormat.format(dueDate.getTime()))) > 0) {
                    bSucessful = true;
                } else {
                    bSucessful = false;
                    this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
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
        System.out.println(this.msCharged);
        this.msLateReturn = patr.GetLateReturn();
        System.out.println(this.msLateReturn);
        this.msBorrowedToYear = patr.GetBorrowedToYear(msPatronID);
        System.out.println("BTD" + this.msBorrowedToYear);
        this.msLateReturnToYear = patr.GetLateReturnToYear(msPatronID);
        this.msRenewDate = patr.GetLastRenewDate(msPatronID);
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

    public boolean charge_ori(String msAccessionNo, String msPatronID, String CI02DDATE, String CI02DTIME, String CI02CDATE, String CI02CTIME, String username) throws SQLException {
        boolean bSucessful = false;
        this.patrs = new Patron(msPatronID);
        this.chargingdate();
        if (this.checkReservationStatus(msPatronID, msAccessionNo)) {
            boolean delete = Reservation.deleteReservation(msPatronID, msAccessionNo);
            System.out.println("Test");
            if (delete) {
                ChargingNew.updatePriority(msAccessionNo);
            }
        }
        if (this.UpdateAccessionDetail(this.msRawDateCharged, this.msRawTimeCharged, CI02DDATE, CI02DTIME, msAccessionNo, msPatronID)) {
            if (this.AddChargeTransaction(msPatronID, this.msRetMatNo, msAccessionNo, CI02DDATE, CI02DTIME, CI02CDATE, CI02CTIME, "C", username)) {
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
        if (bSucessful && ILL.chkILLModExist()) {
            bSucessful = ILL.updateStatus(msAccessionNo);
        }
        try {
            AuditTrail.InsertAudit("CI", "CII0001", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean charge(String msAccessionNo, String msPatronID, String CI02DDATE, String CI02DTIME, String CI02CDATE, String CI02CTIME, String username) throws UnknownHostException {
        boolean bSucessful = false;
        try {
            this.patrs = new Patron(msPatronID);
            this.chargingdate();
            if (this.checkReservationStatus(msPatronID, msAccessionNo)) {
                boolean delete = Reservation.deleteReservation(msPatronID, msAccessionNo);
                System.out.println("Test");
                if (delete) {
                    ChargingNew.updatePriority(msAccessionNo);
                }
            }
            if (this.UpdateAccessionDetail(this.msRawDateCharged, this.msRawTimeCharged, CI02DDATE, CI02DTIME, msAccessionNo, msPatronID)) {
                if (this.AddChargeTransaction(msPatronID, this.msRetMatNo, msAccessionNo, CI02DDATE, CI02DTIME, CI02CDATE, CI02CTIME, "C", username)) {
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
            if (bSucessful && ILL.chkILLModExist()) {
                bSucessful = ILL.updateStatus(msAccessionNo);
            }
            AuditTrail.InsertAudit("CI", "CII0001", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (Exception e) {
            e.printStackTrace();
            bSucessful = false;
            this.errmessage = "041";
        }
        return bSucessful;
    }

    public boolean CanPatronCharge() {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronCharge(String msPatronStatus) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronDisCharge() {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronDisCharge(String msPatronStatus) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronReserve() {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronReserve(String msPatronStatus) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
        return validate;
    }

    public boolean CanPatronBorrow(String vsControlNo) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03MATNO = '" + vsControlNo + "' " + " AND GL27CATE = '" + this.patr.getMsPatronStatus().trim() + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y') ";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public boolean CanPatronBorrow(String vsControlNo, String msPatronStatus) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03MATNO = '" + vsControlNo + "' " + " AND GL27CATE = '" + msPatronStatus + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC " + " AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public boolean CanPatronBorrowbyAcession(String vsAccessionNo) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE CT03DOCNO = '" + vsAccessionNo + "' " + " AND  GL27CATE = '" + this.patr.getMsPatronCategory() + "' " + " AND GL27ICAT = CT03ICAT " + " AND GL27SMD = CT03SMD " + " AND CT03LOCA = GL05LOCA " + " AND GL27BRNC = GL05BRNC ";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public boolean CanPatronBorrowbyAcession(String vsAccessionNo, String msPatronCategory) {
        boolean validate = false;
        String sql = ItemSQL.getMsAllowBorrowItem(vsAccessionNo, msPatronCategory);
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public int getItemCharged(String msPatronId) {
        int noChargedItem = 0;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(CI02DOCNO) AS NOCHARGED FROM CICIRC WHERE CI02PATR ='" + msPatronId + "'";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    noChargedItem = Integer.parseInt(rsObj.getString("NOCHARGED"));
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
        return noChargedItem;
    }

    public boolean CanPatronHold(String vsDocNo) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE   CT03DOCNO = '" + vsDocNo + "' " + "AND GL27CATE = '" + this.patr.getMsPatronCategory() + "' " + "AND GL27ICAT = CT03ICAT " + "AND GL27SMD = CT03SMD " + "AND GL27BRNC = GL05BRNC " + "AND GL05LOCA= CT03LOCA " + "AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public boolean CanPatronHold(String vsDocNo, String msPatronCategory) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM CTDOCM, GLELIG, GLLOCA WHERE   CT03DOCNO = '" + vsDocNo + "' " + "AND GL27CATE = '" + msPatronCategory + "' " + "AND GL27ICAT = CT03ICAT " + "AND GL27SMD = CT03SMD " + "AND GL27BRNC = GL05BRNC " + "AND GL05LOCA= CT03LOCA " + "AND (GL27ALLOWRSV='Y' OR GL27ALLOWRSV='y')";
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    validate = true;
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
        return validate;
    }

    public long getCicircCounter(String msPatronID, String vsMatNo, String vsDocNo) {
        long cicirccounter = 0L;
        String sql = "SELECT CI02COUNTER FROM CICIRC WHERE CI02PATR = '" + msPatronID.trim() + "' " + "AND CI02MATNO = '" + vsMatNo.trim() + "' " + "AND CI02DOCNO = '" + vsDocNo.trim() + "' " + "AND CI02FLAG = 'C' ";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    cicirccounter = rsObj.getLong("CI02COUNTER");
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
        return cicirccounter;
    }

    public String getStatusDesc(String vsStatus) {
        String Statusdesc = null;
        String sql = "SELECT CODE,DESCRIPTION FROM FNDCODE WHERE CODE = '" + vsStatus + "'" + "  and fcode='E'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    Statusdesc = rsObj.getString("DESCRIPTION");
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
        return Statusdesc;
    }

    public String GetCircStatus(String vsItemID) {
        String itemStatus = null;
        String sql = "SELECT CT03DOCNO, CT03STAT FROM CTDOCM WHERE CT03DOCNO = '" + vsItemID + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    itemStatus = rsObj.getString("CT03STAT");
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
        return itemStatus;
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo) {
        boolean exist = false;
        String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03LOCA=GL05LOCA AND CT03DOCNO = '" + msAccessionNo + "'";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
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
                    exist = true;
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
        return exist;
    }

    public boolean validAccession(String msAccessionNo) {
        boolean exist = false;
        try {
            try {
                String sql = "SELECT Count(*) FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "'";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                if (rsObj.next()) {
                    exist = true;
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
            try {
                String sql = "SELECT Count(*) as Count FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "' AND (CT03STAT!= 'A' AND CT03STAT!='H')";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                if (rsObj.next() && Integer.parseInt(rsObj.getString("Count")) > 0) {
                    exist = true;
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
        return exist;
    }

    public String getItemStatus(String msAccessionNo) {
        String status = "";
        String sql = ItemSQL.getMsItemStatus(msAccessionNo);
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.itemStatCode = rsObj.getString("CODE");
                    status = rsObj.getString("DESCRIPTION");
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
        return status;
    }

    public static String getItemStat(String msAccessionNo) {
        String status = "";
        try {
            try {
                String sql = "Select DESCRIPTION from CTDOCM, FNDCODE where CT03STAT!='A' and CODE=CT03STAT and CT03DOCNO='" + msAccessionNo + "' AND FNDCODE='E'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    status = rsObj.getString("DESCRIPTION");
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
            System.out.println("dueDate" + this.msRawDateDue);
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(this.msPatronExpDate);
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            this.errmessage = "084," + this.msRetItemCat + ",@itemcate";
        }
        return bSucessful;
    }

    public boolean chkItemCateMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility, String msRetLocation) throws SQLException, ParseException {
        boolean bSucessful = false;
        String msNoCircByPatronByItemCate = this.getMsNoCircByPatronByItemCate(msPatronID, this.msRetSMD, msRetLocation);
        patronEligibility = this.checkItemCategory(msAccessionNo);
        if (GL27ELIG > Integer.parseInt(msNoCircByPatronByItemCate)) {
            System.out.println("sini ke error isyraf?? NEW ");
            this.chargingdate();
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(this.msPatronExpDate);
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            this.errmessage = "038";
        }
        return bSucessful;
    }

    public boolean chkItemCateMaxLoan_v1(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) throws SQLException, ParseException {
        boolean bSucessful = false;
        String msNoCircByPatronByItemCate = this.getMsNoCircByPatronByItemCate(msPatronID, this.msRetSMD);
        patronEligibility = this.checkItemCategory(msAccessionNo);
        if (patronEligibility > Integer.parseInt(msNoCircByPatronByItemCate) || patronEligibility == 0) {
            this.chargingdate();
            SimpleDateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date memExp = sourceFormat.parse(this.msPatronExpDate);
            Date dueDate = sourceFormat.parse(GeneralUtility.StrToDate(this.msRawDateDue));
            if (memExp.compareTo(dueDate) > 0) {
                bSucessful = true;
            } else {
                bSucessful = false;
                this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
                this.errmessage = "068";
            }
        } else {
            bSucessful = false;
            this.printMessage = "This patron's charging count has exceeded the charging eligibility treshold";
            this.errmessage = "084," + this.msRetItemCat + ",@itemcate";
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
            this.errmessage = "026";
        }
        return bSucessful;
    }

    public boolean chkEligMaxLoan_v1(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCirc = this.getMsNoCircByPatronElig(msPatronID);
        String totalNoItemCirculation = this.getTotalNoItemCirculation(msPatronID);
        if (patronEligibility == 0) {
            bSucessful = true;
        } else if (Integer.parseInt(msNoCirc) < patronEligibility && Integer.parseInt(totalNoItemCirculation) < patronEligibility) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron has exceeded the charging count limit for this item category";
            this.errmessage = "026";
        }
        return bSucessful;
    }

    private String getTotalNoItemCirculation(String msPatronID) {
        String sql = "SELECT COUNT(*) as TOTALITEMCIRCULATING FROM CICIRC WHERE CI02PATR = '" + msPatronID + "' AND CI02FLAG = 'C' ";
        String totalNumberItemCirculating = "";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    totalNumberItemCirculating = rsObj.getString("TOTALITEMCIRCULATING");
                    if (!totalNumberItemCirculating.equals(null)) continue;
                    totalNumberItemCirculating = "0";
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
        return totalNumberItemCirculating;
    }

    public boolean chkEligMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) {
        boolean bSucessful = false;
        String msNoCirc = this.getMsNoCircByPatronElig(msPatronID);
        if (Integer.parseInt(msNoCirc) < GL27ELIG) {
            bSucessful = true;
        } else {
            bSucessful = false;
            this.printMessage = "This patron has exceeded the charging count limit for this item category";
            this.errmessage = "038";
        }
        return bSucessful;
    }

    public boolean checkMaxLoan(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility) throws SQLException, ParseException {
        boolean bSucessful = true;
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

    public boolean checkMaxLoan_v1(String action, String msPatronID, int GL27ELIG, String msAccessionNo, int patronEligibility, String msRetLocation) throws SQLException, ParseException {
        boolean bSucessful = true;
        switch (action) {
            case "chkEligMaxLoan": {
                bSucessful = this.chkEligMaxLoan_v1(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility);
                if (this.chkItemCateMaxLoan(action, msPatronID, GL27ELIG, msAccessionNo, patronEligibility, msRetLocation)) {
                    bSucessful = true;
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
        String duetime;
        String duedate;
        String currentTime;
        String currentdate;
        block21: {
            boolean holidaycount = false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Date today = new Date();
            Date date = new Date();
            currentdate = dateFormat.format(date).toString();
            date = cal.getTime();
            currentTime = timeFormat.format(date).toString();
            System.out.println("Loan" + this.msLoanPeriod);
            cal.add(5, this.msLoanPeriod);
            date = cal.getTime();
            duedate = dateFormat.format(date).toString();
            duetime = timeFormat.format(date).toString();
            String inclusive = "";
            DBConnection db = new DBConnection();
            if (db.getDBType().equals("oracle")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR WHERE rownum = 1";
            } else if (db.getDBType().equals("mssql")) {
                inclusive = "SELECT TOP 1 GL28CIRCMODE FROM GLLIBR";
            } else if (db.getDBType().equals("mysql")) {
                inclusive = "SELECT GL28CIRCMODE FROM GLLIBR LIMIT 1";
            }
            Connection conn1 = null;
            Statement stmt1 = null;
            ResultSet rsObj1 = null;
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                rsObj1 = stmt1.executeQuery(inclusive);
                while (rsObj1.next()) {
                    int totalHoliday;
                    if (!rsObj1.getString("GL28CIRCMODE").equals("I") || (totalHoliday = this.isHoliday(today, date, this.msPatronBranch)) <= 0) continue;
                    cal.setTime(dateFormat.parse(duedate));
                    cal.add(5, totalHoliday);
                    duedate = dateFormat.format(cal.getTime());
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                break block21;
            }
            catch (ParseException e) {
                try {
                    e.printStackTrace();
                    break block21;
                }
                catch (Throwable throwable) {
                    throw throwable;
                }
                finally {
                    try {
                        conn1.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.msRawDateDue = GeneralUtility.DatetoStr(duedate);
        this.msRawTimeDue = GeneralUtility.TimetoStr(duetime);
        this.msRawDateCharged = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeCharged = GeneralUtility.TimetoStr(currentTime);
    }

    private int isWorkingDayorHoliday(Calendar cal, String branch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        date2 = cal.getTime();
        String duedate = dateFormat.format(date2).toString();
        try {
            try {
                String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' and GL30BRNC='" + branch + "'";
                conn = DBConnection.getConnection();
                Statement st = conn.createStatement(1004, 1007);
                rsObj = st.executeQuery(sql2);
                System.out.println(sql2);
                rows = 0;
                rsObj.last();
                rows = rsObj.getRow();
                rsObj.beforeFirst();
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
        return rows;
    }

    public int isHoliday(Date today, Date duedate, String branch) {
        int rows = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Date date2 = new Date();
        String startDate = dateFormat.format(today).toString();
        String duedates = dateFormat.format(duedate).toString();
        try {
            try {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE (GL30DATE > '" + startDate + "'AND GL30DATE<='" + duedates + "') and GL30BRNC='" + branch + "'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    rows = Integer.parseInt(rsObj.getString("Holiday"));
                }
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
            try {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + GeneralUtility.DatetoStr(currentdate) + "'AND '" + GeneralUtility.DatetoStr(duedate) + "'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holiday = Integer.parseInt(rsObj.getString("Holiday"));
                }
            }
            catch (SQLException sQLException) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
        return holiday;
    }

    public int checkItemEligibility(String msPatronCategory, String msPatronBranch) {
        int GL27ELIG = 0;
        this.msLoanPeriod = 0;
        this.msAllowOvd = "";
        String sql = ItemSQL.getMsItemEligibility(this.msRetItemCat, this.msRetSMD, msPatronCategory, this.msRetItemBranch);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    GL27ELIG = rsObj.getInt("GL27ELIG");
                    this.msLoanPeriod = rsObj.getInt("GL27PLOAN");
                    this.msAllowOvd = rsObj.getString("GL27ALLOWOVD");
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
        return GL27ELIG;
    }

    public int checkPatronEligibility() {
        int GL07ELIG = 0;
        String sql = PatronSQL.getMsChkPatronElig(this.msPatronCategory);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    GL07ELIG = rsObj.getInt("GL07ELIG");
                    String limit = rsObj.getString("GL07FINELIMIT");
                    this.msFineLimit = limit == null ? Double.valueOf(0.0) : Double.valueOf(Double.parseDouble(rsObj.getString("GL07FINELIMIT")));
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
        return GL07ELIG;
    }

    public int checkPatronEligibility_V1() {
        int GL07ELIG = 0;
        String sql = PatronSQL.getMsChkPatronElig(this.msPatronCategory);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    GL07ELIG = rsObj.getInt("GL07ELIG");
                    String limit = rsObj.getString("GL07FINELIMIT");
                    this.msFineLimit = limit == null ? Double.valueOf(0.0) : Double.valueOf(Double.parseDouble(rsObj.getString("GL07FINELIMIT")));
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
        return GL07ELIG;
    }

    public int checkItemEligibility(String msGL10ICAT) {
        int GL07ELIG = 0;
        String sql = "SELECT * from GLICAT where GL10ICAT='" + msGL10ICAT + "'";
        System.out.println("Testsd" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    GL07ELIG = rsObj.getInt("GL07ELIG");
                }
                System.out.println(GL07ELIG);
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
        return GL07ELIG;
    }

    public boolean checkReservationStatus(String msPatronID, String msAccessionNo) {
        boolean reservationstatus = false;
        String sql = ItemSQL.getMsChkResvStat(msPatronID, msAccessionNo);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (!rsObj.getString("CI03PATR").equalsIgnoreCase(msPatronID)) continue;
                    reservationstatus = true;
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
        return reservationstatus;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean UpdateAccessionDetail(String msRawDateCharged, String msRawTimeCharged, String msRawDateDue, String msRawTimeDue, String msAccessionNo, String msPatronID) throws SQLException {
        String resultCheckCIHit = this.checkCIHitisNull(msAccessionNo);
        boolean flag = false;
        if (resultCheckCIHit == null) {
            try {
                try {
                    String sSQLStmt1 = "Update CTDOCM Set CT03BDATE = '" + msRawDateCharged + "', " + "CT03BTIME = '" + msRawTimeCharged + "', " + "CT03DDATE = '" + msRawDateDue + "', " + "CT03DTIME = '" + msRawTimeDue + "', " + "CT03STAT = 'C', ";
                    sSQLStmt1 = String.valueOf(sSQLStmt1) + "CT03PATR = '" + msPatronID.toUpperCase() + "', " + "CT03CIRHITS = 0 + 1, " + "CT03LASTACT = '" + msRawDateCharged + "', " + "CT03LASTID = '" + msPatronID.toUpperCase() + "' " + "Where CT03DOCNO = '" + msAccessionNo + "'";
                    System.out.println(sSQLStmt1);
                    conn = DBConnection.getConnection();
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    int count = stmt.executeUpdate(sSQLStmt1);
                    conn.commit();
                    if (count <= 0) return flag;
                    flag = true;
                    return flag;
                }
                catch (SQLException e) {
                    System.err.println("Cannot connect ! ");
                    conn.rollback();
                    e.printStackTrace();
                    if (conn == null) return flag;
                    try {
                        conn.close();
                        return flag;
                    }
                    catch (SQLException sQLException) {}
                }
                return flag;
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
        }
        try {
            try {
                String sSQLStmt1 = "Update CTDOCM Set CT03BDATE = '" + msRawDateCharged + "', " + "CT03BTIME = '" + msRawTimeCharged + "', " + "CT03DDATE = '" + msRawDateDue + "', " + "CT03DTIME = '" + msRawTimeDue + "', " + "CT03STAT = 'C', ";
                sSQLStmt1 = String.valueOf(sSQLStmt1) + "CT03PATR = '" + msPatronID.toUpperCase() + "', " + "CT03CIRHITS = CT03CIRHITS + 1, " + "CT03LASTACT = '" + msRawDateCharged + "', " + "CT03LASTID = '" + msPatronID.toUpperCase() + "' " + "Where CT03DOCNO = '" + msAccessionNo + "'";
                System.out.println(sSQLStmt1);
                conn = DBConnection.getConnection();
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQLStmt1);
                conn.commit();
                if (count <= 0) return flag;
                flag = true;
                return flag;
            }
            catch (SQLException e) {
                System.err.println("Cannot connect ! ");
                conn.rollback();
                e.printStackTrace();
                if (conn == null) return flag;
                try {
                    conn.close();
                    return flag;
                }
                catch (SQLException sQLException) {}
            }
            return flag;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException sQLException) {}
            }
        }
    }

    public String checkCIHitisNull(String msAccessionNo) {
        String checkCT03CIRHITS;
        block13: {
            String SQLStmtCT03CIRHITS = "SELECT CT03CIRHITS FROM CTDOCM WHERE CT03DOCNO ='" + msAccessionNo + "'";
            checkCT03CIRHITS = null;
            try {
                try {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    pstmt = conn.prepareStatement(SQLStmtCT03CIRHITS);
                    rs = pstmt.executeQuery();
                    System.out.println(SQLStmtCT03CIRHITS);
                    while (rs.next()) {
                        checkCT03CIRHITS = rs.getString("CT03CIRHITS");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Closing the connection.");
                    if (conn == null) break block13;
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
            finally {
                System.out.println("Closing the connection.");
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
        }
        return checkCT03CIRHITS;
    }

    public boolean AddChargeTransaction(String msPatronID, String msControlNo, String msAccessionNo, String msRawDateDue, String msRawTimeDue, String msRawDateCharged, String msRawTimeCharged, String msCircStatus, String msOfficerID) throws SQLException {
        boolean flag;
        block25: {
            Glnumb iCounter;
            block24: {
                flag = false;
                iCounter = Glnumb.getGL98VALUE("CICIRCCOUNT");
                String control = "SELECT CT03MATNO FROM CTDOCM where CT03DOCNO='" + msAccessionNo + "'";
                try {
                    try {
                        conn = DBConnection.getConnection();
                        stmt = conn.createStatement();
                        ResultSet rsObj1 = stmt.executeQuery(control);
                        while (rsObj1.next()) {
                            msControlNo = rsObj1.getString("CT03MATNO");
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
                        break block24;
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
            String sql = "INSERT INTO CICIRC  (CI02DOCNO,CI02DDATE,CI02DTIME,CI02PATR,CI02FLAG,CI02CDATE,CI02CTIME,CI02OFFI,CI02DIDATE,CI02DITIME, CI02DIOFFI,CI02SENT1,CI02SENT2,CI02SENT3,CI02MATNO,CI02RENEW,CI02BORROW,CI02COUNTER,CI02RDATE,CI02RTIME,CI02ROFFID,CI02SDRDATE) VALUES ('" + msAccessionNo + "'" + "," + "'" + msRawDateDue + "'" + "," + "'" + msRawTimeDue + "'" + "," + "'" + msPatronID + "'" + "," + "'" + msCircStatus + "'" + "," + "'" + msRawDateCharged + "'" + "," + "'" + msRawTimeCharged + "'" + "," + "'" + msOfficerID + "'" + "," + "null,null,null,null,null,null," + "'" + msControlNo + "'" + "," + 0 + "," + "null" + "," + String.valueOf(iCounter.getGL98VALUE()) + ",null,null,null,null)";
            try {
                try {
                    System.out.println(sql);
                    conn = DBConnection.getConnection();
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    int count = stmt.executeUpdate(sql);
                    conn.commit();
                    flag = true;
                }
                catch (SQLException e) {
                    System.err.println("Cannot connect ! ");
                    conn.rollback();
                    e.printStackTrace();
                    if (conn == null) break block25;
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
        }
        return flag;
    }

    public boolean UpdatePatron(String msPatronID, String msRawDateCharged) {
        boolean flag = false;
        String sql = "Update GLPATR Set GL14BORDATE = GL14BORDATE + 1, GL14BORYEAR = GL14BORYEAR + 1, GL14LASTBOR = '" + msRawDateCharged + "' " + "Where GL14PATR = '" + msPatronID + "'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sql);
                if (count > 0) {
                    flag = true;
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
        return flag;
    }

    public String getEligibility(String msPatronID, String msRetSMD) {
        String sql2 = "select COUNT(CT03ICAT) As CIRCULATEDNOITEM from CTDOCM,CICIRC where CT03DOCNO=CI02DOCNO AND CI02FLAG='C' AND CI02PATR='" + msPatronID.trim() + "'" + " and CT03ICAT='" + this.msRetItemCat + "' and CT03ICAT='" + msRetSMD + "'";
        System.out.println(sql2);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql2);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronByItemCate(String msPatronID, String msRetSMD) {
        String sql2 = "select COUNT(CT03ICAT) As CIRCULATEDNOITEM from CTDOCM,CICIRC where CT03DOCNO=CI02DOCNO AND CI02FLAG='C' AND CI02PATR='" + msPatronID.trim() + "'" + " and CT03ICAT='" + this.msRetItemCat + "' and CT03SMD='" + msRetSMD + "'";
        System.out.println(sql2);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronByItemCate(String msPatronID, String msRetSMD, String msRetLocation) {
        String sql = "SELECT COUNT(*) AS CIRCULATEDNOITEM FROM CICIRC, CTDOCM , GLLOCA WHERE CT03DOCNO = CI02DOCNO AND CI02PATR= '" + msPatronID.trim() + "'" + " AND CT03LOCA = GL05LOCA AND GL05BRNC = (SELECT GL05BRNC FROM GLLOCA WHERE GL05LOCA='" + msRetLocation + "') AND CT03ICAT = '" + this.msRetItemCat + "' AND CT03SMD = '" + msRetSMD + "'" + "AND CI02FLAG='C'";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronCate(String msGL14PATR) {
        String sql2 = "Select COUNT(*) as CIRCULATEDNOITEM from GLPATR, CICIRC where GL14PATR=CI02PATR and CI02FLAG = 'C' and GL14CATE='" + this.msPatronCategory + "' and GL14PATR='" + msGL14PATR + "'";
        System.out.println(sql2);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronElig(String msPatronID) {
        String sql2 = "SELECT COUNT(*) as CIRCULATEDNOITEM FROM CICIRC WHERE CI02PATR = '" + msPatronID + "'  " + "AND CI02DOCNO = (SELECT CT03DOCNO FROM CTDOCM WHERE CT03DOCNO = CI02DOCNO AND CT03SMD = '" + this.msRetSMD + "' AND CT03ICAT = '" + this.msRetItemCat + "') " + "AND CI02FLAG = 'C' ";
        System.out.println(sql2);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsNoCircByPatronElig_v1(String msPatronID) {
        String sql2 = "SELECT COUNT(*) as CIRCULATEDNOITEM FROM CICIRC WHERE CI02PATR = '" + msPatronID + "AND CI02FLAG = 'C' ";
        System.out.println(sql2);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                while (rsObj.next()) {
                    this.msNoCircByPatronByItem = rsObj.getString("CIRCULATEDNOITEM");
                    if (!this.msNoCircByPatronByItem.equals(null)) continue;
                    this.msNoCircByPatronByItem = "0";
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
        return this.msNoCircByPatronByItem;
    }

    public String getMsPatronStatus(String msPatronID) {
        String sql = "SELECT GL14STAT FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msPatronStatus = String.valueOf(rsObj.getString("GL14STAT"));
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
        return this.msPatronStatus;
    }

    public String getMsPatronCategory(String msPatronID) {
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msPatronCategory = String.valueOf(rsObj.getString("GL14CATE"));
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
        return this.msPatronCategory;
    }

    public String getMsPatronBranch(String msPatronID) {
        String sql = "SELECT GL14BRNC FROM GLPATR WHERE GL14PATR= '" + msPatronID + "' ";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msPatronBranch = String.valueOf(rsObj.getString("GL14BRNC"));
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
        return this.msPatronBranch;
    }

    public String getPatrBrnc(String msPatronID) {
        String branch = "";
        String sql = "SELECT GL05LOCA FROM GLPATR,GLLOCA WHERE GL14PATR= '" + msPatronID + "' AND GL05BRNC=GL14BRNC";
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    branch = String.valueOf(rsObj.getString("GL05LOCA"));
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
        return branch;
    }

    public int checkItemCategory(String CT03DOCNO) {
        String sql = "Select GL10ELIG from GLICAT, CTDOCM where GL10ICAT = CT03ICAT and CT03DOCNO = '" + CT03DOCNO + "' ";
        int elig = 0;
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    elig = rsObj.getString("GL10ELIG") == null ? 0 : Integer.parseInt(rsObj.getString("GL10ELIG"));
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
        return elig;
    }

    public String getCirculatedPatrDetails(String CT03DOCNO) {
        String sql = "Select GL14PATR, GL14NAME, CI02DDATE from CICIRC, GLPATR where CI02PATR=GL14PATR and CI02FLAG = 'C' and CI02DOCNO = '" + CT03DOCNO + "' ";
        String details = "";
        System.out.println(sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msBorrowerName = rsObj.getString("GL14NAME");
                    this.msBorrowerID = rsObj.getString("GL14PATR");
                    details = rsObj.getString("CI02DDATE");
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
        return details;
    }

    public static String getBrnc(String location) {
        String sql = "Select GL05BRNC FROM GLBRNC where GL05LOCA = '" + location + "' ";
        String branch = "";
        System.out.println("sss" + sql);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    branch = rsObj.getString("GL05BRNC");
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
        return branch;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }

    public static List<ChargingNew> LateReturnHistory(String msPatronID) throws SQLException {
        ArrayList<ChargingNew> msgrdReturnsHistory = new ArrayList<ChargingNew>();
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                String sql = "Select T1.CI02DOCNO, T1.CI02DDATE, T1.CI02DTIME, T1.CI02DIDATE, T1.CI02DITIME, T4.CT05SRAW as CallNo, T6.CT05SRAW as Title From CICIRC T1 INNER JOIN CTDOCM T2 ON T1.CI02DOCNO = T2.CT03DOCNO INNER JOIN CTPONT T3 ON T2.CT03MATNO = T3.CT06MATNO INNER JOIN CTCALL T4 ON T4.CT05POINTER=T3.CT06POINTER INNER JOIN CTPONT T5 ON T2.CT03MATNO = T5.CT06MATNO INNER JOIN CTTITL T6 ON T6.CT05POINTER = T5.CT06POINTER Where (T1.CI02DIDATE > T1.CI02DDATE) And T1.CI02FLAG = 'D' And T1.CI02PATR = '" + msPatronID + "' " + "and T3.CT06TAG = '090' and " + "T5.CT06TAG = '245' " + "Order By T1.CI02DOCNO";
                System.out.println(sql);
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    ChargingNew circ = new ChargingNew(rsObj.getString("CI02DOCNO"), Handler.removeSubfield(rsObj.getString("CallNo")), Handler.removeSubfield(rsObj.getString("Title")), DateTime.formatDate(rsObj.getString("CI02DIDATE")), DateTime.formatDate(rsObj.getString("CI02DDATE")), DateTime.Time(rsObj.getString("CI02DTIME")));
                    msgrdReturnsHistory.add(circ);
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
        return msgrdReturnsHistory;
    }

    public static List<ChargingNew> relatedItem(String CI03DOCNO, String CI03MATNO) throws SQLException {
        ArrayList<ChargingNew> msgrdReturnsHistory = new ArrayList<ChargingNew>();
        Connection conn1 = null;
        Statement stmt1 = null;
        ResultSet rsObj1 = null;
        try {
            try {
                conn1 = DBConnection.getConnection();
                stmt1 = conn1.createStatement();
                String sql = "Select CT03DOCNO, CT03DDATE, CT03DTIME, DESCRIPTION, CT03PATR, GL05DESC, GL71DESC, GL10DESC, GL47SMD from CTDOCM, GLLOCA, FNDCODE, GLICAT, GLSMD, GLBRNC where GL05LOCA=CT03LOCA AND GL10ICAT = CT03ICAT AND CODE = CT03STAT AND FCODE='E' AND GL47SMD = CT03SMD AND GL71BRNC = GL05BRNC ";
                if (CI03MATNO == null || CI03MATNO.equals("0")) {
                    sql = String.valueOf(sql) + "AND CT03MATNO =(SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + CI03DOCNO + "')";
                }
                if (CI03DOCNO == null || CI03DOCNO.equals("0")) {
                    sql = String.valueOf(sql) + "AND CT03MATNO = '" + CI03MATNO + "'";
                }
                System.out.println(sql);
                rsObj1 = stmt1.executeQuery(sql);
                while (rsObj1.next()) {
                    ChargingNew circ = new ChargingNew(rsObj1.getString("CT03DOCNO"), DateTime.formatDateV2(rsObj1.getString("CT03DDATE")), DateTime.Time(Handler.ifIsNull(rsObj1.getString("CT03DTIME"))), rsObj1.getString("DESCRIPTION"), Handler.ifIsNull(rsObj1.getString("CT03PATR")), rsObj1.getString("GL71DESC"), rsObj1.getString("GL05DESC"), rsObj1.getString("GL10DESC"), rsObj1.getString("GL47SMD"));
                    msgrdReturnsHistory.add(circ);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    conn1.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn1.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return msgrdReturnsHistory;
    }
}
