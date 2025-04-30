/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.databind.ObjectMapper
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.entity.StringEntity
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClientBuilder
 *  org.apache.http.message.BasicHeader
 *  org.apache.http.util.EntityUtils
 */
package com.wilmu.fpx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilmu.fpx.PayLoadModal;
import com.wilmu.fpx.PaymentResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class PayLoadDirect2 {
    private static final String API_ENDPOINT = "https://wilmudev.ppj.gov.my/fpx/direct";

    public PaymentResponse makePayment(PayLoadModal paymentRequest) throws Exception {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(API_ENDPOINT);
        request.addHeader((Header)new BasicHeader("Content-Type", "application/json"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString((Object)paymentRequest);
        System.out.println("testinggg line 38" + json);
        request.setEntity((HttpEntity)new StringEntity(json));
        String responseString = EntityUtils.toString((HttpEntity)client.execute((HttpUriRequest)request).getEntity());
        System.out.println("testinggg line 48" + responseString);
        PaymentResponse paymentResponse = (PaymentResponse)objectMapper.readValue(responseString, PaymentResponse.class);
        return paymentResponse;
    }
}
