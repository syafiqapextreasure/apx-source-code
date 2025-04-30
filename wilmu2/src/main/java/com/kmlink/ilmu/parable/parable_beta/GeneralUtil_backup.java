package com.kmlink.ilmu.parable.parable_beta;

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

public class GeneralUtil_backup {
  private static Connection conn = null;
  
  private static Statement stmt = null;
  
  private static ResultSet rs = null;
  
  public String LB01SIZE = null;
  
  public static String DDCDataSize() {
    String query = "Select LB01SIZE FROM LBMSTR WHERE LB01TPLNAME='SPINEDDCLBL' AND LB01FIELD='LABEL'";
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
        System.out.println("vallllllnoooo" + Handler.getSubfield(resultSet.getString("CallNo"), isbd));
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
    List<ISBD> isbd = ISBD.getPunctuation("090");
    List<ISBD> titleisbd = ISBD.getPunctuation("245");
    while (resultSet.next()) {
      book_spine.setAccessionDate(date_formattter_reverse(resultSet.getString("AccessionDate")));
      book_spine.setAccessionNo(checkAccessionNo(resultSet.getString("AccessionNo")));
      book_spine.setAuthor(removeScript(resultSet.getString("Author")));
      book_spine.setBranch(resultSet.getString("Branch"));
      book_spine.setBranchDesc(resultSet.getString("BranchDesc"));
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
      book_spine.setTitle(Handler.getSubfield(resultSet.getString("Title"), titleisbd));
      book_spine.setUserDefineSpine(resultSet.getString("UserDefineSpine"));
      book_spine.setVolume(resultSet.getString("Volume"));
      book_spine.setSpineStatus(resultSet.getString("SpineStatus"));
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
          System.out.println("Length more than 10 at [ " + k + " ]");
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
          System.out.println("LengthS more than 10 at [ " + k + " ]");
          break;
        } 
        if (Character.isSpaceChar(call_no.charAt(k))) {
          DDC.add(sets);
          sets = "";
          j++;
          System.out.println("LengthA more than 10 at [ " + k + " ]");
          break;
        } 
        System.out.println("Length22 more than 10 at [ " + k + " ]");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
        j++;
      } 
    } else {
      for (int k = j; k < call_no.length(); k++) {
        System.out.println("loop 2 start at [ " + k + " ]");
        if (Character.isSpaceChar(call_no.charAt(k))) {
          if (sets.trim().isEmpty()) {
            j++;
            System.out.println("im breaking at [ " + k + " ]");
            break;
          } 
          System.out.println("break");
          DDC.add(sets);
          sets = "";
          break;
        } 
        if (Character.isDigit(call_no.charAt(k))) {
          System.out.println("there's a digit at [ " + k + " ]");
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
            System.out.println("loop 2 ends at [ " + k + " ]");
            break;
          } 
          if (String.valueOf(call_no.charAt(k + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(k))) {
            DDC.add(sets);
            sets = "";
            System.out.println("loop 2 ends at [ " + k + " ]");
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
      System.out.println("loop 3 start at [ " + i + " ]");
      if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
        System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("loop 3 this is digit [ " + i + " ]");
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
        System.out.println("loop 3 ends at [ " + i + " ]");
        break;
      } 
      System.out.println("call no." + DDC + "]");
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 4 start at [ " + i + " ]");
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
          System.out.println("loop 4 ends at [ " + i + " ]");
        } 
        continue;
      } 
      System.out.println("loop 4 ends at [ " + i + " ]");
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
            System.out.println("Length more than 10 at [ " + k + " ]");
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
            System.out.println("LengthS more than 10 at [ " + k + " ]");
            break;
          } 
          if (Character.isSpaceChar(call_no.charAt(k))) {
            DDC.add(sets);
            sets = "";
            j++;
            System.out.println("LengthA more than 10 at [ " + k + " ]");
            break;
          } 
          System.out.println("Length22 more than 10 at [ " + k + " ]");
          sets = String.valueOf(sets) + String.valueOf(call_no.charAt(k));
          j++;
        } 
      } else {
        for (int k = j; k < call_no.length(); k++) {
          System.out.println("loop 2 start at [ " + k + " ]");
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
              System.out.println("im breaking at [ " + k + " ]");
              break;
            } 
            System.out.println("break");
            DDC.add(sets);
            sets = "";
            break;
          } 
          if (Character.isDigit(call_no.charAt(k))) {
            System.out.println("there's a digit at [ " + k + " ]");
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
              System.out.println("loop 2 ends at [ " + k + " ]");
              break;
            } 
            if (String.valueOf(call_no.charAt(k + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(k))) {
              DDC.add(sets);
              sets = "";
              System.out.println("loop 2 ends at [ " + k + " ]");
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
        System.out.println(String.valueOf(j) + "loop 3 start at [ " + i + " ]" + call_no.charAt(i));
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
          System.out.println("loop 3 this is digit [ " + i + " ]");
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
          System.out.println("loop 3 ends at [ " + i + " ]");
          break;
        } 
        System.out.println("call no." + DDC + "]");
      } 
      for (i = j; i < call_no.length(); i++) {
        System.out.println("loop 4 start at [ " + i + " ]");
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
            System.out.println("loop 4 ends at [ " + i + " ]");
          } 
          continue;
        } 
        System.out.println("loop 4 ends at [ " + i + " ]");
      } 
      System.out.println("SS" + DDC.toString());
    } 
    return DDC;
  }
  
  public List<String> LLCSplitTest(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
    JSONObject jsonObject = new JSONObject();
    List<String> LLC = new ArrayList<>();
    Pattern pattern = Pattern.compile("([A-Z][A-Z])+([\\d\\.]+)(\\.[A-Z]\\d+)([A-Z]\\d+)?([A-Z]\\d+)?\\s(\\d+)$");
    Matcher match = pattern.matcher(call_no);
    if (match.find()) {
      if (match.group(1) != null) {
        jsonObject.put("Subject", match.group(1));
        LLC.add(String.valueOf(match.group(1)) + "<br>");
      } 
      if (match.group(2) != null) {
        jsonObject.put("ClassNo", match.group(2));
        LLC.add(String.valueOf(match.group(2)) + "<br>");
      } 
      if (match.group(3) != null) {
        jsonObject.put("Cutter1", match.group(3));
        LLC.add(String.valueOf(match.group(3)) + "<br>");
      } 
      if (match.group(4) != null) {
        jsonObject.put("Cutter2", match.group(4));
        LLC.add(String.valueOf(match.group(4)) + "<br>");
      } 
      if (match.group(5) != null) {
        jsonObject.put("Cutter3", match.group(5));
        LLC.add(String.valueOf(match.group(5)) + "<br>");
      } 
      if (match.group(6) != null) {
        jsonObject.put("Year", match.group(6));
        LLC.add(String.valueOf(match.group(6)) + "<br>");
      } 
    } 
    return LLC;
  }
  
  public List<String> LLCSplitTest_UserDefineSpine(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo, String styletype, String splitStyle) {
    int subject = 1;
    List<Config_Print> spineLbl = Config_Print.LblMstr(splitStyle);
    List<String> listPrint = new ArrayList<>();
    for (Config_Print k : spineLbl) {
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y")) {
        System.out.println("Testing: " + k.getLBL01FIELD());
        listPrint.add(k.getLBL01FIELD());
      } 
    } 
    int callNoLength = call_no.length();
    List<String> LLC = new ArrayList<>();
    String sets = "";
    int j = 0;
    if (itemCategory != null && !itemCategory.isEmpty()) {
      LLC.add(String.valueOf(itemCategory.trim()) + "<br>");
    } else {
      sets = " ";
      LLC.add(String.valueOf(sets) + "<br>");
    } 
    if (itemSMD != null && !itemSMD.isEmpty()) {
      LLC.add(String.valueOf(itemSMD.trim()) + "<br>");
    } else {
      sets = " ";
      LLC.add(String.valueOf(sets) + "<br>");
    } 
    for (int i = 0; i < call_no.length(); i++) {
      if (!Character.isWhitespace(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else {
        LLC.add(String.valueOf(sets) + "<br>");
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
      System.out.println("Testing1" + k.getLBL01FIELD() + k.getLBL01MTYPE() + k.getLBL01PRINT());
      if (k.getLBL01MTYPE().equals("P") && k.getLBL01PRINT().equals("Y")) {
        System.out.println("Testing" + k.getLBL01FIELD());
        listPrint.add(k.getLBL01FIELD());
      } 
    } 
    System.out.println("PrintLabel" + listPrint.toString());
    for (int list = 0; list < listPrint.size(); list++) {
      if (((String)listPrint.get(list)).equals("SpineItemCategory")) {
        if (itemCategory != null && !itemCategory.isEmpty()) {
          System.out.println("Cater12" + itemCategory.trim());
          DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + itemCategory.trim() + "</strong><br>");
        } else {
          sets = " ";
          DDC.add(sets);
        } 
      } else if (((String)listPrint.get(list)).equals("SpineSMD")) {
        System.out.println("PrintLabel" + itemSMD);
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
      System.out.println("Data" + decimal + "Size" + datasize + "Counter" + counter);
      if (decimal.equals("Y")) {
        if (((String)listPrint.get(list)).equals("Cutter1")) {
          System.out.println("sssd");
          for (int i = j; i < call_no.length(); i++) {
            if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("Length more than 10 at [ " + i + " ]");
              break;
            } 
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } 
        } 
      } else if (decimal.equals("N") && counter > datasize) {
        System.out.println("Data" + counter + "Size" + datasize);
        if (((String)listPrint.get(list)).equals("Cutter1"))
          for (int i = j; i < call_no.length(); i++) {
            if (Character.isDigit(call_no.charAt(i)) && String.valueOf(call_no.charAt(i + 1)).contentEquals(".")) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("LengthS more than 10 at [ " + i + " ]");
              break;
            } 
            if (Character.isSpaceChar(call_no.charAt(i))) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("LengthA more than 10 at [ " + i + " ]");
              break;
            } 
            System.out.println("Length22 more than 10 at [ " + i + " ]");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          }  
      } else if (((String)listPrint.get(list)).equals("Cutter1")) {
        for (int i = j; i < call_no.length(); i++) {
          Pattern p = Pattern.compile("[^a-z0-9 ]", 2);
          Matcher m = p.matcher(String.valueOf(call_no.charAt(i)));
          boolean specialchar = m.find();
          System.out.println("Matrcher" + m.find());
          System.out.println("loop 2 start at [ " + i + " ]" + call_no.charAt(i));
          if (i == call_no.length() - 1) {
            System.out.println("break");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Character.isSpaceChar(call_no.charAt(i))) {
            if (sets.trim().isEmpty()) {
              j++;
              System.out.println("im breaking at [ " + i + " ]");
              break;
            } 
            if (Character.isLetter(call_no.charAt(i + 1)) || specialchar) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("LengthA more than 10 at [ " + i + " ]");
              break;
            } 
            System.out.println("break");
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Character.isDigit(call_no.charAt(i))) {
            System.out.println("there's a digit at [ " + i + " ]");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isLetter(call_no.charAt(i)) || specialchar) {
            System.out.println("Letter");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isDigit(call_no.charAt(i + 1))) {
            System.out.println("there's a digit2");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else {
            if (String.valueOf(call_no.charAt(i)).contentEquals(".") && Character.isLetter(call_no.charAt(i + 1))) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              System.out.println("loop 2 ends at [ " + i + " ]");
              break;
            } 
            if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              System.out.println("loop 2 ends at [ " + i + " ]");
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
          System.out.println("Matrcher" + m.find());
          System.out.println(String.valueOf(j) + "loop 3 start at [ " + i + " ]" + call_no.charAt(i));
          if (i == call_no.length() - 1) {
            System.out.println("break");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
            System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
          } else if (Character.isDigit(call_no.charAt(i))) {
            System.out.println("loop 3 this is digit [ " + i + " ]");
            System.out.println(call_no.length() - 1);
            if (i == call_no.length() - 1) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
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
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            sets = "";
            j++;
            System.out.println("loop 3 ends at [ " + i + " ]");
            break;
          } 
          System.out.println("call no." + DDC + "]");
        }  
      if (((String)listPrint.get(list)).equals("Cutter3"))
        for (int i = j; i < call_no.length(); i++) {
          System.out.println("loop 4 start at [ " + i + " ]");
          Boolean isSpace = Boolean.valueOf(Character.isSpaceChar(call_no.charAt(i)));
          Boolean isPeriod = Boolean.valueOf(String.valueOf(call_no.charAt(i)).contentEquals("."));
          Boolean Letter = Boolean.valueOf(Character.isLetter(call_no.charAt(i)));
          Boolean Digit = Boolean.valueOf(Character.isDigit(call_no.charAt(i)));
          if (i == call_no.length() - 1) {
            System.out.println("break");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            j++;
            DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
            break;
          } 
          if (Letter.booleanValue() || Digit.booleanValue()) {
            System.out.println("break1");
            sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            if (i == call_no.length() - 1) {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
            } 
            j++;
          } else if (isSpace.booleanValue() || isPeriod.booleanValue()) {
            System.out.println("break2");
            if (isPeriod.booleanValue() && Character.isLetter(call_no.charAt(i + 1))) {
              sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
            } else {
              DDC.add("<strong class='" + (String)listPrint.get(list) + "'>" + sets + "</strong><br>");
              sets = "";
              j++;
              System.out.println("loop 4 ends at [ " + i + " ]");
            } 
            continue;
          } 
          System.out.println("loop 4 ends at [ " + i + " ]");
        }  
    } 
    System.out.println("SS" + DDC.toString());
    return DDC;
  }
  
  public List<String> LLCSplit(String itemCategory, String itemSMD, String call_no, String volume, String copyNo, String partNo) {
    int orilength = call_no.length();
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
    int i;
    for (i = j; i < call_no.length(); i++) {
      if (Character.isLetter(call_no.charAt(i))) {
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (!Character.isLetter(call_no.charAt(i))) {
        LLC.add(sets);
        sets = "";
        System.out.println("loop 1 ends at [ " + i + " ]--> " + sets);
        break;
      } 
      System.out.println("LLC" + LLC.toString());
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 2 start at [ " + i + " ]");
      if (Character.isSpaceChar(call_no.charAt(i))) {
        if (sets.trim().isEmpty()) {
          j++;
          System.out.println("im breaking at [ " + i + " ]");
          break;
        } 
        j++;
        LLC.add(sets);
        sets = "";
        break;
      } 
      if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("there's a digit at [ " + i + " ]");
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
          System.out.println("loop 2 ends at [ " + i + " ]");
          break;
        } 
        if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
          LLC.add(sets);
          sets = "";
          System.out.println("loop 2 ends at [ " + i + " ]");
          break;
        } 
      } 
      System.out.println("LLC" + LLC.toString());
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 3 start at [ " + i + " ]" + call_no.length());
      if (String.valueOf(call_no.charAt(i)).contentEquals(".")) {
        System.out.println("---> " + sets);
        System.out.println("XX--> " + call_no.charAt(i) + " <--XX");
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("---->11 " + sets);
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isLetter(call_no.charAt(i)) && Character.isDigit(call_no.charAt(i + 1))) {
        System.out.println("-----> 12" + sets);
        sets = String.valueOf(sets) + String.valueOf(call_no.charAt(i));
        j++;
      } else if (Character.isSpaceChar(call_no.charAt(i))) {
        System.out.println("-->13 " + sets);
        LLC.add(sets);
        sets = "";
        j++;
        System.out.println("loop 3 ends at [ " + i + " ]");
        break;
      } 
      System.out.println("LLC" + LLC.toString());
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 4 start at [ " + i + " ]");
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
          System.out.println("loop 4 ends at [ " + i + " ]");
          System.out.println("LLC" + LLC.toString());
        } 
        continue;
      } 
      System.out.println("loop 4 ends at [ " + i + " ]");
      System.out.println("LLC" + LLC.toString());
    } 
    if (volume != null && !volume.isEmpty())
      LLC.add(volume.trim()); 
    if (copyNo != null && !copyNo.isEmpty())
      LLC.add(copyNo.trim()); 
    if (partNo != null && !partNo.isEmpty())
      LLC.add(partNo.trim()); 
    System.out.println("LLC1" + LLC.toString());
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
        System.out.println("loop 1 ends at [ " + i + " ]--> " + sets);
        break;
      } 
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 2 start at [ " + i + " ]");
      if (Character.isSpaceChar(call_no.charAt(i))) {
        if (sets.trim().isEmpty()) {
          j++;
          System.out.println("im breaking at [ " + i + " ]");
          break;
        } 
        j++;
        LLC.add(sets);
        sets = "";
        break;
      } 
      if (Character.isDigit(call_no.charAt(i))) {
        System.out.println("there's a digit at [ " + i + " ]");
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
          System.out.println("loop 2 ends at [ " + i + " ]");
          break;
        } 
        if (String.valueOf(call_no.charAt(i + 1)).contentEquals(".") && Character.isSpaceChar(call_no.charAt(i))) {
          LLC.add(sets);
          sets = "";
          System.out.println("loop 2 ends at [ " + i + " ]");
          break;
        } 
      } 
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 3 start at [ " + i + " ]");
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
        System.out.println("loop 3 ends at [ " + i + " ]");
        break;
      } 
    } 
    for (i = j; i < call_no.length(); i++) {
      System.out.println("loop 4 start at [ " + i + " ]");
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
          System.out.println("loop 4 ends at [ " + i + " ]");
        } 
        continue;
      } 
      System.out.println("loop 4 ends at [ " + i + " ]");
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
  
  public String checkAccessionNo(String accessionNO) {
    String tenDigitAccessionNo = null;
    if (accessionNO.length() < 10) {
      tenDigitAccessionNo = String.format("%-10s", new Object[] { accessionNO });
      return tenDigitAccessionNo;
    } 
    return accessionNO;
  }
}
