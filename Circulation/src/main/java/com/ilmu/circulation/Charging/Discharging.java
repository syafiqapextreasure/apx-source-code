/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.AccessionDetails;
import com.ilmu.circulation.Charging.AccessionDischarge;
import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.ILL;
import com.ilmu.circulation.Charging.Item;
import com.ilmu.circulation.Charging.Renewal;
import com.ilmu.global.AuditTrail;
import com.ilmu.global.DateTime;
import com.ilmu.global.Glnumb;
import com.ilmu.global.Handler;
import com.ilmu.global.IMS;
import com.ilmu.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.Days;
import org.joda.time.LocalDate;
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
    int totalDiffHours = 0;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    String printMessage;
    int latedays = 0;
    String errmessage = "";

    public Discharging() {
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
        System.out.println("sql");
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
        Object loanType = null;
        AccessionDischarge accessionDischarge = new AccessionDischarge();
        if (validacc) {
            if (this.validAccession) {
                if (this.msRawDateReturned != null) {
                    System.out.println("masuk discharge");
                    this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
                    BigDecimal bigDecimal = this.calculatefines2(msPatronID);
                } else {
                    System.out.println("masuk discharge else");
                    this.errmessage = "081";
                    this.printMessage = "The item has has not been discharged";
                }
            } else {
                this.errmessage = "081";
                this.printMessage = "Invalid Accession Detail in CTDOCM";
            }
        } else {
            this.errmessage = "020";
            this.printMessage = "Invalid Accession";
        }
        return bSucessful;
    }

    public BigDecimal printSlip(String msAccessionNo, String msPatronID) throws SQLException {
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        BigDecimal msFines = this.calculatefines2(msPatronID);
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

    public boolean discharge(String msAccessionNo, String msPatronID, String username, String dischargeTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String currentdate = dateFormat.format(date).toString();
        date = cal.getTime();
        String currentTime = timeFormat.format(date).toString();
        this.msRawDateReturned = GeneralUtility.DatetoStr(currentdate);
        this.msRawTimeReturned = GeneralUtility.TimetoStr(dischargeTime);
        boolean validacc = this.RetrieveAccessionDetail(msAccessionNo);
        String msOfficerID = username;
        double fines = 0.0;
        boolean bSucessful = false;
        boolean reservationstatus = false;
        boolean reserved = false;
        try {
            this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
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
                this.UpdateAccessionDetail = this.DischargeResvItem(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus, msOfficerID);
                if (this.UpdateAccessionDetail) {
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                    AuditTrail.InsertAudit("CI", "CII0004", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
                } else {
                    reserved = false;
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                    this.errmessage = "191H";
                    AuditTrail.InsertAudit("CI", "CII0004", String.valueOf(msAccessionNo) + ":" + msPatronID + " (FAIL)", username);
                }
            } else {
                this.UpdateAccessionDetail = this.DischargeAccessionStatus(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus, this.msRawTimeReturned, msOfficerID, this.msRetDocNo);
                if (this.UpdateAccessionDetail) {
                    if (msFines.signum() != 0) {
                        System.out.println("Writing transaction");
                        if (this.chkOverdue(msPatronID, this.msRawDateReturned, msAccessionNo)) {
                            this.updateOverdueFines(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                        } else {
                            this.UpdateFinesTransaction(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
                        }
                    }
                    bSucessful = true;
                    System.out.println("CTDOCM updated");
                    AuditTrail.InsertAudit("CI", "CII0002", String.valueOf(msAccessionNo) + ":" + msPatronID, username);
                } else {
                    bSucessful = false;
                    System.out.println("CTDOCM not updated");
                    this.errmessage = "191";
                    AuditTrail.InsertAudit("CI", "CII0002", String.valueOf(msAccessionNo) + ":" + msPatronID + " (FAIL)", username);
                }
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    public boolean CanPatronDischarge(String GL14PATR) {
        boolean validate = false;
        try {
            try {
                conn = DBConnection.getConnection();
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
                try {
                    conn.close();
                    stmt.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                conn.close();
                stmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
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
            if (db.getDBType().equals("oracle")) {
                sql = "select * from ciresv, glpatr, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X' AND CI03BRNC=GL71BRNC AND ROWNUM =1 order by CI03PRIOR";
            } else if (db.getDBType().equals("mssql")) {
                sql = "select top 1 * from ciresv, glpatr, GLLOCA, CTDOCM, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CT03DOCNO = '" + msAccessionNo + "' AND CI03NSTAT='X' AND GL05LOCA=CT03LOCA AND GL05LOCA=GL71BRNC " + "order by CI03PRIOR";
            } else if (db.getDBType().equals("mysql")) {
                sql = "select * from ciresv, glpatr, GLLOCA, CTDOCM, GLBRNC where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CT03DOCNO = '" + msAccessionNo + "' AND CI03NSTAT='X' AND GL05LOCA=CT03LOCA AND GL05LOCA=GL71BRNC " + " order by CI03PRIOR limit 1";
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
                sql2 = db.getDBType().equals("mysql") ? " Update CIRESV Set CI03NSTAT ='A',CI03NDATE = '" + msRawDateReturned.trim() + "' ," + "CI03DOCNO = '" + msAccessionNo.trim() + "' ," + "CI03OFFIC = '" + msOfficerID + "', " + "CI03DDATE = '" + output + "' WHERE " + "CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X' AND CI03PRIOR=(SELECT MIN(CI03PRIOR) FROM (SELECT * FROM CIRESV) AS resv WHERE CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X')" : " Update CIRESV Set CI03NSTAT ='A',CI03NDATE = '" + msRawDateReturned.trim() + "' ," + "CI03DOCNO = '" + msAccessionNo.trim() + "' ," + "CI03OFFIC = '" + msOfficerID + "', " + "CI03DDATE = '" + output + "' WHERE " + "CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X' AND CI03PRIOR=(SELECT MIN(CI03PRIOR) FROM CIRESV WHERE CI03MATNO='" + msRetMatNo.trim() + "' AND CI03NSTAT='X')";
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean DischargeResvItem(String msAccessionNo, String msRawDateReturned, String msPatronID, String msStatus, String msOfficerID) {
        boolean success = false;
        if (msAccessionNo == null) return false;
        if (msAccessionNo.isEmpty()) return false;
        if (msRawDateReturned == null) return false;
        if (msRawDateReturned.isEmpty()) return false;
        if (msPatronID == null) return false;
        if (msPatronID.trim().isEmpty()) return false;
        if (msStatus == null) return false;
        if (msStatus.isEmpty()) return false;
        if (msStatus.equals("C")) return false;
        if (msOfficerID == null) return false;
        if (msOfficerID.isEmpty()) {
            return false;
        }
        String updateCTDOCMQuery = "UPDATE CTDOCM SET CT03BDATE = NULL, CT03BTIME = NULL, CT03DDATE = NULL, CT03DTIME = NULL, CT03STAT = ?, CT03PATR = (SELECT CI03PATR FROM CIRESV WHERE CI03DOCNO=? AND CI03PRIOR=1), CT03LASTACT = ?, CT03LASTID = ? WHERE CT03DOCNO = ? AND (CT03STAT = 'C' OR CT03STAT = 'E')";
        String updateCICIRCQuery = "UPDATE CICIRC SET CI02FLAG ='D', CI02DIDATE = ?, CI02DITIME = ?, CI02DIOFFI = ? WHERE CI02PATR = ? AND CI02DOCNO = ? AND CI02FLAG = 'C'";
        String updateGLPATRQuery = "UPDATE GLPATR SET GL14LASTRET = ? WHERE GL14PATR = ?";
        try {
            Throwable throwable = null;
            Object var11_13 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    block31: {
                        PreparedStatement stmtCTDOCM = conn.prepareStatement(updateCTDOCMQuery);
                        try {
                            block30: {
                                PreparedStatement stmtCICIRC = conn.prepareStatement(updateCICIRCQuery);
                                try {
                                    try (PreparedStatement stmtGLPATR = conn.prepareStatement(updateGLPATRQuery);){
                                        conn.setAutoCommit(false);
                                        stmtCTDOCM.setString(1, msStatus);
                                        stmtCTDOCM.setString(2, msAccessionNo.trim());
                                        stmtCTDOCM.setString(3, msRawDateReturned.trim());
                                        stmtCTDOCM.setString(4, msPatronID.trim());
                                        stmtCTDOCM.setString(5, msAccessionNo.trim());
                                        int rowsUpdatedCTDOCM = stmtCTDOCM.executeUpdate();
                                        stmtCICIRC.setString(1, msRawDateReturned.trim());
                                        stmtCICIRC.setString(2, this.msRawTimeReturned.trim());
                                        stmtCICIRC.setString(3, msOfficerID.trim());
                                        stmtCICIRC.setString(4, msPatronID.trim());
                                        stmtCICIRC.setString(5, msAccessionNo.trim());
                                        int rowsUpdatedCICIRC = stmtCICIRC.executeUpdate();
                                        stmtGLPATR.setString(1, msRawDateReturned);
                                        stmtGLPATR.setString(2, msPatronID.trim());
                                        int rowsUpdatedGLPATR = stmtGLPATR.executeUpdate();
                                        if (rowsUpdatedCTDOCM > 0 && rowsUpdatedCICIRC > 0 && rowsUpdatedGLPATR > 0) {
                                            conn.commit();
                                            success = true;
                                        } else {
                                            conn.rollback();
                                        }
                                    }
                                    if (stmtCICIRC == null) break block30;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (stmtCICIRC == null) throw throwable;
                                    stmtCICIRC.close();
                                    throw throwable;
                                }
                                stmtCICIRC.close();
                            }
                            if (stmtCTDOCM == null) break block31;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (stmtCTDOCM == null) throw throwable;
                            stmtCTDOCM.close();
                            throw throwable;
                        }
                        stmtCTDOCM.close();
                    }
                    if (conn == null) return success;
                }
                catch (Throwable throwable4) {
                    if (throwable == null) {
                        throwable = throwable4;
                    } else if (throwable != throwable4) {
                        throwable.addSuppressed(throwable4);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return success;
            }
            catch (Throwable throwable5) {
                if (throwable == null) {
                    throwable = throwable5;
                    throw throwable;
                }
                if (throwable == throwable5) throw throwable;
                throwable.addSuppressed(throwable5);
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            if (conn == null) return success;
            try {
                conn.rollback();
                return success;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean DischargeAccessionStatus(String msAccessionNo, String msRawDateReturned, String msPatronID, String msStatus, String msRawTimeReturned, String msOfficerID, String msRetDocNo) {
        boolean success = false;
        if (msAccessionNo == null) return false;
        if (msAccessionNo.isEmpty()) return false;
        if (msRawDateReturned == null) return false;
        if (msRawDateReturned.isEmpty()) return false;
        if (msPatronID == null) return false;
        if (msPatronID.trim().isEmpty()) return false;
        if (msStatus == null) return false;
        if (msStatus.isEmpty()) return false;
        if (msStatus.equals("C")) return false;
        if (msRawTimeReturned == null) return false;
        if (msRawTimeReturned.isEmpty()) return false;
        if (msOfficerID == null) return false;
        if (msOfficerID.trim().isEmpty()) return false;
        if (msRetDocNo == null) return false;
        if (msRetDocNo.isEmpty()) {
            return false;
        }
        try {
            Throwable throwable = null;
            Object var10_12 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    block31: {
                        PreparedStatement pstmtCTDOCM = conn.prepareStatement("UPDATE CTDOCM SET CT03BDATE = NULL, CT03BTIME = NULL, CT03DDATE = NULL, CT03DTIME = NULL, CT03STAT = ?, CT03PATR = NULL, CT03LASTACT = ?, CT03LASTID = ? WHERE CT03DOCNO = ? AND (CT03STAT = 'C' OR CT03STAT = 'E')");
                        try {
                            block30: {
                                PreparedStatement pstmtCICIRC = conn.prepareStatement("UPDATE CICIRC SET CI02FLAG ='D', CI02DIDATE = ?, CI02DITIME = ?, CI02DIOFFI = ? WHERE CI02PATR = ? AND CI02DOCNO = ? AND CI02FLAG = 'C'");
                                try {
                                    try (PreparedStatement pstmtGLPATR = conn.prepareStatement("UPDATE GLPATR SET GL14LASTRET = ? WHERE GL14PATR = ?");){
                                        conn.setAutoCommit(false);
                                        pstmtCTDOCM.setString(1, msStatus);
                                        pstmtCTDOCM.setString(2, msRawDateReturned.trim());
                                        pstmtCTDOCM.setString(3, msPatronID.trim());
                                        pstmtCTDOCM.setString(4, msAccessionNo.trim());
                                        pstmtCICIRC.setString(1, msRawDateReturned.trim());
                                        pstmtCICIRC.setString(2, msRawTimeReturned.trim());
                                        pstmtCICIRC.setString(3, msOfficerID.trim());
                                        pstmtCICIRC.setString(4, msPatronID.trim());
                                        pstmtCICIRC.setString(5, msAccessionNo.trim());
                                        pstmtGLPATR.setString(1, msRawDateReturned);
                                        pstmtGLPATR.setString(2, msPatronID.trim());
                                        int rowsUpdatedCTDOCM = pstmtCTDOCM.executeUpdate();
                                        int rowsUpdatedCICIRC = pstmtCICIRC.executeUpdate();
                                        int rowsUpdatedGLPATR = pstmtGLPATR.executeUpdate();
                                        if (rowsUpdatedCTDOCM > 0 && rowsUpdatedCICIRC > 0 && rowsUpdatedGLPATR > 0) {
                                            conn.commit();
                                            success = true;
                                        } else {
                                            conn.rollback();
                                        }
                                    }
                                    if (pstmtCICIRC == null) break block30;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (pstmtCICIRC == null) throw throwable;
                                    pstmtCICIRC.close();
                                    throw throwable;
                                }
                                pstmtCICIRC.close();
                            }
                            if (pstmtCTDOCM == null) break block31;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (pstmtCTDOCM == null) throw throwable;
                            pstmtCTDOCM.close();
                            throw throwable;
                        }
                        pstmtCTDOCM.close();
                    }
                    if (conn == null) return success;
                }
                catch (Throwable throwable4) {
                    if (throwable == null) {
                        throwable = throwable4;
                    } else if (throwable != throwable4) {
                        throwable.addSuppressed(throwable4);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return success;
            }
            catch (Throwable throwable5) {
                if (throwable == null) {
                    throwable = throwable5;
                    throw throwable;
                }
                if (throwable == throwable5) throw throwable;
                throwable.addSuppressed(throwable5);
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            if (conn == null) return success;
            try {
                conn.rollback();
                return success;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
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
        System.out.println("maxTotal: " + maxTotal);
        this.latedays = this.calculateDays(this.msRetDueDate, this.msRetItemBranch);
        System.out.println("latedays SUB  " + this.latedays);
        try {
            if (this.latedays <= 0) {
                System.out.println("MASUK A");
                fines = BigDecimal.ZERO;
            } else {
                System.out.println("MASUK B " + this.latedays);
                fineRate = this.fineRate(msPatronID);
                System.out.println("fine Rate: " + fineRate);
                maxfine = this.MaxFine(msPatronID);
                fineRates = this.fineRate(msPatronID, this.latedays);
                int startDay = 1;
                int stopDay = this.getStopDay(startDay);
                System.out.println("fineRates SINI KE? " + fineRates);
                fines = maxfine.compareTo(BigDecimal.ZERO) == 0 ? fineRates : (fineRates.compareTo(maxfine) > 0 ? maxfine : fineRates);
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

    public BigDecimal calculateFineByHours(String msAccessionNo, String msPatronID) throws SQLException, ParseException {
        int totalHoliday = 0;
        AccessionDetails accessionDetail = new AccessionDetails(msAccessionNo, msPatronID);
        this.msRetItemCat = accessionDetail.getMsRetItemCat();
        this.msRetSMD = accessionDetail.getMsRetSMD();
        this.msRetItemBranch = accessionDetail.getMsRetItemBranch();
        this.msRetDueDate = accessionDetail.getMsRetDueDate();
        this.msRetDueTime = accessionDetail.getMsRetDueTime();
        Date dueDate = new SimpleDateFormat("yyyyMMddhhmmss").parse(String.valueOf(this.msRetDueDate) + this.msRetDueTime);
        Date dueTime = new SimpleDateFormat("hhmmss").parse(this.msRetDueTime);
        Date todayDate = new Date();
        this.latedays = totalHoliday = this.checkHolidayInHour(todayDate, dueDate, this.msRetItemBranch);
        System.out.println("latedays: " + this.latedays);
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal fineRates = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        BigDecimal maxTotal = this.maxfine(msPatronID);
        try {
            if (this.latedays <= 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                maxfine = this.MaxFine(msPatronID);
                fineRates = this.fineRate(msPatronID, this.latedays);
                int startDay = 1;
                int stopDay = this.getStopDay(startDay);
                fines = maxfine.compareTo(BigDecimal.ZERO) == 0 ? fineRates : (fineRates.compareTo(maxfine) > 0 ? maxfine : fineRates);
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
                System.out.println("database1" + db.getDBType());
                this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                String sql = null;
                if (db.getDBType().equals("oracle")) {
                    sql = "SELECT GL38FIRST from GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' AND ROWNUM=1 ORDER BY GL38FIRST DESC";
                } else if (db.getDBType().equals("mssql")) {
                    sql = "SELECT TOP 1 GL38FIRST from GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "' ORDER BY GL38FIRST DESC";
                } else if (db.getDBType().equals("mysql")) {
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

    public int calculateDays(String msDocDate, String msBranch) throws SQLException {
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String datereturned = DateTime.getTodayDate();
        LocalDate dateStart = new LocalDate((Object)datecharged);
        System.out.println("Date" + datecharged + datereturned + "www");
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        int holidaycount = 0;
        System.out.println("days  " + days);
        if (days <= 0) {
            this.latedays = 0;
        } else {
            block14: {
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30BRNC = '" + msBranch + "' and GL30DATE BETWEEN '" + msDocDate + "'AND '" + datereturned + "' ";
                System.out.println("SQL BAWAH DATE  " + sql2);
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
            System.out.println("latedays  " + this.latedays);
        }
        return this.latedays;
    }

    public int calculateHours(String msDocDate, String msBranch) throws SQLException {
        String datecharged = DateTime.formatDatelocal(msDocDate);
        String datereturned = DateTime.getTodayDate();
        LocalDate dateStart = new LocalDate((Object)datecharged);
        System.out.println(String.valueOf(datecharged) + datereturned + "www");
        LocalDate dateEnd = LocalDate.now();
        Days d = Days.daysBetween((ReadablePartial)dateStart, (ReadablePartial)dateEnd);
        int days = d.getDays();
        System.out.println("Days count: " + days);
        int holidaycount = 0;
        if (days <= 0) {
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
                    ++count;
                    finerate = rsObj.getBigDecimal("GL38RATE");
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
        String sql3 = null;
        sql3 = "SELECT  GL38START, GL38STOP, GL38RATE, gl38first FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetItemBranch + "'";
        System.out.println("sql3 line 1762" + sql3);
        double rate = 0.0;
        BigDecimal finerate = BigDecimal.ZERO;
        BigDecimal GL38FIRST = BigDecimal.ZERO;
        BigDecimal GL38RATE = BigDecimal.ZERO;
        BigDecimal totalGL38FIRST = BigDecimal.ZERO;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                Renewal test = new Renewal();
                int row = test.rowcount(this.msPatronCategory, this.msRetItemCat, this.msRetSMD, this.msRetItemBranch);
                ResultSet rsObj = stmt.executeQuery(sql3);
                while (rsObj.next()) {
                    BigDecimal rateValue;
                    System.out.println("STOP !!!" + rsObj.getString("GL38STOP"));
                    System.out.println("STRAT !!! " + rsObj.getString("GL38START"));
                    System.out.println("stopDay line 1780 " + row);
                    System.out.println("stopDay line 1781 " + stopDay);
                    if (rsObj.getString("GL38FIRST") != null) {
                        GL38FIRST = new BigDecimal(rsObj.getString("GL38FIRST"));
                        String gl38StopValue = rsObj.getString("GL38STOP");
                        if (gl38StopValue == null || stopDay <= Integer.parseInt(gl38StopValue)) {
                            totalGL38FIRST = totalGL38FIRST.add(BigDecimal.ZERO);
                            System.out.println("Total of GL38FIRSTSSS: " + totalGL38FIRST);
                        } else {
                            totalGL38FIRST = totalGL38FIRST.add(GL38FIRST);
                            System.out.println("Total of GL38FIRST: " + totalGL38FIRST);
                        }
                    }
                    GL38RATE = rsObj.getString("GL38RATE") != null ? new BigDecimal(rsObj.getString("GL38RATE")) : BigDecimal.ZERO;
                    if (stopDay <= 0) {
                        break;
                    }
                    if (rsObj.getString("GL38STOP") != null) {
                        System.out.println("line 1781");
                        if (stopDay >= Integer.parseInt(rsObj.getString("GL38START")) && stopDay <= Integer.parseInt(rsObj.getString("GL38STOP"))) {
                            System.out.println("woi1 SQL" + sql3);
                            System.out.println("woi1 row" + row);
                            int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                            rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                            if (row == 1) {
                                finerate = rateValue;
                                System.out.println("fineRateN: " + finerate);
                            } else if (stopDay >= Integer.parseInt(rsObj.getString("GL38STOP")) && row > 1) {
                                finerate = rateValue.add(totalGL38FIRST);
                                System.out.println("fineRateY: " + finerate);
                            } else if (stopDay <= Integer.parseInt(rsObj.getString("GL38STOP")) && row > 1) {
                                finerate = rateValue.add(totalGL38FIRST);
                                System.out.println("fineRateZ: " + finerate);
                            } else {
                                finerate = rateValue;
                                System.out.println("fineRateM: " + finerate);
                            }
                            stopDay = 0;
                            continue;
                        }
                        if (stopDay >= Integer.parseInt(rsObj.getString("GL38STOP")) && row == 1) {
                            int noOfDays = 0;
                            if (Integer.parseInt(rsObj.getString("GL38START")) > 1) {
                                System.out.println("woi2 SQL" + sql3);
                                System.out.println("woi2 row" + row);
                                noOfDays = Integer.parseInt(rsObj.getString("GL38STOP")) - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                            } else {
                                System.out.println("woi3 SQL" + sql3);
                                System.out.println("woi3 row" + row);
                                noOfDays = Integer.parseInt(rsObj.getString("GL38STOP"));
                                System.out.println("woi3 noOfDays" + noOfDays);
                            }
                            System.out.println("woi4 SQL" + sql3);
                            System.out.println("woi4 row" + noOfDays);
                            rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                            System.out.println("woi4 rateValue" + rateValue);
                            finerate = rateValue;
                            System.out.println("fineRate" + finerate);
                            stopDay = 0;
                            continue;
                        }
                        System.out.println("heeeeeerrrr");
                        BigDecimal rateValue2 = GL38RATE.multiply(new BigDecimal(stopDay));
                        finerate = rateValue2.add(totalGL38FIRST);
                        System.out.println("fineRateeeeeeeeeee: " + rateValue2);
                        System.out.println("fineRateeeeeeeeeee222: " + finerate);
                        continue;
                    }
                    System.out.println(stopDay < Integer.parseInt(rsObj.getString("GL38START")));
                    if (stopDay < Integer.parseInt(rsObj.getString("GL38START"))) {
                        finerate = BigDecimal.valueOf(0L);
                        continue;
                    }
                    int noOfDays = stopDay - Integer.parseInt(rsObj.getString("GL38START")) + 1;
                    rateValue = GL38RATE.multiply(new BigDecimal(noOfDays));
                    finerate = rateValue.add(totalGL38FIRST);
                    stopDay = 0;
                    System.out.println("woi6 row " + finerate);
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

    /*
     * Unable to fully structure code
     */
    public int checkHolidayInHour(Date todayDate, Date dueDate, String msRetItemBranch) throws ParseException {
        block8: {
            originalDueDate = dueDate;
            dateFormat = new SimpleDateFormat("yyyyMMdd");
            timeFormat = new SimpleDateFormat("hh:mm:ss");
            todayWithZeroTime = dateFormat.parse(dateFormat.format(todayDate));
            dueDateWithZeroTime = dateFormat.parse(dateFormat.format(dueDate));
            origingalDateWithZeroTime = dateFormat.parse(dateFormat.format(originalDueDate));
            cal = Calendar.getInstance();
            cal.setTime(dueDateWithZeroTime);
            incrementDay = 1;
            totalFineInHour = 0;
            if (!dueDateWithZeroTime.equals(todayWithZeroTime)) ** GOTO lbl26
            totalFineInHour = this.countFineDuaDateIsTodayDate(dueDate, msRetItemBranch);
            break block8;
lbl-1000:
            // 1 sources

            {
                if (origingalDateWithZeroTime.equals(dueDateWithZeroTime)) {
                    if (!this.isHoliday(dueDateWithZeroTime, msRetItemBranch) && !this.isLibClose(dueDateWithZeroTime, msRetItemBranch)) {
                        totalFineInHour = this.countFineHourlyFirstDay(dueDate, msRetItemBranch);
                    }
                } else if (dueDateWithZeroTime.equals(todayWithZeroTime)) {
                    if (!this.isHoliday(dueDateWithZeroTime, msRetItemBranch) && !this.isLibClose(dueDateWithZeroTime, msRetItemBranch)) {
                        totalFineInHour = this.countFineHourlyLastDay(dueDate, msRetItemBranch);
                    }
                } else if (!this.isHoliday(dueDateWithZeroTime, msRetItemBranch) && !this.isLibClose(dueDateWithZeroTime, msRetItemBranch)) {
                    totalFineInHour = this.countFineHourly(dueDate, msRetItemBranch);
                }
                cal.add(5, incrementDay);
                dueDateWithZeroTime = cal.getTime();
lbl26:
                // 2 sources

                ** while (dueDateWithZeroTime.before((Date)todayWithZeroTime) || dueDateWithZeroTime.equals((Object)todayWithZeroTime))
            }
        }
        return totalFineInHour;
    }

    public int countFineHourly(Date dueDate, String msRetItemBranch) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmm");
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        LocalDateTime openTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String sql3 = "SELECT * FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
        Date startDate = null;
        Date endDate = null;
        int libStart = 0;
        int libClose = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql3);
            System.out.println(sql3);
            if (rsObj.next()) {
                libStart = Integer.parseInt(rsObj.getString("GL37START"));
                libClose = Integer.parseInt(rsObj.getString("GL37STOP"));
                startDate = timeFormat.parse(rsObj.getString("GL37START"));
                endDate = timeFormat.parse(rsObj.getString("GL37Stop"));
            }
            int totalHourFine = libClose - libStart;
            int diff = endDate.getHours() - startDate.getHours();
            this.totalDiffHours += diff;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.totalDiffHours;
    }

    public int countFineDuaDateIsTodayDate(Date dueDate, String msRetItemBranch) {
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        Date currentDateTime = new Date();
        LocalDateTime openTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String sql3 = "SELECT * FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
        Date endDate = dueDate;
        String libOpenTime = null;
        int diff = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql3);
            System.out.println(sql3);
            if (rsObj.next()) {
                libOpenTime = rsObj.getString("GL37Start");
            }
            Date libOpen = new SimpleDateFormat("yyyyMMddhhmm").parse(String.valueOf(this.msRetDueDate) + libOpenTime);
            if (dueDate.before(currentDateTime)) {
                long diffInMillies = Math.abs(currentDateTime.getTime() - dueDate.getTime());
                diff = (int)(diffInMillies / 3600000L % 24L);
                this.totalDiffHours += diff;
            } else if (dueDate.after(currentDateTime)) {
                this.totalDiffHours = 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.totalDiffHours;
    }

    public int countFineHourlyFirstDay(Date dueDate, String msRetItemBranch) {
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        LocalDateTime openTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String sql3 = "SELECT * FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
        Date endDate = dueDate;
        String libCloseTime = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql3);
            System.out.println(sql3);
            if (rsObj.next()) {
                libCloseTime = rsObj.getString("GL37Stop");
            }
            Date libClose = new SimpleDateFormat("yyyyMMddhhmm").parse(String.valueOf(this.msRetDueDate) + libCloseTime);
            long diffInMillies = Math.abs(libClose.getTime() - dueDate.getTime());
            int diff = (int)(diffInMillies / 3600000L % 24L);
            this.totalDiffHours += diff;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.totalDiffHours;
    }

    public int countFineHourlyLastDay(Date dueDate, String msRetItemBranch) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hhmm");
        Date currentDate = new Date();
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        LocalDateTime openTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String sql3 = "SELECT * FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
        Date startDate = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql3);
            System.out.println(sql3);
            if (rsObj.next()) {
                startDate = timeFormat.parse(rsObj.getString("GL37START"));
            }
            long diffInMillies = Math.abs(currentDate.getTime() - startDate.getTime());
            int diff = (int)(diffInMillies / 3600000L % 24L);
            this.totalDiffHours += diff;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.totalDiffHours;
    }

    public boolean isLibClose(Date dueDate, String msRetItemBranch) {
        boolean libClose = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String duedate = dateFormat.format(dueDate).toString();
        Locale locale = Locale.US;
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
        LocalDateTime openTime = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String day = openTime.format(formatterOutput);
        day = Handler.getDay(day);
        String sql3 = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
        System.out.println(sql3);
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql3);
            if (rsObj.next()) {
                libClose = false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return libClose;
    }

    public boolean isHoliday(Date dueDate, String msRetItemBranch) {
        boolean isholiday = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String duedate = dateFormat.format(dueDate).toString();
        try {
            String sql2 = "SELECT * FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC ='" + msRetItemBranch + "'";
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rsObj = stmt.executeQuery(sql2);
            System.out.println(sql2);
            if (!rsObj.next()) {
                isholiday = false;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return isholiday;
    }

    private int isWorkingDayorHoliday_v1(Calendar cal, String msRetItemBranch, int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        int incrementDay = 1;
        Date nextDay = new Date();
        cal.add(5, incrementDay);
        nextDay = cal.getTime();
        String duedate = dateFormat.format(nextDay).toString();
        boolean next = false;
        boolean stop = false;
        boolean holiday = false;
        boolean libClose = false;
        try {
            while (!stop) {
                String sql2 = "SELECT Count(*) as count FROM GLHOLIDAY WHERE GL30DATE ='" + GeneralUtility.DatetoStr(duedate) + "' AND GL30BRNC = '" + msRetItemBranch + "'";
                conn = DBConnection.getConnection();
                Statement st = conn.createStatement();
                rsObj = st.executeQuery(sql2);
                System.out.println(sql2);
                next = rsObj.next();
                if (next) {
                    int result = Integer.parseInt(rsObj.getString("count"));
                    holiday = result > 0;
                    Locale locale = Locale.US;
                    DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("EEEE", locale);
                    LocalDateTime openTime = nextDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    String day = openTime.format(formatterOutput);
                    day = Handler.getDay(day);
                    String sql3 = "SELECT GL37START FROM GLTIME WHERE GL37BRNC='" + msRetItemBranch + "' AND GL37DAY='" + day + "'";
                    String libStart = "";
                    try {
                        rsObj = st.executeQuery(sql3);
                        if (rsObj.next()) {
                            libStart = rsObj.getString("GL37START");
                        }
                        libClose = libStart == "";
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (!holiday && !libClose) {
                    ++count;
                    break;
                }
                ++count;
                cal.add(5, incrementDay);
                nextDay = cal.getTime();
                duedate = dateFormat.format(nextDay).toString();
                stop = false;
            }
        }
        catch (SQLException e) {
            try {
                conn.close();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return count;
    }
}
