/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.receipting.pdf;

import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import com.ilmu.global.ISBD;
import com.ilmu.receipting.Receipting.PatronService;
import com.ilmu.receipting.Receipting.ReceiptingService;
import com.ilmu.receipting.pdf.Library;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Document {
    private static final String TAG_LIBRARYNAME = "(LIBRARYNAME)";
    private static final String TAG_LIBRARYADD1 = "(LIBRARYADD1)";
    private static final String TAG_LIBRARYADD2 = "(LIBRARYADD2)";
    private static final String TAG_LIBRARYADD3 = "(LIBRARYADD3)";
    private static final String TAG_LIBRARYPOSTCODE = "(LIBRARYPOSTCODE)";
    private static final String TAG_LIBRARYTOWN = "(LIBRARYTOWN)";
    private static final String TAG_LIBRARYPHONE = "(LIBRARYPHONE)";
    private static final String TAG_LIBRARYGSTID = "(LIBRARYGSTID)";
    private static final String TAG_BRANCHNAME = "(BRANCHNAME)";
    private static final String TAG_BRANCHADD1 = "(BRANCHADD1)";
    private static final String TAG_BRANCHADD2 = "(BRANCHADD2)";
    private static final String TAG_BRANCHADD3 = "(BRANCHADD3)";
    private static final String TAG_BRANCHPOSTCODE = "(BRANCHPOSTCODE)";
    private static final String TAG_BRANCHTOWN = "(BRANCHTOWN)";
    private static final String TAG_LETTERSUBJECT = "(LETTERSUBJECT)";
    private static final String TAG_RECEIPTDATE = "(RECEIPTDATE)";
    private static final String TAG_RECEIPTTIME = "(RECEIPTTIME)";
    private static final String TAG_PATRONID = "(PATRONID)";
    private static final String TAG_PATRONNAME = "(PATRONNAME)";
    private static final String TAG_MEMEXPIRYDATE = "(MEMEXPIRYDATE)";
    private static final String TAG_RECEIPTNO = "(RECEIPTNO)";
    private static final String TAG_TOTAL = "(TOTAL)";
    private static final String TAG_RECEIVED = "(RECEIVED)";
    private static final String TAG_CHANGE = "(CHANGE)";
    private static final String TAG_PAYMENTMODE = "(PAYMENTMODE)";
    private static final String TAG_OFFICERID = "(OFFICERID)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_OFFICERLOCA = "(OFFICERLOCA)";
    private static final String TAG_OFFICERDESIGNATION = "(OFFICERDESIGNATION)";
    private static final String TAG_COMPUTERNAME = "(COMPUTERNAME)";
    private static final String TAG_STAFFNAME = "(STAFFNAME)";
    private static final String TAG_STAFFDESIGNATION = "(STAFFDESIGNATION)";
    private static final String TAG_STAFFLOCA = "(STAFFLOCA)";
    private static final String TAG_HODNAME = "(HODNAME)";
    private static final String TAG_HODDESIGNATION = "(HODDESIGNATION)";
    private static final String TAG_HODLOCA = "(HODLOCA)";
    private static final String TAG_TRXCODE = "(TRXCODE)";
    private static final String TAG_TRXNO = "(TRXNO)";
    private static final String TAG_REFERENCE = "(REFERENCE)";
    private static final String TAG_AMOUNT = "(AMOUNT)";
    private static final String TAG_ACCESSION = "(ACCESSION)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_GSTTYPE = "(GSTTYPE)";
    private static final String Tpl_GetTpl = Config.get("template");

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String RECEIPT(Library library, Library letterTitile, Library officer, String patronid, String receiptno, String total, String received, String change, String paymentMode, List<ReceiptingService> receipt2) throws Exception {
        System.out.println("RECEIPT");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LIBRARYNAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARYPOSTCODE, library.getPoscode());
        replaceInfo.put(TAG_LIBRARYTOWN, library.getTown());
        replaceInfo.put(TAG_LIBRARYPHONE, library.getTel());
        replaceInfo.put(TAG_BRANCHNAME, library.getbranch());
        replaceInfo.put(TAG_BRANCHADD1, library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getbrncadd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getbrncpostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getbrnctown());
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_RECEIPTDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_RECEIPTTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_PATRONID, patronid);
        replaceInfo.put(TAG_PATRONNAME, PatronService.getPatronName(patronid));
        replaceInfo.put(TAG_MEMEXPIRYDATE, PatronService.getPatronExpDate(patronid));
        replaceInfo.put(TAG_RECEIPTNO, receiptno);
        replaceInfo.put(TAG_TOTAL, total);
        replaceInfo.put(TAG_RECEIVED, received);
        replaceInfo.put(TAG_CHANGE, change);
        replaceInfo.put(TAG_PAYMENTMODE, paymentMode);
        replaceInfo.put(TAG_OFFICERID, officer.getpatrId());
        replaceInfo.put(TAG_OFFICERNAME, officer.getName());
        replaceInfo.put(TAG_OFFICERLOCA, String.valueOf(officer.getlocation()) + "-" + officer.getlocDesc());
        replaceInfo.put(TAG_OFFICERDESIGNATION, officer.getdesgination());
        replaceInfo.put(TAG_COMPUTERNAME, Library.getComputerName());
        String textBuilder = Document.openFile("receipt.html", receipt2, replaceInfo, "receipt");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFile(String filename, List<ReceiptingService> receipt2, HashMap<String, String> replaceInfo, String action) {
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
                    System.out.println("ACtion" + action);
                    if (action.equals("receipt")) {
                        test = Document.arrayData(html.toString(), receipt2);
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

    public static String arrayData(String html, List<ReceiptingService> receipt2) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("html: " + html);
        if (html != null) {
            System.out.println("html " + html);
            List<ISBD> isbd = ISBD.getPunctuation("245");
            double total = 0.0;
            for (ReceiptingService i : receipt2) {
                total += i.getReceiptAmount();
                String title = i.getCT05SRAW();
                String acc = i.getRE01DOCNO();
                String ref = i.getRE01REFER();
                if (acc.equals("") || acc.equals("null") || acc == null) {
                    acc = "";
                }
                trial.append(html.toString().replace(TAG_TRXCODE, i.getRE01CODE()).replace(TAG_REFERENCE, ref).replace(TAG_AMOUNT, String.format("%.2f", i.getReceiptAmount())).replace(TAG_ACCESSION, acc).replace(TAG_TITLE, Handler.getSubfield(title, isbd)).replace("{LOOP}", ""));
            }
            lines.add(trial.toString());
        }
        return trial.toString();
    }
}
