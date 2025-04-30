/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.binding.pdf;

import com.ilmu.binding.pdf.Library;
import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Document {
    private int counts;
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
    private static final String TAG_VENDORNAME = "(VENDORNAME)";
    private static final String TAG_VENDORADDRESS1 = "(VENDORADDRESS1)";
    private static final String TAG_VENDORADDRESS2 = "(VENDORADDRESS2)";
    private static final String TAG_VENDORADDRESS3 = "(VENDORADDRESS3)";
    private static final String TAG_VENDORPOSTCODE = "(VENDORPOSTCODE)";
    private static final String TAG_VENDORTELEPHONE = "(VENDORTELEPHONE)";
    private static final String TAG_VENDORCONTACT = "(VENDORCONTACT)";
    private static final String TAG_LETTERDATE = "(LETTERDATE)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_DTSENT = "(DTSENT)";
    private static final String TAG_DTEXPEC = "(DTEXPEC)";
    private static final String Tpl_GetTpl = Config.get("template");

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String BindingClaim(Library library, Library vendor, Library loginid, List<Library> bindClaim) {
        System.out.println("BINDING CLAIM LETTER");
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
        replaceInfo.put(TAG_VENDORNAME, vendor.getName());
        replaceInfo.put(TAG_VENDORADDRESS1, vendor.getAddress1());
        replaceInfo.put(TAG_VENDORADDRESS2, vendor.getAddress2());
        replaceInfo.put(TAG_VENDORADDRESS3, vendor.getAddress3());
        replaceInfo.put(TAG_VENDORPOSTCODE, vendor.getPoscode());
        replaceInfo.put(TAG_VENDORTELEPHONE, vendor.getTel());
        replaceInfo.put(TAG_VENDORCONTACT, vendor.getPERINC());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_OFFICERNAME, loginid.getName());
        String textBuilder = Document.openFile("BI01.html", bindClaim, replaceInfo, "BI01");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFile(String filename, List<Library> bindClaim, HashMap<String, String> replaceInfo, String action) {
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
                    String test = Document.arrayData(html.toString(), bindClaim);
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

    public static String arrayData(String html, List<Library> bindClaim) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            int i = 0;
            while (i < bindClaim.size()) {
                trial.append(html.toString().replace(TAG_TITLE, bindClaim.get(i).gettitle()).replace(TAG_DTSENT, bindClaim.get(i).getdtsent()).replace(TAG_DTEXPEC, bindClaim.get(i).getdtexpec()).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }
}
