/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.reserveCollection.reserve;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReserveCollection {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ReserveCollection(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static List<String> getCourseCodeID() {
        System.out.println("In Server getCourseCodeID");
        ArrayList<String> courseCodeId = new ArrayList<String>();
        String query = "SELECT GL12COURSE FROM GLCOUR ";
        Connection conn = null;
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
                    courseCodeId.add(rs.getString("GL12COURSE"));
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
        return courseCodeId;
    }

    public static List<String> getCourseCodeDesc() {
        System.out.println("In Server getCourseCodeDesc");
        ArrayList<String> courseCodeDesc = new ArrayList<String>();
        String query = "SELECT GL12DESC FROM GLCOUR";
        Connection conn = null;
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
                    courseCodeDesc.add(rs.getString("GL12DESC"));
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
        return courseCodeDesc;
    }

    public static List<String> getSemMasterID() {
        ArrayList<String> semMasterId = new ArrayList<String>();
        String query = "SELECT GL60SEMESTER FROM GLSEMS";
        Connection conn = null;
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
                    semMasterId.add(rs.getString("GL60SEMESTER"));
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
        return semMasterId;
    }

    public static List<String> getSemMasterDesc() {
        ArrayList<String> semMasterDesc = new ArrayList<String>();
        String query = "SELECT GL60DESC FROM GLSEMS";
        Connection conn = null;
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
                    semMasterDesc.add(rs.getString("GL60DESC"));
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
        return semMasterDesc;
    }

    public static List<String> getSubjectId() {
        System.out.println("in function getSubjectId");
        ArrayList<String> subjectId = new ArrayList<String>();
        String query = "SELECT GL61SUBJECT FROM GLACSUBJ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
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
                    subjectId.add(rs.getString("GL61SUBJECT"));
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
        return subjectId;
    }

    public static List<String> getSubjectDesc() {
        ArrayList<String> subjectDesc = new ArrayList<String>();
        String query = "SELECT GL61DESC FROM GLACSUBJ";
        Connection conn = null;
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
                    subjectDesc.add(rs.getString("GL61DESC"));
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
        return subjectDesc;
    }

    public static List<String> getAccessionNo(String controlNo) {
        ArrayList<String> accessionNo = new ArrayList<String>();
        String query = "SELECT CT03DOCNO FROM CTDOCM WHERE CT03MATNO=?";
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, controlNo);
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
                accessionNo.add(rs.getString("CT03DOCNO"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return accessionNo;
    }

    public static String addReserve(String courseId, String semesterId, String subjectId, String instructor, String controlNo, String startDate, String endDate, String createdDate, String recordedBy, String title, String callNo, String publication, String author, String docNo) throws ParseException, SQLException {
        System.out.println("in server startDate: " + startDate);
        System.out.println("in server endDate: " + endDate);
        System.out.println("in server createdDate: " + createdDate);
        System.out.println("in server recordedBy: " + recordedBy);
        System.out.println("in server title: " + title);
        System.out.println("in server callNo: " + callNo);
        System.out.println("in server publication: " + publication);
        System.out.println("in server author: " + author);
        System.out.println("in server docNo: " + docNo);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
        Date inputStartDate = formatter.parse(startDate);
        String outputStartDate = sqlFormat.format(inputStartDate);
        Date inputEndDate = formatter.parse(endDate);
        String outputEndDate = sqlFormat.format(inputEndDate);
        Date inputCreatedDate = formatter.parse(createdDate);
        String outputCreatedDate = sqlFormat.format(inputCreatedDate);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("timestamp: " + timestamp);
        System.out.println("outputStartDate: " + outputStartDate);
        System.out.println("outputEndDate: " + outputEndDate);
        String insertQuery = "INSERT INTO RCMASTER(RC01RESERVENO, RC01COURSE, RC01SUBJ, RC01SEMESTER, RC01INSTRUCTOR, RC01CTRLNO, RC01DTSTART, RC01DTEND, RC01CREDATE, RC01CREBY, RC01ITEMINDB, RC01ITITL, RC01ICALLNO, RC01IPUBL, RC01ISUBJ, RC01IAUTH, RC01DOCNO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String getReserveNoQuery = "SELECT GL98VALUE FROM GLNUMB WHERE GL98FUNC='RESERVENO'";
        String ReserveNo = null;
        String ReserveNoWithPrefix = null;
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        ResultSet rs1 = null;
        conn = DBConnection.getConnection();
        try {
            try {
                stmt1 = conn.prepareStatement(getReserveNoQuery);
                rs1 = stmt1.executeQuery();
                ResultSetMetaData metadata = rs1.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs1.next()) {
                    String row = "";
                    int i2 = 1;
                    while (i2 <= columnCount) {
                        row = String.valueOf(row) + rs1.getString(i2);
                        ++i2;
                    }
                    ReserveNo = row;
                }
                System.out.println("reserve no from glnumb: " + ReserveNo);
                int ReserveNoValue = Integer.parseInt(ReserveNo) + 1;
                System.out.println("Integer reserve no: " + ReserveNo);
                System.out.println("outputStartDate: " + outputStartDate);
                System.out.println("outputEndDate: " + outputEndDate);
                System.out.println("outputCreatedDate: " + outputCreatedDate);
                System.out.println("add prefix: " + ("0000000000" + Integer.toString(ReserveNoValue)).substring(Integer.toString(ReserveNoValue).length()));
                ReserveNoWithPrefix = ("0000000000" + Integer.toString(ReserveNoValue)).substring(Integer.toString(ReserveNoValue).length());
                stmt2 = conn.prepareStatement(insertQuery);
                stmt2.setString(1, ReserveNoWithPrefix);
                stmt2.setString(2, courseId);
                stmt2.setString(3, subjectId);
                stmt2.setString(4, semesterId);
                stmt2.setString(5, instructor);
                stmt2.setString(6, controlNo);
                stmt2.setString(7, outputStartDate);
                stmt2.setString(8, outputEndDate);
                stmt2.setString(9, outputCreatedDate);
                stmt2.setString(10, recordedBy);
                stmt2.setString(11, "");
                stmt2.setString(12, title);
                stmt2.setString(13, callNo);
                stmt2.setString(14, publication);
                stmt2.setString(15, "");
                stmt2.setString(16, author);
                stmt2.setString(17, docNo);
                int insertedRow = stmt2.executeUpdate();
                System.out.println("insertedRow_RETRXN: " + insertedRow);
                System.out.println("update glnum reserveNo: " + ReserveNoValue);
                String updateGLNUMB = "Update GLNUMB set GL98VALUE='" + ReserveNoValue + "'" + "where GL98FUNC='RESERVENO'";
                System.out.println(updateGLNUMB);
                stmt2 = conn.prepareStatement(updateGLNUMB);
                int count2 = stmt2.executeUpdate();
                System.out.println("increment GL98VALUE: " + count2);
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
        return ReserveNoWithPrefix;
    }

    public static void deleteReserve(String reserveNo) throws ParseException {
        String query = "DELETE FROM RCMASTER WHERE RC01RESERVENO = '" + reserveNo + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                int deletedRow = delete.executeUpdate();
                System.out.println("no of row deleted: " + deletedRow);
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

    public static void editReserve(String reserveNo, String startDate, String endDate) throws ParseException {
        System.out.println("edit reserve no: " + reserveNo);
        System.out.println("edit reserve startDate: " + startDate);
        System.out.println("edit reserve endDate: " + endDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyyMMdd");
        Date inputStartDate = formatter.parse(startDate);
        String outputStartDate = sqlFormat.format(inputStartDate);
        Date inputEndDate = formatter.parse(endDate);
        String outputEndDate = sqlFormat.format(inputEndDate);
        String query = "UPDATE RCMASTER SET RC01DTSTART='" + outputStartDate + "' , RC01DTEND='" + outputEndDate + "'  WHERE RC01RESERVENO = '" + reserveNo + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                conn = DBConnection.getConnection();
                PreparedStatement update = conn.prepareStatement(query);
                int updatedRow = update.executeUpdate();
                System.out.println("no of row updated: " + updatedRow);
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

    public static void findReserve(String selectedRadio, String inputCriteria) {
    }

    public static List<ReserveCollection> getStartDateEndDateInEdit(String reserveNo) {
        ArrayList<ReserveCollection> reserveCollection = new ArrayList<ReserveCollection>();
        String query = "SELECT * FROM RCMASTER WHERE RC01RESERVENO=?";
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, reserveNo);
            rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            int i = 1;
            while (i <= columnCount) {
                System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                ++i;
            }
            String row = "";
            while (rs.next()) {
                int i2 = 1;
                while (i2 <= columnCount) {
                    row = String.valueOf(row) + rs.getString(i2) + ", ";
                    System.out.println("row in for loop: " + row);
                    ++i2;
                }
                System.out.println("row: " + row);
                reserveCollection.add(new ReserveCollection(ReserveCollection.swapYearAndDayInDateFormat(rs.getString("RC01DTSTART")), ReserveCollection.swapYearAndDayInDateFormat(rs.getString("RC01DTEND"))));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return reserveCollection;
    }

    public static String swapYearAndDayInDateFormat(String inputDate) throws ParseException {
        Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        String outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        System.out.println("output date: " + outputDate);
        return outputDate;
    }

    public static String getAccessionNoByReserveId(String reserveNo) {
        String accessionNo = null;
        String query = "SELECT RC01DOCNO FROM RCMASTER WHERE RC01RESERVENO=?";
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, reserveNo);
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
                    row = String.valueOf(row) + rs.getString(i2);
                    ++i2;
                }
                accessionNo = row;
            }
            System.out.println("accession no in server: " + accessionNo);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return accessionNo;
    }

    public static List<String> getAccessionNoByRsrvId(String reserveNo) {
        ArrayList<String> accessionNo = new ArrayList<String>();
        String query = "SELECT RC01DOCNO FROM RCMASTER WHERE RC01RESERVENO=?";
        Connection conn = null;
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, reserveNo);
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
                    row = String.valueOf(row) + rs.getString(i2);
                    ++i2;
                }
                accessionNo.add(row);
            }
            System.out.println("accession no in server: " + accessionNo);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return accessionNo;
    }
}
