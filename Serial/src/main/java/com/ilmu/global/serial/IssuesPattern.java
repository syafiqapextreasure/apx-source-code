/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.global.serial;

import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import com.ilmu.global.connection.DBConnection;
import com.ilmu.global.serial.Issues;
import com.ilmu.utilities.query.DBQuery;
import com.ilmu.utilities.query.QueryBuilder;
import java.io.File;
import java.io.IOException;
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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class IssuesPattern {
    static Connection conn;
    private List<String> patternList;
    private int patternID;
    private String patternType;
    private static Date previousdate;
    private static Connection c;
    private static Statement stmt;
    private static ResultSet rs;
    private String SE06CPYNO = null;
    private String SE06VOLLBL = null;
    private String SE06ISSLBL = null;
    private String SE06EXPDATE = null;
    public static int previousDate;

    static {
        c = null;
        stmt = null;
        rs = null;
        previousDate = 0;
    }

    public IssuesPattern(int patternID, List<String> pattern) {
        this.patternID = patternID;
        this.patternList = pattern;
    }

    public IssuesPattern(String SE06CPYNO, String SE06VOLLBL, String SE06ISSLBL, String SE06EXPDATE) {
        this.SE06CPYNO = SE06CPYNO;
        this.SE06VOLLBL = SE06VOLLBL;
        this.SE06ISSLBL = SE06ISSLBL;
        this.SE06EXPDATE = SE06EXPDATE;
    }

    public List<String> getList() {
        return this.patternList;
    }

    public int getPatternID() {
        return this.patternID;
    }

    public String getPatternType() {
        return this.patternType;
    }

    public String getSE06CPYNO() {
        return this.SE06CPYNO;
    }

    public String getSE06VOLLBL() {
        return this.SE06VOLLBL;
    }

    public String getSE06ISSLBL() {
        return this.SE06ISSLBL;
    }

    public String getSE06EXPDATE() {
        return this.SE06EXPDATE;
    }

    public static List<IssuesPattern> getAllPattern() {
        ArrayList<IssuesPattern> patternList = new ArrayList<IssuesPattern>();
        String path = Config.get("ISSUEPATTERN");
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Pattern");
            int i = 0;
            while (i < nList.getLength()) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == 1) {
                    Element e = (Element)nNode;
                    ArrayList<String> list = new ArrayList<String>();
                    int x = 0;
                    while (x < e.getElementsByTagName("Item").getLength()) {
                        list.add(e.getElementsByTagName("Item").item(x).getTextContent());
                        ++x;
                    }
                    String patternType = String.valueOf(e.getAttribute("id")) + " [" + e.getElementsByTagName("Item").item(0).getTextContent() + "]";
                    IssuesPattern pattern = new IssuesPattern(Integer.parseInt(e.getAttribute("id")), list);
                    patternList.add(pattern);
                }
                ++i;
            }
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return patternList;
    }

    public static List<IssuesPattern> getCardexTable(String orderno) throws ParseException {
        ArrayList<IssuesPattern> list = new ArrayList<IssuesPattern>();
        String query = "SELECT SE06CPYNO, SE06VOLLBL, SE06ISSLBL, SE06EXPDATE FROM SEISSU WHERE  SE06ORDER ='" + orderno + "'";
        IssuesPattern cardex = null;
        System.out.println(query);
        try {
            try {
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    cardex = new IssuesPattern(rs.getString("SE06CPYNO"), IssuesPattern.getNextVolume(rs.getString("SE06VOLLBL"), rs.getString("SE06EXPDATE")), rs.getString("SE06ISSLBL"), IssuesPattern.getNextExpDate(rs.getString("SE06EXPDATE")));
                    list.add(cardex);
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

    public static String SE04_getNextPattern(int patternID, String currPattern, int istartMonth) {
        List<IssuesPattern> patternList = IssuesPattern.getAllPattern();
        for (IssuesPattern p : patternList) {
            if (patternID != p.patternID) continue;
            System.out.println("patternListpatternList" + p.patternList.size());
            if (patternID != p.patternID) continue;
            System.out.println("istartMonth" + istartMonth);
            int currPosition = p.patternList.indexOf(currPattern);
            System.out.println("currPosition" + currPosition);
            if (p.patternList.size() == 12) {
                return p.patternList.get(istartMonth - 1);
            }
            if (currPosition + 1 == p.patternList.size()) {
                return p.patternList.get(0);
            }
            return p.patternList.get(currPosition + 1);
        }
        return null;
    }

    public static String nextDate(String inputDate, String freq) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        Calendar c = Calendar.getInstance();
        try {
            date = formatter.parse(inputDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        switch (Issues.SE04_getTypeByFreq(freq)) {
            case "M": {
                c.add(2, Issues.SE04_getTimeByFreq(freq));
                break;
            }
            case "WW": {
                c.add(5, Issues.SE04_getTimeByFreq(freq) * 7);
                break;
            }
            case "YYYY": {
                c.add(1, Issues.SE04_getTimeByFreq(freq));
                break;
            }
            case "D": {
                c.add(5, Issues.SE04_getTimeByFreq(freq));
            }
        }
        return formatter.format(c.getTime());
    }

    public static String getNextExpDate(String expDate) throws ParseException, SQLException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(DateTime.DBToDisFormat(expDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(1, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        return format1.format(cal.getTime());
    }

    public static String getNextVolumeYear(String volume, String expDate, int row) throws ParseException {
        System.out.println("curr" + previousDate);
        int newVol = 0;
        StringBuilder sb = new StringBuilder();
        int i = volume.length() - 1;
        while (i >= 0) {
            char c = volume.charAt(i);
            if (!Character.isDigit(c)) break;
            sb.insert(0, c);
            --i;
        }
        String result = sb.toString();
        char last = result.charAt(result.length() - 1);
        System.out.println("Result" + result + "Last" + last);
        if (row == 0) {
            previousDate = 0;
        }
        if (previousDate == 0) {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(expDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            previousDate = calendar.get(1);
            System.out.println("ss" + previousDate);
            newVol = Integer.parseInt(volume);
        } else {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(expDate);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            System.out.println("ss1" + previousDate);
            if (previousDate != calendar.get(1)) {
                newVol = Integer.parseInt(volume) + 1;
                previousDate = calendar.get(1);
                System.out.println("ss2" + previousDate);
            } else {
                newVol = Integer.parseInt(volume);
            }
        }
        volume = String.valueOf(newVol);
        return volume;
    }

    public static String getNextVolumeNo(String volume) throws ParseException {
        if (volume.contains("(")) {
            int newvolume = volume.charAt(volume.length() - 2);
            volume = String.valueOf(++newvolume);
        } else {
            int newvolume = volume.charAt(volume.length() - 1);
            volume = String.valueOf(++newvolume);
        }
        return volume;
    }

    public static String getNextVolume(String volume, String expDate) throws ParseException, SQLException {
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(DateTime.DBToDisFormat(expDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
        String oldVolume = dateFormatter.format(cal.getTime());
        cal.setTime(date1);
        cal.add(1, 1);
        SimpleDateFormat newVol = new SimpleDateFormat("yyyy");
        if (volume.contains(oldVolume)) {
            volume = volume.replace(oldVolume, newVol.format(cal.getTime()));
        }
        return volume;
    }

    public static boolean PatternExist(String vsOrderNo) throws SQLException {
        boolean IsPatternExist = false;
        String sSQLStmt = null;
        try {
            try {
                sSQLStmt = "SELECT COUNT(*) as count FROM SEISSU WHERE SE06ORDER='" + vsOrderNo + "'";
                c = DBConnection.getConnection();
                stmt = c.createStatement();
                rs = stmt.executeQuery(sSQLStmt);
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("Count")) <= 0) continue;
                    IsPatternExist = true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                rs.close();
                stmt.close();
                c.close();
            }
        }
        finally {
            rs.close();
            stmt.close();
            c.close();
        }
        return IsPatternExist;
    }

    public static boolean deletePattern(String vsOrderNo) {
        String query = "DELETE FROM SEISSU WHERE SE06ORDER = '" + vsOrderNo + "'";
        boolean deleteted = false;
        try {
            try {
                c = DBConnection.getConnection();
                PreparedStatement delete = c.prepareStatement(query);
                delete.execute();
                deleteted = true;
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
        return deleteted;
    }

    public static String InsertIssuePattern(String SE06ORDER, int SE06ISSU, String SE06CPYNO, String SE06VOLLBL, String SE06ISSLBL, String SE06EXPDATE, String SE06MATNO, String SE06FREQ) {
        HashMap<String, String> valueStr = new HashMap<String, String>();
        HashMap<String, Integer> valueInt = new HashMap<String, Integer>();
        valueStr.put("SE06ORDER", SE06ORDER);
        valueInt.put("SE06ISSNO", SE06ISSU);
        valueStr.put("SE06CPYNO", SE06CPYNO);
        valueStr.put("SE06VOLLBL", SE06VOLLBL);
        valueStr.put("SE06ISSLBL", SE06ISSLBL);
        valueStr.put("SE06EXPDATE", DateTime.displayToDBFormat(SE06EXPDATE));
        valueStr.put("SE06STAT", "O");
        valueStr.put("SE06MATNO", SE06MATNO);
        valueStr.put("SE06FREQ", SE06FREQ);
        valueStr.put("SE06ISSTAT", "O");
        String query = QueryBuilder.createInsertQuery("SEISSU", valueStr, valueInt, null);
        System.out.println("sasa" + query);
        boolean isSuccess = DBQuery.runQuery(query);
        System.out.println(isSuccess);
        return query;
    }
}
