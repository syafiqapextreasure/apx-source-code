/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.acquisition.Ordering.Ordering;
import com.ilmu.acquisition.RecordForPayment.RecordForPayment;
import com.ilmu.acquisition.RequestForPayment.RequestForPayment;
import com.ilmu.apache.fop.FO2PDF.FO2PDF;
import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import com.ilmu.global.Handler;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Document {
    private static final String FILE_EQ26 = "EQ26.fo";
    private static final String FILE_EQ01 = "EQ01.fo";
    private static final String FILE_EQ02 = "EQ02.fo";
    private static final String FILE_EQ03 = "EQ03.fo";
    private static final String FILE_EQ04 = "EQ04.fo";
    private static final String TAG_ORDER = "(ORDER)";
    private static final String TAG_ORDER_DATE = "(ORDER_DATE)";
    private static final String TAG_INV_NO = "(INV_NO)";
    private static final String TAG_INV_AMT = "(INV_AMT)";
    private static final String TAG_CHEQUE_NO = "(CHEQUE_NO)";
    private static final String TAG_CHEQUE_DATE = "(CHEQUE_DATE)";
    private static final String FILE_EQ12 = "EQ12.fo";
    private static final String FILE_EQ32 = "EQ32.fo";
    private static final String TAG_INVOICE = "(INVOICE)";
    private static final String TAG_REF = "(REF)";
    private static final String FILE_EQ13 = "EQ13.fo";
    private static final String TAG_CHEQUENO = "(CHEQUENO)";
    private static final String TAG_CHEQUEDATE = "(CHEQUEDATE)";
    private static final String Tpl = Config.get("TPL");
    private static final String Fo = Config.get("FO");
    private static final String PDF = Config.get("PDF");
    private static final String TAG_LIBRARY_ORG_NAME = "(LIBRARY_ORG_NAME)";
    private static final String TAG_LIBRARY_ADDRESS_1 = "(LIBRARY_ADDRESS_1)";
    private static final String TAG_LIBRARY_ADDRESS_2 = "(LIBRARY_ADDRESS_2)";
    private static final String TAG_LIBRARY_ADDRESS_3 = "(LIBRARY_ADDRESS_3)";
    private static final String TAG_LIBRARY_TEL = "(LIBRARY_TEL)";
    private static final String TAG_LIBRARY_FAX = "(LIBRARY_FAX)";
    private static final String TAG_DATE = "(DATE)";
    private static final String TAG_TIME = "(TIME)";
    private static final String TAG_OFFICER_NAME = "(OFFICER_NAME)";
    private static final String TAG_DESIGNATION = "(OFFICER_DESIGNATION)";
    private static final String count = "(Count)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_EDITION = "(EDITION)";
    private static final String TAG_QUANTITY = "(QUANTITY)";
    private static final String TAG_UNIT_CURRENCY = "(UNIT_CURRENCY)";
    private static final String TAG_UNIT_FOREIGNPRICE = "(UNIT_FOREIGNPRICE)";
    private static final String TAG_UNIT_TOTALFOREIGNPRICE = "(TOTALFOREIGNPRICE)";
    private static final String TAG_TOTALLOCALPRICE = "(TOTALLOCALPRICE)";
    private static final String TAG_EXPDATE = "(EXPDATE)";

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    private static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
        return sdf.format(cal.getTime());
    }

    public static String GrpforpaymentDoc(List<RequestForPayment> ordering, Library library, String ref) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(TAG_REF, ref);
        replaceInfo.put(TAG_INVOICE, Document.AC02_createGrpListTable(ordering));
        replaceInfo.put(TAG_OFFICER_NAME, "Sysadmin");
        replaceInfo.put(TAG_DESIGNATION, "Pensyarah");
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            System.out.println("Tpl" + Tpl);
            in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ32));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
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
        PrintWriter out = null;
        try {
            out = new PrintWriter(String.valueOf(Fo) + FILE_EQ32);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        for (String l : lines) {
            out.println(l);
        }
        out.close();
        FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ32, String.valueOf(PDF) + "Cataloging/Testing.pdf");
        return new String(textBuilder);
    }

    public static String requestforpaymentDoc(List<RequestForPayment> ordering, Library library, String ref) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(TAG_REF, ref);
        replaceInfo.put(TAG_INVOICE, Document.AC02_createItemTable(ordering));
        replaceInfo.put(TAG_OFFICER_NAME, "Sysadmin");
        replaceInfo.put(TAG_DESIGNATION, "Pensyarah");
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            System.out.println("Tpl" + Tpl);
            in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ12));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
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
        PrintWriter out = null;
        try {
            out = new PrintWriter(String.valueOf(Fo) + FILE_EQ12);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        for (String l : lines) {
            out.println(l);
        }
        out.close();
        FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ12, String.valueOf(PDF) + "Cataloging/Testing.pdf");
        return new String(textBuilder);
    }

    public static String recordforpaymentDoc(List<RecordForPayment> ordering, Library library, String voucherno, String voucherdate, String chqdate, String chqno) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(TAG_INVOICE, Document.AC02_createInvTable(ordering, voucherno, voucherdate));
        replaceInfo.put(TAG_CHEQUENO, chqno);
        replaceInfo.put(TAG_CHEQUEDATE, chqdate);
        replaceInfo.put(TAG_OFFICER_NAME, "Sysadmin");
        replaceInfo.put(TAG_DESIGNATION, "Pensyarah");
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            System.out.println("Tpl" + Tpl);
            in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ13));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
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
        PrintWriter out = null;
        try {
            out = new PrintWriter(String.valueOf(Fo) + FILE_EQ13);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        for (String l : lines) {
            out.println(l);
        }
        out.close();
        FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ13, String.valueOf(PDF) + "Cataloging/Testing.pdf");
        return new String(textBuilder);
    }

    private static String AC02_createInvTable(List<RecordForPayment> requestforpayment, String voucherno, String voucherdate) {
        String table = "";
        boolean count = true;
        for (RecordForPayment i : requestforpayment) {
            table = String.valueOf(table) + "<fo:table-row><fo:table-cell><fo:block>";
            table = String.valueOf(table) + i.getAC05INVNO();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + Handler.ifIsNull(voucherno);
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + voucherdate;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block> ";
            table = String.valueOf(table) + Handler.decimalConversion(String.valueOf(i.getAC05AMT()));
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "</fo:table-row>";
        }
        return table;
    }

    private static String AC02_createGrpListTable(List<RequestForPayment> requestforpayment) {
        String table = "";
        boolean count = true;
        for (RequestForPayment i : requestforpayment) {
            table = String.valueOf(table) + "<fo:table-row><fo:table-cell><fo:block>";
            table = String.valueOf(table) + i.getAC05INVNO();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + Handler.decimalConversion(String.valueOf(i.getAC05AMT()));
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "</fo:table-row>";
        }
        return table;
    }

    private static String AC02_createItemTable(List<RequestForPayment> requestforpayment) {
        String table = "";
        int count = 1;
        String invo = null;
        String desc = null;
        String copy = null;
        double total = 0.0;
        for (RequestForPayment i : requestforpayment) {
            if (i.getAC05ORDER().startsWith("DIS") || i.getAC05ORDER().startsWith("HC") || i.getAC05ORDER().startsWith("SC")) {
                invo = "";
                copy = "";
                total = Double.parseDouble(i.getAC05AMT());
            } else {
                invo = i.getAC05INVNO();
                copy = i.getAC05COPY();
                total = Double.parseDouble(Handler.decimalConversion(i.getAC05AMT())) * (double)Integer.parseInt(i.getAC05COPY());
            }
            desc = i.getAC05ORDER().startsWith("DIS") ? "Less " + i.getAC05DISCOUNT() * 100.0 + "%" : (i.getAC05ORDER().startsWith("HC") ? "Handling Charge" : (i.getAC05ORDER().startsWith("SC") ? "Service Charge" : i.getAC05DESC()));
            table = String.valueOf(table) + "<fo:table-row><fo:table-cell><fo:block>";
            table = String.valueOf(table) + count++;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + invo;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + desc;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + copy;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + i.getAC05FOREX() + " " + Double.parseDouble(i.getAC05FPRICE());
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block>";
            table = String.valueOf(table) + "RM " + total;
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "</fo:table-row>";
        }
        return table;
    }

    public static String reqNotification(List<Ordering> ordering, Library library, String ref, String action) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(TAG_REF, ref);
        replaceInfo.put(TAG_ORDER, Document.Ordering_createOrderingTable(ordering));
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            System.out.println("Tpl" + Tpl);
            in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ26));
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
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
        PrintWriter out = null;
        try {
            out = new PrintWriter(String.valueOf(Fo) + FILE_EQ26);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        for (String l : lines) {
            out.println(l);
        }
        out.close();
        FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ26, String.valueOf(PDF) + "Cataloging/Testing2.pdf");
        return new String(textBuilder);
    }

    public static String orderingDoc(List<Ordering> ordering, Library library, String ref, String action) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(TAG_REF, ref);
        for (Ordering i : ordering) {
            if (!action.equals("44")) continue;
            replaceInfo.put(TAG_ORDER_DATE, DateTime.DBToDisplayFormat(i.geOrderdate()));
            replaceInfo.put(TAG_INV_NO, i.getInvoice());
            replaceInfo.put(TAG_INV_AMT, i.getLocalTotal());
            replaceInfo.put(TAG_CHEQUE_NO, i.getBdraf());
            replaceInfo.put(TAG_CHEQUE_DATE, DateTime.DBToDisplayFormat(i.getBDate()));
        }
        replaceInfo.put(TAG_ORDER, Document.Ordering_createReqTable(ordering));
        replaceInfo.put(TAG_OFFICER_NAME, "Sysadmin");
        replaceInfo.put(TAG_DESIGNATION, "Pensyarah");
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            if (action.equals("40")) {
                in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ01));
            } else if (action.equals("60")) {
                in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ02));
            } else if (action.equals("20")) {
                in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ03));
            } else if (action.equals("44")) {
                in = new BufferedReader(new FileReader(String.valueOf(Tpl) + FILE_EQ04));
            }
            line = in.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] tags = (String[])Arrays.copyOf(replaceInfo.keySet().toArray(), replaceInfo.size(), String[].class);
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
        PrintWriter out = null;
        try {
            if (action.equals("40")) {
                out = new PrintWriter(String.valueOf(Fo) + FILE_EQ01);
            } else if (action.equals("60")) {
                out = new PrintWriter(String.valueOf(Fo) + FILE_EQ02);
            } else if (action.equals("20")) {
                out = new PrintWriter(String.valueOf(Fo) + FILE_EQ03);
            } else if (action.equals("44")) {
                out = new PrintWriter(String.valueOf(Fo) + FILE_EQ04);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (String l : lines) {
            textBuilder.append(l);
        }
        for (String l : lines) {
            out.println(l);
        }
        out.close();
        if (action.equals("40")) {
            FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ01, String.valueOf(PDF) + "Cataloging/Testing1.pdf");
        } else if (action.equals("60")) {
            FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ02, String.valueOf(PDF) + "Cataloging/Testing1.pdf");
        } else if (action.equals("20")) {
            FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ03, String.valueOf(PDF) + "Cataloging/Testing1.pdf");
        } else if (action.equals("44")) {
            FO2PDF.printPDF(String.valueOf(Tpl) + FILE_EQ04, String.valueOf(PDF) + "Cataloging/Testing1.pdf");
        }
        return new String(textBuilder);
    }

    private static String Ordering_createOrderingTable(List<Ordering> requestforpayment) {
        String table = "";
        boolean count = true;
        double total = 0.0;
        for (Ordering i : requestforpayment) {
            table = String.valueOf(table) + "<fo:table-row><fo:table-cell><fo:block font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03ORDER();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03CTRLNO();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getTITLE();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = i.getISBN().trim() == null || i.getISBN().trim().equals("null") ? String.valueOf(table) + " " : String.valueOf(table) + i.getISBN();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + Handler.ifIsNull(i.getEdition());
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + Handler.ifIsNull(i.getPublication());
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getQuantity();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03FOREX() + " " + i.getAC03FPRICE();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + "RM " + i.getLocalTotal();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "</fo:table-row>";
            total += Double.parseDouble(i.getLocalTotal());
        }
        table = String.valueOf(table) + "<fo:table-row><fo:table-cell number-columns-spanned='8' text-align='right'><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + "Total : ";
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "<fo:table-cell><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + " RM " + Handler.decimalConversion(String.valueOf(total));
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "</fo:table-row>";
        return table;
    }

    private static String Ordering_createReqTable(List<Ordering> requestforpayment) {
        String table = "";
        boolean count = true;
        double total = 0.0;
        int qty = 0;
        double ftotal = 0.0;
        for (Ordering i : requestforpayment) {
            table = String.valueOf(table) + "<fo:table-row><fo:table-cell><fo:block font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03ORDER();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = i.getISBN().trim() == null || i.getISBN().trim().equals("null") ? String.valueOf(table) + " " : String.valueOf(table) + i.getISBN();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getTITLE();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + Handler.ifIsNull(i.getEdition());
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getQuantity();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03FOREX() + " " + i.getAC03FPRICE();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03FOREX() + " " + i.getTotal();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + "RM " + i.getLocalTotal();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + i.getAC03CRDATE();
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "<fo:table-cell><fo:block  font-size='8.5pt'>";
            table = String.valueOf(table) + Handler.ifIsNull(i.getPublication());
            table = String.valueOf(table) + "</fo:block></fo:table-cell>";
            table = String.valueOf(table) + "</fo:table-row>";
            total += Double.parseDouble(i.getLocalTotal());
            ftotal += Double.parseDouble(i.getAC03FPRICE());
            if (i.getQuantity().contains("set")) {
                String newqty = i.getQuantity().replace("set", "");
                qty += Integer.parseInt(newqty);
                continue;
            }
            qty += Integer.parseInt(i.getQuantity());
        }
        table = String.valueOf(table) + "<fo:table-row><fo:table-cell number-columns-spanned='4' text-align='right'><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + "Total : ";
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "<fo:table-cell><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + qty;
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "<fo:table-cell><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + Handler.decimalConversion(String.valueOf(ftotal));
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "<fo:table-cell><fo:block font-size='10pt' font-weight='bold'>";
        table = String.valueOf(table) + " RM " + Handler.decimalConversion(String.valueOf(total));
        table = String.valueOf(table) + "</fo:block></fo:table-cell>";
        table = String.valueOf(table) + "</fo:table-row>";
        return table;
    }
}
