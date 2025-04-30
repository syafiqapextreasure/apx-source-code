package com.ppk.topController.lost.LostMaterial.util;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormatter {
  public static String DBToDisplayFormat(String dbDate) {
    if (dbDate == null || dbDate.isEmpty())
      return " "; 
    String date = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    try {
      Date convertedCurrentDate = sdf.parse(dbDate);
      SimpleDateFormat newFormatter = new SimpleDateFormat("dd/MM/yyyy");
      date = newFormatter.format(convertedCurrentDate);
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    return date;
  }
  
  public static String DBToDisFormat(String dbDate) {
    if (dbDate == null || dbDate.isEmpty())
      return " "; 
    String date = "";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    try {
      Date convertedCurrentDate = sdf.parse(dbDate);
      SimpleDateFormat newFormatter = new SimpleDateFormat("yyyyMMdd");
      date = newFormatter.format(convertedCurrentDate);
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    return date;
  }
  
  public static String displayToDBFormat(String displayDate) {
    String date = "";
    String month = "";
    String year = "";
    String dates = "";
    if (!displayDate.isEmpty() || displayDate != "") {
      String[] dateParts = displayDate.split("/");
      date = String.format("%02d", new Object[] { Integer.valueOf(Integer.parseInt(dateParts[0])) });
      month = String.format("%02d", new Object[] { Integer.valueOf(Integer.parseInt(dateParts[1])) });
      year = String.format("%04d", new Object[] { Integer.valueOf(Integer.parseInt(dateParts[2])) });
      dates = String.valueOf(year) + month + date;
    } else {
      dates = "";
    } 
    return dates;
  }
  
  public static String getTodaySystemDate() {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    Calendar cal = Calendar.getInstance();
    return dateFormat.format(cal.getTime());
  }
  
  public static String getTodayDate() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar cal = Calendar.getInstance();
    return dateFormat.format(cal.getTime());
  }
  
  public static String getTodayTime() {
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
    Calendar cal = Calendar.getInstance();
    return timeFormat.format(cal.getTime());
  }
  
  public static String Time(String dbTime) {
    String time = "";
    SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
    try {
      Date convertedCurrentTime = sdf.parse(dbTime);
      SimpleDateFormat newFormatter = new SimpleDateFormat("hh:mm:ss aa");
      time = newFormatter.format(convertedCurrentTime);
    } catch (ParseException e) {
      e.printStackTrace();
    } 
    return time;
  }
  
  public static String DatetoStr(String msDate) {
    String sYear = "";
    String sMonth = "";
    String sDay = "";
    String dateStr = msDate.toString();
    dateStr = dateStr.replaceAll("/", "");
    return dateStr;
  }
  
  public static String TimetoStr(String msTime) {
    String sYear = "";
    String sMonth = "";
    String sDay = "";
    String dateStr = msTime.toString();
    dateStr = dateStr.replaceAll(":", "");
    return dateStr;
  }
  
  public static String formatDatelocal(String dbDate) {
    String year = dbDate.substring(0, 4);
    String month = dbDate.substring(4, 6);
    String date = dbDate.substring(6, 8);
    month = String.valueOf(Integer.parseInt(month));
    date = String.valueOf(Integer.parseInt(date));
    return String.valueOf(year) + "-" + month + "-" + date;
  }

public static DateTimeFormatter ofPattern(String string) {
	// TODO Auto-generated method stub
	return null;
}
}

