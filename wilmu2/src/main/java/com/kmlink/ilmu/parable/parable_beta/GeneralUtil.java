package com.kmlink.ilmu.parable.parable_beta;

import com.kmlink.ilmu.cataloging.Global.CheckingData;
import com.kmlink.ilmu.parable.parable_beta.object.book_spine;
import com.kmlink.ilmu.parable.parable_beta.object.patron;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.wilmu.foundation.glflag2.data.GetGlflag2;
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
    String query = 
      
      "Select LB01SIZE FROM LBMSTR WHERE LB01TPLNAME='BOOKLBL2' AND LB01FIELD='LABEL'";
    String dataSize = null;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next())
        dataSize = rs.getString("LB01SIZE"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
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
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        if (rs.getString("GL99VALUE").equals("Y"))
          exist = true; 
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return exist;
  }
  
  public static String DecimalSplit(String lbltype) {
    String query = "Select LB01DECIMAL FROM LBMSTR WHERE LB01MTYPE='N' AND LB01TPLGRP='" + 
      
      lbltype + "'";
    String decimalsplit = null;
    System.out.println("Decimal" + query);
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next())
        decimalsplit = rs.getString("LB01DECIMAL"); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return decimalsplit;
  }
  
  public static int CharLen(String lbltype) {
    String query = "Select LB01CHARLEN FROM LBMSTR WHERE LB01MTYPE='N' AND LB01TPLGRP='" + 
      
      lbltype + "'";
    int length = 0;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next())
        length = Integer.parseInt(rs.getString("LB01CHARLEN")); 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } 
    } 
    return length;
  }
  
  public static boolean printAcq() {
    String query = "Select GL99VALUE FROM GLFLAG2 WHERE GL99FUNC='PRINTACQ' ";
    boolean exist = false;
    try {
      conn = DBConnection.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(query);
      while (rs.next()) {
        if (rs.getString("GL99VALUE").equals("Y"))
          exist = true; 
      } 
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        conn.close();
      } catch (SQLException e) {
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
      } catch (ParseException e) {
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
      } catch (ParseException e) {
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
    List<book_spine> resultList = new ArrayList<>();
    HashMap<String, Object> result = new HashMap<>();
    ResultSetMetaData metaData = null;
    Integer columnCount = null;
    metaData = resultSet.getMetaData();
    columnCount = Integer.valueOf(metaData.getColumnCount());
    ArrayList<String> arrayList = new ArrayList<>();
    while (resultSet.next()) {
      book_spine book_spine = new book_spine();
      int i = 1;
      System.out.println("Test11" + resultSet.getString("Title"));
      book_spine.setAccessionDate(date_formattter_reverse(resultSet.getString("AccessionDate")));
      book_spine.setAccessionNo(resultSet.getString("AccessionNo"));
      book_spine.setAuthor(Handler.removeSubfield(resultSet.getString("Author")));
      if (resultSet.getString("CallNo") != null) {
        List<ISBD> isbd = ISBD.getPunctuation("090");
        book_spine.setCallNo(Handler.getSubfield(resultSet.getString("CallNo"), isbd));
      } 
      book_spine.setCatalognDate(date_formattter_reverse(resultSet.getString("CatalognDate")));
      book_spine.setCopyNo(Handler.ifIsNull(resultSet.getString("CopyNo")));
      book_spine.setCurrency(Handler.ifIsNull(resultSet.getString("Currency")));
      book_spine.setForeignCost(resultSet.getString("ForeignCost"));
      book_spine.setItemCategoryDesc(resultSet.getString("ItemCategoryDesc"));
      book_spine.setLastIndexDate(date_formattter_reverse(resultSet.getString("LastIndexDate")));
      book_spine.setLocalCost(resultSet.getString("LocalCost"));
      book_spine.setLocationDesc(resultSet.getString("LocationDesc"));
      book_spine.setSMD(resultSet.getString("SMD"));
      book_spine.setSMDDesc(resultSet.getString("SMDDesc"));
      book_spine.setBranch(resultSet.getString("ControlNo"));
      List<ISBD> titleisbd = ISBD.getPunctuation("245");
      book_spine.setTitle(Handler.getSubfield(resultSet.getString("Title"), titleisbd));
      book_spine.setUserDefineSpine(Handler.ifIsNull(resultSet.getString("UserDefineSpine")));
      book_spine.setVolume(Handler.ifIsNull(resultSet.getString("Volume")));
      i++;
      resultList.add(book_spine);
    } 
    result.put("list", resultList);
    result.put("status", "ok");
    System.out.println("Listss" + (new JSONObject(result)).toString());
    return result;
  }
  
  public book_spine extract_book_spine(ResultSet resultSet) throws SQLException {
    List<book_spine> resultList = new ArrayList<>();
    HashMap<String, Object> result = new HashMap<>();
    ResultSetMetaData metaData = null;
    Integer columnCount = null;
    metaData = resultSet.getMetaData();
    columnCount = Integer.valueOf(metaData.getColumnCount());
    int size = resultSet.getRow();
    book_spine book_spine = new book_spine();
    while (resultSet.next()) {
      book_spine.setAccessionDate(date_formattter_reverse(resultSet.getString("AccessionDate")));
      book_spine.setAccessionNo(resultSet.getString("AccessionNo"));
      book_spine.setAuthor(removeScript(resultSet.getString("Author")));
      book_spine.setBranch(resultSet.getString("Branch"));
      book_spine.setBranchDesc(resultSet.getString("BranchDesc"));
      List<ISBD> isbd = ISBD.getPunctuation("090");
      System.out.println("TestCall" + resultSet.getString("CallNo"));
      book_spine.setCallNo(Handler.getSubfield(resultSet.getString("CallNo"), isbd));
      book_spine.setCatalognDate(date_formattter_reverse(resultSet.getString("CatalognDate")));
      book_spine.setCopyNo(resultSet.getString("CopyNo"));
      book_spine.setCurrency(resultSet.getString("Currency"));
      book_spine.setForeignCost(resultSet.getString("ForeignCost"));
      book_spine.setItemCategory(resultSet.getString("ItemCategory"));
      book_spine.setItemCategoryDesc(resultSet.getString("ItemCategoryDesc"));
      book_spine.setLastIndexDate(date_formattter_reverse(resultSet.getString("LastIndexDate")));
      book_spine.setLocalCost(resultSet.getString("LocalCost"));
      book_spine.setLocation(resultSet.getString("Location"));
      book_spine.setLocationDesc(resultSet.getString("LocationDesc"));
      book_spine.setPartNo(resultSet.getString("PartNo"));
      book_spine.setPublicationYear(removeScript(resultSet.getString("PublicationYear")));
      book_spine.setSMD(resultSet.getString("SMD"));
      book_spine.setSMDDesc(resultSet.getString("SMDDesc"));
      book_spine.setSpineItemCategory(resultSet.getString("SpineItemCategory"));
      book_spine.setSpineSMD(resultSet.getString("SpineSMD"));
      List<ISBD> titleisbd = ISBD.getPunctuation("245");
      book_spine.setTitle(Handler.getSubfield(resultSet.getString("Title"), titleisbd));
      book_spine.setUserDefineSpine(resultSet.getString("UserDefineSpine"));
      book_spine.setVolume(resultSet.getString("Volume"));
      book_spine.setSpineStatus(resultSet.getString("SpineStatus"));
      System.out.println("This is the spine Status : ->> " + resultSet.getString("SpineStatus"));
      book_spine.setLabelStatus(resultSet.getString("LabelStatus"));
    } 
    return book_spine;
  }
  
  public HashMap<String, Object> extract_data_patron(ResultSet resultSet) throws SQLException {
    List<patron> resultList = new ArrayList<>();
    HashMap<String, Object> result = new HashMap<>();
    ResultSetMetaData metaData = null;
    Integer columnCount = null;
    metaData = resultSet.getMetaData();
    columnCount = Integer.valueOf(metaData.getColumnCount());
    int size = resultSet.getRow();
    while (resultSet.next()) {
      patron patron = new patron();
      int i = 0;
      while (i <= columnCount.intValue()) {
        patron.setPatronId(resultSet.getString("PatronId"));
        patron.setName(resultSet.getString("Name"));
        patron.setTitleAndName(resultSet.getString("TitleAndName"));
        patron.setPatronCategoryCode(resultSet.getString("PatronCategoryCode"));
        patron.setPatronCategoryDesc(resultSet.getString("PatronCategoryDesc"));
        patron.setDepartmentCode(resultSet.getString("DepartmentCode"));
        patron.setDepartmentName(resultSet.getString("DepartmentName"));
        patron.setCourseCode(resultSet.getString("CourseCode"));
        patron.setCourseDesc(resultSet.getString("CourseDesc"));
        patron.setBranchCode(resultSet.getString("BranchCode"));
        patron.setBranchDesc(resultSet.getString("BranchDesc"));
        patron.setICNumber(resultSet.getString("ICNumber"));
        patron.setDateOfBirth(date_formattter_reverse(resultSet.getString("DateOfBirth")));
        patron.setMembershipDate(date_formattter_reverse(resultSet.getString("MembershipDate")));
        patron.setMembershipExpiryDate(date_formattter_reverse(resultSet.getString("MembershipExpiryDate")));
        patron.setAddress11(resultSet.getString("Address1"));
        patron.setAddress12(resultSet.getString("Address2"));
        patron.setAddress21(resultSet.getString("Address1"));
        patron.setPostCode(resultSet.getString("PostCode"));
        patron.setTown(resultSet.getString("Town"));
        patron.setCoprateID(resultSet.getString("CoprateID"));
        i++;
      } 
      resultList.add(patron);
    } 
    result.put("list", resultList);
    result.put("status", "ok");
    System.out.println((new JSONObject(result)).toString());
    return result;
  }
  
  public patron extract_data_patron_single(ResultSet resultSet) throws SQLException {
    ResultSetMetaData metaData = null;
    metaData = resultSet.getMetaData();
    patron patron = new patron();
    while (resultSet.next()) {
      patron.setPatronId(resultSet.getString("PatronId"));
      patron.setName(resultSet.getString("Name"));
      patron.setTitleAndName(resultSet.getString("TitleAndName"));
      patron.setPatronCategoryCode(resultSet.getString("PatronCategoryCode"));
      patron.setPatronCategoryDesc(resultSet.getString("PatronCategoryDesc"));
      patron.setDepartmentCode(resultSet.getString("DepartmentCode"));
      patron.setDepartmentName(resultSet.getString("DepartmentName"));
      patron.setCourseCode(resultSet.getString("CourseCode"));
      patron.setCourseDesc(resultSet.getString("CourseDesc"));
      patron.setBranchCode(resultSet.getString("BranchCode"));
      patron.setBranchDesc(resultSet.getString("BranchDesc"));
      patron.setICNumber(resultSet.getString("ICNumber"));
      patron.setDateOfBirth(date_formattter_reverse(resultSet.getString("DateOfBirth")));
      patron.setMembershipDate(date_formattter_reverse(resultSet.getString("MembershipDate")));
      patron.setMembershipExpiryDate(date_formattter_reverse(resultSet.getString("MembershipExpiryDate")));
      patron.setAddress11(resultSet.getString("Address1"));
      patron.setAddress12(resultSet.getString("Address2"));
      patron.setAddress21(resultSet.getString("Address1"));
      patron.setPostCode(resultSet.getString("PostCode"));
      patron.setTown(resultSet.getString("Town"));
      patron.setCoprateID(resultSet.getString("CoprateID"));
    } 
    return patron;
  }
  
  public static int getCount(String data) {
    int counter = 0;
    for (int i = 0; i < data.length(); i++) {
      if (Character.isDigit(data.charAt(i))) {
        counter++;
      } else if (String.valueOf(data.charAt(i)).contentEquals(".")) {
        counter++;
      } else if (Character.isSpaceChar(data.charAt(i))) {
        break;
      } 
    } 
    return counter;
  }
  
  public List<String> SplitCallNoDDC(String call_no, String styletype) {
    System.out.println("Cater12" + call_no);
    List<String> DDC = new ArrayList<>();
    int datasize = Integer.parseInt(DDCDataSize());
    String decimal = DecimalSplit(styletype);
    String sets = "";
    int j = 0;
    call_no = call_no.trim();
    int counter = getCount(call_no);
    System.out.println("Data" + decimal + "Size" + datasize + "Counter" + counter);
    if (decimal.equals("Y")) {
      System.out.println("sssd");
      for (int k = j; k < call_no.length(); k++) {
        if (Character.isDigit(call_no.charAt(k)) && String.valueOf(call_no.charAt(k + 1)).contentEquals(".")) {
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("Length more than 10 at [" + k + "]");
          break;
        } 
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
        j++;
      } 
    } else if (decimal.equals("N") && counter > datasize) {
      System.out.println("Data" + counter + "Size" + datasize);
      for (int k = j; k < call_no.length(); k++) {
        if (Character.isDigit(call_no.charAt(k)) && String.valueOf(call_no.charAt(k + 1)).contentEquals(".")) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("LengthS more than 10 at [" + k + "]");
          break;
        } 
        if (Character.isSpaceChar(call_no.charAt(k))) {
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("LengthA more than 10 at [" + k + "]");
          break;
        } 
        System.out.println("Length22 more than 10 at [" + k + "]");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
        j++;
      } 
    } else {
      for (int k = j; k < call_no.length(); k++) {
        System.out.println("loop 2 start at [" + k + "]");
        if (Character.isSpaceChar(call_no.charAt(k))) {
          if (sets.trim().isEmpty()) {
            j++;
            System.out.println("im breaking at [" + k + "]");
            break;
          } 
          System.out.println("break");
          DDC.add(sets);
          sets = "";
          break;
        } 
        if (Character.isDigit(call_no.charAt(k))) {
          System.out.println("there's a digit at [" + k + "]");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } else if (String.valueOf(call_no.charAt(k)).contentEquals(".") && Character.isDigit(call_no.charAt(k + 1))) {
          System.out.println("there's a digit2");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } else if (String.valueOf(call_no.charAt(k)).contentEquals(".")) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } else {
          if (String.valueOf(call_no.charAt(k)).contentEquals(".") && Character.isLetter(call_no.charAt(k + 1))) {
            DDC.add(sets);
            sets = "";
            System.out.println("loop 2 ends at [" + k + "]");
            break;
          } 
          if (String.valueOf(call_no.charAt(k + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(k))) {
            DDC.add(sets);
            sets = "";
            System.out.println("loop 2 ends at [" + k + "]");
            break;
          } 
          if (Character.isLetter(call_no.charAt(k))) {
            System.out.println("Letter");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            j++;
          } 
        } 
      } 
    } 
    int i;
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 3 start at [" + i + "]");
      if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
        System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("loop 3 this is digit [" + i + "]");
        System.out.println(call_no.length() - 1);
        if (i == call_no.length() - 1) {
          DDC.add(sets);
          sets = "";
        } 
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
        System.out.println("--> " + sets);
        DDC.add(sets);
        sets = "";
        j++;
        System.out.println("loop 3 ends at [" + i + "]");
        break;
      } 
      System.out.println("call no." + DDC + "]");
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 4 start at [" + i + "]");
      Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
      Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
      Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
      Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
      if (Letter.booleanValue() || Digit.booleanValue()) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        if (i == call_no.length() - 1) {
          DDC.add(sets);
          sets = "";
        } 
        j++;
      } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
        if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        } else {
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("loop 4 ends at [" + i + "]");
        } 
        continue;
      } 
      System.out.println("loop 4 ends at [" + i + "]");
    } 
    System.out.println("SS" + DDC.toString());
    return DDC;
  }
  
  public List<String> DDCSplit(String itemCategory, String itemSMD, String call_no, String styletype) {
    System.out.println("Cater12" + call_no);
    List<String> DDC = new ArrayList<>();
    if (call_no != "") {
      int datasize = Integer.parseInt(DDCDataSize());
      String decimal = DecimalSplit(styletype);
      String sets = "";
      int j = 0;
      call_no = call_no.trim();
      int counter = getCount(call_no);
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
        for (int k = j; k < call_no.length(); k++) {
          if (Character.isDigit(call_no.charAt(k)) && String.valueOf(call_no.charAt(k + 1)).contentEquals(".")) {
            DDC.add(sets);
            sets = "";
            j++;
            System.out.println("Length more than 10 at [" + k + "]");
            break;
          } 
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } 
      } else if (decimal.equals("N") && counter > datasize) {
        System.out.println("Data" + counter + "Size" + datasize);
        for (int k = j; k < call_no.length(); k++) {
          if (Character.isDigit(call_no.charAt(k)) && String.valueOf(call_no.charAt(k + 1)).contentEquals(".")) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            DDC.add(sets);
            sets = "";
            j++;
            System.out.println("LengthS more than 10 at [" + k + "]");
            break;
          } 
          if (Character.isSpaceChar(call_no.charAt(k))) {
            DDC.add(sets);
            sets = "";
            j++;
            System.out.println("LengthA more than 10 at [" + k + "]");
            break;
          } 
          System.out.println("Length22 more than 10 at [" + k + "]");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } 
      } else {
        for (int k = j; k < call_no.length(); k++) {
          System.out.println("loop 2 start at [" + k + "]");
          if (k == call_no.length() - 1) {
            System.out.println("break");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            j++;
            DDC.add(sets);
            break;
          } 
          if (Character.isSpaceChar(call_no.charAt(k))) {
            if (sets.trim().isEmpty()) {
              j++;
              System.out.println("im breaking at [" + k + "]");
              break;
            } 
            System.out.println("break");
            DDC.add(sets);
            sets = "";
            break;
          } 
          if (Character.isDigit(call_no.charAt(k))) {
            System.out.println("there's a digit at [" + k + "]");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            j++;
          } else if (String.valueOf(call_no.charAt(k)).contentEquals(".") && Character.isDigit(call_no.charAt(k + 1))) {
            System.out.println("there's a digit2");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            j++;
          } else if (String.valueOf(call_no.charAt(k)).contentEquals(".")) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
            j++;
          } else {
            if (String.valueOf(call_no.charAt(k)).contentEquals(".") && Character.isLetter(call_no.charAt(k + 1))) {
              DDC.add(sets);
              sets = "";
              System.out.println("loop 2 ends at [" + k + "]");
              break;
            } 
            if (String.valueOf(call_no.charAt(k + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(k))) {
              DDC.add(sets);
              sets = "";
              System.out.println("loop 2 ends at [" + k + "]");
              break;
            } 
            if (Character.isLetter(call_no.charAt(k))) {
              System.out.println("Letter");
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
              j++;
            } 
          } 
        } 
      } 
      int i;
      for (i = j; i < call_no.length(); i++) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", 2);
        Matcher m = p.matcher(String.valueOf(call_no.charAt(i)));
        boolean specialchar = m.find();
        System.out.println("Matrcher" + m.find());
        System.out.println(String.valueOf(j) + "loop 3 start at [" + i + "]" + call_no.charAt(i));
        if (i == call_no.length() - 1) {
          System.out.println("break");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
          DDC.add(sets);
          break;
        } 
        if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
          System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
        } else if (Character.isDigit(call_no.charAt(i))) {
          System.out.println("loop 3 this is digit [" + i + "]");
          System.out.println(call_no.length() - 1);
          if (i == call_no.length() - 1) {
            DDC.add(sets);
            sets = "";
          } 
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
        } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
          System.out.println("Letter");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
        } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
        } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
          System.out.println("--> " + sets);
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("loop 3 ends at [" + i + "]");
          break;
        } 
        System.out.println("call no." + DDC + "]");
      } 
      for (i = j; i < call_no.length(); i++) {
        System.out.println("loop 4 start at [" + i + "]");
        System.out.println("loop4 start at [" + call_no.charAt(i) + "]");
        Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
        Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
        Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
        Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
        if (i == call_no.length() - 1) {
          System.out.println("break");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          j++;
          DDC.add(sets);
          break;
        } 
        if (Letter.booleanValue() || Digit.booleanValue()) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          if (i == call_no.length() - 1) {
            DDC.add(sets);
            sets = "";
          } 
          j++;
        } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
          if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
          } else {
            DDC.add(sets);
            sets = "";
            j++;
            System.out.println("loop 4 ends at [" + i + "]");
          } 
          continue;
        } 
        System.out.println("loop 4 ends at [" + i + "]");
      } 
      System.out.println("SS" + DDC.toString());
    } 
    return DDC;
  }
  
  public List<String> LLCSplitTest(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
    System.out.println("TrialLbl" + call_no);
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      System.out.println("Testing1" + k.getLBL01FIELD() + k.getLBL01MTYPE() + k.getLBL01PRINT());
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y")) {
        System.out.println("Testing" + k.getLBL01FIELD());
        listPrint.add(k.getLBL01FIELD());
      } 
    } 
    int orilength = call_no.length();
    List<String> LLC = new ArrayList<>();
    String sets = "";
    int j = 0;
    call_no = call_no.trim();
    for (int list = 0; list < listPrint.size(); list++) {
      if (((String)listPrint.get(list)).equals("SpineItemCategory")) {
        if (itemCategory != null && !itemCategory.isEmpty()) {
          System.out.println("Cater" + itemCategory.trim());
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemCategory.trim() + "</strong><br>");
        } else {
          sets = " ";
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
        } 
      } else if (((String)listPrint.get(list)).equals("SpineSMD")) {
        if (itemSMD != null && !itemSMD.isEmpty()) {
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemSMD.trim() + "</strong><br>");
        } else {
          sets = " ";
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
        } 
      } else if (((String)listPrint.get(list)).equals("Subject")) {
        for (int i = j; i < call_no.length(); i++) {
          if (Character.isLetter(call_no.charAt(i))) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (!Character.isLetter(call_no.charAt(i))) {
            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            if (!Character.isDigit(call_no.charAt(i)) && 
              Character.isSpaceChar(call_no.charAt(i)))
              j++; 
            sets = "";
            System.out.println("loop 1 ends at [" + i + "]--> " + sets);
            break;
          } 
          System.out.println("LLC" + LLC.toString());
        } 
      } else if (((String)listPrint.get(list)).equals("Cutter1")) {
        System.out.println("Cutter1in");
        for (int i = j; i < call_no.length(); i++) {
          System.out.println("loop 2 start at [" + i + "]");
          if (Character.isSpaceChar(call_no.charAt(i))) {
            System.out.println("there's a space at [" + i + "]");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isDigit(call_no.charAt(i))) {
            System.out.println("there's a digit at [" + i + "]");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
            System.out.println("there's a digit2");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else {
            if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              System.out.println("loop 2 ends at [" + i + "]");
              break;
            } 
            if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              System.out.println("loop 2 ends at [" + i + "]");
              break;
            } 
          } 
          System.out.println("LLC" + LLC.toString());
        } 
      } else if (((String)listPrint.get(list)).equals("Cutter2")) {
        for (int i = j; i < call_no.length(); i++) {
          System.out.println("loop 3 start at [" + i + "]" + call_no.length());
          if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
            System.out.println("---> " + sets);
            System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isDigit(call_no.charAt(i))) {
            System.out.println("---->11 " + sets);
            if (call_no.length() == orilength - 1) {
              System.out.println(call_no.length() + orilength - 1);
              LLC.add(sets);
              sets = "";
            } else {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
              j++;
            } 
          } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
            System.out.println("-----> 12" + sets);
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isSpaceChar(call_no.charAt(i))) {
            System.out.println("-->13 " + sets);
            LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            sets = "";
            j++;
            System.out.println("loop 3 ends at [" + i + "]");
            break;
          } 
          System.out.println("LLC" + LLC.toString());
        } 
      } else if (((String)listPrint.get(list)).equals("Cutter3")) {
        for (int i = j; i < call_no.length(); i++) {
          System.out.println("loop 4 start at [" + i + "]" + call_no.charAt(i));
          Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
          Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
          Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
          Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
          if (Letter.booleanValue() || Digit.booleanValue()) {
            System.out.println("Letter");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            if (i == call_no.length() - 1) {
              j++;
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
            } else if (Character.isSpaceChar(call_no.charAt(i + 1))) {
              j++;
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              break;
            } 
            j++;
          } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
            System.out.println("Space");
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("loop 4 ends at [" + i + "]");
              System.out.println("LLC" + LLC.toString());
            } 
            continue;
          } 
          System.out.println("loop 4 ends at [" + i + "]");
          System.out.println("LLC" + LLC.toString());
        } 
      } else if (((String)listPrint.get(list)).equals("Year")) {
        for (int i = j; i < call_no.length(); i++) {
          System.out.println("loop 5 start at [" + i + "]" + call_no.charAt(i));
          Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
          Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
          Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
          Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
          if (Letter.booleanValue() || Digit.booleanValue()) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            if (i == call_no.length() - 1) {
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
            } 
            j++;
          } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
              LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("loop 4 ends at [" + i + "]");
              System.out.println("LLC" + LLC.toString());
            } 
            continue;
          } 
          System.out.println("loop 4 ends at [" + i + "]");
          System.out.println("LLC" + LLC.toString());
        } 
      } else if (((String)listPrint.get(list)).equals("SpineVolume")) {
        if (volume != null && !volume.isEmpty())
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + volume.trim() + "</strong><br>"); 
      } else if (((String)listPrint.get(list)).equals("Copy")) {
        if (copyNo != null && !copyNo.isEmpty())
          LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + copyNo.trim() + "</strong><br>"); 
      } else if (((String)listPrint.get(list)).equals("PartNo") && 
        partNo != null && !partNo.isEmpty()) {
        LLC.add("<strong class='" + (String)listPrint.get(list) + "'>" + partNo.trim() + "</strong><br>");
      } 
    } 
    System.out.println("LLC1" + LLC.toString());
    return LLC;
  }
  
  public List<String> LLCSplitTestSpine(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
    System.out.println("volume" + volume + "volume");
    JSONObject jsonObject = new JSONObject();
    List<String> LLC = new ArrayList<>();
    String sets = "";
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y"))
        listPrint.add(k.getLBL01FIELD()); 
    } 
    for (int list = 0; list < listPrint.size(); list++) {
      System.out.println("getlist" + (String)listPrint.get(list));
      if (((String)listPrint.get(list)).equals("SpineItemCategory")) {
        if (itemCategory != null && !itemCategory.isEmpty())
          LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + itemCategory.trim() + "</p><br>"); 
      } else if (((String)listPrint.get(list)).equals("SpineSMD")) {
        if (itemSMD == null || itemSMD.isEmpty()) {
          sets = "";
        } else if (itemSMD != null && !itemSMD.isEmpty()) {
          LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + itemSMD.trim() + "</p>");
        } else if (itemSMD.equals("N")) {
          sets = "";
        } else {
          sets = " ";
          LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + sets + "</p><br>");
        } 
      } else if (((String)listPrint.get(list)).equals("SpineVolume")) {
        System.out.println("SpineVolume1");
        if (volume != null && !volume.isEmpty()) {
          System.out.println("SpineVolume2");
          LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + volume.trim() + "</p>");
          System.out.println("SpineVolume3");
        } 
      } else if (((String)listPrint.get(list)).equals("PartNo")) {
        if (partNo != null && !partNo.isEmpty())
          LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + partNo.trim() + "</p>"); 
      } else if (((String)listPrint.get(list)).equals("Copy") && 
        copyNo != null && !copyNo.isEmpty()) {
        LLC.add("<p class='" + (String)listPrint.get(list) + "'>" + copyNo.trim() + "</p>");
      } 
    } 
    Pattern combinedPattern = Pattern.compile("^(.{1,2})(\\d*\\.?\\d{1,8})(\\S*\\s*)((?:[a-zA-Z]+\\d+)?)((?:[a-zA-Z]+\\d+)?)(\\s*.*)");
    Matcher matcher = combinedPattern.matcher(call_no);
    if (matcher.matches()) {
      String Subject = matcher.group(1);
      String ClassNo = matcher.group(2);
      String cutter1 = matcher.group(3);
      String cutter2 = matcher.group(4);
      String cutter3 = matcher.group(5);
      String Yearbefore = matcher.group(6).trim();
      String[] parts = Yearbefore.split("\\s+");
      String Year = parts[0];
      String rest = (parts.length > 1) ? parts[1] : "";
      if (cutter1.trim().isEmpty() && cutter2.trim().isEmpty() && cutter3.trim().isEmpty()) {
        System.out.println("YearbeforeisEmpty(" + cutter1);
        System.out.println("cutter1.trim().isEmpty(");
        cutter1 = Year;
        cutter2 = rest;
        cutter3 = "";
        Year = "";
        rest = "";
      } else if (cutter2.trim().isEmpty() && cutter3.trim().isEmpty()) {
        cutter2 = Year;
        cutter3 = rest;
        Year = "";
        rest = "";
      } else if (cutter3.trim().isEmpty()) {
        cutter3 = Year;
        Year = rest;
        Year = "";
        rest = "";
      } 
      if (Subject != null) {
        jsonObject.put("Subject", Subject);
        LLC.add("<p class='Subject'>" + Subject + "</p><br>");
      } 
      if (matcher.group(2) != null) {
        jsonObject.put("ClassNo", ClassNo);
        LLC.add("<p class='ClassNo'>" + ClassNo + "</p><br>");
      } 
      if (matcher.group(3) != null) {
        jsonObject.put("Cutter1", cutter1);
        LLC.add("<p class='Cutter1'>" + cutter1 + "</p><br>");
      } 
      if (matcher.group(4) != null) {
        jsonObject.put("Cutter2", cutter2);
        LLC.add("<p class='Cutter2'>" + cutter2 + "</p><br>");
      } 
      if (matcher.group(5) != null) {
        jsonObject.put("Cutter3", cutter3);
        LLC.add("<p class='Cutter3'>" + cutter3 + "</p><br>");
      } 
      if (matcher.group(6) != null) {
        jsonObject.put("Year", Year);
        LLC.add("<p class='Year'>" + Year + "</p><br>");
      } 
      System.out.println("Subject Part: " + Subject);
      System.out.println("ClassNo Part: " + ClassNo);
      System.out.println("cutter1 Part: " + cutter1);
      System.out.println("cutter2 part: " + cutter2);
      System.out.println("cutter3 part: " + cutter3);
      System.out.println("Year part: " + Year);
      System.out.println("Rest: " + rest);
    } else {
      System.out.println("Pattern not matched");
      String inputString = call_no;
      String modifiedString = inputString.replaceAll("\\.(?=[A-Za-z])", " .");
      String[] parts = modifiedString.split("\\s+");
      int partlen = parts.length;
      System.out.println("partlen" + partlen);
      for (int i = 1; i <= parts.length; i++) {
        String word = parts[i - 1];
        System.out.println("Word at position " + i + ": " + word);
        if (i == 1) {
          jsonObject.put("Subject", parts[i - 1]);
          LLC.add("<p class='Subject'>" + parts[i - 1] + "</p><br>");
        } 
        if (i == 2) {
          jsonObject.put("ClassNo", parts[i - 1]);
          LLC.add("<p class='ClassNo'>" + parts[i - 1] + "</p><br>");
        } 
        if (i == 3) {
          jsonObject.put("Cutter1", parts[i - 1]);
          LLC.add("<p class='Cutter1'>" + parts[i - 1] + "</p><br>");
        } 
        if (i == 4 && parts[3].matches("\\d+")) {
          jsonObject.put("Cutter2", parts[i - 1]);
          LLC.add("<p class='Cutter2'>" + parts[i - 1] + "</p><br>");
        } else if (i == 5 && parts[4].matches("\\d+")) {
          jsonObject.put("Cutter3", parts[i - 1]);
          LLC.add("<p class='Cutter3'>" + parts[i - 1] + "</p><br>");
          System.out.println("partlenY" + partlen);
        } else {
          System.out.println("partlen" + partlen);
          if (i == 4) {
            jsonObject.put("Cutter2", parts[3]);
            LLC.add("<p class='Cutter2'>" + parts[3] + "</p><br>");
          } 
          if (i == 5) {
            jsonObject.put("Cutter3", parts[4]);
            LLC.add("<p class='Cutter3'>" + parts[4] + "</p><br>");
          } 
          if (i == 6) {
            jsonObject.put("Year", parts[5]);
            LLC.add("<p class='Year'>" + parts[5] + "</p><br>");
          } 
        } 
      } 
    } 
    return LLC;
  }
  
  public List<String> LLCSplitTestSpineuserDefineSpine(String userDefineSpine) {
    List<String> LLC = new ArrayList<>();
    String[] words = userDefineSpine.split("\\s+");
    int wordCount = words.length;
    System.out.println("Word Count: " + wordCount);
    for (int i = 0; i <= wordCount; i++) {
      if (i == 1)
        LLC.add("<strong class='SpineItemCategory'>" + words[0] + "</strong><br>"); 
      if (i == 2)
        LLC.add("<strong class='Subject'>" + words[1] + "</strong><br>"); 
      if (i == 3)
        LLC.add("<strong class='ClassNo'>" + words[2] + "</strong><br>"); 
      if (i == 4)
        LLC.add("<strong class='Cutter1'>" + words[3] + "</strong><br>"); 
      if (i == 5)
        LLC.add("<strong class='Cutter2'>" + words[5] + "</strong><br>"); 
      if (i == 6)
        LLC.add("<strong class='Cutter3'>" + words[5] + "</strong><br>"); 
    } 
    return LLC;
  }
  
  public List<String> LLCSplitTest_UserDefineSpine(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
    int subject = 1;
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y"))
        listPrint.add(k.getLBL01FIELD()); 
    } 
    int callNoLength = call_no.length();
    List<String> LLC = new ArrayList<>();
    String sets = "";
    int j = 0;
    if (itemCategory != null && !itemCategory.isEmpty()) {
      LLC.add("<strong class='DefineSpineItemCategory'>" + itemCategory.trim() + "</strong><br>");
    } else {
      sets = " ";
      LLC.add("<strong class='DefineSpineItemCategory'" + sets + "</strong><br>");
    } 
    for (int i = 0; i < call_no.length(); i++) {
      if (!Character.isWhitespace(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else {
        LLC.add("<strong class='DefineSubject" + subject + "'>" + sets + "</strong><br>");
        sets = "";
        subject++;
      } 
    } 
    return LLC;
  }
  
  public List<String> DDCSplitTest(String itemCategory, String itemSMD, String call_no, String styletype, String splitStyle) {
    List<String> DDC = new ArrayList<>();
    int datasize = Integer.parseInt(DDCDataSize());
    String decimal = DecimalSplit(styletype);
    String sets = "";
    int j = 0;
    call_no = call_no.trim();
    int counter = getCount(call_no);
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y"))
        listPrint.add(k.getLBL01FIELD()); 
    } 
    for (int list = 0; list < listPrint.size(); list++) {
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
        if (((String)listPrint.get(list)).equals("Cutter1"))
          for (int i = j; i < call_no.length(); i++) {
            if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              break;
            } 
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          }  
      } else if (decimal.equals("N") && counter > datasize) {
        if (((String)listPrint.get(list)).equals("Cutter1"))
          for (int i = j; i < call_no.length(); i++) {
            if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              break;
            } 
            if (Character.isSpaceChar(call_no.charAt(i))) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              break;
            } 
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          }  
      } else if (((String)listPrint.get(list)).equals("Cutter1")) {
        for (int i = j; i < call_no.length(); i++) {
          Pattern p = Pattern.compile("[^a-z0-9 ]", 2);
          Matcher m = p.matcher(String.valueOf(call_no.charAt(i)));
          boolean specialchar = m.find();
          if (i == call_no.length() - 1) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Character.isSpaceChar(call_no.charAt(i))) {
            if (sets.trim().isEmpty()) {
              j++;
              break;
            } 
            if (Character.isLetter(call_no.charAt(i + 1)) || specialchar) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              break;
            } 
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Character.isDigit(call_no.charAt(i))) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
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
        } 
      } 
      if (((String)listPrint.get(list)).equals("Cutter2"))
        for (int i = j; i < call_no.length(); i++) {
          Pattern p = Pattern.compile("[^a-z0-9 ]", 2);
          Matcher m = p.matcher(String.valueOf(call_no.charAt(i)));
          boolean specialchar = m.find();
          if (i == call_no.length() - 1) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isDigit(call_no.charAt(i))) {
            if (i == call_no.length() - 1) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
            } 
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isSpaceChar(call_no.charAt(i)) || i > 10) {
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            sets = "";
            j++;
            break;
          } 
        }  
      if (((String)listPrint.get(list)).equals("Cutter3"))
        for (int i = j; i < call_no.length(); i++) {
          Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
          Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
          Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
          Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
          if (i == call_no.length() - 1) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Letter.booleanValue() || Digit.booleanValue()) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            if (i == call_no.length() - 1) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
            } 
            j++;
          } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
            } 
          } 
        }  
    } 
    return DDC;
  }
  
  public List<String> DDCSplitTest(String itemCategory, String itemSMD, String call_no, String volume, String branchNo, String styletype, String splitStyle) {
    System.out.println("lineee 1905 : " + call_no);
    List<String> DDC = new ArrayList<>();
    String sets = "";
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y"))
        listPrint.add(k.getLBL01FIELD()); 
      if (k.getLBL01FIELD().equals("Location") && k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y"))
        DDC.add("<strong class='Location'>" + branchNo + "</strong><br>"); 
    } 
    for (int list = 0; list < listPrint.size(); list++) {
      System.out.println("getlist" + (String)listPrint.get(list));
      if (((String)listPrint.get(list)).equals("SpineItemCategory")) {
        if (itemCategory.equals("Y") && 
          itemCategory != null && !itemCategory.isEmpty())
          DDC.add("<p class='group0'>" + itemCategory.trim() + "</p><br>"); 
      } else if (((String)listPrint.get(list)).equals("SpineSMD")) {
        if (itemSMD == null || itemSMD.isEmpty()) {
          sets = "";
        } else if (itemSMD != null && !itemSMD.isEmpty()) {
          DDC.add("<p class='" + (String)listPrint.get(list) + "'>" + itemSMD.trim() + "</p>");
        } else if (itemSMD.equals("N")) {
          sets = "";
        } else {
          sets = " ";
          DDC.add("<p class='" + (String)listPrint.get(list) + "'>" + sets + "</p><br>");
        } 
      } else if (((String)listPrint.get(list)).equals("SpineVolume") && 
        volume != null && !volume.isEmpty()) {
        DDC.add("<p class='" + (String)listPrint.get(list) + "'>" + volume.trim() + "</p>");
      } 
    } 
    String ddclengh = CheckingData.getFunc("PARABLEDDC");
    String spineformat = GetGlflag2.getGlflag2Value("SPINEFORMAT");
    System.out.println("call_nocall_nocall_no" + call_no + "ll");
    if (!call_no.equals("")) {
      char char1 = call_no.charAt(0);
      System.out.println("char1" + char1);
      System.out.println("parts3[0]" + Character.isLetter(char1));
      if (spineformat.equals("3") && Character.isLetter(char1)) {
        System.out.println("STEPPPP 1");
        String[] part3 = call_no.split("\\s+");
        for (int i = 0; i < part3.length; i++)
          DDC.add("<p class='Group1'>" + part3[i].toString() + "</p><br>"); 
      } else {
        System.out.println("STEPPPP 2");
        String[] parts = call_no.split("\\s+|(?<=\\d\\.)(?!\\d)|(?<!\\d\\.)(?<!\\.\\d)\\s+");
        if (Integer.parseInt(ddclengh) == 3) {
          String ddcdot = CheckingData.getFunc("PARABLEDDCDOT");
          for (int i = 0; i < parts.length; i++) {
            System.out.println(String.valueOf(parts[i]) + "parts[i]:" + i);
            if (parts[0].contains(".")) {
              if (ddcdot.equals("0")) {
                System.out.println(String.valueOf(parts[i]) + "parts[i]");
                if (i == 0) {
                  System.out.println(String.valueOf(parts[0]) + "parts[i]");
                  String[] splitParts = parts[0].split("\\.");
                  System.out.println("splitParts[0]:" + i);
                  DDC.add("<p class='Group1'>" + splitParts[0].toString() + "." + "</p><br>");
                  DDC.add("<p class='Group1'>" + splitParts[1].toString() + "</p><br>");
                } else {
                  DDC.add("<p class='Group1'>" + parts[i] + "</p><br>");
                } 
              } else if (ddcdot.equals("1")) {
                System.out.println(String.valueOf(parts[i]) + "parts[i]");
                if (i == 0) {
                  String[] splitParts = parts[0].split("\\.");
                  DDC.add("<p class='Group1'>" + splitParts[0].toString() + "</p><br>");
                  DDC.add("<p class='Group1'>." + splitParts[1].toString() + "</p><br>");
                } else {
                  DDC.add("<p class='Group1'>" + parts[i] + "</p><br>");
                } 
              } 
            } else {
              DDC.add("<p class='Group1'>" + parts[i] + "</p><br>");
            } 
          } 
        } else if (Integer.parseInt(ddclengh) == 10) {
          byte b;
          int i;
          String[] arrayOfString;
          for (i = (arrayOfString = parts).length, b = 0; b < i; ) {
            String part = arrayOfString[b];
            DDC.add("<p class='Group1'>" + part + "</p><br>");
            b++;
          } 
        } 
      } 
    } 
    return DDC;
  }
  
  public List<String> LLCSplit(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo) {
    JSONObject jsonObject = new JSONObject();
    List<String> LLC = new ArrayList<>();
    Pattern combinedPattern = Pattern.compile("^(.{1,2})(\\d*\\.?\\d{1,8})(\\S*\\s*)((?:[a-zA-Z]+\\d+)?)((?:[a-zA-Z]+\\d+)?)(\\s*.*)");
    Matcher matcher = combinedPattern.matcher(call_no);
    if (matcher.matches()) {
      String Subject = matcher.group(1);
      String ClassNo = matcher.group(2);
      String cutter1 = matcher.group(3);
      String cutter2 = matcher.group(4);
      String cutter3 = matcher.group(5);
      String Yearbefore = matcher.group(6).trim();
      String[] parts = Yearbefore.split("\\s+");
      String Year = parts[0];
      String rest = (parts.length > 1) ? parts[1] : "";
      if (Subject != null) {
        jsonObject.put("Subject", Subject);
        LLC.add("<div class='Subject'>" + Subject + "</div>");
      } 
      if (matcher.group(2) != null) {
        jsonObject.put("ClassNo", ClassNo);
        LLC.add("<div class='ClassNo'>" + ClassNo + "</div>");
      } 
      if (matcher.group(3) != null) {
        jsonObject.put("Cutter1", cutter1);
        LLC.add("<div class='Cutter1s'>" + cutter1 + "</div>");
      } 
      if (matcher.group(4) != null) {
        jsonObject.put("Cutter2", cutter2);
        LLC.add("<div class='Cutter2s'>" + cutter2 + "</div>");
      } 
      if (matcher.group(5) != null) {
        jsonObject.put("Cutter3", cutter3);
        LLC.add("<div class='Cutter3s'>" + cutter3 + "</div>");
      } 
      if (matcher.group(6) != null) {
        jsonObject.put("Year", Year);
        LLC.add("<div class='Year'>" + Year + "</div>");
      } 
      System.out.println("Subject Part: " + Subject);
      System.out.println("ClassNo Part: " + ClassNo);
      System.out.println("cutter1 Part: " + cutter1);
      System.out.println("cutter2 part: " + cutter2);
      System.out.println("cutter3 part: " + cutter3);
      System.out.println("Year part: " + Year);
      System.out.println("Rest: " + rest);
    } else {
      System.out.println("Pattern not matched");
      String inputString = call_no;
      String modifiedString = inputString.replaceAll("\\.(?=[A-Za-z])", " .");
      String[] parts = modifiedString.split("\\s+");
      int partlen = parts.length;
      System.out.println("partlen" + partlen);
      for (int i = 1; i <= parts.length; i++) {
        String word = parts[i - 1];
        System.out.println("Word at position " + i + ": " + word);
        if (i == 1) {
          jsonObject.put("Subject", parts[i - 1]);
          LLC.add("<div class='Subject'>" + parts[i - 1] + "</div>");
        } 
        if (i == 2) {
          jsonObject.put("ClassNo", parts[i - 1]);
          LLC.add("<div class='ClassNo'>" + parts[i - 1] + "</div>");
        } 
        if (i == 3) {
          jsonObject.put("Cutter1", parts[i - 1]);
          LLC.add("<div class='Cutter1s'>" + parts[i - 1] + "</div>");
        } 
        if (i == 4 && parts[3].matches("\\d+")) {
          jsonObject.put("Year", parts[i - 1]);
          LLC.add("<div class='Year'>" + parts[i - 1] + "</div>");
        } else if (i == 5 && parts[4].matches("\\d+")) {
          jsonObject.put("Year", parts[i - 1]);
          LLC.add("<div class='Year'>" + parts[i - 1] + "</div>");
          System.out.println("partlenY" + partlen);
        } else {
          System.out.println("partlen" + partlen);
          if (i == 4) {
            jsonObject.put("Cutter2", parts[3]);
            LLC.add("<div class='Cutter2s'>" + parts[3] + "</div>");
          } 
          if (i == 5) {
            jsonObject.put("Cutter3", parts[4]);
            LLC.add("<div class='Cutter3s'>" + parts[4] + "</div>");
          } 
          if (i == 6) {
            jsonObject.put("Year", parts[5]);
            LLC.add("<div class='Year'>" + parts[5] + "</div>");
          } 
        } 
      } 
    } 
    return LLC;
  }
  
  public List<String> bookLLCSplit(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo) {
    List<String> LLC = new ArrayList<>();
    String sets = "";
    int j = 0;
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
    int i;
    for (i = j; i < call_no.length(); i++) {
      if (Character.isLetter(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (!Character.isLetter(call_no.charAt(i))) {
        LLC.add(sets);
        sets = "";
        System.out.println("loop 1 ends at [" + i + "]--> " + sets);
        break;
      } 
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 2 start at [" + i + "]");
      if (Character.isSpaceChar(call_no.charAt(i))) {
        if (sets.trim().isEmpty()) {
          j++;
          System.out.println("im breaking at [" + i + "]");
          break;
        } 
        j++;
        LLC.add(sets);
        sets = "";
        break;
      } 
      if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("there's a digit at [" + i + "]");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
        System.out.println("there's a digit2");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
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
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 3 start at [" + i + "]");
      if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
        System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isDigit(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isLetter(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isSpaceChar(call_no.charAt(i))) {
        System.out.println("--> " + sets);
        LLC.add(sets);
        sets = "";
        j++;
        System.out.println("loop 3 ends at [" + i + "]");
        break;
      } 
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 4 start at [" + i + "]");
      Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
      Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
      Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
      Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
      if (Letter.booleanValue() || Digit.booleanValue()) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        if (i == call_no.length() - 1) {
          LLC.add(sets);
          sets = "";
        } 
        j++;
      } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
        if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        } else {
          LLC.add(sets);
          sets = "";
          j++;
          System.out.println("loop 4 ends at [" + i + "]");
        } 
        continue;
      } 
      System.out.println("loop 4 ends at [" + i + "]");
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
