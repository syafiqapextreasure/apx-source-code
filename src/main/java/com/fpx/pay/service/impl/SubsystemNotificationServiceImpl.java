package com.fpx.pay.service.impl;

import com.fpx.pay.model.*;
import com.fpx.pay.service.SubsystemNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SubsystemNotificationServiceImpl implements SubsystemNotificationService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //@Value("${subsystem.direct-url}")
    private String subsystemDirectUrl;

    @Value("http://localhost:8080/payment/success")
    private String subsystemIndirectUrl;

    private final RestTemplate restTemplate;

    public SubsystemNotificationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendDirectNotification(PaymentNotification paynetNotification) {
        SubsystemNotification notification = createNotification(paynetNotification);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SubsystemNotification> request = new HttpEntity<>(notification, headers);
        
        restTemplate.postForObject(
            subsystemDirectUrl,
            request,
            String.class
        );
    }

    @Override
    public void sendIndirectNotification(PaymentNotification paynetNotification) {
        SubsystemNotification notification = createNotification(paynetNotification);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("orderNo", notification.getOrderNo());
        map.add("status", notification.getStatus());
        map.add("txnTime", notification.getTxnTime().format(DATE_FORMATTER));
        map.add("amount", notification.getAmount().toString());
        map.add("txnReference", notification.getTxnReference());
        map.add("txnId", notification.getTxnId());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        restTemplate.postForObject(
            subsystemIndirectUrl,
            request,
            String.class
        );
    }

    @Override
    public PaymentInquiryResponse handlePaymentInquiry(PaymentInquiryRequest request) {
        // TODO: Implement actual payment status lookup from database
        return PaymentInquiryResponse.builder()
            .subsysId(request.getSubsysId())
            .orderNo(request.getOrderNo())
            .status("PENDING")  // Replace with actual status lookup
            .build();
    }

    private SubsystemNotification createNotification(PaymentNotification paynetNotification) {
        String status = determineStatus(paynetNotification);
        
        return SubsystemNotification.builder()
            .orderNo(paynetNotification.getFpx_sellerOrderNo())
            .status(status)
            .txnReference(status.equals("SUCCESS") ? paynetNotification.getFpx_debitAuthNo() : "")
            .txnId("FPX" + paynetNotification.getFpx_txnId())
            .txnTime(LocalDateTime.parse(paynetNotification.getFpx_txnTime(), 
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
            .amount(paynetNotification.getFpx_txnAmount())
            .build();
    }

    private String determineStatus(PaymentNotification notification) {
        // Implement status determination logic based on debitAuthCode and other factors
        if ("00".equals(notification.getFpx_debitAuthCode())) {
            return "SUCCESS";
        } else if (notification.getFpx_debitAuthCode() == null) {
            return "PENDING_APPROVAL";
        } else {
            return "FAILED";
        }
    }
} 