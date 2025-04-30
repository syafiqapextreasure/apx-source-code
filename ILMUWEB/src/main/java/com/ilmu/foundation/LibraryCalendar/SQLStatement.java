/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.util.CollectionUtils
 */
package com.ilmu.foundation.LibraryCalendar;

import com.ilmu.foundation.LibraryCalendar.LibCalendar;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.util.CollectionUtils;

public class SQLStatement {
    public static Connection connection;

    public static List<LibCalendar> getHolidays(String branch) throws SQLException {
        ArrayList<LibCalendar> holidays = new ArrayList<LibCalendar>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * from GLHOLIDAY WHERE GL30BRNC='" + branch + "' ORDER BY GL30DATE ASC";
        System.out.print("query" + query);
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    LibCalendar hol = new LibCalendar(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    holidays.add(hol);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return holidays;
    }

    public static List<LibCalendar> getEvents(String branch) throws SQLException {
        ArrayList<LibCalendar> events = new ArrayList<LibCalendar>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * from GLPLAN WHERE GL35BRNC='" + branch + "' ORDER BY GL35DATE ASC";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    LibCalendar hol = new LibCalendar(rs.getString(1), rs.getString(2), rs.getString(3));
                    events.add(hol);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    public static List<String> fetchBranchesCode() throws SQLException {
        ArrayList<String> branches = new ArrayList<String>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String query = "SELECT GL71BRNC FROM GLBRNC";
        try {
            try {
                con = DBConnection.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    branches.add(rs.getString(1));
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                con.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return branches;
    }

    public static void addHolidayAllBranch(String date, String desc, String type) throws SQLException {
        List<String> branches = SQLStatement.fetchBranchesCode();
        if (!CollectionUtils.isEmpty(branches)) {
            for (String branch : branches) {
                String query = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + date + "', '" + branch + "', '" + desc + "', '" + type + "')";
                boolean isSuccess = DBQuery.runQuery(query);
                System.out.println(String.valueOf(isSuccess) + " for " + branch);
            }
        }
    }

    public static void addWeekend(String day, String branch, String desc, String type) throws SQLException {
        ArrayList<String> dates = new ArrayList<String>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        int year = Calendar.getInstance().get(1);
        int dayOfWeek = Integer.valueOf(day);
        System.out.println(dayOfWeek);
        GregorianCalendar cal = new GregorianCalendar(year, 0, 1);
        int i = 0;
        int inc = 1;
        while (i < 366 && cal.get(1) == year) {
            String dateFormatted;
            if (cal.get(7) == dayOfWeek) {
                System.out.println("aaaaaa");
                System.out.println(cal.getTime());
                dateFormatted = fmt.format(cal.getTime());
                System.out.println(dateFormatted);
                dates.add(dateFormatted);
                ((Calendar)cal).add(5, 7);
                inc = 7;
            } else {
                System.out.println("bbbbbbb");
                cal.set(year, 0, 1, 0, 0);
                cal.getTime();
                System.out.println(cal.getTime() + " GETTIME 1");
                cal.set(7, dayOfWeek);
                cal.getTime();
                System.out.println(cal.getTime());
                dateFormatted = fmt.format(cal.getTime());
                System.out.println(dateFormatted);
                dates.add(dateFormatted);
                ((Calendar)cal).add(5, 7);
            }
            i += inc;
        }
        System.out.println("xxxxx");
        if (!CollectionUtils.isEmpty(dates)) {
            for (String date : dates) {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                String selectQuery = "SELECT * FROM GLHOLIDAY WHERE GL30DATE='" + date + "' AND GL30BRNC='" + branch + "'";
                try {
                    try {
                        con = DBConnection.getConnection();
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(selectQuery);
                        if (!rs.next()) {
                            String insertQuery = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + date + "', '" + branch + "', '" + desc + "', '" + type + "')";
                            boolean isSuccess = DBQuery.runQuery(insertQuery);
                            System.out.println(isSuccess);
                        }
                    }
                    catch (SQLException ex) {
                        ex.printStackTrace();
                        try {
                            con.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }
                catch (Throwable throwable) {
                    try {
                        con.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throw throwable;
                }
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addWeekendAllBranch(String day, String desc, String type) throws SQLException {
        ArrayList<String> dates = new ArrayList<String>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        int year = Calendar.getInstance().get(1);
        int dayOfWeek = Integer.valueOf(day);
        System.out.println(dayOfWeek);
        GregorianCalendar cal = new GregorianCalendar(year, 0, 1);
        int i = 0;
        int inc = 1;
        while (i < 366 && cal.get(1) == year) {
            String dateFormatted;
            if (cal.get(7) == dayOfWeek) {
                System.out.println("aaaaaa");
                System.out.println(cal.getTime());
                dateFormatted = fmt.format(cal.getTime());
                System.out.println(dateFormatted);
                dates.add(dateFormatted);
                ((Calendar)cal).add(5, 7);
                inc = 7;
            } else {
                System.out.println("bbbbbbb");
                cal.set(year, 0, 1, 0, 0);
                cal.getTime();
                System.out.println(cal.getTime() + " GETTIME 1");
                cal.set(7, dayOfWeek);
                cal.getTime();
                System.out.println(cal.getTime());
                dateFormatted = fmt.format(cal.getTime());
                System.out.println(dateFormatted);
                dates.add(dateFormatted);
                ((Calendar)cal).add(5, 7);
            }
            i += inc;
        }
        System.out.println("xxxxx");
        if (!CollectionUtils.isEmpty(dates)) {
            for (String date : dates) {
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                String selectQuery = "SELECT * FROM GLHOLIDAY WHERE GL30DATE='" + date + "'";
                try {
                    try {
                        List<String> branches;
                        con = DBConnection.getConnection();
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(selectQuery);
                        System.out.println(String.valueOf(!rs.next()) + "isisis" + date);
                        if (!rs.next() && !CollectionUtils.isEmpty(branches = SQLStatement.fetchBranchesCode())) {
                            for (String branch : branches) {
                                System.out.println(String.valueOf(!rs.next()) + "yayaya" + date + " " + branch);
                                String insertQuery = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + date + "', '" + branch + "', '" + desc + "', '" + type + "')";
                                boolean isSuccess = DBQuery.runQuery(insertQuery);
                                System.out.println(String.valueOf(isSuccess) + " for " + branch);
                            }
                        }
                    }
                    catch (SQLException ex) {
                        ex.printStackTrace();
                        try {
                            con.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }
                catch (Throwable throwable) {
                    try {
                        con.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                    throw throwable;
                }
                try {
                    con.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addEvent(String date, String branch, String desc) {
        String query = "INSERT INTO GLPLAN(GL35DATE, GL35BRNC, GL35DESC) VALUES ('" + date + "', '" + branch + "', '" + desc + "')";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void updateEvent(String date, String branch, String desc) {
        String query = "UPDATE GLPLAN SET GL35DESC='" + desc + "' WHERE GL35DATE = '" + date + "' AND GL35BRNC = '" + branch + "'";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void deleteEvent(String date, String branch, String desc) {
        String query = "DELETE FROM GLPLAN WHERE GL35DESC = '" + desc + "' AND GL35DATE = '" + date + "' AND GL35BRNC = '" + branch + "'";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void addHoliday(String date, String branch, String desc, String type) {
        String query = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + date + "', '" + branch + "', '" + desc + "', '" + type + "')";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void updateHoliday(String date, String branch, String desc, String type) {
        String query = "UPDATE GLHOLIDAY SET GL30DESC='" + desc + "' WHERE GL30DATE = '" + date + "' AND GL30BRNC='" + branch + "' AND GL30HOLTYPE='" + type + "'";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void deleteHoliday(String date, String branch, String desc, String type) {
        String query = "DELETE FROM GLHOLIDAY WHERE GL30DESC='" + desc + "' AND GL30DATE = '" + date + "' AND GL30BRNC='" + branch + "' AND GL30HOLTYPE='" + type + "'";
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
    }

    public static void delete(String oldDate, String branch, String type, String title, String allBranch) {
        if (allBranch.equals("true")) {
            String query = "DELETE FROM GLHOLIDAY WHERE GL30DESC='" + title + "' AND GL30DATE = '" + oldDate + "' AND GL30HOLTYPE='" + type + "'";
            boolean isSuccess = DBQuery.runQuery(query);
            System.out.println(isSuccess);
        } else {
            String query = "DELETE FROM GLHOLIDAY WHERE GL30DESC='" + title + "' AND GL30DATE = '" + oldDate + "' AND GL30BRNC='" + branch + "' AND GL30HOLTYPE='" + type + "'";
            boolean isSuccess = DBQuery.runQuery(query);
            System.out.println(isSuccess);
        }
    }

    public static void add(String newDate, String branch, String type, String title, String allBranch) throws SQLException {
        if (allBranch.equals("true")) {
            List<String> branches = SQLStatement.fetchBranchesCode();
            if (!CollectionUtils.isEmpty(branches)) {
                for (String getbranch : branches) {
                    String query = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + newDate + "', '" + getbranch + "', '" + title + "', '" + type + "')";
                    boolean isSuccess = DBQuery.runQuery(query);
                    System.out.println(String.valueOf(isSuccess) + " for " + getbranch);
                }
            }
        } else {
            String query = "INSERT INTO GLHOLIDAY(GL30DATE, GL30BRNC, GL30DESC, GL30HOLTYPE) VALUES ('" + newDate + "', '" + branch + "', '" + title + "', '" + type + "')";
            boolean isSuccess = DBQuery.runQuery(query);
            System.out.println(isSuccess);
        }
    }
}
