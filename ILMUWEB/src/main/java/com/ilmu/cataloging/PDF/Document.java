/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.cataloging.PDF.Library;
import com.ilmu.cataloging.Release_Circulation.CatalogingDOC;
import com.ilmu.cataloging.template.Cataloging;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Document {
    private static final String TAG_LIBRARY_ORG_NAME = "(LIBRARY_ORG_NAME)";
    private static final String TAG_LIBRARY_ADDRESS_1 = "(LIBRARY_ADDRESS_1)";
    private static final String TAG_LIBRARY_ADDRESS_2 = "(LIBRARY_ADDRESS_2)";
    private static final String TAG_LIBRARY_ADDRESS_3 = "(LIBRARY_ADDRESS_3)";
    private static final String TAG_LIBRARY_TEL = "(LIBRARY_TEL)";
    private static final String TAG_LIBRARY_FAX = "(LIBRARY_FAX)";
    private static final String TAG_DATE = "(DATE)";
    private static final String TAG_TIME = "(TIME)";
    private static final String ACC_NO = "(ACCNO)";
    private static final String TITLE = "(TITLE)";
    private static final String CALL_NO = "(CALLNO)";
    private static final String TAG_PATR_NAME = "(PATRONNAME)";
    private static final String TAG_PATR_COURSE = "(COURSE)";
    private static final String TAG_PATR_ADD1 = "(PATRONADD1)";
    private static final String TAG_PATR_ADD2 = "(PATRONADD2)";
    private static final String TAG_PATR_ADD3 = "(PATRONADD3)";
    private static final String TAG_PATR_POSTCODE = "(POSTCODE)";
    private static final String TAG_PATR_TOWN = "(TOWN)";
    private static final String TAG_PATR_HOMETEL = "(HOMETEL)";
    private static final String TAG_DUE_DATE = "(DUEDATE)";
    private static final String TAG_PATR_OFFTEL = "(OFFTEL)";
    private static final String TAG_ISBN = "(ISBN)";
    private static final String TAG_LOCA = "(PICKVAR)";
    private static final String TAG_BRNC = "(PICKBRNC)";
    private static final String TAG_OFF_TEL = "(OFFTEL)";
    private static final String TAG_COLL_DAYS = "(COLLECTIONDAYS)";
    private static final String TAG_OFFICER_NAME = "(OFFICER_NAME)";
    private static final String TAG_OFFICER_DESIGN = "(OFFICER_DESIGNATION)";
    private static final String TAG_REFERENCE_NO = "(PAYMENT_REFERENCE_NO)";
    private static final String TAG_VENDOR_NAME = "(VENDOR_NAME)";
    private static final String TAG_VENDOR_ADDRESS_1 = "(VENDOR_ADDRESS_1)";
    private static final String TAG_VENDOR_ADDRESS_2 = "(VENDOR_ADDRESS_2)";
    private static final String TAG_VENDOR_ADDRESS_3 = "(VENDOR_ADDRESS_3)";
    private static final String TAG_VENDOR_PERSON_IN_CHARGE = "(VENDOR_PERSON_IN_CHARGE)";
    private static final String TAG_ACCOUNT_NO = "(ACCOUNT_NO)";
    private static final String TAG_BANK_NAME = "(BANK_NAME)";
    private static final String TAG_ISSUES_LIST = "(ISSUES_LIST)";
    private static final String TAG_INVOICE = "(INVOICE)";
    private static final String TAG_PERSON_NAME = "(PERSON_NAME)";
    private static final String TAG_BANKDRAFT_NO = "(BANKDRAFT_NO)";
    private static final String TAG_BANKDRAFT_DATE = "(BANKDRAFT_DATE)";
    private static final String TAG_VOUCHER_NO = "(VOUCHER_NO)";
    private static final String TAG_VOUCHER_DATE = "(VOUCHER_DATE)";
    private static final String FILE_SE09_CLAIMS_DOCUMENT = "SE09_IssuesLetter.fo";
    private static final String FILE_SE07_PAYMENT_DOCUMENT = "SE07_payment_document.fo";
    private static final String FILE_RELEASE_FOR_CI = "ReleaseForCI.fo";
    private static final String FILE_SE08_INVOICE_PAYMENT = "SE08_Invoice_Payment.fo";
    private static final String PATH = "C:\\Users\\Suitha\\Documents\\workspace\\ILMU\\WebContent\\WEB-INF\\doc\\template\\";

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

    public static String createReleaseForCIDoc(List<Cataloging> cataloging, Library library, CatalogingDOC catDOC, Library patrDetail, String accMatNo, String accTitl, String accCallNo) {
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        System.out.println("Doc1");
        Calendar cal = Calendar.getInstance();
        int collDays = Integer.parseInt(library.getCollDays());
        cal.set(5, cal.get(5) + collDays);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Doc2");
        replaceInfo.put(TAG_LIBRARY_ORG_NAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARY_ADDRESS_3, library.getAddress3());
        replaceInfo.put(TAG_LIBRARY_TEL, "Tel: " + library.getTel());
        replaceInfo.put(TAG_LIBRARY_FAX, "Fax: " + library.getFax());
        replaceInfo.put(TAG_DATE, Document.getCurrentDate());
        replaceInfo.put(TAG_TIME, Document.getCurrentTime());
        replaceInfo.put(ACC_NO, accMatNo);
        replaceInfo.put(TITLE, accTitl);
        replaceInfo.put(CALL_NO, accCallNo);
        replaceInfo.put(TAG_PATR_NAME, patrDetail.getPatrName());
        replaceInfo.put(TAG_PATR_COURSE, patrDetail.getPatrCourse());
        replaceInfo.put(TAG_PATR_ADD1, patrDetail.getPatrAdd1());
        replaceInfo.put(TAG_PATR_ADD2, patrDetail.getPatrAdd2());
        replaceInfo.put(TAG_PATR_ADD3, patrDetail.getPatrAdd3());
        replaceInfo.put(TAG_PATR_POSTCODE, patrDetail.getPatrCode());
        replaceInfo.put(TAG_PATR_TOWN, patrDetail.getPatrTown());
        replaceInfo.put(TAG_PATR_HOMETEL, patrDetail.getPatrHtel());
        replaceInfo.put("(OFFTEL)", patrDetail.getPatrOtel());
        replaceInfo.put(TAG_COLL_DAYS, library.getCollDays());
        replaceInfo.put(TAG_ISBN, catDOC.getISBN());
        replaceInfo.put(TAG_LOCA, catDOC.getLocation());
        replaceInfo.put(TAG_BRNC, catDOC.getBranch());
        replaceInfo.put(TAG_OFFICER_NAME, library.getOfficerName());
        replaceInfo.put(TAG_OFFICER_DESIGN, library.getOfficerDesign());
        replaceInfo.put(TAG_DUE_DATE, sdf.format(cal.getTime()));
        System.out.println("Doc3");
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        System.out.println("Doc4");
        String line = null;
        System.out.println("Doc5");
        try {
            System.out.println("C:\\Users\\Suitha\\Documents\\workspace\\ILMU\\WebContent\\WEB-INF\\doc\\template\\ReleaseForCI.fo");
            in = new BufferedReader(new FileReader("C:\\Users\\Suitha\\Documents\\workspace\\ILMU\\WebContent\\WEB-INF\\doc\\template\\ReleaseForCI.fo"));
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
                    System.out.println(String.valueOf(tag) + "  ->  " + (String)replaceInfo.get(tag));
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
        System.out.println(lines.size());
        for (String l : lines) {
            textBuilder.append(l);
        }
        return new String(textBuilder);
    }
}
