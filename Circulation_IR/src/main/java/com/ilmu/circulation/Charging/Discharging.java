/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joda.time.Days
 *  org.joda.time.LocalDate
 *  org.joda.time.ReadablePartial
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.GeneralUtility;
import com.ilmu.circulation.Charging.Renewal;
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    boolean UpdateLostStatus = false;
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
        String sql = "SELECT CT03DOCNO,CT03STAT,CT03ICAT, (SELECT GL05BRNC FROM GLLOCA WHERE GL05LOCA = CT03LOCA )  AS CT03LOCA,CT03BDATE,CT03MATNO,CT03PATR,CT03COND,CT03DDATE, CT03SMD FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo.trim() + "' And CT03PATR='" + msPatronID + "' AND (CT03STAT='C' OR CT03STAT='E')";
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
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(exist);
        return exist;
    }

    public boolean RetrieveCirculationDetail(String msAccessionNo, String msPatronID) {
        String sql = "SELECT * FROM CICIRC WHERE CI02DOCNO = '" + msAccessionNo.trim() + "' And CI02PATR='" + msPatronID + "' AND CI02FLAG='C'";
        System.out.println("From RetrieveCirculation sql");
        System.out.println(sql);
        boolean exist = false;
        try {
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

    public boolean discharge(String msAccessionNo, String msPatronID) {
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
        String msOfficerID = "SYSADMIN";
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        double fines = 0.0;
        boolean bSucessful = false;
        boolean reservationstatus = false;
        boolean reserved = false;
        try {
            this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
            reservationstatus = this.checkReservationStatus(this.msRetMatNo);
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
            this.UpdateAccessionDetail = this.DischargeAccessionStatus(msAccessionNo, this.msRawDateReturned, msPatronID, this.msRetStatus);
            if (this.UpdateAccessionDetail) {
                bSucessful = true;
                System.out.println("CTDOCM updated");
            } else {
                bSucessful = false;
                System.out.println("CTDOCM not updated");
            }
            if (bSucessful) {
                this.UpdateCirculationDetails = this.UpdateCirculationDetails(this.msRawDateReturned, this.msRawTimeReturned, msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo);
                System.out.println("Circulation Details updated");
                if (msFines.signum() != 0) {
                    System.out.println("Writing transaction");
                    this.UpdateFinesTransaction(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
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
            String sql = "SELECT * FROM CTDOCM, GLLOCA WHERE CT03DOCNO = '" + msAccessionNo + "'" + "AND CT03LOCA = GL05LOCA";
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
                System.out.println(String.valueOf(this.msRetItemBranch) + " ITEM BRANC");
                System.out.println(String.valueOf(rsObj.getString("CT03LOCA")) + " ITEM BRANCLOCA");
                exist = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public boolean checkReservationStatus(String msRetMatNo) {
        boolean success = false;
        boolean count = false;
        int reservedno = 0;
        try {
            String sql = "select count(*) As ReservedNO from ciresv where CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X'";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                reservedno = Integer.parseInt(rsObj.getString("ReservedNO"));
                success = reservedno != 0;
                System.out.println("Count" + reservedno);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean reservationupdate(String msRetMatNo, String msAccessionNo, String msRawDateReturned, String msOfficerID) {
        boolean success = false;
        String CI03DOCNO = "";
        try {
            String sql = "select * from ciresv, glpatr where gl14patr=ci03patr and CI03MATNO='" + msRetMatNo + "' AND CI03NSTAT='X' AND CI03PRIOR=1";
            System.out.println(sql);
            rsObj = stmt.executeQuery(sql);
            while (rsObj.next()) {
                CI03DOCNO = rsObj.getString("CI03DOCNO");
                this.msResvPatrName = rsObj.getString("GL14NAME");
                this.msResvPatrId = rsObj.getString("GL14PATR");
                this.msResvPickup = "Default";
            }
            System.out.println("Sye" + CI03DOCNO);
            if (CI03DOCNO == null || CI03DOCNO.trim().isEmpty() || CI03DOCNO.equals(msAccessionNo)) {
                String sql2 = " Update CIRESV Set CI03NSTAT ='A',CI03NDATE = '" + msRawDateReturned.trim() + "' ," + "CI03DOCNO = '" + msAccessionNo.trim() + "' ," + "CI03OFFIC = '" + msOfficerID + "' " + "Where CI03MATNO = '" + msRetMatNo.trim() + "' " + "And CI03NSTAT = 'X' and CI03PRIOR='1'";
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean UpdateCirculationDetails(String msRawDateReturned, String msRawTimeReturned, String msOfficerID, String msPatronID, String msAccessionNo, String msRetDocNo) {
        boolean success;
        block16: {
            success = false;
            String sSQLStmt1 = "UPDATE CICIRC SET CI02FLAG = 'D', CI02DIDATE = ?, CI02DITIME = ?, CI02DIOFFI = ? WHERE CI02PATR = ? AND CI02DOCNO = ? AND CI02FLAG = 'C'";
            try {
                Throwable throwable = null;
                Object var10_12 = null;
                try {
                    Connection conn = DBConnection.getConnection();
                    try {
                        try (PreparedStatement pstmt = conn.prepareStatement(sSQLStmt1);){
                            pstmt.setString(1, msRawDateReturned.trim());
                            pstmt.setString(2, msRawTimeReturned.trim());
                            pstmt.setString(3, msOfficerID);
                            pstmt.setString(4, msPatronID.trim());
                            pstmt.setString(5, msAccessionNo.trim());
                            int count = pstmt.executeUpdate();
                            System.out.println("Updated rows: " + count);
                            if (count > 0) {
                                success = true;
                            }
                        }
                        if (conn == null) break block16;
                    }
                    catch (Throwable throwable2) {
                        if (throwable == null) {
                            throwable = throwable2;
                        } else if (throwable != throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        if (conn == null) throw throwable;
                        conn.close();
                        throw throwable;
                    }
                    conn.close();
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                        throw throwable;
                    }
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("UpdateCICIRC DONE ");
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
        int count = stmt.executeUpdate(sSQlStmt3);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean UpdateFinesTransaction(String msPatronID, String msRawDateReturned, BigDecimal msFines, String msOfficerID, String msAccessionNo) throws SQLException {
        boolean success;
        block37: {
            success = false;
            String msCode = "100";
            String sql2 = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='TRXNO'";
            try {
                Throwable throwable = null;
                Object var10_12 = null;
                try {
                    Connection conn = DBConnection.getConnection();
                    try {
                        block36: {
                            Statement stmt = conn.createStatement();
                            try {
                                block35: {
                                    try (ResultSet rsObj = stmt.executeQuery(sql2);){
                                        int iCounter = 0;
                                        if (rsObj.next()) {
                                            iCounter = rsObj.getInt("GL98VALUE");
                                        }
                                        String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
                                        String sSQlStmt3 = "INSERT INTO RETRXN (RE01TXNO, RE01CODE, RE01DATE, RE01AMT, RE01PDAMT, RE01STAT, RE01UPDREF, RE01PATR, RE01REFER, RE01OFFID, RE01RCVFROM, RE01DOCNO, RE01SENT1, RE01SENT2, RE01SENT3, RE01CICOUNTER, RE01PAYMODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                        System.out.println(sSQlStmt3);
                                        Throwable throwable2 = null;
                                        Object var18_24 = null;
                                        try (PreparedStatement pstmt = conn.prepareStatement(sSQlStmt3);){
                                            pstmt.setInt(1, iCounter + 1);
                                            pstmt.setString(2, msCode);
                                            pstmt.setString(3, msRawDateReturned);
                                            pstmt.setBigDecimal(4, msFines);
                                            pstmt.setInt(5, 0);
                                            pstmt.setInt(6, 0);
                                            pstmt.setString(7, msAccessionNo.trim());
                                            pstmt.setString(8, msPatronID);
                                            pstmt.setString(9, RE01REFER);
                                            pstmt.setString(10, msOfficerID);
                                            pstmt.setNull(11, 0);
                                            pstmt.setString(12, msAccessionNo.trim());
                                            pstmt.setNull(13, 0);
                                            pstmt.setNull(14, 0);
                                            pstmt.setNull(15, 0);
                                            pstmt.setInt(16, this.circCounter);
                                            pstmt.setNull(17, 0);
                                            int count = pstmt.executeUpdate();
                                            if (count <= 0) break block35;
                                            String sql3 = "UPDATE GLNUMB SET GL98VALUE = ? WHERE GL98FUNC='TRXNO'";
                                            System.out.println(sql3);
                                            Throwable throwable3 = null;
                                            Object var23_31 = null;
                                            try (PreparedStatement pstmt2 = conn.prepareStatement(sql3);){
                                                pstmt2.setInt(1, iCounter + 1);
                                                int count2 = pstmt2.executeUpdate();
                                                if (count2 > 0) {
                                                    success = true;
                                                }
                                            }
                                            catch (Throwable throwable4) {
                                                if (throwable3 == null) {
                                                    throwable3 = throwable4;
                                                    throw throwable3;
                                                }
                                                if (throwable3 == throwable4) throw throwable3;
                                                throwable3.addSuppressed(throwable4);
                                                throw throwable3;
                                            }
                                        }
                                        catch (Throwable throwable5) {
                                            if (throwable2 == null) {
                                                throwable2 = throwable5;
                                                throw throwable2;
                                            }
                                            if (throwable2 == throwable5) throw throwable2;
                                            throwable2.addSuppressed(throwable5);
                                            throw throwable2;
                                        }
                                    }
                                }
                                if (stmt == null) break block36;
                            }
                            catch (Throwable throwable6) {
                                if (throwable == null) {
                                    throwable = throwable6;
                                } else if (throwable != throwable6) {
                                    throwable.addSuppressed(throwable6);
                                }
                                if (stmt == null) throw throwable;
                                stmt.close();
                                throw throwable;
                            }
                            stmt.close();
                        }
                        if (conn == null) break block37;
                    }
                    catch (Throwable throwable7) {
                        if (throwable == null) {
                            throwable = throwable7;
                        } else if (throwable != throwable7) {
                            throwable.addSuppressed(throwable7);
                        }
                        if (conn == null) throw throwable;
                        conn.close();
                        throw throwable;
                    }
                    conn.close();
                }
                catch (Throwable throwable8) {
                    if (throwable == null) {
                        throwable = throwable8;
                        throw throwable;
                    }
                    if (throwable == throwable8) throw throwable;
                    throwable.addSuppressed(throwable8);
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Insert1 DONE ");
        return success;
    }

    public boolean DischargeAccessionStatus(String msAccessionNo, String msRawDateReturned, String msPatronID, String msStatus) throws SQLException {
        boolean success = false;
        String sSQlStmt4 = "Update CTDOCM Set CT03BDATE = NULL, CT03BTIME = NULL, CT03DDATE = NULL, CT03DTIME = NULL, CT03STAT = '" + msStatus + "', " + "CT03PATR = NULL, " + "CT03LASTACT = '" + msRawDateReturned.trim() + "', " + "CT03LASTID = '" + msPatronID + "' " + "Where CT03DOCNO = '" + msAccessionNo.trim() + "' " + "AND (CT03STAT = 'C' Or CT03STAT = 'E')";
        System.out.println(sSQlStmt4);
        int count = stmt.executeUpdate(sSQlStmt4);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String getMsPatronCategory(String msPatronID) {
        String msPatronCategory = "";
        String sql = "SELECT GL14CATE FROM GLPATR WHERE GL14PATR = '" + msPatronID + "'";
        try {
            Throwable throwable = null;
            Object var5_7 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    block21: {
                        Statement stmt = conn.createStatement();
                        try {
                            try (ResultSet rsObj = stmt.executeQuery(sql);){
                                if (rsObj.next()) {
                                    msPatronCategory = rsObj.getString("GL14CATE");
                                }
                            }
                            if (stmt == null) break block21;
                        }
                        catch (Throwable throwable2) {
                            if (throwable == null) {
                                throwable = throwable2;
                            } else if (throwable != throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            if (stmt == null) throw throwable;
                            stmt.close();
                            throw throwable;
                        }
                        stmt.close();
                    }
                    if (conn == null) return msPatronCategory;
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                    } else if (throwable != throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return msPatronCategory;
            }
            catch (Throwable throwable4) {
                if (throwable == null) {
                    throwable = throwable4;
                    throw throwable;
                }
                if (throwable == throwable4) throw throwable;
                throwable.addSuppressed(throwable4);
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return msPatronCategory;
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
                String sql2 = "SELECT COUNT(*) As Holiday FROM GLHOLIDAY WHERE GL30DATE BETWEEN '" + this.circDDate + "' AND '" + datereturned + "'";
                rsObj = stmt.executeQuery(sql2);
                System.out.println(sql2);
                while (rsObj.next()) {
                    holidaycount = Integer.parseInt(rsObj.getString("Holiday"));
                }
                System.out.println("C" + holidaycount);
                String sql3 = "SELECT  GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "'" + "AND GL38ICAT = '" + this.msRetItemCat + "'" + "AND GL38SMD = '" + this.msRetSMD + "'" + "AND GL38BRNC = '" + this.msRetLocation + "'";
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
                String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "' " + "AND GL27ICAT = '" + this.msRetItemCat + "' " + "AND GL27SMD = '" + this.msRetSMD + "' " + "AND GL27BRNC = '" + this.msRetLocation + "' ";
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

    public BigDecimal calculatefines2ORI(String msPatronID) throws SQLException {
        BigDecimal fines = BigDecimal.ZERO;
        BigDecimal fineRate = BigDecimal.ZERO;
        BigDecimal maxfine = BigDecimal.ZERO;
        System.out.println("msRetDueDate : " + this.msRetDueDate);
        this.latedays = this.calculateDays(this.msRetDueDate, this.msRetLocation);
        try {
            if (this.latedays < 0) {
                fines = BigDecimal.ZERO;
            } else {
                fineRate = this.fineRate(msPatronID);
                maxfine = this.MaxFine(msPatronID);
                System.out.println("Fine Rate" + fineRate);
                System.out.println("MaxFine" + maxfine);
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
                System.out.println("fineRates SINI KE? " + fineRates);
                fines = maxfine.compareTo(BigDecimal.ZERO) == 0 ? fineRates : (fineRates.compareTo(maxfine) > 0 ? maxfine : fineRates);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        System.out.println("calculateFine DONE ");
        fines = fines.setScale(2, 1);
        df2.setMaximumFractionDigits(2);
        df2.setMinimumFractionDigits(0);
        df2.setGroupingUsed(false);
        return fines;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BigDecimal MaxFine(String msPatronID) {
        BigDecimal maxfine = BigDecimal.ZERO;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                        String sql5 = "SELECT * FROM GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "' " + "AND GL27ICAT = '" + this.msRetItemCat + "' " + "AND GL27SMD = '" + this.msRetSMD + "' " + "AND GL27BRNC = '" + this.msRetLocation + "'";
                        System.out.println(sql5);
                        ResultSet rsObj = stmt.executeQuery(sql5);
                        System.out.println("MaxFine");
                        if (rsObj.next()) {
                            maxfine = rsObj.getBigDecimal("GL27MAXFINE");
                        }
                        if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                            String sql6 = "SELECT * FROM GLCATE WHERE GL07CATE = '" + this.msPatronCategory + "'";
                            System.out.println(sql6);
                            rsObj = stmt.executeQuery(sql6);
                            while (rsObj.next()) {
                                maxfine = rsObj.getBigDecimal("GL07MAXFINE");
                            }
                            if (maxfine == null || maxfine.compareTo(BigDecimal.ZERO) == 0) {
                                maxfine = BigDecimal.ZERO;
                            }
                        }
                    }
                    if (conn == null) return maxfine;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return maxfine;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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

    public int calculateDaysORI(String msDocDate, String msBranch) throws SQLException {
        System.out.println("msDocDate : " + msDocDate);
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BigDecimal fineRate(String msPatronID) {
        BigDecimal finerate = BigDecimal.ZERO;
        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        String sql3 = "SELECT GL38START, GL38STOP, GL38RATE FROM GLFINE WHERE GL38CATE = '" + this.msPatronCategory + "' " + "AND GL38ICAT = '" + this.msRetItemCat + "' " + "AND GL38SMD = '" + this.msRetSMD + "' " + "AND GL38BRNC = '" + this.msRetLocation + "'";
                        System.out.println(sql3);
                        Throwable throwable2 = null;
                        Object var9_14 = null;
                        try (ResultSet rsObj = stmt.executeQuery(sql3);){
                            int count = 0;
                            while (rsObj.next()) {
                                finerate = rsObj.getBigDecimal("GL38RATE");
                                System.out.println(finerate + " " + ++count);
                            }
                        }
                        catch (Throwable throwable3) {
                            if (throwable2 == null) {
                                throwable2 = throwable3;
                                throw throwable2;
                            } else {
                                if (throwable2 == throwable3) throw throwable2;
                                throwable2.addSuppressed(throwable3);
                            }
                            throw throwable2;
                        }
                    }
                    if (conn == null) return finerate;
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
                return finerate;
            }
            catch (Throwable throwable5) {
                if (throwable == null) {
                    throwable = throwable5;
                    throw throwable;
                } else {
                    if (throwable == throwable5) throw throwable;
                    throwable.addSuppressed(throwable5);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
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

    public String getErrMessage() {
        return this.errmessage;
    }

    public String getPrintMessage() {
        return this.printMessage;
    }

    public boolean modify(String msAccessionNo, String msPatronID, String id) {
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
        String msOfficerID = id;
        this.validAccession = this.RetrieveAccessionDetail(msAccessionNo, msPatronID);
        double fines = 0.0;
        boolean bSucessful = false;
        try {
            System.out.println("Start transaction");
            this.RetrieveCirculationDetail(msAccessionNo, msPatronID);
            BigDecimal msFines = this.calculatefines2(msPatronID);
            BigDecimal mscostitem = this.calculateCItem(msPatronID, msAccessionNo);
            BigDecimal msPrcfee = this.Prcfees(msPatronID);
            System.out.println("MsFines " + msFines);
            System.out.println(msPrcfee.compareTo(BigDecimal.ZERO) == 0);
            String fine = this.eligIncludeFine(msPatronID);
            System.out.println("fine " + fine);
            int tolCiresv = this.totalCiresv(msAccessionNo);
            System.out.println("tolCiresv " + tolCiresv);
            System.out.println("Writing transaction");
            System.out.println("Fines 100");
            if (fine.equals("Y")) {
                this.UpdateFinesTransaction(msPatronID, this.msRawDateReturned, msFines, msOfficerID, msAccessionNo);
            }
            System.out.println("Fines 104");
            this.fineLCOST(msAccessionNo);
            System.out.println("mscostitem " + mscostitem);
            this.UpdateCOSTITEMTransaction(msPatronID, this.msRawDateReturned, mscostitem, msOfficerID, msAccessionNo);
            System.out.println("Fines 105");
            System.out.println(msPrcfee + " msPrcfee");
            if (msPrcfee.compareTo(BigDecimal.ZERO) != 0) {
                this.UpdatePrcfeeTransaction(msPatronID, this.msRawDateReturned, msPrcfee, msOfficerID, msAccessionNo);
            } else {
                System.out.println("Insert3 DONE ELSE");
            }
            System.out.println("Update Status CICIRC");
            this.UpdateCirculationDetails(this.msRawDateReturned, this.msRawTimeReturned, msOfficerID, msPatronID, msAccessionNo, this.msRetDocNo);
            System.out.println("DELETE DATA AT TABLE CIRESV");
            if (tolCiresv == 1) {
                this.DeleteCIRESV(msAccessionNo);
                System.out.println("DATA DELETE");
            } else {
                System.out.println("Delete DONE ELSE");
            }
            System.out.println("Update Status");
            this.UpdateLostStatus = this.UpdateAccessionStatus(msAccessionNo, this.msRawDateReturned, msOfficerID);
            if (this.UpdateLostStatus) {
                bSucessful = true;
                System.out.println("Lost updated");
            } else {
                bSucessful = false;
                System.out.println("Lost Not updated");
            }
            System.out.println("--END--");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return bSucessful;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean UpdatePrcfeeTransaction(String msPatronID, String msRawDateReturned, BigDecimal msPrcfee, String msOfficerID, String msAccessionNo) throws SQLException {
        boolean success;
        block61: {
            Object conn;
            String msCode;
            block58: {
                success = false;
                msCode = "";
                String sql4 = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = 'RECPTPROCESSFEE'";
                try {
                    Throwable throwable = null;
                    Object var10_13 = null;
                    try {
                        conn = DBConnection.getConnection();
                        try {
                            block57: {
                                Statement stmt = conn.createStatement();
                                try {
                                    try (ResultSet rsObj = stmt.executeQuery(sql4);){
                                        if (rsObj.next()) {
                                            msCode = rsObj.getString("GL99VALUE");
                                        }
                                    }
                                    if (stmt == null) break block57;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (stmt == null) throw throwable;
                                    stmt.close();
                                    throw throwable;
                                }
                                stmt.close();
                            }
                            if (conn == null) break block58;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (conn == null) throw throwable;
                            conn.close();
                            throw throwable;
                        }
                        conn.close();
                    }
                    catch (Throwable throwable4) {
                        if (throwable == null) {
                            throwable = throwable4;
                            throw throwable;
                        }
                        if (throwable == throwable4) throw throwable;
                        throwable.addSuppressed(throwable4);
                        throw throwable;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            int iCounter = 0;
            String sql2 = "SELECT * FROM GLNUMB WHERE GL98FUNC='TRXNO'";
            try {
                conn = null;
                Object var12_20 = null;
                try {
                    Connection conn2 = DBConnection.getConnection();
                    try {
                        block60: {
                            Statement stmt = conn2.createStatement();
                            try {
                                try (ResultSet rsObj = stmt.executeQuery(sql2);){
                                    while (true) {
                                        if (!rsObj.next()) {
                                            String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
                                            String sSQlStmt3 = "INSERT INTO RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                            System.out.println(sSQlStmt3);
                                            Throwable throwable = null;
                                            Object var19_31 = null;
                                            try (PreparedStatement pstmt = conn2.prepareStatement(sSQlStmt3);){
                                                pstmt.setInt(1, iCounter + 1);
                                                pstmt.setString(2, msCode);
                                                pstmt.setString(3, msRawDateReturned);
                                                pstmt.setBigDecimal(4, msPrcfee);
                                                pstmt.setInt(5, 0);
                                                pstmt.setInt(6, 0);
                                                pstmt.setString(7, msAccessionNo.trim());
                                                pstmt.setString(8, msPatronID);
                                                pstmt.setString(9, RE01REFER);
                                                pstmt.setString(10, msOfficerID);
                                                pstmt.setNull(11, 0);
                                                pstmt.setString(12, msAccessionNo.trim());
                                                pstmt.setNull(13, 0);
                                                pstmt.setNull(14, 0);
                                                pstmt.setNull(15, 0);
                                                pstmt.setInt(16, this.circCounter);
                                                pstmt.setNull(17, 0);
                                                int count = pstmt.executeUpdate();
                                                if (count <= 0) break;
                                                String sql3 = "UPDATE GLNUMB SET GL98VALUE = ? WHERE GL98FUNC='TRXNO'";
                                                System.out.println(sql3);
                                                Throwable throwable5 = null;
                                                Object var24_38 = null;
                                                try (PreparedStatement pstmt2 = conn2.prepareStatement(sql3);){
                                                    pstmt2.setInt(1, iCounter + 1);
                                                    int count2 = pstmt2.executeUpdate();
                                                    if (count2 > 0) {
                                                        success = true;
                                                    }
                                                    break;
                                                }
                                                catch (Throwable throwable6) {
                                                    if (throwable5 == null) {
                                                        throwable5 = throwable6;
                                                        throw throwable5;
                                                    }
                                                    if (throwable5 == throwable6) throw throwable5;
                                                    throwable5.addSuppressed(throwable6);
                                                    throw throwable5;
                                                }
                                            }
                                            catch (Throwable throwable7) {
                                                if (throwable == null) {
                                                    throwable = throwable7;
                                                    throw throwable;
                                                }
                                                if (throwable == throwable7) throw throwable;
                                                throwable.addSuppressed(throwable7);
                                                throw throwable;
                                            }
                                        }
                                        iCounter = rsObj.getInt("GL98VALUE");
                                    }
                                }
                                if (stmt == null) break block60;
                            }
                            catch (Throwable throwable) {
                                if (conn == null) {
                                    conn = throwable;
                                } else if (conn != throwable) {
                                    ((Throwable)conn).addSuppressed(throwable);
                                }
                                if (stmt == null) throw conn;
                                stmt.close();
                                throw conn;
                            }
                            stmt.close();
                        }
                        if (conn2 == null) break block61;
                    }
                    catch (Throwable throwable) {
                        if (conn == null) {
                            conn = throwable;
                        } else if (conn != throwable) {
                            ((Throwable)conn).addSuppressed(throwable);
                        }
                        if (conn2 == null) throw conn;
                        conn2.close();
                        throw conn;
                    }
                    conn2.close();
                }
                catch (Throwable throwable) {
                    if (conn == null) {
                        conn = throwable;
                        throw conn;
                    }
                    if (conn == throwable) throw conn;
                    ((Throwable)conn).addSuppressed(throwable);
                    throw conn;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Insert3 DONE IF");
        return success;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BigDecimal Prcfees(String msPatronID) {
        BigDecimal prcfees = BigDecimal.ZERO;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                        String sql5 = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "' " + "AND GL27ICAT = '" + this.msRetItemCat + "' " + "AND GL27SMD = '" + this.msRetSMD + "' " + "AND GL27BRNC = '" + this.msRetLocation + "' ";
                        System.out.println(sql5);
                        ResultSet rsObj = stmt.executeQuery(sql5);
                        System.out.println("MaxFine");
                        if (rsObj.next()) {
                            prcfees = rsObj.getString("GL27PRCFEES") != null ? rsObj.getBigDecimal("GL27PRCFEES") : BigDecimal.ZERO;
                        }
                    }
                    if (conn == null) return prcfees;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return prcfees;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return prcfees;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String eligIncludeFine(String msPatronID) {
        String includeFIne = null;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                        String sqlIncludeFIne = "SELECT  * from GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "' " + "AND GL27ICAT = '" + this.msRetItemCat + "' " + "AND GL27SMD = '" + this.msRetSMD + "' " + "AND GL27BRNC = '" + this.msRetLocation + "' ";
                        System.out.println(sqlIncludeFIne);
                        ResultSet rsObj = stmt.executeQuery(sqlIncludeFIne);
                        System.out.println("MaxFine");
                        if (rsObj.next()) {
                            includeFIne = rsObj.getString("GL27INCFINE");
                        }
                    }
                    if (conn == null) return includeFIne;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return includeFIne;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return includeFIne;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int TimeCost(String msPatronID) {
        int timecost = 0;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        this.msPatronCategory = this.getMsPatronCategory(msPatronID);
                        String sql5 = "SELECT * FROM GLELIG WHERE GL27CATE = '" + this.msPatronCategory + "' " + "AND GL27ICAT = '" + this.msRetItemCat + "' " + "AND GL27SMD = '" + this.msRetSMD + "' " + "AND GL27BRNC = '" + this.msRetLocation + "'";
                        System.out.println(sql5);
                        ResultSet rsObj = stmt.executeQuery(sql5);
                        System.out.println("TIMECOST");
                        if (rsObj.next()) {
                            timecost = rsObj.getInt("GL27TIMESCOST");
                        }
                    }
                    if (conn == null) return timecost;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return timecost;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return timecost;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public BigDecimal fineLCOST(String msAccessionNo) {
        BigDecimal lcost = BigDecimal.ZERO;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        String sql5 = "SELECT * FROM CTDOCM WHERE CT03DOCNO = '" + msAccessionNo + "'";
                        System.out.println(sql5);
                        ResultSet rsObj = stmt.executeQuery(sql5);
                        System.out.println("MaxFine");
                        if (rsObj.next()) {
                            lcost = rsObj.getBigDecimal("CT03LCOST");
                        }
                    }
                    if (conn == null) return lcost;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return lcost;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return lcost;
    }

    public BigDecimal calculateCItem(String msPatronID, String msAccessionNo) throws SQLException {
        int noTimeCost = 0;
        BigDecimal Lcost = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        try {
            String sql6;
            noTimeCost = this.TimeCost(msPatronID);
            Lcost = this.fineLCOST(msAccessionNo);
            if (noTimeCost == 0) {
                System.out.println("noTimeCost == 0");
                sql6 = "SELECT * FROM GLLIBR";
                System.out.println(sql6);
                rsObj = stmt.executeQuery(sql6);
                while (rsObj.next()) {
                    noTimeCost = rsObj.getInt("GL28TIMESCOST");
                }
                total = Lcost.multiply(new BigDecimal(noTimeCost));
                System.out.println("noTimeCost == 0 ANSWER");
            } else {
                this.errmessage = "182";
            }
            if (Lcost.compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("Lcost.compareTo(BigDecimal.ZERO) == 0");
                sql6 = "SELECT * FROM GLLIBR";
                System.out.println(sql6);
                rsObj = stmt.executeQuery(sql6);
                while (rsObj.next()) {
                    Lcost = rsObj.getBigDecimal("GL28RCOST");
                }
                total = Lcost.multiply(new BigDecimal(noTimeCost));
                System.out.println("Lcost.compareTo(BigDecimal.ZERO) == 0 ANSWER");
            }
            total = Lcost.multiply(new BigDecimal(noTimeCost));
            System.out.println(total);
        }
        catch (Exception exception) {
            // empty catch block
        }
        System.out.println("calculateCItem DONE ");
        total = total.setScale(2, 1);
        df2.setMaximumFractionDigits(2);
        df2.setMinimumFractionDigits(0);
        df2.setGroupingUsed(false);
        return total;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean UpdateCOSTITEMTransaction(String msPatronID, String msRawDateReturned, BigDecimal mscostitem, String msOfficerID, String msAccessionNo) throws SQLException {
        boolean success;
        block61: {
            Object conn;
            String msCode;
            block58: {
                success = false;
                msCode = "";
                String sql4 = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = 'RECPTLOSTFEE'";
                try {
                    Throwable throwable = null;
                    Object var10_13 = null;
                    try {
                        conn = DBConnection.getConnection();
                        try {
                            block57: {
                                Statement stmt = conn.createStatement();
                                try {
                                    try (ResultSet rsObj = stmt.executeQuery(sql4);){
                                        if (rsObj.next()) {
                                            msCode = rsObj.getString("GL99VALUE");
                                        }
                                    }
                                    if (stmt == null) break block57;
                                }
                                catch (Throwable throwable2) {
                                    if (throwable == null) {
                                        throwable = throwable2;
                                    } else if (throwable != throwable2) {
                                        throwable.addSuppressed(throwable2);
                                    }
                                    if (stmt == null) throw throwable;
                                    stmt.close();
                                    throw throwable;
                                }
                                stmt.close();
                            }
                            if (conn == null) break block58;
                        }
                        catch (Throwable throwable3) {
                            if (throwable == null) {
                                throwable = throwable3;
                            } else if (throwable != throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            if (conn == null) throw throwable;
                            conn.close();
                            throw throwable;
                        }
                        conn.close();
                    }
                    catch (Throwable throwable4) {
                        if (throwable == null) {
                            throwable = throwable4;
                            throw throwable;
                        }
                        if (throwable == throwable4) throw throwable;
                        throwable.addSuppressed(throwable4);
                        throw throwable;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            int iCounter = 0;
            String sql2 = "SELECT * FROM GLNUMB where GL98FUNC='TRXNO'";
            try {
                conn = null;
                Object var12_20 = null;
                try {
                    Connection conn2 = DBConnection.getConnection();
                    try {
                        block60: {
                            Statement stmt = conn2.createStatement();
                            try {
                                try (ResultSet rsObj = stmt.executeQuery(sql2);){
                                    while (true) {
                                        if (!rsObj.next()) {
                                            String RE01REFER = String.valueOf(this.circDDate) + this.circDTime + ":" + this.latedays;
                                            String sSQlStmt3 = "Insert Into RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                                            System.out.println(sSQlStmt3);
                                            Throwable throwable = null;
                                            Object var19_31 = null;
                                            try (PreparedStatement pstmt = conn2.prepareStatement(sSQlStmt3);){
                                                pstmt.setInt(1, iCounter + 1);
                                                pstmt.setString(2, msCode);
                                                pstmt.setString(3, msRawDateReturned);
                                                pstmt.setBigDecimal(4, mscostitem);
                                                pstmt.setInt(5, 0);
                                                pstmt.setInt(6, 0);
                                                pstmt.setString(7, msAccessionNo.trim());
                                                pstmt.setString(8, msPatronID);
                                                pstmt.setString(9, RE01REFER);
                                                pstmt.setString(10, msOfficerID);
                                                pstmt.setNull(11, 0);
                                                pstmt.setString(12, msAccessionNo.trim());
                                                pstmt.setNull(13, 0);
                                                pstmt.setNull(14, 0);
                                                pstmt.setNull(15, 0);
                                                pstmt.setInt(16, this.circCounter);
                                                pstmt.setNull(17, 0);
                                                int count = pstmt.executeUpdate();
                                                if (count <= 0) break;
                                                String sql3 = "UPDATE GLNUMB SET GL98VALUE = ? WHERE GL98FUNC='TRXNO'";
                                                System.out.println(sql3);
                                                Throwable throwable5 = null;
                                                Object var24_38 = null;
                                                try (PreparedStatement pstmt2 = conn2.prepareStatement(sql3);){
                                                    pstmt2.setInt(1, iCounter + 1);
                                                    int count2 = pstmt2.executeUpdate();
                                                    if (count2 > 0) {
                                                        success = true;
                                                    }
                                                    break;
                                                }
                                                catch (Throwable throwable6) {
                                                    if (throwable5 == null) {
                                                        throwable5 = throwable6;
                                                        throw throwable5;
                                                    }
                                                    if (throwable5 == throwable6) throw throwable5;
                                                    throwable5.addSuppressed(throwable6);
                                                    throw throwable5;
                                                }
                                            }
                                            catch (Throwable throwable7) {
                                                if (throwable == null) {
                                                    throwable = throwable7;
                                                    throw throwable;
                                                }
                                                if (throwable == throwable7) throw throwable;
                                                throwable.addSuppressed(throwable7);
                                                throw throwable;
                                            }
                                        }
                                        iCounter = rsObj.getInt("GL98VALUE");
                                    }
                                }
                                if (stmt == null) break block60;
                            }
                            catch (Throwable throwable) {
                                if (conn == null) {
                                    conn = throwable;
                                } else if (conn != throwable) {
                                    ((Throwable)conn).addSuppressed(throwable);
                                }
                                if (stmt == null) throw conn;
                                stmt.close();
                                throw conn;
                            }
                            stmt.close();
                        }
                        if (conn2 == null) break block61;
                    }
                    catch (Throwable throwable) {
                        if (conn == null) {
                            conn = throwable;
                        } else if (conn != throwable) {
                            ((Throwable)conn).addSuppressed(throwable);
                        }
                        if (conn2 == null) throw conn;
                        conn2.close();
                        throw conn;
                    }
                    conn2.close();
                }
                catch (Throwable throwable) {
                    if (conn == null) {
                        conn = throwable;
                        throw conn;
                    }
                    if (conn == throwable) throw conn;
                    ((Throwable)conn).addSuppressed(throwable);
                    throw conn;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Insert2 DONE ");
        return success;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int totalCiresv(String msAccessionNo) {
        int totalVal = 0;
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (Statement stmt = conn.createStatement();){
                        String sql5 = "SELECT COUNT(*) AS COUNT FROM CIRESV WHERE CI03DOCNO = '" + msAccessionNo.trim() + "'";
                        System.out.println(sql5);
                        ResultSet rsObj = stmt.executeQuery(sql5);
                        System.out.println("MaxFine");
                        if (rsObj.next()) {
                            totalVal = rsObj.getInt("COUNT");
                        }
                    }
                    if (conn == null) return totalVal;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return totalVal;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return totalVal;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean DeleteCIRESV(String msAccessionNo) {
        boolean success;
        block16: {
            success = false;
            String sql = "DELETE FROM CIRESV WHERE CI03DOCNO = ?";
            try {
                Throwable throwable = null;
                Object var5_7 = null;
                try {
                    Connection conn = DBConnection.getConnection();
                    try {
                        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
                            pstmt.setString(1, msAccessionNo.trim());
                            int count = pstmt.executeUpdate();
                            System.out.println("Deleted rows: " + count);
                            if (count > 0) {
                                success = true;
                            }
                        }
                        if (conn == null) break block16;
                    }
                    catch (Throwable throwable2) {
                        if (throwable == null) {
                            throwable = throwable2;
                        } else if (throwable != throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        if (conn == null) throw throwable;
                        conn.close();
                        throw throwable;
                    }
                    conn.close();
                }
                catch (Throwable throwable3) {
                    if (throwable == null) {
                        throwable = throwable3;
                        throw throwable;
                    }
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                    throw throwable;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Delete DONE IF");
        return success;
    }

    public boolean UpdateCICIRCFLAG(String msAccessionNo) throws SQLException {
        boolean success = false;
        String SQLStmt2 = "UPDATE CICIRC SET CI02FLAG = 'D' WHERE CI02DOCNO = '" + msAccessionNo.trim() + "'";
        int count = stmt.executeUpdate(SQLStmt2);
        if (count > 0) {
            success = true;
        }
        return success;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean UpdateAccessionStatus(String msAccessionNo, String msRawDateReturned, String msOfficerID) {
        boolean success = false;
        String sql = "UPDATE CTDOCM SET CT03STAT = 'L', CT03LASTID = ?, CT03LASTACT = ? WHERE CT03DOCNO = ?";
        try {
            Throwable throwable = null;
            Object var7_9 = null;
            try {
                Connection conn = DBConnection.getConnection();
                try {
                    try (PreparedStatement pstmt = conn.prepareStatement(sql);){
                        pstmt.setString(1, msOfficerID.trim());
                        pstmt.setString(2, msRawDateReturned.trim());
                        pstmt.setString(3, msAccessionNo.trim());
                        int count = pstmt.executeUpdate();
                        System.out.println("Updated rows: " + count);
                        if (count > 0) {
                            success = true;
                        }
                    }
                    if (conn == null) return success;
                }
                catch (Throwable throwable2) {
                    if (throwable == null) {
                        throwable = throwable2;
                    } else if (throwable != throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    if (conn == null) throw throwable;
                    conn.close();
                    throw throwable;
                }
                conn.close();
                return success;
            }
            catch (Throwable throwable3) {
                if (throwable == null) {
                    throwable = throwable3;
                    throw throwable;
                } else {
                    if (throwable == throwable3) throw throwable;
                    throwable.addSuppressed(throwable3);
                }
                throw throwable;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
