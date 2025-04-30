/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.ill.pdf;

import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import com.ilmu.global.globalFunc;
import com.ilmu.ill.pdf.Library;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class Document {
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
    private static final String TAG_LETTERSUBJECT = "(LETTERSUBJECT)";
    private static final String TAG_LETTERDATE = "(LETTERDATE)";
    private static final String TAG_REQUESTORNAME = "(REQUESTORNAME)";
    private static final String TAG_REQUESTORADD1 = "(REQUESTORADD1)";
    private static final String TAG_REQUESTORADD2 = "(REQUESTORADD2)";
    private static final String TAG_REQUESTORADD3 = "(REQUESTORADD3)";
    private static final String TAG_REQUESTNO = "(REQUESTNO)";
    private static final String TAG_DATEREQUESTED = "(DATEREQUESTED)";
    private static final String TAG_AUTHOR = "(AUTHOR)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_PUBLISHER = "(PUBLISHER)";
    private static final String TAG_SMD = "(SMD)";
    private static final String TAG_DUEDATE = "(DUEDATE)";
    private static final String TAG_DUETIME = "(DUETIME)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_DESIGNATION = "(DESIGNATION)";
    private static final String TAG_STAFFNAME = "(STAFFNAME)";
    private static final String TAG_STAFFDESIGNATION = "(STAFFDESIGNATION)";
    private static final String TAG_HODNAME = "(HODNAME)";
    private static final String TAG_HODDESIGNATION = "(HODDESIGNATION)";
    private static final String TAG_REQUESTORID = "(REQUESTORID)";
    private static final String TAG_REFERENCENO = "(REFERENCENO)";
    private static final String TAG_VOLUME = "(VOLUME)";
    private static final String TAG_ISSUE = "(ISSUE)";
    private static final String TAG_PAGENUMBER = "(PAGENUMBER)";
    private static final String TAG_DOCUMENTTYPE = "(DOCUMENTTYPE)";
    private static final String TAG_CONTACTPERSON = "(CONTACTPERSON)";
    private static final String Tpl_GetTpl = Config.get("template");

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String EL01(Library library, String requestno, String RequestedDate, Library patron, String liferayLogin, String sAccessionNo, String ControlNo, String sRequestor) {
        String cirhead;
        System.out.println("ILL INCOMING");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getsLibraryName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getsLibraryAdd1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getsLibraryAdd2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getsLibraryAdd3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getsLibraryPostcode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getssLibraryTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getsBranchName());
        replaceInfo.put(TAG_BRANCHADD1, library.getsBranchAdd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getsBranchAdd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getsBranchAdd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getssBranchPostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getssBranchTown());
        replaceInfo.put(TAG_LETTERSUBJECT, library.getsLetterSubject());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_REQUESTORNAME, patron.getName());
        replaceInfo.put(TAG_REQUESTORADD1, patron.getadd1());
        replaceInfo.put(TAG_REQUESTORADD2, patron.getadd2());
        replaceInfo.put(TAG_REQUESTORADD3, patron.getadd3());
        replaceInfo.put(TAG_REQUESTNO, requestno);
        replaceInfo.put(TAG_DATEREQUESTED, RequestedDate);
        System.out.println("ControlNoControlNo2" + ControlNo);
        String sTitle = Library.GetTagValue(ControlNo, "245", "No");
        String sAuthor = Library.GetTagValue(ControlNo, "100", "No");
        String sPublisher = Library.GetTagValue(ControlNo, "260", "No");
        String sVolume = Library.GetVolume(sAccessionNo, ControlNo);
        String sSMD = Library.GetSMD(sAccessionNo, ControlNo);
        if (sAccessionNo.equals(null)) {
            System.out.println("kkkkk");
        } else {
            System.out.println("nottttttt");
        }
        replaceInfo.put(TAG_AUTHOR, sAuthor);
        replaceInfo.put(TAG_TITLE, sTitle);
        replaceInfo.put(TAG_PUBLISHER, sPublisher);
        replaceInfo.put(TAG_SMD, sSMD);
        replaceInfo.put(TAG_DUEDATE, "");
        replaceInfo.put(TAG_DUETIME, DateTime.getCurrentTime());
        String gsUserId = liferayLogin;
        String sDeptHead = cirhead = Library.GetDeptOfficer();
        String sSender = cirhead.equals("") ? gsUserId : (!sDeptHead.equals("") ? sDeptHead : gsUserId);
        replaceInfo.put(TAG_OFFICERNAME, Library.GetPatronName(sSender));
        replaceInfo.put(TAG_DESIGNATION, Library.GetDesignation(sSender));
        replaceInfo.put(TAG_STAFFNAME, Library.GetPatronName(gsUserId));
        replaceInfo.put(TAG_STAFFDESIGNATION, Library.GetDesignation(gsUserId));
        replaceInfo.put(TAG_HODNAME, Library.GetPatronName(sDeptHead));
        replaceInfo.put(TAG_HODDESIGNATION, Library.GetDesignation(sDeptHead));
        String textBuilder = Document.openFile("EL01.html", replaceInfo, "EL01");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFile(String filename, HashMap<String, String> replaceInfo, String action) {
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

    public static String EL05(Library letterTitile, Library library, String liferayLogin, Library patron, String sRequestor, globalFunc reqDetail, String refno, String reqDate, String ControlNo) {
        String cirhead;
        System.out.println("ILL INCOMING");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getsLibraryName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getsLibraryAdd1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getsLibraryAdd2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getsLibraryAdd3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getsLibraryPostcode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getssLibraryTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getsBranchName());
        replaceInfo.put(TAG_BRANCHADD1, library.getsBranchAdd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getsBranchAdd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getsBranchAdd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getssBranchPostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getssBranchTown());
        replaceInfo.put(TAG_LETTERSUBJECT, library.getsLetterSubject());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_REQUESTORNAME, patron.getName());
        replaceInfo.put(TAG_REQUESTORADD1, patron.getadd1());
        replaceInfo.put(TAG_REQUESTORADD2, patron.getadd2());
        replaceInfo.put(TAG_REQUESTORADD3, patron.getadd3());
        replaceInfo.put(TAG_REQUESTORID, sRequestor);
        replaceInfo.put(TAG_REFERENCENO, refno);
        replaceInfo.put(TAG_DATEREQUESTED, reqDate);
        String sTitle = Library.GetTagValue(ControlNo, "245", "No");
        String sAuthor = Library.GetTagValue(ControlNo, "100", "No");
        String sPublisher = Library.GetTagValue(ControlNo, "260", "No");
        replaceInfo.put(TAG_AUTHOR, sAuthor);
        replaceInfo.put(TAG_TITLE, sTitle);
        replaceInfo.put(TAG_PUBLISHER, sPublisher);
        replaceInfo.put(TAG_VOLUME, reqDetail.getrsVolume());
        replaceInfo.put(TAG_ISSUE, reqDetail.getrsIssue());
        replaceInfo.put(TAG_PAGENUMBER, reqDetail.getrsPageNumber());
        replaceInfo.put(TAG_DOCUMENTTYPE, reqDetail.getrsDocumentType());
        String gsUserId = liferayLogin;
        String sDeptHead = cirhead = Library.GetDeptOfficer();
        String sSender = cirhead.equals("") ? gsUserId : (!sDeptHead.equals("") ? sDeptHead : gsUserId);
        replaceInfo.put(TAG_OFFICERNAME, Library.GetPatronName(sSender));
        replaceInfo.put(TAG_DESIGNATION, Library.GetDesignation(sSender));
        replaceInfo.put(TAG_STAFFNAME, Library.GetPatronName(gsUserId));
        replaceInfo.put(TAG_STAFFDESIGNATION, Library.GetDesignation(gsUserId));
        replaceInfo.put(TAG_HODNAME, Library.GetPatronName(sDeptHead));
        replaceInfo.put(TAG_HODDESIGNATION, Library.GetDesignation(sDeptHead));
        String textBuilder = Document.openFileEL05("EL05.html", replaceInfo, "EL05");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileEL05(String filename, HashMap<String, String> replaceInfo, String action) {
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

    public static String EL03(Library letterTitile, Library library, String liferayLogin, Library patron, String sRequestor, globalFunc reqDetail, String refno, String reqDate, String ControlNo, String sDueDate) {
        String cirhead;
        System.out.println("ILL INCOMING");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getsLibraryName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getsLibraryAdd1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getsLibraryAdd2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getsLibraryAdd3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getsLibraryPostcode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getssLibraryTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getsBranchName());
        replaceInfo.put(TAG_BRANCHADD1, library.getsBranchAdd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getsBranchAdd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getsBranchAdd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getssBranchPostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getssBranchTown());
        replaceInfo.put(TAG_LETTERSUBJECT, library.getsLetterSubject());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_REQUESTORID, sRequestor);
        replaceInfo.put(TAG_REQUESTORNAME, patron.getName());
        replaceInfo.put(TAG_REQUESTORADD1, patron.getadd1());
        replaceInfo.put(TAG_REQUESTORADD2, patron.getadd2());
        replaceInfo.put(TAG_REQUESTORADD3, patron.getadd3());
        replaceInfo.put(TAG_REFERENCENO, refno);
        replaceInfo.put(TAG_DATEREQUESTED, reqDate);
        replaceInfo.put(TAG_DUEDATE, sDueDate);
        String sAuthor = Library.GetTagValue(ControlNo, "100", "No");
        String sTitle = Library.GetTagValue(ControlNo, "245", "No");
        String sPublisher = Library.GetTagValue(ControlNo, "260", "No");
        replaceInfo.put(TAG_AUTHOR, sAuthor);
        replaceInfo.put(TAG_TITLE, sTitle);
        replaceInfo.put(TAG_PUBLISHER, sPublisher);
        replaceInfo.put(TAG_VOLUME, reqDetail.getrsVolume());
        replaceInfo.put(TAG_ISSUE, reqDetail.getrsIssue());
        replaceInfo.put(TAG_PAGENUMBER, reqDetail.getrsPageNumber());
        replaceInfo.put(TAG_DOCUMENTTYPE, reqDetail.getrsDocumentType());
        String gsUserId = liferayLogin;
        String sDeptHead = cirhead = Library.GetDeptOfficer();
        String sSender = cirhead.equals("") ? gsUserId : (!sDeptHead.equals("") ? sDeptHead : gsUserId);
        replaceInfo.put(TAG_OFFICERNAME, Library.GetPatronName(sSender));
        replaceInfo.put(TAG_DESIGNATION, Library.GetDesignation(sSender));
        replaceInfo.put(TAG_STAFFNAME, Library.GetPatronName(gsUserId));
        replaceInfo.put(TAG_STAFFDESIGNATION, Library.GetDesignation(gsUserId));
        replaceInfo.put(TAG_HODNAME, Library.GetPatronName(sDeptHead));
        replaceInfo.put(TAG_HODDESIGNATION, Library.GetDesignation(sDeptHead));
        String textBuilder = Document.openFileEL03("EL03.html", replaceInfo, "EL03");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileEL03(String filename, HashMap<String, String> replaceInfo, String action) {
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

    public static String EL02(Library letterTitile, Library library, String liferayLogin, Library patron, String sRequestor, String refno, String ControlNo, String sContactPerson, String reqDate, String action2, String doctype, String sVolume, String sIssue, String sPageNumber) {
        String cirhead;
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getsLibraryName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getsLibraryAdd1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getsLibraryAdd2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getsLibraryAdd3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getsLibraryPostcode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getssLibraryTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getsBranchName());
        replaceInfo.put(TAG_BRANCHADD1, library.getsBranchAdd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getsBranchAdd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getsBranchAdd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getssBranchPostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getssBranchTown());
        replaceInfo.put(TAG_LETTERSUBJECT, library.getsLetterSubject());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_CONTACTPERSON, sContactPerson);
        replaceInfo.put(TAG_REQUESTORNAME, patron.getName());
        replaceInfo.put(TAG_REQUESTORADD1, patron.getadd1());
        replaceInfo.put(TAG_REQUESTORADD2, patron.getadd2());
        replaceInfo.put(TAG_REQUESTORADD3, patron.getadd3());
        replaceInfo.put(TAG_REFERENCENO, refno);
        replaceInfo.put(TAG_DATEREQUESTED, reqDate);
        String sAuthor = Library.GetTagValue(ControlNo, "100", "No");
        String sTitle = Library.GetTagValue(ControlNo, "245", "No");
        String sPublisher = Library.GetTagValue(ControlNo, "260", "No");
        replaceInfo.put(TAG_AUTHOR, sAuthor);
        replaceInfo.put(TAG_TITLE, sTitle);
        replaceInfo.put(TAG_PUBLISHER, sPublisher);
        replaceInfo.put(TAG_VOLUME, sVolume);
        replaceInfo.put(TAG_ISSUE, sIssue);
        replaceInfo.put(TAG_PAGENUMBER, sPageNumber);
        replaceInfo.put(TAG_DOCUMENTTYPE, doctype);
        String gsUserId = liferayLogin;
        String sDeptHead = cirhead = Library.GetDeptOfficer();
        String sSender = cirhead.equals("") ? gsUserId : (!sDeptHead.equals("") ? sDeptHead : gsUserId);
        replaceInfo.put(TAG_OFFICERNAME, Library.GetPatronName(sSender));
        replaceInfo.put(TAG_DESIGNATION, Library.GetDesignation(sSender));
        replaceInfo.put(TAG_STAFFNAME, Library.GetPatronName(gsUserId));
        replaceInfo.put(TAG_STAFFDESIGNATION, Library.GetDesignation(gsUserId));
        replaceInfo.put(TAG_HODNAME, Library.GetPatronName(sDeptHead));
        replaceInfo.put(TAG_HODDESIGNATION, Library.GetDesignation(sDeptHead));
        String textBuilder = Document.openFileEL03("EL02.html", replaceInfo, "EL02");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String EL06(Library letterTitile, Library library, String liferayLogin, Library patron, String sRequestor, String refno, String ControlNo, String sContactPerson, String reqDate, String doctype, String sVolume, String sIssue, String sPageNumber, String dtDue) {
        String cirhead;
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getsLibraryName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getsLibraryAdd1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getsLibraryAdd2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getsLibraryAdd3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getsLibraryPostcode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getssLibraryTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getsBranchName());
        replaceInfo.put(TAG_BRANCHADD1, library.getsBranchAdd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getsBranchAdd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getsBranchAdd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getssBranchPostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getssBranchTown());
        replaceInfo.put(TAG_LETTERSUBJECT, library.getsLetterSubject());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_CONTACTPERSON, sContactPerson);
        replaceInfo.put(TAG_REQUESTORNAME, patron.getName());
        replaceInfo.put(TAG_REQUESTORADD1, patron.getadd1());
        replaceInfo.put(TAG_REQUESTORADD2, patron.getadd2());
        replaceInfo.put(TAG_REQUESTORADD3, patron.getadd3());
        replaceInfo.put(TAG_REFERENCENO, refno);
        replaceInfo.put(TAG_DATEREQUESTED, reqDate);
        replaceInfo.put(TAG_DUEDATE, dtDue);
        String sAuthor = Library.GetTagValue(ControlNo, "100", "No");
        String sTitle = Library.GetTagValue(ControlNo, "245", "No");
        String sPublisher = Library.GetTagValue(ControlNo, "260", "No");
        replaceInfo.put(TAG_AUTHOR, sAuthor);
        replaceInfo.put(TAG_TITLE, sTitle);
        replaceInfo.put(TAG_PUBLISHER, sPublisher);
        replaceInfo.put(TAG_VOLUME, sVolume);
        replaceInfo.put(TAG_ISSUE, sIssue);
        replaceInfo.put(TAG_PAGENUMBER, sPageNumber);
        replaceInfo.put(TAG_DOCUMENTTYPE, doctype);
        String gsUserId = liferayLogin;
        String sDeptHead = cirhead = Library.GetDeptOfficer();
        String sSender = cirhead.equals("") ? gsUserId : (!sDeptHead.equals("") ? sDeptHead : gsUserId);
        replaceInfo.put(TAG_OFFICERNAME, Library.GetPatronName(sSender));
        replaceInfo.put(TAG_DESIGNATION, Library.GetDesignation(sSender));
        replaceInfo.put(TAG_STAFFNAME, Library.GetPatronName(gsUserId));
        replaceInfo.put(TAG_STAFFDESIGNATION, Library.GetDesignation(gsUserId));
        replaceInfo.put(TAG_HODNAME, Library.GetPatronName(sDeptHead));
        replaceInfo.put(TAG_HODDESIGNATION, Library.GetDesignation(sDeptHead));
        String textBuilder = Document.openFileEL03("EL06.html", replaceInfo, "EL06");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }
}
