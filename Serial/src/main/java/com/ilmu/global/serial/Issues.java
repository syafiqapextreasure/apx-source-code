/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.ilmu.global.serial;

import com.ilmu.global.DataFormatter;
import com.ilmu.global.DateFormatter;
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Issues {
    static Connection c = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    private int copyNo;
    private String volume;
    private String issue;
    private String expectedDate;
    private String receivedDate;
    private String claim1;
    private String claim2;
    private String claim3;
    private String status;
    private String freq;
    private String branch;
    private String location;
    private String itemCategory;
    private String volDesc;
    private String issDesc;
    private String issNo;
    private String orderNo;

    public Issues(int copyNo, String volume, String issue, String expectedDate, String receivedDate, String claim1, String claim2, String claim3) {
        this.copyNo = copyNo;
        this.volume = volume;
        this.issue = issue;
        this.expectedDate = expectedDate;
        this.receivedDate = receivedDate;
        this.claim1 = claim1;
        this.claim2 = claim2;
        this.claim3 = claim3;
    }

    public Issues(int copyNo, String volume, String issue, String expectedDate) {
        this.copyNo = copyNo;
        this.volume = volume;
        this.issue = issue;
        this.expectedDate = expectedDate;
    }

    public Issues(String orderNo, int copyNo, String issNo, String volume, String issues, String expectedDate, String status, String receivedDate, String freq, String branch, String location, String itemCategory, String volDesc, String issDesc, String claim1, String claim2, String claim3) {
        this.orderNo = orderNo;
        this.copyNo = copyNo;
        this.issNo = issNo;
        this.volume = volume;
        this.issue = issues;
        this.expectedDate = expectedDate;
        this.status = status;
        this.receivedDate = receivedDate;
        this.freq = freq;
        this.branch = branch;
        this.location = location;
        this.itemCategory = itemCategory;
        this.volDesc = volDesc;
        this.issDesc = issDesc;
        this.claim1 = claim1;
        this.claim2 = claim2;
        this.claim3 = claim3;
    }

    public Issues(String orderNo, String issNo, int copy) {
        this.orderNo = orderNo;
        this.issNo = issNo;
        this.copyNo = copy;
    }

    public Issues(String status, int copyNo, String volumeNo, String issNo) {
        this.copyNo = copyNo;
        this.volume = volumeNo;
        this.issNo = issNo;
        this.status = status;
    }

    public int getCopyNo() {
        return this.copyNo;
    }

    public String getVolume() {
        return this.volume;
    }

    public String getIssue() {
        return this.issue;
    }

    public String getExpectedDate() {
        return this.expectedDate;
    }

    public String getReceivedDate() {
        return this.receivedDate;
    }

    public String getClaim1() {
        return this.claim1;
    }

    public String getClaim2() {
        return this.claim2;
    }

    public String getClaim3() {
        return this.claim3;
    }

    public String getStatus() {
        return this.status;
    }

    public String getFreq() {
        return this.freq;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getLocation() {
        return this.location;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public String getVolDesc() {
        return this.volDesc;
    }

    public String getIssDesc() {
        return this.issDesc;
    }

    public String getIssNo() {
        return this.issNo;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public static List<Issues> SE04_getIssuesListByOrderID(String orderID) {
        ArrayList<Issues> list = new ArrayList<Issues>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT SE06ORDER, SE06CPYNO, SE06VOLLBL, SE06ISSLBL, SE06EXPDATE, SE06RCVDATE, SE06CLAIM1, SE06CLAIM2, SE06CLAIM3 ");
        query.append("FROM SEISSU WHERE SE06ORDER = '" + orderID + "'");
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query.toString());
                while (rs.next()) {
                    Issues newIssues = new Issues(Integer.parseInt(rs.getString("SE06CPYNO")), rs.getString("SE06VOLLBL"), rs.getString("SE06ISSLBL"), DateFormatter.DBToDisplayFormat(rs.getString("SE06EXPDATE")), DateFormatter.DBToDisplayFormat(rs.getString("SE06RCVDATE")), DateFormatter.DBToDisplayFormat(rs.getString("SE06CLAIM1")), DateFormatter.DBToDisplayFormat(rs.getString("SE06CLAIM2")), DateFormatter.DBToDisplayFormat(rs.getString("SE06CLAIM3")));
                    list.add(newIssues);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void SE04_deleteByOrderID(String orderID) {
        String query = "DELETE FROM SEISSU WHERE SE06ORDER = '" + orderID + "'";
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int SE04_getTimeByFreq(String freq) {
        String query = "SELECT GL49TIME FROM GLFREQ WHERE GL49FREQ = '" + freq + "'";
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                int n = rs.getInt("GL49TIME");
                return n;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String SE04_getTypeByFreq(String freq) {
        String query = "SELECT GL49PTYPE FROM GLFREQ WHERE GL49FREQ = '" + freq + "'";
        try {
            c = DBConnection.getConnection();
            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String string = rs.getString("GL49PTYPE");
                return string;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String SE04_computeStopDate(String startDate, String freq, int noOfIssues) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        Calendar c = Calendar.getInstance();
        try {
            date = formatter.parse(startDate);
            c.setTime(date);
            System.out.println("StartDate" + date);
            switch (Issues.SE04_getTypeByFreq(freq)) {
                case "M": {
                    c.add(2, Issues.SE04_getTimeByFreq(freq) * (noOfIssues - 1));
                    break;
                }
                case "WW": {
                    c.add(5, Issues.SE04_getTimeByFreq(freq) * 7 * (noOfIssues - 1));
                    break;
                }
                case "YYYY": {
                    c.add(1, Issues.SE04_getTimeByFreq(freq) * (noOfIssues - 1));
                    break;
                }
                case "D": {
                    c.add(5, Issues.SE04_getTimeByFreq(freq) * (noOfIssues - 1));
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("StopDate" + formatter.format(c.getTime()));
        return formatter.format(c.getTime());
    }

    public static List<Issues> SE04_decodeJson(String jsonIssue) throws JSONException {
        JSONArray jsonarr = new JSONArray(jsonIssue);
        ArrayList<Issues> issList = new ArrayList<Issues>();
        int i = 0;
        while (i < jsonarr.length()) {
            JSONObject jsonobj = jsonarr.getJSONObject(i);
            Issues newIss = new Issues(jsonobj.getInt("copies"), jsonobj.getString("volume"), jsonobj.getString("issue"), jsonobj.getString("date"));
            issList.add(newIss);
            ++i;
        }
        return issList;
    }

    public static List<Issues> SE04_decodeJson_updateIssues(String jsonIssue) throws JSONException {
        JSONArray jsonarr = new JSONArray(jsonIssue);
        ArrayList<Issues> issList = new ArrayList<Issues>();
        int i = 0;
        while (i < jsonarr.length()) {
            JSONObject jsonobj = jsonarr.getJSONObject(i);
            Issues newIss = new Issues(jsonobj.getString("orderNo"), jsonobj.getString("issNo"), jsonobj.getInt("copy"));
            issList.add(newIss);
            ++i;
        }
        return issList;
    }

    public static synchronized List<Issues> SE09_getIssuesListByOrderID(String orderID) {
        ArrayList<Issues> list = new ArrayList<Issues>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT T1.*, T2.SE04DESC FROM SEISSU T1 LEFT JOIN SESTAT T2 ON (T1.SE06ISSTAT = T2.SE04STAT) ");
        query.append("WHERE SE06ORDER = '" + orderID + "' AND (SE06STAT = 'O' OR SE06STAT = 'R' OR SE06STAT = 'P') Order By SE06EXPDATE Asc");
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query.toString());
                while (rs.next()) {
                    Issues newIssues = new Issues(orderID, rs.getInt("SE06CPYNO"), rs.getString("SE06ISSNO"), DataFormatter.replaceDashForNull(rs.getString("SE06VOLLBL")), DataFormatter.replaceDashForNull(rs.getString("SE06ISSLBL")), rs.getString("SE06EXPDATE"), rs.getString("SE04DESC"), rs.getString("SE06RCVDATE"), DataFormatter.replaceDashForNull(rs.getString("SE06FREQ")), "Branch", DataFormatter.replaceDashForNull(rs.getString("SE06LOCA")), DataFormatter.replaceDashForNull(rs.getString("SE06ICAT")), DataFormatter.replaceEmptyForNull(rs.getString("SE06VOLDESC")), DataFormatter.replaceEmptyForNull(rs.getString("SE06ISSDESC")), rs.getString("SE06CLAIM1"), rs.getString("SE06CLAIM2"), rs.getString("SE06CLAIM3"));
                    list.add(newIssues);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                try {
                    c.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                c.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static synchronized boolean SE09_updateIssuesStatus(String orderNo, String issNo, int copy, String status) {
        String query = "UPDATE SEISSU ";
        query = String.valueOf(query) + "SET SE06STAT = '" + status + "', SE06ISSTAT = '" + status + "', SE06STATDATE = '" + DateTime.getTodayDate() + "' ";
        query = String.valueOf(query) + "WHERE SE06ORDER = '" + orderNo + "' AND SE06ISSNO = '" + issNo + "' AND SE06CPYNO = " + copy;
        try {
            c = DBConnection.getConnection();
            PreparedStatement statment = c.prepareStatement(query);
            statment.execute();
            c.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static synchronized boolean SE09_updateIssuesStatusByJson(String jsonIssue, String status) {
        c = DBConnection.getConnection();
        try {
            c.setAutoCommit(false);
            List<Issues> issList = Issues.SE04_decodeJson_updateIssues(jsonIssue);
            for (Issues iss : issList) {
                String query = "UPDATE SEISSU ";
                query = String.valueOf(query) + "SET SE06STAT = '" + status + "', SE06ISSTAT = '" + status + "', SE06STATDATE = '" + DateTime.getTodayDate() + "'";
                if (status.equals("R")) {
                    query = String.valueOf(query) + " , SE06RCVDATE = '" + DateTime.getTodayDate() + "'";
                }
                query = String.valueOf(query) + " WHERE SE06ORDER = '" + iss.getOrderNo() + "' AND SE06ISSNO = '" + iss.getIssNo() + "' AND SE06CPYNO = " + iss.getCopyNo();
                PreparedStatement insertIss = c.prepareStatement(query);
                insertIss.execute();
            }
            c.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                c.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    public static synchronized boolean SE09_updateEIS(String jsonIssue, String reason) {
        c = DBConnection.getConnection();
        try {
            List<Issues> issList = Issues.SE04_decodeJson_updateIssues(jsonIssue);
            String invNo = DBQuery.getSingleData("SE07INVNO", "SEINVO", "SE07ORDER = '" + issList.get(0).getOrderNo() + "'");
            String rDate = DBQuery.getSingleData("SE07RDATE", "SEINVO", "SE07ORDER = '" + issList.get(0).getOrderNo() + "'");
            HashMap<String, String> strMap = new HashMap<String, String>();
            strMap.put("SE08ORDER", issList.get(0).getOrderNo());
            strMap.put("SE08INVNO", invNo);
            strMap.put("SE08TRXDATE", rDate);
            strMap.put("SE08REASON", reason);
            strMap.put("SE08OFFID", "sysadmin");
            String query = QueryBuilder.createInsertQuery("SEEISP", strMap, null, null);
            return DBQuery.runQuery(query);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized List<Integer> SE09_getCopyList(String orderNo) {
        List<String> list = DBQuery.getListData("DISTINCT(SE06CPYNO) AS Data", "SEISSU", "SE06ORDER = '" + orderNo + "'");
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (String s : list) {
            intList.add(Integer.valueOf(s));
        }
        return intList;
    }

    public static synchronized int SE09_getMaxIssNo(String orderNo) {
        String data = DBQuery.getSingleDataAsData("MAX(SE06ISSNO) AS Data", "SEISSU", "SE06ORDER = '" + orderNo + "'");
        return Integer.parseInt(data);
    }

    public static synchronized String SE09_getIssueStatusCode(String orderNo, int copy, String issNo) {
        String condition = "SE06ORDER='" + orderNo + "' AND SE06CPYNO=" + copy + " AND SE06ISSNO='" + issNo + "'";
        return DBQuery.getSingleData("SE06ISSTAT", "SEISSU", condition);
    }

    public static synchronized List<Issues> SE09_decodeJson(String jsonIssue) throws JSONException {
        JSONArray jsonarr = new JSONArray(jsonIssue);
        ArrayList<Issues> issList = new ArrayList<Issues>();
        int i = 0;
        while (i < jsonarr.length()) {
            JSONObject jsonobj = jsonarr.getJSONObject(i);
            Issues newIss = new Issues(jsonobj.getString("orderNo"), jsonobj.getString("issNo"), jsonobj.getInt("copy"));
            issList.add(newIss);
            ++i;
        }
        return issList;
    }

    public static synchronized boolean SE09_issuesClaimsByJson(String json) {
        try {
            List<Issues> issList = Issues.SE09_decodeJson(json);
            ArrayList<String> queryList = new ArrayList<String>();
            boolean claimable = true;
            for (Issues iss : issList) {
                block17: {
                    String statusCode = Issues.SE09_getIssueStatusCode(iss.getOrderNo(), iss.getCopyNo(), iss.getIssNo());
                    String newStatusCode = "";
                    String columnToUpdate = "";
                    String valueToUpdate = DateTime.getTodayDate();
                    if (statusCode.equals("C3") || statusCode.equals("P")) break block17;
                    switch (statusCode) {
                        case "O": {
                            newStatusCode = "C1";
                            columnToUpdate = "SE06CLAIM1";
                            break;
                        }
                        case "C1": {
                            newStatusCode = "C2";
                            columnToUpdate = "SE06CLAIM2";
                            break;
                        }
                        case "C2": {
                            newStatusCode = "C3";
                            columnToUpdate = "SE06CLAIM3";
                        }
                    }
                    HashMap<String, String> conditionMap = new HashMap<String, String>();
                    conditionMap.put("SE06ORDER", iss.getOrderNo());
                    conditionMap.put("SE06CPYNO", String.valueOf(iss.getCopyNo()));
                    conditionMap.put("SE06ISSNO", iss.getIssNo());
                    HashMap<String, String> updateMap = new HashMap<String, String>();
                    updateMap.put(columnToUpdate, valueToUpdate);
                    updateMap.put("SE06ISSTAT", newStatusCode);
                    queryList.add(QueryBuilder.createUpdateQuery("SEISSU", updateMap, null, null, conditionMap));
                    continue;
                }
                claimable = false;
            }
            if (claimable) {
                return DBQuery.runBatchQuery(queryList);
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized List<Issues> SE09_getIssueListByJson(String jsonIssue) throws JSONException {
        List<Issues> issList = Issues.SE09_decodeJson(jsonIssue);
        List<Issues> allIssList = Issues.SE09_getIssuesListByOrderID(issList.get(0).getOrderNo());
        ArrayList<Issues> newIssList = new ArrayList<Issues>();
        for (Issues i : allIssList) {
            for (Issues selectedIss : issList) {
                if (!selectedIss.issNo.equals(i.getIssNo()) || selectedIss.copyNo != i.getCopyNo()) continue;
                newIssList.add(i);
            }
        }
        return newIssList;
    }

    public static synchronized boolean SE09_checkinIssueByJson(String json, String status, String checkinDate, String location, String itemCategory, String issDesc, String volDesc) throws JSONException {
        List<Issues> issList = Issues.SE04_decodeJson_updateIssues(json);
        ArrayList<String> queryList = new ArrayList<String>();
        for (Issues i : issList) {
            HashMap<String, String> conditionMap = new HashMap<String, String>();
            HashMap<String, String> strMap = new HashMap<String, String>();
            conditionMap.put("SE06ORDER", i.getOrderNo());
            conditionMap.put("SE06ISSNO", i.getIssNo());
            conditionMap.put("SE06CPYNO", String.valueOf(i.getCopyNo()));
            strMap.put("SE06STAT", status);
            strMap.put("SE06ISSTAT", status);
            strMap.put("SE06RCVDATE", DateFormatter.displayToDBFormat(checkinDate));
            strMap.put("SE06LOCA", location);
            strMap.put("SE06ICAT", itemCategory);
            if (!issDesc.equals("")) {
                strMap.put("SE06ISSDESC", issDesc);
            }
            if (!volDesc.equals("")) {
                strMap.put("SE06VOLDESC", volDesc);
            }
            queryList.add(QueryBuilder.createUpdateQuery("SEISSU", strMap, null, null, conditionMap));
        }
        return DBQuery.runBatchQuery(queryList);
    }
}
