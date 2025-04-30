/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.cataloging.PDF;

import com.ilmu.cataloging.PDF.AssignOrderItems;
import com.ilmu.cataloging.PDF.Library;
import com.ilmu.global.Config;
import com.ilmu.global.DateTime;
import com.ilmu.serial.RecordForPayment.RecordForPayment;
import com.ilmu.serial.RequestForPayment.RequestForPayment;
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
    private static final String TAG_LETTERSUBJECT = "(LETTERSUBJECT)";
    private static final String TAG_LETTERDATE = "(LETTERDATE)";
    private static final String TAG_LETTERTIME = "(LETTERTIME)";
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
    private static final String TAG_VENDORID = "(VENDORID)";
    private static final String TAG_VENDORNAME = "(VENDORNAME)";
    private static final String TAG_VENDORADDRESS1 = "(VENDORADDRESS1)";
    private static final String TAG_VENDORADDRESS2 = "(VENDORADDRESS2)";
    private static final String TAG_VENDORADDRESS3 = "(VENDORADDRESS3)";
    private static final String TAG_VENDORPOSTCODE = "(VENDORPOSTCODE)";
    private static final String TAG_VENDORTELEPHONE = "(VENDORTELEPHONE)";
    private static final String TAG_VENDORCONTACT = "(VENDORCONTACT)";
    private static final String TAG_VENDORACCOUNTNO = "(VENDORACCOUNTNO)";
    private static final String TAG_VENDORBANK = "(VENDORBANK)";
    private static final String TAG_REFERENCENO = "(REFERENCENO)";
    private static final String TAG_ORDDATE = "(ORDDATE)";
    private static final String TAG_ORDERTOTAL = "(ORDERTOTAL)";
    private static final String TAG_ORDERFOREIGN = "(ORDERFOREIGN)";
    private static final String TAG_TOTALQUANTITY = "(TOTALQUANTITY)";
    private static final String TAG_ORDERTOTALINWORD = "(ORDERTOTALINWORD)";
    private static final String TAG_FOREIGNTOTAL = "(FOREIGNTOTAL)";
    private static final String TAG_TOTALAMOUNT = "(TOTALAMOUNT)";
    private static final String TAG_OFFICERNAME = "(OFFICERNAME)";
    private static final String TAG_DESIGNATION = "(DESIGNATION)";
    private static final String TAG_STAFFNAME = "(STAFFNAME)";
    private static final String TAG_STAFFDESIGNATION = "(STAFFDESIGNATION)";
    private static final String TAG_HODNAME = "(HODNAME)";
    private static final String TAG_HODDESIGNATION = "(HODDESIGNATION)";
    private static final String TAG_BATCHREQUESTORID = "(BATCHREQUESTORID)";
    private static final String TAG_LOGINOFFICER = "(LOGINOFFICER)";
    private static final String TAG_EXPDATE = "(EXPDATE)";
    private static final String TAG_INVOICENO = "(INVOICENO)";
    private static final String TAG_INVOICEAMOUNT = "(INVOICEAMOUNT)";
    private static final String TAG_ORDERDATE = "(ORDERDATE)";
    private static final String TAG_CHEQUENO = "(CHEQUENO)";
    private static final String TAG_CHEQUEDATE = "(CHEQUEDATE)";
    private static final String TAG_ORDERNO = "(ORDERNO)";
    private static final String TAG_TITLE = "(TITLE)";
    private static final String TAG_ISSN = "(ISSN)";
    private static final String TAG_DTSTART = "(DTSTART)";
    private static final String TAG_DTSTOP = "(DTSTOP)";
    private static final String TAG_AMOUNT = "(AMOUNT)";
    private static final String TAG_FOREIGN = "(FOREIGN)";
    private static final String TAG_FOREX = "(FOREX)";
    private static final String TAG_VOUCHERNO = "(VOUCHERNO)";
    private static final String TAG_VOUCHERDATE = "(VOUCHERDATE)";
    private static final String TAG_REASON = "(REASON)";
    private static final String TAG_LOGINOFFICERDESIGNATION = "(LOGINOFFICERDESIGNATION)";
    private static final String TAG_PAYREFERNO = "(PAYREFERNO)";
    private static final String TAG_CURRENCY = "(CURRENCY)";
    private static final String TAG_FOREXRATE = "(FOREXRATE)";
    private static final String TAG_POREFERNO = "(POREFERNO)";
    private static final String TAG_BANKDRAFT = "(BANKDRAFT)";
    private static final String TAG_DRAFTDATE = "(DRAFTDATE)";
    private static final String TAG_REVIEWLISTNO = "(REVIEWLISTNO)";
    private static final String TAG_CURRENCYLBL = "(CURRENCYLBL)";
    private static final String TAG_TOTALFOREIGNAMOUNT = "(TOTALFOREIGNAMOUNT)";
    private static final String TAG_RUJUKAN = "(RUJUKAN)";
    private static final String TAG_TARIKH = "(TARIKH)";
    private static final String TAG_NAMAPEMOHON = "(NAMAPEMOHON)";
    private static final String TAG_JABATANSEKOLAH = "(JABATANSEKOLAH)";
    private static final String TAG_JUDUL = "(JUDUL)";
    private static final String TAG_PENERBIT = "(PENERBIT)";
    private static final String TAG_KOS = "(KOS)";
    private static final String TAG_STATUS = "(STATUS)";
    private static final String TAG_KETUAPERPUSTAKAAN = "(KETUAPERPUSTAKAAN)";
    private static final String TAG_JABATAN = "(JABATAN)";
    private static final String TAG_SAMBUNGAN = "(SAMBUNGAN)";
    private static final String TAG_EMAIL = "(EMAIL)";
    private static final String TAG_TAJUKNOMBORTEMPAH = "(TAJUKNOMBORTEMPAH)";
    private static final String TAG_STATUSPEMOHONAN1 = "(STATUSPEMOHONAN1)";
    private static final String TAG_TARIKHPEMOHONAN1 = "(TARIKHPEMOHONAN1)";
    private static final String TAG_STATUSPEMOHONAN2 = "(STATUSPEMOHONAN2)";
    private static final String TAG_TARIKHPEMOHONAN2 = "(TARIKHPEMOHONAN2)";
    private static final String TAG_STATUSPEMOHONAN3 = "(STATUSPEMOHONAN3)";
    private static final String TAG_TARIKHPEMOHONAN3 = "(TARIKHPEMOHONAN3)";
    private static final String TAG_NOMBORTEMPAH = "(NOMBORTEMPAH)";
    private static final String TAG_STATUSPEMOHONAN4 = "(STATUSPEMOHONAN4)";
    private static final String TAG_TARIKHPEMOHONAN4 = "(TARIKHPEMOHONAN4)";
    private static final String TAG_STATUSPEMOHONAN = "(STATUSPEMOHONAN)";
    private static final String TAG_TARIKHPEMOHONAN = "(TARIKHPEMOHONAN)";
    private static final String TAG_VOTECENTER = "(VOTECENTER)";
    private static final String TAG_CURRENCYCODE = "(CURRENCYCODE)";
    private static final String Tpl_GetTpl = Config.get("template");
    private static final String TAG_QUANTITY = "(QUANTITY)";
    private static final String TAG_UNITFOREIGNPRICE = "(UNITFOREIGNPRICE)";
    private static final String TAG_TOTALFOREIGNPRICE = "(TOTALFOREIGNPRICE)";
    private static final String TAG_UNITLOCALPRICE = "(UNITLOCALPRICE)";
    private static final String TAG_TOTALLOCALPRICE = "(TOTALLOCALPRICE)";
    private static final String TAG_UNITCURRENCY = "(UNITCURRENCY)";
    private static final String TAG_EXCHANGERATE = "(EXCHANGERATE)";
    private static final String TAG_VOLFROM = "(VOLFROM)";
    private static final String TAG_ISSFROM = "(ISSFROM)";
    private static final String TAG_VOLTO = "(VOLTO)";
    private static final String TAG_ISSTO = "(ISSTO)";
    private static final String TAG_ISSUES = "(ISSUES)";
    private static final String TAG_ANNOTE = "(ANNOTE)";
    private static final String TAG_PUBLISHER = "(PUBLISHER)";
    private static final String TAG_RQTRORDER = "(RQTRORDER)";
    private static final String TAG_RQTRID = "(RQTRID)";
    private static final String TAG_RQTRNAME = "(RQTRNAME)";
    private static final String TAG_RQTRDEPT = "(RQTRDEPT)";
    private static final String TAG_DEPTDESC = "(DEPTDESC)";
    private static final String TAG_NO = "(NO)";
    private static final String TAG_ISBN = "(ISBN)";
    private static final String TAG_STARTDATE = "(STARTDATE)";
    private static final String TAG_FREQUENCY = "(FREQUENCY)";
    private static final String TAG_ISSCOPYNO = "(ISSCOPYNO)";
    private static final String TAG_ISSVOLUME = "(ISSVOLUME)";
    private static final String TAG_ISSLABEL = "(ISSLABEL)";
    private static final String TAG_ISSTATUS = "(ISSTATUS)";
    private static final String TAG_ISSTATUS1 = "(ISSTATUS1)";
    private static final String TAG_ISSTATUS2 = "(ISSTATUS2)";
    private static final String TAG_ISSTATUS3 = "(ISSTATUS3)";
    private static final String TAG_INVOICE = "(INVOICE)";
    private static final String TAG_DESC = "(DESC)";
    private static final String TAG_FOREIGNAMOUNT = "(FOREIGNAMOUNT)";
    private static final String TAG_ITEMCOUNT = "(ITEMCOUNT)";
    private static final String TAG_VOUCHER = "(VOUCHER)";
    private static final String TAG_VCHDATE = "(VCHDATE)";
    private static final String TAG_REQUESTNO = "(REQUESTNO)";
    private static final String TAG_REQUESTOR = "(REQUESTOR)";
    private static final String TAG_REQUESTORNAME = "(REQUESTORNAME)";
    private static final String TAG_REMARKS = "(REMARKS)";
    private static final String TAG_AUTHOR = "(AUTHOR)";
    private static final String TAG_PUBLICATION = "(PUBLICATION)";
    private static final String TAG_EDITION = "(EDITION)";
    private static final String TAG_COPIES = "(COPIES)";
    private static final String TAG_SETS = "(SETS)";
    private static final String TAG_RATING = "(RATING)";

    private static String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    }

    public static String PreviewLetter(Library library, Library vendorInfo, List<AssignOrderItems> assignOrderItems, Library sender, String ref, Library letterTitile, Library hod, Library orderSpan, Library batchrequstor, Library orderTotal, List<AssignOrderItems> assignRequestorDetail, String letterId) {
        System.out.println("In letter SERIAL PO PREVIEW");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
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
        replaceInfo.put(TAG_VENDORNAME, vendorInfo.getName());
        replaceInfo.put(TAG_VENDORADDRESS1, vendorInfo.getAddress1());
        replaceInfo.put(TAG_VENDORADDRESS2, vendorInfo.getAddress2());
        replaceInfo.put(TAG_VENDORADDRESS3, vendorInfo.getAddress3());
        replaceInfo.put(TAG_VENDORPOSTCODE, vendorInfo.getPoscode());
        replaceInfo.put(TAG_VENDORTELEPHONE, vendorInfo.getTel());
        replaceInfo.put(TAG_VENDORCONTACT, vendorInfo.getPERINC());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_REFERENCENO, ref);
        replaceInfo.put(TAG_ORDDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_ORDERTOTAL, orderTotal.gettolOrder());
        replaceInfo.put(TAG_ORDERFOREIGN, orderTotal.getorderForeign());
        replaceInfo.put(TAG_ORDERTOTALINWORD, orderTotal.getinword());
        replaceInfo.put(TAG_TOTALQUANTITY, orderTotal.gettolQty());
        System.out.println("SPANNN" + orderSpan.getName());
        int orderSpanInt = Integer.parseInt(orderSpan.getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(5, orderSpanInt);
        System.out.println(dateFormat.format(c.getTime()));
        replaceInfo.put(TAG_EXPDATE, dateFormat.format(c.getTime()));
        replaceInfo.put(TAG_LOGINOFFICER, sender.getName());
        replaceInfo.put(TAG_OFFICERNAME, hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_STAFFNAME, sender.getName());
        replaceInfo.put(TAG_STAFFDESIGNATION, sender.getdesgination());
        replaceInfo.put(TAG_HODNAME, hod.getName());
        replaceInfo.put(TAG_HODDESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_UNITCURRENCY, orderTotal.getforex());
        String textBuilder = null;
        if (letterId.equals("ES04")) {
            textBuilder = Document.openFile("ES04.html", assignOrderItems, replaceInfo, "ES04", assignRequestorDetail);
        } else if (letterId.equals("ES01")) {
            textBuilder = Document.openFile("ES01.html", assignOrderItems, replaceInfo, "ES01", assignRequestorDetail);
        } else if (letterId.equals("ES05")) {
            textBuilder = Document.openFile("ES05.html", assignOrderItems, replaceInfo, "ES05", assignRequestorDetail);
        } else if (letterId.equals("Es03")) {
            textBuilder = Document.openFile("ES03.html", assignOrderItems, replaceInfo, "ES03", assignRequestorDetail);
        }
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFile(String filename, List<AssignOrderItems> assignOrderItems, HashMap<String, String> replaceInfo, String action, List<AssignOrderItems> assignRequestorDetail) {
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
                    if (action.equals("ES04")) {
                        test = Document.arrayData(html.toString(), assignOrderItems);
                    } else if (action.equals("ES01")) {
                        test = Document.arrayData(html.toString(), assignOrderItems);
                    } else if (action.equals("ES05")) {
                        test = Document.arrayData(html.toString(), assignOrderItems);
                    } else if (action.equals("ES03")) {
                        test = Document.arrayData(html.toString(), assignOrderItems);
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

    public static String arrayData(String html, List<AssignOrderItems> assignOrderItems) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            int i = 0;
            while (i < assignOrderItems.size()) {
                trial.append(html.toString().replace(TAG_NO, assignOrderItems.get(i).getNO()).replace(TAG_ORDERNO, assignOrderItems.get(i).getORDERNO()).replace(TAG_ISSN, assignOrderItems.get(i).getISSN()).replace(TAG_TITLE, assignOrderItems.get(i).getTITLE()).replace(TAG_QUANTITY, assignOrderItems.get(i).getQUANTITY()).replace(TAG_UNITFOREIGNPRICE, assignOrderItems.get(i).getUNITFOREIGNPRICE()).replace(TAG_TOTALFOREIGNPRICE, assignOrderItems.get(i).getTOTALFOREIGNPRICE()).replace(TAG_UNITCURRENCY, assignOrderItems.get(i).getUNITCURRENCY()).replace(TAG_EXCHANGERATE, assignOrderItems.get(i).getEXCHANGERATE()).replace(TAG_VOLFROM, assignOrderItems.get(i).getVOLFROM()).replace(TAG_ISSFROM, assignOrderItems.get(i).getISSFROM()).replace(TAG_VOLTO, assignOrderItems.get(i).getVOLTO()).replace(TAG_ISSTO, assignOrderItems.get(i).getISSTO()).replace(TAG_ISSUES, assignOrderItems.get(i).getISSUES()).replace(TAG_PUBLISHER, assignOrderItems.get(i).getPUBLISHER()).replace(TAG_ANNOTE, assignOrderItems.get(i).getANNOTE()).replace(TAG_RQTRORDER, assignOrderItems.get(i).getRQTRORDER()).replace(TAG_RQTRID, assignOrderItems.get(i).getRQTRID()).replace(TAG_RQTRNAME, assignOrderItems.get(i).getRQTRNAME()).replace(TAG_RQTRDEPT, assignOrderItems.get(i).getRQTRDEPT()).replace(TAG_DEPTDESC, assignOrderItems.get(i).getDEPTDESC()).replace(TAG_TOTALLOCALPRICE, assignOrderItems.get(i).getTOTALLOCALPRICE()).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }

    public static String requestforpaymentDoc(Library library, Library letterTitile, Library vendorInfo, Library hod, String ref, RequestForPayment orderTotal, String rate, List<RequestForPayment> requestpayment, String vendor, String chkForeign) {
        System.out.println("ES18");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LIBRARYNAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_VENDORID, vendorInfo.getvendcode());
        replaceInfo.put(TAG_VENDORNAME, vendorInfo.getName());
        replaceInfo.put(TAG_VENDORADDRESS1, vendorInfo.getAddress1());
        replaceInfo.put(TAG_VENDORADDRESS2, vendorInfo.getAddress2());
        replaceInfo.put(TAG_VENDORADDRESS3, vendorInfo.getAddress3());
        replaceInfo.put(TAG_VENDORCONTACT, vendorInfo.getPERINC());
        replaceInfo.put(TAG_OFFICERNAME, hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_PAYREFERNO, ref);
        replaceInfo.put(TAG_CURRENCYLBL, "Amount");
        replaceInfo.put(TAG_CURRENCY, "");
        replaceInfo.put(TAG_VENDORACCOUNTNO, vendorInfo.getvendAcc());
        replaceInfo.put(TAG_VENDORBANK, vendorInfo.getvendBank());
        replaceInfo.put(TAG_FOREXRATE, rate);
        replaceInfo.put(TAG_FOREIGNTOTAL, orderTotal.gettotalforign());
        replaceInfo.put(TAG_POREFERNO, "");
        replaceInfo.put(TAG_TOTALAMOUNT, orderTotal.gettotalorder());
        String textBuilder = Document.openFileES18("ES18.html", requestpayment, replaceInfo, "ES18", vendor, chkForeign);
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileES18(String filename, List<RequestForPayment> requestpayment, HashMap<String, String> replaceInfo, String action, String vendor, String chkForeign) {
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
                    if (action.equals("ES18")) {
                        System.out.println("testtttt");
                        test = Document.arrayDataRequestForPaymnet(html.toString(), requestpayment, vendor, chkForeign);
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

    public static String arrayDataRequestForPaymnet(String html, List<RequestForPayment> requestpayment, String vendor, String chkForeign) throws SQLException {
        StringBuilder trial;
        block11: {
            ArrayList<String> lines = new ArrayList<String>();
            trial = new StringBuilder();
            if (html == null) break block11;
            int i = 0;
            while (i < requestpayment.size()) {
                String sOrderNo = requestpayment.get(i).getSE07ORDER();
                String sItemDesc = "";
                String sInvoiceNo = "";
                String cForeignAmount = requestpayment.get(i).getSE07AMT();
                double fcForeignAmount = Double.parseDouble(cForeignAmount);
                cForeignAmount = String.format("%.2f", fcForeignAmount);
                String cForignPrice = requestpayment.get(i).getSE07FPRICE();
                double fcForignProce = Double.parseDouble(cForignPrice);
                cForignPrice = String.format("%.2f", fcForignProce);
                String cAmount = requestpayment.get(i).getSE07AMT();
                double fcAmount = Double.parseDouble(cAmount);
                cAmount = String.format("%.2f", fcAmount);
                String sForex = chkForeign == "undefined" ? "MYR" : requestpayment.get(i).getSE07FOREX();
                switch (sOrderNo) {
                    case "CreNote": {
                        String sInvoiceDesc = "";
                        String sCreditNoteNo = AssignOrderItems.getNoteNo(vendor, requestpayment.get(i).getSE07INVNO(), "C");
                        sItemDesc = "Credit Note No.:" + sCreditNoteNo;
                        sInvoiceNo = requestpayment.get(i).getSE07INVNO();
                        break;
                    }
                    case "DebNote": {
                        String sInvoiceDesc = "";
                        String sDebitNoteNo = AssignOrderItems.getNoteNo(vendor, requestpayment.get(i).getSE07INVNO(), "D");
                        sItemDesc = "Debit Note No.:" + sDebitNoteNo;
                        sInvoiceNo = requestpayment.get(i).getSE07INVNO();
                        break;
                    }
                    default: {
                        sItemDesc = requestpayment.get(i).getSE07DESC();
                        sInvoiceNo = requestpayment.get(i).getSE07INVNO();
                    }
                }
                trial.append(html.toString().replace(TAG_NO, Integer.toString(i + 1)).replace(TAG_ITEMCOUNT, Integer.toString(i + 1)).replace(TAG_INVOICE, sInvoiceNo).replace(TAG_DESC, sItemDesc).replace(TAG_QUANTITY, requestpayment.get(i).getSE07COPY()).replace(TAG_FOREIGNAMOUNT, String.valueOf(sForex) + cForeignAmount).replace(TAG_AMOUNT, cAmount).replace(TAG_CURRENCY, sForex).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }

    public static String GrpforpaymentDoc(Library library, Library vendorInfo, Library hod, String ref, Library letterTitile, String currency, RequestForPayment orderTotal, List<RequestForPayment> requestpayment) {
        System.out.println("ES18");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
        replaceInfo.put(TAG_LIBRARYNAME, library.getOrgName());
        replaceInfo.put(TAG_LIBRARYADD1, library.getAddress1());
        replaceInfo.put(TAG_LIBRARYADD2, library.getAddress2());
        replaceInfo.put(TAG_LIBRARYADD3, library.getAddress3());
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_VENDORNAME, vendorInfo.getName());
        replaceInfo.put(TAG_VENDORADDRESS1, vendorInfo.getAddress1());
        replaceInfo.put(TAG_VENDORADDRESS2, vendorInfo.getAddress2());
        replaceInfo.put(TAG_VENDORADDRESS3, vendorInfo.getAddress3());
        replaceInfo.put(TAG_VENDORPOSTCODE, vendorInfo.getAddress3());
        replaceInfo.put(TAG_VENDORCONTACT, vendorInfo.getPERINC());
        replaceInfo.put(TAG_OFFICERNAME, hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_VENDORACCOUNTNO, vendorInfo.getvendAcc());
        replaceInfo.put(TAG_VENDORBANK, vendorInfo.getvendBank());
        replaceInfo.put(TAG_CURRENCYCODE, currency);
        replaceInfo.put(TAG_REFERENCENO, ref);
        replaceInfo.put(TAG_TOTALAMOUNT, orderTotal.gettotalorder());
        replaceInfo.put(TAG_TOTALFOREIGNAMOUNT, orderTotal.gettotalforign());
        String textBuilder = Document.openFileES25("ES25.html", requestpayment, replaceInfo, "ES25");
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileES25(String filename, List<RequestForPayment> requestpayment, HashMap<String, String> replaceInfo, String action) {
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
                    if (action.equals("ES25")) {
                        test = Document.arrayDataGrpforpaymentDoc(html.toString(), requestpayment);
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

    public static String arrayDataGrpforpaymentDoc(String html, List<RequestForPayment> requestpayment) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            int i = 0;
            while (i < requestpayment.size()) {
                String cAmount = requestpayment.get(i).getSE07AMT();
                double fcAmount = Double.parseDouble(cAmount);
                String ccAmount = String.format("%.2f", fcAmount);
                trial.append(html.toString().replace(TAG_NO, Integer.toString(i + 1)).replace(TAG_INVOICE, requestpayment.get(i).getSE07INVNO()).replace(TAG_CURRENCY, requestpayment.get(i).getSE07FOREX()).replace(TAG_AMOUNT, ccAmount).replace(TAG_FOREIGNAMOUNT, requestpayment.get(i).getSE07FPRICE()).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }

    public static String recordforpaymentDoc(Library library, Library letterTitile, Library vendorInfo, String txtChequeNo, String txtChequeDt, Library hod, Library sender, String currency, List<RecordForPayment> ordering, String voucherno, String voucherdate) {
        System.out.println("ES19");
        String text = "";
        StringBuilder html = new StringBuilder();
        HashMap<String, String> replaceInfo = new HashMap<String, String>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        replaceInfo.put(TAG_LETTERSUBJECT, letterTitile.getName());
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
        replaceInfo.put(TAG_LETTERDATE, DateTime.getSysTodayDate());
        replaceInfo.put(TAG_LETTERTIME, DateTime.getCurrentTime());
        replaceInfo.put(TAG_VENDORID, vendorInfo.getvendcode());
        replaceInfo.put(TAG_VENDORNAME, vendorInfo.getName());
        replaceInfo.put(TAG_VENDORADDRESS1, vendorInfo.getAddress1());
        replaceInfo.put(TAG_VENDORADDRESS2, vendorInfo.getAddress2());
        replaceInfo.put(TAG_VENDORADDRESS3, vendorInfo.getAddress3());
        replaceInfo.put(TAG_VENDORPOSTCODE, vendorInfo.getPoscode());
        replaceInfo.put(TAG_VENDORTELEPHONE, vendorInfo.getTel());
        replaceInfo.put(TAG_VENDORCONTACT, vendorInfo.getPERINC());
        replaceInfo.put(TAG_BANKDRAFT, txtChequeNo);
        replaceInfo.put(TAG_DRAFTDATE, txtChequeDt);
        replaceInfo.put(TAG_FOREX, currency);
        double totalLPrice = 0.0;
        boolean count = true;
        for (RecordForPayment i : ordering) {
            totalLPrice += Double.parseDouble(i.getSE07AMT());
        }
        replaceInfo.put(TAG_TOTALAMOUNT, String.format("%.2f", totalLPrice));
        replaceInfo.put(TAG_OFFICERNAME, hod.getName());
        replaceInfo.put(TAG_DESIGNATION, hod.getdesgination());
        replaceInfo.put(TAG_STAFFNAME, sender.getName());
        replaceInfo.put(TAG_STAFFDESIGNATION, sender.getdesgination());
        replaceInfo.put(TAG_HODNAME, hod.getName());
        replaceInfo.put(TAG_HODDESIGNATION, hod.getdesgination());
        String textBuilder = Document.openFileES19("ES19.html", ordering, replaceInfo, "ES19", voucherno, voucherdate);
        System.out.println(textBuilder);
        return new String(textBuilder);
    }

    public static String openFileES19(String filename, List<RecordForPayment> ordering, HashMap<String, String> replaceInfo, String action, String voucherno, String voucherdate) {
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
                    if (action.equals("ES19")) {
                        test = Document.arrayDatarecordforpaymentDoc(html.toString(), ordering, voucherno, voucherdate);
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

    public static String arrayDatarecordforpaymentDoc(String html, List<RecordForPayment> ordering, String voucherno, String voucherdate) throws SQLException {
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder trial = new StringBuilder();
        if (html != null) {
            int i = 0;
            while (i < ordering.size()) {
                trial.append(html.toString().replace(TAG_NO, Integer.toString(i + 1)).replace(TAG_INVOICE, ordering.get(i).getSE07INVNO()).replace(TAG_VOUCHER, voucherno).replace(TAG_VCHDATE, voucherdate).replace(TAG_FOREX, ordering.get(i).getSE07FOREX()).replace(TAG_FOREIGN, ordering.get(i).getSE07FPRICE()).replace(TAG_AMOUNT, ordering.get(i).getSE07AMT()).replace("{LOOP}", ""));
                ++i;
            }
            lines.add(trial.toString());
            System.out.println("Content = " + trial.toString());
        }
        return trial.toString();
    }
}
