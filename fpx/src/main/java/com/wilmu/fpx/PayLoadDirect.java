/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  com.wilmu.global.InsertRETRXNPay
 *  com.wilmu.pay.GetBillNo
 *  com.wilmu.pay.GetRetrxnTxnoCode
 *  com.wilmu.pay.GetbillnoPatr
 *  com.wilmu.perbaharuikeahlian.InsertGLPATA
 *  com.wilmu.perbaharuikeahlian.UpdateGlpatrafterfpxrenewal
 *  com.wilmu.utilities.RecordTransaction
 *  javax.servlet.ServletException
 *  javax.servlet.ServletInputStream
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.wilmu.fpx;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmu.fpx.UpdateRebill;
import com.wilmu.global.InsertRETRXNPay;
import com.wilmu.pay.GetBillNo;
import com.wilmu.pay.GetRetrxnTxnoCode;
import com.wilmu.pay.GetbillnoPatr;
import com.wilmu.perbaharuikeahlian.InsertGLPATA;
import com.wilmu.perbaharuikeahlian.UpdateGlpatrafterfpxrenewal;
import com.wilmu.utilities.RecordTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/direct"})
public class PayLoadDirect
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        String orderNo = request.getParameter("orderNo");
        String amount = request.getParameter("amount");
        String txnId = request.getParameter("txnId");
        String txnTime = request.getParameter("txnTime");
        String txnReference = request.getParameter("txnReference");
        String status = request.getParameter("status");
        String bankName = request.getParameter("bankName");
        System.out.println("hello : " + _token);
        System.out.println("age : " + bankName);
        response.setCharacterEncoding("UTF8");
        response.getWriter().print("hii there get");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try (ServletInputStream inputStream = request.getInputStream();){
                ObjectMapper objectMapper = new ObjectMapper();
                Map payloadMap = (Map)objectMapper.readValue((InputStream)inputStream, (TypeReference)new TypeReference<Map<String, Object>>(){});
                Map payload = (Map)payloadMap.get("payload");
                String orderNo = (String)payload.get("orderNo");
                String amount = (String)payload.get("amount");
                String txnId = (String)payload.get("txnId");
                String txnTime = (String)payload.get("txnTime");
                String txnReference = (String)payload.get("txnReference");
                String status = (String)payload.get("status");
                String bankName = (String)payload.get("bankName");
                System.out.println("orderNo: " + orderNo);
                System.out.println("amount: " + amount);
                System.out.println("txnId: " + txnId);
                System.out.println("txnTime: " + txnTime);
                System.out.println("txnReference: " + txnReference);
                System.out.println("status: " + status);
                System.out.println("bankName: " + bankName);
                String oriBillno = GetBillNo.GetbillnoReBill((String)orderNo);
                String[] arrayBillno = oriBillno.split("=");
                String billno = arrayBillno[1].trim();
                String patrBillno = arrayBillno[2].trim();
                String officeid = arrayBillno[2].trim();
                boolean isSuccess = UpdateRebill.updateReBill(orderNo, txnId, txnTime, txnReference, status, bankName, "Direct");
                if (status.equals("SUCCESS") && isSuccess) {
                    List txnoncode = new ArrayList();
                    txnoncode = GetRetrxnTxnoCode.GetSQLStmtGetRetrxnTxnoCode((String)billno, (String)"");
                    int i = 0;
                    while (i < txnoncode.size()) {
                        String newcode = ((GetRetrxnTxnoCode)txnoncode.get(i)).getCode();
                        int icode = Integer.parseInt(newcode);
                        String newamt = ((GetRetrxnTxnoCode)txnoncode.get(i)).getAmt();
                        String newtxno = ((GetRetrxnTxnoCode)txnoncode.get(i)).getTxno();
                        String offoidid = ((GetRetrxnTxnoCode)txnoncode.get(i)).getOfficeid();
                        String gsModule = "CI";
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
                                RecordTransaction.InsertAudit((String)gsModule2, (String)"GLU0006", (String)(String.valueOf(billpatr) + ", " + expdate + ", " + txnTime + ", " + dateFormat.format(currentDatePlusOne)), (String)"FPX");
                                String colmm = "GL14EXPDATE";
                                String mode2 = "U";
                                InsertGLPATA.insertGLPATA((String)billpatr, (String)colmm, (String)expdate, (String)dateFormat.format(currentDatePlusOne), (String)"FPX", (String)mode2);
                            }
                        }
                        ++i;
                    }
                }
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print("Hello from doPost");
            }
            catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
            response.sendError(400, "Error reading input stream");
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
