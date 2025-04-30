/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.reserveMngmt;

import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReserveCollectionManagement {
    private static Connection conn;
    private String course;
    private String semester;
    private String subject;
    private String instructor;
    private String controlNo;
    private String title;
    private String startDate;
    private String endDate;
    private String reserveNo;

    public ReserveCollectionManagement(String course, String semester, String subject, String instructor, String controlNo, String title, String startDate, String endDate, String reserveNo) {
        this.course = course;
        this.semester = semester;
        this.subject = subject;
        this.instructor = instructor;
        this.controlNo = controlNo;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reserveNo = reserveNo;
    }

    public static List<ReserveCollectionManagement> getRC(String dateFrom, String dateTo) throws ParseException, SQLException {
        String query = "SELECT RC01COURSE, RC01SEMESTER, RC01SUBJ, RC01INSTRUCTOR, RC01CTRLNO, RC01ITITL, RC01DTSTART, RC01DTEND, RC01RESERVENO FROM RCMASTER WHERE RC01DTEND<=?";
        ArrayList<ReserveCollectionManagement> rcm = new ArrayList<ReserveCollectionManagement>();
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setDate(1, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dateTo).getTime()));
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    rcm.add(new ReserveCollectionManagement(rs.getString("RC01COURSE"), rs.getString("RC01SEMESTER"), rs.getString("RC01SUBJ"), rs.getString("RC01INSTRUCTOR"), rs.getString("RC01CTRLNO"), rs.getString("RC01ITITL"), rs.getString("RC01DTSTART"), rs.getString("RC01DTEND"), rs.getString("RC01RESERVENO")));
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
        return rcm;
    }

    public static List<String> getFetchList() throws ParseException {
        ArrayList<String> fetchList = new ArrayList<String>();
        String query = "SELECT DISTINCT RC01DTEND FROM RCMASTER";
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
                        row = rs.getString(i2);
                        System.out.println("row: " + row);
                        ++i2;
                    }
                    fetchList.add(ReserveCollectionManagement.swapYearAndDayInDateFormat(rs.getString("RC01DTEND")));
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
        return fetchList;
    }

    public static List<ReserveCollectionManagement> getFetchList(String fetchListDD) throws ParseException, SQLException {
        String query = "SELECT RC01COURSE, RC01SEMESTER, RC01SUBJ, RC01INSTRUCTOR, RC01CTRLNO, RC01ITITL, RC01DTSTART, RC01DTEND, RC01RESERVENO FROM RCMASTER WHERE RC01DTEND=?";
        ArrayList<ReserveCollectionManagement> fetchList = new ArrayList<ReserveCollectionManagement>();
        conn = DBConnection.getConnection();
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setDate(1, new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(fetchListDD).getTime()));
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    fetchList.add(new ReserveCollectionManagement(rs.getString("RC01COURSE"), rs.getString("RC01SEMESTER"), rs.getString("RC01SUBJ"), rs.getString("RC01INSTRUCTOR"), rs.getString("RC01CTRLNO"), rs.getString("RC01ITITL"), ReserveCollectionManagement.swapYearAndDayInDateFormat(rs.getString("RC01DTSTART")), ReserveCollectionManagement.swapYearAndDayInDateFormat(rs.getString("RC01DTEND")), rs.getString("RC01RESERVENO")));
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
        return fetchList;
    }

    public static String swapYearAndDayInDateFormat(String inputDate) throws ParseException {
        if (inputDate.equals("")) {
            return inputDate;
        }
        System.out.println("inputDate: :" + inputDate);
        Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        System.out.println("inputDateFormat: :" + inputDateFormat);
        String outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        System.out.println("swapYearAndDayInDateFormat: " + outputDate);
        return outputDate;
    }

    public static boolean deleteReservation(String reserveNo) {
        boolean status = false;
        String query = "DELETE FROM RCMASTER WHERE RC01RESERVENO = '" + reserveNo + "'";
        System.out.println(query);
        Connection conn = null;
        try {
            try {
                Object stmt = null;
                Object rs = null;
                conn = DBConnection.getConnection();
                PreparedStatement delete = conn.prepareStatement(query);
                int deletedRow = delete.executeUpdate();
                System.out.println("no of row deleted: " + deletedRow);
                if (deletedRow >= 1) {
                    status = true;
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
}
