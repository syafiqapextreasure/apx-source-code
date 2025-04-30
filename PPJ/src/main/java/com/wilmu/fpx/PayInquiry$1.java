/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.json.JSONObject
 */
package com.wilmu.fpx;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import org.json.JSONObject;

class PayInquiry$1
implements Callable<String> {
    private final /* synthetic */ String val$URL_PayInquiry;
    private final /* synthetic */ String val$data;

    PayInquiry$1(String string, String string2) {
        this.val$URL_PayInquiry = string;
        this.val$data = string2;
    }

    @Override
    public String call() throws Exception {
        Object test = null;
        String p = null;
        boolean i = false;
        do {
            URL url = new URL(this.val$URL_PayInquiry);
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
            writer.write(this.val$data);
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
}
