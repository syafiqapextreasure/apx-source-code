/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.global.InsertRETRXNPay
 *  com.wilmu.global.patron.PatronBasicInfo
 *  com.wilmu.global.print.Config
 *  com.wilmu.pay.GetBillNo
 *  com.wilmu.pay.GetRetrxnTxnoCode
 *  com.wilmu.pay.GetbillnoPatr
 *  com.wilmu.perbaharuikeahlian.InsertGLPATA
 *  com.wilmu.perbaharuikeahlian.UpdateGlpatrafterfpxrenewal
 *  com.wilmu.roombooking.EquipmentCost
 *  com.wilmu.roombooking.RoomDetail
 *  com.wilmu.utilities.RecordTransaction
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu.fpx;

import com.wilmu.fpx.GetRecodeDesc;
import com.wilmu.fpx.PayLoadModal;
import com.wilmu.fpx.UpdateRebill;
import com.wilmu.fpx.bookingRoomDesc;
import com.wilmu.global.InsertRETRXNPay;
import com.wilmu.global.patron.PatronBasicInfo;
import com.wilmu.global.print.Config;
import com.wilmu.pay.GetBillNo;
import com.wilmu.pay.GetRetrxnTxnoCode;
import com.wilmu.pay.GetbillnoPatr;
import com.wilmu.perbaharuikeahlian.InsertGLPATA;
import com.wilmu.perbaharuikeahlian.UpdateGlpatrafterfpxrenewal;
import com.wilmu.roombooking.EquipmentCost;
import com.wilmu.roombooking.RoomDetail;
import com.wilmu.utilities.RecordTransaction;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/indirect"})
public class PayLoadIndirect
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response, PayLoadModal paymentRequest) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PayLoadDirect");
        String token = request.getParameter("_token");
        String orderNo = request.getParameter("orderNo");
        String amount = request.getParameter("amount");
        String txnId = request.getParameter("txnId");
        String txnTime = request.getParameter("txnTime");
        String txnReference = request.getParameter("txnReference");
        String status = request.getParameter("status");
        String bankName = request.getParameter("bankName");
        String billdesc = "";
        SimpleDateFormat inputFormats = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat outputFormats = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date datess = null;
        try {
            datess = inputFormats.parse(txnTime);
        }
        catch (ParseException e2) {
            e2.printStackTrace();
        }
        String formattedDatess = outputFormats.format(datess);
        System.out.println("Formatted Date: " + formattedDatess);
        System.out.println("indirectindirectindirectindirect");
        System.out.println("_token : " + token);
        System.out.println("orderNo : " + orderNo);
        System.out.println("amount : " + amount);
        System.out.println("txnId : " + txnId);
        System.out.println("txnTime : " + txnTime);
        System.out.println("txnReference : " + txnReference);
        System.out.println("status : " + status);
        System.out.println("bankName hhh : " + bankName);
        String oriBillno = GetBillNo.GetbillnoReBill((String)orderNo);
        String[] arrayBillno = oriBillno.split("=");
        String billno = arrayBillno[1].trim();
        String patrBillno = arrayBillno[2].trim();
        String billstats = arrayBillno[3];
        String billemail = arrayBillno[4];
        String billphone = arrayBillno[5];
        StringBuilder htmlTable = new StringBuilder();
        List txnoncode = new ArrayList();
        try {
            txnoncode = GetRetrxnTxnoCode.GetSQLStmtGetRetrxnTxnoCode((String)billno, (String)"");
            System.out.println("txnoncode : " + txnoncode);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if (status.equals("FAILED")) {
            UpdateRebill.updateReBill(orderNo, "", txnTime, txnReference, status, bankName, "Indirect");
        } else if (status.equals("SUCCESS")) {
            System.out.println("billnobillno : " + billno);
            if (billstats.equals("") || billstats.equals(" ")) {
                boolean isSuccess = UpdateRebill.updateReBill(orderNo, txnId, txnTime, txnReference, status, bankName, "Indirect");
                int i = 0;
                while (i < txnoncode.size()) {
                    String newcode = ((GetRetrxnTxnoCode)txnoncode.get(i)).getCode();
                    int icode = Integer.parseInt(newcode);
                    String newamt = ((GetRetrxnTxnoCode)txnoncode.get(i)).getAmt();
                    String newtxno = ((GetRetrxnTxnoCode)txnoncode.get(i)).getTxno();
                    String offoidid = ((GetRetrxnTxnoCode)txnoncode.get(i)).getOfficeid();
                    String gsModule = "CI";
                    System.out.println("newcodenewcode : " + newcode);
                    boolean isSuccess2 = InsertRETRXNPay.insertRETRNX((String)String.valueOf(++icode), (String)newamt, (String)newtxno, (String)txnId, (String)"FPX", (String)patrBillno, (String)billno, (String)offoidid);
                    RecordTransaction.InsertAudit((String)gsModule, (String)"REI0001", (String)(String.valueOf(orderNo) + ":" + txnId), (String)"FPX");
                    if (!(newcode.equals("100") && newcode.equals("110") && newcode.equals("120") && newcode.equals("130"))) {
                        String billpatr = GetbillnoPatr.GetbillnoRePatr((String)orderNo);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                        Date date = new Date();
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);
                        c.add(1, 1);
                        Date currentDatePlusOne = c.getTime();
                        System.out.println("line211: " + dateFormat.format(currentDatePlusOne));
                        boolean bSuccessful3 = UpdateGlpatrafterfpxrenewal.UpdatePatrExpdateStatRenewal((String)billpatr, (String)dateFormat.format(currentDatePlusOne));
                        if (bSuccessful3) {
                            String gsModule2 = "GL";
                            String expdate = arrayBillno[6].trim();
                            RecordTransaction.InsertAudit((String)gsModule2, (String)"GLU0006", (String)(String.valueOf(billpatr) + ", " + expdate + ", " + txnTime + ", " + dateFormat.format(date)), (String)"FPX");
                            String colmm = "GL14EXPDATE";
                            String mode2 = "U";
                            InsertGLPATA.insertGLPATA((String)billpatr, (String)colmm, (String)expdate, (String)dateFormat.format(currentDatePlusOne), (String)"FPX", (String)mode2);
                        }
                    } else if (status.equals("PENDING_APPROVAL")) {
                        UpdateRebill.updateReBill(orderNo, txnId, txnTime, txnReference, status, bankName, "Indirect");
                    }
                    System.out.println("billdesc4 : " + billdesc);
                    htmlTable.append("\t\t<tr>\n");
                    htmlTable.append("\t\t\t<td><a class=\"cut\">-</a><span contenteditable>").append(i + 1).append("</span></td>\n");
                    htmlTable.append("\t\t\t<td><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(i)).getTxnodesc()).append("</span></td>\n");
                    htmlTable.append("\t\t\t<td><span data-prefix></span><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(i)).getAmt()).append(".00</span></td>\n");
                    htmlTable.append("\t\t</tr>\n");
                    ++i;
                }
            }
        } else {
            UpdateRebill.updateReBill(orderNo, "", txnTime, txnReference, status, bankName, "Indirect");
        }
        response.setCharacterEncoding("UTF8");
        response.getWriter().print(orderNo);
        List txnoncode2 = new ArrayList();
        try {
            txnoncode2 = GetRetrxnTxnoCode.GetSQLStmtGetRetrxnTxnoCode((String)billno, (String)"pay");
            System.out.println("txnoncode : " + txnoncode2);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        int x = 0;
        while (x < txnoncode2.size()) {
            if (((GetRetrxnTxnoCode)txnoncode2.get(0)).getCode().equals("201") || ((GetRetrxnTxnoCode)txnoncode2.get(0)).getCode().equals("211") || ((GetRetrxnTxnoCode)txnoncode2.get(0)).getCode().equals("221")) {
                System.out.println("111111 : ");
                htmlTable.append("\t\t<tr>\n");
                htmlTable.append("\t\t\t<td><a class=\"cut\">-</a><span contenteditable>").append(x + 1).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span contenteditable>").append(String.valueOf(((GetRetrxnTxnoCode)txnoncode.get(x)).getTxnodesc()) + " " + Config.get((String)"resitdescregister")).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span data-prefix></span><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(x)).getAmt()).append("</span></td>\n");
                htmlTable.append("\t\t</tr>\n");
            } else if (((GetRetrxnTxnoCode)txnoncode2.get(0)).getCode().equals("701")) {
                System.out.println("22222 : ");
                String bookingid = ((GetRetrxnTxnoCode)txnoncode.get(0)).getReferdesc();
                String roomdetailStr = null;
                String roomdetail = RoomDetail.getroomdetail((String)bookingid);
                roomdetailStr = "<br><b>Butiran Tempahan</b><br> Caj Bilik : " + roomdetail;
                List equipcost = new ArrayList();
                try {
                    equipcost = EquipmentCost.GetSQLStmtGetEquipmentCost((String)bookingid);
                    System.out.println("equipcost : " + equipcost);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                StringBuilder roomequipcostBuilder = new StringBuilder();
                int z = 0;
                while (z < equipcost.size()) {
                    EquipmentCost equipmentCost = (EquipmentCost)equipcost.get(z);
                    String equipmentCostStr = "<br>" + equipmentCost.getItemdesc() + " : " + equipmentCost.getUnit() + " - " + equipmentCost.getCost();
                    roomequipcostBuilder.append(equipmentCostStr);
                    ++z;
                }
                String roomequipcost = roomequipcostBuilder.toString();
                System.out.println("roomequipcost : " + roomequipcost);
                htmlTable.append("\t\t<tr>\n");
                htmlTable.append("\t\t\t<td><a class=\"cut\">-</a><span contenteditable>").append(x + 1).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span contenteditable>").append(String.valueOf(((GetRetrxnTxnoCode)txnoncode.get(x)).getTxnodesc()) + roomdetailStr + roomequipcost).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span data-prefix></span><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(x)).getAmt()).append("</span></td>\n");
                htmlTable.append("\t\t</tr>\n");
            } else {
                System.out.println("33333 : ");
                htmlTable.append("\t\t<tr>\n");
                htmlTable.append("\t\t\t<td><a class=\"cut\">-</a><span contenteditable>").append(x + 1).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(x)).getTxnodesc()).append("</span></td>\n");
                htmlTable.append("\t\t\t<td><span data-prefix></span><span contenteditable>").append(((GetRetrxnTxnoCode)txnoncode.get(x)).getAmt()).append("</span></td>\n");
                htmlTable.append("\t\t</tr>\n");
            }
            ++x;
        }
        System.out.println("patrBillnopatrBillno : " + patrBillno);
        String addd22 = null;
        String patronName = null;
        try {
            List patradd = PatronBasicInfo.GetSQLStmtPatrBacisInfo((String)patrBillno);
            patronName = ((PatronBasicInfo)patradd.get(0)).getName();
            String add1 = ((PatronBasicInfo)patradd.get(0)).getAdd1();
            String add2 = ((PatronBasicInfo)patradd.get(0)).getAdd2();
            String add3 = ((PatronBasicInfo)patradd.get(0)).getAdd3();
            String postcode = ((PatronBasicInfo)patradd.get(0)).getPostcode();
            String town = ((PatronBasicInfo)patradd.get(0)).getTownDesc();
            addd22 = String.valueOf(add1) + "<br>" + add2 + "<br>" + add3 + "<br>" + postcode + ", " + town;
            System.out.println("patremailpatremail : " + addd22);
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        String newcode2 = ((GetRetrxnTxnoCode)txnoncode.get(0)).getCode();
        if (!(newcode2.equals("100") && newcode2.equals("110") && newcode2.equals("120") && newcode2.equals("130"))) {
            if (!newcode2.equals("700")) {
                billdesc = GetRecodeDesc.re01code(newcode2);
                System.out.println("billdesc 2 : " + billdesc);
            } else {
                billdesc = bookingRoomDesc.roomdesc(billno);
                System.out.println("billdesc1 : " + billdesc);
            }
        } else {
            billdesc = "Bayaran Bahan Rosak/Hilang";
            System.out.println("billdesc3 : " + billdesc);
        }
        System.out.println("billdesc : " + billdesc);
        PayLoadModal payloadmodal = new PayLoadModal(orderNo, amount, txnId, txnTime, txnReference, status, bankName, billemail, billphone, addd22, patrBillno);
        String htmlTableAsString = htmlTable.toString();
        byte[] utf8Bytes = htmlTableAsString.getBytes("UTF-8");
        String decodedHtmlTable = new String(utf8Bytes, "UTF-8");
        System.out.println("decodedHtmlTable : " + decodedHtmlTable);
        RequestDispatcher rd = request.getRequestDispatcher("/template?MODULE=fpx&ACTION=Receipt.jsp?orderNo=" + orderNo + "&amount=" + amount + "&txnId=" + txnId + "&txnTime=" + formattedDatess + "&txnReference=" + txnReference + "&status=" + status + "&bankName=" + bankName + "&pname=" + patrBillno + "&phone=" + billphone + "&mail=" + billemail + "&add=" + addd22 + "&billdesc=" + billdesc + "&patronName=" + patronName + "&tabless=" + decodedHtmlTable);
        rd.forward((ServletRequest)request, (ServletResponse)response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
