/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.wilmu.global.print.Config
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.json.JSONObject
 */
package com.wilmu.fpx;

import com.wilmu.fpx.InsertReBillAudit;
import com.wilmu.fpx.UpdateRebillStat;
import com.wilmu.global.print.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(value={"/PayInquiry"})
public class PayInquiry
extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String data = request.getParameter("data");
        String testTimerTotal = request.getParameter("testTimerTotal");
        System.out.println("PayInquiry" + data);
        final String URL_PayInquiry = Config.get((String)"payInquiry");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        URL url = new URL(URL_PayInquiry);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write(data);
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();
        InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream() : httpConn.getErrorStream();
        Scanner s1 = new Scanner(responseStream).useDelimiter("\\A");
        String responseInsert = s1.hasNext() ? s1.next() : "";
        System.out.println("PayInquiry" + responseInsert);
        JSONObject responseObject = new JSONObject(responseInsert);
        Object subsysIdObj = responseObject.getJSONObject("payload").opt("subsysId");
        String subsysId = subsysIdObj != null ? subsysIdObj.toString() : null;
        System.out.println("subsysId " + subsysId);
        Object bankIdObj = responseObject.getJSONObject("payload").opt("bankId");
        String bankId = bankIdObj != null ? bankIdObj.toString() : null;
        Object bankNameObj = responseObject.getJSONObject("payload").opt("bankName");
        String bankName = bankNameObj != null ? bankNameObj.toString() : null;
        Object txnIdObj = responseObject.getJSONObject("payload").opt("txnId");
        String txnId = txnIdObj != null ? txnIdObj.toString() : null;
        Object txnTimeObj = responseObject.getJSONObject("payload").opt("txnTime");
        String txnTime = txnTimeObj != null ? txnTimeObj.toString() : null;
        Object sellerTxnTimeObj = responseObject.getJSONObject("payload").opt("sellerTxnTime");
        String sellerTxnTime = sellerTxnTimeObj != null ? sellerTxnTimeObj.toString() : null;
        Object orderNoObj = responseObject.getJSONObject("payload").opt("orderNo");
        String orderNo = orderNoObj != null ? orderNoObj.toString() : null;
        Object exOrderNoObj = responseObject.getJSONObject("payload").opt("exOrderNo");
        String exOrderNo = exOrderNoObj != null ? exOrderNoObj.toString() : null;
        Object statusObj = responseObject.getJSONObject("payload").opt("status");
        String status = statusObj != null ? statusObj.toString() : null;
        Object successObj = responseObject.getJSONObject("payload").opt("success");
        String success = successObj != null ? successObj.toString() : null;
        Object amountObj = responseObject.getJSONObject("payload").opt("amount");
        String amount = amountObj != null ? amountObj.toString() : null;
        Object ppjTransNoObj = responseObject.getJSONObject("payload").opt("ppjTransNo");
        String ppjTransNo = ppjTransNoObj != null ? ppjTransNoObj.toString() : null;
        Object sapDocNoObj = responseObject.getJSONObject("payload").opt("sapDocNo");
        String sapDocNo = sapDocNoObj != null ? sapDocNoObj.toString() : null;
        InsertReBillAudit.addrebillaudit(subsysId, bankId, bankName, txnId, txnTime, sellerTxnTime, orderNo, exOrderNo, status, success, amount, ppjTransNo, sapDocNo);
        if (status.equals("PENDING")) {
            UpdateRebillStat.updateReBillstat(orderNo, "PENDING");
        } else if (status.equals("null")) {
            UpdateRebillStat.updateReBillstat(orderNo, "pending");
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>(){

            @Override
            public String call() throws Exception {
                Object test = null;
                String p = null;
                boolean i = false;
                do {
                    URL url = new URL(URL_PayInquiry);
                    HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                    httpConn.setRequestMethod("POST");
                    httpConn.setRequestProperty("Content-Type", "application/json");
                    httpConn.setDoOutput(true);
                    OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    httpConn.getOutputStream().close();
                    InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream() : httpConn.getErrorStream();
                    Scanner s = new Scanner(responseStream).useDelimiter("\\A");
                    String response2 = s.hasNext() ? s.next() : "";
                    System.out.println("PayInquiry" + response2);
                    JSONObject responseObject = new JSONObject(response2);
                    Object statusObj = responseObject.getJSONObject("payload").opt("status");
                    System.out.println("statusObj " + statusObj);
                    String status = statusObj != null ? statusObj.toString() : null;
                    System.out.println("status " + status);
                    p = status == "pending" || status == "" || status == null || status == "null" ? "B" : "A";
                    System.out.println(p);
                    Thread.sleep(1000L);
                } while (p == "B");
                System.out.println("Task started");
                System.out.println("Task completed");
                return "Hello";
            }
        });
        int timerGet = Integer.parseInt(testTimerTotal);
        int timerGetFinal = timerGet * 1000;
        try {
            String result = future.get(14000L, TimeUnit.MILLISECONDS);
            out.println("<html><body>");
            out.println("<h2>" + result + "</h2>");
            out.println("</body></html>");
            System.out.println("LINE 98: Task completed successfully" + result);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            out.println("<html><body>");
            out.println("<h2>Task timed out or was interrupted!</h2>");
            out.println("</body></html>");
            System.out.println("LINE 103: Task timed out or was interrupted!");
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1000L, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            executor.shutdownNow();
        }
        try {
            System.out.println(future.get());
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public String getServletInfo() {
        return "Short description";
    }
}
