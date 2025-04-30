/*
 * Decompiled with CFR 0.152.
 */
package com.wilmu.fpx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class curltesting {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://fpxuat.ppj.gov.my/pay-api/pay/access");
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "application/json");
        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write("{\"payload\":{\"subsysId\": \"PPK\",\"password\": \"pPk@2022WLm\",\"orderNo\": \"PPK0000000009\",\"description\": \"PPK0000000009\",\"txnTime\": \"2023-02-16 11:43:06\",\"feeCode\": \"\",\"amount\": \"1.00\"}}");
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();
        InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream() : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}
