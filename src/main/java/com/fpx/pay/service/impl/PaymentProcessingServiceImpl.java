package com.fpx.pay.service.impl;

import com.fpx.pay.model.PaymentRequest;
import com.fpx.pay.model.PaymentNotification;
import com.fpx.pay.service.PaymentProcessingService;
import com.fpx.pay.service.CorporatePaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class PaymentProcessingServiceImpl implements PaymentProcessingService {

    private static final String PAYMENT_MSG_TYPE = "AR";
    private static final String PAYMENT_VERSION = "6.0"; // Update according to PayNet's requirements
    private static final String CORPORATE_TOKEN = "02";

    //@Value("${payment.paynet.seller-id}")
    private String sellerId;

   // @Value("${payment.paynet.seller-bank-code}")
    private String sellerBankCode;

    @Value("${payment.paynet.api-url}")
    private String paynetApiUrl;

    private final RestTemplate restTemplate;
    private final CorporatePaymentService corporatePaymentService;

    public PaymentProcessingServiceImpl(RestTemplate restTemplate, CorporatePaymentService corporatePaymentService) {
        this.restTemplate = restTemplate;
        this.corporatePaymentService = corporatePaymentService;
    }

    @Override
    public void processPayment(PaymentRequest request) {
        // For corporate payments, create a tracking record
        if (CORPORATE_TOKEN.equals(request.getFpx_msgToken())) {
            corporatePaymentService.createCorporatePayment(request);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = convertRequestToFormData(request);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

        restTemplate.postForObject(
            paynetApiUrl + "/FPXMain/seller2DReceiver.jsp",
            httpEntity,
            String.class
        );
    }

    private MultiValueMap<String, String> convertRequestToFormData(PaymentRequest request) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        
        // Add all required parameters
        map.add("fpx_msgType", PAYMENT_MSG_TYPE);
        map.add("fpx_msgToken", request.getFpx_msgToken());
        map.add("fpx_sellerId", sellerId);
        map.add("fpx_sellerExOrderNo", request.getFpx_sellerExOrderNo());
        map.add("fpx_sellerTxnTime", request.getFpx_sellerTxnTime());
        map.add("fpx_sellerOrderNo", request.getFpx_sellerOrderNo());
        map.add("fpx_sellerBankCode", sellerBankCode);
        map.add("fpx_txnCurrency", "MYR");
        map.add("fpx_txnAmount", request.getFpx_txnAmount().toString());
        map.add("fpx_buyerBankId", request.getFpx_buyerBankId());
        map.add("fpx_productDesc", request.getFpx_productDesc());
        map.add("fpx_version", PAYMENT_VERSION);

        // Calculate and add checksum
        String checksum = calculateChecksum(
            PAYMENT_MSG_TYPE,
            request.getFpx_msgToken(),
            sellerId,
            request.getFpx_sellerExOrderNo(),
            request.getFpx_sellerTxnTime(),
            request.getFpx_sellerOrderNo(),
            sellerBankCode,
            "MYR",
            request.getFpx_txnAmount().toString(),
            request.getFpx_buyerBankId(),
            request.getFpx_productDesc(),
            PAYMENT_VERSION
        );
        map.add("fpx_checkSum", checksum);

        // Add optional parameters if present
        if (request.getFpx_buyerEmail() != null) {
            map.add("fpx_buyerEmail", request.getFpx_buyerEmail());
        }
        if (request.getFpx_buyerName() != null) {
            map.add("fpx_buyerName", request.getFpx_buyerName());
        }

        return map;
    }

    @Override
    public void handleDirectNotification(PaymentNotification notification) {
        validateNotificationChecksum(notification);
        
        if (CORPORATE_TOKEN.equals(notification.getFpx_msgToken())) {
            // Handle corporate payment notification
            corporatePaymentService.handlePaymentApproval(notification);
        }
        
        // Update payment status in database
        updatePaymentStatus(notification);
        
        // Notify subsystem of payment status
        notifySubsystem(notification);
    }

    @Override
    public void handleIndirectNotification(PaymentNotification notification) {
        // For corporate payments, we only handle direct notifications
        if (!CORPORATE_TOKEN.equals(notification.getFpx_msgToken())) {
            handleDirectNotification(notification);
        }
    }

    private void validateNotificationChecksum(PaymentNotification notification) {
        String calculatedChecksum = calculateChecksum(
            notification.getFpx_msgType(),
            notification.getFpx_msgToken(),
            notification.getFpx_sellerId(),
            notification.getFpx_sellerExOrderNo(),
            notification.getFpx_sellerTxnTime(),
            notification.getFpx_sellerOrderNo(),
            notification.getFpx_sellerBankCode(),
            notification.getFpx_txnCurrency(),
            notification.getFpx_txnAmount().toString(),
            notification.getFpx_txnId().toString(),
            notification.getFpx_txnTime()
        );

        if (!calculatedChecksum.equals(notification.getFpx_checkSum())) {
            throw new RuntimeException("Invalid notification checksum");
        }
    }

    private void updatePaymentStatus(PaymentNotification notification) {
        // TODO: Implement payment status update in database
    }

    private void notifySubsystem(PaymentNotification notification) {
        // TODO: Implement subsystem notification
    }

    @Override
    public String calculateChecksum(String... params) {
        try {
            String data = String.join("", params);
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to calculate checksum", e);
        }
    }
} 