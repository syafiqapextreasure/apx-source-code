package com.fpx.pay.service.impl;

import com.fpx.pay.model.PaymentAccessPayload;
import com.fpx.pay.model.PaymentAccessResponse;
import com.fpx.pay.service.PaymentAccessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentAccessServiceImpl implements PaymentAccessService {

    @Value("${payment.paynet.api-url}")
    private String paynetApiUrl;

    private final RestTemplate restTemplate;

    public PaymentAccessServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PaymentAccessResponse requestAccess(PaymentAccessPayload payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentAccessPayload> request = new HttpEntity<>(payload, headers);

        return restTemplate.postForObject(
            paynetApiUrl + "/pay-api/pay/access",
            request,
            PaymentAccessResponse.class
        );
    }
} 