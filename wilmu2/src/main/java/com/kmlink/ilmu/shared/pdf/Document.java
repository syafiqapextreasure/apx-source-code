/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  freemarker.template.Configuration
 *  freemarker.template.DefaultObjectWrapper
 *  freemarker.template.ObjectWrapper
 *  freemarker.template.Template
 *  javax.servlet.ServletContext
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.StringUtils
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.kmlink.ilmu.shared.pdf;

import com.kmlink.ilmu.circulation.Charging.Patron;
import com.kmlink.ilmu.circulation.Global.Handler;
import com.kmlink.ilmu.overdueNotification.OverdueNotification;
import com.kmlink.ilmu.shared.global.Config;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import com.kmlink.ilmu.shared.pdf.EmailManagement;
import com.kmlink.ilmu.shared.pdf.Library;
import com.kmlink.ilmu.shared.pdf.saveMail;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Document {
    private static Connection conn = null;
    private static final String TAG_PATRONNAME = "(PATRONNAME)";
    private static final String TAG_LETTERDATE = "(LETTERDATE)";
    private static final String TAG_PATRONID = "(PATRONID)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_AUTHOR = "(AUTHOR)";
    private static final String TAG_CALLNO = "(CALLNO)";
    private static final String TAG_ACCNO = "(ACCNO)";
    private static final String TAG_BORROWDATE = "(BORROWDATE)";
    private static final String TAG_BORROWTIME = "(BORROWTIME)";
    private static final String TAG_DUEDATE = "(DUEDATE)";
    private static final String TAG_DUETIME = "(DUETIME)";
    private static final String TAG_REMINDER = "(REMINDER)";
    private static final String TAG_LATEBY = "(LATEBY)";
    private static final String TAG_FINES = "(FINES)";
    private static final String TAG_LETTERSUBJECT = "(LETTERSUBJECT)";
    private static final String TAG_LIBRARYNAME = "(LIBRARYNAME)";
    private static final String TAG_LIBRARYADD1 = "(LIBRARYADD1)";
    private static final String TAG_LIBRARYADD2 = "(LIBRARYADD2)";
    private static final String TAG_LIBRARYADD3 = "(LIBRARYADD3)";
    private static final String TAG_LIBRARYPOSTCODE = "(LIBRARYPOSTCODE)";
    private static final String TAG_LIBRARYTOWN = "(LIBRARYTOWN)";
    private static final String TAG_LIBRARYPHONE = "(LIBRARYPHONE)";
    private static final String TAG_BRANCHNAME = "(BRANCHNAME)";
    private static final String TAG_BRANCHADD1 = "(BRANCHADD1)";
    private static final String TAG_BRANCHADD2 = "(BRANCHADD2)";
    private static final String TAG_BRANCHADD3 = "(BRANCHADD3)";
    private static final String TAG_BRANCHPOSTCODE = "(BRANCHPOSTCODE)";
    private static final String TAG_BRANCHTOWN = "(BRANCHTOWN)";
    private static final String TAG_LETTERTIME = "(LETTERTIME)";
    private static final String TAG_VENDORNAME = "(VENDORNAME)";
    private static final String TAG_VENDORADDRESS1 = "(VENDORADDRESS1)";
    private static final String TAG_VENDORADDRESS2 = "(VENDORADDRESS2)";
    private static final String TAG_VENDORADDRESS3 = "(VENDORADDRESS3)";
    private static final String TAG_VENDORPOSTCODE = "(VENDORPOSTCODE)";
    private static final String TAG_VENDORTELEPHONE = "(VENDORTELEPHONE)";
    private static final String TAG_VENDORCONTACT = "(VENDORCONTACT)";
    private static final String TAG_REFERENCENO = "(REFERENCENO)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_DESIGNATION = "(DESIGNATION)";
    private static final String TAG_STAFFNAME = "(STAFFNAME)";
    private static final String TAG_STAFFDESIGNATION = "(STAFFDESIGNATION)";
    private static final String TAG_HODNAME = "(HODNAME)";
    private static final String TAG_HODDESIGNATION = "(HODDESIGNATION)";
    private static final String TAG_ORDERNO = "(ORDERNO)";
    private static final String TAG_ORDDATE = "(ORDDATE)";
    private static final String TAG_ISSN = "(ISSN)";
    private static final String TAG_DTSTART = "(DTSTART)";
    private static final String TAG_DTSTOP = "(DTSTOP)";
    private static final String TAG_INVOICENO = "(INVOICENO)";
    private static final String TAG_AMOUNT = "(AMOUNT)";
    private static final String TAG_FOREIGN = "(FOREIGN)";
    private static final String TAG_FOREX = "(FOREX)";
    private static final String TAG_CHEQUENO = "(CHEQUENO)";
    private static final String TAG_CHEQUEDATE = "(CHEQUEDATE)";
    private static final String TAG_VOUCHERNO = "(VOUCHERNO)";
    private static final String TAG_VOUCHERDATE = "(VOUCHERDATE)";
    private static final String TAG_REASON = "(REASON)";
    private static final String TAG_REVIEWLISTNO = "(REVIEWLISTNO)";
    private static final String TAG_REQUESTNO = "(REQUESTNO)";
    private static final String TAG_REQUESTOR = "(REQUESTOR)";
    private static final String TAG_REQUESTORNAME = "(REQUESTORNAME)";
    private static final String TAG_ISBN = "(ISBN)";
    private static final String TAG_CURRENCY = "(CURRENCY)";
    private static final String TAG_UNITFOREIGNPRICE = "(UNITFOREIGNPRICE)";
    private static final String TAG_UNITLOCALPRICE = "(UNITLOCALPRICE)";
    private static final String TAG_REMARKS = "(REMARKS)";
    private static final String TAG_RATING = "(RATING)";
    private static final String TAG_ISSVOLUME = "(ISSVOLUME)";
    private static final String TAG_ISSLABEL = "(ISSLABEL)";
    private static final String TAG_ISSTATUS = "(ISSTATUS)";
    private static final String TAG_ISSCOPYNO = "(ISSCOPYNO)";
    private static final String TAG_ISSTATUS1 = "(ISSTATUS1)";
    private static final String TAG_ISSTATUS2 = "(ISSTATUS2)";
    private static final String TAG_ISSTATUS3 = "(ISSTATUS3)";
    private static final String TAG_VOLFROM = "(VOLFROM)";
    private static final String TAG_VOLTO = "(VOLTO)";
    private static final String TAG_STARTDATE = "(STARTDATE)";
    private static final String TAG_FREQUENCY = "(FREQUENCY)";
    private static final String TAG_PATRONADD1 = "(PATRONADD1)";
    private static final String TAG_PATRONADD2 = "(PATRONADD2)";
    private static final String TAG_PATRONADD3 = "(PATRONADD3)";
    private static final String TAG_PATRONPOSTCODE = "(PATRONPOSTCODE)";
    private static final String TAG_PATRONTOWN = "(PATRONTOWN)";
    private static final String TAG_PATRONPHONE = "(PATRONPHONE)";
    private static Patron patrs;
    private String chargeDate;
    private String chargeTime;
    private String dueTime;
    private static final String Tpl_GetTpl;

    static {
        Tpl_GetTpl = Config.get("template");
    }

    public String getChargeDate() {
        return this.chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getChargeTime() {
        return this.chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getDueTime() {
        return this.dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public Document(String chargeDate, String chargeTime, String dueTime) {
        this.chargeDate = chargeDate;
        this.chargeTime = chargeTime;
        this.dueTime = dueTime;
    }

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String ER06(String mailNo, String accessionNo, String filteredPatronId, String filteredPatronName, String title, String callNo, String dueDate, String lateBy, String fines, String officetype) throws IOException {
        System.out.println("ER06 LINE 205");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String todayDate = dtf.format(localDate);
        ArrayList hashMapList = new ArrayList();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_PATRONNAME, filteredPatronName);
        replaceInfo.put(TAG_LETTERDATE, todayDate);
        replaceInfo.put(TAG_TITLE, title);
        replaceInfo.put(TAG_CALLNO, callNo);
        replaceInfo.put(TAG_ACCNO, accessionNo);
        replaceInfo.put(TAG_BORROWDATE, dueDate);
        replaceInfo.put(TAG_DUEDATE, dueDate);
        replaceInfo.put(TAG_LATEBY, lateBy);
        replaceInfo.put(TAG_FINES, fines);
        String textBuilder = Document.openFileThirdNotification("ER06.html", replaceInfo, "ER06");
        return new String(textBuilder);
    }

    public static String TR06(String mailNo, String accessionNo, String filteredPatronId, String filteredPatronName, String title, String callNo, String dueDate, String lateBy, String fines) {
        String text = "";
        StringBuilder html = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String todayDate = dtf.format(localDate);
        System.out.println(todayDate);
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_PATRONNAME, filteredPatronName);
        replaceInfo.put(TAG_LETTERDATE, todayDate);
        replaceInfo.put(TAG_TITLE, title);
        replaceInfo.put(TAG_CALLNO, callNo);
        replaceInfo.put(TAG_ACCNO, accessionNo);
        replaceInfo.put(TAG_BORROWDATE, dueDate);
        replaceInfo.put(TAG_DUEDATE, dueDate);
        replaceInfo.put(TAG_LATEBY, lateBy);
        replaceInfo.put(TAG_FINES, fines);
        hashMapList.add(replaceInfo);
        StringBuilder textBuilder = Document.openFile("ER06.html", hashMapList, "ER06");
        return new String(textBuilder);
    }

    public static String NO01(String mailNo, String accessionNo, String filteredPatronId, String filteredPatronName, String title, String callNo, String dueDate, String reminder) throws ParseException {
        String text = "";
        StringBuilder html = new StringBuilder();
        List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
        int i = 0;
        while (i < getDuedateTime.size()) {
            System.out.println("getduedatetime " + getDuedateTime.get(i).getChargeDate());
            ++i;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("current datetime" + dtf.format(now));
        String currentDateTime = now.format(dtf);
        String currentDate = currentDateTime.substring(0, 10);
        String currentTime = currentDateTime.substring(11, 19);
        System.out.println("currentDate" + currentDate);
        System.out.println("currentTime" + currentTime);
        String borrowTime = getDuedateTime.get(0).getChargeTime();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_PATRONNAME, filteredPatronName);
        replaceInfo.put(TAG_LETTERDATE, currentDate);
        replaceInfo.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
        replaceInfo.put(TAG_TITLE, title);
        replaceInfo.put(TAG_CALLNO, callNo);
        replaceInfo.put(TAG_ACCNO, accessionNo);
        replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
        replaceInfo.put(TAG_BORROWTIME, String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6)));
        replaceInfo.put(TAG_DUEDATE, dueDate);
        replaceInfo.put(TAG_DUETIME, getDuedateTime.get(0).getDueTime());
        replaceInfo.put(TAG_REMINDER, reminder);
        StringBuilder textBuilder = Document.openFile("ER06.html", replaceInfo, "ER06");
        return new String(textBuilder);
    }

    public static String PreviewDocument(String json, String reminderSelected, String liferayLogin, ServletContext servletContext, String officetype) throws ParseException, JSONException, IOException {
        System.out.println("ER06 LINE 335");
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashSet set = new HashSet(list);
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        Object author = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            System.out.println("ER06 LINE 370" + officetype);
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    System.out.println("ER06 LINE 416" + patron);
                    String filteredPatronId = patron.substring(0, patron.indexOf(","));
                    patrs = new Patron(filteredPatronId);
                    byte[] germanBytes = title.getBytes();
                    String asciiEncodedString = new String(germanBytes, StandardCharsets.US_ASCII);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    if (officetype.equals("department")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                        if (patrs.getmsPatronOHomePatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                    } else if (officetype.equals("office")) {
                        System.out.println("patrs.getMsPatronOTown()" + patrs.getMsPatronOTown());
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                        if (patrs.getMsPatronOTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                    } else if (officetype.equals("home")) {
                        System.out.println("patrs.getMsPatronTown()" + patrs.getMsPatronTown());
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                        if (patrs.getMsPatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                    }
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected == null) {
                    textBuilder = Document.openFile("ER10.html", replaceTitle, hashMapList, "ER10", servletContext);
                } else if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            JSONObject jsonObject;
            HashMap<String, String> replaceInfo;
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                replaceInfo = new HashMap<String, String>();
                jsonObject = jsonArray.getJSONObject(i3);
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                if (k == 0) {
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    patrs = new Patron(filteredPatronId);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    if (officetype.equals("department")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                        if (patrs.getmsPatronOHomePatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                    } else if (officetype.equals("office")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                        if (patrs.getMsPatronOTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                    } else if (officetype.equals("home")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                        if (patrs.getMsPatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                    }
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                    hashMapList.add(replaceInfo);
                } else if (k > 0) {
                    if (!((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) break;
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    patrs = new Patron(filteredPatronId);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    if (officetype.equals("department")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                        if (patrs.getmsPatronOHomePatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                    } else if (officetype.equals("office")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                        if (patrs.getMsPatronOTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                    } else if (officetype.equals("home")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                        if (patrs.getMsPatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                    }
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                    hashMapList.add(replaceInfo);
                }
                ++k;
            }
            if (reminderSelected == null) {
                textBuilder = Document.openFile("ER10.html", replaceTitle, hashMapList, "ER10", servletContext);
            } else if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
            }
        }
        return textBuilder;
    }

    public static String PreviewDocumentUseNestedLoop(String json, String reminderSelected) throws ParseException, JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronId = patron.substring(0, patron.indexOf(",") - 1);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceInfo.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            JSONObject jsonObject;
            HashMap<String, String> replaceInfo;
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                replaceInfo = new HashMap<String, String>();
                jsonObject = jsonArray.getJSONObject(i3);
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            i3 = 0;
            while (i3 < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                if (!((String)compareIndex.get(i3)).equals(compareIndex.get(i3 + 1))) {
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(i3);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                    break;
                }
                replaceInfo = new HashMap();
                jsonObject = jsonArray.getJSONObject(i3);
                if (jsonObject.has("Accession No")) {
                    accessionNo = jsonObject.getString("Accession No");
                }
                if (jsonObject.has("Patron")) {
                    patron = jsonObject.getString("Patron");
                }
                if (jsonObject.has("Title")) {
                    title = jsonObject.getString("Title");
                }
                if (jsonObject.has("Call No")) {
                    callNo = jsonObject.getString("Call No");
                }
                if (jsonObject.has("Due Date")) {
                    dueDate = jsonObject.getString("Due Date");
                }
                if (jsonObject.has("Reminder Type")) {
                    reminderType = jsonObject.getString("Reminder Type");
                }
                filteredPatronId = patron.substring(0, patron.indexOf(44));
                filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                getDuedateTime = Document.getBorrowDateTime(accessionNo);
                dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                now = LocalDateTime.now();
                currentDateTime = now.format(dtf);
                currentDate = currentDateTime.substring(0, 10);
                currentTime = currentDateTime.substring(11, 19);
                borrowTime = getDuedateTime.get(0).getChargeTime();
                dueTime = getDuedateTime.get(0).getDueTime();
                borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                replaceTitle.put(TAG_LETTERDATE, currentDate);
                replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                replaceTitle.put(TAG_PATRONID, filteredPatronId);
                replaceInfo.put(TAG_TITLE, title);
                replaceInfo.put(TAG_CALLNO, callNo);
                replaceInfo.put(TAG_ACCNO, accessionNo);
                replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                replaceInfo.put(TAG_DUEDATE, dueDate);
                replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                replaceInfo.put(TAG_REMINDER, reminderType);
                hashMapList.add(replaceInfo);
                ++i3;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
            }
        }
        return textBuilder;
    }

    public static String SendNotificationToMailBrowserUseNestedLoop(String json, String reminderSelected) throws ParseException, JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronIdSentNotice);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            i3 = 0;
            while (i3 < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                String filteredPatronIdSentNotice;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (!((String)compareIndex.get(i3)).equals(compareIndex.get(i3 + 1))) {
                    replaceInfo = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(i3);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                    break;
                }
                replaceInfo = new HashMap();
                jsonObject = jsonArray.getJSONObject(i3);
                if (jsonObject.has("Accession No")) {
                    accessionNo = jsonObject.getString("Accession No");
                }
                if (jsonObject.has("Patron")) {
                    patron = jsonObject.getString("Patron");
                }
                if (jsonObject.has("Title")) {
                    title = jsonObject.getString("Title");
                }
                if (jsonObject.has("Call No")) {
                    callNo = jsonObject.getString("Call No");
                }
                if (jsonObject.has("Due Date")) {
                    dueDate = jsonObject.getString("Due Date");
                }
                if (jsonObject.has("Reminder Type")) {
                    reminderType = jsonObject.getString("Reminder Type");
                }
                filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                filteredPatronId = patron.substring(0, patron.indexOf(44));
                filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                getDuedateTime = Document.getBorrowDateTime(accessionNo);
                dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                now = LocalDateTime.now();
                currentDateTime = now.format(dtf);
                currentDate = currentDateTime.substring(0, 10);
                currentTime = currentDateTime.substring(11, 19);
                borrowTime = getDuedateTime.get(0).getChargeTime();
                dueTime = getDuedateTime.get(0).getDueTime();
                borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                replaceTitle.put(TAG_LETTERDATE, currentDate);
                replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                replaceTitle.put(TAG_PATRONID, filteredPatronId);
                replaceInfo.put(TAG_TITLE, title);
                replaceInfo.put(TAG_CALLNO, callNo);
                replaceInfo.put(TAG_ACCNO, accessionNo);
                replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                replaceInfo.put(TAG_DUEDATE, dueDate);
                replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                replaceInfo.put(TAG_REMINDER, reminderType);
                hashMapList.add(replaceInfo);
                ++i3;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
            }
        }
        return textBuilder;
    }

    public static String SendNotificationToMailBrowser(String json, String reminderSelected, ServletContext servletContext) throws ParseException, JSONException, IOException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    String filteredPatronId = patron.substring(0, patron.indexOf(","));
                    patrs = new Patron(filteredPatronId);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceTitle.put(TAG_LIBRARYADD1, patrs.getMsPatronHomeAdd1());
                    replaceTitle.put(TAG_LIBRARYADD2, patrs.getMsPatronHomeAdd2());
                    replaceTitle.put(TAG_LIBRARYADD3, patrs.getMsPatronHomeAdd3());
                    replaceTitle.put(TAG_LIBRARYPOSTCODE, patrs.getMsPatronCode());
                    replaceTitle.put(TAG_LIBRARYTOWN, patrs.getMsPatronTown());
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                String filteredPatronIdSentNotice;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (k == 0) {
                    replaceInfo = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(","));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    patrs = new Patron(filteredPatronId);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceTitle.put(TAG_LIBRARYADD1, patrs.getMsPatronHomeAdd1());
                    replaceTitle.put(TAG_LIBRARYADD2, patrs.getMsPatronHomeAdd2());
                    replaceTitle.put(TAG_LIBRARYADD3, patrs.getMsPatronHomeAdd3());
                    replaceTitle.put(TAG_LIBRARYPOSTCODE, patrs.getMsPatronCode());
                    replaceTitle.put(TAG_LIBRARYTOWN, patrs.getMsPatronTown());
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                } else if (k > 0) {
                    if (!((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) break;
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(","));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    patrs = new Patron(filteredPatronId);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceTitle.put(TAG_LIBRARYADD1, patrs.getMsPatronHomeAdd1());
                    replaceTitle.put(TAG_LIBRARYADD2, patrs.getMsPatronHomeAdd2());
                    replaceTitle.put(TAG_LIBRARYADD3, patrs.getMsPatronHomeAdd3());
                    replaceTitle.put(TAG_LIBRARYPOSTCODE, patrs.getMsPatronCode());
                    replaceTitle.put(TAG_LIBRARYTOWN, patrs.getMsPatronTown());
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                ++k;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
            }
        }
        return textBuilder;
    }

    public static String SendNotificationToMailBrowser_original(String json, String reminderSelected) throws ParseException, JSONException {
        String textBuilder = null;
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> listInsertedRow = new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronId = patron.substring(0, patron.indexOf(44));
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                String filteredPatronIdSentNotice;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (k == 0) {
                    replaceInfo = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                } else if (k > 0) {
                    if (!((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) break;
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                }
                ++k;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
            }
        }
        return textBuilder;
    }

    public static String PrintDocument(String json, String liferayLogin, String reminderSelected) throws ParseException, JSONException, SQLException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronIdSentNotice);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                String filteredPatronIdSentNotice;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (k == 0) {
                    replaceInfo = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                } else if (k > 0) {
                    if (!((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) break;
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    hashMapList.add(replaceInfo);
                }
                ++k;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
            }
        }
        return textBuilder;
    }

    public static String PrintDocument_backup07052021(String json, String liferayLogin, String reminderSelected) throws ParseException, JSONException, SQLException {
        ArrayList<String> listInsertedRow = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        JSONObject jsonPassToClient = new JSONObject();
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        String dueDate = null;
        String reminderType = null;
        String fine = null;
        String textBuilder = null;
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    if (jsonObject.has("Fines")) {
                        fine = Integer.toString(jsonObject.getInt("Fines"));
                    }
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronId = patron.substring(0, patron.indexOf(44));
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    if (Integer.parseInt(fine) > 0) {
                        Document.insertRETRXN(filteredPatronId, accessionNo, fine, liferayLogin, Document.getCICount(filteredPatronId, accessionNo));
                    }
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                    int mailno = saveMail.Get98VALUE("MAILNO");
                    int newmailno = new Integer(mailno + 1);
                    saveMail.updatingmailno(newmailno);
                }
                if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
                }
                JSONArray ja = new JSONArray();
                for (String row : listInsertedRow) {
                    ja.put((Object)row);
                }
                jsonPassToClient.put("insertedRow", (Object)ja);
                jsonPassToClient.put("html", (Object)textBuilder.toString());
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                int newmailno;
                int mailno;
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronName;
                String filteredPatronId;
                String filteredPatronIdSentNotice;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (k == 0) {
                    replaceInfo = new HashMap<String, String>();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    if (jsonObject.has("Fines")) {
                        fine = String.valueOf(jsonObject.getInt("Fines"));
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    if (Float.parseFloat(fine) > 0.0f) {
                        Document.insertRETRXN(filteredPatronId, accessionNo, fine, liferayLogin, Document.getCICount(filteredPatronId, accessionNo));
                    }
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                    mailno = saveMail.Get98VALUE("MAILNO");
                    newmailno = new Integer(mailno + 1);
                    saveMail.updatingmailno(newmailno);
                } else if (k > 0 && ((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) {
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    if (jsonObject.has("Fines")) {
                        fine = Integer.toString(jsonObject.getInt("Fines"));
                    }
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    if (Float.parseFloat(fine) > 0.0f) {
                        Document.insertRETRXN(filteredPatronId, accessionNo, fine, liferayLogin, Document.getCICount(filteredPatronId, accessionNo));
                    }
                    hashMapList.add(replaceInfo);
                    listInsertedRow.add(accessionNo);
                    mailno = saveMail.Get98VALUE("MAILNO");
                    newmailno = new Integer(mailno + 1);
                    saveMail.updatingmailno(newmailno);
                }
                ++k;
            }
            if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04");
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05");
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06");
            }
            JSONArray ja = new JSONArray();
            for (String row : listInsertedRow) {
                ja.put((Object)row);
            }
            jsonPassToClient.put("insertedRow", (Object)ja);
            jsonPassToClient.put("html", (Object)textBuilder.toString());
        }
        return textBuilder;
    }

    public static String PrintDocumentNestedForLoop(String json, String liferayLogin, String reminderSelected, ServletContext servletContext, String officetype) throws ParseException, JSONException, SQLException, IOException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while (i < jsonArray.length()) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(jsonObject.optString("Patron"));
            ++i;
        }
        i = 0;
        while (i < list.size()) {
            ++i;
        }
        HashMap<String, String> replaceTitle = new HashMap<String, String>();
        ArrayList<String> compareIndex = new ArrayList<String>();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        String textBuilder = null;
        String accessionNo = null;
        String patron = null;
        String title = null;
        String callNo = null;
        Object author = null;
        String dueDate = null;
        String reminderType = null;
        if (jsonArray.length() == 1) {
            int i2 = 0;
            while (i2 < jsonArray.length()) {
                if (i2 == 0) {
                    HashMap<String, String> replaceInfo = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray.getJSONObject(i2);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    String filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    String filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    String filteredPatronId = patron.substring(0, patron.indexOf(","));
                    patrs = new Patron(filteredPatronId);
                    List<Document> getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDateTime = now.format(dtf);
                    String currentDate = currentDateTime.substring(0, 10);
                    String currentTime = currentDateTime.substring(11, 19);
                    String borrowTime = getDuedateTime.get(0).getChargeTime();
                    String dueTime = getDuedateTime.get(0).getDueTime();
                    String borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    String dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    if (officetype.equals("department")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                        if (patrs.getmsPatronOHomePatronCode() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronCode());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                    } else if (officetype.equals("office")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                        if (patrs.getMsPatronOTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                    } else if (officetype.equals("home")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                        if (patrs.getMsPatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                    }
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                    hashMapList.add(replaceInfo);
                }
                if (reminderSelected == null) {
                    textBuilder = Document.openFile("ER10.html", replaceTitle, hashMapList, "ER10", servletContext);
                } else if (reminderSelected.equals("first")) {
                    textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
                } else if (reminderSelected.equals("second")) {
                    textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
                } else if (reminderSelected.equals("third")) {
                    textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
                }
                ++i2;
            }
        } else if (jsonArray.length() > 1) {
            int i3 = 0;
            while (i3 < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i3).getString("Patron"));
                ++i3;
            }
            i3 = 0;
            while (i3 < compareIndex.size()) {
                String dueTimeFormat;
                String borrowTimeFormat;
                String dueTime;
                String borrowTime;
                String currentTime;
                String currentDate;
                String currentDateTime;
                LocalDateTime now;
                DateTimeFormatter dtf;
                List<Document> getDuedateTime;
                String filteredPatronIdSentNotice;
                String filteredPatronName;
                String filteredPatronId;
                JSONObject jsonObject;
                HashMap<String, String> replaceInfo;
                if (i3 != compareIndex.size() - 1 && !((String)compareIndex.get(i3)).equals(compareIndex.get(i3 + 1))) {
                    replaceInfo = new HashMap();
                    jsonObject = jsonArray.getJSONObject(i3);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    if (jsonObject.has("Title")) {
                        title = jsonObject.getString("Title");
                    }
                    if (jsonObject.has("Call No")) {
                        callNo = jsonObject.getString("Call No");
                    }
                    if (jsonObject.has("Due Date")) {
                        dueDate = jsonObject.getString("Due Date");
                    }
                    if (jsonObject.has("Reminder Type")) {
                        reminderType = jsonObject.getString("Reminder Type");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    patrs = new Patron(filteredPatronId);
                    filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                    OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                    getDuedateTime = Document.getBorrowDateTime(accessionNo);
                    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    now = LocalDateTime.now();
                    currentDateTime = now.format(dtf);
                    currentDate = currentDateTime.substring(0, 10);
                    currentTime = currentDateTime.substring(11, 19);
                    borrowTime = getDuedateTime.get(0).getChargeTime();
                    dueTime = getDuedateTime.get(0).getDueTime();
                    borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                    dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                    if (officetype.equals("department")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                        if (patrs.getmsPatronOHomePatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                    } else if (officetype.equals("office")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                        if (patrs.getMsPatronOTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                    } else if (officetype.equals("home")) {
                        replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                        replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                        replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                        replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                        if (patrs.getMsPatronTown() == null) {
                            replaceTitle.put(TAG_PATRONTOWN, "");
                        } else {
                            replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                        }
                        replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                    }
                    replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                    replaceTitle.put(TAG_LETTERDATE, currentDate);
                    replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                    replaceTitle.put(TAG_PATRONID, filteredPatronId);
                    replaceInfo.put(TAG_TITLE, title);
                    replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                    replaceInfo.put(TAG_CALLNO, callNo);
                    replaceInfo.put(TAG_ACCNO, accessionNo);
                    replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                    replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                    replaceInfo.put(TAG_DUEDATE, dueDate);
                    replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                    replaceInfo.put(TAG_REMINDER, reminderType);
                    replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                    hashMapList.add(replaceInfo);
                    break;
                }
                replaceInfo = new HashMap<String, String>();
                jsonObject = jsonArray.getJSONObject(i3);
                if (jsonObject.has("Accession No")) {
                    accessionNo = jsonObject.getString("Accession No");
                }
                if (jsonObject.has("Patron")) {
                    patron = jsonObject.getString("Patron");
                }
                if (jsonObject.has("Title")) {
                    title = jsonObject.getString("Title");
                }
                if (jsonObject.has("Call No")) {
                    callNo = jsonObject.getString("Call No");
                }
                if (jsonObject.has("Due Date")) {
                    dueDate = jsonObject.getString("Due Date");
                }
                if (jsonObject.has("Reminder Type")) {
                    reminderType = jsonObject.getString("Reminder Type");
                }
                filteredPatronId = patron.substring(0, patron.indexOf(44));
                filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                filteredPatronIdSentNotice = patron.substring(0, patron.indexOf(44));
                patrs = new Patron(filteredPatronId);
                OverdueNotification.updateSentNotificationCICRC(filteredPatronIdSentNotice, accessionNo, reminderSelected);
                getDuedateTime = Document.getBorrowDateTime(accessionNo);
                dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                now = LocalDateTime.now();
                currentDateTime = now.format(dtf);
                currentDate = currentDateTime.substring(0, 10);
                currentTime = currentDateTime.substring(11, 19);
                borrowTime = getDuedateTime.get(0).getChargeTime();
                dueTime = getDuedateTime.get(0).getDueTime();
                borrowTimeFormat = String.join((CharSequence)":", borrowTime.substring(0, 2), borrowTime.substring(2, 4), borrowTime.substring(4, 6));
                dueTimeFormat = String.join((CharSequence)":", dueTime.substring(0, 2), dueTime.substring(2, 4), dueTime.substring(4, 6));
                if (officetype.equals("department")) {
                    replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOHomeAdd1());
                    replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOHomeAdd2());
                    replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOHomeAdd3());
                    replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getmsPatronOHomePatronCode());
                    if (patrs.getmsPatronOHomePatronTown() == null) {
                        replaceTitle.put(TAG_PATRONTOWN, "");
                    } else {
                        replaceTitle.put(TAG_PATRONTOWN, patrs.getmsPatronOHomePatronTown());
                    }
                    replaceTitle.put(TAG_PATRONPHONE, patrs.getmsPatronOHomeHtel());
                } else if (officetype.equals("office")) {
                    replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronOPatronAdd1());
                    replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronOPatronAdd2());
                    replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronOPatronAdd3());
                    replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronOPostcode());
                    if (patrs.getMsPatronOTown() == null) {
                        replaceTitle.put(TAG_PATRONTOWN, "");
                    } else {
                        replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronOTown());
                    }
                    replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronOOtel());
                } else if (officetype.equals("home")) {
                    replaceTitle.put(TAG_PATRONADD1, patrs.getMsPatronHomeAdd1());
                    replaceTitle.put(TAG_PATRONADD2, patrs.getMsPatronHomeAdd2());
                    replaceTitle.put(TAG_PATRONADD3, patrs.getMsPatronHomeAdd3());
                    replaceTitle.put(TAG_PATRONPOSTCODE, patrs.getMsPatronCode());
                    if (patrs.getMsPatronTown() == null) {
                        replaceTitle.put(TAG_PATRONTOWN, "");
                    } else {
                        replaceTitle.put(TAG_PATRONTOWN, patrs.getMsPatronTown());
                    }
                    replaceTitle.put(TAG_PATRONPHONE, patrs.getMsPatronHomeTel());
                }
                replaceTitle.put(TAG_PATRONNAME, filteredPatronName);
                replaceTitle.put(TAG_LETTERDATE, currentDate);
                replaceTitle.put(TAG_LETTERTIME, Document.changeTimeFormat(currentTime));
                replaceTitle.put(TAG_PATRONID, filteredPatronId);
                replaceInfo.put(TAG_TITLE, title);
                replaceInfo.put(TAG_AUTHOR, Document.getAuthor(accessionNo));
                replaceInfo.put(TAG_CALLNO, callNo);
                replaceInfo.put(TAG_ACCNO, accessionNo);
                replaceInfo.put(TAG_BORROWDATE, Document.changeStringDate(getDuedateTime.get(0).getChargeDate()));
                replaceInfo.put(TAG_BORROWTIME, borrowTimeFormat);
                replaceInfo.put(TAG_DUEDATE, dueDate);
                replaceInfo.put(TAG_DUETIME, dueTimeFormat);
                replaceInfo.put(TAG_REMINDER, reminderType);
                replaceTitle.put(TAG_OFFICERNAME, liferayLogin);
                hashMapList.add(replaceInfo);
                ++i3;
            }
            if (reminderSelected == null) {
                textBuilder = Document.openFile("ER10.html", replaceTitle, hashMapList, "ER10", servletContext);
            } else if (reminderSelected.equals("first")) {
                textBuilder = Document.openFile("ER04.html", replaceTitle, hashMapList, "ER04", servletContext);
            } else if (reminderSelected.equals("second")) {
                textBuilder = Document.openFile("ER05.html", replaceTitle, hashMapList, "ER05", servletContext);
            } else if (reminderSelected.equals("third")) {
                textBuilder = Document.openFile("ER06.html", replaceTitle, hashMapList, "ER06", servletContext);
            }
        }
        return textBuilder;
    }

    private static String getAuthor(String accessionNo) {
        String matNo = null;
        String author = null;
        String sql1 = "SELECT CT03MATNO FROM CTDOCM WHERE CT03DOCNO='" + accessionNo + "'";
        System.out.println("getAuthor" + sql1);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DBConnection.getConnection();
        try {
            stmt = conn.prepareStatement(sql1);
            rs = stmt.executeQuery();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            int i = 1;
            while (i <= columnCount) {
                System.out.println("what is metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                ++i;
            }
            while (rs.next()) {
                matNo = Handler.ifIsNull(rs.getString("CT03MATNO"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "SELECT REPLACE(REPLACE(T3.CT05SRAW, '|a',''),'|b', ' ') AS CallNo,\r\nREPLACE(REPLACE(REPLACE(REPLACE(T5.CT05SRAW, '|a',''),'|b', ' : '),'|c',' / '), '|f',', ') AS TITLE,\r\nREPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(T7.CT05SRAW, '|a',''),'|b', ' '),'|d', ' '),'|k', ' '),'|i', ' '),'-', ' ') AS AUTHOR,\r\nREPLACE(REPLACE(T9.CT05SRAW, '|a',''),'|b', ' ') AS PUBLICATION,\r\nT11.GL05DESC, T12.GL71DESC\r\nFROM CTMATM T1\r\nLEFT JOIN CTPONT T2 ON (T2.CT06MATNO = T1.CT02MATNO AND T2.CT06TAG = '090')\r\nLEFT JOIN CTCALL T3 ON (T3.CT05POINTER = T2.CT06POINTER)\r\nLEFT JOIN CTPONT T4 ON (T4.CT06MATNO = T1.CT02MATNO AND T4.CT06TAG = '245')\r\nLEFT JOIN CTTITL T5 ON (T5.CT05POINTER = T4.CT06POINTER )\r\nLEFT JOIN CTPONT T6 ON (T6.CT06MATNO = T1.CT02MATNO AND T6.CT06TAG = '100')\r\nLEFT JOIN CTAUTH T7 ON (T7.CT05POINTER = T6.CT06POINTER )\r\nLEFT JOIN CTPONT T8 ON (T8.CT06MATNO = T1.CT02MATNO AND T8.CT06TAG = '260')\r\nLEFT JOIN CTPUBL T9 ON (T9.CT05POINTER = T8.CT06POINTER )\r\nLEFT JOIN CTDOCM T10 ON (T10.CT03MATNO = T1.CT02MATNO)\r\nLEFT JOIN GLLOCA T11 ON (T11.GL05LOCA = T10.CT03LOCA)\r\nLEFT JOIN GLBRNC T12 ON (T12.GL71BRNC = T11.GL05BRNC)\r\nWHERE CT02MATNO='" + matNo + "'";
        System.out.println(sql2);
        try {
            try {
                stmt = conn.prepareStatement(sql2);
                rs = stmt.executeQuery();
                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                int i = 1;
                while (i <= columnCount) {
                    System.out.println("metadata [" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    author = Handler.ifIsNull(rs.getString("AUTHOR"));
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
        return author;
    }

    public static List<String> returnInsertedAccesionNo() {
        ArrayList<String> accessionNoList = new ArrayList<String>();
        accessionNoList.add("adfadsbdsa");
        accessionNoList.add("12412213");
        return accessionNoList;
    }

    public static void insertRETRXN(String patronId, String accessionNo, String fee, String recordedBy, String CICounter) throws ParseException, SQLException {
        int iCounter = Document.getRETRXN();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = now.format(dtf);
        String currentDate = currentDateTime.substring(0, 8);
        String insertRETRXN = "INSERT INTO RETRXN (RE01TXNO,RE01CODE,RE01DATE,RE01AMT,RE01PDAMT,RE01STAT,RE01UPDREF,RE01PATR,RE01REFER,RE01OFFID,RE01RCVFROM,RE01DOCNO,RE01SENT1,RE01SENT2,RE01SENT3,RE01CICOUNTER,RE01PAYMODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String msCode = "100";
        conn = DBConnection.getConnection();
        Object rs = null;
        try {
            try {
                PreparedStatement stmt1 = null;
                PreparedStatement stmt2 = null;
                stmt1 = conn.prepareStatement(insertRETRXN);
                stmt1.setInt(1, iCounter + 1);
                stmt1.setString(2, msCode);
                stmt1.setString(3, currentDate);
                stmt1.setBigDecimal(4, new BigDecimal(fee));
                stmt1.setBigDecimal(5, new BigDecimal(0.0));
                stmt1.setString(6, "0");
                stmt1.setString(7, accessionNo);
                stmt1.setString(8, patronId);
                stmt1.setString(9, "");
                stmt1.setString(10, recordedBy);
                stmt1.setString(11, "NULL");
                stmt1.setString(12, accessionNo);
                stmt1.setString(13, "NULL");
                stmt1.setString(14, "NULL");
                stmt1.setString(15, "NULL");
                stmt1.setInt(16, Integer.parseInt(CICounter));
                stmt1.setString(17, "NULL");
                int insertedRow_RETRXN = stmt1.executeUpdate();
                String sql3 = "Update GLNUMB set GL98VALUE='" + (iCounter + 1) + "'" + "where GL98FUNC='TRXNO'";
                System.out.println(sql3);
                stmt2 = conn.prepareStatement(sql3);
                int count2 = stmt2.executeUpdate();
                System.out.println("count2" + count2);
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
        String sql2 = "SELECT GL98VALUE FROM GLNUMB where GL98FUNC='TRXNO'";
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
                    iCounter = rs.getInt("GL98VALUE");
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

    public static String getCICount(String patronId, String accessionNo) {
        String CICounter = "";
        String sql2 = "SELECT CI02COUNTER FROM CICIRC WHERE CI02DOCNO='" + accessionNo + "' AND CI02PATR='" + patronId + "'";
        System.out.println(sql2);
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
                    System.out.println("[" + metadata.getColumnTypeName(i) + "|" + metadata.getColumnName(i) + "]" + ", ");
                    ++i;
                }
                while (rs.next()) {
                    CICounter = rs.getString("CI02COUNTER");
                }
                System.out.println(CICounter);
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
        return CICounter;
    }

    public static String TON01(String mailNo, String accessionNo, String filteredPatronId, String filteredPatronName, String title, String callNo, String dueDate) {
        String text = "";
        StringBuilder html = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String todayDate = dtf.format(localDate);
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_PATRONNAME, filteredPatronName);
        replaceInfo.put(TAG_LETTERDATE, todayDate);
        replaceInfo.put(TAG_TITLE, title);
        replaceInfo.put(TAG_CALLNO, callNo);
        replaceInfo.put(TAG_ACCNO, accessionNo);
        replaceInfo.put(TAG_BORROWDATE, dueDate);
        replaceInfo.put(TAG_DUEDATE, dueDate);
        hashMapList.add(replaceInfo);
        StringBuilder textBuilder = Document.openFile("TON01.txt", hashMapList, "TON01");
        return new String(textBuilder);
    }

    public static StringBuilder openFile(String filename, HashMap<String, String> replaceInfo, String action) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder textBuilder = new StringBuilder();
        BufferedReader in = null;
        String line = null;
        StringBuilder html = new StringBuilder();
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains("{LOOP}")) {
                html.append(String.valueOf(line) + "\n");
            } else if (line.contains("{END LOOP}")) {
                Document.arrayData(html.toString(), replaceInfo);
            }
            String[] stringArray = tags;
            int n = tags.length;
            int n2 = 0;
            while (n2 < n) {
                String tag = stringArray[n2];
                if (line.contains(tag) && replaceInfo.get(tag) == null) {
                    replaceInfo.put(tag, "");
                }
                ++n2;
            }
            lines.add(line);
            try {
                line = in.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        return textBuilder;
    }

    public static String openFile(String filename, HashMap<String, String> replaceTitle, List<HashMap<String, String>> hashMapList, String action) {
        BufferedReader in = null;
        String line = "";
        StringBuilder html = new StringBuilder();
        ArrayList<String> loop = new ArrayList<String>();
        boolean mark = false;
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            while ((line = in.readLine()) != null) {
                if (line.contains("{LOOP}")) {
                    loop.add(line);
                    continue;
                }
                if (line.contains("{END LOOP}")) {
                    loop.add(line);
                    if (loop.isEmpty()) continue;
                    int i = 0;
                    while (i < hashMapList.size()) {
                        HashMap<String, String> m = hashMapList.get(i);
                        ArrayList<String> copy = new ArrayList<String>(loop);
                        int j = 0;
                        while (j < copy.size()) {
                            for (Map.Entry<String, String> entry : m.entrySet()) {
                                copy.set(j, StringUtils.replace((String)((String)copy.get(j)), (String)entry.getKey(), (String)entry.getValue()).replaceAll("\\{LOOP\\}", "").replaceAll("\\{END LOOP\\}", ""));
                            }
                            html.append((String)copy.get(j));
                            ++j;
                        }
                        ++i;
                    }
                    loop.clear();
                    continue;
                }
                if (line.startsWith("<!--")) {
                    mark = true;
                    continue;
                }
                if (line.endsWith("-->")) {
                    mark = false;
                    continue;
                }
                if (mark) continue;
                if (replaceTitle.size() > 0) {
                    for (Map.Entry<String, String> e : replaceTitle.entrySet()) {
                        line = StringUtils.replace((String)line, (String)e.getKey(), (String)e.getValue());
                    }
                    html.append(line);
                    continue;
                }
                html.append(line);
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String htmlFilter = html.toString().replaceAll("[\\n\\t]", "");
        return htmlFilter;
    }

    public static String openFile(String filename, HashMap<String, String> replaceTitle, List<HashMap<String, String>> hashMapList, String action, ServletContext context) throws IOException {
        BufferedReader in = null;
        String line = "";
        StringBuilder html = new StringBuilder();
        ArrayList<String> loop = new ArrayList<String>();
        InputStream is = context.getResourceAsStream("image/libLogo.jpg");
        byte[] fileContent = null;
        String encodedString = null;
        try {
            if (is != null) {
                fileContent = IOUtils.toByteArray((InputStream)is);
                encodedString = Base64.getEncoder().encodeToString(fileContent);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        boolean mark = false;
        in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
        while ((line = in.readLine()) != null) {
            if (line.contains("<img src=")) {
                if (encodedString == null) continue;
                html.append("<img src= data:image/png;base64," + encodedString + ">");
                continue;
            }
            if (line.contains("{LOOP}")) {
                loop.add(line);
                continue;
            }
            if (line.contains("{END LOOP}")) {
                loop.add(line);
                if (loop.isEmpty()) continue;
                int i = 0;
                while (i < hashMapList.size()) {
                    HashMap<String, String> m = hashMapList.get(i);
                    ArrayList<String> copy = new ArrayList<String>(loop);
                    int j = 0;
                    while (j < copy.size()) {
                        for (Map.Entry<String, String> entry : m.entrySet()) {
                            copy.set(j, StringUtils.replace((String)((String)copy.get(j)), (String)entry.getKey(), (String)entry.getValue()).replaceAll("\\{LOOP\\}", "").replaceAll("\\{END LOOP\\}", ""));
                        }
                        html.append((String)copy.get(j));
                        ++j;
                    }
                    ++i;
                }
                loop.clear();
                continue;
            }
            if (line.startsWith("<!--")) {
                mark = true;
                continue;
            }
            if (line.endsWith("-->")) {
                mark = false;
                continue;
            }
            if (mark) continue;
            if (replaceTitle.size() > 0) {
                for (Map.Entry<String, String> e : replaceTitle.entrySet()) {
                    try {
                        if (e.getValue() == null && e.getKey().equals(TAG_LIBRARYADD3)) continue;
                        line = StringUtils.replace((String)line, (String)e.getKey(), (String)e.getValue());
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (line.contains("LIBRARYADD3")) continue;
                html.append(line);
                continue;
            }
            html.append(line);
        }
        in.close();
        String htmlFilter = html.toString().replaceAll("[\\n\\t]", "");
        return htmlFilter;
    }

    public static String writeFile_ER06(String filename, HashMap<String, String> replaceTitle, List<HashMap<String, String>> hashMapList, String action) {
        BufferedReader in = null;
        String line = "";
        StringBuilder html = new StringBuilder();
        ArrayList<String> loop = new ArrayList<String>();
        boolean mark = false;
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            while ((line = in.readLine()) != null) {
                if (line.contains("{LOOP}")) {
                    loop.add(line);
                    continue;
                }
                if (line.contains("{END LOOP}")) {
                    loop.add(line);
                    if (loop.isEmpty()) continue;
                    int i = 0;
                    while (i < hashMapList.size()) {
                        HashMap<String, String> m = hashMapList.get(i);
                        ArrayList<String> copy = new ArrayList<String>(loop);
                        int j = 0;
                        while (j < copy.size()) {
                            for (Map.Entry<String, String> entry : m.entrySet()) {
                                copy.set(j, StringUtils.replace((String)((String)copy.get(j)), (String)entry.getKey(), (String)entry.getValue()).replaceAll("\\{LOOP\\}", "").replaceAll("\\{END LOOP\\}", ""));
                            }
                            html.append((String)copy.get(j));
                            ++j;
                        }
                        ++i;
                    }
                    loop.clear();
                    continue;
                }
                if (line.startsWith("<!--")) {
                    mark = true;
                    continue;
                }
                if (line.endsWith("-->")) {
                    mark = false;
                    continue;
                }
                if (mark) continue;
                if (replaceTitle.size() > 0) {
                    for (Map.Entry<String, String> e : replaceTitle.entrySet()) {
                        line = StringUtils.replace((String)line, (String)e.getKey(), (String)e.getValue());
                    }
                    html.append(line);
                    continue;
                }
                html.append(line);
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String htmlFilter = html.toString().replaceAll("[\\n\\t]", "");
        return htmlFilter;
    }

    public static StringBuilder openFile(String filename, List<HashMap<String, String>> hashMapList, String action) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder textBuilder = new StringBuilder();
        BufferedReader in = null;
        String line = null;
        StringBuilder html = new StringBuilder();
        Object tags = null;
        HashMap dateTime = new HashMap();
        for (HashMap<String, String> map : hashMapList) {
            for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                String key = mapEntry.getKey();
                String string = mapEntry.getValue();
            }
        }
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains("{LOOP}")) {
                html.append(String.valueOf(line) + "\n");
            } else if (line.contains("{END LOOP}")) {
                lines.add(Document.arrayData(html.toString(), hashMapList));
            }
            try {
                line = in.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        return textBuilder;
    }

    public static String openFileThirdNotification(String filename, HashMap<String, String> replaceInfo, String action) throws IOException {
        System.out.println("ER06 LINE 3444");
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder textBuilder = new StringBuilder();
        BufferedReader in = null;
        String line = null;
        StringBuilder html = new StringBuilder();
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            if (line.contains("{LOOP}")) {
                html.append(String.valueOf(line) + "\n");
            } else if (!line.contains("{END LOOP}")) {
                String[] stringArray = tags;
                int n = tags.length;
                int n2 = 0;
                while (n2 < n) {
                    String tag = stringArray[n2];
                    if (line.contains(tag)) {
                        if (replaceInfo.get(tag) == null) {
                            replaceInfo.put(tag, "");
                        }
                        line = line.replace(tag, String.valueOf(replaceInfo.get(tag)));
                    }
                    ++n2;
                }
                lines.add(line);
            }
            try {
                line = in.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        return textBuilder.toString();
    }

    public static String arrayData(String html, HashMap<String, String> hashMap) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            lines.add(trial.toString());
        }
        return html;
    }

    public static String arrayData(String html, List<HashMap<String, String>> hashMapList) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            for (HashMap<String, String> map : hashMapList) {
                trial.append("<tr>");
                for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                    String key = mapEntry.getKey();
                    String value = mapEntry.getValue();
                    trial.append("<td>");
                    trial.append(mapEntry.getValue());
                    trial.append("</td>");
                }
                trial.append("</tr>");
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }

    public static StringBuilder openFileTXT(String filename, HashMap<String, String> replaceInfo, String action) {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder textBuilder = new StringBuilder();
        BufferedReader in = null;
        String line = null;
        StringBuilder html = new StringBuilder();
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
        System.out.println("Array tags: " + tags);
        try {
            System.out.println("file name: " + filename);
            System.out.println("Tpl_GetTpl: " + Tpl_GetTpl);
            in = new BufferedReader(new FileReader(String.valueOf(Tpl_GetTpl) + filename));
            line = in.readLine();
            System.out.println("print line: " + line);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            String[] stringArray = tags;
            int n = tags.length;
            int n2 = 0;
            while (n2 < n) {
                String tag = stringArray[n2];
                if (line.contains(tag)) {
                    if (replaceInfo.get(tag) == null) {
                        replaceInfo.put(tag, "");
                    }
                    line = line.replace(tag, String.valueOf(replaceInfo.get(tag)));
                }
                ++n2;
            }
            lines.add("\n" + line);
            try {
                line = in.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        return textBuilder;
    }

    public static String ES11(Library library, Library letterTitile, Library getVendDetail, Library hod, Library loginDetail, List<EmailManagement> claimncancel, String action) {
        String text = "";
        StringBuilder html = new StringBuilder();
        ArrayList<HashMap<String, String>> hashMapList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (action.equals("1st Claim")) {
            replaceInfo.put(TAG_LETTERSUBJECT, String.valueOf(letterTitile.getName()) + " FIRST");
        }
        if (action.equals("2nd Claim")) {
            replaceInfo.put(TAG_LETTERSUBJECT, String.valueOf(letterTitile.getName()) + " SECOND");
        }
        if (action.equals("3rd Claim")) {
            replaceInfo.put(TAG_LETTERSUBJECT, String.valueOf(letterTitile.getName()) + " THIRD");
        }
        replaceInfo.put(TAG_LIBRARYNAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getPoscode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getbranch());
        replaceInfo.put(TAG_BRANCHADD1, library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getbrncadd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getbrncpostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getbrnctown());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_VENDORNAME, getVendDetail.getGL39NAME());
        replaceInfo.put(TAG_VENDORADDRESS1, getVendDetail.getGL39ADD1());
        replaceInfo.put(TAG_VENDORADDRESS2, getVendDetail.getGL39ADD2());
        replaceInfo.put(TAG_VENDORADDRESS3, getVendDetail.getGL39ADD3());
        replaceInfo.put(TAG_VENDORPOSTCODE, getVendDetail.getGL39PCODE());
        replaceInfo.put(TAG_VENDORTELEPHONE, getVendDetail.getGL39TELNO());
        replaceInfo.put(TAG_VENDORCONTACT, getVendDetail.getPERINC());
        replaceInfo.put(TAG_REFERENCENO, "");
        replaceInfo.put(TAG_OFFICERNAME, hod.getName());
        replaceInfo.put(TAG_STAFFNAME, loginDetail.getName());
        replaceInfo.put(TAG_HODNAME, hod.getName());
        hashMapList.add(replaceInfo);
        StringBuilder textBuilder = Document.openFile("ER06.html", hashMapList, "ER06");
        return new String(textBuilder);
    }

    public String read(String fileName, Map<String, String> params) {
        try {
            String templateLocation = "/aaaaa";
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(templateLocation));
            cfg.setObjectWrapper((ObjectWrapper)new DefaultObjectWrapper());
            Template template = null;
            template = cfg.getTemplate(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintWriter out = new PrintWriter(baos);
            template.process(params, (Writer)out);
            out.flush();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            int size = bais.available();
            byte[] bytes = new byte[size];
            bais.read(bytes, 0, size);
            String result = new String(bytes, Charset.forName("UTF-8"));
            baos.close();
            bais.close();
            return result;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return fileName;
        }
    }

    public static List<Document> getBorrowDateTime(String accessionNo) {
        ArrayList<Document> document = new ArrayList<Document>();
        String query = "SELECT CI02CDATE, CI02CTIME, CI02DTIME FROM CICIRC WHERE CI02DOCNO=? AND CI02FLAG='C'\r\n";
        Connection conn = null;
        try {
            try {
                PreparedStatement stmt = null;
                ResultSet rs = null;
                conn = DBConnection.getConnection();
                stmt = conn.prepareStatement(query);
                stmt.setString(1, accessionNo);
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
                        System.out.println(rs.getString(i2));
                        row = String.valueOf(row) + rs.getString(i2) + ", ";
                        ++i2;
                    }
                    System.out.println("row: " + row);
                    document.add(new Document(rs.getString("CI02CDATE"), rs.getString("CI02CTIME"), rs.getString("CI02DTIME")));
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
        return document;
    }

    public static String changeStringDate(String inputDate) throws ParseException {
        Date inputDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(inputDate);
        System.out.println(inputDateFormat);
        String outputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(inputDateFormat);
        System.out.println(outputDate);
        return outputDate;
    }

    public static String convertTime(String input) {
        String output = String.valueOf(input.substring(0, 2)) + ":" + input.substring(2, 4) + ":" + input.substring(4, 6) + " " + Document.ampm(input.substring(0, 2));
        System.out.println(output);
        return output;
    }

    public static String changeTimeFormat(String input) {
        String output = String.valueOf(input.substring(0, 8)) + " " + Document.ampm(input.substring(0, 2));
        System.out.println(output);
        return output;
    }

    public static String ampm(String input) {
        String output = null;
        int parsedInput = Integer.parseInt(input);
        if (parsedInput >= 0 && parsedInput <= 11) {
            output = "am";
        } else if (parsedInput >= 12 && parsedInput <= 23) {
            output = "pm";
        }
        return output;
    }

    public static List<String> removedAcessionNo(String json, String liferayLogin, String reminderSelected) {
        ArrayList<String> listInsertedRow;
        block15: {
            String patron;
            String accessionNo;
            ArrayList<String> compareIndex;
            JSONArray jsonArray;
            block16: {
                jsonArray = new JSONArray(json);
                ArrayList<String> list = new ArrayList<String>();
                listInsertedRow = new ArrayList<String>();
                int i = 0;
                while (i < jsonArray.length()) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    list.add(jsonObject.optString("Patron"));
                    ++i;
                }
                compareIndex = new ArrayList<String>();
                Object textBuilder = null;
                accessionNo = null;
                patron = null;
                if (jsonArray.length() != 1) break block16;
                int i2 = 0;
                while (i2 < jsonArray.length()) {
                    if (i2 == 0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i2);
                        if (jsonObject.has("Accession No")) {
                            accessionNo = jsonObject.getString("Accession No");
                        }
                        if (jsonObject.has("Patron")) {
                            patron = jsonObject.getString("Patron");
                        }
                        listInsertedRow.add(accessionNo);
                    }
                    ++i2;
                }
                break block15;
            }
            if (jsonArray.length() <= 1) break block15;
            int i = 0;
            while (i < jsonArray.length()) {
                compareIndex.add(jsonArray.getJSONObject(i).getString("Patron"));
                ++i;
            }
            int k = 0;
            while (k < compareIndex.size()) {
                String filteredPatronName;
                String filteredPatronId;
                JSONObject jsonObject;
                if (k == 0) {
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    listInsertedRow.add(accessionNo);
                } else if (k > 0) {
                    if (!((String)compareIndex.get(k - 1)).equals(compareIndex.get(k))) break;
                    jsonObject = jsonArray.getJSONObject(k);
                    if (jsonObject.has("Accession No")) {
                        accessionNo = jsonObject.getString("Accession No");
                    }
                    if (jsonObject.has("Patron")) {
                        patron = jsonObject.getString("Patron");
                    }
                    filteredPatronId = patron.substring(0, patron.indexOf(44));
                    filteredPatronName = patron.substring(patron.lastIndexOf(",") + 1);
                    listInsertedRow.add(accessionNo);
                }
                ++k;
            }
        }
        return listInsertedRow;
    }
}
