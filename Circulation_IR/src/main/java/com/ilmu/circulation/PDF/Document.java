/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.PDF;

import com.ilmu.circulation.PDF.Library;
import com.ilmu.circulation.PDF.recallLetter;
import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
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
    private static final String TAG_PATRONNAME = "(PATRONNAME)";
    private static final String TAG_PATRONADD1 = "(PATRONADD1)";
    private static final String TAG_PATRONADD2 = "(PATRONADD2)";
    private static final String TAG_PATRONADD3 = "(PATRONADD3)";
    private static final String TAG_PATRONID = "(PATRONID)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_CALLNO = "(CALLNO)";
    private static final String TAG_ISBN = "(ISBN)";
    private static final String TAG_ACCNO = "(ACCNO)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_DESIGNATION = "(DESIGNATION)";
    private static final String TAG_POSTCODE = "(POSTCODE)";
    private static final String TAG_TOWN = "(TOWN)";
    private static final String TAG_COURSE = "(COURSE)";
    private static final String Tpl_GetTpl = Config.get("template");

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String ER02(Library library, Library letterTitile, recallLetter patronDetail, recallLetter cirDetail, Library sender) {
        System.out.println("ER02");
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
        replaceInfo.put(TAG_BRANCHNAME, library.getbranch());
        replaceInfo.put(TAG_BRANCHADD1, library.getbrncadd1());
        replaceInfo.put(TAG_BRANCHADD2, library.getbrncadd2());
        replaceInfo.put(TAG_BRANCHADD3, library.getbrncadd3());
        replaceInfo.put(TAG_BRANCHPOSTCODE, library.getbrncpostcode());
        replaceInfo.put(TAG_BRANCHTOWN, library.getbrnctown());
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_PATRONNAME, patronDetail.getPATRONNAME());
        replaceInfo.put(TAG_PATRONADD1, patronDetail.getPATRONADD1());
        replaceInfo.put(TAG_PATRONADD2, patronDetail.getPATRONADD2());
        replaceInfo.put(TAG_PATRONADD3, patronDetail.getPATRONADD3());
        replaceInfo.put(TAG_PATRONID, patronDetail.getPATRONID());
        replaceInfo.put(TAG_POSTCODE, patronDetail.getPOSTCODE());
        replaceInfo.put(TAG_TOWN, patronDetail.getTOWN());
        replaceInfo.put(TAG_COURSE, patronDetail.getCOURSE());
        replaceInfo.put(TAG_TITLE, cirDetail.getTITLE());
        replaceInfo.put(TAG_CALLNO, cirDetail.getCALLNO());
        replaceInfo.put(TAG_ISBN, cirDetail.getISBN());
        replaceInfo.put(TAG_ACCNO, cirDetail.getACCNO());
        replaceInfo.put(TAG_OFFICERNAME, sender.getName());
        replaceInfo.put(TAG_DESIGNATION, sender.getdesgination());
        String textBuilder = Document.openFileER02("ER02.html", replaceInfo, "ER02");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileER02(String filename, HashMap<String, String> replaceInfo, String action) {
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
