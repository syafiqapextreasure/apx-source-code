/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.apache.fop.FO2PDF.FO2PDF;
import com.ilmu.cataloging.PDF.Library;
import com.ilmu.cataloging.Release_Circulation.CatalogingDOC;
import com.ilmu.cataloging.template.Cataloging;
import com.ilmu.global.Config;
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
    private static final String FILE_RELEASE_FOR_CI = "ReleaseForCI.fo";
    private static final String Release_For_CI_Tpl = Config.get("Release_For_CI_Tpl");
    private static final String Fo_Release_For_CI = Config.get("FO_Release_For_CI");
    private static final String PDF_Release_For_CI = Config.get("PDF_Release_For_CI");
    private static int count = 0;

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

    public static String createReleaseForCIDoc(List<Cataloging> cataloging, Library library, CatalogingDOC catDOC, CatalogingDOC ISBN, Library receiver, Library sender, String accMatNo, String accTitl, String accCallNo, String matno) {
        ++count;
        String text = "";
        StringBuilder textBuilder = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        int collDays = Integer.parseInt(library.getCollDays());
        cal.set(5, cal.get(5) + collDays);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
        replaceInfo.put(TAG_PATR_NAME, receiver.getPatrName());
        replaceInfo.put(TAG_PATR_COURSE, receiver.getPatrCourse());
        replaceInfo.put(TAG_PATR_ADD1, receiver.getPatrAdd1());
        replaceInfo.put(TAG_PATR_ADD2, receiver.getPatrAdd2());
        replaceInfo.put(TAG_PATR_ADD3, receiver.getPatrAdd3());
        replaceInfo.put(TAG_PATR_POSTCODE, receiver.getPatrCode());
        replaceInfo.put(TAG_PATR_TOWN, receiver.getPatrTown());
        replaceInfo.put(TAG_PATR_HOMETEL, receiver.getPatrHtel());
        replaceInfo.put("(OFFTEL)", receiver.getPatrOtel());
        replaceInfo.put(TAG_COLL_DAYS, library.getCollDays());
        System.out.println("sss" + ISBN.getISBN());
        replaceInfo.put(TAG_ISBN, Handler.ifIsNull(ISBN.getISBN()));
        replaceInfo.put(TAG_LOCA, Handler.ifIsNull(catDOC.getLocation()));
        replaceInfo.put(TAG_BRNC, Handler.ifIsNull(catDOC.getBranch()));
        replaceInfo.put(TAG_OFFICER_NAME, sender.getPatrName());
        replaceInfo.put(TAG_OFFICER_DESIGN, sender.getPatrCourse());
        replaceInfo.put(TAG_DUE_DATE, sdf.format(cal.getTime()));
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;
        String line = null;
        try {
            in = new BufferedReader(new FileReader(String.valueOf(Release_For_CI_Tpl) + FILE_RELEASE_FOR_CI));
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
            out = new PrintWriter(String.valueOf(Fo_Release_For_CI) + FILE_RELEASE_FOR_CI);
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
        FO2PDF.printPDF(String.valueOf(Fo_Release_For_CI) + FILE_RELEASE_FOR_CI, String.valueOf(PDF_Release_For_CI) + "Cataloging/" + receiver.getPatrId() + ".pdf");
        System.out.println(count);
        if (count == 2) {
            Library.updateCIRESV(accMatNo, matno);
        }
        return new String(textBuilder);
    }
}
