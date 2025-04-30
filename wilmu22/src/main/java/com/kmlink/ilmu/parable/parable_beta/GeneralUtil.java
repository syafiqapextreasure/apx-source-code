/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.parable.parable_beta.Config_Print;
import com.kmlink.ilmu.parable.parable_beta.Handler;
import com.kmlink.ilmu.parable.parable_beta.ISBD;
import com.kmlink.ilmu.parable.parable_beta.object.book_spine;
import com.kmlink.ilmu.parable.parable_beta.object.patron;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class GeneralUtil {
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    public String LB01SIZE = null;

    public static String DDCDataSize() {
        String query = "Select LB01SIZE FROM LBMSTR WHERE LB01TPLNAME='BOOKLBL2' AND LB01FIELD='LABEL'";
        String dataSize = null;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    dataSize = rs.getString("LB01SIZE");
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
        return dataSize;
    }

    public static boolean checkFormat() {
        String query = "Select GL99VALUE FROM GLFLAG2 WHERE GL99FUNC = 'PRINTSPINE' ";
        boolean exist = false;
        System.out.println("Test" + query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (!rs.getString("GL99VALUE").equals("Y")) continue;
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

    public static String DecimalSplit(String lbltype) {
        String query = "Select LB01DECIMAL FROM LBMSTR WHERE LB01MTYPE='N' AND LB01TPLGRP='" + lbltype + "'";
        String decimalsplit = null;
        System.out.println("Decimal" + query);
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    decimalsplit = rs.getString("LB01DECIMAL");
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
        return decimalsplit;
    }

    public static int CharLen(String lbltype) {
        String query = "Select LB01CHARLEN FROM LBMSTR WHERE LB01MTYPE='N' AND LB01TPLGRP='" + lbltype + "'";
        int length = 0;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    length = Integer.parseInt(rs.getString("LB01CHARLEN"));
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
        return length;
    }

    public static boolean printAcq() {
        String query = "Select GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='PRINTACQ' ";
        boolean exist = false;
        try {
            try {
                conn = DBConnection.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    if (!rs.getString("GL99VALUE").equals("Y")) continue;
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

    public String date_formattter(String date) {
        String finalString = "";
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = null;
            try {
                date1 = formatter.parse(date);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateformat = new SimpleDateFormat("YYYYMMdd");
            finalString = dateformat.format(date1);
            return finalString.trim().toLowerCase().toString();
        }
        return date;
    }

    public String date_formattter_reverse(String date) {
        String finalString = "";
        if (date != null && !date.trim().isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date1 = null;
            try {
                date1 = formatter.parse(date);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            finalString = dateformat.format(date1);
            return finalString.trim().toLowerCase().toString();
        }
        return date;
    }

    public String removeScript(String content) {
        if (content != null) {
            Pattern p = Pattern.compile("\\|[a-z]");
            return p.matcher(content).replaceAll(" ");
        }
        return content;
    }

    public HashMap<String, Object> extract_data(ResultSet resultSet) throws SQLException {
        ArrayList<book_spine> resultList = new ArrayList<book_spine>();
        HashMap<String, Object> result = new HashMap<String, Object>();
        ResultSetMetaData metaData = null;
        Integer columnCount = null;
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
        ArrayList arrayList = new ArrayList();
        while (resultSet.next()) {
            book_spine book_spine2 = new book_spine();
            int i = 1;
            System.out.println("Test11" + resultSet.getString("Title"));
            book_spine2.setAccessionDate(this.date_formattter_reverse(resultSet.getString("AccessionDate")));
            book_spine2.setAccessionNo(resultSet.getString("AccessionNo"));
            book_spine2.setAuthor(Handler.removeSubfield(resultSet.getString("Author")));
            if (resultSet.getString("CallNo") != null) {
                List<ISBD> isbd = ISBD.getPunctuation("090");
                book_spine2.setCallNo(Handler.getSubfield(resultSet.getString("CallNo"), isbd));
            }
            book_spine2.setCatalognDate(this.date_formattter_reverse(resultSet.getString("CatalognDate")));
            book_spine2.setCopyNo(Handler.ifIsNull(resultSet.getString("CopyNo")));
            book_spine2.setCurrency(Handler.ifIsNull(resultSet.getString("Currency")));
            book_spine2.setForeignCost(resultSet.getString("ForeignCost"));
            book_spine2.setItemCategoryDesc(resultSet.getString("ItemCategoryDesc"));
            book_spine2.setLastIndexDate(this.date_formattter_reverse(resultSet.getString("LastIndexDate")));
            book_spine2.setLocalCost(resultSet.getString("LocalCost"));
            book_spine2.setLocationDesc(resultSet.getString("LocationDesc"));
            book_spine2.setSMD(resultSet.getString("SMD"));
            book_spine2.setSMDDesc(resultSet.getString("SMDDesc"));
            book_spine2.setBranch(resultSet.getString("ControlNo"));
            List<ISBD> titleisbd = ISBD.getPunctuation("245");
            book_spine2.setTitle(Handler.getSubfield(resultSet.getString("Title"), titleisbd));
            book_spine2.setUserDefineSpine(Handler.ifIsNull(resultSet.getString("UserDefineSpine")));
            book_spine2.setVolume(Handler.ifIsNull(resultSet.getString("Volume")));
            ++i;
            resultList.add(book_spine2);
        }
        result.put("list", resultList);
        result.put("status", "ok");
        System.out.println("Listss" + new JSONObject(result).toString());
        return result;
    }

    public book_spine extract_book_spine(ResultSet resultSet) throws SQLException {
        ArrayList resultList = new ArrayList();
        HashMap result = new HashMap();
        ResultSetMetaData metaData = null;
        Integer columnCount = null;
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
        int size = resultSet.getRow();
        book_spine book_spine2 = new book_spine();
        while (resultSet.next()) {
            book_spine2.setAccessionDate(this.date_formattter_reverse(resultSet.getString("AccessionDate")));
            book_spine2.setAccessionNo(resultSet.getString("AccessionNo"));
            book_spine2.setAuthor(this.removeScript(resultSet.getString("Author")));
            book_spine2.setBranch(resultSet.getString("Branch"));
            book_spine2.setBranchDesc(resultSet.getString("BranchDesc"));
            List<ISBD> isbd = ISBD.getPunctuation("090");
            System.out.println("TestCall" + resultSet.getString("CallNo"));
            book_spine2.setCallNo(Handler.getSubfield(resultSet.getString("CallNo"), isbd));
            book_spine2.setCatalognDate(this.date_formattter_reverse(resultSet.getString("CatalognDate")));
            book_spine2.setCopyNo(resultSet.getString("CopyNo"));
            book_spine2.setCurrency(resultSet.getString("Currency"));
            book_spine2.setForeignCost(resultSet.getString("ForeignCost"));
            book_spine2.setItemCategory(resultSet.getString("ItemCategory"));
            book_spine2.setItemCategoryDesc(resultSet.getString("ItemCategoryDesc"));
            book_spine2.setLastIndexDate(this.date_formattter_reverse(resultSet.getString("LastIndexDate")));
            book_spine2.setLocalCost(resultSet.getString("LocalCost"));
            book_spine2.setLocation(resultSet.getString("Location"));
            book_spine2.setLocationDesc(resultSet.getString("LocationDesc"));
            book_spine2.setPartNo(resultSet.getString("PartNo"));
            book_spine2.setPublicationYear(this.removeScript(resultSet.getString("PublicationYear")));
            book_spine2.setSMD(resultSet.getString("SMD"));
            book_spine2.setSMDDesc(resultSet.getString("SMDDesc"));
            book_spine2.setSpineItemCategory(resultSet.getString("SpineItemCategory"));
            book_spine2.setSpineSMD(resultSet.getString("SpineSMD"));
            List<ISBD> titleisbd = ISBD.getPunctuation("245");
            book_spine2.setTitle(Handler.getSubfield(resultSet.getString("Title"), titleisbd));
            book_spine2.setUserDefineSpine(resultSet.getString("UserDefineSpine"));
            book_spine2.setVolume(resultSet.getString("Volume"));
            book_spine2.setSpineStatus(resultSet.getString("SpineStatus"));
            System.out.println("This is the spine Status : ->> " + resultSet.getString("SpineStatus"));
            book_spine2.setLabelStatus(resultSet.getString("LabelStatus"));
        }
        return book_spine2;
    }

    public HashMap<String, Object> extract_data_patron(ResultSet resultSet) throws SQLException {
        ArrayList<patron> resultList = new ArrayList<patron>();
        HashMap<String, Object> result = new HashMap<String, Object>();
        ResultSetMetaData metaData = null;
        Integer columnCount = null;
        metaData = resultSet.getMetaData();
        columnCount = metaData.getColumnCount();
        int size = resultSet.getRow();
        while (resultSet.next()) {
            patron patron2 = new patron();
            int i = 0;
            while (i <= columnCount) {
                patron2.setPatronId(resultSet.getString("PatronId"));
                patron2.setName(resultSet.getString("Name"));
                patron2.setTitleAndName(resultSet.getString("TitleAndName"));
                patron2.setPatronCategoryCode(resultSet.getString("PatronCategoryCode"));
                patron2.setPatronCategoryDesc(resultSet.getString("PatronCategoryDesc"));
                patron2.setDepartmentCode(resultSet.getString("DepartmentCode"));
                patron2.setDepartmentName(resultSet.getString("DepartmentName"));
                patron2.setCourseCode(resultSet.getString("CourseCode"));
                patron2.setCourseDesc(resultSet.getString("CourseDesc"));
                patron2.setBranchCode(resultSet.getString("BranchCode"));
                patron2.setBranchDesc(resultSet.getString("BranchDesc"));
                patron2.setICNumber(resultSet.getString("ICNumber"));
                patron2.setDateOfBirth(this.date_formattter_reverse(resultSet.getString("DateOfBirth")));
                patron2.setMembershipDate(this.date_formattter_reverse(resultSet.getString("MembershipDate")));
                patron2.setMembershipExpiryDate(this.date_formattter_reverse(resultSet.getString("MembershipExpiryDate")));
                patron2.setAddress11(resultSet.getString("Address1"));
                patron2.setAddress12(resultSet.getString("Address2"));
                patron2.setAddress21(resultSet.getString("Address1"));
                patron2.setPostCode(resultSet.getString("PostCode"));
                patron2.setTown(resultSet.getString("Town"));
                patron2.setCoprateID(resultSet.getString("CoprateID"));
                ++i;
            }
            resultList.add(patron2);
        }
        result.put("list", resultList);
        result.put("status", "ok");
        System.out.println(new JSONObject(result).toString());
        return result;
    }

    public patron extract_data_patron_single(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = null;
        metaData = resultSet.getMetaData();
        patron patron2 = new patron();
        while (resultSet.next()) {
            patron2.setPatronId(resultSet.getString("PatronId"));
            patron2.setName(resultSet.getString("Name"));
            patron2.setTitleAndName(resultSet.getString("TitleAndName"));
            patron2.setPatronCategoryCode(resultSet.getString("PatronCategoryCode"));
            patron2.setPatronCategoryDesc(resultSet.getString("PatronCategoryDesc"));
            patron2.setDepartmentCode(resultSet.getString("DepartmentCode"));
            patron2.setDepartmentName(resultSet.getString("DepartmentName"));
            patron2.setCourseCode(resultSet.getString("CourseCode"));
            patron2.setCourseDesc(resultSet.getString("CourseDesc"));
            patron2.setBranchCode(resultSet.getString("BranchCode"));
            patron2.setBranchDesc(resultSet.getString("BranchDesc"));
            patron2.setICNumber(resultSet.getString("ICNumber"));
            patron2.setDateOfBirth(this.date_formattter_reverse(resultSet.getString("DateOfBirth")));
            patron2.setMembershipDate(this.date_formattter_reverse(resultSet.getString("MembershipDate")));
            patron2.setMembershipExpiryDate(this.date_formattter_reverse(resultSet.getString("MembershipExpiryDate")));
            patron2.setAddress11(resultSet.getString("Address1"));
            patron2.setAddress12(resultSet.getString("Address2"));
            patron2.setAddress21(resultSet.getString("Address1"));
            patron2.setPostCode(resultSet.getString("PostCode"));
            patron2.setTown(resultSet.getString("Town"));
            patron2.setCoprateID(resultSet.getString("CoprateID"));
        }
        return patron2;
    }

    public static int getCount(String data) {
        int counter = 0;
        int i = 0;
        while (i < data.length()) {
            if (Character.isDigit(data.charAt(i))) {
                ++counter;
            } else if (String.valueOf(data.charAt(i)).contentEquals(".")) {
                ++counter;
            } else if (Character.isSpaceChar(data.charAt(i))) break;
            ++i;
        }
        return counter;
    }

    /*
     * Unable to fully structure code
     */
    public List<String> SplitCallNoDDC(String call_no, String styletype) {
        System.out.println("Cater12" + call_no);
        DDC = new ArrayList<String>();
        datasize = Integer.parseInt(GeneralUtil.DDCDataSize());
        decimal = GeneralUtil.DecimalSplit(styletype);
        sets = "";
        j = 0;
        call_no = call_no.trim();
        counter = GeneralUtil.getCount(call_no);
        System.out.println("Data" + decimal + "Size" + datasize + "Counter" + counter);
        if (decimal.equals("Y")) {
            System.out.println("sssd");
            i = j;
            while (i < call_no.length()) {
                if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                    DDC.add(sets);
                    sets = "";
                    ++j;
                    System.out.println("Length more than 10 at [" + i + "]");
                    break;
                }
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
                ++i;
            }
        } else if (decimal.equals("N") && counter > datasize) {
            System.out.println("Data" + counter + "Size" + datasize);
            i = j;
            while (i < call_no.length()) {
                if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    DDC.add(sets);
                    sets = "";
                    ++j;
                    System.out.println("LengthS more than 10 at [" + i + "]");
                    break;
                }
                if (Character.isSpaceChar(call_no.charAt(i))) {
                    DDC.add(sets);
                    sets = "";
                    ++j;
                    System.out.println("LengthA more than 10 at [" + i + "]");
                    break;
                }
                System.out.println("Length22 more than 10 at [" + i + "]");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
                ++i;
            }
        } else {
            i = j;
            while (i < call_no.length()) {
                System.out.println("loop 2 start at [" + i + "]");
                if (Character.isSpaceChar(call_no.charAt(i))) {
                    if (sets.trim().isEmpty()) {
                        ++j;
                        System.out.println("im breaking at [" + i + "]");
                    } else {
                        System.out.println("break");
                        DDC.add(sets);
                        sets = "";
                    }
                    break;
                }
                if (Character.isDigit(call_no.charAt(i))) {
                    System.out.println("there's a digit at [" + i + "]");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                    System.out.println("there's a digit2");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else {
                    if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                        DDC.add(sets);
                        sets = "";
                        System.out.println("loop 2 ends at [" + i + "]");
                        break;
                    }
                    if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                        DDC.add(sets);
                        sets = "";
                        System.out.println("loop 2 ends at [" + i + "]");
                        break;
                    }
                    if (Character.isLetter(call_no.charAt(i))) {
                        System.out.println("Letter");
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    }
                }
                ++i;
            }
        }
        i = j;
        while (i < call_no.length()) {
            System.out.println("loop 3 start at [" + i + "]");
            if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isDigit(call_no.charAt(i))) {
                System.out.println("loop 3 this is digit [" + i + "]");
                System.out.println(call_no.length() - 1);
                if (i == call_no.length() - 1) {
                    DDC.add(sets);
                    sets = "";
                }
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
                System.out.println("--> " + sets);
                DDC.add(sets);
                sets = "";
                ++j;
                System.out.println("loop 3 ends at [" + i + "]");
                break;
            }
            System.out.println("call no." + DDC + "]");
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            block36: {
                System.out.println("loop 4 start at [" + i + "]");
                isSpace = Character.isSpaceChar(call_no.charAt(i));
                isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                Letter = Character.isLetter(call_no.charAt(i));
                Digit = Character.isDigit(call_no.charAt(i));
                if (!Letter.booleanValue() && !Digit.booleanValue()) break block36;
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                if (i == call_no.length() - 1) {
                    DDC.add(sets);
                    sets = "";
                }
                ++j;
                ** GOTO lbl154
            }
            if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl154
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
                DDC.add(sets);
                sets = "";
                ++j;
lbl154:
                // 3 sources

                System.out.println("loop 4 ends at [" + i + "]");
            }
            ++i;
        }
        System.out.println("SS" + DDC.toString());
        return DDC;
    }

    /*
     * Unable to fully structure code
     */
    public List<String> DDCSplit(String itemCategory, String itemSMD, String call_no, String styletype) {
        block49: {
            System.out.println("Cater12" + call_no);
            DDC = new ArrayList<String>();
            if (call_no == "") break block49;
            datasize = Integer.parseInt(GeneralUtil.DDCDataSize());
            decimal = GeneralUtil.DecimalSplit(styletype);
            sets = "";
            j = 0;
            call_no = call_no.trim();
            counter = GeneralUtil.getCount(call_no);
            if (itemCategory != null && !itemCategory.isEmpty()) {
                System.out.println("Cater12" + itemCategory.trim());
                DDC.add(itemCategory.trim());
            } else {
                sets = " ";
                DDC.add(sets);
            }
            if (itemSMD == null || itemSMD.isEmpty()) {
                sets = "";
            } else if (itemSMD != null && !itemSMD.isEmpty()) {
                DDC.add(itemSMD.trim());
            } else if (itemSMD.equals("N")) {
                sets = "";
            } else {
                sets = " ";
                DDC.add(sets);
            }
            System.out.println("Data" + decimal + "Size" + datasize + "Counter" + counter);
            if (decimal.equals("Y")) {
                System.out.println("sssd");
                i = j;
                while (i < call_no.length()) {
                    if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                        DDC.add(sets);
                        sets = "";
                        ++j;
                        System.out.println("Length more than 10 at [" + i + "]");
                        break;
                    }
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                    ++i;
                }
            } else if (decimal.equals("N") && counter > datasize) {
                System.out.println("Data" + counter + "Size" + datasize);
                i = j;
                while (i < call_no.length()) {
                    if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        DDC.add(sets);
                        sets = "";
                        ++j;
                        System.out.println("LengthS more than 10 at [" + i + "]");
                        break;
                    }
                    if (Character.isSpaceChar(call_no.charAt(i))) {
                        DDC.add(sets);
                        sets = "";
                        ++j;
                        System.out.println("LengthA more than 10 at [" + i + "]");
                        break;
                    }
                    System.out.println("Length22 more than 10 at [" + i + "]");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                    ++i;
                }
            } else {
                i = j;
                while (i < call_no.length()) {
                    System.out.println("loop 2 start at [" + i + "]");
                    if (i == call_no.length() - 1) {
                        System.out.println("break");
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        DDC.add(sets);
                        break;
                    }
                    if (Character.isSpaceChar(call_no.charAt(i))) {
                        if (sets.trim().isEmpty()) {
                            ++j;
                            System.out.println("im breaking at [" + i + "]");
                        } else {
                            System.out.println("break");
                            DDC.add(sets);
                            sets = "";
                        }
                        break;
                    }
                    if (Character.isDigit(call_no.charAt(i))) {
                        System.out.println("there's a digit at [" + i + "]");
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                        System.out.println("there's a digit2");
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else {
                        if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                            DDC.add(sets);
                            sets = "";
                            System.out.println("loop 2 ends at [" + i + "]");
                            break;
                        }
                        if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                            DDC.add(sets);
                            sets = "";
                            System.out.println("loop 2 ends at [" + i + "]");
                            break;
                        }
                        if (Character.isLetter(call_no.charAt(i))) {
                            System.out.println("Letter");
                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                            ++j;
                        }
                    }
                    ++i;
                }
            }
            i = j;
            while (i < call_no.length()) {
                p = Pattern.compile("[^a-z0-9 ]", 2);
                m = p.matcher(String.valueOf(call_no.charAt(i)));
                specialchar = m.find();
                System.out.println("Matrcher" + m.find());
                System.out.println(String.valueOf(j) + "loop 3 start at [" + i + "]" + call_no.charAt(i));
                if (i == call_no.length() - 1) {
                    System.out.println("break");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                    DDC.add(sets);
                    break;
                }
                if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                    System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (Character.isDigit(call_no.charAt(i))) {
                    System.out.println("loop 3 this is digit [" + i + "]");
                    System.out.println(call_no.length() - 1);
                    if (i == call_no.length() - 1) {
                        DDC.add(sets);
                        sets = "";
                    }
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
                    System.out.println("Letter");
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    ++j;
                } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
                    System.out.println("--> " + sets);
                    DDC.add(sets);
                    sets = "";
                    ++j;
                    System.out.println("loop 3 ends at [" + i + "]");
                    break;
                }
                System.out.println("call no." + DDC + "]");
                ++i;
            }
            i = j;
            while (i < call_no.length()) {
                block50: {
                    System.out.println("loop 4 start at [" + i + "]");
                    System.out.println("loop4 start at [" + call_no.charAt(i) + "]");
                    isSpace = Character.isSpaceChar(call_no.charAt(i));
                    isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                    Letter = Character.isLetter(call_no.charAt(i));
                    Digit = Character.isDigit(call_no.charAt(i));
                    if (i == call_no.length() - 1) {
                        System.out.println("break");
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        DDC.add(sets);
                        break;
                    }
                    if (!Letter.booleanValue() && !Digit.booleanValue()) break block50;
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                    if (i == call_no.length() - 1) {
                        DDC.add(sets);
                        sets = "";
                    }
                    ++j;
                    ** GOTO lbl207
                }
                if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl207
                if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                } else {
                    DDC.add(sets);
                    sets = "";
                    ++j;
lbl207:
                    // 3 sources

                    System.out.println("loop 4 ends at [" + i + "]");
                }
                ++i;
            }
            System.out.println("SS" + DDC.toString());
        }
        return DDC;
    }

    /*
     * Unable to fully structure code
     */
    public List<String> LLCSplitTest(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
        System.out.println("TrialLbl" + call_no);
        spineLbl = Config_Print.LblMstr(splitStyle);
        listPrint = new ArrayList<String>();
        for (Config_Print k : spineLbl) {
            System.out.println("Testing1" + k.getLBL01FIELD() + k.getLBL01MTYPE() + k.getLBL01PRINT());
            if (!k.getLBL01MTYPE().equals("P") || !k.getLBL01PRINT().equals("Y")) continue;
            System.out.println("Testing" + k.getLBL01FIELD());
            listPrint.add(k.getLBL01FIELD());
        }
        orilength = call_no.length();
        LLC = new ArrayList<String>();
        sets = "";
        j = 0;
        call_no = call_no.trim();
        list = 0;
        while (list < listPrint.size()) {
            block47: {
                block55: {
                    block53: {
                        block52: {
                            block51: {
                                block50: {
                                    block49: {
                                        block48: {
                                            if (!((String)listPrint.get(list)).equals("SpineItemCategory")) break block48;
                                            if (itemCategory != null && !itemCategory.isEmpty()) {
                                                System.out.println("Cater" + itemCategory.trim());
                                                LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemCategory.trim() + "</strong><br>");
                                            } else {
                                                sets = " ";
                                                LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                            }
                                            break block47;
                                        }
                                        if (!((String)listPrint.get(list)).equals("SpineSMD")) break block49;
                                        if (itemSMD != null && !itemSMD.isEmpty()) {
                                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemSMD.trim() + "</strong><br>");
                                        } else {
                                            sets = " ";
                                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                        }
                                        break block47;
                                    }
                                    if (!((String)listPrint.get(list)).equals("Subject")) break block50;
                                    i = j;
                                    while (i < call_no.length()) {
                                        if (Character.isLetter(call_no.charAt(i))) {
                                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                            ++j;
                                        } else if (!Character.isLetter(call_no.charAt(i))) {
                                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                            if (!Character.isDigit(call_no.charAt(i)) && Character.isSpaceChar(call_no.charAt(i))) {
                                                ++j;
                                            }
                                            sets = "";
                                            System.out.println("loop 1 ends at [" + i + "]--> " + sets);
                                            break block47;
                                        }
                                        System.out.println("LLC" + LLC.toString());
                                        ++i;
                                    }
                                    break block47;
                                }
                                if (!((String)listPrint.get(list)).equals("Cutter1")) break block51;
                                System.out.println("Cutter1in");
                                i = j;
                                while (i < call_no.length()) {
                                    System.out.println("loop 2 start at [" + i + "]");
                                    if (Character.isSpaceChar(call_no.charAt(i))) {
                                        System.out.println("there's a space at [" + i + "]");
                                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                        ++j;
                                    } else if (Character.isDigit(call_no.charAt(i))) {
                                        System.out.println("there's a digit at [" + i + "]");
                                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                        ++j;
                                    } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                                        System.out.println("there's a digit2");
                                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                        ++j;
                                    } else {
                                        if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                            sets = "";
                                            System.out.println("loop 2 ends at [" + i + "]");
                                            break block47;
                                        }
                                        if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                            sets = "";
                                            System.out.println("loop 2 ends at [" + i + "]");
                                            break block47;
                                        }
                                    }
                                    System.out.println("LLC" + LLC.toString());
                                    ++i;
                                }
                                break block47;
                            }
                            if (!((String)listPrint.get(list)).equals("Cutter2")) break block52;
                            i = j;
                            while (i < call_no.length()) {
                                System.out.println("loop 3 start at [" + i + "]" + call_no.length());
                                if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                                    System.out.println("---> " + sets);
                                    System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
                                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                    ++j;
                                } else if (Character.isDigit(call_no.charAt(i))) {
                                    System.out.println("---->11 " + sets);
                                    if (call_no.length() == orilength - 1) {
                                        System.out.println(call_no.length() + orilength - 1);
                                        LLC.add(sets);
                                        sets = "";
                                    } else {
                                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                        ++j;
                                    }
                                } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
                                    System.out.println("-----> 12" + sets);
                                    sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                    ++j;
                                } else if (Character.isSpaceChar(call_no.charAt(i))) {
                                    System.out.println("-->13 " + sets);
                                    LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                    sets = "";
                                    ++j;
                                    System.out.println("loop 3 ends at [" + i + "]");
                                    break block47;
                                }
                                System.out.println("LLC" + LLC.toString());
                                ++i;
                            }
                            break block47;
                        }
                        if (!((String)listPrint.get(list)).equals("Cutter3")) break block53;
                        i = j;
                        while (i < call_no.length()) {
                            block54: {
                                System.out.println("loop 4 start at [" + i + "]" + call_no.charAt(i));
                                isSpace = Character.isSpaceChar(call_no.charAt(i));
                                isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                                Letter = Character.isLetter(call_no.charAt(i));
                                Digit = Character.isDigit(call_no.charAt(i));
                                if (!Letter.booleanValue() && !Digit.booleanValue()) break block54;
                                System.out.println("Letter");
                                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                                if (i == call_no.length() - 1) {
                                    ++j;
                                    LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                    sets = "";
                                } else if (Character.isSpaceChar(call_no.charAt(i + 1))) {
                                    ++j;
                                    LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                    sets = "";
                                    break block47;
                                }
                                ++j;
                                ** GOTO lbl170
                            }
                            if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl170
                            System.out.println("Space");
                            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                            } else {
                                LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                sets = "";
                                ++j;
lbl170:
                                // 3 sources

                                System.out.println("loop 4 ends at [" + i + "]");
                                System.out.println("LLC" + LLC.toString());
                            }
                            ++i;
                        }
                        break block47;
                    }
                    if (!((String)listPrint.get(list)).equals("Year")) break block55;
                    i = j;
                    while (i < call_no.length()) {
                        block56: {
                            System.out.println("loop 5 start at [" + i + "]" + call_no.charAt(i));
                            isSpace = Character.isSpaceChar(call_no.charAt(i));
                            isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                            Letter = Character.isLetter(call_no.charAt(i));
                            Digit = Character.isDigit(call_no.charAt(i));
                            if (!Letter.booleanValue() && !Digit.booleanValue()) break block56;
                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                            if (i == call_no.length() - 1) {
                                LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                                sets = "";
                            }
                            ++j;
                            ** GOTO lbl201
                        }
                        if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl201
                        if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        } else {
                            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
lbl201:
                            // 3 sources

                            System.out.println("loop 4 ends at [" + i + "]");
                            System.out.println("LLC" + LLC.toString());
                        }
                        ++i;
                    }
                    break block47;
                }
                if (((String)listPrint.get(list)).equals("SpineVolume")) {
                    if (volume != null && !volume.isEmpty()) {
                        LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + volume.trim() + "</strong><br>");
                    }
                } else if (((String)listPrint.get(list)).equals("Copy")) {
                    if (copyNo != null && !copyNo.isEmpty()) {
                        LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + copyNo.trim() + "</strong><br>");
                    }
                } else if (((String)listPrint.get(list)).equals("PartNo") && partNo != null && !partNo.isEmpty()) {
                    LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + partNo.trim() + "</strong><br>");
                }
            }
            ++list;
        }
        System.out.println("LLC1" + LLC.toString());
        return LLC;
    }

    public List<String> LLCSplitTestSpine(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> LLC = new ArrayList<String>();
        Pattern pattern = Pattern.compile("([A-Z]+)?(\\d+\\.?\\d+)?\\s?(\\.?[A-Z]\\d+)?([A-Z]\\d+)?([A-Z]\\d+)?\\s?(\\d+)?");
        Matcher match = pattern.matcher(call_no);
        if (match.find()) {
            if (match.group(1) != null) {
                jsonObject.put("Subject", (Object)match.group(1));
                LLC.add("<strong class='Subject'>" + match.group(1) + "</strong><br>");
            }
            if (match.group(2) != null) {
                jsonObject.put("ClassNo", (Object)match.group(2));
                LLC.add("<strong class='ClassNo'>" + match.group(2) + "</strong><br>");
            }
            if (match.group(3) != null) {
                jsonObject.put("Cutter1", (Object)match.group(3));
                LLC.add("<strong class='Cutter1'>" + match.group(3) + "</strong><br>");
            }
            if (match.group(4) != null) {
                jsonObject.put("Cutter2", (Object)match.group(4));
                LLC.add("<strong class='Cutter2'>" + match.group(4) + "</strong><br>");
            }
            if (match.group(5) != null) {
                jsonObject.put("Cutter3", (Object)match.group(5));
                LLC.add("<strong class='Cutter3'>" + match.group(5) + "</strong><br>");
            }
            if (match.group(6) != null) {
                jsonObject.put("Year", (Object)match.group(6));
                LLC.add("<strong class='Year'>" + match.group(6) + "</strong><br>");
            }
        }
        return LLC;
    }

    public List<String> LLCSplitTest_UserDefineSpine(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
        int subject = 1;
        List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
        ArrayList<String> listPrint = new ArrayList<String>();
        for (Config_Print k : spineLbl) {
            if (!k.getLBL01MTYPE().equals("P") || !k.getLBL01PRINT().equals("Y")) continue;
            listPrint.add(k.getLBL01FIELD());
        }
        int callNoLength = call_no.length();
        ArrayList<String> LLC = new ArrayList<String>();
        String sets = "";
        int j = 0;
        if (itemCategory != null && !itemCategory.isEmpty()) {
            LLC.add("<strong class='DefineSpineItemCategory'>" + itemCategory.trim() + "</strong><br>");
        } else {
            sets = " ";
            LLC.add("<strong class='DefineSpineItemCategory'" + sets + "</strong><br>");
        }
        int i = 0;
        while (i < call_no.length()) {
            if (!Character.isWhitespace(call_no.charAt(i))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else {
                LLC.add("<strong class='DefineSubject" + subject + "'>" + sets + "</strong><br>");
                sets = "";
                ++subject;
            }
            ++i;
        }
        return LLC;
    }

    public List<String> DDCSplitTest(String itemCategory, String itemSMD, String call_no, String styletype, String splitStyle) {
        ArrayList<String> DDC = new ArrayList<String>();
        int datasize = Integer.parseInt(GeneralUtil.DDCDataSize());
        String decimal = GeneralUtil.DecimalSplit(styletype);
        String sets = "";
        int j = 0;
        call_no = call_no.trim();
        int counter = GeneralUtil.getCount(call_no);
        List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
        ArrayList<String> listPrint = new ArrayList<String>();
        for (Config_Print k : spineLbl) {
            if (!k.getLBL01MTYPE().equals("P") || !k.getLBL01PRINT().equals("Y")) continue;
            listPrint.add(k.getLBL01FIELD());
        }
        int list = 0;
        while (list < listPrint.size()) {
            boolean specialchar;
            Matcher m;
            Pattern p;
            if (((String)listPrint.get(list)).equals("SpineItemCategory")) {
                if (itemCategory != null && !itemCategory.isEmpty()) {
                    DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemCategory.trim() + "</strong><br>");
                } else {
                    sets = " ";
                    DDC.add(sets);
                }
            } else if (((String)listPrint.get(list)).equals("SpineSMD")) {
                if (itemSMD == null || itemSMD.isEmpty()) {
                    sets = "";
                } else if (itemSMD != null && !itemSMD.isEmpty()) {
                    DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemSMD.trim() + "</strong><br>");
                } else if (itemSMD.equals("N")) {
                    sets = "";
                } else {
                    sets = " ";
                    DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                }
            }
            if (decimal.equals("Y")) {
                if (((String)listPrint.get(list)).equals("Cutter1")) {
                    int i = j;
                    while (i < call_no.length()) {
                        if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
                            break;
                        }
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        ++i;
                    }
                }
            } else if (decimal.equals("N") && counter > datasize) {
                if (((String)listPrint.get(list)).equals("Cutter1")) {
                    int i = j;
                    while (i < call_no.length()) {
                        if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
                            break;
                        }
                        if (Character.isSpaceChar(call_no.charAt(i))) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
                            break;
                        }
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        ++i;
                    }
                }
            } else if (((String)listPrint.get(list)).equals("Cutter1")) {
                int i = j;
                while (i < call_no.length()) {
                    p = Pattern.compile("[^a-z0-9 ]", 2);
                    m = p.matcher(String.valueOf(call_no.charAt(i)));
                    specialchar = m.find();
                    if (i == call_no.length() - 1) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                        break;
                    }
                    if (Character.isSpaceChar(call_no.charAt(i))) {
                        if (sets.trim().isEmpty()) {
                            ++j;
                        } else if (Character.isLetter(call_no.charAt(i + 1)) || specialchar) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
                        } else {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                        }
                        break;
                    }
                    if (Character.isDigit(call_no.charAt(i))) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else {
                        if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            break;
                        }
                        if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            break;
                        }
                    }
                    ++i;
                }
            }
            if (((String)listPrint.get(list)).equals("Cutter2")) {
                int i = j;
                while (i < call_no.length()) {
                    p = Pattern.compile("[^a-z0-9 ]", 2);
                    m = p.matcher(String.valueOf(call_no.charAt(i)));
                    specialchar = m.find();
                    if (i == call_no.length() - 1) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                        break;
                    }
                    if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (Character.isDigit(call_no.charAt(i))) {
                        if (i == call_no.length() - 1) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                        }
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                    } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
                        DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                        sets = "";
                        ++j;
                        break;
                    }
                    ++i;
                }
            }
            if (((String)listPrint.get(list)).equals("Cutter3")) {
                int i = j;
                while (i < call_no.length()) {
                    Boolean isSpace = Character.isSpaceChar(call_no.charAt(i));
                    Boolean isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                    Boolean Letter = Character.isLetter(call_no.charAt(i));
                    Boolean Digit = Character.isDigit(call_no.charAt(i));
                    if (i == call_no.length() - 1) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        ++j;
                        DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                        break;
                    }
                    if (Letter.booleanValue() || Digit.booleanValue()) {
                        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        if (i == call_no.length() - 1) {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                        }
                        ++j;
                    } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
                        if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                        } else {
                            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
                            sets = "";
                            ++j;
                        }
                    }
                    ++i;
                }
            }
            ++list;
        }
        return DDC;
    }

    public List<String> DDCSplitTest(String itemCategory, String itemSMD, String call_no, String branchNo, String styletype, String splitStyle) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> DDC = new ArrayList<String>();
        Pattern pattern = Pattern.compile("([A-Z]+[/]+\\d+[/]+\\d+)?([A-Z]+\\d+)?([A-Z]+)?\\s?\\s?(\\d+[/][A-Z]+)?([A-Z]+\\d+)?(\\d+)?\\s?(\\.\\d+)?\\s?(\\d+[/]?\\d+)?(\\.\\d+)?([A-Z]+)?\\s?(\\d+)?([A-Z]+)?\\s?((\\([^\\[]*\\))?)?");
        Matcher match = pattern.matcher(call_no);
        List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
        ArrayList<String> listPrint = new ArrayList<String>();
        for (Config_Print k : spineLbl) {
            if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y")) {
                listPrint.add(k.getLBL01FIELD());
            }
            if (!k.getLBL01FIELD().equals("Location") || !k.getLBL01MTYPE().equals("P") || !k.getLBL01PRINT().equals("Y")) continue;
            DDC.add("<strong class='Location'>" + branchNo + "</strong><br>");
        }
        int list = 0;
        while (list < listPrint.size()) {
            if (((String)listPrint.get(list)).equals("SpineItemCategory") && itemCategory != null && !itemCategory.isEmpty()) {
                DDC.add("<class='group0'>" + itemCategory.trim() + "</><br>");
            }
            ++list;
        }
        if (match.find()) {
            if (match.group(1) != null) {
                jsonObject.put("Group1", (Object)match.group(1));
                DDC.add("<class='Group1'>" + match.group(1) + "</><br>");
            }
            if (match.group(2) != null) {
                jsonObject.put("Group2", (Object)match.group(2));
                DDC.add("<class='Group2'>" + match.group(2) + "</><br>");
            }
            if (match.group(3) != null) {
                jsonObject.put("Group3", (Object)match.group(3));
                DDC.add("<class='Group3'>" + match.group(3) + "</><br>");
            }
            if (match.group(4) != null) {
                jsonObject.put("Group4", (Object)match.group(4));
                DDC.add("<class='Group4'>" + match.group(4) + "</><br>");
            }
            if (match.group(5) != null) {
                jsonObject.put("Group5", (Object)match.group(5));
                DDC.add("<class='Group5'>" + match.group(5) + "</><br>");
            }
            if (match.group(6) != null) {
                jsonObject.put("Group6", (Object)match.group(6));
                DDC.add("<class='Group6'>" + match.group(6) + "</><br>");
            }
            if (match.group(7) != null) {
                jsonObject.put("Group7", (Object)match.group(7));
                DDC.add("<class='Group7'>" + match.group(7) + "</><br>");
            }
            if (match.group(8) != null) {
                jsonObject.put("Group8", (Object)match.group(8));
                DDC.add("<class='Group8'>" + match.group(8) + "</><br>");
            }
            if (match.group(9) != null) {
                jsonObject.put("Group9", (Object)match.group(9));
                DDC.add("<class='Group9'>" + match.group(9) + "</><br>");
            }
            if (match.group(10) != null) {
                jsonObject.put("Group10", (Object)match.group(10));
                DDC.add("<class='Group10'>" + match.group(10) + "</><br>");
            }
            if (match.group(11) != null) {
                jsonObject.put("Group11", (Object)match.group(11));
                DDC.add("<class='Group11'>" + match.group(11) + "</><br>");
            }
            if (match.group(12) != null) {
                jsonObject.put("Group12", (Object)match.group(12));
                DDC.add("<class='Group12'>" + match.group(12) + "</><br>");
            }
            if (match.group(13) != null) {
                jsonObject.put("Group13", (Object)match.group(13));
                DDC.add("<class='Group13'>" + match.group(13) + "</><br>");
            }
        }
        return DDC;
    }

    /*
     * Unable to fully structure code
     */
    public List<String> LLCSplit(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo) {
        orilength = call_no.length();
        LLC = new ArrayList<String>();
        sets = "";
        j = 0;
        call_no = call_no.trim();
        if (itemCategory != null && !itemCategory.isEmpty()) {
            System.out.println("Cater" + itemCategory.trim());
            LLC.add(itemCategory.trim());
        } else {
            sets = " ";
            LLC.add(sets);
        }
        if (itemSMD != null && !itemSMD.isEmpty()) {
            LLC.add(itemSMD.trim());
        } else {
            sets = " ";
            LLC.add(sets);
        }
        i = j;
        while (i < call_no.length()) {
            if (Character.isLetter(call_no.charAt(i))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (!Character.isLetter(call_no.charAt(i))) {
                LLC.add(sets);
                sets = "";
                System.out.println("loop 1 ends at [" + i + "]--> " + sets);
                break;
            }
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            if (Character.isSpaceChar(call_no.charAt(i))) {
                if (sets.trim().isEmpty()) {
                    ++j;
                    break;
                }
                ++j;
                LLC.add(sets);
                sets = "";
                break;
            }
            if (Character.isDigit(call_no.charAt(i))) {
                System.out.println("there's a digit at [" + i + "]");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                System.out.println("there's a digit2");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else {
                if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                    LLC.add(sets);
                    sets = "";
                    System.out.println("loop 2 ends at [" + i + "]");
                    break;
                }
                if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                    LLC.add(sets);
                    sets = "";
                    System.out.println("loop 2 ends at [" + i + "]");
                    break;
                }
            }
            System.out.println("LLC" + LLC.toString());
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            System.out.println("loop 3 start at [" + i + "]" + call_no.length());
            if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                System.out.println("---> " + sets);
                System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isDigit(call_no.charAt(i))) {
                System.out.println("---->11 " + sets);
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
                System.out.println("-----> 12" + sets);
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isSpaceChar(call_no.charAt(i))) {
                System.out.println("-->13 " + sets);
                LLC.add(sets);
                sets = "";
                ++j;
                System.out.println("loop 3 ends at [" + i + "]");
                break;
            }
            System.out.println("LLC" + LLC.toString());
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            block32: {
                System.out.println("loop 4 start at [" + i + "]");
                isSpace = Character.isSpaceChar(call_no.charAt(i));
                isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                Letter = Character.isLetter(call_no.charAt(i));
                Digit = Character.isDigit(call_no.charAt(i));
                if (!Letter.booleanValue() && !Digit.booleanValue()) break block32;
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                if (i == call_no.length() - 1) {
                    LLC.add(sets);
                    sets = "";
                }
                ++j;
                ** GOTO lbl125
            }
            if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl125
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
                LLC.add(sets);
                sets = "";
                ++j;
lbl125:
                // 3 sources

                System.out.println("loop 4 ends at [" + i + "]");
                System.out.println("LLC" + LLC.toString());
            }
            ++i;
        }
        if (volume != null && !volume.isEmpty()) {
            LLC.add(volume.trim());
        }
        if (copyNo != null && !copyNo.isEmpty()) {
            LLC.add(copyNo.trim());
        }
        if (partNo != null && !partNo.isEmpty()) {
            LLC.add(partNo.trim());
        }
        System.out.println("LLC1" + LLC.toString());
        return LLC;
    }

    /*
     * Unable to fully structure code
     */
    public List<String> bookLLCSplit(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo) {
        LLC = new ArrayList<String>();
        sets = "";
        j = 0;
        call_no = call_no.trim();
        if (itemCategory != null && !itemCategory.isEmpty()) {
            System.out.println("Cater" + itemCategory.trim());
            LLC.add(itemCategory.trim());
        } else {
            sets = " ";
            LLC.add(sets);
        }
        if (itemSMD != null && !itemSMD.isEmpty()) {
            LLC.add(itemSMD.trim());
        } else {
            sets = " ";
            LLC.add(sets);
        }
        System.out.println("SDDS" + call_no.length());
        i = j;
        while (i < call_no.length()) {
            if (Character.isLetter(call_no.charAt(i))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (!Character.isLetter(call_no.charAt(i))) {
                LLC.add(sets);
                sets = "";
                System.out.println("loop 1 ends at [" + i + "]--> " + sets);
                break;
            }
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            System.out.println("loop 2 start at [" + i + "]");
            if (Character.isSpaceChar(call_no.charAt(i))) {
                if (sets.trim().isEmpty()) {
                    ++j;
                    System.out.println("im breaking at [" + i + "]");
                    break;
                }
                ++j;
                LLC.add(sets);
                sets = "";
                break;
            }
            if (Character.isDigit(call_no.charAt(i))) {
                System.out.println("there's a digit at [" + i + "]");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
                System.out.println("there's a digit2");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else {
                if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
                    LLC.add(sets);
                    sets = "";
                    System.out.println("loop 2 ends at [" + i + "]");
                    break;
                }
                if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
                    LLC.add(sets);
                    sets = "";
                    System.out.println("loop 2 ends at [" + i + "]");
                    break;
                }
            }
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            System.out.println("loop 3 start at [" + i + "]");
            if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
                System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isDigit(call_no.charAt(i))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isLetter(call_no.charAt(i))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                ++j;
            } else if (Character.isSpaceChar(call_no.charAt(i))) {
                System.out.println("--> " + sets);
                LLC.add(sets);
                sets = "";
                ++j;
                System.out.println("loop 3 ends at [" + i + "]");
                break;
            }
            ++i;
        }
        i = j;
        while (i < call_no.length()) {
            block35: {
                System.out.println("loop 4 start at [" + i + "]");
                isSpace = Character.isSpaceChar(call_no.charAt(i));
                isPeriod = String.valueOf(call_no.charAt(i)).contentEquals(".");
                Letter = Character.isLetter(call_no.charAt(i));
                Digit = Character.isDigit(call_no.charAt(i));
                if (!Letter.booleanValue() && !Digit.booleanValue()) break block35;
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
                if (i == call_no.length() - 1) {
                    LLC.add(sets);
                    sets = "";
                }
                ++j;
                ** GOTO lbl122
            }
            if (!isSpace.booleanValue() && !isPeriod.booleanValue()) ** GOTO lbl122
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
                sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
                LLC.add(sets);
                sets = "";
                ++j;
lbl122:
                // 3 sources

                System.out.println("loop 4 ends at [" + i + "]");
            }
            ++i;
        }
        if (volume != null && !volume.isEmpty()) {
            LLC.add(volume.trim());
        } else {
            LLC.add("");
        }
        if (copyNo != null && !copyNo.isEmpty()) {
            LLC.add(copyNo.trim());
        } else {
            LLC.add("");
        }
        if (partNo != null && !partNo.isEmpty()) {
            LLC.add(partNo.trim());
        } else {
            LLC.add("");
        }
        return LLC;
    }
}
