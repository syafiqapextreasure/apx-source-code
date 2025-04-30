/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.LocalDateTime
 *  org.joda.time.ReadablePartial
 */
package com.kmlink.ilmu.circulation.Charging;

import com.kmlink.ilmu.circulation.Charging.GeneralUtility;
import com.kmlink.ilmu.circulation.Charging.ILL;
import com.kmlink.ilmu.circulation.Charging.Item;
import com.kmlink.ilmu.circulation.Charging.Renewal;
import com.kmlink.ilmu.circulation.Global.AuditTrail;
import com.kmlink.ilmu.circulation.Global.DateTime;
import com.kmlink.ilmu.circulation.Global.Glnumb;
import com.kmlink.ilmu.circulation.Global.IMS;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;

public class Discharging {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rsObj = null;
    private String msRetAccessionNo;
    private String msRetStatus;
    private String msRetItemCat;
    private String msRetMatNo;
    private String msRetDocNo;
    private String msRetSMD;
    private String msRetLocation;
    private String msRetPatron;
    private String msRetCondition;
    private String msRetDueTime;
    private String msRetTimeCharged;
    private String msRetDueDate;
    private String msNoCircByPatron;
    private String msRetDateCharged;
    private String msPatronCategory;
    private String msRetItemBranch;
    private String msResvPatrName;
    private String msResvPatrId;
    private String msResvPickup;
    private String circDDate;
    private String circDTime;
    private int circCounter;
    String msRawDateReturned;
    String msRawTimeReturned;
    private int msLateBy;
    private BigDecimal msFinesByItem;
    boolean validAccession = false;
    boolean UpdateAccessionDetail = false;
    boolean UpdatePatronDetails = false;
    boolean UpdateCirculationDetails = false;
    boolean UpdateReservation = false;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    String printMessage;
    int latedays = 0;
    String errmessage = "";

    public Discharging() {
        try {
            System.out.println("Discharging Module");
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getItemStatus(String msAccessionNo) {
        String itemStatus = "";
        String sql = "SELECT GL36STAT, GL36DESC FROM GLDOCS, CTDOCM WHERE GL36STAT=CT03STAT AND CT03DOCNO='" + msAccessionNo + "'";
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                itemStatus = rsObj.getString("GL36DESC");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemStatus;
    }

    public boolean RetrievePrintDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And (CT03STAT='A')";
        System.out.println(sql);
        boolean exist = false;
        try {
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                this.msRetAccessionNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                this.msRetDocNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                this.msRetDueDate = String.valueOf(rsObj.getString("CT03DDATE"));
                this.msRetDateCharged = String.valueOf(rsObj.getString("CT03BDATE"));
                this.msRetDueTime = String.valueOf(rsObj.getString("CT03DTIME"));
                this.msRetTimeCharged = String.valueOf(rsObj.getString("CT03BTIME"));
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(exist);
        return exist;
    }

    public boolean RetrieveAccessionDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD, CT03DTIME, CT03BTIME FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "' And CT03PATR='" + msPatronID + "' AND (CT03STAT='C' OR CT03STAT='E')";
        System.out.println(sql);
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.msRetAccessionNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                    this.msRetStatus = String.valueOf(rsObj.getString("CT03STAT"));
                    this.msRetItemCat = String.valueOf(rsObj.getString("CT03ICAT"));
                    this.msRetMatNo = String.valueOf(rsObj.getString("CT03MATNO"));
                    this.msRetDocNo = String.valueOf(rsObj.getString("CT03DOCNO"));
                    this.msRetSMD = String.valueOf(rsObj.getString("CT03SMD"));
                    this.msRetLocation = String.valueOf(rsObj.getString("CT03LOCA"));
                    this.msRetPatron = String.valueOf(rsObj.getString("CT03PATR"));
                    this.msRetDueDate = String.valueOf(rsObj.getString("CT03DDATE"));
                    this.msRetDateCharged = String.valueOf(rsObj.getString("CT03BDATE"));
                    this.msRetDueTime = String.valueOf(rsObj.getString("CT03DTIME"));
                    this.msRetTimeCharged = String.valueOf(rsObj.getString("CT03BTIME"));
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

    public boolean RetrieveCirculationDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT * FROM CICIRC WHERE CI02DOCNO = '" + msAccessionNo.trim() + "' And CI02PATR='" + msPatronID + "'AND CI02FLAG='C'";
        System.out.println("From RetrieveCirculation sql");
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    this.circDDate = String.valueOf(rsObj.getString("CI02DDATE"));
                    this.circDTime = String.valueOf(rsObj.getString("CI02DTIME"));
                    this.circCounter = rsObj.getInt("CI02COUNTER");
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
        String sql = "SELECT Count(*) FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "'";
        try {
            try {
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

    public boolean ExecuteDischarge(String msAccessionNo, String msPatronID) throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msRawDateReturned = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeReturned = GeneralUtility.TimetoStr(currentTime);
        boolean validacc = this.validAccession(msAccessionNo);
        String msOfficerID = "SYSADMIN";
        this.RetrieveAccessionDetail(msAccessionNo);
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        double fines = 0.0;
        boolean bSucessful = false;
        boolean reservationstatus = false;
        boolean reserved = false;
        if (validacc) {
            if (this.validAccession) {
                if (this.msRawDateReturned != null) {
                    this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
                    BigDecimal bigDecimal = this.calculatefines2(msPatronID);
                } else {
                    this.errmessage = "071";
                    this.printMessage = "The item has has not been discharged";
                    System.out.println("The item has not been discharged");
                }
            } else {
                this.errmessage = "046";
                this.printMessage = "The item has has not been discharged";
                System.out.println("The item has not been discharged");
            }
        } else {
            this.errmessage = "020";
            this.printMessage = "The item has has not been discharged";
            System.out.println("The item has not been discharged");
        }
        return bSucessful;
    }

    public BigDecimal printSlip(String msAccessionNo, String msPatronID) throws SQLException {
        System.out.println("1");
        System.out.println("2");
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        System.out.println("3" + this.msRetDueDate + this.msRetLocation);
        BigDecimal msFines = this.calculatefines2(msPatronID);
        System.out.println("4");
        System.out.println(msFines);
        return msFines;
    }

    public boolean chkOverdue(String msPatronID, String msRawDateReturned, String msAccessionNo) throws SQLException {
        String sql = "SELECT Count(*) AS COUNT FROM RETRXN WHERE RE01PATR='" + msPatronID + "' AND RE01DOCNO='" + msAccessionNo + "' AND RE01REFER LIKE '" + this.circDDate + "%'";
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    if (Integer.parseInt(rsObj.getString("Count")) <= 0) continue;
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

    public boolean discharge_ori(String msAccessionNo, String msPatronID, String username) throws SQLException {
        System.out.println("Discharging.discharge() msAccessionNo: " + msAccessionNo + " msPatronID: " + msPatronID + " username: " + username);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msRawDateReturned = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeReturned = GeneralUtility.TimetoStr(currentTime);
        boolean validacc = this.RetrieveAccessionDetail(msAccessionNo);
        String msOfficerID = username;
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        double fines = 0.0;
        boolean bSucessful = false;
        boolean reservationstatus = false;
        boolean reserved = false;
        try {
            this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
            reservationstatus = Item.checkReservationStatus(this.msRetMatNo);
            BigDecimal msFines = this.calculatefines2(msPatronID);
            System.out.println("MsFines " + reservationstatus);
            if (reservationstatus) {
                this.UpdateReservation = this.reservationupdate(this.msRetMatNo, msAccessionNo, this.msRawDateReturned, msOfficerID);
                if (this.UpdateReservation) {
                    this.msRetStatus = "H";
                    System.out.println("Reservation Status Updated");
                    reserved = true;
                } else {
                    this.msRetStatus = "A";
                    System.out.println("Reservation Status Not updated");
                }
            } else {
                this.msRetStatus = "A";
                System.out.println("Reservation Status Not updated");
            }
            if (this.msRetStatus.equals("H")) {
                this.UpdateAccessionDetail = this.DischargeResvItem(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus);
                if (this.UpdateAccessionDetail) {
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                } else {
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                }
            } else {
                this.UpdateAccessionDetail = this.DischargeAccessionStatus(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus);
                if (this.UpdateAccessionDetail) {
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                } else {
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                }
            }
            if (bSucessful) {
                this.UpdateCirculationDetails = this.UpdateCirculationDetails(this.msRawDateReturned, this.msRawTimeReturned, msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo);
                System.out.println("Circulation Details updated");
                if (msFines.signum() != 0) {
                    System.out.println("Writing transaction");
                    if (this.chkOverdue(msPatronID, this.msRawDateReturned, msAccessionNo)) {
                        this.updateOverdueFines(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                    } else {
                        this.UpdateFinesTransaction(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                    }
                }
            }
            if (this.UpdateCirculationDetails) {
                this.UpdatePatronDetails = this.UpdatePatronLastReturn(msPatronID, this.msRawDateReturned);
                bSucessful = true;
            } else {
                System.out.println("Circulation Details not updated");
                bSucessful = false;
            }
            if (this.UpdatePatronDetails) {
                bSucessful = true;
                this.printMessage = "The item has been successfully discharged";
                System.out.println("Patron Details updated");
            } else {
                bSucessful = false;
                System.out.println("Patron Details Not updated");
            }
            if (reserved && msFines != null && msFines.compareTo(BigDecimal.ZERO) != 0) {
                this.errmessage = "resvFine," + System.lineSeparator() + "Patron Name: " + this.msResvPatrName + System.lineSeparator() + "Patron ID : " + this.msResvPatrId + System.lineSeparator() + "Pickup Branch :" + this.msResvPickup;
            } else if (msFines != null && msFines.compareTo(BigDecimal.ZERO) != 0) {
                System.out.println("Rservarf forfd");
                this.errmessage = "074";
            } else if (reserved) {
                this.errmessage = "064," + System.lineSeparator() + "Patron Name: " + this.msResvPatrName + System.lineSeparator() + "Patron ID : " + this.msResvPatrId + System.lineSeparator() + "Pickup Branch :" + this.msResvPickup;
            }
            if (bSucessful && ILL.chkILLModExist() && ILL.getchkILLDischargeAcc(msAccessionNo)) {
                bSucessful = ILL.updateDischargingStat(msAccessionNo);
            }
            AuditTrail.InsertAudit("CI", "CII0002", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (UnknownHostException | SQLException e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean discharge(String msAccessionNo, String msPatronID, String username) throws SQLException, UnknownHostException {
        System.out.println("Discharging.discharge() msAccessionNo: " + msAccessionNo + " msPatronID: " + msPatronID + " username: " + username);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msRawDateReturned = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeReturned = GeneralUtility.TimetoStr(currentTime);
        boolean validacc = this.RetrieveAccessionDetail(msAccessionNo);
        String msOfficerID = username;
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        double fines = 0.0;
        boolean bSucessful = false;
        boolean reservationstatus = false;
        boolean reserved = false;
        try {
            this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
            reservationstatus = Item.checkReservationStatus(this.msRetMatNo);
            BigDecimal msFines = this.calculatefines2(msPatronID);
            System.out.println("MsFines " + reservationstatus);
            if (reservationstatus) {
                this.UpdateReservation = this.reservationupdate(this.msRetMatNo, msAccessionNo, this.msRawDateReturned, msOfficerID);
                if (this.UpdateReservation) {
                    this.msRetStatus = "H";
                    System.out.println("Reservation Status Updated");
                    reserved = true;
                } else {
                    this.msRetStatus = "A";
                    System.out.println("Reservation Status Not updated");
                }
            } else {
                this.msRetStatus = "A";
                System.out.println("Reservation Status Not updated");
            }
            if (this.msRetStatus.equals("H")) {
                this.UpdateAccessionDetail = this.DischargeResvItem(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus);
                if (this.UpdateAccessionDetail) {
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                } else {
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                }
            } else {
                this.UpdateAccessionDetail = this.DischargeAccessionStatus(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus);
                if (this.UpdateAccessionDetail) {
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                } else {
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                }
            }
            if (bSucessful) {
                this.UpdateCirculationDetails = this.UpdateCirculationDetails(this.msRawDateReturned, this.msRawTimeReturned, msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo);
                if (msFines.signum() != 0) {
                    System.out.println("Writing transaction");
                    if (this.chkOverdue(msPatronID, this.msRawDateReturned, msAccessionNo)) {
                        this.updateOverdueFines(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                    } else {
                        this.UpdateFinesTransaction(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                    }
                }
            }
            if (this.UpdateCirculationDetails) {
                this.UpdatePatronDetails = this.UpdatePatronLastReturn(msPatronID, this.msRawDateReturned);
                bSucessful = true;
            } else {
                System.out.println("Circulation Details not updated");
                bSucessful = false;
            }
            if (this.UpdatePatronDetails) {
                bSucessful = true;
                this.printMessage = "The item has been successfully discharged";
                System.out.println("Patron Details updated");
            } else {
                bSucessful = false;
                System.out.println("Patron Details Not updated");
            }
            if (reserved && msFines != null && msFines.compareTo(BigDecimal.ZERO) != 0) {
                this.errmessage = "resvFine," + System.lineSeparator() + "Patron Name: " + this.msResvPatrName + System.lineSeparator() + "Patron ID : " + this.msResvPatrId + System.lineSeparator() + "Pickup Branch :" + this.msResvPickup;
            } else if (msFines != null && msFines.compareTo(BigDecimal.ZERO) != 0) {
                System.out.println("Rservarf forfd");
                this.errmessage = "074";
            } else if (reserved) {
                this.errmessage = "064," + System.lineSeparator() + "Patron Name: " + this.msResvPatrName + System.lineSeparator() + "Patron ID : " + this.msResvPatrId + System.lineSeparator() + "Pickup Branch :" + this.msResvPickup;
            }
            if (bSucessful && ILL.chkILLModExist() && ILL.getchkILLDischargeAcc(msAccessionNo)) {
                bSucessful = ILL.updateDischargingStat(msAccessionNo);
            }
            AuditTrail.InsertAudit("CI", "CII0002", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean CanPatronDischarge(String GL14PATR) {
        boolean validate = false;
        try {
            stmt = conn.createStatement();
            String sql = "Select GL08ACTION2 From GLPATR, GLSTAT Where GL14STAT=GL08STAT and GL14PATR = '" + GL14PATR + "'";
            ResultSet rsObj = stmt.executeQuery(sql);
            System.out.println(sql);
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

    public boolean RetrieveAccessionDetail(String msAccessionNo) {
        boolean exist = false;
        try {
            try {
                String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo + "'" + "AND CT03LOCA = GL05LOCA";
                System.out.println(sql);
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
                    this.msRetDueDate = String.valueOf(rsObj.getString("CT03DDATE"));
                    System.out.println(rsObj.getString("CT03LOCA"));
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

    public boolean reservationupdate(String msRetMatNo, String msAccessionNo, String msRawDateReturned, String msOfficerID) {
        boolean success = false;
        String CI03DOCNO = "";
        DBConnection db = new DBConnection();
        try {
            String sql = null;
            if (DBConnection.getDBType().equals("oracle")) {
                sql = "select * from ciresv, glpatr, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X' AND CI03BRNC=GL71BRNC AND ROWNUM =1 order by CI03PRIOR";
            } else if (DBConnection.getDBType().equals("mssql")) {
                sql = "select top 1 * from ciresv, glpatr, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X' AND CI03BRNC=GL71BRNC order by CI03PRIOR";
            } else if (DBConnection.getDBType().equals("mysql")) {
                sql = "select * from ciresv, glpatr, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X' AND CI03BRNC=GL71BRNC order by CI03PRIOR limit 1";
            }
            System.out.println(sql);
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                CI03DOCNO = rsObj.getString("CI03DOCNO");
                this.msResvPatrName = rsObj.getString("GL14NAME");
                this.msResvPatrId = rsObj.getString("GL14PATR");
                this.msResvPickup = rsObj.getString("GL71DESC");
            }
            if (CI03DOCNO == null || CI03DOCNO.trim().isEmpty() || CI03DOCNO.equals(msAccessionNo)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(5, 7);
                String output = sdf.format(c.getTime());
                System.out.println(output);
                String sql2 = "";
                sql2 = DBConnection.getDBType().equals("mysql") ? " Update CIRESV Set CI03NSTAT ='A',CI03NDATE = '" + msRawDateReturned.trim() + "' ," + "CI03DOCNO = '" + msAccessionNo.trim() + "' ," + "CI03OFFIC = '" + msOfficerID + "', " + "CI03DDATE = '" + output + "' WHERE " + "CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X' AND CI03PRIOR=(SELECT MIN(CI03PRIOR) FROM (SELECT * FROM CIRESV) AS resv WHERE CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X')" : " Update CIRESV Set CI03NSTAT ='A',CI03NDATE = '" + msRawDateReturned.trim() + "' ," + "CI03DOCNO = '" + msAccessionNo.trim() + "' ," + "CI03OFFIC = '" + msOfficerID + "', " + "CI03DDATE = '" + output + "' WHERE " + "CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X' AND CI03PRIOR=(SELECT MIN(CI03PRIOR) FROM CIRESV WHERE CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X')";
                System.out.println(sql2);
                int count = stmt.executeUpdate(sql2);
                System.out.println("R" + count);
                if (count > 0) {
                    success = true;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean UpdateCirculationDetails(String msRawDateReturned, String msRawTimeReturned, String msOfficerID, String msPatronID, String msAccessionNo, String msRetDocNo) {
        boolean success = false;
        try {
            try {
                String sSQLStmt1 = " Update CICIRC Set CI02FLAG ='D',CI02DIDATE = '" + msRawDateReturned.trim() + "' ," + "CI02DITIME = '" + msRawTimeReturned.trim() + "' ," + "CI02DIOFFI = '" + msOfficerID + "' " + "Where CI02PATR = '" + msPatronID.trim() + "' " + "And CI02DOCNO = '" + msAccessionNo.trim() + "'  " + "And CI02FLAG = 'C'";
                System.out.println(sSQLStmt1);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQLStmt1);
                System.out.println("C" + count);
                if (count > 0) {
                    success = true;
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
        return success;
    }

    public boolean UpdateCIINCO(String msAccessionNo, String msMaterialNo, String msPatronID) throws SQLException {
        boolean success = false;
        String SQLStmt2 = "UPDATE CIINCO SET CI01STAT = 'R'WHERE CI01MATNO = '" + msAccessionNo.trim() + "' " + "AND CI01ACCESSION = '" + msMaterialNo + "' " + "AND CI01REQUESTOR = '" + msPatronID.trim() + "'";
        int count = stmt.executeUpdate(SQLStmt2);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    public boolean UpdatePatronLastReturn(String msPatronID, String msRawDateReturned) throws SQLException {
        boolean success = false;
        String sSQlStmt3 = "Update GLPATR Set GL14LASTRET = '" + msRawDateReturned + "' " + "Where GL14PATR = '" + msPatronID + "'";
        System.out.println(sSQlStmt3);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQlStmt3);
                if (count > 0) {
                    success = true;
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
        return success;
    }

    public boolean updateOverdueFines(String msPatronID, String msRawDateReturned, BigDecimal msFines, String msOfficerID, String msAccessionNo) {
        boolean success = false;
        String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
        String sSQlStmt4 = "Update RETRXN Set RE01DATE = '" + msRawDateReturned + "', RE01AMT = '" + msFines + "', " + "RE01REFER='" + RE01REFER + "'" + "WHERE RE01PATR='" + msPatronID + "' AND RE01DOCNO='" + msAccessionNo + "' AND RE01REFER LIKE '" + this.circDDate + "%'";
        System.out.println(sSQlStmt4);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQlStmt4);
                if (count > 0) {
                    success = true;
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
        return success;
    }

    public boolean UpdateFinesTransaction(String msPatronID, String msRawDateReturned, BigDecimal msFines, String msOfficerID, String msAccessionNo) throws SQLException {
        boolean success = false;
        String msCode = "100";
        Glnumb iCounter = Glnumb.getGL98VALUE("TRXNO");
        String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
        String sSQlStmt3 = "Insert Into RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) values(" + String.valueOf(iCounter.getGL98VALUE()) + "," + "'" + msCode + "'" + "," + "'" + msRawDateReturned + "'" + "," + msFines + "," + "0," + "0," + "'" + msAccessionNo.trim() + "'" + "," + "'" + msPatronID + "'" + "," + "'" + RE01REFER + "'" + "," + "'" + msOfficerID + "'" + ",null" + "," + "'" + msAccessionNo.trim() + "'" + ",null," + "null," + "null," + "'" + this.circCounter + "'" + ",null" + ")";
        System.out.println(sSQlStmt3);
        boolean chkIfIMSExist = IMS.chkIfExistIMS();
        if (chkIfIMSExist) {
            IMS.IMS_Fines(msAccessionNo.trim(), RE01REFER, msPatronID, msFines, "0", msRawDateReturned, msCode, "N");
        }
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQlStmt3);
                success = true;
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
        return success;
    }

    public boolean DischargeResvItem(String msAccessionNo, String msRawDateReturned, String msPatronID, String msStatus) throws SQLException {
        boolean success = false;
        String sSQlStmt4 = "Update CTDOCM Set CT03BDATE = NULL, CT03BTIME = NULL, CT03DDATE = NULL, CT03DTIME = NULL, CT03STAT = '" + msStatus + "', " + "CT03PATR = (SELECT CI03PATR FROM CIRESV where CI03DOCNO='" + msAccessionNo + "' and CI03PRIOR=1), " + "CT03LASTACT = '" + msRawDateReturned.trim() + "', " + "CT03LASTID = '" + msPatronID + "' " + "Where CT03DOCNO = '" + msAccessionNo.trim() + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
        System.out.println(sSQlStmt4);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                int count = stmt.executeUpdate(sSQlStmt4);
                if (count > 0) {
                    success = true;
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
        return success;
    }

    public boolean DischargeAccessionStatus(String msAccessionNo, String msRawDateReturned, String msPatronID, String msStatus) throws SQLException {
        boolean success = false;
        try {
            try {
                String sSQlStmt4 = "Update CTDOCM Set CT03BDATE = NULL, CT03BTIME = NULL, CT03DDATE = NULL, CT03DTIME = NULL, CT03STAT = '" + msStatus + "', " + "CT03PATR = NULL, " + "CT03LASTACT = '" + msRawDateReturned.trim() + "', " + "CT03LASTID = '" + msPatronID + "' " + "Where CT03DOCNO = '" + msAccessionNo.trim() + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
                System.out.println(sSQlStmt4);
                conn = DBConnection.getConnection();
                conn.setAutoCommit(false);
                stmt = conn.createStatement(1005, 1008);
                int count = stmt.executeUpdate(sSQlStmt4);
                if (count > 0) {
                    success = true;
                    conn.commit();
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
        return success;
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

    public int getStopDay(int startDay) {
        String sql = "SELECT GL38STOP FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' and GL38START= '" + startDay + "'";
        System.out.println(sql);
        int stopDay = 0;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql);
                while (rsObj.next()) {
                    stopDay = rsObj.getString("GL38STOP") == null ? 0 : Integer.parseInt(rsObj.getString("GL38STOP"));
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
        return stopDay;
    }

    public BigDecimal calculatefines(String msPatronID) {
        BigDecimal fines = BigDecimal.ZERO;
        int holidaycount = 0;
        int trxno = 0;
        BigDecimal maxfine = BigDecimal.ZERO;
        BigDecimal finerate = BigDecimal.ZERO;
        System.out.println(this.circDDate);
        String datecharged = DateTime.formatDatelocal(this.circDDate);
        String datereturned = DateTime.getTodayDate();
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        LocalDate dateStart = new LocalDate((Object)datecharged);
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Date Start" + dateStart);
        System.out.println("Date end" + dateEnd);
        System.out.println("Number of days" + days);
        String CI02DATE = "";
        if (days < 0) {
            this.errmessage = "021";
        }
        if (days > 0) {
            try {
                String sql1 = "select * from CICIRC where CI02PATR='" + msPatronID + "'";
                ResultSet rsObj = stmt.executeQuery(sql1);
                while (rsObj.next()) {
                    CI02DATE = rsObj.getString("CI02DDATE");
                }
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + this.circDDate + "'AND '" + datereturned + "'";
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                System.out.println("C" + holidaycount);
                String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
                System.out.println(sql3);
                rsObj = stmt.executeQuery(sql3);
                while (rsObj.next()) {
                    finerate = rsObj.getBigDecimal("GL38RATE");
                }
                String sql4 = "SELECT  GL98VALUE from GLNUMB where GL98FUNC= 'TRXNO'";
                System.out.println(sql4);
                rsObj = stmt.executeQuery(sql4);
                while (rsObj.next()) {
                    trxno = rsObj.getInt("GL98VALUE");
                }
                String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetItemBranch + "'";
                System.out.println(sql5);
                rsObj = stmt.executeQuery(sql5);
                while (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL27MAXFINE");
                }
                if (maxfine == null) {
                    maxfine = BigDecimal.ZERO;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            this.latedays = days - holidaycount;
            System.out.println("TRXNO" + trxno++);
            System.out.println("finerate" + finerate);
            String RE01REFER = String.valueOf(DateTime.splitDate(this.circDDate)) + DateTime.formatTime(this.circDTime) + ":" + this.latedays;
            System.out.println("Late Days" + this.latedays);
            System.out.println("REFERENCE" + RE01REFER);
            fines = finerate.multiply(new BigDecimal(this.latedays));
            double totalfines = 0.0;
            System.out.println("MaxFines" + maxfine);
            System.out.println("Fines" + fines);
            if (maxfine.compareTo(BigDecimal.ZERO) == 0) {
                this.errmessage = "021";
            } else if (maxfine.compareTo(fines) < 0) {
                fines = maxfine;
            }
            System.out.println("Fines" + fines);
        }
        return fines;
    }

    public BigDecimal calculatefines2(String msPatronID) throws SQLException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal fineRates = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        BigDecimal maxTotal = this.maxfine(msPatronID);
        this.latedays = this.calculateDays(this.msRetDueDate, this.msRetItemBranch);
        System.out.println("11111" + this.latedays);
        try {
            if (this.latedays <= 0) {
                System.out.println("111133" + this.latedays);
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                System.out.println("InitialRate" + fineRate);
                maxfine = this.MaxFine(msPatronID);
                fineRates = this.fineRate(msPatronID, this.latedays);
                int startDay = 1;
                int stopDay = this.getStopDay(startDay);
                System.out.println("Day22222" + fineRates);
                fines = maxfine.compareTo(BigDecimal.ZERO) == 0 ? fineRates : (fineRates.compareTo(maxfine) > 0 ? maxfine : fineRates);
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

    public BigDecimal MaxFine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        try {
            try {
                this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                String sql = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "'" + "AND GL27ICAT = '" + this.msRetItemCat + "'" + "AND GL27SMD = '" + this.msRetSMD + "'" + "AND GL27BRNC = '" + this.msRetItemBranch + "'";
                System.out.println(sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                System.out.println("MaxFine");
                if (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL27MAXFINE");
                    System.out.println("GL27MAXFINE" + maxfine);
                }
                if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                    String sql6 = "Select * from GLCATE WHERE GL07CATE = '" + this.msPatronCategory + "'";
                    System.out.println(sql6);
                    rsObj = stmt.executeQuery(sql6);
                    while (rsObj.next()) {
                        maxfine = rsObj.getBigDecimal("GL07MAXFINE");
                        System.out.println("GL07MAXFINE" + maxfine);
                    }
                    if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                        maxfine = BigDecimal.ZERO;
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
        return maxfine;
    }

    public BigDecimal maxfine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        DBConnection db = new DBConnection();
        try {
            try {
                System.out.println("database1" + DBConnection.getDBType());
                this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                String sql = null;
                if (DBConnection.getDBType().equals("oracle")) {
                    sql = "SELECT GL38FIRST from GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' AND ROWNUM=1 ORDER BY GL38FIRST DESC";
                } else if (DBConnection.getDBType().equals("mssql")) {
                    sql = "SELECT TOP 1 GL38FIRST from GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' ORDER BY GL38FIRST DESC";
                } else if (DBConnection.getDBType().equals("mysql")) {
                    sql = "SELECT GL38FIRST from GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' ORDER BY GL38FIRST DESC LIMIT 1";
                }
                System.out.println("database" + sql);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                ResultSet rsObj = stmt.executeQuery(sql);
                if (rsObj.next()) {
                    maxfine = rsObj.getBigDecimal("GL38FIRST");
                    System.out.println("MaxFines" + maxfine);
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

    public int getLateBy() {
        return this.latedays;
    }

    private String getPtype(String cate, String icat) {
        String ptype = "";
        try {
            try {
                String sql2 = "SELECT GL27PTYPE FROM GLELIG WHERE GL27CATE ='" + this.msPatronCategory + "' and GL27ICAT='" + this.msRetItemCat + "' and GL27SMD='" + this.msRetSMD + "' and GL27BRNC='" + this.msRetItemBranch + "'";
                System.out.println("Test" + sql2);
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    ptype = rsObj.getString("GL27PTYPE");
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
        return ptype;
    }

    public int prcCalTime(String msDocTime, String msDocDate, String msBranch) throws SQLException {
        System.out.println("sd" + msDocDate);
        String timecharged = DateTime.formatTime(msDocDate);
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String timereturned = DateTime.getToday24Time();
        LocalDateTime dateStart = new LocalDateTime((Object)(String.valueOf(datecharged) + " " + datecharged));
        LocalDateTime dateEnd = LocalDateTime.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        int holidaycount = 0;
        if (days <= 0) {
            System.out.println("LateDays 0");
            this.latedays = 0;
        } else {
            block14: {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + timereturned + "' ";
                try {
                    try {
                        conn = DBConnection.getConnection();
                        stmt = conn.createStatement();
                        rsObj = stmt.executeQuery(sql2);
                        while (rsObj.next()) {
                            holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
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
                        break block14;
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
            this.latedays = days - holidaycount;
        }
        return this.latedays;
    }

    public int calculateDays(String msDocDate, String msBranch) throws SQLException {
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String datereturned = DateTime.getTodayDate();
        LocalDate dateStart = new LocalDate((Object)datecharged);
        System.out.println("Date" + datecharged + datereturned + "www");
        LocalDate dateEnd = LocalDate.now();
        System.out.println("Date" + dateStart + dateEnd + "www");
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Days count" + days);
        int holidaycount = 0;
        if (days <= 0) {
            System.out.println("LateDays 0");
            this.latedays = 0;
        } else {
            block14: {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + datereturned + "' ";
                try {
                    try {
                        conn = DBConnection.getConnection();
                        stmt = conn.createStatement();
                        rsObj = stmt.executeQuery(sql2);
                        while (rsObj.next()) {
                            holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
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
                        break block14;
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
            this.latedays = days - holidaycount;
        }
        return this.latedays;
    }

    public BigDecimal fineRate(String msPatronID) {
        BigDecimal finerate = BigDecimal.ZERO;
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        try {
            try {
                String sql3 = "SELECT GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                System.out.println(sql3);
                ResultSet rsObj = stmt.executeQuery(sql3);
                int count = 0;
                while (rsObj.next()) {
                    finerate = rsObj.getBigDecimal("GL38RATE");
                    System.out.println("" + finerate + ++count);
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
        return finerate;
    }

    public BigDecimal fineRate(String msPatronID, int stopDay) {
        BigDecimal finerate;
        block18: {
            String sql3 = null;
            sql3 = "SELECT  GL38START, GL38STOP, GL38RATE, gl38first FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
            double rate = 0.0;
            finerate = BigDecimal.ZERO;
            try {
                try {
                    conn = DBConnection.getConnection();
                    stmt = conn.createStatement();
                    System.out.println(sql3);
                    Renewal test = new Renewal();
                    int row = test.rowcount(this.msPatronCategory, this.msRetItemCat, this.msRetSMD, this.msRetItemBranch);
                    ResultSet rsObj = stmt.executeQuery(sql3);
                    while (rsObj.next()) {
                        System.out.println("Stop" + rsObj.getString("GL38START"));
                        if (stopDay > 0) {
                            BigDecimal GL38RATE;
                            System.out.println("Stop1" + rsObj.getString("GL38START"));
                            if (rsObj.getString("GL38STOP") != null) {
                                if (stopDay >= Integer.parseInt(rsObj.getString("GL38START")) && stopDay <= Integer.parseInt(rsObj.getString("GL38STOP"))) {
                                    System.out.println("In between" + rsObj.getString("GL38START"));
                                    GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                                    int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                                    BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                                    finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                                    System.out.println("Rate" + rateValue);
                                    stopDay = 0;
                                } else if (stopDay >= Integer.parseInt(rsObj.getString("GL38STOP")) && row == 1) {
                                    System.out.println("In between" + rsObj.getString("GL38START"));
                                    GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                                    int noOfDays = 0;
                                    noOfDays = Integer.parseInt(rsObj.getString("GL38START")) > 1 ? Integer.parseInt(rsObj.getString("GL38STOP")) - Integer.parseInt(rsObj.getString("GL38START")) + 1 : Integer.parseInt(rsObj.getString("GL38STOP"));
                                    BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                                    finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                                    System.out.println("Rate" + rateValue);
                                    stopDay = 0;
                                }
                            } else {
                                System.out.println("its null4" + rsObj.getString("GL38START") + rsObj.getString("GL38FIRST"));
                                GL38RATE = new BigDecimal(rsObj.getString("GL38RATE"));
                                BigDecimal GL38FIRST = new BigDecimal(rsObj.getString("GL38FIRST"));
                                int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                                System.out.println("its null3" + noOfDays);
                                BigDecimal rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                                System.out.println("its null2" + GL38FIRST);
                                finerate = rateValue.add(new BigDecimal(rsObj.getString("GL38FIRST")));
                                System.out.println("its null1" + rateValue);
                                System.out.println("its nulls" + rate);
                                stopDay = 0;
                            }
                            System.out.println("Overall" + finerate);
                            continue;
                        }
                        break;
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
                    break block18;
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
        System.out.println("Overall" + finerate);
        return finerate;
    }

    public static int getTotalRow(String msPatronCategory, String msRetItemCat, String msRetSMD, String msRetItemBranch) {
        String sql = "SELECT Count(*) as Count FROM GLFINE WHERE GL38CATE = '" + msPatronCategory + "'" + "AND GL38ICAT = '" + msRetItemCat + "'" + "AND GL38SMD = '" + msRetSMD + "'" + "AND GL38BRNC = '" + msRetItemBranch + "'";
        int deletable = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                deletable = Integer.parseInt(rsObj.getString("Count"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return deletable;
    }

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }
}
