/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.membershipRenewal;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MembershipRenewal {
    private static Connection conn = null;
    private String patronId;
    private String patronName;
    private String memberDate;
    private String expiredDate;
    private String addr1;
    private String addr2;
    private String addr3;
    private String code;
    private String town;
    private String recordedBy;
    private String recordedDate;
    private String itemChargedToDate;
    private String lateReturnToDate;
    private String noOfItemLost;
    private String noOfSuspend;
    private String itemChargedYTD;
    private String lateReturnsYTD;
    private String onloanItemCount;
    private String overdueItemCount;
    private String reservationCount;
    private String lastChargeDate;
    private String lastReturnDate;
    private String lastRenewDate;
    private BigDecimal amountOutstanding;
    private BigDecimal amountPaid;
    private String groupId;
    private String status;
    private String DOB;
    private String dept;
    private String category;
    private String gender;
    private String newExpiryDate;
    private String fee;
    private String updateStatus;
    private String checkDays;

    public String getPatronId() {
        return this.patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getPatronName() {
        return this.patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getMemberDate() {
        return this.memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    public String getExpiredDate() {
        return this.expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getAddr1() {
        return this.addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return this.addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddr3() {
        return this.addr3;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getRecordedBy() {
        return this.recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getRecordedDate() {
        return this.recordedDate;
    }

    public void setRecordedDate(String recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getItemChargedToDate() {
        return this.itemChargedToDate;
    }

    public void setItemChargedToDate(String itemChargedToDate) {
        this.itemChargedToDate = itemChargedToDate;
    }

    public String getLateReturnToDate() {
        return this.lateReturnToDate;
    }

    public void setLateReturnToDate(String lateReturnToDate) {
        this.lateReturnToDate = lateReturnToDate;
    }

    public String getNoOfItemLost() {
        return this.noOfItemLost;
    }

    public void setNoOfItemLost(String noOfItemLost) {
        this.noOfItemLost = noOfItemLost;
    }

    public String getNoOfSuspend() {
        return this.noOfSuspend;
    }

    public void setNoOfSuspend(String noOfSuspend) {
        this.noOfSuspend = noOfSuspend;
    }

    public String getItemChargedYTD() {
        return this.itemChargedYTD;
    }

    public void setItemChargedYTD(String itemChargedYTD) {
        this.itemChargedYTD = itemChargedYTD;
    }

    public String getLateReturnsYTD() {
        return this.lateReturnsYTD;
    }

    public void setLateReturnsYTD(String lateReturnsYTD) {
        this.lateReturnsYTD = lateReturnsYTD;
    }

    public String getOnloanItemCount() {
        return this.onloanItemCount;
    }

    public void setOnloanItemCount(String onloanItemCount) {
        this.onloanItemCount = onloanItemCount;
    }

    public String getOverdueItemCount() {
        return this.overdueItemCount;
    }

    public void setOverdueItemCount(String overdueItemCount) {
        this.overdueItemCount = overdueItemCount;
    }

    public String getReservationCount() {
        return this.reservationCount;
    }

    public void setReservationCount(String reservationCount) {
        this.reservationCount = reservationCount;
    }

    public String getLastChargeDate() {
        return this.lastChargeDate;
    }

    public void setLastChargeDate(String lastChargeDate) {
        this.lastChargeDate = lastChargeDate;
    }

    public String getLastReturnDate() {
        return this.lastReturnDate;
    }

    public void setLastReturnDate(String lastReturnDate) {
        this.lastReturnDate = lastReturnDate;
    }

    public String getLastRenewDate() {
        return this.lastRenewDate;
    }

    public void setLastRenewDate(String lastRenewDate) {
        this.lastRenewDate = lastRenewDate;
    }

    public BigDecimal getAmountOutstanding() {
        return this.amountOutstanding;
    }

    public void setAmountOutstanding(BigDecimal amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public BigDecimal getAmountPaid() {
        return this.amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDOB() {
        return this.DOB;
    }

    public void setDOB(String dOB) {
        this.DOB = dOB;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewExpiryDate() {
        return this.newExpiryDate;
    }

    public void setNewExpiryDate(String newExpiryDate) {
        this.newExpiryDate = newExpiryDate;
    }

    public String getFee() {
        return this.fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCheckDays() {
        return this.checkDays;
    }

    public void setCheckDays(String checkDays) {
        this.checkDays = checkDays;
    }

    public MembershipRenewal(String newExpiryDate, String category, String fee) {
        this.newExpiryDate = newExpiryDate;
        this.category = category;
        this.fee = fee;
    }

    public MembershipRenewal(String updateStatus, String newExpiryDate) {
        this.updateStatus = updateStatus;
        this.newExpiryDate = newExpiryDate;
    }

    public MembershipRenewal(String newExpiryDate, String category, String fee, String checkDays) {
        this.newExpiryDate = newExpiryDate;
        this.category = category;
        this.fee = fee;
        this.checkDays = checkDays;
    }

    public MembershipRenewal(String patronName, String addr1, String addr2, String addr3, String code, String town, String memberDate, String expiredDate, String recordedBy, String itemChargedToDate, String lateReturnToDate, String onloanItemCount, String overdueItemCount, String reservationCount, String noOfItemLost, String noOfSuspend, String itemChargedYTD, String lateReturnsYTD, String lastChargeDate, String lastReturnDate, String lastRenewDate, BigDecimal amountOutstanding, BigDecimal amountPaid, String DOB, String gender, String groupId, String status, String dept, String category) {
        this.patronName = patronName;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
        this.code = code;
        this.town = town;
        this.memberDate = memberDate;
        this.expiredDate = expiredDate;
        this.recordedBy = recordedBy;
        this.itemChargedToDate = itemChargedToDate;
        this.lateReturnToDate = lateReturnToDate;
        this.onloanItemCount = onloanItemCount;
        this.overdueItemCount = overdueItemCount;
        this.reservationCount = reservationCount;
        this.noOfItemLost = noOfItemLost;
        this.noOfSuspend = noOfSuspend;
        this.itemChargedYTD = itemChargedYTD;
        this.lateReturnsYTD = lateReturnsYTD;
        this.lastRenewDate = lastRenewDate;
        this.lastChargeDate = lastChargeDate;
        this.lastReturnDate = lastReturnDate;
        this.lastRenewDate = lastRenewDate;
        this.amountOutstanding = amountOutstanding;
        this.amountPaid = amountPaid;
        this.DOB = DOB;
        this.gender = gender;
        this.groupId = groupId;
        this.status = status;
        this.dept = dept;
        this.category = category;
    }

    public String toString() {
        return "MembershipRenewal [patronName=" + this.patronName + ", memberDate=" + this.memberDate + ", expiredDate=" + this.expiredDate + ", addr1=" + this.addr1 + ", addr2=" + this.addr2 + ", addr3=" + this.addr3 + ", code=" + this.code + ", town=" + this.town + ", recordedBy=" + this.recordedBy + ", recordedDate=" + this.recordedDate + ", itemChargedToDate=" + this.itemChargedToDate + ", lateReturnToDate=" + this.lateReturnToDate + ", noOfItemLost=" + this.noOfItemLost + ", noOfSuspend=" + this.noOfSuspend + ", itemChargedYTD=" + this.itemChargedYTD + ", lateReturnsYTD=" + this.lateReturnsYTD + ", onloanItemCount=" + this.onloanItemCount + ", overdueItemCount=" + this.overdueItemCount + ", reservationCount=" + this.reservationCount + ", lastChargeDate=" + this.lastChargeDate + ", lastReturnDate=" + this.lastReturnDate + ", lastRenewDate=" + this.lastRenewDate + ", amountOutstanding=" + this.amountOutstanding + ", amountPaid=" + this.amountPaid + ", groupId=" + this.groupId + ", status=" + this.status + ", DOB=" + this.DOB + ", dept=" + this.dept + ", category=" + this.category + ", gender=" + this.gender + ", newExpiryDate=" + this.newExpiryDate + ", fee=" + this.fee + ", updateStatus=" + this.updateStatus + "]";
    }

    public static List<MembershipRenewal> getPatronDetail(String patronId) throws ParseException {
        ArrayList<MembershipRenewal> patronDetail;
        block14: {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDateTime now = LocalDateTime.now();
            String currentDateTime = now.format(dtf);
            String currentDate = currentDateTime.substring(0, 8);
            System.out.println("currentDateTimeInString: " + currentDateTime);
            System.out.println("currentDate: " + currentDate);
            String YTD = String.valueOf(currentDate.substring(0, 4)) + "0101";
            System.out.println("YTD: " + YTD);
            String query = "SELECT GL14NAME, GL14ADD1, GL14ADD2, GL14ADD3, GL14CODE, GL14TOWN, GL14MEMDATE, GL14EXPDATE, GL14RECBY, GL14DOB, GL14SEX,\r\n(SELECT COUNT(*) FROM CICIRC T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI02MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI02MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI02PATR)\r\nLEFT JOIN CTDOCM T7 ON (T7.CT03DOCNO = T1.CI02DOCNO)\r\nLEFT JOIN GLLOCA T8 ON (T7.CT03LOCA = T8.GL05LOCA)\r\nLEFT JOIN GLELIG T9 ON (T9.GL27CATE = T6.GL14CATE AND T9.GL27ICAT = T7.CT03ICAT AND T9.GL27SMD = T7.CT03SMD AND T9.GL27BRNC=T8.GL05BRNC)\r\nWHERE CI02PATR=?\r\nAND CI02FLAG='C') AS OnLoanItemCount,\r\n(SELECT COUNT(*) FROM CIRESV T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CI03MATNO AND T2.CT06TAG = '245')\r\nLEFT JOIN CTTITL T3 ON (T2.CT06POINTER = T3.CT05POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CI03MATNO AND T4.CT06TAG = '090')\r\nLEFT JOIN CTCALL T5 ON (T4.CT06POINTER = T5.CT05POINTER)\r\nLEFT JOIN GLPATR T6 ON (T6.GL14PATR = T1.CI03PATR)\r\nWHERE CI03PATR=?) AS ReservationCount,\r\n(SELECT COUNT(*) FROM CICIRC WHERE CI02PATR=?) AS ItemChargedToDate,\r\n(SELECT COUNT(*) FROM CICIRC WHERE CI02PATR =? AND CI02FLAG = 'D' AND CI02DDATE < CI02DIDATE) AS LateReturnToDate,\r\n(SELECT COUNT(CT03DOCNO) FROM CTDOCM WHERE CT03PATR=? AND CT03STAT ='L') AS NoOfItemLost,\r\n(SELECT COUNT(CT03DOCNO) FROM CTDOCM WHERE CT03PATR=? AND CT03STAT ='S') AS NoOfSuspend,\r\n(SELECT COUNT(*) FROM CICIRC WHERE CI02PATR =? AND CI02CDATE>=" + YTD + ") AS ItemChargedYTD,\r\n" + "(SELECT COUNT(*) FROM CICIRC WHERE CI02PATR =? AND CI02DIDATE>CI02DDATE AND CI02DIDATE>=" + YTD + ") AS LateReturnsYTD,\r\n" + "(SELECT  max(CI02CDATE) FROM CICIRC WHERE CI02PATR =?) AS LastChargeDate,\r\n" + "(SELECT max(CI02DIDATE) FROM CICIRC WHERE CI02PATR=? AND CI02FLAG='D') AS LastReturnDate,\r\n" + "(SELECT max(CI02RDATE) FROM CICIRC WHERE CI02PATR =?) AS LastRenewDate,\r\n" + "(SELECT SUM(RE01AMT) FROM RETRXN, GLTRXC WHERE RE01PATR=? AND RE01CODE=GL38TXCD AND GL38TYPE='R') AS AmountPaid,\r\n" + "(SELECT GL02NAME FROM GLPATR T1 LEFT JOIN GLGRMA T2 ON (T2.GL02GRP= T1.GL14GRID) WHERE GL14PATR=?) AS GroupID,\r\n" + "(SELECT GL08DESC FROM GLPATR T1 LEFT JOIN GLSTAT T2 ON (T2.GL08STAT= T1.GL14STAT) WHERE GL14PATR=?) AS Status,\r\n" + "(SELECT GL11NAME FROM GLPATR T1 LEFT JOIN GLDEPT T2 ON (T2.GL11DEPT=T1.GL14DEPT) WHERE GL14PATR=?) AS Dept,\r\n" + "(SELECT GL07DESC FROM GLPATR T1 LEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE) WHERE GL14PATR=?) AS Category\r\n" + "FROM GLPATR\r\n" + "WHERE GL14PATR=?\r\n";
            patronDetail = new ArrayList<MembershipRenewal>();
            System.out.println(query);
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, patronId);
                    stmt.setString(2, patronId);
                    stmt.setString(3, patronId);
                    stmt.setString(4, patronId);
                    stmt.setString(5, patronId);
                    stmt.setString(6, patronId);
                    stmt.setString(7, patronId);
                    stmt.setString(8, patronId);
                    stmt.setString(9, patronId);
                    stmt.setString(10, patronId);
                    stmt.setString(11, patronId);
                    stmt.setString(12, patronId);
                    stmt.setString(13, patronId);
                    stmt.setString(14, patronId);
                    stmt.setString(15, patronId);
                    stmt.setString(16, patronId);
                    stmt.setString(17, patronId);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i2 = 1;
                        while (i2 <= columnCount) {
                            System.out.println("patron detail: " + rs.getString(i2));
                            row = String.valueOf(row) + rs.getString(i2) + ", ";
                            ++i2;
                        }
                        System.out.println(row);
                        patronDetail.add(new MembershipRenewal(rs.getString("GL14NAME"), rs.getString("GL14ADD1"), rs.getString("GL14ADD2"), rs.getString("GL14ADD3"), rs.getString("GL14CODE"), rs.getString("GL14TOWN"), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("GL14MEMDATE")), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("GL14EXPDATE")), rs.getString("GL14RECBY"), rs.getString("ItemChargedToDate"), rs.getString("LateReturnToDate"), rs.getString("OnLoanItemCount"), rs.getString("OnLoanItemCount"), rs.getString("ReservationCount"), rs.getString("NoOfItemLost"), rs.getString("NoOfSuspend"), rs.getString("ItemChargedYTD"), rs.getString("LateReturnsYTD"), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("LastChargeDate")), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("LastReturnDate")), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("LastRenewDate")), MembershipRenewal.calculateOutstandingAmount(patronId), rs.getBigDecimal("AmountPaid"), MembershipRenewal.swapYearAndDayInDateFormat(rs.getString("GL14DOB")), rs.getString("GL14SEX"), rs.getString("GroupId"), rs.getString("Status"), rs.getString("Dept"), rs.getString("Category")));
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
        System.out.println("patron detail result: " + patronDetail);
        return patronDetail;
    }

    public static List<MembershipRenewal> searchByPatronId(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String queryNoFilterBy = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nORDER BY GL14PATR\r\n";
        String query = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nAND T2.GL07DESC=?\r\nORDER BY GL14PATR\r\n";
        String query1NoFilter = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nORDER BY GL14NAME\r\n";
        String query1 = "SELECT GL14PATR, T2.GL07CATE, GL14NAME\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14PATR LIKE ?\r\nAND T2.GL07DESC=?\r\nORDER BY GL14NAME\r\n";
        ArrayList<MembershipRenewal> batchRenewal = new ArrayList<MembershipRenewal>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    if (filteredBy.equals("")) {
                        stmt = conn.prepareStatement(queryNoFilterBy);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                    } else {
                        stmt = conn.prepareStatement(query);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                        stmt.setString(2, filteredBy);
                    }
                } else if (sortedBy.equals("GL14NAME")) {
                    if (filteredBy.equals("")) {
                        stmt = conn.prepareStatement(query1NoFilter);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                    } else {
                        stmt = conn.prepareStatement(query);
                        stmt.setString(1, String.valueOf(searchText) + "%");
                        stmt.setString(2, filteredBy);
                    }
                    System.out.println("order by: " + query1);
                }
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        System.out.println("what if get subject: " + rs.getString(i2));
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    System.out.println(row);
                    batchRenewal.add(new MembershipRenewal(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return batchRenewal;
    }

    public static List<MembershipRenewal> searchByName(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String query = "SELECT GL14PATR, GL14NAME, T2.GL07DESC\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14NAME LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME, T2.GL07DESC\r\nFROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE=T1.GL14CATE)\r\nWHERE GL14NAME LIKE ?\r\nORDER BY GL14NAME\r\n";
        ArrayList<MembershipRenewal> batchRenewal = new ArrayList<MembershipRenewal>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        System.out.println("what if get subject: " + rs.getString(i2));
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    System.out.println(row);
                    batchRenewal.add(new MembershipRenewal(rs.getString("GL14PATR"), rs.getString("GL14NAME"), rs.getString("GL07DESC")));
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
        return batchRenewal;
    }

    public static List<MembershipRenewal> searchByNewIc(String searchBy, String searchText, String filteredBy, String sortedBy) {
        System.out.println("in new ic search");
        String query = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14NEWIC LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14NEWIC LIKE ?\r\nORDER BY GL14PATR";
        ArrayList<MembershipRenewal> batchRenewal = new ArrayList<MembershipRenewal>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        System.out.println("what if get subject: " + rs.getString(i2));
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    System.out.println(row);
                    batchRenewal.add(new MembershipRenewal(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return batchRenewal;
    }

    public static List<MembershipRenewal> searchByOldIc(String searchBy, String searchText, String filteredBy, String sortedBy) {
        String query = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14OLDIC LIKE ?\r\nORDER BY GL14PATR";
        String query1 = "SELECT GL14PATR, GL14NAME\r\nFROM GLPATR\r\nWHERE GL14OLDIC LIKE ?\r\nORDER BY GL14NAME";
        ArrayList<MembershipRenewal> batchRenewal = new ArrayList<MembershipRenewal>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                if (sortedBy.equals("GL14PATR")) {
                    stmt = conn.prepareStatement(query);
                } else if (sortedBy.equals("GL14NAME")) {
                    stmt = conn.prepareStatement(query1);
                }
                stmt.setString(1, String.valueOf(searchText) + "%");
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        System.out.println("what if get subject: " + rs.getString(i2));
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    System.out.println(row);
                    batchRenewal.add(new MembershipRenewal(rs.getString("GL14PATR"), rs.getString("GL14NAME")));
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
        return batchRenewal;
    }

    public static List<String> getFileterPatronCategoryDesc() {
        ArrayList<String> patron = new ArrayList<String>();
        String query = "SELECT GL07DESC FROM GLCATE ";
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    patron.add(rs.getString("GL07DESC"));
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
        return patron;
    }

    public static String swapYearAndDayInDateFormat(String inputDate) throws ParseException {
        String outputDate = "";
        System.out.println("inputDate: " + inputDate);
        if (inputDate == null || inputDate.isEmpty()) {
            outputDate = "null";
            return "null";
        }
        Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        System.out.println(inputDateFormat);
        outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        System.out.println("output Date: " + outputDate);
        return outputDate;
    }

    private static BigDecimal calculateOutstandingAmount(String patronId) {
        String query = "SELECT RE01AMT FROM RETRXN, GLTRXC WHERE RE01PATR =? AND RE01CODE=GL38TXCD\r\nAND  GL38MODE = 'D' ORDER BY RE01DATE\r\n";
        String query1 = "SELECT RE01AMT FROM RETRXN, GLTRXC WHERE RE01PATR =?\r\nAND RE01CODE=GL38TXCD AND GL38MODE='C' AND RE01STAT = '1'\r\n";
        BigDecimal outstandingAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal paidAmount = BigDecimal.ZERO;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, patronId);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    i = 1;
                    while (i <= columnCount) {
                        totalAmount = totalAmount.add(rs.getBigDecimal(i));
                        ++i;
                    }
                    System.out.println(totalAmount);
                }
                PreparedStatement stmt1 = null;
                ResultSet rs1 = null;
                stmt1 = conn.prepareStatement(query1);
                stmt1.setString(1, patronId);
                rs1 = stmt1.executeQuery();
                ResultSetMetaData metadata1 = rs.getMetaData();
                int columnCount1 = metadata1.getColumnCount();
                int i2 = 1;
                while (i2 <= columnCount1) {
                    System.out.println("[" + metadata1.getColumnTypeName(i2) + "|" + metadata1.getColumnName(i2) + "]" + ", ");
                    ++i2;
                }
                while (rs1.next()) {
                    i2 = 1;
                    while (i2 <= columnCount1) {
                        System.out.println("Paid Amount: " + rs1.getBigDecimal(i2));
                        paidAmount = paidAmount.add(rs1.getBigDecimal(i2));
                        ++i2;
                    }
                    System.out.println(paidAmount);
                }
                outstandingAmount = totalAmount.subtract(paidAmount);
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
        return outstandingAmount;
    }

    public static List<MembershipRenewal> membershipDetail(String patronId) throws ParseException {
        System.out.println("UpdateMembership: " + patronId);
        String query = "SELECT GL14DOB, GL14MEMDATE, GL14EXPDATE, GL07DESC, GL07RENEWFEE, GL07RENEWGRC FROM GLPATR T1\r\nLEFT JOIN GLCATE T2 ON (T2.GL07CATE = T1.GL14CATE)\r\nWHERE GL14PATR=?";
        ArrayList<MembershipRenewal> membershipDetail = new ArrayList<MembershipRenewal>();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, patronId);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    membershipDetail.add(new MembershipRenewal(MembershipRenewal.swapYearAndDayInDateFormat(MembershipRenewal.calculateRenewalDate(patronId, rs.getString("GL14DOB"), rs.getString("GL14MEMDATE"), rs.getString("GL14EXPDATE"), rs.getInt("GL07RENEWGRC"))), rs.getString("GL07DESC"), rs.getString("GL07RENEWFEE"), MembershipRenewal.validateExpiredDate()));
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
        return membershipDetail;
    }

    public static List<MembershipRenewal> updateMembership(String patronId, String expDate, String newExpDate, String recordedBy, BigDecimal fee) throws ParseException {
        int updatedRow;
        String duration;
        String outputDate;
        ArrayList<MembershipRenewal> membershipRenewal;
        block40: {
            membershipRenewal = new ArrayList<MembershipRenewal>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            String currentDateTime = now.format(dtf);
            String currentDate = currentDateTime.substring(0, 8);
            String currentTime = currentDateTime.substring(9, 15);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
            Date inputDate = formatter.parse(newExpDate);
            outputDate = sqlFormat.format(inputDate);
            String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='VALIDATERENEWAL'";
            duration = "";
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(query);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        i = 1;
                        while (i <= columnCount) {
                            duration = rs.getString(i);
                            ++i;
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
                    break block40;
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
        String queryUpdate = "Update GLPATR SET GL14EXPDATE=?\r\nWHERE GL14PATR=?\r\n";
        long countDay = MembershipRenewal.countNumberOfDays(patronId);
        System.out.println("countDay: " + countDay);
        if (countDay >= (long)(-Integer.parseInt(duration)) && countDay >= 0L) {
            try {
                try {
                    PreparedStatement stmt = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(queryUpdate);
                    stmt.setString(1, outputDate);
                    stmt.setString(2, patronId);
                    updatedRow = stmt.executeUpdate();
                    MembershipRenewal.insertRETRXN(patronId, newExpDate, recordedBy, fee, MembershipRenewal.getRETRXN());
                    MembershipRenewal.insertGLAUDIT(patronId, newExpDate, recordedBy, fee);
                    MembershipRenewal.insertGLPATA(patronId, expDate, newExpDate, recordedBy, fee);
                    membershipRenewal.add(new MembershipRenewal("pass", newExpDate));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e3) {
                        e3.printStackTrace();
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
        } else if (countDay <= (long)Integer.parseInt(duration) && countDay <= 0L) {
            try {
                try {
                    PreparedStatement stmt = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(queryUpdate);
                    stmt.setString(1, outputDate);
                    stmt.setString(2, patronId);
                    updatedRow = stmt.executeUpdate();
                    MembershipRenewal.insertRETRXN(patronId, newExpDate, recordedBy, fee, MembershipRenewal.getRETRXN());
                    MembershipRenewal.insertGLAUDIT(patronId, newExpDate, recordedBy, fee);
                    MembershipRenewal.insertGLPATA(patronId, expDate, newExpDate, recordedBy, fee);
                    membershipRenewal.add(new MembershipRenewal("pass", newExpDate));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        conn.close();
                    }
                    catch (SQLException e4) {
                        e4.printStackTrace();
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
        } else {
            membershipRenewal.add(new MembershipRenewal("fail", null, duration));
        }
        return membershipRenewal;
    }

    public static void insertRETRXN(String patronId, String newExpDate, String recordedBy, BigDecimal fee, int iCounter) throws ParseException {
        String msCode;
        String currentDate;
        block23: {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
            LocalDateTime now = LocalDateTime.now();
            String currentDateTime = now.format(dtf);
            currentDate = currentDateTime.substring(0, 8);
            String currentTime = currentDateTime.substring(9, 15);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
            Date inputDate = formatter.parse(newExpDate);
            String outputDate = sqlFormat.format(inputDate);
            msCode = null;
            String sqlGetMsCode = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='MEMRENEW'";
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(sqlGetMsCode);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        msCode = rs.getString("GL99VALUE");
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
                    break block23;
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
        String insertRETRXN = "INSERT INTO RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt1 = null;
                PreparedStatement stmt2 = null;
                stmt1 = conn.prepareStatement(insertRETRXN);
                stmt1.setInt(1, iCounter + 1);
                stmt1.setString(2, msCode);
                stmt1.setString(3, currentDate);
                stmt1.setBigDecimal(4, fee);
                stmt1.setBigDecimal(5, new BigDecimal(0.0));
                stmt1.setString(6, "1");
                stmt1.setString(7, "0");
                stmt1.setString(8, patronId);
                stmt1.setString(9, "");
                stmt1.setString(10, recordedBy);
                stmt1.setString(11, "NULL");
                stmt1.setString(12, "NULL");
                stmt1.setString(13, "NULL");
                stmt1.setString(14, "NULL");
                stmt1.setString(15, "NULL");
                stmt1.setInt(16, 0);
                stmt1.setString(17, "NULL");
                int insertedRow_RETRXN = stmt1.executeUpdate();
                String sql3 = "Update GLNUMB set GL98VALUE='" + (iCounter + 1) + "'" + "where GL98FUNC='TRXNO'";
                System.out.println(sql3);
                stmt2 = conn.prepareStatement(sql3);
                int n = stmt2.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
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

    public static void insertGLAUDIT(String patronId, String newExpDate, String recordedBy, BigDecimal fee) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = now.format(dtf);
        String currentDate = currentDateTime.substring(0, 8);
        String currentTime = currentDateTime.substring(9, 15);
        System.out.println("Current Date and Current Time: " + currentDate + "    " + currentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
        Date inputDate = formatter.parse(newExpDate);
        String outputDate = sqlFormat.format(inputDate);
        String insertGLAUDIT = "INSERT INTO GLAUDIT (GL68ACTCODE,GL68PATRONID,GL68DATE,GL68TIME,GL68REFER,GL68TEMNID) VALUES(?,?,?,?,?,?)";
        try {
            try {
                PreparedStatement stmt2 = null;
                conn = DBConnection.getConnection();
                stmt2 = conn.prepareStatement(insertGLAUDIT);
                stmt2.setString(1, "GLU0004");
                stmt2.setString(2, patronId);
                stmt2.setString(3, currentDate);
                stmt2.setString(4, currentTime);
                stmt2.setString(5, recordedBy);
                stmt2.setString(6, "");
                int n = stmt2.executeUpdate();
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
    }

    public static void insertGLPATA(String patronId, String expDate, String newExpDate, String recordedBy, BigDecimal fee) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = now.format(dtf);
        String currentDate = currentDateTime.substring(0, 8);
        String currentTime = currentDateTime.substring(9, 15);
        System.out.println("Current Date and Current Time: " + currentDate + "    " + currentTime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
        Date inputNewExpDate = formatter.parse(newExpDate);
        Date inputExpDate = formatter.parse(expDate);
        String outputNewExpDate = sqlFormat.format(inputNewExpDate);
        String outputExpDate = sqlFormat.format(inputExpDate);
        String insertGLPATA = "INSERT INTO GLPATA (GL72PATR,GL72DATE,GL72TIME,GL72COLM,GL72FVAL,GL72TVAL,GL72RECBY,GL72TEMNID,GL72MODE) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt3 = null;
        conn = DBConnection.getConnection();
        try {
            try {
                stmt3 = conn.prepareStatement(insertGLPATA);
                stmt3.setString(1, patronId);
                stmt3.setString(2, currentDate);
                stmt3.setString(3, currentTime);
                stmt3.setString(4, "GL14EXPDATE");
                stmt3.setString(5, outputExpDate);
                stmt3.setString(6, outputNewExpDate);
                stmt3.setString(7, "");
                stmt3.setString(8, recordedBy);
                stmt3.setString(9, "");
                int insertedRow_GLPATA = stmt3.executeUpdate();
                System.out.println("sql insertGLPATA:" + insertGLPATA);
                System.out.println("insertedRow_GLPATA: " + insertedRow_GLPATA);
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
    }

    public static int getRETRXN() {
        int iCounter = 0;
        String sql2 = "SELECT MAX(RE01TXNO) FROM RETRXN ";
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(sql2);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = rs.getString(i2);
                        ++i2;
                    }
                    System.out.println("value row: " + row);
                    iCounter = Integer.parseInt(row);
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
        return iCounter;
    }

    public static String validateExpiredDate() {
        String duration = "";
        String query = "SELECT GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='VALIDATERENEWAL'";
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    i = 1;
                    while (i <= columnCount) {
                        duration = rs.getString(i);
                        ++i;
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
        return duration;
    }

    private static long countNumberOfDays(String patronId) {
        String dbExpDate;
        DateTimeFormatter dtf;
        block14: {
            dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
            dbExpDate = null;
            String query = "SELECT GL14EXPDATE FROM GLPATR\r\nWHERE GL14PATR=?\r\n";
            try {
                try {
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    conn = DBConnection.getConnection();
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, patronId);
                    rs = stmt.executeQuery();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnCount = metadata.getColumnCount();
                    int i = 1;
                    while (i <= columnCount) {
                        System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                        ++i;
                    }
                    while (rs.next()) {
                        String row = "";
                        int i2 = 1;
                        while (i2 <= columnCount) {
                            System.out.println("what if get subject: " + rs.getString(i2));
                            dbExpDate = rs.getString(i2);
                            ++i2;
                        }
                        System.out.println(dbExpDate);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate expiryDate = LocalDate.parse(dbExpDate, formatter);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDate Now" + dtf.format(now));
        long days = ChronoUnit.DAYS.between(expiryDate, now);
        System.out.println("Days between" + days);
        return days;
    }

    public static String calculateRenewalDate(String patronId, String patronDOB, String membershipDate, String expiryDate, int numberOfYear) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nextExpireDate = null;
        String subStringExpDate = expiryDate.substring(4, 8);
        long numberOfDays = MembershipRenewal.countNumberOfDays(patronId);
        if (numberOfDays <= 0L && numberOfDays >= (long)(-Integer.parseInt(MembershipRenewal.validateExpiredDate()))) {
            LocalDate ld = LocalDate.parse(expiryDate, dtf);
            LocalDate yearAfterUpdate = ld.plusYears(numberOfYear);
            nextExpireDate = yearAfterUpdate.format(dtf);
        } else if (numberOfDays >= 0L) {
            LocalDate todayDate = LocalDate.now();
            String currentDate = todayDate.format(dtf);
            String subStringCurrentDate = currentDate.substring(0, 4);
            int currDate = Integer.parseInt(subStringCurrentDate);
            int intNewExpDate = currDate + numberOfYear;
            nextExpireDate = String.valueOf(intNewExpDate).concat(subStringExpDate);
        } else {
            nextExpireDate = null;
        }
        return nextExpireDate;
    }

    public static String calculateRenewalDate1(String patronId, String patronDOB, String membershipDate, String expiryDate, int numberOfYear) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nextExpireDate = null;
        String subStringExpDate = expiryDate.substring(4, 8);
        long numberOfDays = MembershipRenewal.countNumberOfDays(patronId);
        if (numberOfDays <= 0L && numberOfDays >= (long)(-Integer.parseInt(MembershipRenewal.validateExpiredDate()))) {
            LocalDate ld = LocalDate.parse(expiryDate, dtf);
            LocalDate yearAfterUpdate = ld.plusYears(numberOfYear);
            nextExpireDate = yearAfterUpdate.format(dtf);
        } else if (numberOfDays >= 0L) {
            LocalDate todayDate = LocalDate.now();
            String currentDate = todayDate.format(dtf);
            String subStringCurrentDate = currentDate.substring(0, 4);
            String subStringPatronDOB = patronDOB.substring(0, 4);
            String subStringPatronDOBmmDD = patronDOB.substring(4, 8);
            int DOB = Integer.parseInt(subStringPatronDOB);
            int currDate = Integer.parseInt(subStringCurrentDate);
            int preBalanceYear = currDate - DOB;
            int balanceYear = numberOfYear - preBalanceYear;
            int intNewExpDate = DOB + numberOfYear;
            nextExpireDate = String.valueOf(intNewExpDate).concat(subStringExpDate);
        } else {
            nextExpireDate = null;
        }
        return nextExpireDate;
    }
}
