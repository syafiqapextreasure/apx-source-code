/*
 * Decompiled with CFR 0.152.
 */
package com.kmlink.ilmu.circulation.PDF;

import com.kmlink.ilmu.circulation.PDF.Library;
import com.kmlink.ilmu.circulation.PDF.renewalLetter;
import com.kmlink.ilmu.circulation.PDF.reservationletter;
import com.kmlink.ilmu.circulation.resv.Reservation;
import com.kmlink.ilmu.shared.global.Config;
import com.kmlink.ilmu.shared.global.DateTime;
import com.kmlink.ilmu.shared.global.Handler;
import com.kmlink.ilmu.shared.global.ISBD;
import com.kmlink.ilmu.shared.global.connection.DBConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Document {
    private int counts;
    private String CI02DOCNO;
    private String CT05SRAW;
    private String CI02CDATE;
    private String CI02CTIME;
    private String CI02DDATE;
    private String CI02DTIME;
    private static final String TAG_LIBRARY_ORG_NAME = "(LIBRARYNAME)";
    private static final String TAG_RECEIVERID = "(PATRONID)";
    private static final String TAG_LIBRARY_ADDRESS_1 = "(LIBRARYADDRESS1)";
    private static final String TAG_LIBRARY_ADDRESS_2 = "(LIBRARYADDRESS2)";
    private static final String TAG_LIBRARY_ADDRESS_3 = "(LIBRARYADDRESS3)";
    private static final String TAG_LIBRARY_POSTCODE = "(LIBRARYPOSTCODE)";
    private static final String TAG_LIBRARY_TOWN = "(LIBRARYTOWN)";
    private static final String TAG_LIBRARY_BRANCHNAME = "(BRANCHNAME)";
    private static final String TAG_LIBRARY_BRANCHADD1 = "(BRANCHADD1)";
    private static final String TAG_LIBRARY_BRANCHADD2 = "(BRANCHADD1)";
    private static final String TAG_LIBRARY_BRANCHADD3 = "(BRANCHADD3)";
    private static final String TAG_LIBRARY_BRANCHPOSTCODE = "(BRANCHPOSTCODE)";
    private static final String TAG_LIBRARY_BRANCHTOWN = "(BRANCHTOWN)";
    private static final String TAG_PNAME = "(PNAME)";
    private static final String TAG_PADDRESS = "(PADDRESS)";
    private static final String TAG_PATRONID = "(PATRONID)";
    private static final String TAG_PATRONNAME = "(PATRONNAME)";
    private static final String TAG_ADDR1 = "(ADDR1)";
    private static final String TAG_ADDR2 = "(ADDR2)";
    private static final String TAG_ADDR3 = "(ADDR3)";
    private static final String TAG_SLIPHEADER = "(SLIPHEADER)";
    private static final String TAG_LIBRARY_TEL = "(LIBRARY_TEL)";
    private static final String TAG_LIBRARY_FAX = "(LIBRARY_FAX)";
    private static final String TAG_DATE = "(DATETIME)";
    private static final String TAG_BIL = "(BIL)";
    private static final String TAG_ACCNO = "(ACCNO)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_CHARGE_DATE = "(CHARGE_DATE)";
    private static final String TAG_CHARGE_TIME = "(CHARGE_TIME)";
    private static final String TAG_DUE_DATE = "(DUE_DATE)";
    private static final String TAG_DUE_TIME = "(DUE_TIME)";
    private static final String TAG_OFFICER_ID = "(OFFICERID)";
    private static final String TAG_OFFICER_NAME = "(OFFICERNAME)";
    private static final String TAG_COMPUTERNAME = "(COMPUTERNAME)";
    private static final String TAG_LIBRARYNAME = "(LIBRARYNAME)";
    private static final String TAG_LIBRARYADD1 = "(LIBRARYADD1)";
    private static final String TAG_LIBRARYADD2 = "(LIBRARYADD2)";
    private static final String TAG_LIBRARYADD3 = "(LIBRARYADD3)";
    private static final String TAG_LIBRARYTOWN = "(LIBRARYTOWN)";
    private static final String TAG_LIBRARYPOSTCODE = "(LIBRARYPOSTCODE)";
    private static final String TAG_BRANCHNAME = "(BRANCHNAME)";
    private static final String TAG_BRANCHADD1 = "(BRANCHADD1)";
    private static final String TAG_BRANCHADD2 = "(BRANCHADD2)";
    private static final String TAG_BRANCHADD3 = "(BRANCHADD3)";
    private static final String TAG_BRANCHTOWN = "(BRANCHTOWN)";
    private static final String TAG_BRANCHPOSTCODE = "(BRANCHPOSTCODE)";
    private static final String TAG_LETTERDATE = "(LETTERDATE)";
    private static final String TAG_LETTERTIME = "(LETTERTIME)";
    private static final String TAG_LETTERSUBJECT = "(LETTERSUBJECT)";
    private static final String TAG_PATRONADD1 = "(PATRONADD1)";
    private static final String TAG_PATRONADD2 = "(PATRONADD2)";
    private static final String TAG_PATRONADD3 = "(PATRONADD3)";
    private static final String TAG_HOMETEL = "(HOMETEL)";
    private static final String TAG_OFFTEL = "(OFFTEL)";
    private static final String TAG_POSTCODE = "(POSTCODE)";
    private static final String TAG_TOWN = "(TOWN)";
    private static final String TAG_CALLNO = "(CALLNO)";
    private static final String TAG_ISBN = "(ISBN)";
    private static final String TAG_DUEDATE = "(DUEDATE)";
    private static final String TAG_PICKVAR = "(PICKVAR)";
    private static final String TAG_PICKBRNC = "(PICKBRNC)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_DESIGNATION = "(DESIGNATION)";
    private static final String TAG_STAFFNAME = "(STAFFNAME)";
    private static final String TAG_STAFFDESIGNATION = "(STAFFDESIGNATION)";
    private static final String TAG_HODNAME = "(HODNAME)";
    private static final String TAG_HODDESIGNATION = "(HODDESIGNATION)";
    private static final String TAG_COURSE = "(COURSE)";
    private static final String TAG_COLLECTIONDAYS = "(COLLECTIONDAYS)";
    private static final String TAG_ACCESSIONNOHEADER = "(ACCESSIONNOHEADER)";
    private static final String TAG_TITLEHEADER = "(TITLEHEADER)";
    private static final String TAG_PATRONBARCODEVALUE = "(TAG_PATRONBARCODEVALUE)";
    private static final String TAG_OFFICERID = "(OFFICERID)";
    private static final String TAG_THANKYOU = "(THANKYOU)";
    private static final String TAG_MEMEXPIRYDATE = "(MEMEXPIRYDATE)";
    private static final String TAG_RENEWDATE = "(RENEWDATE)";
    private static final String TAG_RENEWTIME = "(RENEWTIME)";
    private static final String TAG_DDATE = "(DDATE)";
    private static final String TAG_RDATE = "(RDATE)";
    private static final String TAG_NDATE = "(NDATE)";
    private static final String TAG_LATEBY = "(LATEBY)";
    private static final String TAG_FINES = "(FINES)";
    private static final String TAG_BEGINVARIABLE = "(BEGINVARIABLE)";
    private static final String TAG_DISCHARGEDATE = "(DISCHARGEDATE)";
    private static final String TAG_DISCHARGETIME = "(DISCHARGETIME)";
    private static final String TAG_CDATE = "(CDATE)";
    private static final String TAG_DATETIME = "(DATETIME)";
    private static final String TAG_DUEDT = "(DUEDT)";
    private static final String TAG_RENEWDT = "(RENEWDT)";
    private static final String TAG_NEWDUEDT = "(NEWDUEDT)";
    private static final String TAG_LATE = "(LATE)";
    private static final String TAG_FINESHEADER = "(FINESHEADER)";
    private static final String TAG_CHARGEDDT = "(CHARGEDDT)";
    private static final String TAG_RETURNEDDT = "(RETURNEDDT)";
    private static final String TAG_RESVDATE = "(RETURNEDDT)";
    private static final String Tpl_GetTpl = Config.get("template");

    public String getAccession() {
        return this.CI02DOCNO;
    }

    public String getTitle() {
        return this.CT05SRAW;
    }

    public String getChargeDate() {
        return this.CI02CDATE;
    }

    public String getChargeTime() {
        return this.CI02CTIME;
    }

    public String getDueDate() {
        return this.CI02DDATE;
    }

    public String getDueTime() {
        return this.CI02DTIME;
    }

    public static String charge(Library library, List<Document> charge, Library patron, Library sender) {
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put(TAG_SLIPHEADER, "Charging Slip");
        replaceInfo.put("(DATETIME)", DateTime.getSysTodayDate());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put("(PATRONID)", patron.getPatrId());
        replaceInfo.put(TAG_PATRONNAME, patron.getPatrName());
        replaceInfo.put(TAG_ADDR1, patron.getPatrAdd1());
        replaceInfo.put(TAG_ADDR2, patron.getPatrAdd2());
        replaceInfo.put(TAG_ADDR3, patron.getPatrAdd3());
        replaceInfo.put("(OFFICERID)", sender.getPatrId());
        replaceInfo.put("(OFFICERNAME)", sender.getName());
        replaceInfo.put(TAG_COMPUTERNAME, Library.getComputerName());
        String textBuilder = Document.openFile("charge.html", charge, replaceInfo, "charging");
        return new String(textBuilder);
    }

    public static String openFile(String filename, List<Document> charge, HashMap<String, String> replaceInfo, String action) {
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
                try {
                    String test = null;
                    if (action.equals("charging")) {
                        test = Document.arrayData(html.toString(), charge);
                    }
                    lines.add(test);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
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

    public Document(String CI02DOCNO, String CT05SRAW, String CI02CDATE, String CI02CTIME, String CI02DDATE, String CI02DTIME) {
        this.CI02DOCNO = CI02DOCNO;
        this.CT05SRAW = CT05SRAW;
        this.CI02CDATE = CI02CDATE;
        this.CI02CTIME = CI02CTIME;
        this.CI02DDATE = CI02DDATE;
        this.CI02DTIME = CI02DTIME;
    }

    public static String arrayData(String html, List<Document> charge) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            List<ISBD> isbd = ISBD.getPunctuation("245");
            System.out.println("HTML11" + html.toString());
            int i = 0;
            while (i < charge.size()) {
                trial.append(html.toString().replace(TAG_BIL, String.valueOf(i + 1)).replace(TAG_ACCNO, charge.get(i).getAccession()).replace(TAG_TITLE, Handler.getSubfield(Handler.removeSubf(charge.get(i).getTitle()), isbd)).replace(TAG_CHARGE_DATE, charge.get(i).getChargeDate()).replace(TAG_CHARGE_TIME, charge.get(i).getChargeTime()).replace(TAG_DUE_DATE, charge.get(i).getDueDate()).replace(TAG_DUE_TIME, charge.get(i).getDueTime()).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
        }
        return trial.toString();
    }

    public static List<Document> charges(String accession) throws SQLException {
        ArrayList<Document> details = new ArrayList<Document>();
        String sql2 = "Select CI02DOCNO, CI02CDATE, CI02CTIME, CI02DDATE, CI02DTIME, CT05SRAW from CICIRC, CTPONT, CTTITL where ct06matno = ci02matno and CT06POINTER = CT05POINTER and ct06tag = '245' and ci02docno IN (" + accession + ") " + "and ci02flag = 'C'";
        System.out.println(sql2);
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rsObj = stmt.executeQuery(sql2);
        boolean count = false;
        try {
            while (rsObj.next()) {
                Document charge = new Document(rsObj.getString("CI02DOCNO"), rsObj.getString("CT05SRAW"), DateTime.DBToDisplayFormat(rsObj.getString("CI02CDATE")), DateTime.Time(rsObj.getString("CI02CTIME")), DateTime.DBToDisplayFormat(rsObj.getString("CI02DDATE")), DateTime.Time(rsObj.getString("CI02DTIME")));
                details.add(charge);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public static String ER01(Library library, Library letterTitile, reservationletter patronDetail, reservationletter resvDetail, reservationletter collectDays, Library sender, Library hod) {
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_PATRONNAME, patronDetail.getPATRONNAME());
        replaceInfo.put(TAG_PATRONADD1, patronDetail.getPATRONADD1());
        replaceInfo.put(TAG_PATRONADD2, patronDetail.getPATRONADD2());
        replaceInfo.put(TAG_PATRONADD3, patronDetail.getPATRONADD3());
        replaceInfo.put("(PATRONID)", "ID:" + patronDetail.getPATRONID());
        replaceInfo.put(TAG_HOMETEL, patronDetail.getHOMETEL());
        replaceInfo.put(TAG_OFFTEL, patronDetail.getOFFTEL());
        replaceInfo.put(TAG_POSTCODE, patronDetail.getPOSTCODE());
        replaceInfo.put(TAG_TOWN, patronDetail.getTOWN());
        replaceInfo.put(TAG_TITLE, resvDetail.getTITLE());
        replaceInfo.put(TAG_CALLNO, resvDetail.getCALLNO());
        replaceInfo.put(TAG_ISBN, resvDetail.getISBN());
        replaceInfo.put(TAG_ACCNO, resvDetail.getACCNO());
        replaceInfo.put(TAG_DUEDATE, resvDetail.getDUEDATE());
        replaceInfo.put(TAG_PICKVAR, "Pickup Branch");
        replaceInfo.put(TAG_PICKBRNC, resvDetail.getPICKBRNC());
        replaceInfo.put("(OFFICERNAME)", sender.getName());
        replaceInfo.put(TAG_DESIGNATION, sender.getdesgination());
        replaceInfo.put(TAG_STAFFNAME, resvDetail.getSTAFFNAME());
        replaceInfo.put(TAG_STAFFDESIGNATION, resvDetail.getSTAFFDESIGNATION());
        replaceInfo.put(TAG_HODNAME, hod.getName());
        replaceInfo.put(TAG_HODDESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_COURSE, patronDetail.getCOURSE());
        replaceInfo.put(TAG_COLLECTIONDAYS, collectDays.getCOLLECTIONDAYS());
        String textBuilder = Document.openFileER01("ER01.html", replaceInfo, "ER01");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileER01(String filename, HashMap<String, String> replaceInfo, String action) {
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

    public static String Renewal(Library library, Library sender, renewalLetter GetPatronInfo, String CT03DOCNO, int lengthCT03DOCNO) {
        System.out.println("Renewal");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put(TAG_SLIPHEADER, "RENEWAL SLIP");
        replaceInfo.put("(DATETIME)", "Date");
        replaceInfo.put(TAG_ACCESSIONNOHEADER, "Accession No.");
        replaceInfo.put(TAG_TITLEHEADER, "Title");
        replaceInfo.put("(PATRONID)", GetPatronInfo.getPATRONID());
        replaceInfo.put(TAG_PATRONBARCODEVALUE, "");
        replaceInfo.put(TAG_PNAME, "Name");
        replaceInfo.put(TAG_PATRONNAME, GetPatronInfo.getPATRONNAME());
        replaceInfo.put(TAG_PADDRESS, "Address");
        replaceInfo.put(TAG_ADDR1, GetPatronInfo.getADDR1());
        replaceInfo.put(TAG_ADDR2, GetPatronInfo.getADDR2());
        replaceInfo.put(TAG_ADDR3, GetPatronInfo.getADDR3());
        replaceInfo.put("(OFFICERID)", sender.getPatrId());
        replaceInfo.put("(OFFICERNAME)", sender.getName());
        replaceInfo.put(TAG_COMPUTERNAME, Library.getComputerName());
        replaceInfo.put(TAG_THANKYOU, "Thank You");
        replaceInfo.put(TAG_MEMEXPIRYDATE, GetPatronInfo.getMEMEXPIRYDATE());
        replaceInfo.put(TAG_RENEWDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_RENEWTIME, DateTime.getCurrentTime());
        String textBuilder = Document.openFileRenewal("renewal.html", replaceInfo, "renewal", CT03DOCNO, lengthCT03DOCNO);
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileRenewal(String filename, HashMap<String, String> replaceInfo, String action, String CT03DOCNO, int lengthCT03DOCNO) {
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
                try {
                    String test = null;
                    if (action.equals("renewal")) {
                        test = Document.arrayDataRenewal(html.toString(), CT03DOCNO, lengthCT03DOCNO);
                    }
                    lines.add(test);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
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

    public static String arrayDataRenewal(String html, String CT03DOCNO, int lengthCT03DOCNO) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            List<Reservation> getItemInfo = null;
            List<Reservation> getCirculated = null;
            String[] accession = CT03DOCNO.split(",");
            int x = 0;
            while (x < accession.length) {
                System.out.println("Actions23" + accession[x]);
                String[] dischargeDetails = accession[x].split("\\|");
                getItemInfo = Reservation.getItemInfo(dischargeDetails[0], "accession");
                for (Reservation k : getItemInfo) {
                    getCirculated = Reservation.getRenewInfo(dischargeDetails[0]);
                    for (Reservation l : getCirculated) {
                        trial.append(html.toString().replace(TAG_RENEWDATE, DateTime.getSysTodayDate()).replace(TAG_RENEWTIME, DateTime.getCurrentTime()).replace(TAG_ACCNO, dischargeDetails[0]).replace(TAG_TITLE, k.getCT05SRAW()).replace(TAG_DUEDT, "Original Due Date").replace(TAG_DDATE, dischargeDetails[1]).replace(TAG_RENEWDT, "Renew Date").replace(TAG_RDATE, DateTime.getSysTodayDate()).replace(TAG_NEWDUEDT, "New Due Date").replace(TAG_NDATE, l.getCI02DDATE()).replace(TAG_LATE, "Late By").replace(TAG_LATEBY, String.valueOf(dischargeDetails[3]) + " day(s)").replace(TAG_FINESHEADER, "Fines").replace(TAG_FINES, dischargeDetails[4]).replace("{LOOP}", ""));
                    }
                }
                ++x;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }

    public static String Discharging(Library library, Library sender, renewalLetter GetPatronInfo, String CT03DOCNO, int lengthCT03DOCNO) {
        System.out.println("Discharging");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put(TAG_SLIPHEADER, " DISCHARGING SLIP");
        replaceInfo.put("(DATETIME)", "Date");
        replaceInfo.put(TAG_ACCESSIONNOHEADER, "Accession No.");
        replaceInfo.put(TAG_TITLEHEADER, "Title");
        replaceInfo.put("(PATRONID)", GetPatronInfo.getPATRONID());
        replaceInfo.put(TAG_PATRONBARCODEVALUE, "");
        replaceInfo.put(TAG_PNAME, "Name");
        replaceInfo.put(TAG_PATRONNAME, GetPatronInfo.getPATRONNAME());
        replaceInfo.put(TAG_PADDRESS, "Address");
        replaceInfo.put(TAG_ADDR1, GetPatronInfo.getADDR1());
        replaceInfo.put(TAG_ADDR2, GetPatronInfo.getADDR2());
        replaceInfo.put(TAG_ADDR3, GetPatronInfo.getADDR3());
        replaceInfo.put(TAG_COMPUTERNAME, Library.getComputerName());
        replaceInfo.put(TAG_THANKYOU, "Thank You");
        replaceInfo.put(TAG_MEMEXPIRYDATE, GetPatronInfo.getMEMEXPIRYDATE());
        replaceInfo.put(TAG_DISCHARGEDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_DISCHARGETIME, DateTime.getCurrentTime());
        String textBuilder = Document.openFileDischarging("discharge.html", replaceInfo, "discharge", CT03DOCNO, lengthCT03DOCNO);
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileDischarging(String filename, HashMap<String, String> replaceInfo, String action, String CT03DOCNO, int lengthCT03DOCNO) {
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
                try {
                    String test = null;
                    if (action.equals("discharge")) {
                        test = Document.arrayDataDischarging(html.toString(), CT03DOCNO, lengthCT03DOCNO);
                    }
                    lines.add(test);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
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

    public static String arrayDataDischarging(String html, String CT03DOCNO, int lengthCT03DOCNO) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            List<Reservation> getItemInfo = null;
            List<Reservation> getCirculated = null;
            String[] accession = CT03DOCNO.split(",");
            int x = 0;
            while (x < accession.length) {
                String[] dischargeDetails = accession[x].split("\\|");
                getItemInfo = Reservation.getItemInfo(dischargeDetails[0], "accession");
                for (Reservation k : getItemInfo) {
                    getCirculated = Reservation.getDischargeInfo(dischargeDetails[0], dischargeDetails[1], dischargeDetails[3], dischargeDetails[2], dischargeDetails[4]);
                    for (Reservation l : getCirculated) {
                        trial.append(html.toString().replace(TAG_DISCHARGEDATE, DateTime.getSysTodayDate()).replace(TAG_DISCHARGETIME, DateTime.getCurrentTime()).replace(TAG_ACCNO, dischargeDetails[0]).replace(TAG_TITLE, k.getCT05SRAW()).replace(TAG_CHARGEDDT, "Charge Date").replace(TAG_CDATE, l.getCI02CDATE()).replace("(RETURNEDDT)", "Return Date").replace(TAG_RDATE, DateTime.getSysTodayDate()).replace(TAG_DUEDT, "Due Date").replace(TAG_DDATE, l.getCI02DDATE()).replace(TAG_LATE, "Late By").replace(TAG_LATEBY, String.valueOf(dischargeDetails[5]) + " day(s)").replace(TAG_FINESHEADER, "Fines").replace(TAG_FINES, dischargeDetails[6]).replace("{LOOP}", ""));
                    }
                }
                ++x;
            }
            lines.add(trial.toString());
        }
        return trial.toString();
    }

    public static String ER03(Library library, Library letterTitile, reservationletter patronDetail, reservationletter resvDetail, Library hod) {
        System.out.println("ER03");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_PATRONNAME, patronDetail.getPATRONNAME());
        replaceInfo.put(TAG_PATRONADD1, patronDetail.getPATRONADD1());
        replaceInfo.put(TAG_PATRONADD2, patronDetail.getPATRONADD2());
        replaceInfo.put(TAG_PATRONADD3, patronDetail.getPATRONADD3());
        replaceInfo.put("(PATRONID)", patronDetail.getPATRONID());
        replaceInfo.put(TAG_TITLE, resvDetail.getTITLE());
        replaceInfo.put(TAG_CALLNO, resvDetail.getCALLNO());
        replaceInfo.put(TAG_ISBN, resvDetail.getISBN());
        replaceInfo.put("(OFFICERNAME)", hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_POSTCODE, patronDetail.getPOSTCODE());
        replaceInfo.put(TAG_TOWN, patronDetail.getTOWN());
        replaceInfo.put(TAG_COURSE, patronDetail.getCOURSE());
        String textBuilder = Document.openFileER03("ER03.html", replaceInfo, "ER03");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileER03(String filename, HashMap<String, String> replaceInfo, String action) {
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

    public static String ER11(Library library, Library letterTitile, reservationletter patronDetail, reservationletter resvDetail, Library sender, Library hod, String rsvdate) {
        System.out.println("ER03");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put("(LIBRARYNAME)", library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put("(LIBRARYPOSTCODE)", library.getPoscode());
        replaceInfo.put("(LIBRARYTOWN)", library.getTown());
        replaceInfo.put("(BRANCHNAME)", library.getbranch());
        replaceInfo.put("(BRANCHADD1)", library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put("(BRANCHADD3)", library.getbrncadd3());
        replaceInfo.put("(BRANCHPOSTCODE)", library.getbrncpostcode());
        replaceInfo.put("(BRANCHTOWN)", library.getbrnctown());
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_PATRONNAME, patronDetail.getPATRONNAME());
        replaceInfo.put(TAG_PATRONADD1, patronDetail.getPATRONADD1());
        replaceInfo.put(TAG_PATRONADD2, patronDetail.getPATRONADD2());
        replaceInfo.put(TAG_PATRONADD3, patronDetail.getPATRONADD3());
        replaceInfo.put("(PATRONID)", patronDetail.getPATRONID());
        replaceInfo.put(TAG_HOMETEL, patronDetail.getHOMETEL());
        replaceInfo.put(TAG_OFFTEL, patronDetail.getOFFTEL());
        replaceInfo.put(TAG_POSTCODE, patronDetail.getPOSTCODE());
        replaceInfo.put(TAG_TOWN, patronDetail.getTOWN());
        replaceInfo.put(TAG_COURSE, patronDetail.getCOURSE());
        replaceInfo.put(TAG_TITLE, resvDetail.getTITLE());
        replaceInfo.put(TAG_CALLNO, resvDetail.getCALLNO());
        replaceInfo.put(TAG_ISBN, resvDetail.getISBN());
        replaceInfo.put(TAG_ACCNO, resvDetail.getACCNO());
        replaceInfo.put("(RETURNEDDT)", rsvdate);
        replaceInfo.put(TAG_PICKVAR, "Pickup Branch");
        replaceInfo.put(TAG_PICKBRNC, resvDetail.getPICKBRNC());
        replaceInfo.put("(OFFICERNAME)", hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_STAFFNAME, sender.getName());
        replaceInfo.put(TAG_STAFFDESIGNATION, sender.getdesgination());
        replaceInfo.put(TAG_HODNAME, hod.getName());
        replaceInfo.put(TAG_HODDESIGNATION, hod.getdesgination());
        String textBuilder = Document.openFileER11("ER11.html", replaceInfo, "ER11");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileER11(String filename, HashMap<String, String> replaceInfo, String action) {
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
}
